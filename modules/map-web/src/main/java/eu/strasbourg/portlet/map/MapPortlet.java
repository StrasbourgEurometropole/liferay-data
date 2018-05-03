package eu.strasbourg.portlet.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
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

	@Override
	public void render(RenderRequest request, RenderResponse renderResponse) throws IOException, PortletException {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

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

			boolean hasConfig = false; // Permet de cocher tous les POI si
										// aucune configuration
			boolean widgetMod = false;
			long groupId = -1; // Group du site dans lequel on doit afficher le
			// détail du POI
			boolean openInNewTab = false; // Ouvertures du détail des POIS dans
											// la
			// même fenêtre par défaut
			String typesContenu = "all"; // Les type de contenus
			String categoriesIdsString = ""; // Les id des catégories affichées
												// non cochées
			String categoriesDefaultsIdsString = ""; // Les catégories affichées
														// coché
			String prefilterCategoriesIdsString = ""; // Les catégories masquées
														// et
			// cochées
			boolean districtUser = false; // Affichage de la map du quartier de
			// l'utilisateur
			AssetCategory district = null;
			String interestsIdsString = ""; // Les id des intérêts affichés non
											// cochés
			String interestsDefaultsIdsString = ""; // Les intérêts affichés
													// cochés
			boolean showFavorites = true; // Affichage des favoris par défaut
			boolean showConfig = true; // Affichage de la zone de configuration
			boolean showList = true; // Affichage de la liste à droite
			List<AssetCategory> categories = null; // Les catégories actives
			List<Interest> interests = null; // Les intérêts actifs

			// Est-ce que la config du portlet est défini ?
			if (configuration.hasConfig()) {
				// Chargement de la configuration globale pour le mode widget
				if (configuration.widgetMod()) {
					ExpandoBridge ed = themeDisplay.getScopeGroup().getExpandoBridge();
					hasConfig = true;
					widgetMod = true;
					try {
						String globalConfig = GetterUtil.getString(ed.getAttribute("map_global_config"));
						JSONObject json = JSONFactoryUtil.createJSONObject(globalConfig);
						JSONArray jsonArray = json.getJSONArray("typesContenu");
						typesContenu = jsonArray.join(",").replace("\"", "");
						JSONArray jsonArrayCategories = json.getJSONArray("categoriesIds");
						categoriesIdsString = jsonArrayCategories.join(",");
						JSONArray jsonArrayCategoriesDefault = json.getJSONArray("categoriesDefaultsIds");
						categoriesDefaultsIdsString = jsonArrayCategoriesDefault.join(",");
						JSONArray jsonArrayPrefilterCategories = json.getJSONArray("prefilterCategoriesIds");
						prefilterCategoriesIdsString = jsonArrayPrefilterCategories.join(",");
						JSONArray jsonArrayInterests = json.getJSONArray("interestsIds");
						interestsIdsString = jsonArrayInterests.join(",");
						JSONArray jsonArrayInterestsDefault = json.getJSONArray("interestsDefaultsIds");
						interestsDefaultsIdsString = jsonArrayInterestsDefault.join(",");
						showFavorites = json.getBoolean("showFavorites");
						showConfig = json.getBoolean("showConfig");
						showList = json.getBoolean("showList");
					} catch (Exception ex) {
						_log.error("Missing expando field : map_global_config");
					}
				}
				// Chargement de la configuration du portlet sinon
				else {
					hasConfig = true;
					groupId = configuration.groupId();
					openInNewTab = configuration.openInNewTab();
					districtUser = configuration.districtUser();
					if (Validator.isNotNull(address)) {
						String sectorType = "quartier_elus";
						district = adictService.getDistrictByAddressAndSector(address, sectorType);
					}
					typesContenu = configuration.typesContenu();
					categoriesIdsString = configuration.categoriesIds();
					categoriesDefaultsIdsString = configuration.categoriesDefaultsIds();
					prefilterCategoriesIdsString = configuration.prefilterCategoriesIds();
					interestsIdsString = configuration.interestsIds();
					interestsDefaultsIdsString = configuration.interestsDefaultsIds();
					showFavorites = configuration.showFavorites();
					showConfig = configuration.showConfig();
					showList = configuration.showList();
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
					// Si jamais aucun intérêt affiché et non cochée n'est coché
					// intentionnellement...
					interestIds = new ArrayList<Long>();

				List<Long> interestsDefaultsIds;
				if (!interestsDefaultsIdsString.equals(""))
					interestsDefaultsIds = Arrays.stream(interestsDefaultsIdsString.split(","))
							.map(i -> Long.parseLong(i.replace("\"", ""))).collect(Collectors.toList());
				else
					// Si jamais aucun intérêt affiché et coché n'est coché
					// intentionnellement...
					interestsDefaultsIds = new ArrayList<Long>();

				// Récupération de tous les centres d'intérêts affiché avec le
				// statut publié
				interests = InterestLocalServiceUtil.getInterests(-1, -1).stream()
						.filter(i -> i.getStatus() == 0 && (interestIds.contains(i.getInterestId())
								|| interestsDefaultsIds.contains(i.getInterestId())))
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
			} // Si pas de config on récupère toutes les catégories et tous les
				// intérêts
			else {
				categories = AssetCategoryLocalServiceUtil.getAssetCategories(-1, -1).stream()
						.sorted(Comparator.comparing(c2 -> c2.getTitle(Locale.FRANCE))).collect(Collectors.toList());
				interests = InterestLocalServiceUtil.getInterests(-1, -1).stream().filter(i -> i.getStatus() == 0)
						.sorted(Comparator.comparing(i -> i.getType().getTitle(Locale.FRANCE)))
						.collect(Collectors.toList());
			}

			// Si l'utilisateur est connecté et qu'il a configuré le portlet
			// autour de moi
			if (Validator.isNotNull(internalId)
					&& Validator.isNotNull(PublikUserLocalServiceUtil.getByPublikUserId(internalId).getMapConfig())) {
				hasConfig = true;
				PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);

				JSONObject json = JSONFactoryUtil.createJSONObject(user.getMapConfig());
				JSONArray jsonArrayCategories = json.getJSONArray("categories");
				if (jsonArrayCategories != null) {
					if (Validator.isNotNull(categoriesDefaultsIdsString)) {
						categoriesDefaultsIdsString += ",";
					}
					categoriesDefaultsIdsString += jsonArrayCategories.join(",");
				}
				JSONArray jsonArrayInterests = json.getJSONArray("interests");
				if (jsonArrayInterests != null) {
					if (Validator.isNotNull(interestsDefaultsIdsString)) {
						interestsDefaultsIdsString += ",";
					}
					interestsDefaultsIdsString += jsonArrayInterests.join(",");
				}

				showFavorites = json.getBoolean("showFavorites");
			} else // Sinon on prend par defaut les catégories de l'utilisateur
					// s'il en a
			{
				if (Validator.isNotNull(internalId)) {
					hasConfig = true;
					String userDefautPOI = StringUtil.merge(InterestLocalServiceUtil.getByPublikUserId(internalId)
							.stream().map(i -> i.getInterestId()).collect(Collectors.toList()), ",");
					if (Validator.isNotNull(userDefautPOI)) {
						if (Validator.isNotNull(interestsDefaultsIdsString)) {
							interestsDefaultsIdsString += ",";
						}
						interestsDefaultsIdsString += userDefautPOI;
					}
					// String userDefautCategories =
					// StringUtil.merge(InterestLocalServiceUtil.getByPublikUserId(internalId)
					// .stream().map(i ->
					// i.getInterestId()).collect(Collectors.toList()), ",");
					// if (Validator.isNotNull(userDefautCategories)) {
					// categoriesDefaultsIdsString += userDefautCategories;
					// }
				}
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

			eu.strasbourg.service.strasbourg.service.StrasbourgServiceUtil.getService();
			request.setAttribute("hasConfig", hasConfig);
			request.setAttribute("widgetMod", widgetMod);
			request.setAttribute("widgetIntro", configuration.widgetIntro());
			request.setAttribute("widgetLink", configuration.widgetLink());
			request.setAttribute("groupId", groupId);
			request.setAttribute("openInNewTab", openInNewTab);
			request.setAttribute("districtUser", districtUser);
			request.setAttribute("district", district);
			request.setAttribute("typesContenu", typesContenu);
			request.setAttribute("vocabularyGroups", vocabularyGroup);
			request.setAttribute("categoriesCheckedIds", categoriesDefaultsIdsString);
			request.setAttribute("prefilterCategoriesIds", prefilterCategoriesIdsString);
			request.setAttribute("interestGroups", InterestGroupDisplay.getInterestGroups(interests));
			request.setAttribute("interestsCheckedIds", interestsDefaultsIdsString);
			request.setAttribute("showFavorites", showFavorites);
			request.setAttribute("showConfig", showConfig);
			request.setAttribute("showList", showList);
			request.setAttribute("address", address);
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
				user.setMapConfig("");
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

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("toggleInterestPoint")) {

				// Récupération du publik ID avec la session
				String internalId = getPublikID(resourceRequest);

				if (Validator.isNotNull(internalId)) {
					PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);

					// JSON initialisation
					JSONObject json = JSONFactoryUtil.createJSONObject();
					JSONArray jsonArrayCategories = JSONFactoryUtil.createJSONArray();
					JSONArray jsonArrayInterests = JSONFactoryUtil.createJSONArray();

					String[] checkboxNamesCategories = ParamUtil.getString(resourceRequest, "checkboxNamesCategories")
							.split(",");
					for (String checkboxName : checkboxNamesCategories) {
						String categoryIdString = ParamUtil.getString(resourceRequest, checkboxName);

						if (Validator.isNotNull(categoryIdString) && !categoryIdString.equals("false"))
							jsonArrayCategories.put(Long.parseLong(categoryIdString));
					}

					String[] checkboxNamesInterests = ParamUtil.getString(resourceRequest, "checkboxNamesInterests")
							.split(",");
					for (String checkboxName : checkboxNamesInterests) {
						String interestIdString = ParamUtil.getString(resourceRequest, checkboxName);

						if (Validator.isNotNull(interestIdString) && !interestIdString.equals("false"))
							jsonArrayInterests.put(Long.parseLong(interestIdString));
					}

					// Enregistrement des préférences utilisateur.
					json.put("showFavorites", ParamUtil.getBoolean(resourceRequest, "showFavorites"));
					json.put("categories", jsonArrayCategories);
					json.put("interests", jsonArrayInterests);
					user.setMapConfig(json.toJSONString());
					PublikUserLocalServiceUtil.updatePublikUser(user);
				}
			}
		} catch (Exception e) {
			_log.error(e);
		}

		super.serveResource(resourceRequest, resourceResponse);
	}

	private String getPublikID(PortletRequest resourceRequest) {

		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();

		return SessionParamUtil.getString(originalRequest, "publik_internal_id");
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	@Reference
	private AdictService adictService;

}