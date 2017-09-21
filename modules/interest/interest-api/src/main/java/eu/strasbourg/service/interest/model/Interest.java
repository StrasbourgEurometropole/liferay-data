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

package eu.strasbourg.service.interest.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Interest service. Represents a row in the &quot;interest_Interest&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see InterestModel
 * @see eu.strasbourg.service.interest.model.impl.InterestImpl
 * @see eu.strasbourg.service.interest.model.impl.InterestModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.interest.model.impl.InterestImpl")
@ProviderType
public interface Interest extends InterestModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.interest.model.impl.InterestImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Interest, Long> INTEREST_ID_ACCESSOR = new Accessor<Interest, Long>() {
			@Override
			public Long get(Interest interest) {
				return interest.getInterestId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Interest> getTypeClass() {
				return Interest.class;
			}
		};

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Retourne la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Retourne le type du centre d'intérêt
	*/
	public com.liferay.asset.kernel.model.AssetCategory getType();

	/**
	* Retourne la version JSON du centre d'intérêt
	*/
	public com.liferay.portal.kernel.json.JSONObject toJSON();
}