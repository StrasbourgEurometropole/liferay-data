package eu.strasbourg.portlet.map.configuration;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "Strasbourg", scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE)
@Meta.OCD(id = "eu.strasbourg.portlet.map.configuration.MapConfiguration", localization = "content/Language", name = "portlet.map.configuration.name")
public interface MapConfiguration {
	
	@Meta.AD(name = "groupId", required = false)
	public long groupId();
	
	@Meta.AD(name = "openInNewTab", required = false)
	public boolean openInNewTab();

}
