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

package eu.strasbourg.service.activity.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssociationService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssociationService
 * @generated
 */
@ProviderType
public class AssociationServiceWrapper implements AssociationService,
	ServiceWrapper<AssociationService> {
	public AssociationServiceWrapper(AssociationService associationService) {
		_associationService = associationService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _associationService.getOSGiServiceIdentifier();
	}

	@Override
	public AssociationService getWrappedService() {
		return _associationService;
	}

	@Override
	public void setWrappedService(AssociationService associationService) {
		_associationService = associationService;
	}

	private AssociationService _associationService;
}