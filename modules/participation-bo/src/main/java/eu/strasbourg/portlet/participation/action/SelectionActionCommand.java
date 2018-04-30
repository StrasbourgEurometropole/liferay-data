package eu.strasbourg.portlet.participation.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.service.ParticipationLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { 
		"javax.portlet.name=" + StrasbourgPortletKeys.PARTICIPATION_BO,
		"mvc.command.name=selectionAction" 
	},
	service = MVCActionCommand.class
)
public class SelectionActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest actionRequest,
		ActionResponse actionResponse) throws PortletException {
		
		String tab = ParamUtil.getString(actionRequest, "tab");

		try {
			long[] selectionIds = StringUtil
				.split(ParamUtil.getString(actionRequest, "selectionIds"), 0L);

			for (long entryId : selectionIds) {
				switch (ParamUtil.getString(actionRequest, "cmd")) {
				case "delete":
					if (tab.equals("participations")) {
						_participationLocalService.removeParticipation(entryId);
					}
					break;
				case "publish":
					if (tab.equals("participations")) {
						Participation participation = _participationLocalService.getParticipation(entryId);
						_participationLocalService.updateStatus(participation, WorkflowConstants.STATUS_APPROVED);
					}
					break;
				case "unpublish":
					if (tab.equals("participations")) {
						Participation participation = _participationLocalService.getParticipation(entryId);
						_participationLocalService.updateStatus(participation, WorkflowConstants.STATUS_DRAFT);
					}
					break;
				}
			}
		} catch (PortalException e) {
			_log.error(e);
		}
		return false;
	}
	
	@Reference(unbind = "-")
	protected void setParticipationLocalService(ParticipationLocalService participationLocalService) {
		_participationLocalService = participationLocalService;
	}
	
	private ParticipationLocalService _participationLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
