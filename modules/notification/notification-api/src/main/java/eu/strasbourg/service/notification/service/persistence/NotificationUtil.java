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

import eu.strasbourg.service.notification.model.Notification;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the notification service. This utility wraps {@link eu.strasbourg.service.notification.service.persistence.impl.NotificationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see NotificationPersistence
 * @see eu.strasbourg.service.notification.service.persistence.impl.NotificationPersistenceImpl
 * @generated
 */
@ProviderType
public class NotificationUtil {
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
	public static void clearCache(Notification notification) {
		getPersistence().clearCache(notification);
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
	public static List<Notification> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Notification> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Notification> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Notification> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Notification update(Notification notification) {
		return getPersistence().update(notification);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Notification update(Notification notification,
		ServiceContext serviceContext) {
		return getPersistence().update(notification, serviceContext);
	}

	/**
	* Returns all the notifications where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @return the matching notifications
	*/
	public static List<Notification> findByPublicationDateAndStatus(
		Date publicationDate, int status) {
		return getPersistence()
				   .findByPublicationDateAndStatus(publicationDate, status);
	}

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
	public static List<Notification> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end) {
		return getPersistence()
				   .findByPublicationDateAndStatus(publicationDate, status,
			start, end);
	}

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
	public static List<Notification> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		OrderByComparator<Notification> orderByComparator) {
		return getPersistence()
				   .findByPublicationDateAndStatus(publicationDate, status,
			start, end, orderByComparator);
	}

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
	public static List<Notification> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		OrderByComparator<Notification> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPublicationDateAndStatus(publicationDate, status,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first notification in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification
	* @throws NoSuchNotificationException if a matching notification could not be found
	*/
	public static Notification findByPublicationDateAndStatus_First(
		Date publicationDate, int status,
		OrderByComparator<Notification> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchNotificationException {
		return getPersistence()
				   .findByPublicationDateAndStatus_First(publicationDate,
			status, orderByComparator);
	}

	/**
	* Returns the first notification in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification, or <code>null</code> if a matching notification could not be found
	*/
	public static Notification fetchByPublicationDateAndStatus_First(
		Date publicationDate, int status,
		OrderByComparator<Notification> orderByComparator) {
		return getPersistence()
				   .fetchByPublicationDateAndStatus_First(publicationDate,
			status, orderByComparator);
	}

	/**
	* Returns the last notification in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification
	* @throws NoSuchNotificationException if a matching notification could not be found
	*/
	public static Notification findByPublicationDateAndStatus_Last(
		Date publicationDate, int status,
		OrderByComparator<Notification> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchNotificationException {
		return getPersistence()
				   .findByPublicationDateAndStatus_Last(publicationDate,
			status, orderByComparator);
	}

	/**
	* Returns the last notification in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification, or <code>null</code> if a matching notification could not be found
	*/
	public static Notification fetchByPublicationDateAndStatus_Last(
		Date publicationDate, int status,
		OrderByComparator<Notification> orderByComparator) {
		return getPersistence()
				   .fetchByPublicationDateAndStatus_Last(publicationDate,
			status, orderByComparator);
	}

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
	public static Notification[] findByPublicationDateAndStatus_PrevAndNext(
		long notificationId, Date publicationDate, int status,
		OrderByComparator<Notification> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchNotificationException {
		return getPersistence()
				   .findByPublicationDateAndStatus_PrevAndNext(notificationId,
			publicationDate, status, orderByComparator);
	}

	/**
	* Removes all the notifications where publicationDate &lt; &#63; and status = &#63; from the database.
	*
	* @param publicationDate the publication date
	* @param status the status
	*/
	public static void removeByPublicationDateAndStatus(Date publicationDate,
		int status) {
		getPersistence()
			.removeByPublicationDateAndStatus(publicationDate, status);
	}

	/**
	* Returns the number of notifications where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @return the number of matching notifications
	*/
	public static int countByPublicationDateAndStatus(Date publicationDate,
		int status) {
		return getPersistence()
				   .countByPublicationDateAndStatus(publicationDate, status);
	}

	/**
	* Returns all the notifications where expirationDate &lt; &#63; and status = &#63;.
	*
	* @param expirationDate the expiration date
	* @param status the status
	* @return the matching notifications
	*/
	public static List<Notification> findByExpirationDateAndStatus(
		Date expirationDate, int status) {
		return getPersistence()
				   .findByExpirationDateAndStatus(expirationDate, status);
	}

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
	public static List<Notification> findByExpirationDateAndStatus(
		Date expirationDate, int status, int start, int end) {
		return getPersistence()
				   .findByExpirationDateAndStatus(expirationDate, status,
			start, end);
	}

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
	public static List<Notification> findByExpirationDateAndStatus(
		Date expirationDate, int status, int start, int end,
		OrderByComparator<Notification> orderByComparator) {
		return getPersistence()
				   .findByExpirationDateAndStatus(expirationDate, status,
			start, end, orderByComparator);
	}

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
	public static List<Notification> findByExpirationDateAndStatus(
		Date expirationDate, int status, int start, int end,
		OrderByComparator<Notification> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByExpirationDateAndStatus(expirationDate, status,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first notification in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	*
	* @param expirationDate the expiration date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification
	* @throws NoSuchNotificationException if a matching notification could not be found
	*/
	public static Notification findByExpirationDateAndStatus_First(
		Date expirationDate, int status,
		OrderByComparator<Notification> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchNotificationException {
		return getPersistence()
				   .findByExpirationDateAndStatus_First(expirationDate, status,
			orderByComparator);
	}

	/**
	* Returns the first notification in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	*
	* @param expirationDate the expiration date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching notification, or <code>null</code> if a matching notification could not be found
	*/
	public static Notification fetchByExpirationDateAndStatus_First(
		Date expirationDate, int status,
		OrderByComparator<Notification> orderByComparator) {
		return getPersistence()
				   .fetchByExpirationDateAndStatus_First(expirationDate,
			status, orderByComparator);
	}

	/**
	* Returns the last notification in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	*
	* @param expirationDate the expiration date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification
	* @throws NoSuchNotificationException if a matching notification could not be found
	*/
	public static Notification findByExpirationDateAndStatus_Last(
		Date expirationDate, int status,
		OrderByComparator<Notification> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchNotificationException {
		return getPersistence()
				   .findByExpirationDateAndStatus_Last(expirationDate, status,
			orderByComparator);
	}

	/**
	* Returns the last notification in the ordered set where expirationDate &lt; &#63; and status = &#63;.
	*
	* @param expirationDate the expiration date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching notification, or <code>null</code> if a matching notification could not be found
	*/
	public static Notification fetchByExpirationDateAndStatus_Last(
		Date expirationDate, int status,
		OrderByComparator<Notification> orderByComparator) {
		return getPersistence()
				   .fetchByExpirationDateAndStatus_Last(expirationDate, status,
			orderByComparator);
	}

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
	public static Notification[] findByExpirationDateAndStatus_PrevAndNext(
		long notificationId, Date expirationDate, int status,
		OrderByComparator<Notification> orderByComparator)
		throws eu.strasbourg.service.notification.exception.NoSuchNotificationException {
		return getPersistence()
				   .findByExpirationDateAndStatus_PrevAndNext(notificationId,
			expirationDate, status, orderByComparator);
	}

	/**
	* Removes all the notifications where expirationDate &lt; &#63; and status = &#63; from the database.
	*
	* @param expirationDate the expiration date
	* @param status the status
	*/
	public static void removeByExpirationDateAndStatus(Date expirationDate,
		int status) {
		getPersistence().removeByExpirationDateAndStatus(expirationDate, status);
	}

	/**
	* Returns the number of notifications where expirationDate &lt; &#63; and status = &#63;.
	*
	* @param expirationDate the expiration date
	* @param status the status
	* @return the number of matching notifications
	*/
	public static int countByExpirationDateAndStatus(Date expirationDate,
		int status) {
		return getPersistence()
				   .countByExpirationDateAndStatus(expirationDate, status);
	}

	/**
	* Caches the notification in the entity cache if it is enabled.
	*
	* @param notification the notification
	*/
	public static void cacheResult(Notification notification) {
		getPersistence().cacheResult(notification);
	}

	/**
	* Caches the notifications in the entity cache if it is enabled.
	*
	* @param notifications the notifications
	*/
	public static void cacheResult(List<Notification> notifications) {
		getPersistence().cacheResult(notifications);
	}

	/**
	* Creates a new notification with the primary key. Does not add the notification to the database.
	*
	* @param notificationId the primary key for the new notification
	* @return the new notification
	*/
	public static Notification create(long notificationId) {
		return getPersistence().create(notificationId);
	}

	/**
	* Removes the notification with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationId the primary key of the notification
	* @return the notification that was removed
	* @throws NoSuchNotificationException if a notification with the primary key could not be found
	*/
	public static Notification remove(long notificationId)
		throws eu.strasbourg.service.notification.exception.NoSuchNotificationException {
		return getPersistence().remove(notificationId);
	}

	public static Notification updateImpl(Notification notification) {
		return getPersistence().updateImpl(notification);
	}

	/**
	* Returns the notification with the primary key or throws a {@link NoSuchNotificationException} if it could not be found.
	*
	* @param notificationId the primary key of the notification
	* @return the notification
	* @throws NoSuchNotificationException if a notification with the primary key could not be found
	*/
	public static Notification findByPrimaryKey(long notificationId)
		throws eu.strasbourg.service.notification.exception.NoSuchNotificationException {
		return getPersistence().findByPrimaryKey(notificationId);
	}

	/**
	* Returns the notification with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param notificationId the primary key of the notification
	* @return the notification, or <code>null</code> if a notification with the primary key could not be found
	*/
	public static Notification fetchByPrimaryKey(long notificationId) {
		return getPersistence().fetchByPrimaryKey(notificationId);
	}

	public static java.util.Map<java.io.Serializable, Notification> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the notifications.
	*
	* @return the notifications
	*/
	public static List<Notification> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Notification> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Notification> findAll(int start, int end,
		OrderByComparator<Notification> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Notification> findAll(int start, int end,
		OrderByComparator<Notification> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the notifications from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of notifications.
	*
	* @return the number of notifications
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static NotificationPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NotificationPersistence, NotificationPersistence> _serviceTracker =
		ServiceTrackerFactory.open(NotificationPersistence.class);
}