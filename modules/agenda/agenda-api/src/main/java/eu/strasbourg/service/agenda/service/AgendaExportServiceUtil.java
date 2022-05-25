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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for AgendaExport. This utility wraps
 * <code>eu.strasbourg.service.agenda.service.impl.AgendaExportServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author BenjaminBini
 * @see AgendaExportService
 * @generated
 */
public class AgendaExportServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.agenda.service.impl.AgendaExportServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * Renvoit la liste des catégories parentes d'un vocabulaire
	 */
	public static com.liferay.portal.kernel.json.JSONArray getParentCategories(
			Long vocabularyId, String localeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getParentCategories(vocabularyId, localeId);
	}

	public static AgendaExportService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AgendaExportService, AgendaExportService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(AgendaExportService.class);

		ServiceTracker<AgendaExportService, AgendaExportService>
			serviceTracker =
				new ServiceTracker<AgendaExportService, AgendaExportService>(
					bundle.getBundleContext(), AgendaExportService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}