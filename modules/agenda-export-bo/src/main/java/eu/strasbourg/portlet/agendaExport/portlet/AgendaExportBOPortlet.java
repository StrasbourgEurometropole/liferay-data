package eu.strasbourg.portlet.agendaExport.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import eu.strasbourg.portlet.agendaExport.displayContext.EditAgendaExportDisplayContext;
import eu.strasbourg.portlet.agendaExport.displayContext.ViewAgendaExportDisplayContext;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author jeremy.zwickert
 */
@Component(
        immediate=true,
        property= {
                "com.liferay.portlet.instanceable=false",
                "com.liferay.portlet.footer-portlet-javascript=/js/agenda-export-bo-main.js",
                "com.liferay.portlet.header-portlet-css=/css/agenda-export-bo-main.css",
                "com.liferay.portlet.single-page-application=false",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/agenda-export-bo-view.jsp",
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user",
        		"javax.portlet.name=" + StrasbourgPortletKeys.AGENDA_EXPORT_BO
        },
        service = Portlet.class
)
public class AgendaExportBOPortlet extends MVCPortlet {
	
	@Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
        
        String cmd = ParamUtil.getString(renderRequest, "cmd");
        
        renderResponse.setTitle("Export Agenda");
        //si on est sur la page d'ajout, on affiche bien évidemment un lien de retour
        String returnURL = ParamUtil.getString(renderRequest,"returnURL");
        
        boolean showBackButton = Validator.isNotNull(returnURL);
        if (showBackButton){
            portletDisplay.setShowBackIcon(true);
            portletDisplay.setURLBack(returnURL);
        }
        
      //on set le displayContext selon la page sur laquelle on est
        if (cmd.equals("editAgendaExports") || cmd.equals("copyAgendaExports")){
            EditAgendaExportDisplayContext dc = new EditAgendaExportDisplayContext(renderRequest,renderResponse);
            renderRequest.setAttribute("dc",dc);
        } else {
            ViewAgendaExportDisplayContext dc = new ViewAgendaExportDisplayContext(renderRequest,renderResponse);
            renderRequest.setAttribute("dc",dc);
        }
        super.render(renderRequest, renderResponse);
	}
}