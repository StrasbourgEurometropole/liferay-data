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

package eu.strasbourg.service.agenda.service.impl;

import eu.strasbourg.service.agenda.model.AgendaExportPeriod;
import eu.strasbourg.service.agenda.service.base.AgendaExportPeriodLocalServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the agenda export period local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.agenda.service.AgendaExportPeriodLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see AgendaExportPeriodLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.AgendaExportPeriodLocalServiceUtil
 */
public class AgendaExportPeriodLocalServiceImpl
	extends AgendaExportPeriodLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.agenda.service.AgendaExportPeriodLocalServiceUtil} to access the agenda export period local service.
	 */

	 /**
	 * Retourne les périodes d'un événement
	 */
	@Override
	public List<AgendaExportPeriod> getByAgendaExportId(long eventId) {
		return this.agendaExportPeriodPersistence.findByAgendaExportId(eventId, -1, -1, null, false);
	}
}