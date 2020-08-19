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
import eu.strasbourg.service.ejob.service.OfferLocalService;
import eu.strasbourg.service.office.exporter.api.OffersCsvExporter;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component(
	immediate = true,
	property = {},
	service = OffersCsvExporter.class
)
public class OffersCsvExporterImpl implements OffersCsvExporter {
	
	private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
			this.getClass().getClassLoader());

	@Override
	public void exportOffers() {

		// Recuperation des offres
		List <Offer> offers = this._offerLocalService.getOffers(-1, -1);

		// ne prend pas les stages ni les apprentissages
		// ne prend que les offres dont la date du jour est comprise  entre le début et la fin de la date de publication
		Date now = new Date();
		offers = offers.stream()
				.filter(o -> !o.getOfferTypeRecrutement().getName().equals("Stage") && !o.getOfferTypeRecrutement().getName().equals("Apprentissage"))
				.filter(o -> o.getPublicationStartDate().before(now) && o.getPublicationEndDate().after(now))
				.collect(Collectors.toList());
		
		this.exportOffers(offers);
	}

	public void exportOffers(List <Offer> offers) {
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
			String url = "http://vm19012:8080/web/strasbourg.eu/test-azc/-/entity/id/" + offer.getOfferId();
			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");
			String startDate = dateFormat.format(offer.getPublicationStartDate());
			String endDate = dateFormat.format(offer.getPublicationEndDate());
			String description = "Du " + startDate + " au " + endDate;
			String diffusion = "";
			if(Validator.isNotNull(offer.getExportTotem()))
				diffusion = "Publié en " + offer.getExportTotem();

			csv.append(offer.getOfferId() + ";" + url + ";" + offer.getPost(Locale.FRANCE)
					+ ";" + description + ";" + startDate + ";" + endDate + ";" + diffusion);
			csv.append(CharPool.NEW_LINE);
		}

		String fileName = "interf_totems.csv";
//		byte[] bytes = csv.toString().replace('.', ',').getBytes();
//		String contentType = ContentTypes.APPLICATION_TEXT;
//		PortletResponseUtil.sendFile(resourceRequest, resourceResponse,
//				fileName, bytes, contentType);

		String fullPath = StrasbourgPropsUtil.getAgendaImportDirectory() + "/"
				+ fileName;
		File file = new File(fullPath);
		try (PrintWriter printWriter = new PrintWriter(file)) {
			printWriter.print(csv);
		} catch (FileNotFoundException e) {
			log.error(e);
		}
	}

	Log log = LogFactoryUtil.getLog(this.getClass());

	@Reference(unbind = "-")
	protected void setOfferLocalService(OfferLocalService offerLocalService) {
		this._offerLocalService = offerLocalService;
	}

	private OfferLocalService _offerLocalService;
}
