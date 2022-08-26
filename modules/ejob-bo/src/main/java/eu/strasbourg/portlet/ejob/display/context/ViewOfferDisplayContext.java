package eu.strasbourg.portlet.ejob.display.context;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.OfferLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.constants.RoleNames;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

public class ViewOfferDisplayContext
	extends ViewListBaseDisplayContext<Offer> {
	private List<Offer> _offers;
	private final RenderRequest request;
	private final ThemeDisplay themeDisplay;
	private AssetVocabulary filieres;
	private AssetVocabulary niveauEtudes;

	public ViewOfferDisplayContext(RenderRequest request,
								   RenderResponse response) {
		super(Offer.class, request, response);
		this.request = request;
		this.themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	}
	@SuppressWarnings("unused")
	public List<Offer> getOffers() throws PortalException {
		if (this._offers == null) {
			Hits hits = getHits(this._themeDisplay.getScopeGroupId());

			// Création de la liste d'objet
			List<Offer> results = new ArrayList<Offer>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					Offer offer = OfferLocalServiceUtil.fetchOffer(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (offer != null) {
						results.add(offer);
					}
				}
			}
			this._offers = results;
		}
		return this._offers;
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.EJOB_BO, StrasbourgPortletKeys.EJOB_BO,
			actionId);
	}


	public boolean isContribOnly(){
		try {
			if(isAdminOrResp())
				return false;

			Role assistantRecrutement = RoleLocalServiceUtil.getRole(this.themeDisplay.getCompanyId(), RoleNames.ASSISTANT_RECRUTEMENT);
			return UserGroupRoleLocalServiceUtil.hasUserGroupRole(themeDisplay.getUserId(),themeDisplay.getScopeGroupId(), assistantRecrutement.getRoleId());
		} catch (PortalException e) {
			_log.error(e.getMessage(), e);
		}
		return false;
	}


	public boolean isAdminOrResp(){
		try {
			Role  responsableEmploi = RoleLocalServiceUtil.getRole(this.themeDisplay.getCompanyId(), RoleNames.RESPONSABLE_EMPLOI);
			Role siteAdministrator = RoleLocalServiceUtil.getRole(this.themeDisplay.getCompanyId(), RoleNames.SITE_ADMLINISTRATOR);
			if(themeDisplay.getPermissionChecker().isOmniadmin()
					|| UserGroupRoleLocalServiceUtil.hasUserGroupRole(themeDisplay.getUserId(),themeDisplay.getScopeGroupId(), responsableEmploi.getRoleId())
					|| UserGroupRoleLocalServiceUtil.hasUserGroupRole(themeDisplay.getUserId(),themeDisplay.getScopeGroupId(), siteAdministrator.getRoleId()))
				return true;
		} catch (PortalException e) {
			_log.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * Renvoie le vocabulaire Filieres
	 */
	@SuppressWarnings("unused")
	public AssetVocabulary getFilieres() {
		long groupId = themeDisplay.getLayout().getGroupId();
		this.filieres = AssetVocabularyAccessor.getEJobFilieres(groupId);
		return this.filieres;
	}

	/**
	 * Renvoie le vocabulaire Niveau d'étude
	 */
	@SuppressWarnings("unused")
	public AssetVocabulary getNiveauEtudes() {
		long groupId = themeDisplay.getLayout().getGroupId();
		this.niveauEtudes = AssetVocabularyAccessor.getEJobNiveauEtude(groupId);
		return this.niveauEtudes;
	}

	/**
	 * Renvoie le nom de la colonne sur laquelle on fait le tri pour
	 * ElasticSearch
	 */
	@Override
	public String getOrderByColSearchField() {
		switch (this.getOrderByCol()) {
			case "title":
				return "localized_title_fr_FR_sortable";
			case "end-date":
				return "endDate_Number_sortable";
			case "status":
				return "status_sortable";
			default:
				return "publishDate_sortable";
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass());

}
