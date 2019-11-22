package eu.strasbourg.portlet.agendaExport.displayContext;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import eu.strasbourg.service.agenda.model.AgendaExport;
import eu.strasbourg.service.agenda.service.AgendaExportLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewAgendaExportDisplayContext extends  ViewListBaseDisplayContext<AgendaExport> {

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    private List<AgendaExport> _agendaExports;

    public ViewAgendaExportDisplayContext(RenderRequest request, RenderResponse response) {
        super(AgendaExport.class, request, response);
    }
    
    public List<AgendaExport> getAgendaExports() throws PortalException {

        if (this._agendaExports == null) {
            Hits hits = getHits(this._themeDisplay.getCompanyGroupId());
            //Création de la liste d'objet
            this._agendaExports = populateAgendaExports(hits);
        }
        return this._agendaExports;
    }

	
    private List<AgendaExport> populateAgendaExports(Hits hits) {
        List<AgendaExport> results = new ArrayList<>();
        if (hits != null) {
            for (Document document :
                    hits.getDocs()) {
            	AgendaExport agendaExport = AgendaExportLocalServiceUtil.fetchAgendaExport(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                if (agendaExport != null) {

                    results.add(agendaExport);
                }
            }
        }
        return results;
    }
    
    public boolean hasPermission(String actionId) {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getScopeGroupId(),
                StrasbourgPortletKeys.AGENDA_EXPORT_BO,
                StrasbourgPortletKeys.AGENDA_EXPORT_BO,
                actionId);
    }
}
