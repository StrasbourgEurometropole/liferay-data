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

package eu.strasbourg.service.like.model.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.like.model.LikeType;

/**
 * The extended model implementation for the Like service. Represents a row in the &quot;like_Like&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.like.model.Like} interface.
 * </p>
 *
 * @author Cedric Henry
 */
@ProviderType
public class LikeImpl extends LikeBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a like model instance should use the {@link eu.strasbourg.service.like.model.Like} interface instead.
	 */
	public LikeImpl() {
	}

	@Override
	public LikeType getLikeType() {
		return LikeType.get(this.getTypeId());
	}

	/**
	 * Retourne la version JSON d'un favoris
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		result.put("id", this.getLikeId());
		result.put("title", this.getTitle());
		result.put("isDislike", this.getIsDislike());
		result.put("typeId", this.getTypeId());
		result.put("entityId", this.getEntityId());
		result.put("entityGroupId", this.getEntityGroupId());

		return result;
	}
}