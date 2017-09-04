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

import eu.strasbourg.service.interest.exception.NoSuchUserInterestException;
import eu.strasbourg.service.interest.model.UserInterest;

/**
 * The persistence interface for the user interest service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.interest.service.persistence.impl.UserInterestPersistenceImpl
 * @see UserInterestUtil
 * @generated
 */
@ProviderType
public interface UserInterestPersistence extends BasePersistence<UserInterest> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserInterestUtil} to access the user interest persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the user interests where interestId = &#63;.
	*
	* @param interestId the interest ID
	* @return the matching user interests
	*/
	public java.util.List<UserInterest> findByInterestId(long interestId);

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
	public java.util.List<UserInterest> findByInterestId(long interestId,
		int start, int end);

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
	public java.util.List<UserInterest> findByInterestId(long interestId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserInterest> orderByComparator);

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
	public java.util.List<UserInterest> findByInterestId(long interestId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserInterest> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user interest in the ordered set where interestId = &#63;.
	*
	* @param interestId the interest ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user interest
	* @throws NoSuchUserInterestException if a matching user interest could not be found
	*/
	public UserInterest findByInterestId_First(long interestId,
		com.liferay.portal.kernel.util.OrderByComparator<UserInterest> orderByComparator)
		throws NoSuchUserInterestException;

	/**
	* Returns the first user interest in the ordered set where interestId = &#63;.
	*
	* @param interestId the interest ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user interest, or <code>null</code> if a matching user interest could not be found
	*/
	public UserInterest fetchByInterestId_First(long interestId,
		com.liferay.portal.kernel.util.OrderByComparator<UserInterest> orderByComparator);

	/**
	* Returns the last user interest in the ordered set where interestId = &#63;.
	*
	* @param interestId the interest ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user interest
	* @throws NoSuchUserInterestException if a matching user interest could not be found
	*/
	public UserInterest findByInterestId_Last(long interestId,
		com.liferay.portal.kernel.util.OrderByComparator<UserInterest> orderByComparator)
		throws NoSuchUserInterestException;

	/**
	* Returns the last user interest in the ordered set where interestId = &#63;.
	*
	* @param interestId the interest ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user interest, or <code>null</code> if a matching user interest could not be found
	*/
	public UserInterest fetchByInterestId_Last(long interestId,
		com.liferay.portal.kernel.util.OrderByComparator<UserInterest> orderByComparator);

	/**
	* Returns the user interests before and after the current user interest in the ordered set where interestId = &#63;.
	*
	* @param userInterestPK the primary key of the current user interest
	* @param interestId the interest ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user interest
	* @throws NoSuchUserInterestException if a user interest with the primary key could not be found
	*/
	public UserInterest[] findByInterestId_PrevAndNext(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK userInterestPK,
		long interestId,
		com.liferay.portal.kernel.util.OrderByComparator<UserInterest> orderByComparator)
		throws NoSuchUserInterestException;

	/**
	* Removes all the user interests where interestId = &#63; from the database.
	*
	* @param interestId the interest ID
	*/
	public void removeByInterestId(long interestId);

	/**
	* Returns the number of user interests where interestId = &#63;.
	*
	* @param interestId the interest ID
	* @return the number of matching user interests
	*/
	public int countByInterestId(long interestId);

	/**
	* Returns all the user interests where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the matching user interests
	*/
	public java.util.List<UserInterest> findByPublikUserId(long publikUserId);

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
	public java.util.List<UserInterest> findByPublikUserId(long publikUserId,
		int start, int end);

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
	public java.util.List<UserInterest> findByPublikUserId(long publikUserId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserInterest> orderByComparator);

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
	public java.util.List<UserInterest> findByPublikUserId(long publikUserId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserInterest> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user interest in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user interest
	* @throws NoSuchUserInterestException if a matching user interest could not be found
	*/
	public UserInterest findByPublikUserId_First(long publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<UserInterest> orderByComparator)
		throws NoSuchUserInterestException;

	/**
	* Returns the first user interest in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user interest, or <code>null</code> if a matching user interest could not be found
	*/
	public UserInterest fetchByPublikUserId_First(long publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<UserInterest> orderByComparator);

	/**
	* Returns the last user interest in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user interest
	* @throws NoSuchUserInterestException if a matching user interest could not be found
	*/
	public UserInterest findByPublikUserId_Last(long publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<UserInterest> orderByComparator)
		throws NoSuchUserInterestException;

	/**
	* Returns the last user interest in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user interest, or <code>null</code> if a matching user interest could not be found
	*/
	public UserInterest fetchByPublikUserId_Last(long publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<UserInterest> orderByComparator);

	/**
	* Returns the user interests before and after the current user interest in the ordered set where publikUserId = &#63;.
	*
	* @param userInterestPK the primary key of the current user interest
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user interest
	* @throws NoSuchUserInterestException if a user interest with the primary key could not be found
	*/
	public UserInterest[] findByPublikUserId_PrevAndNext(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK userInterestPK,
		long publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<UserInterest> orderByComparator)
		throws NoSuchUserInterestException;

	/**
	* Removes all the user interests where publikUserId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	*/
	public void removeByPublikUserId(long publikUserId);

	/**
	* Returns the number of user interests where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the number of matching user interests
	*/
	public int countByPublikUserId(long publikUserId);

	/**
	* Caches the user interest in the entity cache if it is enabled.
	*
	* @param userInterest the user interest
	*/
	public void cacheResult(UserInterest userInterest);

	/**
	* Caches the user interests in the entity cache if it is enabled.
	*
	* @param userInterests the user interests
	*/
	public void cacheResult(java.util.List<UserInterest> userInterests);

	/**
	* Creates a new user interest with the primary key. Does not add the user interest to the database.
	*
	* @param userInterestPK the primary key for the new user interest
	* @return the new user interest
	*/
	public UserInterest create(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK userInterestPK);

	/**
	* Removes the user interest with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userInterestPK the primary key of the user interest
	* @return the user interest that was removed
	* @throws NoSuchUserInterestException if a user interest with the primary key could not be found
	*/
	public UserInterest remove(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK userInterestPK)
		throws NoSuchUserInterestException;

	public UserInterest updateImpl(UserInterest userInterest);

	/**
	* Returns the user interest with the primary key or throws a {@link NoSuchUserInterestException} if it could not be found.
	*
	* @param userInterestPK the primary key of the user interest
	* @return the user interest
	* @throws NoSuchUserInterestException if a user interest with the primary key could not be found
	*/
	public UserInterest findByPrimaryKey(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK userInterestPK)
		throws NoSuchUserInterestException;

	/**
	* Returns the user interest with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userInterestPK the primary key of the user interest
	* @return the user interest, or <code>null</code> if a user interest with the primary key could not be found
	*/
	public UserInterest fetchByPrimaryKey(
		eu.strasbourg.service.interest.service.persistence.UserInterestPK userInterestPK);

	@Override
	public java.util.Map<java.io.Serializable, UserInterest> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the user interests.
	*
	* @return the user interests
	*/
	public java.util.List<UserInterest> findAll();

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
	public java.util.List<UserInterest> findAll(int start, int end);

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
	public java.util.List<UserInterest> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserInterest> orderByComparator);

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
	public java.util.List<UserInterest> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserInterest> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the user interests from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of user interests.
	*
	* @return the number of user interests
	*/
	public int countAll();
}