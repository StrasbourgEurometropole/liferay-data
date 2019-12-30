package eu.strasbourg.portlet.projectpopup.configuration;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author alexandre.quere
 */
@Component(
        configurationPid = "eu.strasbourg.portlet.project.configuration.ProjectPopupConfiguration",
        configurationPolicy = ConfigurationPolicy.OPTIONAL,
        immediate = true,
        property = {
                "javax.portlet.name=eu_strasbourg_portlet_project_ProjectPopupPortlet"
        },
        service = ConfigurationAction.class
)
public class ProjectPopupConfigurationAction extends DefaultConfigurationAction {

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    /**
     * Sauvegarde des préférences
     */
    @Override
    public void processAction(PortletConfig portletConfig,
                              ActionRequest request,
                              ActionResponse response)
            throws Exception {
        String cmd = ParamUtil.getString(request,"cmd");
        if (cmd.equals("update")){

            setPreference(request, "hasConfig", "true");

            // Popup à afficher
            String popupTemplateId = ParamUtil.getString(request,"popupTemplateId");
            request.setAttribute("popupTemplateId",popupTemplateId);
            setPreference(request,"popupTemplateId",popupTemplateId);

            // Popup désactivée
            Boolean disable = ParamUtil.getBoolean(request,"disable");
            request.setAttribute("disable",disable);
            setPreference(request,"disable",String.valueOf(disable));

            // Nombre de fichiers autorisés
            int nbFiles = ParamUtil.getInteger(request,"nbFiles");
            request.setAttribute("nbFiles",nbFiles>10?10:nbFiles);
            setPreference(request,"nbFiles", ""+(nbFiles>10?10:nbFiles));

            // Taille maximale des fichiers
            int sizeFile = ParamUtil.getInteger(request,"sizeFile");
            request.setAttribute("sizeFile",sizeFile>10?10:sizeFile);
            setPreference(request,"sizeFile", ""+(sizeFile>10?10:sizeFile));

            // Types de fichiers autorisés
            String typesFiles = ParamUtil.getString(request,"typesFiles");
            request.setAttribute("typesFiles",typesFiles);
            setPreference(request,"typesFiles",typesFiles);
        }
        super.processAction(portletConfig, request, response);
    }


    /**
     * Send to the JSP the needed data
     */
    @Override
    public void include(PortletConfig portletConfig, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try{
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

            ProjectPopupConfiguration configuration = themeDisplay.getPortletDisplay()
                    .getPortletInstanceConfiguration(ProjectPopupConfiguration.class);

            // Ce flag permet de savoir si une configuration du portlet a déjà
            // été enregistrée
            // Utile pour cocher les centres d'intéret par défaut
            request.setAttribute("hasConfig", configuration.hasConfig());

            // Popup à afficher
            String popupTemplateId = configuration.popupTemplateId();
            request.setAttribute("popupTemplateId",popupTemplateId);

            // Popup désactivée
            boolean disable = configuration.disable();
            request.setAttribute("disable",disable);

            // Nombre de fichiers autorisés
            String nbFiles = configuration.nbFiles();
            request.setAttribute("nbFiles",nbFiles);

            // Taille maximale des fichiers
            String sizeFile = configuration.sizeFile();
            request.setAttribute("sizeFile",sizeFile);

            // Types de fichiers autorisés
            String typesFiles = configuration.typesFiles();
            request.setAttribute("typesFiles",typesFiles);
        }catch(Exception e){
            _log.error(e);
        }
        super.include(portletConfig, request, response);
    }
}
