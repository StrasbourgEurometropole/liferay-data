package eu.strasbourg.portlet.activity.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.activity.model.Association;
import eu.strasbourg.service.activity.service.AssociationLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewAssociationsDisplayContext
	extends ViewListBaseDisplayContext<Association> {
	private List<Association> associations;

	public ViewAssociationsDisplayContext(RenderRequest request,
                                          RenderResponse response) {
		super(Association.class, request, response);
	}

	public List<Association> getAssociations() throws PortalException {
		if (this.associations == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());

			// Création de la liste d'objet
			List<Association> results = new ArrayList<Association>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					Association association = AssociationLocalServiceUtil.fetchAssociation(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (association != null) {
						results.add(association);
					}
				}
			}
			this.associations = results;
		}
		return this.associations;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.ACTIVITY_BO,
			StrasbourgPortletKeys.ACTIVITY_BO, actionId);
	}

}
