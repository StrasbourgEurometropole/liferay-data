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

	@Meta.AD(name = "displayDatesButtons", required = false, deflt = "false")
	public boolean displayDatesButtons();

	@Meta.AD(name = "displaySorting", required = false, deflt = "false")
	public boolean displaySorting();

	@Meta.AD(name = "displayAssetType", required = false, deflt = "false")
	public boolean displayAssetType();

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

//	@Meta.AD(name = "groupBy", required = false)
//	public long groupBy();

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
