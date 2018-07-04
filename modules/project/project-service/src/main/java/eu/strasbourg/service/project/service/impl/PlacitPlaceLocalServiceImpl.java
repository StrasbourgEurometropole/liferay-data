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

package eu.strasbourg.service.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;

import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.base.PlacitPlaceLocalServiceBaseImpl;

/**
 * The implementation of the placit place local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.service.PlacitPlaceLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see PlacitPlaceLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.PlacitPlaceLocalServiceUtil
 */
public class PlacitPlaceLocalServiceImpl extends PlacitPlaceLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.project.service.PlacitPlaceLocalServiceUtil} to access the placit place local service.
	 */
	
	/**
	 * Retourne les lieux d'un projet
	 */
	@Override
	public List<PlacitPlace> getByProject(long projectId) {
		return this.placitPlacePersistence.findByProject(projectId);
	}
	
	/**
	 * Retourne les lieux d'une participation
	 */
	@Override
	public List<PlacitPlace> getByParticipation(long participationId) {
		return this.placitPlacePersistence.findByProject(participationId);
	}
	
	/**
	 * Crée un lieu Placit vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public PlacitPlace createPlacitPlace(ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		PlacitPlace placitPlace = this.placitPlaceLocalService
			.createPlacitPlace(pk);

		placitPlace.setGroupId(sc.getScopeGroupId());
		placitPlace.setUserName(user.getFullName());
		placitPlace.setUserId(sc.getUserId());

		return placitPlace;
	}

	/**
	 * Met à jour un lieu Placit et l'enregistre en base de données
	 */
	@Override
	public PlacitPlace updatePlacitPlace(PlacitPlace placitPlace, ServiceContext sc)
			throws PortalException {
		placitPlace = this.placitPlaceLocalService.updatePlacitPlace(placitPlace);
		this.updateAssetEntry(placitPlace, sc);
		this.reindex(placitPlace, false);

		return placitPlace;
	}
	
	/**
	 * Met à jour l'AssetEntry rattachée à l'entité
	 */
	private void updateAssetEntry(PlacitPlace placitPlace, ServiceContext sc) 
			throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			placitPlace.getCreateDate(), // Date of creation
			placitPlace.getModifiedDate(), // Date of modification
			PlacitPlace.class.getName(), // Class name
			placitPlace.getPrimaryKey(), // Class PK
			placitPlace.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			true, // Visible
			placitPlace.getCreateDate(), // Start date
			null, // End date
			placitPlace.getCreateDate(), // Publication date
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			placitPlace.getPlaceName(), // Title
			"", // Description
			"", // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority

		// Réindexe l'édition
		this.reindex(placitPlace, false);
	}
	
	/**
	 * Supprime une entité
	 */
	@Override
	public PlacitPlace removePlacitPlace(long placitPlaceId) 
			throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(
				PlacitPlace.class.getName(), placitPlaceId);

		if (entry != null) {
			// Supprime le lien avec les catégories
			for (long categoryId : entry.getCategoryIds()) {
				this.assetEntryLocalService.deleteAssetCategoryAssetEntry(
					categoryId, entry.getEntryId());
			}

			// Supprime le lien avec les tags
			long[] tagIds = AssetEntryLocalServiceUtil
				.getAssetTagPrimaryKeys(entry.getEntryId());
			for (int i = 0; i < tagIds.length; i++) {
				AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagIds[i], entry.getEntryId());
			}

			// Supprime l'assetEntry
			AssetEntryLocalServiceUtil.deleteEntry(PlacitPlace.class.getName(), placitPlaceId);
		}

		// Supprime l'entité
		PlacitPlace placitPlace = this.placitPlacePersistence.remove(placitPlaceId);

		// Supprime l'index
		this.reindex(placitPlace, true);

		return placitPlace;
	}
	
	/**
	 * Reindex l'entité dans le moteur de recherche
	 */
	private void reindex(PlacitPlace placitPlace, boolean delete) 
			throws SearchException {
		Indexer<PlacitPlace> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(PlacitPlace.class);
		if (delete) {
			indexer.delete(placitPlace);
		} else {
			indexer.reindex(placitPlace);
		}
	}
	
	/**
	 * Renvoie la liste des vocabulaires rattachés à l'entité
	 */
	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil
			.getClassNameId(PlacitPlace.class);
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
	 * Retourne tous les lieux Placit d'un groupe
	 */
	@Override
	public List<PlacitPlace> getByGroupId(long groupId) {
		return this.placitPlacePersistence.findByGroupId(groupId);
	}

	/**
	 * Retourne toutes les lieux de cours d'un lieu
	 */
	@Override
	public List<PlacitPlace> getBySigId(String sigId) {
		return this.placitPlacePersistence.findBySigId(sigId);
	}

	/**
	 * Lance une recherche selon le searchContext
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<PlacitPlace> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(PlacitPlace.class);
		return indexer.search(searchContext);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	@Override
	public List<PlacitPlace> findByKeyword(String keyword, long groupId, int start, int end) {
		DynamicQuery dynamicQuery = dynamicQuery();
		
		if (keyword.length() > 0) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery
				.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return placitPlacePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Lance une recherche par liste d'ids
	 */
	@Override
	public List<PlacitPlace> findByIds(List<Long> placitPlaceIds) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (placitPlaceIds.size() > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.in("placitPlaceId",
					placitPlaceIds));
		} else {
			return new ArrayList<PlacitPlace>();
		}

		return placitPlacePersistence.findWithDynamicQuery(dynamicQuery,
			-1, -1);
	}
	
}