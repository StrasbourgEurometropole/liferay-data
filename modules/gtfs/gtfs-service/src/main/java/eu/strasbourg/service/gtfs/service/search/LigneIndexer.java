package eu.strasbourg.service.gtfs.service.search;

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

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;

import eu.strasbourg.service.gtfs.model.Ligne;
import eu.strasbourg.service.gtfs.service.LigneLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;

@Component(
	immediate = true, 
	service = Indexer.class
)
public class LigneIndexer extends BaseIndexer<Ligne> {
	
	public static final String CLASS_NAME = Ligne.class.getName();
	
	public LigneIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Ligne ligne) throws Exception {
		deleteDocument(ligne.getCompanyId(), ligne.getLigneId());
	}

	/**
	 * Fonction appelée lors de l'indexation de l'item
	 * C'est ici qu'on choisi les champs à indexer
	 */
	@Override
	protected Document doGetDocument(Ligne ligne) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, ligne);

		// On indexe toute la hiérarchie de catégories (parents et enfants des
		// catégories de l'entité)
		long[] assetCategoryIds = AssetVocabularyHelper
			.getFullHierarchyCategoriesIds(ligne.getCategories());
		List<AssetCategory> assetCategories = AssetVocabularyHelper
			.getFullHierarchyCategories(ligne.getCategories());
		document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
		addSearchAssetCategoryTitles(document, Field.ASSET_CATEGORY_TITLES,
			assetCategories);
		
		Map<Locale, String> titleFieldMap = new HashMap<Locale, String>();
		titleFieldMap.put(Locale.FRANCE, ligne.getTitle());
		
		Map<Locale, String> descriptionFieldMap = new HashMap<Locale, String>();
		descriptionFieldMap.put(Locale.FRANCE, ligne.getShortName());
		
		document.addLocalizedText(Field.TITLE, titleFieldMap);
		document.addLocalizedText(Field.DESCRIPTION, descriptionFieldMap);
		document.addNumber(Field.STATUS, ligne.getStatus());
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
		Ligne entry = LigneLocalServiceUtil.getLigne(classPK);
		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEntries(companyId);		
	}

	@Override
	protected void doReindex(Ligne ligne) throws Exception {
		Document document = getDocument(ligne);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
				ligne.getCompanyId(), document, isCommitImmediately());
		
	}
	
	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = LigneLocalServiceUtil
			.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {

				}
			});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Ligne>() {

				@Override
				public void performAction(Ligne entry) {
					try {
						Document document = getDocument(entry);
						
						indexableActionableDynamicQuery.addDocuments(document);
					} catch (PortalException pe) {
						_log.error("Unable to index Ligne entry "
							+ entry.getLigneId());
					}
				}

			});

		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}
	
	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	
}
