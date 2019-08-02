package eu.strasbourg.portlet.association.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
		id = "eu.strasbourg.portlet.association.configuration.SearchAssociationConfiguration",
		localization = "content/Language",
		name = "portlet.association.configuration.name")
public interface SearchAssociationConfiguration {
	@Meta.AD(name = "templateKey", required = false)
	public String templateKey();

	@Meta.AD(name = "layoutFriendlyURL", required = false)
	public String layoutFriendlyURL();

	@Meta.AD(name = "delta", required = false)
	public long delta();
}
