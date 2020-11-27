package eu.strasbourg.portlet.search_asset_v2.configuration;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import eu.strasbourg.portlet.search_asset_v2.configuration.bean.ConfigurationData;
import eu.strasbourg.portlet.search_asset_v2.configuration.constants.ConfigurationConstants;
import eu.strasbourg.portlet.search_asset_v2.configuration.display.context.SearchAssetConfigurationDisplayContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import javax.portlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component(
	configurationPid = "eu.strasbourg.portlet.search_asset_v2.configuration.SearchAssetConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL,
	immediate = true,
	property = {
		"javax.portlet.name=eu_strasbourg_portlet_search_asset_v2_SearchAssetPortlet"
	},
	service = ConfigurationAction.class
)
public class SearchAssetConfigurationAction extends DefaultConfigurationAction {

	/**
	 * Sauvegarde des préférences
	 */
	@Override
	public void processAction(PortletConfig portletConfig,
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
			.getAttribute(WebKeys.THEME_DISPLAY);
		String cmd = ParamUtil.getString(actionRequest, "cmd");
		if (cmd.equals("update")) {
			ConfigurationData configData = new ConfigurationData(actionRequest);
			configData.saveConfiguration(this);
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

			SearchAssetConfigurationDisplayContext dc = new SearchAssetConfigurationDisplayContext(request);
			request.setAttribute("dc", dc);

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
						assetRendererFactory -> assetRendererFactory.isCategorizable()
							&& assetRendererFactory.getClassName()
								.startsWith("eu.strasbourg"));

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
			request.setAttribute("assetClassNamesIds",
				assetClassNamesIdsString);

			// Recherche de JournalArticle
			boolean searchJournalArticle = ParamUtil.getBoolean(request,
				"searchJournalArticle", configuration.searchJournalArticle());
			request.setAttribute("searchJournalArticle", searchJournalArticle);

			// Recherche de documents
			boolean searchDocument = ParamUtil.getBoolean(request,
					"searchDocument", configuration.searchDocument());
			request.setAttribute("searchDocument", searchDocument);

			// Recherche de démarches (procédures)
			boolean searchDemarche = ParamUtil.getBoolean(request,
					"searchDemarche", configuration.searchDemarche());
			request.setAttribute("searchDemarche", searchDemarche);

			// Liste des templates
			List<List<DDMTemplate>> templatesList = new ArrayList<List<DDMTemplate>>();
			for (int i = 0; i < availableAssetRendererFactories.size(); i++) {
				// Pour les contenus web, on utilise les display templates
				// utilisés pour les AssetEntry
				long classNameId = availableAssetRendererFactories.get(i)
					.getClassNameId();
				List<DDMTemplate> templates = DDMTemplateLocalServiceUtil
					.getTemplates(themeDisplay.getScopeGroupId(), classNameId);
				templatesList.add(templates);
			}
			request.setAttribute("templatesList", templatesList);

			// Liste des templates pour les JournalArticle (techniquement
			// AssetEntry)
			long assetEntryClassNameId = ClassNameLocalServiceUtil
				.getClassNameId(AssetEntry.class);
			List<DDMTemplate> assetEntryTemplates = DDMTemplateLocalServiceUtil
				.getTemplates(themeDisplay.getScopeGroupId(),
					assetEntryClassNameId);
			request.setAttribute("assetEntryTemplatesList",
				assetEntryTemplates);

			// Liste des templates pour les documents
			long documentClassNameId = ClassNameLocalServiceUtil
					.getClassNameId(FileEntry.class);
			List<DDMTemplate> documentTemplates = DDMTemplateLocalServiceUtil
					.getTemplates(themeDisplay.getScopeGroupId(),
							documentClassNameId);
			request.setAttribute("documentTemplatesList", documentTemplates);

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

			// Template sélectionné pour JournalArticle
			long journalArticleTemplateKey = ParamUtil.getLong(request,
				"journalArticleTemplateKey",
				GetterUtil.getLong(configuration.journalArticleTemplateKey()));
			request.setAttribute("journalArticleTemplateKey",
				journalArticleTemplateKey);

			// Template sélectionné pour les documents
			long documentTemplateKey = ParamUtil.getLong(request,
					"documentTemplateKey",
					GetterUtil.getLong(configuration.documentTemplateKey()));
			request.setAttribute("documentTemplateKey", documentTemplateKey);

			// Layouts
			String[] layoutsFriendlyURLs = configuration.layoutsFriendlyURLs()
				.split(",");
			request.setAttribute("layoutsFriendlyURLs", layoutsFriendlyURLs);

			// Vocabulaires
			List<AssetVocabulary> allVocabularies = AssetVocabularyLocalServiceUtil
				.getAssetVocabularies(-1, -1);
			List<AssetVocabulary> vocabularies = new ArrayList<AssetVocabulary>();
			for (AssetVocabulary vocabulary : allVocabularies) {
				if (vocabulary.getGroupId() == themeDisplay.getScopeGroupId()
					|| vocabulary.getGroupId() == themeDisplay
						.getCompanyGroupId()) {
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

			// Types des contrôles
			String[] vocabulariesControlTypes = configuration
				.vocabulariesControlTypes().split(",");
			request.setAttribute("vocabulariesControlTypes",
				vocabulariesControlTypes);

			// Scope global
			boolean globalScope = ParamUtil.getBoolean(request, "globalScope",
				configuration.globalScope());
			request.setAttribute("globalScope", globalScope);

			// Champ date
			boolean dateField = ParamUtil.getBoolean(request, "dateField",
				configuration.dateField());
			request.setAttribute("dateField", dateField);

			// Tri par date
			boolean displayDateSorting = ParamUtil.getBoolean(request,
				"displayDateSorting", configuration.displayDateSorting());
			request.setAttribute("displayDateSorting", displayDateSorting);

			// Ne pas afficher de résultat avant une recherche utilisateur
			boolean hideResultsBeforeSearch = ParamUtil.getBoolean(request,
				"hideResultsBeforeSearch",
				configuration.hideResultsBeforeSearch());
			request.setAttribute("hideResultsBeforeSearch",
				hideResultsBeforeSearch);

			// Delta
			long delta = ParamUtil.getLong(request, "delta",
				configuration.delta());
			request.setAttribute("delta", delta);
			
			// Formulaire à afficher
			String searchForm = ParamUtil.getString(request, "searchForm", configuration.searchForm());
			request.setAttribute("searchForm", searchForm);

			// Préfiltres catégories
			String prefilterCategoriesIds = configuration
				.prefilterCategoriesIds().replace(";", ",");
			request.setAttribute("prefilterCategoriesIds",
				prefilterCategoriesIds);

			// Préfiltre tags
			String prefilterTagsNames = configuration.prefilterTagsNames();
			request.setAttribute("prefilterTagsNames", prefilterTagsNames);

			// Boost tags
			String boostTagsNames = configuration.boostTagsNames();
			request.setAttribute("boostTagsNames", boostTagsNames);

			// Tri par défaut
			String defaultSortField = configuration.defaultSortField();
			request.setAttribute("defaultSortField", defaultSortField);
			String defaultSortType = configuration.defaultSortType();
			request.setAttribute("defaultSortType", defaultSortType);

			// Filtre par date par défault
			long defaultDateRange = configuration.defaultDateRange();
			request.setAttribute("defaultDateRange", defaultDateRange);

			// Affichage bouton export
			boolean displayExport = ParamUtil.getBoolean(request,
				"displayExport", configuration.displayExport());
			request.setAttribute("displayExport", displayExport);
			
			// Type d'export
			String exportType = ParamUtil.getString(request, "exportType", configuration.exportType());
			request.setAttribute("exportType", exportType);

			super.include(portletConfig, request, response);
		} catch (ConfigurationException e) {
			_log.error(e);
		}
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
