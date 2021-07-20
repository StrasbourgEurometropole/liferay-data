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
 * Provides a wrapper for {@link ProcurationService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProcurationService
 * @generated
 */
@ProviderType
public class ProcurationServiceWrapper
	implements ProcurationService, ServiceWrapper<ProcurationService> {

	public ProcurationServiceWrapper(ProcurationService procurationService) {
		_procurationService = procurationService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject
		findAssociatedProcurationJSON(long councilSessionId) {

		return _procurationService.findAssociatedProcurationJSON(
			councilSessionId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _procurationService.getOSGiServiceIdentifier();
	}

	@Override
	public ProcurationService getWrappedService() {
		return _procurationService;
	}

	@Override
	public void setWrappedService(ProcurationService procurationService) {
		_procurationService = procurationService;
	}

	private ProcurationService _procurationService;

}