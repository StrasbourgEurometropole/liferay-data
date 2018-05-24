package eu.strasbourg.portlet.my_district.configuration;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
	configurationPid = "eu.strasbourg.portlet.my_district.configuration.MyDistrictConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.MY_DISTRICT_WEB },
	service = ConfigurationAction.class)
public class MyDistrictConfigurationAction
	extends DefaultConfigurationAction {

	/**
	 * Action : Sauvegarde de la configuration si on a validé le formulaire ou
	 * envoi de la JSP des sélecteurs si on a changé la liste déroulante des
	 * types d'entité
	 */
	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest request, ActionResponse response) throws Exception {


		String cmd = ParamUtil.getString(request, "cmd");

		if (cmd.equals("update")) {
			
			// No address text
			Map<Locale, String> noAddressMap = LocalizationUtil
				.getLocalizationMap(request, "noAddressMap");
			LocalizedValuesMap mapNoAddress = new LocalizedValuesMap();
			for (Map.Entry<Locale, String> e : noAddressMap.entrySet()) {
				mapNoAddress.put(e.getKey(), e.getValue());
			}
			String noAddressXML = LocalizationUtil.getXml(mapNoAddress, "noAddress");
			setPreference(request, "noAddressXML", noAddressXML);
			
			// Town hall text
			Map<Locale, String> townHallMap = LocalizationUtil
				.getLocalizationMap(request, "townHallMap");
			LocalizedValuesMap mapTownHall = new LocalizedValuesMap();
			for (Map.Entry<Locale, String> e : townHallMap.entrySet()) {
				mapTownHall.put(e.getKey(), e.getValue());
			}
			String townHallXML = LocalizationUtil.getXml(mapTownHall, "townHall");
			setPreference(request, "townHallXML", townHallXML);
			
			// Territory direction text
			Map<Locale, String> territoryDirectionMap = LocalizationUtil
				.getLocalizationMap(request, "territoryDirectionMap");
			LocalizedValuesMap mapTerritoryDirection = new LocalizedValuesMap();
			for (Map.Entry<Locale, String> e : territoryDirectionMap.entrySet()) {
				mapTerritoryDirection.put(e.getKey(), e.getValue());
			}
			String territoryDirectionXML = LocalizationUtil.getXml(mapTerritoryDirection, "territoryDirection");
			setPreference(request, "territoryDirectionXML", territoryDirectionXML);
		}
		super.processAction(portletConfig, request, response);
	}

	/**
	 * Envoie à la JSP de configuration des informations nécessaires
	 */
	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

			MyDistrictConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
						MyDistrictConfiguration.class);

			request.setAttribute("noAddress", configuration.noAddressXML());
			request.setAttribute("townHall", configuration.townHallXML());
			request.setAttribute("territoryDirection", configuration.territoryDirectionXML());			
		} catch (ConfigurationException e) {
			_log.error(e);
		}
		super.include(portletConfig, request, response);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
