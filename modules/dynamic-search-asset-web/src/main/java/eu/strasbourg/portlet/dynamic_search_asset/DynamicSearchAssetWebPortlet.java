package eu.strasbourg.portlet.dynamic_search_asset;

import eu.strasbourg.portlet.dynamic_search_asset.configuration.DynamicSearchAssetConfiguration;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	private DynamicSearchAssetConfiguration configuration;
	private List<String> classNames;
	
	private List<AssetEntry> assetEntries;
	
	/**
	 * Initialisation de la vue
	 */
	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		try {
			// Recuperation du contexte de la requete
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = new Long(themeDisplay.getLayout().getGroupId());
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
			if (resourceID.equals("searchAsset")) {
				
				// Recuperation du searchContext
				HttpServletRequest servletRequest = PortalUtil.getHttpServletRequest(request);
				SearchContext searchContext = SearchContextFactory.getInstance(servletRequest);
				
				// Recuperation des classNames selectionnes demandes par l'utilisateur
				String selectedClassNames = ParamUtil.getString(request, "selectedClassNames");
				List<String> classNames = Arrays.asList(selectedClassNames.split(","));
				
				// Recuperation des mots clefs demandes par l'utilisateur
				String keywords = ParamUtil.getString(request, "keywords");
				
				// Inclusion ou non du scope global
				boolean globalScope = this.configuration.globalScope();
				long globalGroupId = themeDisplay.getCompanyGroupId();
				
				// Recuperation du nombre de resultat max demande
				int maxResults = (int) this.configuration.delta();
				
				// Recherche
//				Hits hits = SearchHelper.getGlobalSearchHits(searchContext, classNames, groupId, globalGroupId, globalScope,
//						keywords, dateField, dateFieldName, fromDate, toDate, categoriesIds, prefilterCategoriesIds,
//						prefilterTagsNames, idSIGPlace, themeDisplay.getLocale(), 0,
//						maxResults, "score", false);
//				List<AssetEntry> results = new ArrayList<AssetEntry>();
//				if (hits != null) {
//					int i = 0;
//					for (float s : hits.getScores()) {
//						_log.info(GetterUtil.getString(hits.getDocs()[i].get(Field.TITLE)) + " : " + s);
//						i++;
//						if (i > 10)
//							break;
//					}
//
//					for (Document document : hits.getDocs()) {
//						AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(
//								GetterUtil.getString(document.get(Field.ENTRY_CLASS_NAME)),
//								GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
//						if (entry != null) {
//							results.add(entry);
//						}
//					}
//				}
//
//				this.assetEntries = results;
				
				JSONObject jsonResponse = this.constructJSONSelection(request);
				
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
		
		// Parcrous des résultats
		for (AssetEntry assetEntry : this.assetEntries) {
			
			switch (assetEntry.getClassName()) {
				
				
				
			}
			
		}
		
		return  jsonResponse;
	}
	
	/**
	 * Retourne la liste des class names configurés recherchable
	 */
	public List<String> getConfiguredClassNames() {
		if (this.classNames == null) {
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
			this.classNames = classNames;
		}
		return this.classNames;
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