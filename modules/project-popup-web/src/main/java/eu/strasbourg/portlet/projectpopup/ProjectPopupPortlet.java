package eu.strasbourg.portlet.projectpopup;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.projectpopup.configuration.ProjectPopupConfiguration;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.io.IOException;
import java.util.List;

import static eu.strasbourg.portlet.projectpopup.utils.ProjectPopupUtils.getPublikID;

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
    public static final String CITY_NAME = "Strasbourg";
    public static final String REDIRECT_URL_PARAM = "redirectURL";

    @Override
    public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
        AssetVocabularyAccessor assetVocabularyAccessor = new AssetVocabularyAccessor();
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        String publikID = getPublikID(request);
        try {
            // Récupération de la configuration du portlet
            ProjectPopupConfiguration configuration = themeDisplay.getPortletDisplay()
                    .getPortletInstanceConfiguration(ProjectPopupConfiguration.class);

            // Récupération du paramètre de tri des commentaires
            String popupTemplateId = configuration.popupTemplateId();

            if(configuration.disable()){
                popupTemplateId = "filePetitionDisable";
            }else {

                if (Validator.isNull(popupTemplateId)) {
                    popupTemplateId = "filePetition";
                }

                // Récupération de l'asset entry Id qui est partagé par le portlet détail
                // entité sur la même page.
                // les popups n'ont pas forcement besion de l'entryId (déposer une petition par exemple)
                // //donc il faut etre en mesure de pouvoir gerer ca.
                long entryID = this.getPortletAssetEntryId(request);

                JSONObject user = null;
                if (publikID != null && !publikID.isEmpty())
                    user = PublikApiClient.getUserDetails(publikID);

                long groupId = themeDisplay.getLayout().getGroupId();

                // Récupération des quartiers
                List<AssetCategory> districts = AssetVocabularyHelper.getAllDistrictsFromCity(CITY_NAME);

                // Récupération des thematics
                List<AssetCategory> thematics = assetVocabularyAccessor.getThematics(groupId).getCategories();

                // Récupération des thematics
                List<AssetCategory> projects = assetVocabularyAccessor.getProjects(groupId).getCategories();

                request.setAttribute("quartiers", districts);
                request.setAttribute("thematics", thematics);
                request.setAttribute("projects", projects);

                if (entryID != -1)
                    request.setAttribute("entryId", entryID);
                request.setAttribute("userConnected", user);
            }

            // URL de redirection pour le POST evitant les soumissions multiples
            String redirectURL = themeDisplay.getURLPortal() + themeDisplay.getURLCurrent();

            request.setAttribute(REDIRECT_URL_PARAM, redirectURL);
            request.setAttribute("popupTemplateId", popupTemplateId);

        } catch (Exception e) {
            _log.error("erreur : ", e);
        }
        super.render(request, response);
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