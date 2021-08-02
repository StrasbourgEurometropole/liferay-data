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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlaceService}.
 *
 * @author Angelique Zunino Champougny
 * @see PlaceService
 * @generated
 */
@ProviderType
public class PlaceServiceWrapper
	implements PlaceService, ServiceWrapper<PlaceService> {

	public PlaceServiceWrapper(PlaceService placeService) {
		_placeService = placeService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _placeService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getPlaceById(long id)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _placeService.getPlaceById(id);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getPlaceByIdSIG(
			String sigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _placeService.getPlaceByIdSIG(sigId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getPlaces()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _placeService.getPlaces();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getPlacesByNameAndLanguage(
			String name, String language)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _placeService.getPlacesByNameAndLanguage(name, language);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getPlacesByTerritory(
			String territoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _placeService.getPlacesByTerritory(territoryId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getPlacesByTerritoryAndType(
			String territoryId, String typeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _placeService.getPlacesByTerritoryAndType(territoryId, typeId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getPlacesByType(
			String typeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _placeService.getPlacesByType(typeId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getPlacesByTypes(
			java.util.List<String> typesId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _placeService.getPlacesByTypes(typesId);
	}

	/**
	 * Retourne le géoJSON des lieux
	 */
	@Override
	public com.liferay.portal.kernel.json.JSONObject getPlacesGeoJSON()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _placeService.getPlacesGeoJSON();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getRealtime()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _placeService.getRealtime();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getTypes()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _placeService.getTypes();
	}

	@Override
	public PlaceService getWrappedService() {
		return _placeService;
	}

	@Override
	public void setWrappedService(PlaceService placeService) {
		_placeService = placeService;
	}

	private PlaceService _placeService;

}