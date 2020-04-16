package eu.strasbourg.service.council.service.search;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.service.CouncilSessionLocalServiceUtil;
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
public class CouncilSessionIndexer extends BaseIndexer<CouncilSession> {

    public static final String CLASS_NAME = CouncilSession.class.getName();

    public CouncilSessionIndexer() {
        setFilterSearch(true);
        setPermissionAware(true);
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    @Override
    protected void doDelete(CouncilSession councilSession) throws Exception {
        deleteDocument(councilSession.getCompanyId(), councilSession.getCouncilSessionId());
    }

    /**
     * Fonction appelée lors de l'indexation de l'item
     * C'est ici qu'on choisi les champs à indexer
     */
    @Override
    protected Document doGetDocument(CouncilSession deliberation) {
        Document document = getBaseModelDocument(CLASS_NAME, deliberation);

        // On indexe toute la hiérarchie de catégories (parents et enfants des
        // catégories de l'entité)
        long[] assetCategoryIds = AssetVocabularyHelper
                .getFullHierarchyCategoriesIds(deliberation.getCategories());
        List<AssetCategory> assetCategories = AssetVocabularyHelper
                .getFullHierarchyCategories(deliberation.getCategories());
        document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
        addSearchAssetCategoryTitles(document, Field.ASSET_CATEGORY_TITLES, assetCategories);

        Map<Locale, String> titleFieldMap = new HashMap<>();
        titleFieldMap.put(Locale.FRANCE, deliberation.getTitle());

        Map<Locale, String> descriptionFieldMap = new HashMap<>();
        descriptionFieldMap.put(Locale.FRANCE, deliberation.getTitle());

        document.addLocalizedText(Field.TITLE, titleFieldMap);
        document.addLocalizedText(Field.DESCRIPTION, descriptionFieldMap);
        document.addNumber(Field.STATUS, deliberation.getStatus());
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
        CouncilSession entry = CouncilSessionLocalServiceUtil.getCouncilSession(classPK);
        doReindex(entry);
    }

    @Override
    protected void doReindex(String[] ids) throws Exception {
        long companyId = GetterUtil.getLong(ids[0]);
        reindexEntries(companyId);
    }

    @Override
    protected void doReindex(CouncilSession deliberation) throws Exception {
        Document document = getDocument(deliberation);

        IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
                deliberation.getCompanyId(), document, isCommitImmediately());

    }

    protected void reindexEntries(long companyId) throws PortalException {
        final IndexableActionableDynamicQuery indexableActionableDynamicQuery = CouncilSessionLocalServiceUtil
                .getIndexableActionableDynamicQuery();

        indexableActionableDynamicQuery.setAddCriteriaMethod(
                new ActionableDynamicQuery.AddCriteriaMethod() {
                    @Override
                    public void addCriteria(DynamicQuery dynamicQuery) {

                    }
                });
        indexableActionableDynamicQuery.setCompanyId(companyId);
        indexableActionableDynamicQuery.setPerformActionMethod(
                new ActionableDynamicQuery.PerformActionMethod<CouncilSession>() {

                    @Override
                    public void performAction(CouncilSession entry) {
                        try {
                            Document document = getDocument(entry);

                            indexableActionableDynamicQuery.addDocuments(document);
                        } catch (PortalException pe) {
                            _log.error("Unable to index CouncilSession entry "
                                    + entry.getCouncilSessionId());
                        }
                    }

                });

        indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
        indexableActionableDynamicQuery.performActions();
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
