package eu.strasbourg.portlet.zone.configuration;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.adict.AdictService;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	configurationPid = "eu.strasbourg.portlet.zone.configuration.ZoneConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = { "javax.portlet.name=" + StrasbourgPortletKeys.ZONE_WEB },
	service = ConfigurationAction.class)
public class ZoneConfigurationAction extends DefaultConfigurationAction {

	@Reference
	private AdictService adictService;
	
	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		// Text
		Map<Locale, String> textMap = LocalizationUtil
			.getLocalizationMap(actionRequest, "textMap");
		LocalizedValuesMap map = new LocalizedValuesMap();
		for (Map.Entry<Locale, String> e : textMap.entrySet()) {
			map.put(e.getKey(), e.getValue());
		}
		String textXML = LocalizationUtil.getXml(map, "text");
		setPreference(actionRequest, "textXML", textXML);
		
		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

			ZoneConfiguration configuration = themeDisplay
				.getPortletDisplay()
				.getPortletInstanceConfiguration(ZoneConfiguration.class);

			request.setAttribute("text", configuration.textXML());
		} catch (ConfigurationException e) {
			_log.error(e);
		}
		
		super.include(portletConfig, request, response);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
