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

package eu.strasbourg.service.favorite.model.impl;

import java.util.Locale;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import aQute.bnd.annotation.ProviderType;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.favorite.model.FavoriteType;

/**
 * The extended model implementation for the Favorite service. Represents a row
 * in the &quot;favorite_Favorite&quot; database table, with each column mapped
 * to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.favorite.model.Favorite} interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class FavoriteImpl extends FavoriteBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a favorite
	 * model instance should use the {@link
	 * eu.strasbourg.service.favorite.model.Favorite} interface instead.
	 */
	public FavoriteImpl() {
	}

	@Override
	public boolean hasAssetEntry() {
		return !this.getFavoriteType().getFavoriteClass().equals(String.class);
	}

	@Override
	public AssetEntry getAssetEntry() {
        String className=this.getFavoriteType().getFavoriteClass()!=null
                ?this.getFavoriteType().getFavoriteClass().getName():null;
		return AssetEntryLocalServiceUtil.fetchEntry(className,
				this.getEntityId());
	}

	@Override
	public FavoriteType getFavoriteType() {
		return FavoriteType.get(this.getTypeId());
	}

	@Override
	public boolean isOnDashboard() {
		if(Validator.isNotNull(this.getOnDashboardDate()))
			return true;
		return false;
	}

	/**
	 * Retourne la version JSON d'un favoris
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject result = JSONFactoryUtil.createJSONObject();

		result.put("id", this.getFavoriteId());
		result.put("title", this.getTitle());
		result.put("url", this.getUrl());
		result.put("typeId", this.getTypeId());
		result.put("entityId", this.getEntityId());
		if (this.getAssetEntry() != null) {
			result.put("entityTitle", this.getAssetEntry().getTitle(Locale.FRANCE));
		}

		return result;
	}
}