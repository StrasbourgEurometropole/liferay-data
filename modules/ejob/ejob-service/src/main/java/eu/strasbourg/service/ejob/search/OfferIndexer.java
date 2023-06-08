package eu.strasbourg.service.ejob.search;

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
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.service.ejob.service.OfferLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.DateHelper;
import org.osgi.service.component.annotations.Component;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component(immediate = true, service = Indexer.class)
public class OfferIndexer extends BaseIndexer<Offer> {

	public static final String CLASS_NAME = Offer.class.getName();

	public OfferIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Offer offer) throws Exception {
		deleteDocument(offer.getCompanyId(), offer.getOfferId());
	}

	/**
	 * Fonction appel�e lors de l'indexation de l'item
	 * C'est ici qu'on choisi les champs � indexer
	 */
	@Override
	protected Document doGetDocument(Offer offer) {
		Document document = getBaseModelDocument(CLASS_NAME, offer);

		// On indexe toute la hiérarchie de catégories (parents et enfants des
		// catégories de l'entité)
		long[] assetCategoryIds = AssetVocabularyHelper
			.getFullHierarchyCategoriesIds(offer.getCategories());
		List<AssetCategory> assetCategories = AssetVocabularyHelper
			.getFullHierarchyCategories(offer.getCategories());
		document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
		addSearchAssetCategoryTitles(document, Field.ASSET_CATEGORY_TITLES,
			assetCategories);
		
		document.addLocalizedText(Field.TITLE, offer.getPostMap());

		Map<Locale, String> descriptionMap = new HashMap<Locale, String>();
		Map<Locale, String> contentMap = new HashMap<Locale, String>();

		for (Map.Entry<Locale, String> titleMap : offer.getPostMap().entrySet()) {
			Locale locale = titleMap.getKey();

			// On ajoute les données du bloc gris
			String description = offer.getPermanentDescription(locale);
			description += " " + offer.getFullTimeDescription(locale);
			description += " " + offer.getDuration(locale);
			descriptionMap.put(locale, description);


			// On ajoute les autres données
			String content = offer.getIntroduction(locale);
			content += " " + offer.getActivities(locale);
			content += " " + offer.getProfil(locale);
			content += " " + offer.getAvantages(locale);
			content += " " + offer.getConditions(locale);
			contentMap.put(locale, content);
		}
		document.addLocalizedText(Field.DESCRIPTION, descriptionMap);
		document.addLocalizedText(Field.CONTENT, contentMap);
		document.addNumber(Field.STATUS, offer.getStatus());
		document.addNumber("emailSend", offer.getEmailSend());

		List<Date> dates = new ArrayList<Date>();
		Date startDate = offer.getPublicationStartDate();
		Date endDate = offer.getPublicationEndDate();
		dates.addAll(DateHelper.getDaysBetweenDates(startDate, endDate));
		document.addDateSortable("dates",
				dates.toArray(new Date[dates.size()]));
		document.addDateSortable("startDate", offer.getPublicationStartDate());
		document.addDateSortable("endDate", offer.getPublicationEndDate());
		return document;
	}

	@Override
	protected Summary doGetSummary(Document document, Locale locale,
		String snippet, PortletRequest portletRequest,
		PortletResponse portletResponse) {
		Summary summary = createSummary(document, Field.TITLE, Field.URL);
		return summary;
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		Offer entry = OfferLocalServiceUtil.getOffer(classPK);
		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEntries(companyId);
	}

	@Override
	protected void doReindex(Offer offer) throws Exception {
		Document document = getDocument(offer);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
				offer.getCompanyId(), document, isCommitImmediately());

	}

	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = OfferLocalServiceUtil
			.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {

				}
			});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Offer>() {

				@Override
				public void performAction(Offer entry) {
					try {
						Document document = getDocument(entry);

						indexableActionableDynamicQuery.addDocuments(document);
					} catch (PortalException pe) {
						_log.error("Unable to index offer entry "
							+ entry.getOfferId());
					}
				}

			});

		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
