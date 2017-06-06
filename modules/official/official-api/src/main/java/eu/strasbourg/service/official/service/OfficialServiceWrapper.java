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

package eu.strasbourg.service.official.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OfficialService}.
 *
 * @author AngeliqueZUNINO
 * @see OfficialService
 * @generated
 */
@ProviderType
public class OfficialServiceWrapper implements OfficialService,
	ServiceWrapper<OfficialService> {
	public OfficialServiceWrapper(OfficialService officialService) {
		_officialService = officialService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _officialService.getOSGiServiceIdentifier();
	}

	@Override
	public OfficialService getWrappedService() {
		return _officialService;
	}

	@Override
	public void setWrappedService(OfficialService officialService) {
		_officialService = officialService;
	}

	private OfficialService _officialService;
}