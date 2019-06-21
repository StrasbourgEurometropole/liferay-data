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

import eu.strasbourg.service.gtfs.model.Trip;
import eu.strasbourg.service.gtfs.service.base.TripLocalServiceBaseImpl;
import eu.strasbourg.utils.models.TripsGTFS;

/**
 * The implementation of the trip local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.gtfs.service.TripLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see TripLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.TripLocalServiceUtil
 */
public class TripLocalServiceImpl extends TripLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.gtfs.service.TripLocalServiceUtil} to access the trip local service.
	 */
	
	/**
	 * Crée une agence vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Trip createTrip(ServiceContext sc) throws PortalException {
		long pk = counterLocalService.increment();
		Trip trip = this.tripLocalService.createTrip(pk);

		return trip;
	}
	
	/**
	 * Crée un voyage à partir d'une entrée GTFS
	 */
	@Override
	public Trip createTripFromGTFS(TripsGTFS entry) throws PortalException {
		long pk = counterLocalService.increment();
		Trip trip = this.tripLocalService.createTrip(pk);
		
		trip.setRoute_id(entry.getRoute_id());
		trip.setService_id(entry.getService_id());
		trip.setTrip_id(entry.getTrip_id());
		trip.setTrip_headsign(entry.getTrip_headsign());
		trip.setDirection_id(entry.getDirection_id() == 1);
		trip.setBlock_id(entry.getBlock_id());
		
		trip = this.tripLocalService.updateTrip(trip);

		return trip;
	}
	
	/**
	 * Met à jour un Trip et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public Trip updateTrip(Trip trip, ServiceContext sc) throws PortalException {
		trip = this.tripLocalService.updateTrip(trip);

		return trip;
	}
	
	/**
	 * Supprime une agence
	 */
	@Override
	public Trip removeTrip(long tripId) throws PortalException {
		Trip trip = this.tripPersistence.remove(tripId);

		return trip;
	}
	
	/**
	 * Supprime toutes les Trips
	 */
	@Override
	public void removeAllTrips() throws PortalException {
		this.tripPersistence.removeAll();
	}
	
	/**
	 * Import des voyage sous le format de données GTFS
	 */
	@Override
	public void importFromGTFS(Map<String, TripsGTFS> data) throws PortalException {
		// Flush de la table avant incorporation des nouvelles données
		this.removeAllTrips();
		
		for (Map.Entry<String, TripsGTFS> mapEntry : data.entrySet()) {
			this.createTripFromGTFS(mapEntry.getValue());
		}
	}
	
}