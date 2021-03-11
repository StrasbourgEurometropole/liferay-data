package eu.strasbourg.service.help.search;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.help.model.HelpProposal;
import eu.strasbourg.service.help.service.HelpProposalLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(immediate = true, service = Indexer.class)
public class HelpProposalIndexer extends BaseIndexer<HelpProposal> {

	public static final String CLASS_NAME = HelpProposal.class.getName();
	public static final String INDEX_DATES = "dates";

	public HelpProposalIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}
	
	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(HelpProposal helpProposal) throws Exception {
		deleteDocument(helpProposal.getCompanyId(), helpProposal.getHelpProposalId());
	}

	/**
	 * Fonction appelée lors de l'indexation de l'item
	 * C'est ici qu'on choisi les champs à indexer
	 */
	@Override
	protected Document doGetDocument(HelpProposal helpProposal) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, helpProposal);
	
		// On indexe toute la hiérarchie de catégories (parents et enfants des
		// catégories de l'entité)
		long[] assetCategoryIds = AssetVocabularyHelper
			.getFullHierarchyCategoriesIds(helpProposal.getCategories());
		List<AssetCategory> assetCategories = AssetVocabularyHelper
			.getFullHierarchyCategories(helpProposal.getCategories());
		document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
		addSearchAssetCategoryTitles(document, Field.ASSET_CATEGORY_TITLES,
			assetCategories);
		
		Map<Locale, String> titleFieldMap = new HashMap<>();
		titleFieldMap.put(Locale.FRANCE, helpProposal.getTitle());
		titleFieldMap.put(Locale.GERMANY, helpProposal.getTitle());
		titleFieldMap.put(Locale.ENGLISH, helpProposal.getTitle());
		document.addLocalizedText(Field.TITLE, titleFieldMap);
		
		Map<Locale, String> descriptionFieldMap = new HashMap<>();
		descriptionFieldMap.put(Locale.FRANCE, helpProposal.getDescription());
		descriptionFieldMap.put(Locale.GERMANY, helpProposal.getDescription());
		descriptionFieldMap.put(Locale.ENGLISH, helpProposal.getDescription());
		document.addLocalizedText(Field.DESCRIPTION, descriptionFieldMap);

		document.addDateSortable(INDEX_DATES, new Date[]{helpProposal.getModifiedByUserDate()});
		
		document.addNumber(Field.STATUS, helpProposal.getStatus());
		
		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) throws Exception {
		Summary summary = createSummary(document, Field.TITLE, Field.URL);
		return summary;
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		HelpProposal entry = HelpProposalLocalServiceUtil.getHelpProposal(classPK);
		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEntries(companyId);
	}
	
	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = HelpProposalLocalServiceUtil
			.getIndexableActionableDynamicQuery();
	
		indexableActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
	
				}
			});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<HelpProposal>() {
	
				@Override
				public void performAction(HelpProposal entry) {
					try {
						Document document = getDocument(entry);
	
						indexableActionableDynamicQuery.addDocuments(document);
					} catch (PortalException pe) {
						_log.error("Unable to index helpProposal entry "
							+ entry.getHelpProposalId());
					}
				}
	
			});
	
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	protected void doReindex(HelpProposal helpProposal) throws Exception {
		Document document = getDocument(helpProposal);
		
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
				helpProposal.getCompanyId(), document, isCommitImmediately());
	}

}
