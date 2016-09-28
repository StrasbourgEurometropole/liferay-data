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

package eu.strasbourg.service.edition.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
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
import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.base.EditionGalleryLocalServiceBaseImpl;

/**
 * The implementation of the edition gallery local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.edition.service.EditionGalleryLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see EditionGalleryLocalServiceBaseImpl
 * @see eu.strasbourg.service.edition.service.EditionGalleryLocalServiceUtil
 */
@ProviderType
public class EditionGalleryLocalServiceImpl
	extends EditionGalleryLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.edition.service.EditionGalleryLocalServiceUtil} to
	 * access the edition gallery local service.
	 */

	/**
	 * Add an empty Edition Gallery
	 * 
	 * @return The added Edition Gallery
	 * @throws PortalException
	 */
	public EditionGallery addEditionGallery() throws PortalException {
		long pk = counterLocalService.increment();

		EditionGallery editionGallery = this.editionGalleryLocalService
			.createEditionGallery(pk);
		return editionGallery;
	}

	/**
	 * Update an Edition Gallery
	 * 
	 * @param editionGallery
	 *            The updated Edition Gallery
	 * @param sc
	 *            Service Context
	 * @return The updated Edition
	 * @throws PortalException
	 */
	public EditionGallery updateEditionGallery(EditionGallery editionGallery,
		ServiceContext sc) throws PortalException {

		editionGallery.setGroupId(sc.getScopeGroupId());
		editionGallery = this.editionGalleryLocalService
			.updateEditionGallery(editionGallery);
		updateAssetEntry(editionGallery, sc);

		return editionGallery;
	}

	private void updateAssetEntry(EditionGallery editionGallery,
		ServiceContext sc) throws PortalException {

		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			editionGallery.getCreateDate(), // Date of creation
			editionGallery.getModifiedDate(), // Date of modification
			EditionGallery.class.getName(), // Class name
			editionGallery.getPrimaryKey(), // Class PK
			editionGallery.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			editionGallery.getStatus(), // Visible
			editionGallery.getPublicationDate(), // Start date
			null, // End date
			editionGallery.getPublicationDate(), // Publication date
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			editionGallery.getTitle(), // Title
			editionGallery.getDescription(), // Description
			editionGallery.getDescription(), // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority

		Indexer<EditionGallery> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(EditionGallery.class);
		indexer.reindex(editionGallery);
	}

	public void changeStatus(EditionGallery editionGallery, boolean status)
		throws PortalException {
		editionGallery.setStatus(status);
		this.editionGalleryLocalService.updateEditionGallery(editionGallery);

		AssetEntry entry = this.assetEntryLocalService.getEntry(
			EditionGallery.class.getName(), editionGallery.getPrimaryKey());
		entry.setVisible(status);
		this.assetEntryLocalService.updateAssetEntry(entry);

		Indexer<EditionGallery> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(EditionGallery.class);
		indexer.reindex(editionGallery);
	}

	/**
	 * Delete an Edition Gallery
	 * 
	 * @param galleryId
	 *            The ID of the edition gallery to delete
	 * @return The deleted EditionGallery
	 * @throws PortalException
	 */
	public EditionGallery removeGallery(long galleryId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
			.getEntry(EditionGallery.class.getName(), galleryId);
		// Delete the link with categories
		long[] categoryIds = entry.getCategoryIds();
		for (int i = 0; i < categoryIds.length; i++) {
			AssetEntryLocalServiceUtil.deleteAssetCategoryAssetEntry(
				categoryIds[i], entry.getEntryId());
		}

		// Delete the link with tags
		long[] tagIds = AssetEntryLocalServiceUtil
			.getAssetTagPrimaryKeys(entry.getEntryId());
		for (int i = 0; i < tagIds.length; i++) {
			AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagIds[i],
				entry.getEntryId());
		}

		// Delete the link with other entries
		List<AssetLink> links = this.assetLinkLocalService
			.getLinks(entry.getEntryId());
		for (AssetLink link : links) {
			AssetLinkLocalServiceUtil.deleteAssetLink(link);
		}
		// Delete the AssetEntry
		AssetEntryLocalServiceUtil.deleteEntry(EditionGallery.class.getName(),
			galleryId);

		// Delete the Edition Gallery
		EditionGallery editionGallery = editionGalleryPersistence
			.remove(galleryId);

		// Delete the index
		Indexer<EditionGallery> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(EditionGallery.class);
		indexer.delete(editionGallery);

		return editionGallery;
	}

	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil
			.getClassNameId(EditionGallery.class);
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
	 * Retourne toutes les galeries éditions d'un groupe
	 */
	public List<EditionGallery> getByGroupId(long groupId) {
		return this.editionGalleryPersistence.findByGroupId(groupId);
	}

	public List<EditionGallery> findByKeyword(String keyword, long groupId,
		int start, int end) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (keyword.length() > 0) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery
				.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return editionPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	public long findByKeywordCount(String keyword, long groupId) {
		DynamicQuery dynamicQuery = dynamicQuery();
		if (keyword.length() > 0) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery
				.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return editionPersistence.countWithDynamicQuery(dynamicQuery);
	}

	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<EditionGallery> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(EditionGallery.class);
		return indexer.search(searchContext);
	}
}