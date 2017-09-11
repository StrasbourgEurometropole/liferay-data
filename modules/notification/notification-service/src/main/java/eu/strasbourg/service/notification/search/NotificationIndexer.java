package eu.strasbourg.service.notification.search;

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
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelperUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import eu.strasbourg.service.notification.model.Notification;
import eu.strasbourg.service.notification.service.NotificationLocalServiceUtil;

@Component(immediate = true, service = Indexer.class)
public class NotificationIndexer extends BaseIndexer<Notification> {

	public static final String CLASS_NAME = Notification.class.getName();

	private long companyId;

	public NotificationIndexer() {

		try {
			Company defaultCompany = CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");
			this.companyId = defaultCompany.getCompanyId();
		} catch (PortalException e) {
			_log.error(e);
		}
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	protected void doDelete(Notification notification) throws Exception {
		deleteDocument(companyId, notification.getNotificationId());
	}

	/**
	 * Fonction appelze lors de l'indexation de l'item C'est ici qu'on choisi
	 * les champs à indexer
	 */
	@Override
	protected Document doGetDocument(Notification notification) throws Exception {
		Document document = getBaseModelDocument(CLASS_NAME, notification);

		document.addKeyword(Field.COMPANY_ID, companyId);
		document.addKeyword(Field.GROUP_ID, companyId);
		document.addLocalizedText(Field.TITLE, notification.getTitleMap());
		document.addLocalizedText(Field.DESCRIPTION, notification.getDescriptionMap());
		document.addNumber(Field.STATUS, notification.getStatus());

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
		Notification entry = NotificationLocalServiceUtil.getNotification(classPK);
		doReindex(entry);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);
		reindexEntries(companyId);
	}

	@Override
	protected void doReindex(Notification Notification) throws Exception {
		Document document = getDocument(Notification);
		IndexWriterHelperUtil.updateDocument(getSearchEngineId(), companyId, document, isCommitImmediately());
	}

	protected void reindexEntries(long companyId) throws PortalException {
		final IndexableActionableDynamicQuery indexableActionableDynamicQuery = NotificationLocalServiceUtil
				.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {
			@Override
			public void addCriteria(DynamicQuery dynamicQuery) {

			}
		});
		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery
				.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Notification>() {

					@Override
					public void performAction(Notification entry) {
						try {
							Document document = getDocument(entry);

							indexableActionableDynamicQuery.addDocuments(document);
						} catch (PortalException pe) {
							_log.error("Unable to index Notification entry " + entry.getNotificationId());
						}
					}

				});

		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());
		indexableActionableDynamicQuery.performActions();
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
}
