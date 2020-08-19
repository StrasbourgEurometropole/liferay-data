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

package eu.strasbourg.service.ejob.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AlertService}.
 *
 * @author Brian Wing Shun Chan
 * @see AlertService
 * @generated
 */
@ProviderType
public class AlertServiceWrapper
	implements AlertService, ServiceWrapper<AlertService> {

	public AlertServiceWrapper(AlertService alertService) {
		_alertService = alertService;
	}

	/**
	 * Créer une alerte à un utilisateur
	 */
	@Override
	public com.liferay.portal.kernel.json.JSONObject addAlert(
		String name, String categoriesId, String keyword, String languageId) {

		return _alertService.addAlert(name, categoriesId, keyword, languageId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _alertService.getOSGiServiceIdentifier();
	}

	@Override
	public AlertService getWrappedService() {
		return _alertService;
	}

	@Override
	public void setWrappedService(AlertService alertService) {
		_alertService = alertService;
	}

	private AlertService _alertService;

}