package eu.strasbourg.portlet.page_header.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
	category = "Strasbourg",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(
	id = "eu.strasbourg.portlet.page_header.configuration.PageHeaderConfiguration",
	localization = "content/Language",
	name = "portlet.page_header.configuration.name")
public interface PageHeaderConfiguration {

	@Meta.AD(name="displayShareButtons", required= false)
	public boolean displayShareButtons(); 

	@Meta.AD(name="displayImage", required= false)
	public boolean displayImage(); 
	
	@Meta.AD(name="alternativeTheme", required= false)
	public boolean alternativeTheme(); 
	
	@Meta.AD(name="imageCredit", deflt = "", required = false)
	public String imageCredit();

}
