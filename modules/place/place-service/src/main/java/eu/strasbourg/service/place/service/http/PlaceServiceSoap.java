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

package eu.strasbourg.service.place.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import eu.strasbourg.service.place.service.PlaceServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>PlaceServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>eu.strasbourg.service.place.model.PlaceSoap</code>. If the method in the
 * service utility returns a
 * <code>eu.strasbourg.service.place.model.Place</code>, that is translated to a
 * <code>eu.strasbourg.service.place.model.PlaceSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see PlaceServiceHttp
 * @generated
 */
@ProviderType
public class PlaceServiceSoap {

	public static String getPlaces() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue =
				PlaceServiceUtil.getPlaces();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static String getPlaceById(long id) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				PlaceServiceUtil.getPlaceById(id);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static String getPlaceByIdSIG(String sigId) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				PlaceServiceUtil.getPlaceByIdSIG(sigId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static String getPlacesByType(String typeId) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue =
				PlaceServiceUtil.getPlacesByType(typeId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static String getPlacesByTypes(java.util.List<String> typesId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONArray returnValue =
				PlaceServiceUtil.getPlacesByTypes(typesId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static String getPlacesByTerritory(String territoryId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONArray returnValue =
				PlaceServiceUtil.getPlacesByTerritory(territoryId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static String getPlacesByNameAndLanguage(
			String name, String language)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONArray returnValue =
				PlaceServiceUtil.getPlacesByNameAndLanguage(name, language);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static String getPlacesByTerritoryAndType(
			String territoryId, String typeId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONArray returnValue =
				PlaceServiceUtil.getPlacesByTerritoryAndType(
					territoryId, typeId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static String getTypes() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue =
				PlaceServiceUtil.getTypes();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static String getEquipments() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue =
				PlaceServiceUtil.getEquipments();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static String getRealtime() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				PlaceServiceUtil.getRealtime();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	 * Retourne le géoJSON des lieux
	 */
	public static String getPlacesGeoJSON() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				PlaceServiceUtil.getPlacesGeoJSON();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PlaceServiceSoap.class);

}