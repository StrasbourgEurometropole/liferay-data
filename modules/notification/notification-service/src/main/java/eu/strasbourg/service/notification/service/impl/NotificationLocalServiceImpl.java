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

package eu.strasbourg.service.notification.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import eu.strasbourg.service.notification.model.Notification;
import eu.strasbourg.service.notification.model.UserNotificationStatus;
import eu.strasbourg.service.notification.service.base.NotificationLocalServiceBaseImpl;
import eu.strasbourg.service.notification.service.persistence.UserNotificationStatusPK;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.utils.StrasbourgPropsUtil;

/**
 * The implementation of the notification local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.notification.service.NotificationLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see NotificationLocalServiceBaseImpl
 * @see eu.strasbourg.service.notification.service.NotificationLocalServiceUtil
 */
public class NotificationLocalServiceImpl extends NotificationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.notification.service.NotificationLocalServiceUtil}
	 * to access the notification local service.
	 */
	private final Log log = LogFactoryUtil.getLog(this.getClass());

	/**
	 * Crée une notification vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Notification createNotification(ServiceContext sc) throws PortalException {
		long pk = counterLocalService.increment();

		Notification notification = this.notificationLocalService.createNotification(pk);
		notification.setStatus(WorkflowConstants.STATUS_DRAFT);

		return notification;
	}

	/**
	 * Met à jour une édition et l'enregistre en base de données
	 */
	@Override
	public Notification updateNotification(Notification notification, ServiceContext sc) throws PortalException {

		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			if (notification.getPublicationDate().after(new Date())) {
				notification.setStatus(WorkflowConstants.STATUS_SCHEDULED);
			} else {
				notification.setStatus(WorkflowConstants.STATUS_APPROVED);
				this.createUserNotificationStatusesForNotification(notification);
				this.sendNotificationToUserChannels(notification);
			}
		} else {
			notification.setStatus(WorkflowConstants.STATUS_DRAFT);
			this.deleteUserNotificationStatusesForNotification(notification);
		}
		notification = this.notificationLocalService.updateNotification(notification);
		this.reindex(notification, false);

		return notification;
	}

	/**
	 * Création des UserNotificationStatus pour les utilisateurs concernés par
	 * une notification
	 */
	private void createUserNotificationStatusesForNotification(Notification notification) {
		// La notification a été publiée, on crée les instances
		// utilisateurs et on envoie les infos aux canaux de diffusions
		List<PublikUser> usersToNotify = notification.getUsersToNotify();
		for (PublikUser user : usersToNotify) {
			UserNotificationStatusPK userNotificationStatusPK = new UserNotificationStatusPK(
					notification.getNotificationId(), user.getPublikId());
			UserNotificationStatus existingStatus = this.userNotificationStatusLocalService
					.fetchUserNotificationStatus(userNotificationStatusPK);
			if (existingStatus == null) {
				UserNotificationStatus status = this.userNotificationStatusLocalService
						.createUserNotificationStatus(userNotificationStatusPK);
				this.userNotificationStatusLocalService.updateUserNotificationStatus(status);
			}
		}
	}

	/**
	 * Création des UserNotificationStatus pour les utilisateurs concernés par
	 * une notification
	 */
	private void sendNotificationToUserChannels(Notification notification) {
		// TODO : envoi sur les canaux
		// System.out.println("TODO : envoi sur les canaux");
	}

	/**
	 * Suppression de tous les UserNoficiationStatus pour une notification
	 */
	private void deleteUserNotificationStatusesForNotification(Notification notification) {
		// La notification a été dépubliée, on supprime les instances
		// utilisateurs
		List<UserNotificationStatus> userNotifications = this.userNotificationStatusLocalService
				.getByNotificationId(notification.getNotificationId());
		for (UserNotificationStatus status : userNotifications) {
			this.userNotificationStatusLocalService.deleteUserNotificationStatus(status);
		}
	}

	/**
	 * Met à jour le statut de l'édition "manuellement"
	 */
	@Override
	public void updateStatus(Notification notification, int status) throws PortalException {
		ServiceContext sc = new ServiceContext();
		if (status == WorkflowConstants.STATUS_APPROVED) {
			sc.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);
		} else {
			sc.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);
		}

		this.updateNotification(notification, sc);
	}

	/**
	 * Modifie le statut de tous les notifications au statut "SCHEDULED" qui ont
	 * une date de publication dans le passé.
	 */
	@Override
	public void publishRelevantNotifications() throws PortalException {
		List<Notification> notifications = this.notificationPersistence.findByPublicationDateAndStatus(new Date(),
				WorkflowConstants.STATUS_SCHEDULED);
		int n = 0;
		for (Notification notification : notifications) {
			this.updateStatus(notification, WorkflowConstants.STATUS_APPROVED);
			n++;
		}
		if (n > 0) {
			log.info("Published " + n + " notifications");
		}
	}

	/**
	 * Dépublie les notifications dont la date de fin est dépassée
	 */
	@Override
	public void unpublishPastNotifications() throws PortalException {
		List<Notification> pastNotifications = this.notificationPersistence.findByExpirationDateAndStatus(new Date(),
				WorkflowConstants.STATUS_APPROVED);
		for (Notification notification : pastNotifications) {
			if (notification.getStatus() != WorkflowConstants.STATUS_DRAFT) {
				this.updateStatus(notification, WorkflowConstants.STATUS_DRAFT);
			}
		}
	}

	/**
	 * Supprime les notifications dépubliées depuis au moins un mois
	 */
	@Override
	public void deleteOldUnpublishedNotifications() throws PortalException {
		LocalDate oneMonthAgoLocalDate = LocalDate.now().minusDays(StrasbourgPropsUtil.getDaysBeforeSuppression());
		Date oneMonthAgo = Date.from(oneMonthAgoLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<Notification> notifications = this.notificationPersistence.findByExpirationDateAndStatus(oneMonthAgo,
				WorkflowConstants.STATUS_DRAFT);
		for (Notification notification : notifications) {
			this.removeNotification(notification.getNotificationId());
		}
	}

	/**
	 * Supprime une notification
	 */
	@Override
	public Notification removeNotification(long notificationId) throws PortalException {

		// Supprime la notification
		Notification notification = notificationPersistence.remove(notificationId);

		// Suppression des UsersNotificationStatus
		this.deleteUserNotificationStatusesForNotification(notification);

		// Supprime l'index
		this.reindex(notification, true);

		return notification;
	}

	/**
	 * Reindex l'édition dans le moteur de recherche
	 */
	private void reindex(Notification notification, boolean delete) throws SearchException {
		Indexer<Notification> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Notification.class);
		if (delete) {
			indexer.delete(notification);
		} else {
			indexer.reindex(notification);
		}
	}

	/**
	 * Lance une recherche selon le searchContext
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<Notification> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Notification.class);
		return indexer.search(searchContext);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	@Override
	public List<Notification> findByKeyword(String keyword, long groupId, int start, int end) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (keyword.length() > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}
		dynamicQuery.add(PropertyFactoryUtil.forName("status").eq(WorkflowConstants.STATUS_APPROVED));

		return notificationPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Compte de la recherche par mots-clés
	 */
	@Override
	public long findByKeywordCount(String keyword, long groupId) {
		DynamicQuery dynamicQuery = dynamicQuery();
		if (keyword.length() > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}
		dynamicQuery.add(PropertyFactoryUtil.forName("status").eq(WorkflowConstants.STATUS_APPROVED));

		return notificationPersistence.countWithDynamicQuery(dynamicQuery);
	}

}