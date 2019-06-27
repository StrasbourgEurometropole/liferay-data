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

package eu.strasbourg.service.gtfs.service.impl;

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

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import eu.strasbourg.service.gtfs.model.Arret;
import eu.strasbourg.service.gtfs.service.DirectionLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.base.ArretLocalServiceBaseImpl;

/**
 * The implementation of the arret local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.gtfs.service.ArretLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see ArretLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.ArretLocalServiceUtil
 */
public class ArretLocalServiceImpl extends ArretLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.gtfs.service.ArretLocalServiceUtil} to access the arret local service.
	 */
	
	public final static Log log = LogFactoryUtil.getLog(ArretLocalServiceImpl.class);
	
	/**
	 * Crée une entree avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Arret createArret(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());
		
		long pk = counterLocalService.increment();
		
		Arret arret = this.arretLocalService.createArret(pk);
		
		arret.setGroupId(sc.getScopeGroupId());
		arret.setUserId(sc.getUserId());
		
		if (user != null)
			arret.setUserName(user.getFullName());
		
		arret.setStatus(WorkflowConstants.STATUS_DRAFT);
		
		return arret;
	}
	
	/**
	 * Met à jour une entree et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public Arret updateArret(Arret arret, ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());
		
		arret.setStatusByUserId(sc.getUserId());
		arret.setStatusDate(sc.getModifiedDate());
		if (user != null)
			arret.setStatusByUserName(user.getFullName());
		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			arret.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			// Supprime les Direction associées
			// Le GTFS ne devrait plus posseder d'indentifiant relatif a cet arret 
			// mais c'est une securite
			DirectionLocalServiceUtil.removeByStopId(arret.getStopId());
			arret.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		
		arret = this.arretLocalService.updateArret(arret);
		
		this.updateAssetEntry(arret, sc);
		this.reindex(arret, false);

		return arret;
	}
	
	/**
	 * Met à jour l'AssetEntry rattachee à l'entite
	 */
	private void updateAssetEntry(Arret arret, ServiceContext sc) throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				arret.getCreateDate(), // Date of creation
				arret.getModifiedDate(), // Date of modification
				Arret.class.getName(), // Class name
				arret.getPrimaryKey(), // Class PK
				arret.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				arret.isApproved(), // Visible
				arret.getCreateDate(), // Start date
				null, // End date
				arret.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				arret.getTitle(), // Title
				arret.getCode(), // Description
				arret.getCode(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe l'entite
		this.reindex(arret, false);
	}
	
	/**
	 * Met à jour le statut de l'entree par le framework workflow
	 */
	@Override
	public Arret updateStatus(long userId, long entryId, int status,
								ServiceContext sc, Map<String, Serializable> workflowContext)
			throws PortalException {
		Date now = new Date();
		// Statut de l'entité
		Arret arret = this.getArret(entryId);
		arret.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			arret.setStatusByUserId(user.getUserId());
			arret.setStatusByUserName(user.getFullName());
		}
		arret.setStatusDate(new Date());
		arret = this.arretLocalService.updateArret(arret);
		
		// Statut de l'entry
		AssetEntry entry = this.assetEntryLocalService
				.getEntry(Arret.class.getName(), arret.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(arret, false);

		return arret;
	}
	
	/**
	 * Supprime l'entree
	 */
	@Override
	public Arret removeArret(long arretId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(Arret.class.getName(), arretId);

		if (entry != null) {
			// Supprime le lien avec les categories
			for (long categoryId : entry.getCategoryIds()) {
				this.assetEntryLocalService.deleteAssetCategoryAssetEntry(categoryId, entry.getEntryId());
			}

			// Supprime le lien avec les etiquettes
			long[] tagIds = AssetEntryLocalServiceUtil.getAssetTagPrimaryKeys(entry.getEntryId());
			for (int i = 0; i < tagIds.length; i++) {
				AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagIds[i],entry.getEntryId());
			}

			// Supprime le lien avec les autres entries
			List<AssetLink> links = this.assetLinkLocalService.getLinks(entry.getEntryId());
			for (AssetLink link : links) {
				this.assetLinkLocalService.deleteAssetLink(link);
			}

			// Delete the AssetEntry
			AssetEntryLocalServiceUtil.deleteEntry(Arret.class.getName(), arretId);

		}		
		
		// Supprime l'entree
		Arret arret = arretPersistence.remove(arretId);
		
		// Supprime les Direction associées
		// Le GTFS ne devrait plus posseder d'indentifiant relatif a cet arret 
		// mais c'est une securite
		DirectionLocalServiceUtil.removeByStopId(arret.getStopId());

		// Supprime l'index
		this.reindex(arret, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
				arret.getCompanyId(), arret.getGroupId(), Arret.class.getName(),
				arret.getArretId());

		return arret;
	}
	
	/**
	 * Reindex l'entree dans le moteur de recherche
	 */
	private void reindex(Arret arret, boolean delete) throws SearchException {
		Indexer<Arret> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Arret.class);
		if (delete) {
			indexer.delete(arret);
		} else {
			indexer.reindex(arret);
		}
	}
	
	/**
	 * Renvoie la liste des vocabulaires rattachés à l'entree
	 */
	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(Arret.class);
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
	 * Retourne toutes les entrees d'un groupe
	 */
	@Override
	public List<Arret> getByGroupId(long groupId) {
		return this.arretPersistence.findByGroupId(groupId);
	}
	
	/**
	 * Retourne un arret via son stopId CTS
	 */
	@Override
	public Arret getByStopId(String stopId) {
		return this.arretPersistence.fetchByStopId(stopId);
	}
	
	/**
	 * Recherche par mot clés
	 */
	@Override
	public List<Arret> findByKeyword(String keyword, long groupId, int start, int end) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (keyword.length() > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return this.arretPersistence.findWithDynamicQuery(dynamicQuery, start, end);
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
		return arretPersistence.countWithDynamicQuery(dynamicQuery);
	}
	
}