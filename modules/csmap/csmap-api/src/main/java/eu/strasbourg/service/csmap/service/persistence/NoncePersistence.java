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

import eu.strasbourg.service.csmap.exception.NoSuchNonceException;
import eu.strasbourg.service.csmap.model.Nonce;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the nonce service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NonceUtil
 * @generated
 */
@ProviderType
public interface NoncePersistence extends BasePersistence<Nonce> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NonceUtil} to access the nonce persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the nonces where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching nonces
	 */
	public java.util.List<Nonce> findByUuid(String uuid);

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
	public java.util.List<Nonce> findByUuid(String uuid, int start, int end);

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
	public java.util.List<Nonce> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Nonce>
			orderByComparator);

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
	public java.util.List<Nonce> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Nonce>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first nonce in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching nonce
	 * @throws NoSuchNonceException if a matching nonce could not be found
	 */
	public Nonce findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Nonce>
				orderByComparator)
		throws NoSuchNonceException;

	/**
	 * Returns the first nonce in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching nonce, or <code>null</code> if a matching nonce could not be found
	 */
	public Nonce fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Nonce>
			orderByComparator);

	/**
	 * Returns the last nonce in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching nonce
	 * @throws NoSuchNonceException if a matching nonce could not be found
	 */
	public Nonce findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Nonce>
				orderByComparator)
		throws NoSuchNonceException;

	/**
	 * Returns the last nonce in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching nonce, or <code>null</code> if a matching nonce could not be found
	 */
	public Nonce fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Nonce>
			orderByComparator);

	/**
	 * Returns the nonces before and after the current nonce in the ordered set where uuid = &#63;.
	 *
	 * @param nonceId the primary key of the current nonce
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next nonce
	 * @throws NoSuchNonceException if a nonce with the primary key could not be found
	 */
	public Nonce[] findByUuid_PrevAndNext(
			long nonceId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Nonce>
				orderByComparator)
		throws NoSuchNonceException;

	/**
	 * Removes all the nonces where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of nonces where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching nonces
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the nonce where value = &#63; or throws a <code>NoSuchNonceException</code> if it could not be found.
	 *
	 * @param value the value
	 * @return the matching nonce
	 * @throws NoSuchNonceException if a matching nonce could not be found
	 */
	public Nonce findByValue(String value) throws NoSuchNonceException;

	/**
	 * Returns the nonce where value = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param value the value
	 * @return the matching nonce, or <code>null</code> if a matching nonce could not be found
	 */
	public Nonce fetchByValue(String value);

	/**
	 * Returns the nonce where value = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param value the value
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching nonce, or <code>null</code> if a matching nonce could not be found
	 */
	public Nonce fetchByValue(String value, boolean retrieveFromCache);

	/**
	 * Removes the nonce where value = &#63; from the database.
	 *
	 * @param value the value
	 * @return the nonce that was removed
	 */
	public Nonce removeByValue(String value) throws NoSuchNonceException;

	/**
	 * Returns the number of nonces where value = &#63;.
	 *
	 * @param value the value
	 * @return the number of matching nonces
	 */
	public int countByValue(String value);

	/**
	 * Caches the nonce in the entity cache if it is enabled.
	 *
	 * @param nonce the nonce
	 */
	public void cacheResult(Nonce nonce);

	/**
	 * Caches the nonces in the entity cache if it is enabled.
	 *
	 * @param nonces the nonces
	 */
	public void cacheResult(java.util.List<Nonce> nonces);

	/**
	 * Creates a new nonce with the primary key. Does not add the nonce to the database.
	 *
	 * @param nonceId the primary key for the new nonce
	 * @return the new nonce
	 */
	public Nonce create(long nonceId);

	/**
	 * Removes the nonce with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param nonceId the primary key of the nonce
	 * @return the nonce that was removed
	 * @throws NoSuchNonceException if a nonce with the primary key could not be found
	 */
	public Nonce remove(long nonceId) throws NoSuchNonceException;

	public Nonce updateImpl(Nonce nonce);

	/**
	 * Returns the nonce with the primary key or throws a <code>NoSuchNonceException</code> if it could not be found.
	 *
	 * @param nonceId the primary key of the nonce
	 * @return the nonce
	 * @throws NoSuchNonceException if a nonce with the primary key could not be found
	 */
	public Nonce findByPrimaryKey(long nonceId) throws NoSuchNonceException;

	/**
	 * Returns the nonce with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param nonceId the primary key of the nonce
	 * @return the nonce, or <code>null</code> if a nonce with the primary key could not be found
	 */
	public Nonce fetchByPrimaryKey(long nonceId);

	/**
	 * Returns all the nonces.
	 *
	 * @return the nonces
	 */
	public java.util.List<Nonce> findAll();

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
	public java.util.List<Nonce> findAll(int start, int end);

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
	public java.util.List<Nonce> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Nonce>
			orderByComparator);

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
	public java.util.List<Nonce> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Nonce>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the nonces from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of nonces.
	 *
	 * @return the number of nonces
	 */
	public int countAll();

}