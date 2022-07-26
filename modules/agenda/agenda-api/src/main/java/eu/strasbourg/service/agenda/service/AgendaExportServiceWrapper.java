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

package eu.strasbourg.service.agenda.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AgendaExportService}.
 *
 * @author BenjaminBini
 * @see AgendaExportService
 * @generated
 */
public class AgendaExportServiceWrapper
	implements AgendaExportService, ServiceWrapper<AgendaExportService> {

	public AgendaExportServiceWrapper(AgendaExportService agendaExportService) {
		_agendaExportService = agendaExportService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _agendaExportService.getOSGiServiceIdentifier();
	}

	/**
	 * Renvoit la liste des catégories parentes d'un vocabulaire
	 */
	@Override
	public com.liferay.portal.kernel.json.JSONArray getParentCategories(
			Long vocabularyId, String localeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _agendaExportService.getParentCategories(vocabularyId, localeId);
	}

	@Override
	public AgendaExportService getWrappedService() {
		return _agendaExportService;
	}

	@Override
	public void setWrappedService(AgendaExportService agendaExportService) {
		_agendaExportService = agendaExportService;
	}

	private AgendaExportService _agendaExportService;

}