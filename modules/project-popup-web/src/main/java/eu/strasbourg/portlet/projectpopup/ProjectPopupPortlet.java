package eu.strasbourg.portlet.projectpopup;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.projectpopup.configuration.ProjectPopupConfiguration;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author alexandre.quere
 */
@Component(
        immediate = true,
        property = {
                "com.liferay.portlet.display-category=Strasbourg",
                "com.liferay.portlet.instanceable=true",
                "com.liferay.portlet.css-class-wrapper=project-popup-portlet",
                "javax.portlet.display-name=Popups Participer",
                "javax.portlet.init-param.add-process-action-success-action=false",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/project-popup-view.jsp",
                "javax.portlet.name=" + StrasbourgPortletKeys.PROJECT_POPUP_WEB,
                "javax.portlet.init-param.config-template=/project-popup-configuration.jsp",
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
)
public class ProjectPopupPortlet extends MVCPortlet {

    /**
     * le log
     */
    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
    private static final String SHARED_ASSET_ID = "LIFERAY_SHARED_assetEntryID";
    private static final String CITY_NAME = "Strasbourg";

    @Override
    public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        AssetVocabularyAccessor assetVocabularyAccessor = new AssetVocabularyAccessor();
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        String publikID = getPublikID(request);
        try {
            // Récupération de la configuration du portlet
            ProjectPopupConfiguration configuration = themeDisplay.getPortletDisplay()
                    .getPortletInstanceConfiguration(ProjectPopupConfiguration.class);

            long groupId = themeDisplay.getLayout().getGroupId();
            // Récupération du paramètre de tri des commentaires
            String popupTemplateId = configuration.popupTemplateId();
            if (Validator.isNull(popupTemplateId)) {
                popupTemplateId = "filePetition";
            }

            // Récupération de l'asset entry Id qui est partagé par le portlet détail
            // entité sur la même page.
            // les popups n'ont pas forcement besion de l'entryId (déposer une petition par exemple)
            // //donc il faut etre en mesure de pouvoir gerer ca.
            long entryID = this.getPortletAssetEntryId(request);

            PublikUser user=null;
            if (publikID != null && !publikID.isEmpty())
                user = PublikUserLocalServiceUtil.getByPublikUserId(publikID);

            // Récupération des quartiers
                List<AssetCategory> districts = AssetVocabularyHelper.getAllDistrictsFromCity(CITY_NAME);

            // Récupération des thematics
            List<AssetCategory> thematics = assetVocabularyAccessor.getThematics(groupId).getCategories();

            // Récupération des thematics
            List<AssetCategory> projects = assetVocabularyAccessor.getProjects(groupId).getCategories();

            request.setAttribute("popupTemplateId", popupTemplateId);
            request.setAttribute("quartiers", districts);
            request.setAttribute("thematics", thematics);
            request.setAttribute("projects", projects);
            if (entryID!=-1)
                request.setAttribute("entryId", entryID);
            request.setAttribute("userConnected",user);

        } catch (Exception e) {
            _log.error("erreur : ", e);
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


    /**
     * Recupere l'ID de l'assetEntry du detail de la page
     *
     * @throws PortalException
     */
    private long getPortletAssetEntryId(PortletRequest request) throws PortalException {
        PortletSession portletSession = request.getPortletSession();

        if (portletSession.getAttribute(SHARED_ASSET_ID, PortletSession.APPLICATION_SCOPE) != null) {
            return (long) portletSession.getAttribute(SHARED_ASSET_ID,
                    PortletSession.APPLICATION_SCOPE);
        }

        return -1;
    }
}