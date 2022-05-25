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

import eu.strasbourg.service.notif.model.NatureNotif;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the nature notif service. This utility wraps <code>eu.strasbourg.service.notif.service.persistence.impl.NatureNotifPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NatureNotifPersistence
 * @generated
 */
public class NatureNotifUtil {

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
	public static void clearCache(NatureNotif natureNotif) {
		getPersistence().clearCache(natureNotif);
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
	public static Map<Serializable, NatureNotif> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<NatureNotif> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NatureNotif> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NatureNotif> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NatureNotif> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NatureNotif update(NatureNotif natureNotif) {
		return getPersistence().update(natureNotif);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NatureNotif update(
		NatureNotif natureNotif, ServiceContext serviceContext) {

		return getPersistence().update(natureNotif, serviceContext);
	}

	/**
	 * Returns all the nature notifs where serviceId = &#63;.
	 *
	 * @param serviceId the service ID
	 * @return the matching nature notifs
	 */
	public static List<NatureNotif> findByServiceId(long serviceId) {
		return getPersistence().findByServiceId(serviceId);
	}

	/**
	 * Returns a range of all the nature notifs where serviceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NatureNotifModelImpl</code>.
	 * </p>
	 *
	 * @param serviceId the service ID
	 * @param start the lower bound of the range of nature notifs
	 * @param end the upper bound of the range of nature notifs (not inclusive)
	 * @return the range of matching nature notifs
	 */
	public static List<NatureNotif> findByServiceId(
		long serviceId, int start, int end) {

		return getPersistence().findByServiceId(serviceId, start, end);
	}

	/**
	 * Returns an ordered range of all the nature notifs where serviceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NatureNotifModelImpl</code>.
	 * </p>
	 *
	 * @param serviceId the service ID
	 * @param start the lower bound of the range of nature notifs
	 * @param end the upper bound of the range of nature notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching nature notifs
	 */
	public static List<NatureNotif> findByServiceId(
		long serviceId, int start, int end,
		OrderByComparator<NatureNotif> orderByComparator) {

		return getPersistence().findByServiceId(
			serviceId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the nature notifs where serviceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NatureNotifModelImpl</code>.
	 * </p>
	 *
	 * @param serviceId the service ID
	 * @param start the lower bound of the range of nature notifs
	 * @param end the upper bound of the range of nature notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching nature notifs
	 */
	public static List<NatureNotif> findByServiceId(
		long serviceId, int start, int end,
		OrderByComparator<NatureNotif> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByServiceId(
			serviceId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first nature notif in the ordered set where serviceId = &#63;.
	 *
	 * @param serviceId the service ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching nature notif
	 * @throws NoSuchNatureNotifException if a matching nature notif could not be found
	 */
	public static NatureNotif findByServiceId_First(
			long serviceId, OrderByComparator<NatureNotif> orderByComparator)
		throws eu.strasbourg.service.notif.exception.
			NoSuchNatureNotifException {

		return getPersistence().findByServiceId_First(
			serviceId, orderByComparator);
	}

	/**
	 * Returns the first nature notif in the ordered set where serviceId = &#63;.
	 *
	 * @param serviceId the service ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching nature notif, or <code>null</code> if a matching nature notif could not be found
	 */
	public static NatureNotif fetchByServiceId_First(
		long serviceId, OrderByComparator<NatureNotif> orderByComparator) {

		return getPersistence().fetchByServiceId_First(
			serviceId, orderByComparator);
	}

	/**
	 * Returns the last nature notif in the ordered set where serviceId = &#63;.
	 *
	 * @param serviceId the service ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching nature notif
	 * @throws NoSuchNatureNotifException if a matching nature notif could not be found
	 */
	public static NatureNotif findByServiceId_Last(
			long serviceId, OrderByComparator<NatureNotif> orderByComparator)
		throws eu.strasbourg.service.notif.exception.
			NoSuchNatureNotifException {

		return getPersistence().findByServiceId_Last(
			serviceId, orderByComparator);
	}

	/**
	 * Returns the last nature notif in the ordered set where serviceId = &#63;.
	 *
	 * @param serviceId the service ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching nature notif, or <code>null</code> if a matching nature notif could not be found
	 */
	public static NatureNotif fetchByServiceId_Last(
		long serviceId, OrderByComparator<NatureNotif> orderByComparator) {

		return getPersistence().fetchByServiceId_Last(
			serviceId, orderByComparator);
	}

	/**
	 * Returns the nature notifs before and after the current nature notif in the ordered set where serviceId = &#63;.
	 *
	 * @param natureId the primary key of the current nature notif
	 * @param serviceId the service ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next nature notif
	 * @throws NoSuchNatureNotifException if a nature notif with the primary key could not be found
	 */
	public static NatureNotif[] findByServiceId_PrevAndNext(
			long natureId, long serviceId,
			OrderByComparator<NatureNotif> orderByComparator)
		throws eu.strasbourg.service.notif.exception.
			NoSuchNatureNotifException {

		return getPersistence().findByServiceId_PrevAndNext(
			natureId, serviceId, orderByComparator);
	}

	/**
	 * Removes all the nature notifs where serviceId = &#63; from the database.
	 *
	 * @param serviceId the service ID
	 */
	public static void removeByServiceId(long serviceId) {
		getPersistence().removeByServiceId(serviceId);
	}

	/**
	 * Returns the number of nature notifs where serviceId = &#63;.
	 *
	 * @param serviceId the service ID
	 * @return the number of matching nature notifs
	 */
	public static int countByServiceId(long serviceId) {
		return getPersistence().countByServiceId(serviceId);
	}

	/**
	 * Caches the nature notif in the entity cache if it is enabled.
	 *
	 * @param natureNotif the nature notif
	 */
	public static void cacheResult(NatureNotif natureNotif) {
		getPersistence().cacheResult(natureNotif);
	}

	/**
	 * Caches the nature notifs in the entity cache if it is enabled.
	 *
	 * @param natureNotifs the nature notifs
	 */
	public static void cacheResult(List<NatureNotif> natureNotifs) {
		getPersistence().cacheResult(natureNotifs);
	}

	/**
	 * Creates a new nature notif with the primary key. Does not add the nature notif to the database.
	 *
	 * @param natureId the primary key for the new nature notif
	 * @return the new nature notif
	 */
	public static NatureNotif create(long natureId) {
		return getPersistence().create(natureId);
	}

	/**
	 * Removes the nature notif with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param natureId the primary key of the nature notif
	 * @return the nature notif that was removed
	 * @throws NoSuchNatureNotifException if a nature notif with the primary key could not be found
	 */
	public static NatureNotif remove(long natureId)
		throws eu.strasbourg.service.notif.exception.
			NoSuchNatureNotifException {

		return getPersistence().remove(natureId);
	}

	public static NatureNotif updateImpl(NatureNotif natureNotif) {
		return getPersistence().updateImpl(natureNotif);
	}

	/**
	 * Returns the nature notif with the primary key or throws a <code>NoSuchNatureNotifException</code> if it could not be found.
	 *
	 * @param natureId the primary key of the nature notif
	 * @return the nature notif
	 * @throws NoSuchNatureNotifException if a nature notif with the primary key could not be found
	 */
	public static NatureNotif findByPrimaryKey(long natureId)
		throws eu.strasbourg.service.notif.exception.
			NoSuchNatureNotifException {

		return getPersistence().findByPrimaryKey(natureId);
	}

	/**
	 * Returns the nature notif with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param natureId the primary key of the nature notif
	 * @return the nature notif, or <code>null</code> if a nature notif with the primary key could not be found
	 */
	public static NatureNotif fetchByPrimaryKey(long natureId) {
		return getPersistence().fetchByPrimaryKey(natureId);
	}

	/**
	 * Returns all the nature notifs.
	 *
	 * @return the nature notifs
	 */
	public static List<NatureNotif> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the nature notifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NatureNotifModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of nature notifs
	 * @param end the upper bound of the range of nature notifs (not inclusive)
	 * @return the range of nature notifs
	 */
	public static List<NatureNotif> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the nature notifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NatureNotifModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of nature notifs
	 * @param end the upper bound of the range of nature notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of nature notifs
	 */
	public static List<NatureNotif> findAll(
		int start, int end, OrderByComparator<NatureNotif> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the nature notifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>NatureNotifModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of nature notifs
	 * @param end the upper bound of the range of nature notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of nature notifs
	 */
	public static List<NatureNotif> findAll(
		int start, int end, OrderByComparator<NatureNotif> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the nature notifs from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of nature notifs.
	 *
	 * @return the number of nature notifs
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static NatureNotifPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<NatureNotifPersistence, NatureNotifPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(NatureNotifPersistence.class);

		ServiceTracker<NatureNotifPersistence, NatureNotifPersistence>
			serviceTracker =
				new ServiceTracker
					<NatureNotifPersistence, NatureNotifPersistence>(
						bundle.getBundleContext(), NatureNotifPersistence.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}