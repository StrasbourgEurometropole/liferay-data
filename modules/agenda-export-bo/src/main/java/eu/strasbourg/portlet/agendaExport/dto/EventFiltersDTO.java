package eu.strasbourg.portlet.agendaExport.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.document.library.kernel.model.DLFileEntry;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "filters")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventFiltersDTO {

    @XmlElement(name = "title")
    private String title;

    /**
     * Actuellement on ne stocke qu'une seule période
     * On utilise déjà une liste pour simplifier une future évolution
     */
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

    @XmlTransient
    private List<EventCategoryDTO> acceptedCategories;

    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag")
    private List<String> tags;

    @XmlTransient
    private String filename;

    @XmlTransient
    private DLFileEntry file;

    @XmlTransient
    private String groupDepth;

    @XmlTransient
    private AggregationFilterDTO aggregationFilter;

    @XmlTransient
    private boolean firstCategoryFilter;

    @XmlTransient
    private boolean secondCategoryFilter;

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

    @JsonIgnore
    public List<EventCategoryDTO> getAcceptedCategories() {
        return acceptedCategories;
    }

    public void setAcceptedCategories(List<EventCategoryDTO> acceptedCategories) {
        this.acceptedCategories = acceptedCategories;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @JsonIgnore
    public DLFileEntry getFile() {
        return file;
    }

    public void setFile(DLFileEntry file) {
        this.file = file;
    }

    public String getGroupDepth() {
        return groupDepth;
    }

    public void setGroupDepth(String groupDepth) {
        this.groupDepth = groupDepth;
    }

    @JsonIgnore
    public AggregationFilterDTO getAggregationFilter() {
        return aggregationFilter;
    }

    public void setAggregationFilter(AggregationFilterDTO aggregationFilter) {
        this.aggregationFilter = aggregationFilter;
    }

    @JsonIgnore
    public boolean isFirstCategoryFilter() {
        return firstCategoryFilter;
    }

    public void setFirstCategoryFilter(boolean firstCategoryFilter) {
        this.firstCategoryFilter = firstCategoryFilter;
    }

    @JsonIgnore
    public boolean isSecondCategoryFilter() {
        return secondCategoryFilter;
    }

    public void setSecondCategoryFilter(boolean secondCategoryFilter) {
        this.secondCategoryFilter = secondCategoryFilter;
    }

    /**
     * Rempli la liste des catégories (EventCategoryDTO) en fonction d'une liste d'AssetCategories
     * @param categories
     */
    public void addAssetCategories(List<AssetCategory> categories) {

        if(this.categories == null) {
            this.categories = new ArrayList<>();
        }

        for(AssetCategory category : categories) {
            EventCategoryDTO categoryDTO = new EventCategoryDTO(category.getName());
            categoryDTO.setCategoryId(category.getCategoryId());
            this.categories.add(categoryDTO);
        }
    }

    /**
     * Rempli la liste des catégories (EventCategoryDTO) en fonction d'une liste d'AssetCategories
     * @param categories
     */
    public void addAcceptedAssetCategories(List<AssetCategory> categories) {
        if(this.acceptedCategories == null) {
            this.acceptedCategories = new ArrayList<>();
        }

        for(AssetCategory category : categories) {
            this.acceptedCategories.add(new EventCategoryDTO(category.getName()));
        }
    }

    /**
     * Ajoute une nouvelle catégorie
     * @param category
     */
    public void addAcceptedAssetCategory(EventCategoryDTO category) {
        if(this.acceptedCategories == null) {
            this.acceptedCategories = new ArrayList<>();
        }

        this.acceptedCategories.add(category);
    }

    /**
     * Rajoute jusqu'à 2 niveau de filtre d'agregation
     * @param firstAggregationType
     * @param firstAggregationValue
     * @param secondAggregationType
     * @param secondaggregationValue
     */
    public void addAggregationFilters(
            String firstAggregationType, String firstAggregationValue,
            String secondAggregationType, String secondaggregationValue
    ) {

        AggregationFilterDTO secondAggregationFilterDTO = null;
        if(!secondAggregationType.equals("")) {
            secondAggregationFilterDTO = new AggregationFilterDTO(secondAggregationType, secondaggregationValue);
        }

        if(!firstAggregationType.equals("")) {
            aggregationFilter = new AggregationFilterDTO(firstAggregationType, firstAggregationValue);
            aggregationFilter.setAggregationFilterDTO(secondAggregationFilterDTO);
        }

    }

    /**
     *
     * TODO méthode récursive quand il y aura plus de résultats
     * @param level
     * @return
     */
    public AggregationFilterDTO getAggregationFilter(int level) {

        if(level == 1) {
            return aggregationFilter;
        } else if(level == 2 && aggregationFilter != null) {
            return aggregationFilter.getAggregationFilterDTO();
        }

        return null;
    }

    /**
     * Renvoit la première période de la liste des périodes
     * @return
     */
    public PeriodDTO getFirstPeriod() {

        if(this.getPeriods() == null) {
            return null;
        }

        return this.getPeriods().get(0);
    }

    /**
     * Est ce que la valeur
     * @param category
     * @return
     */
    public boolean isInAcceptedCategories(EventCategoryDTO category) {

        if(this.acceptedCategories == null) {
            return false;
        }

        for(EventCategoryDTO categoryDTO : this.acceptedCategories) {
            if(categoryDTO.getName().equals(category.getName())) {
                return true;
            }
        }

        return false;
    }
}
