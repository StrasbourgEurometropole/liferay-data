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

package eu.strasbourg.service.interest.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.interest.exception.NoSuchInterestException;
import eu.strasbourg.service.interest.model.Interest;

/**
 * The persistence interface for the interest service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.interest.service.persistence.impl.InterestPersistenceImpl
 * @see InterestUtil
 * @generated
 */
@ProviderType
public interface InterestPersistence extends BasePersistence<Interest> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link InterestUtil} to access the interest persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the interests where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching interests
	*/
	public java.util.List<Interest> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the interests where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of interests
	* @param end the upper bound of the range of interests (not inclusive)
	* @return the range of matching interests
	*/
	public java.util.List<Interest> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the interests where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of interests
	* @param end the upper bound of the range of interests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching interests
	*/
	public java.util.List<Interest> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator);

	/**
	* Returns an ordered range of all the interests where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of interests
	* @param end the upper bound of the range of interests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching interests
	*/
	public java.util.List<Interest> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first interest in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching interest
	* @throws NoSuchInterestException if a matching interest could not be found
	*/
	public Interest findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator)
		throws NoSuchInterestException;

	/**
	* Returns the first interest in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching interest, or <code>null</code> if a matching interest could not be found
	*/
	public Interest fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator);

	/**
	* Returns the last interest in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching interest
	* @throws NoSuchInterestException if a matching interest could not be found
	*/
	public Interest findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator)
		throws NoSuchInterestException;

	/**
	* Returns the last interest in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching interest, or <code>null</code> if a matching interest could not be found
	*/
	public Interest fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator);

	/**
	* Returns the interests before and after the current interest in the ordered set where uuid = &#63;.
	*
	* @param interestId the primary key of the current interest
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next interest
	* @throws NoSuchInterestException if a interest with the primary key could not be found
	*/
	public Interest[] findByUuid_PrevAndNext(long interestId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator)
		throws NoSuchInterestException;

	/**
	* Removes all the interests where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of interests where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching interests
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the interest where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchInterestException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching interest
	* @throws NoSuchInterestException if a matching interest could not be found
	*/
	public Interest findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchInterestException;

	/**
	* Returns the interest where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching interest, or <code>null</code> if a matching interest could not be found
	*/
	public Interest fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the interest where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching interest, or <code>null</code> if a matching interest could not be found
	*/
	public Interest fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the interest where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the interest that was removed
	*/
	public Interest removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchInterestException;

	/**
	* Returns the number of interests where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching interests
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the interests where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching interests
	*/
	public java.util.List<Interest> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the interests where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of interests
	* @param end the upper bound of the range of interests (not inclusive)
	* @return the range of matching interests
	*/
	public java.util.List<Interest> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the interests where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of interests
	* @param end the upper bound of the range of interests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching interests
	*/
	public java.util.List<Interest> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator);

	/**
	* Returns an ordered range of all the interests where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of interests
	* @param end the upper bound of the range of interests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching interests
	*/
	public java.util.List<Interest> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first interest in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching interest
	* @throws NoSuchInterestException if a matching interest could not be found
	*/
	public Interest findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator)
		throws NoSuchInterestException;

	/**
	* Returns the first interest in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching interest, or <code>null</code> if a matching interest could not be found
	*/
	public Interest fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator);

	/**
	* Returns the last interest in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching interest
	* @throws NoSuchInterestException if a matching interest could not be found
	*/
	public Interest findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator)
		throws NoSuchInterestException;

	/**
	* Returns the last interest in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching interest, or <code>null</code> if a matching interest could not be found
	*/
	public Interest fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator);

	/**
	* Returns the interests before and after the current interest in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param interestId the primary key of the current interest
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next interest
	* @throws NoSuchInterestException if a interest with the primary key could not be found
	*/
	public Interest[] findByUuid_C_PrevAndNext(long interestId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator)
		throws NoSuchInterestException;

	/**
	* Removes all the interests where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of interests where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching interests
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the interests where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching interests
	*/
	public java.util.List<Interest> findByGroupId(long groupId);

	/**
	* Returns a range of all the interests where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of interests
	* @param end the upper bound of the range of interests (not inclusive)
	* @return the range of matching interests
	*/
	public java.util.List<Interest> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the interests where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of interests
	* @param end the upper bound of the range of interests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching interests
	*/
	public java.util.List<Interest> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator);

	/**
	* Returns an ordered range of all the interests where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of interests
	* @param end the upper bound of the range of interests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching interests
	*/
	public java.util.List<Interest> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first interest in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching interest
	* @throws NoSuchInterestException if a matching interest could not be found
	*/
	public Interest findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator)
		throws NoSuchInterestException;

	/**
	* Returns the first interest in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching interest, or <code>null</code> if a matching interest could not be found
	*/
	public Interest fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator);

	/**
	* Returns the last interest in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching interest
	* @throws NoSuchInterestException if a matching interest could not be found
	*/
	public Interest findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator)
		throws NoSuchInterestException;

	/**
	* Returns the last interest in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching interest, or <code>null</code> if a matching interest could not be found
	*/
	public Interest fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator);

	/**
	* Returns the interests before and after the current interest in the ordered set where groupId = &#63;.
	*
	* @param interestId the primary key of the current interest
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next interest
	* @throws NoSuchInterestException if a interest with the primary key could not be found
	*/
	public Interest[] findByGroupId_PrevAndNext(long interestId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator)
		throws NoSuchInterestException;

	/**
	* Removes all the interests where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of interests where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching interests
	*/
	public int countByGroupId(long groupId);

	/**
	* Caches the interest in the entity cache if it is enabled.
	*
	* @param interest the interest
	*/
	public void cacheResult(Interest interest);

	/**
	* Caches the interests in the entity cache if it is enabled.
	*
	* @param interests the interests
	*/
	public void cacheResult(java.util.List<Interest> interests);

	/**
	* Creates a new interest with the primary key. Does not add the interest to the database.
	*
	* @param interestId the primary key for the new interest
	* @return the new interest
	*/
	public Interest create(long interestId);

	/**
	* Removes the interest with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param interestId the primary key of the interest
	* @return the interest that was removed
	* @throws NoSuchInterestException if a interest with the primary key could not be found
	*/
	public Interest remove(long interestId) throws NoSuchInterestException;

	public Interest updateImpl(Interest interest);

	/**
	* Returns the interest with the primary key or throws a {@link NoSuchInterestException} if it could not be found.
	*
	* @param interestId the primary key of the interest
	* @return the interest
	* @throws NoSuchInterestException if a interest with the primary key could not be found
	*/
	public Interest findByPrimaryKey(long interestId)
		throws NoSuchInterestException;

	/**
	* Returns the interest with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param interestId the primary key of the interest
	* @return the interest, or <code>null</code> if a interest with the primary key could not be found
	*/
	public Interest fetchByPrimaryKey(long interestId);

	@Override
	public java.util.Map<java.io.Serializable, Interest> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the interests.
	*
	* @return the interests
	*/
	public java.util.List<Interest> findAll();

	/**
	* Returns a range of all the interests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of interests
	* @param end the upper bound of the range of interests (not inclusive)
	* @return the range of interests
	*/
	public java.util.List<Interest> findAll(int start, int end);

	/**
	* Returns an ordered range of all the interests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of interests
	* @param end the upper bound of the range of interests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of interests
	*/
	public java.util.List<Interest> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator);

	/**
	* Returns an ordered range of all the interests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of interests
	* @param end the upper bound of the range of interests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of interests
	*/
	public java.util.List<Interest> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Interest> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the interests from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of interests.
	*
	* @return the number of interests
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}