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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.interest.model.UserInterest;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the user interest service. This utility wraps {@link eu.strasbourg.service.interest.service.persistence.impl.UserInterestPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see UserInterestPersistence
 * @see eu.strasbourg.service.interest.service.persistence.impl.UserInterestPersistenceImpl
 * @generated
 */
@ProviderType
public class UserInterestUtil {
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
	public static void clearCache(UserInterest userInterest) {
		getPersistence().clearCache(userInterest);
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
	public static List<UserInterest> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserInterest> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserInterest> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserInterest> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserInterest update(UserInterest userInterest) {
		return getPersistence().update(userInterest);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserInterest update(UserInterest userInterest,
		ServiceContext serviceContext) {
		return getPersistence().update(userInterest, serviceContext);
	}

	/**
	* Returns all the user interests where interestId = &#63;.
	*
	* @param interestId the interest ID
	* @return the matching user interests
	*/
	public static List<UserInterest> findByInterestId(long interestId) {
		return getPersistence().findByInterestId(interestId);
	}

	/**
	* Returns a range of all the user interests where interestId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param interestId the interest ID
	* @param start the lower bound of the range of user interests
	* @param end the upper bound of the range of user interests (not inclusive)
	* @return the range of matching user interests
	*/
	public static List<UserInterest> findByInterestId(long interestId,
		int start, int end) {
		return getPersistence().findByInterestId(interestId, start, end);
	}

	/**
	* Returns an ordered range of all the user interests where interestId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param interestId the interest ID
	* @param start the lower bound of the range of user interests
	* @param end the upper bound of the range of user interests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user interests
	*/
	public static List<UserInterest> findByInterestId(long interestId,
		int start, int end, OrderByComparator<UserInterest> orderByComparator) {
		return getPersistence()
				   .findByInterestId(interestId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user interests where interestId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param interestId the interest ID
	* @param start the lower bound of the range of user interests
	* @param end the upper bound of the range of user interests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user interests
	*/
	public static List<UserInterest> findByInterestId(long interestId,
		int start, int end, OrderByComparator<UserInterest> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByInterestId(interestId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user interest in the ordered set where interestId = &#63;.
	*
	* @param interestId the interest ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user interest
	* @throws NoSuchUserInterestException if a matching user interest could not be found
	*/
	public static UserInterest findByInterestId_First(long interestId,
		OrderByComparator<UserInterest> orderByComparator)
		throws eu.strasbourg.service.interest.exception.NoSuchUserInterestException {
		return getPersistence()
				   .findByInterestId_First(interestId, orderByComparator);
	}

	/**
	* Returns the first user interest in the ordered set where interestId = &#63;.
	*
	* @param interestId the interest ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user interest, or <code>null</code> if a matching user interest could not be found
	*/
	public static UserInterest fetchByInterestId_First(long interestId,
		OrderByComparator<UserInterest> orderByComparator) {
		return getPersistence()
				   .fetchByInterestId_First(interestId, orderByComparator);
	}

	/**
	* Returns the last user interest in the ordered set where interestId = &#63;.
	*
	* @param interestId the interest ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user interest
	* @throws NoSuchUserInterestException if a matching user interest could not be found
	*/
	public static UserInterest findByInterestId_Last(long interestId,
		OrderByComparator<UserInterest> orderByComparator)
		throws eu.strasbourg.service.interest.exception.NoSuchUserInterestException {
		return getPersistence()
				   .findByInterestId_Last(interestId, orderByComparator);
	}

	/**
	* Returns the last user interest in the ordered set where interestId = &#63;.
	*
	* @param interestId the interest ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user interest, or <code>null</code> if a matching user interest could not be found
	*/
	public static UserInterest fetchByInterestId_Last(long interestId,
		OrderByComparator<UserInterest> orderByComparator) {
		return getPersistence()
				   .fetchByInterestId_Last(interestId, orderByComparator);
	}

	/**
	* Returns the user interests before and after the current user interest in the ordered set where interestId = &#63;.
	*
	* @param userInterestPK the primary key of the current user interest
	* @param interestId the interest ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user interest
	* @throws NoSuchUserInterestException if a user interest with the primary key could not be found
	*/
	public static UserInterest[] findByInterestId_PrevAndNext(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK userInterestPK,
		long interestId, OrderByComparator<UserInterest> orderByComparator)
		throws eu.strasbourg.service.interest.exception.NoSuchUserInterestException {
		return getPersistence()
				   .findByInterestId_PrevAndNext(userInterestPK, interestId,
			orderByComparator);
	}

	/**
	* Removes all the user interests where interestId = &#63; from the database.
	*
	* @param interestId the interest ID
	*/
	public static void removeByInterestId(long interestId) {
		getPersistence().removeByInterestId(interestId);
	}

	/**
	* Returns the number of user interests where interestId = &#63;.
	*
	* @param interestId the interest ID
	* @return the number of matching user interests
	*/
	public static int countByInterestId(long interestId) {
		return getPersistence().countByInterestId(interestId);
	}

	/**
	* Returns all the user interests where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the matching user interests
	*/
	public static List<UserInterest> findByPublikUserId(long publikUserId) {
		return getPersistence().findByPublikUserId(publikUserId);
	}

	/**
	* Returns a range of all the user interests where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of user interests
	* @param end the upper bound of the range of user interests (not inclusive)
	* @return the range of matching user interests
	*/
	public static List<UserInterest> findByPublikUserId(long publikUserId,
		int start, int end) {
		return getPersistence().findByPublikUserId(publikUserId, start, end);
	}

	/**
	* Returns an ordered range of all the user interests where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of user interests
	* @param end the upper bound of the range of user interests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user interests
	*/
	public static List<UserInterest> findByPublikUserId(long publikUserId,
		int start, int end, OrderByComparator<UserInterest> orderByComparator) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the user interests where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of user interests
	* @param end the upper bound of the range of user interests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user interests
	*/
	public static List<UserInterest> findByPublikUserId(long publikUserId,
		int start, int end, OrderByComparator<UserInterest> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first user interest in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user interest
	* @throws NoSuchUserInterestException if a matching user interest could not be found
	*/
	public static UserInterest findByPublikUserId_First(long publikUserId,
		OrderByComparator<UserInterest> orderByComparator)
		throws eu.strasbourg.service.interest.exception.NoSuchUserInterestException {
		return getPersistence()
				   .findByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the first user interest in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user interest, or <code>null</code> if a matching user interest could not be found
	*/
	public static UserInterest fetchByPublikUserId_First(long publikUserId,
		OrderByComparator<UserInterest> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the last user interest in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user interest
	* @throws NoSuchUserInterestException if a matching user interest could not be found
	*/
	public static UserInterest findByPublikUserId_Last(long publikUserId,
		OrderByComparator<UserInterest> orderByComparator)
		throws eu.strasbourg.service.interest.exception.NoSuchUserInterestException {
		return getPersistence()
				   .findByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the last user interest in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user interest, or <code>null</code> if a matching user interest could not be found
	*/
	public static UserInterest fetchByPublikUserId_Last(long publikUserId,
		OrderByComparator<UserInterest> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the user interests before and after the current user interest in the ordered set where publikUserId = &#63;.
	*
	* @param userInterestPK the primary key of the current user interest
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user interest
	* @throws NoSuchUserInterestException if a user interest with the primary key could not be found
	*/
	public static UserInterest[] findByPublikUserId_PrevAndNext(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK userInterestPK,
		long publikUserId, OrderByComparator<UserInterest> orderByComparator)
		throws eu.strasbourg.service.interest.exception.NoSuchUserInterestException {
		return getPersistence()
				   .findByPublikUserId_PrevAndNext(userInterestPK,
			publikUserId, orderByComparator);
	}

	/**
	* Removes all the user interests where publikUserId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	*/
	public static void removeByPublikUserId(long publikUserId) {
		getPersistence().removeByPublikUserId(publikUserId);
	}

	/**
	* Returns the number of user interests where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the number of matching user interests
	*/
	public static int countByPublikUserId(long publikUserId) {
		return getPersistence().countByPublikUserId(publikUserId);
	}

	/**
	* Caches the user interest in the entity cache if it is enabled.
	*
	* @param userInterest the user interest
	*/
	public static void cacheResult(UserInterest userInterest) {
		getPersistence().cacheResult(userInterest);
	}

	/**
	* Caches the user interests in the entity cache if it is enabled.
	*
	* @param userInterests the user interests
	*/
	public static void cacheResult(List<UserInterest> userInterests) {
		getPersistence().cacheResult(userInterests);
	}

	/**
	* Creates a new user interest with the primary key. Does not add the user interest to the database.
	*
	* @param userInterestPK the primary key for the new user interest
	* @return the new user interest
	*/
	public static UserInterest create(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK userInterestPK) {
		return getPersistence().create(userInterestPK);
	}

	/**
	* Removes the user interest with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userInterestPK the primary key of the user interest
	* @return the user interest that was removed
	* @throws NoSuchUserInterestException if a user interest with the primary key could not be found
	*/
	public static UserInterest remove(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK userInterestPK)
		throws eu.strasbourg.service.interest.exception.NoSuchUserInterestException {
		return getPersistence().remove(userInterestPK);
	}

	public static UserInterest updateImpl(UserInterest userInterest) {
		return getPersistence().updateImpl(userInterest);
	}

	/**
	* Returns the user interest with the primary key or throws a {@link NoSuchUserInterestException} if it could not be found.
	*
	* @param userInterestPK the primary key of the user interest
	* @return the user interest
	* @throws NoSuchUserInterestException if a user interest with the primary key could not be found
	*/
	public static UserInterest findByPrimaryKey(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK userInterestPK)
		throws eu.strasbourg.service.interest.exception.NoSuchUserInterestException {
		return getPersistence().findByPrimaryKey(userInterestPK);
	}

	/**
	* Returns the user interest with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userInterestPK the primary key of the user interest
	* @return the user interest, or <code>null</code> if a user interest with the primary key could not be found
	*/
	public static UserInterest fetchByPrimaryKey(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK userInterestPK) {
		return getPersistence().fetchByPrimaryKey(userInterestPK);
	}

	public static java.util.Map<java.io.Serializable, UserInterest> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the user interests.
	*
	* @return the user interests
	*/
	public static List<UserInterest> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the user interests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user interests
	* @param end the upper bound of the range of user interests (not inclusive)
	* @return the range of user interests
	*/
	public static List<UserInterest> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the user interests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user interests
	* @param end the upper bound of the range of user interests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user interests
	*/
	public static List<UserInterest> findAll(int start, int end,
		OrderByComparator<UserInterest> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user interests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserInterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user interests
	* @param end the upper bound of the range of user interests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of user interests
	*/
	public static List<UserInterest> findAll(int start, int end,
		OrderByComparator<UserInterest> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the user interests from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of user interests.
	*
	* @return the number of user interests
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static UserInterestPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<UserInterestPersistence, UserInterestPersistence> _serviceTracker =
		ServiceTrackerFactory.open(UserInterestPersistence.class);
}