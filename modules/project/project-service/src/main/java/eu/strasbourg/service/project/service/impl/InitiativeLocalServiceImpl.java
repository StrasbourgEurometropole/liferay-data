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

import java.util.List;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;

import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.Initiative;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.base.InitiativeLocalServiceBaseImpl;

/**
 * The implementation of the initiative local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.service.InitiativeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see InitiativeLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.InitiativeLocalServiceUtil
 */
public class InitiativeLocalServiceImpl extends InitiativeLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.project.service.InitiativeLocalServiceUtil} to access the initiative local service.
	 */
	
	/**
	 * Retourne toutes les initiatives d'un groupe
	 */
	@Override
	public List<Initiative> getByGroupId(long groupId) {
		return this.initiativePersistence.findByGroupId(groupId);
	}
	
	/**
	 * Supprime une initiative
	 */
	@Override
	public Initiative removeInitiative(long initiativeId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
				.fetchEntry(Initiative.class.getName(), initiativeId);

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
			AssetEntryLocalServiceUtil.deleteEntry(Initiative.class.getName(),
					initiativeId);
			
			// Supprime les lieux
			List<PlacitPlace> placitPlaces = this.placitPlaceLocalService
				.getByInitiative(initiativeId);
			for (PlacitPlace placitPlace : placitPlaces) {
				this.placitPlaceLocalService.removePlacitPlace(
						placitPlace.getPlacitPlaceId());
			}

		}

		// Supprime la initiative
		Initiative initiative = initiativePersistence.remove(initiativeId);

		// Delete the index
		this.reindex(initiative, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
				initiative.getCompanyId(), initiative.getGroupId(),
				Initiative.class.getName(), initiative.getInitiativeId());

		return initiative;
	}
	
	/**
	 * Reindex la initiative dans le moteur de recherche
	 */
	private void reindex(Initiative initiative, boolean delete) throws SearchException {
		Indexer<Initiative> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(Initiative.class);
		if (delete) {
			indexer.delete(initiative);
		} else {
			indexer.reindex(initiative);
		}
	}
}