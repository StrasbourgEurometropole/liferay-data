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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.activity.exception.NoSuchActivityOrganizerException;
import eu.strasbourg.service.activity.model.ActivityOrganizer;

/**
 * The persistence interface for the activity organizer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see eu.strasbourg.service.activity.service.persistence.impl.ActivityOrganizerPersistenceImpl
 * @see ActivityOrganizerUtil
 * @generated
 */
@ProviderType
public interface ActivityOrganizerPersistence extends BasePersistence<ActivityOrganizer> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ActivityOrganizerUtil} to access the activity organizer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the activity organizers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching activity organizers
	*/
	public java.util.List<ActivityOrganizer> findByUuid(java.lang.String uuid);

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
	public java.util.List<ActivityOrganizer> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<ActivityOrganizer> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator);

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
	public java.util.List<ActivityOrganizer> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity organizer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity organizer
	* @throws NoSuchActivityOrganizerException if a matching activity organizer could not be found
	*/
	public ActivityOrganizer findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator)
		throws NoSuchActivityOrganizerException;

	/**
	* Returns the first activity organizer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	public ActivityOrganizer fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator);

	/**
	* Returns the last activity organizer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity organizer
	* @throws NoSuchActivityOrganizerException if a matching activity organizer could not be found
	*/
	public ActivityOrganizer findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator)
		throws NoSuchActivityOrganizerException;

	/**
	* Returns the last activity organizer in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	public ActivityOrganizer fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator);

	/**
	* Returns the activity organizers before and after the current activity organizer in the ordered set where uuid = &#63;.
	*
	* @param activityOrganizerId the primary key of the current activity organizer
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity organizer
	* @throws NoSuchActivityOrganizerException if a activity organizer with the primary key could not be found
	*/
	public ActivityOrganizer[] findByUuid_PrevAndNext(
		long activityOrganizerId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator)
		throws NoSuchActivityOrganizerException;

	/**
	* Removes all the activity organizers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of activity organizers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching activity organizers
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the activity organizer where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchActivityOrganizerException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching activity organizer
	* @throws NoSuchActivityOrganizerException if a matching activity organizer could not be found
	*/
	public ActivityOrganizer findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchActivityOrganizerException;

	/**
	* Returns the activity organizer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	public ActivityOrganizer fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the activity organizer where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	public ActivityOrganizer fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the activity organizer where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the activity organizer that was removed
	*/
	public ActivityOrganizer removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchActivityOrganizerException;

	/**
	* Returns the number of activity organizers where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching activity organizers
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the activity organizers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching activity organizers
	*/
	public java.util.List<ActivityOrganizer> findByUuid_C(
		java.lang.String uuid, long companyId);

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
	public java.util.List<ActivityOrganizer> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

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
	public java.util.List<ActivityOrganizer> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator);

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
	public java.util.List<ActivityOrganizer> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity organizer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity organizer
	* @throws NoSuchActivityOrganizerException if a matching activity organizer could not be found
	*/
	public ActivityOrganizer findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator)
		throws NoSuchActivityOrganizerException;

	/**
	* Returns the first activity organizer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	public ActivityOrganizer fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator);

	/**
	* Returns the last activity organizer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity organizer
	* @throws NoSuchActivityOrganizerException if a matching activity organizer could not be found
	*/
	public ActivityOrganizer findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator)
		throws NoSuchActivityOrganizerException;

	/**
	* Returns the last activity organizer in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	public ActivityOrganizer fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator);

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
	public ActivityOrganizer[] findByUuid_C_PrevAndNext(
		long activityOrganizerId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator)
		throws NoSuchActivityOrganizerException;

	/**
	* Removes all the activity organizers where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of activity organizers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching activity organizers
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the activity organizers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching activity organizers
	*/
	public java.util.List<ActivityOrganizer> findByGroupId(long groupId);

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
	public java.util.List<ActivityOrganizer> findByGroupId(long groupId,
		int start, int end);

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
	public java.util.List<ActivityOrganizer> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator);

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
	public java.util.List<ActivityOrganizer> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first activity organizer in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity organizer
	* @throws NoSuchActivityOrganizerException if a matching activity organizer could not be found
	*/
	public ActivityOrganizer findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator)
		throws NoSuchActivityOrganizerException;

	/**
	* Returns the first activity organizer in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	public ActivityOrganizer fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator);

	/**
	* Returns the last activity organizer in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity organizer
	* @throws NoSuchActivityOrganizerException if a matching activity organizer could not be found
	*/
	public ActivityOrganizer findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator)
		throws NoSuchActivityOrganizerException;

	/**
	* Returns the last activity organizer in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	public ActivityOrganizer fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator);

	/**
	* Returns the activity organizers before and after the current activity organizer in the ordered set where groupId = &#63;.
	*
	* @param activityOrganizerId the primary key of the current activity organizer
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next activity organizer
	* @throws NoSuchActivityOrganizerException if a activity organizer with the primary key could not be found
	*/
	public ActivityOrganizer[] findByGroupId_PrevAndNext(
		long activityOrganizerId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator)
		throws NoSuchActivityOrganizerException;

	/**
	* Removes all the activity organizers where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of activity organizers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching activity organizers
	*/
	public int countByGroupId(long groupId);

	/**
	* Caches the activity organizer in the entity cache if it is enabled.
	*
	* @param activityOrganizer the activity organizer
	*/
	public void cacheResult(ActivityOrganizer activityOrganizer);

	/**
	* Caches the activity organizers in the entity cache if it is enabled.
	*
	* @param activityOrganizers the activity organizers
	*/
	public void cacheResult(
		java.util.List<ActivityOrganizer> activityOrganizers);

	/**
	* Creates a new activity organizer with the primary key. Does not add the activity organizer to the database.
	*
	* @param activityOrganizerId the primary key for the new activity organizer
	* @return the new activity organizer
	*/
	public ActivityOrganizer create(long activityOrganizerId);

	/**
	* Removes the activity organizer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityOrganizerId the primary key of the activity organizer
	* @return the activity organizer that was removed
	* @throws NoSuchActivityOrganizerException if a activity organizer with the primary key could not be found
	*/
	public ActivityOrganizer remove(long activityOrganizerId)
		throws NoSuchActivityOrganizerException;

	public ActivityOrganizer updateImpl(ActivityOrganizer activityOrganizer);

	/**
	* Returns the activity organizer with the primary key or throws a {@link NoSuchActivityOrganizerException} if it could not be found.
	*
	* @param activityOrganizerId the primary key of the activity organizer
	* @return the activity organizer
	* @throws NoSuchActivityOrganizerException if a activity organizer with the primary key could not be found
	*/
	public ActivityOrganizer findByPrimaryKey(long activityOrganizerId)
		throws NoSuchActivityOrganizerException;

	/**
	* Returns the activity organizer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param activityOrganizerId the primary key of the activity organizer
	* @return the activity organizer, or <code>null</code> if a activity organizer with the primary key could not be found
	*/
	public ActivityOrganizer fetchByPrimaryKey(long activityOrganizerId);

	@Override
	public java.util.Map<java.io.Serializable, ActivityOrganizer> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the activity organizers.
	*
	* @return the activity organizers
	*/
	public java.util.List<ActivityOrganizer> findAll();

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
	public java.util.List<ActivityOrganizer> findAll(int start, int end);

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
	public java.util.List<ActivityOrganizer> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator);

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
	public java.util.List<ActivityOrganizer> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ActivityOrganizer> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the activity organizers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of activity organizers.
	*
	* @return the number of activity organizers
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}