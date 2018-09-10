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

package eu.strasbourg.service.comment.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.comment.model.Comment;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the comment service. This utility wraps {@link eu.strasbourg.service.comment.service.persistence.impl.CommentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Romain Vergnais
 * @see CommentPersistence
 * @see eu.strasbourg.service.comment.service.persistence.impl.CommentPersistenceImpl
 * @generated
 */
@ProviderType
public class CommentUtil {
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
	public static void clearCache(Comment comment) {
		getPersistence().clearCache(comment);
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
	public static List<Comment> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Comment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Comment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Comment> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Comment update(Comment comment) {
		return getPersistence().update(comment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Comment update(Comment comment, ServiceContext serviceContext) {
		return getPersistence().update(comment, serviceContext);
	}

	/**
	* Returns all the comments where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching comments
	*/
	public static List<Comment> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the comments where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @return the range of matching comments
	*/
	public static List<Comment> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the comments where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching comments
	*/
	public static List<Comment> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Comment> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the comments where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching comments
	*/
	public static List<Comment> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Comment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first comment in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public static Comment findByUuid_First(java.lang.String uuid,
		OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first comment in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public static Comment fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Comment> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last comment in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public static Comment findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last comment in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public static Comment fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Comment> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the comments before and after the current comment in the ordered set where uuid = &#63;.
	*
	* @param commentId the primary key of the current comment
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next comment
	* @throws NoSuchCommentException if a comment with the primary key could not be found
	*/
	public static Comment[] findByUuid_PrevAndNext(long commentId,
		java.lang.String uuid, OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence()
				   .findByUuid_PrevAndNext(commentId, uuid, orderByComparator);
	}

	/**
	* Removes all the comments where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of comments where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching comments
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the comment where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCommentException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public static Comment findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the comment where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public static Comment fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the comment where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public static Comment fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the comment where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the comment that was removed
	*/
	public static Comment removeByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of comments where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching comments
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the comments where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching comments
	*/
	public static List<Comment> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the comments where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @return the range of matching comments
	*/
	public static List<Comment> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the comments where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching comments
	*/
	public static List<Comment> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Comment> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the comments where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching comments
	*/
	public static List<Comment> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Comment> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first comment in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public static Comment findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first comment in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public static Comment fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Comment> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last comment in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public static Comment findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last comment in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public static Comment fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Comment> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the comments before and after the current comment in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param commentId the primary key of the current comment
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next comment
	* @throws NoSuchCommentException if a comment with the primary key could not be found
	*/
	public static Comment[] findByUuid_C_PrevAndNext(long commentId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(commentId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the comments where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of comments where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching comments
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the comments where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching comments
	*/
	public static List<Comment> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the comments where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @return the range of matching comments
	*/
	public static List<Comment> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the comments where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching comments
	*/
	public static List<Comment> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Comment> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the comments where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching comments
	*/
	public static List<Comment> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Comment> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first comment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public static Comment findByGroupId_First(long groupId,
		OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first comment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public static Comment fetchByGroupId_First(long groupId,
		OrderByComparator<Comment> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last comment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public static Comment findByGroupId_Last(long groupId,
		OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last comment in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public static Comment fetchByGroupId_Last(long groupId,
		OrderByComparator<Comment> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the comments before and after the current comment in the ordered set where groupId = &#63;.
	*
	* @param commentId the primary key of the current comment
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next comment
	* @throws NoSuchCommentException if a comment with the primary key could not be found
	*/
	public static Comment[] findByGroupId_PrevAndNext(long commentId,
		long groupId, OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(commentId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the comments where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of comments where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching comments
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the comments where assetEntryId = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @return the matching comments
	*/
	public static List<Comment> findByAssetEntryId(long assetEntryId, int status) {
		return getPersistence().findByAssetEntryId(assetEntryId, status);
	}

	/**
	* Returns a range of all the comments where assetEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @return the range of matching comments
	*/
	public static List<Comment> findByAssetEntryId(long assetEntryId,
		int status, int start, int end) {
		return getPersistence()
				   .findByAssetEntryId(assetEntryId, status, start, end);
	}

	/**
	* Returns an ordered range of all the comments where assetEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching comments
	*/
	public static List<Comment> findByAssetEntryId(long assetEntryId,
		int status, int start, int end,
		OrderByComparator<Comment> orderByComparator) {
		return getPersistence()
				   .findByAssetEntryId(assetEntryId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the comments where assetEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching comments
	*/
	public static List<Comment> findByAssetEntryId(long assetEntryId,
		int status, int start, int end,
		OrderByComparator<Comment> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByAssetEntryId(assetEntryId, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first comment in the ordered set where assetEntryId = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public static Comment findByAssetEntryId_First(long assetEntryId,
		int status, OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence()
				   .findByAssetEntryId_First(assetEntryId, status,
			orderByComparator);
	}

	/**
	* Returns the first comment in the ordered set where assetEntryId = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public static Comment fetchByAssetEntryId_First(long assetEntryId,
		int status, OrderByComparator<Comment> orderByComparator) {
		return getPersistence()
				   .fetchByAssetEntryId_First(assetEntryId, status,
			orderByComparator);
	}

	/**
	* Returns the last comment in the ordered set where assetEntryId = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public static Comment findByAssetEntryId_Last(long assetEntryId,
		int status, OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence()
				   .findByAssetEntryId_Last(assetEntryId, status,
			orderByComparator);
	}

	/**
	* Returns the last comment in the ordered set where assetEntryId = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public static Comment fetchByAssetEntryId_Last(long assetEntryId,
		int status, OrderByComparator<Comment> orderByComparator) {
		return getPersistence()
				   .fetchByAssetEntryId_Last(assetEntryId, status,
			orderByComparator);
	}

	/**
	* Returns the comments before and after the current comment in the ordered set where assetEntryId = &#63; and status = &#63;.
	*
	* @param commentId the primary key of the current comment
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next comment
	* @throws NoSuchCommentException if a comment with the primary key could not be found
	*/
	public static Comment[] findByAssetEntryId_PrevAndNext(long commentId,
		long assetEntryId, int status,
		OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence()
				   .findByAssetEntryId_PrevAndNext(commentId, assetEntryId,
			status, orderByComparator);
	}

	/**
	* Removes all the comments where assetEntryId = &#63; and status = &#63; from the database.
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	*/
	public static void removeByAssetEntryId(long assetEntryId, int status) {
		getPersistence().removeByAssetEntryId(assetEntryId, status);
	}

	/**
	* Returns the number of comments where assetEntryId = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param status the status
	* @return the number of matching comments
	*/
	public static int countByAssetEntryId(long assetEntryId, int status) {
		return getPersistence().countByAssetEntryId(assetEntryId, status);
	}

	/**
	* Returns all the comments where assetEntryId = &#63; and level = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param level the level
	* @param status the status
	* @return the matching comments
	*/
	public static List<Comment> findByAssetEntryIdAndLevel(long assetEntryId,
		int level, int status) {
		return getPersistence()
				   .findByAssetEntryIdAndLevel(assetEntryId, level, status);
	}

	/**
	* Returns a range of all the comments where assetEntryId = &#63; and level = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param level the level
	* @param status the status
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @return the range of matching comments
	*/
	public static List<Comment> findByAssetEntryIdAndLevel(long assetEntryId,
		int level, int status, int start, int end) {
		return getPersistence()
				   .findByAssetEntryIdAndLevel(assetEntryId, level, status,
			start, end);
	}

	/**
	* Returns an ordered range of all the comments where assetEntryId = &#63; and level = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param level the level
	* @param status the status
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching comments
	*/
	public static List<Comment> findByAssetEntryIdAndLevel(long assetEntryId,
		int level, int status, int start, int end,
		OrderByComparator<Comment> orderByComparator) {
		return getPersistence()
				   .findByAssetEntryIdAndLevel(assetEntryId, level, status,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the comments where assetEntryId = &#63; and level = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetEntryId the asset entry ID
	* @param level the level
	* @param status the status
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching comments
	*/
	public static List<Comment> findByAssetEntryIdAndLevel(long assetEntryId,
		int level, int status, int start, int end,
		OrderByComparator<Comment> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByAssetEntryIdAndLevel(assetEntryId, level, status,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first comment in the ordered set where assetEntryId = &#63; and level = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param level the level
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public static Comment findByAssetEntryIdAndLevel_First(long assetEntryId,
		int level, int status, OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence()
				   .findByAssetEntryIdAndLevel_First(assetEntryId, level,
			status, orderByComparator);
	}

	/**
	* Returns the first comment in the ordered set where assetEntryId = &#63; and level = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param level the level
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public static Comment fetchByAssetEntryIdAndLevel_First(long assetEntryId,
		int level, int status, OrderByComparator<Comment> orderByComparator) {
		return getPersistence()
				   .fetchByAssetEntryIdAndLevel_First(assetEntryId, level,
			status, orderByComparator);
	}

	/**
	* Returns the last comment in the ordered set where assetEntryId = &#63; and level = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param level the level
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public static Comment findByAssetEntryIdAndLevel_Last(long assetEntryId,
		int level, int status, OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence()
				   .findByAssetEntryIdAndLevel_Last(assetEntryId, level,
			status, orderByComparator);
	}

	/**
	* Returns the last comment in the ordered set where assetEntryId = &#63; and level = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param level the level
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public static Comment fetchByAssetEntryIdAndLevel_Last(long assetEntryId,
		int level, int status, OrderByComparator<Comment> orderByComparator) {
		return getPersistence()
				   .fetchByAssetEntryIdAndLevel_Last(assetEntryId, level,
			status, orderByComparator);
	}

	/**
	* Returns the comments before and after the current comment in the ordered set where assetEntryId = &#63; and level = &#63; and status = &#63;.
	*
	* @param commentId the primary key of the current comment
	* @param assetEntryId the asset entry ID
	* @param level the level
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next comment
	* @throws NoSuchCommentException if a comment with the primary key could not be found
	*/
	public static Comment[] findByAssetEntryIdAndLevel_PrevAndNext(
		long commentId, long assetEntryId, int level, int status,
		OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence()
				   .findByAssetEntryIdAndLevel_PrevAndNext(commentId,
			assetEntryId, level, status, orderByComparator);
	}

	/**
	* Removes all the comments where assetEntryId = &#63; and level = &#63; and status = &#63; from the database.
	*
	* @param assetEntryId the asset entry ID
	* @param level the level
	* @param status the status
	*/
	public static void removeByAssetEntryIdAndLevel(long assetEntryId,
		int level, int status) {
		getPersistence()
			.removeByAssetEntryIdAndLevel(assetEntryId, level, status);
	}

	/**
	* Returns the number of comments where assetEntryId = &#63; and level = &#63; and status = &#63;.
	*
	* @param assetEntryId the asset entry ID
	* @param level the level
	* @param status the status
	* @return the number of matching comments
	*/
	public static int countByAssetEntryIdAndLevel(long assetEntryId, int level,
		int status) {
		return getPersistence()
				   .countByAssetEntryIdAndLevel(assetEntryId, level, status);
	}

	/**
	* Returns all the comments where parentCommentId = &#63; and status = &#63;.
	*
	* @param parentCommentId the parent comment ID
	* @param status the status
	* @return the matching comments
	*/
	public static List<Comment> findByParentCommentId(long parentCommentId,
		int status) {
		return getPersistence().findByParentCommentId(parentCommentId, status);
	}

	/**
	* Returns a range of all the comments where parentCommentId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCommentId the parent comment ID
	* @param status the status
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @return the range of matching comments
	*/
	public static List<Comment> findByParentCommentId(long parentCommentId,
		int status, int start, int end) {
		return getPersistence()
				   .findByParentCommentId(parentCommentId, status, start, end);
	}

	/**
	* Returns an ordered range of all the comments where parentCommentId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCommentId the parent comment ID
	* @param status the status
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching comments
	*/
	public static List<Comment> findByParentCommentId(long parentCommentId,
		int status, int start, int end,
		OrderByComparator<Comment> orderByComparator) {
		return getPersistence()
				   .findByParentCommentId(parentCommentId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the comments where parentCommentId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentCommentId the parent comment ID
	* @param status the status
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching comments
	*/
	public static List<Comment> findByParentCommentId(long parentCommentId,
		int status, int start, int end,
		OrderByComparator<Comment> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByParentCommentId(parentCommentId, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first comment in the ordered set where parentCommentId = &#63; and status = &#63;.
	*
	* @param parentCommentId the parent comment ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public static Comment findByParentCommentId_First(long parentCommentId,
		int status, OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence()
				   .findByParentCommentId_First(parentCommentId, status,
			orderByComparator);
	}

	/**
	* Returns the first comment in the ordered set where parentCommentId = &#63; and status = &#63;.
	*
	* @param parentCommentId the parent comment ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public static Comment fetchByParentCommentId_First(long parentCommentId,
		int status, OrderByComparator<Comment> orderByComparator) {
		return getPersistence()
				   .fetchByParentCommentId_First(parentCommentId, status,
			orderByComparator);
	}

	/**
	* Returns the last comment in the ordered set where parentCommentId = &#63; and status = &#63;.
	*
	* @param parentCommentId the parent comment ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment
	* @throws NoSuchCommentException if a matching comment could not be found
	*/
	public static Comment findByParentCommentId_Last(long parentCommentId,
		int status, OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence()
				   .findByParentCommentId_Last(parentCommentId, status,
			orderByComparator);
	}

	/**
	* Returns the last comment in the ordered set where parentCommentId = &#63; and status = &#63;.
	*
	* @param parentCommentId the parent comment ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching comment, or <code>null</code> if a matching comment could not be found
	*/
	public static Comment fetchByParentCommentId_Last(long parentCommentId,
		int status, OrderByComparator<Comment> orderByComparator) {
		return getPersistence()
				   .fetchByParentCommentId_Last(parentCommentId, status,
			orderByComparator);
	}

	/**
	* Returns the comments before and after the current comment in the ordered set where parentCommentId = &#63; and status = &#63;.
	*
	* @param commentId the primary key of the current comment
	* @param parentCommentId the parent comment ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next comment
	* @throws NoSuchCommentException if a comment with the primary key could not be found
	*/
	public static Comment[] findByParentCommentId_PrevAndNext(long commentId,
		long parentCommentId, int status,
		OrderByComparator<Comment> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence()
				   .findByParentCommentId_PrevAndNext(commentId,
			parentCommentId, status, orderByComparator);
	}

	/**
	* Removes all the comments where parentCommentId = &#63; and status = &#63; from the database.
	*
	* @param parentCommentId the parent comment ID
	* @param status the status
	*/
	public static void removeByParentCommentId(long parentCommentId, int status) {
		getPersistence().removeByParentCommentId(parentCommentId, status);
	}

	/**
	* Returns the number of comments where parentCommentId = &#63; and status = &#63;.
	*
	* @param parentCommentId the parent comment ID
	* @param status the status
	* @return the number of matching comments
	*/
	public static int countByParentCommentId(long parentCommentId, int status) {
		return getPersistence().countByParentCommentId(parentCommentId, status);
	}

	/**
	* Caches the comment in the entity cache if it is enabled.
	*
	* @param comment the comment
	*/
	public static void cacheResult(Comment comment) {
		getPersistence().cacheResult(comment);
	}

	/**
	* Caches the comments in the entity cache if it is enabled.
	*
	* @param comments the comments
	*/
	public static void cacheResult(List<Comment> comments) {
		getPersistence().cacheResult(comments);
	}

	/**
	* Creates a new comment with the primary key. Does not add the comment to the database.
	*
	* @param commentId the primary key for the new comment
	* @return the new comment
	*/
	public static Comment create(long commentId) {
		return getPersistence().create(commentId);
	}

	/**
	* Removes the comment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param commentId the primary key of the comment
	* @return the comment that was removed
	* @throws NoSuchCommentException if a comment with the primary key could not be found
	*/
	public static Comment remove(long commentId)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence().remove(commentId);
	}

	public static Comment updateImpl(Comment comment) {
		return getPersistence().updateImpl(comment);
	}

	/**
	* Returns the comment with the primary key or throws a {@link NoSuchCommentException} if it could not be found.
	*
	* @param commentId the primary key of the comment
	* @return the comment
	* @throws NoSuchCommentException if a comment with the primary key could not be found
	*/
	public static Comment findByPrimaryKey(long commentId)
		throws eu.strasbourg.service.comment.exception.NoSuchCommentException {
		return getPersistence().findByPrimaryKey(commentId);
	}

	/**
	* Returns the comment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param commentId the primary key of the comment
	* @return the comment, or <code>null</code> if a comment with the primary key could not be found
	*/
	public static Comment fetchByPrimaryKey(long commentId) {
		return getPersistence().fetchByPrimaryKey(commentId);
	}

	public static java.util.Map<java.io.Serializable, Comment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the comments.
	*
	* @return the comments
	*/
	public static List<Comment> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @return the range of comments
	*/
	public static List<Comment> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of comments
	*/
	public static List<Comment> findAll(int start, int end,
		OrderByComparator<Comment> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the comments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CommentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of comments
	* @param end the upper bound of the range of comments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of comments
	*/
	public static List<Comment> findAll(int start, int end,
		OrderByComparator<Comment> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the comments from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of comments.
	*
	* @return the number of comments
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CommentPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommentPersistence, CommentPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CommentPersistence.class);
}