package eu.strasbourg.portlet.map;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**	
 * @author romain.vergnais	
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Autour de moi",	
		"javax.portlet.init-param.template-path=/",	
		"javax.portlet.init-param.view-template=/map-view.jsp",
		"javax.portlet.init-param.config-template=/map-configuration.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.MAP_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},	
	service = Portlet.class
)
public class MapPortlet extends MVCPortlet {
}