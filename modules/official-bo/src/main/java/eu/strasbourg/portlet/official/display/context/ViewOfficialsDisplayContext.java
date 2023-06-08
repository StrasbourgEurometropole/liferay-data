package eu.strasbourg.portlet.official.display.context;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.official.model.Official;
import eu.strasbourg.service.official.service.OfficialLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewOfficialsDisplayContext
	extends ViewListBaseDisplayContext<Official> {
	private List<Official> _officials;

	public ViewOfficialsDisplayContext(RenderRequest request,
		RenderResponse response) {
		super(Official.class, request, response);
	}

	public List<Official> getOfficials() throws PortalException {
		if (this._officials == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());

			// Création de la liste d'objet
			List<Official> results = new ArrayList<Official>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					Official official = OfficialLocalServiceUtil.fetchOfficial(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (official != null) {
						results.add(official);
					}
				}
			}
			this._officials = results;
		}
		return this._officials;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.OFFICIAL_BO, StrasbourgPortletKeys.OFFICIAL_BO,
			actionId);
	}

}
