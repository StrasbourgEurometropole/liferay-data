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

import eu.strasbourg.service.csmap.exception.NoSuchCacheAgendaJsonException;
import eu.strasbourg.service.csmap.model.CacheAgendaJson;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the cache agenda json service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CacheAgendaJsonUtil
 * @generated
 */
@ProviderType
public interface CacheAgendaJsonPersistence
	extends BasePersistence<CacheAgendaJson> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CacheAgendaJsonUtil} to access the cache agenda json persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Caches the cache agenda json in the entity cache if it is enabled.
	 *
	 * @param cacheAgendaJson the cache agenda json
	 */
	public void cacheResult(CacheAgendaJson cacheAgendaJson);

	/**
	 * Caches the cache agenda jsons in the entity cache if it is enabled.
	 *
	 * @param cacheAgendaJsons the cache agenda jsons
	 */
	public void cacheResult(java.util.List<CacheAgendaJson> cacheAgendaJsons);

	/**
	 * Creates a new cache agenda json with the primary key. Does not add the cache agenda json to the database.
	 *
	 * @param cacheId the primary key for the new cache agenda json
	 * @return the new cache agenda json
	 */
	public CacheAgendaJson create(long cacheId);

	/**
	 * Removes the cache agenda json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheId the primary key of the cache agenda json
	 * @return the cache agenda json that was removed
	 * @throws NoSuchCacheAgendaJsonException if a cache agenda json with the primary key could not be found
	 */
	public CacheAgendaJson remove(long cacheId)
		throws NoSuchCacheAgendaJsonException;

	public CacheAgendaJson updateImpl(CacheAgendaJson cacheAgendaJson);

	/**
	 * Returns the cache agenda json with the primary key or throws a <code>NoSuchCacheAgendaJsonException</code> if it could not be found.
	 *
	 * @param cacheId the primary key of the cache agenda json
	 * @return the cache agenda json
	 * @throws NoSuchCacheAgendaJsonException if a cache agenda json with the primary key could not be found
	 */
	public CacheAgendaJson findByPrimaryKey(long cacheId)
		throws NoSuchCacheAgendaJsonException;

	/**
	 * Returns the cache agenda json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cacheId the primary key of the cache agenda json
	 * @return the cache agenda json, or <code>null</code> if a cache agenda json with the primary key could not be found
	 */
	public CacheAgendaJson fetchByPrimaryKey(long cacheId);

	/**
	 * Returns all the cache agenda jsons.
	 *
	 * @return the cache agenda jsons
	 */
	public java.util.List<CacheAgendaJson> findAll();

	/**
	 * Returns a range of all the cache agenda jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheAgendaJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache agenda jsons
	 * @param end the upper bound of the range of cache agenda jsons (not inclusive)
	 * @return the range of cache agenda jsons
	 */
	public java.util.List<CacheAgendaJson> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the cache agenda jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheAgendaJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache agenda jsons
	 * @param end the upper bound of the range of cache agenda jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cache agenda jsons
	 */
	public java.util.List<CacheAgendaJson> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CacheAgendaJson>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cache agenda jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheAgendaJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache agenda jsons
	 * @param end the upper bound of the range of cache agenda jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cache agenda jsons
	 */
	public java.util.List<CacheAgendaJson> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CacheAgendaJson>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the cache agenda jsons from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of cache agenda jsons.
	 *
	 * @return the number of cache agenda jsons
	 */
	public int countAll();

}