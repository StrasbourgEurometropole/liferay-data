package eu.strasbourg.service.agenda.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

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

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.DateHelper;

@Component(immediate = true, service = Indexer.class)
public class EventIndexer extends BaseIndexer<Event> {

	public static final String CLASS_NAME = Event.class.getName();

	public EventIndexer() {
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Event event) throws Exception {
		deleteDocument(event.getCompanyId(), event.getEventId());
	}

	/**
	 * Fonction appelée lors de l'indexation de l'item C'est ici qu'on choisi
	 * les champs à indexer
	 */
	@Override
	protected Document doGetDocument(Event event) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, event);

		// On indexe toute la hiérarchie de catégories (parents et enfants des
		// catégories de l'entité)
		long[] assetCategoryIds = AssetVocabularyHelper
			.getFullHierarchyCategoriesIds(event.getCategories());
		List<AssetCategory> assetCategories = AssetVocabularyHelper
			.getFullHierarchyCategories(event.getCategories());
		document.addKeyword(Field.ASSET_CATEGORY_IDS, assetCategoryIds);
		addSearchAssetCategoryTitles(document, Field.ASSET_CATEGORY_TITLES,
			assetCategories);

		
		Map<Locale, String> titleFieldMap = new HashMap<Locale, String>();
		for (Entry<Locale, String> titleEntry : event.getTitleMap().entrySet()) {
			Locale locale = titleEntry.getKey();
			String eventTitle = titleEntry.getValue();
			String eventPlaceAlias = event.getPlaceAlias(locale);
			titleFieldMap.put(locale, eventTitle + " " + eventPlaceAlias);
		}
		document.addLocalizedText(Field.TITLE, titleFieldMap);
		
		document.addLocalizedText(Field.DESCRIPTION, event.getDescriptionMap());
		document.addNumber(Field.STATUS, event.getStatus());
		
		List<Date> dates = new ArrayList<Date>();
		Date now = new Date();
		for (EventPeriod period : event.getEventPeriods()) {
			Date startDate = period.getStartDate().after(now) ?  period.getStartDate() : now;
			Date endDate = period.getEndDate();
			dates.addAll(DateHelper.getDaysBetweenDates(startDate, endDate));
		}
		document.addDateSortable("dates",
			dates.toArray(new Date[dates.size()]));
		document.addDateSortable("startDate", event.getFirstStartDate());
		document.addDateSortable("endDate", event.getLastEndDate());
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
		Event entry = EventLocalServiceUtil.getEvent(classPK);
		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEntries(companyId);
	}

	@Override
	protected void doReindex(Event event) throws Exception {
		Document document = getDocument(event);

		IndexWriterHelperUtil.updateDocument(getSearchEngineId(),
			event.getCompanyId(), document, isCommitImmediately());

	}

	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = EventLocalServiceUtil
			.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {

				}
			});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Event>() {

				@Override
				public void performAction(Event entry) {
					try {
						Document document = getDocument(entry);

						indexableActionableDynamicQuery.addDocuments(document);
					} catch (PortalException pe) {
						_log.error("Unable to index event entry "
							+ entry.getEventId());
					}
				}

			});

		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
