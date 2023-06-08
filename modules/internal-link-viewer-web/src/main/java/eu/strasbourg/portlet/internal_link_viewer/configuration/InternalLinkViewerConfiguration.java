package eu.strasbourg.portlet.internal_link_viewer.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
	id = "eu.strasbourg.portlet.entity_detail.configuration.InternalLinkViewerConfiguration",
	localization = "content/Language",
	name = "portlet.entity_detail.configuration.name")
public interface InternalLinkViewerConfiguration {

	@Meta.AD(name="linksUuids", deflt = "", required = false)
	public String linksUuids();

	@Meta.AD(name="template", deflt="default", required= false)
	public String template();
}
