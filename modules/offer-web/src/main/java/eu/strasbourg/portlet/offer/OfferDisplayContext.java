package eu.strasbourg.portlet.offer;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.offer.configuration.OfferConfiguration;
import eu.strasbourg.service.ejob.model.Alert;
import eu.strasbourg.service.ejob.service.AlertLocalServiceUtil;
import eu.strasbourg.utils.Pager;
import eu.strasbourg.utils.PublikApiClient;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OfferDisplayContext {

    private ThemeDisplay themeDisplay;
    private RenderRequest request;
    private RenderResponse response;
    private Log log = LogFactoryUtil.getLog(this.getClass());
    private OfferConfiguration configuration;
    private String publikId;
    private List<Alert> alerts;
    private List<Application> applications;
    protected SearchContainer<Alert> alertSearchContainer;
    protected SearchContainer<Application> applicationSearchContainer;

    public OfferDisplayContext(RenderRequest request, RenderResponse response) {
        this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        this.request = request;
        this.response = response;
        try {
            configuration = this.themeDisplay.getPortletDisplay()
                    .getPortletInstanceConfiguration(OfferConfiguration.class);
        } catch (Exception e) {
            log.error(e);
        }
    }

    public String getIntro() {
        String intro = configuration.introduction();
        if (Validator.isNull(intro)) {
            intro = "No configuration";
        }
        return intro;
    }

    // Récupération de l'id utilisateur
    private String getPublikID(PortletRequest resourceRequest) {
        if(Validator.isNull(this.publikId)) {
            LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
            HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
            this.publikId = SessionParamUtil.getString(originalRequest, "publik_internal_id");
        }

        return this.publikId;
    }

    /**
     * Retourne toutes les candidatures
     */
    public List<Application> getApplications(){
        if (this.applications == null) {
            // Récupération du publik ID avec la session
            String internalId = getPublikID(request);

            // Récupération de la liste des candidatures de l'utilisateur
            this.applications = new ArrayList<Application>();
            JSONObject userForms = PublikApiClient.getUserForms(internalId, true);
            if (userForms.toString().equals("{}")) {
                request.setAttribute("error", "publik");
            } else {
                JSONArray forms = userForms.getJSONArray("data");
                if(forms != null){
                    for (int  i=0; i<forms.length(); i++) {
                        JSONObject form = forms.getJSONObject(i);
                        if(form.getString("category_id").equals("nadege")){
                            Application application = new Application(form.getString("form_display_name"), form.getString("url"), form.getString("form_number"), form.getString("form_receipt_date"), form.getString("form_status"));
                            this.applications.add(application);
                        }
                    }
                }
            }
        }

        return this.applications;
    }

    /**
     * Retourne les candidatures entre les indexes start (inclu) et end (non inclu)
     */
    public List<Application> getApplicationPaginatedResults() {
        return ListUtil.subList(getApplications(), this.getApplicationSearchContainer().getStart(), this.getApplicationSearchContainer().getEnd());
    }

    /**
     * Retourne le nombre total de candidatures
     */
    public int getResultCount() {
        return getApplications().size();
    }

    /**
     * Retourne le searchContainer des candidatures
     */
    public SearchContainer<Application> getApplicationSearchContainer() {
        if (applicationSearchContainer == null) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            PortletURL iteratorURL = response.createRenderURL();
            iteratorURL.setParameters(parameterMap);
            applicationSearchContainer = new SearchContainer<Application>(request, iteratorURL, null,
                    "no-application");
            applicationSearchContainer.setDelta(this.getDelta());
            applicationSearchContainer.setTotal(this.getResultCount());
            applicationSearchContainer.setResults(applications);
        }
        return applicationSearchContainer;
    }

    /**
     * Retourne le nombre de candidatures à afficher par page
     */
    public int getDelta() {
        return 5;
    }

    /**
     * Retourne le pager de la page de candidatures
     */
    public Pager getApplicationPager() {
        return new Pager(this.getApplicationSearchContainer().getTotal(), (int) this.getDelta(),
                this.getApplicationSearchContainer().getCur());
    }

    /**
     * Retourne l'URL sur laquelle aller pour accéder à la Xième page da candidature
     */
    public String getApplicationURLForPage(long pageIndex) {
        PortletURL url = this.getApplicationSearchContainer().getIteratorURL();
        url.setParameter("cur", String.valueOf(pageIndex));
        String valueToReturn = url.toString();
        url.setParameter("cur", String.valueOf(this.getApplicationSearchContainer().getCur()));
        return valueToReturn;
    }

    public String getTexte() {
        String text =  configuration.text();
        if (Validator.isNull(text)) {
            text = "No configuration";
        }
        return text;
    }

    /**
     * Retourne toutes les alertes
     */
    public List<Alert> getAlerts(){
        if (this.alerts == null) {
            // Récupération du publik ID avec la session
            String internalId = getPublikID(request);

            // Récupération de la liste des alertes de l'utilisateur
            this.alerts = AlertLocalServiceUtil.findByPublikUserId(internalId);
        }

        return this.alerts;
    }

    /**
     * Retourne le nombre total d'alertes
     */
    public int getResultAlertCount() {
        return getAlerts().size();
    }

    /**
     * Retourne le searchContainer d'alerte
     */
    public SearchContainer<Alert> getAlertSearchContainer() {
        if (alertSearchContainer == null) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            PortletURL iteratorURL = response.createRenderURL();
            iteratorURL.setParameters(parameterMap);
            alertSearchContainer = new SearchContainer<Alert>(request, iteratorURL, null,
                    "no-alert");
            alertSearchContainer.setDelta(0);
            alertSearchContainer.setTotal(this.getResultAlertCount());
            alertSearchContainer.setResults(this.alerts);
        }
        return alertSearchContainer;
    }

    public String getSearchOfferURL() {
        String url =  configuration.url();
        if (Validator.isNull(url)) {
            url = "No configuration";
        }
        return url;
    }
}