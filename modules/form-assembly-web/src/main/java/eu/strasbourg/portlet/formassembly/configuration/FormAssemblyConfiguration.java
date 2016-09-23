package eu.strasbourg.portlet.formassembly.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.SYSTEM)
@Meta.OCD(
	id = "eu.strasbourg.portlet.formassembly.configuration.FormAssemblyConfiguration",
	localization = "content/Language",
	name = "portlet.formassembly.configuration.name")
public interface FormAssemblyConfiguration {

	@Meta.AD(name="path", required = false)
	public String path();
	
	@Meta.AD(name="token", required = false)
	public String token();

	@Meta.AD(name="formId", required = false)
	public String formId();
	
}
