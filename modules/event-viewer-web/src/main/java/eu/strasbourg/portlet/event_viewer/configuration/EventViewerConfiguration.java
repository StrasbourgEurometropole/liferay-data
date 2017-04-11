package eu.strasbourg.portlet.event_viewer.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
	id = "eu.strasbourg.portlet.entity_detail.configuration.InternalLinkViewerConfiguration",
	localization = "content/Language",
	name = "portlet.entity_detail.configuration.name")
public interface EventViewerConfiguration {

	@Meta.AD(name = "categoriesIds", deflt = "", required = false)
	public String categoriesIds();

	@Meta.AD(name = "tagsNames", deflt = "", required = false)
	public String tagsNames();

	@Meta.AD(name = "agendaPageUuid", deflt = "", required = false)
	public String agendaPageUuid();
	
	@Meta.AD(name = "agendaURL", deflt = "", required = false)
	public String agendaURL();
	
	@Meta.AD(name = "agendaURLText", deflt = "", required = false)
	public String agendaURLText();

	// Format : yyyy-MM-dd
	@Meta.AD(name = "fromDate", deflt = "", required = false)
	public String fromDate();

	// Format : yyyy-MM-dd
	@Meta.AD(name = "toDate", deflt = "", required = false)
	public String toDate();

}
