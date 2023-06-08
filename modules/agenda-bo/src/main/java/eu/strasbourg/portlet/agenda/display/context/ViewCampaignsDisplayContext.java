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

import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.service.CampaignLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewCampaignsDisplayContext extends ViewListBaseDisplayContext<Campaign> {

	private List<Campaign> _campaigns;

	public ViewCampaignsDisplayContext(RenderRequest request,
		RenderResponse response) {
		super(Campaign.class, request, response);
	}

	public List<Campaign> getCampaigns() throws PortalException {
		if (this._campaigns == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());

			// Création de la liste d'objet
			List<Campaign> results = new ArrayList<Campaign>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					Campaign campaign = CampaignLocalServiceUtil.fetchCampaign(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (campaign != null) {
						results.add(campaign);
					}
				}
			}
			this._campaigns = results;
		}
		return this._campaigns;
	}
	
	@Override
	public String[] getOrderColumns() {
		return new String[] { "title", "modified-date",
			"status" };
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
