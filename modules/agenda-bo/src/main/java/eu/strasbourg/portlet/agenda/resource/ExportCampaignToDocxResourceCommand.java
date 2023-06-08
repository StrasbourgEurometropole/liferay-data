package eu.strasbourg.portlet.agenda.resource;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.service.CampaignLocalService;
import eu.strasbourg.service.office.exporter.api.CampaignDocxExporter;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * Export d'une campagne au format JSON
 */
@Component(immediate = true, property = { "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_BO,
		"mvc.command.name=exportDocx" }, service = MVCResourceCommand.class)
public class ExportCampaignToDocxResourceCommand implements MVCResourceCommand {

	private CampaignDocxExporter campaignDocxExporter;

	@Reference (unbind = "-")
	protected void setCampaignDocxExporter(CampaignDocxExporter campaignDocxExporter) {
		this.campaignDocxExporter = campaignDocxExporter;
	}

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		long campaignId = ParamUtil.getLong(resourceRequest, "campaignId");
		Campaign campaign = campaignLocalService.fetchCampaign(campaignId);
		resourceResponse.setContentType("application/force-download");
		resourceResponse.setProperty("content-disposition",
				"attachment; filename=" + campaign.getTitle(resourceRequest.getLocale()) + ".docx");

		try {
			campaignDocxExporter.exportCampaign(resourceResponse.getPortletOutputStream(), campaign);
			resourceResponse.getPortletOutputStream().flush();
		} catch (IOException e) {
			_log.error(e.getMessage(), e);
		}

		return true;
	}

	private CampaignLocalService campaignLocalService;

	@Reference(unbind = "-")
	protected void setCampaignLocalService(CampaignLocalService campaignLocalService) {
		this.campaignLocalService = campaignLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
