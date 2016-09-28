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

package eu.strasbourg.service.artwork.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetLinkLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.service.base.ArtworkCollectionLocalServiceBaseImpl;

/**
 * The implementation of the artworkCollection local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.artworkCollection.service.ArtworkCollectionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see ArtworkCollectionLocalServiceBaseImpl
 * @see eu.strasbourg.service.artworkCollection.service.ArtworkCollectionLocalServiceUtil
 */
@ProviderType
public class ArtworkCollectionLocalServiceImpl extends ArtworkCollectionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.artworkCollection.service.ArtworkCollectionLocalServiceUtil} to access
	 * the artworkCollection local service.
	 */

	/**
	 * Create an empty artworkCollection
	 */
	public ArtworkCollection addArtworkCollection() throws PortalException {
		long pk = counterLocalService.increment();

		ArtworkCollection artworkCollection = this.artworkCollectionLocalService.createArtworkCollection(pk);

		return artworkCollection;
	}

	/**
	 * Update an artowrk
	 * 
	 * @throws PortalException
	 */
	public ArtworkCollection updateArtworkCollection(ArtworkCollection artworkCollection, ServiceContext sc)
		throws PortalException {
		artworkCollection.setGroupId(sc.getScopeGroupId());
		artworkCollection = this.artworkCollectionLocalService.updateArtworkCollection(artworkCollection);
		this.updateAssetEntry(artworkCollection, sc);
		this.reindex(artworkCollection);
		return artworkCollection;
	}

	/**
	 * Update the AssetEntry linked to the artworkCollection
	 */
	private void updateAssetEntry(ArtworkCollection artworkCollection, ServiceContext sc)
		throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			artworkCollection.getCreateDate(), // Date of creation
			artworkCollection.getModifiedDate(), // Date of modification
			ArtworkCollection.class.getName(), // Class name
			artworkCollection.getPrimaryKey(), // Class PK
			artworkCollection.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			artworkCollection.getStatus(), // Visible
			artworkCollection.getCreateDate(), // Start date
			null, // End date
			artworkCollection.getCreateDate(), // Date of publication
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			artworkCollection.getTitle(), // Title
			artworkCollection.getDescription(), // Description
			artworkCollection.getDescription(), // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority
	}

	/**
	 * Change the publication status of the artworkCollection
	 */
	public void changeStatus(ArtworkCollection artworkCollection, boolean publicationStatus)
		throws PortalException {
		// Change the status of the item
		artworkCollection.setStatus(publicationStatus);
		this.artworkCollectionLocalService.updateArtworkCollection(artworkCollection);

		// Change the status of the AssetEntry
		AssetEntry entry = artworkCollection.getAssetEntry();
		entry.setVisible(publicationStatus);
		this.assetEntryLocalService.updateAssetEntry(entry);

		// Reindex it
		this.reindex(artworkCollection);
	}

	/**
	 * Delete an artworkCollection
	 */
	public ArtworkCollection removeArtworkCollection(long artworkCollectionId) throws PortalException {
		AssetEntry entry = this.assetEntryLocalService
			.getEntry(ArtworkCollection.class.getName(), artworkCollectionId);

		// Delete the link with categories
		for (long categoryId : entry.getCategoryIds()) {
			this.assetEntryLocalService
				.deleteAssetCategoryAssetEntry(categoryId, entry.getEntryId());
		}

		// Delete the link with tags
		long[] tagsIds = this.assetEntryLocalService.getAssetTagPrimaryKeys(entry.getEntryId());
		for (long tagId :tagsIds) {
			this.assetEntryLocalService.deleteAssetTagAssetEntry(tagId, entry.getEntryId());
		}

		// Delete the link with other entries
		List<AssetLink> links = AssetLinkLocalServiceUtil.getLinks(entry.getEntryId());
		for (AssetLink link : links) {
			AssetLinkLocalServiceUtil.deleteAssetLink(link);
		}
		
		// Delete the entry
		this.assetEntryLocalService.deleteEntry(entry);

		// Delete the artworkCollection
		ArtworkCollection artworkCollection = this.artworkCollectionPersistence.remove(artworkCollectionId);

		// Delete the index
		reindex(artworkCollection);
		
		return artworkCollection;
	}

	/**
	 * Reindex the artworkCollection in the search engine
	 */
	private void reindex(ArtworkCollection artworkCollection) throws SearchException {
		Indexer<ArtworkCollection> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(ArtworkCollection.class);
		indexer.reindex(artworkCollection);
	}
	
	/**
	 * Return the vocabularies attached to the ArtworkCollection entity
	 */
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil
			.getClassNameId(ArtworkCollection.class);
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
	 * Retourne toutes les collections d'oeuvres d'un groupe
	 */
	public List<ArtworkCollection> getByGroupId(long groupId) {
		return this.artworkCollectionPersistence.findByGroupId(groupId);
	}

	public List<ArtworkCollection> findByKeyword(String keyword, long groupId, int start, int end) {
		DynamicQuery dynamicQuery = dynamicQuery();
		
		if (keyword.length() > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}
		
		return artworkCollectionPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}
	
	public long findByKeywordCount(String keyword, long groupId) {
		DynamicQuery dynamicQuery = dynamicQuery();
		if (keyword.length() > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return artworkCollectionPersistence.countWithDynamicQuery(dynamicQuery);
	}
	
	/**
	 * Search
	 */
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<ArtworkCollection> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(ArtworkCollection.class);
		return indexer.search(searchContext);
	}
}