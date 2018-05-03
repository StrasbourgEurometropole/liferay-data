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
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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

@Component(configurationPid = "eu.strasbourg.portlet.map.configuration.MapConfiguration", configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true, property = {
		"javax.portlet.name=" + StrasbourgPortletKeys.MAP_WEB }, service = ConfigurationAction.class)
public class MapConfigurationAction extends DefaultConfigurationAction {

	/**
	 * Action : Sauvegarde de la configuration si on a validé le formulaire ou
	 * envoi de la JSP des sélecteurs si on a changé la liste déroulante des
	 * types d'entité
	 */
	@Override
	public void processAction(PortletConfig portletConfig, ActionRequest request, ActionResponse response)
			throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(request, "cmd");

		if (cmd.equals("update")) {

			// JSON initialisation (Pour la sauvegarde de la configuration
			// globale utilisée par le portlet en mode Widget)
			JSONObject json = JSONFactoryUtil.createJSONObject();
			JSONArray jsonArrayTypeContenu = JSONFactoryUtil.createJSONArray();
			JSONArray jsonArrayPrefilter = JSONFactoryUtil.createJSONArray();
			JSONArray jsonArrayFilter = JSONFactoryUtil.createJSONArray();
			JSONArray jsonArrayDefault = JSONFactoryUtil.createJSONArray();
			JSONArray jsonArray2 = JSONFactoryUtil.createJSONArray();
			JSONArray jsonArray3 = JSONFactoryUtil.createJSONArray();

			setPreference(request, "hasConfig", "true");

			// Widget mod
			String widgetMod = ParamUtil.getString(request, "widgetMod");
			setPreference(request, "widgetMod", widgetMod);
			json.put("widgetMod", widgetMod);

			// Texte introduction en mode widget
			String widgetIntro = ParamUtil.getString(request, "widgetIntro");
			setPreference(request, "widgetIntro", widgetIntro);

			// URL lien bouton mode widget
			String widgetLink = ParamUtil.getString(request, "widgetLink");
			setPreference(request, "widgetLink", widgetLink);

			// Choix du site vers lequel les liens redirigent
			String groupId = ParamUtil.getString(request, "groupId");
			setPreference(request, "groupId", groupId);
			json.put("groupId", groupId);

			// Choix "nouvel onglet, onglet courant"
			String openInNewTab = ParamUtil.getString(request, "openInNewTab");
			setPreference(request, "openInNewTab", openInNewTab);
			json.put("openInNewTab", openInNewTab);

			// Types de contenu (Type de POI)
			String typesContenuString = "";
			String[] typesContenu = ParamUtil.getStringValues(request, "typeContenu");
			for (int i = 0; i < typesContenu.length; i++) {
				String typeContenuString = typesContenu[i];
				boolean typeContenuSelected = Validator.isNotNull(typeContenuString)
						&& !typeContenuString.equals("false");
				if (typeContenuSelected) {
					if (typesContenuString.length() > 0) {
						typesContenuString += ",";
					}
					typesContenuString += typeContenuString;
					jsonArrayTypeContenu.put(typesContenuString);
				}
			}
			setPreference(request, "typesContenu", typesContenuString);
			json.put("typesContenu", jsonArrayTypeContenu);

			// Préfiltre catégories
			String prefilterCategoriesIds = ParamUtil.getString(request,
				"prefilterCategoriesIds");
			// On enregistre les ids des catégories sous forme de String
			// On sépare les catégories par des virgules
			List<Long> vocabulariesIds = new ArrayList<Long>();
			for (String categoryIdStr : prefilterCategoriesIds.split(",")) {
				Long categoryId = GetterUtil.getLong(categoryIdStr);
				if (categoryId > 0) {
					AssetCategory category = AssetCategoryLocalServiceUtil
						.fetchAssetCategory(categoryId);
					if (category != null && !vocabulariesIds
						.contains(category.getVocabularyId())) {
						vocabulariesIds.add(category.getVocabularyId());
					}
				}
			}
			String sortedPrefilterCategoriesIds = "";
			for (Long vocabularyId : vocabulariesIds) {
				for (String categoryIdStr : prefilterCategoriesIds.split(",")) {
					Long categoryId = GetterUtil.getLong(categoryIdStr);
					if (categoryId > 0) {
						AssetCategory category = AssetCategoryLocalServiceUtil
							.fetchAssetCategory(categoryId);
						if (category != null
							&& vocabularyId == category.getVocabularyId()) {
							if (sortedPrefilterCategoriesIds.length() > 0) {
								sortedPrefilterCategoriesIds += ",";
							}
							sortedPrefilterCategoriesIds += categoryId;
							jsonArrayPrefilter.put(Long.parseLong(categoryId.toString()));
						}
					}
				}
			}
			setPreference(request, "prefilterCategoriesIds",
				sortedPrefilterCategoriesIds);
			json.put("prefilterCategoriesIds", jsonArrayPrefilter);

			// Filtre catégories
			String categoriesIds = ParamUtil.getString(request,
				"categoriesIds");
			// On enregistre les ids des catégories sous forme de String
			// On sépare les catégories par des virgules
			vocabulariesIds = new ArrayList<Long>();
			for (String categoryIdStr : categoriesIds.split(",")) {
				Long categoryId = GetterUtil.getLong(categoryIdStr);
				if (categoryId > 0) {
					AssetCategory category = AssetCategoryLocalServiceUtil
						.fetchAssetCategory(categoryId);
					if (category != null && !vocabulariesIds
						.contains(category.getVocabularyId())) {
						vocabulariesIds.add(category.getVocabularyId());
					}
				}
			}
			String sortedCategoriesIds = "";
			for (Long vocabularyId : vocabulariesIds) {
				for (String categoryIdStr : categoriesIds.split(",")) {
					Long categoryId = GetterUtil.getLong(categoryIdStr);
					if (categoryId > 0) {
						AssetCategory category = AssetCategoryLocalServiceUtil
							.fetchAssetCategory(categoryId);
						if (category != null
							&& vocabularyId == category.getVocabularyId()) {
							if (sortedCategoriesIds.length() > 0) {
								sortedCategoriesIds += ",";
							}
							sortedCategoriesIds += categoryId;
							jsonArrayFilter.put(Long.parseLong(categoryId.toString()));
						}
					}
				}
			}
			setPreference(request, "categoriesIds",
				sortedCategoriesIds);
			json.put("categoriesIds", jsonArrayFilter);

			// Filtre catégories par défaut
			String categoriesDefaultsIds = ParamUtil.getString(request,
				"categoriesDefaultsIds");
			// On enregistre les ids des catégories sous forme de String
			// On sépare les catégories par des virgules
			vocabulariesIds = new ArrayList<Long>();
			for (String categoryIdStr : categoriesDefaultsIds.split(",")) {
				Long categoryId = GetterUtil.getLong(categoryIdStr);
				if (categoryId > 0) {
					AssetCategory category = AssetCategoryLocalServiceUtil
						.fetchAssetCategory(categoryId);
					if (category != null && !vocabulariesIds
						.contains(category.getVocabularyId())) {
						vocabulariesIds.add(category.getVocabularyId());
					}
				}
			}
			String sortedCategoriesDefaultsIds = "";
			for (Long vocabularyId : vocabulariesIds) {
				for (String categoryIdStr : categoriesDefaultsIds.split(",")) {
					Long categoryId = GetterUtil.getLong(categoryIdStr);
					if (categoryId > 0) {
						AssetCategory category = AssetCategoryLocalServiceUtil
							.fetchAssetCategory(categoryId);
						if (category != null
							&& vocabularyId == category.getVocabularyId()) {
							if (sortedCategoriesDefaultsIds.length() > 0) {
								sortedCategoriesDefaultsIds += ",";
							}
							sortedCategoriesDefaultsIds += categoryId;
							jsonArrayDefault.put(Long.parseLong(categoryId.toString()));
						}
					}
				}
			}
			setPreference(request, "categoriesDefaultsIds",
				sortedCategoriesDefaultsIds);
			json.put("categoriesDefaultsIds", jsonArrayDefault);

			// Choix afficher les favoris
			String showFavorites = ParamUtil.getString(request, "showFavorites");
			setPreference(request, "showFavorites", showFavorites);
			json.put("showFavorites", showFavorites);

			// Choix afficher la zone de config
			String showConfig = ParamUtil.getString(request, "showConfig");
			setPreference(request, "showConfig", showConfig);
			json.put("showConfig", showConfig);

			// Choix afficher la liste à droite
			String showList = ParamUtil.getString(request, "showList");
			setPreference(request, "showList", showList);
			json.put("showList", showList);

			// Config par défaut
			String defaultConfig = ParamUtil.getString(request, "defaultConfig");
			setPreference(request, "defaultConfig", defaultConfig);

			// Filtre sur le quartier de l'utilisateur
			String districtUser = ParamUtil.getString(request, "districtUser");
			setPreference(request, "districtUser", districtUser);
			json.put("districtUser", districtUser);

			// Centres d'intérêts affichés non cochés
			String interestsIdsString = "";
			// Centres d'intérêts affichés cochés
			String interestsDefaultsIdsString = "";
			long interestsCount = ParamUtil.getLong(request, "interestsCount");
			for (long i = 0; i < interestsCount; i++) {
				String interestIdString = ParamUtil.getString(request, "interestId_" + i);
				boolean interestSelected = Validator.isNotNull(interestIdString) && !interestIdString.equals("false");
				if (interestSelected) {
					if (interestsIdsString.length() > 0) {
						interestsIdsString += ",";
					}
					interestsIdsString += interestIdString;
					jsonArray2.put(Long.parseLong(interestIdString));
				}
				interestIdString = ParamUtil.getString(request, "interestDefaultId_" + i);
				interestSelected = Validator.isNotNull(interestIdString) && !interestIdString.equals("false");
				if (interestSelected) {
					if (interestsDefaultsIdsString.length() > 0) {
						interestsDefaultsIdsString += ",";
					}
					interestsDefaultsIdsString += interestIdString;
					jsonArray3.put(Long.parseLong(interestIdString));
				}
			}
			setPreference(request, "interestsIds", interestsIdsString);
			json.put("interestsIds", jsonArray2);
			setPreference(request, "interestsDefaultsIds", interestsDefaultsIdsString);
			json.put("interestsDefaultsIds", jsonArray3);

			// Si la case est cochée on écrase (Si elle existe) la précédente
			// configuration globale
			if (ParamUtil.getBoolean(request, "defaultConfig")) {
				ExpandoBridge ed = themeDisplay.getScopeGroup().getExpandoBridge();

				try {
					// String globalConfig =
					// GetterUtil.getString(ed.getAttribute("map_global_config"));
					ed.setAttribute("map_global_config", json.toJSONString());
				} catch (Exception ex) {
					_log.error("Missing expando field : map_global_config");
				}
			}
		}
		super.processAction(portletConfig, request, response);
	}

	/**
	 * Envoie à la JSP de configuration des informations nécessaires
	 */
	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			// Récupération de la configuration
			MapConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(MapConfiguration.class);

			// Ce flag permet de savoir si une configuration du portlet a déjà
			// été enregistrée
			// Utile pour cocher les centres d'intéret par défaut
			request.setAttribute("hasConfig", configuration.hasConfig());

			// Widget mod du portlet
			request.setAttribute("widgetMod", configuration.widgetMod());

			// Texte intro mode widget
			request.setAttribute("widgetIntro", configuration.widgetIntro());

			// URL bouton mode widget
			request.setAttribute("widgetLink", configuration.widgetLink());

			// Choix du site vers lequel les liens redirigent
			List<Group> sites = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(), 0, true);
			request.setAttribute("sites", sites);
			request.setAttribute("selectedGroupId", configuration.groupId());

			// Choix "nouvel onglet, onglet courant"
			request.setAttribute("openInNewTab", configuration.openInNewTab());

			// Types de contenu
			String[] typesContenu = ParamUtil.getStringValues(request, "typesContenu");
			String typesContenuString;
			if (typesContenu.length > 0) {
				typesContenuString = StringUtil.merge(typesContenu);
			} else {
				typesContenuString = configuration.typesContenu();
			}
			request.setAttribute("typesContenu", typesContenuString);
			
			// Préfiltres catégories
			String prefilterCategoriesIds = configuration.prefilterCategoriesIds().replace(";", ",");
			request.setAttribute("prefilterCategoriesIds", prefilterCategoriesIds);

			// Filtres
			String categoriesIds = configuration.categoriesIds().replace(";", ",");
			request.setAttribute("categoriesIds", categoriesIds);
			String categoriesDefaultsIds = configuration.categoriesDefaultsIds().replace(";", ",");
			request.setAttribute("categoriesDefaultsIds", categoriesDefaultsIds);

			// Choix afficher les favoris
			request.setAttribute("showFavorites", configuration.showFavorites());

			// Choix afficher la zone de config
			request.setAttribute("showConfig", configuration.showConfig());

			// Choix afficher la liste à droite
			request.setAttribute("showList", configuration.showList());

			// Config par défaut
			request.setAttribute("defaultConfig", configuration.defaultConfig());

			// Préfiltre sur le quartier utilisateur
			request.setAttribute("districtUser", configuration.districtUser());

			// Centre d'intérêts
			List<Interest> interests = InterestLocalServiceUtil.getInterests(-1, -1).stream()
					.filter(i -> i.getStatus() == 0).collect(Collectors.toList());
			request.setAttribute("interests", interests);

			// Centre d'intérêts affichés non coché
			long[] interestsIds = ParamUtil.getLongValues(request, "interestsIds");
			String interestsIdsString;
			if (interestsIds.length > 0) {
				interestsIdsString = StringUtil.merge(interestsIds);
			} else {
				interestsIdsString = configuration.interestsIds();
			}
			request.setAttribute("interestsIds", interestsIdsString);

			// Centre d'intérêts affichés cochés
			long[] interestsDefaultsIds = ParamUtil.getLongValues(request, "interestsDefaultsIds");
			String interestsDefaultsIdsString;
			if (interestsDefaultsIds.length > 0) {
				interestsDefaultsIdsString = StringUtil.merge(interestsDefaultsIds);
			} else {
				interestsDefaultsIdsString = configuration.interestsDefaultsIds();
			}
			request.setAttribute("interestsDefaultsIds", interestsDefaultsIdsString);

			request.setAttribute("groupId", "-1");

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
