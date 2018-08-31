package eu.strasbourg.portlet.map_search_asset;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.ParticipationLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
		"javax.portlet.display-name=Carte recherche d'asset",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/map-search-asset-view.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.MAP_SEARCH_ASSET_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MapSearchAssetWebPortlet extends MVCPortlet {
	
	private static final String CITY_NAME = "Strasbourg";
	private static final String ATTRIBUTE_IS_MARKEABLE = "isMarkeable";
	private static final String ATTRIBUTE_LINK = "link";
	private static final String ATTRIBUTE_IS_USER_PARTICIPATE = "isUserPart";
	private static final String DETAIL_PARTICIPATION_URL = "detail-participation/-/entity/id/";
	private static final String DETAIL_EVENT_URL = "detail-evenement/-/entity/id/";
	private static final String JSON_OBJECT_PROJECTS = "projects";
	private static final String JSON_OBJECT_PARTICIPATIONS = "participations";
	private static final String JSON_OBJECT_EVENTS = "events";
	private static final String RESSSOURCE_CHANGE_DISTRICT = "changeDistrictSelection";
	private static final String RESSSOURCE_CHANGE_PROJECTS = "changeProjectsSelection";
	private static final String RESSSOURCE_CHANGE_PARTICIPATION = "changeParticipationsSelection";
	private static final String RESSSOURCE_CHANGE_SUB_ENTITIES = "changeSubEntitiesSelection";
	
	// Listes des IDs des entités séléctionnées en front
	private long selectedDistrictCategoryId;
	private List<Long> selectedProjectIds;
	private List<Long> selectedParticipationIds;
	private List<Long> selectedEventIds;
	
	// Listes des entités à afficher en front
	private List<AssetCategory> districtCategories;
	private List<Project> projects;
	private List<Participation> participations;
	private List<Event> events;
	
	/**
	 * Initialisation de la vue
	 */
	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		// Recuperation du contexte de la requete
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = new Long(themeDisplay.getLayout().getGroupId());
		
		// Recuperation et attribution des informations de l'utilisateur
		String publikUserId = getPublikID(request);
		request.setAttribute("publikUserId", publikUserId);
		
		// Recuperation et attribution de l'URL de base du site
		String homeUrl = getHomeURL(request);
		request.setAttribute("homeURL", homeUrl);
		
		// Recuperation et attribution des quartiers
		this.districtCategories = AssetVocabularyHelper.getAllDistrictsFromCity(CITY_NAME);
		request.setAttribute("districtCategories", this.districtCategories);
		
		// Recuperation et attribution des projets
		this.projects = ProjectLocalServiceUtil.getPublishedByGroupId(groupId);
		request.setAttribute("projects", this.projects);
		
		// Initialisation des variables tempons
		this.selectedDistrictCategoryId = -1;
		this.selectedProjectIds = new ArrayList<Long>();
		this.selectedParticipationIds = new ArrayList<Long>();
		this.selectedEventIds = new ArrayList<Long>();
		this.participations = new ArrayList<Participation>();
		this.events = new ArrayList<Event>();
		
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
			// -------- REQUETE : Nouvelle sélection de quartier  ------------
			// ---------------------------------------------------------------
			if (resourceID.equals(RESSSOURCE_CHANGE_DISTRICT)) { 
				
				this.selectedDistrictCategoryId = ParamUtil.getLong(request, "selectedDistrictId");
				
				// Réinitialisation des éléments enfants
				this.participations.clear();
				this.events.clear();
				
				if (this.selectedDistrictCategoryId > 0) {
					this.projects = ProjectLocalServiceUtil.findByCategoryIds(new long[] {this.selectedDistrictCategoryId});
				} else {
					this.projects = ProjectLocalServiceUtil.getPublishedByGroupId(groupId);
				}
				
				// Récupération des éléments "enfants" en adaptant les séléctions
				List<Long> tempSelectedProjects = new ArrayList<Long>();
				for (Project project : this.projects) {
					if (this.selectedProjectIds.contains(project.getProjectId())) {
						tempSelectedProjects.add(project.getProjectId());
					}
				}
				this.selectedProjectIds = tempSelectedProjects;
				
				for (Long projectId : this.selectedProjectIds) {
					Project project = ProjectLocalServiceUtil.fetchProject(projectId);
					this.participations.addAll(project.getParticipations());
				}
				
				List<Long> tempSelectedParticipations = new ArrayList<Long>();
				for (Participation participation : this.participations) {
					if (this.selectedParticipationIds.contains(participation.getParticipationId())) {
						tempSelectedParticipations.add(participation.getParticipationId());
					}
				}
				this.selectedParticipationIds = tempSelectedParticipations;
				
				for (Long participationId : this.selectedParticipationIds) {
					Participation participation = ParticipationLocalServiceUtil.fetchParticipation(participationId);
					this.events.addAll(participation.getEvents());
				}
				
				List<Long> tempSelectedEvents = new ArrayList<Long>();
				for (Event event : this.events) {
					if (this.selectedEventIds.contains(event.getEventId())) 
						tempSelectedEvents.add(event.getEventId());
				}
				this.selectedEventIds = tempSelectedEvents;
				
				
			} 
			// ---------------------------------------------------------------
			// -------- REQUETE : Nouvelle sélection de projets  -------------
			// ---------------------------------------------------------------
			else if (resourceID.equals(RESSSOURCE_CHANGE_PROJECTS)) { 
				
				String requestSelectedProjectIds = ParamUtil.getString(request, "selectedProjectIds");
				
				// Réinitialisation des éléments concernés
				this.selectedProjectIds.clear();
				this.participations.clear();
				this.events.clear();
				
				if (requestSelectedProjectIds != "") {
					
					// Parcours des Ids
					for (String requestSelectedProjectId : requestSelectedProjectIds.split(",")) {
						long projectId = Long.parseLong((requestSelectedProjectId));
						
						Project project = ProjectLocalServiceUtil.fetchProject(projectId);
						
						if (project != null) {
							this.selectedProjectIds.add(projectId);
							this.participations.addAll(project.getParticipations());
						}
					}
				
				
					// Récupération des éléments "enfants" en adaptant les séléctions
					List<Long> tempSelectedParticipations = new ArrayList<Long>();
					for (Participation participation : this.participations) {
						if (this.selectedParticipationIds.contains(participation.getParticipationId())) {
							tempSelectedParticipations.add(participation.getParticipationId());
						}
					}
					this.selectedParticipationIds = tempSelectedParticipations;
					
					for (Long participationId : this.selectedParticipationIds) {
						Participation participation = ParticipationLocalServiceUtil.fetchParticipation(participationId);
						this.events.addAll(participation.getEvents());
					}
					
					List<Long> tempSelectedEvents = new ArrayList<Long>();
					for (Event event : this.events) {
						if (this.selectedEventIds.contains(event.getEventId())) 
							tempSelectedEvents.add(event.getEventId());
					}
					this.selectedEventIds = tempSelectedEvents;
				
				} else {
					this.selectedParticipationIds.clear();
					this.selectedEventIds.clear();
				}
				
			}
			// ---------------------------------------------------------------
			// -------- REQUETE : Nouvelle sélection de participations  ------
			// ---------------------------------------------------------------
			else if (resourceID.equals(RESSSOURCE_CHANGE_PARTICIPATION)) {
				
				String requestSelectedParticipationIds = ParamUtil.getString(request, "selectedParticipationIds");
				
				// Réinitialisation des éléments concernés
				this.selectedParticipationIds.clear();
				this.events.clear();
				
				if (requestSelectedParticipationIds != "") {
					
					// Parcours des Ids
					for (String requestSelectedParticipationId : requestSelectedParticipationIds.split(",")) {
						long participationId = Long.parseLong((requestSelectedParticipationId));
						
						Participation participation = ParticipationLocalServiceUtil.fetchParticipation(participationId);
						
						if (participation != null) {
							this.selectedParticipationIds.add(participationId);
							this.events.addAll(participation.getEvents());
						}
					}
					
					// Récupération des éléments "enfants" en adaptant les séléctions
					List<Long> tempSelectedEvents = new ArrayList<Long>();
					for (Event event : this.events) {
						if (this.selectedEventIds.contains(event.getEventId())) 
							tempSelectedEvents.add(event.getEventId());
					}
					this.selectedEventIds = tempSelectedEvents;
					
				} else {
					this.selectedEventIds.clear();
				}
				
			} 
			// ---------------------------------------------------------------
			// -------- REQUETE : Nouvelle sélection d'entités dépendantes des participations
			// ---------------------------------------------------------------
			else if (resourceID.equals(RESSSOURCE_CHANGE_SUB_ENTITIES)) {
				
				String requestSelectedEventIds = ParamUtil.getString(request, "selectedEventIds");
				
				// Réinitialisation des éléments concernés
				this.selectedEventIds.clear();
				
				if (requestSelectedEventIds != "") {
					
					// Parcours des Ids
					for (String requestSelectedEventId : requestSelectedEventIds.split(",")) {
						long eventId = Long.parseLong((requestSelectedEventId));
						
						Event event = EventLocalServiceUtil.fetchEvent(eventId);
						
						if (event != null) {
							this.selectedEventIds.add(eventId);
						}
					}
					
				}
				
			}
			
			JSONObject jsonResponse = this.constructJSONSelection(request);
			
			// Recuperation de l'élément d'écriture de la réponse
			PrintWriter writer = response.getWriter();
			writer.print(jsonResponse.toString());
			
		} catch (Exception e) {
			_log.error(e);
		}
		super.serveResource(request, response);
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
	 */
	private JSONObject constructJSONSelection(ResourceRequest request) {
		JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
		
		// Gestion des projets
		JSONArray jsonProjects = JSONFactoryUtil.createJSONArray();
		for (Project project : this.projects) {
			// Récupération du JSON de l'entité
			JSONObject jsonProject = project.toJSON();
			// Si l'entité est dans la liste de celles séléctionnées
			jsonProject.put(
					ATTRIBUTE_IS_MARKEABLE, 
					this.selectedProjectIds.contains(project.getProjectId()) ? true : false
			);
			jsonProject.put(
					ATTRIBUTE_LINK, 
					this.getHomeURL(request) + "/" + project.getDetailURL()
			);
			// Ajout de l'entité dans le tableau de résutats correspondant
			jsonProjects.put(jsonProject);
		}
		jsonResponse.put(JSON_OBJECT_PROJECTS, jsonProjects);
		
		// Gestion des participations
		JSONArray jsonParticipations = JSONFactoryUtil.createJSONArray();
		for (Participation participation : this.participations) {
			JSONObject jsonParticipation = participation.toJSON();
			jsonParticipation.put(
					ATTRIBUTE_IS_MARKEABLE, 
					this.selectedParticipationIds.contains(participation.getParticipationId()) ? true : false
			);
			jsonParticipation.put(
					ATTRIBUTE_LINK, 
					this.getHomeURL(request) + DETAIL_PARTICIPATION_URL + participation.getParticipationId()
			);
			jsonParticipations.put(jsonParticipation);
		}
		jsonResponse.put(JSON_OBJECT_PARTICIPATIONS, jsonParticipations);
		
		// Gestion des événements
		JSONArray jsonEvents = JSONFactoryUtil.createJSONArray();
		for (Event event : this.events) {
			JSONObject jsonEvent = event.toJSON();
			String publikUserId = this.getPublikID(request);
			jsonEvent.put(
					ATTRIBUTE_IS_MARKEABLE, 
					this.selectedEventIds.contains(event.getEventId()) ? true : false
			);
			jsonEvent.put(
					ATTRIBUTE_LINK, 
					this.getHomeURL(request) + DETAIL_EVENT_URL + event.getEventId()
			);
			jsonEvent.put(
					ATTRIBUTE_IS_USER_PARTICIPATE, 
					publikUserId != "" ? event.isUserParticipate(publikUserId) : false
			);
			jsonEvents.put(jsonEvent);
		}
		jsonResponse.put(JSON_OBJECT_EVENTS, jsonEvents);
		
		return  jsonResponse;
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