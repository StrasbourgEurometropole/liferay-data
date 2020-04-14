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
 * The extended model interface for the Deliberation service. Represents a row in the &quot;council_Deliberation&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see DeliberationModel
 * @see eu.strasbourg.service.council.model.impl.DeliberationImpl
 * @see eu.strasbourg.service.council.model.impl.DeliberationModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.council.model.impl.DeliberationImpl")
@ProviderType
public interface Deliberation extends DeliberationModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.council.model.impl.DeliberationImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Deliberation, Long> DELIBERATION_ID_ACCESSOR = new Accessor<Deliberation, Long>() {
			@Override
			public Long get(Deliberation deliberation) {
				return deliberation.getDeliberationId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Deliberation> getTypeClass() {
				return Deliberation.class;
			}
		};
}