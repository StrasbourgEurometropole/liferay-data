package eu.strasbourg.portlet.search_asset;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.search_asset.configuration.SearchAssetConfiguration;
import eu.strasbourg.portlet.search_asset.display.context.SearchAssetDisplayContext;

@Component(
	immediate = true,
	configurationPid = "eu.strasbourg.portlet.page_header.configuration.PageHeaderConfiguration",
	property = { "com.liferay.portlet.display-category=category.Strasbourg",
		"com.liferay.portlet.instanceable=true",
		"com.liferay.portlet.css-class-wrapper=search-asset-portlet",
		"javax.portlet.display-name=Recherche d'asset",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/search-asset-view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class SearchAssetPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest,
		RenderResponse renderResponse) throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest
			.getAttribute(WebKeys.THEME_DISPLAY);

		try {
			
			SearchAssetDisplayContext dc = new SearchAssetDisplayContext(renderRequest, renderResponse);
			renderRequest.setAttribute("dc", dc);
			
			super.render(renderRequest, renderResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}