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
	@Meta.AD(name="assetClassNameId", required=false)
	public long assetClassNameId(); 
	
	@Meta.AD(name="assetClassName", required=false)
	public String assetClassName(); 
	
	@Meta.AD(name="vocabulariesIds", required=false)
	public String vocabulariesIds();
	
	@Meta.AD(name="delta", required=false)
	public long delta();
}
