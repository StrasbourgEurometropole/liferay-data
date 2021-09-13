package eu.strasbourg.portlet.agenda.csmap.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.service.csmap.model.Agenda;
import eu.strasbourg.service.csmap.service.AgendaLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

public class ViewCsmapAgendaThematiqueDisplayContext extends ViewListBaseDisplayContext<Agenda> {

    private List<Agenda> _agendas;

    public ViewCsmapAgendaThematiqueDisplayContext(RenderRequest request,
                                                   RenderResponse response) {
        super(Agenda.class, request, response);
    }

    public List<Agenda> getAgendas() throws PortalException {
        if (this._agendas == null) {
            this._agendas = AgendaLocalServiceUtil.getAgendasThematique();
        }
//        this._searchContainer.setResults(this._agendas);
        return this._agendas;
    }

    /**
     * Wrapper autour du permission checker pour les permissions de module
     */
    public boolean hasPermission(String actionId) {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getCompanyGroupId(),
                StrasbourgPortletKeys.CSMAP_BO_AGENDA, StrasbourgPortletKeys.CSMAP_BO_AGENDA,
                actionId);
    }
}
