package eu.strasbourg.portlet.gtfs.display.context;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.gtfs.model.Arret;
import eu.strasbourg.service.gtfs.service.ArretLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;
import eu.strasbourg.utils.display.context.ViewListBaseDisplayContext;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ViewArretsDisplayContext
		extends ViewListBaseDisplayContext<Arret> {

	public ViewArretsDisplayContext(RenderRequest request,
									RenderResponse response) {
		super(Arret.class, request, response);
	}

	public List<Arret> getArrets() throws PortalException {
		if (this._arrets == null) {
			List<Arret> results = new ArrayList<Arret>();
			Hits hits = getHits(this._themeDisplay.getCompanyGroupId());

			if (hits != null)
			{
				for (Document document : hits.getDocs())
				{
					Arret arret = ArretLocalServiceUtil.fetchArret(GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (arret != null)
					{
						results.add(arret);
					}
				}
			}
			this._arrets = results;
		}
		return this._arrets;
	}

	/**
	 * Retourne la liste des ids de categories sur lesquels la liste des arrêts
	 * est filtrée. Si l'utilisateur est un contributeur arrêt, on filtre
	 * toujours sur les catégories liées à l'utilisateur. Le front-end n'affiche
	 * que les catégories sur lesquels l'utilisateur a le droit de filtrer, on
	 * considère donc que getFilterCategories ne peut renvoyer que des
	 * catégories autorisées pour l'utilisateur
	 */
	@Override
	public String getFilterCategoriesIds() throws PortalException {
		// Pas de filtre par l'utilisateur
		if (Validator.isNull(super.getFilterCategoriesIds())|| super.getFilterCategoriesIds().equals(","))
		{
			return this.getCategoriesIdsPermission();
		}
		else
		{
			return super.getFilterCategoriesIds();
		}
	}

	/**
	 * Retourne la liste des IDs des catégories que l'utilisateur peut voir
	 *
	 * @return
	 * @throws PortalException
	 */
	private String getCategoriesIdsPermission() throws PortalException {
		String categoriesIds = "";
		if (this.hasPermission("CONTRIBUTE")) {
			User user = _themeDisplay.getUser();
			AssetVocabulary placeTypeVocabulary = AssetVocabularyHelper
					.getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
			if (placeTypeVocabulary != null) {
				long placeTypeVocabularyId = placeTypeVocabulary
						.getVocabularyId();
				List<AssetCategory> userCategories = AssetCategoryLocalServiceUtil
						.getCategories(User.class.getName(), user.getUserId());
				List<AssetCategory> userPlaceTypeCategories = userCategories
						.stream()
						.filter(c -> c.getVocabularyId() == placeTypeVocabularyId)
						.collect(Collectors.toList());

				for (AssetCategory category : userPlaceTypeCategories) {
					if (Validator.isNull(categoriesIds)) {
						categoriesIds += ",";
					}
					categoriesIds += String.valueOf(category.getCategoryId());
					categoriesIds += ",";
				}
			}
		}


		return categoriesIds;
	}

	/**
	 * Retourne les Hits de recherche
	 */
	@Override
	protected Hits getHits(long groupId) throws PortalException {
		HttpServletRequest servletRequest = PortalUtil
				.getHttpServletRequest(_request);
		SearchContext searchContext = SearchContextFactory
				.getInstance(servletRequest);

		// Recherche des hits
		String keywords = ParamUtil.getString(servletRequest, "keywords");
		Hits hits = SearchHelper.getBOSearchHits(searchContext,
				this.getSearchContainer().getStart(),
				this.getSearchContainer().getEnd(), Arret.class.getName(), groupId,
				this.getFilterCategoriesIds(), keywords,
				this.getOrderByColSearchField(),
				"desc".equals(this.getOrderByType()), BooleanClauseOccur.SHOULD);

		// Total
		int count = (int) SearchHelper.getBOSearchCount(searchContext,
				Arret.class.getName(), groupId,
				this.getFilterCategoriesIds(), keywords, BooleanClauseOccur.SHOULD);
		this.getSearchContainer().setTotal(count);

		// Dans le cas d'un contributeur lieu n'ayant pas de catégorie (l'admin peut tjrs tous les voir, même sans catégories)
		// Notes : pas utiliser pour le moment
//		if(!_themeDisplay.getPermissionChecker().isOmniadmin()  && this.getCategoriesIdsPermission().isEmpty())
//			return null;

		return hits;
	}

	/**
	 * Retourne les Hits de recherche en ignorant la pagination
	 */
	@Override
	protected Hits getAllHits(long groupId) throws PortalException {
		HttpServletRequest servletRequest = PortalUtil
				.getHttpServletRequest(_request);
		SearchContext searchContext = SearchContextFactory
				.getInstance(servletRequest);

		// Recherche des hits
		String keywords = ParamUtil.getString(servletRequest, "keywords");
		Hits hits = SearchHelper.getBOSearchHits(searchContext,
				-1, -1, Arret.class.getName(), groupId,
				this.getFilterCategoriesIds(), keywords,
				this.getOrderByColSearchField(),
				"desc".equals(this.getOrderByType()), BooleanClauseOccur.SHOULD);

		// Total
		int count = (int) SearchHelper.getBOSearchCount(searchContext,
				Arret.class.getName(), groupId,
				this.getFilterCategoriesIds(), keywords, BooleanClauseOccur.SHOULD);
		this.getSearchContainer().setTotal(count);

		// Dans le cas d'un contributeur lieu n'ayant pas de catégorie (l'admin peut tjrs tous les voir, même sans catégories)
		// Notes : pas utiliser pour le moment
//		if(!_themeDisplay.getPermissionChecker().isOmniadmin()  && this.getCategoriesIdsPermission().isEmpty())
//			return null;

		return hits;
	}

    /**
     * Wrapper autour du permission checker pour les permissions de module
     */
    public boolean hasPermission(String actionId) throws PortalException {
        return _themeDisplay.getPermissionChecker().hasPermission(
                this._themeDisplay.getCompanyGroupId(),
                StrasbourgPortletKeys.GTFS_BO, StrasbourgPortletKeys.GTFS_BO,
                actionId);
    }

    /**
     * Retourne le nom d'un utilisateur par son Id
     */
    public String getUserName(long userId) throws PortalException {
        User user = UserLocalServiceUtil.getUser(userId);
        if(Validator.isNotNull(user))
            return user.getFullName();

        return "";
    }

	private List<Arret> _arrets;

}
