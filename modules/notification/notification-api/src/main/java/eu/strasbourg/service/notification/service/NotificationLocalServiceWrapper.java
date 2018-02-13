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

package eu.strasbourg.service.notification.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NotificationLocalService}.
 *
 * @author BenjaminBini
 * @see NotificationLocalService
 * @generated
 */
@ProviderType
public class NotificationLocalServiceWrapper implements NotificationLocalService,
	ServiceWrapper<NotificationLocalService> {
	public NotificationLocalServiceWrapper(
		NotificationLocalService notificationLocalService) {
		_notificationLocalService = notificationLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _notificationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _notificationLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _notificationLocalService.getIndexableActionableDynamicQuery();
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
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _notificationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Lance une recherche selon le searchContext
	*/
	@Override
	public com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return _notificationLocalService.search(searchContext);
	}

	/**
	* Adds the notification to the database. Also notifies the appropriate model listeners.
	*
	* @param notification the notification
	* @return the notification that was added
	*/
	@Override
	public eu.strasbourg.service.notification.model.Notification addNotification(
		eu.strasbourg.service.notification.model.Notification notification) {
		return _notificationLocalService.addNotification(notification);
	}

	/**
	* Crée une notification vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.notification.model.Notification createNotification(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _notificationLocalService.createNotification(sc);
	}

	/**
	* Creates a new notification with the primary key. Does not add the notification to the database.
	*
	* @param notificationId the primary key for the new notification
	* @return the new notification
	*/
	@Override
	public eu.strasbourg.service.notification.model.Notification createNotification(
		long notificationId) {
		return _notificationLocalService.createNotification(notificationId);
	}

	/**
	* Deletes the notification from the database. Also notifies the appropriate model listeners.
	*
	* @param notification the notification
	* @return the notification that was removed
	*/
	@Override
	public eu.strasbourg.service.notification.model.Notification deleteNotification(
		eu.strasbourg.service.notification.model.Notification notification) {
		return _notificationLocalService.deleteNotification(notification);
	}

	/**
	* Deletes the notification with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param notificationId the primary key of the notification
	* @return the notification that was removed
	* @throws PortalException if a notification with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.notification.model.Notification deleteNotification(
		long notificationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _notificationLocalService.deleteNotification(notificationId);
	}

	@Override
	public eu.strasbourg.service.notification.model.Notification fetchNotification(
		long notificationId) {
		return _notificationLocalService.fetchNotification(notificationId);
	}

	/**
	* Returns the notification with the primary key.
	*
	* @param notificationId the primary key of the notification
	* @return the notification
	* @throws PortalException if a notification with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.notification.model.Notification getNotification(
		long notificationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _notificationLocalService.getNotification(notificationId);
	}

	/**
	* Supprime une notification
	*/
	@Override
	public eu.strasbourg.service.notification.model.Notification removeNotification(
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
	public eu.strasbourg.service.notification.model.Notification updateNotification(
		eu.strasbourg.service.notification.model.Notification notification) {
		return _notificationLocalService.updateNotification(notification);
	}

	/**
	* Met à jour une édition et l'enregistre en base de données
	*/
	@Override
	public eu.strasbourg.service.notification.model.Notification updateNotification(
		eu.strasbourg.service.notification.model.Notification notification,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _notificationLocalService.updateNotification(notification, sc);
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
	public java.lang.String getOSGiServiceIdentifier() {
		return _notificationLocalService.getOSGiServiceIdentifier();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.notification.model.impl.NotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.notification.model.impl.NotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _notificationLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Lance une recherche par mots-clés
	*/
	@Override
	public java.util.List<eu.strasbourg.service.notification.model.Notification> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _notificationLocalService.findByKeyword(keyword, groupId, start,
			end);
	}

	/**
	* Returns a range of all the notifications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.notification.model.impl.NotificationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of notifications
	* @param end the upper bound of the range of notifications (not inclusive)
	* @return the range of notifications
	*/
	@Override
	public java.util.List<eu.strasbourg.service.notification.model.Notification> getNotifications(
		int start, int end) {
		return _notificationLocalService.getNotifications(start, end);
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
		return _notificationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Compte de la recherche par mots-clés
	*/
	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _notificationLocalService.findByKeywordCount(keyword, groupId);
	}

	/**
	* Supprime les notifications dépubliées depuis au moins un mois
	*/
	@Override
	public void deleteOldUnpublishedNotifications()
		throws com.liferay.portal.kernel.exception.PortalException {
		_notificationLocalService.deleteOldUnpublishedNotifications();
	}

	/**
	* Modifie le statut de tous les notifications au statut "SCHEDULED" qui ont
	* une date de publication dans le passé.
	*/
	@Override
	public void publishRelevantNotifications()
		throws com.liferay.portal.kernel.exception.PortalException {
		_notificationLocalService.publishRelevantNotifications();
	}

	/**
	* Dépublie les notifications dont la date de fin est dépassée
	*/
	@Override
	public void unpublishPastNotifications()
		throws com.liferay.portal.kernel.exception.PortalException {
		_notificationLocalService.unpublishPastNotifications();
	}

	/**
	* Met à jour le statut de l'édition "manuellement"
	*/
	@Override
	public void updateStatus(
		eu.strasbourg.service.notification.model.Notification notification,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		_notificationLocalService.updateStatus(notification, status);
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