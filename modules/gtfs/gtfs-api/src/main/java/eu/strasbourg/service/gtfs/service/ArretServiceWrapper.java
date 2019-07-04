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

package eu.strasbourg.service.gtfs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ArretService}.
 *
 * @author Cedric Henry
 * @see ArretService
 * @generated
 */
@ProviderType
public class ArretServiceWrapper implements ArretService,
	ServiceWrapper<ArretService> {
	public ArretServiceWrapper(ArretService arretService) {
		_arretService = arretService;
	}

	/**
	* Recuperer les donnees temps real de la CTS pour un arret
	*
	* @param stopCode code SMS de l'arret (ex: "275c" pour l'arret de tram Homme de fer)
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONArray getArretRealTime(
		java.lang.String stopCode) {
		return _arretService.getArretRealTime(stopCode);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _arretService.getOSGiServiceIdentifier();
	}

	@Override
	public ArretService getWrappedService() {
		return _arretService;
	}

	@Override
	public void setWrappedService(ArretService arretService) {
		_arretService = arretService;
	}

	private ArretService _arretService;
}