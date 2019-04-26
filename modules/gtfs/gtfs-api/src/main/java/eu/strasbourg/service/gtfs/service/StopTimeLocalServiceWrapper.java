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
 * Provides a wrapper for {@link StopTimeLocalService}.
 *
 * @author Cedric Henry
 * @see StopTimeLocalService
 * @generated
 */
@ProviderType
public class StopTimeLocalServiceWrapper implements StopTimeLocalService,
	ServiceWrapper<StopTimeLocalService> {
	public StopTimeLocalServiceWrapper(
		StopTimeLocalService stopTimeLocalService) {
		_stopTimeLocalService = stopTimeLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stopTimeLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stopTimeLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stopTimeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stopTimeLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stopTimeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the stop time to the database. Also notifies the appropriate model listeners.
	*
	* @param stopTime the stop time
	* @return the stop time that was added
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.StopTime addStopTime(
		eu.strasbourg.service.gtfs.model.StopTime stopTime) {
		return _stopTimeLocalService.addStopTime(stopTime);
	}

	/**
	* Crée un StopTime vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.StopTime createStopTime(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stopTimeLocalService.createStopTime(sc);
	}

	/**
	* Creates a new stop time with the primary key. Does not add the stop time to the database.
	*
	* @param id the primary key for the new stop time
	* @return the new stop time
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.StopTime createStopTime(long id) {
		return _stopTimeLocalService.createStopTime(id);
	}

	/**
	* Deletes the stop time from the database. Also notifies the appropriate model listeners.
	*
	* @param stopTime the stop time
	* @return the stop time that was removed
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.StopTime deleteStopTime(
		eu.strasbourg.service.gtfs.model.StopTime stopTime) {
		return _stopTimeLocalService.deleteStopTime(stopTime);
	}

	/**
	* Deletes the stop time with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the stop time
	* @return the stop time that was removed
	* @throws PortalException if a stop time with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.StopTime deleteStopTime(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stopTimeLocalService.deleteStopTime(id);
	}

	@Override
	public eu.strasbourg.service.gtfs.model.StopTime fetchStopTime(long id) {
		return _stopTimeLocalService.fetchStopTime(id);
	}

	/**
	* Returns the stop time with the primary key.
	*
	* @param id the primary key of the stop time
	* @return the stop time
	* @throws PortalException if a stop time with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.StopTime getStopTime(long id)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stopTimeLocalService.getStopTime(id);
	}

	/**
	* Supprime un StopTime
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.StopTime removeStopTime(
		long stopTimeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stopTimeLocalService.removeStopTime(stopTimeId);
	}

	/**
	* Updates the stop time in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stopTime the stop time
	* @return the stop time that was updated
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.StopTime updateStopTime(
		eu.strasbourg.service.gtfs.model.StopTime stopTime) {
		return _stopTimeLocalService.updateStopTime(stopTime);
	}

	/**
	* Met à jour un StopTime et l'enregistre en base de données
	*
	* @throws IOException
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.StopTime updateStopTime(
		eu.strasbourg.service.gtfs.model.StopTime stopTime,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stopTimeLocalService.updateStopTime(stopTime, sc);
	}

	/**
	* Returns the number of stop times.
	*
	* @return the number of stop times
	*/
	@Override
	public int getStopTimesCount() {
		return _stopTimeLocalService.getStopTimesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stopTimeLocalService.getOSGiServiceIdentifier();
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
		return _stopTimeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.StopTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stopTimeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.StopTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stopTimeLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the stop times.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.StopTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of stop times
	* @param end the upper bound of the range of stop times (not inclusive)
	* @return the range of stop times
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.StopTime> getStopTimes(
		int start, int end) {
		return _stopTimeLocalService.getStopTimes(start, end);
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
		return _stopTimeLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stopTimeLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Supprime toutes le StopTime
	*/
	@Override
	public void removeAllStopTime()
		throws com.liferay.portal.kernel.exception.PortalException {
		_stopTimeLocalService.removeAllStopTime();
	}

	@Override
	public StopTimeLocalService getWrappedService() {
		return _stopTimeLocalService;
	}

	@Override
	public void setWrappedService(StopTimeLocalService stopTimeLocalService) {
		_stopTimeLocalService = stopTimeLocalService;
	}

	private StopTimeLocalService _stopTimeLocalService;
}