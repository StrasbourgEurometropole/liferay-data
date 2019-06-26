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

package eu.strasbourg.service.gtfs.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
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

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import eu.strasbourg.service.gtfs.model.ImportHistoric;
import eu.strasbourg.service.gtfs.service.AgencyLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.CalendarDateLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.CalendarLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.RouteLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.StopLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.StopTimeLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.TripLocalServiceUtil;
import eu.strasbourg.service.gtfs.service.base.ImportHistoricLocalServiceBaseImpl;
import eu.strasbourg.utils.GTFSLoaderHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.exception.FileAccessException;
import eu.strasbourg.utils.models.AgencyGTFS;
import eu.strasbourg.utils.models.CalendarDatesGTFS;
import eu.strasbourg.utils.models.CalendarGTFS;
import eu.strasbourg.utils.models.RoutesGTFS;
import eu.strasbourg.utils.models.StopTimesGTFS;
import eu.strasbourg.utils.models.StopsGTFS;
import eu.strasbourg.utils.models.TripsGTFS;

/**
 * The implementation of the import historic local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.gtfs.service.ImportHistoricLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see ImportHistoricLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.ImportHistoricLocalServiceUtil
 */
public class ImportHistoricLocalServiceImpl	extends ImportHistoricLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.gtfs.service.ImportHistoricLocalServiceUtil} to access the import historic local service.
	 */
	
	public final static Log log = LogFactoryUtil.getLog(ImportHistoricLocalServiceImpl.class);
	
	/**
	 * Crée une entree d'import vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public ImportHistoric createImportHistoric(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		ImportHistoric importHistoric = this.importHistoricLocalService.createImportHistoric(pk);

		importHistoric.setGroupId(sc.getScopeGroupId());
		importHistoric.setUserName(user.getFullName());
		importHistoric.setUserId(sc.getUserId());

		importHistoric.setStatus(WorkflowConstants.STATUS_DRAFT);

		return importHistoric;
	}
	
	/**
	 * Met à jour une entree d'import et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public ImportHistoric updateImportHistoric(ImportHistoric importHistoric, ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());
		
		importHistoric.setStatusByUserId(sc.getUserId());
		importHistoric.setStatusByUserName(user.getFullName());
		importHistoric.setStatusDate(sc.getModifiedDate());

		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			importHistoric.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			importHistoric.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		importHistoric = this.importHistoricLocalService.updateImportHistoric(importHistoric);
		this.updateAssetEntry(importHistoric, sc);
		this.reindex(importHistoric, false);

		return importHistoric;
	}
	
	/**
	 * Met à jour l'AssetEntry rattachee à l'entree d'import
	 */
	private void updateAssetEntry(ImportHistoric importHistoric, ServiceContext sc) throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
				sc.getScopeGroupId(), // Group ID
				importHistoric.getCreateDate(), // Date of creation
				importHistoric.getModifiedDate(), // Date of modification
				ImportHistoric.class.getName(), // Class name
				importHistoric.getPrimaryKey(), // Class PK
				importHistoric.getUuid(), // UUID
				0, // Class type ID
				sc.getAssetCategoryIds(), // Categories IDs
				sc.getAssetTagNames(), // Tags IDs
				true, // Listable
				importHistoric.isApproved(), // Visible
				importHistoric.getCreateDate(), // Start date
				null, // End date
				importHistoric.getCreateDate(), // Publication date
				null, // Date of expiration
				ContentTypes.TEXT_HTML, // Content type
				Integer.toString(importHistoric.getResult()), // Title
				importHistoric.getOperations(), // Description
				importHistoric.getOperations(), // Summary
				null, // URL
				null, // Layout uuid
				0, // Width
				0, // Height
				null); // Priority

		// Réindexe l'entite
		this.reindex(importHistoric, false);
	}
	
	/**
	 * Met à jour le statut de l'entree d'import par le framework workflow
	 */
	@Override
	public ImportHistoric updateStatus(long userId, long entryId, int status,
								ServiceContext sc, Map<String, Serializable> workflowContext)
			throws PortalException {
		Date now = new Date();
		// Statut de l'entité
		ImportHistoric importHistoric = this.getImportHistoric(entryId);
		importHistoric.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			importHistoric.setStatusByUserId(user.getUserId());
			importHistoric.setStatusByUserName(user.getFullName());
		}
		importHistoric.setStatusDate(new Date());
		importHistoric = this.importHistoricLocalService.updateImportHistoric(importHistoric);
		
		// Statut de l'entry
		AssetEntry entry = this.assetEntryLocalService
				.getEntry(ImportHistoric.class.getName(), importHistoric.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(importHistoric, false);

		return importHistoric;
	}
	
	/**
	 * Met à jour le statut de l'entree d'import "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(ImportHistoric importHistoric, int status) throws PortalException {
		this.updateStatus(importHistoric.getUserId(), importHistoric.getImportHistoricId(), status, null,
				null);
	}
	
	/**
	 * Supprime l'entree d'import
	 */
	@Override
	public ImportHistoric removeImportHistoric(long importHistoricId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
				.fetchEntry(ImportHistoric.class.getName(), importHistoricId);

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
			AssetEntryLocalServiceUtil.deleteEntry(ImportHistoric.class.getName(),
					importHistoricId);

		}
		
		// Supprime l'entree d'historique
		ImportHistoric importHistoric = importHistoricPersistence.remove(importHistoricId);

		// Delete the index
		this.reindex(importHistoric, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
				importHistoric.getCompanyId(), importHistoric.getGroupId(), ImportHistoric.class.getName(),
				importHistoric.getImportHistoricId());

		return importHistoric;
	}
	
	/**
	 * Reindex le projet dans le moteur de recherche
	 */
	private void reindex(ImportHistoric importHistoric, boolean delete) throws SearchException {
		Indexer<ImportHistoric> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(ImportHistoric.class);
		if (delete) {
			indexer.delete(importHistoric);
		} else {
			indexer.reindex(importHistoric);
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
				.getClassNameId(ImportHistoric.class);
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
	 * Effectue l'import des donnees issues des fichiers GTFS
	 */
	@Override
	public void doImportGTFS() {
		// Recuperation du chemin absolu vers les fichiers du GTFS
		String GTFSPath = StrasbourgPropsUtil.getGTFSPath();
		
		try {
			Timestamp startTimestamp = new Timestamp(System.currentTimeMillis());
			log.info("Starting import of GTFS files");
			
			// Recuperation des lignes
			Map<String, AgencyGTFS> mapAgencys;
			mapAgencys = GTFSLoaderHelper.readAgencyData(GTFSPath);
			AgencyLocalServiceUtil.importFromGTFS(mapAgencys);
			log.info("GTFS Files : get " + mapAgencys.size() + " Agency entries");
			
			// Recuperation des calendrier
			Map<String, CalendarGTFS> mapCalendars;
			mapCalendars = GTFSLoaderHelper.readCalendarData(GTFSPath);
			CalendarLocalServiceUtil.importFromGTFS(mapCalendars);
			log.info("GTFS Files : get " + mapCalendars.size() + " Calendar entries");
			
			// Recuperation des dates de calendrier
			Map<String, List<CalendarDatesGTFS>> mapCalendarDates;
			mapCalendarDates = GTFSLoaderHelper.readCalendarDatesData(GTFSPath);
			CalendarDateLocalServiceUtil.importFromGTFS(mapCalendarDates);
			log.info("GTFS Files : get " + mapCalendarDates.size() + " CalendarDate keys");
			
			// Recuperation des lignes
			Map<String, RoutesGTFS> mapRoutes;
			mapRoutes = GTFSLoaderHelper.readRoutesData(GTFSPath);
			RouteLocalServiceUtil.importFromGTFS(mapRoutes);
			log.info("GTFS Files : get " + mapRoutes.size() + " Route entries");
			
			// Recuperation des temps d'arrêt
			Map<String, List<StopTimesGTFS>> mapStopTimes;
			mapStopTimes = GTFSLoaderHelper.readStopTimesData(GTFSPath);
			StopTimeLocalServiceUtil.importFromGTFS(mapStopTimes);
			log.info("GTFS Files : get " + mapStopTimes.size() + " StopTime keys");
			
			// Recuperation des routes
			Map<String, StopsGTFS> mapStops;
			mapStops = GTFSLoaderHelper.readStopsData(GTFSPath);
			StopLocalServiceUtil.importFromGTFS(mapStops);
			log.info("GTFS Files : get " + mapStops.size() + " Stops entries");
			
			// Recuperation des voyages
			Map<String, TripsGTFS> mapTrips;
			mapTrips = GTFSLoaderHelper.readTripsData(GTFSPath);
			TripLocalServiceUtil.importFromGTFS(mapTrips);
			log.info("GTFS Files : get " + mapTrips.size() + " Trips keys");
			
			Timestamp endTimestamp = new Timestamp(System.currentTimeMillis());
			long processTime = (endTimestamp.getTime() - startTimestamp.getTime()) / 1000;
			log.info("Finishing import of GTFS files in " + processTime + " seconds.");
			
			throw new PortalException();
			
		} catch (FileAccessException | PortalException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retourne tous les projets d'un groupe
	 */
	@Override
	public List<ImportHistoric> getByGroupId(long groupId) {
		return this.importHistoricPersistence.findByGroupId(groupId);
	}
	
	/**
	 * Recherche par mot clés
	 */
	@Override
	public List<ImportHistoric> findByKeyword(String keyword, long groupId, int start, int end) {
		DynamicQuery dynamicQuery = dynamicQuery();

		if (keyword.length() > 0) {
			dynamicQuery.add(
					RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
		}
		if (groupId > 0) {
			dynamicQuery
					.add(PropertyFactoryUtil.forName("groupId").eq(groupId));
		}

		return this.importHistoricPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}
	
	/**
	 * Recherche par mot clés (compte)
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
		return this.importHistoricPersistence.countWithDynamicQuery(dynamicQuery);
	}
	
}