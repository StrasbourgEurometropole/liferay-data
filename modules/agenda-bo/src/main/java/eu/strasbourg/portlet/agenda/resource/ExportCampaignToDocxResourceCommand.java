package eu.strasbourg.portlet.agenda.resource;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.service.CampaignLocalService;
import eu.strasbourg.service.office.exporter.OfficeExporterService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * Export d'une campagne au format JSON
 */
@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_BO,
		"mvc.command.name=exportDocx" }, service = MVCResourceCommand.class)
public class ExportCampaignToDocxResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		long campaignId = ParamUtil.getLong(resourceRequest, "campaignId");
		Campaign campaign = campaignLocalService.fetchCampaign(campaignId);
		resourceResponse.setContentType("application/force-download");
		resourceResponse.setProperty("content-disposition",
				"attachment; filename=" + campaign.getTitle(resourceRequest.getLocale()) + ".docx");
		/*
		 * if (campaign != null) { JSONObject json = campaign.generateExport();
		 * String jsonString = json.toJSONString(); try { PrintWriter writer =
		 * resourceResponse.getWriter(); writer.write(jsonString);
		 * writer.close(); } catch (IOException e) { log.error(e); } }
		 */
		try {
			OfficeExporterService.exportCampaign(resourceResponse.getPortletOutputStream(), campaign);
			resourceResponse.getPortletOutputStream().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	private CampaignLocalService campaignLocalService;

	@Reference(unbind = "-")
	protected void setEventLocalService(CampaignLocalService campaignLocalService) {
		this.campaignLocalService = campaignLocalService;
	}

}
