package eu.strasbourg.service.council.service.search;

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
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Type;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
import eu.strasbourg.service.council.service.TypeLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(
        immediate = true,
        service = Indexer.class
)
public class TypeIndexer extends BaseIndexer<Type> {

    public static final String CLASS_NAME = Type.class.getName();

    public TypeIndexer() {
        setFilterSearch(true);
        setPermissionAware(true);
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    @Override
    protected void doDelete(Type type) throws Exception {
        deleteDocument(type.getCompanyId(), type.getTypeId());
    }

    /**
     * Fonction appelée lors de l'indexation de l'item
     * C'est ici qu'on choisi les champs à indexer
     */
    @Override
    protected Document doGetDocument(Type type) {
        Document document = getBaseModelDocument(CLASS_NAME, type);

        // On indexe toute la hiérarchie de catégories (parents et enfants des
        // catégories de l'entité)
        long[] assetCategoryIds = AssetVocabularyHelper
                .getFullHierarchyCategoriesIds(type.getCategories());
        List<AssetCategory> assetCategories = AssetVocabularyHelper
                .getFullHierarchyCategories(type.getCategories());
        document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
        addSearchAssetCategoryTitles(document, Field.ASSET_CATEGORY_TITLES, assetCategories);

        Map<Locale, String> titleFieldMap = new HashMap<>();
        titleFieldMap.put(Locale.FRANCE, type.getTitle());

        Map<Locale, String> descriptionFieldMap = new HashMap<>();
        descriptionFieldMap.put(Locale.FRANCE, type.getTitle());

        document.addLocalizedText(Field.TITLE, titleFieldMap);
        document.addLocalizedText(Field.DESCRIPTION, descriptionFieldMap);
        document.addNumber(Field.STATUS, type.getStatus());
        return document;
    }

    @Override
    protected Summary doGetSummary(Document document, Locale locale,
                                   String snippet, PortletRequest portletRequest,
                                   PortletResponse portletResponse) {
        return createSummary(document, Field.TITLE, Field.URL);
    }

    @Override
    protected void doReindex(String className, long classPK) throws Exception {
        Type entry = TypeLocalServiceUtil.getType(classPK);
        doReindex(entry);
    }

    @Override
    protected void doReindex(String[] ids) throws Exception {
        long companyId = GetterUtil.getLong(ids[0]);
        reindexEntries(companyId);
    }

    @Override
    protected void doReindex(Type type) throws Exception {
        Document document = getDocument(type);

        IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
                type.getCompanyId(), document, isCommitImmediately());

    }

    protected void reindexEntries(long companyId) throws PortalException {
        final IndexableActionableDynamicQuery indexableActionableDynamicQuery = TypeLocalServiceUtil
                .getIndexableActionableDynamicQuery();

        indexableActionableDynamicQuery.setAddCriteriaMethod(
                new ActionableDynamicQuery.AddCriteriaMethod() {
                    @Override
                    public void addCriteria(DynamicQuery dynamicQuery) {

                    }
                });
        indexableActionableDynamicQuery.setCompanyId(companyId);
        indexableActionableDynamicQuery.setPerformActionMethod(
                new ActionableDynamicQuery.PerformActionMethod<Type>() {

                    @Override
                    public void performAction(Type entry) {
                        try {
                            Document document = getDocument(entry);

                            indexableActionableDynamicQuery.addDocuments(document);
                        } catch (PortalException pe) {
                            _log.error("Unable to index Type entry "
                                    + entry.getTypeId());
                        }
                    }

                });

        indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
        indexableActionableDynamicQuery.performActions();
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
