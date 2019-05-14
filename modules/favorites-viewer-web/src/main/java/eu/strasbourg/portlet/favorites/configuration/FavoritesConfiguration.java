package eu.strasbourg.portlet.favorites.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.favorites.configuration.FavoriteConfiguration", localization = "content/Language", name = "portlet.favorites.configuration.name")
public interface FavoritesConfiguration {

	@Meta.AD(name="showAllURL", deflt = "", required = false)
	public String showAllURL();
	
	@Meta.AD(name="template", deflt = "default", required = false)
	public String template();

	@Meta.AD(name = "noFavoritesXML", deflt = "", required = false)
	public String noFavoritesXML();

	@Meta.AD(name="texteXML", deflt = "", required = false)
	public String texteXML();
	
}
