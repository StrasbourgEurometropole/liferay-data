package eu.strasbourg.portlet.slider_une.configuration;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.*;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component(
        configurationPid = "eu.strasbourg.portlet.slider_une.configuration.SliderUneConfiguration",
        configurationPolicy = ConfigurationPolicy.OPTIONAL,
        immediate = true,
        property = {
                "javax.portlet.name=" + StrasbourgPortletKeys.SLIDER_UNE_WEB },
        service = ConfigurationAction.class)
public class SliderUneConfigurationAction
        extends DefaultConfigurationAction {

    /**
     * Action : Sauvegarde de la configuration si on a validé le formulaire ou
     * envoi de la JSP des sélecteurs si on a changé la liste déroulante des
     * types d'entité
     */
    @Override
    public void processAction(PortletConfig portletConfig,
                              ActionRequest request, ActionResponse response) throws Exception {

        String cmd = ParamUtil.getString(request, "cmd");
        if (cmd.equals("update")) {

            // Champs selectionnés
            String classPKs = "";
            for (int i = 1; i < 25; i++) {
                String classPK = ParamUtil.getString(request, "classPK_" + i);
                if(Validator.isNotNull(classPK))
                    classPKs += classPKs.length() > 0 ? "," + classPK : classPK;
            }
            setPreference(request, "classPKs", classPKs);

            // Affichage des tags
            boolean showTags = ParamUtil.getBoolean(request,
                    "showTags");
            setPreference(request, "showTags",
                    String.valueOf(showTags));

        }
        super.processAction(portletConfig, request, response);
    }

    /**
     * Envoie à la JSP de configuration des informations nécessaires
     */
    @Override
    public void include(PortletConfig portletConfig, HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) request
                    .getAttribute(WebKeys.THEME_DISPLAY);

            SliderUneConfiguration configuration = themeDisplay
                    .getPortletDisplay().getPortletInstanceConfiguration(
                            SliderUneConfiguration.class);

            // items selectionnés
            String classPKsString = "";
            for (int i = 1; i < 25; i++) {
                String classPK = ParamUtil.getString(request, "classPK_" + i);
                if(Validator.isNotNull(classPK)) {
                    classPKsString += classPKsString.length() > 0 ? "," + classPK : classPK;
                }
            }
            if(classPKsString.length() == 0)
                classPKsString = configuration.classPKs();
            String[] classPKs = new String[24];
            int index = 0;
            if(!classPKsString.isEmpty()) {
                for (String classPK : classPKsString.split(",")) {
                    // Vérifie si l'évènement ou le contenu web n'est pas dépublié
                    AssetEntry journalArticleEntry = null;
                    journalArticleEntry = AssetEntryLocalServiceUtil.fetchEntry(JournalArticle.class.getName(),
                            Long.parseLong(classPK));
                    if (journalArticleEntry != null && journalArticleEntry.isVisible()) {
                        classPKs[index] = classPK;
                        index++;
                    }
                    AssetEntry eventEntry = null;
                    eventEntry = AssetEntryLocalServiceUtil.fetchEntry(Event.class.getName(),
                            Long.parseLong(classPK));
                    if (eventEntry != null && eventEntry.isVisible()) {
                        classPKs[index] = classPK;
                        index++;
                    }

                }
            }
            request.setAttribute("classPKs", classPKs);

            // Affichage des tags
            boolean showTags = ParamUtil.getBoolean(request,
                    "showTags", configuration.showTags());
            request.setAttribute("showTags", showTags);


        } catch (ConfigurationException e) {
            _log.error(e);
        }
        super.include(portletConfig, request, response);
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
