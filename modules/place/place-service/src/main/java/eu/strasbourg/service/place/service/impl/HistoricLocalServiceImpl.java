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

import eu.strasbourg.service.place.model.Historic;
import eu.strasbourg.service.place.service.base.HistoricLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * The implementation of the historic local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.place.service.HistoricLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see HistoricLocalServiceBaseImpl
 */
public class HistoricLocalServiceImpl extends HistoricLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>eu.strasbourg.service.place.service.HistoricLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>eu.strasbourg.service.place.service.HistoricLocalServiceUtil</code>.
	 */

	/**
	 * Retourne les caches d'un lieu créé après une date et actif
	 */
	@Override
	public List<Historic> getBySuppressionDate(Date date) {
		return this.historicPersistence.findBySuppressionDate(date);
	}
}