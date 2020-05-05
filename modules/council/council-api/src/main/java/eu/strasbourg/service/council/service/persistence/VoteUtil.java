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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.council.model.Vote;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the vote service. This utility wraps {@link eu.strasbourg.service.council.service.persistence.impl.VotePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VotePersistence
 * @see eu.strasbourg.service.council.service.persistence.impl.VotePersistenceImpl
 * @generated
 */
@ProviderType
public class VoteUtil {
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
	public static void clearCache(Vote vote) {
		getPersistence().clearCache(vote);
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
	public static List<Vote> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Vote> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Vote> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Vote> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Vote update(Vote vote) {
		return getPersistence().update(vote);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Vote update(Vote vote, ServiceContext serviceContext) {
		return getPersistence().update(vote, serviceContext);
	}

	/**
	* Returns all the votes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching votes
	*/
	public static List<Vote> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the votes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of votes
	* @param end the upper bound of the range of votes (not inclusive)
	* @return the range of matching votes
	*/
	public static List<Vote> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the votes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of votes
	* @param end the upper bound of the range of votes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching votes
	*/
	public static List<Vote> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Vote> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the votes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of votes
	* @param end the upper bound of the range of votes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching votes
	*/
	public static List<Vote> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Vote> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first vote in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching vote
	* @throws NoSuchVoteException if a matching vote could not be found
	*/
	public static Vote findByUuid_First(java.lang.String uuid,
		OrderByComparator<Vote> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchVoteException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first vote in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching vote, or <code>null</code> if a matching vote could not be found
	*/
	public static Vote fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Vote> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last vote in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching vote
	* @throws NoSuchVoteException if a matching vote could not be found
	*/
	public static Vote findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Vote> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchVoteException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last vote in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching vote, or <code>null</code> if a matching vote could not be found
	*/
	public static Vote fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Vote> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the votes before and after the current vote in the ordered set where uuid = &#63;.
	*
	* @param voteId the primary key of the current vote
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next vote
	* @throws NoSuchVoteException if a vote with the primary key could not be found
	*/
	public static Vote[] findByUuid_PrevAndNext(long voteId,
		java.lang.String uuid, OrderByComparator<Vote> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchVoteException {
		return getPersistence()
				   .findByUuid_PrevAndNext(voteId, uuid, orderByComparator);
	}

	/**
	* Removes all the votes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of votes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching votes
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the vote where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchVoteException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching vote
	* @throws NoSuchVoteException if a matching vote could not be found
	*/
	public static Vote findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.council.exception.NoSuchVoteException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the vote where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching vote, or <code>null</code> if a matching vote could not be found
	*/
	public static Vote fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the vote where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching vote, or <code>null</code> if a matching vote could not be found
	*/
	public static Vote fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the vote where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the vote that was removed
	*/
	public static Vote removeByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.council.exception.NoSuchVoteException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of votes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching votes
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the votes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching votes
	*/
	public static List<Vote> findByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the votes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of votes
	* @param end the upper bound of the range of votes (not inclusive)
	* @return the range of matching votes
	*/
	public static List<Vote> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the votes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of votes
	* @param end the upper bound of the range of votes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching votes
	*/
	public static List<Vote> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Vote> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the votes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static List<Vote> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Vote> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first vote in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching vote
	* @throws NoSuchVoteException if a matching vote could not be found
	*/
	public static Vote findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Vote> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchVoteException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first vote in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching vote, or <code>null</code> if a matching vote could not be found
	*/
	public static Vote fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Vote> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last vote in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching vote
	* @throws NoSuchVoteException if a matching vote could not be found
	*/
	public static Vote findByUuid_C_Last(java.lang.String uuid, long companyId,
		OrderByComparator<Vote> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchVoteException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last vote in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching vote, or <code>null</code> if a matching vote could not be found
	*/
	public static Vote fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Vote> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the votes before and after the current vote in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param voteId the primary key of the current vote
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next vote
	* @throws NoSuchVoteException if a vote with the primary key could not be found
	*/
	public static Vote[] findByUuid_C_PrevAndNext(long voteId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Vote> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchVoteException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(voteId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the votes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of votes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching votes
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the votes where deliberationId = &#63;.
	*
	* @param deliberationId the deliberation ID
	* @return the matching votes
	*/
	public static List<Vote> findByDeliberationId(long deliberationId) {
		return getPersistence().findByDeliberationId(deliberationId);
	}

	/**
	* Returns a range of all the votes where deliberationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliberationId the deliberation ID
	* @param start the lower bound of the range of votes
	* @param end the upper bound of the range of votes (not inclusive)
	* @return the range of matching votes
	*/
	public static List<Vote> findByDeliberationId(long deliberationId,
		int start, int end) {
		return getPersistence().findByDeliberationId(deliberationId, start, end);
	}

	/**
	* Returns an ordered range of all the votes where deliberationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliberationId the deliberation ID
	* @param start the lower bound of the range of votes
	* @param end the upper bound of the range of votes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching votes
	*/
	public static List<Vote> findByDeliberationId(long deliberationId,
		int start, int end, OrderByComparator<Vote> orderByComparator) {
		return getPersistence()
				   .findByDeliberationId(deliberationId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the votes where deliberationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param deliberationId the deliberation ID
	* @param start the lower bound of the range of votes
	* @param end the upper bound of the range of votes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching votes
	*/
	public static List<Vote> findByDeliberationId(long deliberationId,
		int start, int end, OrderByComparator<Vote> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDeliberationId(deliberationId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first vote in the ordered set where deliberationId = &#63;.
	*
	* @param deliberationId the deliberation ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching vote
	* @throws NoSuchVoteException if a matching vote could not be found
	*/
	public static Vote findByDeliberationId_First(long deliberationId,
		OrderByComparator<Vote> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchVoteException {
		return getPersistence()
				   .findByDeliberationId_First(deliberationId, orderByComparator);
	}

	/**
	* Returns the first vote in the ordered set where deliberationId = &#63;.
	*
	* @param deliberationId the deliberation ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching vote, or <code>null</code> if a matching vote could not be found
	*/
	public static Vote fetchByDeliberationId_First(long deliberationId,
		OrderByComparator<Vote> orderByComparator) {
		return getPersistence()
				   .fetchByDeliberationId_First(deliberationId,
			orderByComparator);
	}

	/**
	* Returns the last vote in the ordered set where deliberationId = &#63;.
	*
	* @param deliberationId the deliberation ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching vote
	* @throws NoSuchVoteException if a matching vote could not be found
	*/
	public static Vote findByDeliberationId_Last(long deliberationId,
		OrderByComparator<Vote> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchVoteException {
		return getPersistence()
				   .findByDeliberationId_Last(deliberationId, orderByComparator);
	}

	/**
	* Returns the last vote in the ordered set where deliberationId = &#63;.
	*
	* @param deliberationId the deliberation ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching vote, or <code>null</code> if a matching vote could not be found
	*/
	public static Vote fetchByDeliberationId_Last(long deliberationId,
		OrderByComparator<Vote> orderByComparator) {
		return getPersistence()
				   .fetchByDeliberationId_Last(deliberationId, orderByComparator);
	}

	/**
	* Returns the votes before and after the current vote in the ordered set where deliberationId = &#63;.
	*
	* @param voteId the primary key of the current vote
	* @param deliberationId the deliberation ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next vote
	* @throws NoSuchVoteException if a vote with the primary key could not be found
	*/
	public static Vote[] findByDeliberationId_PrevAndNext(long voteId,
		long deliberationId, OrderByComparator<Vote> orderByComparator)
		throws eu.strasbourg.service.council.exception.NoSuchVoteException {
		return getPersistence()
				   .findByDeliberationId_PrevAndNext(voteId, deliberationId,
			orderByComparator);
	}

	/**
	* Removes all the votes where deliberationId = &#63; from the database.
	*
	* @param deliberationId the deliberation ID
	*/
	public static void removeByDeliberationId(long deliberationId) {
		getPersistence().removeByDeliberationId(deliberationId);
	}

	/**
	* Returns the number of votes where deliberationId = &#63;.
	*
	* @param deliberationId the deliberation ID
	* @return the number of matching votes
	*/
	public static int countByDeliberationId(long deliberationId) {
		return getPersistence().countByDeliberationId(deliberationId);
	}

	/**
	* Returns the vote where deliberationId = &#63; and officialId = &#63; or throws a {@link NoSuchVoteException} if it could not be found.
	*
	* @param deliberationId the deliberation ID
	* @param officialId the official ID
	* @return the matching vote
	* @throws NoSuchVoteException if a matching vote could not be found
	*/
	public static Vote findByDeliberationIdAndOfficialId(long deliberationId,
		long officialId)
		throws eu.strasbourg.service.council.exception.NoSuchVoteException {
		return getPersistence()
				   .findByDeliberationIdAndOfficialId(deliberationId, officialId);
	}

	/**
	* Returns the vote where deliberationId = &#63; and officialId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param deliberationId the deliberation ID
	* @param officialId the official ID
	* @return the matching vote, or <code>null</code> if a matching vote could not be found
	*/
	public static Vote fetchByDeliberationIdAndOfficialId(long deliberationId,
		long officialId) {
		return getPersistence()
				   .fetchByDeliberationIdAndOfficialId(deliberationId,
			officialId);
	}

	/**
	* Returns the vote where deliberationId = &#63; and officialId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param deliberationId the deliberation ID
	* @param officialId the official ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching vote, or <code>null</code> if a matching vote could not be found
	*/
	public static Vote fetchByDeliberationIdAndOfficialId(long deliberationId,
		long officialId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByDeliberationIdAndOfficialId(deliberationId,
			officialId, retrieveFromCache);
	}

	/**
	* Removes the vote where deliberationId = &#63; and officialId = &#63; from the database.
	*
	* @param deliberationId the deliberation ID
	* @param officialId the official ID
	* @return the vote that was removed
	*/
	public static Vote removeByDeliberationIdAndOfficialId(
		long deliberationId, long officialId)
		throws eu.strasbourg.service.council.exception.NoSuchVoteException {
		return getPersistence()
				   .removeByDeliberationIdAndOfficialId(deliberationId,
			officialId);
	}

	/**
	* Returns the number of votes where deliberationId = &#63; and officialId = &#63;.
	*
	* @param deliberationId the deliberation ID
	* @param officialId the official ID
	* @return the number of matching votes
	*/
	public static int countByDeliberationIdAndOfficialId(long deliberationId,
		long officialId) {
		return getPersistence()
				   .countByDeliberationIdAndOfficialId(deliberationId,
			officialId);
	}

	/**
	* Caches the vote in the entity cache if it is enabled.
	*
	* @param vote the vote
	*/
	public static void cacheResult(Vote vote) {
		getPersistence().cacheResult(vote);
	}

	/**
	* Caches the votes in the entity cache if it is enabled.
	*
	* @param votes the votes
	*/
	public static void cacheResult(List<Vote> votes) {
		getPersistence().cacheResult(votes);
	}

	/**
	* Creates a new vote with the primary key. Does not add the vote to the database.
	*
	* @param voteId the primary key for the new vote
	* @return the new vote
	*/
	public static Vote create(long voteId) {
		return getPersistence().create(voteId);
	}

	/**
	* Removes the vote with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param voteId the primary key of the vote
	* @return the vote that was removed
	* @throws NoSuchVoteException if a vote with the primary key could not be found
	*/
	public static Vote remove(long voteId)
		throws eu.strasbourg.service.council.exception.NoSuchVoteException {
		return getPersistence().remove(voteId);
	}

	public static Vote updateImpl(Vote vote) {
		return getPersistence().updateImpl(vote);
	}

	/**
	* Returns the vote with the primary key or throws a {@link NoSuchVoteException} if it could not be found.
	*
	* @param voteId the primary key of the vote
	* @return the vote
	* @throws NoSuchVoteException if a vote with the primary key could not be found
	*/
	public static Vote findByPrimaryKey(long voteId)
		throws eu.strasbourg.service.council.exception.NoSuchVoteException {
		return getPersistence().findByPrimaryKey(voteId);
	}

	/**
	* Returns the vote with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param voteId the primary key of the vote
	* @return the vote, or <code>null</code> if a vote with the primary key could not be found
	*/
	public static Vote fetchByPrimaryKey(long voteId) {
		return getPersistence().fetchByPrimaryKey(voteId);
	}

	public static java.util.Map<java.io.Serializable, Vote> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the votes.
	*
	* @return the votes
	*/
	public static List<Vote> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the votes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of votes
	* @param end the upper bound of the range of votes (not inclusive)
	* @return the range of votes
	*/
	public static List<Vote> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the votes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of votes
	* @param end the upper bound of the range of votes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of votes
	*/
	public static List<Vote> findAll(int start, int end,
		OrderByComparator<Vote> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the votes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of votes
	* @param end the upper bound of the range of votes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of votes
	*/
	public static List<Vote> findAll(int start, int end,
		OrderByComparator<Vote> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the votes from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of votes.
	*
	* @return the number of votes
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VotePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VotePersistence, VotePersistence> _serviceTracker =
		ServiceTrackerFactory.open(VotePersistence.class);
}