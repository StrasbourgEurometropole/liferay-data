package eu.strasbourg.portlet.dynamic_search_asset;

import eu.strasbourg.portlet.dynamic_search_asset.configuration.DynamicSearchAssetConfiguration;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.ParticipationLocalServiceUtil;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;
import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.service.VideoLocalServiceUtil;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import com.liferay.asset.kernel.model.AssetEntry;
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
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

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
	
	private static final String PLACIT_TAG = "participer";
	private static final String SEARCH_FORM_PLACIT = "placit";
	private static final String ATTRIBUTE_CLASSNAME = "className";
	private static final String ATTRIBUTE_LINK = "link";
	private static final String ATTRIBUTE_TITLE = "title";
	private static final String ATTRIBUTE_CHAPO = "chapo";
	private static final String ATTRIBUTE_IMAGE_URL = "imageURL";
	private static final String ATTRIBUTE_PUBLISH_DATE = "publishDate";
	private static final String ATTRIBUTE_IS_USER_PARTICIPATE = "isUserPart";
	private static final String DETAIL_PARTICIPATION_URL = "detail-participation/-/entity/id/";
	private static final String DETAIL_PETITION_URL = "detail-petition/-/entity/id/";
	private static final String DETAIL_EVENT_URL = "detail-evenement/-/entity/id/";
	private static final String DETAIL_VIDEO_URL = "detail-video/-/entity/id/";
	private static final String NEWS_TAG_NAME = "actualite";
	
	private DynamicSearchAssetConfiguration configuration;
	
	private List<AssetEntry> assetEntries;
	
	/**
	 * Initialisation de la vue
	 */
	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		try {
			// Recuperation du contexte de la requete
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			this.configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(DynamicSearchAssetConfiguration.class);
			
			// Recuperation et attribution des informations de l'utilisateur
			String publikUserId = getPublikID(request);
			request.setAttribute("publikUserId", publikUserId);
			
			// Recuperation et attribution de l'URL de base du site
			String homeUrl = getHomeURL(request);
			request.setAttribute("homeURL", homeUrl);
			
			// Recuperation du formulaire configuré
			String searchForm = configuration.searchForm();
			request.setAttribute("searchForm", searchForm);
			
			// Recuperation des classes demandees
			List<String> classNames = this.getConfiguredClassNames();
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
	 * 		comme dans les modules BO pour les MVCActionCommand, toutefois il faudrait alors mutualiser
	 * 		les données dans une classe externe pour agir sur les même résultats 
	 */
	@Override
	public void serveResource(ResourceRequest request, ResourceResponse response)
			throws IOException, PortletException {
		try {
			// Recuperation du contexte de la requete
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = new Long(themeDisplay.getLayout().getGroupId());
			String resourceID = request.getResourceID();	
			
			// ---------------------------------------------------------------
			// -------- REQUETE : Effectuer une recherche --------------------
			// ---------------------------------------------------------------
			if (resourceID.equals("searchSubmit")) {
				
				// Recuperation du searchContext
				HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(request);
				SearchContext searchContext = SearchContextFactory.getInstance(servletRequest);
				
				// Recuperation des classNames selectionnes demandes par l'utilisateur
				String selectedClassNames = ParamUtil.getString(request, "selectedClassNames");
				String[] classNames = selectedClassNames.split(",");
				
				// Recuperation des mots clefs demandes par l'utilisateur
				String keywords = ParamUtil.getString(request, "keywords");
				
				// Inclusion ou non du scope global
				boolean globalScope = this.configuration.globalScope();
				long globalGroupId = themeDisplay.getCompanyGroupId();
				
				// Recuperation du nombre de resultat max demande
				int maxResults = (int) this.configuration.delta();
				
				// Recuperation de la configuration du prefiltre par date de la configuration
				boolean useDatePrefilter = this.configuration.dateField();
				long dateRangeFrom = (int) this.configuration.dateRangeFrom();
				long dateRangeTo = (int) this.configuration.dateRangeTo();
				LocalDate fromDate = LocalDate.now().plusDays(dateRangeFrom);
				LocalDate toDate = LocalDate.now().plusDays(dateRangeTo);
				
				// Recuperation de la configuration des prefiltre sur les categories
				String prefilterCategoriesIdsString = this.configuration.prefilterCategoriesIds();
				List<Long[]> prefilterCategoriesIds = new ArrayList<Long[]>();
				for (String prefilterCategoriesIdsGroupByVocabulary : prefilterCategoriesIdsString.split(";")) {
					Long[] prefilterCategoriesIdsForVocabulary = ArrayUtil
							.toLongArray(StringUtil.split(prefilterCategoriesIdsGroupByVocabulary, ",", 0));
					prefilterCategoriesIds.add(prefilterCategoriesIdsForVocabulary);
				}
				
				// Recuperation de la configuration des prefiltre sur les etiquettes
				String prefilterTagsNamesString = this.configuration.prefilterTagsNames();
				String[] prefilterTagsNames = StringUtil.split(prefilterTagsNamesString);
				
				// Recherche
				Hits hits = SearchHelper.getGlobalSearchHits(searchContext, classNames, groupId, globalGroupId, globalScope,
						keywords, useDatePrefilter, "publishDate_sortable", fromDate, toDate, new ArrayList<Long[]>(),
						prefilterCategoriesIds, prefilterTagsNames, themeDisplay.getLocale(), 0,
						maxResults, "score", false);
				List<AssetEntry> results = new ArrayList<AssetEntry>();
				
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
						if (entry != null) {
							results.add(entry);
						}
					}
				}

				this.assetEntries = results;
				
				this.applyTemplateBehaviors(request);
				
				JSONArray jsonResponse = this.constructJSONSelection(request);
				
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
	 * @throws PortalException 
	 */
	private void applyTemplateBehaviors(ResourceRequest request) throws PortalException {
		
		String searchForm = configuration.searchForm();
		
		switch (searchForm) {
			
			/**
			 * Comportement(s) : Plateforme-Citoyenne
			 */
			case SEARCH_FORM_PLACIT :
				
				// Parcours des résultats
				for (Iterator <AssetEntry> results = this.assetEntries.iterator(); results.hasNext();) {
					AssetEntry assetEntry = results.next();
					
					String assetClassName = assetEntry.getClassName();
					
					// Retirer les événements n'appartenant pas à Placit via le tag dédié
					if (assetClassName.equals(Event.class.getName())) {
						List<String> assetTags =  Arrays.asList(assetEntry.getTagNames());
						
						if (!assetTags.contains(PLACIT_TAG)) {
							results.remove();
						}
					}
					
					// Retirer les vidéos n'appartenant pas à Placit via le tag dédié
					if (assetClassName.equals(Video.class.getName())) {
						List<String> assetTags =  Arrays.asList(assetEntry.getTagNames());
						
						if (!assetTags.contains(PLACIT_TAG)) {
							results.remove();
						}
					}
					
				}
				break;
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
	 * @throws PortalException 
	 * @throws DocumentException 
	 */
	private JSONArray constructJSONSelection(ResourceRequest request) throws PortalException, DocumentException {
		
		// Récupération du contexte de la requète
		String publikUserId = this.getPublikID(request);
		
		// Initialisation du JSON de réponse
		JSONArray jsonResponse = JSONFactoryUtil.createJSONArray();
		
		// Parcours des résultats
		for (AssetEntry assetEntry : this.assetEntries) {
			
			String assetClassName = assetEntry.getClassName();
			
			// Récupération du JSON de l'entité selon le type d'assetEntry
			// Note : impossibilité d'utilisé un switch case pour cause d'utilisation de non-constante
			// TODO : Trouver comment contrer l'archaïssité de java sur le sujet des constantes sur les switch
			
			/**
			 * AssetEntry : Événement
			 */
			if (assetClassName.equals(Event.class.getName())) {
				Event event = EventLocalServiceUtil.getEvent(assetEntry.getClassPK());
				
				JSONObject jsonEvent = event.toJSON();
				
				jsonEvent.put(
					ATTRIBUTE_CLASSNAME,
					Event.class.getName()
				);
				jsonEvent.put(
					ATTRIBUTE_LINK,
					this.getHomeURL(request) + DETAIL_EVENT_URL + event.getEventId()
				);
				jsonEvent.put(
					ATTRIBUTE_IS_USER_PARTICIPATE, 
					publikUserId != "" ? event.isUserParticipate(publikUserId) : false
				);
			}
			
			/**
			 * AssetEntry : Projet
			 */
			else if (assetClassName.equals(Project.class.getName())) {
				Project project = ProjectLocalServiceUtil.getProject(assetEntry.getClassPK());
				
				JSONObject jsonProject = project.toJSON();
				
				jsonProject.put(
					ATTRIBUTE_CLASSNAME,
					Project.class.getName()
				);
				jsonProject.put(
					ATTRIBUTE_LINK, 
					this.getHomeURL(request) + project.getDetailURL()
				);
				
				jsonResponse.put(jsonProject);
			}
			
			/**
			 * AssetEntry : Participation
			 */
			else if (assetClassName.equals(Participation.class.getName())) {
				Participation participation = ParticipationLocalServiceUtil.getParticipation(assetEntry.getClassPK());
				
				JSONObject jsonParticipation = participation.toJSON();
				
				jsonParticipation.put(
					ATTRIBUTE_CLASSNAME,
					Participation.class.getName()
				);
				jsonParticipation.put(
					ATTRIBUTE_LINK,
					this.getHomeURL(request) + DETAIL_PARTICIPATION_URL + participation.getParticipationId()
				);
				
				jsonResponse.put(jsonParticipation);
			}
			
			/**
			 * AssetEntry : Vidéo
			 */
			else if (assetClassName.equals(Video.class.getName())) {
				Video video = VideoLocalServiceUtil.getVideo(assetEntry.getClassPK());
				
				JSONObject jsonVideo = video.toJSON();
				
				jsonVideo.put(
					ATTRIBUTE_CLASSNAME,
					Video.class.getName()
				);
				jsonVideo.put(
					ATTRIBUTE_LINK,
					this.getHomeURL(request) + DETAIL_VIDEO_URL + video.getVideoId()
				);
				
				jsonResponse.put(jsonVideo);
			}
			
			/**
			 * AssetEntry : Pétition
			 */
			else if (assetClassName.equals(Petition.class.getName())) {
				Petition petition = PetitionLocalServiceUtil.fetchPetition(assetEntry.getClassPK());
				
				// TODO : Mettre en place les éléments de retranscription JSON d'une pétition
				/**
				JSONObject jsonPetition = petition.toJSON();
				
				jsonPetition.put(
					ATTRIBUTE_CLASSNAME,
					Petition.class.getName()
				);
				jsonPetition.put(
					ATTRIBUTE_LINK,
					this.getHomeURL(request) + DETAIL_PETITION_URL + petition.getPetitionId()
				);
				
				jsonResponse.put(jsonPetition);
				*/
			}
			
			/**
			 * AssetEntry : JournalArticle avant identification d'un potentiel Article
			 */
			else if (assetClassName.equals(JournalArticle.class.getName())) {
				
				List<String> tagNames = Arrays.asList(assetEntry.getTagNames());
				
				// Outil permettant de passer outre les accents lors d'un test d'équivalence
				Collator collator = Collator.getInstance();
				collator.setStrength(Collator.NO_DECOMPOSITION);
				Boolean containsNewsTagName = false;
				
				// Vérification de la véracité d'un JournalArticle de type actualité
				for (String nameToTest : tagNames) {
					if (collator.compare(nameToTest, NEWS_TAG_NAME) == 0) {
						containsNewsTagName = true;
					}
				}
				
				// Si tel est le cas
				if (containsNewsTagName) {
					JournalArticle journalArticle = JournalArticleServiceUtil.getLatestArticle(assetEntry.getClassPK());
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
					
					// Récupération du contenu dynamique en XML de l'article
					com.liferay.portal.kernel.xml.Document docXML = SAXReaderUtil.read(journalArticle.getContentByLocale("fr_FR"));
					
					JSONObject jsonArticle = JSONFactoryUtil.createJSONObject();
					
					jsonArticle.put(
						ATTRIBUTE_CLASSNAME,
						JournalArticle.class.getName()
					);
					jsonArticle.put(
						ATTRIBUTE_LINK,
						this.getHomeURL(request) + "-/" + journalArticle.getUrlTitle()
					);
					jsonArticle.put(
						ATTRIBUTE_TITLE,
						docXML.valueOf("//dynamic-element[@name='title']/dynamic-content/text()")
					);
					jsonArticle.put(
						ATTRIBUTE_CHAPO,
						docXML.valueOf("//dynamic-element[@name='chapo']/dynamic-content/text()")
					);
					jsonArticle.put(
						ATTRIBUTE_PUBLISH_DATE,
						dateFormat.format(journalArticle.getDisplayDate())
					);
					jsonArticle.put(
						ATTRIBUTE_IMAGE_URL,
						docXML.valueOf("//dynamic-element[@name='thumbnail']/dynamic-content/text()")
					);
					
					jsonResponse.put(jsonArticle);
					
				} else {
					continue;
				}
			}
			
		}
		
		return  jsonResponse;
	}
	
	/**
	 * Retourne la liste des class names configurés recherchable
	 */
	public List<String> getConfiguredClassNames() {
		List<String> classNames = new ArrayList<String>();
		for (String className : this.configuration.assetClassNames().split(",")) {
			if (Validator.isNotNull(className)) {
				classNames.add(className);
			}
		}
		if (this.configuration.searchNews()) {
			classNames.add(JournalArticle.class.getName());
		}
		if (this.configuration.searchDocument()) {
			classNames.add(DLFileEntry.class.getName());
		}
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
	
	/**
	 * Récupération de l'URL de base du site pour le lien vers les pages des entités
	 */
	private String getHomeURL(PortletRequest request) {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		if (themeDisplay.getScopeGroup().getPublicLayoutSet().getVirtualHostname().isEmpty()
				|| themeDisplay.getScopeGroup().isStagingGroup()) {
			try {
				return "/web" + themeDisplay.getLayout().getGroup().getFriendlyURL() + "/";
			} catch (PortalException e) {
				return "/web/";
			}
		} else {
			return "/";
		}
	}
	
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
}