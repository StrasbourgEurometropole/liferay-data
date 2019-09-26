package eu.strasbourg.portlet.agendaExport.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import eu.strasbourg.service.agenda.model.AgendaExport;
import eu.strasbourg.service.agenda.service.AgendaExportLocalService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_EXPORT_BO,
                "mvc.command.name=saveAgendaExport"
        },
        service = MVCActionCommand.class
)
public class SaveAgendaExportActionCommand implements MVCActionCommand{
	@Override
	public boolean processAction(ActionRequest request, ActionResponse response) throws PortletException {

		try {
			ServiceContext sc = ServiceContextFactory.getInstance(request);
			sc.setScopeGroupId(((ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY)).getCompanyGroupId());
			long agendaExportId = ParamUtil.getLong(request, "agendaExportId");
			AgendaExport agendaExport;
			if (agendaExportId == 0) {
				agendaExport = _agendaExportLocalService.createAgendaExport(sc);
			} else {
				agendaExport = _agendaExportLocalService.getAgendaExport(agendaExportId);
				agendaExport.setNew(false);	
			}

			boolean isCopy = ParamUtil.getBoolean(request, "isCopy");
			if(isCopy) {
				agendaExport =_agendaExportLocalService.cloneAgendaExport(sc, agendaExport);		
			}

			Map<Locale, String> title = LocalizationUtil.getLocalizationMap(request, "title");
			agendaExport.setTitleMap(title);
					
			
			if(!isCopy) {
				
				//TOUTDOUX
				// Ici l'enregistrement des autres champs (vu qu'� la copie on change juste le titre et copie le reste de l'objet)
			}
			
			_agendaExportLocalService.updateAgendaExport(agendaExport, sc);

			// Redirection (�vite double
			// requ�te POST si l'utilisateur actualise sa page)
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			String portletName = (String) request.getAttribute(WebKeys.PORTLET_ID);
			PortletURL renderUrl = PortletURLFactoryUtil.create(request, portletName, themeDisplay.getPlid(),
					PortletRequest.RENDER_PHASE);
			response.sendRedirect(renderUrl.toString());
		} catch (Exception e) {
			_log.error(e);
		}

		return true;
	}

	private AgendaExportLocalService _agendaExportLocalService;

	@Reference(unbind = "-")
	protected void setAgendaExportLocalService(AgendaExportLocalService agendaExportLocalService) {

		_agendaExportLocalService = agendaExportLocalService;
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
}
