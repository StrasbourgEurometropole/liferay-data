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

package eu.strasbourg.service.council.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Procuration service. Represents a row in the &quot;council_Procuration&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ProcurationModel
 * @generated
 */
@ImplementationClassName(
	"eu.strasbourg.service.council.model.impl.ProcurationImpl"
)
@ProviderType
public interface Procuration extends PersistedModel, ProcurationModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>eu.strasbourg.service.council.model.impl.ProcurationImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Procuration, Long> PROCURATION_ID_ACCESSOR =
		new Accessor<Procuration, Long>() {

			@Override
			public Long get(Procuration procuration) {
				return procuration.getProcurationId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Procuration> getTypeClass() {
				return Procuration.class;
			}

		};

	/**
	 * Retourne le nom complet de l'élu reçevant la procuration
	 */
	public String getOfficialVotersFullName();

}