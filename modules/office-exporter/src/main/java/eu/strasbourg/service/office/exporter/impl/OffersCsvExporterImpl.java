package eu.strasbourg.service.office.exporter.impl;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.office.exporter.api.OffersCsvExporter;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Component(
	immediate = true,
	property = {},
	service = OffersCsvExporter.class
)
public class OffersCsvExporterImpl implements OffersCsvExporter {
	
	private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
			this.getClass().getClassLoader());

	public boolean exportOffers(List <Offer> offers) {
		StringBundler csv = new StringBundler(); // StringBuilder du CSV

		// Le début est le même pour les deux modes
		csv.append(LanguageUtil.get(bundle, "csv.offer-id") + ";");
		csv.append(LanguageUtil.get(bundle, "csv.offer-url") + ";");
		csv.append(LanguageUtil.get(bundle, "title") + ";");
		csv.append(LanguageUtil.get(bundle, "description") + ";");
		csv.append(LanguageUtil.get(bundle, "csv.publication-start-date") + ";");
		csv.append(LanguageUtil.get(bundle, "csv.publication-end-date") + ";");
		csv.append(LanguageUtil.get(bundle, "csv.diffusion"));
		csv.append(CharPool.NEW_LINE);

		// On construit notre CSV à partir de la liste des offres
		for (Offer offer : offers) {
			String url = StrasbourgPropsUtil.getEJobURLOffer() + offer.getOfferId();
			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");
			String startDate = dateFormat.format(offer.getPublicationStartDate());
			String endDate = dateFormat.format(offer.getPublicationEndDate());
			String description = "Du " + startDate + " au " + endDate;
			String diffusion = "";
			if(Validator.isNotNull(offer.getTypePublication()))
				diffusion = LanguageUtil.get(bundle, "csv.publicated") + offer.getTypePublication().getTitle(Locale.FRANCE).toLowerCase();

			csv.append(offer.getOfferId() + ";" + url + ";" + offer.getPost(Locale.FRANCE)
					+ ";" + description + ";" + startDate + ";" + endDate + ";" + diffusion);
			csv.append(CharPool.NEW_LINE);
		}

		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyMMdd_hhmmss");
		String fileName = "interf_totems"+ dateFormat.format(new Date()) +".csv";
		String fullPath = System.getProperty("java.io.tmpdir") + "/"
				+ fileName;
		File file = new File(fullPath);
		try (PrintWriter printWriter = new PrintWriter(file)) {
			printWriter.print(csv);
		} catch (FileNotFoundException e) {
			log.error(e);
			return false;
		}

		try{
			JSch jsch = new JSch();
			Session session = jsch.getSession( StrasbourgPropsUtil.getEJobFTPUser(), StrasbourgPropsUtil.getEJobFTPHost(), Integer.parseInt(StrasbourgPropsUtil.getEJobFTPPort()) );
			session.setConfig( "PreferredAuthentications", "password" );
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword( StrasbourgPropsUtil.getEJobFTPPassword() );
			session.connect();

			if (!session.isConnected()) {
				log.error("Connexion session échouée.");
				return false;
			} else {
				Channel channel = session.openChannel( "sftp") ;
				ChannelSftp sftp = (ChannelSftp) channel;
				sftp.connect();
				if (!sftp.isConnected()) {
					log.error("Connexion SFTP échouée.");
					return false;
				} else {
					sftp.cd(StrasbourgPropsUtil.getEJobFTPUser());
						FileInputStream fileIS = new FileInputStream(fullPath);
						sftp.put(fileIS, fileName);
						log.info("Dépôt du fichier effectué.");
				}
			}

			session.disconnect();
		} catch (IOException | JSchException | SftpException e) {
			log.error(e);
			return false;
		}
		log.info("Export FTP effectué.");

		return true;

	}

	Log log = LogFactoryUtil.getLog(this.getClass());
}
