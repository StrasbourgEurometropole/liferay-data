package eu.strasbourg.portlet.agendaExport.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "filters")
public class EventFiltersDTO {

    @XmlElement(name = "title")
    private String title;

    @XmlElementWrapper(name = "periods")
    @XmlElement(name = "period")
    private List<PeriodDTO> periods;

    @XmlElement(name = "language")
    private String language;

    @XmlElementWrapper(name = "vocabularies")
    @XmlElement(name = "vocabulary")
    private List<EventVocabularyDTO> vocabularies;

    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag")
    private List<String> tags;

    public EventFiltersDTO(String title, String language) {
        this.title = title;
        this.language = language;
        this.periods = new ArrayList<>();
    }

    public EventFiltersDTO(String title, List<PeriodDTO> periods, String language, List<EventVocabularyDTO> vocabularies, List<String> tags) {
        this.title = title;
        this.periods = periods;
        this.language = language;
        this.vocabularies = vocabularies;
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PeriodDTO> getPeriod() {
        return periods;
    }

    public void setPeriod(List<PeriodDTO> periods) {
        this.periods = periods;
    }

    /**
     * Return the startDate in the n element
     * @param index
     */
    public LocalDate getStartDate(int index) {
        return periods.get(index).getStartDate();
    }

    /**
     * Return the startDate in the n element
     * @param index
     */
    public LocalDate getEndDate(int index) {
        return periods.get(index).getEndDate();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<EventVocabularyDTO> getVocabularies() {
        return vocabularies;
    }

    public void setVocabularies(List<EventVocabularyDTO> vocabularies) {
        this.vocabularies = vocabularies;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void addPeriod(LocalDate startDate, LocalDate endDate) {
        periods.add(new PeriodDTO(startDate, endDate));
    }

//    public void addVocabularyAndCategories() {
//
//        if(vocabularies == null && vocabularies.isEmpty()) {
//            vocabularies = new ArrayList<>();
//        }
//
//        vocabularies.add(new PeriodDTO(startDate, endDate));
//    }
}
