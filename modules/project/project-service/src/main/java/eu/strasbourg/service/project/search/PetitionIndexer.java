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
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
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
public class PetitionIndexer extends BaseIndexer<Petition> {

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
    public static final String CLASS_NAME = Petition.class.getName();

    public PetitionIndexer() {
        setFilterSearch(true);
        setPermissionAware(true);
    }

    @Override
    protected void doDelete(Petition petition) throws Exception {
        deleteDocument(petition.getCompanyId(),petition.getPetitionId());
    }

    @Override
    protected Document doGetDocument(Petition petition) throws Exception {
        Document document = getBaseModelDocument(CLASS_NAME, petition);

        // On indexe toute la hiérarchie de catégories (parents et enfants des
        // catégories de l'entité)
        long[] assetCategoryIds = AssetVocabularyHelper
                .getFullHierarchyCategoriesIds(petition.getCategories());
        List<AssetCategory> assetCategories = AssetVocabularyHelper
                .getFullHierarchyCategories(petition.getCategories());
        document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
        addSearchAssetCategoryTitles(document, Field.ASSET_CATEGORY_TITLES,
                assetCategories);

        Map<Locale, String> titleFieldMap = new HashMap<>();
        titleFieldMap.put(Locale.FRANCE, petition.getTitle());
        titleFieldMap.put(Locale.GERMANY, petition.getTitle());
        titleFieldMap.put(Locale.ENGLISH, petition.getTitle());

        Map<Locale, String> descriptionFieldMap = new HashMap<>();
        descriptionFieldMap.put(Locale.FRANCE, petition.getDescription());

        document.addLocalizedText(Field.TITLE, titleFieldMap);
        document.addLocalizedText(Field.DESCRIPTION, descriptionFieldMap);
        document.addNumber(Field.STATUS, petition.getStatus());
        return document;
    }

    @Override
    protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest, PortletResponse portletResponse) throws Exception {
        return createSummary(document, Field.TITLE,Field.URL);
    }

    @Override
    protected void doReindex(String className, long classPK) throws Exception {
        Petition petition = PetitionLocalServiceUtil.getPetition(classPK);
        doReindex(petition);
    }

    @Override
    protected void doReindex(String[] ids) throws Exception {
        long companyId = GetterUtil.getLong(ids[0]);
        reindexEntries(companyId);
    }

    @Override
    protected void doReindex(Petition petition) throws Exception {
        Document document = getDocument(petition);
        IndexWriterHelperUtil.updateDocument(getSearchEngineId(),petition.getCompanyId(),document,isCommitImmediately());
    }

    protected void reindexEntries(long companyId) throws PortalException {
        final IndexableActionableDynamicQuery indexableActionableDynamicQuery = PetitionLocalServiceUtil.getIndexableActionableDynamicQuery();
        indexableActionableDynamicQuery.setAddCriteriaMethod(dynamicQuery -> {
        });
        indexableActionableDynamicQuery.setCompanyId(companyId);
        indexableActionableDynamicQuery.setPerformActionMethod((ActionableDynamicQuery
                .PerformActionMethod<Petition>) petition -> {
            Document document = getDocument(petition);
            indexableActionableDynamicQuery.addDocuments(document);
        });
        indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
        indexableActionableDynamicQuery.performActions();
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }
}
