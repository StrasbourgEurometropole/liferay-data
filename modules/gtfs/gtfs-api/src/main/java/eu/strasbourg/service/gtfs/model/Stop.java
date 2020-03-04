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
 * The extended model interface for the Stop service. Represents a row in the &quot;gtfs_Stop&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see StopModel
 * @see eu.strasbourg.service.gtfs.model.impl.StopImpl
 * @see eu.strasbourg.service.gtfs.model.impl.StopModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.gtfs.model.impl.StopImpl")
@ProviderType
public interface Stop extends StopModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.gtfs.model.impl.StopImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Stop, Long> ID_ACCESSOR = new Accessor<Stop, Long>() {
			@Override
			public Long get(Stop stop) {
				return stop.getId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Stop> getTypeClass() {
				return Stop.class;
			}
		};
}