package eu.strasbourg.portlet.project.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alexandre.quere
 */
public class ViewPetitionsDisplayContext extends ViewListBaseDisplayContext<Petition> {

    private List<Petition> _petitions;

    public ViewPetitionsDisplayContext(RenderRequest request, RenderResponse response) {
        super(Petition.class, request, response);
    }

    public List<Petition> getPetitions() throws PortalException {
        if (this._petitions == null) {
            Hits hits = getHits(this._themeDisplay.getScopeGroupId());
            this._petitions = createObjectList(hits);
        }
        return this._petitions;
    }

    private List<Petition> createObjectList(Hits hits) {
        // Création de la liste d'objet
        List<Petition> results = new ArrayList<>();
        if (hits != null) {
            for (Document document : hits.getDocs()) {
                Petition petition = PetitionLocalServiceUtil.fetchPetition(
                        GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                if (petition != null) {
                    results.add(petition);
                }
            }
        }
        return results;
    }


    /**
     * Retourne la liste des pétitions correspondante à la recherche lancée en ignorant la pagination
     */
    private List<Petition> getAllPetitions() throws PortalException {
        Hits hits = getAllHits(this._themeDisplay.getCompanyGroupId());

        // Création de la liste d'objet
        return createObjectList(hits);
    }

    /**
     * Retourne la liste des PK de toutes les pétitions
     * @return liste de PK (ex: "1,5,7,8")
     */
    public String getAllPetitionIds() throws PortalException {
        StringBuilder petitionIds = new StringBuilder();
        for (Petition petition : this.getPetitions()) {
            if (petitionIds.length() > 0) {
                petitionIds.append(",");
            }
            petitionIds.append(petition.getPetitionId());
        }
        return petitionIds.toString();
    }

    /**
     * Wrapper autour du permission checker pour les permissions de module
     */
    public boolean hasPermission(String actionId) throws PortalException {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getScopeGroupId(),
                StrasbourgPortletKeys.PROJECT_BO, StrasbourgPortletKeys.PROJECT_BO,
                actionId);
    }
}
