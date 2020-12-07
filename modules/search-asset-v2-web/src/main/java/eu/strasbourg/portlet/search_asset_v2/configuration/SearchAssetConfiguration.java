package eu.strasbourg.portlet.search_asset_v2.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	id = "eu.strasbourg.portlet.search_asset_v2.configuration.SearchAssetConfiguration",
	localization = "content/Language",
	name = "portlet.search_asset_v2.configuration.name"
)
public interface SearchAssetConfiguration {

	// TODO clean
	@Meta.AD(name = "templatesKeys", required = false)
	public String templatesKeys();

	@Meta.AD(name = "assetClassNamesIds", required = false)
	public String assetClassNamesIds();

	@Meta.AD(name = "assetClassNames", required = false)
	public String assetClassNames();

	@Meta.AD(name = "layoutsFriendlyURLs", required = false)
	public String layoutsFriendlyURLs();

	@Meta.AD(name = "searchJournalArticle", required = false)
	public boolean searchJournalArticle();

	@Meta.AD(name = "journalArticleTemplateKey", required = false)
	public String journalArticleTemplateKey();

	@Meta.AD(name = "vocabulariesIds", required = false)
	public String vocabulariesIds();

	@Meta.AD(name = "prefilterCategoriesIds", required = false)
	public String prefilterCategoriesIds();

	@Meta.AD(name = "prefilterTagsNames", required = false)
	public String prefilterTagsNames();

	@Meta.AD(name = "defaultSortField", required = false, deflt = "modified_sortable")
	public String defaultSortField();

	@Meta.AD(name = "defaultSortType", required = false, deflt = "desc")
	public String defaultSortType();

	@Meta.AD(name = "defaultDateRange", required = false, deflt = "31")
	public long defaultDateRange();

	@Meta.AD(name = "searchDocument", required = false)
	public boolean searchDocument();

	@Meta.AD(name = "documentTemplateKey", required = false)
	public String documentTemplateKey();

	@Meta.AD(name = "globalScope", required = false)
	public boolean globalScope();

	@Meta.AD(name = "dateField", required = false)
	public boolean dateField();

	@Meta.AD(name = "displayDateSorting", required = false, deflt = "false")
	public boolean displayDateSorting();

	@Meta.AD(name = "searchDemarche", required = false)
	public boolean searchDemarche();
	// TODO end clean

	// Search asset v2
	/**
	 * JSON des types d'asset
	 */
	@Meta.AD(name = "assetTypes", required = false)
	public String assetTypes();

	/**
	 * JSON des vocabulaires accessibles en filtre par le visiteur
	 */
	@Meta.AD(name = "vocabulariesControlTypes", required = false)
	public String vocabulariesControlTypes();

	@Meta.AD(name = "displayDateField", required = false, deflt = "false")
	public boolean displayDateField();

	@Meta.AD(name = "displaySorting", required = false, deflt = "false")
	public boolean displaySorting();

	@Meta.AD(name = "boostTagsNames", required = false)
	public String boostTagsNames();

	@Meta.AD(name = "filterField", required = false, deflt = "modified_sortable")
	public String filterField();

	@Meta.AD(name = "defaultFilterDateRange", required = false, deflt = "31")
	public long defaultFilterDateRange();

	@Meta.AD(name = "randomSort", required = false, deflt = "false")
	public boolean randomSort();

	@Meta.AD(name = "firstSortingField", required = false, deflt = "modified_sortable")
	public String firstSortingField();

	@Meta.AD(name = "firstSortingType", required = false, deflt = "desc")
	public String firstSortingType();

	@Meta.AD(name = "secondSortingField", required = false, deflt = "title")
	public String secondSortingField();

	@Meta.AD(name = "secondSortingType", required = false, deflt = "asc")
	public String secondSortingType();

	@Meta.AD(name = "groupBy", required = false)
	public String groupBy();

	@Meta.AD(name = "hideResultsBeforeSearch", required = false)
	public boolean hideResultsBeforeSearch();

	@Meta.AD(name = "delta", required = false, deflt = "20")
	public long delta();

	@Meta.AD(name = "searchForm", required = false)
	public String searchForm();

	@Meta.AD(name = "displayExport", required = false)
	public boolean displayExport();

	@Meta.AD(name = "exportType", required = false)
	public String exportType();

}
