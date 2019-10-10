package eu.strasbourg.portlet.agendaExport.resource;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Export d'une campagne au format JSON
 */
@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_EXPORT_BO,
		"mvc.command.name=exportAgendaExport" }, service = MVCResourceCommand.class)
public class AgendaExportResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		Map<Locale, String> title = LocalizationUtil.getLocalizationMap(resourceRequest, "title");
		Map<Long, List<Long>> vocabularies = this.getCategories(resourceRequest);
		LocalDate startDate = this.getDate(resourceRequest,"startDate", "0");
		LocalDate endDate = this.getDate(resourceRequest,"endDate", "0");
		String language = ParamUtil.getString(resourceRequest, "language");
		String[] tags = ParamUtil.getString(resourceRequest, "assetTagNames").split(",");

		WordprocessingMLPackage wordPackage = null;
		File file = null;

		try {

//			WordprocessingMLPackage.createPackage();

			/*
			wordPackage = WordprocessingMLPackage.createPackage();
			MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
			mainDocumentPart.addStyledParagraphOfText("Title", "Hello World!");
			mainDocumentPart.addParagraphOfText("Welcome To Baeldung");
			file = new File("welcome.docx");
			*/

		} catch (Exception e) {
			//e.printStackTrace();
		}

//		wordPackage.save(exportFile);

		resourceResponse.setContentType("application/force-download");
		resourceResponse.setProperty("content-disposition", "attachment; filename=test.docx");

		try {
			resourceResponse.getPortletOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	/**
	 *
	 * @param request
	 * @param field
	 * @param index
	 * @return Date | null
	 */
	private LocalDate getDate(ResourceRequest request, String field, String index) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		if(!Validator.isNotNull(ParamUtil.getString(request, field + index))) {
			return null;
		}

		Date date = ParamUtil.getDate(request, field + index, dateFormat);
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 *
	 * Récupération des catégories
	 * @param request
	 * @return Map<Long, List<Long>>
	 */
	private Map<Long, List<Long>> getCategories(ResourceRequest request) {
		int vocabularyNumber = ParamUtil.getInteger(request, "vocabulary_number");
		Map<Long, List<Long>> vocabularies = new HashMap<>();
		for(int i = 0; i < vocabularyNumber; i++) {
			long vocabularyId = ParamUtil.getLong(request, "vocabulary_" + i + "_id");
			long[] categoryIds = ParamUtil.getLongValues(request, "vocabulary_" + i + "_select");
			if(categoryIds.length == 0) {
				continue;
			}
			List<Long> categories = new ArrayList<>();
			for (int j = 0; j < categoryIds.length; j++) {
				categories.add(categoryIds[j]);
			}
			vocabularies.put(vocabularyId, categories);
		}
		return vocabularies;
	}
}
