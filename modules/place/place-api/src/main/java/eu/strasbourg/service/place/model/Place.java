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

package eu.strasbourg.service.place.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Place service. Represents a row in the &quot;place_Place&quot; database table, with each column mapped to a property of this class.
 *
 * @author Angelique Zunino Champougny
 * @see PlaceModel
 * @see eu.strasbourg.service.place.model.impl.PlaceImpl
 * @see eu.strasbourg.service.place.model.impl.PlaceModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.place.model.impl.PlaceImpl")
@ProviderType
public interface Place extends PlaceModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.place.model.impl.PlaceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Place, Long> PLACE_ID_ACCESSOR = new Accessor<Place, Long>() {
			@Override
			public Long get(Place place) {
				return place.getPlaceId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Place> getTypeClass() {
				return Place.class;
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
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	*/
	public java.lang.String getImageURL();

	/**
	* Retourne le copyright de l'image principale
	*/
	public java.lang.String getImageCopyright(java.util.Locale locale);

	/**
	* Retourne le prix rattaché au lieu
	*/
	public eu.strasbourg.service.place.model.Price getPrice();

	/**
	* Retourne les ScheduleExceptions du lieu
	*/
	public java.util.List<eu.strasbourg.service.place.model.ScheduleException> getScheduleExceptions();

	/**
	* Retourne les SubPlaces du lieux
	*/
	public java.util.List<eu.strasbourg.service.place.model.SubPlace> getSubPlaces();

	/**
	* Retourne les Periods du lieux
	*/
	public java.util.List<eu.strasbourg.service.place.model.Period> getPeriods();

	/**
	* Retourne le territoire du lieu
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getSources();

	/**
	* Retourne les types du lieu
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTypes();
}