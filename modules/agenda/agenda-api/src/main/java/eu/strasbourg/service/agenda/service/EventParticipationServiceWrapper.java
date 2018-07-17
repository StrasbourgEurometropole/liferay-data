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
 * Provides a wrapper for {@link EventParticipationService}.
 *
 * @author BenjaminBini
 * @see EventParticipationService
 * @generated
 */
@ProviderType
public class EventParticipationServiceWrapper
	implements EventParticipationService,
		ServiceWrapper<EventParticipationService> {
	public EventParticipationServiceWrapper(
		EventParticipationService eventParticipationService) {
		_eventParticipationService = eventParticipationService;
	}

	/**
	* Ajoute une participation à un utilisateur
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject addEventParticipationLink(
		long eventId, long groupId) {
		return _eventParticipationService.addEventParticipationLink(eventId,
			groupId);
	}

	/**
	* Supprime une participation d'evenement d'un utilisateur
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject deleteEventParticipationLink(
		long eventId) {
		return _eventParticipationService.deleteEventParticipationLink(eventId);
	}

	/**
	* Retourne les participation d'evenement d'un utilisateur
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject getUserEventParticipations(
		java.lang.String userId) {
		return _eventParticipationService.getUserEventParticipations(userId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _eventParticipationService.getOSGiServiceIdentifier();
	}

	@Override
	public EventParticipationService getWrappedService() {
		return _eventParticipationService;
	}

	@Override
	public void setWrappedService(
		EventParticipationService eventParticipationService) {
		_eventParticipationService = eventParticipationService;
	}

	private EventParticipationService _eventParticipationService;
}