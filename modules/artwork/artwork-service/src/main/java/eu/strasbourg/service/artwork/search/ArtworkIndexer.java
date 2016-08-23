package eu.strasbourg.service.artwork.search;

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

import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.service.ArtworkLocalServiceUtil;

@Component(immediate = true, service = Indexer.class)
public class ArtworkIndexer extends BaseIndexer<Artwork> {

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

	public static final String CLASS_NAME = Artwork.class.getName();

	public ArtworkIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Artwork artwork) throws Exception {
		deleteDocument(artwork.getCompanyId(), artwork.getArtworkId());
	}

	/**
	 * Fonction appelée lors de l'indexation de l'item
	 * C'est ici qu'on choisi les champs à indexer
	 */
	@Override
	protected Document doGetDocument(Artwork artwork) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, artwork);
		document.addLocalizedText(Field.TITLE, artwork.getTitleMap());
		document.addLocalizedText(Field.DESCRIPTION,
			artwork.getDescriptionMap());
		document.addNumber(Field.STATUS, artwork.getStatus() ? 1 : 0);
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
		Artwork entry = ArtworkLocalServiceUtil.getArtwork(classPK);
		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEntries(companyId);
	}

	@Override
	protected void doReindex(Artwork artwork) throws Exception {
		Document document = getDocument(artwork);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
			artwork.getCompanyId(), document, isCommitImmediately());

	}

	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = ArtworkLocalServiceUtil
			.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {

				}
			});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Artwork>() {

				@Override
				public void performAction(Artwork entry) {
					try {
						Document document = getDocument(entry);

						indexableActionableDynamicQuery.addDocuments(document);
					} catch (PortalException pe) {
						System.out.println("Unable to index artwork entry "
							+ entry.getArtworkId());
					}
				}

			});

		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}

}
