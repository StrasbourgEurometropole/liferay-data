package eu.strasbourg.portlet.legacy.place.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
	id = "eu.strasbourg.portlet.legacy.place.configuration.LegacyPlaceViewerConfiguration",
	localization = "content/Language",
	name = "portlet.legacy.place.configuration.name")
public interface LegacyPlaceViewerConfiguration {

	@Meta.AD(name="SIGId", deflt = "", required = false)
	public String SIGId();
	
	@Meta.AD(name="placeName", deflt = "", required = false)
	public String placeName();
}
