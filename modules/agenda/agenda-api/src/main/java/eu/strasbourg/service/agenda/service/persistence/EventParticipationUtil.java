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

import eu.strasbourg.service.agenda.model.EventParticipation;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the event participation service. This utility wraps {@link eu.strasbourg.service.agenda.service.persistence.impl.EventParticipationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see EventParticipationPersistence
 * @see eu.strasbourg.service.agenda.service.persistence.impl.EventParticipationPersistenceImpl
 * @generated
 */
@ProviderType
public class EventParticipationUtil {
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
	public static void clearCache(EventParticipation eventParticipation) {
		getPersistence().clearCache(eventParticipation);
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
	public static List<EventParticipation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EventParticipation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EventParticipation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EventParticipation> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EventParticipation update(
		EventParticipation eventParticipation) {
		return getPersistence().update(eventParticipation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EventParticipation update(
		EventParticipation eventParticipation, ServiceContext serviceContext) {
		return getPersistence().update(eventParticipation, serviceContext);
	}

	/**
	* Returns all the event participations where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the matching event participations
	*/
	public static List<EventParticipation> findByPublikUserId(
		java.lang.String publikUserId) {
		return getPersistence().findByPublikUserId(publikUserId);
	}

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
	public static List<EventParticipation> findByPublikUserId(
		java.lang.String publikUserId, int start, int end) {
		return getPersistence().findByPublikUserId(publikUserId, start, end);
	}

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
	public static List<EventParticipation> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		OrderByComparator<EventParticipation> orderByComparator) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator);
	}

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
	public static List<EventParticipation> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		OrderByComparator<EventParticipation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first event participation in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event participation
	* @throws NoSuchEventParticipationException if a matching event participation could not be found
	*/
	public static EventParticipation findByPublikUserId_First(
		java.lang.String publikUserId,
		OrderByComparator<EventParticipation> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventParticipationException {
		return getPersistence()
				   .findByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the first event participation in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event participation, or <code>null</code> if a matching event participation could not be found
	*/
	public static EventParticipation fetchByPublikUserId_First(
		java.lang.String publikUserId,
		OrderByComparator<EventParticipation> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the last event participation in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event participation
	* @throws NoSuchEventParticipationException if a matching event participation could not be found
	*/
	public static EventParticipation findByPublikUserId_Last(
		java.lang.String publikUserId,
		OrderByComparator<EventParticipation> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventParticipationException {
		return getPersistence()
				   .findByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the last event participation in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event participation, or <code>null</code> if a matching event participation could not be found
	*/
	public static EventParticipation fetchByPublikUserId_Last(
		java.lang.String publikUserId,
		OrderByComparator<EventParticipation> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the event participations before and after the current event participation in the ordered set where publikUserId = &#63;.
	*
	* @param eventParticipationId the primary key of the current event participation
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event participation
	* @throws NoSuchEventParticipationException if a event participation with the primary key could not be found
	*/
	public static EventParticipation[] findByPublikUserId_PrevAndNext(
		long eventParticipationId, java.lang.String publikUserId,
		OrderByComparator<EventParticipation> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventParticipationException {
		return getPersistence()
				   .findByPublikUserId_PrevAndNext(eventParticipationId,
			publikUserId, orderByComparator);
	}

	/**
	* Removes all the event participations where publikUserId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	*/
	public static void removeByPublikUserId(java.lang.String publikUserId) {
		getPersistence().removeByPublikUserId(publikUserId);
	}

	/**
	* Returns the number of event participations where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the number of matching event participations
	*/
	public static int countByPublikUserId(java.lang.String publikUserId) {
		return getPersistence().countByPublikUserId(publikUserId);
	}

	/**
	* Returns all the event participations where eventId = &#63;.
	*
	* @param eventId the event ID
	* @return the matching event participations
	*/
	public static List<EventParticipation> findByEventId(long eventId) {
		return getPersistence().findByEventId(eventId);
	}

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
	public static List<EventParticipation> findByEventId(long eventId,
		int start, int end) {
		return getPersistence().findByEventId(eventId, start, end);
	}

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
	public static List<EventParticipation> findByEventId(long eventId,
		int start, int end,
		OrderByComparator<EventParticipation> orderByComparator) {
		return getPersistence()
				   .findByEventId(eventId, start, end, orderByComparator);
	}

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
	public static List<EventParticipation> findByEventId(long eventId,
		int start, int end,
		OrderByComparator<EventParticipation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByEventId(eventId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first event participation in the ordered set where eventId = &#63;.
	*
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event participation
	* @throws NoSuchEventParticipationException if a matching event participation could not be found
	*/
	public static EventParticipation findByEventId_First(long eventId,
		OrderByComparator<EventParticipation> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventParticipationException {
		return getPersistence().findByEventId_First(eventId, orderByComparator);
	}

	/**
	* Returns the first event participation in the ordered set where eventId = &#63;.
	*
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event participation, or <code>null</code> if a matching event participation could not be found
	*/
	public static EventParticipation fetchByEventId_First(long eventId,
		OrderByComparator<EventParticipation> orderByComparator) {
		return getPersistence().fetchByEventId_First(eventId, orderByComparator);
	}

	/**
	* Returns the last event participation in the ordered set where eventId = &#63;.
	*
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event participation
	* @throws NoSuchEventParticipationException if a matching event participation could not be found
	*/
	public static EventParticipation findByEventId_Last(long eventId,
		OrderByComparator<EventParticipation> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventParticipationException {
		return getPersistence().findByEventId_Last(eventId, orderByComparator);
	}

	/**
	* Returns the last event participation in the ordered set where eventId = &#63;.
	*
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event participation, or <code>null</code> if a matching event participation could not be found
	*/
	public static EventParticipation fetchByEventId_Last(long eventId,
		OrderByComparator<EventParticipation> orderByComparator) {
		return getPersistence().fetchByEventId_Last(eventId, orderByComparator);
	}

	/**
	* Returns the event participations before and after the current event participation in the ordered set where eventId = &#63;.
	*
	* @param eventParticipationId the primary key of the current event participation
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event participation
	* @throws NoSuchEventParticipationException if a event participation with the primary key could not be found
	*/
	public static EventParticipation[] findByEventId_PrevAndNext(
		long eventParticipationId, long eventId,
		OrderByComparator<EventParticipation> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventParticipationException {
		return getPersistence()
				   .findByEventId_PrevAndNext(eventParticipationId, eventId,
			orderByComparator);
	}

	/**
	* Removes all the event participations where eventId = &#63; from the database.
	*
	* @param eventId the event ID
	*/
	public static void removeByEventId(long eventId) {
		getPersistence().removeByEventId(eventId);
	}

	/**
	* Returns the number of event participations where eventId = &#63;.
	*
	* @param eventId the event ID
	* @return the number of matching event participations
	*/
	public static int countByEventId(long eventId) {
		return getPersistence().countByEventId(eventId);
	}

	/**
	* Returns the event participation where publikUserId = &#63; and eventId = &#63; or throws a {@link NoSuchEventParticipationException} if it could not be found.
	*
	* @param publikUserId the publik user ID
	* @param eventId the event ID
	* @return the matching event participation
	* @throws NoSuchEventParticipationException if a matching event participation could not be found
	*/
	public static EventParticipation findByPublikUserIdAndEventId(
		java.lang.String publikUserId, long eventId)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventParticipationException {
		return getPersistence()
				   .findByPublikUserIdAndEventId(publikUserId, eventId);
	}

	/**
	* Returns the event participation where publikUserId = &#63; and eventId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param publikUserId the publik user ID
	* @param eventId the event ID
	* @return the matching event participation, or <code>null</code> if a matching event participation could not be found
	*/
	public static EventParticipation fetchByPublikUserIdAndEventId(
		java.lang.String publikUserId, long eventId) {
		return getPersistence()
				   .fetchByPublikUserIdAndEventId(publikUserId, eventId);
	}

	/**
	* Returns the event participation where publikUserId = &#63; and eventId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param publikUserId the publik user ID
	* @param eventId the event ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching event participation, or <code>null</code> if a matching event participation could not be found
	*/
	public static EventParticipation fetchByPublikUserIdAndEventId(
		java.lang.String publikUserId, long eventId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByPublikUserIdAndEventId(publikUserId, eventId,
			retrieveFromCache);
	}

	/**
	* Removes the event participation where publikUserId = &#63; and eventId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	* @param eventId the event ID
	* @return the event participation that was removed
	*/
	public static EventParticipation removeByPublikUserIdAndEventId(
		java.lang.String publikUserId, long eventId)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventParticipationException {
		return getPersistence()
				   .removeByPublikUserIdAndEventId(publikUserId, eventId);
	}

	/**
	* Returns the number of event participations where publikUserId = &#63; and eventId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param eventId the event ID
	* @return the number of matching event participations
	*/
	public static int countByPublikUserIdAndEventId(
		java.lang.String publikUserId, long eventId) {
		return getPersistence()
				   .countByPublikUserIdAndEventId(publikUserId, eventId);
	}

	/**
	* Caches the event participation in the entity cache if it is enabled.
	*
	* @param eventParticipation the event participation
	*/
	public static void cacheResult(EventParticipation eventParticipation) {
		getPersistence().cacheResult(eventParticipation);
	}

	/**
	* Caches the event participations in the entity cache if it is enabled.
	*
	* @param eventParticipations the event participations
	*/
	public static void cacheResult(List<EventParticipation> eventParticipations) {
		getPersistence().cacheResult(eventParticipations);
	}

	/**
	* Creates a new event participation with the primary key. Does not add the event participation to the database.
	*
	* @param eventParticipationId the primary key for the new event participation
	* @return the new event participation
	*/
	public static EventParticipation create(long eventParticipationId) {
		return getPersistence().create(eventParticipationId);
	}

	/**
	* Removes the event participation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventParticipationId the primary key of the event participation
	* @return the event participation that was removed
	* @throws NoSuchEventParticipationException if a event participation with the primary key could not be found
	*/
	public static EventParticipation remove(long eventParticipationId)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventParticipationException {
		return getPersistence().remove(eventParticipationId);
	}

	public static EventParticipation updateImpl(
		EventParticipation eventParticipation) {
		return getPersistence().updateImpl(eventParticipation);
	}

	/**
	* Returns the event participation with the primary key or throws a {@link NoSuchEventParticipationException} if it could not be found.
	*
	* @param eventParticipationId the primary key of the event participation
	* @return the event participation
	* @throws NoSuchEventParticipationException if a event participation with the primary key could not be found
	*/
	public static EventParticipation findByPrimaryKey(long eventParticipationId)
		throws eu.strasbourg.service.agenda.exception.NoSuchEventParticipationException {
		return getPersistence().findByPrimaryKey(eventParticipationId);
	}

	/**
	* Returns the event participation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param eventParticipationId the primary key of the event participation
	* @return the event participation, or <code>null</code> if a event participation with the primary key could not be found
	*/
	public static EventParticipation fetchByPrimaryKey(
		long eventParticipationId) {
		return getPersistence().fetchByPrimaryKey(eventParticipationId);
	}

	public static java.util.Map<java.io.Serializable, EventParticipation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the event participations.
	*
	* @return the event participations
	*/
	public static List<EventParticipation> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<EventParticipation> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<EventParticipation> findAll(int start, int end,
		OrderByComparator<EventParticipation> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<EventParticipation> findAll(int start, int end,
		OrderByComparator<EventParticipation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the event participations from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of event participations.
	*
	* @return the number of event participations
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static EventParticipationPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EventParticipationPersistence, EventParticipationPersistence> _serviceTracker =
		ServiceTrackerFactory.open(EventParticipationPersistence.class);
}