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

import eu.strasbourg.service.project.model.Phase;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the phase service. This utility wraps {@link eu.strasbourg.service.project.service.persistence.impl.PhasePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see PhasePersistence
 * @see eu.strasbourg.service.project.service.persistence.impl.PhasePersistenceImpl
 * @generated
 */
@ProviderType
public class PhaseUtil {
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
	public static void clearCache(Phase phase) {
		getPersistence().clearCache(phase);
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
	public static List<Phase> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Phase> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Phase> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Phase> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Phase update(Phase phase) {
		return getPersistence().update(phase);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Phase update(Phase phase, ServiceContext serviceContext) {
		return getPersistence().update(phase, serviceContext);
	}

	/**
	* Returns all the phases where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching phases
	*/
	public static List<Phase> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the phases where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @return the range of matching phases
	*/
	public static List<Phase> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the phases where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching phases
	*/
	public static List<Phase> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Phase> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the phases where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching phases
	*/
	public static List<Phase> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Phase> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first phase in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public static Phase findByUuid_First(java.lang.String uuid,
		OrderByComparator<Phase> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPhaseException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first phase in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public static Phase fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Phase> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last phase in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public static Phase findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Phase> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPhaseException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last phase in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public static Phase fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Phase> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the phases before and after the current phase in the ordered set where uuid = &#63;.
	*
	* @param PhaseId the primary key of the current phase
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next phase
	* @throws NoSuchPhaseException if a phase with the primary key could not be found
	*/
	public static Phase[] findByUuid_PrevAndNext(long PhaseId,
		java.lang.String uuid, OrderByComparator<Phase> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPhaseException {
		return getPersistence()
				   .findByUuid_PrevAndNext(PhaseId, uuid, orderByComparator);
	}

	/**
	* Removes all the phases where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of phases where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching phases
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the phase where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPhaseException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public static Phase findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.project.exception.NoSuchPhaseException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the phase where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public static Phase fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the phase where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public static Phase fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the phase where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the phase that was removed
	*/
	public static Phase removeByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.project.exception.NoSuchPhaseException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of phases where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching phases
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the phases where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching phases
	*/
	public static List<Phase> findByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the phases where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @return the range of matching phases
	*/
	public static List<Phase> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the phases where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching phases
	*/
	public static List<Phase> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Phase> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the phases where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching phases
	*/
	public static List<Phase> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Phase> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first phase in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public static Phase findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Phase> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPhaseException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first phase in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public static Phase fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Phase> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last phase in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public static Phase findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Phase> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPhaseException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last phase in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public static Phase fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Phase> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the phases before and after the current phase in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param PhaseId the primary key of the current phase
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next phase
	* @throws NoSuchPhaseException if a phase with the primary key could not be found
	*/
	public static Phase[] findByUuid_C_PrevAndNext(long PhaseId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Phase> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPhaseException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(PhaseId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the phases where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of phases where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching phases
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the phases where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching phases
	*/
	public static List<Phase> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the phases where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @return the range of matching phases
	*/
	public static List<Phase> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the phases where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching phases
	*/
	public static List<Phase> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Phase> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the phases where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching phases
	*/
	public static List<Phase> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Phase> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first phase in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public static Phase findByGroupId_First(long groupId,
		OrderByComparator<Phase> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPhaseException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first phase in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public static Phase fetchByGroupId_First(long groupId,
		OrderByComparator<Phase> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last phase in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public static Phase findByGroupId_Last(long groupId,
		OrderByComparator<Phase> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPhaseException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last phase in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public static Phase fetchByGroupId_Last(long groupId,
		OrderByComparator<Phase> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the phases before and after the current phase in the ordered set where groupId = &#63;.
	*
	* @param PhaseId the primary key of the current phase
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next phase
	* @throws NoSuchPhaseException if a phase with the primary key could not be found
	*/
	public static Phase[] findByGroupId_PrevAndNext(long PhaseId, long groupId,
		OrderByComparator<Phase> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPhaseException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(PhaseId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the phases where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of phases where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching phases
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the phases where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @return the matching phases
	*/
	public static List<Phase> findByPublikId(java.lang.String publikId) {
		return getPersistence().findByPublikId(publikId);
	}

	/**
	* Returns a range of all the phases where publikId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikId the publik ID
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @return the range of matching phases
	*/
	public static List<Phase> findByPublikId(java.lang.String publikId,
		int start, int end) {
		return getPersistence().findByPublikId(publikId, start, end);
	}

	/**
	* Returns an ordered range of all the phases where publikId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikId the publik ID
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching phases
	*/
	public static List<Phase> findByPublikId(java.lang.String publikId,
		int start, int end, OrderByComparator<Phase> orderByComparator) {
		return getPersistence()
				   .findByPublikId(publikId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the phases where publikId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikId the publik ID
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching phases
	*/
	public static List<Phase> findByPublikId(java.lang.String publikId,
		int start, int end, OrderByComparator<Phase> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPublikId(publikId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first phase in the ordered set where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public static Phase findByPublikId_First(java.lang.String publikId,
		OrderByComparator<Phase> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPhaseException {
		return getPersistence().findByPublikId_First(publikId, orderByComparator);
	}

	/**
	* Returns the first phase in the ordered set where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public static Phase fetchByPublikId_First(java.lang.String publikId,
		OrderByComparator<Phase> orderByComparator) {
		return getPersistence()
				   .fetchByPublikId_First(publikId, orderByComparator);
	}

	/**
	* Returns the last phase in the ordered set where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public static Phase findByPublikId_Last(java.lang.String publikId,
		OrderByComparator<Phase> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPhaseException {
		return getPersistence().findByPublikId_Last(publikId, orderByComparator);
	}

	/**
	* Returns the last phase in the ordered set where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public static Phase fetchByPublikId_Last(java.lang.String publikId,
		OrderByComparator<Phase> orderByComparator) {
		return getPersistence().fetchByPublikId_Last(publikId, orderByComparator);
	}

	/**
	* Returns the phases before and after the current phase in the ordered set where publikId = &#63;.
	*
	* @param PhaseId the primary key of the current phase
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next phase
	* @throws NoSuchPhaseException if a phase with the primary key could not be found
	*/
	public static Phase[] findByPublikId_PrevAndNext(long PhaseId,
		java.lang.String publikId, OrderByComparator<Phase> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchPhaseException {
		return getPersistence()
				   .findByPublikId_PrevAndNext(PhaseId, publikId,
			orderByComparator);
	}

	/**
	* Removes all the phases where publikId = &#63; from the database.
	*
	* @param publikId the publik ID
	*/
	public static void removeByPublikId(java.lang.String publikId) {
		getPersistence().removeByPublikId(publikId);
	}

	/**
	* Returns the number of phases where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @return the number of matching phases
	*/
	public static int countByPublikId(java.lang.String publikId) {
		return getPersistence().countByPublikId(publikId);
	}

	/**
	* Caches the phase in the entity cache if it is enabled.
	*
	* @param phase the phase
	*/
	public static void cacheResult(Phase phase) {
		getPersistence().cacheResult(phase);
	}

	/**
	* Caches the phases in the entity cache if it is enabled.
	*
	* @param phases the phases
	*/
	public static void cacheResult(List<Phase> phases) {
		getPersistence().cacheResult(phases);
	}

	/**
	* Creates a new phase with the primary key. Does not add the phase to the database.
	*
	* @param PhaseId the primary key for the new phase
	* @return the new phase
	*/
	public static Phase create(long PhaseId) {
		return getPersistence().create(PhaseId);
	}

	/**
	* Removes the phase with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param PhaseId the primary key of the phase
	* @return the phase that was removed
	* @throws NoSuchPhaseException if a phase with the primary key could not be found
	*/
	public static Phase remove(long PhaseId)
		throws eu.strasbourg.service.project.exception.NoSuchPhaseException {
		return getPersistence().remove(PhaseId);
	}

	public static Phase updateImpl(Phase phase) {
		return getPersistence().updateImpl(phase);
	}

	/**
	* Returns the phase with the primary key or throws a {@link NoSuchPhaseException} if it could not be found.
	*
	* @param PhaseId the primary key of the phase
	* @return the phase
	* @throws NoSuchPhaseException if a phase with the primary key could not be found
	*/
	public static Phase findByPrimaryKey(long PhaseId)
		throws eu.strasbourg.service.project.exception.NoSuchPhaseException {
		return getPersistence().findByPrimaryKey(PhaseId);
	}

	/**
	* Returns the phase with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param PhaseId the primary key of the phase
	* @return the phase, or <code>null</code> if a phase with the primary key could not be found
	*/
	public static Phase fetchByPrimaryKey(long PhaseId) {
		return getPersistence().fetchByPrimaryKey(PhaseId);
	}

	public static java.util.Map<java.io.Serializable, Phase> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the phases.
	*
	* @return the phases
	*/
	public static List<Phase> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the phases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @return the range of phases
	*/
	public static List<Phase> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the phases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of phases
	*/
	public static List<Phase> findAll(int start, int end,
		OrderByComparator<Phase> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the phases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of phases
	*/
	public static List<Phase> findAll(int start, int end,
		OrderByComparator<Phase> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the phases from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of phases.
	*
	* @return the number of phases
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PhasePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PhasePersistence, PhasePersistence> _serviceTracker =
		ServiceTrackerFactory.open(PhasePersistence.class);
}