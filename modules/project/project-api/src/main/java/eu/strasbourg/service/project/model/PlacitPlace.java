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

package eu.strasbourg.service.project.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the PlacitPlace service. Represents a row in the &quot;project_PlacitPlace&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see PlacitPlaceModel
 * @see eu.strasbourg.service.project.model.impl.PlacitPlaceImpl
 * @see eu.strasbourg.service.project.model.impl.PlacitPlaceModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.project.model.impl.PlacitPlaceImpl")
@ProviderType
public interface PlacitPlace extends PlacitPlaceModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.project.model.impl.PlacitPlaceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<PlacitPlace, Long> PLACIT_PLACE_ID_ACCESSOR = new Accessor<PlacitPlace, Long>() {
			@Override
			public Long get(PlacitPlace placitPlace) {
				return placitPlace.getPlacitPlaceId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<PlacitPlace> getTypeClass() {
				return PlacitPlace.class;
			}
		};

	/**
	* Retourne le projet du lieu Placit
	*/
	public eu.strasbourg.service.project.model.Project getProject();

	/**
	* Retourne la participation du lieu Placit
	*/
	public eu.strasbourg.service.project.model.Participation getParticipation();

	/**
	* Retourne l'AssetEntry rattaché à cette entité
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Retourne la liste des AssetCategory rattachées à cette entité (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Retourne le nom du lieu SIG
	*/
	public java.lang.String getSIGPlaceAlias(java.util.Locale locale);

	/**
	* Retourne le nom du lieu SIG ou "manuel"
	*/
	public java.lang.String getPlaceAlias(java.util.Locale locale);
}