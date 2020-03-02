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
 * The extended model interface for the StopTime service. Represents a row in the &quot;gtfs_StopTime&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see StopTimeModel
 * @see eu.strasbourg.service.gtfs.model.impl.StopTimeImpl
 * @see eu.strasbourg.service.gtfs.model.impl.StopTimeModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.gtfs.model.impl.StopTimeImpl")
@ProviderType
public interface StopTime extends StopTimeModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.gtfs.model.impl.StopTimeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StopTime, Long> ID_ACCESSOR = new Accessor<StopTime, Long>() {
			@Override
			public Long get(StopTime stopTime) {
				return stopTime.getId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<StopTime> getTypeClass() {
				return StopTime.class;
			}
		};
}