package eu.strasbourg.service.place.search;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.place.model.GoogleMyBusinessHistoric;
import eu.strasbourg.service.place.service.GoogleMyBusinessHistoricLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(immediate = true, service = Indexer.class)
public class GoogleMyBusinessHistoricIndexer  extends BaseIndexer<GoogleMyBusinessHistoric> {

    public static final String CLASS_NAME = GoogleMyBusinessHistoric.class.getName();

    public GoogleMyBusinessHistoricIndexer() {
        setDefaultSelectedFieldNames(
                Field.ASSET_TAG_NAMES, Field.COMPANY_ID, Field.CONTENT,
                Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK, Field.GROUP_ID,
                Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID, Field.TITLE, Field.UID);

        setFilterSearch(true);
        setPermissionAware(true);
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    @Override
    protected void doDelete(GoogleMyBusinessHistoric googleMyBusinessHistoric) throws Exception {
        deleteDocument(googleMyBusinessHistoric.getCompanyId(), googleMyBusinessHistoric.getGoogleMyBusinessHistoricId());
    }

    /**
     * Fonction appelée lors de l'indexation de l'item C'est ici qu'on choisi
     * les champs à indexer
     */
    @Override
    protected Document doGetDocument(GoogleMyBusinessHistoric googleMyBusinessHistoric) throws Exception {
        Document document = getBaseModelDocument(CLASS_NAME, googleMyBusinessHistoric);

        // On indexe toute la hiérarchie de catégories (parents et enfants des
        // catégories de l'entité)
        long[] assetCategoryIds = AssetVocabularyHelper
                .getFullHierarchyCategoriesIds(googleMyBusinessHistoric.getCategories());
        List<AssetCategory> assetCategories = AssetVocabularyHelper
                .getFullHierarchyCategories(googleMyBusinessHistoric.getCategories());
        document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
        addSearchAssetCategoryTitles(document, Field.ASSET_CATEGORY_TITLES,
                assetCategories);

        Map<Locale, String> titleFieldMap = new HashMap<Locale, String>();
        titleFieldMap.put(Locale.FRANCE, googleMyBusinessHistoric.getResultLabel());
        document.addLocalizedText(Field.TITLE, titleFieldMap);

        Map<Locale, String> descriptionFieldMap = new HashMap<Locale, String>();
        descriptionFieldMap.put(Locale.FRANCE, googleMyBusinessHistoric.getErrorDescription());
        document.addLocalizedText(Field.DESCRIPTION, descriptionFieldMap);

        document.addNumber(Field.STATUS, googleMyBusinessHistoric.getStatus());
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
        GoogleMyBusinessHistoric entry = GoogleMyBusinessHistoricLocalServiceUtil.getGoogleMyBusinessHistoric(classPK);
        doReindex(entry);
    }

    @Override
    protected void doReindex(String[] ids) throws Exception {
        long companyId = GetterUtil.getLong(ids[0]);
        reindexEntries(companyId);
    }

    @Override
    protected void doReindex(GoogleMyBusinessHistoric googleMyBusinessHistoric) throws Exception {
        Document document = getDocument(googleMyBusinessHistoric);

        IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
                googleMyBusinessHistoric.getCompanyId(), document, isCommitImmediately());

    }

    protected void reindexEntries(long companyId) throws PortalException {
        final IndexableActionableDynamicQuery indexableActionableDynamicQuery = GoogleMyBusinessHistoricLocalServiceUtil
                .getIndexableActionableDynamicQuery();

        indexableActionableDynamicQuery.setAddCriteriaMethod(
                new ActionableDynamicQuery.AddCriteriaMethod() {
                    @Override
                    public void addCriteria(DynamicQuery dynamicQuery) {

                    }
                });
        indexableActionableDynamicQuery.setCompanyId(companyId);
        indexableActionableDynamicQuery.setPerformActionMethod(
                new ActionableDynamicQuery.PerformActionMethod<GoogleMyBusinessHistoric>() {

                    @Override
                    public void performAction(GoogleMyBusinessHistoric entry) {
                        try {
                            Document document = getDocument(entry);

                            indexableActionableDynamicQuery
                                    .addDocuments(document);
                        } catch (PortalException pe) {
                            _log.error("Unable to index anonymisationHistoric entry "
                                    + entry.getGoogleMyBusinessHistoricId());
                        }
                    }

                });

        indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
        indexableActionableDynamicQuery.performActions();
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
