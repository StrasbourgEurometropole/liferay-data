package eu.strasbourg.portlet.participation.display.context;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.service.ParticipationLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewParticipationsDisplayContext extends ViewListBaseDisplayContext<Participation> {

	private List <Participation> _participations;
	
	public ViewParticipationsDisplayContext(RenderRequest request, RenderResponse response) {
		super(Participation.class, request, response);
	}

	public List<Participation> getParticipations() throws PortalException {
		if (this._participations == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());
			this._participations = createObjectList(hits);
		}
		return this._participations;
	}

	private List<Participation> createObjectList(Hits hits) {
		// Création de la liste d'objet
		List<Participation> results = new ArrayList<>();
		if (hits != null) {
			for (Document document : hits.getDocs()) {
				Participation participation = ParticipationLocalServiceUtil.fetchParticipation(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (participation != null) {
					results.add(participation);
				}
			}
		}
		return results;
	}

	/**
	 * Retourne la liste des participations correspondant à la recherche lancée en ignorant la pagination
	 */
	private List<Participation> getAllParticipations() throws PortalException {
		Hits hits = getAllHits(this._themeDisplay.getCompanyGroupId());

		// Création de la liste d'objet
		return createObjectList(hits);
	}
	
	/**
	 * Retourne la liste des PK de toutes les participations
	 * @return liste de PK (ex: "1,5,7,8")
	 */
	public String getAllParticipationIds() throws PortalException {
		String participationIds = "";
		for (Participation participation : this.getAllParticipations()) {
			if (participationIds.length() > 0) {
				participationIds += ",";
			}
			participationIds += participation.getParticipationId();
		}
		return participationIds;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.PARTICIPATION_BO, StrasbourgPortletKeys.PARTICIPATION_BO,
			actionId);
	}
}
