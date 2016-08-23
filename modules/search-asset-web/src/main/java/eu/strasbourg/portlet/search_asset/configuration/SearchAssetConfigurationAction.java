package eu.strasbourg.portlet.search_asset.configuration;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PredicateFilter;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import eu.strasbourg.utils.AssetVocabularyHelper;

@Component(
	configurationPid = "eu.strasbourg.portlet.search_asset.configuration.SearchAssetConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=eu_strasbourg_portlet_search_asset_SearchAssetPortlet" },
	service = ConfigurationAction.class)
public class SearchAssetConfigurationAction extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		String jsp = request.getParameter("jspPage");
		if (Validator.isNull(jsp)) { // Default JSP
			return "/search-asset-configuration.jsp";
		} else {
			return jsp;
		}
	}

	/**
	 * Save the preferences
	 */
	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, "cmd");
		if (cmd.equals("update")) {
			long assetClassNameId = ParamUtil.getLong(actionRequest,
				"assetClassNameId");
			setPreference(actionRequest, "assetClassNameId",
				String.valueOf(assetClassNameId));

			ClassName assetClassName = ClassNameLocalServiceUtil
				.getClassName(assetClassNameId);
			setPreference(actionRequest, "assetClassName",
				assetClassName.getClassName());

			long delta = ParamUtil.getLong(actionRequest, "delta");
			setPreference(actionRequest, "delta", String.valueOf(delta));

			String vocabulariesIdsString = "";
			long vocabulariesCount = ParamUtil.getLong(actionRequest,
				"vocabulariesCount");
			for (long i = 0; i < vocabulariesCount; i++) {
				String vocabularyIdString = ParamUtil.getString(actionRequest,
					"vocabularyId_" + i);
				boolean vocabularySelected = !Validator
					.isNull(vocabularyIdString)
					&& !vocabularyIdString.equals("false");
				if (vocabularySelected) {
					if (vocabulariesIdsString.length() > 0) {
						vocabulariesIdsString += ",";
					}
					vocabulariesIdsString += vocabularyIdString;
				}
			}
			setPreference(actionRequest, "vocabulariesIds",
				vocabulariesIdsString);
		} else if (cmd.equals("update-vocabularies")) {
			updateVocabularies(portletConfig, actionRequest, actionResponse);
		}
		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	/**
	 * Send to the JSP the needed data
	 */
	@Override
	public void include(PortletConfig portletConfig, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) request
				.getAttribute(WebKeys.THEME_DISPLAY);

			SearchAssetConfiguration configuration = themeDisplay
				.getPortletDisplay().getPortletInstanceConfiguration(
					SearchAssetConfiguration.class);

			// classNameId for the asset type we want to search
			long classNameId = ParamUtil.getLong(request, "assetClassNameId",
				configuration.assetClassNameId());
			request.setAttribute("assetClassNameId", classNameId);

			// Delta
			long delta = ParamUtil.getLong(request, "delta",
				configuration.delta());
			request.setAttribute("delta", delta);

			// Vocabularies ids
			long[] vocabulariesIds = ParamUtil.getLongValues(request,
				"vocabulariesIds");
			String vocabulariesIdsString;
			if (vocabulariesIds.length > 0) {
				vocabulariesIdsString = StringUtil.merge(vocabulariesIds);
			} else {
				vocabulariesIdsString = configuration.vocabulariesIds();
			}
			request.setAttribute("vocabulariesIds", vocabulariesIdsString);

			// Liste tous les types possibles d'asset
			// On ne prend que ceux qui commencent par "eu.strasbourg"
			List<AssetRendererFactory<?>> availableAssetRendererFactories = ListUtil
				.filter(
					AssetRendererFactoryRegistryUtil.getAssetRendererFactories(
						themeDisplay.getCompany().getCompanyId()),
					new PredicateFilter<AssetRendererFactory<?>>() {

						@Override
						public boolean filter(
							AssetRendererFactory<?> assetRendererFactory) {
							return assetRendererFactory.isCategorizable()
								&& assetRendererFactory.getClassName()
									.startsWith("eu.strasbourg");
						}

					});

			request.setAttribute("availableAssetRendererFactories",
				availableAssetRendererFactories);

			// If not already set, set the vocabularies
			if (request.getAttribute("vocabularies") == null) {
				List<AssetVocabulary> vocabularies = AssetVocabularyHelper
					.getVocabulariesForAssetType(
						themeDisplay.getSiteGroupIdOrLiveGroupId(),
						classNameId);
				request.setAttribute("vocabularies", vocabularies);
			}

			super.include(portletConfig, request, response);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Action for Ajax request when changing the "Asset type" dropdown
	 * 
	 * @param portletConfig
	 * @param actionRequest
	 * @param actionResponse
	 * @throws PortletException
	 * @throws IOException
	 */
	public void updateVocabularies(PortletConfig portletConfig,
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException, IOException {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
			.getAttribute(WebKeys.THEME_DISPLAY);
		long classNameId = ParamUtil.getLong(actionRequest, "assetClassNameId");

		// Get the list of vocabularies linked to the asset type
		List<AssetVocabulary> vocabularies = AssetVocabularyHelper
			.getVocabulariesForAssetType(
				themeDisplay.getSiteGroupIdOrLiveGroupId(), classNameId);
		actionRequest.setAttribute("vocabularies", vocabularies);
		String jspPath = "/search-asset-configuration-vocabularies.jsp";
		actionResponse.setRenderParameter("jspPage", jspPath);
	}
}
