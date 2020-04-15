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

package eu.strasbourg.service.council.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DeliberationService}.
 *
 * @author Brian Wing Shun Chan
 * @see DeliberationService
 * @generated
 */
@ProviderType
public class DeliberationServiceWrapper implements DeliberationService,
	ServiceWrapper<DeliberationService> {
	public DeliberationServiceWrapper(DeliberationService deliberationService) {
		_deliberationService = deliberationService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _deliberationService.getOSGiServiceIdentifier();
	}

	@Override
	public DeliberationService getWrappedService() {
		return _deliberationService;
	}

	@Override
	public void setWrappedService(DeliberationService deliberationService) {
		_deliberationService = deliberationService;
	}

	private DeliberationService _deliberationService;
}