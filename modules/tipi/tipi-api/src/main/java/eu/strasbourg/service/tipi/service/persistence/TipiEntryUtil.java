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

package eu.strasbourg.service.tipi.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.tipi.model.TipiEntry;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the tipi entry service. This utility wraps {@link eu.strasbourg.service.tipi.service.persistence.impl.TipiEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see TipiEntryPersistence
 * @see eu.strasbourg.service.tipi.service.persistence.impl.TipiEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class TipiEntryUtil {
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
	public static void clearCache(TipiEntry tipiEntry) {
		getPersistence().clearCache(tipiEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TipiEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TipiEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TipiEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TipiEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TipiEntry update(TipiEntry tipiEntry) {
		return getPersistence().update(tipiEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TipiEntry update(TipiEntry tipiEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(tipiEntry, serviceContext);
	}

	/**
	* Returns all the tipi entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching tipi entries
	*/
	public static List<TipiEntry> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the tipi entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TipiEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of tipi entries
	* @param end the upper bound of the range of tipi entries (not inclusive)
	* @return the range of matching tipi entries
	*/
	public static List<TipiEntry> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the tipi entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TipiEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of tipi entries
	* @param end the upper bound of the range of tipi entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tipi entries
	*/
	public static List<TipiEntry> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<TipiEntry> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the tipi entries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TipiEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of tipi entries
	* @param end the upper bound of the range of tipi entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tipi entries
	*/
	public static List<TipiEntry> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<TipiEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first tipi entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tipi entry
	* @throws NoSuchTipiEntryException if a matching tipi entry could not be found
	*/
	public static TipiEntry findByUuid_First(java.lang.String uuid,
		OrderByComparator<TipiEntry> orderByComparator)
		throws eu.strasbourg.service.tipi.exception.NoSuchTipiEntryException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first tipi entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tipi entry, or <code>null</code> if a matching tipi entry could not be found
	*/
	public static TipiEntry fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<TipiEntry> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last tipi entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tipi entry
	* @throws NoSuchTipiEntryException if a matching tipi entry could not be found
	*/
	public static TipiEntry findByUuid_Last(java.lang.String uuid,
		OrderByComparator<TipiEntry> orderByComparator)
		throws eu.strasbourg.service.tipi.exception.NoSuchTipiEntryException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last tipi entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tipi entry, or <code>null</code> if a matching tipi entry could not be found
	*/
	public static TipiEntry fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<TipiEntry> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the tipi entries before and after the current tipi entry in the ordered set where uuid = &#63;.
	*
	* @param id the primary key of the current tipi entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tipi entry
	* @throws NoSuchTipiEntryException if a tipi entry with the primary key could not be found
	*/
	public static TipiEntry[] findByUuid_PrevAndNext(long id,
		java.lang.String uuid, OrderByComparator<TipiEntry> orderByComparator)
		throws eu.strasbourg.service.tipi.exception.NoSuchTipiEntryException {
		return getPersistence()
				   .findByUuid_PrevAndNext(id, uuid, orderByComparator);
	}

	/**
	* Removes all the tipi entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of tipi entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching tipi entries
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the tipi entries where date = &#63;.
	*
	* @param date the date
	* @return the matching tipi entries
	*/
	public static List<TipiEntry> findByDate(Date date) {
		return getPersistence().findByDate(date);
	}

	/**
	* Returns a range of all the tipi entries where date = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TipiEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param date the date
	* @param start the lower bound of the range of tipi entries
	* @param end the upper bound of the range of tipi entries (not inclusive)
	* @return the range of matching tipi entries
	*/
	public static List<TipiEntry> findByDate(Date date, int start, int end) {
		return getPersistence().findByDate(date, start, end);
	}

	/**
	* Returns an ordered range of all the tipi entries where date = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TipiEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param date the date
	* @param start the lower bound of the range of tipi entries
	* @param end the upper bound of the range of tipi entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching tipi entries
	*/
	public static List<TipiEntry> findByDate(Date date, int start, int end,
		OrderByComparator<TipiEntry> orderByComparator) {
		return getPersistence().findByDate(date, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the tipi entries where date = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TipiEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param date the date
	* @param start the lower bound of the range of tipi entries
	* @param end the upper bound of the range of tipi entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching tipi entries
	*/
	public static List<TipiEntry> findByDate(Date date, int start, int end,
		OrderByComparator<TipiEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDate(date, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first tipi entry in the ordered set where date = &#63;.
	*
	* @param date the date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tipi entry
	* @throws NoSuchTipiEntryException if a matching tipi entry could not be found
	*/
	public static TipiEntry findByDate_First(Date date,
		OrderByComparator<TipiEntry> orderByComparator)
		throws eu.strasbourg.service.tipi.exception.NoSuchTipiEntryException {
		return getPersistence().findByDate_First(date, orderByComparator);
	}

	/**
	* Returns the first tipi entry in the ordered set where date = &#63;.
	*
	* @param date the date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tipi entry, or <code>null</code> if a matching tipi entry could not be found
	*/
	public static TipiEntry fetchByDate_First(Date date,
		OrderByComparator<TipiEntry> orderByComparator) {
		return getPersistence().fetchByDate_First(date, orderByComparator);
	}

	/**
	* Returns the last tipi entry in the ordered set where date = &#63;.
	*
	* @param date the date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tipi entry
	* @throws NoSuchTipiEntryException if a matching tipi entry could not be found
	*/
	public static TipiEntry findByDate_Last(Date date,
		OrderByComparator<TipiEntry> orderByComparator)
		throws eu.strasbourg.service.tipi.exception.NoSuchTipiEntryException {
		return getPersistence().findByDate_Last(date, orderByComparator);
	}

	/**
	* Returns the last tipi entry in the ordered set where date = &#63;.
	*
	* @param date the date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tipi entry, or <code>null</code> if a matching tipi entry could not be found
	*/
	public static TipiEntry fetchByDate_Last(Date date,
		OrderByComparator<TipiEntry> orderByComparator) {
		return getPersistence().fetchByDate_Last(date, orderByComparator);
	}

	/**
	* Returns the tipi entries before and after the current tipi entry in the ordered set where date = &#63;.
	*
	* @param id the primary key of the current tipi entry
	* @param date the date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tipi entry
	* @throws NoSuchTipiEntryException if a tipi entry with the primary key could not be found
	*/
	public static TipiEntry[] findByDate_PrevAndNext(long id, Date date,
		OrderByComparator<TipiEntry> orderByComparator)
		throws eu.strasbourg.service.tipi.exception.NoSuchTipiEntryException {
		return getPersistence()
				   .findByDate_PrevAndNext(id, date, orderByComparator);
	}

	/**
	* Removes all the tipi entries where date = &#63; from the database.
	*
	* @param date the date
	*/
	public static void removeByDate(Date date) {
		getPersistence().removeByDate(date);
	}

	/**
	* Returns the number of tipi entries where date = &#63;.
	*
	* @param date the date
	* @return the number of matching tipi entries
	*/
	public static int countByDate(Date date) {
		return getPersistence().countByDate(date);
	}

	/**
	* Caches the tipi entry in the entity cache if it is enabled.
	*
	* @param tipiEntry the tipi entry
	*/
	public static void cacheResult(TipiEntry tipiEntry) {
		getPersistence().cacheResult(tipiEntry);
	}

	/**
	* Caches the tipi entries in the entity cache if it is enabled.
	*
	* @param tipiEntries the tipi entries
	*/
	public static void cacheResult(List<TipiEntry> tipiEntries) {
		getPersistence().cacheResult(tipiEntries);
	}

	/**
	* Creates a new tipi entry with the primary key. Does not add the tipi entry to the database.
	*
	* @param id the primary key for the new tipi entry
	* @return the new tipi entry
	*/
	public static TipiEntry create(long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the tipi entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the tipi entry
	* @return the tipi entry that was removed
	* @throws NoSuchTipiEntryException if a tipi entry with the primary key could not be found
	*/
	public static TipiEntry remove(long id)
		throws eu.strasbourg.service.tipi.exception.NoSuchTipiEntryException {
		return getPersistence().remove(id);
	}

	public static TipiEntry updateImpl(TipiEntry tipiEntry) {
		return getPersistence().updateImpl(tipiEntry);
	}

	/**
	* Returns the tipi entry with the primary key or throws a {@link NoSuchTipiEntryException} if it could not be found.
	*
	* @param id the primary key of the tipi entry
	* @return the tipi entry
	* @throws NoSuchTipiEntryException if a tipi entry with the primary key could not be found
	*/
	public static TipiEntry findByPrimaryKey(long id)
		throws eu.strasbourg.service.tipi.exception.NoSuchTipiEntryException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the tipi entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the tipi entry
	* @return the tipi entry, or <code>null</code> if a tipi entry with the primary key could not be found
	*/
	public static TipiEntry fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	public static java.util.Map<java.io.Serializable, TipiEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the tipi entries.
	*
	* @return the tipi entries
	*/
	public static List<TipiEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the tipi entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TipiEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of tipi entries
	* @param end the upper bound of the range of tipi entries (not inclusive)
	* @return the range of tipi entries
	*/
	public static List<TipiEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the tipi entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TipiEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of tipi entries
	* @param end the upper bound of the range of tipi entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of tipi entries
	*/
	public static List<TipiEntry> findAll(int start, int end,
		OrderByComparator<TipiEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the tipi entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TipiEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of tipi entries
	* @param end the upper bound of the range of tipi entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of tipi entries
	*/
	public static List<TipiEntry> findAll(int start, int end,
		OrderByComparator<TipiEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the tipi entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of tipi entries.
	*
	* @return the number of tipi entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static TipiEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TipiEntryPersistence, TipiEntryPersistence> _serviceTracker =
		ServiceTrackerFactory.open(TipiEntryPersistence.class);
}