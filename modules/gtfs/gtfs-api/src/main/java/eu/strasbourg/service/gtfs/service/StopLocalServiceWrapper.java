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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StopLocalService}.
 *
 * @author Cedric Henry
 * @see StopLocalService
 * @generated
 */
@ProviderType
public class StopLocalServiceWrapper implements StopLocalService,
	ServiceWrapper<StopLocalService> {
	public StopLocalServiceWrapper(StopLocalService stopLocalService) {
		_stopLocalService = stopLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stopLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stopLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stopLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stopLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stopLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the stop to the database. Also notifies the appropriate model listeners.
	*
	* @param stop the stop
	* @return the stop that was added
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Stop addStop(
		eu.strasbourg.service.gtfs.model.Stop stop) {
		return _stopLocalService.addStop(stop);
	}

	/**
	* Crée une agence vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Stop createStop(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stopLocalService.createStop(sc);
	}

	/**
	* Creates a new stop with the primary key. Does not add the stop to the database.
	*
	* @param id the primary key for the new stop
	* @return the new stop
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Stop createStop(long id) {
		return _stopLocalService.createStop(id);
	}

	/**
	* Crée un arret à partir d'une entrée GTFS
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Stop createStopFromGTFS(
		eu.strasbourg.utils.models.StopsGTFS entry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stopLocalService.createStopFromGTFS(entry);
	}

	/**
	* Deletes the stop from the database. Also notifies the appropriate model listeners.
	*
	* @param stop the stop
	* @return the stop that was removed
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Stop deleteStop(
		eu.strasbourg.service.gtfs.model.Stop stop) {
		return _stopLocalService.deleteStop(stop);
	}

	/**
	* Deletes the stop with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the stop
	* @return the stop that was removed
	* @throws PortalException if a stop with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Stop deleteStop(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stopLocalService.deleteStop(id);
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Stop fetchStop(long id) {
		return _stopLocalService.fetchStop(id);
	}

	/**
	* Recuperer un arret via son stopId
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Stop getByStopId(
		java.lang.String stopId) {
		return _stopLocalService.getByStopId(stopId);
	}

	/**
	* Returns the stop with the primary key.
	*
	* @param id the primary key of the stop
	* @return the stop
	* @throws PortalException if a stop with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Stop getStop(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stopLocalService.getStop(id);
	}

	/**
	* Supprime un Stop
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Stop removeStop(long stopId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stopLocalService.removeStop(stopId);
	}

	/**
	* Updates the stop in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stop the stop
	* @return the stop that was updated
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Stop updateStop(
		eu.strasbourg.service.gtfs.model.Stop stop) {
		return _stopLocalService.updateStop(stop);
	}

	/**
	* Met à jour un Stop et l'enregistre en base de données
	*
	* @throws IOException
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Stop updateStop(
		eu.strasbourg.service.gtfs.model.Stop stop,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stopLocalService.updateStop(stop, sc);
	}

	/**
	* Returns the number of stops.
	*
	* @return the number of stops
	*/
	@Override
	public int getStopsCount() {
		return _stopLocalService.getStopsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stopLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _stopLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _stopLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _stopLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Recuperer tous les arrets
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.Stop> getAllStops() {
		return _stopLocalService.getAllStops();
	}

	/**
	* Returns a range of all the stops.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.StopModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of stops
	* @param end the upper bound of the range of stops (not inclusive)
	* @return the range of stops
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.Stop> getStops(
		int start, int end) {
		return _stopLocalService.getStops(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _stopLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _stopLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Import des arrets sous le format de données GTFS
	*/
	@Override
	public void importFromGTFS(
		java.util.Map<java.lang.String, eu.strasbourg.utils.models.StopsGTFS> data)
		throws com.liferay.portal.kernel.exception.PortalException {
		_stopLocalService.importFromGTFS(data);
	}

	/**
	* Supprime toutes les arrets
	*/
	@Override
	public void removeAllStops()
		throws com.liferay.portal.kernel.exception.PortalException {
		_stopLocalService.removeAllStops();
	}

	@Override
	public StopLocalService getWrappedService() {
		return _stopLocalService;
	}

	@Override
	public void setWrappedService(StopLocalService stopLocalService) {
		_stopLocalService = stopLocalService;
	}

	private StopLocalService _stopLocalService;
}