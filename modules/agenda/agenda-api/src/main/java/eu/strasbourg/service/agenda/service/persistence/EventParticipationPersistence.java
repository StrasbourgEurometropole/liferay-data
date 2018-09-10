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

import eu.strasbourg.service.agenda.exception.NoSuchEventParticipationException;
import eu.strasbourg.service.agenda.model.EventParticipation;

/**
 * The persistence interface for the event participation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.agenda.service.persistence.impl.EventParticipationPersistenceImpl
 * @see EventParticipationUtil
 * @generated
 */
@ProviderType
public interface EventParticipationPersistence extends BasePersistence<EventParticipation> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EventParticipationUtil} to access the event participation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the event participations where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the matching event participations
	*/
	public java.util.List<EventParticipation> findByPublikUserId(
		java.lang.String publikUserId);

	/**
	* Returns a range of all the event participations where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of event participations
	* @param end the upper bound of the range of event participations (not inclusive)
	* @return the range of matching event participations
	*/
	public java.util.List<EventParticipation> findByPublikUserId(
		java.lang.String publikUserId, int start, int end);

	/**
	* Returns an ordered range of all the event participations where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of event participations
	* @param end the upper bound of the range of event participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching event participations
	*/
	public java.util.List<EventParticipation> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventParticipation> orderByComparator);

	/**
	* Returns an ordered range of all the event participations where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of event participations
	* @param end the upper bound of the range of event participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching event participations
	*/
	public java.util.List<EventParticipation> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventParticipation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first event participation in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event participation
	* @throws NoSuchEventParticipationException if a matching event participation could not be found
	*/
	public EventParticipation findByPublikUserId_First(
		java.lang.String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<EventParticipation> orderByComparator)
		throws NoSuchEventParticipationException;

	/**
	* Returns the first event participation in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event participation, or <code>null</code> if a matching event participation could not be found
	*/
	public EventParticipation fetchByPublikUserId_First(
		java.lang.String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<EventParticipation> orderByComparator);

	/**
	* Returns the last event participation in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event participation
	* @throws NoSuchEventParticipationException if a matching event participation could not be found
	*/
	public EventParticipation findByPublikUserId_Last(
		java.lang.String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<EventParticipation> orderByComparator)
		throws NoSuchEventParticipationException;

	/**
	* Returns the last event participation in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event participation, or <code>null</code> if a matching event participation could not be found
	*/
	public EventParticipation fetchByPublikUserId_Last(
		java.lang.String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<EventParticipation> orderByComparator);

	/**
	* Returns the event participations before and after the current event participation in the ordered set where publikUserId = &#63;.
	*
	* @param eventParticipationId the primary key of the current event participation
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event participation
	* @throws NoSuchEventParticipationException if a event participation with the primary key could not be found
	*/
	public EventParticipation[] findByPublikUserId_PrevAndNext(
		long eventParticipationId, java.lang.String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<EventParticipation> orderByComparator)
		throws NoSuchEventParticipationException;

	/**
	* Removes all the event participations where publikUserId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	*/
	public void removeByPublikUserId(java.lang.String publikUserId);

	/**
	* Returns the number of event participations where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the number of matching event participations
	*/
	public int countByPublikUserId(java.lang.String publikUserId);

	/**
	* Returns all the event participations where eventId = &#63;.
	*
	* @param eventId the event ID
	* @return the matching event participations
	*/
	public java.util.List<EventParticipation> findByEventId(long eventId);

	/**
	* Returns a range of all the event participations where eventId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param eventId the event ID
	* @param start the lower bound of the range of event participations
	* @param end the upper bound of the range of event participations (not inclusive)
	* @return the range of matching event participations
	*/
	public java.util.List<EventParticipation> findByEventId(long eventId,
		int start, int end);

	/**
	* Returns an ordered range of all the event participations where eventId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param eventId the event ID
	* @param start the lower bound of the range of event participations
	* @param end the upper bound of the range of event participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching event participations
	*/
	public java.util.List<EventParticipation> findByEventId(long eventId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventParticipation> orderByComparator);

	/**
	* Returns an ordered range of all the event participations where eventId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param eventId the event ID
	* @param start the lower bound of the range of event participations
	* @param end the upper bound of the range of event participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching event participations
	*/
	public java.util.List<EventParticipation> findByEventId(long eventId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventParticipation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first event participation in the ordered set where eventId = &#63;.
	*
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event participation
	* @throws NoSuchEventParticipationException if a matching event participation could not be found
	*/
	public EventParticipation findByEventId_First(long eventId,
		com.liferay.portal.kernel.util.OrderByComparator<EventParticipation> orderByComparator)
		throws NoSuchEventParticipationException;

	/**
	* Returns the first event participation in the ordered set where eventId = &#63;.
	*
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event participation, or <code>null</code> if a matching event participation could not be found
	*/
	public EventParticipation fetchByEventId_First(long eventId,
		com.liferay.portal.kernel.util.OrderByComparator<EventParticipation> orderByComparator);

	/**
	* Returns the last event participation in the ordered set where eventId = &#63;.
	*
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event participation
	* @throws NoSuchEventParticipationException if a matching event participation could not be found
	*/
	public EventParticipation findByEventId_Last(long eventId,
		com.liferay.portal.kernel.util.OrderByComparator<EventParticipation> orderByComparator)
		throws NoSuchEventParticipationException;

	/**
	* Returns the last event participation in the ordered set where eventId = &#63;.
	*
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event participation, or <code>null</code> if a matching event participation could not be found
	*/
	public EventParticipation fetchByEventId_Last(long eventId,
		com.liferay.portal.kernel.util.OrderByComparator<EventParticipation> orderByComparator);

	/**
	* Returns the event participations before and after the current event participation in the ordered set where eventId = &#63;.
	*
	* @param eventParticipationId the primary key of the current event participation
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event participation
	* @throws NoSuchEventParticipationException if a event participation with the primary key could not be found
	*/
	public EventParticipation[] findByEventId_PrevAndNext(
		long eventParticipationId, long eventId,
		com.liferay.portal.kernel.util.OrderByComparator<EventParticipation> orderByComparator)
		throws NoSuchEventParticipationException;

	/**
	* Removes all the event participations where eventId = &#63; from the database.
	*
	* @param eventId the event ID
	*/
	public void removeByEventId(long eventId);

	/**
	* Returns the number of event participations where eventId = &#63;.
	*
	* @param eventId the event ID
	* @return the number of matching event participations
	*/
	public int countByEventId(long eventId);

	/**
	* Returns the event participation where publikUserId = &#63; and eventId = &#63; or throws a {@link NoSuchEventParticipationException} if it could not be found.
	*
	* @param publikUserId the publik user ID
	* @param eventId the event ID
	* @return the matching event participation
	* @throws NoSuchEventParticipationException if a matching event participation could not be found
	*/
	public EventParticipation findByPublikUserIdAndEventId(
		java.lang.String publikUserId, long eventId)
		throws NoSuchEventParticipationException;

	/**
	* Returns the event participation where publikUserId = &#63; and eventId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param publikUserId the publik user ID
	* @param eventId the event ID
	* @return the matching event participation, or <code>null</code> if a matching event participation could not be found
	*/
	public EventParticipation fetchByPublikUserIdAndEventId(
		java.lang.String publikUserId, long eventId);

	/**
	* Returns the event participation where publikUserId = &#63; and eventId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param publikUserId the publik user ID
	* @param eventId the event ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching event participation, or <code>null</code> if a matching event participation could not be found
	*/
	public EventParticipation fetchByPublikUserIdAndEventId(
		java.lang.String publikUserId, long eventId, boolean retrieveFromCache);

	/**
	* Removes the event participation where publikUserId = &#63; and eventId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	* @param eventId the event ID
	* @return the event participation that was removed
	*/
	public EventParticipation removeByPublikUserIdAndEventId(
		java.lang.String publikUserId, long eventId)
		throws NoSuchEventParticipationException;

	/**
	* Returns the number of event participations where publikUserId = &#63; and eventId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param eventId the event ID
	* @return the number of matching event participations
	*/
	public int countByPublikUserIdAndEventId(java.lang.String publikUserId,
		long eventId);

	/**
	* Caches the event participation in the entity cache if it is enabled.
	*
	* @param eventParticipation the event participation
	*/
	public void cacheResult(EventParticipation eventParticipation);

	/**
	* Caches the event participations in the entity cache if it is enabled.
	*
	* @param eventParticipations the event participations
	*/
	public void cacheResult(
		java.util.List<EventParticipation> eventParticipations);

	/**
	* Creates a new event participation with the primary key. Does not add the event participation to the database.
	*
	* @param eventParticipationId the primary key for the new event participation
	* @return the new event participation
	*/
	public EventParticipation create(long eventParticipationId);

	/**
	* Removes the event participation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventParticipationId the primary key of the event participation
	* @return the event participation that was removed
	* @throws NoSuchEventParticipationException if a event participation with the primary key could not be found
	*/
	public EventParticipation remove(long eventParticipationId)
		throws NoSuchEventParticipationException;

	public EventParticipation updateImpl(EventParticipation eventParticipation);

	/**
	* Returns the event participation with the primary key or throws a {@link NoSuchEventParticipationException} if it could not be found.
	*
	* @param eventParticipationId the primary key of the event participation
	* @return the event participation
	* @throws NoSuchEventParticipationException if a event participation with the primary key could not be found
	*/
	public EventParticipation findByPrimaryKey(long eventParticipationId)
		throws NoSuchEventParticipationException;

	/**
	* Returns the event participation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param eventParticipationId the primary key of the event participation
	* @return the event participation, or <code>null</code> if a event participation with the primary key could not be found
	*/
	public EventParticipation fetchByPrimaryKey(long eventParticipationId);

	@Override
	public java.util.Map<java.io.Serializable, EventParticipation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the event participations.
	*
	* @return the event participations
	*/
	public java.util.List<EventParticipation> findAll();

	/**
	* Returns a range of all the event participations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of event participations
	* @param end the upper bound of the range of event participations (not inclusive)
	* @return the range of event participations
	*/
	public java.util.List<EventParticipation> findAll(int start, int end);

	/**
	* Returns an ordered range of all the event participations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of event participations
	* @param end the upper bound of the range of event participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of event participations
	*/
	public java.util.List<EventParticipation> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventParticipation> orderByComparator);

	/**
	* Returns an ordered range of all the event participations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of event participations
	* @param end the upper bound of the range of event participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of event participations
	*/
	public java.util.List<EventParticipation> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EventParticipation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the event participations from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of event participations.
	*
	* @return the number of event participations
	*/
	public int countAll();
}