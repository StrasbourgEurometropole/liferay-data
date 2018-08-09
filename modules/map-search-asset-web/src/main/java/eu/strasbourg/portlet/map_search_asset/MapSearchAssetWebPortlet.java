package eu.strasbourg.portlet.map_search_asset;

import com.liferay.asset.kernel.model.AssetCategory;
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
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import java.io.IOException;
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
	
	private long selectedDistrictCategoryId;
	private List<Long> selectedProjectIds;
	private List<Long> selectedParticipationIds;
	private List<Long> selectedEventIds;
	
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
		
		//Recuperation et attribution des informations de l'utilisateur
		String publikUserId = getPublikID(request);
		request.setAttribute("publikUserId", publikUserId);
		
		// Recuperation et attribution des quartiers
		this.districtCategories = AssetVocabularyHelper.getAllDistrictsFromCity(CITY_NAME);
		request.setAttribute("districtCategories", this.districtCategories);
		
		// Recuperation et attribution des projets
		this.projects = ProjectLocalServiceUtil.getByGroupId(groupId);
		request.setAttribute("projects", this.projects);
		
		super.render(request, response);
	}
	
	/**
	 * Méthode exécutée lors d'un appel AJAX
	 */
	@Override
	public void serveResource(ResourceRequest request, ResourceResponse response)
			throws IOException, PortletException {
		try {
			// Recuperation du contexte de la requete
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = new Long(themeDisplay.getLayout().getGroupId());
			String resourceID = request.getResourceID();

			if (resourceID.equals("changeDistrict")) {
				long paramSelectedDistrictCategoryId = ParamUtil.getLong(request, "selectedDistrict");
				
				if (paramSelectedDistrictCategoryId > 0) {
					this.projects = ProjectLocalServiceUtil.findByCategoryIds(new long[] {paramSelectedDistrictCategoryId});
					
				} else {
					this.projects = ProjectLocalServiceUtil.getByGroupId(groupId);
				}
				
			}
			
			
		} catch (Exception e) {
			_log.error(e);
		}
		super.serveResource(request, response);
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