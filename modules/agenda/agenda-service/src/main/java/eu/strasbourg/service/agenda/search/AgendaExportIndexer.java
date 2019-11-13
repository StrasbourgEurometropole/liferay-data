package eu.strasbourg.service.agenda.search;

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

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;

import eu.strasbourg.service.agenda.model.AgendaExport;
import eu.strasbourg.service.agenda.service.AgendaExportLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;

@Component(immediate = true, service = Indexer.class)
public class AgendaExportIndexer extends BaseIndexer<AgendaExport>{
	
	public static final String CLASS_NAME = AgendaExport.class.getName();

	public AgendaExportIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	
	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(AgendaExport agendaExport) throws Exception {
		deleteDocument(agendaExport.getCompanyId(), agendaExport.getAgendaExportId());
	}
	
	/**
	 * Fonction appelée lors de l'indexation de l'item C'est ici qu'on choisi
	 * les champs à indexer
	 */
	@Override
	protected Document doGetDocument(AgendaExport agendaExport) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, agendaExport);

		// On indexe toute la hiérarchie de catégories (parents et enfants des
		// catégories de l'entité)
		long[] assetCategoryIds = AssetVocabularyHelper
			.getFullHierarchyCategoriesIds(agendaExport.getCategories());
		List<AssetCategory> assetCategories = AssetVocabularyHelper
			.getFullHierarchyCategories(agendaExport.getCategories());
		document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
		addSearchAssetCategoryTitles(document, Field.ASSET_CATEGORY_TITLES,
			assetCategories);

		document.addLocalizedText(Field.TITLE, agendaExport.getTitleMap());
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
		AgendaExport entry = AgendaExportLocalServiceUtil.getAgendaExport(classPK);
		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEntries(companyId);
	}
	
	@Override
	protected void doReindex(AgendaExport agendaExport) throws Exception {
		Document document = getDocument(agendaExport);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
				agendaExport.getCompanyId(), document, isCommitImmediately());

	}
	
	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = AgendaExportLocalServiceUtil
			.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {

				}
			});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<AgendaExport>() {

				@Override
				public void performAction(AgendaExport entry) {
					try {
						Document document = getDocument(entry);

						indexableActionableDynamicQuery.addDocuments(document);
					} catch (PortalException pe) {
						_log.error("Unable to index agendaExport entry "
							+ entry.getAgendaExportId());
					}
				}

			});

		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
