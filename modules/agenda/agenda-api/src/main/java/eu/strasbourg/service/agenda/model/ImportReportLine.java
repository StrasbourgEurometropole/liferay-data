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
 * The extended model interface for the ImportReportLine service. Represents a row in the &quot;agenda_ImportReportLine&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see ImportReportLineModel
 * @generated
 */
@ImplementationClassName(
	"eu.strasbourg.service.agenda.model.impl.ImportReportLineImpl"
)
@ProviderType
public interface ImportReportLine
	extends ImportReportLineModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>eu.strasbourg.service.agenda.model.impl.ImportReportLineImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ImportReportLine, Long> LINE_ID_ACCESSOR =
		new Accessor<ImportReportLine, Long>() {

			@Override
			public Long get(ImportReportLine importReportLine) {
				return importReportLine.getLineId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ImportReportLine> getTypeClass() {
				return ImportReportLine.class;
			}

		};

	public void error(String cause);

	public boolean hasError();

}