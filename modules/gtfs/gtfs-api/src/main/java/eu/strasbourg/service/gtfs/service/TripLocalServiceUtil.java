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

package eu.strasbourg.service.gtfs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Trip. This utility wraps
 * {@link eu.strasbourg.service.gtfs.service.impl.TripLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Cedric Henry
 * @see TripLocalService
 * @see eu.strasbourg.service.gtfs.service.base.TripLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.impl.TripLocalServiceImpl
 * @generated
 */
@ProviderType
public class TripLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.gtfs.service.impl.TripLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the trip to the database. Also notifies the appropriate model listeners.
	*
	* @param trip the trip
	* @return the trip that was added
	*/
	public static eu.strasbourg.service.gtfs.model.Trip addTrip(
		eu.strasbourg.service.gtfs.model.Trip trip) {
		return getService().addTrip(trip);
	}

	/**
	* Crée une agence vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.gtfs.model.Trip createTrip(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createTrip(sc);
	}

	/**
	* Creates a new trip with the primary key. Does not add the trip to the database.
	*
	* @param id the primary key for the new trip
	* @return the new trip
	*/
	public static eu.strasbourg.service.gtfs.model.Trip createTrip(long id) {
		return getService().createTrip(id);
	}

	/**
	* Crée un voyage à partir d'une entrée GTFS
	*/
	public static eu.strasbourg.service.gtfs.model.Trip createTripFromGTFS(
		eu.strasbourg.utils.models.TripsGTFS entry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createTripFromGTFS(entry);
	}

	/**
	* Deletes the trip from the database. Also notifies the appropriate model listeners.
	*
	* @param trip the trip
	* @return the trip that was removed
	*/
	public static eu.strasbourg.service.gtfs.model.Trip deleteTrip(
		eu.strasbourg.service.gtfs.model.Trip trip) {
		return getService().deleteTrip(trip);
	}

	/**
	* Deletes the trip with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the trip
	* @return the trip that was removed
	* @throws PortalException if a trip with the primary key could not be found
	*/
	public static eu.strasbourg.service.gtfs.model.Trip deleteTrip(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTrip(id);
	}

	public static eu.strasbourg.service.gtfs.model.Trip fetchTrip(long id) {
		return getService().fetchTrip(id);
	}

	/**
	* Returns the trip with the primary key.
	*
	* @param id the primary key of the trip
	* @return the trip
	* @throws PortalException if a trip with the primary key could not be found
	*/
	public static eu.strasbourg.service.gtfs.model.Trip getTrip(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTrip(id);
	}

	/**
	* Supprime une agence
	*/
	public static eu.strasbourg.service.gtfs.model.Trip removeTrip(long tripId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeTrip(tripId);
	}

	/**
	* Updates the trip in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trip the trip
	* @return the trip that was updated
	*/
	public static eu.strasbourg.service.gtfs.model.Trip updateTrip(
		eu.strasbourg.service.gtfs.model.Trip trip) {
		return getService().updateTrip(trip);
	}

	/**
	* Met à jour un Trip et l'enregistre en base de données
	*
	* @throws IOException
	*/
	public static eu.strasbourg.service.gtfs.model.Trip updateTrip(
		eu.strasbourg.service.gtfs.model.Trip trip,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateTrip(trip, sc);
	}

	/**
	* Returns the number of trips.
	*
	* @return the number of trips
	*/
	public static int getTripsCount() {
		return getService().getTripsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.TripModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.TripModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the trips.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.TripModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of trips
	* @param end the upper bound of the range of trips (not inclusive)
	* @return the range of trips
	*/
	public static java.util.List<eu.strasbourg.service.gtfs.model.Trip> getTrips(
		int start, int end) {
		return getService().getTrips(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Import des voyage sous le format de données GTFS
	*/
	public static void importFromGTFS(
		java.util.Map<java.lang.String, eu.strasbourg.utils.models.TripsGTFS> data)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().importFromGTFS(data);
	}

	/**
	* Supprime toutes les Trips
	*/
	public static void removeAllTrips()
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().removeAllTrips();
	}

	public static TripLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TripLocalService, TripLocalService> _serviceTracker =
		ServiceTrackerFactory.open(TripLocalService.class);
}