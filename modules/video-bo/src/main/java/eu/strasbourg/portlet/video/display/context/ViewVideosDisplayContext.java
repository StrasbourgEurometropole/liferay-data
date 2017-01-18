package eu.strasbourg.portlet.video.display.context;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.service.VideoLocalServiceUtil;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

public class ViewVideosDisplayContext {

	public ViewVideosDisplayContext(RenderRequest request,
		RenderResponse response) {

		this._request = request;
		this._response = response;
		this._themeDisplay = (ThemeDisplay) _request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public SearchContainer<Video> getSearchContainer() throws PortalException {

		PortletURL iteratorURL = this._response.createRenderURL();
		iteratorURL.setParameter("tab", "videos");
		iteratorURL.setParameter("orderByCol", this.getOrderByCol());
		iteratorURL.setParameter("orderByType", this.getOrderByType());
		iteratorURL.setParameter("filterCategoriesIds",
			this.getFilterCategoriesIds());
		iteratorURL.setParameter("keywords", this.getKeywords());

		if (this._searchContainer == null) {
			this._searchContainer = new SearchContainer<Video>(this._request,
				iteratorURL, null, "no-entries-were-found");

			this._searchContainer.setEmptyResultsMessageCssClass(
				"taglib-empty-result-message-header-has-plus-btn");
			this._searchContainer
				.setRowChecker(new EmptyOnClickRowChecker(this._response));
			this._searchContainer.setOrderByColParam("orderByCol");
			this._searchContainer.setOrderByTypeParam("orderByType");
		}
		return _searchContainer;
	}

	public List<Video> getVideos() throws PortalException {
		if (this._videos == null) {
			HttpServletRequest servletRequest = PortalUtil
				.getHttpServletRequest(_request);
			SearchContext searchContext = SearchContextFactory
				.getInstance(servletRequest);

			// Recherche des hits
			String keywords = ParamUtil.getString(servletRequest, "keywords");
			Hits hits = SearchHelper.getBOSearchHits(searchContext,
				this.getSearchContainer().getStart(),
				this.getSearchContainer().getEnd(), Video.class.getName(),
				this._themeDisplay.getScopeGroupId(),
				this.getFilterCategoriesIds(), keywords,
				this.getOrderByColSearchField(),
				"desc".equals(this.getOrderByType()));

			// Total
			int count = (int) SearchHelper.getBOSearchCount(searchContext,
				Video.class.getName(), this._themeDisplay.getScopeGroupId(),
				this.getFilterCategoriesIds(), keywords);
			this.getSearchContainer().setTotal(count);

			// Création de la liste d'objet
			List<Video> results = new ArrayList<Video>();
			if (hits != null) {
				for (Document document : hits.getDocs()) {
					Video video = VideoLocalServiceUtil.fetchVideo(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
					if (video != null) {
						results.add(video);
					}
				}
			}
			this._videos = results;
		}
		return this._videos;
	}

	public String getKeywords() {
		if (Validator.isNull(_keywords)) {
			_keywords = ParamUtil.getString(_request, "keywords");
		}
		return _keywords;
	}

	public String getOrderByColSearchField() {
		switch (this.getOrderByCol()) {
		case "title":
			return "localized_title_fr_FR_sortable";
		case "modified-date":
			return "modified_sortable";
		case "status":
			return "status_sortable";
		default:
			return "modified_sortable";
		}
	}

	public String getOrderByCol() {
		return ParamUtil.getString(this._request, "orderByCol",
			"modified-date");
	}

	public String getOrderByType() {
		return ParamUtil.getString(this._request, "orderByType", "desc");
	}

	public String[] getOrderColumns() {
		return new String[] { "title", "modified-date", "status" };
	}

	public List<AssetVocabulary> getVocabularies() {
		if (this._vocabularies == null) {
			this._vocabularies = VideoLocalServiceUtil
				.getAttachedVocabularies(this._themeDisplay.getScopeGroupId());
		}
		return this._vocabularies;
	}

	/**
	 * Retourne la liste des IDs des catégories sur lesquels on doit filtrer les
	 * éditions Liste sous forme de string qui se présente comme suit :
	 * ",categoryId1,categoryId2,categoryId3," Si le paramètre
	 * "vocabularyToRemove" est présent, on enlève les catégories appartenant à
	 * ce vocabulaire de la liste Si le paramètre "categoryToAdd" est présent,
	 * on ajoute ladite catégorie à la liste
	 * 
	 * @return
	 * @throws PortalException
	 */
	public String getFilterCategoriesIds() throws PortalException {
		if (Validator.isNotNull(_filterCategoriesIds)) {
			return _filterCategoriesIds;
		}
		_filterCategoriesIds = ParamUtil.getString(_request,
			"filterCategoriesIds", ",");
		Long vocabularyToRemove = ParamUtil.getLong(_request,
			"vocabularyToRemove");
		if (vocabularyToRemove > 0) {
			AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil
				.getVocabulary(vocabularyToRemove);
			List<AssetCategory> categories = vocabulary.getCategories();
			for (AssetCategory category : categories) {
				if (_filterCategoriesIds
					.contains(String.valueOf(category.getCategoryId()))) {
					_filterCategoriesIds = _filterCategoriesIds
						.replace("," + category.getCategoryId(), "");
				}
			}
			_filterCategoriesIds = _filterCategoriesIds
				.replace(vocabularyToRemove + ",", "");
		}
		String categoryToAdd = ParamUtil.getString(_request, "categoryToAdd");
		if (Validator.isNotNull(categoryToAdd)) {
			_filterCategoriesIds += categoryToAdd + ",";
		}
		return _filterCategoriesIds;
	}

	/**
	 * Retourne le nom à afficher pour un filtre "Vocabulaire" - Si aucune
	 * catégorie du vocabulaire n'a été sélectionnée, le nom du vocabulaire - Si
	 * une catégorie du vocabulaire a été sélectionnée, le nom de la catégorie
	 */
	public String getVocabularyFilterLabel(AssetVocabulary vocabulary)
		throws PortalException {
		List<AssetCategory> categories = vocabulary.getCategories();
		for (AssetCategory category : categories) {
			if (this.getFilterCategoriesIds()
				.contains(String.valueOf(category.getCategoryId()))) {
				return category.getName();
			}
		}
		return vocabulary.getName();
	}

	/**
	 * @return True si le framework workflow est actif pour ce type d'entité
	 */
	public boolean isWorkflowEnabled() {
		return WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			_themeDisplay.getCompanyId(), _themeDisplay.getScopeGroupId(),
			Video.class.getName());
	}

	/**
	 * Wrapper autour du permission checker pour les permissions de module
	 */
	public boolean hasPermission(String actionId) throws PortalException {
		return _themeDisplay.getPermissionChecker().hasPermission(
			this._themeDisplay.getScopeGroupId(),
			StrasbourgPortletKeys.VIDEO_BO, StrasbourgPortletKeys.VIDEO_BO,
			actionId);
	}

	private final RenderRequest _request;
	private final RenderResponse _response;
	private final ThemeDisplay _themeDisplay;

	private SearchContainer<Video> _searchContainer;
	private List<Video> _videos;
	private List<AssetVocabulary> _vocabularies;
	private String _keywords;
	private String _filterCategoriesIds;

}
