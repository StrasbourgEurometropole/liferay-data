package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.council.model.Type;
import eu.strasbourg.service.council.service.TypeLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewTypesDisplayContext extends ViewListBaseDisplayContext<Type> {

    private List<Type> types;

    public ViewTypesDisplayContext(RenderRequest request, RenderResponse response) {
        super(Type.class, request, response);
    }

    @SuppressWarnings("unused")
    public List<Type> getTypes() throws PortalException {
        if (this.types == null) {
            Hits hits = getHits(this._themeDisplay.getScopeGroupId());

            List<Type> results = new ArrayList<Type>();
            if (hits != null) {
                for (Document document : hits.getDocs()) {
                    Type type = TypeLocalServiceUtil.fetchType(
                            GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                    if (type != null) {
                        results.add(type);
                    }
                }
            }
            this.types = results;
        }
        return this.types;
    }


    /**
     * Retourne la liste des types correspondant à la recherche lancée en ignorant la pagination
     */
    private List<Type> getAllTypes() throws PortalException {
        Hits hits = getAllHits(this._themeDisplay.getCompanyGroupId());

        List<Type> results = new ArrayList<>();
        if (hits != null) {
            for (Document document : hits.getDocs()) {
                Type type = TypeLocalServiceUtil
                        .fetchType(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                if (type != null) {
                    results.add(type);
                }
            }
        }
        return results;
    }

    /**
     * Retourne la liste des PK de tous les types
     * @return liste de PK (ex: "1,5,7,8")
     */
    @SuppressWarnings("unused")
    public String getAllTypeIds() throws PortalException {
        StringBuilder typesIds = new StringBuilder();
        for (Type type : this.getAllTypes()) {
            if (typesIds.length() > 0) {
                typesIds.append(",");
            }
            typesIds.append(type.getTypeId());
        }
        return typesIds.toString();
    }

    /**
     * Wrapper autour du permission checker pour les permissions de module
     */
    @SuppressWarnings("unused")
    public boolean hasPermission(String actionId) {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getScopeGroupId(),
                StrasbourgPortletKeys.COUNCIL_BO, StrasbourgPortletKeys.COUNCIL_BO,
                actionId);
    }

}
