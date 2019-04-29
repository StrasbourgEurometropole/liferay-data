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

package eu.strasbourg.service.gtfs.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ImportHistory service. Represents a row in the &quot;gtfs_ImportHistory&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see ImportHistoryModel
 * @see eu.strasbourg.service.gtfs.model.impl.ImportHistoryImpl
 * @see eu.strasbourg.service.gtfs.model.impl.ImportHistoryModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.gtfs.model.impl.ImportHistoryImpl")
@ProviderType
public interface ImportHistory extends ImportHistoryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.gtfs.model.impl.ImportHistoryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ImportHistory, Long> IMPORT_HISTORY_ID_ACCESSOR =
		new Accessor<ImportHistory, Long>() {
			@Override
			public Long get(ImportHistory importHistory) {
				return importHistory.getImportHistoryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ImportHistory> getTypeClass() {
				return ImportHistory.class;
			}
		};
}