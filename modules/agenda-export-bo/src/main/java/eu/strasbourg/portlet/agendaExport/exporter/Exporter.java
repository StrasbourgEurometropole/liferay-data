package eu.strasbourg.portlet.agendaExport.exporter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import eu.strasbourg.portlet.agendaExport.dto.*;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.SearchHelper;
import org.docx4j.Docx4J;
import org.docx4j.model.datastorage.BindingHandler;
import org.docx4j.openpackaging.io3.Save;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class Exporter {

    private static AssetVocabularyAccessor assetVocabularyAccessor;
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static DateTimeFormatter monthDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-01'T'00:00:00");

    public static OutputStream exportDOCX(
        ResourceRequest req, ResourceResponse res, ResourceBundle bundle,
        OutputStream os, ThemeDisplay themeDisplay, EventFiltersDTO filters,
        List<Long[]> sortedCategories
    ) {

        try {

            List<Event> events = searchEvents(req, res, themeDisplay, filters, sortedCategories);

            /** Create and fill DTO objects **/
            List<EventDTO> eventDTOs = createEventDTOList(events, filters, themeDisplay);
            loadChildrenCategoriesForFilters(filters);
            loadParentCategoriesInfo(eventDTOs);
            sortCategoriesByVocabularies(eventDTOs);
            ExportAgendaDTO data = sortDTOObjects(
                themeDisplay, filters, eventDTOs, Integer.parseInt(filters.getGroupDepth())
            );

            /** JAXB Marshaller **/
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(ExportAgendaDTO.class);
            Marshaller m = context.createMarshaller();
            m.marshal(data, writer);
            String xmlContent = writer.toString();

            /** DOCX4J and response **/
            WordprocessingMLPackage wordMLPackage = null;

            DLFileEntry file = filters.getFile();
            if(file != null) {

                String filename = "";
                DateTimeFormatter dateFormatterFilename = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                PeriodDTO firstPeriod = filters.getFirstPeriod();

                res.setProperty("content-type", "application/force-download");
                res.setProperty("content-disposition", "attachment; filename=\""+createFilename(bundle, filters)+".docx\"");

                wordMLPackage = Docx4J.load(file.getContentStream());

                BindingHandler.getHyperlinkResolver().setHyperlinkStyle("Hyperlink");
                Docx4J.bind(wordMLPackage, xmlContent, Docx4J.FLAG_BIND_INSERT_XML);
                Save saver = new Save(wordMLPackage);
                saver.save(os);
            }

        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }

        return os;
    }

    public static OutputStream exportJSON(
        ResourceRequest req, ResourceResponse res, ResourceBundle bundle,
        OutputStream os, ThemeDisplay themeDisplay, EventFiltersDTO filters,
        List<Long[]> sortedCategories
    ) {
        try {

            List<Event> events = searchEvents(req, res, themeDisplay, filters, sortedCategories);

            /** Create and fill DTO objects **/
            List<EventDTO> eventDTOs = createEventDTOList(events, filters, themeDisplay);
            loadChildrenCategoriesForFilters(filters);
            loadParentCategoriesInfo(eventDTOs);
            sortCategoriesByVocabularies(eventDTOs);
            ExportAgendaDTO data = sortDTOObjects(
                themeDisplay, filters, eventDTOs, Integer.parseInt(filters.getGroupDepth())
            );

            /** Export data **/
            ObjectMapper mapper = new ObjectMapper();
            //Remove empty values
            mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
            String json = mapper.writeValueAsString(data);
            byte[] b = json.getBytes(StandardCharsets.UTF_8);

            res.setProperty("content-type", "application/force-download");
            res.setProperty("content-disposition", "attachment; filename=\""+createFilename(bundle, filters)+".json\"");
            os.write(b);

        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }

        return os;
    }

    /**
     *
     * @param resourceRequest
     * @param resourceResponse
     * @param filters
     */
    private static List<Event> searchEvents(
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
            themeDisplay.getCompanyGroupId(), true, "",
            true, "dates_Number_sortable", filters.getStartDate(0),
            filters.getEndDate(0), categoriesRechercheIds, new ArrayList<Long[]>(), StringUtil.split(""),
            false, themeDisplay.getLocale(), 0,
            5000, "modified_sortable", true);

        List<Event> events = new ArrayList<>();
        for (Document document : hits.getDocs()) {
            Event event = EventLocalServiceUtil.fetchEvent(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
            if (event != null) {
                events.add(event);
            }
        }

        return events;
    }

    private static ExportAgendaDTO sortDTOObjects(
        ThemeDisplay themeDisplay, EventFiltersDTO filters, List<EventDTO> events, int level
    ) throws PortalException {

        ExportAgendaDTO exportAgendaDTO = new ExportAgendaDTO();
        exportAgendaDTO.setFilters(filters);
        exportAgendaDTO.setLocale(themeDisplay.getLocale());

        switch(level) {
            case 0:
                //insert events
                events = sortEventsByDate(events);
                exportAgendaDTO.setEvents(events);
                break;
            case 1:
                //create groups
                List<EventGroupDTO> groups = orderEventsInGroups(
                    events,
                    filters.getAggregationFilter(1),
                    filters
                );

                //sort groups
                if(filters.getAggregationFilter(1).getType().equals("DAY")
                    || filters.getAggregationFilter(1).getType().equals("MONTH")
                ) {
                    groups = sortGroupsByDate(groups);
                } else {
                    groups = sortGroupsByAlphaOrder(groups);
                }

                //sort events
                for(EventGroupDTO group : groups) {
                    group.setEvents(sortEventsByHours(group.getEvents()));
                }

                exportAgendaDTO.setGroups(groups);
                break;
            case 2:
                //create groups and subgroups
                List<EventGroupDTO> mainGroups = orderEventsInGroups(
                    events,
                    filters.getAggregationFilter(1),
                    filters
                );
                orderGroupsInGroups(
                    mainGroups,
                    filters.getAggregationFilter(2),
                    filters
                );

                //sort groups
                if(filters.getAggregationFilter(1).getType().equals("DAY")
                    || filters.getAggregationFilter(1).getType().equals("MONTH")
                ) {
                    mainGroups = sortGroupsByDate(mainGroups);
                } else {
                    mainGroups = sortGroupsByAlphaOrder(mainGroups);
                }

                //sort events
                for(EventGroupDTO group : mainGroups) {
                    for(EventGroupDTO subgroup : group.getSubgoups()) {
                        subgroup.setEvents(sortEventsByHours(subgroup.getEvents()));
                    }
                }

                exportAgendaDTO.setGroups(mainGroups);
                break;
        }

        return exportAgendaDTO;
    }

    /**
     * Return the list of events as a list of EventDTO instances
     * @param events
     * @param filters
     * @param themeDisplay
     * @return
     */
    private static List<EventDTO> createEventDTOList(
        List<Event> events, EventFiltersDTO filters, ThemeDisplay themeDisplay
    ) throws PortalException {

        List<EventDTO> DTOList = new ArrayList<>();
        for(Event event : events) {
            EventDTO eventDTO = new EventDTO(event, filters, themeDisplay.getLocale());

            //get Right name for places
            Long placeId = event.getPlaceId();
            Place place = null;
            if(placeId != null && placeId != 0) {
                place = PlaceLocalServiceUtil.getPlace(placeId);
            }
            eventDTO.addPlace(event, place);
            DTOList.add(eventDTO);
        }

        return DTOList;
    }

    private static EventVocabularyDTO getVocabularyInList(List<EventVocabularyDTO> vocabularies, String name) {

        if(vocabularies == null || name == null) {
            return null;
        }

        for(EventVocabularyDTO vocabularyDTO : vocabularies) {
            if(name.equals(vocabularyDTO.getName())) {
                return vocabularyDTO;
            }
        }

        return null;
    }

    /**
     * Récupère les ancêtres des catégories (catégorie parente et/ou vocabulaire parent)
     * @param eventDTOS
     * @throws PortalException
     */
    private static void loadParentCategoriesInfo(List<EventDTO> eventDTOS) throws PortalException {
        for(EventDTO eventDTO : eventDTOS) {

            //Charge les vocabulaires et catégories parents
            for(EventCategoryDTO categoryDTO : eventDTO.getCategories()) {
                AssetVocabulary vocabulary =
                    AssetVocabularyLocalServiceUtil.getVocabulary(categoryDTO.getVocabularyId());
                List<AssetCategory> categories =
                    AssetVocabularyHelper.getCategoryWithAncestors(categoryDTO.getCategoryId());

                categoryDTO.addParentCategories(categories, vocabulary);
                categoryDTO.addVocabulary(vocabulary);
            }
        }
    }

    /**
     * Charge les catégories enfantes de chaque catégories des filtres
     * @param filters
     */
    private static void loadChildrenCategoriesForFilters(EventFiltersDTO filters) {

        if(filters.getCategories() == null) {
            return;
        }

        //charge les catégorie enfantes des filtres
        for(EventCategoryDTO categoryDTO : filters.getCategories()) {
            List<AssetCategory> childrenCategories =
                AssetCategoryLocalServiceUtil.getChildCategories(categoryDTO.getCategoryId());

            filters.addAcceptedAssetCategory(categoryDTO);
            filters.addAcceptedAssetCategories(childrenCategories);
        }
    }

    private static void sortCategoriesByVocabularies(List<EventDTO> eventDTOS) throws PortalException {

        if(assetVocabularyAccessor == null) {
            assetVocabularyAccessor = new AssetVocabularyAccessor();
        }

        for(EventDTO eventDTO : eventDTOS) {

            //Rajout des vocabulaires
            eventDTO.addVocabulary(assetVocabularyAccessor.getEventPublics());
            eventDTO.addVocabulary(assetVocabularyAccessor.getTerritories());
            eventDTO.addVocabulary(assetVocabularyAccessor.getEventThemes());
            eventDTO.addVocabulary(assetVocabularyAccessor.getEventTypes());
            eventDTO.addVocabulary(assetVocabularyAccessor.getPlaceTypes());

            for(EventCategoryDTO categoryDTO : eventDTO.getCategories()) {
                AssetVocabulary vocabulary =
                        AssetVocabularyLocalServiceUtil.getVocabulary(categoryDTO.getVocabularyId());

                EventVocabularyDTO vocabularyDTO = eventDTO.getVocabularyByName(vocabulary.getName());
                if(vocabularyDTO != null) {
                    vocabularyDTO.addCategory(categoryDTO.getName());
                }
            }
        }
    }

    /**
     * Filtre les events en fonction d'un argument et les place dans le groupe correspondant
     * Si un event appartient à plusieurs groupe, celui-ci est dupliqué
     * @param events
     * @param aggregationFilterDTO
     * @param filters
     * @return
     */
    private static List<EventGroupDTO> orderEventsInGroups(List<EventDTO> events, AggregationFilterDTO aggregationFilterDTO, EventFiltersDTO filters) throws PortalException {

        List<EventGroupDTO> groups = new ArrayList<>();
        List<String> values;

        if(aggregationFilterDTO == null) {
            return new ArrayList<>();
        }

        for(EventDTO event : events) {

            values = getValues(
                event, aggregationFilterDTO.getType(), aggregationFilterDTO.getValue(),
                filters, filters.isFirstCategoryFilter()
            );

            //for each values, get or create the group and add the event in this group
            for(String value : values) {
                EventGroupDTO group = getOrCreateGroup(groups, aggregationFilterDTO.getType(), value);
                if(group != null) {
                    if(aggregationFilterDTO.getType().equals("DAY")){
                        event.updatePeriods(dateFormatter, value);
                    }
                    group.addEvent(event);
                }
            }
        }

        return groups;
    }

    /**
     * Aggrège les events contenus dans des groupes dans des sous-groupes en fonction du filtre voulu
     * @param groups
     * @param aggregationFilterDTO
     * @param filters
     * @return
     */
    private static List<EventGroupDTO> orderGroupsInGroups(List<EventGroupDTO> groups, AggregationFilterDTO aggregationFilterDTO, EventFiltersDTO filters) throws PortalException {

        if(groups == null || aggregationFilterDTO == null) {
            return new ArrayList<EventGroupDTO>();
        }

        for (Iterator<EventGroupDTO> iter = groups.listIterator(); iter.hasNext(); ) {
            EventGroupDTO group = iter.next();
            List<EventGroupDTO> subGroups = new ArrayList<>();

            /**
             * Pour chaque groupe, on veut lister tous les events et les séparer en sous groupes
             */

            List<String> values;
            List<EventDTO> events = group.getEvents();
            for(EventDTO event : events) {

                values = getValues(
                    event, aggregationFilterDTO.getType(), aggregationFilterDTO.getValue(),
                    filters, filters.isSecondCategoryFilter()
                );

                //for each values, get or create the group and add the event in this group
                for (String value : values) {
                    if(aggregationFilterDTO.getType().equals("DAY")) {
                        event.updatePeriods(dateFormatter, value);
                    }
                    EventGroupDTO subGroup = getOrCreateGroup(subGroups, aggregationFilterDTO.getType(), value);
                    subGroup.addEvent(event);
                }
            }

            subGroups = sortGroupsByAlphaOrder(subGroups);
            group.setSubgoups(subGroups);
            group.clearEvents();

            //Remove group if its empty
            if(group.getSubgoups().size() == 0) {
                iter.remove();
            }
        }

        return groups;
    }

    /**
     * Renvoit une liste de valeurs filtrées en fonction du type de comparaison
     * @param event
     * @param filterType
     * @param filters
     * @return
     */
    private static List<String> getValues(
        EventDTO event, String filterType, String value, EventFiltersDTO filters, boolean categoryFilter
    ) throws PortalException {

        List<String> values = new ArrayList<>();
        switch(filterType.toUpperCase()) {
            case "DAY":
                for(PeriodDTO period : event.getPeriods()) {

                    LocalDate startDate = period.getStartDate();
                    LocalDate endDate = period.getEndDate();

                    //get the days number between these dates
                    long days = DAYS.between(startDate, endDate) == 0 ? 1 : DAYS.between(startDate, endDate) + 1;

                    //Create the groups associated to these days
                    for(int i = 0; i < days; i++) {

                        LocalDate date = startDate.plusDays(i);

                        for(PeriodDTO filterPeriod : filters.getPeriods()) {
                            if(dateIsWithinRange(filterPeriod.getStartDate(), date, filterPeriod.getEndDate())) {
                                LocalDateTime ldt = date.atStartOfDay();
                                values.add(ldt.format(dateFormatter));
                            }
                        }
                    }
                }
                break;
            case "MONTH":
                for(PeriodDTO period : event.getPeriods()) {

                    LocalDate startDate = period.getStartDate();
                    LocalDate endDate = period.getEndDate();

                    //get the days number between these dates
                    long days = DAYS.between(startDate, endDate) == 0 ? 1 : DAYS.between(startDate, endDate) + 1;

                    //Create the groups associated to these days
                    for(int i = 0; i < days; i++) {

                        LocalDate date = startDate.plusDays(i);

                        for(PeriodDTO filterPeriod : filters.getPeriods()) {
                            if(filterPeriod.getStartDate().getMonth().equals(date.getMonth())) {
                                LocalDateTime ldt = date.atStartOfDay();
                                values.add(ldt.format(monthDateFormatter));
                            }
                        }
                    }
                }
                break;
            case "VOCABULARY":

                if(event.getVocabularies() == null) {
                    break;
                }

                AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.getVocabulary(Long.parseLong(value));
                value = vocabulary.getName();

                for(EventCategoryDTO category : event.getCategories()) {
                    for(EventCategoryDTO parentCategory : category.getParentCategories()) {
                        if(
                            parentCategory.getVocabulary().getName().equals(value) &&
                            !values.contains(parentCategory.getName())
                        ) {
                            values.add(parentCategory.getName());
                        }
                    }
                }

                break;
            case "CATEGORY":

                if(event.getCategories() == null) {
                    break;
                }

                if(value == "") {
                    break;
                }

                AssetCategory category = AssetCategoryLocalServiceUtil.getCategory(Long.parseLong(value));
                value = category.getName();

                for(EventCategoryDTO categoryDTO : event.getCategories()) {
                    if(
                        (!categoryFilter && categoryDTO.isChild(value)) ||
                        (categoryFilter && categoryDTO.isChild(value) && filters.isInAcceptedCategories(categoryDTO))
                    )
                    {
                        values.add(categoryDTO.getName());
                    }
                }

                break;
        }

        return values;
    }

    /**
     * Tri les events par heures croissantes
     * @param events
     * @return
     */
    private static List<EventDTO> sortEventsByHours(List<EventDTO> events) {
        List<EventDTO> sortedEvents = new ArrayList<>();
        List<EventDTO> unsortedEvents = new ArrayList<>();

        for (EventDTO event : events) {
            if(event.getPeriod() == null) {
                unsortedEvents.add(event);
                continue;
            }

            //Récupération de la période et de l'horaire
            PeriodDTO period = event.getPeriod();
            if(period.scheduleHasValidFormat()) {
                sortedEvents.add(event);
            } else {
                unsortedEvents.add(event);
            }
        }

        //Sort Events that contain valid hours
        sortedEvents.sort(
            (e1,e2) -> {
                LocalTime lt1 = e1.getPeriod().scheduleToLocalTime();
                LocalTime lt2 = e2.getPeriod().scheduleToLocalTime();
                return lt1.compareTo(lt2);
            }
        );

        //Repopulate events list
        events.clear();
        events.addAll(sortedEvents);
        events.addAll(unsortedEvents);
        return events;
    }

    /**
     * Tri les groupes par dates croissantes
     * @param events
     * @return
     */
    private static List<EventDTO> sortEventsByDate(List<EventDTO> events) {

        events.sort(
            (g1,g2) -> {
                LocalDate ltd1 = g1.getFirstStartDate();
                LocalDate ltd2 = g2.getFirstStartDate();
                return ltd1.compareTo(ltd2);
            }
        );

        return events;
    }


    /**
     * Tri les groupes par dates croissantes
     * @param groups
     * @return
     */
    private static List<EventGroupDTO> sortGroupsByDate(List<EventGroupDTO> groups) {

        groups.sort(
            (g1,g2) -> {
                LocalDateTime ltd1 = LocalDateTime.parse(g1.getValue());
                LocalDateTime ltd2 = LocalDateTime.parse(g2.getValue());
                return ltd1.compareTo(ltd2);
            }
        );

        return groups;
    }

    /**
     * Tri les groupes par ordre alphabetique croissantes
     * @param groups
     * @return
     */
    private static List<EventGroupDTO> sortGroupsByAlphaOrder(List<EventGroupDTO> groups) {

        groups.sort(
            Comparator.comparing(EventGroupDTO::getValue, String.CASE_INSENSITIVE_ORDER)
        );

        return groups;
    }

    /**
     * Est ce que la date est entre les 2 bornes temporelles ?
     * @param startDate
     * @param testDate
     * @param endDate
     * @return
     */
    private static boolean dateIsWithinRange(LocalDate startDate, LocalDate testDate, LocalDate endDate) {
        return !(testDate.isBefore(startDate) || testDate.isAfter(endDate));
    }


    /**
     * Créé ou récupère une instance EventGroupDTO
     * @param groups
     * @param filterType
     * @param value
     * @return
     */
    private static EventGroupDTO getOrCreateGroup(List<EventGroupDTO> groups, String filterType, String value) {

        if(groups == null) {
            return null;
        }

        //Find the group
        for(EventGroupDTO group : groups)  {
            if(group.getValue().equals(value)) {
                return group;
            }
        }

        //Create the group if it not exist
        EventGroupDTO group = new EventGroupDTO(filterType.toUpperCase(), value);
        groups.add(group);
        return group;

    }

    /**
     * Créé le nom du fichier
     * Le format de sortie change en fonction de la période
     * @param bundle
     * @param filters
     * @return
     */
    private static String createFilename(ResourceBundle bundle, EventFiltersDTO filters) {
        String filename;
        DateTimeFormatter dateFormatterFilename = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        PeriodDTO firstPeriod = filters.getFirstPeriod();

        //Construction du nom du fichier
        if(firstPeriod != null) {
            if(firstPeriod.getStartDate().equals(firstPeriod.getEndDate())) {
                filename = filters.getTitle() + " " + dateFormatterFilename.format(firstPeriod.getStartDate());
            } else {
                filename =
                    filters.getTitle() + " " +
                    dateFormatterFilename.format(firstPeriod.getStartDate()) +
                    " " + LanguageUtil.get(bundle, "eu.agenda.export.period.middle") + " " +
                    dateFormatterFilename.format(firstPeriod.getEndDate());
            }
        } else {
            filename = filters.getTitle();
        }

        return filename;
    }

    private static final Log _log = LogFactoryUtil.getLog(Exporter.class.getName());
}
