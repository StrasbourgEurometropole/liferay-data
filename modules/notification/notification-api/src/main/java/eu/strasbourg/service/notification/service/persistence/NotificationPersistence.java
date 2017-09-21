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

import eu.strasbourg.service.notification.exception.NoSuchNotificationException;
import eu.strasbourg.service.notification.model.Notification;

import java.util.Date;

/**
 * The persistence interface for the notification service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.notification.service.persistence.impl.NotificationPersistenceImpl
 * @see NotificationUtil
 * @generated
 */
@ProviderType
public interface NotificationPersistence extends BasePersistence<Notification> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NotificationUtil} to access the notification persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the notifications where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @return the matching notifications
	*/
	public java.util.List<Notification> findByPublicationDateAndStatus(
		Date publicationDate, int status);

	/**
	* Returns a range of all the notifications where publicationDate &lt; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param start the lower bound of the range of notifications
	* @param end the upper bound of the range of notifications (not inclusive)
	* @return the range of matching notifications
	*/
	public java.util.List<Notification> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end);

	/**
	* Returns an ordered range of all the notifications where publicationDate &lt; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param start the lower bound of the range of notifications
	* @param end the upper bound of the range of notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching notifications
	*/
	public java.util.List<Notification> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notification> orderByComparator);

	/**
	* Returns an ordered range of all the notifications where publicationDate &lt; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param start the lower bound of the range of notifications
	* @param end the upper bound of the range of notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching notifications
	*/
	public java.util.List<Notification> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notification> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first notification in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification
	* @throws NoSuchNotificationException if a matching notification could not be found
	*/
	public Notification findByPublicationDateAndStatus_First(
		Date publicationDate, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Notification> orderByComparator)
		throws NoSuchNotificationException;

	/**
	* Returns the first notification in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification, or <code>null</code> if a matching notification could not be found
	*/
	public Notification fetchByPublicationDateAndStatus_First(
		Date publicationDate, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Notification> orderByComparator);

	/**
	* Returns the last notification in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification
	* @throws NoSuchNotificationException if a matching notification could not be found
	*/
	public Notification findByPublicationDateAndStatus_Last(
		Date publicationDate, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Notification> orderByComparator)
		throws NoSuchNotificationException;

	/**
	* Returns the last notification in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification, or <code>null</code> if a matching notification could not be found
	*/
	public Notification fetchByPublicationDateAndStatus_Last(
		Date publicationDate, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Notification> orderByComparator);

	/**
	* Returns the notifications before and after the current notification in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param notificationId the primary key of the current notification
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notification
	* @throws NoSuchNotificationException if a notification with the primary key could not be found
	*/
	public Notification[] findByPublicationDateAndStatus_PrevAndNext(
		long notificationId, Date publicationDate, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Notification> orderByComparator)
		throws NoSuchNotificationException;

	/**
	* Removes all the notifications where publicationDate &lt; &#63; and status = &#63; from the database.
	*
	* @param publicationDate the publication date
	* @param status the status
	*/
	public void removeByPublicationDateAndStatus(Date publicationDate,
		int status);

	/**
	* Returns the number of notifications where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @return the number of matching notifications
	*/
	public int countByPublicationDateAndStatus(Date publicationDate, int status);

	/**
	* Returns all the notifications where expirationDate &lt; &#63; and status = &#63;.
	*
	* @param expirationDate the expiration date
	* @param status the status
	* @return the matching notifications
	*/
	public java.util.List<Notification> findByExpirationDateAndStatus(
		Date expirationDate, int status);

	/**
	* Returns a range of all the notifications where expirationDate &lt; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expirationDate the expiration date
	* @param status the status
	* @param start the lower bound of the range of notifications
	* @param end the upper bound of the range of notifications (not inclusive)
	* @return the range of matching notifications
	*/
	public java.util.List<Notification> findByExpirationDateAndStatus(
		Date expirationDate, int status, int start, int end);

	/**
	* Returns an ordered range of all the notifications where expirationDate &lt; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expirationDate the expiration date
	* @param status the status
	* @param start the lower bound of the range of notifications
	* @param end the upper bound of the range of notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching notifications
	*/
	public java.util.List<Notification> findByExpirationDateAndStatus(
		Date expirationDate, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notification> orderByComparator);

	/**
	* Returns an ordered range of all the notifications where expirationDate &lt; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param expirationDate the expiration date
	* @param status the status
	* @param start the lower bound of the range of notifications
	* @param end the upper bound of the range of notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching notifications
	*/
	public java.util.List<Notification> findByExpirationDateAndStatus(
		Date expirationDate, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notification> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first notification in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	*
	* @param expirationDate the expiration date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification
	* @throws NoSuchNotificationException if a matching notification could not be found
	*/
	public Notification findByExpirationDateAndStatus_First(
		Date expirationDate, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Notification> orderByComparator)
		throws NoSuchNotificationException;

	/**
	* Returns the first notification in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	*
	* @param expirationDate the expiration date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification, or <code>null</code> if a matching notification could not be found
	*/
	public Notification fetchByExpirationDateAndStatus_First(
		Date expirationDate, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Notification> orderByComparator);

	/**
	* Returns the last notification in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	*
	* @param expirationDate the expiration date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification
	* @throws NoSuchNotificationException if a matching notification could not be found
	*/
	public Notification findByExpirationDateAndStatus_Last(
		Date expirationDate, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Notification> orderByComparator)
		throws NoSuchNotificationException;

	/**
	* Returns the last notification in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	*
	* @param expirationDate the expiration date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification, or <code>null</code> if a matching notification could not be found
	*/
	public Notification fetchByExpirationDateAndStatus_Last(
		Date expirationDate, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Notification> orderByComparator);

	/**
	* Returns the notifications before and after the current notification in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	*
	* @param notificationId the primary key of the current notification
	* @param expirationDate the expiration date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next notification
	* @throws NoSuchNotificationException if a notification with the primary key could not be found
	*/
	public Notification[] findByExpirationDateAndStatus_PrevAndNext(
		long notificationId, Date expirationDate, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Notification> orderByComparator)
		throws NoSuchNotificationException;

	/**
	* Removes all the notifications where expirationDate &lt; &#63; and status = &#63; from the database.
	*
	* @param expirationDate the expiration date
	* @param status the status
	*/
	public void removeByExpirationDateAndStatus(Date expirationDate, int status);

	/**
	* Returns the number of notifications where expirationDate &lt; &#63; and status = &#63;.
	*
	* @param expirationDate the expiration date
	* @param status the status
	* @return the number of matching notifications
	*/
	public int countByExpirationDateAndStatus(Date expirationDate, int status);

	/**
	* Caches the notification in the entity cache if it is enabled.
	*
	* @param notification the notification
	*/
	public void cacheResult(Notification notification);

	/**
	* Caches the notifications in the entity cache if it is enabled.
	*
	* @param notifications the notifications
	*/
	public void cacheResult(java.util.List<Notification> notifications);

	/**
	* Creates a new notification with the primary key. Does not add the notification to the database.
	*
	* @param notificationId the primary key for the new notification
	* @return the new notification
	*/
	public Notification create(long notificationId);

	/**
	* Removes the notification with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationId the primary key of the notification
	* @return the notification that was removed
	* @throws NoSuchNotificationException if a notification with the primary key could not be found
	*/
	public Notification remove(long notificationId)
		throws NoSuchNotificationException;

	public Notification updateImpl(Notification notification);

	/**
	* Returns the notification with the primary key or throws a {@link NoSuchNotificationException} if it could not be found.
	*
	* @param notificationId the primary key of the notification
	* @return the notification
	* @throws NoSuchNotificationException if a notification with the primary key could not be found
	*/
	public Notification findByPrimaryKey(long notificationId)
		throws NoSuchNotificationException;

	/**
	* Returns the notification with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param notificationId the primary key of the notification
	* @return the notification, or <code>null</code> if a notification with the primary key could not be found
	*/
	public Notification fetchByPrimaryKey(long notificationId);

	@Override
	public java.util.Map<java.io.Serializable, Notification> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the notifications.
	*
	* @return the notifications
	*/
	public java.util.List<Notification> findAll();

	/**
	* Returns a range of all the notifications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notifications
	* @param end the upper bound of the range of notifications (not inclusive)
	* @return the range of notifications
	*/
	public java.util.List<Notification> findAll(int start, int end);

	/**
	* Returns an ordered range of all the notifications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notifications
	* @param end the upper bound of the range of notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of notifications
	*/
	public java.util.List<Notification> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notification> orderByComparator);

	/**
	* Returns an ordered range of all the notifications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notifications
	* @param end the upper bound of the range of notifications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of notifications
	*/
	public java.util.List<Notification> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Notification> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the notifications from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of notifications.
	*
	* @return the number of notifications
	*/
	public int countAll();
}