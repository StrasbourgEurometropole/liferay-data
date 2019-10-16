package eu.strasbourg.portlet.agendaExport.resource;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.portlet.agendaExport.dto.EventFiltersDTO;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.io3.Save;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Export d'une campagne au format JSON
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_EXPORT_BO,
		"mvc.command.name=exportAgendaExport"
	},
	service = MVCResourceCommand.class
)
public class AgendaExportResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {

		//Retrieve values
		Map<Locale, String> title = LocalizationUtil.getLocalizationMap(resourceRequest, "title");
		Map<Long, List<Long>> vocabularies = this.getCategories(resourceRequest);
		LocalDate startDate = this.getDate(resourceRequest,"startDate", "0");
		LocalDate endDate = this.getDate(resourceRequest,"endDate", "0");
		String language = ParamUtil.getString(resourceRequest, "language");
		String[] tags = ParamUtil.getString(resourceRequest, "assetTagNames").split(",");

		//Load categories and vocabularies
		List<Long[]> sortedCategories = sortCategoriesForSearch(vocabularies);

		//Create DTO filter Object
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		EventFiltersDTO filters = new EventFiltersDTO(title.get(themeDisplay.getLocale()), language);
		filters.addPeriod(startDate, endDate);
		filters.setTags(Arrays.asList(tags));

		//Search query
		List<Event> events = null;
		try {
			events = searchEvents(resourceRequest, resourceResponse, themeDisplay, filters, sortedCategories);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Create and fill DTO objects

		WordprocessingMLPackage wordMLPackage = null;
		File file = null;
		String docx_filepath = "C:/test.docx";

//		resourceResponse.getWriter().close();

        try {
            //Load docx file
            wordMLPackage = Docx4J.load(new File(docx_filepath));

//            Docx4J.bind(wordMLPackage, xmlString, Docx4J.FLAG_BIND_INSERT_XML | Docx4J.FLAG_BIND_BIND_XML);

            //Send it to the client
			resourceResponse.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml");
			resourceResponse.setProperty("content-disposition", "attachment; filename=test.docx");

			OutputStream os = resourceResponse.getPortletOutputStream();
			Save saver = new Save(wordMLPackage);
			saver.save(os);
			os.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 *
	 * @param resourceRequest
	 * @param resourceResponse
	 * @param filters
	 */
	private List<Event> searchEvents(
		ResourceRequest resourceRequest, ResourceResponse resourceResponse, ThemeDisplay themeDisplay,
		EventFiltersDTO filters, List<Long[]> categoriesRechercheIds
	) {
		// Search context
		HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(resourceRequest);
		SearchContext searchContext = SearchContextFactory.getInstance(servletRequest);

		// ClassNames
		String[] classNames = new String[1];
		classNames[0] = Event.class.getName();

		// GroupId
		Group group = GroupLocalServiceUtil.fetchFriendlyURLGroup(themeDisplay.getCompanyId(), "/strasbourg.eu");
		long groupId = group.getGroupId();

		Hits hits = SearchHelper.getGlobalSearchHits(searchContext, classNames, groupId,
			themeDisplay.getCompanyGroupId(), true, "", false, "", filters.getStartDate(0), filters.getEndDate(0), categoriesRechercheIds,
			new ArrayList<Long[]>(), StringUtil.split("actu,webmag"), false, themeDisplay.getLocale(), 0,
			12, "modified_sortable", true);

		List<Event> events = new ArrayList<>();
		for (Document document : hits.getDocs()) {
			Event event = EventLocalServiceUtil.fetchEvent(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
			if (event != null) {
				events.add(event);
			}
		}

		return events;
	}


	private List<Long[]> sortCategoriesForSearch(Map<Long, List<Long>> vocabularyMap) {

		List<Long[]> sortedCategories = new ArrayList<>();
		for (Map.Entry<Long, List<Long>> entry : vocabularyMap.entrySet()) {
			Long key = entry.getKey();
			List<Long> categories = entry.getValue();
			Long[] categoriesArray = new Long[categories.size()];
			int i = 0;
			for(Long category : categories) {
				categoriesArray[i] = category;
				i++;
			}
			sortedCategories.add(categoriesArray);
		}

		return sortedCategories;
	}

	/**
	 * Charge la liste des vocabulaires
	 * @param vocabularyMap Map contenant les vocabulaires et les catégories
	 * @return List<AssetVocabulary>
	 */
	private List<AssetVocabulary> getVocabularies(Map<Long, List<Long>> vocabularyMap) {

		List<AssetVocabulary> vocabularies = new ArrayList<>();
		long[] vocabularyIds = new long[vocabularyMap.size()];
		int i = 0;
		for (Map.Entry<Long, List<Long>> entry : vocabularyMap.entrySet()) {
			Long key = entry.getKey();
			vocabularyIds[i] = key;
			i++;
		}

		try {
			vocabularies = AssetVocabularyLocalServiceUtil.getVocabularies(vocabularyIds);
		} catch (PortalException e) {
			e.printStackTrace();
		}

		return vocabularies;
	}

	private List<AssetCategory> getCategories(Map<Long, List<Long>> vocabularyMap) {

		List<AssetCategory> categories = new ArrayList<>();
//		long[] vocabularyIds = new long[vocabularyMap.size()];
//		int i = 0;
//		for (Map.Entry<Long, List<Long>> entry : vocabularyMap.entrySet()) {
//			Long key = entry.getKey();
//			vocabularyIds[i] = key;
//			i++;
//		}
//
//		try {
//			categories = AssetVocabularyLocalServiceUtil.getVocabularies(vocabularyIds);
//		} catch (PortalException e) {
//			e.printStackTrace();
//		}
//
		return categories;

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
