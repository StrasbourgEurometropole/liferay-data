package eu.strasbourg.portlet.map;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.map.configuration.MapConfiguration;
import eu.strasbourg.service.adict.AdictService;
import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.service.InterestLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.PortletHelper;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import eu.strasbourg.utils.constants.VocabularyNames;

/**
 * @author romain.vergnais
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=Autour de moi",
		"javax.portlet.init-param.add-process-action-success-action=false", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/map-view.jsp",
		"javax.portlet.init-param.config-template=/map-configuration.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.MAP_WEB, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class MapPortlet extends MVCPortlet {

    private ThemeDisplay themeDisplay;

	@Override
	public void render(RenderRequest request, RenderResponse renderResponse) throws IOException, PortletException {

		try {
			themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            configId = null;

			// Récupération de la configuration
			MapConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(MapConfiguration.class);
			
			// Récupération du publik ID avec la session
			String internalId = getPublikID(request);

			String address = null;
			if (Validator.isNotNull(internalId)) {
				JSONObject userDetail = PublikApiClient.getUserDetails(internalId);
				address = userDetail.get("address") + " " + userDetail.get("zipcode") + " " + userDetail.get("city");
			}

			boolean hasConfig = false; // Permet de cocher tous les POI si aucune configuration
			String mode = ""; // Mode d'affichage
			boolean widgetMod = false;
			long groupId = -1; // Group du site dans lequel on doit afficher le détail du POI
			boolean openInNewTab = false; // Ouvertures du détail des POIS dans la même fenêtre par défaut
			String typesContenu = ""; // Les type de contenus
			String eventExplanationText = ""; // récupération du texte à afficher pour les évènements
			boolean showConfig = true; // Affichage de la zone de configuration
			boolean showList = true; // Affichage de la liste à droite
			String prefilterCategoriesIdsString = ""; // Les catégories masquées et cochées
			String categoriesIdsString = ""; // Les id des catégories affichées non cochées
			String categoriesDefaultsIdsString = ""; // Les catégories affichées coché
			boolean districtUser = false; // Affichage de la map du quartier de l'utilisateur
			String interestsIdsString = ""; // Les id des intérêts affichés
			String interestsDefaultsIdsString = ""; // Les intérêts cochés
			boolean showFavorites = false; // Affichage des favoris par défaut
			boolean showTraffic = false; // Affichage de l'info trafic
			String trafficCategoryId = ""; // Liaison de l'affichage de l'info trafic à une catégorie
			String trafficInterestId = ""; // Liaison de l'affichage de l'info trafic à un CI
			
			List<AssetCategory> categories = null; // Les catégories actives
			List<Interest> interests = null; // Les intérêts actifs
			AssetCategory district = null;

			// Est-ce que la config du portlet est défini ?
			if (configuration.hasConfig()) {
				hasConfig = true;
				mode = configuration.mode();
				groupId = configuration.groupId();
				openInNewTab = configuration.openInNewTab();
				// Chargement de la configuration globale pour le mode widget
				if (configuration.widgetMod()) {
					ExpandoBridge ed = themeDisplay.getScopeGroup().getExpandoBridge();
					widgetMod = true;
					try {
						String globalConfig = GetterUtil.getString(ed.getAttribute("map_global_config"));
						JSONObject json = JSONFactoryUtil.createJSONObject(globalConfig);
						JSONArray jsonArray = json.getJSONArray("typesContenu");
						typesContenu = jsonArray.join(",").replace("\"", "");
						JSONArray jsonArrayInterests = json.getJSONArray("interestsIds");
						interestsIdsString = jsonArrayInterests.join(",");
						showFavorites = json.getBoolean("showFavorites");
						trafficInterestId = json.getString("trafficInterestId");
					} catch (Exception ex) {
						_log.error("Missing expando field : map_global_config");
					}
				}
				// Chargement de la configuration du portlet sinon
				else {
					typesContenu = configuration.typesContenu();
					Map<Locale, String> mapText = LocalizationUtil.getLocalizationMap(configuration.eventExplanationXML());
					for (Map.Entry<Locale, String> map : mapText.entrySet()) {
						if (themeDisplay.getLocale().toString().equals(map.getKey().toString())) {
							eventExplanationText = HtmlUtil.unescape(map.getValue());
							break;
						}
					}
					if (Validator.isNull(eventExplanationText)) {
						eventExplanationText = "No configuration";
					}
					showConfig = configuration.showConfig();
					showList = configuration.showList();
					prefilterCategoriesIdsString = configuration.prefilterCategoriesIds();
					categoriesIdsString = configuration.categoriesIds();
					categoriesDefaultsIdsString = configuration.categoriesDefaultsIds();
					districtUser = configuration.districtUser();
					if (districtUser) {
                        if (Validator.isNotNull(address)) {
                        	try {
								district = adictService.getDistrictByAddress(address);
							}catch (Exception e ){
								_log.error(e);
							}
                        }
                        if (district == null) {
                            HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(request);
                            HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(servletRequest);
                            String districtId = ParamUtil.getString(originalRequest, "district");
                            if (Validator.isNotNull(districtId)) {
                                try {
                                    AssetVocabulary territoryVocabulary =
                                            AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.TERRITORY);
                                    district = AssetVocabularyHelper.getCategoryByExternalId(territoryVocabulary, districtId);
                                } catch (PortalException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

					interestsIdsString = configuration.interestsIds();
					showFavorites = configuration.showFavorites();
					showTraffic = configuration.showTraffic();
					trafficCategoryId = configuration.linkCategoryId();
					trafficInterestId = configuration.linkInterestId();
				}

				List<Long> categoriesIds;
				if (!categoriesIdsString.equals(""))
					categoriesIds = Arrays.stream(categoriesIdsString.split(","))
							.map(i -> Long.parseLong(i.replace("\"", ""))).collect(Collectors.toList());
				else
					// Si jamais aucune catégorie affichée et non cochée n'est
					// pas cochée intentionnellement...
					categoriesIds = new ArrayList<Long>();

				List<Long> categoriesDefaultsIds;
				if (!categoriesDefaultsIdsString.equals(""))
					categoriesDefaultsIds = Arrays.stream(categoriesDefaultsIdsString.split(","))
							.map(i -> Long.parseLong(i.replace("\"", ""))).collect(Collectors.toList());
				else
					// Si jamais aucune catégorie affichées et cochée n'est
					// pas cochée intentionnellement...
					categoriesDefaultsIds = new ArrayList<Long>();

				// Récupération de toutes les catégories à affichées
				categories = AssetCategoryLocalServiceUtil.getAssetCategories(-1, -1).stream()
						.filter(c -> categoriesIds.contains(c.getCategoryId())
								|| categoriesDefaultsIds.contains(c.getCategoryId()))
						.sorted(Comparator.comparing(c2 -> c2.getTitle(Locale.FRANCE))).collect(Collectors.toList());

				// On supprime les catégories du préfiltre qui sont également
				// dans les filtres
				List<String> prefilterCategoriesIds = new ArrayList<String>();
				if (!prefilterCategoriesIdsString.equals(""))
					prefilterCategoriesIds = Arrays.stream(prefilterCategoriesIdsString.split(","))
							.map(c -> c.replace("\"", "")).filter(c -> !categoriesIds.contains(Long.parseLong(c))
									&& !categoriesDefaultsIds.contains(Long.parseLong(c)))
							.collect(Collectors.toList());
				prefilterCategoriesIdsString = String.join(",", prefilterCategoriesIds);

				List<Long> interestIds;
				if (!interestsIdsString.equals(""))
					interestIds = Arrays.stream(interestsIdsString.split(","))
							.map(i -> Long.parseLong(i.replace("\"", ""))).collect(Collectors.toList());
				else
					// Si jamais aucun intérêt affiché n'est coché intentionnellement...
					interestIds = new ArrayList<Long>();

				// Récupération de tous les centres d'intérêts affiché avec le
				// statut publié
				interests = InterestLocalServiceUtil.getInterests(-1, -1).stream()
						.filter(i -> i.getStatus() == 0 && interestIds.contains(i.getInterestId()))
						.sorted(Comparator.comparing(i2 -> i2.getType().getTitle(Locale.FRANCE)))
						.collect(Collectors.toList());

				// Si on ne veut que les POIs d'un quartier, on supprime le
				// vocabulaire territoire de la liste de catégories ainsi que le
				// group quartier de CI à afficher et on ajoute le quartier de
				// l'utilisateur en préfiltre
				if (districtUser) {
					AssetVocabulary vocabulary = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.TERRITORY);
					if (Validator.isNotNull(vocabulary)) {
						categories.removeIf(c -> c.getVocabularyId() == vocabulary.getVocabularyId());
					}
					// Récupération de toutes les catégories en préfiltre
					prefilterCategoriesIdsString = "";
					if(district != null){
						prefilterCategoriesIdsString += district.getCategoryId();
					}
					for (String categoryId : prefilterCategoriesIds) {
						if (!vocabulary.getCategories().stream()
								.anyMatch(c -> c.getCategoryId() == Long.parseLong(categoryId))) {
							if (prefilterCategoriesIdsString.length() > 0)
								prefilterCategoriesIdsString += ",";
							prefilterCategoriesIdsString += categoryId;
						}
					}
					interests.removeIf(i -> i.getType().getName().equals("Quartier"));
				}
			} // Si pas de config on ne récupère aucunes catégories ni intérêts
			else {
				categories = new ArrayList<>();
				interests = new ArrayList<>();
			}

			// on regroupe les catégories par vocabulaire
			categories = categories.stream().sorted(Comparator.comparing(c -> c.getVocabularyId()))
					.collect(Collectors.toList());
			Map<String, List<AssetCategory>> vocabularyGroup = new HashMap<String, List<AssetCategory>>();
			long oldVocabulary = -1;
			List<AssetCategory> categoriesVocabulary = null;
			for (AssetCategory category : categories) {
				if (oldVocabulary != category.getVocabularyId()) {
					if (oldVocabulary != -1) {
						String vocabularyDescription = "";
						AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil
								.fetchAssetVocabulary(oldVocabulary);
						if (vocabulary != null) {
							vocabularyDescription = vocabulary.getDescription(Locale.FRANCE);
						}
						vocabularyGroup.put(vocabularyDescription, categoriesVocabulary);
					}
					categoriesVocabulary = new ArrayList<AssetCategory>();
					oldVocabulary = category.getVocabularyId();
				}
				categoriesVocabulary.add(category);
			}
			if (oldVocabulary != -1) {
				String vocabularyName = "";
				AssetVocabulary vocabulary = AssetVocabularyLocalServiceUtil.fetchAssetVocabulary(oldVocabulary);
				if (vocabulary != null) {
					vocabularyName = vocabulary.getDescription(Locale.FRANCE);
				}
				vocabularyGroup.put(vocabularyName, categoriesVocabulary);
			}

			// Si l'utilisateur est connecté et qu'il a configuré le portlet
			// autour de moi
			boolean hasUserConfig = false;
			boolean hasUserConfigForPortlet = false;
			PublikUser user = null;
			String userConfigString = "";
			if (Validator.isNotNull(internalId)) {
				user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);
				userConfigString = user.getMapConfig();
				if (Validator.isNotNull(userConfigString)) {
					hasUserConfig = true;
				}
			}
			if (hasUserConfig) {
				JSONObject userPortletConfig = null;
				// Une config par portlet (nouvelle façon de faire)
				if (userConfigString != null && userConfigString.startsWith("[")) {
					JSONArray userConfigs =  JSONFactoryUtil.createJSONArray(userConfigString);
					// On va recherche le configId correspondant
					String configId = getConfigId();
					for (int i = 0; i < userConfigs.length(); i++) {
						JSONObject config = userConfigs.getJSONObject(i);
						if (config.getString("configId").equals(configId)) {
							userPortletConfig = config;
							hasUserConfigForPortlet = true;
							break;
						}
					}
				} else { // Ca n'a été enregistré que pour le mode widget (legacy)
					userPortletConfig = JSONFactoryUtil.createJSONObject(userConfigString);
				}


				if (userPortletConfig != null) {
                    JSONArray jsonArrayCategories = userPortletConfig.getJSONArray("categories");
                    if (jsonArrayCategories != null) {
                        categoriesDefaultsIdsString = jsonArrayCategories.join(",");
                    }
                    JSONArray jsonArrayInterests = userPortletConfig.getJSONArray("interests");
                    if (jsonArrayInterests != null) {
                        interestsDefaultsIdsString = jsonArrayInterests.join(",");
                    }
                    showFavorites = userPortletConfig.getBoolean("showFavorites"); 
                }
			} 
			
			// Si l'utilisateur n'a pas de config pour ce portlet on prend ses centres d'intérêts (s'il en a)
			if (!hasUserConfigForPortlet) { 
				if (Validator.isNotNull(internalId)) {
					String userDefautPOI = StringUtil.merge(InterestLocalServiceUtil.getByPublikUserId(internalId)
							.stream().map(i -> i.getInterestId()).collect(Collectors.toList()), ",");
					if (Validator.isNotNull(userDefautPOI)) {
						interestsDefaultsIdsString += userDefautPOI;
					}
				}
			}
			
			request.setAttribute("hasConfig", hasConfig);
			request.setAttribute("mode", mode);
			request.setAttribute("widgetMod", widgetMod);
			request.setAttribute("defaultConfig", configuration.defaultConfig());
			request.setAttribute("groupId", groupId);
			request.setAttribute("openInNewTab", openInNewTab);
			request.setAttribute("typesContenu", typesContenu);
			request.setAttribute("eventExplanationText", eventExplanationText);
			request.setAttribute("showConfig", showConfig);
			request.setAttribute("showList", showList);			
			request.setAttribute("widgetIntro", configuration.widgetIntro());
			request.setAttribute("widgetLink", configuration.widgetLink());
			request.setAttribute("vocabularyGroups", vocabularyGroup);
			request.setAttribute("prefilterCategoriesIds", prefilterCategoriesIdsString);
			request.setAttribute("categoriesCheckedIds", categoriesDefaultsIdsString);			
			request.setAttribute("districtUser", districtUser);
			request.setAttribute("district", district);
			request.setAttribute("interestGroups", InterestGroupDisplay.getInterestGroups(interests));
			request.setAttribute("interestsCheckedIds", interestsDefaultsIdsString);
			request.setAttribute("showFavorites", showFavorites);
			request.setAttribute("showTraffic", showTraffic);
			request.setAttribute("trafficCategoryId", trafficCategoryId);
			request.setAttribute("trafficInterestId", trafficInterestId);
			request.setAttribute("address", address);
			request.setAttribute("internalId", internalId);
			
			// titre personnalisable en mode widget
			request.setAttribute("title", PortletHelper.getPortletTitle("auround-me", request));
			
			MapDisplayContext dc = new MapDisplayContext(themeDisplay);
			request.setAttribute("dc", dc);
			if (widgetMod) {
				request.setAttribute(getMVCPathAttributeName(renderResponse.getNamespace()), "/map-widget-view.jsp");
			}
		} catch (Exception e) {
			_log.error(e);
		}
		super.render(request, renderResponse);
	}

	public void resetUserConfiguration(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException {

		try {
			// Récupération du publik ID avec la session
			String internalId = getPublikID(actionRequest);

			if (Validator.isNotNull(internalId)) {
				PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);
				String userConfigString = user.getMapConfig();
				JSONArray userConfig = getCurrentPortletUserConfig(userConfigString);
				userConfig = getUserConfigWithoutCurrentPortlet(userConfig, getConfigId());
				userConfigString = userConfig.toJSONString();
				user.setMapConfig(userConfigString);
				PublikUserLocalServiceUtil.updatePublikUser(user);
			}

			// Redirection (évite double
			// requête POST si l'utilisateur actualise sa page)
			ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String portletName = (String) actionRequest.getAttribute(WebKeys.PORTLET_ID);
			PortletURL renderUrl = PortletURLFactoryUtil.create(actionRequest, portletName, themeDisplay.getPlid(),
					PortletRequest.RENDER_PHASE);
			actionResponse.sendRedirect(renderUrl.toString());
		} catch (Exception e) {
			_log.error(e);
		}

	}

	/**
	 * Enregistrement de la configuration utilisateur
	 */
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		try {
            configId = null;
            themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("toggleInterestPoint")) {

				// Récupération du publik ID avec la session
				String internalId = getPublikID(resourceRequest);

				if (Validator.isNull(internalId)) {
					return;
				}

				PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);
				if (Validator.isNull(user)) {
					return;
				}

				// JSON initialisation
				JSONObject configForPortlet = createUserConfigForPorltet(resourceRequest);
				JSONArray configForUser = addPortletConfigToUserConfig(user.getMapConfig(), configForPortlet);
				user.setMapConfig(configForUser.toJSONString());
				PublikUserLocalServiceUtil.updatePublikUser(user);
			}
		} catch (Exception e) {
			_log.error(e);
		}

		super.serveResource(resourceRequest, resourceResponse);
	}

    /**
     * Retourne la configuration existante du portlet à partir de la String enregistrée
     */
	private JSONArray getCurrentPortletUserConfig(String userConfigString) {
        JSONArray userConfig;
        if (Validator.isNull(userConfigString)) { // Cas où il n'y a pas encore de config utilisateur
            userConfig = JSONFactoryUtil.createJSONArray();
        } else if (userConfigString.startsWith("[")) { // Cas où la config utilisateur existe déjà
            try {
                userConfig = JSONFactoryUtil.createJSONArray(userConfigString);
            } catch (JSONException e) {
                userConfig = JSONFactoryUtil.createJSONArray();
            }
        } else { // Cas où la config utilisateur date du moment où l'unique config sauvée étant pour le widget
            userConfig = JSONFactoryUtil.createJSONArray();
            try {
                JSONObject portletConfigInOlderFormat = JSONFactoryUtil.createJSONObject(userConfigString);
                portletConfigInOlderFormat.put("configId", "widget");
                userConfig.put(portletConfigInOlderFormat);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return userConfig;
    }

    /**
     * Retourne le configId du portlet en cours
     */
    private String getConfigId() {
        if (Validator.isNull(configId)) {
            try {
                MapConfiguration configuration = themeDisplay.getPortletDisplay()
                        .getPortletInstanceConfiguration(MapConfiguration.class);
                if (configuration.defaultConfig() || configuration.widgetMod()) {
                    configId = "widget";
                } else {
                    configId = themeDisplay.getPortletDisplay().getInstanceId();
                }
            } catch (ConfigurationException e) {
                configId = themeDisplay.getPortletDisplay().getInstanceId();
            }
        }
        return configId;
    }
    private String configId;

    /**
     * Retire la configuration du portlet en cours à partir de la configuration userConfig
     */
    private JSONArray getUserConfigWithoutCurrentPortlet(JSONArray userConfig, String configId) {
	    JSONArray userConfigWithoutCurrentPortlet = JSONFactoryUtil.createJSONArray();
        for (int i = 0; i < userConfig.length(); i++) {
            JSONObject portletConfig = userConfig.getJSONObject(i);
            String currentConfigId = portletConfig.getString("configId");
            if (!configId.equals(currentConfigId)) {
                userConfigWithoutCurrentPortlet.put(portletConfig);
            }
        }
        return userConfigWithoutCurrentPortlet;
    }

    /**
     * Ajoute la configuration du portlet en cours à la configuration utilisateur
     */
	private JSONArray addPortletConfigToUserConfig(String userConfigString, JSONObject currentPortletConfig) {
		JSONArray userConfig = getCurrentPortletUserConfig(userConfigString);
		String currentPortletConfigId = currentPortletConfig.getString("configId");
		userConfig = getUserConfigWithoutCurrentPortlet(userConfig, currentPortletConfigId);
        userConfig.put(currentPortletConfig);
		return userConfig;
	}

    /**
     * Crée la configuration du portlet en cours
     */
	private JSONObject createUserConfigForPorltet(ResourceRequest resourceRequest) {
		JSONObject configForPortlet = JSONFactoryUtil.createJSONObject();

		// ConfigId
        configForPortlet.put("configId", getConfigId());

        // Catégories
        JSONArray jsonArrayCategories = JSONFactoryUtil.createJSONArray();
		String[] checkboxNamesCategories = ParamUtil.getString(resourceRequest, "checkboxNamesCategories")
				.split(",");
		for (String checkboxName : checkboxNamesCategories) {
			String categoryIdString = ParamUtil.getString(resourceRequest, checkboxName);

			if (Validator.isNotNull(categoryIdString) && !categoryIdString.equals("false"))
				jsonArrayCategories.put(Long.parseLong(categoryIdString));
		}
        configForPortlet.put("categories", jsonArrayCategories);

        // CI
        JSONArray jsonArrayInterests = JSONFactoryUtil.createJSONArray();
		String[] checkboxNamesInterests = ParamUtil.getString(resourceRequest, "checkboxNamesInterests")
				.split(",");
		for (String checkboxName : checkboxNamesInterests) {
			String interestIdString = ParamUtil.getString(resourceRequest, checkboxName);

			if (Validator.isNotNull(interestIdString) && !interestIdString.equals("false"))
				jsonArrayInterests.put(Long.parseLong(interestIdString));
		}
        configForPortlet.put("interests", jsonArrayInterests);

        // Favoris
		configForPortlet.put("showFavorites", ParamUtil.getBoolean(resourceRequest, "showFavorites"));

		return configForPortlet;
	}

	private String getPublikID(PortletRequest resourceRequest) {

		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();

		return SessionParamUtil.getString(originalRequest, "publik_internal_id");
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	private AdictService adictService;

	@Reference(unbind = "-")
    public void setAdictService(AdictService adictService) {
        this.adictService = adictService;
    }
}