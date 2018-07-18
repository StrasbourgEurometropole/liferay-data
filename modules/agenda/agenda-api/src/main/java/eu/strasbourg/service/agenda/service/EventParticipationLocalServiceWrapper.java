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
 * Provides a wrapper for {@link EventParticipationLocalService}.
 *
 * @author BenjaminBini
 * @see EventParticipationLocalService
 * @generated
 */
@ProviderType
public class EventParticipationLocalServiceWrapper
	implements EventParticipationLocalService,
		ServiceWrapper<EventParticipationLocalService> {
	public EventParticipationLocalServiceWrapper(
		EventParticipationLocalService eventParticipationLocalService) {
		_eventParticipationLocalService = eventParticipationLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _eventParticipationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _eventParticipationLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _eventParticipationLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventParticipationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventParticipationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the event participation to the database. Also notifies the appropriate model listeners.
	*
	* @param eventParticipation the event participation
	* @return the event participation that was added
	*/
	@Override
	public eu.strasbourg.service.agenda.model.EventParticipation addEventParticipation(
		eu.strasbourg.service.agenda.model.EventParticipation eventParticipation) {
		return _eventParticipationLocalService.addEventParticipation(eventParticipation);
	}

	/**
	* Cree une nouvelle participation a un evenement
	*/
	@Override
	public eu.strasbourg.service.agenda.model.EventParticipation createEventParticipation() {
		return _eventParticipationLocalService.createEventParticipation();
	}

	/**
	* Creates a new event participation with the primary key. Does not add the event participation to the database.
	*
	* @param eventParticipationId the primary key for the new event participation
	* @return the new event participation
	*/
	@Override
	public eu.strasbourg.service.agenda.model.EventParticipation createEventParticipation(
		long eventParticipationId) {
		return _eventParticipationLocalService.createEventParticipation(eventParticipationId);
	}

	/**
	* Deletes the event participation from the database. Also notifies the appropriate model listeners.
	*
	* @param eventParticipation the event participation
	* @return the event participation that was removed
	*/
	@Override
	public eu.strasbourg.service.agenda.model.EventParticipation deleteEventParticipation(
		eu.strasbourg.service.agenda.model.EventParticipation eventParticipation) {
		return _eventParticipationLocalService.deleteEventParticipation(eventParticipation);
	}

	/**
	* Deletes the event participation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventParticipationId the primary key of the event participation
	* @return the event participation that was removed
	* @throws PortalException if a event participation with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.EventParticipation deleteEventParticipation(
		long eventParticipationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventParticipationLocalService.deleteEventParticipation(eventParticipationId);
	}

	@Override
	public eu.strasbourg.service.agenda.model.EventParticipation fetchEventParticipation(
		long eventParticipationId) {
		return _eventParticipationLocalService.fetchEventParticipation(eventParticipationId);
	}

	/**
	* Returns the event participation with the primary key.
	*
	* @param eventParticipationId the primary key of the event participation
	* @return the event participation
	* @throws PortalException if a event participation with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.EventParticipation getEventParticipation(
		long eventParticipationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventParticipationLocalService.getEventParticipation(eventParticipationId);
	}

	/**
	* Updates the event participation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param eventParticipation the event participation
	* @return the event participation that was updated
	*/
	@Override
	public eu.strasbourg.service.agenda.model.EventParticipation updateEventParticipation(
		eu.strasbourg.service.agenda.model.EventParticipation eventParticipation) {
		return _eventParticipationLocalService.updateEventParticipation(eventParticipation);
	}

	/**
	* Returns the number of event participations.
	*
	* @return the number of event participations
	*/
	@Override
	public int getEventParticipationsCount() {
		return _eventParticipationLocalService.getEventParticipationsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _eventParticipationLocalService.getOSGiServiceIdentifier();
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
		return _eventParticipationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _eventParticipationLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _eventParticipationLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Retourne la liste des likes/dislikes d'un evenement
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.EventParticipation> getByEventId(
		long eventId) {
		return _eventParticipationLocalService.getByEventId(eventId);
	}

	/**
	* Retourne la liste des likes/dislikes d'un utilisateur
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.EventParticipation> getByPublikUser(
		java.lang.String publikUserId) {
		return _eventParticipationLocalService.getByPublikUser(publikUserId);
	}

	/**
	* Returns a range of all the event participations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.EventParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of event participations
	* @param end the upper bound of the range of event participations (not inclusive)
	* @return the range of event participations
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.EventParticipation> getEventParticipations(
		int start, int end) {
		return _eventParticipationLocalService.getEventParticipations(start, end);
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
		return _eventParticipationLocalService.dynamicQueryCount(dynamicQuery);
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
		return _eventParticipationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public EventParticipationLocalService getWrappedService() {
		return _eventParticipationLocalService;
	}

	@Override
	public void setWrappedService(
		EventParticipationLocalService eventParticipationLocalService) {
		_eventParticipationLocalService = eventParticipationLocalService;
	}

	private EventParticipationLocalService _eventParticipationLocalService;
}