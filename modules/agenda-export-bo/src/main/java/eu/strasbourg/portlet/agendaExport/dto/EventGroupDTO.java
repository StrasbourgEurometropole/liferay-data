package eu.strasbourg.portlet.agendaExport.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "group")
public class EventGroupDTO {

    //jour / mois / vocabulaire
    @XmlElement(name = "name")
    private String name;

    @XmlElementWrapper(name = "subgroups")
    @XmlElement(name = "subgroup")
    private List<EventGroupDTO> subgoups;

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
