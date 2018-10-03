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

package eu.strasbourg.service.agenda.service.impl;

import aQute.bnd.annotation.ProviderType;
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
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
import eu.strasbourg.service.agenda.exception.NoSuchEventException;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.model.EventModel;
import eu.strasbourg.service.agenda.model.EventParticipation;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.service.EventPeriodLocalServiceUtil;
import eu.strasbourg.service.agenda.service.base.EventLocalServiceBaseImpl;
import eu.strasbourg.service.agenda.utils.AgendaImporter;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * The implementation of the event local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.agenda.service.EventLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see EventLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.EventLocalServiceUtil
 */
@ProviderType
public class EventLocalServiceImpl extends EventLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.agenda.service.EventLocalServiceUtil} to access the
	 * event local service.
	 */

	/**
	 * Crée une édition vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Event createEvent(ServiceContext sc) throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		long pk = counterLocalService.increment();

		Event event = this.eventLocalService.createEvent(pk);

		event.setGroupId(sc.getScopeGroupId());
		event.setCompanyId(sc.getCompanyId());
		event.setUserName(user.getFullName());
		event.setUserId(sc.getUserId());

		event.setStatus(WorkflowConstants.STATUS_DRAFT);

		return event;
	}

	/**
	 * Met à jour une édition et l'enregistre en base de données
	 * @throws IOException 
	 */
	@Override
	public Event updateEvent(Event event, ServiceContext sc)
		throws PortalException, IOException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());

		event.setCompanyId(sc.getCompanyId());
		event.setStatusByUserId(sc.getUserId());
		event.setStatusByUserName(user.getFullName());
		event.setStatusDate(sc.getModifiedDate());
		event.setImageHeight(0);
		event.setImageWidth(0);
		
	/*	if(event.getImageId() == null || event.getImageId() == 0) {
			URL url = new URL(event.getExternalImageURL());
	        final BufferedImage bi = ImageIO.read(url);
			event.setImageHeight(bi.getHeight());
			event.setImageWidth(bi.getWidth());
		}
		else {
			String imageURL = FileEntryHelper.getFileEntryURL(event.getImageId());
			
			String completeImageURL = StrasbourgPropsUtil.getURL() + imageURL;
			URL url = new URL(completeImageURL);
	        final BufferedImage bi = ImageIO.read(url);
			event.setImageHeight(bi.getHeight());
			event.setImageWidth(bi.getWidth());
		}*/
		
		// On classe les périodes par date de début, ce qui va nous
		// permettre
		// de setter les champs "firstStartDate" et "lastEndDate" sur
		// l'événement
		if (event.getEventPeriods().size() > 0) {
			List<EventPeriod> periods = new ArrayList<EventPeriod>(
				event.getEventPeriods());
			periods.sort(
				(p1, p2) -> p1.getStartDate().compareTo(p2.getStartDate()));

			Date firstStartDate = periods.get(0).getStartDate();
			Date lastEndDate = periods.get(periods.size() - 1).getEndDate();
			event.setFirstStartDate(firstStartDate);
			event.setLastEndDate(lastEndDate);
		}

		// Si on n'utilise pas le framework workflow, simple gestion
		// brouillon/publié
		if (!WorkflowDefinitionLinkLocalServiceUtil.hasWorkflowDefinitionLink(
			sc.getCompanyId(), sc.getScopeGroupId(), Event.class.getName())) {
			if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
				if (event.getPublicationDate().after(new Date())) {
					event.setStatus(WorkflowConstants.STATUS_SCHEDULED);
				} else {
					event.setStatus(WorkflowConstants.STATUS_APPROVED);
				}
			} else {
				event.setStatus(WorkflowConstants.STATUS_DRAFT);
				// Si le statut est "DRAFT" et qu'il y a une version live, on
				// supprime cette dernière
				Event liveEvent = event.getLiveVersion();
				if (liveEvent != null) {
					this.removeEvent(liveEvent.getEventId());
				}
			}
			event = this.eventLocalService.updateEvent(event);
			this.updateAssetEntry(event, sc);
			this.reindex(event, false);
		} else { // Si le framework worflow est actif, c'est celui-ci qui gère
				 // l'enregistrement
			event = this.eventLocalService.updateEvent(event);
			WorkflowHandlerRegistryUtil.startWorkflowInstance(
				event.getCompanyId(), event.getGroupId(), event.getUserId(),
				Event.class.getName(), event.getPrimaryKey(), event, sc);
		}

		return event;
	}

	/**
	 * Met à jour l'AssetEntry rattachée à l'édition
	 */
	private void updateAssetEntry(Event event, ServiceContext sc)
		throws PortalException {
		this.assetEntryLocalService.updateEntry(sc.getUserId(), // User ID
			sc.getScopeGroupId(), // Group ID
			event.getCreateDate(), // Date of creation
			event.getModifiedDate(), // Date of modification
			Event.class.getName(), // Class name
			event.getPrimaryKey(), // Class PK
			event.getUuid(), // UUID
			0, // Class type ID
			sc.getAssetCategoryIds(), // Categories IDs
			sc.getAssetTagNames(), // Tags IDs
			true, // Listable
			event.isApproved(), // Visible
			event.getPublicationDate(), // Start date
			null, // End date
			event.getPublicationDate(), // Publication date
			null, // Date of expiration
			ContentTypes.TEXT_HTML, // Content type
			event.getTitle(), // Title
			event.getDescription(), // Description
			event.getDescription(), // Summary
			null, // URL
			null, // Layout uuid
			0, // Width
			0, // Height
			null); // Priority

		// Réindexe l'édition
		this.reindex(event, false);
	}

	/**
	 * Met à jour le statut de l'édition par le framework workflow
	 */
	@Override
	public Event updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<String, Serializable> workflowContext)
		throws PortalException {
		Date now = new Date();
		Event event = this.getEvent(entryId);

		event.setStatus(status);
		User user = UserLocalServiceUtil.fetchUser(userId);
		if (user != null) {
			event.setStatusByUserId(user.getUserId());
			event.setStatusByUserName(user.getFullName());
		}
		event.setStatusDate(new Date());
		if (event.isApproved()) {
			event.setPublicationDate(now);
		}

		event = this.eventLocalService.updateEvent(event);

		AssetEntry entry = this.assetEntryLocalService
			.getEntry(Event.class.getName(), event.getPrimaryKey());
		entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
		if (entry.isVisible()) {
			entry.setPublishDate(now);
		}
		this.assetEntryLocalService.updateAssetEntry(entry);

		this.reindex(event, false);

		// Si le nouveau statut est "DRAFT" et qu'il y a une version live, on
		// supprime cette dernière
		Event liveEvent = event.getLiveVersion();
		if (status == WorkflowConstants.STATUS_DRAFT && liveEvent != null) {
			this.removeEvent(liveEvent.getEventId());
		}

		return event;
	}

	/**
	 * Met à jour le statut de l'édition "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(Event event, int status) throws PortalException {
		this.updateStatus(event.getUserId(), event.getEventId(), status, null,
			null);
	}

	/**
	 * Modifie le statut de tous les events au statut "SCHEDULED" qui ont une
	 * date de publication dans le futur.
	 */
	@Override
	public void checkEvents() throws PortalException {
		List<Event> events = this.eventPersistence
			.findByPublicationDateAndStatus(new Date(),
				WorkflowConstants.STATUS_SCHEDULED);
		int n = 0;
		for (Event event : events) {
			this.updateStatus(event, WorkflowConstants.STATUS_APPROVED);
			n++;
		}
		if (n > 0) {
			_log.info("Published " + n + " events");
		}
	}

	/**
	 * Dépublie les événements dont la dernière date de fin est dépassée
	 */
	@Override
	public void unpublishPastEvents() throws PortalException {
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DATE, -1);
		List<Event> events = this.eventPersistence
			.findByLastEndDate(yesterday.getTime());
		for (Event event : events) {
			if (event.getStatus() != WorkflowConstants.STATUS_DRAFT) {
				this.updateStatus(event, WorkflowConstants.STATUS_DRAFT);
			}
		}
	}

	/**
	 * Supprime les événements dépubliés depuis au moins un mois
	 */
	@Override
	public void deleteOldUnpublishedEvents() throws PortalException {
		LocalDate oneMonthAgoLocalDate = LocalDate.now().minusMonths(1);
		Date oneMonthAgo = Date.from(oneMonthAgoLocalDate
			.atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<Event> events = this.eventPersistence.findByStatusDateAndStatus(
			oneMonthAgo, WorkflowConstants.STATUS_DRAFT);
		for (Event event : events) {
			this.removeEvent(event.getEventId());
		}
	}

	/**
	 * Delete an Event
	 * 
	 * @param eventId
	 *            The ID of the event to delete
	 * @return The deleted Event
	 * @throws PortalException
	 */
	@Override
	public Event removeEvent(long eventId) throws PortalException {
		AssetEntry entry = AssetEntryLocalServiceUtil
			.fetchEntry(Event.class.getName(), eventId);

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

			// Supprime l'assetEntry
			AssetEntryLocalServiceUtil.deleteEntry(Event.class.getName(),
				eventId);

			// Supprime les périodes
			List<EventPeriod> periods = EventPeriodLocalServiceUtil
				.getByEventId(eventId);
			for (EventPeriod period : periods) {
				EventPeriodLocalServiceUtil.deleteEventPeriod(period);
			}
		}

		// Delete the Event
		Event event = eventPersistence.remove(eventId);

		// Delete the index
		this.reindex(event, true);

		// Supprime ce qui a rapport au workflow
		WorkflowInstanceLinkLocalServiceUtil.deleteWorkflowInstanceLinks(
			event.getCompanyId(), event.getGroupId(), Event.class.getName(),
			event.getEventId());
		
		return event;
	}

	/**
	 * Reindex l'édition dans le moteur de recherche
	 */
	private void reindex(Event event, boolean delete) throws SearchException {
		Indexer<Event> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Event.class);
		if (delete) {
			indexer.delete(event);
		} else {
			indexer.reindex(event);
		}
	}

	/**
	 * Renvoie la liste des vocabulaires rattachés à l'entité Event
	 */
	@Override
	public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
		List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
			.getAssetVocabularies(-1, -1);
		List<AssetVocabulary> attachedVocabularies = new ArrayList<AssetVocabulary>();
		long classNameId = ClassNameLocalServiceUtil
			.getClassNameId(Event.class);
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
	public List<Event> getByGroupId(long groupId) {
		return this.eventPersistence.findByGroupId(groupId);
	}

	/**
	 * Lance une recherche selon le searchContext
	 */
	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		Indexer<Event> indexer = IndexerRegistryUtil
			.nullSafeGetIndexer(Event.class);
		return indexer.search(searchContext);
	}

	/**
	 * Lance une recherche par mots-clés
	 */
	@Override
	public List<Event> findByKeyword(String keyword, long groupId, int start,
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
		dynamicQuery.add(PropertyFactoryUtil.forName("status")
			.eq(WorkflowConstants.STATUS_APPROVED));

		return eventPersistence.findWithDynamicQuery(dynamicQuery, start, end);
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
		dynamicQuery.add(PropertyFactoryUtil.forName("status")
			.eq(WorkflowConstants.STATUS_APPROVED));

		return eventPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Lance l'import des événements
	 * @throws IOException 
	 */
	@Override
	public boolean doImport() throws IOException {
		AgendaImporter agendaImporter = new AgendaImporter();
		agendaImporter.doImport();
		return true;
	}
	
	@Override
	public Event findBySourceAndIdSource(String source, String idSource) {
		try {
			return eventPersistence.findBySourceAndIdSource(source, idSource);
		} catch (NoSuchEventException e) {
			return null;
		}
	}

	/**
	 * Lance une recherche par placeSIGId
	 */
	@Override
	public List<Event> findByPlaceSIGId(String placeSIGId) {
		return eventPersistence.findByPlaceSIGId(placeSIGId);
	}

	private final Log _log = LogFactoryUtil.getLog(this.getClass());

	public List<Event> findEventByUserPublikId(String publikId){
		List<EventParticipation> resultList = eventParticipationLocalService.getByPublikUser(publikId);
		List<Event> eventList = resultList.stream().map(result -> {
			Event event = null;
			try {
				event = getEvent(result.getEventId());
			} catch (PortalException e) {
				e.printStackTrace();
			}
			return event;
		}).collect(Collectors.toList());
		return eventList.stream()
				.filter(EventModel::isApproved)
				.collect(Collectors.toList());
	}
}