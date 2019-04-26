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

import eu.strasbourg.service.gtfs.model.Calendar;
import eu.strasbourg.service.gtfs.service.base.CalendarLocalServiceBaseImpl;

/**
 * The implementation of the calendar local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.gtfs.service.CalendarLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Cedric Henry
 * @see CalendarLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.CalendarLocalServiceUtil
 */
public class CalendarLocalServiceImpl extends CalendarLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.gtfs.service.CalendarLocalServiceUtil} to access the calendar local service.
	 */
	
	/**
	 * Crée un Calendar vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Calendar createCalendar(ServiceContext sc) throws PortalException {
		long pk = counterLocalService.increment();
		Calendar calendar = this.calendarLocalService.createCalendar(pk);

		return calendar;
	}
	
	/**
	 * Met à jour un Calendar et l'enregistre en base de données
	 * @throws IOException
	 */
	@Override
	public Calendar updateCalendar(Calendar calendar, ServiceContext sc) throws PortalException {
		calendar = this.calendarLocalService.updateCalendar(calendar);

		return calendar;
	}
	
	/**
	 * Supprime un Calendar
	 */
	@Override
	public Calendar removeCalendar(long calendarId) throws PortalException {
		Calendar calendar = this.calendarPersistence.remove(calendarId);

		return calendar;
	}
	
	/**
	 * Supprime toutes les Calendar
	 */
	@Override
	public void removeAllCalendar() throws PortalException {
		this.calendarPersistence.removeAll();
	}
	
}