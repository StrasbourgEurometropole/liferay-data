package eu.strasbourg.service.agenda.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.utils.DateHelper;

@Component(immediate = true, service = Indexer.class)
public class EventIndexer extends BaseIndexer<Event> {

	/**
	 * On ajoute un traitement pour utiliser le filtre sur "assetCategoryIds"
	 * comme un "et"
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
	 * Fonction appelée lors de l'indexation de l'item
	 * C'est ici qu'on choisi les champs à indexer
	 */
	@Override
	protected Document doGetDocument(Event event) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, event);
		document.addLocalizedText(Field.TITLE, event.getTitleMap());
		document.addLocalizedText(Field.DESCRIPTION,
			event.getDescriptionMap());
		document.addNumber(Field.STATUS, event.getStatus());
		
		List<Date> dates = new ArrayList<Date>();
		for (EventPeriod period : event.getEventPeriods()) {
			Date startDate = period.getStartDate();
			Date endDate = period.getEndDate();
			dates.addAll(DateHelper.getDaysBetweenDates(startDate, endDate));
		}
		document.addDateSortable("dates", dates.toArray(new Date[dates.size()]));
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
						System.out.println("Unable to index event entry "
							+ entry.getEventId());
					}
				}

			});

		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}

}
