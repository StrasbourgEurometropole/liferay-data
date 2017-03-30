package eu.strasbourg.portlet.edition.display.context;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.service.EditionLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewEditionsDisplayContext
	extends ViewListBaseDisplayContext<Edition> {
	private List<Edition> _editions;

	public ViewEditionsDisplayContext(RenderRequest request,
		RenderResponse response) {
		super(Edition.class, request, response);
	}

	public List<Edition> getEditions() throws PortalException {
		if (this._editions == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());

			// Création de la liste d'objet
			List<Edition> results = new ArrayList<Edition>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					Edition edition = EditionLocalServiceUtil.fetchEdition(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (edition != null) {
						results.add(edition);
					}
				}
			}
			this._editions = results;
		}
		return this._editions;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.EDITION_BO, StrasbourgPortletKeys.EDITION_BO,
			actionId);
	}

}
