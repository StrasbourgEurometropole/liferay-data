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
	@Meta.AD(name="templatesKeys", required=false)
	public String templatesKeys(); 
	
	@Meta.AD(name="assetClassNamesIds", required=false)
	public String assetClassNamesIds(); 
	
	@Meta.AD(name="assetClassNames", required=false)
	public String assetClassNames(); 
	
	@Meta.AD(name="layoutsFriendlyURLs", required=false)
	public String layoutsFriendlyURLs();
	
	@Meta.AD(name="vocabulariesIds", required=false)
	public String vocabulariesIds();
	
	@Meta.AD(name="delta", required=false)
	public long delta();
}
