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
import java.util.Map;

import eu.strasbourg.service.gtfs.model.Stop;
import eu.strasbourg.service.gtfs.service.base.StopLocalServiceBaseImpl;
import eu.strasbourg.utils.models.StopsGTFS;

/**
 * The implementation of the stop local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.gtfs.service.StopLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see StopLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.StopLocalServiceUtil
 */
public class StopLocalServiceImpl extends StopLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.gtfs.service.StopLocalServiceUtil} to access the stop local service.
	 */
	
	/**
	 * Crée une agence vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Stop createStop(ServiceContext sc) throws PortalException {
		long pk = counterLocalService.increment();
		Stop stop = this.stopLocalService.createStop(pk);

		return stop;
	}
	
	/**
	 * Crée un arret à partir d'une entrée GTFS
	 */
	@Override
	public Stop createStopFromGTFS(StopsGTFS entry) throws PortalException {
		long pk = counterLocalService.increment();
		Stop stop = this.stopLocalService.createStop(pk);
		
		stop.setStop_id(entry.getStop_id());
		stop.setStop_code(entry.getStop_code());
		stop.setStop_lat(Double.toString(entry.getStop_lat()));
		stop.setStop_lon(Double.toString(entry.getStop_lon()));
		stop.setStop_name(entry.getStop_name());
		stop.setStop_url(entry.getStop_url());
		stop.setStop_desc(entry.getStop_desc());
		
		
		stop = this.stopLocalService.updateStop(stop);

		return stop;
	}
	
	/**
	 * Met à jour un Stop et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public Stop updateStop(Stop stop, ServiceContext sc) throws PortalException {
		stop = this.stopLocalService.updateStop(stop);

		return stop;
	}
	
	/**
	 * Supprime un Stop
	 */
	@Override
	public Stop removeStop(long stopId) throws PortalException {
		Stop stop = this.stopPersistence.remove(stopId);

		return stop;
	}
	
	/**
	 * Supprime toutes les arrets
	 */
	@Override
	public void removeAllStops() throws PortalException {
		this.stopPersistence.removeAll();
	}
	
	/**
	 * Import des arrets sous le format de données GTFS
	 */
	@Override
	public void importFromGTFS(Map<String, StopsGTFS> data) throws PortalException {
		// Flush de la table avant incorporation des nouvelles données
		this.removeAllStops();
		
		for (Map.Entry<String, StopsGTFS> mapEntry : data.entrySet()) {
			this.createStopFromGTFS(mapEntry.getValue());
		}
	}
	
}