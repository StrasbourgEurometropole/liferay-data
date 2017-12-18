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
 * {@link PlaceServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link eu.strasbourg.service.place.model.PlaceSoap}.
 * If the method in the service utility returns a
 * {@link eu.strasbourg.service.place.model.Place}, that is translated to a
 * {@link eu.strasbourg.service.place.model.PlaceSoap}. Methods that SOAP cannot
 * safely wire are skipped.
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
 * @see eu.strasbourg.service.place.model.PlaceSoap
 * @see PlaceServiceUtil
 * @generated
 */
@ProviderType
public class PlaceServiceSoap {
	public static java.lang.String getPlaces() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = PlaceServiceUtil.getPlaces();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getPlaceById(long id)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = PlaceServiceUtil.getPlaceById(id);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getPlaceByIdSIG(java.lang.String sigId)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = PlaceServiceUtil.getPlaceByIdSIG(sigId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getPlacesByType(java.lang.String typeId)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = PlaceServiceUtil.getPlacesByType(typeId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getPlacesByTerritory(
		java.lang.String territoryId) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = PlaceServiceUtil.getPlacesByTerritory(territoryId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getPlacesByNameAndLanguage(
		java.lang.String name, java.lang.String language)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = PlaceServiceUtil.getPlacesByNameAndLanguage(name,
					language);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getPlacesByTerritoryAndType(
		java.lang.String territoryId, java.lang.String typeId)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue = PlaceServiceUtil.getPlacesByTerritoryAndType(territoryId,
					typeId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Retourne l'horrible ancien web service LR6
	*/
	public static java.lang.String getLegacyJSON() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = PlaceServiceUtil.getLegacyJSON();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PlaceServiceSoap.class);
}