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

package eu.strasbourg.service.gtfs.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.gtfs.model.Alert;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the alert service. This utility wraps {@link eu.strasbourg.service.gtfs.service.persistence.impl.AlertPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see AlertPersistence
 * @see eu.strasbourg.service.gtfs.service.persistence.impl.AlertPersistenceImpl
 * @generated
 */
@ProviderType
public class AlertUtil {
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
	public static void clearCache(Alert alert) {
		getPersistence().clearCache(alert);
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
	public static List<Alert> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Alert> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Alert> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Alert> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Alert update(Alert alert) {
		return getPersistence().update(alert);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Alert update(Alert alert, ServiceContext serviceContext) {
		return getPersistence().update(alert, serviceContext);
	}

	/**
	* Returns all the alerts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching alerts
	*/
	public static List<Alert> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the alerts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @return the range of matching alerts
	*/
	public static List<Alert> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the alerts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching alerts
	*/
	public static List<Alert> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Alert> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the alerts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching alerts
	*/
	public static List<Alert> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Alert> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first alert in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public static Alert findByUuid_First(java.lang.String uuid,
		OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchAlertException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first alert in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public static Alert fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Alert> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last alert in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public static Alert findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchAlertException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last alert in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public static Alert fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Alert> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the alerts before and after the current alert in the ordered set where uuid = &#63;.
	*
	* @param alertId the primary key of the current alert
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next alert
	* @throws NoSuchAlertException if a alert with the primary key could not be found
	*/
	public static Alert[] findByUuid_PrevAndNext(long alertId,
		java.lang.String uuid, OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchAlertException {
		return getPersistence()
				   .findByUuid_PrevAndNext(alertId, uuid, orderByComparator);
	}

	/**
	* Removes all the alerts where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of alerts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching alerts
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the alert where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchAlertException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public static Alert findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.gtfs.exception.NoSuchAlertException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the alert where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public static Alert fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the alert where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public static Alert fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the alert where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the alert that was removed
	*/
	public static Alert removeByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.gtfs.exception.NoSuchAlertException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of alerts where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching alerts
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the alerts where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching alerts
	*/
	public static List<Alert> findByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the alerts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @return the range of matching alerts
	*/
	public static List<Alert> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the alerts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching alerts
	*/
	public static List<Alert> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Alert> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the alerts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching alerts
	*/
	public static List<Alert> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Alert> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first alert in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public static Alert findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchAlertException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first alert in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public static Alert fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Alert> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last alert in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public static Alert findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchAlertException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last alert in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public static Alert fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Alert> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the alerts before and after the current alert in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param alertId the primary key of the current alert
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next alert
	* @throws NoSuchAlertException if a alert with the primary key could not be found
	*/
	public static Alert[] findByUuid_C_PrevAndNext(long alertId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchAlertException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(alertId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the alerts where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of alerts where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching alerts
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the alerts where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching alerts
	*/
	public static List<Alert> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the alerts where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @return the range of matching alerts
	*/
	public static List<Alert> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the alerts where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching alerts
	*/
	public static List<Alert> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Alert> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the alerts where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching alerts
	*/
	public static List<Alert> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Alert> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first alert in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public static Alert findByGroupId_First(long groupId,
		OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchAlertException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first alert in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public static Alert fetchByGroupId_First(long groupId,
		OrderByComparator<Alert> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last alert in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public static Alert findByGroupId_Last(long groupId,
		OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchAlertException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last alert in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public static Alert fetchByGroupId_Last(long groupId,
		OrderByComparator<Alert> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the alerts before and after the current alert in the ordered set where groupId = &#63;.
	*
	* @param alertId the primary key of the current alert
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next alert
	* @throws NoSuchAlertException if a alert with the primary key could not be found
	*/
	public static Alert[] findByGroupId_PrevAndNext(long alertId, long groupId,
		OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchAlertException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(alertId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the alerts where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of alerts where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching alerts
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the alerts where arretId = &#63;.
	*
	* @param arretId the arret ID
	* @return the matching alerts
	*/
	public static List<Alert> findByArretId(long arretId) {
		return getPersistence().findByArretId(arretId);
	}

	/**
	* Returns a range of all the alerts where arretId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param arretId the arret ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @return the range of matching alerts
	*/
	public static List<Alert> findByArretId(long arretId, int start, int end) {
		return getPersistence().findByArretId(arretId, start, end);
	}

	/**
	* Returns an ordered range of all the alerts where arretId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param arretId the arret ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching alerts
	*/
	public static List<Alert> findByArretId(long arretId, int start, int end,
		OrderByComparator<Alert> orderByComparator) {
		return getPersistence()
				   .findByArretId(arretId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the alerts where arretId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param arretId the arret ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching alerts
	*/
	public static List<Alert> findByArretId(long arretId, int start, int end,
		OrderByComparator<Alert> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByArretId(arretId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first alert in the ordered set where arretId = &#63;.
	*
	* @param arretId the arret ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public static Alert findByArretId_First(long arretId,
		OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchAlertException {
		return getPersistence().findByArretId_First(arretId, orderByComparator);
	}

	/**
	* Returns the first alert in the ordered set where arretId = &#63;.
	*
	* @param arretId the arret ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public static Alert fetchByArretId_First(long arretId,
		OrderByComparator<Alert> orderByComparator) {
		return getPersistence().fetchByArretId_First(arretId, orderByComparator);
	}

	/**
	* Returns the last alert in the ordered set where arretId = &#63;.
	*
	* @param arretId the arret ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public static Alert findByArretId_Last(long arretId,
		OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchAlertException {
		return getPersistence().findByArretId_Last(arretId, orderByComparator);
	}

	/**
	* Returns the last alert in the ordered set where arretId = &#63;.
	*
	* @param arretId the arret ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public static Alert fetchByArretId_Last(long arretId,
		OrderByComparator<Alert> orderByComparator) {
		return getPersistence().fetchByArretId_Last(arretId, orderByComparator);
	}

	/**
	* Returns the alerts before and after the current alert in the ordered set where arretId = &#63;.
	*
	* @param alertId the primary key of the current alert
	* @param arretId the arret ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next alert
	* @throws NoSuchAlertException if a alert with the primary key could not be found
	*/
	public static Alert[] findByArretId_PrevAndNext(long alertId, long arretId,
		OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchAlertException {
		return getPersistence()
				   .findByArretId_PrevAndNext(alertId, arretId,
			orderByComparator);
	}

	/**
	* Removes all the alerts where arretId = &#63; from the database.
	*
	* @param arretId the arret ID
	*/
	public static void removeByArretId(long arretId) {
		getPersistence().removeByArretId(arretId);
	}

	/**
	* Returns the number of alerts where arretId = &#63;.
	*
	* @param arretId the arret ID
	* @return the number of matching alerts
	*/
	public static int countByArretId(long arretId) {
		return getPersistence().countByArretId(arretId);
	}

	/**
	* Caches the alert in the entity cache if it is enabled.
	*
	* @param alert the alert
	*/
	public static void cacheResult(Alert alert) {
		getPersistence().cacheResult(alert);
	}

	/**
	* Caches the alerts in the entity cache if it is enabled.
	*
	* @param alerts the alerts
	*/
	public static void cacheResult(List<Alert> alerts) {
		getPersistence().cacheResult(alerts);
	}

	/**
	* Creates a new alert with the primary key. Does not add the alert to the database.
	*
	* @param alertId the primary key for the new alert
	* @return the new alert
	*/
	public static Alert create(long alertId) {
		return getPersistence().create(alertId);
	}

	/**
	* Removes the alert with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param alertId the primary key of the alert
	* @return the alert that was removed
	* @throws NoSuchAlertException if a alert with the primary key could not be found
	*/
	public static Alert remove(long alertId)
		throws eu.strasbourg.service.gtfs.exception.NoSuchAlertException {
		return getPersistence().remove(alertId);
	}

	public static Alert updateImpl(Alert alert) {
		return getPersistence().updateImpl(alert);
	}

	/**
	* Returns the alert with the primary key or throws a {@link NoSuchAlertException} if it could not be found.
	*
	* @param alertId the primary key of the alert
	* @return the alert
	* @throws NoSuchAlertException if a alert with the primary key could not be found
	*/
	public static Alert findByPrimaryKey(long alertId)
		throws eu.strasbourg.service.gtfs.exception.NoSuchAlertException {
		return getPersistence().findByPrimaryKey(alertId);
	}

	/**
	* Returns the alert with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param alertId the primary key of the alert
	* @return the alert, or <code>null</code> if a alert with the primary key could not be found
	*/
	public static Alert fetchByPrimaryKey(long alertId) {
		return getPersistence().fetchByPrimaryKey(alertId);
	}

	public static java.util.Map<java.io.Serializable, Alert> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the alerts.
	*
	* @return the alerts
	*/
	public static List<Alert> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the alerts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @return the range of alerts
	*/
	public static List<Alert> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the alerts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of alerts
	*/
	public static List<Alert> findAll(int start, int end,
		OrderByComparator<Alert> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the alerts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of alerts
	*/
	public static List<Alert> findAll(int start, int end,
		OrderByComparator<Alert> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the alerts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of alerts.
	*
	* @return the number of alerts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AlertPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AlertPersistence, AlertPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AlertPersistence.class);
}