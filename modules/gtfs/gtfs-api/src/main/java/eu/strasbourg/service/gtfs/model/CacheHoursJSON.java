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
 * The extended model interface for the CacheHoursJSON service. Represents a row in the &quot;gtfs_CacheHoursJSON&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see CacheHoursJSONModel
 * @generated
 */
@ImplementationClassName(
	"eu.strasbourg.service.gtfs.model.impl.CacheHoursJSONImpl"
)
@ProviderType
public interface CacheHoursJSON extends CacheHoursJSONModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>eu.strasbourg.service.gtfs.model.impl.CacheHoursJSONImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CacheHoursJSON, String> STOP_CODE_ACCESSOR =
		new Accessor<CacheHoursJSON, String>() {

			@Override
			public String get(CacheHoursJSON cacheHoursJSON) {
				return cacheHoursJSON.getStopCode();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<CacheHoursJSON> getTypeClass() {
				return CacheHoursJSON.class;
			}

		};

}