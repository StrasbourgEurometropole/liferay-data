package eu.strasbourg.portlet.search_asset;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.search_asset.configuration.SearchAssetConfiguration;
import eu.strasbourg.portlet.search_asset.display.context.SearchAssetDisplayContext;

@Component(
	immediate = true,
	configurationPid = "eu.strasbourg.portlet.page_header.configuration.PageHeaderConfiguration",
	property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.css-class-wrapper=search-asset-portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/search-asset-view.jsp",
		"javax.portlet.init-param.config-template=/search-asset-configuration.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class SearchAssetPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
				.getAttribute(WebKeys.THEME_DISPLAY);
			SearchAssetConfiguration configuration = themeDisplay
				.getPortletDisplay()
				.getPortletInstanceConfiguration(SearchAssetConfiguration.class);
			
			// On set le DisplayContext
			SearchAssetDisplayContext dc = new SearchAssetDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);

			// Boolean pour dire qu'on vient du portlet de recherche et non d'un asset publisher
			renderRequest.setAttribute("fromSearchPortlet", true);
			Map<String, Long> className_layoutId = new HashMap<String, Long>();
			
			// On envoie a la jsp la map className / layout qui fait correspondre à chaque type d'asset une page de détail
			int i = 0;
			for (String className : configuration.assetClassNames().split(",")) {
				String layoutFriendlyURL = configuration.layoutsFriendlyURLs().split(",")[i];
				Layout layout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), false, layoutFriendlyURL);
				className_layoutId.put(className, layout.getPlid());
				i++;
			}
			renderRequest.setAttribute("classNameLayoutId", className_layoutId);
			
			super.render(renderRequest, renderResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}