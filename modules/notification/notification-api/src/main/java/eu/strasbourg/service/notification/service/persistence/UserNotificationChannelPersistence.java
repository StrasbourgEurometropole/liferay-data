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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.notification.exception.NoSuchUserNotificationChannelException;
import eu.strasbourg.service.notification.model.UserNotificationChannel;

/**
 * The persistence interface for the user notification channel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.notification.service.persistence.impl.UserNotificationChannelPersistenceImpl
 * @see UserNotificationChannelUtil
 * @generated
 */
@ProviderType
public interface UserNotificationChannelPersistence extends BasePersistence<UserNotificationChannel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserNotificationChannelUtil} to access the user notification channel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the user notification channels where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the matching user notification channels
	*/
	public java.util.List<UserNotificationChannel> findByPublikUserId(
		long publikUserId);

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
	public java.util.List<UserNotificationChannel> findByPublikUserId(
		long publikUserId, int start, int end);

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
	public java.util.List<UserNotificationChannel> findByPublikUserId(
		long publikUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationChannel> orderByComparator);

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
	public java.util.List<UserNotificationChannel> findByPublikUserId(
		long publikUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationChannel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user notification channel in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification channel
	* @throws NoSuchUserNotificationChannelException if a matching user notification channel could not be found
	*/
	public UserNotificationChannel findByPublikUserId_First(long publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationChannel> orderByComparator)
		throws NoSuchUserNotificationChannelException;

	/**
	* Returns the first user notification channel in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification channel, or <code>null</code> if a matching user notification channel could not be found
	*/
	public UserNotificationChannel fetchByPublikUserId_First(
		long publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationChannel> orderByComparator);

	/**
	* Returns the last user notification channel in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification channel
	* @throws NoSuchUserNotificationChannelException if a matching user notification channel could not be found
	*/
	public UserNotificationChannel findByPublikUserId_Last(long publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationChannel> orderByComparator)
		throws NoSuchUserNotificationChannelException;

	/**
	* Returns the last user notification channel in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification channel, or <code>null</code> if a matching user notification channel could not be found
	*/
	public UserNotificationChannel fetchByPublikUserId_Last(long publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationChannel> orderByComparator);

	/**
	* Returns the user notification channels before and after the current user notification channel in the ordered set where publikUserId = &#63;.
	*
	* @param userNotificationChannelPK the primary key of the current user notification channel
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification channel
	* @throws NoSuchUserNotificationChannelException if a user notification channel with the primary key could not be found
	*/
	public UserNotificationChannel[] findByPublikUserId_PrevAndNext(
		eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK userNotificationChannelPK,
		long publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationChannel> orderByComparator)
		throws NoSuchUserNotificationChannelException;

	/**
	* Removes all the user notification channels where publikUserId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	*/
	public void removeByPublikUserId(long publikUserId);

	/**
	* Returns the number of user notification channels where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the number of matching user notification channels
	*/
	public int countByPublikUserId(long publikUserId);

	/**
	* Returns all the user notification channels where channelId = &#63;.
	*
	* @param channelId the channel ID
	* @return the matching user notification channels
	*/
	public java.util.List<UserNotificationChannel> findByChannelId(
		long channelId);

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
	public java.util.List<UserNotificationChannel> findByChannelId(
		long channelId, int start, int end);

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
	public java.util.List<UserNotificationChannel> findByChannelId(
		long channelId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationChannel> orderByComparator);

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
	public java.util.List<UserNotificationChannel> findByChannelId(
		long channelId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationChannel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first user notification channel in the ordered set where channelId = &#63;.
	*
	* @param channelId the channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification channel
	* @throws NoSuchUserNotificationChannelException if a matching user notification channel could not be found
	*/
	public UserNotificationChannel findByChannelId_First(long channelId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationChannel> orderByComparator)
		throws NoSuchUserNotificationChannelException;

	/**
	* Returns the first user notification channel in the ordered set where channelId = &#63;.
	*
	* @param channelId the channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user notification channel, or <code>null</code> if a matching user notification channel could not be found
	*/
	public UserNotificationChannel fetchByChannelId_First(long channelId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationChannel> orderByComparator);

	/**
	* Returns the last user notification channel in the ordered set where channelId = &#63;.
	*
	* @param channelId the channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification channel
	* @throws NoSuchUserNotificationChannelException if a matching user notification channel could not be found
	*/
	public UserNotificationChannel findByChannelId_Last(long channelId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationChannel> orderByComparator)
		throws NoSuchUserNotificationChannelException;

	/**
	* Returns the last user notification channel in the ordered set where channelId = &#63;.
	*
	* @param channelId the channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user notification channel, or <code>null</code> if a matching user notification channel could not be found
	*/
	public UserNotificationChannel fetchByChannelId_Last(long channelId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationChannel> orderByComparator);

	/**
	* Returns the user notification channels before and after the current user notification channel in the ordered set where channelId = &#63;.
	*
	* @param userNotificationChannelPK the primary key of the current user notification channel
	* @param channelId the channel ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user notification channel
	* @throws NoSuchUserNotificationChannelException if a user notification channel with the primary key could not be found
	*/
	public UserNotificationChannel[] findByChannelId_PrevAndNext(
		eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK userNotificationChannelPK,
		long channelId,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationChannel> orderByComparator)
		throws NoSuchUserNotificationChannelException;

	/**
	* Removes all the user notification channels where channelId = &#63; from the database.
	*
	* @param channelId the channel ID
	*/
	public void removeByChannelId(long channelId);

	/**
	* Returns the number of user notification channels where channelId = &#63;.
	*
	* @param channelId the channel ID
	* @return the number of matching user notification channels
	*/
	public int countByChannelId(long channelId);

	/**
	* Caches the user notification channel in the entity cache if it is enabled.
	*
	* @param userNotificationChannel the user notification channel
	*/
	public void cacheResult(UserNotificationChannel userNotificationChannel);

	/**
	* Caches the user notification channels in the entity cache if it is enabled.
	*
	* @param userNotificationChannels the user notification channels
	*/
	public void cacheResult(
		java.util.List<UserNotificationChannel> userNotificationChannels);

	/**
	* Creates a new user notification channel with the primary key. Does not add the user notification channel to the database.
	*
	* @param userNotificationChannelPK the primary key for the new user notification channel
	* @return the new user notification channel
	*/
	public UserNotificationChannel create(
		eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK userNotificationChannelPK);

	/**
	* Removes the user notification channel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userNotificationChannelPK the primary key of the user notification channel
	* @return the user notification channel that was removed
	* @throws NoSuchUserNotificationChannelException if a user notification channel with the primary key could not be found
	*/
	public UserNotificationChannel remove(
		eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK userNotificationChannelPK)
		throws NoSuchUserNotificationChannelException;

	public UserNotificationChannel updateImpl(
		UserNotificationChannel userNotificationChannel);

	/**
	* Returns the user notification channel with the primary key or throws a {@link NoSuchUserNotificationChannelException} if it could not be found.
	*
	* @param userNotificationChannelPK the primary key of the user notification channel
	* @return the user notification channel
	* @throws NoSuchUserNotificationChannelException if a user notification channel with the primary key could not be found
	*/
	public UserNotificationChannel findByPrimaryKey(
		eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK userNotificationChannelPK)
		throws NoSuchUserNotificationChannelException;

	/**
	* Returns the user notification channel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userNotificationChannelPK the primary key of the user notification channel
	* @return the user notification channel, or <code>null</code> if a user notification channel with the primary key could not be found
	*/
	public UserNotificationChannel fetchByPrimaryKey(
		eu.strasbourg.service.notification.service.persistence.UserNotificationChannelPK userNotificationChannelPK);

	@Override
	public java.util.Map<java.io.Serializable, UserNotificationChannel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the user notification channels.
	*
	* @return the user notification channels
	*/
	public java.util.List<UserNotificationChannel> findAll();

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
	public java.util.List<UserNotificationChannel> findAll(int start, int end);

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
	public java.util.List<UserNotificationChannel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationChannel> orderByComparator);

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
	public java.util.List<UserNotificationChannel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<UserNotificationChannel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the user notification channels from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of user notification channels.
	*
	* @return the number of user notification channels
	*/
	public int countAll();
}