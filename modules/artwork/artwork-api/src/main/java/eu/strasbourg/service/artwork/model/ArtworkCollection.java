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

package eu.strasbourg.service.artwork.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ArtworkCollection service. Represents a row in the &quot;artwork_ArtworkCollection&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see ArtworkCollectionModel
 * @see eu.strasbourg.service.artwork.model.impl.ArtworkCollectionImpl
 * @see eu.strasbourg.service.artwork.model.impl.ArtworkCollectionModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.artwork.model.impl.ArtworkCollectionImpl")
@ProviderType
public interface ArtworkCollection extends ArtworkCollectionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.artwork.model.impl.ArtworkCollectionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ArtworkCollection, Long> COLLECTION_ID_ACCESSOR =
		new Accessor<ArtworkCollection, Long>() {
			@Override
			public Long get(ArtworkCollection artworkCollection) {
				return artworkCollection.getCollectionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ArtworkCollection> getTypeClass() {
				return ArtworkCollection.class;
			}
		};

	/**
	* Retourne l'AssetEntry correspondant � cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry()
		throws com.liferay.portal.kernel.exception.PortalException;

	/**
	* Retourne la liste des AssetCategory correspondant � cet item (via l'AssetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories()
		throws com.liferay.portal.kernel.exception.PortalException;
}