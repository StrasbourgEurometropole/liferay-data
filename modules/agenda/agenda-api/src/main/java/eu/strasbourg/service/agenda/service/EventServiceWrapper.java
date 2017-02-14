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
 * Provides a wrapper for {@link EventService}.
 *
 * @author BenjaminBini
 * @see EventService
 * @generated
 */
@ProviderType
public class EventServiceWrapper implements EventService,
	ServiceWrapper<EventService> {
	public EventServiceWrapper(EventService eventService) {
		_eventService = eventService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getCategories()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventService.getCategories();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getEvents()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventService.getEvents();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getEventsByCategory(
		long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventService.getEventsByCategory(categoryId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getEventsByDate(
		java.lang.String date)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventService.getEventsByDate(date);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getEventsByLanguage(
		java.lang.String language)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventService.getEventsByLanguage(language);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getEventsByPlace(
		java.lang.String placeSIGId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventService.getEventsByPlace(placeSIGId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getPublics()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventService.getPublics();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getServices()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventService.getServices();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getThemes()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventService.getThemes();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getTypes()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventService.getTypes();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getCategory(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventService.getCategory(id);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getEvent(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eventService.getEvent(id);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _eventService.getOSGiServiceIdentifier();
	}

	@Override
	public java.lang.String helloWorld() {
		return _eventService.helloWorld();
	}

	@Override
	public EventService getWrappedService() {
		return _eventService;
	}

	@Override
	public void setWrappedService(EventService eventService) {
		_eventService = eventService;
	}

	private EventService _eventService;
}