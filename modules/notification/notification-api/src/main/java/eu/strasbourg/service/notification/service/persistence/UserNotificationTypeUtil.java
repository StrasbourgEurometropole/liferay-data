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

import eu.strasbourg.service.notification.model.UserNotificationType;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the user notification type service. This utility wraps {@link eu.strasbourg.service.notification.service.persistence.impl.UserNotificationTypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see UserNotificationTypePersistence
 * @see eu.strasbourg.service.notification.service.persistence.impl.UserNotificationTypePersistenceImpl
 * @generated
 */
@ProviderType
public class UserNotificationTypeUtil {
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
	public static void clearCache(UserNotificationType userNotificationType) {
		getPersistence().clearCache(userNotificationType);
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
	public static List<UserNotificationType> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserNotificationType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserNotificationType> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserNotificationType> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserNotificationType update(
		UserNotificationType userNotificationType) {
		return getPersistence().update(userNotificationType);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserNotificationType update(
		UserNotificationType userNotificationType, ServiceContext serviceContext) {
		return getPersistence().update(userNotificationType, serviceContext);
	}

	/**
	* Returns all the user notification types where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the matching user notification types
	*/
	public static List<UserNotificationType> findByPublikUserId(
		java.lang.String publikUserId) {
		return getPersistence().findByPublikUserId(publikUserId);
	}

	/**
	* Returns a range of all the user notification types where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of user notification types
	* @param end the upper bound of the range of user notification types (not inclusive)
	* @return the range of matching user notification types
	*/
	public static List<UserNotificationType> findByPublikUserId(
		java.lang.String publikUserId, int start, int end) {
		return getPersistence().findByPublikUserId(publikUserId, start, end);
	}

	/**
	* Returns an ordered range of all the user notification types where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of user notification types
	* @param end the upper bound of the range of user notification types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification types
	*/
	public static List<UserNotificationType> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		OrderByComparator<UserNotificationType> orderByComparator) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the user notification types where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of user notification types
	* @param end the upper bound of the range of user notification types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification types
	*/
	public static List<UserNotificationType> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		OrderByComparator<UserNotificationType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first user notification type in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification type
	* @throws NoSuchUserNotificationTypeException if a matching user notification type could not be found
	*/
	public static UserNotificationType findByPublikUserId_First(
		java.lang.String publikUserId,
		OrderByComparator<UserNotificationType> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationTypeException {
		return getPersistence()
				   .findByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the first user notification type in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification type, or <code>null</code> if a matching user notification type could not be found
	*/
	public static UserNotificationType fetchByPublikUserId_First(
		java.lang.String publikUserId,
		OrderByComparator<UserNotificationType> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the last user notification type in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification type
	* @throws NoSuchUserNotificationTypeException if a matching user notification type could not be found
	*/
	public static UserNotificationType findByPublikUserId_Last(
		java.lang.String publikUserId,
		OrderByComparator<UserNotificationType> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationTypeException {
		return getPersistence()
				   .findByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the last user notification type in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification type, or <code>null</code> if a matching user notification type could not be found
	*/
	public static UserNotificationType fetchByPublikUserId_Last(
		java.lang.String publikUserId,
		OrderByComparator<UserNotificationType> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the user notification types before and after the current user notification type in the ordered set where publikUserId = &#63;.
	*
	* @param userNotificationTypePK the primary key of the current user notification type
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification type
	* @throws NoSuchUserNotificationTypeException if a user notification type with the primary key could not be found
	*/
	public static UserNotificationType[] findByPublikUserId_PrevAndNext(
		eu.strasbourg.service.notification.service.persistence.UserNotificationTypePK userNotificationTypePK,
		java.lang.String publikUserId,
		OrderByComparator<UserNotificationType> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationTypeException {
		return getPersistence()
				   .findByPublikUserId_PrevAndNext(userNotificationTypePK,
			publikUserId, orderByComparator);
	}

	/**
	* Removes all the user notification types where publikUserId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	*/
	public static void removeByPublikUserId(java.lang.String publikUserId) {
		getPersistence().removeByPublikUserId(publikUserId);
	}

	/**
	* Returns the number of user notification types where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the number of matching user notification types
	*/
	public static int countByPublikUserId(java.lang.String publikUserId) {
		return getPersistence().countByPublikUserId(publikUserId);
	}

	/**
	* Returns all the user notification types where typeId = &#63;.
	*
	* @param typeId the type ID
	* @return the matching user notification types
	*/
	public static List<UserNotificationType> findByTypeId(long typeId) {
		return getPersistence().findByTypeId(typeId);
	}

	/**
	* Returns a range of all the user notification types where typeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param typeId the type ID
	* @param start the lower bound of the range of user notification types
	* @param end the upper bound of the range of user notification types (not inclusive)
	* @return the range of matching user notification types
	*/
	public static List<UserNotificationType> findByTypeId(long typeId,
		int start, int end) {
		return getPersistence().findByTypeId(typeId, start, end);
	}

	/**
	* Returns an ordered range of all the user notification types where typeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param typeId the type ID
	* @param start the lower bound of the range of user notification types
	* @param end the upper bound of the range of user notification types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification types
	*/
	public static List<UserNotificationType> findByTypeId(long typeId,
		int start, int end,
		OrderByComparator<UserNotificationType> orderByComparator) {
		return getPersistence()
				   .findByTypeId(typeId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user notification types where typeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param typeId the type ID
	* @param start the lower bound of the range of user notification types
	* @param end the upper bound of the range of user notification types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification types
	*/
	public static List<UserNotificationType> findByTypeId(long typeId,
		int start, int end,
		OrderByComparator<UserNotificationType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTypeId(typeId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user notification type in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification type
	* @throws NoSuchUserNotificationTypeException if a matching user notification type could not be found
	*/
	public static UserNotificationType findByTypeId_First(long typeId,
		OrderByComparator<UserNotificationType> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationTypeException {
		return getPersistence().findByTypeId_First(typeId, orderByComparator);
	}

	/**
	* Returns the first user notification type in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification type, or <code>null</code> if a matching user notification type could not be found
	*/
	public static UserNotificationType fetchByTypeId_First(long typeId,
		OrderByComparator<UserNotificationType> orderByComparator) {
		return getPersistence().fetchByTypeId_First(typeId, orderByComparator);
	}

	/**
	* Returns the last user notification type in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification type
	* @throws NoSuchUserNotificationTypeException if a matching user notification type could not be found
	*/
	public static UserNotificationType findByTypeId_Last(long typeId,
		OrderByComparator<UserNotificationType> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationTypeException {
		return getPersistence().findByTypeId_Last(typeId, orderByComparator);
	}

	/**
	* Returns the last user notification type in the ordered set where typeId = &#63;.
	*
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification type, or <code>null</code> if a matching user notification type could not be found
	*/
	public static UserNotificationType fetchByTypeId_Last(long typeId,
		OrderByComparator<UserNotificationType> orderByComparator) {
		return getPersistence().fetchByTypeId_Last(typeId, orderByComparator);
	}

	/**
	* Returns the user notification types before and after the current user notification type in the ordered set where typeId = &#63;.
	*
	* @param userNotificationTypePK the primary key of the current user notification type
	* @param typeId the type ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification type
	* @throws NoSuchUserNotificationTypeException if a user notification type with the primary key could not be found
	*/
	public static UserNotificationType[] findByTypeId_PrevAndNext(
		eu.strasbourg.service.notification.service.persistence.UserNotificationTypePK userNotificationTypePK,
		long typeId, OrderByComparator<UserNotificationType> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationTypeException {
		return getPersistence()
				   .findByTypeId_PrevAndNext(userNotificationTypePK, typeId,
			orderByComparator);
	}

	/**
	* Removes all the user notification types where typeId = &#63; from the database.
	*
	* @param typeId the type ID
	*/
	public static void removeByTypeId(long typeId) {
		getPersistence().removeByTypeId(typeId);
	}

	/**
	* Returns the number of user notification types where typeId = &#63;.
	*
	* @param typeId the type ID
	* @return the number of matching user notification types
	*/
	public static int countByTypeId(long typeId) {
		return getPersistence().countByTypeId(typeId);
	}

	/**
	* Caches the user notification type in the entity cache if it is enabled.
	*
	* @param userNotificationType the user notification type
	*/
	public static void cacheResult(UserNotificationType userNotificationType) {
		getPersistence().cacheResult(userNotificationType);
	}

	/**
	* Caches the user notification types in the entity cache if it is enabled.
	*
	* @param userNotificationTypes the user notification types
	*/
	public static void cacheResult(
		List<UserNotificationType> userNotificationTypes) {
		getPersistence().cacheResult(userNotificationTypes);
	}

	/**
	* Creates a new user notification type with the primary key. Does not add the user notification type to the database.
	*
	* @param userNotificationTypePK the primary key for the new user notification type
	* @return the new user notification type
	*/
	public static UserNotificationType create(
		eu.strasbourg.service.notification.service.persistence.UserNotificationTypePK userNotificationTypePK) {
		return getPersistence().create(userNotificationTypePK);
	}

	/**
	* Removes the user notification type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userNotificationTypePK the primary key of the user notification type
	* @return the user notification type that was removed
	* @throws NoSuchUserNotificationTypeException if a user notification type with the primary key could not be found
	*/
	public static UserNotificationType remove(
		eu.strasbourg.service.notification.service.persistence.UserNotificationTypePK userNotificationTypePK)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationTypeException {
		return getPersistence().remove(userNotificationTypePK);
	}

	public static UserNotificationType updateImpl(
		UserNotificationType userNotificationType) {
		return getPersistence().updateImpl(userNotificationType);
	}

	/**
	* Returns the user notification type with the primary key or throws a {@link NoSuchUserNotificationTypeException} if it could not be found.
	*
	* @param userNotificationTypePK the primary key of the user notification type
	* @return the user notification type
	* @throws NoSuchUserNotificationTypeException if a user notification type with the primary key could not be found
	*/
	public static UserNotificationType findByPrimaryKey(
		eu.strasbourg.service.notification.service.persistence.UserNotificationTypePK userNotificationTypePK)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationTypeException {
		return getPersistence().findByPrimaryKey(userNotificationTypePK);
	}

	/**
	* Returns the user notification type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userNotificationTypePK the primary key of the user notification type
	* @return the user notification type, or <code>null</code> if a user notification type with the primary key could not be found
	*/
	public static UserNotificationType fetchByPrimaryKey(
		eu.strasbourg.service.notification.service.persistence.UserNotificationTypePK userNotificationTypePK) {
		return getPersistence().fetchByPrimaryKey(userNotificationTypePK);
	}

	public static java.util.Map<java.io.Serializable, UserNotificationType> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the user notification types.
	*
	* @return the user notification types
	*/
	public static List<UserNotificationType> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the user notification types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user notification types
	* @param end the upper bound of the range of user notification types (not inclusive)
	* @return the range of user notification types
	*/
	public static List<UserNotificationType> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the user notification types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user notification types
	* @param end the upper bound of the range of user notification types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user notification types
	*/
	public static List<UserNotificationType> findAll(int start, int end,
		OrderByComparator<UserNotificationType> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user notification types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user notification types
	* @param end the upper bound of the range of user notification types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of user notification types
	*/
	public static List<UserNotificationType> findAll(int start, int end,
		OrderByComparator<UserNotificationType> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the user notification types from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of user notification types.
	*
	* @return the number of user notification types
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static UserNotificationTypePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<UserNotificationTypePersistence, UserNotificationTypePersistence> _serviceTracker =
		ServiceTrackerFactory.open(UserNotificationTypePersistence.class);
}