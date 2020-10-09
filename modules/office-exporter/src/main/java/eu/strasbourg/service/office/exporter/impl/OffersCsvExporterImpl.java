package eu.strasbourg.service.office.exporter.impl;

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
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.osgi.service.component.annotations.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
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
		csv.append(LanguageUtil.get(bundle, "offer-id") + ";");
		csv.append(LanguageUtil.get(bundle, "offer-url") + ";");
		csv.append(LanguageUtil.get(bundle, "title") + ";");
		csv.append(LanguageUtil.get(bundle, "description") + ";");
		csv.append(LanguageUtil.get(bundle, "publication-start-date") + ";");
		csv.append(LanguageUtil.get(bundle, "publication-end-date") + ";");
		csv.append(LanguageUtil.get(bundle, "diffusion"));
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
				diffusion = "Publié en " + offer.getTypePublication().getTitle(Locale.FRANCE).toLowerCase();

			csv.append(offer.getOfferId() + ";" + url + ";" + offer.getPost(Locale.FRANCE)
					+ ";" + description + ";" + startDate + ";" + endDate + ";" + diffusion);
			csv.append(CharPool.NEW_LINE);
		}

		String fileName = "interf_totems.csv";
		String fullPath = System.getProperty("java.io.tmpdir") + "/"
				+ fileName;
		File file = new File(fullPath);
		try (PrintWriter printWriter = new PrintWriter(file)) {
			printWriter.print(csv);
			FTPClient ftpClient = new FTPClient();
			ftpClient.connect(StrasbourgPropsUtil.getEJobFTPHost(), Integer.parseInt(StrasbourgPropsUtil.getEJobFTPPort()));
			showServerReply(ftpClient);
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				log.error("Accès au serveur refusé. Code de l'erreur: " + replyCode);
				return false;
			}
			boolean success = ftpClient.login(StrasbourgPropsUtil.getEJobFTPUser(), StrasbourgPropsUtil.getEJobFTPPassword());
			showServerReply(ftpClient);
			if (!success) {
				log.error("Authentification FTP échouée.");
				return false;
			} else {
				if(ftpClient.changeWorkingDirectory(StrasbourgPropsUtil.getEJobFTPUser())) {
					ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
					FileInputStream fileIS = new FileInputStream(fullPath);
					ftpClient.storeFile(fileName, fileIS);
					log.info("Dépôt du fichier effectué.");
				} else {
					log.error("Changement de répertoire échoué.");
					return false;
				}
			}

			ftpClient.logout();
		} catch (IOException e) {
			log.error(e);
			return false;
		}
		log.info("Export FTP effectué.");

		return true;

	}

	private void showServerReply(FTPClient ftp) {
		String[] replies = ftp.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String aReply : replies) {
				log.info("SERVER: " + aReply);
			}
		}
	}

	Log log = LogFactoryUtil.getLog(this.getClass());
}
