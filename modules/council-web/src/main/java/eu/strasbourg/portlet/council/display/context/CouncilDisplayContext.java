package eu.strasbourg.portlet.council.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
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
import eu.strasbourg.service.council.model.Type;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.DeliberationServiceUtil;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.council.service.TypeLocalServiceUtil;
import eu.strasbourg.utils.LayoutHelper;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

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

        long officialId = 0;
        Official official = getOfficialFromPublikMail();

        if (official != null) {
            officialId = official.getOfficialId();
        }

        return officialId;
    }

    /**
     * Renvoi la session en cours si elle existe
     */
    @SuppressWarnings("unused")
    public List<CouncilSession> getCouncilSessions() {

        List<CouncilSession> councilSessionList = new ArrayList<>();
        if (this.councilSession == null) {

            // Calcul de la date
            GregorianCalendar gc = CouncilSessionLocalServiceUtil.calculDateForFindCouncil();
            List<CouncilSession> todayCouncils = CouncilSessionLocalServiceUtil.findByDate(gc.getTime());

            Official official = getOfficialFromPublikMail();
            councilSessionList = getCouncilSessionsForOffical(todayCouncils, official);

        } else {
            councilSessionList.add(councilSession);
        }
        return councilSessionList;
    }

    public CouncilSession getCouncilSession() {

        List<CouncilSession> councilSessions = this.getCouncilSessions();
        if (councilSessions.size() == 1) {
            this.councilSession = councilSessions.get(0);
        }
        return this.councilSession;
    }

    private List<CouncilSession> getCouncilSessionsForOffical(List<CouncilSession> todayCouncils, Official official) {

        List<CouncilSession> councilSessionList;
        List<Type> officialCouncilTypes = official.getCouncilTypes();

        councilSessionList = todayCouncils.stream()
                .filter(council -> officialCouncilTypes.stream()
                        .anyMatch(type -> type.getTitle().equals(council.getTypeCouncil().getTitle())))
                .collect(Collectors.toList());

        return councilSessionList;
    }

    public int numberOfTodaysCouncils(List<CouncilSession> todaysCouncils) {
        return todaysCouncils.size();
    }

    /**
     * Retourne un votant à partir de son mail
     */
    private Official getOfficialFromPublikMail() {

        String publikMail = this.getPublikEmail();
        return OfficialLocalServiceUtil.findByEmail(publikMail);
    }

//    /**
//     * Renvoi le type de la session du jour si elle existe
//     */
//    @SuppressWarnings("unused")
//    public String getCouncilSessionType() {
//        String title = "";
//
//        if (Validator.isNotNull(this.getCouncilSessions())) {
//            long typeId = this.councilSession.getTypeId();
//            Type type = TypeLocalServiceUtil.fetchType(typeId);
//            if (Validator.isNotNull(type)) {
//                title = type.getTitle();
//            }
//        }
//
//        return title;
//    }

    /**
     * Si l'utilisateur connecté est un utilisateur MonStrasbourg & co. sans lien avec les conseils
     */
    @SuppressWarnings("unused")
    public boolean isConfirmedCouncilUser() {
        return !isGuestUser()
                && !this.isSessionNotAvailable()
                && !this.isSimplePublikUser()
                && !this.isOfficialTypeMismatchOrNotActive()
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
     * Si il n'y a pas de conseils
     */
    @SuppressWarnings("unused")
    public boolean isSessionNotAvailable() {
        return this.getCouncilSessions().isEmpty() && this.getCouncilSession() == null;
    }

    /**
     * Si les types de la session et de l'élu ne collent pas ou si l'élu est inactif
     */
    @SuppressWarnings("unused")
    public boolean isOfficialTypeMismatchOrNotActive() {

        boolean result = false;
        Official currentOfficial = OfficialLocalServiceUtil.fetchOfficial(this.getOfficialId());
        List<CouncilSession> councilSessions = this.getCouncilSessions();

        if (currentOfficial != null && councilSessions != null) {
            List<CouncilSession> councilSessionsForOffical = getCouncilSessionsForOffical(councilSessions, currentOfficial);
            boolean hasType = councilSessionsForOffical.size() >= 1;

            if (hasType && !currentOfficial.getIsActive()) {
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

    public JSONObject fetchUserFront(long officialId, String officialDeviceInfo, long councilSessionId) {
        return DeliberationServiceUtil.getUserFront(officialId, officialDeviceInfo, councilSessionId);
    }

}
