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
 * Provides a wrapper for {@link LigneService}.
 *
 * @author Cedric Henry
 * @see LigneService
 * @generated
 */
@ProviderType
public class LigneServiceWrapper implements LigneService,
	ServiceWrapper<LigneService> {
	public LigneServiceWrapper(LigneService ligneService) {
		_ligneService = ligneService;
	}

	/**
	* Recuperer les couleurs des lignes
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONArray getLigneColors() {
		return _ligneService.getLigneColors();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ligneService.getOSGiServiceIdentifier();
	}

	/**
	* Recuperer les couleurs des lignes pour freemarker
	*/
	@Override
	public java.util.Map<java.lang.String, java.lang.String[]> getLigneColorsFreemarker() {
		return _ligneService.getLigneColorsFreemarker();
	}

	@Override
	public LigneService getWrappedService() {
		return _ligneService;
	}

	@Override
	public void setWrappedService(LigneService ligneService) {
		_ligneService = ligneService;
	}

	private LigneService _ligneService;
}