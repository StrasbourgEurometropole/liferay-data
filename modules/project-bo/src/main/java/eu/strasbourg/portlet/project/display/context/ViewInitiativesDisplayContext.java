package eu.strasbourg.portlet.project.display.context;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewInitiativesDisplayContext extends ViewListBaseDisplayContext<Initiative> {

	private List <Initiative> _initiatives;
	
	public ViewInitiativesDisplayContext(RenderRequest request, RenderResponse response) {
		super(Initiative.class, request, response);
	}

	public List<Initiative> getInitiatives() throws PortalException {
		if (this._initiatives == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());
			this._initiatives = createObjectList(hits);
		}
		return this._initiatives;
	}

	private List<Initiative> createObjectList(Hits hits) {
		// Création de la liste d'objet
		List<Initiative> results = new ArrayList<>();
		if (hits != null) {
			for (Document document : hits.getDocs()) {
				Initiative initiative = InitiativeLocalServiceUtil.fetchInitiative(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (initiative != null) {
					results.add(initiative);
				}
			}
		}
		return results;
	}

	/**
	 * Retourne la liste des initiatives correspondant à la recherche lancée en ignorant la pagination
	 */
	private List<Initiative> getAllInitiatives() throws PortalException {
		Hits hits = getAllHits(this._themeDisplay.getCompanyGroupId());

		// Création de la liste d'objet
		return createObjectList(hits);
	}
	
	/**
	 * Retourne la liste des PK de toutes les initiatives
	 * @return liste de PK (ex: "1,5,7,8")
	 */
	public String getAllInitiativeIds() throws PortalException {
		String initiativeIds = "";
		for (Initiative initiative : this.getAllInitiatives()) {
			if (initiativeIds.length() > 0) {
				initiativeIds += ",";
			}
			initiativeIds += initiative.getInitiativeId();
		}
		return initiativeIds;
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
