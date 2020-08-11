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

package eu.strasbourg.service.ejob.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.ejob.model.Alert;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the alert service. This utility wraps <code>eu.strasbourg.service.ejob.service.persistence.impl.AlertPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AlertPersistence
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
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Alert> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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
	public static List<Alert> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Alert> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Alert> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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
	public static List<Alert> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the alerts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AlertModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of alerts
	 * @param end the upper bound of the range of alerts (not inclusive)
	 * @return the range of matching alerts
	 */
	public static List<Alert> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the alerts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AlertModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of alerts
	 * @param end the upper bound of the range of alerts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching alerts
	 */
	public static List<Alert> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Alert> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the alerts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AlertModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of alerts
	 * @param end the upper bound of the range of alerts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching alerts
	 */
	public static List<Alert> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Alert> orderByComparator, boolean retrieveFromCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first alert in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching alert
	 * @throws NoSuchAlertException if a matching alert could not be found
	 */
	public static Alert findByUuid_First(
			String uuid, OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.ejob.exception.NoSuchAlertException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first alert in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching alert, or <code>null</code> if a matching alert could not be found
	 */
	public static Alert fetchByUuid_First(
		String uuid, OrderByComparator<Alert> orderByComparator) {

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
	public static Alert findByUuid_Last(
			String uuid, OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.ejob.exception.NoSuchAlertException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last alert in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching alert, or <code>null</code> if a matching alert could not be found
	 */
	public static Alert fetchByUuid_Last(
		String uuid, OrderByComparator<Alert> orderByComparator) {

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
	public static Alert[] findByUuid_PrevAndNext(
			long alertId, String uuid,
			OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.ejob.exception.NoSuchAlertException {

		return getPersistence().findByUuid_PrevAndNext(
			alertId, uuid, orderByComparator);
	}

	/**
	 * Removes all the alerts where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of alerts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching alerts
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the alert where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchAlertException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching alert
	 * @throws NoSuchAlertException if a matching alert could not be found
	 */
	public static Alert findByUUID_G(String uuid, long groupId)
		throws eu.strasbourg.service.ejob.exception.NoSuchAlertException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the alert where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching alert, or <code>null</code> if a matching alert could not be found
	 */
	public static Alert fetchByUUID_G(String uuid, long groupId) {
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
	public static Alert fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	 * Removes the alert where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the alert that was removed
	 */
	public static Alert removeByUUID_G(String uuid, long groupId)
		throws eu.strasbourg.service.ejob.exception.NoSuchAlertException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of alerts where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching alerts
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the alerts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching alerts
	 */
	public static List<Alert> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the alerts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AlertModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of alerts
	 * @param end the upper bound of the range of alerts (not inclusive)
	 * @return the range of matching alerts
	 */
	public static List<Alert> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the alerts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AlertModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of alerts
	 * @param end the upper bound of the range of alerts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching alerts
	 */
	public static List<Alert> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Alert> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the alerts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AlertModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static List<Alert> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Alert> orderByComparator, boolean retrieveFromCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, retrieveFromCache);
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
	public static Alert findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.ejob.exception.NoSuchAlertException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first alert in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching alert, or <code>null</code> if a matching alert could not be found
	 */
	public static Alert fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Alert> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
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
	public static Alert findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.ejob.exception.NoSuchAlertException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last alert in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching alert, or <code>null</code> if a matching alert could not be found
	 */
	public static Alert fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Alert> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
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
	public static Alert[] findByUuid_C_PrevAndNext(
			long alertId, String uuid, long companyId,
			OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.ejob.exception.NoSuchAlertException {

		return getPersistence().findByUuid_C_PrevAndNext(
			alertId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the alerts where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of alerts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching alerts
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the alerts where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the matching alerts
	 */
	public static List<Alert> findByPublikUserId(String publikUserId) {
		return getPersistence().findByPublikUserId(publikUserId);
	}

	/**
	 * Returns a range of all the alerts where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AlertModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of alerts
	 * @param end the upper bound of the range of alerts (not inclusive)
	 * @return the range of matching alerts
	 */
	public static List<Alert> findByPublikUserId(
		String publikUserId, int start, int end) {

		return getPersistence().findByPublikUserId(publikUserId, start, end);
	}

	/**
	 * Returns an ordered range of all the alerts where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AlertModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of alerts
	 * @param end the upper bound of the range of alerts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching alerts
	 */
	public static List<Alert> findByPublikUserId(
		String publikUserId, int start, int end,
		OrderByComparator<Alert> orderByComparator) {

		return getPersistence().findByPublikUserId(
			publikUserId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the alerts where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AlertModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of alerts
	 * @param end the upper bound of the range of alerts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching alerts
	 */
	public static List<Alert> findByPublikUserId(
		String publikUserId, int start, int end,
		OrderByComparator<Alert> orderByComparator, boolean retrieveFromCache) {

		return getPersistence().findByPublikUserId(
			publikUserId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first alert in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching alert
	 * @throws NoSuchAlertException if a matching alert could not be found
	 */
	public static Alert findByPublikUserId_First(
			String publikUserId, OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.ejob.exception.NoSuchAlertException {

		return getPersistence().findByPublikUserId_First(
			publikUserId, orderByComparator);
	}

	/**
	 * Returns the first alert in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching alert, or <code>null</code> if a matching alert could not be found
	 */
	public static Alert fetchByPublikUserId_First(
		String publikUserId, OrderByComparator<Alert> orderByComparator) {

		return getPersistence().fetchByPublikUserId_First(
			publikUserId, orderByComparator);
	}

	/**
	 * Returns the last alert in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching alert
	 * @throws NoSuchAlertException if a matching alert could not be found
	 */
	public static Alert findByPublikUserId_Last(
			String publikUserId, OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.ejob.exception.NoSuchAlertException {

		return getPersistence().findByPublikUserId_Last(
			publikUserId, orderByComparator);
	}

	/**
	 * Returns the last alert in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching alert, or <code>null</code> if a matching alert could not be found
	 */
	public static Alert fetchByPublikUserId_Last(
		String publikUserId, OrderByComparator<Alert> orderByComparator) {

		return getPersistence().fetchByPublikUserId_Last(
			publikUserId, orderByComparator);
	}

	/**
	 * Returns the alerts before and after the current alert in the ordered set where publikUserId = &#63;.
	 *
	 * @param alertId the primary key of the current alert
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next alert
	 * @throws NoSuchAlertException if a alert with the primary key could not be found
	 */
	public static Alert[] findByPublikUserId_PrevAndNext(
			long alertId, String publikUserId,
			OrderByComparator<Alert> orderByComparator)
		throws eu.strasbourg.service.ejob.exception.NoSuchAlertException {

		return getPersistence().findByPublikUserId_PrevAndNext(
			alertId, publikUserId, orderByComparator);
	}

	/**
	 * Removes all the alerts where publikUserId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 */
	public static void removeByPublikUserId(String publikUserId) {
		getPersistence().removeByPublikUserId(publikUserId);
	}

	/**
	 * Returns the number of alerts where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the number of matching alerts
	 */
	public static int countByPublikUserId(String publikUserId) {
		return getPersistence().countByPublikUserId(publikUserId);
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
		throws eu.strasbourg.service.ejob.exception.NoSuchAlertException {

		return getPersistence().remove(alertId);
	}

	public static Alert updateImpl(Alert alert) {
		return getPersistence().updateImpl(alert);
	}

	/**
	 * Returns the alert with the primary key or throws a <code>NoSuchAlertException</code> if it could not be found.
	 *
	 * @param alertId the primary key of the alert
	 * @return the alert
	 * @throws NoSuchAlertException if a alert with the primary key could not be found
	 */
	public static Alert findByPrimaryKey(long alertId)
		throws eu.strasbourg.service.ejob.exception.NoSuchAlertException {

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AlertModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AlertModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of alerts
	 * @param end the upper bound of the range of alerts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of alerts
	 */
	public static List<Alert> findAll(
		int start, int end, OrderByComparator<Alert> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the alerts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AlertModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of alerts
	 * @param end the upper bound of the range of alerts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of alerts
	 */
	public static List<Alert> findAll(
		int start, int end, OrderByComparator<Alert> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
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

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AlertPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AlertPersistence, AlertPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AlertPersistence.class);

		ServiceTracker<AlertPersistence, AlertPersistence> serviceTracker =
			new ServiceTracker<AlertPersistence, AlertPersistence>(
				bundle.getBundleContext(), AlertPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}