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

import eu.strasbourg.service.agenda.model.EventParticipation;
import eu.strasbourg.service.agenda.service.base.EventParticipationLocalServiceBaseImpl;


/**
 * The implementation of the event participation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.agenda.service.EventParticipationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see EventParticipationLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.EventParticipationLocalServiceUtil
 */
public class EventParticipationLocalServiceImpl
	extends EventParticipationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.agenda.service.EventParticipationLocalServiceUtil} to access the event participation local service.
	 */
	
	/**
	 * Cree une nouvelle participation a un evenement
	 */
	@Override
	public EventParticipation createEventParticipation() {
		long pk = this.counterLocalService.increment();
		return this.eventParticipationLocalService.createEventParticipation(pk);
	}
	
	/**
	 * Retourne la liste des likes/dislikes d'un utilisateur
	 */
	@Override
	public List<EventParticipation> getByPublikUser(String publikUserId) {
		return this.eventParticipationPersistence.findByPublikUserId(publikUserId);
	}
	
	/**
	 * Retourne la liste des likes/dislikes d'un evenement
	 */
	@Override
	public List<EventParticipation> getByEventId(long eventId) {
		return this.eventParticipationPersistence.findByEventId(eventId);
	}
	
}