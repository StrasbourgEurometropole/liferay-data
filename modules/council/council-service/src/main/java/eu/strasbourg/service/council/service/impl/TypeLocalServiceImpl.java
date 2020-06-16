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

package eu.strasbourg.service.council.service.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.council.model.CouncilSession;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.OfficialTypeCouncil;
import eu.strasbourg.service.council.model.Type;
import eu.strasbourg.service.council.service.base.TypeLocalServiceBaseImpl;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The implementation of the type local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.council.service.TypeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TypeLocalServiceBaseImpl
 * @see eu.strasbourg.service.council.service.TypeLocalServiceUtil
 */
public class TypeLocalServiceImpl extends TypeLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.council.service.TypeLocalServiceUtil} to access the type local service.
	 */

	public final static Log log = LogFactoryUtil.getLog(TypeLocalServiceImpl.class);

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Type createType(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = this.counterLocalService.increment();
		Type type = this.typeLocalService.createType(pk);

		type.setUserName(user.getFullName());
		type.setUserId(sc.getUserId());
		type.setGroupId(sc.getScopeGroupId());
		type.setStatus(WorkflowConstants.STATUS_DRAFT);

		return type;
	}

	/**
	 * Met à jour une entité et l'enregistre en base de données
	 */
	@Override
	public Type updateType(Type type, ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		type.setStatusByUserId(sc.getUserId());
		type.setStatusByUserName(user.getFullName());
		type.setStatusDate(sc.getModifiedDate());

		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			type.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			type.setStatus(WorkflowConstants.STATUS_DRAFT);
		}

		// Ajout ou renommmage de la catégorie associée
		Type oldType = this.typeLocalService.fetchType(
				type.getTypeId());
		// Si il existe déjà, c'est une mise à jour sinon on le créée
		if (oldType != null) {
			// On renomme la catégorie si le titre a été modifié
			if (!oldType.getTitle().equals(type.getTitle())) {
				AssetVocabulary conseil = AssetVocabularyHelper.getVocabulary(VocabularyNames.COUNCIL_SESSION
						, sc.getScopeGroupId());
				AssetCategory typeCategory = conseil.getCategories().stream()
						.filter(c -> c.getName().equals(oldType.getTitle())).findFirst().get();
				AssetVocabularyHelper.renameCategory(typeCategory, type.getTitle());
			}
		} else {
			AssetVocabularyHelper.addCategoryToVocabulary(type.getTitle(),
					VocabularyNames.COUNCIL_SESSION, sc);
		}

		type = this.typeLocalService.updateType(type);

		this.updateAssetEntry(type, sc);
		this.reindex(type, false);

		return type;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à l'entité
	 */
	private void updateAssetEntry(Type type, ServiceContext sc)
			throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				type.getCreateDate(), // Date of creation
				type.getModifiedDate(), // Date of modification
				Type.class.getName(), // Class name
				type.getPrimaryKey(), // Class PK
				type.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				type.isApproved(), // Visible
				type.getCreateDate(), // Start date
				null, // End date
				type.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				type.getTitle(), // Title
				type.getTitle(), // Description
				type.getTitle(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe l'entité
		this.reindex(type, false);
	}

	/**
	 * Met à jour le statut de l'entité par le framework workflow
	 */
	@Override
	public Type updateStatus(long userId, long entryId, int status,
									   ServiceContext sc, Map<String, Serializable> workflowContext)
			throws PortalException {
		Date now = new Date();
		// Statut de l'entité
		Type type = this.getType(entryId);
		type.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			type.setStatusByUserId(user.getUserId());
			type.setStatusByUserName(user.getFullName());
		}
		type.setStatusDate(new Date());
		type = this.typeLocalService.updateType(type);

		// Statut de l'entry
		AssetEntry entry = this.assetEntryLocalService
				.getEntry(Type.class.getName(), type.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(type, false);

		return type;
	}

	/**
	 * Met à jour le statut de l'entité "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(Type type, int status) throws PortalException {
		this.updateStatus(type.getUserId(), type.getTypeId(), status, null,null);
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public Type removeType(long typeId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
				.fetchEntry(Type.class.getName(), typeId);

		if (entry != null) {
			// Supprime les liens avec les catégories
			for (long categoryId : entry.getCategoryIds()) {
				this.assetEntryLocalService.deleteAssetCategoryAssetEntry(
						categoryId, entry.getEntryId());
			}

			// Supprime les liens avec les étiquettes
			long[] tagIds = AssetEntryLocalServiceUtil
					.getAssetTagPrimaryKeys(entry.getEntryId());
			for (long tagId : tagIds) {
				AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagId,
						entry.getEntryId());
			}

			// Supprime lien avec les autres entries
			List<AssetLink> links = this.assetLinkLocalService
					.getLinks(entry.getEntryId());
			for (AssetLink link : links) {
				this.assetLinkLocalService.deleteAssetLink(link);
			}

			// Supprime l'AssetEntry
			AssetEntryLocalServiceUtil.deleteEntry(Type.class.getName(), typeId);
		}

		// Supprime le type de conseil des votants
		List<OfficialTypeCouncil> typeVotants = this.officialTypeCouncilLocalService.findByTypeId(typeId);
		for (OfficialTypeCouncil typeVotant : typeVotants) {
			this.officialTypeCouncilLocalService.removeOfficialTypeCouncil(typeVotant.getOfficialId(), typeVotant.getTypeId());
		}

		// Suppression de la catégorie associée
		Type type = this.typeLocalService.fetchType(typeId);
		if (type != null)
			AssetVocabularyHelper.removeCategory(type.getTitle(), type.getGroupId());

		// Supprime l'entité
		type = this.typePersistence.remove(typeId);

		// Supprime l'index
		this.reindex(type, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
				type.getCompanyId(), type.getGroupId(), Type.class.getName(),type.getTypeId());

		return type;
	}

	/**
	 * Reindex l'entité dans le moteur de recherche
	 */
	private void reindex(Type type, boolean delete) throws SearchException {
		Indexer<Type> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Type.class);
		if (delete) {
			indexer.delete(type);
		} else {
			indexer.reindex(type);
		}
	}

	/**
	 * Recherche par titre de Type
	 */
	@Override
	public List<Type> findByTitre(String title){
		return this.typePersistence.findByTitle(title);
	}

	/**
	 * Recherche par roleId de Type
	 */
	@Override
	public List<Type> findByRoleId(long roleId){
		return this.typePersistence.findByRoleId(roleId);
	}
	/**
	 * Si le titre avec l'ID donné est déjà utilisé par un autre type
	 */
	@Override
	public boolean isTitleAlreadyUsed(String title, long typeId) {
		boolean result = false;
		for (Type type : this.findByTitre(title)) {
			if (type.getTypeId() != typeId)
				result = true;
		}
		return result;
	}

	/**
	 * Si le type a des conseils
	 */
	@Override
	public boolean hasCouncil(long typeId) {
		boolean result = false;
		List<CouncilSession> councilsSessions = this.councilSessionLocalService.findByTypeId(typeId);
		if(councilsSessions.size() > 0)
			result = true;
		return result;
	}
}