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

package eu.strasbourg.service.council.model.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.utils.AssetVocabularyHelper;

import java.util.List;

/**
 * The extended model implementation for the Official service. Represents a row in the &quot;council_Official&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.council.model.Official} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class OfficialImpl extends OfficialBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a official model instance should use the {@link eu.strasbourg.service.council.model.Official} interface instead.
	 */

	public static final String JSON_OFFICIAL_ID = "officialId";
	public static final String JSON_FULL_NAME = "fullName";
	public static final String JSON_IS_MUNICIPAL = "isMunicipal";
	public static final String JSON_IS_EUROMETROPOL = "isEurometropol";
	public static final String JSON_IS_ACTIVE = "isActive";

	public OfficialImpl() {
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Official.class.getName(), this.getOfficialId());
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via l'assetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper.getAssetEntryCategories(this.getAssetEntry());
	}

	/**
	 * Renvoie le nom de complet au format "Prénom NOM"
	 */
	@Override
	public String getFullName() {
		return StringUtil.upperCase(this.getLastname()) + " " +  this.getFirstname() ;
	}

	/**
	 * Renvoie l'élu au format JSON
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject jsonOfficial = JSONFactoryUtil.createJSONObject();

		jsonOfficial.put(JSON_OFFICIAL_ID, this.getOfficialId());
		jsonOfficial.put(JSON_FULL_NAME, this.getFullName());
		jsonOfficial.put(JSON_IS_MUNICIPAL, this.getIsMunicipal());
		jsonOfficial.put(JSON_IS_EUROMETROPOL, this.getIsEurometropolitan());
		jsonOfficial.put(JSON_IS_ACTIVE, this.getIsActive());

		return jsonOfficial;
	}

}