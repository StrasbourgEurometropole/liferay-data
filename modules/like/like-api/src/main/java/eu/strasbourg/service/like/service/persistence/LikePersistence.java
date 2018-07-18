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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.like.exception.NoSuchLikeException;
import eu.strasbourg.service.like.model.Like;

/**
 * The persistence interface for the like service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.like.service.persistence.impl.LikePersistenceImpl
 * @see LikeUtil
 * @generated
 */
@ProviderType
public interface LikePersistence extends BasePersistence<Like> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LikeUtil} to access the like persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the likes where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the matching likes
	*/
	public java.util.List<Like> findByPublikUserId(
		java.lang.String publikUserId);

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
	public java.util.List<Like> findByPublikUserId(
		java.lang.String publikUserId, int start, int end);

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
	public java.util.List<Like> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator);

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
	public java.util.List<Like> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first like in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching like
	* @throws NoSuchLikeException if a matching like could not be found
	*/
	public Like findByPublikUserId_First(java.lang.String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator)
		throws NoSuchLikeException;

	/**
	* Returns the first like in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching like, or <code>null</code> if a matching like could not be found
	*/
	public Like fetchByPublikUserId_First(java.lang.String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator);

	/**
	* Returns the last like in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching like
	* @throws NoSuchLikeException if a matching like could not be found
	*/
	public Like findByPublikUserId_Last(java.lang.String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator)
		throws NoSuchLikeException;

	/**
	* Returns the last like in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching like, or <code>null</code> if a matching like could not be found
	*/
	public Like fetchByPublikUserId_Last(java.lang.String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator);

	/**
	* Returns the likes before and after the current like in the ordered set where publikUserId = &#63;.
	*
	* @param likeId the primary key of the current like
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next like
	* @throws NoSuchLikeException if a like with the primary key could not be found
	*/
	public Like[] findByPublikUserId_PrevAndNext(long likeId,
		java.lang.String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator)
		throws NoSuchLikeException;

	/**
	* Removes all the likes where publikUserId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	*/
	public void removeByPublikUserId(java.lang.String publikUserId);

	/**
	* Returns the number of likes where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the number of matching likes
	*/
	public int countByPublikUserId(java.lang.String publikUserId);

	/**
	* Returns all the likes where publikUserId = &#63; and isDislike = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	* @return the matching likes
	*/
	public java.util.List<Like> findByPublikUserIdAndIsDislike(
		java.lang.String publikUserId, boolean isDislike);

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
	public java.util.List<Like> findByPublikUserIdAndIsDislike(
		java.lang.String publikUserId, boolean isDislike, int start, int end);

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
	public java.util.List<Like> findByPublikUserIdAndIsDislike(
		java.lang.String publikUserId, boolean isDislike, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator);

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
	public java.util.List<Like> findByPublikUserIdAndIsDislike(
		java.lang.String publikUserId, boolean isDislike, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first like in the ordered set where publikUserId = &#63; and isDislike = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching like
	* @throws NoSuchLikeException if a matching like could not be found
	*/
	public Like findByPublikUserIdAndIsDislike_First(
		java.lang.String publikUserId, boolean isDislike,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator)
		throws NoSuchLikeException;

	/**
	* Returns the first like in the ordered set where publikUserId = &#63; and isDislike = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching like, or <code>null</code> if a matching like could not be found
	*/
	public Like fetchByPublikUserIdAndIsDislike_First(
		java.lang.String publikUserId, boolean isDislike,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator);

	/**
	* Returns the last like in the ordered set where publikUserId = &#63; and isDislike = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching like
	* @throws NoSuchLikeException if a matching like could not be found
	*/
	public Like findByPublikUserIdAndIsDislike_Last(
		java.lang.String publikUserId, boolean isDislike,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator)
		throws NoSuchLikeException;

	/**
	* Returns the last like in the ordered set where publikUserId = &#63; and isDislike = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching like, or <code>null</code> if a matching like could not be found
	*/
	public Like fetchByPublikUserIdAndIsDislike_Last(
		java.lang.String publikUserId, boolean isDislike,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator);

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
	public Like[] findByPublikUserIdAndIsDislike_PrevAndNext(long likeId,
		java.lang.String publikUserId, boolean isDislike,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator)
		throws NoSuchLikeException;

	/**
	* Removes all the likes where publikUserId = &#63; and isDislike = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	*/
	public void removeByPublikUserIdAndIsDislike(
		java.lang.String publikUserId, boolean isDislike);

	/**
	* Returns the number of likes where publikUserId = &#63; and isDislike = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param isDislike the is dislike
	* @return the number of matching likes
	*/
	public int countByPublikUserIdAndIsDislike(java.lang.String publikUserId,
		boolean isDislike);

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
	public Like findByAllAttributes(java.lang.String publikUserId,
		java.lang.String title, boolean isDislike, long typeId, long entityId)
		throws NoSuchLikeException;

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
	public Like fetchByAllAttributes(java.lang.String publikUserId,
		java.lang.String title, boolean isDislike, long typeId, long entityId);

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
	public Like fetchByAllAttributes(java.lang.String publikUserId,
		java.lang.String title, boolean isDislike, long typeId, long entityId,
		boolean retrieveFromCache);

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
	public Like removeByAllAttributes(java.lang.String publikUserId,
		java.lang.String title, boolean isDislike, long typeId, long entityId)
		throws NoSuchLikeException;

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
	public int countByAllAttributes(java.lang.String publikUserId,
		java.lang.String title, boolean isDislike, long typeId, long entityId);

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
	public Like findByAllAttributesExceptIsDislike(
		java.lang.String publikUserId, java.lang.String title, long typeId,
		long entityId) throws NoSuchLikeException;

	/**
	* Returns the like where publikUserId = &#63; and title = &#63; and typeId = &#63; and entityId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param publikUserId the publik user ID
	* @param title the title
	* @param typeId the type ID
	* @param entityId the entity ID
	* @return the matching like, or <code>null</code> if a matching like could not be found
	*/
	public Like fetchByAllAttributesExceptIsDislike(
		java.lang.String publikUserId, java.lang.String title, long typeId,
		long entityId);

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
	public Like fetchByAllAttributesExceptIsDislike(
		java.lang.String publikUserId, java.lang.String title, long typeId,
		long entityId, boolean retrieveFromCache);

	/**
	* Removes the like where publikUserId = &#63; and title = &#63; and typeId = &#63; and entityId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	* @param title the title
	* @param typeId the type ID
	* @param entityId the entity ID
	* @return the like that was removed
	*/
	public Like removeByAllAttributesExceptIsDislike(
		java.lang.String publikUserId, java.lang.String title, long typeId,
		long entityId) throws NoSuchLikeException;

	/**
	* Returns the number of likes where publikUserId = &#63; and title = &#63; and typeId = &#63; and entityId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param title the title
	* @param typeId the type ID
	* @param entityId the entity ID
	* @return the number of matching likes
	*/
	public int countByAllAttributesExceptIsDislike(
		java.lang.String publikUserId, java.lang.String title, long typeId,
		long entityId);

	/**
	* Returns all the likes where entityId = &#63; and typeId = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @return the matching likes
	*/
	public java.util.List<Like> findByEntityIdAndTypeId(long entityId,
		long typeId);

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
	public java.util.List<Like> findByEntityIdAndTypeId(long entityId,
		long typeId, int start, int end);

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
	public java.util.List<Like> findByEntityIdAndTypeId(long entityId,
		long typeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator);

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
	public java.util.List<Like> findByEntityIdAndTypeId(long entityId,
		long typeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first like in the ordered set where entityId = &#63; and typeId = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching like
	* @throws NoSuchLikeException if a matching like could not be found
	*/
	public Like findByEntityIdAndTypeId_First(long entityId, long typeId,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator)
		throws NoSuchLikeException;

	/**
	* Returns the first like in the ordered set where entityId = &#63; and typeId = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching like, or <code>null</code> if a matching like could not be found
	*/
	public Like fetchByEntityIdAndTypeId_First(long entityId, long typeId,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator);

	/**
	* Returns the last like in the ordered set where entityId = &#63; and typeId = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching like
	* @throws NoSuchLikeException if a matching like could not be found
	*/
	public Like findByEntityIdAndTypeId_Last(long entityId, long typeId,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator)
		throws NoSuchLikeException;

	/**
	* Returns the last like in the ordered set where entityId = &#63; and typeId = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching like, or <code>null</code> if a matching like could not be found
	*/
	public Like fetchByEntityIdAndTypeId_Last(long entityId, long typeId,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator);

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
	public Like[] findByEntityIdAndTypeId_PrevAndNext(long likeId,
		long entityId, long typeId,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator)
		throws NoSuchLikeException;

	/**
	* Removes all the likes where entityId = &#63; and typeId = &#63; from the database.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	*/
	public void removeByEntityIdAndTypeId(long entityId, long typeId);

	/**
	* Returns the number of likes where entityId = &#63; and typeId = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @return the number of matching likes
	*/
	public int countByEntityIdAndTypeId(long entityId, long typeId);

	/**
	* Returns all the likes where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param isDislike the is dislike
	* @return the matching likes
	*/
	public java.util.List<Like> findByEntityIdAndTypeIdAndIsDislike(
		long entityId, long typeId, boolean isDislike);

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
	public java.util.List<Like> findByEntityIdAndTypeIdAndIsDislike(
		long entityId, long typeId, boolean isDislike, int start, int end);

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
	public java.util.List<Like> findByEntityIdAndTypeIdAndIsDislike(
		long entityId, long typeId, boolean isDislike, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator);

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
	public java.util.List<Like> findByEntityIdAndTypeIdAndIsDislike(
		long entityId, long typeId, boolean isDislike, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator,
		boolean retrieveFromCache);

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
	public Like findByEntityIdAndTypeIdAndIsDislike_First(long entityId,
		long typeId, boolean isDislike,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator)
		throws NoSuchLikeException;

	/**
	* Returns the first like in the ordered set where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param isDislike the is dislike
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching like, or <code>null</code> if a matching like could not be found
	*/
	public Like fetchByEntityIdAndTypeIdAndIsDislike_First(long entityId,
		long typeId, boolean isDislike,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator);

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
	public Like findByEntityIdAndTypeIdAndIsDislike_Last(long entityId,
		long typeId, boolean isDislike,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator)
		throws NoSuchLikeException;

	/**
	* Returns the last like in the ordered set where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param isDislike the is dislike
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching like, or <code>null</code> if a matching like could not be found
	*/
	public Like fetchByEntityIdAndTypeIdAndIsDislike_Last(long entityId,
		long typeId, boolean isDislike,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator);

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
	public Like[] findByEntityIdAndTypeIdAndIsDislike_PrevAndNext(long likeId,
		long entityId, long typeId, boolean isDislike,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator)
		throws NoSuchLikeException;

	/**
	* Removes all the likes where entityId = &#63; and typeId = &#63; and isDislike = &#63; from the database.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param isDislike the is dislike
	*/
	public void removeByEntityIdAndTypeIdAndIsDislike(long entityId,
		long typeId, boolean isDislike);

	/**
	* Returns the number of likes where entityId = &#63; and typeId = &#63; and isDislike = &#63;.
	*
	* @param entityId the entity ID
	* @param typeId the type ID
	* @param isDislike the is dislike
	* @return the number of matching likes
	*/
	public int countByEntityIdAndTypeIdAndIsDislike(long entityId, long typeId,
		boolean isDislike);

	/**
	* Caches the like in the entity cache if it is enabled.
	*
	* @param like the like
	*/
	public void cacheResult(Like like);

	/**
	* Caches the likes in the entity cache if it is enabled.
	*
	* @param likes the likes
	*/
	public void cacheResult(java.util.List<Like> likes);

	/**
	* Creates a new like with the primary key. Does not add the like to the database.
	*
	* @param likeId the primary key for the new like
	* @return the new like
	*/
	public Like create(long likeId);

	/**
	* Removes the like with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param likeId the primary key of the like
	* @return the like that was removed
	* @throws NoSuchLikeException if a like with the primary key could not be found
	*/
	public Like remove(long likeId) throws NoSuchLikeException;

	public Like updateImpl(Like like);

	/**
	* Returns the like with the primary key or throws a {@link NoSuchLikeException} if it could not be found.
	*
	* @param likeId the primary key of the like
	* @return the like
	* @throws NoSuchLikeException if a like with the primary key could not be found
	*/
	public Like findByPrimaryKey(long likeId) throws NoSuchLikeException;

	/**
	* Returns the like with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param likeId the primary key of the like
	* @return the like, or <code>null</code> if a like with the primary key could not be found
	*/
	public Like fetchByPrimaryKey(long likeId);

	@Override
	public java.util.Map<java.io.Serializable, Like> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the likes.
	*
	* @return the likes
	*/
	public java.util.List<Like> findAll();

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
	public java.util.List<Like> findAll(int start, int end);

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
	public java.util.List<Like> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator);

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
	public java.util.List<Like> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Like> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the likes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of likes.
	*
	* @return the number of likes
	*/
	public int countAll();
}