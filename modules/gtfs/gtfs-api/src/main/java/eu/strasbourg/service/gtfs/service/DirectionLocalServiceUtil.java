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
 * Provides the local service utility for Direction. This utility wraps
 * {@link eu.strasbourg.service.gtfs.service.impl.DirectionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Cedric Henry
 * @see DirectionLocalService
 * @see eu.strasbourg.service.gtfs.service.base.DirectionLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.impl.DirectionLocalServiceImpl
 * @generated
 */
@ProviderType
public class DirectionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.gtfs.service.impl.DirectionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the direction to the database. Also notifies the appropriate model listeners.
	*
	* @param direction the direction
	* @return the direction that was added
	*/
	public static eu.strasbourg.service.gtfs.model.Direction addDirection(
		eu.strasbourg.service.gtfs.model.Direction direction) {
		return getService().addDirection(direction);
	}

	/**
	* Crée une entree avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.gtfs.model.Direction createDirection(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createDirection(sc);
	}

	/**
	* Creates a new direction with the primary key. Does not add the direction to the database.
	*
	* @param directionId the primary key for the new direction
	* @return the new direction
	*/
	public static eu.strasbourg.service.gtfs.model.Direction createDirection(
		long directionId) {
		return getService().createDirection(directionId);
	}

	/**
	* Deletes the direction from the database. Also notifies the appropriate model listeners.
	*
	* @param direction the direction
	* @return the direction that was removed
	*/
	public static eu.strasbourg.service.gtfs.model.Direction deleteDirection(
		eu.strasbourg.service.gtfs.model.Direction direction) {
		return getService().deleteDirection(direction);
	}

	/**
	* Deletes the direction with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param directionId the primary key of the direction
	* @return the direction that was removed
	* @throws PortalException if a direction with the primary key could not be found
	*/
	public static eu.strasbourg.service.gtfs.model.Direction deleteDirection(
		long directionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDirection(directionId);
	}

	public static eu.strasbourg.service.gtfs.model.Direction fetchDirection(
		long directionId) {
		return getService().fetchDirection(directionId);
	}

	/**
	* Returns the direction matching the UUID and group.
	*
	* @param uuid the direction's UUID
	* @param groupId the primary key of the group
	* @return the matching direction, or <code>null</code> if a matching direction could not be found
	*/
	public static eu.strasbourg.service.gtfs.model.Direction fetchDirectionByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchDirectionByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Retourne une direction via son tripId
	*/
	public static eu.strasbourg.service.gtfs.model.Direction getByTripId(
		java.lang.String tripId) {
		return getService().getByTripId(tripId);
	}

	/**
	* Returns the direction with the primary key.
	*
	* @param directionId the primary key of the direction
	* @return the direction
	* @throws PortalException if a direction with the primary key could not be found
	*/
	public static eu.strasbourg.service.gtfs.model.Direction getDirection(
		long directionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDirection(directionId);
	}

	/**
	* Returns the direction matching the UUID and group.
	*
	* @param uuid the direction's UUID
	* @param groupId the primary key of the group
	* @return the matching direction
	* @throws PortalException if a matching direction could not be found
	*/
	public static eu.strasbourg.service.gtfs.model.Direction getDirectionByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDirectionByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime l'entree
	*/
	public static eu.strasbourg.service.gtfs.model.Direction removeDirection(
		long directionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeDirection(directionId);
	}

	/**
	* Updates the direction in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param direction the direction
	* @return the direction that was updated
	*/
	public static eu.strasbourg.service.gtfs.model.Direction updateDirection(
		eu.strasbourg.service.gtfs.model.Direction direction) {
		return getService().updateDirection(direction);
	}

	/**
	* Met à jour une entree et l'enregistre en base de données
	*
	* @throws PortalException
	* @throws IOException
	*/
	public static eu.strasbourg.service.gtfs.model.Direction updateDirection(
		eu.strasbourg.service.gtfs.model.Direction direction,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateDirection(direction, sc);
	}

	/**
	* Returns the number of directions.
	*
	* @return the number of directions
	*/
	public static int getDirectionsCount() {
		return getService().getDirectionsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.DirectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.DirectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Retourne la liste de toutes les directions
	*/
	public static java.util.List<eu.strasbourg.service.gtfs.model.Direction> getAll() {
		return getService().getAll();
	}

	/**
	* Retourne toutes les entrees d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.gtfs.model.Direction> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
	}

	/**
	* Retourne toutes direction d'une ligne
	*/
	public static java.util.List<eu.strasbourg.service.gtfs.model.Direction> getByRouteId(
		java.lang.String routeId) {
		return getService().getByRouteId(routeId);
	}

	/**
	* Retourne toutes direction d'un arret
	*/
	public static java.util.List<eu.strasbourg.service.gtfs.model.Direction> getByStopId(
		java.lang.String stopId) {
		return getService().getByStopId(stopId);
	}

	/**
	* Returns a range of all the directions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.DirectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of directions
	* @param end the upper bound of the range of directions (not inclusive)
	* @return the range of directions
	*/
	public static java.util.List<eu.strasbourg.service.gtfs.model.Direction> getDirections(
		int start, int end) {
		return getService().getDirections(start, end);
	}

	/**
	* Returns all the directions matching the UUID and company.
	*
	* @param uuid the UUID of the directions
	* @param companyId the primary key of the company
	* @return the matching directions, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.gtfs.model.Direction> getDirectionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getDirectionsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of directions matching the UUID and company.
	*
	* @param uuid the UUID of the directions
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of directions
	* @param end the upper bound of the range of directions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching directions, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.gtfs.model.Direction> getDirectionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.gtfs.model.Direction> orderByComparator) {
		return getService()
				   .getDirectionsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Supprime les entree correspondants au routeId donnee
	*/
	public static java.util.List<eu.strasbourg.service.gtfs.model.Direction> removeByRouteId(
		java.lang.String routeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeByRouteId(routeId);
	}

	/**
	* Supprime les entree correspondants au stopId donnee
	*/
	public static java.util.List<eu.strasbourg.service.gtfs.model.Direction> removeByStopId(
		java.lang.String stopId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeByStopId(stopId);
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
	* Supprime les entrees
	*/
	public static void removeDirections(
		java.util.List<eu.strasbourg.service.gtfs.model.Direction> directions)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().removeDirections(directions);
	}

	/**
	* Met à jour les entree donnees
	*
	* @throws IOException
	*/
	public static void updateDirections(
		java.util.List<eu.strasbourg.service.gtfs.model.Direction> directions,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateDirections(directions, sc);
	}

	public static DirectionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DirectionLocalService, DirectionLocalService> _serviceTracker =
		ServiceTrackerFactory.open(DirectionLocalService.class);
}