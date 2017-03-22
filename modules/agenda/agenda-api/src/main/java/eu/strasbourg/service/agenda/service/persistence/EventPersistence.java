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

import eu.strasbourg.service.agenda.exception.NoSuchEventException;
import eu.strasbourg.service.agenda.model.Event;

import java.util.Date;

/**
 * The persistence interface for the event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.agenda.service.persistence.impl.EventPersistenceImpl
 * @see EventUtil
 * @generated
 */
@ProviderType
public interface EventPersistence extends BasePersistence<Event> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EventUtil} to access the event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the events where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching events
	*/
	public java.util.List<Event> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the events where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of matching events
	*/
	public java.util.List<Event> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the events where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns an ordered range of all the events where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the first event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the last event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the last event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the events before and after the current event in the ordered set where uuid = &#63;.
	*
	* @param eventId the primary key of the current event
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public Event[] findByUuid_PrevAndNext(long eventId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Removes all the events where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of events where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching events
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the event where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEventException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchEventException;

	/**
	* Returns the event where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the event where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the event where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the event that was removed
	*/
	public Event removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchEventException;

	/**
	* Returns the number of events where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching events
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the events where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching events
	*/
	public java.util.List<Event> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the events where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of matching events
	*/
	public java.util.List<Event> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the events where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns an ordered range of all the events where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the first event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the last event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the last event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the events before and after the current event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param eventId the primary key of the current event
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public Event[] findByUuid_C_PrevAndNext(long eventId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Removes all the events where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of events where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching events
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the events where title = &#63;.
	*
	* @param title the title
	* @return the matching events
	*/
	public java.util.List<Event> findByTitle(java.lang.String title);

	/**
	* Returns a range of all the events where title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of matching events
	*/
	public java.util.List<Event> findByTitle(java.lang.String title, int start,
		int end);

	/**
	* Returns an ordered range of all the events where title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByTitle(java.lang.String title, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns an ordered range of all the events where title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByTitle(java.lang.String title, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first event in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByTitle_First(java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the first event in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByTitle_First(java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the last event in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByTitle_Last(java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the last event in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByTitle_Last(java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the events before and after the current event in the ordered set where title = &#63;.
	*
	* @param eventId the primary key of the current event
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public Event[] findByTitle_PrevAndNext(long eventId,
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Removes all the events where title = &#63; from the database.
	*
	* @param title the title
	*/
	public void removeByTitle(java.lang.String title);

	/**
	* Returns the number of events where title = &#63;.
	*
	* @param title the title
	* @return the number of matching events
	*/
	public int countByTitle(java.lang.String title);

	/**
	* Returns all the events where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching events
	*/
	public java.util.List<Event> findByGroupId(long groupId);

	/**
	* Returns a range of all the events where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of matching events
	*/
	public java.util.List<Event> findByGroupId(long groupId, int start, int end);

	/**
	* Returns an ordered range of all the events where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns an ordered range of all the events where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first event in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the first event in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the last event in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the last event in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the events before and after the current event in the ordered set where groupId = &#63;.
	*
	* @param eventId the primary key of the current event
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public Event[] findByGroupId_PrevAndNext(long eventId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Removes all the events where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of events where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching events
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the events where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @return the matching events
	*/
	public java.util.List<Event> findByGroupIdAndTitle(long groupId,
		java.lang.String title);

	/**
	* Returns a range of all the events where groupId = &#63; and title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param title the title
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of matching events
	*/
	public java.util.List<Event> findByGroupIdAndTitle(long groupId,
		java.lang.String title, int start, int end);

	/**
	* Returns an ordered range of all the events where groupId = &#63; and title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param title the title
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByGroupIdAndTitle(long groupId,
		java.lang.String title, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns an ordered range of all the events where groupId = &#63; and title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param title the title
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByGroupIdAndTitle(long groupId,
		java.lang.String title, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first event in the ordered set where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByGroupIdAndTitle_First(long groupId,
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the first event in the ordered set where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByGroupIdAndTitle_First(long groupId,
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the last event in the ordered set where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByGroupIdAndTitle_Last(long groupId,
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the last event in the ordered set where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByGroupIdAndTitle_Last(long groupId,
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the events before and after the current event in the ordered set where groupId = &#63; and title = &#63;.
	*
	* @param eventId the primary key of the current event
	* @param groupId the group ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public Event[] findByGroupIdAndTitle_PrevAndNext(long eventId,
		long groupId, java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Removes all the events where groupId = &#63; and title = &#63; from the database.
	*
	* @param groupId the group ID
	* @param title the title
	*/
	public void removeByGroupIdAndTitle(long groupId, java.lang.String title);

	/**
	* Returns the number of events where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @return the number of matching events
	*/
	public int countByGroupIdAndTitle(long groupId, java.lang.String title);

	/**
	* Returns all the events where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @return the matching events
	*/
	public java.util.List<Event> findByPublicationDateAndStatus(
		Date publicationDate, int status);

	/**
	* Returns a range of all the events where publicationDate &lt; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of matching events
	*/
	public java.util.List<Event> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end);

	/**
	* Returns an ordered range of all the events where publicationDate &lt; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns an ordered range of all the events where publicationDate &lt; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first event in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByPublicationDateAndStatus_First(Date publicationDate,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the first event in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByPublicationDateAndStatus_First(Date publicationDate,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the last event in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByPublicationDateAndStatus_Last(Date publicationDate,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the last event in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByPublicationDateAndStatus_Last(Date publicationDate,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the events before and after the current event in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param eventId the primary key of the current event
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public Event[] findByPublicationDateAndStatus_PrevAndNext(long eventId,
		Date publicationDate, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Removes all the events where publicationDate &lt; &#63; and status = &#63; from the database.
	*
	* @param publicationDate the publication date
	* @param status the status
	*/
	public void removeByPublicationDateAndStatus(Date publicationDate,
		int status);

	/**
	* Returns the number of events where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @return the number of matching events
	*/
	public int countByPublicationDateAndStatus(Date publicationDate, int status);

	/**
	* Returns the event where source = &#63; and idSource = &#63; or throws a {@link NoSuchEventException} if it could not be found.
	*
	* @param source the source
	* @param idSource the id source
	* @return the matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findBySourceAndIdSource(java.lang.String source,
		java.lang.String idSource) throws NoSuchEventException;

	/**
	* Returns the event where source = &#63; and idSource = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param source the source
	* @param idSource the id source
	* @return the matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchBySourceAndIdSource(java.lang.String source,
		java.lang.String idSource);

	/**
	* Returns the event where source = &#63; and idSource = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param source the source
	* @param idSource the id source
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchBySourceAndIdSource(java.lang.String source,
		java.lang.String idSource, boolean retrieveFromCache);

	/**
	* Removes the event where source = &#63; and idSource = &#63; from the database.
	*
	* @param source the source
	* @param idSource the id source
	* @return the event that was removed
	*/
	public Event removeBySourceAndIdSource(java.lang.String source,
		java.lang.String idSource) throws NoSuchEventException;

	/**
	* Returns the number of events where source = &#63; and idSource = &#63;.
	*
	* @param source the source
	* @param idSource the id source
	* @return the number of matching events
	*/
	public int countBySourceAndIdSource(java.lang.String source,
		java.lang.String idSource);

	/**
	* Returns the event where idSource = &#63; or throws a {@link NoSuchEventException} if it could not be found.
	*
	* @param idSource the id source
	* @return the matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByIdSource(java.lang.String idSource)
		throws NoSuchEventException;

	/**
	* Returns the event where idSource = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param idSource the id source
	* @return the matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByIdSource(java.lang.String idSource);

	/**
	* Returns the event where idSource = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param idSource the id source
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByIdSource(java.lang.String idSource,
		boolean retrieveFromCache);

	/**
	* Removes the event where idSource = &#63; from the database.
	*
	* @param idSource the id source
	* @return the event that was removed
	*/
	public Event removeByIdSource(java.lang.String idSource)
		throws NoSuchEventException;

	/**
	* Returns the number of events where idSource = &#63;.
	*
	* @param idSource the id source
	* @return the number of matching events
	*/
	public int countByIdSource(java.lang.String idSource);

	/**
	* Caches the event in the entity cache if it is enabled.
	*
	* @param event the event
	*/
	public void cacheResult(Event event);

	/**
	* Caches the events in the entity cache if it is enabled.
	*
	* @param events the events
	*/
	public void cacheResult(java.util.List<Event> events);

	/**
	* Creates a new event with the primary key. Does not add the event to the database.
	*
	* @param eventId the primary key for the new event
	* @return the new event
	*/
	public Event create(long eventId);

	/**
	* Removes the event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventId the primary key of the event
	* @return the event that was removed
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public Event remove(long eventId) throws NoSuchEventException;

	public Event updateImpl(Event event);

	/**
	* Returns the event with the primary key or throws a {@link NoSuchEventException} if it could not be found.
	*
	* @param eventId the primary key of the event
	* @return the event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public Event findByPrimaryKey(long eventId) throws NoSuchEventException;

	/**
	* Returns the event with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param eventId the primary key of the event
	* @return the event, or <code>null</code> if a event with the primary key could not be found
	*/
	public Event fetchByPrimaryKey(long eventId);

	@Override
	public java.util.Map<java.io.Serializable, Event> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the events.
	*
	* @return the events
	*/
	public java.util.List<Event> findAll();

	/**
	* Returns a range of all the events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of events
	*/
	public java.util.List<Event> findAll(int start, int end);

	/**
	* Returns an ordered range of all the events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of events
	*/
	public java.util.List<Event> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns an ordered range of all the events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of events
	*/
	public java.util.List<Event> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the events from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of events.
	*
	* @return the number of events
	*/
	public int countAll();

	/**
	* Returns the primaryKeys of manifestations associated with the event.
	*
	* @param pk the primary key of the event
	* @return long[] of the primaryKeys of manifestations associated with the event
	*/
	public long[] getManifestationPrimaryKeys(long pk);

	/**
	* Returns all the manifestations associated with the event.
	*
	* @param pk the primary key of the event
	* @return the manifestations associated with the event
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> getManifestations(
		long pk);

	/**
	* Returns a range of all the manifestations associated with the event.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the event
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of manifestations associated with the event
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> getManifestations(
		long pk, int start, int end);

	/**
	* Returns an ordered range of all the manifestations associated with the event.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the event
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of manifestations associated with the event
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.Manifestation> getManifestations(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.agenda.model.Manifestation> orderByComparator);

	/**
	* Returns the number of manifestations associated with the event.
	*
	* @param pk the primary key of the event
	* @return the number of manifestations associated with the event
	*/
	public int getManifestationsSize(long pk);

	/**
	* Returns <code>true</code> if the manifestation is associated with the event.
	*
	* @param pk the primary key of the event
	* @param manifestationPK the primary key of the manifestation
	* @return <code>true</code> if the manifestation is associated with the event; <code>false</code> otherwise
	*/
	public boolean containsManifestation(long pk, long manifestationPK);

	/**
	* Returns <code>true</code> if the event has any manifestations associated with it.
	*
	* @param pk the primary key of the event to check for associations with manifestations
	* @return <code>true</code> if the event has any manifestations associated with it; <code>false</code> otherwise
	*/
	public boolean containsManifestations(long pk);

	/**
	* Adds an association between the event and the manifestation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestationPK the primary key of the manifestation
	*/
	public void addManifestation(long pk, long manifestationPK);

	/**
	* Adds an association between the event and the manifestation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestation the manifestation
	*/
	public void addManifestation(long pk,
		eu.strasbourg.service.agenda.model.Manifestation manifestation);

	/**
	* Adds an association between the event and the manifestations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestationPKs the primary keys of the manifestations
	*/
	public void addManifestations(long pk, long[] manifestationPKs);

	/**
	* Adds an association between the event and the manifestations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestations the manifestations
	*/
	public void addManifestations(long pk,
		java.util.List<eu.strasbourg.service.agenda.model.Manifestation> manifestations);

	/**
	* Clears all associations between the event and its manifestations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event to clear the associated manifestations from
	*/
	public void clearManifestations(long pk);

	/**
	* Removes the association between the event and the manifestation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestationPK the primary key of the manifestation
	*/
	public void removeManifestation(long pk, long manifestationPK);

	/**
	* Removes the association between the event and the manifestation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestation the manifestation
	*/
	public void removeManifestation(long pk,
		eu.strasbourg.service.agenda.model.Manifestation manifestation);

	/**
	* Removes the association between the event and the manifestations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestationPKs the primary keys of the manifestations
	*/
	public void removeManifestations(long pk, long[] manifestationPKs);

	/**
	* Removes the association between the event and the manifestations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestations the manifestations
	*/
	public void removeManifestations(long pk,
		java.util.List<eu.strasbourg.service.agenda.model.Manifestation> manifestations);

	/**
	* Sets the manifestations associated with the event, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestationPKs the primary keys of the manifestations to be associated with the event
	*/
	public void setManifestations(long pk, long[] manifestationPKs);

	/**
	* Sets the manifestations associated with the event, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestations the manifestations to be associated with the event
	*/
	public void setManifestations(long pk,
		java.util.List<eu.strasbourg.service.agenda.model.Manifestation> manifestations);

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}