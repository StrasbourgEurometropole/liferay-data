package eu.strasbourg.portlet.map.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.map.configuration.MapConfiguration", localization = "content/Language", name = "portlet.map.configuration.name")
public interface MapConfiguration {
	
	//Est à faux tant qu'aucune configuration n'a été enregistrée
	@Meta.AD(name = "hasConfig", required = false)
	public boolean hasConfig();
	
	@Meta.AD(name = "widgetMod", required = false)
	public boolean widgetMod();
	
	@Meta.AD(name = "widgetIntro", required = false)
	public String widgetIntro();

	@Meta.AD(name = "widgetLink", required = false)
	public String widgetLink();
	
	@Meta.AD(name = "groupId", required = false)
	public long groupId();
	
	@Meta.AD(name = "openInNewTab", required = false)
	public boolean openInNewTab();
	
	@Meta.AD(name = "typesContenu", required = false)
	public String typesContenu();

	@Meta.AD(name = "eventExplanationXML", deflt = "", required = false)
	public String eventExplanationXML();
	
	@Meta.AD(name = "prefilterCategoriesIds", required = false)
	public String prefilterCategoriesIds();
	
	@Meta.AD(name = "categoriesIds", required = false)
	public String categoriesIds();
	
	@Meta.AD(name = "categoriesDefaultsIds", required = false)
	public String categoriesDefaultsIds();
	
	@Meta.AD(name = "showFavorites", required = false)
	public boolean showFavorites();
	
	@Meta.AD(name = "showConfig", required = false)
	public boolean showConfig();
	
	@Meta.AD(name = "showList", required = false)
	public boolean showList();
	
	@Meta.AD(name = "defaultConfig", required = false)
	public boolean defaultConfig();
	
	@Meta.AD(name = "districtUser", required = false)
	public boolean districtUser();
	
	@Meta.AD(name = "interestsIds", required = false)
	public String interestsIds();

}
