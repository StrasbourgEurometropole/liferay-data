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

package eu.strasbourg.service.gtfs.service.impl;

import com.liferay.portal.kernel.json.JSONArray;

import eu.strasbourg.service.gtfs.service.base.ArretServiceBaseImpl;
import eu.strasbourg.service.gtfs.utils.CTSService;

/**
 * The implementation of the arret remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.gtfs.service.ArretService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Cedric Henry
 * @see ArretServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.ArretServiceUtil
 */
public class ArretServiceImpl extends ArretServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.gtfs.service.ArretServiceUtil} to access the arret remote service.
	 */
	
	/**
	 * Recuperer les donnees temps real de la CTS pour un arret
	 * @param stopCode code SMS de l'arret (ex: "275c" pour l'arret de tram Homme de fer)
	 */
	@Override
	public JSONArray getArretRealTime(String stopCode) {
		return CTSService.stopMonitoring(stopCode);
	}
	
}