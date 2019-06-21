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
 * The extended model interface for the Arret service. Represents a row in the &quot;gtfs_Arret&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see ArretModel
 * @see eu.strasbourg.service.gtfs.model.impl.ArretImpl
 * @see eu.strasbourg.service.gtfs.model.impl.ArretModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.gtfs.model.impl.ArretImpl")
@ProviderType
public interface Arret extends ArretModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.gtfs.model.impl.ArretImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Arret, Long> ARRET_ID_ACCESSOR = new Accessor<Arret, Long>() {
			@Override
			public Long get(Arret arret) {
				return arret.getArretId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Arret> getTypeClass() {
				return Arret.class;
			}
		};
}