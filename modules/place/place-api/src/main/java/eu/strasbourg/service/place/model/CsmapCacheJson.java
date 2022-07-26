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

package eu.strasbourg.service.place.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CsmapCacheJson service. Represents a row in the &quot;place_CsmapCacheJson&quot; database table, with each column mapped to a property of this class.
 *
 * @author Angelique Zunino Champougny
 * @see CsmapCacheJsonModel
 * @generated
 */
@ImplementationClassName(
	"eu.strasbourg.service.place.model.impl.CsmapCacheJsonImpl"
)
@ProviderType
public interface CsmapCacheJson extends CsmapCacheJsonModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>eu.strasbourg.service.place.model.impl.CsmapCacheJsonImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CsmapCacheJson, String> SIG_ID_ACCESSOR =
		new Accessor<CsmapCacheJson, String>() {

			@Override
			public String get(CsmapCacheJson csmapCacheJson) {
				return csmapCacheJson.getSigId();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<CsmapCacheJson> getTypeClass() {
				return CsmapCacheJson.class;
			}

		};

}