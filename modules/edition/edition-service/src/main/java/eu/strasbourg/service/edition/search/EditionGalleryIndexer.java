package eu.strasbourg.service.edition.search;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.TermsFilter;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.EditionGalleryLocalServiceUtil;

@Component(immediate = true, service = Indexer.class)
public class EditionGalleryIndexer extends BaseIndexer<EditionGallery> {

	/**
	 * On ajoute un traitement pour utiliser le filtre sur "assetCategoryIds" comme un "et"
	 */
	@Override
	public void postProcessContextBooleanFilter(
		BooleanFilter contextBooleanFilter, SearchContext searchContext)
		throws Exception {

		long[] categoryIds = searchContext.getAssetCategoryIds();
		for (long categoryId : categoryIds) {
			TermsFilter categoryIdTermsFilter = new TermsFilter(
				Field.ASSET_CATEGORY_IDS);
			categoryIdTermsFilter.addValue(String.valueOf(categoryId));
			contextBooleanFilter.add(categoryIdTermsFilter,
				BooleanClauseOccur.MUST);
		}
		super.postProcessContextBooleanFilter(contextBooleanFilter,
			searchContext);
	}

	public static final String CLASS_NAME = EditionGallery.class.getName();

	public EditionGalleryIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(EditionGallery editionGallery) throws Exception {
		deleteDocument(editionGallery.getCompanyId(),
			editionGallery.getGalleryId());
	}

	/**
	 * Fonction appelée lors de l'indexation de l'item
	 * C'est ici qu'on choisi les champs à indexer
	 */
	@Override
	protected Document doGetDocument(EditionGallery editionGallery)
		throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, editionGallery);
		document.addLocalizedText(Field.TITLE, editionGallery.getTitleMap());
		document.addLocalizedText(Field.DESCRIPTION,
			editionGallery.getDescriptionMap());
		document.addNumber(Field.STATUS, editionGallery.getStatus() ? 1 : 0);
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
		EditionGallery entry = EditionGalleryLocalServiceUtil
			.getEditionGallery(classPK);
		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEntries(companyId);
	}

	@Override
	protected void doReindex(EditionGallery editionGallery) throws Exception {
		Document document = getDocument(editionGallery);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
			editionGallery.getCompanyId(), document, isCommitImmediately());

	}

	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = EditionGalleryLocalServiceUtil
			.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {

				}
			});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<EditionGallery>() {

				@Override
				public void performAction(EditionGallery entry) {
					try {
						Document document = getDocument(entry);

						indexableActionableDynamicQuery.addDocuments(document);
					} catch (PortalException pe) {
						System.out.println("Unable to index edition entry "
							+ entry.getGalleryId());
					}
				}

			});

		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}

}
