package eu.strasbourg.portlet.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
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
import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.service.InterestLocalServiceUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.PublikApiClient;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author romain.vergnais
 */
@Component(immediate = true,
		property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=Autour de moi",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/map-view.jsp",
		"javax.portlet.init-param.config-template=/map-configuration.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.MAP_WEB, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class MapPortlet extends MVCPortlet {

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
	@Override
	public void render(RenderRequest request, RenderResponse renderResponse) throws IOException, PortletException {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			// Récupération de la configuration
			MapConfiguration configuration = themeDisplay.getPortletDisplay()
					.getPortletInstanceConfiguration(MapConfiguration.class);
			
			// Récupération du publik ID avec la session
     		String internalId = getPublikID(request);
			
     		boolean showFavorites = true; //Affichage des favoris par défaut
     		String interestsIdsString = ""; //Les id des POI actifs
     		String interestsDefaultsIds = ""; //Les POI cochés par défaut
     		boolean hasConfig = false; //Permet de cocher tous les POI si aucune configuration
     		List<Interest> interests = null; //Les POI actifs
     		boolean widgetMod = false;
     		
     		//Chargement de la configuration globale pour le mode widget
     		if(configuration.hasConfig() && configuration.widgetMod())
     		{
     			ExpandoBridge ed = themeDisplay.getScopeGroup().getExpandoBridge();
     			hasConfig = true;
     			widgetMod = true;
     			try{
     				String globalConfig = GetterUtil.getString(ed.getAttribute("map_global_config"));
     				JSONObject json = JSONFactoryUtil.createJSONObject(globalConfig);
    				JSONArray jsonArray = json.getJSONArray("interestsIds");
    				JSONArray jsonArray2 = json.getJSONArray("interestsDefaultsIds");
    				
    				interestsIdsString = jsonArray.join(",").replace("\"", "");
    				showFavorites = json.getBoolean("showFavorites");
    				interestsDefaultsIds = jsonArray2.join(",");
				}
				catch (Exception ex) {
					_log.error("Missing expando field : map_global_config");
				}
     		}
     		//Chargement de la configuration du portlet sinon
     		else if(configuration.hasConfig())
     		{
     			interestsIdsString = configuration.interestsIds();
     			showFavorites = configuration.showFavorites();
     			interestsDefaultsIds = configuration.interestsDefaultsIds();
				hasConfig = true;
     		}
     		else //Si pas de config on récupère tous les centres d'intérêt avec le statut publié
			{	
				interests = InterestLocalServiceUtil.getInterests(-1, -1).stream()
						.filter(i -> i.getStatus() == 0).collect(Collectors.toList());
			}
			
			//Est-ce que la config du portlet est défini ?
			if(configuration.hasConfig())
			{
				List<Long> interestIds;
				
				if(!interestsIdsString.equals(""))
					interestIds = Arrays.stream(interestsIdsString.split(","))
						.map(i -> Long.parseLong(i))
						.collect(Collectors.toList());
				else
					//Si jamais aucun POI n'est coché intentionnellement...
					interestIds = new ArrayList<Long>();
					
				//Récupération de tous les centres d'intérêts actifs avec le statut publié
				interests = InterestLocalServiceUtil.getInterests(-1, -1).stream()
						.filter(i -> i.getStatus() == 0 && interestIds.contains(i.getInterestId())).collect(Collectors.toList());
				
			}
			
            String address = null;
            if(Validator.isNotNull(internalId)){
                JSONObject userDetail = PublikApiClient.getUserDetails(internalId);
                address = userDetail.get("address") + " " + userDetail.get("zipcode") + " " + userDetail.get("city");
            }
			
			//Si l'utilisateur est connecté et qu'il a configuré le portlet autour de moi
			if(Validator.isNotNull(internalId) && Validator.isNotNull(PublikUserLocalServiceUtil.getByPublikUserId(internalId).getMapConfig()))
			{
				PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);
				
				JSONObject json = JSONFactoryUtil.createJSONObject(user.getMapConfig());
				JSONArray jsonArray = json.getJSONArray("interests");
				
				interestsDefaultsIds = jsonArray.join(",");
				showFavorites = json.getBoolean("showFavorites");
				hasConfig = true;
			}
			else //Sinon on prend par defaut les POI de l'utilisateur s'il en a
			{ 
				if(Validator.isNotNull(internalId))
				{
					String userDefautPOI = StringUtil.merge(InterestLocalServiceUtil.getByPublikUserId(internalId).stream()
							.map(i -> i.getInterestId())
							.collect(Collectors.toList()), ",");
					interestsDefaultsIds = Validator.isNotNull(userDefautPOI) ? userDefautPOI : interestsDefaultsIds;
					hasConfig = true;
				}
			}
			
			request.setAttribute("interests", interests);
			request.setAttribute("interestsCheckedIds", interestsDefaultsIds);
			request.setAttribute("hasConfig", hasConfig);
			request.setAttribute("showFavorites", showFavorites);
			request.setAttribute("widgetMod", widgetMod);
			request.setAttribute("address", address);
		} catch (Exception e) {
			_log.error(e);
		}
		super.render(request, renderResponse);
	}
	
	public void resetUserConfiguration(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortalException, SystemException {
		
		try{
			//Récupération du publik ID avec la session
			String internalId = getPublikID(actionRequest);
			
			if(Validator.isNotNull(internalId))
			{
				PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);
    			user.setMapConfig("");
    			PublikUserLocalServiceUtil.updatePublikUser(user);
			}
		}
		catch (Exception e)
		{
			_log.error(e);
		}

	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		try 
		{
			String resourceID = resourceRequest.getResourceID();
			
			if (resourceID.equals("toggleInterestPoint")) {
           	 
            	// Récupération du publik ID avec la session
         		String internalId = getPublikID(resourceRequest);
         		
         		if(Validator.isNotNull(internalId))
    			{
    				PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);
         		
	         		//JSON initialisation
	         		JSONObject json = JSONFactoryUtil.createJSONObject();
	         		JSONArray jsonArray =  JSONFactoryUtil.createJSONArray();
	         		
	         		// Centres d'intérêts actifs
	    			String interestsIdsString = "";
	    			long interestsCount = ParamUtil.getLong(resourceRequest, "interestsCount");
	    			
	    			for (long i = 0; i < interestsCount; i++)
	    			{
	    				String interestIdString = ParamUtil.getString(resourceRequest, "interestPointId_" + i);
	    				boolean interestSelected = Validator.isNotNull(interestIdString) && !interestIdString.equals("false");
	    				
	    				if (interestSelected)
	    					jsonArray.put(Long.parseLong(interestIdString));
	    			}
	    			
	    			//Enregistrement des préférences utilisateur.
	    			json.put("showFavorites", ParamUtil.getBoolean(resourceRequest, "showFavorites"));
	    			json.put("interests", jsonArray);
	    			user.setMapConfig(json.toJSONString());
	    			PublikUserLocalServiceUtil.updatePublikUser(user);
    			}
             }
		}
		catch (Exception e)
		{
			_log.error(e);
		}
		
		super.serveResource(resourceRequest, resourceResponse);
	}
	
	private String getPublikID(PortletRequest resourceRequest){
		
		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
 		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
		
 		return SessionParamUtil.getString(originalRequest, "publik_internal_id");
	}

	
}