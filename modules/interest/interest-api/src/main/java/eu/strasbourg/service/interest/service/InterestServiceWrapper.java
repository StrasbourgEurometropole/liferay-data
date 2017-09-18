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

package eu.strasbourg.service.interest.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link InterestService}.
 *
 * @author BenjaminBini
 * @see InterestService
 * @generated
 */
@ProviderType
public class InterestServiceWrapper implements InterestService,
	ServiceWrapper<InterestService> {
	public InterestServiceWrapper(InterestService interestService) {
		_interestService = interestService;
	}

	/**
	* Retourne la liste de tous les centres d'intérêt
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject getInterests() {
		return _interestService.getInterests();
	}

	/**
	* Retourne la liste des intérêts de l'utilisateur ayant l'id (publik) passé
	* en paramètre
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject getUserInterests(
		java.lang.String userId) {
		return _interestService.getUserInterests(userId);
	}

	/**
	* Modifie les intérêts de l'utilisateur ayant l'id (publik) passé en
	* paramètre
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject setUserInterests(
		java.lang.String userId, java.lang.String interestIds) {
		return _interestService.setUserInterests(userId, interestIds);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _interestService.getOSGiServiceIdentifier();
	}

	@Override
	public InterestService getWrappedService() {
		return _interestService;
	}

	@Override
	public void setWrappedService(InterestService interestService) {
		_interestService = interestService;
	}

	private InterestService _interestService;
}