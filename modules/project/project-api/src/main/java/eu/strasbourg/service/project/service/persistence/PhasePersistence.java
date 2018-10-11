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

import eu.strasbourg.service.project.exception.NoSuchPhaseException;
import eu.strasbourg.service.project.model.Phase;

/**
 * The persistence interface for the phase service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.persistence.impl.PhasePersistenceImpl
 * @see PhaseUtil
 * @generated
 */
@ProviderType
public interface PhasePersistence extends BasePersistence<Phase> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PhaseUtil} to access the phase persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the phases where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching phases
	*/
	public java.util.List<Phase> findByUuid(java.lang.String uuid);

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
	public java.util.List<Phase> findByUuid(java.lang.String uuid, int start,
		int end);

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
	public java.util.List<Phase> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator);

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
	public java.util.List<Phase> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first phase in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public Phase findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator)
		throws NoSuchPhaseException;

	/**
	* Returns the first phase in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public Phase fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator);

	/**
	* Returns the last phase in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public Phase findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator)
		throws NoSuchPhaseException;

	/**
	* Returns the last phase in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public Phase fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator);

	/**
	* Returns the phases before and after the current phase in the ordered set where uuid = &#63;.
	*
	* @param PhaseId the primary key of the current phase
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next phase
	* @throws NoSuchPhaseException if a phase with the primary key could not be found
	*/
	public Phase[] findByUuid_PrevAndNext(long PhaseId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator)
		throws NoSuchPhaseException;

	/**
	* Removes all the phases where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of phases where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching phases
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the phase where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPhaseException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public Phase findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPhaseException;

	/**
	* Returns the phase where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public Phase fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the phase where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public Phase fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the phase where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the phase that was removed
	*/
	public Phase removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPhaseException;

	/**
	* Returns the number of phases where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching phases
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the phases where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching phases
	*/
	public java.util.List<Phase> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<Phase> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<Phase> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator);

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
	public java.util.List<Phase> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first phase in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public Phase findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator)
		throws NoSuchPhaseException;

	/**
	* Returns the first phase in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public Phase fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator);

	/**
	* Returns the last phase in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public Phase findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator)
		throws NoSuchPhaseException;

	/**
	* Returns the last phase in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public Phase fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator);

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
	public Phase[] findByUuid_C_PrevAndNext(long PhaseId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator)
		throws NoSuchPhaseException;

	/**
	* Removes all the phases where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of phases where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching phases
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the phases where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching phases
	*/
	public java.util.List<Phase> findByGroupId(long groupId);

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
	public java.util.List<Phase> findByGroupId(long groupId, int start, int end);

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
	public java.util.List<Phase> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator);

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
	public java.util.List<Phase> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first phase in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public Phase findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator)
		throws NoSuchPhaseException;

	/**
	* Returns the first phase in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public Phase fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator);

	/**
	* Returns the last phase in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public Phase findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator)
		throws NoSuchPhaseException;

	/**
	* Returns the last phase in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public Phase fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator);

	/**
	* Returns the phases before and after the current phase in the ordered set where groupId = &#63;.
	*
	* @param PhaseId the primary key of the current phase
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next phase
	* @throws NoSuchPhaseException if a phase with the primary key could not be found
	*/
	public Phase[] findByGroupId_PrevAndNext(long PhaseId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator)
		throws NoSuchPhaseException;

	/**
	* Removes all the phases where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of phases where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching phases
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the phases where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @return the matching phases
	*/
	public java.util.List<Phase> findByPublikId(java.lang.String publikId);

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
	public java.util.List<Phase> findByPublikId(java.lang.String publikId,
		int start, int end);

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
	public java.util.List<Phase> findByPublikId(java.lang.String publikId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator);

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
	public java.util.List<Phase> findByPublikId(java.lang.String publikId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first phase in the ordered set where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public Phase findByPublikId_First(java.lang.String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator)
		throws NoSuchPhaseException;

	/**
	* Returns the first phase in the ordered set where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public Phase fetchByPublikId_First(java.lang.String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator);

	/**
	* Returns the last phase in the ordered set where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phase
	* @throws NoSuchPhaseException if a matching phase could not be found
	*/
	public Phase findByPublikId_Last(java.lang.String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator)
		throws NoSuchPhaseException;

	/**
	* Returns the last phase in the ordered set where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching phase, or <code>null</code> if a matching phase could not be found
	*/
	public Phase fetchByPublikId_Last(java.lang.String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator);

	/**
	* Returns the phases before and after the current phase in the ordered set where publikId = &#63;.
	*
	* @param PhaseId the primary key of the current phase
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next phase
	* @throws NoSuchPhaseException if a phase with the primary key could not be found
	*/
	public Phase[] findByPublikId_PrevAndNext(long PhaseId,
		java.lang.String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator)
		throws NoSuchPhaseException;

	/**
	* Removes all the phases where publikId = &#63; from the database.
	*
	* @param publikId the publik ID
	*/
	public void removeByPublikId(java.lang.String publikId);

	/**
	* Returns the number of phases where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @return the number of matching phases
	*/
	public int countByPublikId(java.lang.String publikId);

	/**
	* Caches the phase in the entity cache if it is enabled.
	*
	* @param phase the phase
	*/
	public void cacheResult(Phase phase);

	/**
	* Caches the phases in the entity cache if it is enabled.
	*
	* @param phases the phases
	*/
	public void cacheResult(java.util.List<Phase> phases);

	/**
	* Creates a new phase with the primary key. Does not add the phase to the database.
	*
	* @param PhaseId the primary key for the new phase
	* @return the new phase
	*/
	public Phase create(long PhaseId);

	/**
	* Removes the phase with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param PhaseId the primary key of the phase
	* @return the phase that was removed
	* @throws NoSuchPhaseException if a phase with the primary key could not be found
	*/
	public Phase remove(long PhaseId) throws NoSuchPhaseException;

	public Phase updateImpl(Phase phase);

	/**
	* Returns the phase with the primary key or throws a {@link NoSuchPhaseException} if it could not be found.
	*
	* @param PhaseId the primary key of the phase
	* @return the phase
	* @throws NoSuchPhaseException if a phase with the primary key could not be found
	*/
	public Phase findByPrimaryKey(long PhaseId) throws NoSuchPhaseException;

	/**
	* Returns the phase with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param PhaseId the primary key of the phase
	* @return the phase, or <code>null</code> if a phase with the primary key could not be found
	*/
	public Phase fetchByPrimaryKey(long PhaseId);

	@Override
	public java.util.Map<java.io.Serializable, Phase> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the phases.
	*
	* @return the phases
	*/
	public java.util.List<Phase> findAll();

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
	public java.util.List<Phase> findAll(int start, int end);

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
	public java.util.List<Phase> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator);

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
	public java.util.List<Phase> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Phase> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the phases from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of phases.
	*
	* @return the number of phases
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}