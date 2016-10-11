package eu.strasbourg.service.video.search;

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

import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.service.VideoGalleryLocalServiceUtil;

@Component(immediate = true, service = Indexer.class)
public class VideoGalleryIndexer extends BaseIndexer<VideoGallery> {

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

	public static final String CLASS_NAME = VideoGallery.class.getName();

	public VideoGalleryIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(VideoGallery videoGallery) throws Exception {
		deleteDocument(videoGallery.getCompanyId(),
			videoGallery.getGalleryId());
	}

	/**
	 * Fonction appelée lors de l'indexation de l'item
	 * C'est ici que l'on choisi les champs à indexer
	 */
	@Override
	protected Document doGetDocument(VideoGallery videoGallery)
		throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, videoGallery);
		document.addLocalizedText(Field.TITLE, videoGallery.getTitleMap());
		document.addLocalizedText(Field.DESCRIPTION,
			videoGallery.getDescriptionMap());
		document.addNumber(Field.STATUS, videoGallery.getStatus());
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
		VideoGallery entry = VideoGalleryLocalServiceUtil
			.getVideoGallery(classPK);
		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEntries(companyId);
	}

	@Override
	protected void doReindex(VideoGallery videoGallery) throws Exception {
		Document document = getDocument(videoGallery);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
			videoGallery.getCompanyId(), document, isCommitImmediately());

	}

	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = VideoGalleryLocalServiceUtil
			.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {

				}
			});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<VideoGallery>() {

				@Override
				public void performAction(VideoGallery entry) {
					try {
						Document document = getDocument(entry);

						indexableActionableDynamicQuery.addDocuments(document);
					} catch (PortalException pe) {
						System.out.println("Unable to index video entry "
							+ entry.getGalleryId());
					}
				}

			});

		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}

}
