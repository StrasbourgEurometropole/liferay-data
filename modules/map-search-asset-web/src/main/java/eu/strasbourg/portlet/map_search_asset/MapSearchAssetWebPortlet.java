package eu.strasbourg.portlet.map_search_asset;

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.SessionParamUtil;

import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
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
	
	@Override
	public void render(RenderRequest request, RenderResponse response) throws IOException, PortletException {
		super.render(request, response);
	}
	
	/**
	 * Récupération du publik ID avec la session
	 * @return L'id publik de l'utilisateur courant
	 */
	private String getPublikID(PortletRequest resourceRequest) {
		
		LiferayPortletRequest liferayPortletRequest = PortalUtil.getLiferayPortletRequest(resourceRequest);
		HttpServletRequest originalRequest = liferayPortletRequest.getHttpServletRequest();
		
		return SessionParamUtil.getString(originalRequest, "publik_internal_id");
	}
	
}