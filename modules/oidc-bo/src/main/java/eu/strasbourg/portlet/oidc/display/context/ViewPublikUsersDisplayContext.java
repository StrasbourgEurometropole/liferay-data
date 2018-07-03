package eu.strasbourg.portlet.oidc.display.context;

import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

public class ViewPublikUsersDisplayContext extends ViewListBaseDisplayContext<PublikUser> {
	
	private List <PublikUser> _publikUsers;

	public ViewPublikUsersDisplayContext(RenderRequest request, RenderResponse response) 
			throws PortalException {
		super(PublikUser.class, request, response);
	}
	
	public List<PublikUser> getPublikUsers() throws PortalException {
		if (this._publikUsers == null) {
			this._publikUsers = PublikUserLocalServiceUtil.getAllPublikUsers();
		}
		return this._publikUsers;
	}
	
	/**
	 * Retourne la liste des PK de tous les projets
	 * @return liste de PK (ex: "1,5,7,8")
	 */
	public String getAllPublikUserIds() throws PortalException {
		String publikUserIds = "";
		for (PublikUser publikUser : this.getPublikUsers()) {
			if (publikUserIds.length() > 0) {
				publikUserIds += ",";
			}
			publikUserIds += publikUser.getPublikUserLiferayId();
		}
		return publikUserIds;
	}
	
	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.OIDC_BO, StrasbourgPortletKeys.OIDC_BO,
			actionId);
	}

}
