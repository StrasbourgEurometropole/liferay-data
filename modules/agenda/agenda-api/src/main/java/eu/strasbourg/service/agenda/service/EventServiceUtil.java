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
 * Provides the remote service utility for Event. This utility wraps
 * {@link eu.strasbourg.service.agenda.service.impl.EventServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author BenjaminBini
 * @see EventService
 * @see eu.strasbourg.service.agenda.service.base.EventServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.impl.EventServiceImpl
 * @generated
 */
@ProviderType
public class EventServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.agenda.service.impl.EventServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONArray getCategories()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCategories();
	}

	public static com.liferay.portal.kernel.json.JSONArray getEvents()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEvents();
	}

	public static com.liferay.portal.kernel.json.JSONArray getEventsByCategory(
		long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEventsByCategory(categoryId);
	}

	public static com.liferay.portal.kernel.json.JSONArray getEventsByDate(
		java.lang.String date)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEventsByDate(date);
	}

	public static com.liferay.portal.kernel.json.JSONArray getEventsByLanguage(
		java.lang.String language)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEventsByLanguage(language);
	}

	public static com.liferay.portal.kernel.json.JSONArray getEventsByPlace(
		java.lang.String placeSIGId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEventsByPlace(placeSIGId);
	}

	public static com.liferay.portal.kernel.json.JSONArray getPublics()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPublics();
	}

	public static com.liferay.portal.kernel.json.JSONArray getServices()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getServices();
	}

	public static com.liferay.portal.kernel.json.JSONArray getThemes()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getThemes();
	}

	public static com.liferay.portal.kernel.json.JSONArray getTypes()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTypes();
	}

	public static com.liferay.portal.kernel.json.JSONObject getCategory(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCategory(id);
	}

	public static com.liferay.portal.kernel.json.JSONObject getEvent(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEvent(id);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static EventService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EventService, EventService> _serviceTracker = ServiceTrackerFactory.open(EventService.class);
}