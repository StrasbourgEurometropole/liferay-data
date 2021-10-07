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

package eu.strasbourg.service.notif.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import eu.strasbourg.service.notif.model.Notification;
import eu.strasbourg.service.notif.service.base.NotificationLocalServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the notification local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.notif.service.NotificationLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NotificationLocalServiceBaseImpl
 */
public class NotificationLocalServiceImpl
	extends NotificationLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.notif.service.NotificationLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.notif.service.NotificationLocalServiceUtil</code>.
	 */

	/**
	 * Crée une offre vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Notification createNotification(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Notification notification = this.notificationLocalService.createNotification(pk);

		notification.setGroupId(sc.getScopeGroupId());
		notification.setUserName(user.getFullName());
		notification.setUserId(sc.getUserId());

		notification.setStatus(WorkflowConstants.STATUS_DRAFT);

		return notification;
	}

	@Override
	public Notification duplicateNotification(ServiceContext sc, Notification notificationToCopy) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Notification notification = (Notification)notificationToCopy.clone();

		notification.setGroupId(sc.getScopeGroupId());
		notification.setUserName(user.getFullName());
		notification.setUserId(sc.getUserId());
		notification.setNew(true);
		notification.setPrimaryKey(pk);

		String uuid = PortalUUIDUtil.generate();

		notification.setUuid(uuid);

		return notification;
	}

	/**
	 * Met à jour une notification et l'enregistre en base de données
	 */
	@Override
	public Notification updateNotification(Notification notification, ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		notification.setStatusByUserId(sc.getUserId());
		notification.setStatusByUserName(user.getFullName());
		notification.setStatusDate(sc.getModifiedDate());

		// Si on n'utilise pas le framework workflow, simple gestion
		// brouillon/publié
		if (!WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
				sc.getCompanyId(), sc.getScopeGroupId(), Notification.class.getName())) {
			if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
				notification.setStatus(WorkflowConstants.STATUS_APPROVED);
			} else {
				notification.setStatus(WorkflowConstants.STATUS_DRAFT);
			}
			notification = this.notificationLocalService.updateNotification(notification);
			this.updateAssetEntry(notification, sc);
			this.reindex(notification, false);
		} else { // Si le framework worflow est actif, c'est celui-ci qui gère
			// l'enregistrement
			notification = this.notificationLocalService.updateNotification(notification);
			this.updateAssetEntry(notification, sc);
			// On passe l'utilisateur qui a modifié le statut de l'offre (statusByUserId)
			WorkflowHandlerRegistryUtil.startWorkflowInstance(
					notification.getCompanyId(), notification.getGroupId(),
					notification.getStatusByUserId(), Notification.class.getName(),
					notification.getPrimaryKey(), notification, sc);
		}

		return notification;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à l'offre
	 */
	private void updateAssetEntry(Notification notification, ServiceContext sc)
			throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				notification.getCreateDate(), // Date of creation
				notification.getModifiedDate(), // Date of modification
				Notification.class.getName(), // Class name
				notification.getPrimaryKey(), // Class PK
				notification.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				notification.isApproved(), // Visible
				notification.getStartDate(), // Start date
				notification.getEndDate(), // End date
				notification.getBroadcastDate(), // Publication date
				notification.getBroadcastDate(), // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				notification.getTitle(), // Title
				notification.getSubtitle(), // Description
				notification.getSubtitle(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe l'edition
		this.reindex(notification, false);
	}

	/**
	 * Supprime une notification
	 */
	@Override
	public Notification removeNotification(long notificationId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
				.fetchEntry(Notification.class.getName(), notificationId);

		if (entry != null) {
			// Delete the link with categories
			for (long categoryId : entry.getCategoryIds()) {
				this.assetEntryLocalService.deleteAssetCategoryAssetEntry(
						categoryId, entry.getEntryId());
			}

			// Delete the link with tags
			long[] tagIds = AssetEntryLocalServiceUtil
					.getAssetTagPrimaryKeys(entry.getEntryId());
			for (long tagId : tagIds) {
				AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagId,
						entry.getEntryId());
			}

			// Supprime lien avec les autres entries
			List<AssetLink> links = this.assetLinkLocalService
					.getLinks(entry.getEntryId());
			for (AssetLink link : links) {
				this.assetLinkLocalService.deleteAssetLink(link);
			}

			// Delete the AssetEntry
			AssetEntryLocalServiceUtil.deleteEntry(Notification.class.getName(),
					notificationId);
		}

		// Delete the Notification
		Notification notification = notificationPersistence.remove(notificationId);

		// Delete the index
		this.reindex(notification, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
				notification.getCompanyId(), notification.getGroupId(),
				Notification.class.getName(), notification.getNotificationId());

		return notification;
	}

	/**
	 * Reindex la notification dans le moteur de recherche
	 */
	private void reindex(Notification notification, boolean delete)
			throws SearchException {
		Indexer<Notification> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(Notification.class);
		if (delete) {
			indexer.delete(notification);
		} else {
			indexer.reindex(notification);
		}
	}

	@Override
	public List<Notification> getByServiceIds(long[] serviceIds) {
		return notificationPersistence.findByServiceIds(serviceIds);
	}
}