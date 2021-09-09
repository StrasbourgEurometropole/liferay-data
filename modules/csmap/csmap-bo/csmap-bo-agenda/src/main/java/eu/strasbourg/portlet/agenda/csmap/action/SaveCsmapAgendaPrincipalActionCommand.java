package eu.strasbourg.portlet.agenda.csmap.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import eu.strasbourg.service.csmap.model.Agenda;
import eu.strasbourg.service.csmap.service.AgendaLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

@Component(
        immediate = true,
        property = {"javax.portlet.name=" + StrasbourgPortletKeys.CSMAP_BO_AGENDA,
                "mvc.command.name=saveAgendaPrincipal"},
        service = MVCActionCommand.class)
public class SaveCsmapAgendaPrincipalActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest request, ActionResponse response) {
        long agendaPrincipalId = ParamUtil.getLong(request, "agendaId");
        Agenda agenda;
        if (agendaPrincipalId != 0) {
            try {
                agenda = _agendaLocalService.getAgenda(agendaPrincipalId);
            } catch (Exception e) {
                agenda = _agendaLocalService.createAgenda(agendaPrincipalId);
            }
        } else {
            agenda = _agendaLocalService.createAgenda();
        }

        StringBuilder campaigns = new StringBuilder();
        long[] campaignsIds = ParamUtil.getLongValues(request, "campaigns");
        for (long campaignsId : campaignsIds) {
            if (campaigns.toString().equals("")) {
                campaigns = new StringBuilder(String.valueOf(campaignsId));
            } else {
                campaigns.append(",").append(campaignsId);
            }
        }
        agenda.setCampaignsIds(campaigns.toString());

        StringBuilder agendaThemes = new StringBuilder();
        long[] agendaThemesIds = ParamUtil.getLongValues(request, "agendaThemes");
        for (long agendaThemesId : agendaThemesIds) {
            if (agendaThemes.toString().equals("")) {
                agendaThemes = new StringBuilder(String.valueOf(agendaThemesId));
            } else {
                agendaThemes.append(",").append(agendaThemesId);
            }
        }
        agenda.setThemesIds(agendaThemes.toString());

        StringBuilder agendaTypes = new StringBuilder();
        long[] agendaTypesIds = ParamUtil.getLongValues(request, "agendaTypes");
        for (long agendaTypesId : agendaTypesIds) {
            if (agendaTypes.toString().equals("")) {
                agendaTypes = new StringBuilder(String.valueOf(agendaTypesId));
            } else {
                agendaTypes.append(",").append(agendaTypesId);
            }
        }
        agenda.setTypesIds(agendaTypes.toString());

        StringBuilder tags = new StringBuilder();
        long[] tagsIds = ParamUtil.getLongValues(request, "tags");
        for (long tagsId : tagsIds) {
            if (tags.toString().equals("")) {
                tags = new StringBuilder(String.valueOf(tagsId));
            } else {
                tags.append(",").append(tagsId);
            }
        }
        agenda.setTags(tags.toString());
        agenda.setIsPrincipal(true);
        agenda.setIsActive(true);
        agenda.setImageId((long) 0);

        _agendaLocalService.updateAgenda(agenda);
    }

    private AgendaLocalService _agendaLocalService;

    @Reference(unbind = "-")
    protected void setAgendaExportLocalService(AgendaLocalService agendaLocalService) {

        _agendaLocalService = agendaLocalService;
    }
}

