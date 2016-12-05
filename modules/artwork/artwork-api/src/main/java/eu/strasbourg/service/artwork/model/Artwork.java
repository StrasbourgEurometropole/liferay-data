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
 * The extended model interface for the Artwork service. Represents a row in the &quot;artwork_Artwork&quot; database table, with each column mapped to a property of this class.
 *
 * @author BenjaminBini
 * @see ArtworkModel
 * @see eu.strasbourg.service.artwork.model.impl.ArtworkImpl
 * @see eu.strasbourg.service.artwork.model.impl.ArtworkModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.artwork.model.impl.ArtworkImpl")
@ProviderType
public interface Artwork extends ArtworkModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.artwork.model.impl.ArtworkImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Artwork, Long> ARTWORK_ID_ACCESSOR = new Accessor<Artwork, Long>() {
			@Override
			public Long get(Artwork artwork) {
				return artwork.getArtworkId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Artwork> getTypeClass() {
				return Artwork.class;
			}
		};

	/**
	* Retourne l'AssetEntry correspondant à cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Retourne la liste des AssetCategory correspondant à cet item (via
	* l'AssetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Retourne l'URL de l'image à partir de l'id du DLFileEntry
	*/
	public java.lang.String getImageURL();

	/**
	* Retourne le copyright de l'image principale
	*/
	public java.lang.String getImageCopyright(java.util.Locale locale);

	/**
	* Retourne la légende de l'image principale
	*/
	public java.lang.String getImageLegend(java.util.Locale locale);

	/**
	* Retourne la légende de l'image principale suivie de son copyright
	*/
	public java.lang.String getImageLegendAndCopyright(java.util.Locale locale);

	/**
	* Retourne la liste des URL publiques des images additionnelles
	*/
	public java.util.List<java.lang.String> getImagesURLs();

	/**
	* Retourne la liste des URL publiques des images additionnelles
	*/
	public java.util.List<java.lang.String> getImagesLegendsAndCopyrights(
		java.util.Locale locale);

	/**
	* Retourne la liste des collections d'oeuvres
	*/
	public java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkCollections();

	/**
	* Retourne la liste des ids de collections d'oeuvres sous forme de String
	*/
	public java.lang.String getArtworkCollectionsIds();

	/**
	* Retourne la liste des collections d'oeuvres publiées
	*/
	public java.util.List<eu.strasbourg.service.artwork.model.ArtworkCollection> getPublishedArtworkCollections();

	/**
	* Retourne la version live de l'oeuvre, si elle existe
	*/
	public eu.strasbourg.service.artwork.model.Artwork getLiveVersion();

	/**
	* Retourne les sources de l'oeuvre
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getSources();

	/**
	* Retourne la classe css correspondante à la source
	*/
	public java.lang.String getSourceCSSClass();
}