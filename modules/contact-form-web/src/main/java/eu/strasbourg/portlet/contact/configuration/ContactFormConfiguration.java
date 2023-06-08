package eu.strasbourg.portlet.contact.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
	id = "eu.strasbourg.portlet.contact.configuration.ContactFormConfiguration",
	localization = "content/Language",
	name = "portlet.contact.configuration.name")
public interface ContactFormConfiguration {
	
	@Meta.AD(name="template", deflt = "", required = false)
	public String template();

	@Meta.AD(name="title", deflt = "", required = false)
	public String title();
	
	@Meta.AD(name="email", deflt = "", required = false)
	public String email();
	
	@Meta.AD(name="descriptionText", deflt = "", required = false)
	public String descriptionText();
	
	@Meta.AD(name="privacyText", deflt = "", required = false)
	public String privacyText();
		
}
