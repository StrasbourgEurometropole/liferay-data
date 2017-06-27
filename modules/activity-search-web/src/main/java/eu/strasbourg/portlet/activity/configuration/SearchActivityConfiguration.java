package eu.strasbourg.portlet.activity.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
	id = "eu.strasbourg.portlet.activity.configuration.SearchActivityConfiguration",
	localization = "content/Language",
	name = "portlet.activity.configuration.name")
public interface SearchActivityConfiguration {

	@Meta.AD(name = "detailPageUuid", deflt = "", required = false)
	public String detailPageUuid();

	@Meta.AD(name = "activityTypeIds", deflt = "", required = false)
	public String activityTypeIds();

	@Meta.AD(name = "activityTypeUuids", deflt = "", required = false)
	public String activityTypeUuids();

	@Meta.AD(name = "activityTypeNames", deflt = "", required = false)
	public String activityTypeNames();

	
	@Meta.AD(name = "courseTypeIds", deflt = "", required = false)
	public String courseTypeIds();

	@Meta.AD(name = "courseTypeUuids", deflt = "", required = false)
	public String courseTypeUuids();

	@Meta.AD(name = "courseTypeNames", deflt = "", required = false)
	public String courseTypeNames();

	
	@Meta.AD(name = "publicIds", deflt = "", required = false)
	public String publicIds();

	@Meta.AD(name = "publicUuids", deflt = "", required = false)
	public String publicUuids();

	@Meta.AD(name = "publicNames", deflt = "", required = false)
	public String publicNames();


	@Meta.AD(name = "territoryIds", deflt = "", required = false)
	public String territoryIds();

	@Meta.AD(name = "territoryUuids", deflt = "", required = false)
	public String territoryUuids();

	@Meta.AD(name = "territoryNames", deflt = "", required = false)
	public String territoryNames();


	@Meta.AD(name = "textXML", deflt = "", required = false)
	public String textXML();

}
