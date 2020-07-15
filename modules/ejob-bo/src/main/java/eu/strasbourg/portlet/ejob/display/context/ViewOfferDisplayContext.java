package eu.strasbourg.portlet.ejob.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.OfferLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewOfferDisplayContext
	extends ViewListBaseDisplayContext<Offer> {
	private List<Offer> _offers;

	public ViewOfferDisplayContext(RenderRequest request,
								   RenderResponse response) {
		super(Offer.class, request, response);
	}

	public List<Offer> getOffers() throws PortalException {
		if (this._offers == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());

			// Création de la liste d'objet
			List<Offer> results = new ArrayList<Offer>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					Offer offer = OfferLocalServiceUtil.fetchOffer(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (offer != null) {
						results.add(offer);
					}
				}
			}
			this._offers = results;
		}
		return this._offers;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.EJOB_BO, StrasbourgPortletKeys.EJOB_BO,
			actionId);
	}

}
