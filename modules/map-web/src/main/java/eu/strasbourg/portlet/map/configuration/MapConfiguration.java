package eu.strasbourg.portlet.map.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.map.configuration.MapConfiguration", localization = "content/Language", name = "portlet.map.configuration.name")
public interface MapConfiguration {
	
	//Est à faux tant qu'aucune configuration n'a été enregistrée
	@Meta.AD(name = "hasConfig", required = false)
	public boolean hasConfig();
	
	@Meta.AD(name = "mode", required = false)
	public String mode();
	
	@Meta.AD(name = "widgetMod", required = false)
	public boolean widgetMod();
	
	@Meta.AD(name = "defaultConfig", required = false)
	public boolean defaultConfig();
	
	@Meta.AD(name = "groupId", required = false)
	public long groupId();
	
	@Meta.AD(name = "openInNewTab", required = false)
	public boolean openInNewTab();

	@Meta.AD(name = "zoom", deflt = "", required = false)
	public String zoom();

	@Meta.AD(name = "cadrageX", deflt = "", required = false)
	public String cadrageX();

	@Meta.AD(name = "cadrageY", deflt = "", required = false)
	public String cadrageY();

	@Meta.AD(name = "showPictos", required = false)
	public boolean showPictos();
	
	@Meta.AD(name = "typesContenu", required = false)
	public String typesContenu();

	@Meta.AD(name = "eventExplanationXML", deflt = "", required = false)
	public String eventExplanationXML();

	@Meta.AD(name = "showConfig", required = false)
	public boolean showConfig();
	
	@Meta.AD(name = "showList", required = false)
	public boolean showList();
	
	@Meta.AD(name = "widgetIntro", required = false)
	public String widgetIntro();

	@Meta.AD(name = "widgetLink", required = false)
	public String widgetLink();
	
	@Meta.AD(name = "prefilterCategoriesIds", required = false)
	public String prefilterCategoriesIds();
	
	@Meta.AD(name = "categoriesIds", required = false)
	public String categoriesIds();
	
	@Meta.AD(name = "categoriesDefaultsIds", required = false)
	public String categoriesDefaultsIds();
	
	@Meta.AD(name = "districtUser", required = false)
	public boolean districtUser();
	
	@Meta.AD(name = "interestsIds", required = false)
	public String interestsIds();
	
	@Meta.AD(name = "showFavorites", required = false)
	public boolean showFavorites();
	
	@Meta.AD(name = "showTraffic", required = false)
	public boolean showTraffic();
	
	@Meta.AD(name = "linkCategoryId", required = false)
	public String linkCategoryId();

	@Meta.AD(name = "categoryTitle", deflt = "", required = false)
	public String categoryTitle();

	@Meta.AD(name = "linkInterestId", deflt = "", required = false)
	public String linkInterestId();
	
	@Meta.AD(name = "showTransports", required = false)
	public boolean showTransports();
	
	@Meta.AD(name = "tranportsLinkCategoryId", required = false)
	public String tranportsLinkCategoryId();

	@Meta.AD(name = "tranportsCategoryTitle", deflt = "", required = false)
	public String tranportsCategoryTitle();

	@Meta.AD(name = "transportLinkInterestId", deflt = "", required = false)
	public String transportLinkInterestId();

}
