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
import eu.strasbourg.service.edition.service.base.EditionLocalServiceBaseImpl;

/**
 * The implementation of the edition local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.edition.service.EditionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see EditionLocalServiceBaseImpl
 * @see eu.strasbourg.service.edition.service.EditionLocalServiceUtil
 */
@ProviderType
public class EditionLocalServiceImpl extends EditionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.edition.service.EditionLocalServiceUtil} to access
	 * the edition local service.
	 */

	/**
	 * Add an empty Edition
	 * 
	 * @return The added Edition
	 * @throws PortalException
	 */
	public Edition addEdition() throws PortalException {
		long pk = counterLocalService.increment();

		Edition edition = this.editionLocalService.createEdition(pk);
		return edition;
	}

	/**
	 * Update an Edition
	 * 
	 * @param edition
	 *            The updated Edition
	 * @param sc
	 *            Service Context
	 * @return The updated Edition
	 * @throws PortalException
	 */
	public Edition updateEdition(Edition edition, ServiceContext sc)
		throws PortalException {

		edition.setGroupId(sc.getScopeGroupId());
		edition = this.editionLocalService.updateEdition(edition);
		updateAssetEntry(edition, sc);

		return edition;
	}

	private void updateAssetEntry(Edition edition, ServiceContext sc)
		throws PortalException {

		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			edition.getCreateDate(), // Date of creation
			edition.getModifiedDate(), // Date of modification
			Edition.class.getName(), // Class name
			edition.getPrimaryKey(), // Class PK
			edition.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			edition.getStatus(), // Visible
			edition.getPublicationDate(), // Start date
			null, // End date
			edition.getPublicationDate(), // Publication date
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			edition.getTitle(), // Title
			edition.getDescription(), // Description
			edition.getDescription(), // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority

		Indexer<Edition> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Edition.class);
		indexer.reindex(edition);

	}

	public void changeStatus(Edition edition, boolean publicationStatus)
		throws PortalException {
		edition.setStatus(publicationStatus);
		this.editionLocalService.updateEdition(edition);

		AssetEntry entry = this.assetEntryLocalService
			.getEntry(Edition.class.getName(), edition.getPrimaryKey());
		entry.setVisible(publicationStatus);
		this.assetEntryLocalService.updateAssetEntry(entry);

		Indexer<Edition> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Edition.class);
		indexer.reindex(edition);
	}

	/**
	 * Delete an Edition
	 * 
	 * @param editionId
	 *            The ID of the edition to delete
	 * @return The deleted Edition
	 * @throws PortalException
	 */
	public Edition removeEdition(long editionId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
			.getEntry(Edition.class.getName(), editionId);

		// Delete the link with categories
		for (long categoryId : entry.getCategoryIds()) {
			this.assetEntryLocalService
				.deleteAssetCategoryAssetEntry(categoryId, entry.getEntryId());
		}

		// Delete the link with tags
		long[] tagIds = AssetEntryLocalServiceUtil
			.getAssetTagPrimaryKeys(entry.getEntryId());
		for (int i = 0; i < tagIds.length; i++) {
			AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagIds[i],
				entry.getEntryId());
		}

		// Supprime lien avec les autres entries
		List<AssetLink> links = this.assetLinkLocalService.getLinks(entry.getEntryId());
		for (AssetLink link : links) {
			this.assetLinkLocalService.deleteAssetLink(link);
		}

		// Delete the AssetEntry
		AssetEntryLocalServiceUtil.deleteEntry(Edition.class.getName(),
			editionId);

		// Delete the Edition
		Edition edition = editionPersistence.remove(editionId);

		// Delete the index
		Indexer<Edition> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Edition.class);
		indexer.delete(edition);

		return edition;
	}

	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil
			.getClassNameId(Edition.class);
		for (AssetVocabulary vocabulary : vocabularies) {
			if (vocabulary.getGroupId() == groupId
				&& LongStream.of(vocabulary.getSelectedClassNameIds())
					.anyMatch(c -> c == classNameId)) {
				attachedVocabularies.add(vocabulary);
			}
		}
		return attachedVocabularies;
	}

	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<Edition> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Edition.class);
		return indexer.search(searchContext);
	}
	
	public List<Edition> findByKeyword(String keyword, long groupId, int start, int end) {
		DynamicQuery dynamicQuery = dynamicQuery();
		
		if (keyword.length() > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}
		
		return editionPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}
	
	public long findByKeywordCount(String keyword, long groupId) {
		DynamicQuery dynamicQuery = dynamicQuery();
		if (keyword.length() > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return editionPersistence.countWithDynamicQuery(dynamicQuery);
	}

}