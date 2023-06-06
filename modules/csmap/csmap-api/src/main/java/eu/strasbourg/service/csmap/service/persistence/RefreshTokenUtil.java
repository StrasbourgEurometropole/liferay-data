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

import eu.strasbourg.service.csmap.model.RefreshToken;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the refresh token service. This utility wraps <code>eu.strasbourg.service.csmap.service.persistence.impl.RefreshTokenPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RefreshTokenPersistence
 * @generated
 */
public class RefreshTokenUtil {

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
	public static void clearCache(RefreshToken refreshToken) {
		getPersistence().clearCache(refreshToken);
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
	public static Map<Serializable, RefreshToken> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<RefreshToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RefreshToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RefreshToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RefreshToken> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RefreshToken update(RefreshToken refreshToken) {
		return getPersistence().update(refreshToken);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RefreshToken update(
		RefreshToken refreshToken, ServiceContext serviceContext) {

		return getPersistence().update(refreshToken, serviceContext);
	}

	/**
	 * Returns all the refresh tokens where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching refresh tokens
	 */
	public static List<RefreshToken> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the refresh tokens where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @return the range of matching refresh tokens
	 */
	public static List<RefreshToken> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the refresh tokens where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching refresh tokens
	 */
	public static List<RefreshToken> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<RefreshToken> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the refresh tokens where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching refresh tokens
	 */
	public static List<RefreshToken> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<RefreshToken> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first refresh token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching refresh token
	 * @throws NoSuchRefreshTokenException if a matching refresh token could not be found
	 */
	public static RefreshToken findByUuid_First(
			String uuid, OrderByComparator<RefreshToken> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.
			NoSuchRefreshTokenException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first refresh token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	public static RefreshToken fetchByUuid_First(
		String uuid, OrderByComparator<RefreshToken> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last refresh token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching refresh token
	 * @throws NoSuchRefreshTokenException if a matching refresh token could not be found
	 */
	public static RefreshToken findByUuid_Last(
			String uuid, OrderByComparator<RefreshToken> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.
			NoSuchRefreshTokenException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last refresh token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	public static RefreshToken fetchByUuid_Last(
		String uuid, OrderByComparator<RefreshToken> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the refresh tokens before and after the current refresh token in the ordered set where uuid = &#63;.
	 *
	 * @param refreshTokenId the primary key of the current refresh token
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next refresh token
	 * @throws NoSuchRefreshTokenException if a refresh token with the primary key could not be found
	 */
	public static RefreshToken[] findByUuid_PrevAndNext(
			long refreshTokenId, String uuid,
			OrderByComparator<RefreshToken> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.
			NoSuchRefreshTokenException {

		return getPersistence().findByUuid_PrevAndNext(
			refreshTokenId, uuid, orderByComparator);
	}

	/**
	 * Removes all the refresh tokens where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of refresh tokens where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching refresh tokens
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the refresh token where value = &#63; or throws a <code>NoSuchRefreshTokenException</code> if it could not be found.
	 *
	 * @param value the value
	 * @return the matching refresh token
	 * @throws NoSuchRefreshTokenException if a matching refresh token could not be found
	 */
	public static RefreshToken findByValue(String value)
		throws eu.strasbourg.service.csmap.exception.
			NoSuchRefreshTokenException {

		return getPersistence().findByValue(value);
	}

	/**
	 * Returns the refresh token where value = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param value the value
	 * @return the matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	public static RefreshToken fetchByValue(String value) {
		return getPersistence().fetchByValue(value);
	}

	/**
	 * Returns the refresh token where value = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param value the value
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	public static RefreshToken fetchByValue(
		String value, boolean useFinderCache) {

		return getPersistence().fetchByValue(value, useFinderCache);
	}

	/**
	 * Removes the refresh token where value = &#63; from the database.
	 *
	 * @param value the value
	 * @return the refresh token that was removed
	 */
	public static RefreshToken removeByValue(String value)
		throws eu.strasbourg.service.csmap.exception.
			NoSuchRefreshTokenException {

		return getPersistence().removeByValue(value);
	}

	/**
	 * Returns the number of refresh tokens where value = &#63;.
	 *
	 * @param value the value
	 * @return the number of matching refresh tokens
	 */
	public static int countByValue(String value) {
		return getPersistence().countByValue(value);
	}

	/**
	 * Returns all the refresh tokens where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the matching refresh tokens
	 */
	public static List<RefreshToken> findByPublikId(String publikId) {
		return getPersistence().findByPublikId(publikId);
	}

	/**
	 * Returns a range of all the refresh tokens where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @return the range of matching refresh tokens
	 */
	public static List<RefreshToken> findByPublikId(
		String publikId, int start, int end) {

		return getPersistence().findByPublikId(publikId, start, end);
	}

	/**
	 * Returns an ordered range of all the refresh tokens where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching refresh tokens
	 */
	public static List<RefreshToken> findByPublikId(
		String publikId, int start, int end,
		OrderByComparator<RefreshToken> orderByComparator) {

		return getPersistence().findByPublikId(
			publikId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the refresh tokens where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching refresh tokens
	 */
	public static List<RefreshToken> findByPublikId(
		String publikId, int start, int end,
		OrderByComparator<RefreshToken> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPublikId(
			publikId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first refresh token in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching refresh token
	 * @throws NoSuchRefreshTokenException if a matching refresh token could not be found
	 */
	public static RefreshToken findByPublikId_First(
			String publikId, OrderByComparator<RefreshToken> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.
			NoSuchRefreshTokenException {

		return getPersistence().findByPublikId_First(
			publikId, orderByComparator);
	}

	/**
	 * Returns the first refresh token in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	public static RefreshToken fetchByPublikId_First(
		String publikId, OrderByComparator<RefreshToken> orderByComparator) {

		return getPersistence().fetchByPublikId_First(
			publikId, orderByComparator);
	}

	/**
	 * Returns the last refresh token in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching refresh token
	 * @throws NoSuchRefreshTokenException if a matching refresh token could not be found
	 */
	public static RefreshToken findByPublikId_Last(
			String publikId, OrderByComparator<RefreshToken> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.
			NoSuchRefreshTokenException {

		return getPersistence().findByPublikId_Last(
			publikId, orderByComparator);
	}

	/**
	 * Returns the last refresh token in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	public static RefreshToken fetchByPublikId_Last(
		String publikId, OrderByComparator<RefreshToken> orderByComparator) {

		return getPersistence().fetchByPublikId_Last(
			publikId, orderByComparator);
	}

	/**
	 * Returns the refresh tokens before and after the current refresh token in the ordered set where publikId = &#63;.
	 *
	 * @param refreshTokenId the primary key of the current refresh token
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next refresh token
	 * @throws NoSuchRefreshTokenException if a refresh token with the primary key could not be found
	 */
	public static RefreshToken[] findByPublikId_PrevAndNext(
			long refreshTokenId, String publikId,
			OrderByComparator<RefreshToken> orderByComparator)
		throws eu.strasbourg.service.csmap.exception.
			NoSuchRefreshTokenException {

		return getPersistence().findByPublikId_PrevAndNext(
			refreshTokenId, publikId, orderByComparator);
	}

	/**
	 * Removes all the refresh tokens where publikId = &#63; from the database.
	 *
	 * @param publikId the publik ID
	 */
	public static void removeByPublikId(String publikId) {
		getPersistence().removeByPublikId(publikId);
	}

	/**
	 * Returns the number of refresh tokens where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the number of matching refresh tokens
	 */
	public static int countByPublikId(String publikId) {
		return getPersistence().countByPublikId(publikId);
	}

	/**
	 * Caches the refresh token in the entity cache if it is enabled.
	 *
	 * @param refreshToken the refresh token
	 */
	public static void cacheResult(RefreshToken refreshToken) {
		getPersistence().cacheResult(refreshToken);
	}

	/**
	 * Caches the refresh tokens in the entity cache if it is enabled.
	 *
	 * @param refreshTokens the refresh tokens
	 */
	public static void cacheResult(List<RefreshToken> refreshTokens) {
		getPersistence().cacheResult(refreshTokens);
	}

	/**
	 * Creates a new refresh token with the primary key. Does not add the refresh token to the database.
	 *
	 * @param refreshTokenId the primary key for the new refresh token
	 * @return the new refresh token
	 */
	public static RefreshToken create(long refreshTokenId) {
		return getPersistence().create(refreshTokenId);
	}

	/**
	 * Removes the refresh token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param refreshTokenId the primary key of the refresh token
	 * @return the refresh token that was removed
	 * @throws NoSuchRefreshTokenException if a refresh token with the primary key could not be found
	 */
	public static RefreshToken remove(long refreshTokenId)
		throws eu.strasbourg.service.csmap.exception.
			NoSuchRefreshTokenException {

		return getPersistence().remove(refreshTokenId);
	}

	public static RefreshToken updateImpl(RefreshToken refreshToken) {
		return getPersistence().updateImpl(refreshToken);
	}

	/**
	 * Returns the refresh token with the primary key or throws a <code>NoSuchRefreshTokenException</code> if it could not be found.
	 *
	 * @param refreshTokenId the primary key of the refresh token
	 * @return the refresh token
	 * @throws NoSuchRefreshTokenException if a refresh token with the primary key could not be found
	 */
	public static RefreshToken findByPrimaryKey(long refreshTokenId)
		throws eu.strasbourg.service.csmap.exception.
			NoSuchRefreshTokenException {

		return getPersistence().findByPrimaryKey(refreshTokenId);
	}

	/**
	 * Returns the refresh token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param refreshTokenId the primary key of the refresh token
	 * @return the refresh token, or <code>null</code> if a refresh token with the primary key could not be found
	 */
	public static RefreshToken fetchByPrimaryKey(long refreshTokenId) {
		return getPersistence().fetchByPrimaryKey(refreshTokenId);
	}

	/**
	 * Returns all the refresh tokens.
	 *
	 * @return the refresh tokens
	 */
	public static List<RefreshToken> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the refresh tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @return the range of refresh tokens
	 */
	public static List<RefreshToken> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the refresh tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of refresh tokens
	 */
	public static List<RefreshToken> findAll(
		int start, int end, OrderByComparator<RefreshToken> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the refresh tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of refresh tokens
	 */
	public static List<RefreshToken> findAll(
		int start, int end, OrderByComparator<RefreshToken> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the refresh tokens from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of refresh tokens.
	 *
	 * @return the number of refresh tokens
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static RefreshTokenPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<RefreshTokenPersistence, RefreshTokenPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RefreshTokenPersistence.class);

		ServiceTracker<RefreshTokenPersistence, RefreshTokenPersistence>
			serviceTracker =
				new ServiceTracker
					<RefreshTokenPersistence, RefreshTokenPersistence>(
						bundle.getBundleContext(),
						RefreshTokenPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}