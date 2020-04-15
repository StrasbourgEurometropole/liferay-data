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

package eu.strasbourg.service.council.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the session service. This utility wraps {@link eu.strasbourg.service.council.service.persistence.impl.SessionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SessionPersistence
 * @see eu.strasbourg.service.council.service.persistence.impl.SessionPersistenceImpl
 * @generated
 */
@ProviderType
public class SessionUtil {
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
	public static void clearCache(Session session) {
		getPersistence().clearCache(session);
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
	public static List<Session> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Session> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Session> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Session> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Session update(Session session) {
		return getPersistence().update(session);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Session update(Session session, ServiceContext serviceContext) {
		return getPersistence().update(session, serviceContext);
	}

	/**
	* Returns all the sessions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sessions
	*/
	public static List<com.liferay.portal.kernel.dao.orm.Session> findByUuid(
		java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the sessions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sessions
	* @param end the upper bound of the range of sessions (not inclusive)
	* @return the range of matching sessions
	*/
	public static List<com.liferay.portal.kernel.dao.orm.Session> findByUuid(
		java.lang.String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the sessions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sessions
	* @param end the upper bound of the range of sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sessions
	*/
	public static List<com.liferay.portal.kernel.dao.orm.Session> findByUuid(
		java.lang.String uuid, int start, int end,
		OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sessions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of sessions
	* @param end the upper bound of the range of sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sessions
	*/
	public static List<com.liferay.portal.kernel.dao.orm.Session> findByUuid(
		java.lang.String uuid, int start, int end,
		OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first session in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching session
	* @throws NoSuchSessionException if a matching session could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session findByUuid_First(
		java.lang.String uuid,
		OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchSessionException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first session in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching session, or <code>null</code> if a matching session could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session fetchByUuid_First(
		java.lang.String uuid,
		OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last session in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching session
	* @throws NoSuchSessionException if a matching session could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session findByUuid_Last(
		java.lang.String uuid,
		OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchSessionException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last session in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching session, or <code>null</code> if a matching session could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session fetchByUuid_Last(
		java.lang.String uuid,
		OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the sessions before and after the current session in the ordered set where uuid = &#63;.
	*
	* @param sessionId the primary key of the current session
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next session
	* @throws NoSuchSessionException if a session with the primary key could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session[] findByUuid_PrevAndNext(
		long sessionId, java.lang.String uuid,
		OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchSessionException {
		return getPersistence()
				   .findByUuid_PrevAndNext(sessionId, uuid, orderByComparator);
	}

	/**
	* Removes all the sessions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of sessions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sessions
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the session where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSessionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching session
	* @throws NoSuchSessionException if a matching session could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session findByUUID_G(
		java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.council.exception.NoSuchSessionException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the session where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching session, or <code>null</code> if a matching session could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session fetchByUUID_G(
		java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the session where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching session, or <code>null</code> if a matching session could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the session where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the session that was removed
	*/
	public static com.liferay.portal.kernel.dao.orm.Session removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.council.exception.NoSuchSessionException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of sessions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching sessions
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the sessions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching sessions
	*/
	public static List<com.liferay.portal.kernel.dao.orm.Session> findByUuid_C(
		java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the sessions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sessions
	* @param end the upper bound of the range of sessions (not inclusive)
	* @return the range of matching sessions
	*/
	public static List<com.liferay.portal.kernel.dao.orm.Session> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the sessions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sessions
	* @param end the upper bound of the range of sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching sessions
	*/
	public static List<com.liferay.portal.kernel.dao.orm.Session> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sessions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of sessions
	* @param end the upper bound of the range of sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching sessions
	*/
	public static List<com.liferay.portal.kernel.dao.orm.Session> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first session in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching session
	* @throws NoSuchSessionException if a matching session could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session findByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchSessionException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first session in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching session, or <code>null</code> if a matching session could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last session in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching session
	* @throws NoSuchSessionException if a matching session could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchSessionException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last session in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching session, or <code>null</code> if a matching session could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the sessions before and after the current session in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param sessionId the primary key of the current session
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next session
	* @throws NoSuchSessionException if a session with the primary key could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session[] findByUuid_C_PrevAndNext(
		long sessionId, java.lang.String uuid, long companyId,
		OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchSessionException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(sessionId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the sessions where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of sessions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching sessions
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the session where sessionId = &#63; or throws a {@link NoSuchSessionException} if it could not be found.
	*
	* @param sessionId the session ID
	* @return the matching session
	* @throws NoSuchSessionException if a matching session could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session findBySessionId(
		long sessionId)
		throws eu.strasbourg.service.council.exception.NoSuchSessionException {
		return getPersistence().findBySessionId(sessionId);
	}

	/**
	* Returns the session where sessionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param sessionId the session ID
	* @return the matching session, or <code>null</code> if a matching session could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session fetchBySessionId(
		long sessionId) {
		return getPersistence().fetchBySessionId(sessionId);
	}

	/**
	* Returns the session where sessionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param sessionId the session ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching session, or <code>null</code> if a matching session could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session fetchBySessionId(
		long sessionId, boolean retrieveFromCache) {
		return getPersistence().fetchBySessionId(sessionId, retrieveFromCache);
	}

	/**
	* Removes the session where sessionId = &#63; from the database.
	*
	* @param sessionId the session ID
	* @return the session that was removed
	*/
	public static com.liferay.portal.kernel.dao.orm.Session removeBySessionId(
		long sessionId)
		throws eu.strasbourg.service.council.exception.NoSuchSessionException {
		return getPersistence().removeBySessionId(sessionId);
	}

	/**
	* Returns the number of sessions where sessionId = &#63;.
	*
	* @param sessionId the session ID
	* @return the number of matching sessions
	*/
	public static int countBySessionId(long sessionId) {
		return getPersistence().countBySessionId(sessionId);
	}

	/**
	* Caches the session in the entity cache if it is enabled.
	*
	* @param session the session
	*/
	public static void cacheResult(
		com.liferay.portal.kernel.dao.orm.Session session) {
		getPersistence().cacheResult(session);
	}

	/**
	* Caches the sessions in the entity cache if it is enabled.
	*
	* @param sessions the sessions
	*/
	public static void cacheResult(
		List<com.liferay.portal.kernel.dao.orm.Session> sessions) {
		getPersistence().cacheResult(sessions);
	}

	/**
	* Creates a new session with the primary key. Does not add the session to the database.
	*
	* @param sessionId the primary key for the new session
	* @return the new session
	*/
	public static com.liferay.portal.kernel.dao.orm.Session create(
		long sessionId) {
		return getPersistence().create(sessionId);
	}

	/**
	* Removes the session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param sessionId the primary key of the session
	* @return the session that was removed
	* @throws NoSuchSessionException if a session with the primary key could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session remove(
		long sessionId)
		throws eu.strasbourg.service.council.exception.NoSuchSessionException {
		return getPersistence().remove(sessionId);
	}

	public static com.liferay.portal.kernel.dao.orm.Session updateImpl(
		com.liferay.portal.kernel.dao.orm.Session session) {
		return getPersistence().updateImpl(session);
	}

	/**
	* Returns the session with the primary key or throws a {@link NoSuchSessionException} if it could not be found.
	*
	* @param sessionId the primary key of the session
	* @return the session
	* @throws NoSuchSessionException if a session with the primary key could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session findByPrimaryKey(
		long sessionId)
		throws eu.strasbourg.service.council.exception.NoSuchSessionException {
		return getPersistence().findByPrimaryKey(sessionId);
	}

	/**
	* Returns the session with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param sessionId the primary key of the session
	* @return the session, or <code>null</code> if a session with the primary key could not be found
	*/
	public static com.liferay.portal.kernel.dao.orm.Session fetchByPrimaryKey(
		long sessionId) {
		return getPersistence().fetchByPrimaryKey(sessionId);
	}

	public static java.util.Map<java.io.Serializable, com.liferay.portal.kernel.dao.orm.Session> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the sessions.
	*
	* @return the sessions
	*/
	public static List<com.liferay.portal.kernel.dao.orm.Session> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sessions
	* @param end the upper bound of the range of sessions (not inclusive)
	* @return the range of sessions
	*/
	public static List<com.liferay.portal.kernel.dao.orm.Session> findAll(
		int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sessions
	* @param end the upper bound of the range of sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sessions
	*/
	public static List<com.liferay.portal.kernel.dao.orm.Session> findAll(
		int start, int end,
		OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sessions
	* @param end the upper bound of the range of sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sessions
	*/
	public static List<com.liferay.portal.kernel.dao.orm.Session> findAll(
		int start, int end,
		OrderByComparator<com.liferay.portal.kernel.dao.orm.Session> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the sessions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of sessions.
	*
	* @return the number of sessions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SessionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SessionPersistence, SessionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(SessionPersistence.class);
}