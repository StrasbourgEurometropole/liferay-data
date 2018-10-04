package eu.strasbourg.portlet.project.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.ParticipationLocalService;
import eu.strasbourg.service.project.service.PetitionLocalService;
import eu.strasbourg.service.project.service.ProjectLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

@Component(
	immediate = true,
	property = { 
		"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_BO,
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
					if (tab.equals("projects")) {
						_projectLocalService.removeProject(entryId);
					}
					if (tab.equals("participations")) {
						_participationLocalService.removeParticipation(entryId);
					}
					if (tab.equals("petitions")) {
						_petitionLocalService.removePetition(entryId);
					}
					break;
				case "publish":
					if (tab.equals("projects")) {
						Project project = _projectLocalService.getProject(entryId);
						_projectLocalService.updateStatus(project, WorkflowConstants.STATUS_APPROVED);
					}
					if (tab.equals("participations")) {
						Participation participation = _participationLocalService.getParticipation(entryId);
						_participationLocalService.updateStatus(participation, WorkflowConstants.STATUS_APPROVED);
					}
					if (tab.equals("petitions")) {
						Petition petition = _petitionLocalService.getPetition(entryId);
						_petitionLocalService.updateStatus(petition, WorkflowConstants.STATUS_APPROVED);
					}
					break;
				case "unpublish":
					if (tab.equals("projects")) {
						Project project = _projectLocalService.getProject(entryId);
						_projectLocalService.updateStatus(project, WorkflowConstants.STATUS_DRAFT);
					}
					if (tab.equals("participations")) {
						Participation participation = _participationLocalService.getParticipation(entryId);
						_participationLocalService.updateStatus(participation, WorkflowConstants.STATUS_DRAFT);
					}
					if (tab.equals("petitions")) {
						Petition petition = _petitionLocalService.getPetition(entryId);
						_petitionLocalService.updateStatus(petition, WorkflowConstants.STATUS_DRAFT);
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
	protected void setProjectLocalService(ProjectLocalService projectLocalService) {
		_projectLocalService = projectLocalService;
	}
	
	@Reference(unbind = "-")
	protected void setParticipationLocalService(ParticipationLocalService participationLocalService) {
		_participationLocalService = participationLocalService;
	}

	@Reference(unbind = "-")
	protected void setPetitionLocalService(PetitionLocalService petitionLocalService) {
		_petitionLocalService = petitionLocalService;
	}
	
	private ProjectLocalService _projectLocalService;
	
	private ParticipationLocalService _participationLocalService;

	private PetitionLocalService _petitionLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
