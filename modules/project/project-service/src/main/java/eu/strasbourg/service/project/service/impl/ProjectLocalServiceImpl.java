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

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
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

import eu.strasbourg.service.project.exception.NoSuchProjectException;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.model.ProjectTimeline;
import eu.strasbourg.service.project.service.base.ProjectLocalServiceBaseImpl;
import eu.strasbourg.service.project.service.ProjectTimelineLocalServiceUtil;


/**
 * The implementation of the project local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.service.ProjectLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see ProjectLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.ProjectLocalServiceUtil
 */
public class ProjectLocalServiceImpl extends ProjectLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.project.service.ProjectLocalServiceUtil} to access the project local service.
	 */

	public final static Log log = LogFactoryUtil.getLog(ProjectLocalServiceImpl.class);
	/**
	 * Crée un projet vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Project createProject(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Project project = this.projectLocalService.createProject(pk);

		project.setGroupId(sc.getScopeGroupId());
		project.setUserName(user.getFullName());
		project.setUserId(sc.getUserId());

		project.setStatus(WorkflowConstants.STATUS_DRAFT);

		return project;
	}

	/**
	 * Met à jour un projet et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public Project updateProject(Project project, ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());
		
		project.setStatusByUserId(sc.getUserId());
		project.setStatusByUserName(user.getFullName());
		project.setStatusDate(sc.getModifiedDate());
		
		// Recuperation des timelines du projet par ordre decroissant
		// (voir "order" du service.xml)
		List<ProjectTimeline> projectTimelines = project.getProjectTimelines();
		
		// Verifie l'existance d'au moins une timeline
		if (projectTimelines != null && projectTimelines.size() > 1) {
			
			// Garder la premiere timeline en reference
			ProjectTimeline firstTimeline = projectTimelines.get(0);
			
			// Parcourir et calculer
			projectTimelines.forEach(projectTimeline -> {
				long diff = projectTimeline.getDate().getTime() - firstTimeline.getDate().getTime();
			    int dayDiff =((int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
			    
			    projectTimeline.setStartDay(dayDiff);
			});
		}

		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			project.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			project.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		project = this.projectLocalService.updateProject(project);
		this.updateAssetEntry(project, sc);
		this.reindex(project, false);

		return project;
	}

	/**
	 * Met à jour l'AssetEntry rattachée au projet
	 */
	private void updateAssetEntry(Project project, ServiceContext sc)
			throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				project.getCreateDate(), // Date of creation
				project.getModifiedDate(), // Date of modification
				Project.class.getName(), // Class name
				project.getPrimaryKey(), // Class PK
				project.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				project.isApproved(), // Visible
				project.getCreateDate(), // Start date
				null, // End date
				project.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				project.getTitle(), // Title
				project.getDescription(), // Description
				project.getDescription(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe le projet
		this.reindex(project, false);
	}

	/**
	 * Met à jour le statut du projet par le framework workflow
	 */
	@Override
	public Project updateStatus(long userId, long entryId, int status,
								ServiceContext sc, Map<String, Serializable> workflowContext)
			throws PortalException {
		Date now = new Date();
		// Statut de l'entité
		Project project = this.getProject(entryId);
		project.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			project.setStatusByUserId(user.getUserId());
			project.setStatusByUserName(user.getFullName());
		}
		project.setStatusDate(new Date());
		project = this.projectLocalService.updateProject(project);

		// Statut de l'entry
		AssetEntry entry = this.assetEntryLocalService
				.getEntry(Project.class.getName(), project.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(project, false);

		return project;
	}

	/**
	 * Met à jour le statut du projet "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(Project project, int status) throws PortalException {
		this.updateStatus(project.getUserId(), project.getProjectId(), status, null,
				null);
	}

	/**
	 * Supprime un projet
	 */
	@Override
	public Project removeProject(long projectId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
				.fetchEntry(Project.class.getName(), projectId);

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
			AssetEntryLocalServiceUtil.deleteEntry(Project.class.getName(),
					projectId);

			// Supprime les timelines associées au projet
			List<ProjectTimeline> projectTimelines = ProjectTimelineLocalServiceUtil
					.getByProjectId(projectId);
			for (ProjectTimeline projectTimeline : projectTimelines) {
				ProjectTimelineLocalServiceUtil.deleteProjectTimeline(projectTimeline);
			}
			
			// Supprime les lieux
			List<PlacitPlace> placitPlaces = this.placitPlaceLocalService
				.getByProject(projectId);
			for (PlacitPlace placitPlace : placitPlaces) {
				this.placitPlaceLocalService.removePlacitPlace(
						placitPlace.getPlacitPlaceId());
			}

		}

		// Supprime le projet
		Project project = projectPersistence.remove(projectId);

		// Delete the index
		this.reindex(project, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
				project.getCompanyId(), project.getGroupId(), Project.class.getName(),
				project.getProjectId());

		return project;
	}

	/**
	 * Reindex le projet dans le moteur de recherche
	 */
	private void reindex(Project project, boolean delete) throws SearchException {
		Indexer<Project> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(Project.class);
		if (delete) {
			indexer.delete(project);
		} else {
			indexer.reindex(project);
		}
	}

	/**
	 * Renvoie la liste des vocabulaires rattachés à un projet
	 */
	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
				.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil
				.getClassNameId(Project.class);
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
	 * Retourne tous les projets d'un groupe
	 */
	@Override
	public List<Project> getByGroupId(long groupId) {
		return this.projectPersistence.findByGroupId(groupId);
	}

	/**
	 * Recherche par mot clés
	 */
	@Override
	public List<Project> findByKeyword(String keyword, long groupId, int start,
									   int end) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (keyword.length() > 0) {
			dynamicQuery.add(
					RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery
					.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return projectPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Recherche par ID de catégorie
	 */
	@Override
	public List<Project> findByCategoryIds(long[] categoryIds) {
		
		// Initialisation des variables tempons et resutantes
		Project matchingProject = null;
		List<Project> projects = new ArrayList<Project>();
		
		// Creation de la requete d'assetEntry correspondant a notre definition
		AssetEntryQuery assetEntryQueryForCategory = new AssetEntryQuery();
		assetEntryQueryForCategory.setAllCategoryIds(categoryIds);
		assetEntryQueryForCategory.setClassName(Project.class.getName());
		assetEntryQueryForCategory.setOrderByCol1("title");
		assetEntryQueryForCategory.setOrderByType1("ASC");
		assetEntryQueryForCategory.setVisible(true);

		// Recherche en elle-meme
		List<AssetEntry> assetEntries = AssetEntryLocalServiceUtil.getEntries(assetEntryQueryForCategory);
		
		// Parcours des resultats et ajout des projets correspondants dans la liste finale
		for (AssetEntry assetEntry : assetEntries) {
			try {
				matchingProject = this.projectPersistence.findByPrimaryKey(assetEntry.getClassPK());
				
				if (matchingProject != null) {
					projects.add(matchingProject);
				}
			} catch (NoSuchProjectException e) {
				_log.error("Project '"+ assetEntry.getClassPK() + "' doesn't exist anymore " + 
					" but the corresponding AssetEntry '" + assetEntry.getEntryId() + 
					"' is always in the movie ...\n" + e);
				continue;
			}
		}
		
		return projects;
	}

	/**
	 * Recherche par mot clés (compte)
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
		return projectPersistence.countWithDynamicQuery(dynamicQuery);
	}
	
	private Log _log = LogFactoryUtil.getLog(this.getClass());
}