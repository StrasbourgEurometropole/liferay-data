package eu.strasbourg.portlet.publik_user.display.context;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.Pager;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;
import java.util.stream.Collectors;

public class PublikUserDisplayContext {

    /**
     * Retourne l'URL de la page d'accueil
     */
    public String getHomeURL() {
        if (this._themeDisplay.getScopeGroup().getPublicLayoutSet().getVirtualHostname() != null
                || this._themeDisplay.getScopeGroup().isStagingGroup()) {
            return "/web" + this._themeDisplay.getScopeGroup().getFriendlyURL() + "/";
        } else {
            return "/";
        }

    }

    public PublikUserDisplayContext(RenderRequest request, RenderResponse response) throws PortalException {

        this._response = response;
        this._request = request;
        this._themeDisplay = (ThemeDisplay) _request.getAttribute(WebKeys.THEME_DISPLAY);
        this.initSearchContainer();
        this.initEntries();
    }

    private void initSearchContainer() {
        PortletURL iteratorURL = this._response.createRenderURL();

        if (this._searchContainer == null) {
            this._searchContainer = new SearchContainer<PublikUser>(this._request, iteratorURL, null,
                    "no-entries-were-found");
            this._searchContainer.getIteratorURL().setParameter("delta", String.valueOf(this.getDelta()));

            this._searchContainer.setDelta(this.getDelta());
        }
    }

    /**
     * Effectue concrètement la recherche
     */
    private void initEntries() throws PortalException {
        // On récupère tous les publikUser qui ont signé le pacte
        this._publikUsers = PublikUserLocalServiceUtil.getAllPublikUsers()
                .stream()
                .filter(p -> Validator.isNotNull(p.getPactSignature()) && p.isPactDisplay())
                .sorted((o1,o2) -> o1.getPactSignature().compareTo(o2.getPactSignature()))
                .collect(Collectors.toList());
        this.getSearchContainer().setTotal(this._publikUsers.size());
    }

    /**
     * Retourne les résultats de la recherche
     */
    public List<PublikUser> getPublikUsers() throws PortalException {
        return this._publikUsers.subList(this.getSearchContainer().getStart(),
                (this.getSearchContainer().getEnd() > this._publikUsers.size() ? this._publikUsers.size() : this.getSearchContainer().getEnd()));
    }

    /**
     * Retourne le nombre d'items par page à afficher
     */
    public int getDelta() {
        return 12;
    }

    public Pager getPager() {
        return new Pager(this.getSearchContainer().getTotal(), this.getDelta(), this.getSearchContainer().getCur());
    }

    /**
     * Retourne l'URL sur laquelle aller pour accéder à la Xième page
     */
    public String getURLForPage(long pageIndex) {
        PortletURL url = this.getSearchContainer().getIteratorURL();
        url.setParameter("cur", String.valueOf(pageIndex));
        String valueToReturn = url.toString();
        url.setParameter("cur", String.valueOf(this.getSearchContainer().getCur()));
        return valueToReturn;
    }


    /**
     * Retourne le searchContainer des entités
     */
    public SearchContainer<PublikUser> getSearchContainer() {
        return this._searchContainer;
    }


    private static Log _log = LogFactoryUtil.getLog(PublikUserDisplayContext.class);

    private final RenderRequest _request;
    private final RenderResponse _response;
    private final ThemeDisplay _themeDisplay;

    private SearchContainer<PublikUser> _searchContainer;
    private List<PublikUser> _publikUsers;

}
