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
 * Provides a wrapper for {@link AssociationActivityService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssociationActivityService
 * @generated
 */
@ProviderType
public class AssociationActivityServiceWrapper
	implements AssociationActivityService,
		ServiceWrapper<AssociationActivityService> {
	public AssociationActivityServiceWrapper(
		AssociationActivityService associationActivityService) {
		_associationActivityService = associationActivityService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _associationActivityService.getOSGiServiceIdentifier();
	}

	@Override
	public AssociationActivityService getWrappedService() {
		return _associationActivityService;
	}

	@Override
	public void setWrappedService(
		AssociationActivityService associationActivityService) {
		_associationActivityService = associationActivityService;
	}

	private AssociationActivityService _associationActivityService;
}