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

package eu.strasbourg.service.gtfs.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.gtfs.exception.NoSuchCacheAlertJSONException;
import eu.strasbourg.service.gtfs.model.CacheAlertJSON;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the cache alert json service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see CacheAlertJSONUtil
 * @generated
 */
@ProviderType
public interface CacheAlertJSONPersistence
	extends BasePersistence<CacheAlertJSON> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CacheAlertJSONUtil} to access the cache alert json persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, CacheAlertJSON> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the cache alert jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cache alert jsons
	 */
	public java.util.List<CacheAlertJSON> findByUuid(String uuid);

	/**
	 * Returns a range of all the cache alert jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @return the range of matching cache alert jsons
	 */
	public java.util.List<CacheAlertJSON> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the cache alert jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache alert jsons
	 */
	public java.util.List<CacheAlertJSON> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CacheAlertJSON>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cache alert jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cache alert jsons
	 */
	public java.util.List<CacheAlertJSON> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CacheAlertJSON>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first cache alert json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache alert json
	 * @throws NoSuchCacheAlertJSONException if a matching cache alert json could not be found
	 */
	public CacheAlertJSON findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CacheAlertJSON>
				orderByComparator)
		throws NoSuchCacheAlertJSONException;

	/**
	 * Returns the first cache alert json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache alert json, or <code>null</code> if a matching cache alert json could not be found
	 */
	public CacheAlertJSON fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CacheAlertJSON>
			orderByComparator);

	/**
	 * Returns the last cache alert json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache alert json
	 * @throws NoSuchCacheAlertJSONException if a matching cache alert json could not be found
	 */
	public CacheAlertJSON findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CacheAlertJSON>
				orderByComparator)
		throws NoSuchCacheAlertJSONException;

	/**
	 * Returns the last cache alert json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache alert json, or <code>null</code> if a matching cache alert json could not be found
	 */
	public CacheAlertJSON fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CacheAlertJSON>
			orderByComparator);

	/**
	 * Returns the cache alert jsons before and after the current cache alert json in the ordered set where uuid = &#63;.
	 *
	 * @param cacheId the primary key of the current cache alert json
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache alert json
	 * @throws NoSuchCacheAlertJSONException if a cache alert json with the primary key could not be found
	 */
	public CacheAlertJSON[] findByUuid_PrevAndNext(
			long cacheId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CacheAlertJSON>
				orderByComparator)
		throws NoSuchCacheAlertJSONException;

	/**
	 * Removes all the cache alert jsons where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of cache alert jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cache alert jsons
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the cache alert jsons where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @return the matching cache alert jsons
	 */
	public java.util.List<CacheAlertJSON> findBycacheId(long cacheId);

	/**
	 * Returns a range of all the cache alert jsons where cacheId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param cacheId the cache ID
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @return the range of matching cache alert jsons
	 */
	public java.util.List<CacheAlertJSON> findBycacheId(
		long cacheId, int start, int end);

	/**
	 * Returns an ordered range of all the cache alert jsons where cacheId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param cacheId the cache ID
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache alert jsons
	 */
	public java.util.List<CacheAlertJSON> findBycacheId(
		long cacheId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CacheAlertJSON>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cache alert jsons where cacheId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param cacheId the cache ID
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching cache alert jsons
	 */
	public java.util.List<CacheAlertJSON> findBycacheId(
		long cacheId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CacheAlertJSON>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first cache alert json in the ordered set where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache alert json
	 * @throws NoSuchCacheAlertJSONException if a matching cache alert json could not be found
	 */
	public CacheAlertJSON findBycacheId_First(
			long cacheId,
			com.liferay.portal.kernel.util.OrderByComparator<CacheAlertJSON>
				orderByComparator)
		throws NoSuchCacheAlertJSONException;

	/**
	 * Returns the first cache alert json in the ordered set where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache alert json, or <code>null</code> if a matching cache alert json could not be found
	 */
	public CacheAlertJSON fetchBycacheId_First(
		long cacheId,
		com.liferay.portal.kernel.util.OrderByComparator<CacheAlertJSON>
			orderByComparator);

	/**
	 * Returns the last cache alert json in the ordered set where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache alert json
	 * @throws NoSuchCacheAlertJSONException if a matching cache alert json could not be found
	 */
	public CacheAlertJSON findBycacheId_Last(
			long cacheId,
			com.liferay.portal.kernel.util.OrderByComparator<CacheAlertJSON>
				orderByComparator)
		throws NoSuchCacheAlertJSONException;

	/**
	 * Returns the last cache alert json in the ordered set where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache alert json, or <code>null</code> if a matching cache alert json could not be found
	 */
	public CacheAlertJSON fetchBycacheId_Last(
		long cacheId,
		com.liferay.portal.kernel.util.OrderByComparator<CacheAlertJSON>
			orderByComparator);

	/**
	 * Removes all the cache alert jsons where cacheId = &#63; from the database.
	 *
	 * @param cacheId the cache ID
	 */
	public void removeBycacheId(long cacheId);

	/**
	 * Returns the number of cache alert jsons where cacheId = &#63;.
	 *
	 * @param cacheId the cache ID
	 * @return the number of matching cache alert jsons
	 */
	public int countBycacheId(long cacheId);

	/**
	 * Caches the cache alert json in the entity cache if it is enabled.
	 *
	 * @param cacheAlertJSON the cache alert json
	 */
	public void cacheResult(CacheAlertJSON cacheAlertJSON);

	/**
	 * Caches the cache alert jsons in the entity cache if it is enabled.
	 *
	 * @param cacheAlertJSONs the cache alert jsons
	 */
	public void cacheResult(java.util.List<CacheAlertJSON> cacheAlertJSONs);

	/**
	 * Creates a new cache alert json with the primary key. Does not add the cache alert json to the database.
	 *
	 * @param cacheId the primary key for the new cache alert json
	 * @return the new cache alert json
	 */
	public CacheAlertJSON create(long cacheId);

	/**
	 * Removes the cache alert json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheId the primary key of the cache alert json
	 * @return the cache alert json that was removed
	 * @throws NoSuchCacheAlertJSONException if a cache alert json with the primary key could not be found
	 */
	public CacheAlertJSON remove(long cacheId)
		throws NoSuchCacheAlertJSONException;

	public CacheAlertJSON updateImpl(CacheAlertJSON cacheAlertJSON);

	/**
	 * Returns the cache alert json with the primary key or throws a <code>NoSuchCacheAlertJSONException</code> if it could not be found.
	 *
	 * @param cacheId the primary key of the cache alert json
	 * @return the cache alert json
	 * @throws NoSuchCacheAlertJSONException if a cache alert json with the primary key could not be found
	 */
	public CacheAlertJSON findByPrimaryKey(long cacheId)
		throws NoSuchCacheAlertJSONException;

	/**
	 * Returns the cache alert json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cacheId the primary key of the cache alert json
	 * @return the cache alert json, or <code>null</code> if a cache alert json with the primary key could not be found
	 */
	public CacheAlertJSON fetchByPrimaryKey(long cacheId);

	/**
	 * Returns all the cache alert jsons.
	 *
	 * @return the cache alert jsons
	 */
	public java.util.List<CacheAlertJSON> findAll();

	/**
	 * Returns a range of all the cache alert jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @return the range of cache alert jsons
	 */
	public java.util.List<CacheAlertJSON> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the cache alert jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cache alert jsons
	 */
	public java.util.List<CacheAlertJSON> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CacheAlertJSON>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cache alert jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CacheAlertJSONModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache alert jsons
	 * @param end the upper bound of the range of cache alert jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cache alert jsons
	 */
	public java.util.List<CacheAlertJSON> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CacheAlertJSON>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the cache alert jsons from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of cache alert jsons.
	 *
	 * @return the number of cache alert jsons
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}