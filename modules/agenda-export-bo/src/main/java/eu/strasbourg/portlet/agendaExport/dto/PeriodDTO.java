package eu.strasbourg.portlet.agendaExport.dto;

import eu.strasbourg.portlet.agendaExport.XMLadapter.DateAdapter;
import eu.strasbourg.service.agenda.model.EventPeriod;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

@XmlRootElement(name = "period")
@XmlAccessorType(XmlAccessType.FIELD)
public class PeriodDTO {

    @XmlElement(name = "startDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate startDate;

    @XmlElement(name = "endDate")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate endDate;

    @XmlElement(name = "schedule")
    private String schedule;

    public PeriodDTO() {}

    public PeriodDTO(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public PeriodDTO(EventPeriod period, Locale locale) {
        this.startDate = period.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.endDate = period.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
