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

package eu.strasbourg.service.place.service.impl;

import eu.strasbourg.service.place.model.CsmapCacheJson;
import eu.strasbourg.service.place.service.base.CsmapCacheJsonLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the csmap cache json local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.place.service.CsmapCacheJsonLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see CsmapCacheJsonLocalServiceBaseImpl
 */
public class CsmapCacheJsonLocalServiceImpl
	extends CsmapCacheJsonLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.place.service.CsmapCacheJsonLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.place.service.CsmapCacheJsonLocalServiceUtil</code>.
	 */

	/**
	 * Retourne les caches d'un lieu créé après une date et actif
	 */
	@Override
	public List<CsmapCacheJson> getByCreatedDateAndIsActive(Date date) {
		return this.csmapCacheJsonPersistence.findByCreatedDateAndIsActive(date, true);
	}


	/**
	 * Retourne les caches d'un lieu modifié après une date, créé avant cette date et actif
	 */
	@Override
	public List<CsmapCacheJson> getByCreatedDateAndModifiedDateAndIsActive(Date date) {
		return this.csmapCacheJsonPersistence.findByCreatedDateAndModifiedDateAndIsActive(date, date, true);
	}


	/**
	 * Retourne les caches d'un lieu modifié après une date et inactif
	 */
	@Override
	public List<CsmapCacheJson> getByModifiedDateAndIsNotActive(Date date) {
		return this.csmapCacheJsonPersistence.findByModifiedDateAndIsActive(date, false);
	}
}