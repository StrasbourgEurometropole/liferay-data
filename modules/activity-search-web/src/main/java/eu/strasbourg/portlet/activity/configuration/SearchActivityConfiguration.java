package eu.strasbourg.portlet.activity.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
	id = "eu.strasbourg.portlet.activity.configuration.SearchActivityConfiguration",
	localization = "content/Language",
	name = "portlet.activity.configuration.name")
public interface SearchActivityConfiguration {

	@Meta.AD(name = "detailPageUuid", deflt = "", required = false)
	public String detailPageUuid();

}
