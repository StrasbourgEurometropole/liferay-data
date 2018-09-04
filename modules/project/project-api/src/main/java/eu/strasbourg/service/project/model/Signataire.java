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

package eu.strasbourg.service.project.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Signataire service. Represents a row in the &quot;project_Signataire&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see SignataireModel
 * @see eu.strasbourg.service.project.model.impl.SignataireImpl
 * @see eu.strasbourg.service.project.model.impl.SignataireModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.project.model.impl.SignataireImpl")
@ProviderType
public interface Signataire extends SignataireModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.project.model.impl.SignataireImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Signataire, Long> SIGNATAIRE_ID_ACCESSOR = new Accessor<Signataire, Long>() {
			@Override
			public Long get(Signataire signataire) {
				return signataire.getSignataireId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Signataire> getTypeClass() {
				return Signataire.class;
			}
		};
}