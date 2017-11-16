package eu.strasbourg.portlet.search_asset.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
	id = "eu.strasbourg.portlet.search_asset.configuration.SearchAssetConfiguration",
	localization = "content/Language",
	name = "portlet.search_asset.configuration.name")
public interface SearchAssetConfiguration {
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

	@Meta.AD(name = "vocabulariesControlTypes", required = false)
	public String vocabulariesControlTypes();

	@Meta.AD(name = "prefilterCategoriesIds", required = false)
	public String prefilterCategoriesIds();

	@Meta.AD(name = "prefilterTagsNames", required = false)
	public String prefilterTagsNames();
	
	@Meta.AD(name = "boostTagsNames", required = false)
	public String boostTagsNames();
	
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

	@Meta.AD(name = "hideResultsBeforeSearch", required = false)
	public boolean hideResultsBeforeSearch();
	
	@Meta.AD(name = "delta", required = false)
	public long delta();
	
	@Meta.AD(name = "searchForm", required = false)
	public String searchForm();
	
	@Meta.AD(name = "displayExport", required = false)
	public boolean displayExport();
	
	@Meta.AD(name = "exportType", required = false)
	public String exportType();
}
