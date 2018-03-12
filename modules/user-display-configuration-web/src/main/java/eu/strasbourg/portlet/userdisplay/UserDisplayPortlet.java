package eu.strasbourg.portlet.userdisplay;

import java.io.IOException;
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

import com.liferay.portal.convert.ConvertProcessUtil;
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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * @author romain.vergnais
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=Strasbourg", "com.liferay.portlet.requires-namespaced-parameters=false",
		"com.liferay.portlet.instanceable=false", "javax.portlet.display-name=Personnalisation utilisateur",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/user-display-view.jsp",
		"javax.portlet.name=" + StrasbourgPortletKeys.USER_DISPLAY, "javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class UserDisplayPortlet extends MVCPortlet {

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	public void render(RenderRequest request, RenderResponse renderResponse) throws IOException, PortletException {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

			// Récupération du publik ID avec la session
			String internalId = getPublikID(request);
			
			List<String> portlets = new ArrayList<String>(); // Tous les portlets
			List<String> hiddenPortlets = new ArrayList<String>(); // Les portlets à ne pas afficher

			//Attention, ne pas modifier le nom des portlets ici sous peine de casser les configurations déjà existantes
			portlets.add("last-favorites");
			portlets.add("news-agenda");
			portlets.add("around-me");
			portlets.add("signalement");

			if (Validator.isNotNull(internalId) && Validator
					.isNotNull(PublikUserLocalServiceUtil.getByPublikUserId(internalId).getDisplayConfig())) {
				PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);

				JSONObject json = JSONFactoryUtil.createJSONObject(user.getDisplayConfig());
				JSONArray jsonArray = json.getJSONArray("hiddenPortlets");
				jsonArray.forEach(t -> hiddenPortlets.add((String) t));
			}

			request.setAttribute("portlets", portlets);
			request.setAttribute("hiddenPortlets", hiddenPortlets);
		} catch (Exception e) {
			_log.error(e);
		}
		super.render(request, renderResponse);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("togglePortlet")) {

				// Récupération du publik ID avec la session
				String internalId = getPublikID(resourceRequest);

				if (Validator.isNotNull(internalId)) {
					PublikUser user = PublikUserLocalServiceUtil.getByPublikUserId(internalId);

					// JSON initialisation
					String userConfigString = user.getDisplayConfig();
					if (Validator.isNull(userConfigString)) {
						userConfigString = "{\"hiddenPortlets\":[]}";
					}
					JSONObject json = JSONFactoryUtil.createJSONObject(userConfigString);
					JSONArray jsonArray = json.getJSONArray("hiddenPortlets");
					
					String portletName = ParamUtil.getString(resourceRequest, "portletName");
	    			
					//Récupération du portlet name : S'il existe dans la config utilisateur on le supprime
					//Sinon on l'ajoute
	    			if(Validator.isNotNull(portletName))
	    			{
	    				List<String> portlets = new ArrayList<String>(); 
						jsonArray.forEach(a -> portlets.add((String)a));
	    					
	    				if(portlets.contains(portletName))
	    					portlets.remove(portletName);
	    				else
	    					portlets.add(portletName);
	    					
	    				JSONArray jsonArray2 = JSONFactoryUtil.createJSONArray();
	    				portlets.forEach(a -> jsonArray2.put(a));
	    				jsonArray = jsonArray2;
	    			}

					// Enregistrement des préférences utilisateur.
					json.put("hiddenPortlets", jsonArray);
					user.setDisplayConfig(json.toJSONString());
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
}