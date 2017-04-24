package eu.strasbourg.portlet.place_schedule.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.place_schedule.configuration.PlaceScheduleConfiguration", localization = "content/Language", name = "portlet.place_schedule.configuration.name")
public interface PlaceScheduleConfiguration {

	@Meta.AD(name = "categoryId", deflt = "", required = false)
	public long categoryId();

	@Meta.AD(name = "categoryTitle", deflt = "", required = false)
	public String categoryTitle();

	@Meta.AD(name = "textScheduleXML", deflt = "", required = false)
	public String textScheduleXML();

	@Meta.AD(name="linksUuids", deflt = "", required = false)
	public String linksUuids();

}
