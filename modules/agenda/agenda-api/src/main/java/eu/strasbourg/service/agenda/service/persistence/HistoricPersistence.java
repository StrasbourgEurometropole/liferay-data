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

package eu.strasbourg.service.agenda.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.agenda.exception.NoSuchHistoricException;
import eu.strasbourg.service.agenda.model.Historic;

import java.io.Serializable;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the historic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see HistoricUtil
 * @generated
 */
@ProviderType
public interface HistoricPersistence extends BasePersistence<Historic> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link HistoricUtil} to access the historic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, Historic> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the historics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching historics
	 */
	public java.util.List<Historic> findByUuid(String uuid);

	/**
	 * Returns a range of all the historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @return the range of matching historics
	 */
	public java.util.List<Historic> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching historics
	 */
	public java.util.List<Historic> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Historic>
			orderByComparator);

	/**
	 * Returns an ordered range of all the historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching historics
	 */
	public java.util.List<Historic> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Historic>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historic
	 * @throws NoSuchHistoricException if a matching historic could not be found
	 */
	public Historic findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Historic>
				orderByComparator)
		throws NoSuchHistoricException;

	/**
	 * Returns the first historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historic, or <code>null</code> if a matching historic could not be found
	 */
	public Historic fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Historic>
			orderByComparator);

	/**
	 * Returns the last historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historic
	 * @throws NoSuchHistoricException if a matching historic could not be found
	 */
	public Historic findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Historic>
				orderByComparator)
		throws NoSuchHistoricException;

	/**
	 * Returns the last historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historic, or <code>null</code> if a matching historic could not be found
	 */
	public Historic fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Historic>
			orderByComparator);

	/**
	 * Returns the historics before and after the current historic in the ordered set where uuid = &#63;.
	 *
	 * @param eventId the primary key of the current historic
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next historic
	 * @throws NoSuchHistoricException if a historic with the primary key could not be found
	 */
	public Historic[] findByUuid_PrevAndNext(
			long eventId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Historic>
				orderByComparator)
		throws NoSuchHistoricException;

	/**
	 * Removes all the historics where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of historics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching historics
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the historics where suppressionDate &ge; &#63;.
	 *
	 * @param suppressionDate the suppression date
	 * @return the matching historics
	 */
	public java.util.List<Historic> findBySuppressionDate(Date suppressionDate);

	/**
	 * Returns a range of all the historics where suppressionDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>.
	 * </p>
	 *
	 * @param suppressionDate the suppression date
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @return the range of matching historics
	 */
	public java.util.List<Historic> findBySuppressionDate(
		Date suppressionDate, int start, int end);

	/**
	 * Returns an ordered range of all the historics where suppressionDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>.
	 * </p>
	 *
	 * @param suppressionDate the suppression date
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching historics
	 */
	public java.util.List<Historic> findBySuppressionDate(
		Date suppressionDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Historic>
			orderByComparator);

	/**
	 * Returns an ordered range of all the historics where suppressionDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>.
	 * </p>
	 *
	 * @param suppressionDate the suppression date
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching historics
	 */
	public java.util.List<Historic> findBySuppressionDate(
		Date suppressionDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Historic>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first historic in the ordered set where suppressionDate &ge; &#63;.
	 *
	 * @param suppressionDate the suppression date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historic
	 * @throws NoSuchHistoricException if a matching historic could not be found
	 */
	public Historic findBySuppressionDate_First(
			Date suppressionDate,
			com.liferay.portal.kernel.util.OrderByComparator<Historic>
				orderByComparator)
		throws NoSuchHistoricException;

	/**
	 * Returns the first historic in the ordered set where suppressionDate &ge; &#63;.
	 *
	 * @param suppressionDate the suppression date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching historic, or <code>null</code> if a matching historic could not be found
	 */
	public Historic fetchBySuppressionDate_First(
		Date suppressionDate,
		com.liferay.portal.kernel.util.OrderByComparator<Historic>
			orderByComparator);

	/**
	 * Returns the last historic in the ordered set where suppressionDate &ge; &#63;.
	 *
	 * @param suppressionDate the suppression date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historic
	 * @throws NoSuchHistoricException if a matching historic could not be found
	 */
	public Historic findBySuppressionDate_Last(
			Date suppressionDate,
			com.liferay.portal.kernel.util.OrderByComparator<Historic>
				orderByComparator)
		throws NoSuchHistoricException;

	/**
	 * Returns the last historic in the ordered set where suppressionDate &ge; &#63;.
	 *
	 * @param suppressionDate the suppression date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching historic, or <code>null</code> if a matching historic could not be found
	 */
	public Historic fetchBySuppressionDate_Last(
		Date suppressionDate,
		com.liferay.portal.kernel.util.OrderByComparator<Historic>
			orderByComparator);

	/**
	 * Returns the historics before and after the current historic in the ordered set where suppressionDate &ge; &#63;.
	 *
	 * @param eventId the primary key of the current historic
	 * @param suppressionDate the suppression date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next historic
	 * @throws NoSuchHistoricException if a historic with the primary key could not be found
	 */
	public Historic[] findBySuppressionDate_PrevAndNext(
			long eventId, Date suppressionDate,
			com.liferay.portal.kernel.util.OrderByComparator<Historic>
				orderByComparator)
		throws NoSuchHistoricException;

	/**
	 * Removes all the historics where suppressionDate &ge; &#63; from the database.
	 *
	 * @param suppressionDate the suppression date
	 */
	public void removeBySuppressionDate(Date suppressionDate);

	/**
	 * Returns the number of historics where suppressionDate &ge; &#63;.
	 *
	 * @param suppressionDate the suppression date
	 * @return the number of matching historics
	 */
	public int countBySuppressionDate(Date suppressionDate);

	/**
	 * Caches the historic in the entity cache if it is enabled.
	 *
	 * @param historic the historic
	 */
	public void cacheResult(Historic historic);

	/**
	 * Caches the historics in the entity cache if it is enabled.
	 *
	 * @param historics the historics
	 */
	public void cacheResult(java.util.List<Historic> historics);

	/**
	 * Creates a new historic with the primary key. Does not add the historic to the database.
	 *
	 * @param eventId the primary key for the new historic
	 * @return the new historic
	 */
	public Historic create(long eventId);

	/**
	 * Removes the historic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventId the primary key of the historic
	 * @return the historic that was removed
	 * @throws NoSuchHistoricException if a historic with the primary key could not be found
	 */
	public Historic remove(long eventId) throws NoSuchHistoricException;

	public Historic updateImpl(Historic historic);

	/**
	 * Returns the historic with the primary key or throws a <code>NoSuchHistoricException</code> if it could not be found.
	 *
	 * @param eventId the primary key of the historic
	 * @return the historic
	 * @throws NoSuchHistoricException if a historic with the primary key could not be found
	 */
	public Historic findByPrimaryKey(long eventId)
		throws NoSuchHistoricException;

	/**
	 * Returns the historic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventId the primary key of the historic
	 * @return the historic, or <code>null</code> if a historic with the primary key could not be found
	 */
	public Historic fetchByPrimaryKey(long eventId);

	/**
	 * Returns all the historics.
	 *
	 * @return the historics
	 */
	public java.util.List<Historic> findAll();

	/**
	 * Returns a range of all the historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @return the range of historics
	 */
	public java.util.List<Historic> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of historics
	 */
	public java.util.List<Historic> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Historic>
			orderByComparator);

	/**
	 * Returns an ordered range of all the historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>HistoricModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of historics
	 */
	public java.util.List<Historic> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Historic>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the historics from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of historics.
	 *
	 * @return the number of historics
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}