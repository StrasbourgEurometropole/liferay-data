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

package eu.strasbourg.service.ejob.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import eu.strasbourg.service.ejob.model.Alert;
import eu.strasbourg.service.ejob.service.base.AlertLocalServiceBaseImpl;
import eu.strasbourg.utils.AssetVocabularyHelper;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The implementation of the alert local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.ejob.service.AlertLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AlertLocalServiceBaseImpl
 */
public class AlertLocalServiceImpl extends AlertLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.ejob.service.AlertLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.ejob.service.AlertLocalServiceUtil</code>.
	 */

	/**
	 * Crée une edition vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Alert createAlert(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Alert alert = this.alertLocalService.createAlert(pk);

		alert.setGroupId(sc.getScopeGroupId());
		alert.setUserName(user.getFullName());
		alert.setUserId(sc.getUserId());

		alert.setStatus(WorkflowConstants.STATUS_DRAFT);

		return alert;
	}

	/**
	 * Met à jour une edition et l'enregistre en base de données
	 */
	@Override
	public Alert updateAlert(Alert alert, ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		alert.setStatusByUserId(sc.getUserId());
		alert.setStatusByUserName(user.getFullName());
		alert.setStatusDate(sc.getModifiedDate());

		// Si on n'utilise pas le framework workflow, simple gestion
		// brouillon/publié
		if (!WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
				sc.getCompanyId(), sc.getScopeGroupId(), Alert.class.getName())) {
			if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
				alert.setStatus(WorkflowConstants.STATUS_APPROVED);
			} else {
				alert.setStatus(WorkflowConstants.STATUS_DRAFT);
			}
			alert = this.alertLocalService.updateAlert(alert);
			this.updateAssetEntry(alert, sc);
			this.reindex(alert, false);
		} else { // Si le framework worflow est actif, c'est celui-ci qui gère
			// l'enregistrement
			alert = this.alertLocalService.updateAlert(alert);
			WorkflowHandlerRegistryUtil.startWorkflowInstance(
					alert.getCompanyId(), alert.getGroupId(),
					alert.getUserId(), Alert.class.getName(),
					alert.getPrimaryKey(), alert, sc);
		}

		return alert;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à l'edition
	 */
	private void updateAssetEntry(Alert alert, ServiceContext sc)
			throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				alert.getCreateDate(), // Date of creation
				alert.getModifiedDate(), // Date of modification
				Alert.class.getName(), // Class name
				alert.getPrimaryKey(), // Class PK
				alert.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				alert.isApproved(), // Visible
				alert.getCreateDate(), // Start date
				null, // End date
				alert.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				alert.getName(), // Title
				alert.getName(), // Description
				alert.getName(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe l'edition
		this.reindex(alert, false);
	}

	/**
	 * Met à jour le statut de l'edition par le framework workflow
	 */
	@Override
	public Alert updateStatus(long userId, long entryId, int status,
								ServiceContext sc, Map<String, Serializable> workflowContext)
			throws PortalException {
		Date now = new Date();
		// Statut de l'entité
		Alert alert = this.getAlert(entryId);
		alert.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			alert.setStatusByUserId(user.getUserId());
			alert.setStatusByUserName(user.getFullName());
		}
		alert.setStatusDate(new Date());
		alert = this.alertLocalService.updateAlert(alert);

		// Statut de l'entry
		AssetEntry entry = this.assetEntryLocalService
				.getEntry(Alert.class.getName(), alert.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(alert, false);

		return alert;
	}

	/**
	 * Met à jour le statut de l'edition "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(Alert alert, int status)
			throws PortalException {
		this.updateStatus(alert.getUserId(), alert.getAlertId(), status,
				null, null);
	}

	/**
	 * Supprime une alerte
	 */
	@Override
	public Alert removeAlert(long alertId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
				.fetchEntry(Alert.class.getName(), alertId);

		if (entry != null) {
			// Delete the link with categories
			for (long categoryId : entry.getCategoryIds()) {
				this.assetEntryLocalService.deleteAssetCategoryAssetEntry(
						categoryId, entry.getEntryId());
			}

			// Delete the link with tags
			long[] tagIds = AssetEntryLocalServiceUtil
					.getAssetTagPrimaryKeys(entry.getEntryId());
			for (int i = 0; i < tagIds.length; i++) {
				AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagIds[i],
						entry.getEntryId());
			}

			// Supprime lien avec les autres entries
			List<AssetLink> links = this.assetLinkLocalService
					.getLinks(entry.getEntryId());
			for (AssetLink link : links) {
				this.assetLinkLocalService.deleteAssetLink(link);
			}

			// Delete the AssetEntry
			AssetEntryLocalServiceUtil.deleteEntry(Alert.class.getName(),
					alertId);

		}

		// Delete the Edition
		Alert alert = alertPersistence.remove(alertId);

		// Delete the index
		this.reindex(alert, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
				alert.getCompanyId(), alert.getGroupId(),
				Alert.class.getName(), alert.getAlertId());

		return alert;
	}

	/**
	 * Reindex l'edition dans le moteur de recherche
	 */
	private void reindex(Alert alert, boolean delete)
			throws SearchException {
		Indexer<Alert> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(Alert.class);
		if (delete) {
			indexer.delete(alert);
		} else {
			indexer.reindex(alert);
		}
	}

	/**
	 * Retourne les vocabulaires rattrachés à ce type d'entité pour un groupe
	 */
	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		long classNameId = ClassNameLocalServiceUtil
				.getClassNameId(Alert.class);
		return AssetVocabularyHelper.getVocabulariesForAssetType(groupId,
				classNameId);
	}

	@Override
	public List<Alert> findByKeyword(String keyword, long groupId,
											  int start, int end) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (keyword.length() > 0) {
			dynamicQuery.add(
					RestrictionsFactoryUtil.like("name", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery
					.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return alertPersistence.findWithDynamicQuery(dynamicQuery, start,
				end);
	}

	@Override
	public long findByKeywordCount(String keyword, long groupId) {
		DynamicQuery dynamicQuery = dynamicQuery();
		if (keyword.length() > 0) {
			dynamicQuery.add(
					RestrictionsFactoryUtil.like("name", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery
					.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return alertPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Retourne un arret via son stopId CTS
	 */
	@Override
	public List<Alert> findByPublikUserId(String publikUserId) {
		return this.alertPersistence.findByPublikUserId(publikUserId);
	}
}