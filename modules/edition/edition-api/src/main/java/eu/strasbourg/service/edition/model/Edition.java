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

package eu.strasbourg.service.edition.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Edition service. Represents a row in the &quot;edition_Edition&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see EditionModel
 * @see eu.strasbourg.service.edition.model.impl.EditionImpl
 * @see eu.strasbourg.service.edition.model.impl.EditionModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.edition.model.impl.EditionImpl")
@ProviderType
public interface Edition extends EditionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.edition.model.impl.EditionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Edition, Long> EDITION_ID_ACCESSOR = new Accessor<Edition, Long>() {
			@Override
			public Long get(Edition edition) {
				return edition.getEditionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Edition> getTypeClass() {
				return Edition.class;
			}
		};

	/**
	* Retourne l'AssetEntry rattach�e � cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Renvoie la liste des AssetCategory rattach�es � cet item (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories()
		throws com.liferay.portal.kernel.exception.PortalException;

	/**
	* Renvoie l'URL de l'image � partir de l'id du DLFileEntry
	*
	* @throws PortalException
	* @throws NumberFormatException
	*/
	public java.lang.String getImageURL();

	public java.util.List<eu.strasbourg.service.edition.model.EditionGallery> getEditionGalleries();

	public java.lang.String getEditionGalleriesIds();
}