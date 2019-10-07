package eu.strasbourg.portlet.gtfs.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.gtfs.model.Arret;
import eu.strasbourg.service.gtfs.service.ArretLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(immediate = true, property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.GTFS_BO,
		"mvc.command.name=selectionAction" }, service = MVCActionCommand.class)
public class SelectionActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest actionRequest,
			ActionResponse actionResponse) throws PortletException {
		ThemeDisplay td =  (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String tab = ParamUtil.getString(actionRequest, "tab");

		try {
			long[] selectionIds = StringUtil.split(
					ParamUtil.getString(actionRequest, "selectionIds"), 0L);

			for (long entryId : selectionIds) {
				switch (ParamUtil.getString(actionRequest, "cmd")) {
				case "publish":
					if (tab.equals("arrets")) {
						Arret arret = _arretLocalService.getArret(entryId);
						_arretLocalService.updateStatus(arret,
								WorkflowConstants.STATUS_APPROVED);
					}
					break;
				case "unpublish":
					if (tab.equals("arrets")) {
						Arret arret = _arretLocalService.getArret(entryId);
						_arretLocalService.updateStatus(arret,
								WorkflowConstants.STATUS_DRAFT);
					}
					break;
				}
			}
		} catch (PortalException e) {
			_log.error(e);
		}
		return false;
	}

	private ArretLocalService _arretLocalService;

	@Reference(unbind = "-")
	protected void setArretLocalService(ArretLocalService arretLocalService) {

		_arretLocalService = arretLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
