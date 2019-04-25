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
 * Provides a wrapper for {@link CalendarDateLocalService}.
 *
 * @author Cedric Henry
 * @see CalendarDateLocalService
 * @generated
 */
@ProviderType
public class CalendarDateLocalServiceWrapper implements CalendarDateLocalService,
	ServiceWrapper<CalendarDateLocalService> {
	public CalendarDateLocalServiceWrapper(
		CalendarDateLocalService calendarDateLocalService) {
		_calendarDateLocalService = calendarDateLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _calendarDateLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _calendarDateLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _calendarDateLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarDateLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarDateLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the calendar date to the database. Also notifies the appropriate model listeners.
	*
	* @param calendarDate the calendar date
	* @return the calendar date that was added
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.CalendarDate addCalendarDate(
		eu.strasbourg.service.gtfs.model.CalendarDate calendarDate) {
		return _calendarDateLocalService.addCalendarDate(calendarDate);
	}

	/**
	* Creates a new calendar date with the primary key. Does not add the calendar date to the database.
	*
	* @param id the primary key for the new calendar date
	* @return the new calendar date
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.CalendarDate createCalendarDate(
		long id) {
		return _calendarDateLocalService.createCalendarDate(id);
	}

	/**
	* Deletes the calendar date from the database. Also notifies the appropriate model listeners.
	*
	* @param calendarDate the calendar date
	* @return the calendar date that was removed
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.CalendarDate deleteCalendarDate(
		eu.strasbourg.service.gtfs.model.CalendarDate calendarDate) {
		return _calendarDateLocalService.deleteCalendarDate(calendarDate);
	}

	/**
	* Deletes the calendar date with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the calendar date
	* @return the calendar date that was removed
	* @throws PortalException if a calendar date with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.CalendarDate deleteCalendarDate(
		long id) throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarDateLocalService.deleteCalendarDate(id);
	}

	@Override
	public eu.strasbourg.service.gtfs.model.CalendarDate fetchCalendarDate(
		long id) {
		return _calendarDateLocalService.fetchCalendarDate(id);
	}

	/**
	* Returns the calendar date with the primary key.
	*
	* @param id the primary key of the calendar date
	* @return the calendar date
	* @throws PortalException if a calendar date with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.CalendarDate getCalendarDate(
		long id) throws com.liferay.portal.kernel.exception.PortalException {
		return _calendarDateLocalService.getCalendarDate(id);
	}

	/**
	* Updates the calendar date in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calendarDate the calendar date
	* @return the calendar date that was updated
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.CalendarDate updateCalendarDate(
		eu.strasbourg.service.gtfs.model.CalendarDate calendarDate) {
		return _calendarDateLocalService.updateCalendarDate(calendarDate);
	}

	/**
	* Returns the number of calendar dates.
	*
	* @return the number of calendar dates
	*/
	@Override
	public int getCalendarDatesCount() {
		return _calendarDateLocalService.getCalendarDatesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _calendarDateLocalService.getOSGiServiceIdentifier();
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
		return _calendarDateLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.CalendarDateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _calendarDateLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.CalendarDateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _calendarDateLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the calendar dates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.CalendarDateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of calendar dates
	* @param end the upper bound of the range of calendar dates (not inclusive)
	* @return the range of calendar dates
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.CalendarDate> getCalendarDates(
		int start, int end) {
		return _calendarDateLocalService.getCalendarDates(start, end);
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
		return _calendarDateLocalService.dynamicQueryCount(dynamicQuery);
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
		return _calendarDateLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public CalendarDateLocalService getWrappedService() {
		return _calendarDateLocalService;
	}

	@Override
	public void setWrappedService(
		CalendarDateLocalService calendarDateLocalService) {
		_calendarDateLocalService = calendarDateLocalService;
	}

	private CalendarDateLocalService _calendarDateLocalService;
}