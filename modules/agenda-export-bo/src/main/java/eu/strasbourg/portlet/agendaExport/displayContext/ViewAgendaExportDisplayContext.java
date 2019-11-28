package eu.strasbourg.portlet.agendaExport.displayContext;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.service.UserServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import eu.strasbourg.service.agenda.model.AgendaExport;
import eu.strasbourg.service.agenda.service.AgendaExportLocalServiceUtil;
import eu.strasbourg.service.agenda.service.AgendaExportPeriodLocalService;
import eu.strasbourg.service.agenda.service.AgendaExportPeriodLocalServiceUtil;
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

    public boolean canEditAdminContent(Long agendaExportId) throws PortalException {

        if(agendaExportId == null) {
            return true;
        }

        AgendaExport agendaExport = AgendaExportLocalServiceUtil.getAgendaExport(agendaExportId);
        boolean createdByAdmin = false;
        if(agendaExport != null) {
            User user = UserServiceUtil.getUserById(agendaExport.getUserId());
            if(user != null) {
                createdByAdmin = isAdministrator(user);
            }
        }

        //Si le user qui a créé l'entité est un admin, on doit vérifier que l'utilisateur courant a les droits de modifier cette entité
        //lui aussi est un admin
        if(createdByAdmin) {
            return _themeDisplay.getPermissionChecker().isOmniadmin();
        }

        return true;
    }

    public boolean isAdministrator(User user){
        boolean isAdministrator = false;
        Role adminRole = RoleLocalServiceUtil.fetchRole(_themeDisplay.getCompanyId(), "Administrator");
        isAdministrator = user.getRoles().contains(adminRole);
        return isAdministrator;
    }
}
