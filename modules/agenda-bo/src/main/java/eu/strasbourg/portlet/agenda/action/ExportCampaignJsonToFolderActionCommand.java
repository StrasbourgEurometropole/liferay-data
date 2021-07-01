package eu.strasbourg.portlet.agenda.action;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.agenda.model.Campaign;
import eu.strasbourg.service.agenda.service.CampaignLocalService;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.io.*;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_BO,
                "mvc.command.name=exportJsonToFolder" },
        service = MVCActionCommand.class)
public class ExportCampaignJsonToFolderActionCommand implements MVCActionCommand {

    @Override
    public boolean processAction(ActionRequest request, ActionResponse response)
            throws PortletException {

        File directory = new File(
                StrasbourgPropsUtil.getAgendaImportDirectory());

        File f = new File(directory.getAbsolutePath()+"\\campaign.json");
        try {
            f.createNewFile();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        long campaignId = ParamUtil.getLong(request, "campaignId");
        Campaign campaign = campaignLocalService.fetchCampaign(campaignId);
        if (campaign != null) {
            campaign.export();
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
