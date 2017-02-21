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

package eu.strasbourg.service.agenda.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ImportReport service. Represents a row in the &quot;agenda_ImportReport&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see ImportReportModel
 * @see eu.strasbourg.service.agenda.model.impl.ImportReportImpl
 * @see eu.strasbourg.service.agenda.model.impl.ImportReportModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.agenda.model.impl.ImportReportImpl")
@ProviderType
public interface ImportReport extends ImportReportModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.agenda.model.impl.ImportReportImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ImportReport, Long> REPORT_ID_ACCESSOR = new Accessor<ImportReport, Long>() {
			@Override
			public Long get(ImportReport importReport) {
				return importReport.getReportId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ImportReport> getTypeClass() {
				return ImportReport.class;
			}
		};

	public void globalError(java.lang.String cause);

	public void incrementNewEvents();

	public void incrementNewManifestations();

	public void incrementModifiedEvents();

	public void incrementModifiedManifestations();

	public void incrementErrorEvents();

	public void incrementErrorManifestations();

	public java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine> getLines();

	public java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine> getNewManifestationsLines();

	public java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine> getModifiedManifestationsLines();

	public java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine> getErrorManifestationsLines();

	public java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine> getNewEventsLines();

	public java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine> getModifiedEventsLines();

	public java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine> getErrorEventsLines();

	public void sendMail();
}