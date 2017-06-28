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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.tipi.exception.NoSuchTipiEntryException;
import eu.strasbourg.service.tipi.model.TipiEntry;

import java.util.Date;

/**
 * The persistence interface for the tipi entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see eu.strasbourg.service.tipi.service.persistence.impl.TipiEntryPersistenceImpl
 * @see TipiEntryUtil
 * @generated
 */
@ProviderType
public interface TipiEntryPersistence extends BasePersistence<TipiEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TipiEntryUtil} to access the tipi entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the tipi entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching tipi entries
	*/
	public java.util.List<TipiEntry> findByUuid(java.lang.String uuid);

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
	public java.util.List<TipiEntry> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<TipiEntry> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TipiEntry> orderByComparator);

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
	public java.util.List<TipiEntry> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TipiEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first tipi entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tipi entry
	* @throws NoSuchTipiEntryException if a matching tipi entry could not be found
	*/
	public TipiEntry findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TipiEntry> orderByComparator)
		throws NoSuchTipiEntryException;

	/**
	* Returns the first tipi entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tipi entry, or <code>null</code> if a matching tipi entry could not be found
	*/
	public TipiEntry fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TipiEntry> orderByComparator);

	/**
	* Returns the last tipi entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tipi entry
	* @throws NoSuchTipiEntryException if a matching tipi entry could not be found
	*/
	public TipiEntry findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TipiEntry> orderByComparator)
		throws NoSuchTipiEntryException;

	/**
	* Returns the last tipi entry in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tipi entry, or <code>null</code> if a matching tipi entry could not be found
	*/
	public TipiEntry fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TipiEntry> orderByComparator);

	/**
	* Returns the tipi entries before and after the current tipi entry in the ordered set where uuid = &#63;.
	*
	* @param id the primary key of the current tipi entry
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tipi entry
	* @throws NoSuchTipiEntryException if a tipi entry with the primary key could not be found
	*/
	public TipiEntry[] findByUuid_PrevAndNext(long id, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<TipiEntry> orderByComparator)
		throws NoSuchTipiEntryException;

	/**
	* Removes all the tipi entries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of tipi entries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching tipi entries
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns all the tipi entries where date = &#63;.
	*
	* @param date the date
	* @return the matching tipi entries
	*/
	public java.util.List<TipiEntry> findByDate(Date date);

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
	public java.util.List<TipiEntry> findByDate(Date date, int start, int end);

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
	public java.util.List<TipiEntry> findByDate(Date date, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TipiEntry> orderByComparator);

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
	public java.util.List<TipiEntry> findByDate(Date date, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TipiEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first tipi entry in the ordered set where date = &#63;.
	*
	* @param date the date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tipi entry
	* @throws NoSuchTipiEntryException if a matching tipi entry could not be found
	*/
	public TipiEntry findByDate_First(Date date,
		com.liferay.portal.kernel.util.OrderByComparator<TipiEntry> orderByComparator)
		throws NoSuchTipiEntryException;

	/**
	* Returns the first tipi entry in the ordered set where date = &#63;.
	*
	* @param date the date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching tipi entry, or <code>null</code> if a matching tipi entry could not be found
	*/
	public TipiEntry fetchByDate_First(Date date,
		com.liferay.portal.kernel.util.OrderByComparator<TipiEntry> orderByComparator);

	/**
	* Returns the last tipi entry in the ordered set where date = &#63;.
	*
	* @param date the date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tipi entry
	* @throws NoSuchTipiEntryException if a matching tipi entry could not be found
	*/
	public TipiEntry findByDate_Last(Date date,
		com.liferay.portal.kernel.util.OrderByComparator<TipiEntry> orderByComparator)
		throws NoSuchTipiEntryException;

	/**
	* Returns the last tipi entry in the ordered set where date = &#63;.
	*
	* @param date the date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching tipi entry, or <code>null</code> if a matching tipi entry could not be found
	*/
	public TipiEntry fetchByDate_Last(Date date,
		com.liferay.portal.kernel.util.OrderByComparator<TipiEntry> orderByComparator);

	/**
	* Returns the tipi entries before and after the current tipi entry in the ordered set where date = &#63;.
	*
	* @param id the primary key of the current tipi entry
	* @param date the date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next tipi entry
	* @throws NoSuchTipiEntryException if a tipi entry with the primary key could not be found
	*/
	public TipiEntry[] findByDate_PrevAndNext(long id, Date date,
		com.liferay.portal.kernel.util.OrderByComparator<TipiEntry> orderByComparator)
		throws NoSuchTipiEntryException;

	/**
	* Removes all the tipi entries where date = &#63; from the database.
	*
	* @param date the date
	*/
	public void removeByDate(Date date);

	/**
	* Returns the number of tipi entries where date = &#63;.
	*
	* @param date the date
	* @return the number of matching tipi entries
	*/
	public int countByDate(Date date);

	/**
	* Caches the tipi entry in the entity cache if it is enabled.
	*
	* @param tipiEntry the tipi entry
	*/
	public void cacheResult(TipiEntry tipiEntry);

	/**
	* Caches the tipi entries in the entity cache if it is enabled.
	*
	* @param tipiEntries the tipi entries
	*/
	public void cacheResult(java.util.List<TipiEntry> tipiEntries);

	/**
	* Creates a new tipi entry with the primary key. Does not add the tipi entry to the database.
	*
	* @param id the primary key for the new tipi entry
	* @return the new tipi entry
	*/
	public TipiEntry create(long id);

	/**
	* Removes the tipi entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the tipi entry
	* @return the tipi entry that was removed
	* @throws NoSuchTipiEntryException if a tipi entry with the primary key could not be found
	*/
	public TipiEntry remove(long id) throws NoSuchTipiEntryException;

	public TipiEntry updateImpl(TipiEntry tipiEntry);

	/**
	* Returns the tipi entry with the primary key or throws a {@link NoSuchTipiEntryException} if it could not be found.
	*
	* @param id the primary key of the tipi entry
	* @return the tipi entry
	* @throws NoSuchTipiEntryException if a tipi entry with the primary key could not be found
	*/
	public TipiEntry findByPrimaryKey(long id) throws NoSuchTipiEntryException;

	/**
	* Returns the tipi entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the tipi entry
	* @return the tipi entry, or <code>null</code> if a tipi entry with the primary key could not be found
	*/
	public TipiEntry fetchByPrimaryKey(long id);

	@Override
	public java.util.Map<java.io.Serializable, TipiEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the tipi entries.
	*
	* @return the tipi entries
	*/
	public java.util.List<TipiEntry> findAll();

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
	public java.util.List<TipiEntry> findAll(int start, int end);

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
	public java.util.List<TipiEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TipiEntry> orderByComparator);

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
	public java.util.List<TipiEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TipiEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the tipi entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of tipi entries.
	*
	* @return the number of tipi entries
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}