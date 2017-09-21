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

package eu.strasbourg.service.favorite.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Favorite service. Represents a row in the &quot;favorite_Favorite&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see FavoriteModel
 * @see eu.strasbourg.service.favorite.model.impl.FavoriteImpl
 * @see eu.strasbourg.service.favorite.model.impl.FavoriteModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.favorite.model.impl.FavoriteImpl")
@ProviderType
public interface Favorite extends FavoriteModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.favorite.model.impl.FavoriteImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Favorite, Long> FAVORITE_ID_ACCESSOR = new Accessor<Favorite, Long>() {
			@Override
			public Long get(Favorite favorite) {
				return favorite.getFavoriteId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Favorite> getTypeClass() {
				return Favorite.class;
			}
		};

	public boolean hasAssetEntry();

	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	public eu.strasbourg.service.favorite.model.FavoriteType getFavoriteType();

	/**
	* Retourne la version JSON d'un favoris
	*/
	public com.liferay.portal.kernel.json.JSONObject toJSON();
}