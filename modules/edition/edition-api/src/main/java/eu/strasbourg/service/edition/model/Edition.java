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
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.edition.model.impl.EditionImpl")
@ProviderType
public interface Edition extends EditionModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>eu.strasbourg.service.edition.model.impl.EditionImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Edition, Long> EDITION_ID_ACCESSOR =
		new Accessor<Edition, Long>() {

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
	 * Retourne l'AssetEntry rattaché cet item
	 */
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCategories();

	/**
	 * Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	 */
	public String getImageURL();

	/**
	 * Retourne le copyright de l'image principale
	 */
	public String getImageCopyright(java.util.Locale locale);

	/**
	 * Renvoie la liste des galleries auxquelles cette édition appartient
	 */
	public java.util.List<eu.strasbourg.service.edition.model.EditionGallery>
		getEditionGalleries();

	/**
	 * Renvoie la liste des IDs des galleries auxquelles cette édition
	 * appartient sous forme de String
	 */
	public String getEditionGalleriesIds();

	/**
	 * Renvoie la liste des galeries publiées
	 */
	public java.util.List<eu.strasbourg.service.edition.model.EditionGallery>
		getPublishedEditionGalleries();

	/**
	 * Renvoie l'URL de téléchargement du fichier (que ce soit un FileEntry ou
	 * une URL externe)
	 */
	public String getFileDownloadURL(java.util.Locale locale);

	/**
	 * Retourne le nom du fichier si un fichier uploadé est lié à l'édition, le titre de l'édition sinon
	 */
	public String getFileTitle(java.util.Locale locale);

	/**
	 * Renvoie la taille du fichier sous forme de String
	 */
	public String getFileSize(java.util.Locale locale);

	/**
	 * Renovie le type du fichier sous forme de String (si c'est une FileEntry -
	 * renvoie une chaîne vide si c'est une URL externe)
	 */
	public String getFileType(java.util.Locale locale);

	/**
	 * Renvoie la version live de l'édition, si elle existe
	 */
	public eu.strasbourg.service.edition.model.Edition getLiveVersion();

	/**
	 * Retourne les sources de l'édition
	 */
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getSources();

	/**
	 * Retourne les types de l'édition
	 */
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getTypes();

	/**
	 * Retourne le texte à afficher pour les types de l'édition
	 */
	public String getTypesLabels(java.util.Locale locale);

	/**
	 * Retourne la version JSON de l'édition
	 */
	public com.liferay.portal.kernel.json.JSONObject toJSON();

}