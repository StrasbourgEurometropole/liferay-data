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

package eu.strasbourg.service.agenda.model.impl;

import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.utils.ImportReportLineStatus;
import eu.strasbourg.service.agenda.utils.ImportReportStatus;

/**
 * The extended model implementation for the ImportReportLine service.
 * Represents a row in the &quot;agenda_ImportReportLine&quot; database table,
 * with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.agenda.model.ImportReportLine}
 * interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class ImportReportLineImpl extends ImportReportLineBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a import
	 * report line model instance should use the {@link
	 * eu.strasbourg.service.agenda.model.ImportReportLine} interface instead.
	 */
	public ImportReportLineImpl() {
		this.setStatus(ImportReportLineStatus.SUCCESS_ADD);
	}

	@Override
	public void error(String cause) {
		if (Validator.isNotNull(this.getLog())) {
			this.setLog(this.getLog() + " - " + cause);
		} else {
			this.setLog(cause);
		}
		this.setStatus(ImportReportLineStatus.FAILURE);
	}
	
	@Override
	public boolean hasError() {
		return this.getStatus() == ImportReportLineStatus.FAILURE;
	}
	
}