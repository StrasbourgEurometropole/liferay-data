package eu.strasbourg.portlet.resid.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.resid.configuration.ResidConfiguration", localization = "content/Language", name = "portlet.resid.configuration.name")
public interface ResidConfiguration {

	@Meta.AD(name = "maintenance", required = false)
	public boolean maintenance();

	@Meta.AD(name = "liaisonURL", deflt = "", required = false)
	public String liaisonURL();

	@Meta.AD(name = "zones", deflt = "", required = false)
	public String[] zones();

}
