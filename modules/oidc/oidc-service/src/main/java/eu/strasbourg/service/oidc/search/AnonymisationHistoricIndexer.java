package eu.strasbourg.service.oidc.search;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.util.GetterUtil;
import eu.strasbourg.service.oidc.model.AnonymisationHistoric;
import eu.strasbourg.service.oidc.service.AnonymisationHistoricLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(immediate = true, service = Indexer.class)
public class AnonymisationHistoricIndexer extends BaseIndexer<AnonymisationHistoric> {

	public static final String CLASS_NAME = AnonymisationHistoric.class.getName();

	public AnonymisationHistoricIndexer() {
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
	protected void doDelete(AnonymisationHistoric anonymisationHistoric) throws Exception {
		deleteDocument(anonymisationHistoric.getCompanyId(), anonymisationHistoric.getAnonymisationHistoricId());
	}

	/**
	 * Fonction appelée lors de l'indexation de l'item C'est ici qu'on choisi
	 * les champs à indexer
	 */
	@Override
	protected Document doGetDocument(AnonymisationHistoric anonymisationHistoric) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, anonymisationHistoric);

		// On indexe toute la hiérarchie de catégories (parents et enfants des
		// catégories de l'entité)
		long[] assetCategoryIds = AssetVocabularyHelper
				.getFullHierarchyCategoriesIds(anonymisationHistoric.getCategories());
		List<AssetCategory> assetCategories = AssetVocabularyHelper
				.getFullHierarchyCategories(anonymisationHistoric.getCategories());
		document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
		addSearchAssetCategoryTitles(document, Field.ASSET_CATEGORY_TITLES,
				assetCategories);

		Map<Locale, String> titleFieldMap = new HashMap<Locale, String>();
		titleFieldMap.put(Locale.FRANCE, anonymisationHistoric.getResultLabel());
		document.addLocalizedText(Field.TITLE, titleFieldMap);

		Map<Locale, String> descriptionFieldMap = new HashMap<Locale, String>();
		descriptionFieldMap.put(Locale.FRANCE, anonymisationHistoric.getErrorDescription());
		document.addLocalizedText(Field.DESCRIPTION, descriptionFieldMap);
		
		document.addNumber(Field.STATUS, anonymisationHistoric.getStatus());
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
		AnonymisationHistoric entry = AnonymisationHistoricLocalServiceUtil.getAnonymisationHistoric(classPK);
		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEntries(companyId);
	}

	@Override
	protected void doReindex(AnonymisationHistoric anonymisationHistoric) throws Exception {
		Document document = getDocument(anonymisationHistoric);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
				anonymisationHistoric.getCompanyId(), document, isCommitImmediately());

	}

	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = AnonymisationHistoricLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setAddCriteriaMethod(
				new ActionableDynamicQuery.AddCriteriaMethod() {
					@Override
					public void addCriteria(DynamicQuery dynamicQuery) {

					}
				});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
				new ActionableDynamicQuery.PerformActionMethod<AnonymisationHistoric>() {

					@Override
					public void performAction(AnonymisationHistoric entry) {
						try {
							Document document = getDocument(entry);

							indexableActionableDynamicQuery
									.addDocuments(document);
						} catch (PortalException pe) {
							_log.error("Unable to index anonymisationHistoric entry "
									+ entry.getAnonymisationHistoricId());
						}
					}

				});

		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
