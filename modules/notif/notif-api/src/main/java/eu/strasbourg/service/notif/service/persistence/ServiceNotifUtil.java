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

package eu.strasbourg.service.notif.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.notif.model.ServiceNotif;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the service notif service. This utility wraps <code>eu.strasbourg.service.notif.service.persistence.impl.ServiceNotifPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceNotifPersistence
 * @generated
 */
public class ServiceNotifUtil {

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
	public static void clearCache(ServiceNotif serviceNotif) {
		getPersistence().clearCache(serviceNotif);
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
	public static Map<Serializable, ServiceNotif> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ServiceNotif> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ServiceNotif> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ServiceNotif> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ServiceNotif> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ServiceNotif update(ServiceNotif serviceNotif) {
		return getPersistence().update(serviceNotif);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ServiceNotif update(
		ServiceNotif serviceNotif, ServiceContext serviceContext) {

		return getPersistence().update(serviceNotif, serviceContext);
	}

	/**
	 * Returns all the service notifs where organisationId = &#63;.
	 *
	 * @param organisationId the organisation ID
	 * @return the matching service notifs
	 */
	public static List<ServiceNotif> findByOrganisationIds(
		long organisationId) {

		return getPersistence().findByOrganisationIds(organisationId);
	}

	/**
	 * Returns a range of all the service notifs where organisationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>.
	 * </p>
	 *
	 * @param organisationId the organisation ID
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @return the range of matching service notifs
	 */
	public static List<ServiceNotif> findByOrganisationIds(
		long organisationId, int start, int end) {

		return getPersistence().findByOrganisationIds(
			organisationId, start, end);
	}

	/**
	 * Returns an ordered range of all the service notifs where organisationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>.
	 * </p>
	 *
	 * @param organisationId the organisation ID
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service notifs
	 */
	public static List<ServiceNotif> findByOrganisationIds(
		long organisationId, int start, int end,
		OrderByComparator<ServiceNotif> orderByComparator) {

		return getPersistence().findByOrganisationIds(
			organisationId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the service notifs where organisationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>.
	 * </p>
	 *
	 * @param organisationId the organisation ID
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching service notifs
	 */
	public static List<ServiceNotif> findByOrganisationIds(
		long organisationId, int start, int end,
		OrderByComparator<ServiceNotif> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByOrganisationIds(
			organisationId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first service notif in the ordered set where organisationId = &#63;.
	 *
	 * @param organisationId the organisation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service notif
	 * @throws NoSuchServiceNotifException if a matching service notif could not be found
	 */
	public static ServiceNotif findByOrganisationIds_First(
			long organisationId,
			OrderByComparator<ServiceNotif> orderByComparator)
		throws eu.strasbourg.service.notif.exception.
			NoSuchServiceNotifException {

		return getPersistence().findByOrganisationIds_First(
			organisationId, orderByComparator);
	}

	/**
	 * Returns the first service notif in the ordered set where organisationId = &#63;.
	 *
	 * @param organisationId the organisation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service notif, or <code>null</code> if a matching service notif could not be found
	 */
	public static ServiceNotif fetchByOrganisationIds_First(
		long organisationId,
		OrderByComparator<ServiceNotif> orderByComparator) {

		return getPersistence().fetchByOrganisationIds_First(
			organisationId, orderByComparator);
	}

	/**
	 * Returns the last service notif in the ordered set where organisationId = &#63;.
	 *
	 * @param organisationId the organisation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service notif
	 * @throws NoSuchServiceNotifException if a matching service notif could not be found
	 */
	public static ServiceNotif findByOrganisationIds_Last(
			long organisationId,
			OrderByComparator<ServiceNotif> orderByComparator)
		throws eu.strasbourg.service.notif.exception.
			NoSuchServiceNotifException {

		return getPersistence().findByOrganisationIds_Last(
			organisationId, orderByComparator);
	}

	/**
	 * Returns the last service notif in the ordered set where organisationId = &#63;.
	 *
	 * @param organisationId the organisation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service notif, or <code>null</code> if a matching service notif could not be found
	 */
	public static ServiceNotif fetchByOrganisationIds_Last(
		long organisationId,
		OrderByComparator<ServiceNotif> orderByComparator) {

		return getPersistence().fetchByOrganisationIds_Last(
			organisationId, orderByComparator);
	}

	/**
	 * Returns the service notifs before and after the current service notif in the ordered set where organisationId = &#63;.
	 *
	 * @param serviceId the primary key of the current service notif
	 * @param organisationId the organisation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service notif
	 * @throws NoSuchServiceNotifException if a service notif with the primary key could not be found
	 */
	public static ServiceNotif[] findByOrganisationIds_PrevAndNext(
			long serviceId, long organisationId,
			OrderByComparator<ServiceNotif> orderByComparator)
		throws eu.strasbourg.service.notif.exception.
			NoSuchServiceNotifException {

		return getPersistence().findByOrganisationIds_PrevAndNext(
			serviceId, organisationId, orderByComparator);
	}

	/**
	 * Returns all the service notifs where organisationId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>.
	 * </p>
	 *
	 * @param organisationIds the organisation IDs
	 * @return the matching service notifs
	 */
	public static List<ServiceNotif> findByOrganisationIds(
		long[] organisationIds) {

		return getPersistence().findByOrganisationIds(organisationIds);
	}

	/**
	 * Returns a range of all the service notifs where organisationId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>.
	 * </p>
	 *
	 * @param organisationIds the organisation IDs
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @return the range of matching service notifs
	 */
	public static List<ServiceNotif> findByOrganisationIds(
		long[] organisationIds, int start, int end) {

		return getPersistence().findByOrganisationIds(
			organisationIds, start, end);
	}

	/**
	 * Returns an ordered range of all the service notifs where organisationId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>.
	 * </p>
	 *
	 * @param organisationIds the organisation IDs
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service notifs
	 */
	public static List<ServiceNotif> findByOrganisationIds(
		long[] organisationIds, int start, int end,
		OrderByComparator<ServiceNotif> orderByComparator) {

		return getPersistence().findByOrganisationIds(
			organisationIds, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the service notifs where organisationId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>.
	 * </p>
	 *
	 * @param organisationId the organisation ID
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching service notifs
	 */
	public static List<ServiceNotif> findByOrganisationIds(
		long[] organisationIds, int start, int end,
		OrderByComparator<ServiceNotif> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByOrganisationIds(
			organisationIds, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the service notifs where organisationId = &#63; from the database.
	 *
	 * @param organisationId the organisation ID
	 */
	public static void removeByOrganisationIds(long organisationId) {
		getPersistence().removeByOrganisationIds(organisationId);
	}

	/**
	 * Returns the number of service notifs where organisationId = &#63;.
	 *
	 * @param organisationId the organisation ID
	 * @return the number of matching service notifs
	 */
	public static int countByOrganisationIds(long organisationId) {
		return getPersistence().countByOrganisationIds(organisationId);
	}

	/**
	 * Returns the number of service notifs where organisationId = any &#63;.
	 *
	 * @param organisationIds the organisation IDs
	 * @return the number of matching service notifs
	 */
	public static int countByOrganisationIds(long[] organisationIds) {
		return getPersistence().countByOrganisationIds(organisationIds);
	}

	/**
	 * Returns the service notif where csmapTopic = &#63; or throws a <code>NoSuchServiceNotifException</code> if it could not be found.
	 *
	 * @param csmapTopic the csmap topic
	 * @return the matching service notif
	 * @throws NoSuchServiceNotifException if a matching service notif could not be found
	 */
	public static ServiceNotif findByTopic(String csmapTopic)
		throws eu.strasbourg.service.notif.exception.
			NoSuchServiceNotifException {

		return getPersistence().findByTopic(csmapTopic);
	}

	/**
	 * Returns the service notif where csmapTopic = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param csmapTopic the csmap topic
	 * @return the matching service notif, or <code>null</code> if a matching service notif could not be found
	 */
	public static ServiceNotif fetchByTopic(String csmapTopic) {
		return getPersistence().fetchByTopic(csmapTopic);
	}

	/**
	 * Returns the service notif where csmapTopic = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param csmapTopic the csmap topic
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching service notif, or <code>null</code> if a matching service notif could not be found
	 */
	public static ServiceNotif fetchByTopic(
		String csmapTopic, boolean useFinderCache) {

		return getPersistence().fetchByTopic(csmapTopic, useFinderCache);
	}

	/**
	 * Removes the service notif where csmapTopic = &#63; from the database.
	 *
	 * @param csmapTopic the csmap topic
	 * @return the service notif that was removed
	 */
	public static ServiceNotif removeByTopic(String csmapTopic)
		throws eu.strasbourg.service.notif.exception.
			NoSuchServiceNotifException {

		return getPersistence().removeByTopic(csmapTopic);
	}

	/**
	 * Returns the number of service notifs where csmapTopic = &#63;.
	 *
	 * @param csmapTopic the csmap topic
	 * @return the number of matching service notifs
	 */
	public static int countByTopic(String csmapTopic) {
		return getPersistence().countByTopic(csmapTopic);
	}

	/**
	 * Caches the service notif in the entity cache if it is enabled.
	 *
	 * @param serviceNotif the service notif
	 */
	public static void cacheResult(ServiceNotif serviceNotif) {
		getPersistence().cacheResult(serviceNotif);
	}

	/**
	 * Caches the service notifs in the entity cache if it is enabled.
	 *
	 * @param serviceNotifs the service notifs
	 */
	public static void cacheResult(List<ServiceNotif> serviceNotifs) {
		getPersistence().cacheResult(serviceNotifs);
	}

	/**
	 * Creates a new service notif with the primary key. Does not add the service notif to the database.
	 *
	 * @param serviceId the primary key for the new service notif
	 * @return the new service notif
	 */
	public static ServiceNotif create(long serviceId) {
		return getPersistence().create(serviceId);
	}

	/**
	 * Removes the service notif with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param serviceId the primary key of the service notif
	 * @return the service notif that was removed
	 * @throws NoSuchServiceNotifException if a service notif with the primary key could not be found
	 */
	public static ServiceNotif remove(long serviceId)
		throws eu.strasbourg.service.notif.exception.
			NoSuchServiceNotifException {

		return getPersistence().remove(serviceId);
	}

	public static ServiceNotif updateImpl(ServiceNotif serviceNotif) {
		return getPersistence().updateImpl(serviceNotif);
	}

	/**
	 * Returns the service notif with the primary key or throws a <code>NoSuchServiceNotifException</code> if it could not be found.
	 *
	 * @param serviceId the primary key of the service notif
	 * @return the service notif
	 * @throws NoSuchServiceNotifException if a service notif with the primary key could not be found
	 */
	public static ServiceNotif findByPrimaryKey(long serviceId)
		throws eu.strasbourg.service.notif.exception.
			NoSuchServiceNotifException {

		return getPersistence().findByPrimaryKey(serviceId);
	}

	/**
	 * Returns the service notif with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param serviceId the primary key of the service notif
	 * @return the service notif, or <code>null</code> if a service notif with the primary key could not be found
	 */
	public static ServiceNotif fetchByPrimaryKey(long serviceId) {
		return getPersistence().fetchByPrimaryKey(serviceId);
	}

	/**
	 * Returns all the service notifs.
	 *
	 * @return the service notifs
	 */
	public static List<ServiceNotif> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the service notifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @return the range of service notifs
	 */
	public static List<ServiceNotif> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the service notifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of service notifs
	 */
	public static List<ServiceNotif> findAll(
		int start, int end, OrderByComparator<ServiceNotif> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the service notifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of service notifs
	 */
	public static List<ServiceNotif> findAll(
		int start, int end, OrderByComparator<ServiceNotif> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the service notifs from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of service notifs.
	 *
	 * @return the number of service notifs
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ServiceNotifPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ServiceNotifPersistence, ServiceNotifPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ServiceNotifPersistence.class);

		ServiceTracker<ServiceNotifPersistence, ServiceNotifPersistence>
			serviceTracker =
				new ServiceTracker
					<ServiceNotifPersistence, ServiceNotifPersistence>(
						bundle.getBundleContext(),
						ServiceNotifPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}