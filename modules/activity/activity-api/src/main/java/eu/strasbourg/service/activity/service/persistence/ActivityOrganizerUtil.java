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

package eu.strasbourg.service.activity.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.activity.model.ActivityOrganizer;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the activity organizer service. This utility wraps {@link eu.strasbourg.service.activity.service.persistence.impl.ActivityOrganizerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivityOrganizerPersistence
 * @see eu.strasbourg.service.activity.service.persistence.impl.ActivityOrganizerPersistenceImpl
 * @generated
 */
@ProviderType
public class ActivityOrganizerUtil {
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
	public static void clearCache(ActivityOrganizer activityOrganizer) {
		getPersistence().clearCache(activityOrganizer);
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
	public static List<ActivityOrganizer> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ActivityOrganizer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ActivityOrganizer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ActivityOrganizer> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ActivityOrganizer update(ActivityOrganizer activityOrganizer) {
		return getPersistence().update(activityOrganizer);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ActivityOrganizer update(
		ActivityOrganizer activityOrganizer, ServiceContext serviceContext) {
		return getPersistence().update(activityOrganizer, serviceContext);
	}

	/**
	* Returns all the activity organizers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching activity organizers
	*/
	public static List<ActivityOrganizer> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the activity organizers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityOrganizerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of activity organizers
	* @param end the upper bound of the range of activity organizers (not inclusive)
	* @return the range of matching activity organizers
	*/
	public static List<ActivityOrganizer> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the activity organizers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityOrganizerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of activity organizers
	* @param end the upper bound of the range of activity organizers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity organizers
	*/
	public static List<ActivityOrganizer> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<ActivityOrganizer> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the activity organizers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityOrganizerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of activity organizers
	* @param end the upper bound of the range of activity organizers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity organizers
	*/
	public static List<ActivityOrganizer> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<ActivityOrganizer> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first activity organizer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity organizer
	* @throws NoSuchActivityOrganizerException if a matching activity organizer could not be found
	*/
	public static ActivityOrganizer findByUuid_First(java.lang.String uuid,
		OrderByComparator<ActivityOrganizer> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityOrganizerException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first activity organizer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	public static ActivityOrganizer fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<ActivityOrganizer> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last activity organizer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity organizer
	* @throws NoSuchActivityOrganizerException if a matching activity organizer could not be found
	*/
	public static ActivityOrganizer findByUuid_Last(java.lang.String uuid,
		OrderByComparator<ActivityOrganizer> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityOrganizerException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last activity organizer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	public static ActivityOrganizer fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<ActivityOrganizer> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the activity organizers before and after the current activity organizer in the ordered set where uuid = &#63;.
	*
	* @param activityOrganizerId the primary key of the current activity organizer
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity organizer
	* @throws NoSuchActivityOrganizerException if a activity organizer with the primary key could not be found
	*/
	public static ActivityOrganizer[] findByUuid_PrevAndNext(
		long activityOrganizerId, java.lang.String uuid,
		OrderByComparator<ActivityOrganizer> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityOrganizerException {
		return getPersistence()
				   .findByUuid_PrevAndNext(activityOrganizerId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the activity organizers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of activity organizers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching activity organizers
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the activity organizer where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchActivityOrganizerException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching activity organizer
	* @throws NoSuchActivityOrganizerException if a matching activity organizer could not be found
	*/
	public static ActivityOrganizer findByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityOrganizerException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the activity organizer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	public static ActivityOrganizer fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the activity organizer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	public static ActivityOrganizer fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the activity organizer where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the activity organizer that was removed
	*/
	public static ActivityOrganizer removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityOrganizerException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of activity organizers where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching activity organizers
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the activity organizers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching activity organizers
	*/
	public static List<ActivityOrganizer> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the activity organizers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityOrganizerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of activity organizers
	* @param end the upper bound of the range of activity organizers (not inclusive)
	* @return the range of matching activity organizers
	*/
	public static List<ActivityOrganizer> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the activity organizers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityOrganizerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of activity organizers
	* @param end the upper bound of the range of activity organizers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity organizers
	*/
	public static List<ActivityOrganizer> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<ActivityOrganizer> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the activity organizers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityOrganizerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of activity organizers
	* @param end the upper bound of the range of activity organizers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity organizers
	*/
	public static List<ActivityOrganizer> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<ActivityOrganizer> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first activity organizer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity organizer
	* @throws NoSuchActivityOrganizerException if a matching activity organizer could not be found
	*/
	public static ActivityOrganizer findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<ActivityOrganizer> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityOrganizerException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first activity organizer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	public static ActivityOrganizer fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<ActivityOrganizer> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last activity organizer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity organizer
	* @throws NoSuchActivityOrganizerException if a matching activity organizer could not be found
	*/
	public static ActivityOrganizer findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<ActivityOrganizer> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityOrganizerException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last activity organizer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	public static ActivityOrganizer fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<ActivityOrganizer> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the activity organizers before and after the current activity organizer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param activityOrganizerId the primary key of the current activity organizer
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity organizer
	* @throws NoSuchActivityOrganizerException if a activity organizer with the primary key could not be found
	*/
	public static ActivityOrganizer[] findByUuid_C_PrevAndNext(
		long activityOrganizerId, java.lang.String uuid, long companyId,
		OrderByComparator<ActivityOrganizer> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityOrganizerException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(activityOrganizerId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the activity organizers where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of activity organizers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching activity organizers
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the activity organizers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching activity organizers
	*/
	public static List<ActivityOrganizer> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the activity organizers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityOrganizerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of activity organizers
	* @param end the upper bound of the range of activity organizers (not inclusive)
	* @return the range of matching activity organizers
	*/
	public static List<ActivityOrganizer> findByGroupId(long groupId,
		int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the activity organizers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityOrganizerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of activity organizers
	* @param end the upper bound of the range of activity organizers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching activity organizers
	*/
	public static List<ActivityOrganizer> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<ActivityOrganizer> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the activity organizers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityOrganizerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of activity organizers
	* @param end the upper bound of the range of activity organizers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching activity organizers
	*/
	public static List<ActivityOrganizer> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<ActivityOrganizer> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first activity organizer in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity organizer
	* @throws NoSuchActivityOrganizerException if a matching activity organizer could not be found
	*/
	public static ActivityOrganizer findByGroupId_First(long groupId,
		OrderByComparator<ActivityOrganizer> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityOrganizerException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first activity organizer in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	public static ActivityOrganizer fetchByGroupId_First(long groupId,
		OrderByComparator<ActivityOrganizer> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last activity organizer in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity organizer
	* @throws NoSuchActivityOrganizerException if a matching activity organizer could not be found
	*/
	public static ActivityOrganizer findByGroupId_Last(long groupId,
		OrderByComparator<ActivityOrganizer> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityOrganizerException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last activity organizer in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	public static ActivityOrganizer fetchByGroupId_Last(long groupId,
		OrderByComparator<ActivityOrganizer> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the activity organizers before and after the current activity organizer in the ordered set where groupId = &#63;.
	*
	* @param activityOrganizerId the primary key of the current activity organizer
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity organizer
	* @throws NoSuchActivityOrganizerException if a activity organizer with the primary key could not be found
	*/
	public static ActivityOrganizer[] findByGroupId_PrevAndNext(
		long activityOrganizerId, long groupId,
		OrderByComparator<ActivityOrganizer> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityOrganizerException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(activityOrganizerId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the activity organizers where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of activity organizers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching activity organizers
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Caches the activity organizer in the entity cache if it is enabled.
	*
	* @param activityOrganizer the activity organizer
	*/
	public static void cacheResult(ActivityOrganizer activityOrganizer) {
		getPersistence().cacheResult(activityOrganizer);
	}

	/**
	* Caches the activity organizers in the entity cache if it is enabled.
	*
	* @param activityOrganizers the activity organizers
	*/
	public static void cacheResult(List<ActivityOrganizer> activityOrganizers) {
		getPersistence().cacheResult(activityOrganizers);
	}

	/**
	* Creates a new activity organizer with the primary key. Does not add the activity organizer to the database.
	*
	* @param activityOrganizerId the primary key for the new activity organizer
	* @return the new activity organizer
	*/
	public static ActivityOrganizer create(long activityOrganizerId) {
		return getPersistence().create(activityOrganizerId);
	}

	/**
	* Removes the activity organizer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityOrganizerId the primary key of the activity organizer
	* @return the activity organizer that was removed
	* @throws NoSuchActivityOrganizerException if a activity organizer with the primary key could not be found
	*/
	public static ActivityOrganizer remove(long activityOrganizerId)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityOrganizerException {
		return getPersistence().remove(activityOrganizerId);
	}

	public static ActivityOrganizer updateImpl(
		ActivityOrganizer activityOrganizer) {
		return getPersistence().updateImpl(activityOrganizer);
	}

	/**
	* Returns the activity organizer with the primary key or throws a {@link NoSuchActivityOrganizerException} if it could not be found.
	*
	* @param activityOrganizerId the primary key of the activity organizer
	* @return the activity organizer
	* @throws NoSuchActivityOrganizerException if a activity organizer with the primary key could not be found
	*/
	public static ActivityOrganizer findByPrimaryKey(long activityOrganizerId)
		throws eu.strasbourg.service.activity.exception.NoSuchActivityOrganizerException {
		return getPersistence().findByPrimaryKey(activityOrganizerId);
	}

	/**
	* Returns the activity organizer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param activityOrganizerId the primary key of the activity organizer
	* @return the activity organizer, or <code>null</code> if a activity organizer with the primary key could not be found
	*/
	public static ActivityOrganizer fetchByPrimaryKey(long activityOrganizerId) {
		return getPersistence().fetchByPrimaryKey(activityOrganizerId);
	}

	public static java.util.Map<java.io.Serializable, ActivityOrganizer> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the activity organizers.
	*
	* @return the activity organizers
	*/
	public static List<ActivityOrganizer> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the activity organizers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityOrganizerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity organizers
	* @param end the upper bound of the range of activity organizers (not inclusive)
	* @return the range of activity organizers
	*/
	public static List<ActivityOrganizer> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the activity organizers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityOrganizerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity organizers
	* @param end the upper bound of the range of activity organizers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of activity organizers
	*/
	public static List<ActivityOrganizer> findAll(int start, int end,
		OrderByComparator<ActivityOrganizer> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the activity organizers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ActivityOrganizerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity organizers
	* @param end the upper bound of the range of activity organizers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of activity organizers
	*/
	public static List<ActivityOrganizer> findAll(int start, int end,
		OrderByComparator<ActivityOrganizer> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the activity organizers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of activity organizers.
	*
	* @return the number of activity organizers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ActivityOrganizerPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ActivityOrganizerPersistence, ActivityOrganizerPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ActivityOrganizerPersistence.class);
}