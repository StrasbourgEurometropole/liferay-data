package eu.strasbourg.portlet.agendaExport.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import eu.strasbourg.service.agenda.model.AgendaExport;
import eu.strasbourg.service.agenda.service.AgendaExportLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_EXPORT_BO,
"mvc.command.name=selectionAction" }, service = MVCActionCommand.class)
public class SelectionActionCommand implements MVCActionCommand {
	
	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		String tab = ParamUtil.getString(actionRequest, "tab");

		long[] selectionIds = StringUtil.split(ParamUtil.getString(actionRequest, "selectionIds"), 0L);

		for (long entryId : selectionIds) {
			try {
				switch (ParamUtil.getString(actionRequest, "cmd")) {
				case "delete":
					if (tab.equals("agendaExports")) {
						_agendaExportLocalService.removeAgendaExport(entryId);
					}
					break;
				case "publish":
					if (tab.equals("agendaExports")) {
						AgendaExport agendaExport = _agendaExportLocalService.getAgendaExport(entryId);
						_agendaExportLocalService.updateStatus(agendaExport, WorkflowConstants.STATUS_APPROVED);
					}
					break;
				case "unpublish":
					if (tab.equals("agendaExports")) {
						AgendaExport agendaExport = _agendaExportLocalService.getAgendaExport(entryId);
						_agendaExportLocalService.updateStatus(agendaExport, WorkflowConstants.STATUS_DRAFT);
					}
					break;
				}
			} catch (PortalException e) {
				_log.error(e);
			}
		}

		// Redirection (évite double
		// requête POST si l'utilisateur actualise sa page)
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String portletName = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
		PortletURL renderUrl = PortletURLFactoryUtil.create(actionRequest, portletName, themeDisplay.getPlid(),
				PortletRequest.RENDER_PHASE);
		try {
			actionResponse.sendRedirect(renderUrl.toString());
		} catch (Exception ex) {
			_log.error(ex);
		}
		return false;
	}

	private AgendaExportLocalService _agendaExportLocalService;

	@Reference(unbind = "-")
	protected void setAgendaExportLocalService(AgendaExportLocalService agendaExportLocalService) {

		_agendaExportLocalService = agendaExportLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
