package eu.strasbourg.service.office.exporter.impl;

import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.OfferLocalService;
import eu.strasbourg.service.office.exporter.api.OffersXlsxExporter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


@Component(
	immediate = true,
	property = {},
	service = OffersXlsxExporter.class)
public class OffersXlsxExporterImpl implements OffersXlsxExporter {

	private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
			this.getClass().getClassLoader());
	
	private OfferLocalService offerLocalService;

	@Reference(unbind = "-")
	protected void setOfferLocalService(OfferLocalService offerLocalService) {
		this.offerLocalService = offerLocalService;
	}

	public void exportPublishedOffers(OutputStream stream) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Offres");

		Object[][] offerData = { {LanguageUtil.get(bundle,"offer-number"), LanguageUtil.get(bundle, "offer-title"), LanguageUtil.get(bundle, "direction-service"),
				LanguageUtil.get(bundle, "contact-rrh"), LanguageUtil.get(bundle, "publication-start-date"),
				LanguageUtil.get(bundle, "application-end-date") } };

		List<Offer> offers = offerLocalService.getOffers(-1, -1);
		// on ne prend que les offres validées
		// on ne prend que les offres dont la date du jour est comprise  entre le début et la fin de la date de publication
		LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
		Date now = Timestamp.valueOf(today);
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");
		offers = offers.stream()
				.filter(o -> o.getStatus() == WorkflowConstants.STATUS_APPROVED)
				.filter(o -> o.getPublicationStartDate().compareTo(now) <= 0 && o.getPublicationEndDate().after(now))
				.collect(Collectors.toList());
		for (Offer offer : offers) {
			String numero = offer.getPublicationId();
			String direction = offer.getDirection().getTitle(Locale.FRANCE);
			String service = "";
			if(Validator.isNotNull(offer.getService()))
				service = " - " + offer.getService().getTitle(Locale.FRANCE);
			Object[] offerRow = { numero, offer.getPost(Locale.FRANCE), direction + service,
					offer.getContact(), dateFormat.format(offer.getPublicationStartDate()),
					dateFormat.format(offer.getLimitDate()) };
			offerData = ArrayUtil.append(offerData, offerRow);
		}

		int rowIndex = 0;
		int columnIndex = 0;
		for (Object[] offerObject : offerData) {
			Row row = sheet.createRow(rowIndex);
			columnIndex = 0;
			for (Object field : offerObject) {
				Cell cell = row.createCell(columnIndex);
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
				columnIndex++;
			}
			rowIndex++;
		}

		try {
			workbook.write(stream);
			workbook.close();
			stream.flush();
		} catch (IOException e) {
			_log.error(e.getMessage(), e);
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
