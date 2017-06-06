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

package eu.strasbourg.service.official.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Official service. Represents a row in the &quot;official_Official&quot; database table, with each column mapped to a property of this class.
 *
 * @author AngeliqueZUNINO
 * @see OfficialModel
 * @see eu.strasbourg.service.official.model.impl.OfficialImpl
 * @see eu.strasbourg.service.official.model.impl.OfficialModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.official.model.impl.OfficialImpl")
@ProviderType
public interface Official extends OfficialModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.official.model.impl.OfficialImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Official, Long> OFFICIAL_ID_ACCESSOR = new Accessor<Official, Long>() {
			@Override
			public Long get(Official official) {
				return official.getOfficialId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Official> getTypeClass() {
				return Official.class;
			}
		};

	/**
	* Renvoie la version live de l'élu, si il existe
	*/
	public eu.strasbourg.service.official.model.Official getLiveVersion();

	/**
	* Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	*/
	public java.lang.String getImageURL();

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Vérifie si la catégorie fait parti des catégories correspondants à l'élu
	*/
	public boolean isCategoryOfficial(long categoryId);

	/**
	* Retourne les territoires du lieu
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTerritories();

	/**
	* Retourne l' id catégorie Fonction ville correspondant à l'élu
	*/
	public com.liferay.asset.kernel.model.AssetCategory getFonctionCity();

	/**
	* Retourne la liste des quartiers correspondants à l'élu
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getDistricts();

	/**
	* Retourne la catégorie Groupe Politique ville correspondant à l'élu
	*/
	public com.liferay.asset.kernel.model.AssetCategory getPoliticalGroupCity();

	/**
	* Retourne la catégorie Fonction eurometropole correspondant à l'élu
	*/
	public com.liferay.asset.kernel.model.AssetCategory getFonctionEurometropole();

	/**
	* Retourne la catégorie Fonction commune correspondant à l'élu
	*/
	public com.liferay.asset.kernel.model.AssetCategory getFonctionTown();

	/**
	* Retourne la commune correspondants à l'élu
	*/
	public com.liferay.asset.kernel.model.AssetCategory getTown();

	/**
	* Retourne la catégorie Groupe Politique eurométropole correspondant à
	* l'élu
	*/
	public com.liferay.asset.kernel.model.AssetCategory getPoliticalGroupEurometropole();

	/**
	* Retourne la catégorie Autres mandats correspondant à l'élu
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getOthersMandates();

	/**
	* Retourne le nom féminin/masculin de la catégorie
	*
	* @throws PortalException
	*/
	public java.lang.String getName(
		com.liferay.asset.kernel.model.AssetCategory category,
		java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException;

	/**
	* Vérifie si c'est un élu municipal
	*
	* @throws PortalException
	*/
	public boolean isAssistant()
		throws com.liferay.portal.kernel.exception.PortalException;

	/**
	* Vérifie si c'est un élu municipal
	*
	* @throws PortalException
	*/
	public boolean isEluMunicipal()
		throws com.liferay.portal.kernel.exception.PortalException;

	/**
	* Vérifie si c'est un élu eurométropolitain
	*
	* @throws PortalException
	*/
	public boolean isEluEurometropole()
		throws com.liferay.portal.kernel.exception.PortalException;

	/**
	* Vérifie si c'est un élu eurométropolitain
	*
	* @throws PortalException
	*/
	public boolean getCommunalMembership()
		throws com.liferay.portal.kernel.exception.PortalException;
}