package eu.strasbourg.portlet.interest.display.context;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.service.InterestLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewInterestsDisplayContext extends ViewListBaseDisplayContext<Interest> {
	private List<Interest> _interests;
	
	public ViewInterestsDisplayContext(RenderRequest request,
		RenderResponse response) {
		super(Interest.class, request, response);
	}

	public List<Interest> getInterests() throws PortalException {
		if (this._interests == null) {
			Hits hits = getHits(this._themeDisplay.getCompanyGroupId());

			// Création de la liste d'objet
			List<Interest> results = new ArrayList<Interest>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					Interest Interest = InterestLocalServiceUtil.fetchInterest(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (Interest != null) {
						results.add(Interest);
					}
				}
			}
			this._interests = results;
		}
		return this._interests;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.INTEREST_BO, StrasbourgPortletKeys.INTEREST_BO,
			actionId);
	}

}
