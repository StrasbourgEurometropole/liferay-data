package eu.strasbourg.portlet.agendaExport.dto;

import com.liferay.asset.kernel.model.AssetCategory;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "filters")
@XmlAccessorType(XmlAccessType.FIELD)
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

    @XmlElementWrapper(name = "categories")
    @XmlElement(name = "category")
    private List<EventCategoryDTO> categories;

    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag")
    private List<String> tags;

    public EventFiltersDTO() {
    }

    public EventFiltersDTO(String title, String language) {
        this.title = title;
        this.language = language;
        this.periods = new ArrayList<>();
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

    public List<PeriodDTO> getPeriods() {
        return periods;
    }

    public void setPeriods(List<PeriodDTO> periods) {
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

    public List<EventCategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<EventCategoryDTO> categories) {
        this.categories = categories;
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

    public void addAssetCategories(List<AssetCategory> categories) {

        if(this.categories == null) {
            this.categories = new ArrayList<>();
        }

        for(AssetCategory category : categories) {
            this.categories.add(new EventCategoryDTO(category.getName()));
        }
    }
}
