package eu.strasbourg.service.office.exporter.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.model.CampaignEvent;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.office.exporter.api.CampaignDocxExporter;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalService;

/**
 * @author 01i345
 */
@Component(
		immediate = true,
		property = {},
		service = CampaignDocxExporter.class)
public class CampaignDocxExporterImpl implements CampaignDocxExporter {

	private ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
			this.getClass().getClassLoader());
	
	
	private PlaceLocalService placeLocalService;
	
	@Reference(unbind = "-")
	public void setPlaceLocalService(PlaceLocalService placeLocalService) {
		this.placeLocalService = placeLocalService;
	}

	private AssetCategoryLocalService assetCategoryLocalService;

	@Reference(unbind = "-")
	public void setAssetCategoryLocalService(AssetCategoryLocalService assetCategoryLocalService) {
		this.assetCategoryLocalService = assetCategoryLocalService;
	}
	
	public void exportCampaign(OutputStream stream, Campaign campaign) {
		try {
			XWPFDocument document = new XWPFDocument();

			// Titre de la campagne
			XWPFParagraph campaignTitleParagraph = document.createParagraph();
			campaignTitleParagraph.setAlignment(ParagraphAlignment.CENTER);
			campaignTitleParagraph.setSpacingAfter(200);
			XWPFRun run = campaignTitleParagraph.createRun();
			run.setFontSize(24);
			run.setText(campaign.getTitle(Locale.FRANCE));
			

			// Evénements
			List<CampaignEvent> events = campaign.getEvents().stream()
					.filter(e -> e.getStatus() == WorkflowConstants.STATUS_APPROVED).collect(Collectors.toList());
			if (events.size() > 0) {
				int i = 0;
				for (CampaignEvent event : events) {
					i++;
					XWPFParagraph eventTitleParagraph = document.createParagraph();
					eventTitleParagraph.setSpacingBefore(200);
					XWPFRun eventTitleRun = eventTitleParagraph.createRun();
					eventTitleRun.setBold(true);
					eventTitleRun.setUnderline(UnderlinePatterns.SINGLE);
					eventTitleRun.setFontSize(16);
					eventTitleRun.setText(i + " - " + event.getTitle(Locale.FRANCE));

					addSectionTitleParagraph("general", document);
					addI18nFieldParagaph("title", event.getTitleMap(), document);
					addI18nFieldParagaph("subtitle", event.getSubtitleMap(), document);
					addI18nFieldParagaph("description", event.getDescriptionMap(), document);
					addFieldParagraph("part-of", event.getManifestationLabel(Locale.FRANCE), document);

					addSectionTitleParagraph("place-and-contact", document);
					if (Validator.isNotNull(event.getPlaceSIGId())) {
						Place place = placeLocalService.getPlaceBySIGId(event.getPlaceSIGId());
						if (place != null) {
							addI18nFieldParagaph("place-name", place.getAliasMap(), document);
							String address = place.getAddressStreet() + " " + place.getAddressZipCode() + " "
									+ place.getCity(Locale.FRANCE);
							addFieldParagraph("address", address, document);
						}
					} else {
						addI18nFieldParagaph("place-name", event.getPlaceNameMap(), document);
						String address = event.getPlaceStreetNumber() + " " + event.getPlaceStreetName() + " "
								+ event.getPlaceZipCode() + " " + event.getCity();
						addFieldParagraph("address", address, document);
					}
					if (Validator.isNotNull(event.getServiceId())) {
						AssetCategory service = assetCategoryLocalService.fetchAssetCategory(event.getServiceId());
						if (service != null) {
							addFieldParagraph("organizer", service.getTitle(Locale.FRANCE), document);
						}

					} else {
						addFieldParagraph("organizer", event.getServiceName(Locale.FRANCE), document);
					}
					addFieldParagraph("phone", event.getPublicPhone(), document);
					addFieldParagraph("email", event.getPublicEmail(), document);
					addI18nFieldParagaph("website-name", event.getWebsiteNameMap(), document);
					addI18nFieldParagaph("website", event.getWebsiteURLMap(), document);

					if (event.getPeriods().size() > 0) {
						addSectionTitleParagraph("schedule", document);
					}
					for (EventPeriod period : event.getPeriods()) {
						DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("dd/MM/yyyy");
						String periodString = dateFormat.format(period.getStartDate()) + " - "
								+ dateFormat.format(period.getEndDate());
						addFieldParagraph("opening", periodString, document);
						addI18nFieldParagaph(null, period.getTimeDetailMap(), document);
					}
					
					addSectionTitleParagraph("price", document);
					String isFreeLabel = "";
					switch (event.getFree()) {
					case 0:
						isFreeLabel = LanguageUtil.get(bundle, "no");
						break;
					case 1:
						isFreeLabel = LanguageUtil.get(bundle, "yes");
						break;
					case 2:
						isFreeLabel = LanguageUtil.get(bundle, "not-communicated");
						break;
					}
					addFieldParagraph("free", isFreeLabel, document);
					addI18nFieldParagaph("price", event.getPriceMap(), document);

					addSectionTitleParagraph("categories", document);
					addFieldParagraph("types", event.getTypeLabel(Locale.FRANCE), document);
					addFieldParagraph("themes", event.getThemeLabel(Locale.FRANCE), document);
					addFieldParagraph("publics", event.getPublicLabel(Locale.FRANCE), document);

				}
			} else {
				XWPFParagraph eventParagraph = document.createParagraph();
				run = eventParagraph.createRun();
				run.setFontSize(18);
				run.setText(LanguageUtil.get(bundle, "no-event"));
			}
			document.write(stream);
			document.close();
			stream.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void addSectionTitleParagraph(String title, XWPFDocument document) {
		XWPFParagraph sectionTitleParagraph = document.createParagraph();
		sectionTitleParagraph.setSpacingBefore(150);
		XWPFRun run = sectionTitleParagraph.createRun();
		run.setFontSize(14);
		run.setBold(true);
		run.setItalic(true);
		run.setText(LanguageUtil.get(bundle, title));
	}

	private void addFieldParagraph(String fieldName, String fieldValue, XWPFDocument document) {
		if (Validator.isNotNull(fieldName)) {
			XWPFParagraph fieldNameParagraph = document.createParagraph();
			fieldNameParagraph.setSpacingBefore(150);
			XWPFRun run = fieldNameParagraph.createRun();
			run.setFontSize(12);
			run.setUnderline(UnderlinePatterns.SINGLE);
			run.setText(LanguageUtil.get(bundle, fieldName));
		}

		if (Validator.isNull(fieldValue)) {
			XWPFParagraph emptyFieldParagraph = document.createParagraph();
			XWPFRun emptyFieldRun = emptyFieldParagraph.createRun();
			emptyFieldRun.setFontSize(12);
			emptyFieldRun.setText("/");
		} else {
			XWPFParagraph paragraph = document.createParagraph();
			paragraph.setIndentationLeft(200);
			XWPFRun fieldRun = paragraph.createRun();
			fieldRun.setFontSize(12);
			fieldRun.setText(fieldValue);
		}

	}

	private void addI18nFieldParagaph(String fieldName, Map<Locale, String> fieldValue, XWPFDocument document) {
		if (fieldName != null) {
			XWPFParagraph fieldNameParagraph = document.createParagraph();
			fieldNameParagraph.setSpacingBefore(150);
			XWPFRun run = fieldNameParagraph.createRun();
			run.setFontSize(12);
			run.setUnderline(UnderlinePatterns.SINGLE);
			run.setText(LanguageUtil.get(bundle, fieldName));
		}
		for (Map.Entry<Locale, String> entry : fieldValue.entrySet()) {
			XWPFParagraph paragraph = document.createParagraph();
			paragraph.setIndentationLeft(200);
			XWPFRun fieldRun = paragraph.createRun();
			fieldRun.setFontSize(12);
			String text  = HtmlUtil.render(entry.getValue());
			fieldRun.setText(entry.getKey().getLanguage() + " : " + text);
		}
		if (fieldValue.size() == 0) {
			XWPFParagraph emptyFieldParagraph = document.createParagraph();
			XWPFRun emptyFieldRun = emptyFieldParagraph.createRun();
			emptyFieldRun.setFontSize(12);
			emptyFieldRun.setText("/");
		}
	}

}