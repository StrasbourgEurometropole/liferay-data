package eu.strasbourg.service.help.search;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.help.model.HelpRequest;
import eu.strasbourg.service.help.service.HelpRequestLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.*;

@Component(immediate = true, service = Indexer.class)
public class HelpRequestIndexer extends BaseIndexer<HelpRequest> {

    public static final String CLASS_NAME = HelpRequest.class.getName();
    public static final String INDEX_DATES = "dates";

    public HelpRequestIndexer() {
        setFilterSearch(true);
        setPermissionAware(true);
    }

    @Override
    protected void doDelete(HelpRequest helpRequest) throws Exception {
        deleteDocument(helpRequest.getCompanyId(), helpRequest.getHelpRequestId());
    }

    @Override
    protected Document doGetDocument(HelpRequest helpRequest) throws Exception {
        Document document = getBaseModelDocument(CLASS_NAME, helpRequest);

        long[] assetCategoryIds = AssetVocabularyHelper
                .getFullHierarchyCategoriesIds(helpRequest.getCategories());
        List<AssetCategory> assetCategories = AssetVocabularyHelper
                .getFullHierarchyCategories(helpRequest.getCategories());
        document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
        addSearchAssetCategoryTitles(document, Field.ASSET_CATEGORY_TITLES,
                assetCategories);

        Map<Locale, String> helpProposalTitle = new HashMap<>();
        helpProposalTitle.put(Locale.FRANCE, helpRequest.getHelpProposal().getTitle());
        helpProposalTitle.put(Locale.GERMANY, helpRequest.getHelpProposal().getTitle());
        helpProposalTitle.put(Locale.ENGLISH, helpRequest.getHelpProposal().getTitle());
        document.addLocalizedText(Field.TITLE, helpProposalTitle);

        Map<Locale, String> helpSeeker = new HashMap<>();
        helpSeeker.put(Locale.FRANCE, helpRequest.getAuthorNameLabel());
        helpSeeker.put(Locale.GERMANY, helpRequest.getAuthorNameLabel());
        helpSeeker.put(Locale.ENGLISH, helpRequest.getAuthorNameLabel());
        document.addLocalizedText(Field.DESCRIPTION, helpSeeker);

        document.addDateSortable(Field.CREATE_DATE, new Date[]{helpRequest.getCreateDate()});

        return document;
    }

    @Override
    protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest, PortletResponse portletResponse) throws Exception {
        Summary summary = createSummary(document, Field.TITLE, Field.URL);
        return summary;
    }

    @Override
    protected void doReindex(String className, long classPK) throws Exception {
        HelpRequest entry = HelpRequestLocalServiceUtil.getHelpRequest(classPK);
        doReindex(entry);
    }

    @Override
    protected void doReindex(String[] ids) throws Exception {
        long companyId = GetterUtil.getLong(ids[0]);
        reindexEntries(companyId);
    }

    protected void reindexEntries(long companyId) throws PortalException {
        final IndexableActionableDynamicQuery indexableActionableDynamicQuery = HelpRequestLocalServiceUtil
                .getIndexableActionableDynamicQuery();

        indexableActionableDynamicQuery.setAddCriteriaMethod(
                new ActionableDynamicQuery.AddCriteriaMethod() {
                    @Override
                    public void addCriteria(DynamicQuery dynamicQuery) {

                    }
                });
        indexableActionableDynamicQuery.setCompanyId(companyId);
        indexableActionableDynamicQuery.setPerformActionMethod(
                new ActionableDynamicQuery.PerformActionMethod<HelpRequest>() {

                    @Override
                    public void performAction(HelpRequest entry) {
                        try {
                            Document document = getDocument(entry);

                            indexableActionableDynamicQuery.addDocuments(document);
                        } catch (PortalException pe) {
                            _log.error("Unable to index helpRequest entry "
                                    + entry.getHelpRequestId());
                        }
                    }

                });

        indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
        indexableActionableDynamicQuery.performActions();
    }

    private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

    @Override
    protected void doReindex(HelpRequest helpRequest) throws Exception {
        Document document = getDocument(helpRequest);

        IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
                helpRequest.getCompanyId(), document, isCommitImmediately());
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }
}
