package eu.strasbourg.service.comment.search;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(immediate = true, service = Indexer.class)
public class CommentIndexer extends BaseIndexer<Comment> {

	public static final String CLASS_NAME = Comment.class.getName();
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	public CommentIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Comment comment) throws Exception {
		deleteDocument(comment.getCompanyId(), comment.getCommentId());
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale,
		String snippet, PortletRequest portletRequest,
		PortletResponse portletResponse) throws Exception {
		return createSummary(document, Field.USER_NAME, Field.URL);
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		_log.info("doReindex");
		Comment entry = CommentLocalServiceUtil.getComment(classPK);
		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		_log.info("doReindex2");
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEntries(companyId);
	}
	
	@Override
	protected void doReindex(Comment comment) throws Exception {
		_log.info("doReindex3");
		Document document = getDocument(comment);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
			comment.getCompanyId(), document, isCommitImmediately());

	}
	
	protected void reindexEntries(long companyId) throws PortalException {
		_log.info("reindexEntries");
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = CommentLocalServiceUtil
			.getIndexableActionableDynamicQuery();
		indexableActionableDynamicQuery.setAddCriteriaMethod(dynamicQuery -> {});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
				(ActionableDynamicQuery.PerformActionMethod<Comment>) comment -> {
					try {
						Document document = getDocument(comment);
						indexableActionableDynamicQuery.addDocuments(document);
					} catch (PortalException pe) {
						_log.error("Unable to index comment comment "
							+ comment.getCommentId());
					}
				});

		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}

	@Override
	protected Document doGetDocument(Comment comment) {
		Document document = getBaseModelDocument(CLASS_NAME, comment);
		long[] assetCategorIds = AssetVocabularyHelper.getFullHierarchyCategoriesIds(comment.getCategories());
		List<AssetCategory> assetCategories = AssetVocabularyHelper.getFullHierarchyCategories(comment.getCategories());
		document.addKeyword(Field.ASSET_CATEGORY_IDS,assetCategorIds);
		addSearchAssetCategoryTitles(document,Field.ASSET_CATEGORY_TITLES,assetCategories);
		Map<Locale,String> userNameFieldMap = new HashMap<>();
		userNameFieldMap.put(Locale.FRANCE, comment.getUserName());
		document.addLocalizedText(Field.USER_NAME,userNameFieldMap);
		document.addNumber(Field.STATUS, comment.getStatus());
		document.addNumber("reportings", comment.getCountSignalements());
		document.addText("entityType",comment.getTypeAssetEntry());
		document.addText("entityName",comment.getAssetEntryTitle());
		return document;
	}
}
