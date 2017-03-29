package eu.strasbourg.portlet.link.display.context;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.link.model.Link;
import eu.strasbourg.service.link.service.LinkLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewLinksDisplayContext
	extends ViewListBaseDisplayContext<Link> {
	private List<Link> _links;

	public ViewLinksDisplayContext(RenderRequest request,
		RenderResponse response) {
		super(Link.class, request, response);
	}

	public List<Link> getLinks() throws PortalException {
		if (this._links == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());

			// Création de la liste d'objet
			List<Link> results = new ArrayList<Link>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					Link link = LinkLocalServiceUtil.fetchLink(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (link != null) {
						results.add(link);
					}
				}
			}
			this._links = results;
		}
		return this._links;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.LINK_BO, StrasbourgPortletKeys.LINK_BO,
			actionId);
	}

}
