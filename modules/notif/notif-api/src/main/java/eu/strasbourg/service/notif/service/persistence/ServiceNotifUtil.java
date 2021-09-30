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

import aQute.bnd.annotation.ProviderType;

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
@ProviderType
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of service notifs
	 */
	public static List<ServiceNotif> findAll(
		int start, int end, OrderByComparator<ServiceNotif> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
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