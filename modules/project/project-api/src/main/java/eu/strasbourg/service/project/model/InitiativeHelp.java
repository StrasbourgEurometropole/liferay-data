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
 * The extended model interface for the InitiativeHelp service. Represents a row in the &quot;project_InitiativeHelp&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see InitiativeHelpModel
 * @see eu.strasbourg.service.project.model.impl.InitiativeHelpImpl
 * @see eu.strasbourg.service.project.model.impl.InitiativeHelpModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.project.model.impl.InitiativeHelpImpl")
@ProviderType
public interface InitiativeHelp extends InitiativeHelpModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.project.model.impl.InitiativeHelpImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<InitiativeHelp, Long> INITIATIVE_HELP_ID_ACCESSOR =
		new Accessor<InitiativeHelp, Long>() {
			@Override
			public Long get(InitiativeHelp initiativeHelp) {
				return initiativeHelp.getInitiativeHelpId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<InitiativeHelp> getTypeClass() {
				return InitiativeHelp.class;
			}
		};
}