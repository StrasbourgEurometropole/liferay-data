package eu.strasbourg.service.project.search;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

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
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.service.ParticipationLocalServiceUtil;

import eu.strasbourg.utils.AssetVocabularyHelper;

public class ParticipationIndexer extends BaseIndexer<Participation> {

	public static final String CLASS_NAME = Participation.class.getName();
	
	public ParticipationIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}
	
	@Override
	public String getClassName() {
		return CLASS_NAME;
	}
	
	@Override
	protected void doDelete(Participation participation) throws Exception {
		deleteDocument(participation.getCompanyId(), participation.getParticipationId());
	}
	
	/**
	 * Fonction appelée lors de l'indexation de l'item
	 * C'est ici qu'on choisi les champs à indexer
	 */
	@Override
	protected Document doGetDocument(Participation participation) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, participation);
	
		// On indexe toute la hiérarchie de catégories (parents et enfants des
		// catégories de l'entité)
		long[] assetCategoryIds = AssetVocabularyHelper
			.getFullHierarchyCategoriesIds(participation.getCategories());
		List<AssetCategory> assetCategories = AssetVocabularyHelper
			.getFullHierarchyCategories(participation.getCategories());
		document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
		addSearchAssetCategoryTitles(document, Field.ASSET_CATEGORY_TITLES,
			assetCategories);
		
		document.addKeyword(Field.TITLE, participation.getTitle());
		document.addNumber(Field.STATUS, participation.getStatus());
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
		Participation entry = ParticipationLocalServiceUtil.getParticipation(classPK);
		doReindex(entry);
	}
	
	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEntries(companyId);		
	}
	
	@Override
	protected void doReindex(Participation participation) throws Exception {
		Document document = getDocument(participation);
	
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
				participation.getCompanyId(), document, isCommitImmediately());
		
	}
	
	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = ParticipationLocalServiceUtil
			.getIndexableActionableDynamicQuery();
	
		indexableActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
	
				}
			});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Participation>() {
	
				@Override
				public void performAction(Participation entry) {
					try {
						Document document = getDocument(entry);
	
						indexableActionableDynamicQuery.addDocuments(document);
					} catch (PortalException pe) {
						_log.error("Unable to index participation entry "
							+ entry.getParticipationId());
					}
				}
	
			});
	
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}
	
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

}
