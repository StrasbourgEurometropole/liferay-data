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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.agenda.model.Event;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the event service. This utility wraps {@link eu.strasbourg.service.agenda.service.persistence.impl.EventPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see EventPersistence
 * @see eu.strasbourg.service.agenda.service.persistence.impl.EventPersistenceImpl
 * @generated
 */
@ProviderType
public class EventUtil {
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
	public static void clearCache(Event event) {
		getPersistence().clearCache(event);
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
	public static List<Event> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Event> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Event> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Event update(Event event) {
		return getPersistence().update(event);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Event update(Event event, ServiceContext serviceContext) {
		return getPersistence().update(event, serviceContext);
	}

	/**
	* Returns all the events where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching events
	*/
	public static List<Event> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<Event> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<Event> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Event> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<Event> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByUuid_First(java.lang.String uuid,
		OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the events before and after the current event in the ordered set where uuid = &#63;.
	*
	* @param eventId the primary key of the current event
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public static Event[] findByUuid_PrevAndNext(long eventId,
		java.lang.String uuid, OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByUuid_PrevAndNext(eventId, uuid, orderByComparator);
	}

	/**
	* Removes all the events where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of events where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching events
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the event where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEventException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the event where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the event where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the event where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the event that was removed
	*/
	public static Event removeByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of events where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching events
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the events where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching events
	*/
	public static List<Event> findByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<Event> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<Event> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<Event> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Event> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static Event[] findByUuid_C_PrevAndNext(long eventId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(eventId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the events where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of events where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching events
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the events where title = &#63;.
	*
	* @param title the title
	* @return the matching events
	*/
	public static List<Event> findByTitle(java.lang.String title) {
		return getPersistence().findByTitle(title);
	}

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
	public static List<Event> findByTitle(java.lang.String title, int start,
		int end) {
		return getPersistence().findByTitle(title, start, end);
	}

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
	public static List<Event> findByTitle(java.lang.String title, int start,
		int end, OrderByComparator<Event> orderByComparator) {
		return getPersistence().findByTitle(title, start, end, orderByComparator);
	}

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
	public static List<Event> findByTitle(java.lang.String title, int start,
		int end, OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTitle(title, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first event in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByTitle_First(java.lang.String title,
		OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence().findByTitle_First(title, orderByComparator);
	}

	/**
	* Returns the first event in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByTitle_First(java.lang.String title,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence().fetchByTitle_First(title, orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByTitle_Last(java.lang.String title,
		OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence().findByTitle_Last(title, orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByTitle_Last(java.lang.String title,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence().fetchByTitle_Last(title, orderByComparator);
	}

	/**
	* Returns the events before and after the current event in the ordered set where title = &#63;.
	*
	* @param eventId the primary key of the current event
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public static Event[] findByTitle_PrevAndNext(long eventId,
		java.lang.String title, OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByTitle_PrevAndNext(eventId, title, orderByComparator);
	}

	/**
	* Removes all the events where title = &#63; from the database.
	*
	* @param title the title
	*/
	public static void removeByTitle(java.lang.String title) {
		getPersistence().removeByTitle(title);
	}

	/**
	* Returns the number of events where title = &#63;.
	*
	* @param title the title
	* @return the number of matching events
	*/
	public static int countByTitle(java.lang.String title) {
		return getPersistence().countByTitle(title);
	}

	/**
	* Returns all the events where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching events
	*/
	public static List<Event> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

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
	public static List<Event> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

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
	public static List<Event> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

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
	public static List<Event> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Event> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first event in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByGroupId_First(long groupId,
		OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first event in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByGroupId_First(long groupId,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByGroupId_Last(long groupId,
		OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByGroupId_Last(long groupId,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the events before and after the current event in the ordered set where groupId = &#63;.
	*
	* @param eventId the primary key of the current event
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public static Event[] findByGroupId_PrevAndNext(long eventId, long groupId,
		OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(eventId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the events where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of events where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching events
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the events where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @return the matching events
	*/
	public static List<Event> findByGroupIdAndTitle(long groupId,
		java.lang.String title) {
		return getPersistence().findByGroupIdAndTitle(groupId, title);
	}

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
	public static List<Event> findByGroupIdAndTitle(long groupId,
		java.lang.String title, int start, int end) {
		return getPersistence().findByGroupIdAndTitle(groupId, title, start, end);
	}

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
	public static List<Event> findByGroupIdAndTitle(long groupId,
		java.lang.String title, int start, int end,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .findByGroupIdAndTitle(groupId, title, start, end,
			orderByComparator);
	}

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
	public static List<Event> findByGroupIdAndTitle(long groupId,
		java.lang.String title, int start, int end,
		OrderByComparator<Event> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupIdAndTitle(groupId, title, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first event in the ordered set where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByGroupIdAndTitle_First(long groupId,
		java.lang.String title, OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByGroupIdAndTitle_First(groupId, title,
			orderByComparator);
	}

	/**
	* Returns the first event in the ordered set where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByGroupIdAndTitle_First(long groupId,
		java.lang.String title, OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .fetchByGroupIdAndTitle_First(groupId, title,
			orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByGroupIdAndTitle_Last(long groupId,
		java.lang.String title, OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByGroupIdAndTitle_Last(groupId, title, orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByGroupIdAndTitle_Last(long groupId,
		java.lang.String title, OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .fetchByGroupIdAndTitle_Last(groupId, title,
			orderByComparator);
	}

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
	public static Event[] findByGroupIdAndTitle_PrevAndNext(long eventId,
		long groupId, java.lang.String title,
		OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByGroupIdAndTitle_PrevAndNext(eventId, groupId, title,
			orderByComparator);
	}

	/**
	* Removes all the events where groupId = &#63; and title = &#63; from the database.
	*
	* @param groupId the group ID
	* @param title the title
	*/
	public static void removeByGroupIdAndTitle(long groupId,
		java.lang.String title) {
		getPersistence().removeByGroupIdAndTitle(groupId, title);
	}

	/**
	* Returns the number of events where groupId = &#63; and title = &#63;.
	*
	* @param groupId the group ID
	* @param title the title
	* @return the number of matching events
	*/
	public static int countByGroupIdAndTitle(long groupId,
		java.lang.String title) {
		return getPersistence().countByGroupIdAndTitle(groupId, title);
	}

	/**
	* Returns all the events where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @return the matching events
	*/
	public static List<Event> findByPublicationDateAndStatus(
		Date publicationDate, int status) {
		return getPersistence()
				   .findByPublicationDateAndStatus(publicationDate, status);
	}

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
	public static List<Event> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end) {
		return getPersistence()
				   .findByPublicationDateAndStatus(publicationDate, status,
			start, end);
	}

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
	public static List<Event> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .findByPublicationDateAndStatus(publicationDate, status,
			start, end, orderByComparator);
	}

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
	public static List<Event> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		OrderByComparator<Event> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByPublicationDateAndStatus(publicationDate, status,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first event in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByPublicationDateAndStatus_First(
		Date publicationDate, int status,
		OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByPublicationDateAndStatus_First(publicationDate,
			status, orderByComparator);
	}

	/**
	* Returns the first event in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByPublicationDateAndStatus_First(
		Date publicationDate, int status,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .fetchByPublicationDateAndStatus_First(publicationDate,
			status, orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByPublicationDateAndStatus_Last(
		Date publicationDate, int status,
		OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByPublicationDateAndStatus_Last(publicationDate,
			status, orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByPublicationDateAndStatus_Last(
		Date publicationDate, int status,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .fetchByPublicationDateAndStatus_Last(publicationDate,
			status, orderByComparator);
	}

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
	public static Event[] findByPublicationDateAndStatus_PrevAndNext(
		long eventId, Date publicationDate, int status,
		OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByPublicationDateAndStatus_PrevAndNext(eventId,
			publicationDate, status, orderByComparator);
	}

	/**
	* Removes all the events where publicationDate &lt; &#63; and status = &#63; from the database.
	*
	* @param publicationDate the publication date
	* @param status the status
	*/
	public static void removeByPublicationDateAndStatus(Date publicationDate,
		int status) {
		getPersistence()
			.removeByPublicationDateAndStatus(publicationDate, status);
	}

	/**
	* Returns the number of events where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @return the number of matching events
	*/
	public static int countByPublicationDateAndStatus(Date publicationDate,
		int status) {
		return getPersistence()
				   .countByPublicationDateAndStatus(publicationDate, status);
	}

	/**
	* Returns all the events where lastEndDate &lt; &#63;.
	*
	* @param lastEndDate the last end date
	* @return the matching events
	*/
	public static List<Event> findByLastEndDate(Date lastEndDate) {
		return getPersistence().findByLastEndDate(lastEndDate);
	}

	/**
	* Returns a range of all the events where lastEndDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lastEndDate the last end date
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of matching events
	*/
	public static List<Event> findByLastEndDate(Date lastEndDate, int start,
		int end) {
		return getPersistence().findByLastEndDate(lastEndDate, start, end);
	}

	/**
	* Returns an ordered range of all the events where lastEndDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lastEndDate the last end date
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching events
	*/
	public static List<Event> findByLastEndDate(Date lastEndDate, int start,
		int end, OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .findByLastEndDate(lastEndDate, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the events where lastEndDate &lt; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param lastEndDate the last end date
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching events
	*/
	public static List<Event> findByLastEndDate(Date lastEndDate, int start,
		int end, OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLastEndDate(lastEndDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first event in the ordered set where lastEndDate &lt; &#63;.
	*
	* @param lastEndDate the last end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByLastEndDate_First(Date lastEndDate,
		OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByLastEndDate_First(lastEndDate, orderByComparator);
	}

	/**
	* Returns the first event in the ordered set where lastEndDate &lt; &#63;.
	*
	* @param lastEndDate the last end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByLastEndDate_First(Date lastEndDate,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .fetchByLastEndDate_First(lastEndDate, orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where lastEndDate &lt; &#63;.
	*
	* @param lastEndDate the last end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByLastEndDate_Last(Date lastEndDate,
		OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByLastEndDate_Last(lastEndDate, orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where lastEndDate &lt; &#63;.
	*
	* @param lastEndDate the last end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByLastEndDate_Last(Date lastEndDate,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .fetchByLastEndDate_Last(lastEndDate, orderByComparator);
	}

	/**
	* Returns the events before and after the current event in the ordered set where lastEndDate &lt; &#63;.
	*
	* @param eventId the primary key of the current event
	* @param lastEndDate the last end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public static Event[] findByLastEndDate_PrevAndNext(long eventId,
		Date lastEndDate, OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByLastEndDate_PrevAndNext(eventId, lastEndDate,
			orderByComparator);
	}

	/**
	* Removes all the events where lastEndDate &lt; &#63; from the database.
	*
	* @param lastEndDate the last end date
	*/
	public static void removeByLastEndDate(Date lastEndDate) {
		getPersistence().removeByLastEndDate(lastEndDate);
	}

	/**
	* Returns the number of events where lastEndDate &lt; &#63;.
	*
	* @param lastEndDate the last end date
	* @return the number of matching events
	*/
	public static int countByLastEndDate(Date lastEndDate) {
		return getPersistence().countByLastEndDate(lastEndDate);
	}

	/**
	* Returns all the events where statusDate &lt; &#63; and status = &#63;.
	*
	* @param statusDate the status date
	* @param status the status
	* @return the matching events
	*/
	public static List<Event> findByStatusDateAndStatus(Date statusDate,
		int status) {
		return getPersistence().findByStatusDateAndStatus(statusDate, status);
	}

	/**
	* Returns a range of all the events where statusDate &lt; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statusDate the status date
	* @param status the status
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of matching events
	*/
	public static List<Event> findByStatusDateAndStatus(Date statusDate,
		int status, int start, int end) {
		return getPersistence()
				   .findByStatusDateAndStatus(statusDate, status, start, end);
	}

	/**
	* Returns an ordered range of all the events where statusDate &lt; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statusDate the status date
	* @param status the status
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching events
	*/
	public static List<Event> findByStatusDateAndStatus(Date statusDate,
		int status, int start, int end,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .findByStatusDateAndStatus(statusDate, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the events where statusDate &lt; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param statusDate the status date
	* @param status the status
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching events
	*/
	public static List<Event> findByStatusDateAndStatus(Date statusDate,
		int status, int start, int end,
		OrderByComparator<Event> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByStatusDateAndStatus(statusDate, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first event in the ordered set where statusDate &lt; &#63; and status = &#63;.
	*
	* @param statusDate the status date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByStatusDateAndStatus_First(Date statusDate,
		int status, OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByStatusDateAndStatus_First(statusDate, status,
			orderByComparator);
	}

	/**
	* Returns the first event in the ordered set where statusDate &lt; &#63; and status = &#63;.
	*
	* @param statusDate the status date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByStatusDateAndStatus_First(Date statusDate,
		int status, OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .fetchByStatusDateAndStatus_First(statusDate, status,
			orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where statusDate &lt; &#63; and status = &#63;.
	*
	* @param statusDate the status date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByStatusDateAndStatus_Last(Date statusDate,
		int status, OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByStatusDateAndStatus_Last(statusDate, status,
			orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where statusDate &lt; &#63; and status = &#63;.
	*
	* @param statusDate the status date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByStatusDateAndStatus_Last(Date statusDate,
		int status, OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .fetchByStatusDateAndStatus_Last(statusDate, status,
			orderByComparator);
	}

	/**
	* Returns the events before and after the current event in the ordered set where statusDate &lt; &#63; and status = &#63;.
	*
	* @param eventId the primary key of the current event
	* @param statusDate the status date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public static Event[] findByStatusDateAndStatus_PrevAndNext(long eventId,
		Date statusDate, int status, OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByStatusDateAndStatus_PrevAndNext(eventId, statusDate,
			status, orderByComparator);
	}

	/**
	* Removes all the events where statusDate &lt; &#63; and status = &#63; from the database.
	*
	* @param statusDate the status date
	* @param status the status
	*/
	public static void removeByStatusDateAndStatus(Date statusDate, int status) {
		getPersistence().removeByStatusDateAndStatus(statusDate, status);
	}

	/**
	* Returns the number of events where statusDate &lt; &#63; and status = &#63;.
	*
	* @param statusDate the status date
	* @param status the status
	* @return the number of matching events
	*/
	public static int countByStatusDateAndStatus(Date statusDate, int status) {
		return getPersistence().countByStatusDateAndStatus(statusDate, status);
	}

	/**
	* Returns the event where source = &#63; and idSource = &#63; or throws a {@link NoSuchEventException} if it could not be found.
	*
	* @param source the source
	* @param idSource the id source
	* @return the matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findBySourceAndIdSource(java.lang.String source,
		java.lang.String idSource)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence().findBySourceAndIdSource(source, idSource);
	}

	/**
	* Returns the event where source = &#63; and idSource = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param source the source
	* @param idSource the id source
	* @return the matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchBySourceAndIdSource(java.lang.String source,
		java.lang.String idSource) {
		return getPersistence().fetchBySourceAndIdSource(source, idSource);
	}

	/**
	* Returns the event where source = &#63; and idSource = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param source the source
	* @param idSource the id source
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchBySourceAndIdSource(java.lang.String source,
		java.lang.String idSource, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchBySourceAndIdSource(source, idSource, retrieveFromCache);
	}

	/**
	* Removes the event where source = &#63; and idSource = &#63; from the database.
	*
	* @param source the source
	* @param idSource the id source
	* @return the event that was removed
	*/
	public static Event removeBySourceAndIdSource(java.lang.String source,
		java.lang.String idSource)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence().removeBySourceAndIdSource(source, idSource);
	}

	/**
	* Returns the number of events where source = &#63; and idSource = &#63;.
	*
	* @param source the source
	* @param idSource the id source
	* @return the number of matching events
	*/
	public static int countBySourceAndIdSource(java.lang.String source,
		java.lang.String idSource) {
		return getPersistence().countBySourceAndIdSource(source, idSource);
	}

	/**
	* Returns the event where idSource = &#63; or throws a {@link NoSuchEventException} if it could not be found.
	*
	* @param idSource the id source
	* @return the matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByIdSource(java.lang.String idSource)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence().findByIdSource(idSource);
	}

	/**
	* Returns the event where idSource = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param idSource the id source
	* @return the matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByIdSource(java.lang.String idSource) {
		return getPersistence().fetchByIdSource(idSource);
	}

	/**
	* Returns the event where idSource = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param idSource the id source
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByIdSource(java.lang.String idSource,
		boolean retrieveFromCache) {
		return getPersistence().fetchByIdSource(idSource, retrieveFromCache);
	}

	/**
	* Removes the event where idSource = &#63; from the database.
	*
	* @param idSource the id source
	* @return the event that was removed
	*/
	public static Event removeByIdSource(java.lang.String idSource)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence().removeByIdSource(idSource);
	}

	/**
	* Returns the number of events where idSource = &#63;.
	*
	* @param idSource the id source
	* @return the number of matching events
	*/
	public static int countByIdSource(java.lang.String idSource) {
		return getPersistence().countByIdSource(idSource);
	}

	/**
	* Returns all the events where placeSIGId = &#63;.
	*
	* @param placeSIGId the place s i g ID
	* @return the matching events
	*/
	public static List<Event> findByPlaceSIGId(java.lang.String placeSIGId) {
		return getPersistence().findByPlaceSIGId(placeSIGId);
	}

	/**
	* Returns a range of all the events where placeSIGId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeSIGId the place s i g ID
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of matching events
	*/
	public static List<Event> findByPlaceSIGId(java.lang.String placeSIGId,
		int start, int end) {
		return getPersistence().findByPlaceSIGId(placeSIGId, start, end);
	}

	/**
	* Returns an ordered range of all the events where placeSIGId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeSIGId the place s i g ID
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching events
	*/
	public static List<Event> findByPlaceSIGId(java.lang.String placeSIGId,
		int start, int end, OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .findByPlaceSIGId(placeSIGId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the events where placeSIGId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeSIGId the place s i g ID
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching events
	*/
	public static List<Event> findByPlaceSIGId(java.lang.String placeSIGId,
		int start, int end, OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPlaceSIGId(placeSIGId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first event in the ordered set where placeSIGId = &#63;.
	*
	* @param placeSIGId the place s i g ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByPlaceSIGId_First(java.lang.String placeSIGId,
		OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByPlaceSIGId_First(placeSIGId, orderByComparator);
	}

	/**
	* Returns the first event in the ordered set where placeSIGId = &#63;.
	*
	* @param placeSIGId the place s i g ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByPlaceSIGId_First(java.lang.String placeSIGId,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .fetchByPlaceSIGId_First(placeSIGId, orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where placeSIGId = &#63;.
	*
	* @param placeSIGId the place s i g ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public static Event findByPlaceSIGId_Last(java.lang.String placeSIGId,
		OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByPlaceSIGId_Last(placeSIGId, orderByComparator);
	}

	/**
	* Returns the last event in the ordered set where placeSIGId = &#63;.
	*
	* @param placeSIGId the place s i g ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public static Event fetchByPlaceSIGId_Last(java.lang.String placeSIGId,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence()
				   .fetchByPlaceSIGId_Last(placeSIGId, orderByComparator);
	}

	/**
	* Returns the events before and after the current event in the ordered set where placeSIGId = &#63;.
	*
	* @param eventId the primary key of the current event
	* @param placeSIGId the place s i g ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public static Event[] findByPlaceSIGId_PrevAndNext(long eventId,
		java.lang.String placeSIGId, OrderByComparator<Event> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence()
				   .findByPlaceSIGId_PrevAndNext(eventId, placeSIGId,
			orderByComparator);
	}

	/**
	* Removes all the events where placeSIGId = &#63; from the database.
	*
	* @param placeSIGId the place s i g ID
	*/
	public static void removeByPlaceSIGId(java.lang.String placeSIGId) {
		getPersistence().removeByPlaceSIGId(placeSIGId);
	}

	/**
	* Returns the number of events where placeSIGId = &#63;.
	*
	* @param placeSIGId the place s i g ID
	* @return the number of matching events
	*/
	public static int countByPlaceSIGId(java.lang.String placeSIGId) {
		return getPersistence().countByPlaceSIGId(placeSIGId);
	}

	/**
	* Caches the event in the entity cache if it is enabled.
	*
	* @param event the event
	*/
	public static void cacheResult(Event event) {
		getPersistence().cacheResult(event);
	}

	/**
	* Caches the events in the entity cache if it is enabled.
	*
	* @param events the events
	*/
	public static void cacheResult(List<Event> events) {
		getPersistence().cacheResult(events);
	}

	/**
	* Creates a new event with the primary key. Does not add the event to the database.
	*
	* @param eventId the primary key for the new event
	* @return the new event
	*/
	public static Event create(long eventId) {
		return getPersistence().create(eventId);
	}

	/**
	* Removes the event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventId the primary key of the event
	* @return the event that was removed
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public static Event remove(long eventId)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence().remove(eventId);
	}

	public static Event updateImpl(Event event) {
		return getPersistence().updateImpl(event);
	}

	/**
	* Returns the event with the primary key or throws a {@link NoSuchEventException} if it could not be found.
	*
	* @param eventId the primary key of the event
	* @return the event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public static Event findByPrimaryKey(long eventId)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventException {
		return getPersistence().findByPrimaryKey(eventId);
	}

	/**
	* Returns the event with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param eventId the primary key of the event
	* @return the event, or <code>null</code> if a event with the primary key could not be found
	*/
	public static Event fetchByPrimaryKey(long eventId) {
		return getPersistence().fetchByPrimaryKey(eventId);
	}

	public static java.util.Map<java.io.Serializable, Event> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the events.
	*
	* @return the events
	*/
	public static List<Event> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Event> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Event> findAll(int start, int end,
		OrderByComparator<Event> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Event> findAll(int start, int end,
		OrderByComparator<Event> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the events from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of events.
	*
	* @return the number of events
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of manifestations associated with the event.
	*
	* @param pk the primary key of the event
	* @return long[] of the primaryKeys of manifestations associated with the event
	*/
	public static long[] getManifestationPrimaryKeys(long pk) {
		return getPersistence().getManifestationPrimaryKeys(pk);
	}

	/**
	* Returns all the manifestations associated with the event.
	*
	* @param pk the primary key of the event
	* @return the manifestations associated with the event
	*/
	public static List<eu.strasbourg.service.agenda.model.Manifestation> getManifestations(
		long pk) {
		return getPersistence().getManifestations(pk);
	}

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
	public static List<eu.strasbourg.service.agenda.model.Manifestation> getManifestations(
		long pk, int start, int end) {
		return getPersistence().getManifestations(pk, start, end);
	}

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
	public static List<eu.strasbourg.service.agenda.model.Manifestation> getManifestations(
		long pk, int start, int end,
		OrderByComparator<eu.strasbourg.service.agenda.model.Manifestation> orderByComparator) {
		return getPersistence()
				   .getManifestations(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of manifestations associated with the event.
	*
	* @param pk the primary key of the event
	* @return the number of manifestations associated with the event
	*/
	public static int getManifestationsSize(long pk) {
		return getPersistence().getManifestationsSize(pk);
	}

	/**
	* Returns <code>true</code> if the manifestation is associated with the event.
	*
	* @param pk the primary key of the event
	* @param manifestationPK the primary key of the manifestation
	* @return <code>true</code> if the manifestation is associated with the event; <code>false</code> otherwise
	*/
	public static boolean containsManifestation(long pk, long manifestationPK) {
		return getPersistence().containsManifestation(pk, manifestationPK);
	}

	/**
	* Returns <code>true</code> if the event has any manifestations associated with it.
	*
	* @param pk the primary key of the event to check for associations with manifestations
	* @return <code>true</code> if the event has any manifestations associated with it; <code>false</code> otherwise
	*/
	public static boolean containsManifestations(long pk) {
		return getPersistence().containsManifestations(pk);
	}

	/**
	* Adds an association between the event and the manifestation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestationPK the primary key of the manifestation
	*/
	public static void addManifestation(long pk, long manifestationPK) {
		getPersistence().addManifestation(pk, manifestationPK);
	}

	/**
	* Adds an association between the event and the manifestation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestation the manifestation
	*/
	public static void addManifestation(long pk,
		eu.strasbourg.service.agenda.model.Manifestation manifestation) {
		getPersistence().addManifestation(pk, manifestation);
	}

	/**
	* Adds an association between the event and the manifestations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestationPKs the primary keys of the manifestations
	*/
	public static void addManifestations(long pk, long[] manifestationPKs) {
		getPersistence().addManifestations(pk, manifestationPKs);
	}

	/**
	* Adds an association between the event and the manifestations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestations the manifestations
	*/
	public static void addManifestations(long pk,
		List<eu.strasbourg.service.agenda.model.Manifestation> manifestations) {
		getPersistence().addManifestations(pk, manifestations);
	}

	/**
	* Clears all associations between the event and its manifestations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event to clear the associated manifestations from
	*/
	public static void clearManifestations(long pk) {
		getPersistence().clearManifestations(pk);
	}

	/**
	* Removes the association between the event and the manifestation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestationPK the primary key of the manifestation
	*/
	public static void removeManifestation(long pk, long manifestationPK) {
		getPersistence().removeManifestation(pk, manifestationPK);
	}

	/**
	* Removes the association between the event and the manifestation. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestation the manifestation
	*/
	public static void removeManifestation(long pk,
		eu.strasbourg.service.agenda.model.Manifestation manifestation) {
		getPersistence().removeManifestation(pk, manifestation);
	}

	/**
	* Removes the association between the event and the manifestations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestationPKs the primary keys of the manifestations
	*/
	public static void removeManifestations(long pk, long[] manifestationPKs) {
		getPersistence().removeManifestations(pk, manifestationPKs);
	}

	/**
	* Removes the association between the event and the manifestations. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestations the manifestations
	*/
	public static void removeManifestations(long pk,
		List<eu.strasbourg.service.agenda.model.Manifestation> manifestations) {
		getPersistence().removeManifestations(pk, manifestations);
	}

	/**
	* Sets the manifestations associated with the event, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestationPKs the primary keys of the manifestations to be associated with the event
	*/
	public static void setManifestations(long pk, long[] manifestationPKs) {
		getPersistence().setManifestations(pk, manifestationPKs);
	}

	/**
	* Sets the manifestations associated with the event, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the event
	* @param manifestations the manifestations to be associated with the event
	*/
	public static void setManifestations(long pk,
		List<eu.strasbourg.service.agenda.model.Manifestation> manifestations) {
		getPersistence().setManifestations(pk, manifestations);
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static EventPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EventPersistence, EventPersistence> _serviceTracker =
		ServiceTrackerFactory.open(EventPersistence.class);
}