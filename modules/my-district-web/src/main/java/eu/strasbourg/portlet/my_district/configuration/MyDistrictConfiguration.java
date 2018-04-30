package eu.strasbourg.portlet.my_district.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.my_district.configuration.MyDistrictConfiguration", 
localization = "content/Language", name = "portlet.my_district.configuration.name")
public interface MyDistrictConfiguration {

	@Meta.AD(name = "noAddressXML", deflt = "", required = false)
	public String noAddressXML();

	@Meta.AD(name = "townHallXML", deflt = "", required = false)
	public String townHallXML();

	@Meta.AD(name = "territoryDirectionXML", deflt = "", required = false)
	public String territoryDirectionXML();

}
