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

import eu.strasbourg.service.council.model.CouncilSession;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the council session service. This utility wraps {@link eu.strasbourg.service.council.service.persistence.impl.CouncilSessionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CouncilSessionPersistence
 * @see eu.strasbourg.service.council.service.persistence.impl.CouncilSessionPersistenceImpl
 * @generated
 */
@ProviderType
public class CouncilSessionUtil {
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
	public static void clearCache(CouncilSession councilSession) {
		getPersistence().clearCache(councilSession);
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
	public static List<CouncilSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CouncilSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CouncilSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CouncilSession> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CouncilSession update(CouncilSession councilSession) {
		return getPersistence().update(councilSession);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CouncilSession update(CouncilSession councilSession,
		ServiceContext serviceContext) {
		return getPersistence().update(councilSession, serviceContext);
	}

	/**
	* Returns all the council sessions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching council sessions
	*/
	public static List<CouncilSession> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the council sessions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @return the range of matching council sessions
	*/
	public static List<CouncilSession> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the council sessions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching council sessions
	*/
	public static List<CouncilSession> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<CouncilSession> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the council sessions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching council sessions
	*/
	public static List<CouncilSession> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<CouncilSession> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first council session in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching council session
	* @throws NoSuchCouncilSessionException if a matching council session could not be found
	*/
	public static CouncilSession findByUuid_First(java.lang.String uuid,
		OrderByComparator<CouncilSession> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchCouncilSessionException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first council session in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching council session, or <code>null</code> if a matching council session could not be found
	*/
	public static CouncilSession fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<CouncilSession> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last council session in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching council session
	* @throws NoSuchCouncilSessionException if a matching council session could not be found
	*/
	public static CouncilSession findByUuid_Last(java.lang.String uuid,
		OrderByComparator<CouncilSession> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchCouncilSessionException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last council session in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching council session, or <code>null</code> if a matching council session could not be found
	*/
	public static CouncilSession fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<CouncilSession> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the council sessions before and after the current council session in the ordered set where uuid = &#63;.
	*
	* @param councilSessionId the primary key of the current council session
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next council session
	* @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	*/
	public static CouncilSession[] findByUuid_PrevAndNext(
		long councilSessionId, java.lang.String uuid,
		OrderByComparator<CouncilSession> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchCouncilSessionException {
		return getPersistence()
				   .findByUuid_PrevAndNext(councilSessionId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the council sessions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of council sessions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching council sessions
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the council session where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCouncilSessionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching council session
	* @throws NoSuchCouncilSessionException if a matching council session could not be found
	*/
	public static CouncilSession findByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.council.exception.NoSuchCouncilSessionException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the council session where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching council session, or <code>null</code> if a matching council session could not be found
	*/
	public static CouncilSession fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the council session where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching council session, or <code>null</code> if a matching council session could not be found
	*/
	public static CouncilSession fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the council session where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the council session that was removed
	*/
	public static CouncilSession removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.council.exception.NoSuchCouncilSessionException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of council sessions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching council sessions
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the council sessions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching council sessions
	*/
	public static List<CouncilSession> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the council sessions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @return the range of matching council sessions
	*/
	public static List<CouncilSession> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the council sessions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching council sessions
	*/
	public static List<CouncilSession> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<CouncilSession> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the council sessions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching council sessions
	*/
	public static List<CouncilSession> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<CouncilSession> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first council session in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching council session
	* @throws NoSuchCouncilSessionException if a matching council session could not be found
	*/
	public static CouncilSession findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<CouncilSession> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchCouncilSessionException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first council session in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching council session, or <code>null</code> if a matching council session could not be found
	*/
	public static CouncilSession fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<CouncilSession> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last council session in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching council session
	* @throws NoSuchCouncilSessionException if a matching council session could not be found
	*/
	public static CouncilSession findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<CouncilSession> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchCouncilSessionException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last council session in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching council session, or <code>null</code> if a matching council session could not be found
	*/
	public static CouncilSession fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<CouncilSession> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the council sessions before and after the current council session in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param councilSessionId the primary key of the current council session
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next council session
	* @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	*/
	public static CouncilSession[] findByUuid_C_PrevAndNext(
		long councilSessionId, java.lang.String uuid, long companyId,
		OrderByComparator<CouncilSession> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchCouncilSessionException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(councilSessionId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the council sessions where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of council sessions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching council sessions
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the council sessions where date = &#63;.
	*
	* @param date the date
	* @return the matching council sessions
	*/
	public static List<CouncilSession> findByDate(Date date) {
		return getPersistence().findByDate(date);
	}

	/**
	* Returns a range of all the council sessions where date = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param date the date
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @return the range of matching council sessions
	*/
	public static List<CouncilSession> findByDate(Date date, int start, int end) {
		return getPersistence().findByDate(date, start, end);
	}

	/**
	* Returns an ordered range of all the council sessions where date = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param date the date
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching council sessions
	*/
	public static List<CouncilSession> findByDate(Date date, int start,
		int end, OrderByComparator<CouncilSession> orderByComparator) {
		return getPersistence().findByDate(date, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the council sessions where date = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param date the date
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching council sessions
	*/
	public static List<CouncilSession> findByDate(Date date, int start,
		int end, OrderByComparator<CouncilSession> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDate(date, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first council session in the ordered set where date = &#63;.
	*
	* @param date the date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching council session
	* @throws NoSuchCouncilSessionException if a matching council session could not be found
	*/
	public static CouncilSession findByDate_First(Date date,
		OrderByComparator<CouncilSession> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchCouncilSessionException {
		return getPersistence().findByDate_First(date, orderByComparator);
	}

	/**
	* Returns the first council session in the ordered set where date = &#63;.
	*
	* @param date the date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching council session, or <code>null</code> if a matching council session could not be found
	*/
	public static CouncilSession fetchByDate_First(Date date,
		OrderByComparator<CouncilSession> orderByComparator) {
		return getPersistence().fetchByDate_First(date, orderByComparator);
	}

	/**
	* Returns the last council session in the ordered set where date = &#63;.
	*
	* @param date the date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching council session
	* @throws NoSuchCouncilSessionException if a matching council session could not be found
	*/
	public static CouncilSession findByDate_Last(Date date,
		OrderByComparator<CouncilSession> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchCouncilSessionException {
		return getPersistence().findByDate_Last(date, orderByComparator);
	}

	/**
	* Returns the last council session in the ordered set where date = &#63;.
	*
	* @param date the date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching council session, or <code>null</code> if a matching council session could not be found
	*/
	public static CouncilSession fetchByDate_Last(Date date,
		OrderByComparator<CouncilSession> orderByComparator) {
		return getPersistence().fetchByDate_Last(date, orderByComparator);
	}

	/**
	* Returns the council sessions before and after the current council session in the ordered set where date = &#63;.
	*
	* @param councilSessionId the primary key of the current council session
	* @param date the date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next council session
	* @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	*/
	public static CouncilSession[] findByDate_PrevAndNext(
		long councilSessionId, Date date,
		OrderByComparator<CouncilSession> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchCouncilSessionException {
		return getPersistence()
				   .findByDate_PrevAndNext(councilSessionId, date,
			orderByComparator);
	}

	/**
	* Removes all the council sessions where date = &#63; from the database.
	*
	* @param date the date
	*/
	public static void removeByDate(Date date) {
		getPersistence().removeByDate(date);
	}

	/**
	* Returns the number of council sessions where date = &#63;.
	*
	* @param date the date
	* @return the number of matching council sessions
	*/
	public static int countByDate(Date date) {
		return getPersistence().countByDate(date);
	}

	/**
	* Caches the council session in the entity cache if it is enabled.
	*
	* @param councilSession the council session
	*/
	public static void cacheResult(CouncilSession councilSession) {
		getPersistence().cacheResult(councilSession);
	}

	/**
	* Caches the council sessions in the entity cache if it is enabled.
	*
	* @param councilSessions the council sessions
	*/
	public static void cacheResult(List<CouncilSession> councilSessions) {
		getPersistence().cacheResult(councilSessions);
	}

	/**
	* Creates a new council session with the primary key. Does not add the council session to the database.
	*
	* @param councilSessionId the primary key for the new council session
	* @return the new council session
	*/
	public static CouncilSession create(long councilSessionId) {
		return getPersistence().create(councilSessionId);
	}

	/**
	* Removes the council session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param councilSessionId the primary key of the council session
	* @return the council session that was removed
	* @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	*/
	public static CouncilSession remove(long councilSessionId)
		throws eu.strasbourg.service.council.exception.NoSuchCouncilSessionException {
		return getPersistence().remove(councilSessionId);
	}

	public static CouncilSession updateImpl(CouncilSession councilSession) {
		return getPersistence().updateImpl(councilSession);
	}

	/**
	* Returns the council session with the primary key or throws a {@link NoSuchCouncilSessionException} if it could not be found.
	*
	* @param councilSessionId the primary key of the council session
	* @return the council session
	* @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	*/
	public static CouncilSession findByPrimaryKey(long councilSessionId)
		throws eu.strasbourg.service.council.exception.NoSuchCouncilSessionException {
		return getPersistence().findByPrimaryKey(councilSessionId);
	}

	/**
	* Returns the council session with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param councilSessionId the primary key of the council session
	* @return the council session, or <code>null</code> if a council session with the primary key could not be found
	*/
	public static CouncilSession fetchByPrimaryKey(long councilSessionId) {
		return getPersistence().fetchByPrimaryKey(councilSessionId);
	}

	public static java.util.Map<java.io.Serializable, CouncilSession> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the council sessions.
	*
	* @return the council sessions
	*/
	public static List<CouncilSession> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the council sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @return the range of council sessions
	*/
	public static List<CouncilSession> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the council sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of council sessions
	*/
	public static List<CouncilSession> findAll(int start, int end,
		OrderByComparator<CouncilSession> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the council sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of council sessions
	*/
	public static List<CouncilSession> findAll(int start, int end,
		OrderByComparator<CouncilSession> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the council sessions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of council sessions.
	*
	* @return the number of council sessions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CouncilSessionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CouncilSessionPersistence, CouncilSessionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CouncilSessionPersistence.class);
}