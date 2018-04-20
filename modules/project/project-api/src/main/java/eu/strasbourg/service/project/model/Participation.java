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
 * The extended model interface for the Participation service. Represents a row in the &quot;project_Participation&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see ParticipationModel
 * @see eu.strasbourg.service.project.model.impl.ParticipationImpl
 * @see eu.strasbourg.service.project.model.impl.ParticipationModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.project.model.impl.ParticipationImpl")
@ProviderType
public interface Participation extends ParticipationModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.project.model.impl.ParticipationImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Participation, Long> PARTICIPATION_ID_ACCESSOR = new Accessor<Participation, Long>() {
			@Override
			public Long get(Participation participation) {
				return participation.getParticipationId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Participation> getTypeClass() {
				return Participation.class;
			}
		};
}