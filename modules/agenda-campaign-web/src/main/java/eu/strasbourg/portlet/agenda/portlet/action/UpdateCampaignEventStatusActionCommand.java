package eu.strasbourg.portlet.agenda.portlet.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.agenda.model.CampaignEvent;
import eu.strasbourg.service.agenda.model.CampaignEventStatus;
import eu.strasbourg.service.agenda.service.CampaignEventLocalService;
import eu.strasbourg.service.agenda.service.CampaignEventStatusLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.CAMPAIGN_WEB,
		"mvc.command.name=updateStatus" },
	service = MVCActionCommand.class)
public class UpdateCampaignEventStatusActionCommand
	implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest actionRequest,
		ActionResponse actionResponse) throws PortletException {

		try {
			doProcessAction(actionRequest, actionResponse);

			return SessionErrors.isEmpty(actionRequest);
		} catch (PortletException pe) {
			throw pe;
		} catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected void doProcessAction(ActionRequest request,
		ActionResponse response) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
		long[] selectionIds = ParamUtil.getLongValues(request, "selectionIds");
		if (selectionIds.length == 0) {
			long campaignEventId = ParamUtil.getLong(request,
				"campaignEventId");
			selectionIds = new long[] { campaignEventId };
		}
		for (long campaignEventId : selectionIds) {

			// On récupère l'événement
			CampaignEvent event = this.campaignEventLocalService
				.fetchCampaignEvent(campaignEventId);
			// S'il n'existe pas, erreur
			if (event == null || event.getCampaign() == null) {
				SessionErrors.add(request, "event-or-campaign-does-not-exist");
				return;
			}

			String comment = ParamUtil.getString(request, "comment");
			long statusId = ParamUtil.getLong(request, "statusId");
			CampaignEventStatus status;
			if (statusId == 0) {
				// Pas de statut, on en crée un nouveau
				User user = themeDisplay.getUser();
				int newStatus = ParamUtil.getInteger(request, "newStatus", -2);
				status = event.updateStatus(newStatus, comment, user);
				if (status == null) {
					continue;
				} else {
					this.campaignEventLocalService.updateCampaignEvent(event);
					this.campaignEventStatusLocalService
						.updateCampaignEventStatus(status);
					SessionMessages.add(request, "status-updated");
				}
			} else {
				// Un statut existe déjà, on se contente d'updater son
				// commentaire
				status = campaignEventStatusLocalService
					.fetchCampaignEventStatus(statusId);
				if (status == null) {
					continue;
				}
				status.setComment(comment);
				this.campaignEventStatusLocalService
					.updateCampaignEventStatus(status);
			} 
			if (status != null) {
				if (status.getDeletionDenied() == true) {
					event.sendDeletionDeniedMail();
				} else if (statusId == 0 || status.getStatus() == WorkflowConstants.STATUS_DENIED) {
					event.sendStatusMail();
				}
			}
		}

	}

	private CampaignEventLocalService campaignEventLocalService;

	@Reference(unbind = "-")
	protected void setCampaignEventLocalService(
		CampaignEventLocalService campagnLocalService) {
		campaignEventLocalService = campagnLocalService;
	}

	private CampaignEventStatusLocalService campaignEventStatusLocalService;

	@Reference(unbind = "-")
	protected void setCampaignEventStatusLocalService(
		CampaignEventStatusLocalService campagnLocalService) {
		campaignEventStatusLocalService = campagnLocalService;
	}

}