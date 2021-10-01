package eu.strasbourg.portlet.notif.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.service.notif.model.ServiceNotif;
import eu.strasbourg.service.notif.service.ServiceNotifLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

public class ViewServicesDisplayContext
	extends ViewListBaseDisplayContext<ServiceNotif> {
	private List<ServiceNotif> services;

	public ViewServicesDisplayContext(RenderRequest request,
									  RenderResponse response) {
		super(ServiceNotif.class, request, response);
	}
	@SuppressWarnings("unused")
	public List<ServiceNotif> getServices() throws PortalException {

		int countResults = 0;

		if (this.services == null) {

			this.services = ServiceNotifLocalServiceUtil.getServiceNotifs(
					this.getSearchContainer().getStart(),
					this.getSearchContainer().getEnd());

			countResults = ServiceNotifLocalServiceUtil.getServiceNotifs(-1, -1).size();
		}
		this.getSearchContainer().setTotal(countResults);
		return this.services;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	@SuppressWarnings("unused")
	public boolean hasPermission(String actionId) {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.NOTIF_BO, StrasbourgPortletKeys.NOTIF_BO,
			actionId);
	}
}
