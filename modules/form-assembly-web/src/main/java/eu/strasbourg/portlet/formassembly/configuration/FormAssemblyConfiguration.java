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

	/**
	 * URL du webservice de FormAssemnbly
	 * Configuration globale à saisir dans le control panel de Liferay
	 */
	@Meta.AD(name="path", required = false)
	public String path();
	
	/**
	 * Token de l'API de FormAssembly
	 * Configuration globale à saisir dans le control panel de Liferay
	 */
	@Meta.AD(name="token", required = false)
	public String token();

	/**
	 * ID du formulaire à affiche
	 * String localisable (un ID par langue)
	 */
	@Meta.AD(name="formId", required = false)
	public String formId();
	
}
