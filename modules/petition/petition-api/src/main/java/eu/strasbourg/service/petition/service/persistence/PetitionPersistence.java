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

package eu.strasbourg.service.petition.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.petition.exception.NoSuchPetitionException;
import eu.strasbourg.service.petition.model.Petition;

/**
 * The persistence interface for the petition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Alexandre QUERE
 * @see eu.strasbourg.service.petition.service.persistence.impl.PetitionPersistenceImpl
 * @see PetitionUtil
 * @generated
 */
@ProviderType
public interface PetitionPersistence extends BasePersistence<Petition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PetitionUtil} to access the petition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the petitions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching petitions
	*/
	public java.util.List<Petition> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the petitions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of petitions
	* @param end the upper bound of the range of petitions (not inclusive)
	* @return the range of matching petitions
	*/
	public java.util.List<Petition> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the petitions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of petitions
	* @param end the upper bound of the range of petitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching petitions
	*/
	public java.util.List<Petition> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator);

	/**
	* Returns an ordered range of all the petitions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of petitions
	* @param end the upper bound of the range of petitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching petitions
	*/
	public java.util.List<Petition> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first petition in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching petition
	* @throws NoSuchPetitionException if a matching petition could not be found
	*/
	public Petition findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator)
		throws NoSuchPetitionException;

	/**
	* Returns the first petition in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching petition, or <code>null</code> if a matching petition could not be found
	*/
	public Petition fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator);

	/**
	* Returns the last petition in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching petition
	* @throws NoSuchPetitionException if a matching petition could not be found
	*/
	public Petition findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator)
		throws NoSuchPetitionException;

	/**
	* Returns the last petition in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching petition, or <code>null</code> if a matching petition could not be found
	*/
	public Petition fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator);

	/**
	* Returns the petitions before and after the current petition in the ordered set where uuid = &#63;.
	*
	* @param petitionId the primary key of the current petition
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next petition
	* @throws NoSuchPetitionException if a petition with the primary key could not be found
	*/
	public Petition[] findByUuid_PrevAndNext(long petitionId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator)
		throws NoSuchPetitionException;

	/**
	* Removes all the petitions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of petitions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching petitions
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the petition where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPetitionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching petition
	* @throws NoSuchPetitionException if a matching petition could not be found
	*/
	public Petition findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPetitionException;

	/**
	* Returns the petition where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching petition, or <code>null</code> if a matching petition could not be found
	*/
	public Petition fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the petition where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching petition, or <code>null</code> if a matching petition could not be found
	*/
	public Petition fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the petition where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the petition that was removed
	*/
	public Petition removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchPetitionException;

	/**
	* Returns the number of petitions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching petitions
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the petitions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching petitions
	*/
	public java.util.List<Petition> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the petitions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of petitions
	* @param end the upper bound of the range of petitions (not inclusive)
	* @return the range of matching petitions
	*/
	public java.util.List<Petition> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the petitions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of petitions
	* @param end the upper bound of the range of petitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching petitions
	*/
	public java.util.List<Petition> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator);

	/**
	* Returns an ordered range of all the petitions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of petitions
	* @param end the upper bound of the range of petitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching petitions
	*/
	public java.util.List<Petition> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first petition in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching petition
	* @throws NoSuchPetitionException if a matching petition could not be found
	*/
	public Petition findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator)
		throws NoSuchPetitionException;

	/**
	* Returns the first petition in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching petition, or <code>null</code> if a matching petition could not be found
	*/
	public Petition fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator);

	/**
	* Returns the last petition in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching petition
	* @throws NoSuchPetitionException if a matching petition could not be found
	*/
	public Petition findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator)
		throws NoSuchPetitionException;

	/**
	* Returns the last petition in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching petition, or <code>null</code> if a matching petition could not be found
	*/
	public Petition fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator);

	/**
	* Returns the petitions before and after the current petition in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param petitionId the primary key of the current petition
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next petition
	* @throws NoSuchPetitionException if a petition with the primary key could not be found
	*/
	public Petition[] findByUuid_C_PrevAndNext(long petitionId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator)
		throws NoSuchPetitionException;

	/**
	* Removes all the petitions where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of petitions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching petitions
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the petitions where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching petitions
	*/
	public java.util.List<Petition> findByGroupId(long groupId);

	/**
	* Returns a range of all the petitions where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of petitions
	* @param end the upper bound of the range of petitions (not inclusive)
	* @return the range of matching petitions
	*/
	public java.util.List<Petition> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the petitions where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of petitions
	* @param end the upper bound of the range of petitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching petitions
	*/
	public java.util.List<Petition> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator);

	/**
	* Returns an ordered range of all the petitions where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of petitions
	* @param end the upper bound of the range of petitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching petitions
	*/
	public java.util.List<Petition> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first petition in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching petition
	* @throws NoSuchPetitionException if a matching petition could not be found
	*/
	public Petition findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator)
		throws NoSuchPetitionException;

	/**
	* Returns the first petition in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching petition, or <code>null</code> if a matching petition could not be found
	*/
	public Petition fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator);

	/**
	* Returns the last petition in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching petition
	* @throws NoSuchPetitionException if a matching petition could not be found
	*/
	public Petition findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator)
		throws NoSuchPetitionException;

	/**
	* Returns the last petition in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching petition, or <code>null</code> if a matching petition could not be found
	*/
	public Petition fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator);

	/**
	* Returns the petitions before and after the current petition in the ordered set where groupId = &#63;.
	*
	* @param petitionId the primary key of the current petition
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next petition
	* @throws NoSuchPetitionException if a petition with the primary key could not be found
	*/
	public Petition[] findByGroupId_PrevAndNext(long petitionId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator)
		throws NoSuchPetitionException;

	/**
	* Removes all the petitions where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of petitions where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching petitions
	*/
	public int countByGroupId(long groupId);

	/**
	* Caches the petition in the entity cache if it is enabled.
	*
	* @param petition the petition
	*/
	public void cacheResult(Petition petition);

	/**
	* Caches the petitions in the entity cache if it is enabled.
	*
	* @param petitions the petitions
	*/
	public void cacheResult(java.util.List<Petition> petitions);

	/**
	* Creates a new petition with the primary key. Does not add the petition to the database.
	*
	* @param petitionId the primary key for the new petition
	* @return the new petition
	*/
	public Petition create(long petitionId);

	/**
	* Removes the petition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param petitionId the primary key of the petition
	* @return the petition that was removed
	* @throws NoSuchPetitionException if a petition with the primary key could not be found
	*/
	public Petition remove(long petitionId) throws NoSuchPetitionException;

	public Petition updateImpl(Petition petition);

	/**
	* Returns the petition with the primary key or throws a {@link NoSuchPetitionException} if it could not be found.
	*
	* @param petitionId the primary key of the petition
	* @return the petition
	* @throws NoSuchPetitionException if a petition with the primary key could not be found
	*/
	public Petition findByPrimaryKey(long petitionId)
		throws NoSuchPetitionException;

	/**
	* Returns the petition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param petitionId the primary key of the petition
	* @return the petition, or <code>null</code> if a petition with the primary key could not be found
	*/
	public Petition fetchByPrimaryKey(long petitionId);

	@Override
	public java.util.Map<java.io.Serializable, Petition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the petitions.
	*
	* @return the petitions
	*/
	public java.util.List<Petition> findAll();

	/**
	* Returns a range of all the petitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of petitions
	* @param end the upper bound of the range of petitions (not inclusive)
	* @return the range of petitions
	*/
	public java.util.List<Petition> findAll(int start, int end);

	/**
	* Returns an ordered range of all the petitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of petitions
	* @param end the upper bound of the range of petitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of petitions
	*/
	public java.util.List<Petition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator);

	/**
	* Returns an ordered range of all the petitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of petitions
	* @param end the upper bound of the range of petitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of petitions
	*/
	public java.util.List<Petition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Petition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the petitions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of petitions.
	*
	* @return the number of petitions
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}