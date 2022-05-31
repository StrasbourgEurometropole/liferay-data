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

package eu.strasbourg.service.oidc.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.oidc.model.PublikUser;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the publik user service. This utility wraps <code>eu.strasbourg.service.oidc.service.persistence.impl.PublikUserPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PublikUserPersistence
 * @generated
 */
public class PublikUserUtil {

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
	public static void clearCache(PublikUser publikUser) {
		getPersistence().clearCache(publikUser);
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
	public static Map<Serializable, PublikUser> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<PublikUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PublikUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PublikUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PublikUser> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PublikUser update(PublikUser publikUser) {
		return getPersistence().update(publikUser);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PublikUser update(
		PublikUser publikUser, ServiceContext serviceContext) {

		return getPersistence().update(publikUser, serviceContext);
	}

	/**
	 * Returns all the publik users where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching publik users
	 */
	public static List<PublikUser> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the publik users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PublikUserModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of publik users
	 * @param end the upper bound of the range of publik users (not inclusive)
	 * @return the range of matching publik users
	 */
	public static List<PublikUser> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the publik users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PublikUserModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of publik users
	 * @param end the upper bound of the range of publik users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching publik users
	 */
	public static List<PublikUser> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<PublikUser> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the publik users where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PublikUserModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of publik users
	 * @param end the upper bound of the range of publik users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching publik users
	 */
	public static List<PublikUser> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<PublikUser> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first publik user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching publik user
	 * @throws NoSuchPublikUserException if a matching publik user could not be found
	 */
	public static PublikUser findByUuid_First(
			String uuid, OrderByComparator<PublikUser> orderByComparator)
		throws eu.strasbourg.service.oidc.exception.NoSuchPublikUserException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first publik user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching publik user, or <code>null</code> if a matching publik user could not be found
	 */
	public static PublikUser fetchByUuid_First(
		String uuid, OrderByComparator<PublikUser> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last publik user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching publik user
	 * @throws NoSuchPublikUserException if a matching publik user could not be found
	 */
	public static PublikUser findByUuid_Last(
			String uuid, OrderByComparator<PublikUser> orderByComparator)
		throws eu.strasbourg.service.oidc.exception.NoSuchPublikUserException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last publik user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching publik user, or <code>null</code> if a matching publik user could not be found
	 */
	public static PublikUser fetchByUuid_Last(
		String uuid, OrderByComparator<PublikUser> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the publik users before and after the current publik user in the ordered set where uuid = &#63;.
	 *
	 * @param publikUserLiferayId the primary key of the current publik user
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next publik user
	 * @throws NoSuchPublikUserException if a publik user with the primary key could not be found
	 */
	public static PublikUser[] findByUuid_PrevAndNext(
			long publikUserLiferayId, String uuid,
			OrderByComparator<PublikUser> orderByComparator)
		throws eu.strasbourg.service.oidc.exception.NoSuchPublikUserException {

		return getPersistence().findByUuid_PrevAndNext(
			publikUserLiferayId, uuid, orderByComparator);
	}

	/**
	 * Removes all the publik users where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of publik users where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching publik users
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the publik user where publikId = &#63; or throws a <code>NoSuchPublikUserException</code> if it could not be found.
	 *
	 * @param publikId the publik ID
	 * @return the matching publik user
	 * @throws NoSuchPublikUserException if a matching publik user could not be found
	 */
	public static PublikUser findByPublikId(String publikId)
		throws eu.strasbourg.service.oidc.exception.NoSuchPublikUserException {

		return getPersistence().findByPublikId(publikId);
	}

	/**
	 * Returns the publik user where publikId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param publikId the publik ID
	 * @return the matching publik user, or <code>null</code> if a matching publik user could not be found
	 */
	public static PublikUser fetchByPublikId(String publikId) {
		return getPersistence().fetchByPublikId(publikId);
	}

	/**
	 * Returns the publik user where publikId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param publikId the publik ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching publik user, or <code>null</code> if a matching publik user could not be found
	 */
	public static PublikUser fetchByPublikId(
		String publikId, boolean useFinderCache) {

		return getPersistence().fetchByPublikId(publikId, useFinderCache);
	}

	/**
	 * Removes the publik user where publikId = &#63; from the database.
	 *
	 * @param publikId the publik ID
	 * @return the publik user that was removed
	 */
	public static PublikUser removeByPublikId(String publikId)
		throws eu.strasbourg.service.oidc.exception.NoSuchPublikUserException {

		return getPersistence().removeByPublikId(publikId);
	}

	/**
	 * Returns the number of publik users where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the number of matching publik users
	 */
	public static int countByPublikId(String publikId) {
		return getPersistence().countByPublikId(publikId);
	}

	/**
	 * Caches the publik user in the entity cache if it is enabled.
	 *
	 * @param publikUser the publik user
	 */
	public static void cacheResult(PublikUser publikUser) {
		getPersistence().cacheResult(publikUser);
	}

	/**
	 * Caches the publik users in the entity cache if it is enabled.
	 *
	 * @param publikUsers the publik users
	 */
	public static void cacheResult(List<PublikUser> publikUsers) {
		getPersistence().cacheResult(publikUsers);
	}

	/**
	 * Creates a new publik user with the primary key. Does not add the publik user to the database.
	 *
	 * @param publikUserLiferayId the primary key for the new publik user
	 * @return the new publik user
	 */
	public static PublikUser create(long publikUserLiferayId) {
		return getPersistence().create(publikUserLiferayId);
	}

	/**
	 * Removes the publik user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param publikUserLiferayId the primary key of the publik user
	 * @return the publik user that was removed
	 * @throws NoSuchPublikUserException if a publik user with the primary key could not be found
	 */
	public static PublikUser remove(long publikUserLiferayId)
		throws eu.strasbourg.service.oidc.exception.NoSuchPublikUserException {

		return getPersistence().remove(publikUserLiferayId);
	}

	public static PublikUser updateImpl(PublikUser publikUser) {
		return getPersistence().updateImpl(publikUser);
	}

	/**
	 * Returns the publik user with the primary key or throws a <code>NoSuchPublikUserException</code> if it could not be found.
	 *
	 * @param publikUserLiferayId the primary key of the publik user
	 * @return the publik user
	 * @throws NoSuchPublikUserException if a publik user with the primary key could not be found
	 */
	public static PublikUser findByPrimaryKey(long publikUserLiferayId)
		throws eu.strasbourg.service.oidc.exception.NoSuchPublikUserException {

		return getPersistence().findByPrimaryKey(publikUserLiferayId);
	}

	/**
	 * Returns the publik user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param publikUserLiferayId the primary key of the publik user
	 * @return the publik user, or <code>null</code> if a publik user with the primary key could not be found
	 */
	public static PublikUser fetchByPrimaryKey(long publikUserLiferayId) {
		return getPersistence().fetchByPrimaryKey(publikUserLiferayId);
	}

	/**
	 * Returns all the publik users.
	 *
	 * @return the publik users
	 */
	public static List<PublikUser> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the publik users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PublikUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of publik users
	 * @param end the upper bound of the range of publik users (not inclusive)
	 * @return the range of publik users
	 */
	public static List<PublikUser> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the publik users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PublikUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of publik users
	 * @param end the upper bound of the range of publik users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of publik users
	 */
	public static List<PublikUser> findAll(
		int start, int end, OrderByComparator<PublikUser> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the publik users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PublikUserModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of publik users
	 * @param end the upper bound of the range of publik users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of publik users
	 */
	public static List<PublikUser> findAll(
		int start, int end, OrderByComparator<PublikUser> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the publik users from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of publik users.
	 *
	 * @return the number of publik users
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PublikUserPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PublikUserPersistence, PublikUserPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PublikUserPersistence.class);

		ServiceTracker<PublikUserPersistence, PublikUserPersistence>
			serviceTracker =
				new ServiceTracker
					<PublikUserPersistence, PublikUserPersistence>(
						bundle.getBundleContext(), PublikUserPersistence.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}