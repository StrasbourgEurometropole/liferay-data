package eu.strasbourg.portlet.notif.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.notif.model.ServiceNotif;
import eu.strasbourg.service.notif.service.ServiceNotifLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

public class ViewServicesDisplayContext
	extends ViewListBaseDisplayContext<ServiceNotif> {
	private List<ServiceNotif> services;
	private final RenderRequest request;
	private final ThemeDisplay themeDisplay;

	public ViewServicesDisplayContext(RenderRequest request,
									  RenderResponse response) {
		super(ServiceNotif.class, request, response);
		this.request = request;
		this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	}
	@SuppressWarnings("unused")
	public List<ServiceNotif> getServices() throws PortalException {

		int countResults = 0;

		if (this.services == null) {

			this.services = _serviceNotifLocalService.getServiceNotifs(
					this.getSearchContainer().getStart(),
					this.getSearchContainer().getEnd());

			countResults = _serviceNotifLocalService.getServiceNotifs(-1, -1).size();
		}
		this.getSearchContainer().setTotal(countResults);
		return this.services;
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

	private ServiceNotifLocalService _serviceNotifLocalService;

	@Reference(unbind = "-")
	protected void setServiceNotifLocalService(ServiceNotifLocalService serviceNotifLocalService) {
		_serviceNotifLocalService = serviceNotifLocalService;
	}

}
