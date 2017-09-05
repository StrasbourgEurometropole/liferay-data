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

package eu.strasbourg.service.interest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
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
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.service.interest.model.UserInterest;
import eu.strasbourg.service.interest.service.base.InterestLocalServiceBaseImpl;
import eu.strasbourg.service.interest.service.persistence.UserInterestPK;

/**
 * The implementation of the interest local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.interest.service.InterestLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see InterestLocalServiceBaseImpl
 * @see eu.strasbourg.service.interest.service.InterestLocalServiceUtil
 */
@ProviderType
public class InterestLocalServiceImpl extends InterestLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.interest.service.InterestLocalServiceUtil} to
	 * access the interest local service.
	 */

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Interest createInterest(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Interest interest = this.interestLocalService.createInterest(pk);

		interest.setGroupId(sc.getScopeGroupId());
		interest.setCompanyId(sc.getCompanyId());
		interest.setUserName(user.getFullName());
		interest.setUserId(sc.getUserId());

		interest.setStatus(WorkflowConstants.STATUS_DRAFT);

		return interest;
	}

	/**
	 * Met à jour une entité et l'enregistre en base de données
	 */
	@Override
	public Interest updateInterest(Interest interest, ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		interest.setCompanyId(sc.getCompanyId());
		interest.setStatusByUserId(sc.getUserId());
		interest.setStatusByUserName(user.getFullName());
		interest.setStatusDate(sc.getModifiedDate());

		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			interest.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			interest.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		interest = this.interestLocalService.updateInterest(interest);
		this.updateAssetEntry(interest, sc);
		this.reindex(interest, false);

		return interest;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à l'édition
	 */
	private void updateAssetEntry(Interest interest, ServiceContext sc) throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				interest.getCreateDate(), // Date of creation
				interest.getModifiedDate(), // Date of modification
				Interest.class.getName(), // Class name
				interest.getPrimaryKey(), // Class PK
				interest.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				interest.isApproved(), // Visible
				interest.getCreateDate(), // Start date
				null, // End date
				interest.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				interest.getTitle(), // Title
				interest.getDescription(), // Description
				interest.getDescription(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe l'édition
		this.reindex(interest, false);
	}

	/**
	 * Met à jour le statut de l'entité
	 */
	@Override
	public Interest updateStatus(long userId, long entryId, int status) throws PortalException {
		Interest interest = this.getInterest(entryId);

		interest.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			interest.setStatusByUserId(user.getUserId());
			interest.setStatusByUserName(user.getFullName());
		}
		interest.setStatusDate(new Date());

		interest = this.interestLocalService.updateInterest(interest);

		AssetEntry entry = interest.getAssetEntry();
		entry.setVisible(interest.isApproved());
		if (entry.isVisible()) {
			entry.setPublishDate(new Date());
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(interest, false);

		return interest;
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public Interest removeInterest(long interestId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil.fetchEntry(Interest.class.getName(), interestId);

		if (entry != null) {
			// Supprime le lien avec les utilisateurs
			List<UserInterest> userInterests = this.userInterestLocalService.getByInterestId(interestId);
			for (UserInterest userInterest : userInterests) {
				this.userInterestLocalService.deleteUserInterest(userInterest);
			}

			// Supprime le lien avec les catégories
			for (long categoryId : entry.getCategoryIds()) {
				this.assetEntryLocalService.deleteAssetCategoryAssetEntry(categoryId, entry.getEntryId());
			}

			// Supprime le lien avec les tags
			long[] tagIds = AssetEntryLocalServiceUtil.getAssetTagPrimaryKeys(entry.getEntryId());
			for (int i = 0; i < tagIds.length; i++) {
				AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagIds[i], entry.getEntryId());
			}

			// Supprime l'assetEntry
			AssetEntryLocalServiceUtil.deleteEntry(Interest.class.getName(), interestId);
		}

		// Supprimé l'entité
		Interest interest = this.interestPersistence.remove(interestId);

		// Supprime l'index
		this.reindex(interest, true);

		return interest;
	}

	/**
	 * Reindex l'édition dans le moteur de recherche
	 */
	private void reindex(Interest interest, boolean delete) throws SearchException {
		Indexer<Interest> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Interest.class);
		if (delete) {
			indexer.delete(interest);
		} else {
			indexer.reindex(interest);
		}
	}

	/**
	 * Renvoie la liste des vocabulaires rattachés à l'entité Interest
	 */
	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(Interest.class);
		for (AssetVocabulary vocabulary : vocabularies) {
			if (vocabulary.getGroupId() == groupId
					&& LongStream.of(vocabulary.getSelectedClassNameIds()).anyMatch(c -> c == classNameId)) {
				attachedVocabularies.add(vocabulary);
			}
		}
		return attachedVocabularies;
	}

	/**
	 * Retourne toutes les entité d'un groupe
	 */
	@Override
	public List<Interest> getByGroupId(long groupId) {
		return this.interestPersistence.findByGroupId(groupId);
	}

	/**
	 * Retourne tous les centres d'intérêts (publiées) d'un utilisateur
	 */
	@Override
	public List<Interest> getByPublikUserId(long publikUserId) {
		List<UserInterest> userInterests = this.userInterestLocalService.getByPublikUserId(publikUserId);
		return userInterests.stream().map(UserInterest::getInterest).filter(i -> i.isApproved())
				.collect(Collectors.toList());
	}

	/**
	 * Met à jour la relation entre un utilisateur et ses centres d'intérêts
	 */
	@Override
	public void setUserInterests(long publikUserId, long[] interestIds) {
		// On supprime d'abord les existants
		List<UserInterest> userInterests = this.userInterestLocalService.getByPublikUserId(publikUserId);
		for (UserInterest userInterest : userInterests) {
			this.userInterestLocalService.deleteUserInterest(userInterest);
		}
		// Et on ajoute les nouveaux s'ils existent et sont publiés
		for (long interestId : interestIds) {
			Interest interest = this.fetchInterest(interestId);
			if (interest != null && interest.isApproved()) {
				UserInterest userInterest = this.userInterestLocalService
						.createUserInterest(new UserInterestPK(interestId, publikUserId));
				this.userInterestLocalService.updateUserInterest(userInterest);
			}
		}
	}

	/**
	 * Lance une recherche selon le searchContext
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<Interest> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Interest.class);
		return indexer.search(searchContext);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	@Override
	public List<Interest> findByKeyword(String keyword, long groupId, int start, int end) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (keyword.length() > 0) {
			dynamicQuery.add(RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}
		dynamicQuery.add(PropertyFactoryUtil.forName("status").eq(WorkflowConstants.STATUS_APPROVED));

		return interestPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Compte de la recherche par mots-clés
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
		dynamicQuery.add(PropertyFactoryUtil.forName("status").eq(WorkflowConstants.STATUS_APPROVED));

		return interestPersistence.countWithDynamicQuery(dynamicQuery);
	}

}