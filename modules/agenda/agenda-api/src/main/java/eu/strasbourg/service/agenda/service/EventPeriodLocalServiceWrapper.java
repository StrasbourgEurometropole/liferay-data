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
 * Provides a wrapper for {@link EventPeriodLocalService}.
 *
 * @author BenjaminBini
 * @see EventPeriodLocalService
 * @generated
 */
@ProviderType
public class EventPeriodLocalServiceWrapper implements EventPeriodLocalService,
	ServiceWrapper<EventPeriodLocalService> {
	public EventPeriodLocalServiceWrapper(
		EventPeriodLocalService eventPeriodLocalService) {
		_eventPeriodLocalService = eventPeriodLocalService;
	}

	/**
	* Vérifie qu'une liste de période ne contient pas de périodes qui se
	* chevauchent
	*/
	@Override
	public boolean checkForOverlappingPeriods(
		java.util.List<eu.strasbourg.service.agenda.model.EventPeriod> periods) {
		return _eventPeriodLocalService.checkForOverlappingPeriods(periods);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _eventPeriodLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _eventPeriodLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _eventPeriodLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventPeriodLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventPeriodLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the event period to the database. Also notifies the appropriate model listeners.
	*
	* @param eventPeriod the event period
	* @return the event period that was added
	*/
	@Override
	public eu.strasbourg.service.agenda.model.EventPeriod addEventPeriod(
		eu.strasbourg.service.agenda.model.EventPeriod eventPeriod) {
		return _eventPeriodLocalService.addEventPeriod(eventPeriod);
	}

	/**
	* Crée une édition vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.agenda.model.EventPeriod createEventPeriod()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventPeriodLocalService.createEventPeriod();
	}

	/**
	* Creates a new event period with the primary key. Does not add the event period to the database.
	*
	* @param eventPeriodId the primary key for the new event period
	* @return the new event period
	*/
	@Override
	public eu.strasbourg.service.agenda.model.EventPeriod createEventPeriod(
		long eventPeriodId) {
		return _eventPeriodLocalService.createEventPeriod(eventPeriodId);
	}

	/**
	* Deletes the event period from the database. Also notifies the appropriate model listeners.
	*
	* @param eventPeriod the event period
	* @return the event period that was removed
	*/
	@Override
	public eu.strasbourg.service.agenda.model.EventPeriod deleteEventPeriod(
		eu.strasbourg.service.agenda.model.EventPeriod eventPeriod) {
		return _eventPeriodLocalService.deleteEventPeriod(eventPeriod);
	}

	/**
	* Deletes the event period with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventPeriodId the primary key of the event period
	* @return the event period that was removed
	* @throws PortalException if a event period with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.EventPeriod deleteEventPeriod(
		long eventPeriodId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventPeriodLocalService.deleteEventPeriod(eventPeriodId);
	}

	@Override
	public eu.strasbourg.service.agenda.model.EventPeriod fetchEventPeriod(
		long eventPeriodId) {
		return _eventPeriodLocalService.fetchEventPeriod(eventPeriodId);
	}

	/**
	* Returns the event period with the primary key.
	*
	* @param eventPeriodId the primary key of the event period
	* @return the event period
	* @throws PortalException if a event period with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.EventPeriod getEventPeriod(
		long eventPeriodId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventPeriodLocalService.getEventPeriod(eventPeriodId);
	}

	/**
	* Updates the event period in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param eventPeriod the event period
	* @return the event period that was updated
	*/
	@Override
	public eu.strasbourg.service.agenda.model.EventPeriod updateEventPeriod(
		eu.strasbourg.service.agenda.model.EventPeriod eventPeriod) {
		return _eventPeriodLocalService.updateEventPeriod(eventPeriod);
	}

	/**
	* Returns the number of event periods.
	*
	* @return the number of event periods
	*/
	@Override
	public int getEventPeriodsCount() {
		return _eventPeriodLocalService.getEventPeriodsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _eventPeriodLocalService.getOSGiServiceIdentifier();
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
		return _eventPeriodLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.EventPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _eventPeriodLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.EventPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _eventPeriodLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Retourne les périodes d'un événement de campagne
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.EventPeriod> getByCampaignEventId(
		long campaignEventId) {
		return _eventPeriodLocalService.getByCampaignEventId(campaignEventId);
	}

	/**
	* Retourne les périodes d'un événement
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.EventPeriod> getByEventId(
		long eventId) {
		return _eventPeriodLocalService.getByEventId(eventId);
	}

	/**
	* Returns a range of all the event periods.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.EventPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of event periods
	* @param end the upper bound of the range of event periods (not inclusive)
	* @return the range of event periods
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.EventPeriod> getEventPeriods(
		int start, int end) {
		return _eventPeriodLocalService.getEventPeriods(start, end);
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
		return _eventPeriodLocalService.dynamicQueryCount(dynamicQuery);
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
		return _eventPeriodLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public EventPeriodLocalService getWrappedService() {
		return _eventPeriodLocalService;
	}

	@Override
	public void setWrappedService(
		EventPeriodLocalService eventPeriodLocalService) {
		_eventPeriodLocalService = eventPeriodLocalService;
	}

	private EventPeriodLocalService _eventPeriodLocalService;
}