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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for EventParticipation. This utility wraps
 * <code>eu.strasbourg.service.agenda.service.impl.EventParticipationServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author BenjaminBini
 * @see EventParticipationService
 * @generated
 */
public class EventParticipationServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.agenda.service.impl.EventParticipationServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Ajoute une participation à un utilisateur
	 */
	public static com.liferay.portal.kernel.json.JSONObject
		addEventParticipationLink(long eventId, long groupId) {

		return getService().addEventParticipationLink(eventId, groupId);
	}

	/**
	 * Supprime une participation d'evenement d'un utilisateur
	 */
	public static com.liferay.portal.kernel.json.JSONObject
		deleteEventParticipationLink(long eventId) {

		return getService().deleteEventParticipationLink(eventId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * Retourne les participation d'evenement d'un utilisateur
	 */
	public static com.liferay.portal.kernel.json.JSONObject
		getUserEventParticipations(String userId) {

		return getService().getUserEventParticipations(userId);
	}

	/**
	 * Verifie si l'utilisateur courant participe a l'evenement
	 */
	public static com.liferay.portal.kernel.json.JSONObject isUserParticipates(
		long eventId) {

		return getService().isUserParticipates(eventId);
	}

	public static EventParticipationService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<EventParticipationService, EventParticipationService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			EventParticipationService.class);

		ServiceTracker<EventParticipationService, EventParticipationService>
			serviceTracker =
				new ServiceTracker
					<EventParticipationService, EventParticipationService>(
						bundle.getBundleContext(),
						EventParticipationService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}