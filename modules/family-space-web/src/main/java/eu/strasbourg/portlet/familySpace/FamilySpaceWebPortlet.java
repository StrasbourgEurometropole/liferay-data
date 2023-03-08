package eu.strasbourg.portlet.familySpace;

import java.io.IOException;
import java.util.Locale;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortlet;
import eu.strasbourg.utils.PortletHelper;
import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.familySpace.configuration.FamilySpaceConfiguration;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author angelique.champougny
 */
@Component(immediate = true, property = {"com.liferay.portlet.display-category=Strasbourg",
        "com.liferay.portlet.instanceable=true", "com.liferay.portlet.required-namespaced-parameters=false",
        "javax.portlet.display-name=Espace famille", "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/family-space-view.jsp",
        "com.liferay.portlet.render-weight=0",
        "javax.portlet.init-param.config-template=/configuration/family-space-configuration.jsp",
        "javax.portlet.name=" + StrasbourgPortletKeys.FAMILY_SPACE_WEB,
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"}, service = Portlet.class)
public class FamilySpaceWebPortlet extends MVCPortlet {

    @Override
    public void render(RenderRequest renderRequest, RenderResponse response) throws IOException, PortletException {
        ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
        FamilySpaceDisplayContext dc = new FamilySpaceDisplayContext(themeDisplay);
        String publikInternalId = dc.getPublikID(renderRequest);
        String template = "";
        if(dc.isUnderMaintenance()) {
            template = "etape0";
            renderRequest.setAttribute("error", LanguageUtil.get(Locale.FRANCE, "eu.webservice-indispo"));
        }else {
            try {
                FamilySpaceResponse familySpace = FamilySpaceWebService.getResponse(publikInternalId);

                if (Validator.isNull(familySpace)) {
                    // erreur technique
                    template = "etape0";
                    renderRequest.setAttribute("error", LanguageUtil.get(Locale.FRANCE, "eu.webservice-indispo"));
                } else {
                    if (familySpace.getCodeRetour() == 1) {
                        // erreur
                        template = "etape0";
                        renderRequest.setAttribute("error", familySpace.getErreurDescription());
                    } else {
                        if (familySpace.getCount() == 0) {
                            // pas de comptes liés
                            template = "etape0";
                        } else {
                            dc.setFamilySpace(familySpace);
                            template = "etape1";
                        }
                    }
                }
            } catch (Exception e) {
                // erreur technique
                template = "etape0";
                renderRequest.setAttribute("error", LanguageUtil.get(Locale.FRANCE, "eu.webservice-indispo"));
            }
        }
        renderRequest.setAttribute("dc", dc);

        // titre personnalisable
        renderRequest.setAttribute("title", PortletHelper.getPortletTitle("my-family-space", renderRequest));

        include("/templates/" + template + ".jsp", renderRequest, response);
    }
}