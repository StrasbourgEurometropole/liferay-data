package eu.strasbourg.portlet.search.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.search.configuration.ExperimentalSearchConfiguration", localization = "content/Language", name = "portlet.strasbourg.configuration.name")
public interface ExperimentalSearchConfiguration {

	@Meta.AD(name = "template", deflt = "", required = false)
	public String template();

	@Meta.AD(name = "agendaFriendlyURL", required = false)
	public String agendaFriendlyURL();
	

	@Meta.AD(name = "criteria1", required = false)
	public String criteria1();
	
	@Meta.AD(name = "criteria1Options", required = false)
	public String[] criteria1Options();
	
	@Meta.AD(name = "criteria1OptionCategories", required = false)
	public String[] criteria1OptionCategories();
	

	@Meta.AD(name = "criteria2", required = false)
	public String criteria2();

	@Meta.AD(name = "criteria2Options", required = false)
	public String[] criteria2Options();
	
	@Meta.AD(name = "criteria2OptionCategories", required = false)
	public String[] criteria2OptionCategories();

}
