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

package eu.strasbourg.service.tipi.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the TipiEntry service. Represents a row in the &quot;tipi_TipiEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Angelique Zunino Champougny
 * @see TipiEntryModel
 * @see eu.strasbourg.service.tipi.model.impl.TipiEntryImpl
 * @see eu.strasbourg.service.tipi.model.impl.TipiEntryModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.tipi.model.impl.TipiEntryImpl")
@ProviderType
public interface TipiEntry extends TipiEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.tipi.model.impl.TipiEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TipiEntry, Long> ID_ACCESSOR = new Accessor<TipiEntry, Long>() {
			@Override
			public Long get(TipiEntry tipiEntry) {
				return tipiEntry.getId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TipiEntry> getTypeClass() {
				return TipiEntry.class;
			}
		};
}