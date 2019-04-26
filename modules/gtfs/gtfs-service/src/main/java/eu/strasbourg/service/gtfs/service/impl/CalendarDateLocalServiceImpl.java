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

import eu.strasbourg.service.gtfs.model.CalendarDate;
import eu.strasbourg.service.gtfs.service.base.CalendarDateLocalServiceBaseImpl;

/**
 * The implementation of the calendar date local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.gtfs.service.CalendarDateLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see CalendarDateLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.CalendarDateLocalServiceUtil
 */
public class CalendarDateLocalServiceImpl extends CalendarDateLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.gtfs.service.CalendarDateLocalServiceUtil} to access the calendar date local service.
	 */
	
	/**
	 * Crée un CalendarDate vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public CalendarDate createCalendarDate(ServiceContext sc) throws PortalException {
		long pk = counterLocalService.increment();
		CalendarDate calendarDate = this.calendarDateLocalService.createCalendarDate(pk);

		return calendarDate;
	}
	
	/**
	 * Met à jour un CalendarDate et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public CalendarDate updateCalendarDate(CalendarDate calendarDate, ServiceContext sc) throws PortalException {
		calendarDate = this.calendarDateLocalService.updateCalendarDate(calendarDate);

		return calendarDate;
	}
	
	/**
	 * Supprime un CalendarDate
	 */
	@Override
	public CalendarDate removeCalendarDate(long calendarDateId) throws PortalException {
		CalendarDate calendarDate = this.calendarDatePersistence.remove(calendarDateId);

		return calendarDate;
	}
	
	/**
	 * Supprime toutes les CalendarDates
	 */
	@Override
	public void removeAllCalendarDate() throws PortalException {
		this.calendarDatePersistence.removeAll();
	}
	
}