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

import eu.strasbourg.service.csmap.model.BaseNonce;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the base nonce service. This utility wraps <code>eu.strasbourg.service.csmap.service.persistence.impl.BaseNoncePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BaseNoncePersistence
 * @generated
 */
public class BaseNonceUtil {

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
	public static void clearCache(BaseNonce baseNonce) {
		getPersistence().clearCache(baseNonce);
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
	public static Map<Serializable, BaseNonce> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<BaseNonce> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BaseNonce> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BaseNonce> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<BaseNonce> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static BaseNonce update(BaseNonce baseNonce) {
		return getPersistence().update(baseNonce);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static BaseNonce update(
		BaseNonce baseNonce, ServiceContext serviceContext) {

		return getPersistence().update(baseNonce, serviceContext);
	}

	/**
	 * Returns the base nonce where value = &#63; or throws a <code>NoSuchBaseNonceException</code> if it could not be found.
	 *
	 * @param value the value
	 * @return the matching base nonce
	 * @throws NoSuchBaseNonceException if a matching base nonce could not be found
	 */
	public static BaseNonce findByValue(String value)
		throws eu.strasbourg.service.csmap.exception.NoSuchBaseNonceException {

		return getPersistence().findByValue(value);
	}

	/**
	 * Returns the base nonce where value = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param value the value
	 * @return the matching base nonce, or <code>null</code> if a matching base nonce could not be found
	 */
	public static BaseNonce fetchByValue(String value) {
		return getPersistence().fetchByValue(value);
	}

	/**
	 * Returns the base nonce where value = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param value the value
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching base nonce, or <code>null</code> if a matching base nonce could not be found
	 */
	public static BaseNonce fetchByValue(String value, boolean useFinderCache) {
		return getPersistence().fetchByValue(value, useFinderCache);
	}

	/**
	 * Removes the base nonce where value = &#63; from the database.
	 *
	 * @param value the value
	 * @return the base nonce that was removed
	 */
	public static BaseNonce removeByValue(String value)
		throws eu.strasbourg.service.csmap.exception.NoSuchBaseNonceException {

		return getPersistence().removeByValue(value);
	}

	/**
	 * Returns the number of base nonces where value = &#63;.
	 *
	 * @param value the value
	 * @return the number of matching base nonces
	 */
	public static int countByValue(String value) {
		return getPersistence().countByValue(value);
	}

	/**
	 * Caches the base nonce in the entity cache if it is enabled.
	 *
	 * @param baseNonce the base nonce
	 */
	public static void cacheResult(BaseNonce baseNonce) {
		getPersistence().cacheResult(baseNonce);
	}

	/**
	 * Caches the base nonces in the entity cache if it is enabled.
	 *
	 * @param baseNonces the base nonces
	 */
	public static void cacheResult(List<BaseNonce> baseNonces) {
		getPersistence().cacheResult(baseNonces);
	}

	/**
	 * Creates a new base nonce with the primary key. Does not add the base nonce to the database.
	 *
	 * @param baseNonceId the primary key for the new base nonce
	 * @return the new base nonce
	 */
	public static BaseNonce create(long baseNonceId) {
		return getPersistence().create(baseNonceId);
	}

	/**
	 * Removes the base nonce with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param baseNonceId the primary key of the base nonce
	 * @return the base nonce that was removed
	 * @throws NoSuchBaseNonceException if a base nonce with the primary key could not be found
	 */
	public static BaseNonce remove(long baseNonceId)
		throws eu.strasbourg.service.csmap.exception.NoSuchBaseNonceException {

		return getPersistence().remove(baseNonceId);
	}

	public static BaseNonce updateImpl(BaseNonce baseNonce) {
		return getPersistence().updateImpl(baseNonce);
	}

	/**
	 * Returns the base nonce with the primary key or throws a <code>NoSuchBaseNonceException</code> if it could not be found.
	 *
	 * @param baseNonceId the primary key of the base nonce
	 * @return the base nonce
	 * @throws NoSuchBaseNonceException if a base nonce with the primary key could not be found
	 */
	public static BaseNonce findByPrimaryKey(long baseNonceId)
		throws eu.strasbourg.service.csmap.exception.NoSuchBaseNonceException {

		return getPersistence().findByPrimaryKey(baseNonceId);
	}

	/**
	 * Returns the base nonce with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param baseNonceId the primary key of the base nonce
	 * @return the base nonce, or <code>null</code> if a base nonce with the primary key could not be found
	 */
	public static BaseNonce fetchByPrimaryKey(long baseNonceId) {
		return getPersistence().fetchByPrimaryKey(baseNonceId);
	}

	/**
	 * Returns all the base nonces.
	 *
	 * @return the base nonces
	 */
	public static List<BaseNonce> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the base nonces.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BaseNonceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of base nonces
	 * @param end the upper bound of the range of base nonces (not inclusive)
	 * @return the range of base nonces
	 */
	public static List<BaseNonce> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the base nonces.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BaseNonceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of base nonces
	 * @param end the upper bound of the range of base nonces (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of base nonces
	 */
	public static List<BaseNonce> findAll(
		int start, int end, OrderByComparator<BaseNonce> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the base nonces.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BaseNonceModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of base nonces
	 * @param end the upper bound of the range of base nonces (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of base nonces
	 */
	public static List<BaseNonce> findAll(
		int start, int end, OrderByComparator<BaseNonce> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the base nonces from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of base nonces.
	 *
	 * @return the number of base nonces
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static BaseNoncePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BaseNoncePersistence, BaseNoncePersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(BaseNoncePersistence.class);

		ServiceTracker<BaseNoncePersistence, BaseNoncePersistence>
			serviceTracker =
				new ServiceTracker<BaseNoncePersistence, BaseNoncePersistence>(
					bundle.getBundleContext(), BaseNoncePersistence.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}