package eu.strasbourg.portlet.agendaExport.dto;

import eu.strasbourg.service.agenda.model.Event;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@XmlRootElement(name = "group")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventGroupDTO {

    //jour / mois / vocabulaire
    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "value")
    private String value;

    @XmlElementWrapper(name = "subgroups")
    @XmlElement(name = "subgroup")
    private List<EventGroupDTO> subgoups;

    @XmlElementWrapper(name = "events")
    @XmlElement(name = "event")
    private List<EventDTO> events;

    public EventGroupDTO() {
    }

    public EventGroupDTO(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public void addEvent(EventDTO event) {

        if(this.events == null) {
            this.events = new ArrayList<>();
        }

        this.events.add(event);
    }

    public void addEvent(Event event, EventFiltersDTO filters, Locale locale) {

        if(this.events == null) {
            this.events = new ArrayList<>();
        }

        this.events.add(new EventDTO(event, filters, locale));
    }

    /**
     * Clear event list
     */
    public void clearEvents() {
        events.clear();
    }
}
