package eu.strasbourg.service.project.search;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.service.InitiativeLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;

@Component(immediate = true, service = Indexer.class)
public class InitiativeIndexer extends BaseIndexer<Initiative> {

	public static final String CLASS_NAME = Initiative.class.getName();
	
	public InitiativeIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}
	
	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Initiative initiative) throws Exception {
		deleteDocument(initiative.getCompanyId(), initiative.getInitiativeId());
	}

	/**
	 * Fonction appelée lors de l'indexation de l'item
	 * C'est ici qu'on choisi les champs à indexer
	 */
	@Override
	protected Document doGetDocument(Initiative initiative) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, initiative);
	
		// On indexe toute la hiérarchie de catégories (parents et enfants des
		// catégories de l'entité)
		long[] assetCategoryIds = AssetVocabularyHelper
			.getFullHierarchyCategoriesIds(initiative.getCategories());
		List<AssetCategory> assetCategories = AssetVocabularyHelper
			.getFullHierarchyCategories(initiative.getCategories());
		document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
		addSearchAssetCategoryTitles(document, Field.ASSET_CATEGORY_TITLES,
			assetCategories);
		
		Map<Locale, String> titleFieldMap = new HashMap<Locale, String>();
		titleFieldMap.put(Locale.FRANCE, initiative.getTitle());
		titleFieldMap.put(Locale.GERMANY, initiative.getTitle());
		titleFieldMap.put(Locale.ENGLISH, initiative.getTitle());
		document.addLocalizedText(Field.TITLE, titleFieldMap);
		
		Map<Locale, String> descriptionFieldMap = new HashMap<Locale, String>();
		descriptionFieldMap.put(Locale.FRANCE, initiative.getDescription());
		descriptionFieldMap.put(Locale.GERMANY, initiative.getDescription());
		descriptionFieldMap.put(Locale.ENGLISH, initiative.getDescription());
		document.addLocalizedText(Field.DESCRIPTION, descriptionFieldMap);
		
		document.addNumber(Field.STATUS, initiative.getStatus());
		
		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale, String snippet, PortletRequest portletRequest,
			PortletResponse portletResponse) throws Exception {
		Summary summary = createSummary(document, Field.TITLE, Field.URL);
		return summary;
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Initiative entry = InitiativeLocalServiceUtil.getInitiative(classPK);
		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEntries(companyId);
	}
	
	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = InitiativeLocalServiceUtil
			.getIndexableActionableDynamicQuery();
	
		indexableActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
	
				}
			});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Initiative>() {
	
				@Override
				public void performAction(Initiative entry) {
					try {
						Document document = getDocument(entry);
	
						indexableActionableDynamicQuery.addDocuments(document);
					} catch (PortalException pe) {
						_log.error("Unable to index initiative entry "
							+ entry.getInitiativeId());
					}
				}
	
			});
	
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	@Override
	protected void doReindex(Initiative initiative) throws Exception {
		Document document = getDocument(initiative);
		
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
				initiative.getCompanyId(), document, isCommitImmediately());
	}

}
