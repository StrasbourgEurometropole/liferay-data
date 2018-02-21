package eu.strasbourg.portlet.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
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
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author romain.vergnais
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=Autour de moi",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/map-view.jsp",
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
			LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(request);
			HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
			String internalId = SessionParamUtil.getString(originalRequest, "publik_internal_id");
			boolean showFavorites = true; //Affichage des favoris par défaut
			
			List<Interest> interests;
			
			//Est-ce que la config du portlet est défini ?
			if(configuration.hasConfig())
			{
				//Récupération des id des centres d'intérêts actifs
				String interestsIdsString = configuration.interestsIds();
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
				showFavorites = configuration.showFavorites();
			}
			else //Si pas de config on récupère tous les centres d'intérêt avec le statut publié
			{	
				interests = InterestLocalServiceUtil.getInterests(-1, -1).stream()
						.filter(i -> i.getStatus() == 0).collect(Collectors.toList());
			}
			
			String interestsDefaultsIds = "";
			boolean hasConfig = false;
			
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
			else //Sinon affichage par défaut du portlet
			{
				//On prend par defaut les POI de l'utilisateur s'il en a
				if(Validator.isNotNull(internalId))
				{
					interestsDefaultsIds = StringUtil.merge(InterestLocalServiceUtil.getByPublikUserId(internalId).stream()
							.map(i -> i.getInterestId())
							.collect(Collectors.toList()), ",");
					hasConfig = true;
				}
				
				//sinon ceux configurés dans le portlet.
				if(!Validator.isNotNull(interestsDefaultsIds)) 
				{
					interestsDefaultsIds = configuration.interestsDefaultsIds();
					hasConfig = configuration.hasConfig();
				}
			}
			
			request.setAttribute("interests", interests);
			request.setAttribute("interestsCheckedIds", interestsDefaultsIds);
			request.setAttribute("hasConfig", hasConfig);
			request.setAttribute("showFavorites", showFavorites);
		} catch (Exception e) {
			_log.error(e);
		}
		super.render(request, renderResponse);
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		try 
		{
			String resourceID = resourceRequest.getResourceID();
			
			if (resourceID.equals("toggleInterestPoint")) {
           	 
            	// Récupération du publik ID avec la session
         		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
         		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
         		String internalId = SessionParamUtil.getString(originalRequest, "publik_internal_id");
         		
         		
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

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}