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

package eu.strasbourg.service.project.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.project.model.Participation;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the participation service. This utility wraps {@link eu.strasbourg.service.project.service.persistence.impl.ParticipationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see ParticipationPersistence
 * @see eu.strasbourg.service.project.service.persistence.impl.ParticipationPersistenceImpl
 * @generated
 */
@ProviderType
public class ParticipationUtil {
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
	public static void clearCache(Participation participation) {
		getPersistence().clearCache(participation);
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
	public static List<Participation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Participation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Participation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Participation> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Participation update(Participation participation) {
		return getPersistence().update(participation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Participation update(Participation participation,
		ServiceContext serviceContext) {
		return getPersistence().update(participation, serviceContext);
	}

	/**
	* Returns all the participations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching participations
	*/
	public static List<Participation> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the participations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @return the range of matching participations
	*/
	public static List<Participation> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the participations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching participations
	*/
	public static List<Participation> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<Participation> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the participations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching participations
	*/
	public static List<Participation> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<Participation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first participation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching participation
	* @throws NoSuchParticipationException if a matching participation could not be found
	*/
	public static Participation findByUuid_First(java.lang.String uuid,
		OrderByComparator<Participation> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchParticipationException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first participation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public static Participation fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Participation> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last participation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching participation
	* @throws NoSuchParticipationException if a matching participation could not be found
	*/
	public static Participation findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Participation> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchParticipationException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last participation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public static Participation fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Participation> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the participations before and after the current participation in the ordered set where uuid = &#63;.
	*
	* @param participationId the primary key of the current participation
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next participation
	* @throws NoSuchParticipationException if a participation with the primary key could not be found
	*/
	public static Participation[] findByUuid_PrevAndNext(long participationId,
		java.lang.String uuid,
		OrderByComparator<Participation> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchParticipationException {
		return getPersistence()
				   .findByUuid_PrevAndNext(participationId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the participations where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of participations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching participations
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the participation where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchParticipationException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching participation
	* @throws NoSuchParticipationException if a matching participation could not be found
	*/
	public static Participation findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.project.exception.NoSuchParticipationException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the participation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public static Participation fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the participation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public static Participation fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the participation where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the participation that was removed
	*/
	public static Participation removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.project.exception.NoSuchParticipationException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of participations where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching participations
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the participations where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching participations
	*/
	public static List<Participation> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the participations where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @return the range of matching participations
	*/
	public static List<Participation> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the participations where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching participations
	*/
	public static List<Participation> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Participation> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the participations where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching participations
	*/
	public static List<Participation> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Participation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first participation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching participation
	* @throws NoSuchParticipationException if a matching participation could not be found
	*/
	public static Participation findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Participation> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchParticipationException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first participation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public static Participation fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Participation> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last participation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching participation
	* @throws NoSuchParticipationException if a matching participation could not be found
	*/
	public static Participation findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Participation> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchParticipationException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last participation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public static Participation fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Participation> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the participations before and after the current participation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param participationId the primary key of the current participation
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next participation
	* @throws NoSuchParticipationException if a participation with the primary key could not be found
	*/
	public static Participation[] findByUuid_C_PrevAndNext(
		long participationId, java.lang.String uuid, long companyId,
		OrderByComparator<Participation> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchParticipationException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(participationId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the participations where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of participations where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching participations
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the participations where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching participations
	*/
	public static List<Participation> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the participations where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @return the range of matching participations
	*/
	public static List<Participation> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the participations where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching participations
	*/
	public static List<Participation> findByGroupId(long groupId, int start,
		int end, OrderByComparator<Participation> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the participations where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching participations
	*/
	public static List<Participation> findByGroupId(long groupId, int start,
		int end, OrderByComparator<Participation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first participation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching participation
	* @throws NoSuchParticipationException if a matching participation could not be found
	*/
	public static Participation findByGroupId_First(long groupId,
		OrderByComparator<Participation> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchParticipationException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first participation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public static Participation fetchByGroupId_First(long groupId,
		OrderByComparator<Participation> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last participation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching participation
	* @throws NoSuchParticipationException if a matching participation could not be found
	*/
	public static Participation findByGroupId_Last(long groupId,
		OrderByComparator<Participation> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchParticipationException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last participation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public static Participation fetchByGroupId_Last(long groupId,
		OrderByComparator<Participation> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the participations before and after the current participation in the ordered set where groupId = &#63;.
	*
	* @param participationId the primary key of the current participation
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next participation
	* @throws NoSuchParticipationException if a participation with the primary key could not be found
	*/
	public static Participation[] findByGroupId_PrevAndNext(
		long participationId, long groupId,
		OrderByComparator<Participation> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchParticipationException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(participationId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the participations where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of participations where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching participations
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the participations where status = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param groupId the group ID
	* @return the matching participations
	*/
	public static List<Participation> findByStatusAndGroupId(int status,
		long groupId) {
		return getPersistence().findByStatusAndGroupId(status, groupId);
	}

	/**
	* Returns a range of all the participations where status = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param groupId the group ID
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @return the range of matching participations
	*/
	public static List<Participation> findByStatusAndGroupId(int status,
		long groupId, int start, int end) {
		return getPersistence()
				   .findByStatusAndGroupId(status, groupId, start, end);
	}

	/**
	* Returns an ordered range of all the participations where status = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param groupId the group ID
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching participations
	*/
	public static List<Participation> findByStatusAndGroupId(int status,
		long groupId, int start, int end,
		OrderByComparator<Participation> orderByComparator) {
		return getPersistence()
				   .findByStatusAndGroupId(status, groupId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the participations where status = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param groupId the group ID
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching participations
	*/
	public static List<Participation> findByStatusAndGroupId(int status,
		long groupId, int start, int end,
		OrderByComparator<Participation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByStatusAndGroupId(status, groupId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first participation in the ordered set where status = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching participation
	* @throws NoSuchParticipationException if a matching participation could not be found
	*/
	public static Participation findByStatusAndGroupId_First(int status,
		long groupId, OrderByComparator<Participation> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchParticipationException {
		return getPersistence()
				   .findByStatusAndGroupId_First(status, groupId,
			orderByComparator);
	}

	/**
	* Returns the first participation in the ordered set where status = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public static Participation fetchByStatusAndGroupId_First(int status,
		long groupId, OrderByComparator<Participation> orderByComparator) {
		return getPersistence()
				   .fetchByStatusAndGroupId_First(status, groupId,
			orderByComparator);
	}

	/**
	* Returns the last participation in the ordered set where status = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching participation
	* @throws NoSuchParticipationException if a matching participation could not be found
	*/
	public static Participation findByStatusAndGroupId_Last(int status,
		long groupId, OrderByComparator<Participation> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchParticipationException {
		return getPersistence()
				   .findByStatusAndGroupId_Last(status, groupId,
			orderByComparator);
	}

	/**
	* Returns the last participation in the ordered set where status = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public static Participation fetchByStatusAndGroupId_Last(int status,
		long groupId, OrderByComparator<Participation> orderByComparator) {
		return getPersistence()
				   .fetchByStatusAndGroupId_Last(status, groupId,
			orderByComparator);
	}

	/**
	* Returns the participations before and after the current participation in the ordered set where status = &#63; and groupId = &#63;.
	*
	* @param participationId the primary key of the current participation
	* @param status the status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next participation
	* @throws NoSuchParticipationException if a participation with the primary key could not be found
	*/
	public static Participation[] findByStatusAndGroupId_PrevAndNext(
		long participationId, int status, long groupId,
		OrderByComparator<Participation> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchParticipationException {
		return getPersistence()
				   .findByStatusAndGroupId_PrevAndNext(participationId, status,
			groupId, orderByComparator);
	}

	/**
	* Removes all the participations where status = &#63; and groupId = &#63; from the database.
	*
	* @param status the status
	* @param groupId the group ID
	*/
	public static void removeByStatusAndGroupId(int status, long groupId) {
		getPersistence().removeByStatusAndGroupId(status, groupId);
	}

	/**
	* Returns the number of participations where status = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param groupId the group ID
	* @return the number of matching participations
	*/
	public static int countByStatusAndGroupId(int status, long groupId) {
		return getPersistence().countByStatusAndGroupId(status, groupId);
	}

	/**
	* Caches the participation in the entity cache if it is enabled.
	*
	* @param participation the participation
	*/
	public static void cacheResult(Participation participation) {
		getPersistence().cacheResult(participation);
	}

	/**
	* Caches the participations in the entity cache if it is enabled.
	*
	* @param participations the participations
	*/
	public static void cacheResult(List<Participation> participations) {
		getPersistence().cacheResult(participations);
	}

	/**
	* Creates a new participation with the primary key. Does not add the participation to the database.
	*
	* @param participationId the primary key for the new participation
	* @return the new participation
	*/
	public static Participation create(long participationId) {
		return getPersistence().create(participationId);
	}

	/**
	* Removes the participation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param participationId the primary key of the participation
	* @return the participation that was removed
	* @throws NoSuchParticipationException if a participation with the primary key could not be found
	*/
	public static Participation remove(long participationId)
		throws eu.strasbourg.service.project.exception.NoSuchParticipationException {
		return getPersistence().remove(participationId);
	}

	public static Participation updateImpl(Participation participation) {
		return getPersistence().updateImpl(participation);
	}

	/**
	* Returns the participation with the primary key or throws a {@link NoSuchParticipationException} if it could not be found.
	*
	* @param participationId the primary key of the participation
	* @return the participation
	* @throws NoSuchParticipationException if a participation with the primary key could not be found
	*/
	public static Participation findByPrimaryKey(long participationId)
		throws eu.strasbourg.service.project.exception.NoSuchParticipationException {
		return getPersistence().findByPrimaryKey(participationId);
	}

	/**
	* Returns the participation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param participationId the primary key of the participation
	* @return the participation, or <code>null</code> if a participation with the primary key could not be found
	*/
	public static Participation fetchByPrimaryKey(long participationId) {
		return getPersistence().fetchByPrimaryKey(participationId);
	}

	public static java.util.Map<java.io.Serializable, Participation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the participations.
	*
	* @return the participations
	*/
	public static List<Participation> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the participations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @return the range of participations
	*/
	public static List<Participation> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the participations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of participations
	*/
	public static List<Participation> findAll(int start, int end,
		OrderByComparator<Participation> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the participations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of participations
	*/
	public static List<Participation> findAll(int start, int end,
		OrderByComparator<Participation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the participations from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of participations.
	*
	* @return the number of participations
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ParticipationPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ParticipationPersistence, ParticipationPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ParticipationPersistence.class);
}