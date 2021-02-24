package eu.strasbourg.portlet.help.context;

import com.liferay.portal.kernel.exception.PortalException;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

public class ViewHelpSeekersDisplayContext extends ViewListBaseDisplayContext<PublikUser> {

    private List<PublikUser> helpSeekers;

    public ViewHelpSeekersDisplayContext(RenderRequest request, RenderResponse response) {
        super(PublikUser.class, request, response);
    }

    public List<PublikUser> getHelpSeekers() throws PortalException {
        int countResults = 0;

        if (helpSeekers == null) {
            helpSeekers = PublikUserLocalServiceUtil.getPublikUsers(
                    this.getSearchContainer().getStart(),
                    this.getSearchContainer().getEnd(),
                    this.getKeywords(),
                    this.getOrderByColSearchField(),
                    "desc".equals(this.getOrderByType()));

            countResults = PublikUserLocalServiceUtil.getPublikUsers(
                    this.getKeywords(),
                    this.getOrderByColSearchField(),
                    "desc".equals(this.getOrderByType())).size();
        }

        this.getSearchContainer().setTotal(countResults);

        return helpSeekers;
    }

    /**
     * Retourne la liste des utilisateurs correspondant à la recherche lancée en ignorant la pagination
     */
    public List<PublikUser> getAllHelpSeekers() throws PortalException {
        if (helpSeekers == null) {
            helpSeekers = PublikUserLocalServiceUtil.getAllPublikUsers();
        }
        return helpSeekers;
    }

    /**
     * Retourne la liste des PK de tous les demandeurs
     * @return liste de PK (ex: "1,5,7,8")
     */
    public String getAllHelpSeekerIds() throws PortalException {
        StringBuilder helpSeekerIds = new StringBuilder();
        for (PublikUser helpSeeker : this.getHelpSeekers()) {
            if (helpSeekerIds.length() > 0) {
                helpSeekerIds.append(",");
            }
            helpSeekerIds.append(helpSeeker.getPublikUserLiferayId());
        }
        return helpSeekerIds.toString();
    }

    /**
     * Renvoie le nom de la colonne sur laquelle on fait le tri pour demandeurs (PublikUser)
     */
    public String getOrderByColSearchField() {
        switch (this.getOrderByCol()) {
            case "first-name":
                return "firstName";
            case "email":
                return "email";
            case "banish-date":
                return "banishDate";
            default:
                return "lastName";
        }
    }

    /**
     * Wrapper autour du permission checker pour les permissions de module
     */
    public boolean hasPermission(String actionId) throws PortalException {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getScopeGroupId(),
                StrasbourgPortletKeys.HELP_BO, StrasbourgPortletKeys.HELP_BO,
                actionId);
    }

}