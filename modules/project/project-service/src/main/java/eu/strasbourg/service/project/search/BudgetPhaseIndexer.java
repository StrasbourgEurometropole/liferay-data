package eu.strasbourg.service.project.search;

import com.liferay.portal.kernel.search.BaseIndexer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.service.project.service.BudgetPhaseLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.DateHelper;

@Component(
	immediate = true,
	service = Indexer.class
)
public class BudgetPhaseIndexer extends BaseIndexer<BudgetPhase> {
	
	public static final String CLASS_NAME = BudgetPhase.class.getName();
	
	public BudgetPhaseIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(BudgetPhase budgetPhase) throws Exception {
		deleteDocument(budgetPhase.getCompanyId(), budgetPhase.getBudgetPhaseId());
	}

	/**
	 * Fonction appelée lors de l'indexation de l'item
	 * C'est ici qu'on choisi les champs à indexer
	 */
	@Override
	protected Document doGetDocument(BudgetPhase budgetPhase) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, budgetPhase);
	
		// On indexe toute la hiérarchie de catégories (parents et enfants des
		// catégories de l'entité)
		long[] assetCategoryIds = AssetVocabularyHelper
			.getFullHierarchyCategoriesIds(budgetPhase.getCategories());
		List<AssetCategory> assetCategories = AssetVocabularyHelper
			.getFullHierarchyCategories(budgetPhase.getCategories());
		document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
		addSearchAssetCategoryTitles(document, Field.ASSET_CATEGORY_TITLES,
			assetCategories);
		
		Map<Locale, String> titleFieldMap = new HashMap<Locale, String>();
		titleFieldMap.put(Locale.FRANCE, budgetPhase.getTitle());
		titleFieldMap.put(Locale.GERMANY, budgetPhase.getTitle());
		titleFieldMap.put(Locale.ENGLISH, budgetPhase.getTitle());
		document.addLocalizedText(Field.TITLE, titleFieldMap);
		
		Map<Locale, String> descriptionFieldMap = new HashMap<Locale, String>();
		descriptionFieldMap.put(Locale.FRANCE, budgetPhase.getDescription());
		descriptionFieldMap.put(Locale.GERMANY, budgetPhase.getDescription());
		descriptionFieldMap.put(Locale.ENGLISH, budgetPhase.getDescription());
		document.addLocalizedText(Field.DESCRIPTION, descriptionFieldMap);
		
		document.addNumber(Field.STATUS, budgetPhase.getStatus());
		
		Date beginDate = budgetPhase.getBeginDate();
		Date endDate = budgetPhase.getEndDate();
		List<Date> dates = new ArrayList<Date>();
		dates.addAll(DateHelper.getDaysBetweenDates(beginDate, endDate));
		
		Date beginVoteDate = budgetPhase.getBeginVoteDate();
		Date endVoteDate = budgetPhase.getEndVoteDate();
		dates.addAll(DateHelper.getDaysBetweenDates(beginVoteDate, endVoteDate));
		
		document.addDateSortable("dates", dates.toArray(new Date[dates.size()]));
		
		document.addDateSortable("beginDate", beginDate);
		document.addDateSortable("endDate", endDate);
		document.addDateSortable("beginVoteDate", beginVoteDate);
		document.addDateSortable("endVoteDate", endVoteDate);
		
		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale,
		String snippet, PortletRequest portletRequest,
		PortletResponse portletResponse) throws Exception {
		Summary summary = createSummary(document, Field.TITLE, Field.URL);
		return summary;
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		BudgetPhase entry = BudgetPhaseLocalServiceUtil.getBudgetPhase(classPK);
		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEntries(companyId);		
	}

	@Override
	protected void doReindex(BudgetPhase budgetPhase) throws Exception {
		Document document = getDocument(budgetPhase);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
			budgetPhase.getCompanyId(), document, isCommitImmediately());
		
	}
	
	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = BudgetPhaseLocalServiceUtil
			.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {

				}
			});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<BudgetPhase>() {

				@Override
				public void performAction(BudgetPhase entry) {
					try {
						Document document = getDocument(entry);

						indexableActionableDynamicQuery.addDocuments(document);
					} catch (PortalException pe) {
						_log.error("Unable to index budget phase entry "
							+ entry.getBudgetPhaseId());
					}
				}

			});

		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}
	
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}

