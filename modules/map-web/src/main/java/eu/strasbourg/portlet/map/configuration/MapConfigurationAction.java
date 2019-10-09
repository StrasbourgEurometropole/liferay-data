package eu.strasbourg.portlet.map.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
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
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
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
	 * Action : Sauvegarde de la configuration si on a validé le formulaire ou envoi
	 * de la JSP des sélecteurs si on a changé la liste déroulante des types
	 * d'entité
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
			JSONArray jsonArrayUncheckedInterests = JSONFactoryUtil.createJSONArray();

			setPreference(request, "hasConfig", "true");

			// mode
			String mode = ParamUtil.getString(request, "mode");
			setPreference(request, "mode", mode);
			System.out.println("MODE : " + mode);

			// Widget mod
			setPreference(request, "widgetMod", String.valueOf(mode.equals("widget")));

			// Config par défaut
			setPreference(request, "defaultConfig", String.valueOf(mode.equals("aroundme")));

			// mode mon quartier
			setPreference(request, "districtMod", String.valueOf(mode.equals("district")));

			// Choix du site vers lequel les liens redirigent
			String groupId = ParamUtil.getString(request, "groupId");
			setPreference(request, "groupId", groupId);

			// Choix "nouvel onglet, onglet courant"
			String openInNewTab = ParamUtil.getString(request, "openInNewTab");
			setPreference(request, "openInNewTab", openInNewTab);

			// Zoom
			String zoom = ParamUtil.getString(request, "zoom");
			setPreference(request, "zoom", zoom);

			// cadrage
			String cadrageX = ParamUtil.getString(request, "cadrageX");
			setPreference(request, "cadrageX", cadrageX);
			String cadrageY = ParamUtil.getString(request, "cadrageY");
			setPreference(request, "cadrageY", cadrageY);

			// Affichage des pictos dans la config
			String showPictos = ParamUtil.getString(request, "showPictos");
			setPreference(request, "showPictos", showPictos);
			
			if(mode.equals("widget")) {
				// Texte introduction en mode widget
				String widgetIntro = ParamUtil.getString(request, "widgetIntro");
				setPreference(request, "widgetIntro", widgetIntro);

				// URL lien bouton mode widget
				String widgetLink = ParamUtil.getString(request, "widgetLink");
				setPreference(request, "widgetLink", widgetLink);

				// Pas utilisé en mode widget
				setPreference(request, "typesContenu", "");
				setPreference(request, "eventExplanationXML", "");
				setPreference(request, "showConfig", "");
				setPreference(request, "showList", "");
				setPreference(request, "showTraffic", "");
				setPreference(request, "linkCategoryId", "");
				setPreference(request, "categoryTitle", "");
				setPreference(request, "linkInterestId", "");
				setPreference(request, "showTransports", "");
				setPreference(request, "tranportsLinkCategoryId", "");
				setPreference(request, "tranportsCategoryTitle", "");
				setPreference(request, "transportsLinkInterestId", "");
			}else {
				setPreference(request, "widgetIntro", "");
				setPreference(request, "widgetLink", "");

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
						jsonArrayTypeContenu.put(typeContenuString);
					}
				}
				setPreference(request, "typesContenu", typesContenuString);
				json.put("typesContenu", jsonArrayTypeContenu);
				
				// texte explicatif sur les évènements
				Map<Locale, String> eventExplanationMap = LocalizationUtil
					.getLocalizationMap(request, "eventExplanationMap");
				LocalizedValuesMap mapEventExplanation = new LocalizedValuesMap();
				for (Map.Entry<Locale, String> e : eventExplanationMap.entrySet()) {
					mapEventExplanation.put(e.getKey(), e.getValue());
				}
				String eventExplanationXML = LocalizationUtil.getXml(mapEventExplanation, "eventExplanation");
				setPreference(request, "eventExplanationXML", eventExplanationXML);
				
				// Choix afficher la zone de config
				String showConfig = ParamUtil.getString(request, "showConfig");
				setPreference(request, "showConfig", showConfig);

				// Choix afficher la liste à droite
				String showList = ParamUtil.getString(request, "showList");
				setPreference(request, "showList", showList);
				
				// Choix afficher l'info trafic
				String showTraffic = ParamUtil.getString(request, "showTraffic");
				setPreference(request, "showTraffic", showTraffic);
				if(Boolean.parseBoolean(showTraffic)){
					if(mode.equals("normal") || mode.equals("district")) {
						// Liaison de l'info trafic à une catégorie
						String linkCategoryId = ParamUtil.getString(request, "linkCategoryId");
						setPreference(request, "linkCategoryId", linkCategoryId);
						// récupère le nom de la catégorie
						String categoryTitle = "";
						if (Validator.isNotNull(linkCategoryId)) {
							AssetCategory category = AssetCategoryLocalServiceUtil
									.fetchAssetCategory(Long.parseLong(linkCategoryId));
							if (Validator.isNotNull(category)) {
								categoryTitle = category.getTitle(Locale.FRANCE);
							}
						}
						setPreference(request, "categoryTitle", categoryTitle);
					}else {
						// Liaison de l'info trafic à un CI
						String linkInterestId = ParamUtil.getString(request, "linkInterestId");
						setPreference(request, "linkInterestId", linkInterestId);
						json.put("trafficInterestId", linkInterestId);
					}
				}else{
					setPreference(request, "linkCategoryId", "");
					setPreference(request, "categoryTitle", "");
					setPreference(request, "linkInterestId", "");
					json.put("trafficInterestId", "");
				}
				
				// Choix afficher les transports
				String showTransports = ParamUtil.getString(request, "showTransports");
				setPreference(request, "showTransports", showTransports);
				if(Boolean.parseBoolean(showTransports)){
					if(mode.equals("normal")) {
						// Liaison des transports à une catégorie
						String transportsLinkCategoryId = ParamUtil.getString(request, "transportsLinkCategoryId");
						setPreference(request, "transportsLinkCategoryId", transportsLinkCategoryId);
						// Recuperer le nom de la categorie
						String transportsLinkCategoryTitle = "";
						if (Validator.isNotNull(transportsLinkCategoryId)) {
							AssetCategory category = AssetCategoryLocalServiceUtil
									.fetchAssetCategory(Long.parseLong(transportsLinkCategoryId));
							if (Validator.isNotNull(category)) {
								transportsLinkCategoryTitle = category.getTitle(Locale.FRANCE);
							}
						}
						setPreference(request, "transportsLinkCategoryTitle", transportsLinkCategoryTitle);
					}else {
						// Liaison des transports à un CI
						String transportsLinkInterestId = ParamUtil.getString(request, "transportsLinkInterestId");
						setPreference(request, "transportsLinkInterestId", transportsLinkInterestId);
						json.put("transportsLinkInterestId", transportsLinkInterestId);
					}
				}else{
					setPreference(request, "transportsLinkCategoryId", "");
					setPreference(request, "transportsLinkCategoryTitle", "");
					setPreference(request, "transportsLinkInterestId", "");
					json.put("transportsLinkInterestId", "");
				}
			}

			if(mode.equals("normal") || mode.equals("district")) {
				// Préfiltre catégories
				String prefilterCategoriesIds = ParamUtil.getString(request, "prefilterCategoriesIds");
				// On enregistre les ids des catégories sous forme de String
				// On sépare les catégories par des virgules
				List<Long> vocabulariesIds = new ArrayList<Long>();
				for (String categoryIdStr : prefilterCategoriesIds.split(",")) {
					Long categoryId = GetterUtil.getLong(categoryIdStr);
					if (categoryId > 0) {
						AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
						if (category != null && !vocabulariesIds.contains(category.getVocabularyId())) {
							vocabulariesIds.add(category.getVocabularyId());
						}
					}
				}
				String sortedPrefilterCategoriesIds = "";
				for (Long vocabularyId : vocabulariesIds) {
					for (String categoryIdStr : prefilterCategoriesIds.split(",")) {
						Long categoryId = GetterUtil.getLong(categoryIdStr);
						if (categoryId > 0) {
							AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
							if (category != null && vocabularyId == category.getVocabularyId()) {
								if (sortedPrefilterCategoriesIds.length() > 0) {
									sortedPrefilterCategoriesIds += ",";
								}
								sortedPrefilterCategoriesIds += categoryId;
								jsonArrayPrefilter.put(Long.parseLong(categoryId.toString()));
							}
						}
					}
				}
				setPreference(request, "prefilterCategoriesIds", sortedPrefilterCategoriesIds);

				// Filtre catégories
				String categoriesIds = ParamUtil.getString(request, "categoriesIds");
				// On enregistre les ids des catégories sous forme de String
				// On sépare les catégories par des virgules
				vocabulariesIds = new ArrayList<Long>();
				for (String categoryIdStr : categoriesIds.split(",")) {
					Long categoryId = GetterUtil.getLong(categoryIdStr);
					if (categoryId > 0) {
						AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
						if (category != null && !vocabulariesIds.contains(category.getVocabularyId())) {
							vocabulariesIds.add(category.getVocabularyId());
						}
					}
				}
				String sortedCategoriesIds = "";
				for (Long vocabularyId : vocabulariesIds) {
					for (String categoryIdStr : categoriesIds.split(",")) {
						Long categoryId = GetterUtil.getLong(categoryIdStr);
						if (categoryId > 0) {
							AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
							if (category != null && vocabularyId == category.getVocabularyId()) {
								if (sortedCategoriesIds.length() > 0) {
									sortedCategoriesIds += ",";
								}
								sortedCategoriesIds += categoryId;
								jsonArrayFilter.put(Long.parseLong(categoryId.toString()));
							}
						}
					}
				}
				setPreference(request, "categoriesIds", sortedCategoriesIds);

				// Filtre catégories par défaut
				String categoriesDefaultsIds = ParamUtil.getString(request, "categoriesDefaultsIds");
				// On enregistre les ids des catégories sous forme de String
				// On sépare les catégories par des virgules
				vocabulariesIds = new ArrayList<Long>();
				for (String categoryIdStr : categoriesDefaultsIds.split(",")) {
					Long categoryId = GetterUtil.getLong(categoryIdStr);
					if (categoryId > 0) {
						AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
						if (category != null && !vocabulariesIds.contains(category.getVocabularyId())) {
							vocabulariesIds.add(category.getVocabularyId());
						}
					}
				}
				String sortedCategoriesDefaultsIds = "";
				for (Long vocabularyId : vocabulariesIds) {
					for (String categoryIdStr : categoriesDefaultsIds.split(",")) {
						Long categoryId = GetterUtil.getLong(categoryIdStr);
						if (categoryId > 0) {
							AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
							if (category != null && vocabularyId == category.getVocabularyId()) {
								if (sortedCategoriesDefaultsIds.length() > 0) {
									sortedCategoriesDefaultsIds += ",";
								}
								sortedCategoriesDefaultsIds += categoryId;
								jsonArrayDefault.put(Long.parseLong(categoryId.toString()));
							}
						}
					}
				}
				setPreference(request, "categoriesDefaultsIds", sortedCategoriesDefaultsIds);

				// Filtre sur le quartier de l'utilisateur
				if(mode.equals("district")) {
					String districtUser = ParamUtil.getString(request, "districtUser");
					setPreference(request, "districtUser", districtUser);
				}else{
					setPreference(request, "districtUser", "");
				}
			}else {
				setPreference(request, "prefilterCategoriesIds", "");
				setPreference(request, "categoriesIds", "");
				setPreference(request, "categoriesDefaultsIds", "");
				setPreference(request, "districtUser", "");
			}
			
			if(mode.equals("aroundme")) {
				// Centres d'intérêts affichés non cochés
				String interestsIdsString = "";

				List<Interest> interests = InterestLocalServiceUtil.getInterests(-1, -1).stream()
						.filter(i -> i.isApproved()).collect(Collectors.toList());
				for (Interest interest : interests) {
					String interestStatus = ParamUtil.getString(request, "interestStatus" + interest.getInterestId());
					if (interestStatus.equals("unchecked")) {
						if (interestsIdsString.length() > 0) {
							interestsIdsString += ",";
						}
						interestsIdsString += interest.getInterestId();
						jsonArrayUncheckedInterests.put(interest.getInterestId());
					}
				}
				setPreference(request, "interestsIds", interestsIdsString);
				json.put("interestsIds", jsonArrayUncheckedInterests);
				
				// Choix afficher les favoris
				String showFavorites = ParamUtil.getString(request, "showFavorites");
				setPreference(request, "showFavorites", showFavorites);
				json.put("showFavorites", showFavorites);

				// Si on est en mode autour de moi, on écrase (Si elle existe) la précédente
				// configuration globale
				ExpandoBridge ed = themeDisplay.getScopeGroup().getExpandoBridge();

				try {
					// String globalConfig =
					// GetterUtil.getString(ed.getAttribute("map_global_config"));
					ed.setAttribute("map_global_config", json.toJSONString());
				} catch (Exception ex) {
					_log.error("Missing expando field : map_global_config");
				}
			}else {
				setPreference(request, "interestsIds", "");
				setPreference(request, "showFavorites", "");
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

			// mode du portlet
			request.setAttribute("mode", configuration.mode());

			// Widget mod du portlet
			request.setAttribute("widgetMod", configuration.widgetMod());

			// Config par défaut
			request.setAttribute("defaultConfig", configuration.defaultConfig());

			// Config par défaut
			request.setAttribute("districtMod", configuration.districtMod());

			// Choix du site vers lequel les liens redirigent
			List<Group> sites = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(), 0, true);
			request.setAttribute("sites", sites);
			request.setAttribute("selectedGroupId", configuration.groupId());

			request.setAttribute("groupId", "-1");

			// Choix "nouvel onglet, onglet courant"
			request.setAttribute("openInNewTab", configuration.openInNewTab());

			// Zoom
			request.setAttribute("zoom", configuration.zoom());

			// Cadrage
			request.setAttribute("cadrageX", configuration.cadrageX());
			request.setAttribute("cadrageY", configuration.cadrageY());

			// Choix afficher picto dans la zone de config
			request.setAttribute("showPictos", configuration.showPictos());

			// Types de contenu
			String[] typesContenu = ParamUtil.getStringValues(request, "typesContenu");
			String typesContenuString;
			if (typesContenu.length > 0) {
				typesContenuString = StringUtil.merge(typesContenu);
			} else {
				typesContenuString = configuration.typesContenu();
			}
			request.setAttribute("typesContenu", typesContenuString);

			// texte explicatif sur les évènements
			request.setAttribute("eventExplanation", configuration.eventExplanationXML());

			// Cooredonnées d'une zone
			request.setAttribute("coordinateZone", JSONFactoryUtil.createJSONObject());

			// Choix afficher la zone de config
			request.setAttribute("showConfig", configuration.showConfig());

			// Choix afficher la liste à droite
			request.setAttribute("showList", configuration.showList());

			// Texte intro mode widget
			request.setAttribute("widgetIntro", configuration.widgetIntro());

			// URL bouton mode widget
			request.setAttribute("widgetLink", configuration.widgetLink());

			// Préfiltres catégories
			String prefilterCategoriesIds = configuration.prefilterCategoriesIds().replace(";", ",");
			request.setAttribute("prefilterCategoriesIds", prefilterCategoriesIds);

			// Filtres
			String categoriesIds = configuration.categoriesIds().replace(";", ",");
			request.setAttribute("categoriesIds", categoriesIds);
			String categoriesDefaultsIds = configuration.categoriesDefaultsIds().replace(";", ",");
			request.setAttribute("categoriesDefaultsIds", categoriesDefaultsIds);

			// Préfiltre sur le quartier utilisateur
			request.setAttribute("districtUser", configuration.districtUser());

			// Centre d'intérêts
			List<Interest> interests = InterestLocalServiceUtil.getInterests(-1, -1).stream()
					.filter(i -> i.getStatus() == 0).collect(Collectors.toList());
			request.setAttribute("interests", interests);
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

			// Choix afficher l'info traffic
			request.setAttribute("showTraffic", configuration.showTraffic());

			// liaison de la catégorie à l'info trafic
			// Vocabulaires
			List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil.getAssetVocabularies(-1, -1);
			vocabularies = vocabularies.stream().filter(v -> v.getGroupId() == themeDisplay.getCompanyGroupId())
					.collect(Collectors.toList());
			String vocabulariesStr = "";
			for (AssetVocabulary assetVocabulary : vocabularies) {
				if(!vocabulariesStr.equals("")) {
					vocabulariesStr += ",";
				}
				vocabulariesStr += assetVocabulary.getVocabularyId();
			}
			request.setAttribute("vocabularies", vocabulariesStr);
			request.setAttribute("linkCategoryId", configuration.linkCategoryId());
			request.setAttribute("categoryTitle", configuration.categoryTitle());

			// Liaison de l'info trafic à un CI
			request.setAttribute("linkInterestId", configuration.linkInterestId());
			
			// Choix afficher les transports
			request.setAttribute("showTransports", configuration.showTransports());

			// Liaison de la catégorie aux transports
			List<AssetVocabulary> transportsVocabularies = AssetVocabularyLocalServiceUtil.getAssetVocabularies(-1, -1);
			vocabularies = vocabularies.stream().filter(v -> v.getGroupId() == themeDisplay.getCompanyGroupId())
					.collect(Collectors.toList());
			String transportsVocabulariesStr = "";
			for (AssetVocabulary assetVocabulary : transportsVocabularies) {
				if(!transportsVocabulariesStr.equals("")) {
					transportsVocabulariesStr += ",";
				}
				transportsVocabulariesStr += assetVocabulary.getVocabularyId();
			}
			request.setAttribute("transportsVocabulariesStr", transportsVocabulariesStr);
			request.setAttribute("transportsLinkCategoryId", configuration.transportsLinkCategoryId());
			request.setAttribute("transportsLinkCategoryTitle", configuration.transportsLinkCategoryTitle());

			// Liaison des transports à un CI
			request.setAttribute("transportsLinkInterestId", configuration.transportsLinkInterestId());
			
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
