package eu.strasbourg.portlet.interest_viewer.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.interest_viewer.configuration.InterrestViewerConfiguration", 
localization = "content/Language", name = "portlet.interest_viewer.configuration.name")
public interface InterestViewerConfiguration {

	@Meta.AD(name="template", deflt = "", required = false)
	public String template();

	@Meta.AD(name = "noInterestXML", deflt = "", required = false)
	public String noInterestXML();

	@Meta.AD(name = "eventNumberOnListPage", required = false)
	public int eventNumberOnListPage();

	@Meta.AD(name = "newsNumberOnListPage", required = false)
	public int newsNumberOnListPage();
	
	@Meta.AD(name="allNewsURL", deflt = "", required = false)
	public String allNewsURL();

	@Meta.AD(name="allEventsURL", deflt = "", required = false)
	public String allEventsURL();

}
