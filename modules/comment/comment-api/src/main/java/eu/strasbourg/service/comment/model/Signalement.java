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

package eu.strasbourg.service.comment.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Signalement service. Represents a row in the &quot;comment_Signalement&quot; database table, with each column mapped to a property of this class.
 *
 * @author Romain Vergnais
 * @see SignalementModel
 * @see eu.strasbourg.service.comment.model.impl.SignalementImpl
 * @see eu.strasbourg.service.comment.model.impl.SignalementModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.comment.model.impl.SignalementImpl")
@ProviderType
public interface Signalement extends SignalementModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.comment.model.impl.SignalementImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Signalement, Long> SIGNALEMENT_ID_ACCESSOR = new Accessor<Signalement, Long>() {
			@Override
			public Long get(Signalement signalement) {
				return signalement.getSignalementId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Signalement> getTypeClass() {
				return Signalement.class;
			}
		};
}