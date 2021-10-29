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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Event. This utility wraps
 * <code>eu.strasbourg.service.agenda.service.impl.EventServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author BenjaminBini
 * @see EventService
 * @generated
 */
@ProviderType
public class EventServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.agenda.service.impl.EventServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONArray getCategories()
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCategories();
	}

	public static com.liferay.portal.kernel.json.JSONObject getCategory(long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCategory(id);
	}

	public static com.liferay.portal.kernel.json.JSONObject getEvent(long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getEvent(id);
	}

	public static com.liferay.portal.kernel.json.JSONObject getEvents()
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getEvents();
	}

	public static com.liferay.portal.kernel.json.JSONObject getEventsByCategory(
			String categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getEventsByCategory(categoryId);
	}

	public static com.liferay.portal.kernel.json.JSONObject getEventsByDate(
			String date)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getEventsByDate(date);
	}

	public static com.liferay.portal.kernel.json.JSONObject getEventsByLanguage(
			String language)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getEventsByLanguage(language);
	}

	public static com.liferay.portal.kernel.json.JSONObject getEventsByPlace(
			String placeSIGId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getEventsByPlace(placeSIGId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.json.JSONArray getPublics()
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPublics();
	}

	public static com.liferay.portal.kernel.json.JSONArray getServices()
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getServices();
	}

	public static com.liferay.portal.kernel.json.JSONArray getSessions(
		long eventID) {

		return getService().getSessions(eventID);
	}

	public static com.liferay.portal.kernel.json.JSONArray getTerritories()
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTerritories();
	}

	public static com.liferay.portal.kernel.json.JSONArray getThemes()
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getThemes();
	}

	public static com.liferay.portal.kernel.json.JSONArray getTypes()
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTypes();
	}

	public static EventService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EventService, EventService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(EventService.class);

		ServiceTracker<EventService, EventService> serviceTracker =
			new ServiceTracker<EventService, EventService>(
				bundle.getBundleContext(), EventService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}