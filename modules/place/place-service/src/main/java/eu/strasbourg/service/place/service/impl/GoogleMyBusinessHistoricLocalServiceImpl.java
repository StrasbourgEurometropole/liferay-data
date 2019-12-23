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

package eu.strasbourg.service.place.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.place.model.GoogleMyBusinessHistoric;
import eu.strasbourg.service.place.service.base.GoogleMyBusinessHistoricLocalServiceBaseImpl;
import eu.strasbourg.service.place.utils.GoogleSynchronisation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

/**
 * The implementation of the google my business historic local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.place.service.GoogleMyBusinessHistoricLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see GoogleMyBusinessHistoricLocalServiceBaseImpl
 * @see eu.strasbourg.service.place.service.GoogleMyBusinessHistoricLocalServiceUtil
 */
public class GoogleMyBusinessHistoricLocalServiceImpl
	extends GoogleMyBusinessHistoricLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.place.service.GoogleMyBusinessHistoricLocalServiceUtil} to access the google my business historic local service.
	 */


	public final static Log log = LogFactoryUtil.getLog(GoogleMyBusinessHistoricLocalServiceImpl.class);

	/**
	 * Crée une entree google mybusiness vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public GoogleMyBusinessHistoric createGoogleMyBusinessHistoric(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		GoogleMyBusinessHistoric googleMyBusinessHistoric = this.googleMyBusinessHistoricLocalService.createGoogleMyBusinessHistoric(pk);

		googleMyBusinessHistoric.setGroupId(sc.getScopeGroupId());
		googleMyBusinessHistoric.setCompanyId(sc.getCompanyId());
		googleMyBusinessHistoric.setUserName(user.getFullName().equals("") ? "Serveur" : user.getFullName());
		googleMyBusinessHistoric.setUserId(sc.getUserId());

		googleMyBusinessHistoric.setStatus(WorkflowConstants.STATUS_DRAFT);

		return googleMyBusinessHistoric;
	}

	/**
	 * Met à jour une entree google mybusiness et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public GoogleMyBusinessHistoric updateGoogleMyBusinessHistoric(GoogleMyBusinessHistoric googleMyBusinessHistoric, ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		googleMyBusinessHistoric.setStatusByUserId(sc.getUserId());
		googleMyBusinessHistoric.setStatusByUserName(user.getFullName());
		googleMyBusinessHistoric.setStatusDate(sc.getModifiedDate());
		googleMyBusinessHistoric.setCompanyId(sc.getCompanyId());

		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			googleMyBusinessHistoric.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			googleMyBusinessHistoric.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		googleMyBusinessHistoric = this.googleMyBusinessHistoricLocalService.updateGoogleMyBusinessHistoric(googleMyBusinessHistoric);

		this.updateAssetEntry(googleMyBusinessHistoric, sc);
		this.reindex(googleMyBusinessHistoric, false);

		return googleMyBusinessHistoric;
	}

	/**
	 * Met à jour l'AssetEntry rattachee à l'entree google mybusiness
	 */
	private void updateAssetEntry(GoogleMyBusinessHistoric googleMyBusinessHistoric, ServiceContext sc) throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				googleMyBusinessHistoric.getCreateDate(), // Date of creation
				googleMyBusinessHistoric.getModifiedDate(), // Date of modification
				GoogleMyBusinessHistoric.class.getName(), // Class name
				googleMyBusinessHistoric.getPrimaryKey(), // Class PK
				googleMyBusinessHistoric.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				googleMyBusinessHistoric.isApproved(), // Visible
				googleMyBusinessHistoric.getCreateDate(), // Start date
				null, // End date
				googleMyBusinessHistoric.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				Integer.toString(googleMyBusinessHistoric.getResult()), // Title
				googleMyBusinessHistoric.getErrorDescription(), // Description
				googleMyBusinessHistoric.getErrorDescription(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe l'entite
		this.reindex(googleMyBusinessHistoric, false);
	}

	/**
	 * Met à jour le statut de l'entree google mybusiness par le framework workflow
	 */
	@Override
	public GoogleMyBusinessHistoric updateStatus(long userId, long entryId, int status,
											  ServiceContext sc, Map<String, Serializable> workflowContext)
			throws PortalException {
		Date now = new Date();
		// Statut de l'entité
		GoogleMyBusinessHistoric googleMyBusinessHistoric = this.getGoogleMyBusinessHistoric(entryId);
		googleMyBusinessHistoric.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			googleMyBusinessHistoric.setStatusByUserId(user.getUserId());
			googleMyBusinessHistoric.setStatusByUserName(user.getFullName());
		}
		googleMyBusinessHistoric.setStatusDate(new Date());
		googleMyBusinessHistoric = this.googleMyBusinessHistoricLocalService.updateGoogleMyBusinessHistoric(googleMyBusinessHistoric);

		// Statut de l'entry
		AssetEntry entry = this.assetEntryLocalService
				.getEntry(GoogleMyBusinessHistoric.class.getName(), googleMyBusinessHistoric.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(googleMyBusinessHistoric, false);

		return googleMyBusinessHistoric;
	}

	/**
	 * Met à jour le statut de l'entree google mybusiness "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(GoogleMyBusinessHistoric googleMyBusinessHistoric, int status) throws PortalException {
		this.updateStatus(googleMyBusinessHistoric.getUserId(), googleMyBusinessHistoric.getGoogleMyBusinessHistoricId(), status, null,
				null);
	}

	/**
	 * Supprime l'entree google mybusiness
	 */
	@Override
	public GoogleMyBusinessHistoric removeGoogleMyBusinessHistoric(long googleMyBusinessHistoricId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
				.fetchEntry(GoogleMyBusinessHistoric.class.getName(), googleMyBusinessHistoricId);

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
			AssetEntryLocalServiceUtil.deleteEntry(GoogleMyBusinessHistoric.class.getName(),
					googleMyBusinessHistoricId);

		}

		// Supprime l'entree d'historique
		GoogleMyBusinessHistoric googleMyBusinessHistoric = googleMyBusinessHistoricPersistence.remove(googleMyBusinessHistoricId);

		// Delete the index
		this.reindex(googleMyBusinessHistoric, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
				googleMyBusinessHistoric.getCompanyId(), googleMyBusinessHistoric.getGroupId(), GoogleMyBusinessHistoric.class.getName(),
				googleMyBusinessHistoric.getGoogleMyBusinessHistoricId());

		return googleMyBusinessHistoric;
	}

	/**
	 * Reindex google mybusiness dans le moteur de recherche
	 */
	private void reindex(GoogleMyBusinessHistoric googleMyBusinessHistoric, boolean delete) throws SearchException {
		Indexer<GoogleMyBusinessHistoric> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(GoogleMyBusinessHistoric.class);
		if (delete) {
			indexer.delete(googleMyBusinessHistoric);
		} else {
			indexer.reindex(googleMyBusinessHistoric);
		}
	}

	/**
	 * Renvoie la liste des vocabulaires rattachés à google mybusiness
	 */
	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
				.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil
				.getClassNameId(GoogleMyBusinessHistoric.class);
		for (AssetVocabulary vocabulary : vocabularies) {
			if (vocabulary.getGroupId() == groupId
					&& LongStream.of(vocabulary.getSelectedClassNameIds())
					.anyMatch(c -> c == classNameId)) {
				attachedVocabularies.add(vocabulary);
			}
		}
		return attachedVocabularies;
	}

	/**
	 * Retourne tous les google mybusiness d'un groupe
	 */
	@Override
	public List<GoogleMyBusinessHistoric> getByGroupId(long groupId) {
		return this.googleMyBusinessHistoricPersistence.findByGroupId(groupId);
	}

	/**
	 * Recherche par mot clés
	 */
	@Override
	public List<GoogleMyBusinessHistoric> findByKeyword(String keyword, long groupId, int start, int end) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (keyword.length() > 0) {
			dynamicQuery.add(
					RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery
					.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return this.googleMyBusinessHistoricPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Recherche par mot clés (compte)
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
		return this.googleMyBusinessHistoricPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Effectue la synchronisation
	 */
	@Override
	public void doSynchronisation(ServiceContext sc, GoogleMyBusinessHistoric googleMyBusinessHistoric) {
		GoogleSynchronisation googleSynchronisation = new GoogleSynchronisation(sc, googleMyBusinessHistoric);
		googleSynchronisation.doSynchronisation();
	}
}