package eu.strasbourg.portlet.userdisplay;

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
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Personnalisation utilisateur",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/user-display-view.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.USER_DISPLAY,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UserDisplayPortlet extends MVCPortlet {
}