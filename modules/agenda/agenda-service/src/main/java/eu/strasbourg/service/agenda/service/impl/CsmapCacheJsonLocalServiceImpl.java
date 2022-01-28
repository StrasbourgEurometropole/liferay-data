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

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.agenda.model.CsmapCacheJson;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.agenda.service.EventLocalServiceUtil;
import eu.strasbourg.service.agenda.service.base.CsmapCacheJsonLocalServiceBaseImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * The implementation of the csmap cache json local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.agenda.service.CsmapCacheJsonLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see CsmapCacheJsonLocalServiceBaseImpl
 */
public class CsmapCacheJsonLocalServiceImpl
	extends CsmapCacheJsonLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.agenda.service.CsmapCacheJsonLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.agenda.service.CsmapCacheJsonLocalServiceUtil</code>.
	 */

	/**
	 * Retourne les caches d'un event créé après une date et actif
	 */
	@Override
	public List<CsmapCacheJson> getByCreatedDateAndIsActive(Date date) {
		return this.csmapCacheJsonPersistence.findByCreatedDateAndIsActive(date, true);
	}


	/**
	 * Retourne les caches d'un event modifié après une date, créé avant cette date et actif
	 */
	@Override
	public List<CsmapCacheJson> getByCreatedDateAndModifiedDateAndIsActive(Date date) {
		return this.csmapCacheJsonPersistence.findByCreatedDateAndModifiedDateAndIsActive(date, date, true);
	}

	/**
	 * Retourne les caches d'un event créé après une date, actif et avec schedules
	 */
	@Override
	public List<CsmapCacheJson> getByCreatedDateAndIsActiveAndWithSchedules(Date date) {
		return this.csmapCacheJsonPersistence.findByCreatedDateAndIsActiveAndWithSchedules(date, true, true);
	}


	/**
	 * Retourne les caches d'un event modifié après une date, créé avant cette date, actif
	 * et avec schedules
	 */
	@Override
	public List<CsmapCacheJson> getByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(Date date) {
		return this.csmapCacheJsonPersistence.findByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(date, date, true, true);
	}


	/**
	 * Retourne les caches d'un lieu modifié après une date et inactif
	 */
	@Override
	public List<CsmapCacheJson> getByModifiedDateAndIsNotActive(Date date) {
		return this.csmapCacheJsonPersistence.findByModifiedDateAndIsActive(date, false);
	}

	/**
	 * Met à jour les jsonEvent des event
	 */
	@Override
	public void updateJsonEvent() {
		// récupère tous les caches actifs ayant regeneratedDate <= à aujourd'hui - 30j
		LocalDate verificationDate = LocalDate.now().minusDays(30);
		Date verifDate = Date.from(verificationDate.atStartOfDay()
				.atZone(ZoneId.systemDefault())
				.toInstant());
		List<CsmapCacheJson> caches = this.csmapCacheJsonPersistence
				.findByRegeneratedDateAndIsActive(verifDate, true);
		for (CsmapCacheJson cache : caches) {
			Event event = EventLocalServiceUtil.fetchEvent(cache.getEventId());
			if(Validator.isNotNull(event)){
				JSONObject csmapJson = event.getCSMapJSON();
				cache.setJsonEvent(csmapJson.toString());
				if(csmapJson.getJSONArray("schedules").length() > 0)
					cache.setHasSchedules(true);
				else
					cache.setHasSchedules(false);
				cache.setRegeneratedDate(new Date());
				cache.setModifiedEvent(new Date());
				this.updateCsmapCacheJson(cache);
			}
		}
	}
}