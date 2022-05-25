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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Official. This utility wraps
 * <code>eu.strasbourg.service.council.service.impl.OfficialServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see OfficialService
 * @generated
 */
public class OfficialServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.council.service.impl.OfficialServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Recherche des électeurs pour une session données groupés par statut de connexion et nom complet
	 *
	 * @param councilSessionId
	 * @param groupId          ID du site
	 * @return Tableaux des statuts possibles contenant la liste des électeurs assimilables auxdits statuts
	 */
	public static com.liferay.portal.kernel.json.JSONObject
		getOfficialByConnexionStatus(long councilSessionId, long groupId) {

		return getService().getOfficialByConnexionStatus(
			councilSessionId, groupId);
	}

	/**
	 * Recherche d'élu pour l'autocompletion
	 *
	 * @param fullName          Nom, prénom ou les deux de l'élu à trouver
	 * @param type              Type de l'élu recherché (peut être vide)
	 * @param removedOfficialId ID de l'élu à retirer de la liste des résultats (0 si non-utilisé)
	 * @param groupId           Site sur lequel cherchés
	 * @return Liste des élus au format JSON
	 */
	public static com.liferay.portal.kernel.json.JSONArray
		getOfficialByFullNameAndType(
			String fullName, String type, long removedOfficialId,
			long groupId) {

		return getService().getOfficialByFullNameAndType(
			fullName, type, removedOfficialId, groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static OfficialService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OfficialService, OfficialService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OfficialService.class);

		ServiceTracker<OfficialService, OfficialService> serviceTracker =
			new ServiceTracker<OfficialService, OfficialService>(
				bundle.getBundleContext(), OfficialService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}