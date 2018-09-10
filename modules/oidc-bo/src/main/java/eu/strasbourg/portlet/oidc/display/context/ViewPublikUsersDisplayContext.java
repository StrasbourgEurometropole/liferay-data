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
		
		int countResults = 0;
		
		if (this._publikUsers == null) {	
			
			this._publikUsers = PublikUserLocalServiceUtil.getPublikUsers(
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
		return this._publikUsers;
	}
	
	/**
	 * Retourne la liste des événements correspondant à la recherche lancée en ignorant la pagination
	 */
	private List<PublikUser> getAllPublikUsers() throws PortalException {
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
	 * Renvoie le nom de la colonne sur laquelle on fait le tri pour PublikUser
	 * 
	 * @return
	 * @throws PortalException 
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
			StrasbourgPortletKeys.OIDC_BO, StrasbourgPortletKeys.OIDC_BO,
			actionId);
	}

}
