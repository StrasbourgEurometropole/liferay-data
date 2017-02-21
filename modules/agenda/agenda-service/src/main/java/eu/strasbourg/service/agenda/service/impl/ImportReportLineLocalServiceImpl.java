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

package eu.strasbourg.service.agenda.service.impl;

import com.liferay.portal.kernel.exception.PortalException;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.ImportReportLine;
import eu.strasbourg.service.agenda.service.base.ImportReportLineLocalServiceBaseImpl;
import eu.strasbourg.service.agenda.utils.ImportReportStatus;

/**
 * The implementation of the import report line local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.agenda.service.ImportReportLineLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see ImportReportLineLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.ImportReportLineLocalServiceUtil
 */
@ProviderType
public class ImportReportLineLocalServiceImpl
	extends ImportReportLineLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.agenda.service.ImportReportLineLocalServiceUtil} to
	 * access the import report line local service.
	 */

	/**
	 * Crée une ligne de rapport d'import avec une PK, non ajouté à la base de
	 * donnée
	 */
	@Override
	public ImportReportLine createImportReportLine() throws PortalException {
		long pk = counterLocalService.increment();

		ImportReportLine reportLine = this.importReportLineLocalService
			.createImportReportLine(pk);

		return reportLine;
	}
}