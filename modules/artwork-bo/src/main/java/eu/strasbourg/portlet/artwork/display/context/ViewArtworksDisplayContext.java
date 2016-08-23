package eu.strasbourg.portlet.artwork.display.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.service.ArtworkLocalServiceUtil;

public class ViewArtworksDisplayContext {

	public ViewArtworksDisplayContext(RenderRequest request,
		RenderResponse response) {

		this._request = request;
		this._response = response;
		this._themeDisplay = (ThemeDisplay) _request
			.getAttribute(WebKeys.THEME_DISPLAY);
	}

	public SearchContainer<Artwork> getSearchContainer()
		throws PortalException {
		
		PortletURL iteratorURL = this._response.createRenderURL();
		iteratorURL.setParameter("tab", "artworks");
		iteratorURL.setParameter("orderByCol", this.getOrderByCol());
		iteratorURL.setParameter("orderByType", this.getOrderByType());
		iteratorURL.setParameter("filterCategoriesIds",
			this.getFilterCategoriesIds());
		iteratorURL.setParameter("keywords", this.getKeywords());
		
		if (this._searchContainer == null) {
			this._searchContainer = new SearchContainer<Artwork>(this._request,
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

	public List<Artwork> getArtworks() throws PortalException {
		if (this._artworks == null) {
			HttpServletRequest servletRequest = PortalUtil
				.getHttpServletRequest(_request);
			SearchContext searchContext = SearchContextFactory
				.getInstance(servletRequest);
			
			// On set les categories du search context
			String[] categoryIdsStrings = this.getFilterCategoriesIds()
				.split(",");
			if (categoryIdsStrings.length > 0) {
				long[] categoryIds = new long[categoryIdsStrings.length - 1];
				for (int i = 0; i < categoryIds.length; i++) {
					categoryIds[i] = Long.valueOf(categoryIdsStrings[i + 1]);
				}
				searchContext.setAssetCategoryIds(categoryIds);
			}

			// Init attributes, in case we come from edit page
			searchContext.setAttributes(new HashMap<String, Serializable>());
			searchContext.setGroupIds(new long[] {_themeDisplay.getSiteGroupIdOrLiveGroupId()});

			// Sorting
			Sort sort = SortFactoryUtil.create(this.getOrderByColSearchField(),
				this.getOrderByType().equals("desc"));
			searchContext.setSorts(sort);

			// Paging
			searchContext.setStart(this.getSearchContainer().getStart());
			searchContext.setEnd(this.getSearchContainer().getEnd());

			// Results
			List<Artwork> results = new ArrayList<Artwork>();
			Hits hits = ArtworkLocalServiceUtil.search(searchContext);
			for (Document document : hits.getDocs()) {
				Artwork artwork = ArtworkLocalServiceUtil.fetchArtwork(
					GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (artwork != null) {
					results.add(artwork);
				}
			}
			this.getSearchContainer().setTotal(hits.getLength());
			this._artworks = results;
		}
		return this._artworks;
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
		return new String[] { "title", "modified-date",
			"status" };
	}

	public List<AssetVocabulary> getVocabularies() {
		if (this._vocabularies == null) {
			this._vocabularies = ArtworkLocalServiceUtil.getAttachedVocabularies(this._themeDisplay.getScopeGroupId());
		}
		return this._vocabularies;			
	}

	/**
	 * Retourne la liste des IDs des cat�gories sur lesquels on doit filtrer les
	 * �ditions Liste sous forme de string qui se pr�sente comme suit :
	 * ",categoryId1,categoryId2,categoryId3," Si le param�tre
	 * "vocabularyToRemove" est pr�sent, on enl�ve les cat�gories appartenant �
	 * ce vocabulaire de la liste Si le param�tre "categoryToAdd" est pr�sent,
	 * on ajoute ladite cat�gorie � la liste
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
	 * Retourne le nom � afficher pour un filtre "Vocabulaire" - Si aucune
	 * cat�gorie du vocabulaire n'a �t� s�lectionn�e, le nom du vocabulaire - Si
	 * une cat�gorie du vocabulaire a �t� s�lectionn�e, le nom de la cat�gorie
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

	private final RenderRequest _request;
	private final RenderResponse _response;
	private final ThemeDisplay _themeDisplay;

	private SearchContainer<Artwork> _searchContainer;
	private List<Artwork> _artworks;
	private List<AssetVocabulary> _vocabularies;
	private String _keywords;
	private String _filterCategoriesIds;

}
