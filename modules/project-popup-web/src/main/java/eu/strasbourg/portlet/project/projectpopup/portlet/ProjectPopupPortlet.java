package eu.strasbourg.portlet.project.projectpopup.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author alexandre.quere
 */
@Component(
	immediate = true,
	property = {
			"com.liferay.portlet.display-category=Strasbourg",
			"com.liferay.portlet.instanceable=false",
			"com.liferay.portlet.css-class-wrapper=project-popup-portlet",
			"javax.portlet.display-name=ProjectPopup",
			"javax.portlet.init-param.add-process-action-success-action=false",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/project-popup-view.jsp",
			"javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ProjectPopupPortlet extends MVCPortlet {

	/**le log*/
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {

        String userPublikId = getPublikID(request);
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        AssetVocabularyAccessor assetVocabularyAccessor = new AssetVocabularyAccessor();
        try {
            //Récupération de la configuration du portlet

        } catch (Exception e){

        }
        super.render(request, response);
    }

    /**
     * Récupération du publik ID avec la session
     */
    private String getPublikID(PortletRequest request) {
        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
        return SessionParamUtil.getString(originalRequest, "publik_internal_id");
    }

}