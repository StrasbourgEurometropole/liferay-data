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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlaceCategories}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlaceCategories
 * @generated
 */
public class PlaceCategoriesWrapper
	extends BaseModelWrapper<PlaceCategories>
	implements ModelWrapper<PlaceCategories>, PlaceCategories {

	public PlaceCategoriesWrapper(PlaceCategories placeCategories) {
		super(placeCategories);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("placeCategoriesId", getPlaceCategoriesId());
		attributes.put("categoriesIds", getCategoriesIds());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long placeCategoriesId = (Long)attributes.get("placeCategoriesId");

		if (placeCategoriesId != null) {
			setPlaceCategoriesId(placeCategoriesId);
		}

		String categoriesIds = (String)attributes.get("categoriesIds");

		if (categoriesIds != null) {
			setCategoriesIds(categoriesIds);
		}
	}

	/**
	 * Returns the categories IDs of this place categories.
	 *
	 * @return the categories IDs of this place categories
	 */
	@Override
	public String getCategoriesIds() {
		return model.getCategoriesIds();
	}

	/**
	 * Returns the place categories ID of this place categories.
	 *
	 * @return the place categories ID of this place categories
	 */
	@Override
	public long getPlaceCategoriesId() {
		return model.getPlaceCategoriesId();
	}

	/**
	 * Returns the primary key of this place categories.
	 *
	 * @return the primary key of this place categories
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the uuid of this place categories.
	 *
	 * @return the uuid of this place categories
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the categories IDs of this place categories.
	 *
	 * @param categoriesIds the categories IDs of this place categories
	 */
	@Override
	public void setCategoriesIds(String categoriesIds) {
		model.setCategoriesIds(categoriesIds);
	}

	/**
	 * Sets the place categories ID of this place categories.
	 *
	 * @param placeCategoriesId the place categories ID of this place categories
	 */
	@Override
	public void setPlaceCategoriesId(long placeCategoriesId) {
		model.setPlaceCategoriesId(placeCategoriesId);
	}

	/**
	 * Sets the primary key of this place categories.
	 *
	 * @param primaryKey the primary key of this place categories
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the uuid of this place categories.
	 *
	 * @param uuid the uuid of this place categories
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected PlaceCategoriesWrapper wrap(PlaceCategories placeCategories) {
		return new PlaceCategoriesWrapper(placeCategories);
	}

}