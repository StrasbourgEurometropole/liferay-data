package eu.strasbourg.portlet.agendaExport.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventCategoryDTO {

    @XmlElement(name = "name")
    private String name;

    @XmlTransient
    private Long categoryId;

    @XmlTransient
    private Long vocabularyId;

    @XmlTransient
    private EventVocabularyDTO vocabulary;

    @XmlTransient
    private List<EventCategoryDTO> parentCategories;

    public EventCategoryDTO() {}

    public EventCategoryDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public EventVocabularyDTO getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(EventVocabularyDTO vocabulary) {
        this.vocabulary = vocabulary;
    }

    @JsonIgnore
    public List<EventCategoryDTO> getParentCategories() {
        return parentCategories;
    }

    public void setParentCategories(List<EventCategoryDTO> parentCategories) {
        this.parentCategories = parentCategories;
    }

//    @JsonIgnore
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @JsonIgnore
    public Long getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(Long vocabularyId) {
        this.vocabularyId = vocabularyId;
    }

    public void addVocabulary(AssetVocabulary vocabulary) {
        EventVocabularyDTO vocabularyDTO = new EventVocabularyDTO();
        vocabularyDTO.setName(vocabulary.getName());
        this.setVocabulary(vocabularyDTO);
    }

    public void addParentCategories(List<AssetCategory> categories, AssetVocabulary vocabulary) {

        if(this.parentCategories == null) {
            parentCategories = new ArrayList<>();
        }

        for(AssetCategory parent : categories) {

            if(parent.getName().equals(name)) {
                continue;
            }

            EventCategoryDTO categoryDTO = new EventCategoryDTO();
            EventVocabularyDTO vocabularyDTO = new EventVocabularyDTO();
            categoryDTO.setName(parent.getName());
            categoryDTO.setCategoryId(parent.getCategoryId());
            vocabularyDTO.setName(vocabulary.getName());
            categoryDTO.setVocabulary(vocabularyDTO);
            this.parentCategories.add(categoryDTO);
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean isChild(String name) {

        for(EventCategoryDTO parent : parentCategories) {
            if(parent.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }
}
