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
 * Provides the remote service utility for EventParticipation. This utility wraps
 * {@link eu.strasbourg.service.agenda.service.impl.EventParticipationServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author BenjaminBini
 * @see EventParticipationService
 * @see eu.strasbourg.service.agenda.service.base.EventParticipationServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.impl.EventParticipationServiceImpl
 * @generated
 */
@ProviderType
public class EventParticipationServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.agenda.service.impl.EventParticipationServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Ajoute une participation à un utilisateur
	*/
	public static com.liferay.portal.kernel.json.JSONObject addEventParticipationLink(
		long eventId, long groupId) {
		return getService().addEventParticipationLink(eventId, groupId);
	}

	/**
	* Supprime une participation d'evenement d'un utilisateur
	*/
	public static com.liferay.portal.kernel.json.JSONObject deleteEventParticipationLink(
		long eventId) {
		return getService().deleteEventParticipationLink(eventId);
	}

	/**
	* Retourne les participation d'evenement d'un utilisateur
	*/
	public static com.liferay.portal.kernel.json.JSONObject getUserEventParticipations(
		java.lang.String userId) {
		return getService().getUserEventParticipations(userId);
	}

	/**
	* Verifie si l'utilisateur courant participe a l'evenement
	*/
	public static com.liferay.portal.kernel.json.JSONObject isUserParticipates(
		long eventId) {
		return getService().isUserParticipates(eventId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static EventParticipationService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EventParticipationService, EventParticipationService> _serviceTracker =
		ServiceTrackerFactory.open(EventParticipationService.class);
}