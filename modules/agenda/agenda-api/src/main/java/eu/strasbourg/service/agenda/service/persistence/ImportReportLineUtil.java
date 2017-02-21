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

import eu.strasbourg.service.agenda.model.ImportReportLine;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the import report line service. This utility wraps {@link eu.strasbourg.service.agenda.service.persistence.impl.ImportReportLinePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see ImportReportLinePersistence
 * @see eu.strasbourg.service.agenda.service.persistence.impl.ImportReportLinePersistenceImpl
 * @generated
 */
@ProviderType
public class ImportReportLineUtil {
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
	public static void clearCache(ImportReportLine importReportLine) {
		getPersistence().clearCache(importReportLine);
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
	public static List<ImportReportLine> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ImportReportLine> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ImportReportLine> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ImportReportLine> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ImportReportLine update(ImportReportLine importReportLine) {
		return getPersistence().update(importReportLine);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ImportReportLine update(ImportReportLine importReportLine,
		ServiceContext serviceContext) {
		return getPersistence().update(importReportLine, serviceContext);
	}

	/**
	* Returns all the import report lines where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching import report lines
	*/
	public static List<ImportReportLine> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the import report lines where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @return the range of matching import report lines
	*/
	public static List<ImportReportLine> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the import report lines where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import report lines
	*/
	public static List<ImportReportLine> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<ImportReportLine> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the import report lines where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import report lines
	*/
	public static List<ImportReportLine> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<ImportReportLine> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first import report line in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import report line
	* @throws NoSuchImportReportLineException if a matching import report line could not be found
	*/
	public static ImportReportLine findByUuid_First(java.lang.String uuid,
		OrderByComparator<ImportReportLine> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchImportReportLineException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first import report line in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import report line, or <code>null</code> if a matching import report line could not be found
	*/
	public static ImportReportLine fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<ImportReportLine> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last import report line in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import report line
	* @throws NoSuchImportReportLineException if a matching import report line could not be found
	*/
	public static ImportReportLine findByUuid_Last(java.lang.String uuid,
		OrderByComparator<ImportReportLine> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchImportReportLineException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last import report line in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import report line, or <code>null</code> if a matching import report line could not be found
	*/
	public static ImportReportLine fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<ImportReportLine> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the import report lines before and after the current import report line in the ordered set where uuid = &#63;.
	*
	* @param lineId the primary key of the current import report line
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import report line
	* @throws NoSuchImportReportLineException if a import report line with the primary key could not be found
	*/
	public static ImportReportLine[] findByUuid_PrevAndNext(long lineId,
		java.lang.String uuid,
		OrderByComparator<ImportReportLine> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchImportReportLineException {
		return getPersistence()
				   .findByUuid_PrevAndNext(lineId, uuid, orderByComparator);
	}

	/**
	* Removes all the import report lines where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of import report lines where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching import report lines
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the import report lines where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @return the matching import report lines
	*/
	public static List<ImportReportLine> findByTypeAndStatus(
		java.lang.String type, long status) {
		return getPersistence().findByTypeAndStatus(type, status);
	}

	/**
	* Returns a range of all the import report lines where type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @return the range of matching import report lines
	*/
	public static List<ImportReportLine> findByTypeAndStatus(
		java.lang.String type, long status, int start, int end) {
		return getPersistence().findByTypeAndStatus(type, status, start, end);
	}

	/**
	* Returns an ordered range of all the import report lines where type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import report lines
	*/
	public static List<ImportReportLine> findByTypeAndStatus(
		java.lang.String type, long status, int start, int end,
		OrderByComparator<ImportReportLine> orderByComparator) {
		return getPersistence()
				   .findByTypeAndStatus(type, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the import report lines where type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import report lines
	*/
	public static List<ImportReportLine> findByTypeAndStatus(
		java.lang.String type, long status, int start, int end,
		OrderByComparator<ImportReportLine> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTypeAndStatus(type, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first import report line in the ordered set where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import report line
	* @throws NoSuchImportReportLineException if a matching import report line could not be found
	*/
	public static ImportReportLine findByTypeAndStatus_First(
		java.lang.String type, long status,
		OrderByComparator<ImportReportLine> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchImportReportLineException {
		return getPersistence()
				   .findByTypeAndStatus_First(type, status, orderByComparator);
	}

	/**
	* Returns the first import report line in the ordered set where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import report line, or <code>null</code> if a matching import report line could not be found
	*/
	public static ImportReportLine fetchByTypeAndStatus_First(
		java.lang.String type, long status,
		OrderByComparator<ImportReportLine> orderByComparator) {
		return getPersistence()
				   .fetchByTypeAndStatus_First(type, status, orderByComparator);
	}

	/**
	* Returns the last import report line in the ordered set where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import report line
	* @throws NoSuchImportReportLineException if a matching import report line could not be found
	*/
	public static ImportReportLine findByTypeAndStatus_Last(
		java.lang.String type, long status,
		OrderByComparator<ImportReportLine> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchImportReportLineException {
		return getPersistence()
				   .findByTypeAndStatus_Last(type, status, orderByComparator);
	}

	/**
	* Returns the last import report line in the ordered set where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import report line, or <code>null</code> if a matching import report line could not be found
	*/
	public static ImportReportLine fetchByTypeAndStatus_Last(
		java.lang.String type, long status,
		OrderByComparator<ImportReportLine> orderByComparator) {
		return getPersistence()
				   .fetchByTypeAndStatus_Last(type, status, orderByComparator);
	}

	/**
	* Returns the import report lines before and after the current import report line in the ordered set where type = &#63; and status = &#63;.
	*
	* @param lineId the primary key of the current import report line
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import report line
	* @throws NoSuchImportReportLineException if a import report line with the primary key could not be found
	*/
	public static ImportReportLine[] findByTypeAndStatus_PrevAndNext(
		long lineId, java.lang.String type, long status,
		OrderByComparator<ImportReportLine> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchImportReportLineException {
		return getPersistence()
				   .findByTypeAndStatus_PrevAndNext(lineId, type, status,
			orderByComparator);
	}

	/**
	* Removes all the import report lines where type = &#63; and status = &#63; from the database.
	*
	* @param type the type
	* @param status the status
	*/
	public static void removeByTypeAndStatus(java.lang.String type, long status) {
		getPersistence().removeByTypeAndStatus(type, status);
	}

	/**
	* Returns the number of import report lines where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @return the number of matching import report lines
	*/
	public static int countByTypeAndStatus(java.lang.String type, long status) {
		return getPersistence().countByTypeAndStatus(type, status);
	}

	/**
	* Returns all the import report lines where reportId = &#63;.
	*
	* @param reportId the report ID
	* @return the matching import report lines
	*/
	public static List<ImportReportLine> findByReportId(long reportId) {
		return getPersistence().findByReportId(reportId);
	}

	/**
	* Returns a range of all the import report lines where reportId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param reportId the report ID
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @return the range of matching import report lines
	*/
	public static List<ImportReportLine> findByReportId(long reportId,
		int start, int end) {
		return getPersistence().findByReportId(reportId, start, end);
	}

	/**
	* Returns an ordered range of all the import report lines where reportId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param reportId the report ID
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import report lines
	*/
	public static List<ImportReportLine> findByReportId(long reportId,
		int start, int end,
		OrderByComparator<ImportReportLine> orderByComparator) {
		return getPersistence()
				   .findByReportId(reportId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the import report lines where reportId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param reportId the report ID
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import report lines
	*/
	public static List<ImportReportLine> findByReportId(long reportId,
		int start, int end,
		OrderByComparator<ImportReportLine> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByReportId(reportId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first import report line in the ordered set where reportId = &#63;.
	*
	* @param reportId the report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import report line
	* @throws NoSuchImportReportLineException if a matching import report line could not be found
	*/
	public static ImportReportLine findByReportId_First(long reportId,
		OrderByComparator<ImportReportLine> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchImportReportLineException {
		return getPersistence().findByReportId_First(reportId, orderByComparator);
	}

	/**
	* Returns the first import report line in the ordered set where reportId = &#63;.
	*
	* @param reportId the report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import report line, or <code>null</code> if a matching import report line could not be found
	*/
	public static ImportReportLine fetchByReportId_First(long reportId,
		OrderByComparator<ImportReportLine> orderByComparator) {
		return getPersistence()
				   .fetchByReportId_First(reportId, orderByComparator);
	}

	/**
	* Returns the last import report line in the ordered set where reportId = &#63;.
	*
	* @param reportId the report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import report line
	* @throws NoSuchImportReportLineException if a matching import report line could not be found
	*/
	public static ImportReportLine findByReportId_Last(long reportId,
		OrderByComparator<ImportReportLine> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchImportReportLineException {
		return getPersistence().findByReportId_Last(reportId, orderByComparator);
	}

	/**
	* Returns the last import report line in the ordered set where reportId = &#63;.
	*
	* @param reportId the report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import report line, or <code>null</code> if a matching import report line could not be found
	*/
	public static ImportReportLine fetchByReportId_Last(long reportId,
		OrderByComparator<ImportReportLine> orderByComparator) {
		return getPersistence().fetchByReportId_Last(reportId, orderByComparator);
	}

	/**
	* Returns the import report lines before and after the current import report line in the ordered set where reportId = &#63;.
	*
	* @param lineId the primary key of the current import report line
	* @param reportId the report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import report line
	* @throws NoSuchImportReportLineException if a import report line with the primary key could not be found
	*/
	public static ImportReportLine[] findByReportId_PrevAndNext(long lineId,
		long reportId, OrderByComparator<ImportReportLine> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchImportReportLineException {
		return getPersistence()
				   .findByReportId_PrevAndNext(lineId, reportId,
			orderByComparator);
	}

	/**
	* Removes all the import report lines where reportId = &#63; from the database.
	*
	* @param reportId the report ID
	*/
	public static void removeByReportId(long reportId) {
		getPersistence().removeByReportId(reportId);
	}

	/**
	* Returns the number of import report lines where reportId = &#63;.
	*
	* @param reportId the report ID
	* @return the number of matching import report lines
	*/
	public static int countByReportId(long reportId) {
		return getPersistence().countByReportId(reportId);
	}

	/**
	* Caches the import report line in the entity cache if it is enabled.
	*
	* @param importReportLine the import report line
	*/
	public static void cacheResult(ImportReportLine importReportLine) {
		getPersistence().cacheResult(importReportLine);
	}

	/**
	* Caches the import report lines in the entity cache if it is enabled.
	*
	* @param importReportLines the import report lines
	*/
	public static void cacheResult(List<ImportReportLine> importReportLines) {
		getPersistence().cacheResult(importReportLines);
	}

	/**
	* Creates a new import report line with the primary key. Does not add the import report line to the database.
	*
	* @param lineId the primary key for the new import report line
	* @return the new import report line
	*/
	public static ImportReportLine create(long lineId) {
		return getPersistence().create(lineId);
	}

	/**
	* Removes the import report line with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lineId the primary key of the import report line
	* @return the import report line that was removed
	* @throws NoSuchImportReportLineException if a import report line with the primary key could not be found
	*/
	public static ImportReportLine remove(long lineId)
		throws eu.strasbourg.service.agenda.exception.NoSuchImportReportLineException {
		return getPersistence().remove(lineId);
	}

	public static ImportReportLine updateImpl(ImportReportLine importReportLine) {
		return getPersistence().updateImpl(importReportLine);
	}

	/**
	* Returns the import report line with the primary key or throws a {@link NoSuchImportReportLineException} if it could not be found.
	*
	* @param lineId the primary key of the import report line
	* @return the import report line
	* @throws NoSuchImportReportLineException if a import report line with the primary key could not be found
	*/
	public static ImportReportLine findByPrimaryKey(long lineId)
		throws eu.strasbourg.service.agenda.exception.NoSuchImportReportLineException {
		return getPersistence().findByPrimaryKey(lineId);
	}

	/**
	* Returns the import report line with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lineId the primary key of the import report line
	* @return the import report line, or <code>null</code> if a import report line with the primary key could not be found
	*/
	public static ImportReportLine fetchByPrimaryKey(long lineId) {
		return getPersistence().fetchByPrimaryKey(lineId);
	}

	public static java.util.Map<java.io.Serializable, ImportReportLine> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the import report lines.
	*
	* @return the import report lines
	*/
	public static List<ImportReportLine> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the import report lines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @return the range of import report lines
	*/
	public static List<ImportReportLine> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the import report lines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of import report lines
	*/
	public static List<ImportReportLine> findAll(int start, int end,
		OrderByComparator<ImportReportLine> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the import report lines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of import report lines
	*/
	public static List<ImportReportLine> findAll(int start, int end,
		OrderByComparator<ImportReportLine> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the import report lines from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of import report lines.
	*
	* @return the number of import report lines
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ImportReportLinePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ImportReportLinePersistence, ImportReportLinePersistence> _serviceTracker =
		ServiceTrackerFactory.open(ImportReportLinePersistence.class);
}