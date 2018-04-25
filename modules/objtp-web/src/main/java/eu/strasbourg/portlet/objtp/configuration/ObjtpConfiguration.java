package eu.strasbourg.portlet.objtp.configuration;

import java.util.List;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
	id = "eu.strasbourg.portlet.objtp.configuration.ObjtpConfiguration",
	localization = "content/Language",
	name = "portlet.objtp.configuration.name")
public interface ObjtpConfiguration {

	@Meta.AD(name="title", deflt = "", required = false)
	public String title();
	
	@Meta.AD(name="categoryCodes", deflt = "", required = false)
	public String categoryCodes();
	
}
