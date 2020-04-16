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
 * The extended model interface for the CouncilSession service. Represents a row in the &quot;council_CouncilSession&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see CouncilSessionModel
 * @see eu.strasbourg.service.council.model.impl.CouncilSessionImpl
 * @see eu.strasbourg.service.council.model.impl.CouncilSessionModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.council.model.impl.CouncilSessionImpl")
@ProviderType
public interface CouncilSession extends CouncilSessionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.council.model.impl.CouncilSessionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CouncilSession, Long> COUNCIL_SESSION_ID_ACCESSOR =
		new Accessor<CouncilSession, Long>() {
			@Override
			public Long get(CouncilSession councilSession) {
				return councilSession.getCouncilSessionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<CouncilSession> getTypeClass() {
				return CouncilSession.class;
			}
		};

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();
}