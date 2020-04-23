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

package eu.strasbourg.service.council.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OfficialService}.
 *
 * @author Brian Wing Shun Chan
 * @see OfficialService
 * @generated
 */
@ProviderType
public class OfficialServiceWrapper implements OfficialService,
	ServiceWrapper<OfficialService> {
	public OfficialServiceWrapper(OfficialService officialService) {
		_officialService = officialService;
	}

	/**
	* Recherche d'élu pour l'autocompletion
	*
	* @param fullName Nom, prénom ou les deux de l'élu à trouver
	* @param type Type de l'élu recherché (peut être vide)
	* @param removedOfficialId ID de l'élu à retirer de la liste des résultats (0 si non-utilisé)
	* @param groupId Site sur lequel cherchés
	* @return Liste des élus au format JSON
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONArray getOfficialByFullNameAndType(
		java.lang.String fullName, java.lang.String type,
		long removedOfficialId, long groupId) {
		return _officialService.getOfficialByFullNameAndType(fullName, type,
			removedOfficialId, groupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _officialService.getOSGiServiceIdentifier();
	}

	@Override
	public OfficialService getWrappedService() {
		return _officialService;
	}

	@Override
	public void setWrappedService(OfficialService officialService) {
		_officialService = officialService;
	}

	private OfficialService _officialService;
}