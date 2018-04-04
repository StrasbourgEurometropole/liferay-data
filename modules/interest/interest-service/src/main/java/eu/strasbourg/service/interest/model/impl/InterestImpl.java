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

package eu.strasbourg.service.interest.model.impl;

import java.util.List;
import java.util.Locale;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import aQute.bnd.annotation.ProviderType;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.interest.model.Interest;
import eu.strasbourg.utils.AssetVocabularyHelper;

/**
 * The extended model implementation for the Interest service. Represents a row in the &quot;interest_Interest&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.interest.model.Interest} interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class InterestImpl extends InterestBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a interest model instance should use the {@link eu.strasbourg.service.interest.model.Interest} interface instead.
	 */
	public InterestImpl() {
	}
	

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Interest.class.getName(),
			this.getInterestId());
	}
	
	/**
	 * Retourne la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper
			.getAssetEntryCategories(this.getAssetEntry());
	}
	
	/**
	 * Retourne le type du centre d'intérêt
	 */
	@Override
	public AssetCategory getType() {
		return AssetCategoryLocalServiceUtil.fetchAssetCategory(this.getTypeId());
	}
	
	/**
	 * Retourne la version JSON du centre d'intérêt
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject jsonInterest = JSONFactoryUtil.createJSONObject();
		jsonInterest.put("id", this.getInterestId());
		jsonInterest.put("name", this.getTitle(Locale.FRANCE));
		jsonInterest.put("description", this.getDescription(Locale.FRANCE));
		AssetCategory typeCategory = this.getType();
		if (typeCategory != null) {
			String typeName = typeCategory.getTitle(Locale.FRANCE);
			jsonInterest.put("type", typeName);

			String typeExternalId = AssetVocabularyHelper.getExternalId(typeCategory);
			jsonInterest.put("typeId", Validator.isNull(typeExternalId) ? this.getTypeId() : typeExternalId);
		}
		JSONArray categories = JSONFactoryUtil.createJSONArray();
		for (AssetCategory category : this.getCategories()) {
			categories.put(category.getCategoryId());
		}
		jsonInterest.put("categories", categories);
		return jsonInterest;
	}
}