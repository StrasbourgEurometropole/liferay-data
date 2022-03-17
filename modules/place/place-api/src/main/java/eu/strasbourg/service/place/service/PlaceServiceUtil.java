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

package eu.strasbourg.service.place.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Place. This utility wraps
 * <code>eu.strasbourg.service.place.service.impl.PlaceServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Angelique Zunino Champougny
 * @see PlaceService
 * @generated
 */
@ProviderType
public class PlaceServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.place.service.impl.PlaceServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.json.JSONArray getEquipments()
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getEquipments();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.json.JSONObject getPlaceById(
		long id) {

		return getService().getPlaceById(id);
	}

	public static com.liferay.portal.kernel.json.JSONObject getPlaceByIdSIG(
		String sigId) {

		return getService().getPlaceByIdSIG(sigId);
	}

	public static com.liferay.portal.kernel.json.JSONArray getPlaces() {
		return getService().getPlaces();
	}

	public static com.liferay.portal.kernel.json.JSONArray
		getPlacesByNameAndLanguage(String name, String language) {

		return getService().getPlacesByNameAndLanguage(name, language);
	}

	public static com.liferay.portal.kernel.json.JSONArray getPlacesByTerritory(
			String territoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPlacesByTerritory(territoryId);
	}

	public static com.liferay.portal.kernel.json.JSONArray
			getPlacesByTerritoryAndType(String territoryId, String typeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPlacesByTerritoryAndType(territoryId, typeId);
	}

	public static com.liferay.portal.kernel.json.JSONArray getPlacesByType(
			String typeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPlacesByType(typeId);
	}

	public static com.liferay.portal.kernel.json.JSONArray getPlacesByTypes(
			java.util.List<String> typesId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPlacesByTypes(typesId);
	}

	/**
	 * Retourne le géoJSON des lieux
	 */
	public static com.liferay.portal.kernel.json.JSONObject getPlacesGeoJSON() {
		return getService().getPlacesGeoJSON();
	}

	public static com.liferay.portal.kernel.json.JSONObject getRealtime() {
		return getService().getRealtime();
	}

	public static com.liferay.portal.kernel.json.JSONArray getTypes()
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTypes();
	}

	public static PlaceService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PlaceService, PlaceService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PlaceService.class);

		ServiceTracker<PlaceService, PlaceService> serviceTracker =
			new ServiceTracker<PlaceService, PlaceService>(
				bundle.getBundleContext(), PlaceService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}