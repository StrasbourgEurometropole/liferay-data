package eu.strasbourg.portlet.agendaExport.dto;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "group")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventGroupDTO {

    //jour / mois / vocabulaire
    @XmlElement(name = "name")
    private String name;

    @XmlElementWrapper(name = "subgroups")
    @XmlElement(name = "subgroup")
    private List<EventGroupDTO> subgoups;

    public EventGroupDTO() {
    }

    public EventGroupDTO(String name, List<EventGroupDTO> subgoups) {
        this.name = name;
        this.subgoups = subgoups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EventGroupDTO> getSubgoups() {
        return subgoups;
    }

    public void setSubgoups(List<EventGroupDTO> subgoups) {
        this.subgoups = subgoups;
    }
}
