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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.liferay.portal.kernel.exception.PortalException;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.ImportReport;
import eu.strasbourg.service.agenda.model.ImportReportLine;
import eu.strasbourg.service.agenda.service.base.ImportReportLocalServiceBaseImpl;

/**
 * The implementation of the import report local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.agenda.service.ImportReportLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author BenjaminBini
 * @see ImportReportLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.ImportReportLocalServiceUtil
 */
@ProviderType
public class ImportReportLocalServiceImpl
	extends ImportReportLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.agenda.service.ImportReportLocalServiceUtil} to
	 * access the import report local service.
	 */

	/**
	 * Crée une édition vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public ImportReport createImportReport() throws PortalException {
		long pk = counterLocalService.increment();

		ImportReport report = this.importReportLocalService
			.createImportReport(pk);

		return report;
	}

	@Override
	public List<ImportReportLine> getReportLines(ImportReport report) {
		return this.importReportLinePersistence
			.findByReportId(report.getReportId());
	}

	/**
	 * On ne garde que les 10 rapports les plus récents
	 */
	@Override
	public void purgeReports() {
		List<ImportReport> reports = this.getImportReports(-1, -1);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.HOUR, -1);
		List<ImportReport> reportsToDelete = reports.stream()
			.sorted((r1, r2) -> r1.getReportId() < r2.getReportId() ? 1 : -1)
			.skip(10)
			.collect(Collectors.toList());
		for (ImportReport report : reportsToDelete) {
			List<ImportReportLine> lines = report.getLines();
			for (ImportReportLine line : lines) {
				this.importReportLineLocalService.deleteImportReportLine(line);
			}
			this.deleteImportReport(report);
		}
		return;
	}
}