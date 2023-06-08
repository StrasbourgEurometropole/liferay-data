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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.project.exception.NoSuchParticipationException;
import eu.strasbourg.service.project.model.Participation;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the participation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see ParticipationUtil
 * @generated
 */
@ProviderType
public interface ParticipationPersistence
	extends BasePersistence<Participation> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ParticipationUtil} to access the participation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, Participation> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the participations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching participations
	 */
	public java.util.List<Participation> findByUuid(String uuid);

	/**
	 * Returns a range of all the participations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParticipationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @return the range of matching participations
	 */
	public java.util.List<Participation> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the participations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParticipationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching participations
	 */
	public java.util.List<Participation> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the participations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParticipationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching participations
	 */
	public java.util.List<Participation> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first participation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching participation
	 * @throws NoSuchParticipationException if a matching participation could not be found
	 */
	public Participation findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Participation>
				orderByComparator)
		throws NoSuchParticipationException;

	/**
	 * Returns the first participation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching participation, or <code>null</code> if a matching participation could not be found
	 */
	public Participation fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator);

	/**
	 * Returns the last participation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching participation
	 * @throws NoSuchParticipationException if a matching participation could not be found
	 */
	public Participation findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Participation>
				orderByComparator)
		throws NoSuchParticipationException;

	/**
	 * Returns the last participation in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching participation, or <code>null</code> if a matching participation could not be found
	 */
	public Participation fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator);

	/**
	 * Returns the participations before and after the current participation in the ordered set where uuid = &#63;.
	 *
	 * @param participationId the primary key of the current participation
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next participation
	 * @throws NoSuchParticipationException if a participation with the primary key could not be found
	 */
	public Participation[] findByUuid_PrevAndNext(
			long participationId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Participation>
				orderByComparator)
		throws NoSuchParticipationException;

	/**
	 * Removes all the participations where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of participations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching participations
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the participation where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchParticipationException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching participation
	 * @throws NoSuchParticipationException if a matching participation could not be found
	 */
	public Participation findByUUID_G(String uuid, long groupId)
		throws NoSuchParticipationException;

	/**
	 * Returns the participation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching participation, or <code>null</code> if a matching participation could not be found
	 */
	public Participation fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the participation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching participation, or <code>null</code> if a matching participation could not be found
	 */
	public Participation fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the participation where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the participation that was removed
	 */
	public Participation removeByUUID_G(String uuid, long groupId)
		throws NoSuchParticipationException;

	/**
	 * Returns the number of participations where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching participations
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the participations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching participations
	 */
	public java.util.List<Participation> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the participations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParticipationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @return the range of matching participations
	 */
	public java.util.List<Participation> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the participations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParticipationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching participations
	 */
	public java.util.List<Participation> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the participations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParticipationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching participations
	 */
	public java.util.List<Participation> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first participation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching participation
	 * @throws NoSuchParticipationException if a matching participation could not be found
	 */
	public Participation findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Participation>
				orderByComparator)
		throws NoSuchParticipationException;

	/**
	 * Returns the first participation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching participation, or <code>null</code> if a matching participation could not be found
	 */
	public Participation fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator);

	/**
	 * Returns the last participation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching participation
	 * @throws NoSuchParticipationException if a matching participation could not be found
	 */
	public Participation findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Participation>
				orderByComparator)
		throws NoSuchParticipationException;

	/**
	 * Returns the last participation in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching participation, or <code>null</code> if a matching participation could not be found
	 */
	public Participation fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator);

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
	public Participation[] findByUuid_C_PrevAndNext(
			long participationId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Participation>
				orderByComparator)
		throws NoSuchParticipationException;

	/**
	 * Removes all the participations where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of participations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching participations
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the participations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching participations
	 */
	public java.util.List<Participation> findByGroupId(long groupId);

	/**
	 * Returns a range of all the participations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParticipationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @return the range of matching participations
	 */
	public java.util.List<Participation> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the participations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParticipationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching participations
	 */
	public java.util.List<Participation> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the participations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParticipationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching participations
	 */
	public java.util.List<Participation> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first participation in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching participation
	 * @throws NoSuchParticipationException if a matching participation could not be found
	 */
	public Participation findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Participation>
				orderByComparator)
		throws NoSuchParticipationException;

	/**
	 * Returns the first participation in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching participation, or <code>null</code> if a matching participation could not be found
	 */
	public Participation fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator);

	/**
	 * Returns the last participation in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching participation
	 * @throws NoSuchParticipationException if a matching participation could not be found
	 */
	public Participation findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Participation>
				orderByComparator)
		throws NoSuchParticipationException;

	/**
	 * Returns the last participation in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching participation, or <code>null</code> if a matching participation could not be found
	 */
	public Participation fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator);

	/**
	 * Returns the participations before and after the current participation in the ordered set where groupId = &#63;.
	 *
	 * @param participationId the primary key of the current participation
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next participation
	 * @throws NoSuchParticipationException if a participation with the primary key could not be found
	 */
	public Participation[] findByGroupId_PrevAndNext(
			long participationId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Participation>
				orderByComparator)
		throws NoSuchParticipationException;

	/**
	 * Removes all the participations where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of participations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching participations
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns all the participations where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @return the matching participations
	 */
	public java.util.List<Participation> findByStatusAndGroupId(
		int status, long groupId);

	/**
	 * Returns a range of all the participations where status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParticipationModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @return the range of matching participations
	 */
	public java.util.List<Participation> findByStatusAndGroupId(
		int status, long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the participations where status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParticipationModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching participations
	 */
	public java.util.List<Participation> findByStatusAndGroupId(
		int status, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the participations where status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParticipationModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching participations
	 */
	public java.util.List<Participation> findByStatusAndGroupId(
		int status, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first participation in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching participation
	 * @throws NoSuchParticipationException if a matching participation could not be found
	 */
	public Participation findByStatusAndGroupId_First(
			int status, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Participation>
				orderByComparator)
		throws NoSuchParticipationException;

	/**
	 * Returns the first participation in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching participation, or <code>null</code> if a matching participation could not be found
	 */
	public Participation fetchByStatusAndGroupId_First(
		int status, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator);

	/**
	 * Returns the last participation in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching participation
	 * @throws NoSuchParticipationException if a matching participation could not be found
	 */
	public Participation findByStatusAndGroupId_Last(
			int status, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Participation>
				orderByComparator)
		throws NoSuchParticipationException;

	/**
	 * Returns the last participation in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching participation, or <code>null</code> if a matching participation could not be found
	 */
	public Participation fetchByStatusAndGroupId_Last(
		int status, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator);

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
	public Participation[] findByStatusAndGroupId_PrevAndNext(
			long participationId, int status, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Participation>
				orderByComparator)
		throws NoSuchParticipationException;

	/**
	 * Removes all the participations where status = &#63; and groupId = &#63; from the database.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 */
	public void removeByStatusAndGroupId(int status, long groupId);

	/**
	 * Returns the number of participations where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @return the number of matching participations
	 */
	public int countByStatusAndGroupId(int status, long groupId);

	/**
	 * Caches the participation in the entity cache if it is enabled.
	 *
	 * @param participation the participation
	 */
	public void cacheResult(Participation participation);

	/**
	 * Caches the participations in the entity cache if it is enabled.
	 *
	 * @param participations the participations
	 */
	public void cacheResult(java.util.List<Participation> participations);

	/**
	 * Creates a new participation with the primary key. Does not add the participation to the database.
	 *
	 * @param participationId the primary key for the new participation
	 * @return the new participation
	 */
	public Participation create(long participationId);

	/**
	 * Removes the participation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param participationId the primary key of the participation
	 * @return the participation that was removed
	 * @throws NoSuchParticipationException if a participation with the primary key could not be found
	 */
	public Participation remove(long participationId)
		throws NoSuchParticipationException;

	public Participation updateImpl(Participation participation);

	/**
	 * Returns the participation with the primary key or throws a <code>NoSuchParticipationException</code> if it could not be found.
	 *
	 * @param participationId the primary key of the participation
	 * @return the participation
	 * @throws NoSuchParticipationException if a participation with the primary key could not be found
	 */
	public Participation findByPrimaryKey(long participationId)
		throws NoSuchParticipationException;

	/**
	 * Returns the participation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param participationId the primary key of the participation
	 * @return the participation, or <code>null</code> if a participation with the primary key could not be found
	 */
	public Participation fetchByPrimaryKey(long participationId);

	/**
	 * Returns all the participations.
	 *
	 * @return the participations
	 */
	public java.util.List<Participation> findAll();

	/**
	 * Returns a range of all the participations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParticipationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @return the range of participations
	 */
	public java.util.List<Participation> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the participations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParticipationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of participations
	 */
	public java.util.List<Participation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator);

	/**
	 * Returns an ordered range of all the participations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ParticipationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of participations
	 * @param end the upper bound of the range of participations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of participations
	 */
	public java.util.List<Participation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the participations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of participations.
	 *
	 * @return the number of participations
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}