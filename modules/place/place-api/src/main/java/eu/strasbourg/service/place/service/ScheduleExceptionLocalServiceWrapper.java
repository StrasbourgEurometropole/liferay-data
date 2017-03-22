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

package eu.strasbourg.service.place.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ScheduleExceptionLocalService}.
 *
 * @author Angelique Zunino Champougny
 * @see ScheduleExceptionLocalService
 * @generated
 */
@ProviderType
public class ScheduleExceptionLocalServiceWrapper
	implements ScheduleExceptionLocalService,
		ServiceWrapper<ScheduleExceptionLocalService> {
	public ScheduleExceptionLocalServiceWrapper(
		ScheduleExceptionLocalService scheduleExceptionLocalService) {
		_scheduleExceptionLocalService = scheduleExceptionLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _scheduleExceptionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _scheduleExceptionLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _scheduleExceptionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduleExceptionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduleExceptionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the schedule exception to the database. Also notifies the appropriate model listeners.
	*
	* @param scheduleException the schedule exception
	* @return the schedule exception that was added
	*/
	@Override
	public eu.strasbourg.service.place.model.ScheduleException addScheduleException(
		eu.strasbourg.service.place.model.ScheduleException scheduleException) {
		return _scheduleExceptionLocalService.addScheduleException(scheduleException);
	}

	/**
	* Crée une exception vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.place.model.ScheduleException createScheduleException(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduleExceptionLocalService.createScheduleException(sc);
	}

	/**
	* Creates a new schedule exception with the primary key. Does not add the schedule exception to the database.
	*
	* @param exceptionId the primary key for the new schedule exception
	* @return the new schedule exception
	*/
	@Override
	public eu.strasbourg.service.place.model.ScheduleException createScheduleException(
		long exceptionId) {
		return _scheduleExceptionLocalService.createScheduleException(exceptionId);
	}

	/**
	* Deletes the schedule exception from the database. Also notifies the appropriate model listeners.
	*
	* @param scheduleException the schedule exception
	* @return the schedule exception that was removed
	*/
	@Override
	public eu.strasbourg.service.place.model.ScheduleException deleteScheduleException(
		eu.strasbourg.service.place.model.ScheduleException scheduleException) {
		return _scheduleExceptionLocalService.deleteScheduleException(scheduleException);
	}

	/**
	* Deletes the schedule exception with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param exceptionId the primary key of the schedule exception
	* @return the schedule exception that was removed
	* @throws PortalException if a schedule exception with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.place.model.ScheduleException deleteScheduleException(
		long exceptionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduleExceptionLocalService.deleteScheduleException(exceptionId);
	}

	@Override
	public eu.strasbourg.service.place.model.ScheduleException fetchScheduleException(
		long exceptionId) {
		return _scheduleExceptionLocalService.fetchScheduleException(exceptionId);
	}

	/**
	* Returns the schedule exception with the primary key.
	*
	* @param exceptionId the primary key of the schedule exception
	* @return the schedule exception
	* @throws PortalException if a schedule exception with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.place.model.ScheduleException getScheduleException(
		long exceptionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _scheduleExceptionLocalService.getScheduleException(exceptionId);
	}

	/**
	* Updates the schedule exception in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param scheduleException the schedule exception
	* @return the schedule exception that was updated
	*/
	@Override
	public eu.strasbourg.service.place.model.ScheduleException updateScheduleException(
		eu.strasbourg.service.place.model.ScheduleException scheduleException) {
		return _scheduleExceptionLocalService.updateScheduleException(scheduleException);
	}

	/**
	* Returns the number of schedule exceptions.
	*
	* @return the number of schedule exceptions
	*/
	@Override
	public int getScheduleExceptionsCount() {
		return _scheduleExceptionLocalService.getScheduleExceptionsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _scheduleExceptionLocalService.getOSGiServiceIdentifier();
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
		return _scheduleExceptionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _scheduleExceptionLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _scheduleExceptionLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Retourne les ScheduleException rattachés à un lieu
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.ScheduleException> getByPlaceId(
		long placeId) {
		return _scheduleExceptionLocalService.getByPlaceId(placeId);
	}

	/**
	* Retourne les ScheduleException rattachés à un sous-lieu
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.ScheduleException> getBySubPlaceId(
		long subPlaceId) {
		return _scheduleExceptionLocalService.getBySubPlaceId(subPlaceId);
	}

	/**
	* Returns a range of all the schedule exceptions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.ScheduleExceptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of schedule exceptions
	* @param end the upper bound of the range of schedule exceptions (not inclusive)
	* @return the range of schedule exceptions
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.ScheduleException> getScheduleExceptions(
		int start, int end) {
		return _scheduleExceptionLocalService.getScheduleExceptions(start, end);
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
		return _scheduleExceptionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _scheduleExceptionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public ScheduleExceptionLocalService getWrappedService() {
		return _scheduleExceptionLocalService;
	}

	@Override
	public void setWrappedService(
		ScheduleExceptionLocalService scheduleExceptionLocalService) {
		_scheduleExceptionLocalService = scheduleExceptionLocalService;
	}

	private ScheduleExceptionLocalService _scheduleExceptionLocalService;
}