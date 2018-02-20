package eu.strasbourg.portlet.map.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.service.InterestLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

@Component(
		configurationPid = "eu.strasbourg.portlet.map.configuration.MapConfiguration",
		configurationPolicy = ConfigurationPolicy.OPTIONAL,
		immediate = true,	
		property = {
				"javax.portlet.name=" + StrasbourgPortletKeys.MAP_WEB },
		service = ConfigurationAction.class)
public class MapConfigurationAction extends DefaultConfigurationAction{

	/**
	 * Action : Sauvegarde de la configuration si on a validé le formulaire ou
	 * envoi de la JSP des sélecteurs si on a changé la liste déroulante des
	 * types d'entité
	 */
	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest request, ActionResponse response) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) request
			.getAttribute(WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(request, "cmd");

		if (cmd.equals("update")) {
			
			setPreference(request, "hasConfig", "true");
			
			//Choix du site vers lequel les liens redirigent
			String groupId = ParamUtil.getString(request, "groupId");
			setPreference(request, "groupId", groupId);
			
			// Choix "nouvel onglet, onglet courant"
			String openInNewTab = ParamUtil.getString(request, "openInNewTab");
			setPreference(request, "openInNewTab", openInNewTab);
			
			// Centres d'intérêts actifs
			String interestsIdsString = "";
			long interestsCount = ParamUtil.getLong(request, "interestsCount");
			for (long i = 0; i < interestsCount; i++) {
				String interestIdString = ParamUtil.getString(request, "interestId_" + i);
				boolean interestSelected = Validator.isNotNull(
					interestIdString) && !interestIdString.equals("false");
				if (interestSelected) {
					if (interestsIdsString.length() > 0) {
						interestsIdsString += ",";
					}
					interestsIdsString += interestIdString;
				}
			}
			setPreference(request, "interestsIds", interestsIdsString);
			
			// Choix afficher les favoris
			String showFavorites = ParamUtil.getString(request, "showFavorites");
			setPreference(request, "showFavorites", showFavorites);
			
			// Centres d'intérêts par défaut
			String interestsDefaultsIdsString = "";
			long interestsDefaultCount = ParamUtil.getLong(request, "interestsDefaultCount");
			for (long i = 0; i < interestsDefaultCount; i++) {
				String interestIdString = ParamUtil.getString(request, "interestDefaultId_" + i);
				boolean interestSelected = Validator.isNotNull(
					interestIdString) && !interestIdString.equals("false");
				if (interestSelected) {
					if (interestsDefaultsIdsString.length() > 0) {
						interestsDefaultsIdsString += ",";
					}
					interestsDefaultsIdsString += interestIdString;
				}
			}
			setPreference(request, "interestsDefaultsIds", interestsDefaultsIdsString);
			
			// Config par défaut
			String defaultConfig = ParamUtil.getString(request, "defaultConfig");
			setPreference(request, "defaultConfig", defaultConfig);
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

			//Récupération de la configuration
			MapConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
						MapConfiguration.class);
			
			//Ce flag permet de savoir si une configuration du portlet a déjà été enregistrée
			//Utile pour cocher les centres d'intéret par défaut
			request.setAttribute("hasConfig", configuration.hasConfig());
			
			//Choix du site vers lequel les liens redirigent
			List<Group> sites = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(), 0, true);
			request.setAttribute("sites", sites);
			request.setAttribute("selectedGroupId", configuration.groupId());
			
			// Choix "nouvel onglet, onglet courant"
			request.setAttribute("openInNewTab", configuration.openInNewTab());
			
			
			//Centre d'intérêts
			List<Interest> interests = InterestLocalServiceUtil.getInterests(-1, -1).stream()
					.filter(i -> i.getStatus() == 0)
					.collect(Collectors.toList());
			request.setAttribute("interests", interests);
			
			
			// Centre d'intérêts actifs
			long[] interestsIds = ParamUtil.getLongValues(request, "interestsIds");
			String interestsIdsString;
			if (interestsIds.length > 0) {
				interestsIdsString = StringUtil.merge(interestsIds);
			} else {
				interestsIdsString = configuration.interestsIds();
			}
			request.setAttribute("interestsIds", interestsIdsString);
			
			// Choix afficher les favoris
			request.setAttribute("showFavorites", configuration.showFavorites());
			
			// Centre d'intérêts sélectionnés
			long[] interestsDefaultsIds = ParamUtil.getLongValues(request, "interestsDefaultsIds");
			String interestsDefaultsIdsString;
			if (interestsDefaultsIds.length > 0) {
				interestsDefaultsIdsString = StringUtil.merge(interestsDefaultsIds);
			} else {
				interestsDefaultsIdsString = configuration.interestsDefaultsIds();
			}
			request.setAttribute("interestsDefaultsIds", interestsDefaultsIdsString);
			
			// Config par défaut
			request.setAttribute("defaultConfig", configuration.defaultConfig());
			
		} catch (ConfigurationException e) {
			_log.error(e);
		}
		super.include(portletConfig, request, response);
	}
	

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/map-configuration.jsp";
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
