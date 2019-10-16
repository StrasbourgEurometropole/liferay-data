package eu.strasbourg.portlet.agendaExport.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "concert")
public class EventConcertDTO {

    @XmlElement(name = "concertId")
    private String concertId;

    @XmlElement(name = "concertComposers")
    private String concertComposers;

    @XmlElement(name = "distribution")
    private String distribution;

    @XmlElement(name = "program")
    private String program;

    public EventConcertDTO(String concertId, String concertComposers, String distribution, String program) {
        this.concertId = concertId;
        this.concertComposers = concertComposers;
        this.distribution = distribution;
        this.program = program;
    }

    public String getConcertId() {
        return concertId;
    }

    public void setConcertId(String concertId) {
        this.concertId = concertId;
    }

    public String getConcertComposers() {
        return concertComposers;
    }

    public void setConcertComposers(String concertComposers) {
        this.concertComposers = concertComposers;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}
