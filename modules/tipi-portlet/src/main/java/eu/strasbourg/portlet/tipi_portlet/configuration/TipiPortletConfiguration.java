package eu.strasbourg.portlet.tipi_portlet.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.tipi_portlet.configuration.TipiPortletConfiguration", localization = "content/Language", name = "portlet.tipi_portlet.configuration.name")
public interface TipiPortletConfiguration {

	@Meta.AD(name = "form", deflt = "", required = false)
	public String form();

}
