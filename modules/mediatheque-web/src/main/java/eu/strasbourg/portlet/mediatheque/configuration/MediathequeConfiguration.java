package eu.strasbourg.portlet.mediatheque.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.mediatheque.configuration.MediathequeConfiguration", 
localization = "content/Language", name = "portlet.mediatheque.configuration.name")
public interface MediathequeConfiguration {

	@Meta.AD(name = "error", deflt = "", required = false)
	public String errorXML();

	@Meta.AD(name = "demarche", deflt = "", required = false)
	public String demarcheXML();
	
	@Meta.AD(name = "retourURL", deflt = "", required = false)
	public String retourURL();
	
	@Meta.AD(name = "contactURL", deflt = "", required = false)
	public String contactURL();
	
	@Meta.AD(name = "mediathequeURL", deflt = "", required = false)
	public String mediathequeURL();

}
