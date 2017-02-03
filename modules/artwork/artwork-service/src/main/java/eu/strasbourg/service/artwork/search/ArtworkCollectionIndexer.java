package eu.strasbourg.service.artwork.search;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;

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

import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.service.ArtworkCollectionLocalServiceUtil;

@Component(immediate = true, service = Indexer.class)
public class ArtworkCollectionIndexer extends BaseIndexer<ArtworkCollection> {

	public static final String CLASS_NAME = ArtworkCollection.class.getName();

	public ArtworkCollectionIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(ArtworkCollection artworkCollection) throws Exception {
		deleteDocument(artworkCollection.getCompanyId(), artworkCollection.getCollectionId());
	}

	/**
	 * Fonction appel�e lors de l'indexation de l'item
	 * C'est ici qu'on choisi les champs � indexer
	 */
	@Override
	protected Document doGetDocument(ArtworkCollection artworkCollection) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, artworkCollection);
		document.addLocalizedText(Field.TITLE, artworkCollection.getTitleMap());
		document.addLocalizedText(Field.DESCRIPTION,
			artworkCollection.getDescriptionMap());
		document.addNumber(Field.STATUS, artworkCollection.getStatus());
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
		ArtworkCollection entry = ArtworkCollectionLocalServiceUtil.getArtworkCollection(classPK);
		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEntries(companyId);
	}

	@Override
	protected void doReindex(ArtworkCollection artworkCollection) throws Exception {
		Document document = getDocument(artworkCollection);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
			artworkCollection.getCompanyId(), document, isCommitImmediately());

	}

	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = ArtworkCollectionLocalServiceUtil
			.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {

				}
			});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<ArtworkCollection>() {

				@Override
				public void performAction(ArtworkCollection entry) {
					try {
						Document document = getDocument(entry);

						indexableActionableDynamicQuery.addDocuments(document);
					} catch (PortalException pe) {
						_log.error("Unable to index artworkCollection entry "
							+ entry.getCollectionId());
					}
				}

			});

		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
