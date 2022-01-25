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

import eu.strasbourg.service.gtfs.exception.NoSuchCacheHoursJSONException;
import eu.strasbourg.service.gtfs.model.CacheHoursJSON;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the cache hours json service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see CacheHoursJSONUtil
 * @generated
 */
@ProviderType
public interface CacheHoursJSONPersistence
	extends BasePersistence<CacheHoursJSON> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CacheHoursJSONUtil} to access the cache hours json persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, CacheHoursJSON> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the cache hours jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching cache hours jsons
	 */
	public java.util.List<CacheHoursJSON> findByUuid(String uuid);

	/**
	 * Returns a range of all the cache hours jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @return the range of matching cache hours jsons
	 */
	public java.util.List<CacheHoursJSON> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the cache hours jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache hours jsons
	 */
	public java.util.List<CacheHoursJSON> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cache hours jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache hours jsons
	 */
	public java.util.List<CacheHoursJSON> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first cache hours json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache hours json
	 * @throws NoSuchCacheHoursJSONException if a matching cache hours json could not be found
	 */
	public CacheHoursJSON findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
				orderByComparator)
		throws NoSuchCacheHoursJSONException;

	/**
	 * Returns the first cache hours json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache hours json, or <code>null</code> if a matching cache hours json could not be found
	 */
	public CacheHoursJSON fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
			orderByComparator);

	/**
	 * Returns the last cache hours json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache hours json
	 * @throws NoSuchCacheHoursJSONException if a matching cache hours json could not be found
	 */
	public CacheHoursJSON findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
				orderByComparator)
		throws NoSuchCacheHoursJSONException;

	/**
	 * Returns the last cache hours json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache hours json, or <code>null</code> if a matching cache hours json could not be found
	 */
	public CacheHoursJSON fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
			orderByComparator);

	/**
	 * Returns the cache hours jsons before and after the current cache hours json in the ordered set where uuid = &#63;.
	 *
	 * @param cacheHoursJSONPK the primary key of the current cache hours json
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache hours json
	 * @throws NoSuchCacheHoursJSONException if a cache hours json with the primary key could not be found
	 */
	public CacheHoursJSON[] findByUuid_PrevAndNext(
			eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK
				cacheHoursJSONPK,
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
				orderByComparator)
		throws NoSuchCacheHoursJSONException;

	/**
	 * Removes all the cache hours jsons where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of cache hours jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching cache hours jsons
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the cache hours jsons where stopCode = &#63;.
	 *
	 * @param stopCode the stop code
	 * @return the matching cache hours jsons
	 */
	public java.util.List<CacheHoursJSON> findByStopCode(String stopCode);

	/**
	 * Returns a range of all the cache hours jsons where stopCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stopCode the stop code
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @return the range of matching cache hours jsons
	 */
	public java.util.List<CacheHoursJSON> findByStopCode(
		String stopCode, int start, int end);

	/**
	 * Returns an ordered range of all the cache hours jsons where stopCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stopCode the stop code
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache hours jsons
	 */
	public java.util.List<CacheHoursJSON> findByStopCode(
		String stopCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cache hours jsons where stopCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param stopCode the stop code
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache hours jsons
	 */
	public java.util.List<CacheHoursJSON> findByStopCode(
		String stopCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first cache hours json in the ordered set where stopCode = &#63;.
	 *
	 * @param stopCode the stop code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache hours json
	 * @throws NoSuchCacheHoursJSONException if a matching cache hours json could not be found
	 */
	public CacheHoursJSON findByStopCode_First(
			String stopCode,
			com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
				orderByComparator)
		throws NoSuchCacheHoursJSONException;

	/**
	 * Returns the first cache hours json in the ordered set where stopCode = &#63;.
	 *
	 * @param stopCode the stop code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache hours json, or <code>null</code> if a matching cache hours json could not be found
	 */
	public CacheHoursJSON fetchByStopCode_First(
		String stopCode,
		com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
			orderByComparator);

	/**
	 * Returns the last cache hours json in the ordered set where stopCode = &#63;.
	 *
	 * @param stopCode the stop code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache hours json
	 * @throws NoSuchCacheHoursJSONException if a matching cache hours json could not be found
	 */
	public CacheHoursJSON findByStopCode_Last(
			String stopCode,
			com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
				orderByComparator)
		throws NoSuchCacheHoursJSONException;

	/**
	 * Returns the last cache hours json in the ordered set where stopCode = &#63;.
	 *
	 * @param stopCode the stop code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache hours json, or <code>null</code> if a matching cache hours json could not be found
	 */
	public CacheHoursJSON fetchByStopCode_Last(
		String stopCode,
		com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
			orderByComparator);

	/**
	 * Returns the cache hours jsons before and after the current cache hours json in the ordered set where stopCode = &#63;.
	 *
	 * @param cacheHoursJSONPK the primary key of the current cache hours json
	 * @param stopCode the stop code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache hours json
	 * @throws NoSuchCacheHoursJSONException if a cache hours json with the primary key could not be found
	 */
	public CacheHoursJSON[] findByStopCode_PrevAndNext(
			eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK
				cacheHoursJSONPK,
			String stopCode,
			com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
				orderByComparator)
		throws NoSuchCacheHoursJSONException;

	/**
	 * Removes all the cache hours jsons where stopCode = &#63; from the database.
	 *
	 * @param stopCode the stop code
	 */
	public void removeByStopCode(String stopCode);

	/**
	 * Returns the number of cache hours jsons where stopCode = &#63;.
	 *
	 * @param stopCode the stop code
	 * @return the number of matching cache hours jsons
	 */
	public int countByStopCode(String stopCode);

	/**
	 * Returns all the cache hours jsons where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching cache hours jsons
	 */
	public java.util.List<CacheHoursJSON> findByType(int type);

	/**
	 * Returns a range of all the cache hours jsons where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @return the range of matching cache hours jsons
	 */
	public java.util.List<CacheHoursJSON> findByType(
		int type, int start, int end);

	/**
	 * Returns an ordered range of all the cache hours jsons where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching cache hours jsons
	 */
	public java.util.List<CacheHoursJSON> findByType(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cache hours jsons where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching cache hours jsons
	 */
	public java.util.List<CacheHoursJSON> findByType(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first cache hours json in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache hours json
	 * @throws NoSuchCacheHoursJSONException if a matching cache hours json could not be found
	 */
	public CacheHoursJSON findByType_First(
			int type,
			com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
				orderByComparator)
		throws NoSuchCacheHoursJSONException;

	/**
	 * Returns the first cache hours json in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching cache hours json, or <code>null</code> if a matching cache hours json could not be found
	 */
	public CacheHoursJSON fetchByType_First(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
			orderByComparator);

	/**
	 * Returns the last cache hours json in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache hours json
	 * @throws NoSuchCacheHoursJSONException if a matching cache hours json could not be found
	 */
	public CacheHoursJSON findByType_Last(
			int type,
			com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
				orderByComparator)
		throws NoSuchCacheHoursJSONException;

	/**
	 * Returns the last cache hours json in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching cache hours json, or <code>null</code> if a matching cache hours json could not be found
	 */
	public CacheHoursJSON fetchByType_Last(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
			orderByComparator);

	/**
	 * Returns the cache hours jsons before and after the current cache hours json in the ordered set where type = &#63;.
	 *
	 * @param cacheHoursJSONPK the primary key of the current cache hours json
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next cache hours json
	 * @throws NoSuchCacheHoursJSONException if a cache hours json with the primary key could not be found
	 */
	public CacheHoursJSON[] findByType_PrevAndNext(
			eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK
				cacheHoursJSONPK,
			int type,
			com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
				orderByComparator)
		throws NoSuchCacheHoursJSONException;

	/**
	 * Removes all the cache hours jsons where type = &#63; from the database.
	 *
	 * @param type the type
	 */
	public void removeByType(int type);

	/**
	 * Returns the number of cache hours jsons where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching cache hours jsons
	 */
	public int countByType(int type);

	/**
	 * Returns the cache hours json where stopCode = &#63; and type = &#63; or throws a <code>NoSuchCacheHoursJSONException</code> if it could not be found.
	 *
	 * @param stopCode the stop code
	 * @param type the type
	 * @return the matching cache hours json
	 * @throws NoSuchCacheHoursJSONException if a matching cache hours json could not be found
	 */
	public CacheHoursJSON findByStopCodeAndType(String stopCode, int type)
		throws NoSuchCacheHoursJSONException;

	/**
	 * Returns the cache hours json where stopCode = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param stopCode the stop code
	 * @param type the type
	 * @return the matching cache hours json, or <code>null</code> if a matching cache hours json could not be found
	 */
	public CacheHoursJSON fetchByStopCodeAndType(String stopCode, int type);

	/**
	 * Returns the cache hours json where stopCode = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param stopCode the stop code
	 * @param type the type
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching cache hours json, or <code>null</code> if a matching cache hours json could not be found
	 */
	public CacheHoursJSON fetchByStopCodeAndType(
		String stopCode, int type, boolean retrieveFromCache);

	/**
	 * Removes the cache hours json where stopCode = &#63; and type = &#63; from the database.
	 *
	 * @param stopCode the stop code
	 * @param type the type
	 * @return the cache hours json that was removed
	 */
	public CacheHoursJSON removeByStopCodeAndType(String stopCode, int type)
		throws NoSuchCacheHoursJSONException;

	/**
	 * Returns the number of cache hours jsons where stopCode = &#63; and type = &#63;.
	 *
	 * @param stopCode the stop code
	 * @param type the type
	 * @return the number of matching cache hours jsons
	 */
	public int countByStopCodeAndType(String stopCode, int type);

	/**
	 * Caches the cache hours json in the entity cache if it is enabled.
	 *
	 * @param cacheHoursJSON the cache hours json
	 */
	public void cacheResult(CacheHoursJSON cacheHoursJSON);

	/**
	 * Caches the cache hours jsons in the entity cache if it is enabled.
	 *
	 * @param cacheHoursJSONs the cache hours jsons
	 */
	public void cacheResult(java.util.List<CacheHoursJSON> cacheHoursJSONs);

	/**
	 * Creates a new cache hours json with the primary key. Does not add the cache hours json to the database.
	 *
	 * @param cacheHoursJSONPK the primary key for the new cache hours json
	 * @return the new cache hours json
	 */
	public CacheHoursJSON create(
		eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK
			cacheHoursJSONPK);

	/**
	 * Removes the cache hours json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheHoursJSONPK the primary key of the cache hours json
	 * @return the cache hours json that was removed
	 * @throws NoSuchCacheHoursJSONException if a cache hours json with the primary key could not be found
	 */
	public CacheHoursJSON remove(
			eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK
				cacheHoursJSONPK)
		throws NoSuchCacheHoursJSONException;

	public CacheHoursJSON updateImpl(CacheHoursJSON cacheHoursJSON);

	/**
	 * Returns the cache hours json with the primary key or throws a <code>NoSuchCacheHoursJSONException</code> if it could not be found.
	 *
	 * @param cacheHoursJSONPK the primary key of the cache hours json
	 * @return the cache hours json
	 * @throws NoSuchCacheHoursJSONException if a cache hours json with the primary key could not be found
	 */
	public CacheHoursJSON findByPrimaryKey(
			eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK
				cacheHoursJSONPK)
		throws NoSuchCacheHoursJSONException;

	/**
	 * Returns the cache hours json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cacheHoursJSONPK the primary key of the cache hours json
	 * @return the cache hours json, or <code>null</code> if a cache hours json with the primary key could not be found
	 */
	public CacheHoursJSON fetchByPrimaryKey(
		eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK
			cacheHoursJSONPK);

	/**
	 * Returns all the cache hours jsons.
	 *
	 * @return the cache hours jsons
	 */
	public java.util.List<CacheHoursJSON> findAll();

	/**
	 * Returns a range of all the cache hours jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @return the range of cache hours jsons
	 */
	public java.util.List<CacheHoursJSON> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the cache hours jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cache hours jsons
	 */
	public java.util.List<CacheHoursJSON> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
			orderByComparator);

	/**
	 * Returns an ordered range of all the cache hours jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CacheHoursJSONModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cache hours jsons
	 */
	public java.util.List<CacheHoursJSON> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CacheHoursJSON>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the cache hours jsons from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of cache hours jsons.
	 *
	 * @return the number of cache hours jsons
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

	public Set<String> getCompoundPKColumnNames();

}