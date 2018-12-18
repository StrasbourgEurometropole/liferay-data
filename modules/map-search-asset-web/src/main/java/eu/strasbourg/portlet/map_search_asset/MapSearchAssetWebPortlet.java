package eu.strasbourg.portlet.map_search_asset;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
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
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.service.project.service.ParticipationLocalServiceUtil;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
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
	
	// Constante de la ville ou chercher les quartiers
	private static final String CITY_NAME = "Strasbourg";
	
	// Constantes des liens vers les détails
	private static final String DETAIL_PARTICIPATION_URL = "detail-participation/-/entity/id/";
	private static final String DETAIL_PETITION_URL = "detail-petition/-/entity/id/";
	private static final String DETAIL_BUDGET_PARTICIPATIF_URL = "detail-budget-participatif/-/entity/id/";
	private static final String DETAIL_INITIATIVE_URL = "detail-inititative/-/entity/id/";
	private static final String DETAIL_EVENT_URL = "detail-evenement/-/entity/id/";
	
	// Constantes des objets / attributs JSON
	private static final String JSON_OBJECT_PROJECTS = "projects";
	private static final String JSON_OBJECT_PARTICIPATIONS = "participations";
	private static final String JSON_OBJECT_PETITIONS = "petitions";
	private static final String JSON_OBJECT_BUDGETS_PARTICIPATIFS = "budgets";
	private static final String JSON_OBJECT_INITIATIVE = "initiatives";
	private static final String JSON_OBJECT_EVENTS = "events";
	private static final String ATTRIBUTE_LINK = "link";
	
	// Constante des ID de requete
	private static final String RESSSOURCE_CHANGE_DISTRICT = "changeDistrictSelection";
	
	// Listes des entités à afficher en front
	private List<AssetCategory> districtCategories;
	private List<Project> projects;
	private List<Participation> participations;
	private List<Petition> petitions;
	private List<BudgetParticipatif> budgetsParticipatifs;
	private List<Initiative> initiatives;
	private List<Event> events;
	
	/**
	 * Initialisation de la vue
	 */
	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		
		// Recuperation et attribution des informations de l'utilisateur
		String publikUserId = getPublikID(request);
		request.setAttribute("publikUserId", publikUserId);
		
		// Recuperation et attribution de l'URL de base du site
		String homeUrl = getHomeURL(request);
		request.setAttribute("homeURL", homeUrl);
		
		// Recuperation et attribution des quartiers
		this.districtCategories = AssetVocabularyHelper.getAllDistrictsFromCity(CITY_NAME);
		request.setAttribute("districtCategories", this.districtCategories);
		
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
			String resourceID = request.getResourceID();			
			
			// ---------------------------------------------------------------
			// -------- REQUETE : Nouvelle sélection de quartier  ------------
			// ---------------------------------------------------------------
			if (resourceID.equals(RESSSOURCE_CHANGE_DISTRICT)) { 
				
				long selectedDistrictCategoryId = ParamUtil.getLong(request, "selectedDistrictId");
				
				this.refreshEntitiesSelectionByDistrict(request, selectedDistrictCategoryId);
				
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
	 * Met a jour la liste des entites selon le quartier demande
	 */
	private void refreshEntitiesSelectionByDistrict(PortletRequest request, long districtCategoryId) {
		
		// Recuperation du contexte de la requete
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = new Long(themeDisplay.getLayout().getGroupId());
		
		// Recuperation des entites publiees
		this.projects = ProjectLocalServiceUtil.getPublishedByGroupId(groupId);
		this.participations = ParticipationLocalServiceUtil.getPublishedByGroupId(groupId);
		this.petitions = PetitionLocalServiceUtil.getPublishedByGroupId(groupId);
		this.budgetsParticipatifs = BudgetParticipatifLocalServiceUtil.getPublishedByGroupId(groupId);
		this.initiatives = InitiativeLocalServiceUtil.getPublishedByGroupId(groupId);
		List<String> tagLabels =  new ArrayList<String>();
		tagLabels.add("participer");
		this.events = EventLocalServiceUtil.getByTagsWithOrSelection(tagLabels);
		
		if (districtCategoryId > 0) {
			
			// Inititalisation des listes filrees
			// @notes methode preferable a un retrait dans le parcours ou encore la copie  dans une
			// liste tempon pouvant provoquer des erreurs intempestives a cause des references d'objet
			// TODO : Trouver une solution propre a un retrait en parcours
			List<Project> filteredProjects = new ArrayList<Project>();
			List<Participation> filteredParticipations = new ArrayList<Participation>();
			List<Petition> filteredPetitions = new ArrayList<Petition>();
			List<BudgetParticipatif> filteredBudgetsParticipatifs = new ArrayList<BudgetParticipatif>();
			List<Initiative> filteredInitiatives = new ArrayList<Initiative>();
			List<Event> filteredEvents = new ArrayList<Event>();
			
			try {
				AssetCategory districtCategory = AssetCategoryLocalServiceUtil.getAssetCategory(districtCategoryId);
				
				for (Project project : new ArrayList<Project>(this.projects)) {
					if (project.getDistrictCategories().contains(districtCategory)) {
						filteredProjects.add(project);
					}
				}
				for (Participation participation : new ArrayList<Participation>(this.participations)) {
					if (participation.getDistrictCategories().contains(districtCategory)) {
						filteredParticipations.add(participation);
					}
				}
				for (Petition petition : new ArrayList<Petition>(this.petitions)) {
					if (petition.getDistrictCategories().contains(districtCategory)) {
						filteredPetitions.add(petition);
					}
				}
				for (BudgetParticipatif budgetPaticipatif : new ArrayList<BudgetParticipatif>(this.budgetsParticipatifs)) {
					if (budgetPaticipatif.getDistrictCategories().contains(districtCategory)) {
						filteredBudgetsParticipatifs.add(budgetPaticipatif);
					}
				}
				for (Initiative initiative : new ArrayList<Initiative>(this.initiatives)) {
					if (initiative.getDistrictCategories().contains(districtCategory)) {
						filteredInitiatives.add(initiative);
					}
				}
				for (Event event : new ArrayList<Event>(this.events)) {
					if (event.getCategories().contains(districtCategory)) {
						filteredEvents.add(event);
					}
				}
				
			} catch (PortalException e) {
				e.printStackTrace();
			}
			
			this.projects = filteredProjects;
			this.participations = filteredParticipations;
			this.petitions = filteredPetitions;
			this.budgetsParticipatifs = filteredBudgetsParticipatifs;
			this.initiatives = filteredInitiatives;
			this.events = filteredEvents;
			
		}
		
		request.setAttribute("projects", this.projects);
		request.setAttribute("participations", this.participations);
		request.setAttribute("petitions", this.petitions);
		request.setAttribute("budgets-participatifs", this.budgetsParticipatifs);
		request.setAttribute("initiatives", this.initiatives);
		request.setAttribute("events", this.events);

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
	private JSONObject constructJSONSelection(ResourceRequest request) throws PortalException {
		String publikUserId = this.getPublikID(request);
		
		JSONObject jsonResponse = JSONFactoryUtil.createJSONObject();
		
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		// Gestion des projets
		JSONArray jsonProjects = JSONFactoryUtil.createJSONArray();
		for (Project project : this.projects) {
			// Récupération du JSON de l'entité
			JSONObject jsonProject = project.toJSON(publikUserId);
			jsonProject.put(
				ATTRIBUTE_LINK, 
				this.getHomeURL(request) + project.getDetailURL()
			);
			// Ajout de l'entité dans le tableau de résutats correspondant
			jsonProjects.put(jsonProject);
		}
		jsonResponse.put(JSON_OBJECT_PROJECTS, jsonProjects);
		
		// Gestion des participations
		JSONArray jsonParticipations = JSONFactoryUtil.createJSONArray();
		for (Participation participation : this.participations) {
			JSONObject jsonParticipation = participation.toJSON(themeDisplay);
			jsonParticipation.put(
				ATTRIBUTE_LINK, 
				this.getHomeURL(request) + DETAIL_PARTICIPATION_URL + participation.getParticipationId()
			);
			jsonParticipations.put(jsonParticipation);
		}
		jsonResponse.put(JSON_OBJECT_PARTICIPATIONS, jsonParticipations);
		
		// Gestion des petitions
		JSONArray jsonPetitions = JSONFactoryUtil.createJSONArray();
		for (Petition petition : this.petitions) {
			JSONObject jsonPetition = petition.toJSON(publikUserId);
			jsonPetition.put(
				ATTRIBUTE_LINK, 
				this.getHomeURL(request) + DETAIL_PETITION_URL + petition.getPetitionId()
			);
			jsonPetitions.put(jsonPetition);
		}
		jsonResponse.put(JSON_OBJECT_PETITIONS, jsonPetitions);
		
		// Gestion des budgets participatifs
		JSONArray jsonBudgetsParticipatifs = JSONFactoryUtil.createJSONArray();
		for (BudgetParticipatif budgetParticipatif : this.budgetsParticipatifs) {
			JSONObject jsonBudgetParticipatif = budgetParticipatif.toJSON(publikUserId);
			jsonBudgetParticipatif.put(
				ATTRIBUTE_LINK, 
				this.getHomeURL(request) + DETAIL_BUDGET_PARTICIPATIF_URL + budgetParticipatif.getBudgetParticipatifId()
			);
			jsonBudgetsParticipatifs.put(jsonBudgetParticipatif);
		}
		jsonResponse.put(JSON_OBJECT_BUDGETS_PARTICIPATIFS, jsonBudgetsParticipatifs);
				
		// Gestion des initiatives
		JSONArray jsonInitiatives = JSONFactoryUtil.createJSONArray();
		for (Initiative initiative : this.initiatives) {
			JSONObject jsonInitiative = initiative.toJSON();
			jsonInitiative.put(
				ATTRIBUTE_LINK, 
				this.getHomeURL(request) + DETAIL_INITIATIVE_URL + initiative.getInitiativeId()
			);
			jsonInitiatives.put(jsonInitiative);
		}
		jsonResponse.put(JSON_OBJECT_INITIATIVE, jsonInitiatives);
		
		// Gestion des événements
		JSONArray jsonEvents = JSONFactoryUtil.createJSONArray();
		for (Event event : this.events) {
			JSONObject jsonEvent = event.toJSON(publikUserId);
			jsonEvent.put(
				ATTRIBUTE_LINK, 
				this.getHomeURL(request) + DETAIL_EVENT_URL + event.getEventId()
			);
			jsonEvents.put(jsonEvent);
		}
		jsonResponse.put(JSON_OBJECT_EVENTS, jsonEvents);
		
		return  jsonResponse;
	}
	
	/**
	 * Fusion de deux listes sans obtenir de doublon
	 * @param listA La liste contenante
	 * @param listB La liste à ajouter
	 * @return La liste mergé sans doublon
	 */
	public <T> List<T> mergeLists(List<T> listA, List<T> listB) {
		for (T object : listB){
		   if (!listA.contains(object))
			   listA.add(object);
		}
		return listA;
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