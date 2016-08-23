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
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
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
import eu.strasbourg.service.artwork.service.base.ArtworkLocalServiceBaseImpl;

/**
 * The implementation of the artwork local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.artwork.service.ArtworkLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see ArtworkLocalServiceBaseImpl
 * @see eu.strasbourg.service.artwork.service.ArtworkLocalServiceUtil
 */
@ProviderType
public class ArtworkLocalServiceImpl extends ArtworkLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.artwork.service.ArtworkLocalServiceUtil} to access
	 * the artwork local service.
	 */

	/**
	 * Create an empty artwork
	 */
	public Artwork addArtwork() throws PortalException {
		long pk = counterLocalService.increment();

		Artwork artwork = this.artworkLocalService.createArtwork(pk);

		return artwork;
	}

	/**
	 * Update an artwork
	 */
	public Artwork updateArtwork(Artwork artwork, ServiceContext sc)
		throws PortalException {
		artwork.setGroupId(sc.getScopeGroupId());
		artwork = this.artworkLocalService.updateArtwork(artwork);
		this.updateAssetEntry(artwork, sc);
		this.reindex(artwork);
		return artwork;
	}

	/**
	 * Update the AssetEntry linked to the artwork
	 */
	private void updateAssetEntry(Artwork artwork, ServiceContext sc)
		throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			artwork.getCreateDate(), // Date of creation
			artwork.getModifiedDate(), // Date of modification
			Artwork.class.getName(), // Class name
			artwork.getPrimaryKey(), // Class PK
			artwork.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			artwork.getStatus(), // Visible
			artwork.getCreateDate(), // Start date
			null, // End date
			artwork.getCreateDate(), // Date of publication
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			artwork.getTitle(), // Title
			artwork.getDescription(), // Description
			artwork.getDescription(), // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority
	}

	/**
	 * Change the publication status of the artwork
	 */
	public void changeStatus(Artwork artwork, boolean publicationStatus)
		throws PortalException {
		// Change the status of the item
		artwork.setStatus(publicationStatus);
		this.artworkLocalService.updateArtwork(artwork);

		// Change the status of the AssetEntry
		AssetEntry entry = artwork.getAssetEntry();
		entry.setVisible(publicationStatus);
		this.assetEntryLocalService.updateAssetEntry(entry);

		// Reindex it
		this.reindex(artwork);
	}

	/**
	 * Delete an artwork
	 */
	public Artwork removeArtwork(long artworkId) throws PortalException {
		AssetEntry entry = this.assetEntryLocalService
			.getEntry(Artwork.class.getName(), artworkId);

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
		List<AssetLink> links = this.assetLinkLocalService.getLinks(entry.getEntryId());
		for (AssetLink link : links) {
			this.assetLinkLocalService.deleteAssetLink(link);
		}
		
		// Delete the entry
		this.assetEntryLocalService.deleteEntry(entry);

		// Delete the artwork
		Artwork artwork = this.artworkPersistence.remove(artworkId);

		// Delete the index
		reindex(artwork);
		
		return artwork;
	}

	/**
	 * Reindex the artwork in the search engine
	 */
	private void reindex(Artwork artwork) throws SearchException {
		Indexer<Artwork> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Artwork.class);
		indexer.reindex(artwork);
	}
	
	/**
	 * Return the vocabularies attached to the Artwork entity
	 */
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil
			.getClassNameId(Artwork.class);
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
	 * Search
	 */
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<Artwork> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Artwork.class);
		return indexer.search(searchContext);
	}
}