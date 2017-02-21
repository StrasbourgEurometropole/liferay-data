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

package eu.strasbourg.service.agenda.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.agenda.model.ImportReport;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the import report service. This utility wraps {@link eu.strasbourg.service.agenda.service.persistence.impl.ImportReportPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see ImportReportPersistence
 * @see eu.strasbourg.service.agenda.service.persistence.impl.ImportReportPersistenceImpl
 * @generated
 */
@ProviderType
public class ImportReportUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ImportReport importReport) {
		getPersistence().clearCache(importReport);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ImportReport> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ImportReport> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ImportReport> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ImportReport> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ImportReport update(ImportReport importReport) {
		return getPersistence().update(importReport);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ImportReport update(ImportReport importReport,
		ServiceContext serviceContext) {
		return getPersistence().update(importReport, serviceContext);
	}

	/**
	* Returns all the import reports where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching import reports
	*/
	public static List<ImportReport> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the import reports where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import reports
	* @param end the upper bound of the range of import reports (not inclusive)
	* @return the range of matching import reports
	*/
	public static List<ImportReport> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the import reports where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import reports
	* @param end the upper bound of the range of import reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import reports
	*/
	public static List<ImportReport> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<ImportReport> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the import reports where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import reports
	* @param end the upper bound of the range of import reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import reports
	*/
	public static List<ImportReport> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<ImportReport> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first import report in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import report
	* @throws NoSuchImportReportException if a matching import report could not be found
	*/
	public static ImportReport findByUuid_First(java.lang.String uuid,
		OrderByComparator<ImportReport> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchImportReportException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first import report in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import report, or <code>null</code> if a matching import report could not be found
	*/
	public static ImportReport fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<ImportReport> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last import report in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import report
	* @throws NoSuchImportReportException if a matching import report could not be found
	*/
	public static ImportReport findByUuid_Last(java.lang.String uuid,
		OrderByComparator<ImportReport> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchImportReportException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last import report in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import report, or <code>null</code> if a matching import report could not be found
	*/
	public static ImportReport fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<ImportReport> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the import reports before and after the current import report in the ordered set where uuid = &#63;.
	*
	* @param reportId the primary key of the current import report
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import report
	* @throws NoSuchImportReportException if a import report with the primary key could not be found
	*/
	public static ImportReport[] findByUuid_PrevAndNext(long reportId,
		java.lang.String uuid, OrderByComparator<ImportReport> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchImportReportException {
		return getPersistence()
				   .findByUuid_PrevAndNext(reportId, uuid, orderByComparator);
	}

	/**
	* Removes all the import reports where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of import reports where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching import reports
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Caches the import report in the entity cache if it is enabled.
	*
	* @param importReport the import report
	*/
	public static void cacheResult(ImportReport importReport) {
		getPersistence().cacheResult(importReport);
	}

	/**
	* Caches the import reports in the entity cache if it is enabled.
	*
	* @param importReports the import reports
	*/
	public static void cacheResult(List<ImportReport> importReports) {
		getPersistence().cacheResult(importReports);
	}

	/**
	* Creates a new import report with the primary key. Does not add the import report to the database.
	*
	* @param reportId the primary key for the new import report
	* @return the new import report
	*/
	public static ImportReport create(long reportId) {
		return getPersistence().create(reportId);
	}

	/**
	* Removes the import report with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param reportId the primary key of the import report
	* @return the import report that was removed
	* @throws NoSuchImportReportException if a import report with the primary key could not be found
	*/
	public static ImportReport remove(long reportId)
		throws eu.strasbourg.service.agenda.exception.NoSuchImportReportException {
		return getPersistence().remove(reportId);
	}

	public static ImportReport updateImpl(ImportReport importReport) {
		return getPersistence().updateImpl(importReport);
	}

	/**
	* Returns the import report with the primary key or throws a {@link NoSuchImportReportException} if it could not be found.
	*
	* @param reportId the primary key of the import report
	* @return the import report
	* @throws NoSuchImportReportException if a import report with the primary key could not be found
	*/
	public static ImportReport findByPrimaryKey(long reportId)
		throws eu.strasbourg.service.agenda.exception.NoSuchImportReportException {
		return getPersistence().findByPrimaryKey(reportId);
	}

	/**
	* Returns the import report with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param reportId the primary key of the import report
	* @return the import report, or <code>null</code> if a import report with the primary key could not be found
	*/
	public static ImportReport fetchByPrimaryKey(long reportId) {
		return getPersistence().fetchByPrimaryKey(reportId);
	}

	public static java.util.Map<java.io.Serializable, ImportReport> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the import reports.
	*
	* @return the import reports
	*/
	public static List<ImportReport> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the import reports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import reports
	* @param end the upper bound of the range of import reports (not inclusive)
	* @return the range of import reports
	*/
	public static List<ImportReport> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the import reports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import reports
	* @param end the upper bound of the range of import reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of import reports
	*/
	public static List<ImportReport> findAll(int start, int end,
		OrderByComparator<ImportReport> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the import reports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import reports
	* @param end the upper bound of the range of import reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of import reports
	*/
	public static List<ImportReport> findAll(int start, int end,
		OrderByComparator<ImportReport> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the import reports from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of import reports.
	*
	* @return the number of import reports
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ImportReportPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ImportReportPersistence, ImportReportPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ImportReportPersistence.class);
}