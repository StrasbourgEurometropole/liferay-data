package eu.strasbourg.portlet.agenda.portlet.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.agenda.model.CampaignEvent;
import eu.strasbourg.service.agenda.model.CampaignEventStatus;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.service.CampaignEventLocalService;
import eu.strasbourg.service.agenda.service.CampaignEventStatusLocalService;
import eu.strasbourg.service.agenda.service.EventPeriodLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.CAMPAIGN_WEB,
		"mvc.command.name=duplicateCampaignEvent" },
	service = MVCActionCommand.class)
public class DuplicateCampaignEventActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest actionRequest,
		ActionResponse actionResponse) throws PortletException {

		try {
			doProcessAction(actionRequest, actionResponse);
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
				.getAttribute(WebKeys.THEME_DISPLAY);
			String portletName = (String) actionRequest
				.getAttribute(WebKeys.PORTLET_ID);
			PortletURL renderUrl = PortletURLFactoryUtil.create(actionRequest,
				portletName, themeDisplay.getPlid(),
				PortletRequest.RENDER_PHASE);
			actionResponse.sendRedirect(renderUrl.toString());
			return SessionErrors.isEmpty(actionRequest);
		} catch (PortletException pe) {
			throw pe;
		} catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected void doProcessAction(ActionRequest request,
		ActionResponse response) throws Exception {

		ServiceContext sc = ServiceContextFactory.getInstance(request);
		ThemeDisplay td = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);
		long campaignEventId = ParamUtil.getLong(request, "campaignEventId");
		CampaignEvent event = campaignEventLocalService
			.fetchCampaignEvent(campaignEventId);

		CampaignEvent newEvent = campaignEventLocalService
			.createCampaignEvent(sc);

		newEvent.setLastName(event.getLastName());
		newEvent.setFirstName(event.getFirstName());
		newEvent.setPhone(event.getPhone());
		newEvent.setEmail(event.getEmail());
		if (Validator.isNull(event.getService())) {
			newEvent.setServiceId(event.getServiceId());
			newEvent.setService("");
		}
		if (Validator.isNull(event.getServiceId())) {
			newEvent.setServiceId((long) 0);
			newEvent.setService(event.getService());
		}
		newEvent.setOnSiteFirstName(event.getOnSiteFirstName());
		newEvent.setOnSiteLastName(event.getOnSiteLastName());
		newEvent.setOnSitePhone(event.getOnSitePhone());
		newEvent.setTitle(event.getTitle());
		newEvent.setSubtitle(event.getSubtitle());
		newEvent.setDescription(event.getDescription());
		if (Validator.isNotNull(event.getImageId())) {
			newEvent.setImageId(event.getImageId());
		}
		if (Validator.isNotNull(event.getWebImageId())) {
			newEvent.setWebImageId(event.getWebImageId());
		}
		newEvent.setImageOwner(event.getImageOwner());
		newEvent.setManifestationsIds(event.getManifestationsIds());
		if (Validator.isNotNull(event.getPlaceSIGId())) {
			newEvent.setPlaceSIGId(event.getPlaceSIGId());
			newEvent.setPlaceName("");
			newEvent.setPlaceStreetNumber("");
			newEvent.setPlaceStreetName("");
			newEvent.setPlaceZipCode("");
			newEvent.setPlaceCountry("");
		} else {
			newEvent.setPlaceSIGId("");
			newEvent.setPlaceStreetNumber(event.getPlaceStreetNumber());
			newEvent.setPlaceStreetName(event.getPlaceStreetName());
			newEvent.setPlaceZipCode(event.getPlaceZipCode());
			newEvent.setPlaceCityId(event.getPlaceCityId());
			newEvent.setPlaceCountry(event.getPlaceCountry());
		}
		newEvent.setPromoter(event.getPromoter());
		newEvent.setPublicPhone(event.getPublicPhone());
		newEvent.setPublicEmail(event.getPublicEmail());
		newEvent.setWebsiteURL(event.getWebsiteURL());
		newEvent.setWebsiteName(event.getWebsiteName());
		for (EventPeriod period : event.getPeriods()) {
			EventPeriod newPeriod = eventPeriodLocalService.createEventPeriod();
			newPeriod.setStartDate(period.getStartDate());
			newPeriod.setEndDate(period.getEndDate());
			newPeriod.setTimeDetail(period.getTimeDetail());
			newPeriod.setEventId(0);
			newPeriod.setCampaignEventId(newEvent.getCampaignEventId());
			eventPeriodLocalService.updateEventPeriod(newPeriod);
		}
		newEvent.setFree(event.getFree());
		newEvent.setPrice(event.getPrice());
		newEvent.setCampaignId(event.getCampaignId());
		newEvent.setThemeId(event.getThemeId());
		newEvent.setTypeId(event.getTypeId());
		event.setPublicsIds(event.getPublicsIds());
		CampaignEventStatus status = newEvent
			.updateStatus(WorkflowConstants.STATUS_DRAFT, "", td.getUser());
		this.campaignEventStatusLocalService.updateCampaignEventStatus(status);
		this.campaignEventLocalService.updateCampaignEvent(newEvent);
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

	private EventPeriodLocalService eventPeriodLocalService;

	@Reference(unbind = "-")
	protected void setEventPeriodLocalService(
		EventPeriodLocalService campagnLocalService) {
		eventPeriodLocalService = campagnLocalService;
	}
}