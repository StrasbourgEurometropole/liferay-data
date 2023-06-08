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

import eu.strasbourg.service.csmap.exception.NoSuchBaseNonceException;
import eu.strasbourg.service.csmap.model.BaseNonce;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the base nonce service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BaseNonceUtil
 * @generated
 */
@ProviderType
public interface BaseNoncePersistence extends BasePersistence<BaseNonce> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BaseNonceUtil} to access the base nonce persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the base nonce where value = &#63; or throws a <code>NoSuchBaseNonceException</code> if it could not be found.
	 *
	 * @param value the value
	 * @return the matching base nonce
	 * @throws NoSuchBaseNonceException if a matching base nonce could not be found
	 */
	public BaseNonce findByValue(String value) throws NoSuchBaseNonceException;

	/**
	 * Returns the base nonce where value = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param value the value
	 * @return the matching base nonce, or <code>null</code> if a matching base nonce could not be found
	 */
	public BaseNonce fetchByValue(String value);

	/**
	 * Returns the base nonce where value = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param value the value
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching base nonce, or <code>null</code> if a matching base nonce could not be found
	 */
	public BaseNonce fetchByValue(String value, boolean useFinderCache);

	/**
	 * Removes the base nonce where value = &#63; from the database.
	 *
	 * @param value the value
	 * @return the base nonce that was removed
	 */
	public BaseNonce removeByValue(String value)
		throws NoSuchBaseNonceException;

	/**
	 * Returns the number of base nonces where value = &#63;.
	 *
	 * @param value the value
	 * @return the number of matching base nonces
	 */
	public int countByValue(String value);

	/**
	 * Caches the base nonce in the entity cache if it is enabled.
	 *
	 * @param baseNonce the base nonce
	 */
	public void cacheResult(BaseNonce baseNonce);

	/**
	 * Caches the base nonces in the entity cache if it is enabled.
	 *
	 * @param baseNonces the base nonces
	 */
	public void cacheResult(java.util.List<BaseNonce> baseNonces);

	/**
	 * Creates a new base nonce with the primary key. Does not add the base nonce to the database.
	 *
	 * @param baseNonceId the primary key for the new base nonce
	 * @return the new base nonce
	 */
	public BaseNonce create(long baseNonceId);

	/**
	 * Removes the base nonce with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param baseNonceId the primary key of the base nonce
	 * @return the base nonce that was removed
	 * @throws NoSuchBaseNonceException if a base nonce with the primary key could not be found
	 */
	public BaseNonce remove(long baseNonceId) throws NoSuchBaseNonceException;

	public BaseNonce updateImpl(BaseNonce baseNonce);

	/**
	 * Returns the base nonce with the primary key or throws a <code>NoSuchBaseNonceException</code> if it could not be found.
	 *
	 * @param baseNonceId the primary key of the base nonce
	 * @return the base nonce
	 * @throws NoSuchBaseNonceException if a base nonce with the primary key could not be found
	 */
	public BaseNonce findByPrimaryKey(long baseNonceId)
		throws NoSuchBaseNonceException;

	/**
	 * Returns the base nonce with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param baseNonceId the primary key of the base nonce
	 * @return the base nonce, or <code>null</code> if a base nonce with the primary key could not be found
	 */
	public BaseNonce fetchByPrimaryKey(long baseNonceId);

	/**
	 * Returns all the base nonces.
	 *
	 * @return the base nonces
	 */
	public java.util.List<BaseNonce> findAll();

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
	public java.util.List<BaseNonce> findAll(int start, int end);

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
	public java.util.List<BaseNonce> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BaseNonce>
			orderByComparator);

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
	public java.util.List<BaseNonce> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BaseNonce>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the base nonces from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of base nonces.
	 *
	 * @return the number of base nonces
	 */
	public int countAll();

}