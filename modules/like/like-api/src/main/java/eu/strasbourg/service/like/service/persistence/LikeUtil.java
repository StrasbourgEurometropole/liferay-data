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

package eu.strasbourg.service.like.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.like.model.Like;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the like service. This utility wraps {@link eu.strasbourg.service.like.service.persistence.impl.LikePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see LikePersistence
 * @see eu.strasbourg.service.like.service.persistence.impl.LikePersistenceImpl
 * @generated
 */
@ProviderType
public class LikeUtil {
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
	public static void clearCache(Like like) {
		getPersistence().clearCache(like);
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
	public static List<Like> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Like> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Like> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Like> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Like update(Like like) {
		return getPersistence().update(like);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Like update(Like like, ServiceContext serviceContext) {
		return getPersistence().update(like, serviceContext);
	}

	/**
	* Returns all the likes where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the matching likes
	*/
	public static List<Like> findByPublikUserId(java.lang.String publikUserId) {
		return getPersistence().findByPublikUserId(publikUserId);
	}

	/**
	* Returns a range of all the likes where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of likes
	* @param end the upper bound of the range of likes (not inclusive)
	* @return the range of matching likes
	*/
	public static List<Like> findByPublikUserId(java.lang.String publikUserId,
		int start, int end) {
		return getPersistence().findByPublikUserId(publikUserId, start, end);
	}

	/**
	* Returns an ordered range of all the likes where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of likes
	* @param end the upper bound of the range of likes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching likes
	*/
	public static List<Like> findByPublikUserId(java.lang.String publikUserId,
		int start, int end, OrderByComparator<Like> orderByComparator) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the likes where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of likes
	* @param end the upper bound of the range of likes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching likes
	*/
	public static List<Like> findByPublikUserId(java.lang.String publikUserId,
		int start, int end, OrderByComparator<Like> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first like in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching like
	* @throws NoSuchLikeException if a matching like could not be found
	*/
	public static Like findByPublikUserId_First(java.lang.String publikUserId,
		OrderByComparator<Like> orderByComparator)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence()
				   .findByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the first like in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching like, or <code>null</code> if a matching like could not be found
	*/
	public static Like fetchByPublikUserId_First(
		java.lang.String publikUserId, OrderByComparator<Like> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the last like in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching like
	* @throws NoSuchLikeException if a matching like could not be found
	*/
	public static Like findByPublikUserId_Last(java.lang.String publikUserId,
		OrderByComparator<Like> orderByComparator)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence()
				   .findByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the last like in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching like, or <code>null</code> if a matching like could not be found
	*/
	public static Like fetchByPublikUserId_Last(java.lang.String publikUserId,
		OrderByComparator<Like> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the likes before and after the current like in the ordered set where publikUserId = &#63;.
	*
	* @param likeId the primary key of the current like
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next like
	* @throws NoSuchLikeException if a like with the primary key could not be found
	*/
	public static Like[] findByPublikUserId_PrevAndNext(long likeId,
		java.lang.String publikUserId, OrderByComparator<Like> orderByComparator)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence()
				   .findByPublikUserId_PrevAndNext(likeId, publikUserId,
			orderByComparator);
	}

	/**
	* Removes all the likes where publikUserId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	*/
	public static void removeByPublikUserId(java.lang.String publikUserId) {
		getPersistence().removeByPublikUserId(publikUserId);
	}

	/**
	* Returns the number of likes where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the number of matching likes
	*/
	public static int countByPublikUserId(java.lang.String publikUserId) {
		return getPersistence().countByPublikUserId(publikUserId);
	}

	/**
	* Returns all the likes where publikUserId = &#63; and isDislike = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	* @return the matching likes
	*/
	public static List<Like> findByPublikUserIdAndIsDislike(
		java.lang.String publikUserId, boolean isDislike) {
		return getPersistence()
				   .findByPublikUserIdAndIsDislike(publikUserId, isDislike);
	}

	/**
	* Returns a range of all the likes where publikUserId = &#63; and isDislike = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	* @param start the lower bound of the range of likes
	* @param end the upper bound of the range of likes (not inclusive)
	* @return the range of matching likes
	*/
	public static List<Like> findByPublikUserIdAndIsDislike(
		java.lang.String publikUserId, boolean isDislike, int start, int end) {
		return getPersistence()
				   .findByPublikUserIdAndIsDislike(publikUserId, isDislike,
			start, end);
	}

	/**
	* Returns an ordered range of all the likes where publikUserId = &#63; and isDislike = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	* @param start the lower bound of the range of likes
	* @param end the upper bound of the range of likes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching likes
	*/
	public static List<Like> findByPublikUserIdAndIsDislike(
		java.lang.String publikUserId, boolean isDislike, int start, int end,
		OrderByComparator<Like> orderByComparator) {
		return getPersistence()
				   .findByPublikUserIdAndIsDislike(publikUserId, isDislike,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the likes where publikUserId = &#63; and isDislike = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	* @param start the lower bound of the range of likes
	* @param end the upper bound of the range of likes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching likes
	*/
	public static List<Like> findByPublikUserIdAndIsDislike(
		java.lang.String publikUserId, boolean isDislike, int start, int end,
		OrderByComparator<Like> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByPublikUserIdAndIsDislike(publikUserId, isDislike,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first like in the ordered set where publikUserId = &#63; and isDislike = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching like
	* @throws NoSuchLikeException if a matching like could not be found
	*/
	public static Like findByPublikUserIdAndIsDislike_First(
		java.lang.String publikUserId, boolean isDislike,
		OrderByComparator<Like> orderByComparator)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence()
				   .findByPublikUserIdAndIsDislike_First(publikUserId,
			isDislike, orderByComparator);
	}

	/**
	* Returns the first like in the ordered set where publikUserId = &#63; and isDislike = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching like, or <code>null</code> if a matching like could not be found
	*/
	public static Like fetchByPublikUserIdAndIsDislike_First(
		java.lang.String publikUserId, boolean isDislike,
		OrderByComparator<Like> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserIdAndIsDislike_First(publikUserId,
			isDislike, orderByComparator);
	}

	/**
	* Returns the last like in the ordered set where publikUserId = &#63; and isDislike = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching like
	* @throws NoSuchLikeException if a matching like could not be found
	*/
	public static Like findByPublikUserIdAndIsDislike_Last(
		java.lang.String publikUserId, boolean isDislike,
		OrderByComparator<Like> orderByComparator)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence()
				   .findByPublikUserIdAndIsDislike_Last(publikUserId,
			isDislike, orderByComparator);
	}

	/**
	* Returns the last like in the ordered set where publikUserId = &#63; and isDislike = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching like, or <code>null</code> if a matching like could not be found
	*/
	public static Like fetchByPublikUserIdAndIsDislike_Last(
		java.lang.String publikUserId, boolean isDislike,
		OrderByComparator<Like> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserIdAndIsDislike_Last(publikUserId,
			isDislike, orderByComparator);
	}

	/**
	* Returns the likes before and after the current like in the ordered set where publikUserId = &#63; and isDislike = &#63;.
	*
	* @param likeId the primary key of the current like
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next like
	* @throws NoSuchLikeException if a like with the primary key could not be found
	*/
	public static Like[] findByPublikUserIdAndIsDislike_PrevAndNext(
		long likeId, java.lang.String publikUserId, boolean isDislike,
		OrderByComparator<Like> orderByComparator)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence()
				   .findByPublikUserIdAndIsDislike_PrevAndNext(likeId,
			publikUserId, isDislike, orderByComparator);
	}

	/**
	* Removes all the likes where publikUserId = &#63; and isDislike = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	*/
	public static void removeByPublikUserIdAndIsDislike(
		java.lang.String publikUserId, boolean isDislike) {
		getPersistence()
			.removeByPublikUserIdAndIsDislike(publikUserId, isDislike);
	}

	/**
	* Returns the number of likes where publikUserId = &#63; and isDislike = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	* @return the number of matching likes
	*/
	public static int countByPublikUserIdAndIsDislike(
		java.lang.String publikUserId, boolean isDislike) {
		return getPersistence()
				   .countByPublikUserIdAndIsDislike(publikUserId, isDislike);
	}

	/**
	* Returns the like where publikUserId = &#63; and title = &#63; and isDislike = &#63; and typeId = &#63; and entityId = &#63; or throws a {@link NoSuchLikeException} if it could not be found.
	*
	* @param publikUserId the publik user ID
	* @param title the title
	* @param isDislike the is dislike
	* @param typeId the type ID
	* @param entityId the entity ID
	* @return the matching like
	* @throws NoSuchLikeException if a matching like could not be found
	*/
	public static Like findByAllAttributes(java.lang.String publikUserId,
		java.lang.String title, boolean isDislike, long typeId, long entityId)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence()
				   .findByAllAttributes(publikUserId, title, isDislike, typeId,
			entityId);
	}

	/**
	* Returns the like where publikUserId = &#63; and title = &#63; and isDislike = &#63; and typeId = &#63; and entityId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param publikUserId the publik user ID
	* @param title the title
	* @param isDislike the is dislike
	* @param typeId the type ID
	* @param entityId the entity ID
	* @return the matching like, or <code>null</code> if a matching like could not be found
	*/
	public static Like fetchByAllAttributes(java.lang.String publikUserId,
		java.lang.String title, boolean isDislike, long typeId, long entityId) {
		return getPersistence()
				   .fetchByAllAttributes(publikUserId, title, isDislike,
			typeId, entityId);
	}

	/**
	* Returns the like where publikUserId = &#63; and title = &#63; and isDislike = &#63; and typeId = &#63; and entityId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param publikUserId the publik user ID
	* @param title the title
	* @param isDislike the is dislike
	* @param typeId the type ID
	* @param entityId the entity ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching like, or <code>null</code> if a matching like could not be found
	*/
	public static Like fetchByAllAttributes(java.lang.String publikUserId,
		java.lang.String title, boolean isDislike, long typeId, long entityId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByAllAttributes(publikUserId, title, isDislike,
			typeId, entityId, retrieveFromCache);
	}

	/**
	* Removes the like where publikUserId = &#63; and title = &#63; and isDislike = &#63; and typeId = &#63; and entityId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	* @param title the title
	* @param isDislike the is dislike
	* @param typeId the type ID
	* @param entityId the entity ID
	* @return the like that was removed
	*/
	public static Like removeByAllAttributes(java.lang.String publikUserId,
		java.lang.String title, boolean isDislike, long typeId, long entityId)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence()
				   .removeByAllAttributes(publikUserId, title, isDislike,
			typeId, entityId);
	}

	/**
	* Returns the number of likes where publikUserId = &#63; and title = &#63; and isDislike = &#63; and typeId = &#63; and entityId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param title the title
	* @param isDislike the is dislike
	* @param typeId the type ID
	* @param entityId the entity ID
	* @return the number of matching likes
	*/
	public static int countByAllAttributes(java.lang.String publikUserId,
		java.lang.String title, boolean isDislike, long typeId, long entityId) {
		return getPersistence()
				   .countByAllAttributes(publikUserId, title, isDislike,
			typeId, entityId);
	}

	/**
	* Returns the like where publikUserId = &#63; and title = &#63; and typeId = &#63; and entityId = &#63; or throws a {@link NoSuchLikeException} if it could not be found.
	*
	* @param publikUserId the publik user ID
	* @param title the title
	* @param typeId the type ID
	* @param entityId the entity ID
	* @return the matching like
	* @throws NoSuchLikeException if a matching like could not be found
	*/
	public static Like findByAllAttributesExceptIsDislike(
		java.lang.String publikUserId, java.lang.String title, long typeId,
		long entityId)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence()
				   .findByAllAttributesExceptIsDislike(publikUserId, title,
			typeId, entityId);
	}

	/**
	* Returns the like where publikUserId = &#63; and title = &#63; and typeId = &#63; and entityId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param publikUserId the publik user ID
	* @param title the title
	* @param typeId the type ID
	* @param entityId the entity ID
	* @return the matching like, or <code>null</code> if a matching like could not be found
	*/
	public static Like fetchByAllAttributesExceptIsDislike(
		java.lang.String publikUserId, java.lang.String title, long typeId,
		long entityId) {
		return getPersistence()
				   .fetchByAllAttributesExceptIsDislike(publikUserId, title,
			typeId, entityId);
	}

	/**
	* Returns the like where publikUserId = &#63; and title = &#63; and typeId = &#63; and entityId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param publikUserId the publik user ID
	* @param title the title
	* @param typeId the type ID
	* @param entityId the entity ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching like, or <code>null</code> if a matching like could not be found
	*/
	public static Like fetchByAllAttributesExceptIsDislike(
		java.lang.String publikUserId, java.lang.String title, long typeId,
		long entityId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByAllAttributesExceptIsDislike(publikUserId, title,
			typeId, entityId, retrieveFromCache);
	}

	/**
	* Removes the like where publikUserId = &#63; and title = &#63; and typeId = &#63; and entityId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	* @param title the title
	* @param typeId the type ID
	* @param entityId the entity ID
	* @return the like that was removed
	*/
	public static Like removeByAllAttributesExceptIsDislike(
		java.lang.String publikUserId, java.lang.String title, long typeId,
		long entityId)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence()
				   .removeByAllAttributesExceptIsDislike(publikUserId, title,
			typeId, entityId);
	}

	/**
	* Returns the number of likes where publikUserId = &#63; and title = &#63; and typeId = &#63; and entityId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param title the title
	* @param typeId the type ID
	* @param entityId the entity ID
	* @return the number of matching likes
	*/
	public static int countByAllAttributesExceptIsDislike(
		java.lang.String publikUserId, java.lang.String title, long typeId,
		long entityId) {
		return getPersistence()
				   .countByAllAttributesExceptIsDislike(publikUserId, title,
			typeId, entityId);
	}

	/**
	* Returns all the likes where entityId = &#63; and typeId = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @return the matching likes
	*/
	public static List<Like> findByEntityIdAndTypeId(long entityId, long typeId) {
		return getPersistence().findByEntityIdAndTypeId(entityId, typeId);
	}

	/**
	* Returns a range of all the likes where entityId = &#63; and typeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param start the lower bound of the range of likes
	* @param end the upper bound of the range of likes (not inclusive)
	* @return the range of matching likes
	*/
	public static List<Like> findByEntityIdAndTypeId(long entityId,
		long typeId, int start, int end) {
		return getPersistence()
				   .findByEntityIdAndTypeId(entityId, typeId, start, end);
	}

	/**
	* Returns an ordered range of all the likes where entityId = &#63; and typeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param start the lower bound of the range of likes
	* @param end the upper bound of the range of likes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching likes
	*/
	public static List<Like> findByEntityIdAndTypeId(long entityId,
		long typeId, int start, int end,
		OrderByComparator<Like> orderByComparator) {
		return getPersistence()
				   .findByEntityIdAndTypeId(entityId, typeId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the likes where entityId = &#63; and typeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param start the lower bound of the range of likes
	* @param end the upper bound of the range of likes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching likes
	*/
	public static List<Like> findByEntityIdAndTypeId(long entityId,
		long typeId, int start, int end,
		OrderByComparator<Like> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByEntityIdAndTypeId(entityId, typeId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first like in the ordered set where entityId = &#63; and typeId = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching like
	* @throws NoSuchLikeException if a matching like could not be found
	*/
	public static Like findByEntityIdAndTypeId_First(long entityId,
		long typeId, OrderByComparator<Like> orderByComparator)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence()
				   .findByEntityIdAndTypeId_First(entityId, typeId,
			orderByComparator);
	}

	/**
	* Returns the first like in the ordered set where entityId = &#63; and typeId = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching like, or <code>null</code> if a matching like could not be found
	*/
	public static Like fetchByEntityIdAndTypeId_First(long entityId,
		long typeId, OrderByComparator<Like> orderByComparator) {
		return getPersistence()
				   .fetchByEntityIdAndTypeId_First(entityId, typeId,
			orderByComparator);
	}

	/**
	* Returns the last like in the ordered set where entityId = &#63; and typeId = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching like
	* @throws NoSuchLikeException if a matching like could not be found
	*/
	public static Like findByEntityIdAndTypeId_Last(long entityId, long typeId,
		OrderByComparator<Like> orderByComparator)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence()
				   .findByEntityIdAndTypeId_Last(entityId, typeId,
			orderByComparator);
	}

	/**
	* Returns the last like in the ordered set where entityId = &#63; and typeId = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching like, or <code>null</code> if a matching like could not be found
	*/
	public static Like fetchByEntityIdAndTypeId_Last(long entityId,
		long typeId, OrderByComparator<Like> orderByComparator) {
		return getPersistence()
				   .fetchByEntityIdAndTypeId_Last(entityId, typeId,
			orderByComparator);
	}

	/**
	* Returns the likes before and after the current like in the ordered set where entityId = &#63; and typeId = &#63;.
	*
	* @param likeId the primary key of the current like
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next like
	* @throws NoSuchLikeException if a like with the primary key could not be found
	*/
	public static Like[] findByEntityIdAndTypeId_PrevAndNext(long likeId,
		long entityId, long typeId, OrderByComparator<Like> orderByComparator)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence()
				   .findByEntityIdAndTypeId_PrevAndNext(likeId, entityId,
			typeId, orderByComparator);
	}

	/**
	* Removes all the likes where entityId = &#63; and typeId = &#63; from the database.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	*/
	public static void removeByEntityIdAndTypeId(long entityId, long typeId) {
		getPersistence().removeByEntityIdAndTypeId(entityId, typeId);
	}

	/**
	* Returns the number of likes where entityId = &#63; and typeId = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @return the number of matching likes
	*/
	public static int countByEntityIdAndTypeId(long entityId, long typeId) {
		return getPersistence().countByEntityIdAndTypeId(entityId, typeId);
	}

	/**
	* Returns all the likes where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param isDislike the is dislike
	* @return the matching likes
	*/
	public static List<Like> findByEntityIdAndTypeIdAndIsDislike(
		long entityId, long typeId, boolean isDislike) {
		return getPersistence()
				   .findByEntityIdAndTypeIdAndIsDislike(entityId, typeId,
			isDislike);
	}

	/**
	* Returns a range of all the likes where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param isDislike the is dislike
	* @param start the lower bound of the range of likes
	* @param end the upper bound of the range of likes (not inclusive)
	* @return the range of matching likes
	*/
	public static List<Like> findByEntityIdAndTypeIdAndIsDislike(
		long entityId, long typeId, boolean isDislike, int start, int end) {
		return getPersistence()
				   .findByEntityIdAndTypeIdAndIsDislike(entityId, typeId,
			isDislike, start, end);
	}

	/**
	* Returns an ordered range of all the likes where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param isDislike the is dislike
	* @param start the lower bound of the range of likes
	* @param end the upper bound of the range of likes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching likes
	*/
	public static List<Like> findByEntityIdAndTypeIdAndIsDislike(
		long entityId, long typeId, boolean isDislike, int start, int end,
		OrderByComparator<Like> orderByComparator) {
		return getPersistence()
				   .findByEntityIdAndTypeIdAndIsDislike(entityId, typeId,
			isDislike, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the likes where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param isDislike the is dislike
	* @param start the lower bound of the range of likes
	* @param end the upper bound of the range of likes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching likes
	*/
	public static List<Like> findByEntityIdAndTypeIdAndIsDislike(
		long entityId, long typeId, boolean isDislike, int start, int end,
		OrderByComparator<Like> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByEntityIdAndTypeIdAndIsDislike(entityId, typeId,
			isDislike, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first like in the ordered set where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param isDislike the is dislike
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching like
	* @throws NoSuchLikeException if a matching like could not be found
	*/
	public static Like findByEntityIdAndTypeIdAndIsDislike_First(
		long entityId, long typeId, boolean isDislike,
		OrderByComparator<Like> orderByComparator)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence()
				   .findByEntityIdAndTypeIdAndIsDislike_First(entityId, typeId,
			isDislike, orderByComparator);
	}

	/**
	* Returns the first like in the ordered set where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param isDislike the is dislike
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching like, or <code>null</code> if a matching like could not be found
	*/
	public static Like fetchByEntityIdAndTypeIdAndIsDislike_First(
		long entityId, long typeId, boolean isDislike,
		OrderByComparator<Like> orderByComparator) {
		return getPersistence()
				   .fetchByEntityIdAndTypeIdAndIsDislike_First(entityId,
			typeId, isDislike, orderByComparator);
	}

	/**
	* Returns the last like in the ordered set where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param isDislike the is dislike
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching like
	* @throws NoSuchLikeException if a matching like could not be found
	*/
	public static Like findByEntityIdAndTypeIdAndIsDislike_Last(long entityId,
		long typeId, boolean isDislike,
		OrderByComparator<Like> orderByComparator)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence()
				   .findByEntityIdAndTypeIdAndIsDislike_Last(entityId, typeId,
			isDislike, orderByComparator);
	}

	/**
	* Returns the last like in the ordered set where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param isDislike the is dislike
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching like, or <code>null</code> if a matching like could not be found
	*/
	public static Like fetchByEntityIdAndTypeIdAndIsDislike_Last(
		long entityId, long typeId, boolean isDislike,
		OrderByComparator<Like> orderByComparator) {
		return getPersistence()
				   .fetchByEntityIdAndTypeIdAndIsDislike_Last(entityId, typeId,
			isDislike, orderByComparator);
	}

	/**
	* Returns the likes before and after the current like in the ordered set where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	*
	* @param likeId the primary key of the current like
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param isDislike the is dislike
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next like
	* @throws NoSuchLikeException if a like with the primary key could not be found
	*/
	public static Like[] findByEntityIdAndTypeIdAndIsDislike_PrevAndNext(
		long likeId, long entityId, long typeId, boolean isDislike,
		OrderByComparator<Like> orderByComparator)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence()
				   .findByEntityIdAndTypeIdAndIsDislike_PrevAndNext(likeId,
			entityId, typeId, isDislike, orderByComparator);
	}

	/**
	* Removes all the likes where entityId = &#63; and typeId = &#63; and isDislike = &#63; from the database.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param isDislike the is dislike
	*/
	public static void removeByEntityIdAndTypeIdAndIsDislike(long entityId,
		long typeId, boolean isDislike) {
		getPersistence()
			.removeByEntityIdAndTypeIdAndIsDislike(entityId, typeId, isDislike);
	}

	/**
	* Returns the number of likes where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param isDislike the is dislike
	* @return the number of matching likes
	*/
	public static int countByEntityIdAndTypeIdAndIsDislike(long entityId,
		long typeId, boolean isDislike) {
		return getPersistence()
				   .countByEntityIdAndTypeIdAndIsDislike(entityId, typeId,
			isDislike);
	}

	/**
	* Caches the like in the entity cache if it is enabled.
	*
	* @param like the like
	*/
	public static void cacheResult(Like like) {
		getPersistence().cacheResult(like);
	}

	/**
	* Caches the likes in the entity cache if it is enabled.
	*
	* @param likes the likes
	*/
	public static void cacheResult(List<Like> likes) {
		getPersistence().cacheResult(likes);
	}

	/**
	* Creates a new like with the primary key. Does not add the like to the database.
	*
	* @param likeId the primary key for the new like
	* @return the new like
	*/
	public static Like create(long likeId) {
		return getPersistence().create(likeId);
	}

	/**
	* Removes the like with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param likeId the primary key of the like
	* @return the like that was removed
	* @throws NoSuchLikeException if a like with the primary key could not be found
	*/
	public static Like remove(long likeId)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence().remove(likeId);
	}

	public static Like updateImpl(Like like) {
		return getPersistence().updateImpl(like);
	}

	/**
	* Returns the like with the primary key or throws a {@link NoSuchLikeException} if it could not be found.
	*
	* @param likeId the primary key of the like
	* @return the like
	* @throws NoSuchLikeException if a like with the primary key could not be found
	*/
	public static Like findByPrimaryKey(long likeId)
		throws eu.strasbourg.service.like.exception.NoSuchLikeException {
		return getPersistence().findByPrimaryKey(likeId);
	}

	/**
	* Returns the like with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param likeId the primary key of the like
	* @return the like, or <code>null</code> if a like with the primary key could not be found
	*/
	public static Like fetchByPrimaryKey(long likeId) {
		return getPersistence().fetchByPrimaryKey(likeId);
	}

	public static java.util.Map<java.io.Serializable, Like> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the likes.
	*
	* @return the likes
	*/
	public static List<Like> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the likes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of likes
	* @param end the upper bound of the range of likes (not inclusive)
	* @return the range of likes
	*/
	public static List<Like> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the likes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of likes
	* @param end the upper bound of the range of likes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of likes
	*/
	public static List<Like> findAll(int start, int end,
		OrderByComparator<Like> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the likes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of likes
	* @param end the upper bound of the range of likes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of likes
	*/
	public static List<Like> findAll(int start, int end,
		OrderByComparator<Like> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the likes from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of likes.
	*
	* @return the number of likes
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LikePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LikePersistence, LikePersistence> _serviceTracker =
		ServiceTrackerFactory.open(LikePersistence.class);
}