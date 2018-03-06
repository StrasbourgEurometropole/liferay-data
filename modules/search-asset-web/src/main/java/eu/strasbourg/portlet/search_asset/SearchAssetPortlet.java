package eu.strasbourg.portlet.search_asset;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.search_asset.action.ExportPDF;
import eu.strasbourg.portlet.search_asset.configuration.SearchAssetConfiguration;
import eu.strasbourg.portlet.search_asset.display.context.SearchAssetDisplayContext;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;

@Component(immediate = true, configurationPid = "eu.strasbourg.portlet.page_header.configuration.PageHeaderConfiguration", property = {
		"com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.css-class-wrapper=search-asset-portlet",
		"com.liferay.portlet.single-page-application=false",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/search-asset-view.jsp",
		"javax.portlet.init-param.check-auth-token=false",
		"javax.portlet.init-param.config-template=/search-asset-configuration.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class SearchAssetPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
			RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			SearchAssetConfiguration configuration = themeDisplay
					.getPortletDisplay().getPortletInstanceConfiguration(
							SearchAssetConfiguration.class);

			// On set le DisplayContext
			SearchAssetDisplayContext dc = new SearchAssetDisplayContext(
					renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);

			// Boolean pour dire qu'on vient du portlet de recherche et non d'un
			// asset publisher (pour être utilisé dans les ADT si besoin
			renderRequest.setAttribute("fromSearchPortlet", true);

			// On envoie a la jsp la map className / layout qui fait
			// correspondre à chaque type d'asset une page de détail
			int i = 0;
			Map<String, Long> className_layoutId = new HashMap<String, Long>();
			for (String className : configuration.assetClassNames()
					.split(",")) {
				String layoutFriendlyURL = configuration.layoutsFriendlyURLs()
						.split(",")[i];
				Layout layout = LayoutLocalServiceUtil.fetchLayoutByFriendlyURL(
						themeDisplay.getScopeGroupId(), false,
						layoutFriendlyURL);
				if (layout != null) {
					className_layoutId.put(className, layout.getPlid());
				}
				i++;
			}

			renderRequest.setAttribute("classNameLayoutId", className_layoutId);

			super.render(renderRequest, renderResponse);
		} catch (Exception e) {
			_log.error(e);
		}
	}

	/**
	 * L'utilisateur a fait une recherche, on en profite pour set un attribut
	 */
	@Override
	public void processAction(ActionRequest actionRequest,
			ActionResponse actionResponse)
			throws IOException, PortletException {

		actionRequest.setAttribute("userSearch", true);

		super.processAction(actionRequest, actionResponse);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)
			throws IOException, PortletException {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest
					.getAttribute(WebKeys.THEME_DISPLAY);
			SearchAssetConfiguration configuration = themeDisplay
					.getPortletDisplay().getPortletInstanceConfiguration(
							SearchAssetConfiguration.class);
			String exportType = configuration.exportType();
			ExportPDF.printPDFWithXMLWorker(resourceRequest, resourceResponse, exportType);
		} catch (Exception e2) {
			_log.error(e2);
		}
		super.serveResource(resourceRequest, resourceResponse);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}