package eu.strasbourg.portlet.entity_detail.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
	id = "eu.strasbourg.portlet.entity_detail.configuration.EntityDetailConfiguration",
	localization = "content/Language",
	name = "portlet.entity_detail.configuration.name")
public interface EntityDetailConfiguration {

	@Meta.AD(name="className", deflt = "", required = false)
	public String className();
	
	@Meta.AD(name="classPK", required = false)
	public long classPK();
	
}
