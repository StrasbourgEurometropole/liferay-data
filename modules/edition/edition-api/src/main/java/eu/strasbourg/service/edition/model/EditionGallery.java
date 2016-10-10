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
 * The extended model interface for the EditionGallery service. Represents a row in the &quot;edition_EditionGallery&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see EditionGalleryModel
 * @see eu.strasbourg.service.edition.model.impl.EditionGalleryImpl
 * @see eu.strasbourg.service.edition.model.impl.EditionGalleryModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.edition.model.impl.EditionGalleryImpl")
@ProviderType
public interface EditionGallery extends EditionGalleryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.edition.model.impl.EditionGalleryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<EditionGallery, Long> GALLERY_ID_ACCESSOR = new Accessor<EditionGallery, Long>() {
			@Override
			public Long get(EditionGallery editionGallery) {
				return editionGallery.getGalleryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<EditionGallery> getTypeClass() {
				return EditionGallery.class;
			}
		};

	/**
	* Retourne l'AssetEntry rattach�e � cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Renvoie la liste des AssetCategory rattach�es � cet item (via l'assetEntry)
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

	public java.util.List<eu.strasbourg.service.edition.model.Edition> getEditions();

	public java.lang.String getEditionsIds();

	/**
	* Renvoie la version live de la galerie d'édition, si elle existe
	*/
	public eu.strasbourg.service.edition.model.EditionGallery getLiveVersion();
}