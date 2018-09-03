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

package eu.strasbourg.service.project.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SignataireService}.
 *
 * @author Cedric Henry
 * @see SignataireService
 * @generated
 */
@ProviderType
public class SignataireServiceWrapper implements SignataireService,
	ServiceWrapper<SignataireService> {
	public SignataireServiceWrapper(SignataireService signataireService) {
		_signataireService = signataireService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _signataireService.getOSGiServiceIdentifier();
	}

	@Override
	public SignataireService getWrappedService() {
		return _signataireService;
	}

	@Override
	public void setWrappedService(SignataireService signataireService) {
		_signataireService = signataireService;
	}

	private SignataireService _signataireService;
}