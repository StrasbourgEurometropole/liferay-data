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
 * The extended model interface for the OfficialTypeCouncil service. Represents a row in the &quot;council_OfficialTypeCouncil&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see OfficialTypeCouncilModel
 * @generated
 */
@ImplementationClassName(
	"eu.strasbourg.service.council.model.impl.OfficialTypeCouncilImpl"
)
@ProviderType
public interface OfficialTypeCouncil
	extends OfficialTypeCouncilModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>eu.strasbourg.service.council.model.impl.OfficialTypeCouncilImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<OfficialTypeCouncil, Long>
		OFFICIAL_ID_ACCESSOR = new Accessor<OfficialTypeCouncil, Long>() {

			@Override
			public Long get(OfficialTypeCouncil officialTypeCouncil) {
				return officialTypeCouncil.getOfficialId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<OfficialTypeCouncil> getTypeClass() {
				return OfficialTypeCouncil.class;
			}

		};
	public static final Accessor<OfficialTypeCouncil, Long> TYPE_ID_ACCESSOR =
		new Accessor<OfficialTypeCouncil, Long>() {

			@Override
			public Long get(OfficialTypeCouncil officialTypeCouncil) {
				return officialTypeCouncil.getTypeId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<OfficialTypeCouncil> getTypeClass() {
				return OfficialTypeCouncil.class;
			}

		};

}