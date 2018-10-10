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

import eu.strasbourg.service.project.exception.NoSuchInitiativeException;
import eu.strasbourg.service.project.model.Initiative;

/**
 * The persistence interface for the initiative service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.persistence.impl.InitiativePersistenceImpl
 * @see InitiativeUtil
 * @generated
 */
@ProviderType
public interface InitiativePersistence extends BasePersistence<Initiative> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link InitiativeUtil} to access the initiative persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the initiatives where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching initiatives
	*/
	public java.util.List<Initiative> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the initiatives where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @return the range of matching initiatives
	*/
	public java.util.List<Initiative> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the initiatives where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching initiatives
	*/
	public java.util.List<Initiative> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator);

	/**
	* Returns an ordered range of all the initiatives where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching initiatives
	*/
	public java.util.List<Initiative> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first initiative in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative
	* @throws NoSuchInitiativeException if a matching initiative could not be found
	*/
	public Initiative findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator)
		throws NoSuchInitiativeException;

	/**
	* Returns the first initiative in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative, or <code>null</code> if a matching initiative could not be found
	*/
	public Initiative fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator);

	/**
	* Returns the last initiative in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative
	* @throws NoSuchInitiativeException if a matching initiative could not be found
	*/
	public Initiative findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator)
		throws NoSuchInitiativeException;

	/**
	* Returns the last initiative in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative, or <code>null</code> if a matching initiative could not be found
	*/
	public Initiative fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator);

	/**
	* Returns the initiatives before and after the current initiative in the ordered set where uuid = &#63;.
	*
	* @param initiativeId the primary key of the current initiative
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next initiative
	* @throws NoSuchInitiativeException if a initiative with the primary key could not be found
	*/
	public Initiative[] findByUuid_PrevAndNext(long initiativeId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator)
		throws NoSuchInitiativeException;

	/**
	* Removes all the initiatives where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of initiatives where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching initiatives
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the initiative where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchInitiativeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching initiative
	* @throws NoSuchInitiativeException if a matching initiative could not be found
	*/
	public Initiative findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchInitiativeException;

	/**
	* Returns the initiative where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching initiative, or <code>null</code> if a matching initiative could not be found
	*/
	public Initiative fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the initiative where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching initiative, or <code>null</code> if a matching initiative could not be found
	*/
	public Initiative fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the initiative where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the initiative that was removed
	*/
	public Initiative removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchInitiativeException;

	/**
	* Returns the number of initiatives where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching initiatives
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the initiatives where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching initiatives
	*/
	public java.util.List<Initiative> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the initiatives where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @return the range of matching initiatives
	*/
	public java.util.List<Initiative> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the initiatives where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching initiatives
	*/
	public java.util.List<Initiative> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator);

	/**
	* Returns an ordered range of all the initiatives where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching initiatives
	*/
	public java.util.List<Initiative> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first initiative in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative
	* @throws NoSuchInitiativeException if a matching initiative could not be found
	*/
	public Initiative findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator)
		throws NoSuchInitiativeException;

	/**
	* Returns the first initiative in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative, or <code>null</code> if a matching initiative could not be found
	*/
	public Initiative fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator);

	/**
	* Returns the last initiative in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative
	* @throws NoSuchInitiativeException if a matching initiative could not be found
	*/
	public Initiative findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator)
		throws NoSuchInitiativeException;

	/**
	* Returns the last initiative in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative, or <code>null</code> if a matching initiative could not be found
	*/
	public Initiative fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator);

	/**
	* Returns the initiatives before and after the current initiative in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param initiativeId the primary key of the current initiative
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next initiative
	* @throws NoSuchInitiativeException if a initiative with the primary key could not be found
	*/
	public Initiative[] findByUuid_C_PrevAndNext(long initiativeId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator)
		throws NoSuchInitiativeException;

	/**
	* Removes all the initiatives where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of initiatives where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching initiatives
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the initiatives where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching initiatives
	*/
	public java.util.List<Initiative> findByGroupId(long groupId);

	/**
	* Returns a range of all the initiatives where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @return the range of matching initiatives
	*/
	public java.util.List<Initiative> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the initiatives where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching initiatives
	*/
	public java.util.List<Initiative> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator);

	/**
	* Returns an ordered range of all the initiatives where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching initiatives
	*/
	public java.util.List<Initiative> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first initiative in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative
	* @throws NoSuchInitiativeException if a matching initiative could not be found
	*/
	public Initiative findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator)
		throws NoSuchInitiativeException;

	/**
	* Returns the first initiative in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative, or <code>null</code> if a matching initiative could not be found
	*/
	public Initiative fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator);

	/**
	* Returns the last initiative in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative
	* @throws NoSuchInitiativeException if a matching initiative could not be found
	*/
	public Initiative findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator)
		throws NoSuchInitiativeException;

	/**
	* Returns the last initiative in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative, or <code>null</code> if a matching initiative could not be found
	*/
	public Initiative fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator);

	/**
	* Returns the initiatives before and after the current initiative in the ordered set where groupId = &#63;.
	*
	* @param initiativeId the primary key of the current initiative
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next initiative
	* @throws NoSuchInitiativeException if a initiative with the primary key could not be found
	*/
	public Initiative[] findByGroupId_PrevAndNext(long initiativeId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator)
		throws NoSuchInitiativeException;

	/**
	* Removes all the initiatives where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of initiatives where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching initiatives
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the initiatives where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @return the matching initiatives
	*/
	public java.util.List<Initiative> findBypublikId(java.lang.String publikId);

	/**
	* Returns a range of all the initiatives where publikId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikId the publik ID
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @return the range of matching initiatives
	*/
	public java.util.List<Initiative> findBypublikId(
		java.lang.String publikId, int start, int end);

	/**
	* Returns an ordered range of all the initiatives where publikId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikId the publik ID
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching initiatives
	*/
	public java.util.List<Initiative> findBypublikId(
		java.lang.String publikId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator);

	/**
	* Returns an ordered range of all the initiatives where publikId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikId the publik ID
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching initiatives
	*/
	public java.util.List<Initiative> findBypublikId(
		java.lang.String publikId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first initiative in the ordered set where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative
	* @throws NoSuchInitiativeException if a matching initiative could not be found
	*/
	public Initiative findBypublikId_First(java.lang.String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator)
		throws NoSuchInitiativeException;

	/**
	* Returns the first initiative in the ordered set where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative, or <code>null</code> if a matching initiative could not be found
	*/
	public Initiative fetchBypublikId_First(java.lang.String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator);

	/**
	* Returns the last initiative in the ordered set where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative
	* @throws NoSuchInitiativeException if a matching initiative could not be found
	*/
	public Initiative findBypublikId_Last(java.lang.String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator)
		throws NoSuchInitiativeException;

	/**
	* Returns the last initiative in the ordered set where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative, or <code>null</code> if a matching initiative could not be found
	*/
	public Initiative fetchBypublikId_Last(java.lang.String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator);

	/**
	* Returns the initiatives before and after the current initiative in the ordered set where publikId = &#63;.
	*
	* @param initiativeId the primary key of the current initiative
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next initiative
	* @throws NoSuchInitiativeException if a initiative with the primary key could not be found
	*/
	public Initiative[] findBypublikId_PrevAndNext(long initiativeId,
		java.lang.String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator)
		throws NoSuchInitiativeException;

	/**
	* Removes all the initiatives where publikId = &#63; from the database.
	*
	* @param publikId the publik ID
	*/
	public void removeBypublikId(java.lang.String publikId);

	/**
	* Returns the number of initiatives where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @return the number of matching initiatives
	*/
	public int countBypublikId(java.lang.String publikId);

	/**
	* Caches the initiative in the entity cache if it is enabled.
	*
	* @param initiative the initiative
	*/
	public void cacheResult(Initiative initiative);

	/**
	* Caches the initiatives in the entity cache if it is enabled.
	*
	* @param initiatives the initiatives
	*/
	public void cacheResult(java.util.List<Initiative> initiatives);

	/**
	* Creates a new initiative with the primary key. Does not add the initiative to the database.
	*
	* @param initiativeId the primary key for the new initiative
	* @return the new initiative
	*/
	public Initiative create(long initiativeId);

	/**
	* Removes the initiative with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param initiativeId the primary key of the initiative
	* @return the initiative that was removed
	* @throws NoSuchInitiativeException if a initiative with the primary key could not be found
	*/
	public Initiative remove(long initiativeId)
		throws NoSuchInitiativeException;

	public Initiative updateImpl(Initiative initiative);

	/**
	* Returns the initiative with the primary key or throws a {@link NoSuchInitiativeException} if it could not be found.
	*
	* @param initiativeId the primary key of the initiative
	* @return the initiative
	* @throws NoSuchInitiativeException if a initiative with the primary key could not be found
	*/
	public Initiative findByPrimaryKey(long initiativeId)
		throws NoSuchInitiativeException;

	/**
	* Returns the initiative with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param initiativeId the primary key of the initiative
	* @return the initiative, or <code>null</code> if a initiative with the primary key could not be found
	*/
	public Initiative fetchByPrimaryKey(long initiativeId);

	@Override
	public java.util.Map<java.io.Serializable, Initiative> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the initiatives.
	*
	* @return the initiatives
	*/
	public java.util.List<Initiative> findAll();

	/**
	* Returns a range of all the initiatives.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @return the range of initiatives
	*/
	public java.util.List<Initiative> findAll(int start, int end);

	/**
	* Returns an ordered range of all the initiatives.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of initiatives
	*/
	public java.util.List<Initiative> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator);

	/**
	* Returns an ordered range of all the initiatives.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of initiatives
	*/
	public java.util.List<Initiative> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Initiative> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the initiatives from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of initiatives.
	*
	* @return the number of initiatives
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}