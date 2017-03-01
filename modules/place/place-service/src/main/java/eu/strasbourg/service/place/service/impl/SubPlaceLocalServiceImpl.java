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

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.place.model.Period;
import eu.strasbourg.service.place.model.ScheduleException;
import eu.strasbourg.service.place.model.SubPlace;
import eu.strasbourg.service.place.service.base.SubPlaceLocalServiceBaseImpl;

/**
 * The implementation of the sub place local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.place.service.SubPlaceLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see SubPlaceLocalServiceBaseImpl
 * @see eu.strasbourg.service.place.service.SubPlaceLocalServiceUtil
 */
@ProviderType
public class SubPlaceLocalServiceImpl extends SubPlaceLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.place.service.SubPlaceLocalServiceUtil} to access
	 * the sub place local service.
	 */

	/**
	 * Crée un sous-lieu vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public SubPlace createSubPlace(ServiceContext sc) throws PortalException {
		long pk = counterLocalService.increment();
		return this.subPlaceLocalService.createSubPlace(pk);
	}

	/**
	 * Supprime un sous-lieu
	 */
	@Override
	public SubPlace removeSubPlace(long subPlaceId) throws PortalException {

		// Supprime le sous-lieu
		SubPlace subPlace = subPlacePersistence.remove(subPlaceId);

		// Supprime les exceptions liées au sous-lieu
		List<ScheduleException> exceptions = subPlace.getScheduleExceptions();
		for (ScheduleException exception : exceptions) {
			this.scheduleExceptionLocalService.deleteScheduleException(exception.getExceptionId());
		}
		
		// Supprime les périodes
		List<Period> periods = subPlace.getPeriods();
		for (Period period : periods) {
			 this.periodLocalService.removePeriod(period.getPeriodId());
		}

		return subPlace;
	}

	/**
	 * Retourne les SubPlace rattachés à un lieu
	 */
	@Override
	public List<SubPlace> getByPlaceId(long placeId) {
		return this.subPlacePersistence.findByPlaceId(placeId);
	}
}