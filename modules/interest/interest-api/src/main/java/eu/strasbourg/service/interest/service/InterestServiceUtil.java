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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Interest. This utility wraps
 * {@link eu.strasbourg.service.interest.service.impl.InterestServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author BenjaminBini
 * @see InterestService
 * @see eu.strasbourg.service.interest.service.base.InterestServiceBaseImpl
 * @see eu.strasbourg.service.interest.service.impl.InterestServiceImpl
 * @generated
 */
@ProviderType
public class InterestServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.interest.service.impl.InterestServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Retourne la liste de tous les centres d'intérêt
	*/
	public static com.liferay.portal.kernel.json.JSONObject getInterests() {
		return getService().getInterests();
	}

	/**
	* Retourne la liste des intérêts de l'utilisateur ayant l'id (publik) passé
	* en paramètre
	*/
	public static com.liferay.portal.kernel.json.JSONObject getUserInterests(
		java.lang.String userId) {
		return getService().getUserInterests(userId);
	}

	/**
	* Modifie les intérêts de l'utilisateur ayant l'id (publik) passé en
	* paramètre
	*/
	public static com.liferay.portal.kernel.json.JSONObject setUserInterests(
		java.lang.String userId, java.lang.String interestIds) {
		return getService().setUserInterests(userId, interestIds);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static InterestService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<InterestService, InterestService> _serviceTracker =
		ServiceTrackerFactory.open(InterestService.class);
}