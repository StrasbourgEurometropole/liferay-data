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

package eu.strasbourg.service.notif.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NotificationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see NotificationLocalService
 * @generated
 */
@ProviderType
public class NotificationLocalServiceWrapper
	implements NotificationLocalService,
			   ServiceWrapper<NotificationLocalService> {

	public NotificationLocalServiceWrapper(
		NotificationLocalService notificationLocalService) {

		_notificationLocalService = notificationLocalService;
	}

	/**
	 * Adds the notification to the database. Also notifies the appropriate model listeners.
	 *
	 * @param notification the notification
	 * @return the notification that was added
	 */
	@Override
	public eu.strasbourg.service.notif.model.Notification addNotification(
		eu.strasbourg.service.notif.model.Notification notification) {

		return _notificationLocalService.addNotification(notification);
	}

	/**
	 * Creates a new notification with the primary key. Does not add the notification to the database.
	 *
	 * @param notificationId the primary key for the new notification
	 * @return the new notification
	 */
	@Override
	public eu.strasbourg.service.notif.model.Notification createNotification(
		long notificationId) {

		return _notificationLocalService.createNotification(notificationId);
	}

	/**
	 * Crée une offre vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public eu.strasbourg.service.notif.model.Notification createNotification(
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationLocalService.createNotification(sc);
	}

	/**
	 * Deletes the notification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notificationId the primary key of the notification
	 * @return the notification that was removed
	 * @throws PortalException if a notification with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.notif.model.Notification deleteNotification(
			long notificationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationLocalService.deleteNotification(notificationId);
	}

	/**
	 * Deletes the notification from the database. Also notifies the appropriate model listeners.
	 *
	 * @param notification the notification
	 * @return the notification that was removed
	 */
	@Override
	public eu.strasbourg.service.notif.model.Notification deleteNotification(
		eu.strasbourg.service.notif.model.Notification notification) {

		return _notificationLocalService.deleteNotification(notification);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _notificationLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _notificationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.notif.model.impl.NotificationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _notificationLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.notif.model.impl.NotificationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _notificationLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _notificationLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _notificationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.notif.model.Notification fetchNotification(
		long notificationId) {

		return _notificationLocalService.fetchNotification(notificationId);
	}

	/**
	 * Returns the notification matching the UUID and group.
	 *
	 * @param uuid the notification's UUID
	 * @param groupId the primary key of the group
	 * @return the matching notification, or <code>null</code> if a matching notification could not be found
	 */
	@Override
	public eu.strasbourg.service.notif.model.Notification
		fetchNotificationByUuidAndGroupId(String uuid, long groupId) {

		return _notificationLocalService.fetchNotificationByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.notif.model.Notification>
		findByKeyword(String keyword, long groupId, int start, int end) {

		return _notificationLocalService.findByKeyword(
			keyword, groupId, start, end);
	}

	@Override
	public long findByKeywordCount(String keyword, long groupId) {
		return _notificationLocalService.findByKeywordCount(keyword, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _notificationLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<eu.strasbourg.service.notif.model.Notification>
		getByServiceIds(long[] serviceIds) {

		return _notificationLocalService.getByServiceIds(serviceIds);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _notificationLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _notificationLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public java.util.List<eu.strasbourg.service.notif.model.Notification>
		getInProgressNotifications() {

		return _notificationLocalService.getInProgressNotifications();
	}

	/**
	 * Returns the notification with the primary key.
	 *
	 * @param notificationId the primary key of the notification
	 * @return the notification
	 * @throws PortalException if a notification with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.notif.model.Notification getNotification(
			long notificationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationLocalService.getNotification(notificationId);
	}

	/**
	 * Returns the notification matching the UUID and group.
	 *
	 * @param uuid the notification's UUID
	 * @param groupId the primary key of the group
	 * @return the matching notification
	 * @throws PortalException if a matching notification could not be found
	 */
	@Override
	public eu.strasbourg.service.notif.model.Notification
			getNotificationByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationLocalService.getNotificationByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.notif.model.impl.NotificationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of notifications
	 * @param end the upper bound of the range of notifications (not inclusive)
	 * @return the range of notifications
	 */
	@Override
	public java.util.List<eu.strasbourg.service.notif.model.Notification>
		getNotifications(int start, int end) {

		return _notificationLocalService.getNotifications(start, end);
	}

	/**
	 * Returns all the notifications matching the UUID and company.
	 *
	 * @param uuid the UUID of the notifications
	 * @param companyId the primary key of the company
	 * @return the matching notifications, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<eu.strasbourg.service.notif.model.Notification>
		getNotificationsByUuidAndCompanyId(String uuid, long companyId) {

		return _notificationLocalService.getNotificationsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of notifications matching the UUID and company.
	 *
	 * @param uuid the UUID of the notifications
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of notifications
	 * @param end the upper bound of the range of notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching notifications, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<eu.strasbourg.service.notif.model.Notification>
		getNotificationsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<eu.strasbourg.service.notif.model.Notification>
					orderByComparator) {

		return _notificationLocalService.getNotificationsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of notifications.
	 *
	 * @return the number of notifications
	 */
	@Override
	public int getNotificationsCount() {
		return _notificationLocalService.getNotificationsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _notificationLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<eu.strasbourg.service.notif.model.Notification>
		getPastNotifications() {

		return _notificationLocalService.getPastNotifications();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<eu.strasbourg.service.notif.model.Notification>
		getToComeNotifications() {

		return _notificationLocalService.getToComeNotifications();
	}

	/**
	 * Supprime une notification
	 */
	@Override
	public eu.strasbourg.service.notif.model.Notification removeNotification(
			long notificationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationLocalService.removeNotification(notificationId);
	}

	/**
	 * Updates the notification in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param notification the notification
	 * @return the notification that was updated
	 */
	@Override
	public eu.strasbourg.service.notif.model.Notification updateNotification(
		eu.strasbourg.service.notif.model.Notification notification) {

		return _notificationLocalService.updateNotification(notification);
	}

	/**
	 * Met à jour une notification et l'enregistre en base de données
	 */
	@Override
	public eu.strasbourg.service.notif.model.Notification updateNotification(
			eu.strasbourg.service.notif.model.Notification notification,
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationLocalService.updateNotification(notification, sc);
	}

	/**
	 * Met à jour le statut de l'edition par le framework workflow
	 */
	@Override
	public eu.strasbourg.service.notif.model.Notification updateStatus(
			long userId, long entryId, int status,
			com.liferay.portal.kernel.service.ServiceContext sc,
			java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _notificationLocalService.updateStatus(
			userId, entryId, status, sc, workflowContext);
	}

	@Override
	public NotificationLocalService getWrappedService() {
		return _notificationLocalService;
	}

	@Override
	public void setWrappedService(
		NotificationLocalService notificationLocalService) {

		_notificationLocalService = notificationLocalService;
	}

	private NotificationLocalService _notificationLocalService;

}