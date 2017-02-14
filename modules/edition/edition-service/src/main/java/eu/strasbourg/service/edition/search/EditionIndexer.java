package eu.strasbourg.service.edition.search;

import java.util.List;
import java.util.Locale;

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
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.service.EditionLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;

@Component(immediate = true, service = Indexer.class)
public class EditionIndexer extends BaseIndexer<Edition> {

	public static final String CLASS_NAME = Edition.class.getName();

	public EditionIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Edition edition) throws Exception {
		deleteDocument(edition.getCompanyId(), edition.getEditionId());
	}

	/**
	 * Fonction appel�e lors de l'indexation de l'item
	 * C'est ici qu'on choisi les champs � indexer
	 */
	@Override
	protected Document doGetDocument(Edition edition) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, edition);

		// On indexe toute la hiérarchie de catégories (parents et enfants des
		// catégories de l'entité)
		long[] assetCategoryIds = AssetVocabularyHelper
			.getFullHierarchyCategoriesIds(edition.getCategories());
		List<AssetCategory> assetCategories = AssetVocabularyHelper
			.getFullHierarchyCategories(edition.getCategories());
		document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
		addSearchAssetCategoryTitles(document, Field.ASSET_CATEGORY_TITLES,
			assetCategories);
		
		document.addLocalizedText(Field.TITLE, edition.getTitleMap());
		document.addLocalizedText(Field.DESCRIPTION,
			edition.getDescriptionMap());
		document.addNumber(Field.STATUS, edition.getStatus());
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
		Edition entry = EditionLocalServiceUtil.getEdition(classPK);
		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEntries(companyId);
	}

	@Override
	protected void doReindex(Edition edition) throws Exception {
		Document document = getDocument(edition);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
			edition.getCompanyId(), document, isCommitImmediately());

	}

	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = EditionLocalServiceUtil
			.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {

				}
			});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Edition>() {

				@Override
				public void performAction(Edition entry) {
					try {
						Document document = getDocument(entry);

						indexableActionableDynamicQuery.addDocuments(document);
					} catch (PortalException pe) {
						_log.error("Unable to index edition entry "
							+ entry.getEditionId());
					}
				}

			});

		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
