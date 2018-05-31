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

import eu.strasbourg.service.project.service.ParticipationLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.PARTICIPATION_BO,
		"mvc.command.name=deleteParticipation" 
	},
	service = MVCActionCommand.class
)
public class DeleteParticipationActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest request, ActionResponse response)
		throws PortletException {

		try {
			long participationId = ParamUtil.getLong(request, "participationId");
			_participationLocalService.removeParticipation(participationId);
		} catch (PortalException e) {
			_log.error(e);
		}
		return true;
	}

	@Reference(unbind = "-")
	protected void setParticipationLocalService(ParticipationLocalService participationLocalService) {
		_participationLocalService = participationLocalService;
	}
	
	private ParticipationLocalService _participationLocalService;

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
