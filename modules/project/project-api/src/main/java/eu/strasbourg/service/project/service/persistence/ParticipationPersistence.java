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

/**
 * The persistence interface for the participation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.persistence.impl.ParticipationPersistenceImpl
 * @see ParticipationUtil
 * @generated
 */
@ProviderType
public interface ParticipationPersistence extends BasePersistence<Participation> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ParticipationUtil} to access the participation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the participations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching participations
	*/
	public java.util.List<Participation> findByUuid(java.lang.String uuid);

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
	public java.util.List<Participation> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<Participation> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator);

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
	public java.util.List<Participation> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first participation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching participation
	* @throws NoSuchParticipationException if a matching participation could not be found
	*/
	public Participation findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator)
		throws NoSuchParticipationException;

	/**
	* Returns the first participation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public Participation fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator);

	/**
	* Returns the last participation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching participation
	* @throws NoSuchParticipationException if a matching participation could not be found
	*/
	public Participation findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator)
		throws NoSuchParticipationException;

	/**
	* Returns the last participation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public Participation fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator);

	/**
	* Returns the participations before and after the current participation in the ordered set where uuid = &#63;.
	*
	* @param participationId the primary key of the current participation
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next participation
	* @throws NoSuchParticipationException if a participation with the primary key could not be found
	*/
	public Participation[] findByUuid_PrevAndNext(long participationId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator)
		throws NoSuchParticipationException;

	/**
	* Removes all the participations where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of participations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching participations
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the participation where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchParticipationException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching participation
	* @throws NoSuchParticipationException if a matching participation could not be found
	*/
	public Participation findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchParticipationException;

	/**
	* Returns the participation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public Participation fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the participation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public Participation fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the participation where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the participation that was removed
	*/
	public Participation removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchParticipationException;

	/**
	* Returns the number of participations where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching participations
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the participations where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching participations
	*/
	public java.util.List<Participation> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<Participation> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<Participation> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator);

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
	public java.util.List<Participation> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first participation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching participation
	* @throws NoSuchParticipationException if a matching participation could not be found
	*/
	public Participation findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator)
		throws NoSuchParticipationException;

	/**
	* Returns the first participation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public Participation fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator);

	/**
	* Returns the last participation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching participation
	* @throws NoSuchParticipationException if a matching participation could not be found
	*/
	public Participation findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator)
		throws NoSuchParticipationException;

	/**
	* Returns the last participation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public Participation fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator);

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
	public Participation[] findByUuid_C_PrevAndNext(long participationId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator)
		throws NoSuchParticipationException;

	/**
	* Removes all the participations where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of participations where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching participations
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @return the range of matching participations
	*/
	public java.util.List<Participation> findByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<Participation> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator);

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
	public java.util.List<Participation> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first participation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching participation
	* @throws NoSuchParticipationException if a matching participation could not be found
	*/
	public Participation findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator)
		throws NoSuchParticipationException;

	/**
	* Returns the first participation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public Participation fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator);

	/**
	* Returns the last participation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching participation
	* @throws NoSuchParticipationException if a matching participation could not be found
	*/
	public Participation findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator)
		throws NoSuchParticipationException;

	/**
	* Returns the last participation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public Participation fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator);

	/**
	* Returns the participations before and after the current participation in the ordered set where groupId = &#63;.
	*
	* @param participationId the primary key of the current participation
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next participation
	* @throws NoSuchParticipationException if a participation with the primary key could not be found
	*/
	public Participation[] findByGroupId_PrevAndNext(long participationId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator)
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
	* Returns the participation with the primary key or throws a {@link NoSuchParticipationException} if it could not be found.
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

	@Override
	public java.util.Map<java.io.Serializable, Participation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of participations
	*/
	public java.util.List<Participation> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator);

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
	public java.util.List<Participation> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Participation> orderByComparator,
		boolean retrieveFromCache);

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
	public java.util.Set<java.lang.String> getBadColumnNames();
}