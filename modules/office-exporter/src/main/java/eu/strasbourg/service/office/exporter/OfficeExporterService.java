package eu.strasbourg.service.office.exporter;

import java.io.IOException;
import java.io.OutputStream;
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

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.model.CampaignEvent;

/**
 * @author 01i345
 */
public class OfficeExporterService {

	public static boolean exportCampaign(OutputStream stream, Campaign campaign) {
		ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
				OfficeExporterService.class.getClassLoader());
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

					addI18nFieldParagaph("title", event.getTitleMap(), document);
					addI18nFieldParagaph("subtitle", event.getSubtitleMap(), document);
					addI18nFieldParagaph("description", event.getDescriptionMap(), document);
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

		return true;
	}

	private static void addI18nFieldParagaph(String fieldName, Map<Locale, String> field, XWPFDocument document) {
		ResourceBundle bundle = ResourceBundleUtil.getBundle("content.Language",
				OfficeExporterService.class.getClassLoader());
		XWPFParagraph fieldNameParagraph = document.createParagraph();
		fieldNameParagraph.setSpacingBefore(150);
		XWPFRun run = fieldNameParagraph.createRun();
		run.setFontSize(12);
		run.setUnderline(UnderlinePatterns.SINGLE);
		run.setText(LanguageUtil.get(bundle, fieldName));
		
		for (Map.Entry<Locale, String> entry : field.entrySet()) {
			XWPFParagraph paragraph = document.createParagraph();
			paragraph.setIndentationLeft(200);
			XWPFRun fieldRun = paragraph.createRun();
			fieldRun.setFontSize(12);
			fieldRun.setText(entry.getKey().getLanguage() + " : " + entry.getValue());
		}
		if (field.size() == 0) {
			XWPFParagraph emptyFieldParagraph = document.createParagraph();
			XWPFRun emptyFieldRun = emptyFieldParagraph.createRun();
			emptyFieldRun.setFontSize(12);
			emptyFieldRun.setText("/");
		}
	}

}