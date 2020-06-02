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

package eu.strasbourg.service.council.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.council.exception.NoSuchVoteException;
import eu.strasbourg.service.council.model.Vote;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the vote service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VoteUtil
 * @generated
 */
@ProviderType
public interface VotePersistence extends BasePersistence<Vote> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link VoteUtil} to access the vote persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, Vote> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the votes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching votes
	 */
	public java.util.List<Vote> findByUuid(String uuid);

	/**
	 * Returns a range of all the votes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>VoteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @return the range of matching votes
	 */
	public java.util.List<Vote> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the votes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>VoteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching votes
	 */
	public java.util.List<Vote> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Vote>
			orderByComparator);

	/**
	 * Returns an ordered range of all the votes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>VoteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching votes
	 */
	public java.util.List<Vote> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Vote>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first vote in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vote
	 * @throws NoSuchVoteException if a matching vote could not be found
	 */
	public Vote findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Vote>
				orderByComparator)
		throws NoSuchVoteException;

	/**
	 * Returns the first vote in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vote, or <code>null</code> if a matching vote could not be found
	 */
	public Vote fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Vote>
			orderByComparator);

	/**
	 * Returns the last vote in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vote
	 * @throws NoSuchVoteException if a matching vote could not be found
	 */
	public Vote findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Vote>
				orderByComparator)
		throws NoSuchVoteException;

	/**
	 * Returns the last vote in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vote, or <code>null</code> if a matching vote could not be found
	 */
	public Vote fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Vote>
			orderByComparator);

	/**
	 * Returns the votes before and after the current vote in the ordered set where uuid = &#63;.
	 *
	 * @param votePK the primary key of the current vote
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vote
	 * @throws NoSuchVoteException if a vote with the primary key could not be found
	 */
	public Vote[] findByUuid_PrevAndNext(
			eu.strasbourg.service.council.service.persistence.VotePK votePK,
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Vote>
				orderByComparator)
		throws NoSuchVoteException;

	/**
	 * Removes all the votes where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of votes where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching votes
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the vote where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchVoteException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching vote
	 * @throws NoSuchVoteException if a matching vote could not be found
	 */
	public Vote findByUUID_G(String uuid, long groupId)
		throws NoSuchVoteException;

	/**
	 * Returns the vote where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching vote, or <code>null</code> if a matching vote could not be found
	 */
	public Vote fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the vote where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching vote, or <code>null</code> if a matching vote could not be found
	 */
	public Vote fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache);

	/**
	 * Removes the vote where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the vote that was removed
	 */
	public Vote removeByUUID_G(String uuid, long groupId)
		throws NoSuchVoteException;

	/**
	 * Returns the number of votes where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching votes
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the votes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching votes
	 */
	public java.util.List<Vote> findByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the votes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>VoteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @return the range of matching votes
	 */
	public java.util.List<Vote> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the votes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>VoteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching votes
	 */
	public java.util.List<Vote> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Vote>
			orderByComparator);

	/**
	 * Returns an ordered range of all the votes where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>VoteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching votes
	 */
	public java.util.List<Vote> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Vote>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first vote in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vote
	 * @throws NoSuchVoteException if a matching vote could not be found
	 */
	public Vote findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Vote>
				orderByComparator)
		throws NoSuchVoteException;

	/**
	 * Returns the first vote in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vote, or <code>null</code> if a matching vote could not be found
	 */
	public Vote fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Vote>
			orderByComparator);

	/**
	 * Returns the last vote in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vote
	 * @throws NoSuchVoteException if a matching vote could not be found
	 */
	public Vote findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Vote>
				orderByComparator)
		throws NoSuchVoteException;

	/**
	 * Returns the last vote in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vote, or <code>null</code> if a matching vote could not be found
	 */
	public Vote fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Vote>
			orderByComparator);

	/**
	 * Returns the votes before and after the current vote in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param votePK the primary key of the current vote
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vote
	 * @throws NoSuchVoteException if a vote with the primary key could not be found
	 */
	public Vote[] findByUuid_C_PrevAndNext(
			eu.strasbourg.service.council.service.persistence.VotePK votePK,
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Vote>
				orderByComparator)
		throws NoSuchVoteException;

	/**
	 * Removes all the votes where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of votes where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching votes
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the votes where deliberationId = &#63;.
	 *
	 * @param deliberationId the deliberation ID
	 * @return the matching votes
	 */
	public java.util.List<Vote> findByDeliberationId(long deliberationId);

	/**
	 * Returns a range of all the votes where deliberationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>VoteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param deliberationId the deliberation ID
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @return the range of matching votes
	 */
	public java.util.List<Vote> findByDeliberationId(
		long deliberationId, int start, int end);

	/**
	 * Returns an ordered range of all the votes where deliberationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>VoteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param deliberationId the deliberation ID
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching votes
	 */
	public java.util.List<Vote> findByDeliberationId(
		long deliberationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Vote>
			orderByComparator);

	/**
	 * Returns an ordered range of all the votes where deliberationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>VoteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param deliberationId the deliberation ID
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching votes
	 */
	public java.util.List<Vote> findByDeliberationId(
		long deliberationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Vote>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first vote in the ordered set where deliberationId = &#63;.
	 *
	 * @param deliberationId the deliberation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vote
	 * @throws NoSuchVoteException if a matching vote could not be found
	 */
	public Vote findByDeliberationId_First(
			long deliberationId,
			com.liferay.portal.kernel.util.OrderByComparator<Vote>
				orderByComparator)
		throws NoSuchVoteException;

	/**
	 * Returns the first vote in the ordered set where deliberationId = &#63;.
	 *
	 * @param deliberationId the deliberation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vote, or <code>null</code> if a matching vote could not be found
	 */
	public Vote fetchByDeliberationId_First(
		long deliberationId,
		com.liferay.portal.kernel.util.OrderByComparator<Vote>
			orderByComparator);

	/**
	 * Returns the last vote in the ordered set where deliberationId = &#63;.
	 *
	 * @param deliberationId the deliberation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vote
	 * @throws NoSuchVoteException if a matching vote could not be found
	 */
	public Vote findByDeliberationId_Last(
			long deliberationId,
			com.liferay.portal.kernel.util.OrderByComparator<Vote>
				orderByComparator)
		throws NoSuchVoteException;

	/**
	 * Returns the last vote in the ordered set where deliberationId = &#63;.
	 *
	 * @param deliberationId the deliberation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vote, or <code>null</code> if a matching vote could not be found
	 */
	public Vote fetchByDeliberationId_Last(
		long deliberationId,
		com.liferay.portal.kernel.util.OrderByComparator<Vote>
			orderByComparator);

	/**
	 * Returns the votes before and after the current vote in the ordered set where deliberationId = &#63;.
	 *
	 * @param votePK the primary key of the current vote
	 * @param deliberationId the deliberation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vote
	 * @throws NoSuchVoteException if a vote with the primary key could not be found
	 */
	public Vote[] findByDeliberationId_PrevAndNext(
			eu.strasbourg.service.council.service.persistence.VotePK votePK,
			long deliberationId,
			com.liferay.portal.kernel.util.OrderByComparator<Vote>
				orderByComparator)
		throws NoSuchVoteException;

	/**
	 * Removes all the votes where deliberationId = &#63; from the database.
	 *
	 * @param deliberationId the deliberation ID
	 */
	public void removeByDeliberationId(long deliberationId);

	/**
	 * Returns the number of votes where deliberationId = &#63;.
	 *
	 * @param deliberationId the deliberation ID
	 * @return the number of matching votes
	 */
	public int countByDeliberationId(long deliberationId);

	/**
	 * Returns the vote where deliberationId = &#63; and officialId = &#63; or throws a <code>NoSuchVoteException</code> if it could not be found.
	 *
	 * @param deliberationId the deliberation ID
	 * @param officialId the official ID
	 * @return the matching vote
	 * @throws NoSuchVoteException if a matching vote could not be found
	 */
	public Vote findByDeliberationIdAndOfficialId(
			long deliberationId, long officialId)
		throws NoSuchVoteException;

	/**
	 * Returns the vote where deliberationId = &#63; and officialId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param deliberationId the deliberation ID
	 * @param officialId the official ID
	 * @return the matching vote, or <code>null</code> if a matching vote could not be found
	 */
	public Vote fetchByDeliberationIdAndOfficialId(
		long deliberationId, long officialId);

	/**
	 * Returns the vote where deliberationId = &#63; and officialId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param deliberationId the deliberation ID
	 * @param officialId the official ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching vote, or <code>null</code> if a matching vote could not be found
	 */
	public Vote fetchByDeliberationIdAndOfficialId(
		long deliberationId, long officialId, boolean retrieveFromCache);

	/**
	 * Removes the vote where deliberationId = &#63; and officialId = &#63; from the database.
	 *
	 * @param deliberationId the deliberation ID
	 * @param officialId the official ID
	 * @return the vote that was removed
	 */
	public Vote removeByDeliberationIdAndOfficialId(
			long deliberationId, long officialId)
		throws NoSuchVoteException;

	/**
	 * Returns the number of votes where deliberationId = &#63; and officialId = &#63;.
	 *
	 * @param deliberationId the deliberation ID
	 * @param officialId the official ID
	 * @return the number of matching votes
	 */
	public int countByDeliberationIdAndOfficialId(
		long deliberationId, long officialId);

	/**
	 * Caches the vote in the entity cache if it is enabled.
	 *
	 * @param vote the vote
	 */
	public void cacheResult(Vote vote);

	/**
	 * Caches the votes in the entity cache if it is enabled.
	 *
	 * @param votes the votes
	 */
	public void cacheResult(java.util.List<Vote> votes);

	/**
	 * Creates a new vote with the primary key. Does not add the vote to the database.
	 *
	 * @param votePK the primary key for the new vote
	 * @return the new vote
	 */
	public Vote create(
		eu.strasbourg.service.council.service.persistence.VotePK votePK);

	/**
	 * Removes the vote with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param votePK the primary key of the vote
	 * @return the vote that was removed
	 * @throws NoSuchVoteException if a vote with the primary key could not be found
	 */
	public Vote remove(
			eu.strasbourg.service.council.service.persistence.VotePK votePK)
		throws NoSuchVoteException;

	public Vote updateImpl(Vote vote);

	/**
	 * Returns the vote with the primary key or throws a <code>NoSuchVoteException</code> if it could not be found.
	 *
	 * @param votePK the primary key of the vote
	 * @return the vote
	 * @throws NoSuchVoteException if a vote with the primary key could not be found
	 */
	public Vote findByPrimaryKey(
			eu.strasbourg.service.council.service.persistence.VotePK votePK)
		throws NoSuchVoteException;

	/**
	 * Returns the vote with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param votePK the primary key of the vote
	 * @return the vote, or <code>null</code> if a vote with the primary key could not be found
	 */
	public Vote fetchByPrimaryKey(
		eu.strasbourg.service.council.service.persistence.VotePK votePK);

	/**
	 * Returns all the votes.
	 *
	 * @return the votes
	 */
	public java.util.List<Vote> findAll();

	/**
	 * Returns a range of all the votes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>VoteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @return the range of votes
	 */
	public java.util.List<Vote> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the votes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>VoteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of votes
	 */
	public java.util.List<Vote> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Vote>
			orderByComparator);

	/**
	 * Returns an ordered range of all the votes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>VoteModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of votes
	 * @param end the upper bound of the range of votes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of votes
	 */
	public java.util.List<Vote> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Vote>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the votes from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of votes.
	 *
	 * @return the number of votes
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

	public Set<String> getCompoundPKColumnNames();

}