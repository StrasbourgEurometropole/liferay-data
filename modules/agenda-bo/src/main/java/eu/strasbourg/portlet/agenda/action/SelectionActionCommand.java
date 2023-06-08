package eu.strasbourg.portlet.agenda.action;

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

import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.CampaignLocalService;
import eu.strasbourg.service.agenda.service.EventLocalService;
import eu.strasbourg.service.agenda.service.ManifestationLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_BO,
		"mvc.command.name=selectionAction" },
	service = MVCActionCommand.class)
public class SelectionActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest actionRequest,
		ActionResponse actionResponse) throws PortletException {
		String tab = ParamUtil.getString(actionRequest, "tab");

		long[] selectionIds = StringUtil
			.split(ParamUtil.getString(actionRequest, "selectionIds"), 0L);

		for (long entryId : selectionIds) {
			try {
				switch (ParamUtil.getString(actionRequest, "cmd")) {
				case "delete":
					if (tab.equals("events")) {
						_eventLocalService.removeEvent(entryId);
					} else if (tab.equals("manifestations")) {
						_manifestationLocalService.removeManifestation(entryId);
					} else if (tab.equals("campaigns")) {
						_campaignLocalService.removeCampaign(entryId);
					}
					break;
				case "publish":
					if (tab.equals("events")) {
						Event event = _eventLocalService.getEvent(entryId);
						_eventLocalService.updateStatus(event,
							WorkflowConstants.STATUS_APPROVED);
					} else if (tab.equals("manifestations")) {
						Manifestation manifestation = _manifestationLocalService
							.getManifestation(entryId);
						_manifestationLocalService.updateStatus(manifestation,
							WorkflowConstants.STATUS_APPROVED);
					} else if (tab.equals("campaigns")) {
						Campaign campaign = _campaignLocalService
							.getCampaign(entryId);
						_campaignLocalService.updateStatus(campaign,
							WorkflowConstants.STATUS_APPROVED);
					}
					break;
				case "unpublish":
					if (tab.equals("events")) {
						Event event = _eventLocalService.getEvent(entryId);
						_eventLocalService.updateStatus(event,
							WorkflowConstants.STATUS_DRAFT);
					} else if (tab.equals("manifestations")) {
						Manifestation manifestation = _manifestationLocalService
							.getManifestation(entryId);
						_manifestationLocalService.updateStatus(manifestation,
							WorkflowConstants.STATUS_DRAFT);
					} else if (tab.equals("campaigns")) {
						Campaign campaign = _campaignLocalService
							.getCampaign(entryId);
						_campaignLocalService.updateStatus(campaign,
							WorkflowConstants.STATUS_DRAFT);
					}
					break;
				}
			} catch (PortalException e) {
				_log.error(e);
			}
		}
		return false;
	}

	private EventLocalService _eventLocalService;
	private ManifestationLocalService _manifestationLocalService;
	private CampaignLocalService _campaignLocalService;

	@Reference(unbind = "-")
	protected void setEventLocalService(EventLocalService eventLocalService) {

		_eventLocalService = eventLocalService;
	}

	@Reference(unbind = "-")
	protected void setManifestationLocalService(
		ManifestationLocalService manifestationLocalService) {

		_manifestationLocalService = manifestationLocalService;
	}

	@Reference(unbind = "-")
	protected void setCampaignLocalService(
		CampaignLocalService campaignLocalService) {

		_campaignLocalService = campaignLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
