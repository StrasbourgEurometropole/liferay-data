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
 * The extended model interface for the CacheAlertJSON service. Represents a row in the &quot;gtfs_CacheAlertJSON&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see CacheAlertJSONModel
 * @generated
 */
@ImplementationClassName(
	"eu.strasbourg.service.gtfs.model.impl.CacheAlertJSONImpl"
)
@ProviderType
public interface CacheAlertJSON extends CacheAlertJSONModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>eu.strasbourg.service.gtfs.model.impl.CacheAlertJSONImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CacheAlertJSON, Long> CACHE_ID_ACCESSOR =
		new Accessor<CacheAlertJSON, Long>() {

			@Override
			public Long get(CacheAlertJSON cacheAlertJSON) {
				return cacheAlertJSON.getCacheId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CacheAlertJSON> getTypeClass() {
				return CacheAlertJSON.class;
			}

		};

}