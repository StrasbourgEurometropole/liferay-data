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

package eu.strasbourg.service.csmap.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.csmap.model.Nonce;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the nonce service. This utility wraps <code>eu.strasbourg.service.csmap.service.persistence.impl.NoncePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NoncePersistence
 * @generated
 */
@ProviderType
public class NonceUtil {

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
	public static void clearCache(Nonce nonce) {
		getPersistence().clearCache(nonce);
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
	public static Map<Serializable, Nonce> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Nonce> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Nonce> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Nonce> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Nonce> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Nonce update(Nonce nonce) {
		return getPersistence().update(nonce);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Nonce update(Nonce nonce, ServiceContext serviceContext) {
		return getPersistence().update(nonce, serviceContext);
	}

	/**
	 * Returns all the nonces where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching nonces
	 */
	public static List<Nonce> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the nonces where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of nonces
	 * @param end the upper bound of the range of nonces (not inclusive)
	 * @return the range of matching nonces
	 */
	public static List<Nonce> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the nonces where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of nonces
	 * @param end the upper bound of the range of nonces (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching nonces
	 */
	public static List<Nonce> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Nonce> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the nonces where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of nonces
	 * @param end the upper bound of the range of nonces (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching nonces
	 */
	public static List<Nonce> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Nonce> orderByComparator, boolean retrieveFromCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first nonce in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching nonce
	 * @throws NoSuchNonceException if a matching nonce could not be found
	 */
	public static Nonce findByUuid_First(
			String uuid, OrderByComparator<Nonce> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.NoSuchNonceException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first nonce in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching nonce, or <code>null</code> if a matching nonce could not be found
	 */
	public static Nonce fetchByUuid_First(
		String uuid, OrderByComparator<Nonce> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last nonce in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching nonce
	 * @throws NoSuchNonceException if a matching nonce could not be found
	 */
	public static Nonce findByUuid_Last(
			String uuid, OrderByComparator<Nonce> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.NoSuchNonceException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last nonce in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching nonce, or <code>null</code> if a matching nonce could not be found
	 */
	public static Nonce fetchByUuid_Last(
		String uuid, OrderByComparator<Nonce> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the nonces before and after the current nonce in the ordered set where uuid = &#63;.
	 *
	 * @param nonceId the primary key of the current nonce
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next nonce
	 * @throws NoSuchNonceException if a nonce with the primary key could not be found
	 */
	public static Nonce[] findByUuid_PrevAndNext(
			long nonceId, String uuid,
			OrderByComparator<Nonce> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.NoSuchNonceException {

		return getPersistence().findByUuid_PrevAndNext(
			nonceId, uuid, orderByComparator);
	}

	/**
	 * Removes all the nonces where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of nonces where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching nonces
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the nonce where value = &#63; or throws a <code>NoSuchNonceException</code> if it could not be found.
	 *
	 * @param value the value
	 * @return the matching nonce
	 * @throws NoSuchNonceException if a matching nonce could not be found
	 */
	public static Nonce findByValue(String value)
		throws eu.strasbourg.service.csmap.exception.NoSuchNonceException {

		return getPersistence().findByValue(value);
	}

	/**
	 * Returns the nonce where value = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param value the value
	 * @return the matching nonce, or <code>null</code> if a matching nonce could not be found
	 */
	public static Nonce fetchByValue(String value) {
		return getPersistence().fetchByValue(value);
	}

	/**
	 * Returns the nonce where value = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param value the value
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching nonce, or <code>null</code> if a matching nonce could not be found
	 */
	public static Nonce fetchByValue(String value, boolean retrieveFromCache) {
		return getPersistence().fetchByValue(value, retrieveFromCache);
	}

	/**
	 * Removes the nonce where value = &#63; from the database.
	 *
	 * @param value the value
	 * @return the nonce that was removed
	 */
	public static Nonce removeByValue(String value)
		throws eu.strasbourg.service.csmap.exception.NoSuchNonceException {

		return getPersistence().removeByValue(value);
	}

	/**
	 * Returns the number of nonces where value = &#63;.
	 *
	 * @param value the value
	 * @return the number of matching nonces
	 */
	public static int countByValue(String value) {
		return getPersistence().countByValue(value);
	}

	/**
	 * Caches the nonce in the entity cache if it is enabled.
	 *
	 * @param nonce the nonce
	 */
	public static void cacheResult(Nonce nonce) {
		getPersistence().cacheResult(nonce);
	}

	/**
	 * Caches the nonces in the entity cache if it is enabled.
	 *
	 * @param nonces the nonces
	 */
	public static void cacheResult(List<Nonce> nonces) {
		getPersistence().cacheResult(nonces);
	}

	/**
	 * Creates a new nonce with the primary key. Does not add the nonce to the database.
	 *
	 * @param nonceId the primary key for the new nonce
	 * @return the new nonce
	 */
	public static Nonce create(long nonceId) {
		return getPersistence().create(nonceId);
	}

	/**
	 * Removes the nonce with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param nonceId the primary key of the nonce
	 * @return the nonce that was removed
	 * @throws NoSuchNonceException if a nonce with the primary key could not be found
	 */
	public static Nonce remove(long nonceId)
		throws eu.strasbourg.service.csmap.exception.NoSuchNonceException {

		return getPersistence().remove(nonceId);
	}

	public static Nonce updateImpl(Nonce nonce) {
		return getPersistence().updateImpl(nonce);
	}

	/**
	 * Returns the nonce with the primary key or throws a <code>NoSuchNonceException</code> if it could not be found.
	 *
	 * @param nonceId the primary key of the nonce
	 * @return the nonce
	 * @throws NoSuchNonceException if a nonce with the primary key could not be found
	 */
	public static Nonce findByPrimaryKey(long nonceId)
		throws eu.strasbourg.service.csmap.exception.NoSuchNonceException {

		return getPersistence().findByPrimaryKey(nonceId);
	}

	/**
	 * Returns the nonce with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param nonceId the primary key of the nonce
	 * @return the nonce, or <code>null</code> if a nonce with the primary key could not be found
	 */
	public static Nonce fetchByPrimaryKey(long nonceId) {
		return getPersistence().fetchByPrimaryKey(nonceId);
	}

	/**
	 * Returns all the nonces.
	 *
	 * @return the nonces
	 */
	public static List<Nonce> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the nonces.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nonces
	 * @param end the upper bound of the range of nonces (not inclusive)
	 * @return the range of nonces
	 */
	public static List<Nonce> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the nonces.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nonces
	 * @param end the upper bound of the range of nonces (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of nonces
	 */
	public static List<Nonce> findAll(
		int start, int end, OrderByComparator<Nonce> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the nonces.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nonces
	 * @param end the upper bound of the range of nonces (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of nonces
	 */
	public static List<Nonce> findAll(
		int start, int end, OrderByComparator<Nonce> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the nonces from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of nonces.
	 *
	 * @return the number of nonces
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static NoncePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NoncePersistence, NoncePersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(NoncePersistence.class);

		ServiceTracker<NoncePersistence, NoncePersistence> serviceTracker =
			new ServiceTracker<NoncePersistence, NoncePersistence>(
				bundle.getBundleContext(), NoncePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}