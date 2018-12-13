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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EventLocalService}.
 *
 * @author BenjaminBini
 * @see EventLocalService
 * @generated
 */
@ProviderType
public class EventLocalServiceWrapper implements EventLocalService,
	ServiceWrapper<EventLocalService> {
	public EventLocalServiceWrapper(EventLocalService eventLocalService) {
		_eventLocalService = eventLocalService;
	}

	/**
	* Lance l'import des événements
	*
	* @throws IOException
	*/
	@Override
	public boolean doImport() throws java.io.IOException {
		return _eventLocalService.doImport();
	}

	@Override
	public boolean hasManifestationEvent(long manifestationId, long eventId) {
		return _eventLocalService.hasManifestationEvent(manifestationId, eventId);
	}

	@Override
	public boolean hasManifestationEvents(long manifestationId) {
		return _eventLocalService.hasManifestationEvents(manifestationId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _eventLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _eventLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _eventLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _eventLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Lance une recherche selon le searchContext
	*/
	@Override
	public com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return _eventLocalService.search(searchContext);
	}

	/**
	* Adds the event to the database. Also notifies the appropriate model listeners.
	*
	* @param event the event
	* @return the event that was added
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Event addEvent(
		eu.strasbourg.service.agenda.model.Event event) {
		return _eventLocalService.addEvent(event);
	}

	/**
	* Crée une édition vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Event createEvent(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventLocalService.createEvent(sc);
	}

	/**
	* Creates a new event with the primary key. Does not add the event to the database.
	*
	* @param eventId the primary key for the new event
	* @return the new event
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Event createEvent(long eventId) {
		return _eventLocalService.createEvent(eventId);
	}

	/**
	* Deletes the event from the database. Also notifies the appropriate model listeners.
	*
	* @param event the event
	* @return the event that was removed
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Event deleteEvent(
		eu.strasbourg.service.agenda.model.Event event) {
		return _eventLocalService.deleteEvent(event);
	}

	/**
	* Deletes the event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventId the primary key of the event
	* @return the event that was removed
	* @throws PortalException if a event with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Event deleteEvent(long eventId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventLocalService.deleteEvent(eventId);
	}

	@Override
	public eu.strasbourg.service.agenda.model.Event fetchEvent(long eventId) {
		return _eventLocalService.fetchEvent(eventId);
	}

	/**
	* Returns the event matching the UUID and group.
	*
	* @param uuid the event's UUID
	* @param groupId the primary key of the group
	* @return the matching event, or <code>null</code> if a matching event could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Event fetchEventByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _eventLocalService.fetchEventByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public eu.strasbourg.service.agenda.model.Event findBySourceAndIdSource(
		java.lang.String source, java.lang.String idSource) {
		return _eventLocalService.findBySourceAndIdSource(source, idSource);
	}

	/**
	* Returns the event with the primary key.
	*
	* @param eventId the primary key of the event
	* @return the event
	* @throws PortalException if a event with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Event getEvent(long eventId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventLocalService.getEvent(eventId);
	}

	/**
	* Returns the event matching the UUID and group.
	*
	* @param uuid the event's UUID
	* @param groupId the primary key of the group
	* @return the matching event
	* @throws PortalException if a matching event could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Event getEventByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventLocalService.getEventByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Delete an Event
	*
	* @param eventId
	The ID of the event to delete
	* @return The deleted Event
	* @throws PortalException
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Event removeEvent(long eventId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventLocalService.removeEvent(eventId);
	}

	/**
	* Updates the event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param event the event
	* @return the event that was updated
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Event updateEvent(
		eu.strasbourg.service.agenda.model.Event event) {
		return _eventLocalService.updateEvent(event);
	}

	/**
	* Met à jour une édition et l'enregistre en base de données
	*
	* @throws IOException
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Event updateEvent(
		eu.strasbourg.service.agenda.model.Event event,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException,
			java.io.IOException {
		return _eventLocalService.updateEvent(event, sc);
	}

	/**
	* Met à jour le statut de l'édition par le framework workflow
	*/
	@Override
	public eu.strasbourg.service.agenda.model.Event updateStatus(long userId,
		long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventLocalService.updateStatus(userId, entryId, status, sc,
			workflowContext);
	}

	/**
	* Returns the number of events.
	*
	* @return the number of events
	*/
	@Override
	public int getEventsCount() {
		return _eventLocalService.getEventsCount();
	}

	@Override
	public int getManifestationEventsCount(long manifestationId) {
		return _eventLocalService.getManifestationEventsCount(manifestationId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _eventLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _eventLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _eventLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _eventLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Lance une recherche par mots-clés
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Event> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _eventLocalService.findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Lance une recherche par placeSIGId
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Event> findByPlaceSIGId(
		java.lang.String placeSIGId) {
		return _eventLocalService.findByPlaceSIGId(placeSIGId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Event> findEventByUserPublikId(
		java.lang.String publikId) {
		return _eventLocalService.findEventByUserPublikId(publikId);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité Event
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _eventLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les éditions d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getByGroupId(
		long groupId) {
		return _eventLocalService.getByGroupId(groupId);
	}

	/**
	* Retourne les resultats possèdant en etiquette l'une appelation demandee
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getByTagsWithOrSelection(
		java.util.List<java.lang.String> tagLabels) {
		return _eventLocalService.getByTagsWithOrSelection(tagLabels);
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
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getEvents(
		int start, int end) {
		return _eventLocalService.getEvents(start, end);
	}

	/**
	* Returns all the events matching the UUID and company.
	*
	* @param uuid the UUID of the events
	* @param companyId the primary key of the company
	* @return the matching events, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getEventsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _eventLocalService.getEventsByUuidAndCompanyId(uuid, companyId);
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
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getEventsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.agenda.model.Event> orderByComparator) {
		return _eventLocalService.getEventsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getManifestationEvents(
		long manifestationId) {
		return _eventLocalService.getManifestationEvents(manifestationId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getManifestationEvents(
		long manifestationId, int start, int end) {
		return _eventLocalService.getManifestationEvents(manifestationId,
			start, end);
	}

	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getManifestationEvents(
		long manifestationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.agenda.model.Event> orderByComparator) {
		return _eventLocalService.getManifestationEvents(manifestationId,
			start, end, orderByComparator);
	}

	/**
	* Retourne les resultats publiés
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getPublished() {
		return _eventLocalService.getPublished();
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _eventLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _eventLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Compte de la recherche par mots-clés
	*/
	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _eventLocalService.findByKeywordCount(keyword, groupId);
	}

	/**
	* Returns the manifestationIds of the manifestations associated with the event.
	*
	* @param eventId the eventId of the event
	* @return long[] the manifestationIds of manifestations associated with the event
	*/
	@Override
	public long[] getManifestationPrimaryKeys(long eventId) {
		return _eventLocalService.getManifestationPrimaryKeys(eventId);
	}

	@Override
	public void addManifestationEvent(long manifestationId,
		eu.strasbourg.service.agenda.model.Event event) {
		_eventLocalService.addManifestationEvent(manifestationId, event);
	}

	@Override
	public void addManifestationEvent(long manifestationId, long eventId) {
		_eventLocalService.addManifestationEvent(manifestationId, eventId);
	}

	@Override
	public void addManifestationEvents(long manifestationId,
		java.util.List<eu.strasbourg.service.agenda.model.Event> events) {
		_eventLocalService.addManifestationEvents(manifestationId, events);
	}

	@Override
	public void addManifestationEvents(long manifestationId, long[] eventIds) {
		_eventLocalService.addManifestationEvents(manifestationId, eventIds);
	}

	/**
	* Modifie le statut de tous les events au statut "SCHEDULED" qui ont une
	* date de publication dans le futur.
	*/
	@Override
	public void checkEvents()
		throws com.liferay.portal.kernel.exception.PortalException {
		_eventLocalService.checkEvents();
	}

	@Override
	public void clearManifestationEvents(long manifestationId) {
		_eventLocalService.clearManifestationEvents(manifestationId);
	}

	@Override
	public void deleteManifestationEvent(long manifestationId,
		eu.strasbourg.service.agenda.model.Event event) {
		_eventLocalService.deleteManifestationEvent(manifestationId, event);
	}

	@Override
	public void deleteManifestationEvent(long manifestationId, long eventId) {
		_eventLocalService.deleteManifestationEvent(manifestationId, eventId);
	}

	@Override
	public void deleteManifestationEvents(long manifestationId,
		java.util.List<eu.strasbourg.service.agenda.model.Event> events) {
		_eventLocalService.deleteManifestationEvents(manifestationId, events);
	}

	@Override
	public void deleteManifestationEvents(long manifestationId, long[] eventIds) {
		_eventLocalService.deleteManifestationEvents(manifestationId, eventIds);
	}

	/**
	* Supprime les événements dépubliés depuis au moins un mois
	*/
	@Override
	public void deleteOldUnpublishedEvents()
		throws com.liferay.portal.kernel.exception.PortalException {
		_eventLocalService.deleteOldUnpublishedEvents();
	}

	@Override
	public void setManifestationEvents(long manifestationId, long[] eventIds) {
		_eventLocalService.setManifestationEvents(manifestationId, eventIds);
	}

	/**
	* Dépublie les événements dont la dernière date de fin est dépassée
	*/
	@Override
	public void unpublishPastEvents()
		throws com.liferay.portal.kernel.exception.PortalException {
		_eventLocalService.unpublishPastEvents();
	}

	/**
	* Met à jour le statut de l'édition "manuellement" (pas via le workflow)
	*/
	@Override
	public void updateStatus(eu.strasbourg.service.agenda.model.Event event,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		_eventLocalService.updateStatus(event, status);
	}

	@Override
	public EventLocalService getWrappedService() {
		return _eventLocalService;
	}

	@Override
	public void setWrappedService(EventLocalService eventLocalService) {
		_eventLocalService = eventLocalService;
	}

	private EventLocalService _eventLocalService;
}