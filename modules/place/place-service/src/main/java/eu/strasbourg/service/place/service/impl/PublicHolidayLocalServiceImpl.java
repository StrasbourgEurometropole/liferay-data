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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.place.model.PublicHoliday;
import eu.strasbourg.service.place.service.base.PublicHolidayLocalServiceBaseImpl;

/**
 * The implementation of the public holiday local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.place.service.PublicHolidayLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see PublicHolidayLocalServiceBaseImpl
 * @see eu.strasbourg.service.place.service.PublicHolidayLocalServiceUtil
 */
@ProviderType
public class PublicHolidayLocalServiceImpl
	extends PublicHolidayLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.place.service.PublicHolidayLocalServiceUtil} to access the public holiday local service.
	 */

	/**
	 * Crée une période vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public PublicHoliday createPublicHoliday(ServiceContext sc) throws PortalException {
		long pk = counterLocalService.increment();
		return this.publicHolidayLocalService.createPublicHoliday(pk);
	}
}