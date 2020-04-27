package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.council.configuration.CouncilConfiguration;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.Procuration;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.council.service.ProcurationLocalServiceUtil;
import eu.strasbourg.utils.LayoutHelper;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CouncilDisplayContext {

    private final Log log = LogFactoryUtil.getLog(this.getClass().getName());

    private ThemeDisplay themeDisplay;
    private PortletPreferences preferences;
    private CouncilConfiguration configuration;
    private RenderRequest request;
    private CouncilSession councilSession;

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
        this.councilSession = null;
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
    @SuppressWarnings("unused")
    public long getOfficialId() {
        long officialId=0;
        String publikMail = this.getPublikEmail();

        Official official = OfficialLocalServiceUtil.findByEmail(publikMail);

        if(official != null) {
            officialId = official.getOfficialId();
        }

        return officialId;
    }

    /**
     * Renvoi la session en cours si elle existe
     */
    @SuppressWarnings("unused")
    public CouncilSession getCouncilSession() {
        if (this.councilSession == null) {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(new Date());
            gc.set(Calendar.HOUR_OF_DAY, 0);
            gc.set(Calendar.MINUTE, 0);
            gc.set(Calendar.SECOND, 0);
            gc.set(Calendar.MILLISECOND, 0);
            List<CouncilSession> todayCouncils = CouncilSessionLocalServiceUtil.findByDate(gc.getTime());

            if (todayCouncils.size() > 0)
                this.councilSession = todayCouncils.get(0);
        }
        return this.councilSession;
    }

    /**
     * Renvoi le type de la session du jour si elle existe
     */
    @SuppressWarnings("unused")
    public String getCouncilSessionType() {
        return this.getCouncilSession() != null ? this.councilSession.getType() : "";
    }

    /**
     * Si l'utilisateur connecté est un utilisateur MonStrasbourg & co. sans lien avec les conseils
     */
    @SuppressWarnings("unused")
    public boolean isConfirmedCouncilUser() {
        return !isGuestUser()
                && !this.isSessionNotAvailable()
                && !this.isSimplePublikUser()
                && !this.isOfficialTypeMismatchOrNotActive()
                && !this.isOfficialNotedAbsent()
                || this.configuration.useSkypeView().equals("true");
    }

    /**
     * Si l'utilisateur n'est pas connecté (publik ou Liferay)
     */
    @SuppressWarnings("unused")
    public boolean isGuestUser() {
        return Validator.isNull(this.getPublikEmail());
    }

    /**
     * Si l'utilisateur connecté est un utilisateur MonStrasbourg & co. sans lien avec les conseils
     */
    @SuppressWarnings("unused")
    public boolean isSimplePublikUser() {
        return this.getOfficialId() == 0 && Validator.isNotNull(this.getPublikEmail());
    }

    /**
     * Si l'utilisateur connecté est un utilisateur MonStrasbourg & co. sans lien avec les conseils
     */
    @SuppressWarnings("unused")
    public boolean isSessionNotAvailable() {
        return this.getCouncilSession() == null;
    }

    /**
     * Si les types de la session et de l'élu ne cole pas ou si l'élu est inactif
     */
    @SuppressWarnings("unused")
    public boolean isOfficialTypeMismatchOrNotActive() {
        boolean result = false;
        Official currentOfficial = OfficialLocalServiceUtil.fetchOfficial(this.getOfficialId());
        if (currentOfficial != null && this.getCouncilSession() != null) {
            if ((this.getCouncilSession().isMinicipal() && currentOfficial.getIsMunicipal()
                    || this.getCouncilSession().isEurometropolitan() && currentOfficial.getIsEurometropolitan())
                    && currentOfficial.getIsActive())
                result = false;
            else
                result = true;
        }
        return result;
    }

    /**
     * Si l'élu connecté est un élu noté comme absent pour la session
     */
    @SuppressWarnings("unused")
    public boolean isOfficialNotedAbsent() {
        boolean result = false;
        if (this.getCouncilSession() != null) {
            List<Procuration> procurations = ProcurationLocalServiceUtil.findByCouncilSessionId(
                    this.getCouncilSession().getCouncilSessionId());
            for (Procuration procuration : procurations) {
                if (procuration.getOfficialUnavailableId() == this.getOfficialId())
                    result = true;
            }
        }

        return result;
    }

    /**
     * Obtenir l'email de l'utilisateur
     */
    @SuppressWarnings("unused")
    public String getPublikEmail() {
        LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(this.request);
        HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
        return SessionParamUtil.getString(originalRequest, "publik_email");
    }

    /**
     * Obtenir l'URL de connexion au service publik
     */
    @SuppressWarnings("unused")
    public String getPublikLoginURL() throws MalformedURLException, UnsupportedEncodingException {
        HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(
                PortalUtil.getHttpServletRequest(this.request));
        return LayoutHelper.getPublikLoginURL(PortalUtil.getCurrentCompleteURL(originalRequest));
    }

    /**
     * Accesseur du groupId
     */
    @SuppressWarnings("unused")
    public long getGroupId() {
        return this.themeDisplay.getScopeGroupId();
    }

}
