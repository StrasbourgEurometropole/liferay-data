package eu.strasbourg.portlet.map.configuration;

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
import eu.strasbourg.service.interest.model.InterestModel;
import eu.strasbourg.service.interest.service.InterestLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

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

			// Types de contenu (Type de POI)
			boolean hasEventPOIs = false;
			if(mode.equals("widget")) {
				// Pas utilisé en mode widget
				setPreference(request, "typesContenu", "");
			}else {
				String[] typesContenu = ParamUtil.getStringValues(request, "typeContenu");
				StringBuilder typesContenuString = new StringBuilder();
				for (String typeContenuString : typesContenu) {
					boolean typeContenuSelected = Validator.isNotNull(typeContenuString)
							&& !typeContenuString.equals("false");
					if (typeContenuSelected) {
						if (typesContenuString.length() > 0) {
							typesContenuString.append(",");
						}
						typesContenuString.append(typeContenuString);
						jsonArrayTypeContenu.put(typeContenuString);
					}
				}
				setPreference(request, "typesContenu", typesContenuString.toString());
				json.put("typesContenu", jsonArrayTypeContenu);
				hasEventPOIs = typesContenuString.indexOf("eu.strasbourg.service.agenda.model.Event") > 0;
			}

			// texte explicatif sur les évènements
			if(mode.equals("widget")) {
				// Pas utilisé en mode widget
				setPreference(request, "eventExplanationXML", "");
			}else {
				if(hasEventPOIs) {
					Map<Locale, String> eventExplanationMap = LocalizationUtil
							.getLocalizationMap(request, "eventExplanationMap");
					LocalizedValuesMap mapEventExplanation = new LocalizedValuesMap();
					for (Map.Entry<Locale, String> e : eventExplanationMap.entrySet()) {
						mapEventExplanation.put(e.getKey(), e.getValue());
					}
					String eventExplanationXML = LocalizationUtil.getXml(mapEventExplanation, "eventExplanation");
					setPreference(request, "eventExplanationXML", eventExplanationXML);
				}else{
					setPreference(request, "eventExplanationXML", "");
				}
			}

			// Choix afficher la zone de config
			boolean displayConfig = false;
			if(mode.equals("widget")) {
				// Pas utilisé en mode widget
				setPreference(request, "showConfig", "");
			}else {
				String showConfig = ParamUtil.getString(request, "showConfig");
				setPreference(request, "showConfig", showConfig);
				displayConfig = Boolean.parseBoolean(showConfig);
			}

			// Affichage des pictos dans la config
			if(mode.equals("widget")) {
				// Pas utilisé en mode widget
				setPreference(request, "showPictos", "");
			}else {
				if(displayConfig) {
					String showPictos = ParamUtil.getString(request, "showPictos");
					setPreference(request, "showPictos", showPictos);
				}else{
					setPreference(request, "showPictos", "");
				}
			}

			// Choix afficher la liste à droite
			if(mode.equals("widget")) {
				// Pas utilisé en mode widget
				setPreference(request, "showList", "");
			}else {
				String showList = ParamUtil.getString(request, "showList");
				setPreference(request, "showList", showList);
			}

			// Détourage d'un quartier ou d'une commune
			boolean hasClipping = false;
			if(mode.equals("widget")) {
				// Pas utilisé en mode widget
				setPreference(request, "clippingTerritory", "");
			}else{
				String clippingTerritory = ParamUtil.getString(request, "clippingTerritory");
				setPreference(request, "clippingTerritory", clippingTerritory);
				hasClipping = Boolean.parseBoolean(clippingTerritory);
			}

			// Zone de détourage
			if(mode.equals("widget")) {
				// Pas utilisé en mode widget
				setPreference(request, "clippingCategoryId", "");
			}else {
				String clippingCategoryId = "";
				if (hasClipping) {
					clippingCategoryId = ParamUtil.getString(request, "clippingCategoryId");
					setPreference(request, "clippingCategoryId", clippingCategoryId);
				} else {
					setPreference(request, "clippingCategoryId", "");
				}
				json.put("clippingCategoryId", clippingCategoryId);
			}

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

			// Préfiltre catégories
			if(mode.equals("normal") || mode.equals("district")) {
				String prefilterCategoriesIds = ParamUtil.getString(request, "prefilterCategoriesIds");
				// On enregistre les ids des catégories sous forme de String
				// On sépare les catégories par des virgules
				List<Long> vocabulariesIds = new ArrayList<>();
				for (String categoryIdStr : prefilterCategoriesIds.split(",")) {
					long categoryId = GetterUtil.getLong(categoryIdStr);
					if (categoryId > 0) {
						AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
						if (category != null && !vocabulariesIds.contains(category.getVocabularyId())) {
							vocabulariesIds.add(category.getVocabularyId());
						}
					}
				}
				StringBuilder sortedPrefilterCategoriesIds = new StringBuilder();
				for (Long vocabularyId : vocabulariesIds) {
					for (String categoryIdStr : prefilterCategoriesIds.split(",")) {
						long categoryId = GetterUtil.getLong(categoryIdStr);
						if (categoryId > 0) {
							AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
							if (category != null && vocabularyId == category.getVocabularyId()) {
								if (sortedPrefilterCategoriesIds.length() > 0) {
									sortedPrefilterCategoriesIds.append(",");
								}
								sortedPrefilterCategoriesIds.append(categoryId);
							}
						}
					}
				}
				setPreference(request, "prefilterCategoriesIds", sortedPrefilterCategoriesIds.toString());
			}else {
				// Pas utilisé dans les autres modes
				setPreference(request, "prefilterCategoriesIds", "");
			}

			// Choix d'affichage des filtres
			boolean displayList = false;
			if(mode.equals("normal") || mode.equals("district")) {
				String listDisplay = ParamUtil.getString(request, "listDisplay");
				setPreference(request, "listDisplay", listDisplay);
				displayList = Boolean.parseBoolean(listDisplay);
			}else {
				// Pas utilisé dans les autres modes
				setPreference(request, "listDisplay", "");
			}

			// Filtre catégories cochées par défaut
			if(mode.equals("normal") || mode.equals("district")) {
				if(displayList) {
					// Pas utilisé dans l'affichage des filtres en liste
					setPreference(request, "categoriesDefaultsIds", "");
				}else{
					String categoriesDefaultsIds = ParamUtil.getString(request, "categoriesDefaultsIds");
					// On enregistre les ids des catégories sous forme de String
					// On sépare les catégories par des virgules
					List<Long> vocabulariesIds = new ArrayList<>();
					for (String categoryIdStr : categoriesDefaultsIds.split(",")) {
						long categoryId = GetterUtil.getLong(categoryIdStr);
						if (categoryId > 0) {
							AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
							if (category != null && !vocabulariesIds.contains(category.getVocabularyId())) {
								vocabulariesIds.add(category.getVocabularyId());
							}
						}
					}
					StringBuilder sortedCategoriesDefaultsIds = new StringBuilder();
					for (Long vocabularyId : vocabulariesIds) {
						for (String categoryIdStr : categoriesDefaultsIds.split(",")) {
							long categoryId = GetterUtil.getLong(categoryIdStr);
							if (categoryId > 0) {
								AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
								if (category != null && vocabularyId == category.getVocabularyId()) {
									if (sortedCategoriesDefaultsIds.length() > 0) {
										sortedCategoriesDefaultsIds.append(",");
									}
									sortedCategoriesDefaultsIds.append(categoryId);
								}
							}
						}
					}
					setPreference(request, "categoriesDefaultsIds", sortedCategoriesDefaultsIds.toString());
				}
			}else {
				// Pas utilisé dans les autres modes
				setPreference(request, "categoriesDefaultsIds", "");
			}

			// Filtre catégories non  cochées
			if(mode.equals("normal") || mode.equals("district")) {
				if(displayList) {
					// Pas utilisé dans l'affichage des filtres en liste
					setPreference(request, "categoriesIds", "");
				}else{
					String categoriesIds = ParamUtil.getString(request, "categoriesIds");
					// On enregistre les ids des catégories sous forme de String
					// On sépare les catégories par des virgules
					List<Long> vocabulariesIds = new ArrayList<>();
					for (String categoryIdStr : categoriesIds.split(",")) {
						long categoryId = GetterUtil.getLong(categoryIdStr);
						if (categoryId > 0) {
							AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
							if (category != null && !vocabulariesIds.contains(category.getVocabularyId())) {
								vocabulariesIds.add(category.getVocabularyId());
							}
						}
					}
					StringBuilder sortedCategoriesIds = new StringBuilder();
					for (Long vocabularyId : vocabulariesIds) {
						for (String categoryIdStr : categoriesIds.split(",")) {
							long categoryId = GetterUtil.getLong(categoryIdStr);
							if (categoryId > 0) {
								AssetCategory category = AssetCategoryLocalServiceUtil.fetchAssetCategory(categoryId);
								if (category != null && vocabularyId == category.getVocabularyId()) {
									if (sortedCategoriesIds.length() > 0) {
										sortedCategoriesIds.append(",");
									}
									sortedCategoriesIds.append(categoryId);
								}
							}
						}
					}
					setPreference(request, "categoriesIds", sortedCategoriesIds.toString());
				}
			}else {
				// Pas utilisé dans les autres modes
				setPreference(request, "categoriesIds", "");
			}

			// Filtre catégories parentes
			if(mode.equals("normal") || mode.equals("district")) {
				if(displayList) {
					String parentsCategoriesIds = ParamUtil.getString(request, "parentsCategoriesIds");
					// On enregistre les ids des catégories sous forme de String
					// On sépare les catégories par des virgules
					List<Long> vocabulariesIds = new ArrayList<>();
					for (String parentCategoryIdStr : parentsCategoriesIds.split(",")) {
						long parentCategoryId = GetterUtil.getLong(parentCategoryIdStr);
						if (parentCategoryId > 0) {
							AssetCategory parentCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(parentCategoryId);
							if (parentCategory != null && !vocabulariesIds.contains(parentCategory.getVocabularyId())) {
								vocabulariesIds.add(parentCategory.getVocabularyId());
							}
						}
					}
					StringBuilder sortedParentsCategoriesIds = new StringBuilder();
					for (Long vocabularyId : vocabulariesIds) {
						for (String parentCategoryIdStr : parentsCategoriesIds.split(",")) {
							long parentCategoryId = GetterUtil.getLong(parentCategoryIdStr);
							if (parentCategoryId > 0) {
								AssetCategory parentCategory = AssetCategoryLocalServiceUtil.fetchAssetCategory(parentCategoryId);
								if (parentCategory != null && vocabularyId == parentCategory.getVocabularyId()) {
									if (sortedParentsCategoriesIds.length() > 0) {
										sortedParentsCategoriesIds.append(",");
									}
									sortedParentsCategoriesIds.append(parentCategoryId);
								}
							}
						}
					}
					setPreference(request, "parentsCategoriesIds", sortedParentsCategoriesIds.toString());
				}else{
					// Pas utilisé dans l'affichage des filtres en checkbox
					setPreference(request, "parentsCategoriesIds", "");
				}
			}else {
				// Pas utilisé dans les autres modes
				setPreference(request, "parentsCategoriesIds", "");
			}

			// Filtre vocabulaire
			if(mode.equals("normal") || mode.equals("district")) {
				if(displayList) {
					String vocabulariesIdsFilter = ParamUtil.getString(request, "vocabulariesIds");
					setPreference(request, "vocabulariesIds", vocabulariesIdsFilter);
				}else{
					// Pas utilisé dans l'affichage des filtres en checkbox
					setPreference(request, "vocabulariesIds", "");
				}
			}else {
				// Pas utilisé dans les autres modes
				setPreference(request, "vocabulariesIds", "");
			}

			// Filtre par date
			boolean hasDateFilter = false;
			if(mode.equals("normal") || mode.equals("district")) {
				String dateField = ParamUtil.getString(request, "dateField");
				setPreference(request, "dateField", dateField);
				hasDateFilter = Boolean.parseBoolean(dateField);
			}else {
				// Pas utilisé dans les autres modes
				setPreference(request, "dateField", "");
			}

			// Etendu du filtre par date
			if(mode.equals("normal") || mode.equals("district")) {
				if(hasDateFilter) {
					String defaultDateRange = ParamUtil.getString(request, "defaultDateRange");
					setPreference(request, "defaultDateRange", defaultDateRange);
				}else{
					setPreference(request, "defaultDateRange", "");
				}
			}else {
				// Pas utilisé dans les autres modes
				setPreference(request, "defaultDateRange", "");
			}

			// Filtre sur le quartier de l'utilisateur
			if(mode.equals("district")) {
				String districtUser = ParamUtil.getString(request, "districtUser");
				setPreference(request, "districtUser", districtUser);
			}else {
				// Pas utilisé dans les autres modes
				setPreference(request, "districtUser", "");
			}

			// Texte introduction en mode widget
			if(mode.equals("widget")) {
				String widgetIntro = ParamUtil.getString(request, "widgetIntro");
				setPreference(request, "widgetIntro", widgetIntro);
			}else {
				// Pas utilisé dans les autres modes
				setPreference(request, "widgetIntro", "");
			}

			// URL lien bouton mode widget
			if(mode.equals("widget")) {
				String widgetLink = ParamUtil.getString(request, "widgetLink");
				setPreference(request, "widgetLink", widgetLink);
			}else {
				// Pas utilisé dans les autres modes
				setPreference(request, "widgetLink", "");
			}

			// Centres d'intérêts affichés non cochés
			if(mode.equals("aroundme")) {
				StringBuilder interestsIdsString = new StringBuilder();
				List<Interest> interests = InterestLocalServiceUtil.getInterests(-1, -1).stream()
						.filter(InterestModel::isApproved).collect(Collectors.toList());
				for (Interest interest : interests) {
					String interestStatus = ParamUtil.getString(request, "interestStatus" + interest.getInterestId());
					if (interestStatus.equals("unchecked")) {
						if (interestsIdsString.length() > 0) {
							interestsIdsString.append(",");
						}
						interestsIdsString.append(interest.getInterestId());
						jsonArrayUncheckedInterests.put(interest.getInterestId());
					}
				}
				setPreference(request, "interestsIds", interestsIdsString.toString());
				json.put("interestsIds", jsonArrayUncheckedInterests);
			}else {
				// Pas utilisé dans les autres modes
				setPreference(request, "interestsIds", "");
			}

			// Choix afficher les favoris
			if(mode.equals("aroundme")) {
				String showFavorites = ParamUtil.getString(request, "showFavorites");
				setPreference(request, "showFavorites", showFavorites);
				json.put("showFavorites", showFavorites);
			}else {
				// Pas utilisé dans les autres modes
				setPreference(request, "showFavorites", "");
			}

			// Choix afficher l'info trafic
			boolean displayTraffic = false;
			if(mode.equals("widget")) {
				// Pas utilisé en mode widget
				setPreference(request, "showTraffic", "");
			}else {
				String showTraffic = ParamUtil.getString(request, "showTraffic");
				setPreference(request, "showTraffic", showTraffic);
				displayTraffic = Boolean.parseBoolean(showTraffic);
			}

			// Liaison de l'info trafic à une catégorie
			String linkCategoryId = "";
			if(mode.equals("normal") || mode.equals("district")) {
				if(displayTraffic){
					linkCategoryId = ParamUtil.getString(request, "linkCategoryId");
					setPreference(request, "linkCategoryId", linkCategoryId);
				}else{
					setPreference(request, "linkCategoryId", "");
				}
			}else {
				// Pas utilisé dans les autres modes
				setPreference(request, "linkCategoryId", "");
			}

			// Nom de la catégorie
			if(mode.equals("normal") || mode.equals("district")) {
				if(displayTraffic){
					String categoryTitle = "";
					if (Validator.isNotNull(linkCategoryId)) {
						AssetCategory category = AssetCategoryLocalServiceUtil
								.fetchAssetCategory(Long.parseLong(linkCategoryId));
						if (Validator.isNotNull(category)) {
							categoryTitle = category.getTitle(Locale.FRANCE);
						}
					}
					setPreference(request, "categoryTitle", categoryTitle);
				}else{
					setPreference(request, "categoryTitle", "");
				}
			}else {
				// Pas utilisé dans les autres modes
				setPreference(request, "categoryTitle", "");
			}

			// Liaison de l'info trafic à un CI
			if(mode.equals("aroundme")) {
				if(displayTraffic){
					String linkInterestId = ParamUtil.getString(request, "linkInterestId");
					setPreference(request, "linkInterestId", linkInterestId);
					json.put("trafficInterestId", linkInterestId);
				}else{
					setPreference(request, "linkInterestId", "");
					json.put("trafficInterestId", "");
				}
			}else {
				// Pas utilisé dans les autres modes
				setPreference(request, "linkInterestId", "");
				json.put("trafficInterestId", "");
			}

			// Si on est en mode autour de moi, on écrase (Si elle existe) la précédente
			// configuration globale
			if(mode.equals("aroundme")) {
				ExpandoBridge ed = themeDisplay.getScopeGroup().getExpandoBridge();

				try {
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

			// mode du portlet
			request.setAttribute("mode", configuration.mode());

			// Widget mod du portlet
			request.setAttribute("widgetMod", configuration.widgetMod());

			// Config par défaut
			request.setAttribute("defaultConfig", configuration.defaultConfig());

			// Config par défaut
			request.setAttribute("districtMod", configuration.districtMod());

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

			// Choix afficher la zone de config
			request.setAttribute("showConfig", configuration.showConfig());

			// Choix afficher picto dans la zone de config
			request.setAttribute("showPictos", configuration.showPictos());

			// Choix afficher la liste à droite
			request.setAttribute("showList", configuration.showList());

			// Détourage d'un quartier ou d'une commune
			request.setAttribute("clippingTerritory", configuration.clippingTerritory());

			// territoires
			long companyId = themeDisplay.getCompanyGroupId();
			AssetCategory france = AssetVocabularyHelper.getCategory("France", companyId);
			List<String[]> territories = new ArrayList<>();
			if (france != null) {
				List<AssetCategory> cities = AssetVocabularyHelper.getChild(france.getCategoryId());
				for (AssetCategory city : cities) {
					String[] territory = {city.getCategoryId()+"", city.getTitleCurrentValue()};
					territories.add(territory);
					for (AssetCategory district : AssetVocabularyHelper.getChild(city.getCategoryId()) ) {
						String[] subTerritory = {district.getCategoryId()+"", "- " + district.getTitleCurrentValue()};
						territories.add(subTerritory);
					}

				}
			}
			request.setAttribute("territories", territories);
			request.setAttribute("clippingCategoryId", configuration.clippingCategoryId());

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

			// Préfiltres catégories
			String prefilterCategoriesIds = configuration.prefilterCategoriesIds().replace(";", ",");
			request.setAttribute("prefilterCategoriesIds", prefilterCategoriesIds);

			// Choix d'affichage des filtres
			request.setAttribute("listDisplay", configuration.listDisplay());

			// Filtres checkbox cochés par défaut
			String categoriesDefaultsIds = configuration.categoriesDefaultsIds().replace(";", ",");
			request.setAttribute("categoriesDefaultsIds", categoriesDefaultsIds);

			// Filtres checkbox non cochés
			String categoriesIds = configuration.categoriesIds().replace(";", ",");
			request.setAttribute("categoriesIds", categoriesIds);

			// Filtres liste des catégories parentes
			String parentsCategoriesIds = configuration.parentsCategoriesIds().replace(";", ",");
			request.setAttribute("parentsCategoriesIds", parentsCategoriesIds);

			// Filtres liste des vocabulaires
			List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil.getAssetVocabularies(-1, -1);
			vocabularies = vocabularies.stream().filter(v -> v.getGroupId() == themeDisplay.getCompanyGroupId()
					|| v.getGroupId() == themeDisplay.getScopeGroupId())
					.collect(Collectors.toList());
			List<String[]> vocabulariesList = new ArrayList<>();
			for (AssetVocabulary vocabulary : vocabularies) {
				String groupName = "";
				Group group = GroupLocalServiceUtil.fetchGroup(vocabulary.getGroupId());
				if(Validator.isNotNull(group))
					groupName = group.getNameCurrentValue();
				String[] vocabularyDetail = {""+vocabulary.getVocabularyId(), vocabulary.getName(), groupName};
				vocabulariesList.add(vocabularyDetail);
			}
			request.setAttribute("vocabularies", vocabulariesList);
			String vocabulariesIds = configuration.vocabulariesIds().replace(";", ",");
			request.setAttribute("vocabulariesIds", vocabulariesIds);

			// Filtre sur la date
			request.setAttribute("dateField", configuration.dateField());

			// Etendu du filtre sur la date
			request.setAttribute("defaultDateRange", configuration.defaultDateRange());

			// Préfiltre sur le quartier utilisateur
			request.setAttribute("districtUser", configuration.districtUser());

			// Texte intro mode widget
			request.setAttribute("widgetIntro", configuration.widgetIntro());

			// URL bouton mode widget
			request.setAttribute("widgetLink", configuration.widgetLink());

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
			vocabularies = vocabularies.stream().filter(v -> v.getGroupId() == themeDisplay.getCompanyGroupId())
					.collect(Collectors.toList());
			StringBuilder vocabulariesStr = new StringBuilder();
			for (AssetVocabulary assetVocabulary : vocabularies) {
				if(!vocabulariesStr.toString().equals("")) {
					vocabulariesStr.append(",");
				}
				vocabulariesStr.append(assetVocabulary.getVocabularyId());
			}
			request.setAttribute("vocabulariesStr", vocabulariesStr.toString());
			request.setAttribute("linkCategoryId", configuration.linkCategoryId());
			request.setAttribute("categoryTitle", configuration.categoryTitle());

			// Liaison de l'info trafic à un CI
			request.setAttribute("linkInterestId", configuration.linkInterestId());

			
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
