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

import eu.strasbourg.service.csmap.exception.NoSuchCsmapCacheException;
import eu.strasbourg.service.csmap.model.CsmapCache;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the csmap cache service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CsmapCacheUtil
 * @generated
 */
@ProviderType
public interface CsmapCachePersistence extends BasePersistence<CsmapCache> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CsmapCacheUtil} to access the csmap cache persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the csmap cache where codeCache = &#63; or throws a <code>NoSuchCsmapCacheException</code> if it could not be found.
	 *
	 * @param codeCache the code cache
	 * @return the matching csmap cache
	 * @throws NoSuchCsmapCacheException if a matching csmap cache could not be found
	 */
	public CsmapCache findByCodeCache(long codeCache)
		throws NoSuchCsmapCacheException;

	/**
	 * Returns the csmap cache where codeCache = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param codeCache the code cache
	 * @return the matching csmap cache, or <code>null</code> if a matching csmap cache could not be found
	 */
	public CsmapCache fetchByCodeCache(long codeCache);

	/**
	 * Returns the csmap cache where codeCache = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param codeCache the code cache
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching csmap cache, or <code>null</code> if a matching csmap cache could not be found
	 */
	public CsmapCache fetchByCodeCache(long codeCache, boolean useFinderCache);

	/**
	 * Removes the csmap cache where codeCache = &#63; from the database.
	 *
	 * @param codeCache the code cache
	 * @return the csmap cache that was removed
	 */
	public CsmapCache removeByCodeCache(long codeCache)
		throws NoSuchCsmapCacheException;

	/**
	 * Returns the number of csmap caches where codeCache = &#63;.
	 *
	 * @param codeCache the code cache
	 * @return the number of matching csmap caches
	 */
	public int countByCodeCache(long codeCache);

	/**
	 * Returns all the csmap caches where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @return the matching csmap caches
	 */
	public java.util.List<CsmapCache> findByLastProcessNotSuccess(
		Boolean isLastProcessSuccess);

	/**
	 * Returns a range of all the csmap caches where isLastProcessSuccess = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>.
	 * </p>
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @return the range of matching csmap caches
	 */
	public java.util.List<CsmapCache> findByLastProcessNotSuccess(
		Boolean isLastProcessSuccess, int start, int end);

	/**
	 * Returns an ordered range of all the csmap caches where isLastProcessSuccess = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>.
	 * </p>
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap caches
	 */
	public java.util.List<CsmapCache> findByLastProcessNotSuccess(
		Boolean isLastProcessSuccess, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCache>
			orderByComparator);

	/**
	 * Returns an ordered range of all the csmap caches where isLastProcessSuccess = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>.
	 * </p>
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching csmap caches
	 */
	public java.util.List<CsmapCache> findByLastProcessNotSuccess(
		Boolean isLastProcessSuccess, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCache>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first csmap cache in the ordered set where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache
	 * @throws NoSuchCsmapCacheException if a matching csmap cache could not be found
	 */
	public CsmapCache findByLastProcessNotSuccess_First(
			Boolean isLastProcessSuccess,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCache>
				orderByComparator)
		throws NoSuchCsmapCacheException;

	/**
	 * Returns the first csmap cache in the ordered set where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache, or <code>null</code> if a matching csmap cache could not be found
	 */
	public CsmapCache fetchByLastProcessNotSuccess_First(
		Boolean isLastProcessSuccess,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCache>
			orderByComparator);

	/**
	 * Returns the last csmap cache in the ordered set where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache
	 * @throws NoSuchCsmapCacheException if a matching csmap cache could not be found
	 */
	public CsmapCache findByLastProcessNotSuccess_Last(
			Boolean isLastProcessSuccess,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCache>
				orderByComparator)
		throws NoSuchCsmapCacheException;

	/**
	 * Returns the last csmap cache in the ordered set where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache, or <code>null</code> if a matching csmap cache could not be found
	 */
	public CsmapCache fetchByLastProcessNotSuccess_Last(
		Boolean isLastProcessSuccess,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCache>
			orderByComparator);

	/**
	 * Returns the csmap caches before and after the current csmap cache in the ordered set where isLastProcessSuccess = &#63;.
	 *
	 * @param cacheId the primary key of the current csmap cache
	 * @param isLastProcessSuccess the is last process success
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache
	 * @throws NoSuchCsmapCacheException if a csmap cache with the primary key could not be found
	 */
	public CsmapCache[] findByLastProcessNotSuccess_PrevAndNext(
			long cacheId, Boolean isLastProcessSuccess,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCache>
				orderByComparator)
		throws NoSuchCsmapCacheException;

	/**
	 * Removes all the csmap caches where isLastProcessSuccess = &#63; from the database.
	 *
	 * @param isLastProcessSuccess the is last process success
	 */
	public void removeByLastProcessNotSuccess(Boolean isLastProcessSuccess);

	/**
	 * Returns the number of csmap caches where isLastProcessSuccess = &#63;.
	 *
	 * @param isLastProcessSuccess the is last process success
	 * @return the number of matching csmap caches
	 */
	public int countByLastProcessNotSuccess(Boolean isLastProcessSuccess);

	/**
	 * Caches the csmap cache in the entity cache if it is enabled.
	 *
	 * @param csmapCache the csmap cache
	 */
	public void cacheResult(CsmapCache csmapCache);

	/**
	 * Caches the csmap caches in the entity cache if it is enabled.
	 *
	 * @param csmapCaches the csmap caches
	 */
	public void cacheResult(java.util.List<CsmapCache> csmapCaches);

	/**
	 * Creates a new csmap cache with the primary key. Does not add the csmap cache to the database.
	 *
	 * @param cacheId the primary key for the new csmap cache
	 * @return the new csmap cache
	 */
	public CsmapCache create(long cacheId);

	/**
	 * Removes the csmap cache with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheId the primary key of the csmap cache
	 * @return the csmap cache that was removed
	 * @throws NoSuchCsmapCacheException if a csmap cache with the primary key could not be found
	 */
	public CsmapCache remove(long cacheId) throws NoSuchCsmapCacheException;

	public CsmapCache updateImpl(CsmapCache csmapCache);

	/**
	 * Returns the csmap cache with the primary key or throws a <code>NoSuchCsmapCacheException</code> if it could not be found.
	 *
	 * @param cacheId the primary key of the csmap cache
	 * @return the csmap cache
	 * @throws NoSuchCsmapCacheException if a csmap cache with the primary key could not be found
	 */
	public CsmapCache findByPrimaryKey(long cacheId)
		throws NoSuchCsmapCacheException;

	/**
	 * Returns the csmap cache with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cacheId the primary key of the csmap cache
	 * @return the csmap cache, or <code>null</code> if a csmap cache with the primary key could not be found
	 */
	public CsmapCache fetchByPrimaryKey(long cacheId);

	/**
	 * Returns all the csmap caches.
	 *
	 * @return the csmap caches
	 */
	public java.util.List<CsmapCache> findAll();

	/**
	 * Returns a range of all the csmap caches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @return the range of csmap caches
	 */
	public java.util.List<CsmapCache> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the csmap caches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of csmap caches
	 */
	public java.util.List<CsmapCache> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCache>
			orderByComparator);

	/**
	 * Returns an ordered range of all the csmap caches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CsmapCacheModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of csmap caches
	 */
	public java.util.List<CsmapCache> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCache>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the csmap caches from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of csmap caches.
	 *
	 * @return the number of csmap caches
	 */
	public int countAll();

}