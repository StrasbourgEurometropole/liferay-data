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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.IOException;

import eu.strasbourg.service.gtfs.model.StopTime;
import eu.strasbourg.service.gtfs.service.base.StopTimeLocalServiceBaseImpl;

/**
 * The implementation of the stop time local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.gtfs.service.StopTimeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see StopTimeLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.StopTimeLocalServiceUtil
 */
public class StopTimeLocalServiceImpl extends StopTimeLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.gtfs.service.StopTimeLocalServiceUtil} to access the stop time local service.
	 */
	
	/**
	 * Crée un StopTime vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public StopTime createStopTime(ServiceContext sc) throws PortalException {
		long pk = counterLocalService.increment();
		StopTime stopTime = this.stopTimeLocalService.createStopTime(pk);

		return stopTime;
	}
	
	/**
	 * Met à jour un StopTime et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public StopTime updateStopTime(StopTime stopTime, ServiceContext sc) throws PortalException {
		stopTime = this.stopTimeLocalService.updateStopTime(stopTime);

		return stopTime;
	}
	
	/**
	 * Supprime un StopTime
	 */
	@Override
	public StopTime removeStopTime(long stopTimeId) throws PortalException {
		StopTime stopTime = this.stopTimePersistence.remove(stopTimeId);

		return stopTime;
	}
	
	/**
	 * Supprime toutes le StopTime
	 */
	@Override
	public void removeAllStopTime() throws PortalException {
		this.stopTimePersistence.removeAll();
	}
	
}