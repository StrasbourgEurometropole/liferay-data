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
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import eu.strasbourg.service.gtfs.model.ImportHistoric;
import eu.strasbourg.service.gtfs.model.Ligne;
import eu.strasbourg.service.gtfs.service.DirectionLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.base.LigneLocalServiceBaseImpl;

/**
 * The implementation of the ligne local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.gtfs.service.LigneLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see LigneLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.LigneLocalServiceUtil
 */
public class LigneLocalServiceImpl extends LigneLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.gtfs.service.LigneLocalServiceUtil} to access the ligne local service.
	 */
	
	public final static Log log = LogFactoryUtil.getLog(LigneLocalServiceImpl.class);
	
	/**
	 * Crée une entree avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Ligne createLigne(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());
		
		long pk = counterLocalService.increment();
		
		Ligne ligne = this.ligneLocalService.createLigne(pk);
		
		ligne.setGroupId(sc.getScopeGroupId());
		ligne.setUserId(sc.getUserId());
		
		if (user != null)
			ligne.setUserName(user.getFullName());

		ligne.setStatus(WorkflowConstants.STATUS_DRAFT);

		return ligne;
	}
	
	/**
	 * Met à jour une entree et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public Ligne updateLigne(Ligne ligne, ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());
		
		ligne.setStatusByUserId(sc.getUserId());
		ligne.setStatusDate(sc.getModifiedDate());
		
		if (user != null)
			ligne.setStatusByUserName(user.getFullName());

		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			ligne.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			ligne.setStatus(WorkflowConstants.STATUS_DRAFT);
			// Supprime les Direction associées
			// Le GTFS ne devrait plus posseder d'indentifiant relatif a cette ligne 
			// mais c'est une securite
			DirectionLocalServiceUtil.removeByRouteId(ligne.getRouteId());
		}
		
		ligne = this.ligneLocalService.updateLigne(ligne);
		
		this.updateAssetEntry(ligne, sc);
		this.reindex(ligne, false);

		return ligne;
	}
	
	/**
	 * Met à jour les entree donnees
	 * @throws IOException
	 */
	@Override
	public void updateLignes(List<Ligne> lignes, ServiceContext sc) throws PortalException {
		for (Ligne ligne : lignes) {
			this.updateLigne(ligne, sc);
		}
	}
	
	/**
	 * Met à jour l'AssetEntry rattachee à l'entite
	 */
	private void updateAssetEntry(Ligne ligne, ServiceContext sc) throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				ligne.getCreateDate(), // Date of creation
				ligne.getModifiedDate(), // Date of modification
				Ligne.class.getName(), // Class name
				ligne.getPrimaryKey(), // Class PK
				ligne.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				ligne.isApproved(), // Visible
				ligne.getCreateDate(), // Start date
				null, // End date
				ligne.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				ligne.getTitle(), // Title
				ligne.getShortName(), // Description
				ligne.getShortName(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe l'entite
		this.reindex(ligne, false);
	}
	
	/**
	 * Met à jour le statut de l'entree par le framework workflow
	 */
	@Override
	public Ligne updateStatus(long userId, long entryId, int status,
								ServiceContext sc, Map<String, Serializable> workflowContext)
			throws PortalException {
		Date now = new Date();
		// Statut de l'entité
		Ligne ligne = this.getLigne(entryId);
		ligne.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			ligne.setStatusByUserId(user.getUserId());
			ligne.setStatusByUserName(user.getFullName());
		}
		ligne.setStatusDate(new Date());
		ligne = this.ligneLocalService.updateLigne(ligne);
		
		// Statut de l'entry
		AssetEntry entry = this.assetEntryLocalService
				.getEntry(Ligne.class.getName(), ligne.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(ligne, false);

		return ligne;
	}
	
	/**
	 * Met à jour le statut "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(Ligne ligne, int status) throws PortalException {
		this.updateStatus(ligne.getUserId(), ligne.getLigneId(), status, null, null);
	}
	
	/**
	 * Met à jour le statut "manuellement" (pas via le workflow) des entrees
	 */
	@Override
	public void unpublishLignes(List<Ligne> lignes, ImportHistoric importHistoric, ServiceContext sc) throws PortalException {
		for (Ligne ligne : lignes) {
			importHistoric.addNewOperation("Unpublished ligne : " + ligne.getShortName());
			this.updateStatus(ligne, WorkflowConstants.STATUS_DRAFT);
		}
	}
	
	/**
	 * Supprime l'entree
	 */
	@Override
	public Ligne removeLigne(long ligneId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(Ligne.class.getName(), ligneId);

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
			AssetEntryLocalServiceUtil.deleteEntry(Ligne.class.getName(), ligneId);

		}
		
		// Supprime l'entree
		Ligne ligne = lignePersistence.remove(ligneId);
		
		// Supprime les Direction associées
		// Le GTFS ne devrait plus posseder d'indentifiant relatif a cette ligne 
		// mais c'est une securite
		DirectionLocalServiceUtil.removeByRouteId(ligne.getRouteId());

		// Supprime l'index
		this.reindex(ligne, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
				ligne.getCompanyId(), ligne.getGroupId(), Ligne.class.getName(),
				ligne.getLigneId());

		return ligne;
	}
	
	/**
	 * Reindex l'entree dans le moteur de recherche
	 */
	private void reindex(Ligne ligne, boolean delete) throws SearchException {
		Indexer<Ligne> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Ligne.class);
		if (delete) {
			indexer.delete(ligne);
		} else {
			indexer.reindex(ligne);
		}
	}
	
	/**
	 * Renvoie la liste des vocabulaires rattachés à l'entree
	 */
	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(Ligne.class);
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
	public List<Ligne> getByGroupId(long groupId) {
		return this.lignePersistence.findByGroupId(groupId);
	}
	
	/**
	 * Retourne une ligne via son routeId CTS
	 */
	@Override
	public Ligne getByRouteId(String routeId) {
		return this.lignePersistence.fetchByRouteId(routeId);
	}
	
	/**
	 * Retourne la liste de toutes les lignes
	 */
	@Override
	public Map<String, Ligne> getAll() {
		Map<String, Ligne> lignes = new HashMap<>();
		for (Ligne ligne : this.lignePersistence.findAll()) {
			lignes.put(ligne.getRouteId(), ligne);
		}
		return lignes;
	}
	
	/**
	 * Recherche par mot clés
	 */
	@Override
	public List<Ligne> findByKeyword(String keyword, long groupId, int start, int end) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (keyword.length() > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return this.lignePersistence.findWithDynamicQuery(dynamicQuery, start, end);
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
		return this.lignePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Recuperer toutes les couleurs de ligne au format JSON
	 */
	@Override
	public JSONArray getLigneColors() {
		JSONArray ligneColors = JSONFactoryUtil.createJSONArray();
		for (Ligne ligne : this.lignePersistence.findAll()) {
			ligneColors.put(ligne.getColors());
		}
		return ligneColors;
	}

	/**
	 * Recuperer toutes les couleurs de ligne au format HashMap
	 */
	@Override
	public Map<String, String[]> getLigneColorsFreemarker() {
		Map<String, String[]> ligneColors = new HashMap<String, String[]>();
		for (Ligne ligne : this.lignePersistence.findAll()) {
			String[] colors = {ligne.getColors().getString("backgroundColor"), ligne.getColors().getString("textColor")};
			ligneColors.put(ligne.getColors().getString("shortName"), colors);
		}
		return ligneColors;
	}
	
}