package eu.strasbourg.portlet.dynamic_search_asset;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.dynamic_search_asset.configuration.DynamicSearchAssetConfiguration;
import eu.strasbourg.portlet.dynamic_search_asset.constants.Constants;
import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.service.ActivityCourseLocalServiceUtil;
import eu.strasbourg.service.activity.service.ActivityLocalServiceUtil;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.Manifestation;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.agenda.service.ManifestationLocalServiceUtil;
import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.EditionGalleryLocalServiceUtil;
import eu.strasbourg.service.edition.service.EditionLocalServiceUtil;
import eu.strasbourg.service.official.model.Official;
import eu.strasbourg.service.official.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.BudgetPhaseLocalServiceUtil;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.service.project.service.ParticipationLocalServiceUtil;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;
import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.service.VideoLocalServiceUtil;
import eu.strasbourg.utils.LayoutHelper;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Collator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * @author cedric.henry
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.css-class-wrapper=dynamic-search-asset-portlet",
		"javax.portlet.display-name=Recherche d'asset dynamique",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/dynamic-search-asset-view.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.DYNAMIC_SEARCH_ASSET_WEB,
		"javax.portlet.init-param.config-template=/dynamic-search-asset-configuration.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class DynamicSearchAssetWebPortlet extends MVCPortlet {
	
	private List<AssetEntry> assetEntries;
	private long totalResult;

	/**
	 * Initialisation de la vue
	 */
	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		try {
			// Recuperation du contexte de la requete
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			DynamicSearchAssetConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(DynamicSearchAssetConfiguration.class);
			
			// Recuperation et attribution des informations de l'utilisateur
			String publikUserId = getPublikID(request);
			request.setAttribute("publikUserId", publikUserId);
			
			// Recuperation et attribution de l'URL de base du site
			String homeUrl = Utils.getHomeURL(themeDisplay);
			request.setAttribute("homeURL", homeUrl);
			
			// Recuperation du formulaire configuré
			String searchForm = configuration.searchForm();
			request.setAttribute("searchForm", searchForm);
			
			// Recuperation du type de recherche
			Boolean dynamicSearch = configuration.dynamicSearch();
			request.setAttribute("dynamicSearch", dynamicSearch);
			
			// Recuperation des classes demandees
			List<String> classNames = this.getConfiguredClassNamesList(configuration);
			request.setAttribute("classNames", classNames);
			
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		super.render(request, response);
	}
	
	/**
	 * Méthode exécutée lors d'un appel AJAX
	 * Chaque appel est identifié par un RessourceID permettant de gérer le comportement 
	 * à fourir
	 * @note Il est possible de gérer chaque fonction dans une classe MVCRessourceCommand
	 * 		comme dans les modules BO pour les MVCActionCommand, toutefois il faudrait mutualiser
	 * 		les données dans une classe externe pour agir sur les même résultats 
	 */
	@Override
	public void serveResource(ResourceRequest request, ResourceResponse response)
			throws IOException, PortletException {
		try {
			// Recuperation du contexte de la requete
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			DynamicSearchAssetConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(DynamicSearchAssetConfiguration.class);
			long groupId = themeDisplay.getLayout().getGroupId();
			String resourceID = request.getResourceID();	
			
			// ---------------------------------------------------------------
			// -------- REQUETE : Effectuer une recherche --------------------
			// ---------------------------------------------------------------
			if (resourceID.equals("searchSubmit")) {
				
				// Recuperation du searchContext
				HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(request);
				SearchContext searchContext = SearchContextFactory.getInstance(servletRequest);
				
				// Recuperation des classNames selectionnes demandes par l'utilisateur
				String[] classNames;
				String selectedClassNames = ParamUtil.getString(request, "selectedClassNames");
				if(Validator.isNotNull(selectedClassNames))
					classNames = selectedClassNames.split(",");
				else {
					// si le paramètre n'existe pas on prend les className de la configuration
					String configurationClassNames = this.getConfiguredClassNames(configuration);
					classNames = configurationClassNames.split(",");
				}

				// Recuperation des mots clefs demandes par l'utilisateur
				String keywords = ParamUtil.getString(request, "keywords");
				
				// Inclusion ou non du scope global
				boolean globalScope = configuration.globalScope();
				long globalGroupId = themeDisplay.getCompanyGroupId();
				
				// Recuperation du nombre de resultat max demande
				int maxResults = (int) configuration.delta();
				
				// Recuperation de la configuration du prefiltre par date de la configuration
				boolean useDatePrefilter = configuration.dateField();
				long dateRangeFrom = (int) configuration.dateRangeFrom();
				long dateRangeTo = (int) configuration.dateRangeTo();
				LocalDate fromDate = LocalDate.now().plusDays(dateRangeFrom);
				LocalDate toDate = LocalDate.now().plusDays(dateRangeTo);
				
				// Recuperation de la configuration des prefiltre sur les categories
				String prefilterCategoriesIdsString = configuration.prefilterCategoriesIds();
				List<Long[]> prefilterCategoriesIds = new ArrayList<>();
				for (String prefilterCategoriesIdsGroupByVocabulary : prefilterCategoriesIdsString.split(";")) {
					Long[] prefilterCategoriesIdsForVocabulary = ArrayUtil
							.toLongArray(StringUtil.split(prefilterCategoriesIdsGroupByVocabulary, ",", 0));
					prefilterCategoriesIds.add(prefilterCategoriesIdsForVocabulary);
				}
				
				// Recuperation de la configuration des prefiltre sur les etiquettes
				String prefilterTagsNamesString = configuration.prefilterTagsNames();
				String[] prefilterTagsNames = StringUtil.split(prefilterTagsNamesString);
				
				// Recherche
				Hits hits = SearchHelper.getGlobalSearchHits(searchContext, classNames, groupId, globalGroupId, globalScope,
						keywords, useDatePrefilter, "publishDate_sortable", fromDate, toDate, new ArrayList<>(),
						prefilterCategoriesIds, prefilterTagsNames, themeDisplay.getLocale(), 0,
						maxResults, "score", false);
				
				List<AssetEntry> results = new ArrayList<>();
				BudgetPhase activePhase = BudgetPhaseLocalServiceUtil.getActivePhase(groupId);
				AssetCategory activePhaseCategory = activePhase != null ? activePhase.getPhaseCategory() : null;
				
				if (hits != null) {
					int i = 0;
					for (float s : hits.getScores()) {
						_log.info(GetterUtil.getString(hits.getDocs()[i].get(Field.TITLE)) + " : " + s);
						i++;
						if (i > 10)
							break;
					}
					for (Document document : hits.getDocs()) {
						AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(
								GetterUtil.getString(document.get(Field.ENTRY_CLASS_NAME)),
								GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));

						//On elimine tous les BP qui ne font pas parti de la phase active. Si pas de phase active, pas d'affichage des BP
						//C'est dommage de faire le filtrage après la recherche mais la configuration actuelle de la recherche ne permet pas
						//de préfiltrer sur la catégorie pour une seule entité en particuler
						if(document.get(Field.ENTRY_CLASS_NAME).equals("eu.strasbourg.service.project.model.BudgetParticipatif") &&
								(activePhaseCategory == null ||
										!AssetCategoryLocalServiceUtil.hasAssetEntryAssetCategory(entry.getEntryId(), activePhaseCategory.getCategoryId()))) {
							entry = null;
						}

						//On elimine tous les CW qui n'ont pas de layout
						if(document.get(Field.ENTRY_CLASS_NAME).equals(JournalArticle.class.getName())){
							// on vérifie si le jourrnalArticle est utilisé
							try {
								JournalArticle journalArticle = JournalArticleServiceUtil.getLatestArticle(entry.getClassPK());
								String url = LayoutHelper.getJournalArticleLayoutURL(journalArticle.getGroupId(), journalArticle.getArticleId(), themeDisplay);
								if (Validator.isNull(url)) {
									entry = null;
								}
							}catch (PortalException e){
								entry = null;
							}
						}
						
						if (entry != null) {
							results.add(entry);
						}
					}
					this.totalResult = SearchHelper.getGlobalSearchCount(searchContext, classNames, groupId, globalGroupId,
							globalScope, keywords, useDatePrefilter, "publishDate_sortable", fromDate, toDate, new ArrayList<>(),
							prefilterCategoriesIds, prefilterTagsNames, themeDisplay.getLocale());
				}

				this.assetEntries = results;
				
				this.applyTemplateBehaviors(configuration);
				
				JSONArray jsonResponse = this.constructJSONSelection(request, configuration);
				
				// Recuperation de l'élément d'écriture de la réponse
				PrintWriter writer = response.getWriter();
				writer.print(jsonResponse.toString());
			}
			
		} catch (Exception e) {
			_log.error(e);
		}
		super.serveResource(request, response);
	}
	
	/**
	 * Applique un comportement de filtrage suplémentaire selon le template 
	 * de formulaire configuré
	 */
	private void applyTemplateBehaviors(DynamicSearchAssetConfiguration configuration) {
		
		String searchForm = configuration.searchForm();

		// Comportement(s) : Plateforme-Citoyenne
		if (Constants.SEARCH_FORM_PLACIT.equals(searchForm)) {// Parcours des résultats
			for (Iterator<AssetEntry> results = this.assetEntries.iterator(); results.hasNext(); ) {
				AssetEntry assetEntry = results.next();

				String assetClassName = assetEntry.getClassName();

				// Retirer les événements n'appartenant pas à Placit via le tag dédié
				if (assetClassName.equals(Event.class.getName())) {
					List<String> assetTags = Arrays.asList(assetEntry.getTagNames());

					if (!assetTags.contains(Constants.PLACIT_TAG)) {
						results.remove();
					}
				}

				// Retirer les vidéos n'appartenant pas à Placit via le tag dédié
				if (assetClassName.equals(Video.class.getName())) {
					List<String> assetTags = Arrays.asList(assetEntry.getTagNames());

					if (!assetTags.contains(Constants.PLACIT_TAG)) {
						results.remove();
					}
				}

			}
		}
	}
	
	/**
	 * Retourne un objet JSON contenant l'ensemble des entités voulues et valide
	 * un atribut "isMarkeable" à afficher sur la map en front
	 * @return JSONObject sous la forme :
	 * 		{
	 * 			"projects" : 
	 * 				[
     * 					{"id" : 0000,
	 * 					"title" : "blablabla"
	 * 					...},
	 *	  				{...}
	 * 				],
	 * 			"participations" :
	 * 				[{...}],
	 * 			"events" :
	 * 				[{...}],
	 * 		}
	 * @throws  PortalException
	 */
	@SuppressWarnings("JavaDoc")
	private JSONArray constructJSONSelection(ResourceRequest request, DynamicSearchAssetConfiguration configuration) throws PortalException {
		
		// Récupération du contexte de la requète
		String publikUserId = this.getPublikID(request);
		
		// Initialisation du JSON de réponse
		JSONArray jsonResponse = JSONFactoryUtil.createJSONArray();

		JSONObject jsonTotalResult = JSONFactoryUtil.createJSONObject();
		jsonTotalResult.put("totalResult", this.totalResult);
		jsonResponse.put(jsonTotalResult);
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale locale = themeDisplay.getLocale();
		String configAffichage = configuration.searchForm();
		
		// Parcours des résultats
		for (AssetEntry assetEntry : this.assetEntries) {
			
			String assetClassName = assetEntry.getClassName();

			int descriptionMaxLength = configAffichage.equals("strasbourg")?100:-1;
			
			// Récupération du JSON de l'entité selon le type d'assetEntry
			// Note : impossibilité d'utilisé un switch case pour cause d'utilisation de non-constante
			// TODO : Trouver comment contrer l'archaïssité de java sur le sujet des constantes sur les switch

			// AssetEntry : Événement
			if (assetClassName.equals(Event.class.getName())) {

				Event event = EventLocalServiceUtil.getEvent(assetEntry.getClassPK());
				JSONObject jsonEvent = JSONSearchHelper.createEventSearchJson(event, locale, themeDisplay, publikUserId, configAffichage, descriptionMaxLength);
				jsonResponse.put(jsonEvent);
			}
			
			// AssetEntry : Projet
			else if (assetClassName.equals(Project.class.getName())) {
				Project project = ProjectLocalServiceUtil.getProject(assetEntry.getClassPK());
				JSONObject jsonProject = JSONSearchHelper.createProjectSearchJson(project, themeDisplay, configAffichage, descriptionMaxLength);
				jsonResponse.put(jsonProject);
			}

			// AssetEntry : Participation
			else if (assetClassName.equals(Participation.class.getName())) {
				Participation participation = ParticipationLocalServiceUtil.getParticipation(assetEntry.getClassPK());
				JSONObject jsonParticipation = JSONSearchHelper.createParticipationSearchJson(participation, locale, themeDisplay, configAffichage);
				jsonResponse.put(jsonParticipation);
			}

			// AssetEntry : Vidéo
			else if (assetClassName.equals(Video.class.getName())) {
				Video video = VideoLocalServiceUtil.getVideo(assetEntry.getClassPK());
				JSONObject jsonVideo = JSONSearchHelper.createVideoSearchJson(video, locale, themeDisplay, configAffichage);
				jsonResponse.put(jsonVideo);
			}

			// AssetEntry : Pétition
			else if (assetClassName.equals(Petition.class.getName())) {
				Petition petition = PetitionLocalServiceUtil.fetchPetition(assetEntry.getClassPK());
				JSONObject jsonPetition = JSONSearchHelper.createPetitionSearchJson(petition, themeDisplay, configAffichage);
				jsonResponse.put(jsonPetition);
			}
			
			// AssetEntry : Budget Participatif (Projet citoyen)
			else if (assetClassName.equals(BudgetParticipatif.class.getName())) {
				BudgetParticipatif bp = BudgetParticipatifLocalServiceUtil.fetchBudgetParticipatif(assetEntry.getClassPK());
				JSONObject jsonBP = JSONSearchHelper.createBudgetParticipatifSearchJson(bp, locale, themeDisplay, configAffichage);
				jsonResponse.put(jsonBP);
			}
			
			// AssetEntry : Initiatives
			else if (assetClassName.equals(Initiative.class.getName())) {
				Initiative initiative = InitiativeLocalServiceUtil.fetchInitiative(assetEntry.getClassPK());
				JSONObject jsonInitiative = JSONSearchHelper.createInitiativeSearchJson(initiative, themeDisplay, configAffichage);
				jsonResponse.put(jsonInitiative);
			}
			
			// AssetEntry : JournalArticle avant identification d'un potentiel Article
			else if (assetClassName.equals(JournalArticle.class.getName())) {
				String[] tagNames = assetEntry.getTagNames();

				// Outil permettant de passer outre les accents lors d'un test d'équivalence
				Collator collator = Collator.getInstance();
				collator.setStrength(Collator.NO_DECOMPOSITION);
				boolean containsNewsTagName = false;

				// Vérification de la véracité d'un JournalArticle de type actualité pour placit
				for (String nameToTest : tagNames) {
					if (collator.compare(nameToTest, Constants.NEWS_TAG_NAME) == 0 || collator.compare(nameToTest, Constants.ARTICLES_TAG_NAME) == 0) {
						containsNewsTagName = true;
					}
				}

				// Si tel est le cas
				if (containsNewsTagName || !configAffichage.equals(Constants.SEARCH_FORM_PLACIT)) {
					try {
						JSONObject jsonArticle = JSONSearchHelper.createJournalArticleSearchJson(assetEntry, locale, themeDisplay, configAffichage, descriptionMaxLength);
						jsonResponse.put(jsonArticle);
					}catch (Exception e){
						_log.error(e);
					}
				}
			}

			// AssetEntry : Official
			else if (assetClassName.equals(Official.class.getName())) {
				Official official = OfficialLocalServiceUtil.getOfficial(assetEntry.getClassPK());
				JSONObject jsonOfficial = JSONSearchHelper.createOfficialSearchJson(official, locale, themeDisplay, configAffichage);
				jsonResponse.put(jsonOfficial);
			}

			// AssetEntry : Edition
			else if (assetClassName.equals(Edition.class.getName())) {
				Edition edition = EditionLocalServiceUtil.getEdition(assetEntry.getClassPK());
				JSONObject jsonEdition = JSONSearchHelper.createEditionSearchJson(edition, locale, themeDisplay, configAffichage, descriptionMaxLength);
				jsonResponse.put(jsonEdition);
			}

			// AssetEntry : Manifestation
			else if (assetClassName.equals(Manifestation.class.getName())) {
				Manifestation manifestation = ManifestationLocalServiceUtil.getManifestation(assetEntry.getClassPK());
				JSONObject jsonManifestation = JSONSearchHelper.createManifestationSearchJson(manifestation, locale, themeDisplay, configAffichage, descriptionMaxLength);
				jsonResponse.put(jsonManifestation);
			}

			// AssetEntry : EditionGallery
			else if (assetClassName.equals(EditionGallery.class.getName())) {
				EditionGallery editionGallery = EditionGalleryLocalServiceUtil.getEditionGallery(assetEntry.getClassPK());
				JSONObject jsonEditionGallery = JSONSearchHelper.createEditionGallerySearchJson(editionGallery, locale, themeDisplay, configAffichage, descriptionMaxLength);
				jsonResponse.put(jsonEditionGallery);
			}

			// AssetEntry : Place
			else if (assetClassName.equals(Place.class.getName())) {
				Place place = PlaceLocalServiceUtil.getPlace(assetEntry.getClassPK());
				JSONObject jsonPlace = JSONSearchHelper.createPlaceSearchJson(place, locale, themeDisplay, configAffichage);
				jsonResponse.put(jsonPlace);
			}

			// AssetEntry : ActivityCourse
			else if (assetClassName.equals(ActivityCourse.class.getName())) {
				ActivityCourse activityCourse = ActivityCourseLocalServiceUtil.getActivityCourse(assetEntry.getClassPK());
				JSONObject jsonActivityCourse = JSONSearchHelper.createActivityCourseSearchJson(activityCourse, locale, themeDisplay, configAffichage);
				jsonResponse.put(jsonActivityCourse);
			}

			// AssetEntry : Activity
			else if (assetClassName.equals(Activity.class.getName())) {
				Activity activity = ActivityLocalServiceUtil.getActivity(assetEntry.getClassPK());
				JSONObject jsonActivity = JSONSearchHelper.createActivitySearchJson(activity, locale, themeDisplay, configAffichage, descriptionMaxLength);
				jsonResponse.put(jsonActivity);
			}
		}
		
		return  jsonResponse;
	}

	/**
	 * Retourne la liste des class names configurés recherchable
	 */
	public String getConfiguredClassNames(DynamicSearchAssetConfiguration configuration) {
		String classNames = configuration.assetClassNames();
		if (configuration.searchNews()) {
			if (Validator.isNotNull(classNames)) {
				classNames += ",";
			}
			classNames += JournalArticle.class.getName();
		}
		if (configuration.searchDocument()) {
			if (Validator.isNotNull(classNames)) {
				classNames += ",";
			}
			classNames += DLFileEntry.class.getName();
		}
		return classNames;
	}

	/**
	 * Retourne la liste des class names configurés recherchable
	 */
	public List<String> getConfiguredClassNamesList(DynamicSearchAssetConfiguration configuration) {
		List<String> classNames = new ArrayList<String>(Arrays.asList(this.getConfiguredClassNames(configuration).split(",")));
		return classNames;
	}
	
	/**
	 * Récupération du publik ID avec la session
	 * @return L'id publik de l'utilisateur courant
	 */
	private String getPublikID(PortletRequest request) {
		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
		
		return SessionParamUtil.getString(originalRequest, "publik_internal_id");
	}
	
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
}