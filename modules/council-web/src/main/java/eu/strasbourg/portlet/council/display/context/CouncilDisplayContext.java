package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.council.configuration.CouncilConfiguration;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;

public class CouncilDisplayContext {

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    private ThemeDisplay themeDisplay;
    private PortletPreferences preferences;
    private CouncilConfiguration configuration;
    private RenderRequest request;

    /**
     * Constructeur
     */
    public CouncilDisplayContext(PortletPreferences preferences, RenderRequest request) {
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        this.preferences = preferences;
        this.request = request;
        try {
            this.configuration = themeDisplay.getPortletDisplay().getPortletInstanceConfiguration(CouncilConfiguration.class);
        } catch (PortalException e) {
            log.error(e);
        }
    }

    /**
     * Accesseur de la configuration du portlet
     */
    @SuppressWarnings("unused")
    public CouncilConfiguration getConfiguration() {
        return this.configuration;
    }

    /**
     * Renvoi l'id de l'official Connecté (0 si pas un official de la BDD)
     */
    public long getOfficialId() {
        long officialId=0;
        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
        String publikMail = SessionParamUtil.getString(originalRequest, "publik_email");

        Official official = OfficialLocalServiceUtil.findByEmail(publikMail);

        if(official != null) {
            officialId = official.getOfficialId();
        }

        return officialId;
    }

    /**
     * Accesseur du groupId
     */
    @SuppressWarnings("unused")
    public long getGroupId() {
        return this.themeDisplay.getScopeGroupId();
    }

}
