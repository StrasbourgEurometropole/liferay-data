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

import eu.strasbourg.service.agenda.exception.NoSuchCsmapCacheJsonException;
import eu.strasbourg.service.agenda.model.CsmapCacheJson;

import java.io.Serializable;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the csmap cache json service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see CsmapCacheJsonUtil
 * @generated
 */
@ProviderType
public interface CsmapCacheJsonPersistence
	extends BasePersistence<CsmapCacheJson> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CsmapCacheJsonUtil} to access the csmap cache json persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, CsmapCacheJson> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the csmap cache jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByUuid(String uuid);

	/**
	 * Returns a range of all the csmap cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the csmap cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns an ordered range of all the csmap cache jsons where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the first csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the last csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the last csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where uuid = &#63;.
	 *
	 * @param eventId the primary key of the current csmap cache json
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson[] findByUuid_PrevAndNext(
			long eventId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Removes all the csmap cache jsons where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of csmap cache jsons where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching csmap cache jsons
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the csmap cache jsons where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByeventId(long eventId);

	/**
	 * Returns a range of all the csmap cache jsons where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByeventId(
		long eventId, int start, int end);

	/**
	 * Returns an ordered range of all the csmap cache jsons where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByeventId(
		long eventId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns an ordered range of all the csmap cache jsons where eventId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventId the event ID
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByeventId(
		long eventId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first csmap cache json in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByeventId_First(
			long eventId,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the first csmap cache json in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByeventId_First(
		long eventId,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the last csmap cache json in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByeventId_Last(
			long eventId,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the last csmap cache json in the ordered set where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByeventId_Last(
		long eventId,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Removes all the csmap cache jsons where eventId = &#63; from the database.
	 *
	 * @param eventId the event ID
	 */
	public void removeByeventId(long eventId);

	/**
	 * Returns the number of csmap cache jsons where eventId = &#63;.
	 *
	 * @param eventId the event ID
	 * @return the number of matching csmap cache jsons
	 */
	public int countByeventId(long eventId);

	/**
	 * Returns all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createEvent, boolean isActive);

	/**
	 * Returns a range of all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createEvent, boolean isActive, int start, int end);

	/**
	 * Returns an ordered range of all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createEvent, boolean isActive, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns an ordered range of all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByCreatedDateAndIsActive(
		Date createEvent, boolean isActive, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByCreatedDateAndIsActive_First(
			Date createEvent, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the first csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByCreatedDateAndIsActive_First(
		Date createEvent, boolean isActive,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the last csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByCreatedDateAndIsActive_Last(
			Date createEvent, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the last csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByCreatedDateAndIsActive_Last(
		Date createEvent, boolean isActive,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param eventId the primary key of the current csmap cache json
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson[] findByCreatedDateAndIsActive_PrevAndNext(
			long eventId, Date createEvent, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Removes all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 */
	public void removeByCreatedDateAndIsActive(
		Date createEvent, boolean isActive);

	/**
	 * Returns the number of csmap cache jsons where createEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
	 */
	public int countByCreatedDateAndIsActive(
		Date createEvent, boolean isActive);

	/**
	 * Returns all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActive(
			Date createEvent, Date modifiedEvent, boolean isActive);

	/**
	 * Returns a range of all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActive(
			Date createEvent, Date modifiedEvent, boolean isActive, int start,
			int end);

	/**
	 * Returns an ordered range of all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActive(
			Date createEvent, Date modifiedEvent, boolean isActive, int start,
			int end,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator);

	/**
	 * Returns an ordered range of all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActive(
			Date createEvent, Date modifiedEvent, boolean isActive, int start,
			int end,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator,
			boolean retrieveFromCache);

	/**
	 * Returns the first csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByCreatedDateAndModifiedDateAndIsActive_First(
			Date createEvent, Date modifiedEvent, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the first csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByCreatedDateAndModifiedDateAndIsActive_First(
		Date createEvent, Date modifiedEvent, boolean isActive,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the last csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByCreatedDateAndModifiedDateAndIsActive_Last(
			Date createEvent, Date modifiedEvent, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the last csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByCreatedDateAndModifiedDateAndIsActive_Last(
		Date createEvent, Date modifiedEvent, boolean isActive,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param eventId the primary key of the current csmap cache json
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson[]
			findByCreatedDateAndModifiedDateAndIsActive_PrevAndNext(
				long eventId, Date createEvent, Date modifiedEvent,
				boolean isActive,
				com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
					orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Removes all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 */
	public void removeByCreatedDateAndModifiedDateAndIsActive(
		Date createEvent, Date modifiedEvent, boolean isActive);

	/**
	 * Returns the number of csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
	 */
	public int countByCreatedDateAndModifiedDateAndIsActive(
		Date createEvent, Date modifiedEvent, boolean isActive);

	/**
	 * Returns all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @return the matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson>
		findByCreatedDateAndIsActiveAndWithSchedules(
			Date createEvent, boolean isActive, boolean hasSchedules);

	/**
	 * Returns a range of all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson>
		findByCreatedDateAndIsActiveAndWithSchedules(
			Date createEvent, boolean isActive, boolean hasSchedules, int start,
			int end);

	/**
	 * Returns an ordered range of all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson>
		findByCreatedDateAndIsActiveAndWithSchedules(
			Date createEvent, boolean isActive, boolean hasSchedules, int start,
			int end,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator);

	/**
	 * Returns an ordered range of all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson>
		findByCreatedDateAndIsActiveAndWithSchedules(
			Date createEvent, boolean isActive, boolean hasSchedules, int start,
			int end,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator,
			boolean retrieveFromCache);

	/**
	 * Returns the first csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByCreatedDateAndIsActiveAndWithSchedules_First(
			Date createEvent, boolean isActive, boolean hasSchedules,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the first csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByCreatedDateAndIsActiveAndWithSchedules_First(
		Date createEvent, boolean isActive, boolean hasSchedules,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the last csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByCreatedDateAndIsActiveAndWithSchedules_Last(
			Date createEvent, boolean isActive, boolean hasSchedules,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the last csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByCreatedDateAndIsActiveAndWithSchedules_Last(
		Date createEvent, boolean isActive, boolean hasSchedules,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param eventId the primary key of the current csmap cache json
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson[]
			findByCreatedDateAndIsActiveAndWithSchedules_PrevAndNext(
				long eventId, Date createEvent, boolean isActive,
				boolean hasSchedules,
				com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
					orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Removes all the csmap cache jsons where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63; from the database.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 */
	public void removeByCreatedDateAndIsActiveAndWithSchedules(
		Date createEvent, boolean isActive, boolean hasSchedules);

	/**
	 * Returns the number of csmap cache jsons where createEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @return the number of matching csmap cache jsons
	 */
	public int countByCreatedDateAndIsActiveAndWithSchedules(
		Date createEvent, boolean isActive, boolean hasSchedules);

	/**
	 * Returns all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @return the matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
			Date createEvent, Date modifiedEvent, boolean isActive,
			boolean hasSchedules);

	/**
	 * Returns a range of all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
			Date createEvent, Date modifiedEvent, boolean isActive,
			boolean hasSchedules, int start, int end);

	/**
	 * Returns an ordered range of all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
			Date createEvent, Date modifiedEvent, boolean isActive,
			boolean hasSchedules, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator);

	/**
	 * Returns an ordered range of all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson>
		findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
			Date createEvent, Date modifiedEvent, boolean isActive,
			boolean hasSchedules, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator,
			boolean retrieveFromCache);

	/**
	 * Returns the first csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson
			findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules_First(
				Date createEvent, Date modifiedEvent, boolean isActive,
				boolean hasSchedules,
				com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
					orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the first csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson
		fetchByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules_First(
			Date createEvent, Date modifiedEvent, boolean isActive,
			boolean hasSchedules,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator);

	/**
	 * Returns the last csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson
			findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules_Last(
				Date createEvent, Date modifiedEvent, boolean isActive,
				boolean hasSchedules,
				com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
					orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the last csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson
		fetchByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules_Last(
			Date createEvent, Date modifiedEvent, boolean isActive,
			boolean hasSchedules,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator);

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param eventId the primary key of the current csmap cache json
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson[]
			findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules_PrevAndNext(
				long eventId, Date createEvent, Date modifiedEvent,
				boolean isActive, boolean hasSchedules,
				com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
					orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Removes all the csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63; from the database.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 */
	public void removeByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
		Date createEvent, Date modifiedEvent, boolean isActive,
		boolean hasSchedules);

	/**
	 * Returns the number of csmap cache jsons where createEvent &lt; &#63; and modifiedEvent &ge; &#63; and isActive = &#63; and hasSchedules = &#63;.
	 *
	 * @param createEvent the create event
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param hasSchedules the has schedules
	 * @return the number of matching csmap cache jsons
	 */
	public int countByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
		Date createEvent, Date modifiedEvent, boolean isActive,
		boolean hasSchedules);

	/**
	 * Returns all the csmap cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive);

	/**
	 * Returns a range of all the csmap cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive, int start, int end);

	/**
	 * Returns an ordered range of all the csmap cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns an ordered range of all the csmap cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first csmap cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByModifiedDateAndIsActive_First(
			Date modifiedEvent, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the first csmap cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByModifiedDateAndIsActive_First(
		Date modifiedEvent, boolean isActive,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the last csmap cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByModifiedDateAndIsActive_Last(
			Date modifiedEvent, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the last csmap cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByModifiedDateAndIsActive_Last(
		Date modifiedEvent, boolean isActive,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param eventId the primary key of the current csmap cache json
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson[] findByModifiedDateAndIsActive_PrevAndNext(
			long eventId, Date modifiedEvent, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Removes all the csmap cache jsons where modifiedEvent &ge; &#63; and isActive = &#63; from the database.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 */
	public void removeByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive);

	/**
	 * Returns the number of csmap cache jsons where modifiedEvent &ge; &#63; and isActive = &#63;.
	 *
	 * @param modifiedEvent the modified event
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
	 */
	public int countByModifiedDateAndIsActive(
		Date modifiedEvent, boolean isActive);

	/**
	 * Returns all the csmap cache jsons where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @return the matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByRegeneratedDateAndIsActive(
		Date regeneratedDate, boolean isActive);

	/**
	 * Returns a range of all the csmap cache jsons where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByRegeneratedDateAndIsActive(
		Date regeneratedDate, boolean isActive, int start, int end);

	/**
	 * Returns an ordered range of all the csmap cache jsons where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByRegeneratedDateAndIsActive(
		Date regeneratedDate, boolean isActive, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns an ordered range of all the csmap cache jsons where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findByRegeneratedDateAndIsActive(
		Date regeneratedDate, boolean isActive, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first csmap cache json in the ordered set where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByRegeneratedDateAndIsActive_First(
			Date regeneratedDate, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the first csmap cache json in the ordered set where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByRegeneratedDateAndIsActive_First(
		Date regeneratedDate, boolean isActive,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the last csmap cache json in the ordered set where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson findByRegeneratedDateAndIsActive_Last(
			Date regeneratedDate, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the last csmap cache json in the ordered set where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching csmap cache json, or <code>null</code> if a matching csmap cache json could not be found
	 */
	public CsmapCacheJson fetchByRegeneratedDateAndIsActive_Last(
		Date regeneratedDate, boolean isActive,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns the csmap cache jsons before and after the current csmap cache json in the ordered set where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * @param eventId the primary key of the current csmap cache json
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson[] findByRegeneratedDateAndIsActive_PrevAndNext(
			long eventId, Date regeneratedDate, boolean isActive,
			com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
				orderByComparator)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Removes all the csmap cache jsons where regeneratedDate &le; &#63; and isActive = &#63; from the database.
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 */
	public void removeByRegeneratedDateAndIsActive(
		Date regeneratedDate, boolean isActive);

	/**
	 * Returns the number of csmap cache jsons where regeneratedDate &le; &#63; and isActive = &#63;.
	 *
	 * @param regeneratedDate the regenerated date
	 * @param isActive the is active
	 * @return the number of matching csmap cache jsons
	 */
	public int countByRegeneratedDateAndIsActive(
		Date regeneratedDate, boolean isActive);

	/**
	 * Caches the csmap cache json in the entity cache if it is enabled.
	 *
	 * @param csmapCacheJson the csmap cache json
	 */
	public void cacheResult(CsmapCacheJson csmapCacheJson);

	/**
	 * Caches the csmap cache jsons in the entity cache if it is enabled.
	 *
	 * @param csmapCacheJsons the csmap cache jsons
	 */
	public void cacheResult(java.util.List<CsmapCacheJson> csmapCacheJsons);

	/**
	 * Creates a new csmap cache json with the primary key. Does not add the csmap cache json to the database.
	 *
	 * @param eventId the primary key for the new csmap cache json
	 * @return the new csmap cache json
	 */
	public CsmapCacheJson create(long eventId);

	/**
	 * Removes the csmap cache json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param eventId the primary key of the csmap cache json
	 * @return the csmap cache json that was removed
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson remove(long eventId)
		throws NoSuchCsmapCacheJsonException;

	public CsmapCacheJson updateImpl(CsmapCacheJson csmapCacheJson);

	/**
	 * Returns the csmap cache json with the primary key or throws a <code>NoSuchCsmapCacheJsonException</code> if it could not be found.
	 *
	 * @param eventId the primary key of the csmap cache json
	 * @return the csmap cache json
	 * @throws NoSuchCsmapCacheJsonException if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson findByPrimaryKey(long eventId)
		throws NoSuchCsmapCacheJsonException;

	/**
	 * Returns the csmap cache json with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param eventId the primary key of the csmap cache json
	 * @return the csmap cache json, or <code>null</code> if a csmap cache json with the primary key could not be found
	 */
	public CsmapCacheJson fetchByPrimaryKey(long eventId);

	/**
	 * Returns all the csmap cache jsons.
	 *
	 * @return the csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findAll();

	/**
	 * Returns a range of all the csmap cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the csmap cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator);

	/**
	 * Returns an ordered range of all the csmap cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CsmapCacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of csmap cache jsons
	 */
	public java.util.List<CsmapCacheJson> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CsmapCacheJson>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the csmap cache jsons from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of csmap cache jsons.
	 *
	 * @return the number of csmap cache jsons
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}