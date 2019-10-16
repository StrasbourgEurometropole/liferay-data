package eu.strasbourg.portlet.agendaExport.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement(name = "period")
public class PeriodDTO {

    @XmlElement(name = "depth")
    private LocalDate startDate;

    @XmlElement(name = "depth")
    private LocalDate endDate;

    @XmlElement(name = "depth")
    private String schedule;

    public PeriodDTO(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public PeriodDTO(LocalDate startDate, LocalDate endDate, String schedule) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.schedule = schedule;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
