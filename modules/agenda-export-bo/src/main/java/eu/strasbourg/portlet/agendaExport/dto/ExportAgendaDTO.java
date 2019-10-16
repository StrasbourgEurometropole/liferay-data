package eu.strasbourg.portlet.agendaExport.dto;

import eu.strasbourg.service.agenda.model.AgendaExport;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "body")
public class ExportAgendaDTO {

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "filters")
    private EventFiltersDTO filters;

    @XmlElement(name = "depth")
    private int depth;

    @XmlElementWrapper(name = "groups")
    @XmlElement(name = "group")
    private List<EventGroupDTO> groups;

    @XmlElementWrapper(name = "subgroups")
    @XmlElement(name = "subgroup")
    private List<EventGroupDTO> subgoups;

    @XmlElementWrapper(name = "events")
    @XmlElement(name = "event")
    private List<EventDTO> events;

    public ExportAgendaDTO() {}

    public ExportAgendaDTO(String title, EventFiltersDTO filters, int depth, List<EventGroupDTO> groups, List<EventGroupDTO> subgoups, List<EventDTO> events) {
        this.title = title;
        this.filters = filters;
        this.depth = depth;
        this.groups = groups;
        this.subgoups = subgoups;
        this.events = events;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EventFiltersDTO getFilters() {
        return filters;
    }

    public void setFilters(EventFiltersDTO filters) {
        this.filters = filters;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public List<EventGroupDTO> getGroups() {
        return groups;
    }

    public void setGroups(List<EventGroupDTO> groups) {
        this.groups = groups;
    }

    public List<EventGroupDTO> getSubgoups() {
        return subgoups;
    }

    public void setSubgoups(List<EventGroupDTO> subgoups) {
        this.subgoups = subgoups;
    }

    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
    }
}
