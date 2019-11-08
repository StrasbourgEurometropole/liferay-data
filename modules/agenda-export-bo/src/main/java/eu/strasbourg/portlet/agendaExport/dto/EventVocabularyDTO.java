package eu.strasbourg.portlet.agendaExport.dto;

import com.liferay.asset.kernel.model.AssetCategory;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "vocabulary")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventVocabularyDTO {

    @XmlElement(name = "name")
    private String name;

    @XmlElementWrapper(name = "categories")
    @XmlElement(name = "category")
    private List<EventCategoryDTO> categories;

    public EventVocabularyDTO() {
    }

    public EventVocabularyDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EventCategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<EventCategoryDTO> categories) {
        this.categories = categories;
    }

    public void addCategory(String name) {

        if(categories == null) {
            categories = new ArrayList<>();
        }

        categories.add(new EventCategoryDTO(name));
    }

    public void addCategory(AssetCategory category) {

        if(categories == null) {
            categories = new ArrayList<>();
        }

        categories.add(new EventCategoryDTO(category.getName()));
    }
}
