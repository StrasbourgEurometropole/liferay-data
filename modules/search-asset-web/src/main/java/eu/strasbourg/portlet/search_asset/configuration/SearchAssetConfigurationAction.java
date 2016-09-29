package eu.strasbourg.portlet.search_asset.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PredicateFilter;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

@Component(
	configurationPid = "eu.strasbourg.portlet.search_asset.configuration.SearchAssetConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=eu_strasbourg_portlet_search_asset_SearchAssetPortlet"
	},
	service = ConfigurationAction.class)
public class SearchAssetConfigurationAction extends DefaultConfigurationAction {

	/**
	 * Sauvegarde des préférences
	 */
	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, "cmd");
		if (cmd.equals("update")) {
			
			// ClassNamesIds et templates Ids associés
			String assetClassNamesIdsString = "";
			String templatesKeysString = "";
			String layoutsFriendlyURLs = "";
			long assetClassNamesCount = ParamUtil.getLong(actionRequest,
				"assetClassNamesCount");
			int j = 0;
			for (long i = 0; i < assetClassNamesCount; i++) {
				String assetClassNameIdString = ParamUtil.getString(actionRequest,
					"assetClassNameId_" + i);
				boolean assetClassNameSelected = !Validator
					.isNull(assetClassNameIdString)
					&& !assetClassNameIdString.equals("false");
				if (assetClassNameSelected) {
					// ClassName
					if (assetClassNamesIdsString.length() > 0) {
						assetClassNamesIdsString += ",";
					}
					assetClassNamesIdsString += assetClassNameIdString;
					
					// Template correspondant
					String templateKeyString = ParamUtil.getString(actionRequest, "templateKey_" + i);
					if (templatesKeysString.length() > 0) {
						templatesKeysString += ",";
					}
					templatesKeysString += templateKeyString;
					
					// Et la friendlyURL du layout de détail correspondant
					String layoutFriendlyURL = ParamUtil.getString(actionRequest, "layoutFriendlyURL_" + i);
					if (j > 0) {
						layoutsFriendlyURLs += ",";
					}
					layoutsFriendlyURLs += layoutFriendlyURL;
					j++;
				}
				
			}
			setPreference(actionRequest, "assetClassNamesIds",
				assetClassNamesIdsString);
			setPreference(actionRequest, "templatesKeys",
				templatesKeysString);
			setPreference(actionRequest, "layoutsFriendlyURLs",
				layoutsFriendlyURLs);
			
			// ClassNames
			String assetClassNames = "";
			for (String assetClassNameIdString : assetClassNamesIdsString.split(",")) {
				if (assetClassNameIdString.length() > 0) {
    				ClassName assetClassName = ClassNameLocalServiceUtil
    					.getClassName(Long.parseLong(assetClassNameIdString));
    				if (assetClassNames.length() > 0) {
    					assetClassNames += ",";
    				}
    				assetClassNames += assetClassName.getValue();
				}
			}
			setPreference(actionRequest, "assetClassNames",
				assetClassNames);
			
			// Delta
			long delta = ParamUtil.getLong(actionRequest, "delta");
			setPreference(actionRequest, "delta", String.valueOf(delta));

			// VocabulariesIds
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
			
			// Types d'assets sélectionnés
    		long[] assetClassNamesIds = ParamUtil.getLongValues(request,
    			"assetClassNamesIds");
    		String assetClassNamesIdsString;
    		if (assetClassNamesIds.length > 0) {
    			assetClassNamesIdsString = StringUtil.merge(assetClassNamesIds);
    		} else {
    			assetClassNamesIdsString = configuration.assetClassNamesIds();
    		}
    		request.setAttribute("assetClassNamesIds", assetClassNamesIdsString);


    		// Liste des templates
			List<List<DDMTemplate>> templatesList = new ArrayList<List<DDMTemplate>>();
    		for (int i = 0; i < availableAssetRendererFactories.size(); i++) {
    			List<DDMTemplate> templates = DDMTemplateLocalServiceUtil.getTemplates(themeDisplay.getScopeGroupId(), availableAssetRendererFactories.get(i).getClassNameId());
    			templatesList.add(templates);
    		}
    		request.setAttribute("templatesList", templatesList);
			
    		// Templates sélectionnés
    		long[] templatesKeys = ParamUtil.getLongValues(request,
				"templatesKeys");
			String templatesKeysString;
			if (templatesKeys.length > 0) {
				templatesKeysString = StringUtil.merge(templatesKeys);
			} else {
				templatesKeysString = configuration.templatesKeys();
			}
			request.setAttribute("templatesKeys", templatesKeysString);
			
			// Layouts
			String[] layoutsFriendlyURLs = configuration.layoutsFriendlyURLs().split(",");
			request.setAttribute("layoutsFriendlyURLs", layoutsFriendlyURLs);
    			
			// Vocabulaires
			List<AssetVocabulary> allVocabularies = AssetVocabularyLocalServiceUtil.getAssetVocabularies(-1, -1);
			List<AssetVocabulary> vocabularies = new ArrayList<AssetVocabulary>();
			for (AssetVocabulary vocabulary : allVocabularies) {
				if (vocabulary.getGroupId() == themeDisplay.getSiteGroupIdOrLiveGroupId()) {
					vocabularies.add(vocabulary);
				}
			}
			request.setAttribute("vocabularies", vocabularies);
			
			// Vocabulaires sélectionnés
			long[] vocabulariesIds = ParamUtil.getLongValues(request,
				"vocabulariesIds");
			String vocabulariesIdsString;
			if (vocabulariesIds.length > 0) {
				vocabulariesIdsString = StringUtil.merge(vocabulariesIds);
			} else {
				vocabulariesIdsString = configuration.vocabulariesIds();
			}
			request.setAttribute("vocabulariesIds", vocabulariesIdsString);

			// Delta
			long delta = ParamUtil.getLong(request, "delta",
				configuration.delta());
			request.setAttribute("delta", delta);
			
			super.include(portletConfig, request, response);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
}
