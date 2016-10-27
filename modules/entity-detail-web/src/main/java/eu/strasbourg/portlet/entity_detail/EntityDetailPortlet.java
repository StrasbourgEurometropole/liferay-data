package eu.strasbourg.portlet.entity_detail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.portlet.entity_detail.configuration.EntityDetailConfiguration;

@Component(
	immediate = true,
	property = { "com.liferay.portlet.display-category=Strasbourg",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.css-class-wrapper=entity-detail-portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/entity-detail-view.jsp",
		"javax.portlet.init-param.config-template=/entity-detail-configuration.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" },
	service = Portlet.class)
public class EntityDetailPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest request, RenderResponse response)
		throws IOException, PortletException {
		try {
			
			// Récupération de l'entité
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);
			EntityDetailConfiguration configuration;
			configuration = themeDisplay.getPortletDisplay()
				.getPortletInstanceConfiguration(
					EntityDetailConfiguration.class);
			
			Long entryId = ParamUtil.getLong(request, "classPK");
			String className = configuration.className();
			if (entryId <= 0) {
				entryId = configuration.classPK();
			}	
			AssetEntry entry = null;
			if (entryId > 0) {
				entry = AssetEntryLocalServiceUtil.fetchEntry(className, entryId);
				if (entry.getVisible()) {
					request.setAttribute("entry", entry);
				}
			}
			

			// Application display templates stuff
			PortletPreferences preferences = request.getPreferences();
			String displayStyle = GetterUtil.getString(
				preferences.getValue("displayStyle", StringPool.BLANK));
			long displayStyleGroupId = GetterUtil
				.getLong(preferences.getValue("displayStyleGroupId", null), 0);
			Map<String, Object> contextObjects = new HashMap<String, Object>();
			contextObjects.put("entry", entry != null ? entry.getAssetRenderer().getAssetObject() : null);
			request.setAttribute("entries", new ArrayList<AssetEntry>());
			request.setAttribute("displayStyle", displayStyle);
			request.setAttribute("displayStyleGroupId", displayStyleGroupId);
			request.setAttribute("contextObjects", contextObjects);
			 
			// Page cible des liens
			String targetUuid = GetterUtil.getString(
				preferences.getValue("portletSetupLinkToLayoutUuid", StringPool.BLANK));
			if (Validator.isNotNull(targetUuid)) {
				Layout targetLayout = LayoutLocalServiceUtil
					.fetchLayoutByUuidAndGroupId(targetUuid,
						themeDisplay.getScopeGroupId(), false);
				String targetFriendlyURL = PortalUtil
					.getLayoutFriendlyURL(targetLayout, themeDisplay);
				request.setAttribute("targetFriendlyURL", targetFriendlyURL);
			}
			super.render(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}