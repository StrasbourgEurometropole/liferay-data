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

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.EventPeriod;
import eu.strasbourg.service.agenda.service.base.EventPeriodLocalServiceBaseImpl;

/**
 * The implementation of the event period local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.agenda.service.EventPeriodLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see EventPeriodLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.EventPeriodLocalServiceUtil
 */
@ProviderType
public class EventPeriodLocalServiceImpl extends EventPeriodLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.agenda.service.EventPeriodLocalServiceUtil} to access the event period local service.
	 */	

	/**
	 * Crée une édition vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public EventPeriod createEventPeriod() throws PortalException {
		long pk = counterLocalService.increment();

		EventPeriod eventPeriod = this.eventPeriodLocalService.createEventPeriod(pk);
		
		return eventPeriod;
	}

	/**
	 * Retourne les périodes d'un événement
	 */
	@Override
	public List<EventPeriod> getByEventId(long eventId) {
		return this.eventPeriodPersistence.findByEventId(eventId);
	}
}