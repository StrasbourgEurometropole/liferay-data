package eu.strasbourg.portlet.agendaExport.exporter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.portlet.agendaExport.dto.*;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.utils.SearchHelper;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.io3.Save;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class Exporter {


    public static OutputStream exportDOCX(
        ResourceRequest req, ResourceResponse res, OutputStream os, ThemeDisplay themeDisplay, EventFiltersDTO filters,
        List<Long[]> sortedCategories
    ) {

        try {

            List<Event> events = searchEvents(req, res, themeDisplay, filters, sortedCategories);

            /** Create and fill DTO objects **/
            List<EventDTO> eventDTOs = createEventDTOList(events, filters, themeDisplay);
            ExportAgendaDTO data = sortDTOObjects(
                themeDisplay, filters, eventDTOs, filters.getGroupOrdering(), filters.getSubGroupOrdering() ,2
            );

            /** JAXB Marshaller **/
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(ExportAgendaDTO.class);
            Marshaller m = context.createMarshaller();
            m.marshal(data, writer);
            String xmlContent = writer.toString();

            /** DOCX4J and response **/
            WordprocessingMLPackage wordMLPackage = null;

			res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml");
			res.setProperty("content-disposition", "attachment; filename="+filters.getFilename()+".docx");

            wordMLPackage = Docx4J.load(new File(filters.getFilepath()));
            Docx4J.bind(wordMLPackage, xmlContent, Docx4J.FLAG_BIND_INSERT_XML | Docx4J.FLAG_BIND_BIND_XML);
			Save saver = new Save(wordMLPackage);
			saver.save(os);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return os;
    }

    public static OutputStream exportJSON(
        ResourceRequest req, ResourceResponse res, OutputStream os, ThemeDisplay themeDisplay, EventFiltersDTO filters,
        List<Long[]> sortedCategories
    ) {
        try {

            List<Event> events = searchEvents(req, res, themeDisplay, filters, sortedCategories);

            /** Create and fill DTO objects **/
            List<EventDTO> eventDTOs = createEventDTOList(events, filters, themeDisplay);
            ExportAgendaDTO data = sortDTOObjects(
                themeDisplay, filters, eventDTOs, filters.getGroupOrdering(),
                filters.getSubGroupOrdering(), Integer.parseInt(filters.getGroupDepth())
            );

            /** Export data **/
            ObjectMapper mapper = new ObjectMapper();
            //Remove empty values
            mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
            String json = mapper.writeValueAsString(data);
            byte[] b = json.getBytes(StandardCharsets.UTF_8);

            res.setContentType("text/json");
            res.setProperty("content-disposition", "attachment; filename=content.json");
            os.write(b);

        } catch (Exception e) {
            e.printStackTrace();
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

//		Hits hits = SearchHelper.getGlobalSearchHits(searchContext, classNames, groupId,
//			themeDisplay.getCompanyGroupId(), true, "", true, "displayDate", filters.getStartDate(0), filters.getEndDate(0), categoriesRechercheIds,
//			new ArrayList<Long[]>(), StringUtil.split("actu,webmag"), false, themeDisplay.getLocale(), 0,
//			12, "modified_sortable", true);

        Hits hits = SearchHelper.getGlobalSearchHits(searchContext, classNames, 20160,
                20116, true, "", false, "", null, null, new ArrayList<Long[]>(),
                new ArrayList<Long[]>(), StringUtil.split(""), false, themeDisplay.getLocale(), 0,
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

    private static ExportAgendaDTO sortDTOObjects(
            ThemeDisplay themeDisplay, EventFiltersDTO filters, List<EventDTO> events, String filterType, String subFilterType, int level
    ) {

        ExportAgendaDTO exportAgendaDTO = new ExportAgendaDTO();
        exportAgendaDTO.setFilters(filters);
        exportAgendaDTO.setLocale(themeDisplay.getLocale());

        switch(level) {
            case 0:
                //insert events
                exportAgendaDTO.setEvents(events);
                break;
            case 1:
                //create groups
                List<EventGroupDTO> groups = orderEventsInGroups(events, filterType, filters);
                exportAgendaDTO.setGroups(groups);
                break;
            case 2:
                //create groups and subgroups
                List<EventGroupDTO> mainGroups = orderEventsInGroups(events, filterType, filters);
                orderGroupsInGroups(mainGroups, subFilterType, filters);
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
    private static List<EventDTO> createEventDTOList(List<Event> events, EventFiltersDTO filters, ThemeDisplay themeDisplay) {

        List<EventDTO> DTOList = new ArrayList<>();
        for(Event event : events) {
            DTOList.add(new EventDTO(event, filters, themeDisplay.getLocale()));
        }

        return DTOList;
    }

    /**
     * Filtre les events en fonction d'un argument et les place dans le groupe correspondant
     * Si un event appartient à plusieurs groupe, celui-ci est dupliqué
     * @param events
     * @param filterType
     */
    private static List<EventGroupDTO> orderEventsInGroups(List<EventDTO> events, String filterType, EventFiltersDTO filters) {

        List<EventGroupDTO> groups = new ArrayList<>();
        List<String> values;
        for(EventDTO event : events) {

            values = getValues(event, filterType, filters);

            //for each values, get or create the group and add the event in this group
            for(String value : values) {
                EventGroupDTO group = getOrCreateGroup(groups, filterType, value);
                if(group != null) {
                    group.addEvent(event);
                }
            }
        }

        return groups;
    }

    /**
     * Aggrège les events contenus dans des groupes dans des sous-groupes en fonction du filtre voulu
     */
    private static List<EventGroupDTO> orderGroupsInGroups(List<EventGroupDTO> groups, String filterType, EventFiltersDTO filters) {

        if(groups == null) {
            return null;
        }

//        for(EventGroupDTO group : groups) {
        for (Iterator<EventGroupDTO> iter = groups.listIterator(); iter.hasNext(); ) {
            EventGroupDTO group = iter.next();
            List<EventGroupDTO> subGroups = new ArrayList<>();

            /**
             * Pour chaque groupe, on veut lister tous les events et les séparer en sous groupes
             */

            List<String> values;
            for(EventDTO event : group.getEvents()) {

                values = getValues(event, filterType, filters);

                //for each values, get or create the group and add the event in this group
                for (String value : values) {
                    EventGroupDTO subGroup = getOrCreateGroup(subGroups, filterType, value);
                    subGroup.addEvent(event);
                }
            }

            group.setSubgoups(subGroups);
            group.clearEvents();

            //Remove group if its empty
            if(group.getSubgoups().size() == 0) {
                iter.remove();
            }
        }

        return groups;
    }

    private static List<String> getValues(EventDTO event, String filterType, EventFiltersDTO filters) {

        List<String> values = new ArrayList<>();
        switch(filterType.toUpperCase()) {
            case "DAY":
                for(PeriodDTO period : event.getPeriods()) {

                    LocalDate startDate = period.getStartDate();
                    LocalDate endDate = period.getStartDate();

                    //get the days number between these dates
                    //TODO 0 ou 1 ?
                    long days = DAYS.between(startDate, endDate) == 0 ? 1 : DAYS.between(startDate, endDate);

                    //Create the groups associated to these days
                    for(int i = 0; i < days; i++) {

                        LocalDate date = startDate.plusDays(i);

                        for(PeriodDTO filterPeriod : filters.getPeriods()) {
                            if(dateIsWithinRange(filterPeriod.getStartDate(), date, filterPeriod.getEndDate())) {
                                values.add(date.toString()); //TODO choose a format for dates
                            }
                            values.add(date.toString());
                        }
                    }
                }
                break;
            case "MONTH":
                //TODO month

                break;
            case "CATEGORY":

                for(EventCategoryDTO category : event.getCategories()) {
                    values.add(category.getName());
                }

                break;
        }


        return values;
    }

    private static boolean dateIsWithinRange(LocalDate startDate, LocalDate testDate, LocalDate endDate) {
        return !(testDate.isBefore(startDate) || testDate.isAfter(endDate));
    }


    private static EventGroupDTO getOrCreateGroup(List<EventGroupDTO> groups, String filterType, String value) {

        if(groups == null) {
            return null;
        }

        //Find the group
        for(EventGroupDTO group : groups)  {
            if(group.getValue().equals(value)) { //TODO compare any type -> not needed at the moment
                return group;
            }
        }

        //Create the group if it not exist
        EventGroupDTO group = new EventGroupDTO(filterType.toUpperCase(), value);
        groups.add(group);
        return group;

    }
}
