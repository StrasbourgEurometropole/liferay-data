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

package eu.strasbourg.service.csmap.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class PlaceCategoriesSoap implements Serializable {

	public static PlaceCategoriesSoap toSoapModel(PlaceCategories model) {
		PlaceCategoriesSoap soapModel = new PlaceCategoriesSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPlaceCategoriesId(model.getPlaceCategoriesId());
		soapModel.setCategoriesIds(model.getCategoriesIds());

		return soapModel;
	}

	public static PlaceCategoriesSoap[] toSoapModels(PlaceCategories[] models) {
		PlaceCategoriesSoap[] soapModels =
			new PlaceCategoriesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PlaceCategoriesSoap[][] toSoapModels(
		PlaceCategories[][] models) {

		PlaceCategoriesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new PlaceCategoriesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PlaceCategoriesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PlaceCategoriesSoap[] toSoapModels(
		List<PlaceCategories> models) {

		List<PlaceCategoriesSoap> soapModels =
			new ArrayList<PlaceCategoriesSoap>(models.size());

		for (PlaceCategories model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PlaceCategoriesSoap[soapModels.size()]);
	}

	public PlaceCategoriesSoap() {
	}

	public long getPrimaryKey() {
		return _placeCategoriesId;
	}

	public void setPrimaryKey(long pk) {
		setPlaceCategoriesId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPlaceCategoriesId() {
		return _placeCategoriesId;
	}

	public void setPlaceCategoriesId(long placeCategoriesId) {
		_placeCategoriesId = placeCategoriesId;
	}

	public String getCategoriesIds() {
		return _categoriesIds;
	}

	public void setCategoriesIds(String categoriesIds) {
		_categoriesIds = categoriesIds;
	}

	private String _uuid;
	private long _placeCategoriesId;
	private String _categoriesIds;

}