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

import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.EditionGalleryLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewGalleriesDisplayContext
	extends ViewListBaseDisplayContext<EditionGallery> {
	private List<EditionGallery> _galleries;

	public ViewGalleriesDisplayContext(RenderRequest request,
		RenderResponse response) {
		super(EditionGallery.class, request, response);
	}

	public List<EditionGallery> getGalleries() throws PortalException {
		if (this._galleries == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());

			// Création de la liste d'objet
			List<EditionGallery> results = new ArrayList<EditionGallery>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					EditionGallery gallery = EditionGalleryLocalServiceUtil.fetchEditionGallery(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (gallery != null) {
						results.add(gallery);
					}
				}
			}
			this._galleries = results;
		}
		return this._galleries;
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
