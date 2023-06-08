package eu.strasbourg.portlet.agenda.display.context;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.ManifestationLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewManifestationsDisplayContext extends ViewListBaseDisplayContext<Manifestation> {
	private List<Manifestation> _manifestations;
	
	public ViewManifestationsDisplayContext(RenderRequest request,
		RenderResponse response) {
		super(Manifestation.class, request, response);
	}

	public List<Manifestation> getManifestations() throws PortalException {
		if (this._manifestations == null) {
			Hits hits = getHits(this._themeDisplay.getCompanyGroupId());

			// Création de la liste d'objet
			List<Manifestation> results = new ArrayList<Manifestation>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					Manifestation manifestation = ManifestationLocalServiceUtil.fetchManifestation(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (manifestation != null) {
						results.add(manifestation);
					}
				}
			}
			this._manifestations = results;
		}
		return this._manifestations;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.AGENDA_BO, StrasbourgPortletKeys.AGENDA_BO,
			actionId);
	}

}
