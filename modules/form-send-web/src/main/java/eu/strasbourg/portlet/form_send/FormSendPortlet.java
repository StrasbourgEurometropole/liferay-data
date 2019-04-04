package eu.strasbourg.portlet.form_send;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.form_send.configuration.FormSendConfiguration;
import eu.strasbourg.portlet.form_send.context.FormSendDisplayContext;
import org.osgi.service.component.annotations.Component;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import java.io.IOException;

/**
 * @author angelique.champougny
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true", "com.liferay.portlet.required-namespaced-parameters=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/form-send-view.jsp",
		"javax.portlet.init-param.config-template=/configuration/form-send-configuration.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.FORM_SEND_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class FormSendPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

        FormSendDisplayContext dc = new FormSendDisplayContext(request, response);

        request.setAttribute("dc", dc);
        super.render(request, response);

	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}