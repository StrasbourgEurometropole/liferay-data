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
 * The extended model interface for the Alert service. Represents a row in the &quot;gtfs_Alert&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see AlertModel
 * @see eu.strasbourg.service.gtfs.model.impl.AlertImpl
 * @see eu.strasbourg.service.gtfs.model.impl.AlertModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.gtfs.model.impl.AlertImpl")
@ProviderType
public interface Alert extends AlertModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.gtfs.model.impl.AlertImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Alert, Long> ALERT_ID_ACCESSOR = new Accessor<Alert, Long>() {
			@Override
			public Long get(Alert alert) {
				return alert.getAlertId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Alert> getTypeClass() {
				return Alert.class;
			}
		};
}