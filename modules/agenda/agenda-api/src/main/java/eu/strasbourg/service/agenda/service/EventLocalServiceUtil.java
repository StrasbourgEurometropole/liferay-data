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

package eu.strasbourg.service.agenda.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Event. This utility wraps
 * {@link eu.strasbourg.service.agenda.service.impl.EventLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author BenjaminBini
 * @see EventLocalService
 * @see eu.strasbourg.service.agenda.service.base.EventLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.impl.EventLocalServiceImpl
 * @generated
 */
@ProviderType
public class EventLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.agenda.service.impl.EventLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Lance l'import des événements
	*
	* @throws IOException
	*/
	public static boolean doImport() throws java.io.IOException {
		return getService().doImport();
	}

	public static boolean hasManifestationEvent(long manifestationId,
		long eventId) {
		return getService().hasManifestationEvent(manifestationId, eventId);
	}

	public static boolean hasManifestationEvents(long manifestationId) {
		return getService().hasManifestationEvents(manifestationId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Lance une recherche selon le searchContext
	*/
	public static com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return getService().search(searchContext);
	}

	/**
	* Adds the event to the database. Also notifies the appropriate model listeners.
	*
	* @param event the event
	* @return the event that was added
	*/
	public static eu.strasbourg.service.agenda.model.Event addEvent(
		eu.strasbourg.service.agenda.model.Event event) {
		return getService().addEvent(event);
	}

	/**
	* Crée une édition vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.agenda.model.Event createEvent(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createEvent(sc);
	}

	/**
	* Creates a new event with the primary key. Does not add the event to the database.
	*
	* @param eventId the primary key for the new event
	* @return the new event
	*/
	public static eu.strasbourg.service.agenda.model.Event createEvent(
		long eventId) {
		return getService().createEvent(eventId);
	}

	/**
	* Deletes the event from the database. Also notifies the appropriate model listeners.
	*
	* @param event the event
	* @return the event that was removed
	*/
	public static eu.strasbourg.service.agenda.model.Event deleteEvent(
		eu.strasbourg.service.agenda.model.Event event) {
		return getService().deleteEvent(event);
	}

	/**
	* Deletes the event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventId the primary key of the event
	* @return the event that was removed
	* @throws PortalException if a event with the primary key could not be found
	*/
	public static eu.strasbourg.service.agenda.model.Event deleteEvent(
		long eventId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteEvent(eventId);
	}

	public static eu.strasbourg.service.agenda.model.Event fetchEvent(
		long eventId) {
		return getService().fetchEvent(eventId);
	}

	/**
	* Returns the event matching the UUID and group.
	*
	* @param uuid the event's UUID
	* @param groupId the primary key of the group
	* @return the matching event, or <code>null</code> if a matching event could not be found
	*/
	public static eu.strasbourg.service.agenda.model.Event fetchEventByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchEventByUuidAndGroupId(uuid, groupId);
	}

	public static eu.strasbourg.service.agenda.model.Event findBySourceAndIdSource(
		java.lang.String source, java.lang.String idSource) {
		return getService().findBySourceAndIdSource(source, idSource);
	}

	/**
	* Returns the event with the primary key.
	*
	* @param eventId the primary key of the event
	* @return the event
	* @throws PortalException if a event with the primary key could not be found
	*/
	public static eu.strasbourg.service.agenda.model.Event getEvent(
		long eventId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEvent(eventId);
	}

	/**
	* Returns the event matching the UUID and group.
	*
	* @param uuid the event's UUID
	* @param groupId the primary key of the group
	* @return the matching event
	* @throws PortalException if a matching event could not be found
	*/
	public static eu.strasbourg.service.agenda.model.Event getEventByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEventByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Delete an Event
	*
	* @param eventId
	The ID of the event to delete
	* @return The deleted Event
	* @throws PortalException
	*/
	public static eu.strasbourg.service.agenda.model.Event removeEvent(
		long eventId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeEvent(eventId);
	}

	/**
	* Updates the event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param event the event
	* @return the event that was updated
	*/
	public static eu.strasbourg.service.agenda.model.Event updateEvent(
		eu.strasbourg.service.agenda.model.Event event) {
		return getService().updateEvent(event);
	}

	/**
	* Met à jour une édition et l'enregistre en base de données
	*
	* @throws IOException
	*/
	public static eu.strasbourg.service.agenda.model.Event updateEvent(
		eu.strasbourg.service.agenda.model.Event event,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			java.io.IOException {
		return getService().updateEvent(event, sc);
	}

	/**
	* Met à jour le statut de l'édition par le framework workflow
	*/
	public static eu.strasbourg.service.agenda.model.Event updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, entryId, status, sc, workflowContext);
	}

	/**
	* Returns the number of events.
	*
	* @return the number of events
	*/
	public static int getEventsCount() {
		return getService().getEventsCount();
	}

	public static int getManifestationEventsCount(long manifestationId) {
		return getService().getManifestationEventsCount(manifestationId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Lance une recherche par mots-clés
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.Event> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return getService().findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Lance une recherche par placeSIGId
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.Event> findByPlaceSIGId(
		java.lang.String placeSIGId) {
		return getService().findByPlaceSIGId(placeSIGId);
	}

	public static java.util.List<eu.strasbourg.service.agenda.model.Event> findEventByUserPublikId(
		java.lang.String publikId) {
		return getService().findEventByUserPublikId(publikId);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité Event
	*/
	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return getService().getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les éditions d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.Event> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
	}

	/**
	* Retourne les resultats possèdant en etiquette l'une appelation demandee
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.Event> getByTagsWithOrSelection(
		java.util.List<java.lang.String> tagLabels) {
		return getService().getByTagsWithOrSelection(tagLabels);
	}

	/**
	* Returns a range of all the events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of events
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.Event> getEvents(
		int start, int end) {
		return getService().getEvents(start, end);
	}

	/**
	* Returns all the events matching the UUID and company.
	*
	* @param uuid the UUID of the events
	* @param companyId the primary key of the company
	* @return the matching events, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.Event> getEventsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getEventsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of events matching the UUID and company.
	*
	* @param uuid the UUID of the events
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching events, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.Event> getEventsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.agenda.model.Event> orderByComparator) {
		return getService()
				   .getEventsByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	public static java.util.List<eu.strasbourg.service.agenda.model.Event> getManifestationEvents(
		long manifestationId) {
		return getService().getManifestationEvents(manifestationId);
	}

	public static java.util.List<eu.strasbourg.service.agenda.model.Event> getManifestationEvents(
		long manifestationId, int start, int end) {
		return getService().getManifestationEvents(manifestationId, start, end);
	}

	public static java.util.List<eu.strasbourg.service.agenda.model.Event> getManifestationEvents(
		long manifestationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.agenda.model.Event> orderByComparator) {
		return getService()
				   .getManifestationEvents(manifestationId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Compte de la recherche par mots-clés
	*/
	public static long findByKeywordCount(java.lang.String keyword, long groupId) {
		return getService().findByKeywordCount(keyword, groupId);
	}

	/**
	* Returns the manifestationIds of the manifestations associated with the event.
	*
	* @param eventId the eventId of the event
	* @return long[] the manifestationIds of manifestations associated with the event
	*/
	public static long[] getManifestationPrimaryKeys(long eventId) {
		return getService().getManifestationPrimaryKeys(eventId);
	}

	public static void addManifestationEvent(long manifestationId,
		eu.strasbourg.service.agenda.model.Event event) {
		getService().addManifestationEvent(manifestationId, event);
	}

	public static void addManifestationEvent(long manifestationId, long eventId) {
		getService().addManifestationEvent(manifestationId, eventId);
	}

	public static void addManifestationEvents(long manifestationId,
		java.util.List<eu.strasbourg.service.agenda.model.Event> events) {
		getService().addManifestationEvents(manifestationId, events);
	}

	public static void addManifestationEvents(long manifestationId,
		long[] eventIds) {
		getService().addManifestationEvents(manifestationId, eventIds);
	}

	/**
	* Modifie le statut de tous les events au statut "SCHEDULED" qui ont une
	* date de publication dans le futur.
	*/
	public static void checkEvents()
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().checkEvents();
	}

	public static void clearManifestationEvents(long manifestationId) {
		getService().clearManifestationEvents(manifestationId);
	}

	public static void deleteManifestationEvent(long manifestationId,
		eu.strasbourg.service.agenda.model.Event event) {
		getService().deleteManifestationEvent(manifestationId, event);
	}

	public static void deleteManifestationEvent(long manifestationId,
		long eventId) {
		getService().deleteManifestationEvent(manifestationId, eventId);
	}

	public static void deleteManifestationEvents(long manifestationId,
		java.util.List<eu.strasbourg.service.agenda.model.Event> events) {
		getService().deleteManifestationEvents(manifestationId, events);
	}

	public static void deleteManifestationEvents(long manifestationId,
		long[] eventIds) {
		getService().deleteManifestationEvents(manifestationId, eventIds);
	}

	/**
	* Supprime les événements dépubliés depuis au moins un mois
	*/
	public static void deleteOldUnpublishedEvents()
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteOldUnpublishedEvents();
	}

	public static void setManifestationEvents(long manifestationId,
		long[] eventIds) {
		getService().setManifestationEvents(manifestationId, eventIds);
	}

	/**
	* Dépublie les événements dont la dernière date de fin est dépassée
	*/
	public static void unpublishPastEvents()
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().unpublishPastEvents();
	}

	/**
	* Met à jour le statut de l'édition "manuellement" (pas via le workflow)
	*/
	public static void updateStatus(
		eu.strasbourg.service.agenda.model.Event event, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateStatus(event, status);
	}

	public static EventLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EventLocalService, EventLocalService> _serviceTracker =
		ServiceTrackerFactory.open(EventLocalService.class);
}