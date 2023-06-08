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

package eu.strasbourg.service.activity.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
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
import eu.strasbourg.service.activity.model.Practice;
import eu.strasbourg.service.activity.model.PracticeCategories;
import eu.strasbourg.service.activity.service.base.PracticeLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.LongStream;

/**
 * The implementation of the practice local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.activity.service.PracticeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PracticeLocalServiceBaseImpl
 * @see eu.strasbourg.service.activity.service.PracticeLocalServiceUtil
 */
public class PracticeLocalServiceImpl extends PracticeLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.activity.service.PracticeLocalServiceUtil} to access the practice local service.
	 */

	/**
	 * Crée une pratique vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Practice createPractice(ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Practice practice = this.practiceLocalService
				.createPractice(pk);

		practice.setGroupId(sc.getScopeGroupId());
		practice.setUserName(user.getFullName());
		practice.setUserId(sc.getUserId());

		practice.setStatus(WorkflowConstants.STATUS_DRAFT);

		return practice;
	}

	/**
	 * Met à jour une pratique et l'enregistre en base de données
	 */
	@Override
	public Practice updatePractice(Practice practice,
										 ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		practice.setStatusByUserId(sc.getUserId());
		practice.setStatusByUserName(user.getFullName());
		practice.setStatusDate(sc.getModifiedDate());

		// Gestion du statut
		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			practice.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			practice.setStatus(WorkflowConstants.STATUS_DRAFT);
			// Si le statut est "DRAFT" et qu'il y a une version live, on
			// supprime cette dernière
			Practice livePractice = practice
					.getLiveVersion();
			if (livePractice != null) {
				this.removePractice(
						livePractice.getPracticeId());
			}
		}
		practice = this.practiceLocalService
				.updatePractice(practice);
		this.updateAssetEntry(practice, sc);
		this.reindex(practice, false);

		return practice;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à l'entité
	 */
	private void updateAssetEntry(Practice practice,
								  ServiceContext sc) throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				practice.getCreateDate(), // Date of creation
				practice.getModifiedDate(), // Date of modification
				Practice.class.getName(), // Class name
				practice.getPrimaryKey(), // Class PK
				practice.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				practice.isApproved(), // Visible
				practice.getCreateDate(), // Start date
				null, // End date
				practice.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				"Activité de " + practice.getAssociation().getNameCurrentValue(), // Title
				"Activité de " + practice.getAssociation().getNameCurrentValue(), // Description
				"Activité de " + practice.getAssociation().getNameCurrentValue(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe l'édition
		this.reindex(practice, false);
	}

	/**
	 * Met à jour le statut de la pratique par le framework workflow
	 */
	@Override
	public Practice updateStatus(long userId, long entryId, int status)
			throws PortalException {
		Practice practice = this
				.getPractice(entryId);

		practice.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			practice.setStatusByUserId(user.getUserId());
			practice.setStatusByUserName(user.getFullName());
		}
		practice.setStatusDate(new Date());

		practice = this.practiceLocalService
				.updatePractice(practice);

		AssetEntry entry = practice.getAssetEntry();
		entry.setVisible(practice.isApproved());
		if (entry.isVisible()) {
			entry.setPublishDate(new Date());
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(practice, false);

		// Si le nouveau statut est "DRAFT" et qu'il y a une version live, on
		// supprime cette dernière
		Practice livePractice = practice
				.getLiveVersion();
		if (status == WorkflowConstants.STATUS_DRAFT
				&& livePractice != null) {
			this.removePractice(livePractice.getPrimaryKey());
		}

		return practice;
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public Practice removePractice(long practiceId)
			throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
				.fetchEntry(Practice.class.getName(), practiceId);

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
				AssetEntryLocalServiceUtil.deleteAssetTagAssetEntry(tagIds[i],
						entry.getEntryId());
			}

			// Supprime l'assetEntry
			AssetEntryLocalServiceUtil.deleteEntry(
					Practice.class.getName(), practiceId);

		}

		// Supprime l'entité
		Practice practice = this.practicePersistence
				.remove(practiceId);

		// Supprime l'index
		this.reindex(practice, true);

		return practice;
	}

	/**
	 * Reindex l'entité dans le moteur de recherche
	 */
	private void reindex(Practice practice, boolean delete)
			throws SearchException {
		Indexer<Practice> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(Practice.class);
		if (delete) {
			indexer.delete(practice);
		} else {
			indexer.reindex(practice);
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
				.getClassNameId(Practice.class);
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
	 * Retourne toutes les éditions d'un groupe
	 */
	@Override
	public List<Practice> getByGroupId(long groupId) {
		return this.practicePersistence.findByGroupId(groupId);
	}

	/**
	 * Lance une recherche selon le searchContext
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<Practice> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(Practice.class);
		return indexer.search(searchContext);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	@Override
	public List<Practice> findByKeyword(String keyword, long groupId,
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
		dynamicQuery.add(PropertyFactoryUtil.forName("status")
				.eq(WorkflowConstants.STATUS_APPROVED));

		return practicePersistence.findWithDynamicQuery(dynamicQuery,
				start, end);
	}

	/**
	 * Compte de la recherche par mots-clés
	 */
	@Override
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

		return practicePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Retourne les pratiques d'une association
	 */
	@Override
	public List<Practice> getByAssociation(long associationId) {
		return this.practicePersistence.findByAssociation(associationId);
	}

	/**
	 * Retourne les activités triées par domaine de l'association
	 */
	@Override
	public List<PracticeCategories> getPracticesSortedByAssociation(long associationId) {
		// Permet la récupération de toutes les catégories entières
		Session session = practicePersistence.openSession();
		SQLQuery query = session.createSQLQuery("SET SESSION group_concat_max_len = 1000000");
		query.executeUpdate();

		List<PracticeCategories> practicesCategories = new ArrayList<>();
		query = session.createSQLQuery(this.query);
		QueryPos pos = QueryPos.getInstance(query);
		pos.add(associationId);
		for (Object values : query.list()) {
			Object[] categoies = (Object[]) values;
			PracticeCategories practiceCategories = new PracticeCategories(categoies);
			practicesCategories.add(practiceCategories);
		}
		session.close();
		return practicesCategories;
	}

	private String query = "SELECT *," +
			"                (SELECT GROUP_CONCAT(DISTINCT category.title SEPARATOR '||')" +
			"                FROM AssetEntryAssetCategoryRel AS entrycateg" +
			"                INNER JOIN AssetCategory AS category ON category.categoryId = entrycateg.assetCategoryId" +
			"                INNER JOIN AssetVocabulary AS publicVocabulary ON category.vocabularyId = publicVocabulary.vocabularyId AND publicVocabulary.name = 'public de pratique'" +
			"                WHERE entrycateg.assetEntryId = pratique.entryId) as publics," +
			"                (SELECT GROUP_CONCAT(DISTINCT category.title SEPARATOR '||')" +
			"                FROM AssetEntryAssetCategoryRel AS entrycateg" +
			"                INNER JOIN AssetCategory AS category ON category.categoryId = entrycateg.assetCategoryId" +
			"                INNER JOIN AssetVocabulary AS districtVocabulary ON category.vocabularyId = districtVocabulary.vocabularyId AND districtVocabulary.name = 'territoire'" +
			"                WHERE entrycateg.assetEntryId = pratique.entryId) as districts," +
			"                (SELECT GROUP_CONCAT(DISTINCT category.title SEPARATOR '||')" +
			"                FROM AssetEntryAssetCategoryRel AS entrycateg" +
			"                INNER JOIN AssetCategory AS category ON category.categoryId = entrycateg.assetCategoryId" +
			"                INNER JOIN AssetVocabulary AS accessibilityVocabulary ON category.vocabularyId = accessibilityVocabulary.vocabularyId AND accessibilityVocabulary.name = 'accessibilite de pratique'" +
			"                WHERE entrycateg.assetEntryId = pratique.entryId) as accessibilities" +
			"            FROM (SELECT entry.entryId, " +
			"                       CASE WHEN parent.parentCategoryId = 0 THEN parent.title ELSE CASE WHEN gdparent.parentCategoryId = 0 THEN gdparent.title ELSE agdparent.title END END as domaine," +
			"                       CASE WHEN parent.parentCategoryId = 0 THEN category.title ELSE CASE WHEN gdparent.parentCategoryId = 0 THEN parent.title ELSE gdparent.title END END as specialite," +
			"                       CASE WHEN parent.parentCategoryId = 0 THEN '' ELSE CASE WHEN gdparent.parentCategoryId = 0 THEN category.title ELSE parent.title END END as sous_specialite," +
			"                       CASE WHEN parent.parentCategoryId = 0 THEN '' ELSE CASE WHEN gdparent.parentCategoryId = 0 THEN '' ELSE category.title END END as sous_sous_specialite," +
			"                       category.name as pratique" +
			"                FROM activity_Practice AS practice" +
			"                INNER JOIN AssetEntry AS entry ON entry.classPK = practice.practiceId" +
			"                INNER JOIN AssetEntryAssetCategoryRel AS entrycateg ON entrycateg.assetEntryId = entry.entryId" +
			"                INNER JOIN AssetCategory AS category ON category.categoryId = entrycateg.assetCategoryId" +
			"                INNER JOIN AssetVocabulary AS practiceVocabulary ON category.vocabularyId = practiceVocabulary.vocabularyId AND practiceVocabulary.name = 'domaine de pratique'" +
			"                INNER JOIN AssetCategory AS parent ON parent.categoryId = category.parentCategoryId" +
			"                LEFT JOIN AssetCategory AS gdparent ON gdparent.categoryId = parent.parentCategoryId" +
			"                LEFT JOIN AssetCategory AS agdparent ON agdparent.categoryId = gdparent.parentCategoryId" +
			"                WHERE associationId = ?) as pratique" +
			"            ORDER BY domaine, pratique";
}