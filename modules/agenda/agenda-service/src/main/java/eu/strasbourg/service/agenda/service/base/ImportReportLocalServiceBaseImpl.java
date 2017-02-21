/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.strasbourg.service.agenda.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import eu.strasbourg.service.agenda.model.ImportReport;
import eu.strasbourg.service.agenda.service.ImportReportLocalService;
import eu.strasbourg.service.agenda.service.persistence.EventPeriodPersistence;
import eu.strasbourg.service.agenda.service.persistence.EventPersistence;
import eu.strasbourg.service.agenda.service.persistence.ImportReportLinePersistence;
import eu.strasbourg.service.agenda.service.persistence.ImportReportPersistence;
import eu.strasbourg.service.agenda.service.persistence.ManifestationPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the import report local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link eu.strasbourg.service.agenda.service.impl.ImportReportLocalServiceImpl}.
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.agenda.service.impl.ImportReportLocalServiceImpl
 * @see eu.strasbourg.service.agenda.service.ImportReportLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class ImportReportLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements ImportReportLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link eu.strasbourg.service.agenda.service.ImportReportLocalServiceUtil} to access the import report local service.
	 */

	/**
	 * Adds the import report to the database. Also notifies the appropriate model listeners.
	 *
	 * @param importReport the import report
	 * @return the import report that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ImportReport addImportReport(ImportReport importReport) {
		importReport.setNew(true);

		return importReportPersistence.update(importReport);
	}

	/**
	 * Creates a new import report with the primary key. Does not add the import report to the database.
	 *
	 * @param reportId the primary key for the new import report
	 * @return the new import report
	 */
	@Override
	public ImportReport createImportReport(long reportId) {
		return importReportPersistence.create(reportId);
	}

	/**
	 * Deletes the import report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param reportId the primary key of the import report
	 * @return the import report that was removed
	 * @throws PortalException if a import report with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ImportReport deleteImportReport(long reportId)
		throws PortalException {
		return importReportPersistence.remove(reportId);
	}

	/**
	 * Deletes the import report from the database. Also notifies the appropriate model listeners.
	 *
	 * @param importReport the import report
	 * @return the import report that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ImportReport deleteImportReport(ImportReport importReport) {
		return importReportPersistence.remove(importReport);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(ImportReport.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return importReportPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.ImportReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return importReportPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.ImportReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return importReportPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return importReportPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return importReportPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public ImportReport fetchImportReport(long reportId) {
		return importReportPersistence.fetchByPrimaryKey(reportId);
	}

	/**
	 * Returns the import report with the primary key.
	 *
	 * @param reportId the primary key of the import report
	 * @return the import report
	 * @throws PortalException if a import report with the primary key could not be found
	 */
	@Override
	public ImportReport getImportReport(long reportId)
		throws PortalException {
		return importReportPersistence.findByPrimaryKey(reportId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(importReportLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ImportReport.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("reportId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(importReportLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(ImportReport.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("reportId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(importReportLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ImportReport.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("reportId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return importReportLocalService.deleteImportReport((ImportReport)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return importReportPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the import reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.ImportReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of import reports
	 * @param end the upper bound of the range of import reports (not inclusive)
	 * @return the range of import reports
	 */
	@Override
	public List<ImportReport> getImportReports(int start, int end) {
		return importReportPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of import reports.
	 *
	 * @return the number of import reports
	 */
	@Override
	public int getImportReportsCount() {
		return importReportPersistence.countAll();
	}

	/**
	 * Updates the import report in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param importReport the import report
	 * @return the import report that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ImportReport updateImportReport(ImportReport importReport) {
		return importReportPersistence.update(importReport);
	}

	/**
	 * Returns the event local service.
	 *
	 * @return the event local service
	 */
	public eu.strasbourg.service.agenda.service.EventLocalService getEventLocalService() {
		return eventLocalService;
	}

	/**
	 * Sets the event local service.
	 *
	 * @param eventLocalService the event local service
	 */
	public void setEventLocalService(
		eu.strasbourg.service.agenda.service.EventLocalService eventLocalService) {
		this.eventLocalService = eventLocalService;
	}

	/**
	 * Returns the event persistence.
	 *
	 * @return the event persistence
	 */
	public EventPersistence getEventPersistence() {
		return eventPersistence;
	}

	/**
	 * Sets the event persistence.
	 *
	 * @param eventPersistence the event persistence
	 */
	public void setEventPersistence(EventPersistence eventPersistence) {
		this.eventPersistence = eventPersistence;
	}

	/**
	 * Returns the event period local service.
	 *
	 * @return the event period local service
	 */
	public eu.strasbourg.service.agenda.service.EventPeriodLocalService getEventPeriodLocalService() {
		return eventPeriodLocalService;
	}

	/**
	 * Sets the event period local service.
	 *
	 * @param eventPeriodLocalService the event period local service
	 */
	public void setEventPeriodLocalService(
		eu.strasbourg.service.agenda.service.EventPeriodLocalService eventPeriodLocalService) {
		this.eventPeriodLocalService = eventPeriodLocalService;
	}

	/**
	 * Returns the event period persistence.
	 *
	 * @return the event period persistence
	 */
	public EventPeriodPersistence getEventPeriodPersistence() {
		return eventPeriodPersistence;
	}

	/**
	 * Sets the event period persistence.
	 *
	 * @param eventPeriodPersistence the event period persistence
	 */
	public void setEventPeriodPersistence(
		EventPeriodPersistence eventPeriodPersistence) {
		this.eventPeriodPersistence = eventPeriodPersistence;
	}

	/**
	 * Returns the import report local service.
	 *
	 * @return the import report local service
	 */
	public ImportReportLocalService getImportReportLocalService() {
		return importReportLocalService;
	}

	/**
	 * Sets the import report local service.
	 *
	 * @param importReportLocalService the import report local service
	 */
	public void setImportReportLocalService(
		ImportReportLocalService importReportLocalService) {
		this.importReportLocalService = importReportLocalService;
	}

	/**
	 * Returns the import report persistence.
	 *
	 * @return the import report persistence
	 */
	public ImportReportPersistence getImportReportPersistence() {
		return importReportPersistence;
	}

	/**
	 * Sets the import report persistence.
	 *
	 * @param importReportPersistence the import report persistence
	 */
	public void setImportReportPersistence(
		ImportReportPersistence importReportPersistence) {
		this.importReportPersistence = importReportPersistence;
	}

	/**
	 * Returns the import report line local service.
	 *
	 * @return the import report line local service
	 */
	public eu.strasbourg.service.agenda.service.ImportReportLineLocalService getImportReportLineLocalService() {
		return importReportLineLocalService;
	}

	/**
	 * Sets the import report line local service.
	 *
	 * @param importReportLineLocalService the import report line local service
	 */
	public void setImportReportLineLocalService(
		eu.strasbourg.service.agenda.service.ImportReportLineLocalService importReportLineLocalService) {
		this.importReportLineLocalService = importReportLineLocalService;
	}

	/**
	 * Returns the import report line persistence.
	 *
	 * @return the import report line persistence
	 */
	public ImportReportLinePersistence getImportReportLinePersistence() {
		return importReportLinePersistence;
	}

	/**
	 * Sets the import report line persistence.
	 *
	 * @param importReportLinePersistence the import report line persistence
	 */
	public void setImportReportLinePersistence(
		ImportReportLinePersistence importReportLinePersistence) {
		this.importReportLinePersistence = importReportLinePersistence;
	}

	/**
	 * Returns the manifestation local service.
	 *
	 * @return the manifestation local service
	 */
	public eu.strasbourg.service.agenda.service.ManifestationLocalService getManifestationLocalService() {
		return manifestationLocalService;
	}

	/**
	 * Sets the manifestation local service.
	 *
	 * @param manifestationLocalService the manifestation local service
	 */
	public void setManifestationLocalService(
		eu.strasbourg.service.agenda.service.ManifestationLocalService manifestationLocalService) {
		this.manifestationLocalService = manifestationLocalService;
	}

	/**
	 * Returns the manifestation persistence.
	 *
	 * @return the manifestation persistence
	 */
	public ManifestationPersistence getManifestationPersistence() {
		return manifestationPersistence;
	}

	/**
	 * Sets the manifestation persistence.
	 *
	 * @param manifestationPersistence the manifestation persistence
	 */
	public void setManifestationPersistence(
		ManifestationPersistence manifestationPersistence) {
		this.manifestationPersistence = manifestationPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("eu.strasbourg.service.agenda.model.ImportReport",
			importReportLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"eu.strasbourg.service.agenda.model.ImportReport");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ImportReportLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ImportReport.class;
	}

	protected String getModelClassName() {
		return ImportReport.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = importReportPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = eu.strasbourg.service.agenda.service.EventLocalService.class)
	protected eu.strasbourg.service.agenda.service.EventLocalService eventLocalService;
	@BeanReference(type = EventPersistence.class)
	protected EventPersistence eventPersistence;
	@BeanReference(type = eu.strasbourg.service.agenda.service.EventPeriodLocalService.class)
	protected eu.strasbourg.service.agenda.service.EventPeriodLocalService eventPeriodLocalService;
	@BeanReference(type = EventPeriodPersistence.class)
	protected EventPeriodPersistence eventPeriodPersistence;
	@BeanReference(type = ImportReportLocalService.class)
	protected ImportReportLocalService importReportLocalService;
	@BeanReference(type = ImportReportPersistence.class)
	protected ImportReportPersistence importReportPersistence;
	@BeanReference(type = eu.strasbourg.service.agenda.service.ImportReportLineLocalService.class)
	protected eu.strasbourg.service.agenda.service.ImportReportLineLocalService importReportLineLocalService;
	@BeanReference(type = ImportReportLinePersistence.class)
	protected ImportReportLinePersistence importReportLinePersistence;
	@BeanReference(type = eu.strasbourg.service.agenda.service.ManifestationLocalService.class)
	protected eu.strasbourg.service.agenda.service.ManifestationLocalService manifestationLocalService;
	@BeanReference(type = ManifestationPersistence.class)
	protected ManifestationPersistence manifestationPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}