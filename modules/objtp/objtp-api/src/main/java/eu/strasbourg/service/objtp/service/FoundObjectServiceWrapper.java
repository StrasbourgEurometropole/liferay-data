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

package eu.strasbourg.service.objtp.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FoundObjectService}.
 *
 * @author JeremyZwickert
 * @see FoundObjectService
 * @generated
 */
@ProviderType
public class FoundObjectServiceWrapper implements FoundObjectService,
	ServiceWrapper<FoundObjectService> {
	public FoundObjectServiceWrapper(FoundObjectService foundObjectService) {
		_foundObjectService = foundObjectService;
	}

	/**
	* Retourne la liste des objets d'une catégorie
	*
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONArray getFoundObjectByCategoryCode(
		java.lang.String codeCategory)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _foundObjectService.getFoundObjectByCategoryCode(codeCategory);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _foundObjectService.getOSGiServiceIdentifier();
	}

	@Override
	public FoundObjectService getWrappedService() {
		return _foundObjectService;
	}

	@Override
	public void setWrappedService(FoundObjectService foundObjectService) {
		_foundObjectService = foundObjectService;
	}

	private FoundObjectService _foundObjectService;
}