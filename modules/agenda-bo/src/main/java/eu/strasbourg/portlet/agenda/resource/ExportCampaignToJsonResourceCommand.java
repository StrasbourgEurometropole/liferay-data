package eu.strasbourg.portlet.agenda.resource;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.service.CampaignLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * Export d'une campagne au format JSON
 */
@Component(
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_BO,
		"mvc.command.name=exportJson" },
	service = MVCResourceCommand.class)
public class ExportCampaignToJsonResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest,
		ResourceResponse resourceResponse) throws PortletException {
		resourceResponse.setContentType("application/force-download");
		resourceResponse.setProperty("content-disposition", "attachment; filename=campaign.json");
		long campaignId = ParamUtil.getLong(resourceRequest, "campaignId");
		Campaign campaign = campaignLocalService.fetchCampaign(campaignId);
		if (campaign != null) {
			JSONObject json = campaign.generateExport();
			String jsonString = json.toJSONString();
			try {
				PrintWriter writer = resourceResponse.getWriter();
				writer.write(jsonString);
				writer.close();
			} catch (IOException e) {
				log.error(e);
			}
		}

		return true;
	}

	private CampaignLocalService campaignLocalService;

	@Reference(unbind = "-")
	protected void setCampaignLocalService(
		CampaignLocalService campaignLocalService) {
		this.campaignLocalService = campaignLocalService;
	}

	private final Log log = LogFactoryUtil.getLog(this.getClass().getName());
}
