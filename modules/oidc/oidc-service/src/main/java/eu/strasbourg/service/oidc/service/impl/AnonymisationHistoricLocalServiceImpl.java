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

package eu.strasbourg.service.oidc.service.impl;

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
import eu.strasbourg.service.oidc.model.AnonymisationHistoric;
import eu.strasbourg.service.oidc.service.base.AnonymisationHistoricLocalServiceBaseImpl;
import eu.strasbourg.service.oidc.utils.OIDCAnonymiser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

/**
 * The implementation of the anonymisation historic local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.oidc.service.AnonymisationHistoricLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnonymisationHistoricLocalServiceBaseImpl
 * @see eu.strasbourg.service.oidc.service.AnonymisationHistoricLocalServiceUtil
 */
public class AnonymisationHistoricLocalServiceImpl
	extends AnonymisationHistoricLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.oidc.service.AnonymisationHistoricLocalServiceUtil} to access the anonymisation historic local service.
	 */


	public final static Log log = LogFactoryUtil.getLog(AnonymisationHistoricLocalServiceImpl.class);

	/**
	 * Crée une entree d'anonymisation vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public AnonymisationHistoric createAnonymisationHistoric(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		AnonymisationHistoric anonymisationHistoric = this.anonymisationHistoricLocalService.createAnonymisationHistoric(pk);

		anonymisationHistoric.setGroupId(sc.getScopeGroupId());
		anonymisationHistoric.setCompanyId(sc.getCompanyId());
		anonymisationHistoric.setUserName(user.getFullName().equals("") ? "Serveur" : user.getFullName());
		anonymisationHistoric.setUserId(sc.getUserId());

		anonymisationHistoric.setStatus(WorkflowConstants.STATUS_DRAFT);

		return anonymisationHistoric;
	}

	/**
	 * Met à jour une entree d'anonymisation et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public AnonymisationHistoric updateAnonymisationHistoric(AnonymisationHistoric anonymisationHistoric, ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		anonymisationHistoric.setStatusByUserId(sc.getUserId());
		anonymisationHistoric.setStatusByUserName(user.getFullName());
		anonymisationHistoric.setStatusDate(sc.getModifiedDate());
		anonymisationHistoric.setCompanyId(sc.getCompanyId());

		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			anonymisationHistoric.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			anonymisationHistoric.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		anonymisationHistoric = this.anonymisationHistoricLocalService.updateAnonymisationHistoric(anonymisationHistoric);

		this.updateAssetEntry(anonymisationHistoric, sc);
		this.reindex(anonymisationHistoric, false);

		return anonymisationHistoric;
	}

	/**
	 * Met à jour l'AssetEntry rattachee à l'entree d'anonymisation
	 */
	private void updateAssetEntry(AnonymisationHistoric anonymisationHistoric, ServiceContext sc) throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				anonymisationHistoric.getCreateDate(), // Date of creation
				anonymisationHistoric.getModifiedDate(), // Date of modification
				AnonymisationHistoric.class.getName(), // Class name
				anonymisationHistoric.getPrimaryKey(), // Class PK
				anonymisationHistoric.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				anonymisationHistoric.isApproved(), // Visible
				anonymisationHistoric.getCreateDate(), // Start date
				null, // End date
				anonymisationHistoric.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				Integer.toString(anonymisationHistoric.getResult()), // Title
				anonymisationHistoric.getErrorDescription(), // Description
				anonymisationHistoric.getErrorDescription(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe l'entite
		this.reindex(anonymisationHistoric, false);
	}

	/**
	 * Met à jour le statut de l'entree d'anonymisation par le framework workflow
	 */
	@Override
	public AnonymisationHistoric updateStatus(long userId, long entryId, int status,
									   ServiceContext sc, Map<String, Serializable> workflowContext)
			throws PortalException {
		Date now = new Date();
		// Statut de l'entité
		AnonymisationHistoric anonymisationHistoric = this.getAnonymisationHistoric(entryId);
		anonymisationHistoric.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			anonymisationHistoric.setStatusByUserId(user.getUserId());
			anonymisationHistoric.setStatusByUserName(user.getFullName());
		}
		anonymisationHistoric.setStatusDate(new Date());
		anonymisationHistoric = this.anonymisationHistoricLocalService.updateAnonymisationHistoric(anonymisationHistoric);

		// Statut de l'entry
		AssetEntry entry = this.assetEntryLocalService
				.getEntry(AnonymisationHistoric.class.getName(), anonymisationHistoric.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(anonymisationHistoric, false);

		return anonymisationHistoric;
	}

	/**
	 * Met à jour le statut de l'entree d'anonymisation "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(AnonymisationHistoric anonymisationHistoric, int status) throws PortalException {
		this.updateStatus(anonymisationHistoric.getUserId(), anonymisationHistoric.getAnonymisationHistoricId(), status, null,
				null);
	}

	/**
	 * Supprime l'entree d'anonymisation
	 */
	@Override
	public AnonymisationHistoric removeAnonymisationHistoric(long anonymisationHistoricId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
				.fetchEntry(AnonymisationHistoric.class.getName(), anonymisationHistoricId);

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
			AssetEntryLocalServiceUtil.deleteEntry(AnonymisationHistoric.class.getName(),
					anonymisationHistoricId);

		}

		// Supprime l'entree d'historique
		AnonymisationHistoric anonymisationHistoric = anonymisationHistoricPersistence.remove(anonymisationHistoricId);

		// Delete the index
		this.reindex(anonymisationHistoric, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
				anonymisationHistoric.getCompanyId(), anonymisationHistoric.getGroupId(), AnonymisationHistoric.class.getName(),
				anonymisationHistoric.getAnonymisationHistoricId());

		return anonymisationHistoric;
	}

	/**
	 * Reindex l'anonymisation dans le moteur de recherche
	 */
	private void reindex(AnonymisationHistoric anonymisationHistoric, boolean delete) throws SearchException {
		Indexer<AnonymisationHistoric> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(AnonymisationHistoric.class);
		if (delete) {
			indexer.delete(anonymisationHistoric);
		} else {
			indexer.reindex(anonymisationHistoric);
		}
	}

	/**
	 * Renvoie la liste des vocabulaires rattachés à l'anonymisation
	 */
	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
				.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil
				.getClassNameId(AnonymisationHistoric.class);
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
	 * Effectue l'anonymisation des donnees issues des fichiers GTFS
	 */
	@Override
	public void doAnonymisation(ServiceContext sc, AnonymisationHistoric anonymisationHistoric) {
		OIDCAnonymiser anonymisation = new OIDCAnonymiser(sc, anonymisationHistoric);
		anonymisation.doAnonymisation();
	}

	/**
	 * Retourne toutes les anonymisations d'un groupe
	 */
	@Override
	public List<AnonymisationHistoric> getByGroupId(long groupId) {
		return this.anonymisationHistoricPersistence.findByGroupId(groupId);
	}

	/**
	 * Recherche par mot clés
	 */
	@Override
	public List<AnonymisationHistoric> findByKeyword(String keyword, long groupId, int start, int end) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (keyword.length() > 0) {
			dynamicQuery.add(
					RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery
					.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return this.anonymisationHistoricPersistence.findWithDynamicQuery(dynamicQuery, start, end);
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
		return this.anonymisationHistoricPersistence.countWithDynamicQuery(dynamicQuery);
	}
}