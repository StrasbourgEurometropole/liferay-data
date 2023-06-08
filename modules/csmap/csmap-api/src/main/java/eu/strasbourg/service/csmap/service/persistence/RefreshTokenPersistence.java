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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.csmap.exception.NoSuchRefreshTokenException;
import eu.strasbourg.service.csmap.model.RefreshToken;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the refresh token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RefreshTokenUtil
 * @generated
 */
@ProviderType
public interface RefreshTokenPersistence extends BasePersistence<RefreshToken> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RefreshTokenUtil} to access the refresh token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the refresh tokens where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching refresh tokens
	 */
	public java.util.List<RefreshToken> findByUuid(String uuid);

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
	public java.util.List<RefreshToken> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<RefreshToken> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RefreshToken>
			orderByComparator);

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
	public java.util.List<RefreshToken> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RefreshToken>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first refresh token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching refresh token
	 * @throws NoSuchRefreshTokenException if a matching refresh token could not be found
	 */
	public RefreshToken findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<RefreshToken>
				orderByComparator)
		throws NoSuchRefreshTokenException;

	/**
	 * Returns the first refresh token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	public RefreshToken fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RefreshToken>
			orderByComparator);

	/**
	 * Returns the last refresh token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching refresh token
	 * @throws NoSuchRefreshTokenException if a matching refresh token could not be found
	 */
	public RefreshToken findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<RefreshToken>
				orderByComparator)
		throws NoSuchRefreshTokenException;

	/**
	 * Returns the last refresh token in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	public RefreshToken fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<RefreshToken>
			orderByComparator);

	/**
	 * Returns the refresh tokens before and after the current refresh token in the ordered set where uuid = &#63;.
	 *
	 * @param refreshTokenId the primary key of the current refresh token
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next refresh token
	 * @throws NoSuchRefreshTokenException if a refresh token with the primary key could not be found
	 */
	public RefreshToken[] findByUuid_PrevAndNext(
			long refreshTokenId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<RefreshToken>
				orderByComparator)
		throws NoSuchRefreshTokenException;

	/**
	 * Removes all the refresh tokens where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of refresh tokens where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching refresh tokens
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the refresh token where value = &#63; or throws a <code>NoSuchRefreshTokenException</code> if it could not be found.
	 *
	 * @param value the value
	 * @return the matching refresh token
	 * @throws NoSuchRefreshTokenException if a matching refresh token could not be found
	 */
	public RefreshToken findByValue(String value)
		throws NoSuchRefreshTokenException;

	/**
	 * Returns the refresh token where value = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param value the value
	 * @return the matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	public RefreshToken fetchByValue(String value);

	/**
	 * Returns the refresh token where value = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param value the value
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	public RefreshToken fetchByValue(String value, boolean useFinderCache);

	/**
	 * Removes the refresh token where value = &#63; from the database.
	 *
	 * @param value the value
	 * @return the refresh token that was removed
	 */
	public RefreshToken removeByValue(String value)
		throws NoSuchRefreshTokenException;

	/**
	 * Returns the number of refresh tokens where value = &#63;.
	 *
	 * @param value the value
	 * @return the number of matching refresh tokens
	 */
	public int countByValue(String value);

	/**
	 * Returns all the refresh tokens where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the matching refresh tokens
	 */
	public java.util.List<RefreshToken> findByPublikId(String publikId);

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
	public java.util.List<RefreshToken> findByPublikId(
		String publikId, int start, int end);

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
	public java.util.List<RefreshToken> findByPublikId(
		String publikId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RefreshToken>
			orderByComparator);

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
	public java.util.List<RefreshToken> findByPublikId(
		String publikId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RefreshToken>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first refresh token in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching refresh token
	 * @throws NoSuchRefreshTokenException if a matching refresh token could not be found
	 */
	public RefreshToken findByPublikId_First(
			String publikId,
			com.liferay.portal.kernel.util.OrderByComparator<RefreshToken>
				orderByComparator)
		throws NoSuchRefreshTokenException;

	/**
	 * Returns the first refresh token in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	public RefreshToken fetchByPublikId_First(
		String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<RefreshToken>
			orderByComparator);

	/**
	 * Returns the last refresh token in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching refresh token
	 * @throws NoSuchRefreshTokenException if a matching refresh token could not be found
	 */
	public RefreshToken findByPublikId_Last(
			String publikId,
			com.liferay.portal.kernel.util.OrderByComparator<RefreshToken>
				orderByComparator)
		throws NoSuchRefreshTokenException;

	/**
	 * Returns the last refresh token in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching refresh token, or <code>null</code> if a matching refresh token could not be found
	 */
	public RefreshToken fetchByPublikId_Last(
		String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<RefreshToken>
			orderByComparator);

	/**
	 * Returns the refresh tokens before and after the current refresh token in the ordered set where publikId = &#63;.
	 *
	 * @param refreshTokenId the primary key of the current refresh token
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next refresh token
	 * @throws NoSuchRefreshTokenException if a refresh token with the primary key could not be found
	 */
	public RefreshToken[] findByPublikId_PrevAndNext(
			long refreshTokenId, String publikId,
			com.liferay.portal.kernel.util.OrderByComparator<RefreshToken>
				orderByComparator)
		throws NoSuchRefreshTokenException;

	/**
	 * Removes all the refresh tokens where publikId = &#63; from the database.
	 *
	 * @param publikId the publik ID
	 */
	public void removeByPublikId(String publikId);

	/**
	 * Returns the number of refresh tokens where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the number of matching refresh tokens
	 */
	public int countByPublikId(String publikId);

	/**
	 * Caches the refresh token in the entity cache if it is enabled.
	 *
	 * @param refreshToken the refresh token
	 */
	public void cacheResult(RefreshToken refreshToken);

	/**
	 * Caches the refresh tokens in the entity cache if it is enabled.
	 *
	 * @param refreshTokens the refresh tokens
	 */
	public void cacheResult(java.util.List<RefreshToken> refreshTokens);

	/**
	 * Creates a new refresh token with the primary key. Does not add the refresh token to the database.
	 *
	 * @param refreshTokenId the primary key for the new refresh token
	 * @return the new refresh token
	 */
	public RefreshToken create(long refreshTokenId);

	/**
	 * Removes the refresh token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param refreshTokenId the primary key of the refresh token
	 * @return the refresh token that was removed
	 * @throws NoSuchRefreshTokenException if a refresh token with the primary key could not be found
	 */
	public RefreshToken remove(long refreshTokenId)
		throws NoSuchRefreshTokenException;

	public RefreshToken updateImpl(RefreshToken refreshToken);

	/**
	 * Returns the refresh token with the primary key or throws a <code>NoSuchRefreshTokenException</code> if it could not be found.
	 *
	 * @param refreshTokenId the primary key of the refresh token
	 * @return the refresh token
	 * @throws NoSuchRefreshTokenException if a refresh token with the primary key could not be found
	 */
	public RefreshToken findByPrimaryKey(long refreshTokenId)
		throws NoSuchRefreshTokenException;

	/**
	 * Returns the refresh token with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param refreshTokenId the primary key of the refresh token
	 * @return the refresh token, or <code>null</code> if a refresh token with the primary key could not be found
	 */
	public RefreshToken fetchByPrimaryKey(long refreshTokenId);

	/**
	 * Returns all the refresh tokens.
	 *
	 * @return the refresh tokens
	 */
	public java.util.List<RefreshToken> findAll();

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
	public java.util.List<RefreshToken> findAll(int start, int end);

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
	public java.util.List<RefreshToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RefreshToken>
			orderByComparator);

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
	public java.util.List<RefreshToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RefreshToken>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the refresh tokens from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of refresh tokens.
	 *
	 * @return the number of refresh tokens
	 */
	public int countAll();

}