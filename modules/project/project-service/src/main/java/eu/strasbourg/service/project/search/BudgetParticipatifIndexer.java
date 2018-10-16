package eu.strasbourg.service.project.search;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
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
import eu.strasbourg.service.project.model.BudgetParticipatif;
import eu.strasbourg.service.project.service.BudgetParticipatifLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author alexandre.quere
 */
@Component(immediate = true, service = Indexer.class)
public class BudgetParticipatifIndexer extends BaseIndexer<BudgetParticipatif> {

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
    public static final String CLASS_NAME = BudgetParticipatif.class.getName();

    public BudgetParticipatifIndexer() {
        setFilterSearch(true);
        setPermissionAware(true);
    }

    @Override
    protected void doDelete(BudgetParticipatif budgetParticipatif) throws Exception {
        deleteDocument(budgetParticipatif.getCompanyId(),budgetParticipatif.getBudgetParticipatifId());
    }

    @Override
    protected Document doGetDocument(BudgetParticipatif budget) {
        Document document = getBaseModelDocument(CLASS_NAME,budget);

        //On indexe toute la hierarchie de catégories (parents et enfants des catégories de l'entité)
        long[] assetCategoryIds = AssetVocabularyHelper.getFullHierarchyCategoriesIds(budget.getCategories());
        List<AssetCategory> assetCategories = AssetVocabularyHelper
                .getFullHierarchyCategories(budget.getCategories());
        document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
        addSearchAssetCategoryTitles(document, Field.ASSET_CATEGORY_TITLES,
                assetCategories);

        Map<Locale, String> titleFieldMap = new HashMap<>();
        titleFieldMap.put(Locale.FRANCE, budget.getTitle());
        titleFieldMap.put(Locale.GERMANY, budget.getTitle());
        titleFieldMap.put(Locale.ENGLISH, budget.getTitle());
        document.addLocalizedText(Field.TITLE, titleFieldMap);

        Map<Locale, String> descriptionFieldMap = new HashMap<>();
        descriptionFieldMap.put(Locale.FRANCE, budget.getDescription());
        descriptionFieldMap.put(Locale.GERMANY, budget.getDescription());
        descriptionFieldMap.put(Locale.ENGLISH, budget.getDescription());
        document.addLocalizedText(Field.DESCRIPTION, descriptionFieldMap);

        document.addNumber(Field.STATUS, budget.getStatus());
        return document;
    }

    @Override
    protected Summary doGetSummary(Document document, Locale locale,
                                   String snippet,
                                   PortletRequest portletRequest,
                                   PortletResponse portletResponse) throws Exception {
        return createSummary(document, Field.TITLE, Field.URL);
    }

    @Override
    protected void doReindex(String className, long classPK) throws Exception {
        BudgetParticipatif entry = BudgetParticipatifLocalServiceUtil.getBudgetParticipatif(classPK);
        doReindex(entry);
    }

    @Override
    protected void doReindex(String[] ids) throws Exception {
        long companyId = GetterUtil.getLong(ids[0]);
        reindexEntries(companyId);
    }

    @Override
    protected void doReindex(BudgetParticipatif budget) throws Exception {
        Document document = getDocument(budget);

        IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
                budget.getCompanyId(), document, isCommitImmediately());

    }

    protected void reindexEntries(long companyId) throws PortalException {
        final IndexableActionableDynamicQuery indexableActionableDynamicQuery = BudgetParticipatifLocalServiceUtil
                .getIndexableActionableDynamicQuery();

        indexableActionableDynamicQuery.setAddCriteriaMethod(
                dynamicQuery -> {});
        indexableActionableDynamicQuery.setCompanyId(companyId);
        indexableActionableDynamicQuery.setPerformActionMethod(
                (ActionableDynamicQuery.PerformActionMethod<BudgetParticipatif>) entry -> {
                    try {
                        Document document = getDocument(entry);

                        indexableActionableDynamicQuery.addDocuments(document);
                    } catch (PortalException pe) {
                        _log.error("Unable to index participation entry "
                                + entry.getBudgetParticipatifId());
                    }
                });

        indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
        indexableActionableDynamicQuery.performActions();
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }
}
