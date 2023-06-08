package eu.strasbourg.portlet.map;

import com.liferay.asset.kernel.model.AssetCategory;
import eu.strasbourg.service.interest.model.Interest;

import java.util.*;

/**
 * Correspond à la liste des CI d'un type
 */
public class InterestGroupDisplay {
    /**
     * Type de CI
     */
    private AssetCategory category;

    /**
     * Liste des CI du type
     */
    private List<Interest> interests;

    /**
     * Création d'une liste de groupes à partir d'une liste de CI
     */
    public static ArrayList<InterestGroupDisplay> getInterestGroups(List<Interest> interests) {
        ArrayList<InterestGroupDisplay> groups = new ArrayList<>();

        for (Interest interest : interests) {
            Optional<InterestGroupDisplay> optionalGroup = groups.stream()
                    .filter(g -> g.getCategory().getCategoryId() == interest.getTypeId()).findFirst();
            InterestGroupDisplay group;
            group = optionalGroup.orElseGet(() -> new InterestGroupDisplay(interest.getType()));
            group.addInterestToGroup(interest);
            if (!optionalGroup.isPresent()) {
                groups.add(group);
            }
        }

        return groups;
    }

    private InterestGroupDisplay(AssetCategory category) {
        this.category = category;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public AssetCategory getCategory() {
        return category;
    }

    private void addInterestToGroup(Interest interest) {
        if (interests == null) {
            interests = new ArrayList<>();
        }
        this.interests.add(interest);
    }
}
