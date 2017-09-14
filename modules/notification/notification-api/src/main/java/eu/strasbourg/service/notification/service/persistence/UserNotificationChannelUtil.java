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

import eu.strasbourg.service.notification.model.UserNotificationChannel;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the user notification channel service. This utility wraps {@link eu.strasbourg.service.notification.service.persistence.impl.UserNotificationChannelPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see UserNotificationChannelPersistence
 * @see eu.strasbourg.service.notification.service.persistence.impl.UserNotificationChannelPersistenceImpl
 * @generated
 */
@ProviderType
public class UserNotificationChannelUtil {
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
	public static void clearCache(
		UserNotificationChannel userNotificationChannel) {
		getPersistence().clearCache(userNotificationChannel);
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
	public static List<UserNotificationChannel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserNotificationChannel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserNotificationChannel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<UserNotificationChannel> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static UserNotificationChannel update(
		UserNotificationChannel userNotificationChannel) {
		return getPersistence().update(userNotificationChannel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static UserNotificationChannel update(
		UserNotificationChannel userNotificationChannel,
		ServiceContext serviceContext) {
		return getPersistence().update(userNotificationChannel, serviceContext);
	}

	/**
	* Returns all the user notification channels where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the matching user notification channels
	*/
	public static List<UserNotificationChannel> findByPublikUserId(
		java.lang.String publikUserId) {
		return getPersistence().findByPublikUserId(publikUserId);
	}

	/**
	* Returns a range of all the user notification channels where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of user notification channels
	* @param end the upper bound of the range of user notification channels (not inclusive)
	* @return the range of matching user notification channels
	*/
	public static List<UserNotificationChannel> findByPublikUserId(
		java.lang.String publikUserId, int start, int end) {
		return getPersistence().findByPublikUserId(publikUserId, start, end);
	}

	/**
	* Returns an ordered range of all the user notification channels where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of user notification channels
	* @param end the upper bound of the range of user notification channels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification channels
	*/
	public static List<UserNotificationChannel> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		OrderByComparator<UserNotificationChannel> orderByComparator) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the user notification channels where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of user notification channels
	* @param end the upper bound of the range of user notification channels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification channels
	*/
	public static List<UserNotificationChannel> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		OrderByComparator<UserNotificationChannel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first user notification channel in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification channel
	* @throws NoSuchUserNotificationChannelException if a matching user notification channel could not be found
	*/
	public static UserNotificationChannel findByPublikUserId_First(
		java.lang.String publikUserId,
		OrderByComparator<UserNotificationChannel> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationChannelException {
		return getPersistence()
				   .findByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the first user notification channel in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification channel, or <code>null</code> if a matching user notification channel could not be found
	*/
	public static UserNotificationChannel fetchByPublikUserId_First(
		java.lang.String publikUserId,
		OrderByComparator<UserNotificationChannel> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the last user notification channel in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification channel
	* @throws NoSuchUserNotificationChannelException if a matching user notification channel could not be found
	*/
	public static UserNotificationChannel findByPublikUserId_Last(
		java.lang.String publikUserId,
		OrderByComparator<UserNotificationChannel> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationChannelException {
		return getPersistence()
				   .findByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the last user notification channel in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification channel, or <code>null</code> if a matching user notification channel could not be found
	*/
	public static UserNotificationChannel fetchByPublikUserId_Last(
		java.lang.String publikUserId,
		OrderByComparator<UserNotificationChannel> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the user notification channels before and after the current user notification channel in the ordered set where publikUserId = &#63;.
	*
	* @param userNotificationChannelPK the primary key of the current user notification channel
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification channel
	* @throws NoSuchUserNotificationChannelException if a user notification channel with the primary key could not be found
	*/
	public static UserNotificationChannel[] findByPublikUserId_PrevAndNext(
		eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK userNotificationChannelPK,
		java.lang.String publikUserId,
		OrderByComparator<UserNotificationChannel> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationChannelException {
		return getPersistence()
				   .findByPublikUserId_PrevAndNext(userNotificationChannelPK,
			publikUserId, orderByComparator);
	}

	/**
	* Removes all the user notification channels where publikUserId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	*/
	public static void removeByPublikUserId(java.lang.String publikUserId) {
		getPersistence().removeByPublikUserId(publikUserId);
	}

	/**
	* Returns the number of user notification channels where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the number of matching user notification channels
	*/
	public static int countByPublikUserId(java.lang.String publikUserId) {
		return getPersistence().countByPublikUserId(publikUserId);
	}

	/**
	* Returns all the user notification channels where channelId = &#63;.
	*
	* @param channelId the channel ID
	* @return the matching user notification channels
	*/
	public static List<UserNotificationChannel> findByChannelId(long channelId) {
		return getPersistence().findByChannelId(channelId);
	}

	/**
	* Returns a range of all the user notification channels where channelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param channelId the channel ID
	* @param start the lower bound of the range of user notification channels
	* @param end the upper bound of the range of user notification channels (not inclusive)
	* @return the range of matching user notification channels
	*/
	public static List<UserNotificationChannel> findByChannelId(
		long channelId, int start, int end) {
		return getPersistence().findByChannelId(channelId, start, end);
	}

	/**
	* Returns an ordered range of all the user notification channels where channelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param channelId the channel ID
	* @param start the lower bound of the range of user notification channels
	* @param end the upper bound of the range of user notification channels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user notification channels
	*/
	public static List<UserNotificationChannel> findByChannelId(
		long channelId, int start, int end,
		OrderByComparator<UserNotificationChannel> orderByComparator) {
		return getPersistence()
				   .findByChannelId(channelId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user notification channels where channelId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param channelId the channel ID
	* @param start the lower bound of the range of user notification channels
	* @param end the upper bound of the range of user notification channels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching user notification channels
	*/
	public static List<UserNotificationChannel> findByChannelId(
		long channelId, int start, int end,
		OrderByComparator<UserNotificationChannel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByChannelId(channelId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first user notification channel in the ordered set where channelId = &#63;.
	*
	* @param channelId the channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification channel
	* @throws NoSuchUserNotificationChannelException if a matching user notification channel could not be found
	*/
	public static UserNotificationChannel findByChannelId_First(
		long channelId,
		OrderByComparator<UserNotificationChannel> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationChannelException {
		return getPersistence()
				   .findByChannelId_First(channelId, orderByComparator);
	}

	/**
	* Returns the first user notification channel in the ordered set where channelId = &#63;.
	*
	* @param channelId the channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification channel, or <code>null</code> if a matching user notification channel could not be found
	*/
	public static UserNotificationChannel fetchByChannelId_First(
		long channelId,
		OrderByComparator<UserNotificationChannel> orderByComparator) {
		return getPersistence()
				   .fetchByChannelId_First(channelId, orderByComparator);
	}

	/**
	* Returns the last user notification channel in the ordered set where channelId = &#63;.
	*
	* @param channelId the channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification channel
	* @throws NoSuchUserNotificationChannelException if a matching user notification channel could not be found
	*/
	public static UserNotificationChannel findByChannelId_Last(long channelId,
		OrderByComparator<UserNotificationChannel> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationChannelException {
		return getPersistence()
				   .findByChannelId_Last(channelId, orderByComparator);
	}

	/**
	* Returns the last user notification channel in the ordered set where channelId = &#63;.
	*
	* @param channelId the channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification channel, or <code>null</code> if a matching user notification channel could not be found
	*/
	public static UserNotificationChannel fetchByChannelId_Last(
		long channelId,
		OrderByComparator<UserNotificationChannel> orderByComparator) {
		return getPersistence()
				   .fetchByChannelId_Last(channelId, orderByComparator);
	}

	/**
	* Returns the user notification channels before and after the current user notification channel in the ordered set where channelId = &#63;.
	*
	* @param userNotificationChannelPK the primary key of the current user notification channel
	* @param channelId the channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification channel
	* @throws NoSuchUserNotificationChannelException if a user notification channel with the primary key could not be found
	*/
	public static UserNotificationChannel[] findByChannelId_PrevAndNext(
		eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK userNotificationChannelPK,
		long channelId,
		OrderByComparator<UserNotificationChannel> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationChannelException {
		return getPersistence()
				   .findByChannelId_PrevAndNext(userNotificationChannelPK,
			channelId, orderByComparator);
	}

	/**
	* Removes all the user notification channels where channelId = &#63; from the database.
	*
	* @param channelId the channel ID
	*/
	public static void removeByChannelId(long channelId) {
		getPersistence().removeByChannelId(channelId);
	}

	/**
	* Returns the number of user notification channels where channelId = &#63;.
	*
	* @param channelId the channel ID
	* @return the number of matching user notification channels
	*/
	public static int countByChannelId(long channelId) {
		return getPersistence().countByChannelId(channelId);
	}

	/**
	* Caches the user notification channel in the entity cache if it is enabled.
	*
	* @param userNotificationChannel the user notification channel
	*/
	public static void cacheResult(
		UserNotificationChannel userNotificationChannel) {
		getPersistence().cacheResult(userNotificationChannel);
	}

	/**
	* Caches the user notification channels in the entity cache if it is enabled.
	*
	* @param userNotificationChannels the user notification channels
	*/
	public static void cacheResult(
		List<UserNotificationChannel> userNotificationChannels) {
		getPersistence().cacheResult(userNotificationChannels);
	}

	/**
	* Creates a new user notification channel with the primary key. Does not add the user notification channel to the database.
	*
	* @param userNotificationChannelPK the primary key for the new user notification channel
	* @return the new user notification channel
	*/
	public static UserNotificationChannel create(
		eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK userNotificationChannelPK) {
		return getPersistence().create(userNotificationChannelPK);
	}

	/**
	* Removes the user notification channel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userNotificationChannelPK the primary key of the user notification channel
	* @return the user notification channel that was removed
	* @throws NoSuchUserNotificationChannelException if a user notification channel with the primary key could not be found
	*/
	public static UserNotificationChannel remove(
		eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK userNotificationChannelPK)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationChannelException {
		return getPersistence().remove(userNotificationChannelPK);
	}

	public static UserNotificationChannel updateImpl(
		UserNotificationChannel userNotificationChannel) {
		return getPersistence().updateImpl(userNotificationChannel);
	}

	/**
	* Returns the user notification channel with the primary key or throws a {@link NoSuchUserNotificationChannelException} if it could not be found.
	*
	* @param userNotificationChannelPK the primary key of the user notification channel
	* @return the user notification channel
	* @throws NoSuchUserNotificationChannelException if a user notification channel with the primary key could not be found
	*/
	public static UserNotificationChannel findByPrimaryKey(
		eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK userNotificationChannelPK)
		throws eu.strasbourg.service.notification.exception.NoSuchUserNotificationChannelException {
		return getPersistence().findByPrimaryKey(userNotificationChannelPK);
	}

	/**
	* Returns the user notification channel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userNotificationChannelPK the primary key of the user notification channel
	* @return the user notification channel, or <code>null</code> if a user notification channel with the primary key could not be found
	*/
	public static UserNotificationChannel fetchByPrimaryKey(
		eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK userNotificationChannelPK) {
		return getPersistence().fetchByPrimaryKey(userNotificationChannelPK);
	}

	public static java.util.Map<java.io.Serializable, UserNotificationChannel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the user notification channels.
	*
	* @return the user notification channels
	*/
	public static List<UserNotificationChannel> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the user notification channels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user notification channels
	* @param end the upper bound of the range of user notification channels (not inclusive)
	* @return the range of user notification channels
	*/
	public static List<UserNotificationChannel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the user notification channels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user notification channels
	* @param end the upper bound of the range of user notification channels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user notification channels
	*/
	public static List<UserNotificationChannel> findAll(int start, int end,
		OrderByComparator<UserNotificationChannel> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the user notification channels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UserNotificationChannelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of user notification channels
	* @param end the upper bound of the range of user notification channels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of user notification channels
	*/
	public static List<UserNotificationChannel> findAll(int start, int end,
		OrderByComparator<UserNotificationChannel> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the user notification channels from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of user notification channels.
	*
	* @return the number of user notification channels
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static UserNotificationChannelPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<UserNotificationChannelPersistence, UserNotificationChannelPersistence> _serviceTracker =
		ServiceTrackerFactory.open(UserNotificationChannelPersistence.class);
}