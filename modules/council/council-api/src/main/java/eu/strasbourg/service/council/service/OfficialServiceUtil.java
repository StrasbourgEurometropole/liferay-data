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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Official. This utility wraps
 * {@link eu.strasbourg.service.council.service.impl.OfficialServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see OfficialService
 * @see eu.strasbourg.service.council.service.base.OfficialServiceBaseImpl
 * @see eu.strasbourg.service.council.service.impl.OfficialServiceImpl
 * @generated
 */
@ProviderType
public class OfficialServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.council.service.impl.OfficialServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Recherche d'élu pour l'autocompletion
	*
	* @param fullName Nom, prénom ou les deux de l'élu à trouver
	* @param type Type de l'élu recherché (peut être vide)
	* @param removedOfficialId ID de l'élu à retirer de la liste des résultats (0 si non-utilisé)
	* @param groupId Site sur lequel cherchés
	* @return Liste des élus au format JSON
	*/
	public static com.liferay.portal.kernel.json.JSONArray getOfficialByFullNameAndType(
		java.lang.String fullName, java.lang.String type,
		long removedOfficialId, long groupId) {
		return getService()
				   .getOfficialByFullNameAndType(fullName, type,
			removedOfficialId, groupId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static OfficialService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OfficialService, OfficialService> _serviceTracker =
		ServiceTrackerFactory.open(OfficialService.class);
}