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

package eu.strasbourg.service.notification.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.notification.model.UserNotificationStatus;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the user notification status service. This utility wraps {@link eu.strasbourg.service.notification.service.persistence.impl.UserNotificationStatusPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see UserNotificationStatusPersistence
 * @see eu.strasbourg.service.notification.service.persistence.impl.UserNotificationStatusPersistenceImpl
 * @generated
 */
@ProviderType
public class UserNotificationStatusUtil {
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
	public static void clearCache(UserNotificationStatus userNotificationStatus) {
		getPersistence().clearCache(userNotificationStatus);
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
	public static List<UserNotificationStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserNotificationStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserNotificationStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserNotificationStatus> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserNotificationStatus update(
		UserNotificationStatus userNotificationStatus) {
		return getPersistence().update(userNotificationStatus);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserNotificationStatus update(
		UserNotificationStatus userNotificationStatus,
		ServiceContext serviceContext) {
		return getPersistence().update(userNotificationStatus, serviceContext);
	}

	/**
	* Returns all the user notification statuses where notificationId = &#63;.
	*
	* @param notificationId the notification ID
	* @return the matching user notification statuses
	*/
	public static List<UserNotificationStatus> findByNotificationId(
		long notificationId) {
		return getPersistence().findByNotificationId(notificationId);
	}

	/**
	* Returns a range of all the user notification statuses where notificationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param notificationId the notification ID
	* @param start the lower bound of the range of user notification statuses
	* @param end the upper bound of the range of user notification statuses (not inclusive)
	* @return the range of matching user notification statuses
	*/
	public static List<UserNotificationStatus> findByNotificationId(
		long notificationId, int start, int end) {
		return getPersistence().findByNotificationId(notificationId, start, end);
	}

	/**
	* Returns an ordered range of all the user notification statuses where notificationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param notificationId the notification ID
	* @param start the lower bound of the range of user notification statuses
	* @param end the upper bound of the range of user notification statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification statuses
	*/
	public static List<UserNotificationStatus> findByNotificationId(
		long notificationId, int start, int end,
		OrderByComparator<UserNotificationStatus> orderByComparator) {
		return getPersistence()
				   .findByNotificationId(notificationId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the user notification statuses where notificationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param notificationId the notification ID
	* @param start the lower bound of the range of user notification statuses
	* @param end the upper bound of the range of user notification statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification statuses
	*/
	public static List<UserNotificationStatus> findByNotificationId(
		long notificationId, int start, int end,
		OrderByComparator<UserNotificationStatus> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByNotificationId(notificationId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first user notification status in the ordered set where notificationId = &#63;.
	*
	* @param notificationId the notification ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification status
	* @throws NoSuchUserNotificationStatusException if a matching user notification status could not be found
	*/
	public static UserNotificationStatus findByNotificationId_First(
		long notificationId,
		OrderByComparator<UserNotificationStatus> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationStatusException {
		return getPersistence()
				   .findByNotificationId_First(notificationId, orderByComparator);
	}

	/**
	* Returns the first user notification status in the ordered set where notificationId = &#63;.
	*
	* @param notificationId the notification ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification status, or <code>null</code> if a matching user notification status could not be found
	*/
	public static UserNotificationStatus fetchByNotificationId_First(
		long notificationId,
		OrderByComparator<UserNotificationStatus> orderByComparator) {
		return getPersistence()
				   .fetchByNotificationId_First(notificationId,
			orderByComparator);
	}

	/**
	* Returns the last user notification status in the ordered set where notificationId = &#63;.
	*
	* @param notificationId the notification ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification status
	* @throws NoSuchUserNotificationStatusException if a matching user notification status could not be found
	*/
	public static UserNotificationStatus findByNotificationId_Last(
		long notificationId,
		OrderByComparator<UserNotificationStatus> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationStatusException {
		return getPersistence()
				   .findByNotificationId_Last(notificationId, orderByComparator);
	}

	/**
	* Returns the last user notification status in the ordered set where notificationId = &#63;.
	*
	* @param notificationId the notification ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification status, or <code>null</code> if a matching user notification status could not be found
	*/
	public static UserNotificationStatus fetchByNotificationId_Last(
		long notificationId,
		OrderByComparator<UserNotificationStatus> orderByComparator) {
		return getPersistence()
				   .fetchByNotificationId_Last(notificationId, orderByComparator);
	}

	/**
	* Returns the user notification statuses before and after the current user notification status in the ordered set where notificationId = &#63;.
	*
	* @param userNotificationStatusPK the primary key of the current user notification status
	* @param notificationId the notification ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification status
	* @throws NoSuchUserNotificationStatusException if a user notification status with the primary key could not be found
	*/
	public static UserNotificationStatus[] findByNotificationId_PrevAndNext(
		eu.strasbourg.service.notification.service.persistence.UserNotificationStatusPK userNotificationStatusPK,
		long notificationId,
		OrderByComparator<UserNotificationStatus> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationStatusException {
		return getPersistence()
				   .findByNotificationId_PrevAndNext(userNotificationStatusPK,
			notificationId, orderByComparator);
	}

	/**
	* Removes all the user notification statuses where notificationId = &#63; from the database.
	*
	* @param notificationId the notification ID
	*/
	public static void removeByNotificationId(long notificationId) {
		getPersistence().removeByNotificationId(notificationId);
	}

	/**
	* Returns the number of user notification statuses where notificationId = &#63;.
	*
	* @param notificationId the notification ID
	* @return the number of matching user notification statuses
	*/
	public static int countByNotificationId(long notificationId) {
		return getPersistence().countByNotificationId(notificationId);
	}

	/**
	* Returns all the user notification statuses where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the matching user notification statuses
	*/
	public static List<UserNotificationStatus> findByPublikUserId(
		java.lang.String publikUserId) {
		return getPersistence().findByPublikUserId(publikUserId);
	}

	/**
	* Returns a range of all the user notification statuses where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of user notification statuses
	* @param end the upper bound of the range of user notification statuses (not inclusive)
	* @return the range of matching user notification statuses
	*/
	public static List<UserNotificationStatus> findByPublikUserId(
		java.lang.String publikUserId, int start, int end) {
		return getPersistence().findByPublikUserId(publikUserId, start, end);
	}

	/**
	* Returns an ordered range of all the user notification statuses where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of user notification statuses
	* @param end the upper bound of the range of user notification statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification statuses
	*/
	public static List<UserNotificationStatus> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		OrderByComparator<UserNotificationStatus> orderByComparator) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the user notification statuses where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of user notification statuses
	* @param end the upper bound of the range of user notification statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification statuses
	*/
	public static List<UserNotificationStatus> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		OrderByComparator<UserNotificationStatus> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first user notification status in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification status
	* @throws NoSuchUserNotificationStatusException if a matching user notification status could not be found
	*/
	public static UserNotificationStatus findByPublikUserId_First(
		java.lang.String publikUserId,
		OrderByComparator<UserNotificationStatus> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationStatusException {
		return getPersistence()
				   .findByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the first user notification status in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification status, or <code>null</code> if a matching user notification status could not be found
	*/
	public static UserNotificationStatus fetchByPublikUserId_First(
		java.lang.String publikUserId,
		OrderByComparator<UserNotificationStatus> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the last user notification status in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification status
	* @throws NoSuchUserNotificationStatusException if a matching user notification status could not be found
	*/
	public static UserNotificationStatus findByPublikUserId_Last(
		java.lang.String publikUserId,
		OrderByComparator<UserNotificationStatus> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationStatusException {
		return getPersistence()
				   .findByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the last user notification status in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification status, or <code>null</code> if a matching user notification status could not be found
	*/
	public static UserNotificationStatus fetchByPublikUserId_Last(
		java.lang.String publikUserId,
		OrderByComparator<UserNotificationStatus> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the user notification statuses before and after the current user notification status in the ordered set where publikUserId = &#63;.
	*
	* @param userNotificationStatusPK the primary key of the current user notification status
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification status
	* @throws NoSuchUserNotificationStatusException if a user notification status with the primary key could not be found
	*/
	public static UserNotificationStatus[] findByPublikUserId_PrevAndNext(
		eu.strasbourg.service.notification.service.persistence.UserNotificationStatusPK userNotificationStatusPK,
		java.lang.String publikUserId,
		OrderByComparator<UserNotificationStatus> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationStatusException {
		return getPersistence()
				   .findByPublikUserId_PrevAndNext(userNotificationStatusPK,
			publikUserId, orderByComparator);
	}

	/**
	* Removes all the user notification statuses where publikUserId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	*/
	public static void removeByPublikUserId(java.lang.String publikUserId) {
		getPersistence().removeByPublikUserId(publikUserId);
	}

	/**
	* Returns the number of user notification statuses where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the number of matching user notification statuses
	*/
	public static int countByPublikUserId(java.lang.String publikUserId) {
		return getPersistence().countByPublikUserId(publikUserId);
	}

	/**
	* Caches the user notification status in the entity cache if it is enabled.
	*
	* @param userNotificationStatus the user notification status
	*/
	public static void cacheResult(
		UserNotificationStatus userNotificationStatus) {
		getPersistence().cacheResult(userNotificationStatus);
	}

	/**
	* Caches the user notification statuses in the entity cache if it is enabled.
	*
	* @param userNotificationStatuses the user notification statuses
	*/
	public static void cacheResult(
		List<UserNotificationStatus> userNotificationStatuses) {
		getPersistence().cacheResult(userNotificationStatuses);
	}

	/**
	* Creates a new user notification status with the primary key. Does not add the user notification status to the database.
	*
	* @param userNotificationStatusPK the primary key for the new user notification status
	* @return the new user notification status
	*/
	public static UserNotificationStatus create(
		eu.strasbourg.service.notification.service.persistence.UserNotificationStatusPK userNotificationStatusPK) {
		return getPersistence().create(userNotificationStatusPK);
	}

	/**
	* Removes the user notification status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userNotificationStatusPK the primary key of the user notification status
	* @return the user notification status that was removed
	* @throws NoSuchUserNotificationStatusException if a user notification status with the primary key could not be found
	*/
	public static UserNotificationStatus remove(
		eu.strasbourg.service.notification.service.persistence.UserNotificationStatusPK userNotificationStatusPK)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationStatusException {
		return getPersistence().remove(userNotificationStatusPK);
	}

	public static UserNotificationStatus updateImpl(
		UserNotificationStatus userNotificationStatus) {
		return getPersistence().updateImpl(userNotificationStatus);
	}

	/**
	* Returns the user notification status with the primary key or throws a {@link NoSuchUserNotificationStatusException} if it could not be found.
	*
	* @param userNotificationStatusPK the primary key of the user notification status
	* @return the user notification status
	* @throws NoSuchUserNotificationStatusException if a user notification status with the primary key could not be found
	*/
	public static UserNotificationStatus findByPrimaryKey(
		eu.strasbourg.service.notification.service.persistence.UserNotificationStatusPK userNotificationStatusPK)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationStatusException {
		return getPersistence().findByPrimaryKey(userNotificationStatusPK);
	}

	/**
	* Returns the user notification status with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userNotificationStatusPK the primary key of the user notification status
	* @return the user notification status, or <code>null</code> if a user notification status with the primary key could not be found
	*/
	public static UserNotificationStatus fetchByPrimaryKey(
		eu.strasbourg.service.notification.service.persistence.UserNotificationStatusPK userNotificationStatusPK) {
		return getPersistence().fetchByPrimaryKey(userNotificationStatusPK);
	}

	public static java.util.Map<java.io.Serializable, UserNotificationStatus> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the user notification statuses.
	*
	* @return the user notification statuses
	*/
	public static List<UserNotificationStatus> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the user notification statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user notification statuses
	* @param end the upper bound of the range of user notification statuses (not inclusive)
	* @return the range of user notification statuses
	*/
	public static List<UserNotificationStatus> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the user notification statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user notification statuses
	* @param end the upper bound of the range of user notification statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user notification statuses
	*/
	public static List<UserNotificationStatus> findAll(int start, int end,
		OrderByComparator<UserNotificationStatus> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user notification statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user notification statuses
	* @param end the upper bound of the range of user notification statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of user notification statuses
	*/
	public static List<UserNotificationStatus> findAll(int start, int end,
		OrderByComparator<UserNotificationStatus> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the user notification statuses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of user notification statuses.
	*
	* @return the number of user notification statuses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static UserNotificationStatusPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<UserNotificationStatusPersistence, UserNotificationStatusPersistence> _serviceTracker =
		ServiceTrackerFactory.open(UserNotificationStatusPersistence.class);
}