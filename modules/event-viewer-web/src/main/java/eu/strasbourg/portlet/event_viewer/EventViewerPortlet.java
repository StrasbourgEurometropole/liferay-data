package eu.strasbourg.portlet.event_viewer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.event_viewer.configuration.EventViewerConfiguration;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.utils.SearchHelper;

@Component(
	immediate = true,
	property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.css-class-wrapper=event-viewer-portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/event-viewer.jsp",
		"javax.portlet.init-param.config-template=/configuration/event-viewer-configuration.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class EventViewerPortlet extends MVCPortlet {

	private EventViewerConfiguration configuration;
	private ThemeDisplay themeDisplay;
	private Log log = LogFactoryUtil.getLog(this.getClass());
	private RenderRequest request;

	@Override
	public void render(RenderRequest request, RenderResponse response)
		throws IOException, PortletException {
		this.request = request;
		PortletPreferences preferences = request.getPreferences();
		this.themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			this.configuration = this.themeDisplay.getPortletDisplay()
				.getPortletInstanceConfiguration(
					EventViewerConfiguration.class);
		} catch (ConfigurationException e) {
			log.error(e);
		}

		if (Validator.isNull(this.configuration.categoriesIds())
			&& Validator.isNull(this.configuration.tagsNames())
			&& Validator.isNull(this.configuration.fromDate())
			&& Validator.isNull(this.configuration.toDate())) {
			request.setAttribute("noconfig", true);
			super.render(request, response);
			return;
		}

		// Display template
		String displayStyle = GetterUtil
			.getString(preferences.getValue("displayStyle", StringPool.BLANK));
		request.setAttribute("displayStyle", displayStyle);

		long displayStyleGroupId = GetterUtil
			.getLong(preferences.getValue("displayStyleGroupId", null), 0);
		request.setAttribute("displayStyleGroupId", displayStyleGroupId);

		List<AssetEntry> entries = this.getEvents();
		request.setAttribute("entries", entries);

		Map<String, Object> contextObjects = new HashMap<String, Object>();
		contextObjects.put("agendaURL", this.configuration.agendaURL());
		contextObjects.put("agendaURLText", this.configuration.agendaURLText());
		request.setAttribute("contextObjects", contextObjects);

		String className = AssetEntry.class.getName();
		request.setAttribute("className", className);

		super.render(request, response);
	}

	private List<AssetEntry> getEvents() {
		List<AssetEntry> entries = new ArrayList<AssetEntry>();

		// Search context
		HttpServletRequest servletRequest = PortalUtil
			.getHttpServletRequest(request);
		SearchContext searchContext = SearchContextFactory
			.getInstance(servletRequest);

		// Catégories
		String categoriesIdsString = this.configuration.categoriesIds();
		List<Long[]> prefilterCategoriesIds = new ArrayList<Long[]>();
		for (String categoriesIdsGroupByVocabulary : categoriesIdsString
			.split(";")) {
			Long[] categoriesIdsForVocabulary = ArrayUtil.toLongArray(
				StringUtil.split(categoriesIdsGroupByVocabulary, ",", 0));
			prefilterCategoriesIds.add(categoriesIdsForVocabulary);
		}

		// Tags
		String tagsNamesString = this.configuration.tagsNames();
		String[] prefilterTagsNames = StringUtil.split(tagsNamesString);

		// ClassNames
		String[] classNames = new String[1];
		classNames[0] = Event.class.getName();

		// GroupId
		long groupId = this.themeDisplay.getScopeGroupId();

		// GlobalGroupId
		long globalGroupId = this.themeDisplay.getCompanyGroupId();

		// GlobalScope
		boolean globalScope = true;

		// Keywords
		String keywords = "";

		// Champ date
		boolean dateField = true;
		String dateFieldName = "dates_Number_sortable";
		LocalDate fromDate = null;
		LocalDate toDate = null;
		if (Validator.isNotNull(this.configuration.fromDate())
			&& Validator.isNotNull(this.configuration.toDate())) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd");
			fromDate = LocalDate.parse(this.configuration.fromDate(),
				dateTimeFormatter);
			toDate = LocalDate.parse(this.configuration.toDate(),
				dateTimeFormatter);
		} else {
			dateField = false;
		}

		// Catégories de la recherche utilisateur (non existantes pour ce
		// portlet)
		List<Long[]> categoriesIds = new ArrayList<Long[]>();

		// Locale
		Locale locale = this.themeDisplay.getLocale();

		// Pagination et ordre
		int start = -1;
		int end = -1;
		String sortField = dateFieldName;
		boolean isSortDesc = false;

		// Recherche
		Hits hits = SearchHelper.getGlobalSearchHits(searchContext, classNames,
			groupId, globalGroupId, globalScope, keywords, dateField,
			dateFieldName, fromDate, toDate, categoriesIds,
			prefilterCategoriesIds, prefilterTagsNames, locale, start, end,
			sortField, isSortDesc);

		// On renvoie la liste des événements :
		// d'abord les événements du jour classés par date de fin
		// ensuite les autres, classés par date de fin également
		List<Event> eventsOfTheDay = new ArrayList<Event>();
		List<Event> otherEvents = new ArrayList<Event>();
		for (Document document : hits.getDocs()) {
			Event event = EventLocalServiceUtil.fetchEvent(
				GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
			if (event != null) {
				if (this.eventIsHappeningToday(event)) {
					eventsOfTheDay.add(event);
				} else {
					otherEvents.add(event);
				}
			}
		}
		
		eventsOfTheDay = eventsOfTheDay.stream().sorted((e1, e2) -> {
			Date e1EndDate = e1.getLastEndDate() != null ? e1.getLastEndDate() : new Date(Long.MAX_VALUE);
			Date e2EndDate = e2.getLastEndDate() != null ? e2.getLastEndDate() : new Date(Long.MAX_VALUE);
			return e1EndDate.compareTo(e2EndDate);
		}).collect(Collectors.toList());

		otherEvents = otherEvents.stream().sorted((e1, e2) -> {
			Date e1EndDate = e1.getLastEndDate() != null ? e1.getLastEndDate() : new Date(Long.MAX_VALUE);
			Date e2EndDate = e2.getLastEndDate() != null ? e2.getLastEndDate() : new Date(Long.MAX_VALUE);
			return e1EndDate.compareTo(e2EndDate);
		}).collect(Collectors.toList());

		for (Event event : eventsOfTheDay) {
			entries.add(event.getAssetEntry());
		}
		for (Event event : otherEvents) {
			entries.add(event.getAssetEntry());
		}

		return entries;
	}

	private boolean eventIsHappeningToday(Event event) {
		LocalDate today = LocalDate.now(ZoneId.of("Europe/Berlin"));
		for (EventPeriod period : event.getEventPeriods()) {
			LocalDate startDate = period.getStartDate().toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate endDate = period.getEndDate().toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDate();
			endDate = endDate.plusDays(1);
			if (today.isAfter(startDate) && endDate.isBefore(today)
				|| today.isEqual(startDate)) {
				return true;
			}
		}
		return false;
	}
}
