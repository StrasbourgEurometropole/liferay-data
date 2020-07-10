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
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.council.model.Official;
import eu.strasbourg.service.council.model.OfficialTypeCouncil;
import eu.strasbourg.service.council.model.Type;
import eu.strasbourg.service.council.service.OfficialLocalServiceUtil;
import eu.strasbourg.service.council.service.OfficialTypeCouncilLocalServiceUtil;
import eu.strasbourg.service.council.service.ProcurationLocalServiceUtil;
import eu.strasbourg.service.council.service.TypeLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

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

	/** Var name du JSON des électeurs */
	public static final String JSON_OFFICIAL_ID = "officialId";
	public static final String JSON_FULL_NAME = "fullName";
	public static final String JSON_TYPES_IDS = "typesIds";
	public static final String JSON_IS_ACTIVE = "isActive";
	public static final String JSON_LAST_ACTIVITY = "lastActivity";
	public static final String JSON_LAST_SIGN_IN_DEVICE_INFO = "lastSingInDeviceInfo";

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
	 * Renvoie si l'electeur est noté absent pour la session données
	 */
	@Override
	public boolean isNotedAbsent(long councilSessionId) {
		return ProcurationLocalServiceUtil
				.findAbsenceForCouncilSession(councilSessionId, this.getOfficialId()) != null;
	}
	
	/**
	 * Renvoie le statut de connection de l'utilisateur
	 * @return True si la dernière connection date de moins de 15sec
	 */
	@Override
	public boolean isConnected() {
		boolean result = false;
		
		if (this.getLastActivity() != null) {
			Calendar calendarLastActivity = Calendar.getInstance();
			calendarLastActivity.setTime(this.getLastActivity());
			
			Calendar calendarRefrence = Calendar.getInstance();
			calendarRefrence.add(Calendar.SECOND, -15);
			
			if (calendarLastActivity.compareTo(calendarRefrence) > 0) {
				result = true;
			}
		}
		
		return result;
	}

	/**
	 * Renvoie les types de conseil rattachés à cet élu
	 */
	@Override
	public List<Type> getCouncilTypes() {
		List<Type> types = new ArrayList<Type>();
		List<Long> typeCouncilList = OfficialTypeCouncilLocalServiceUtil.findByOfficialId(this.getOfficialId()).stream()
				.map(o -> o.getTypeId()).collect(Collectors.toList());
		for (Long typeCouncil : typeCouncilList) {
			Type type = TypeLocalServiceUtil.fetchType(typeCouncil);
			if(Validator.isNotNull(type)){
				types.add(type);
			}
		}
		return types;
	}

	/**
	 * Renvoie un strind 'id types de conseil rattachés à cet élu
	 */
	@Override
	public String getCouncilTypesIds() {
		String types = OfficialTypeCouncilLocalServiceUtil.findByOfficialId(this.getOfficialId()).stream()
				.map(o -> ""+o.getTypeId()).collect(Collectors.joining(", "));
		return types;
	}

	/**
	 * Renvoie l'élu au format JSON
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject jsonOfficial = JSONFactoryUtil.createJSONObject();
		
		jsonOfficial.put(JSON_OFFICIAL_ID, this.getOfficialId());
		jsonOfficial.put(JSON_FULL_NAME, this.getFullName());
		jsonOfficial.put(JSON_TYPES_IDS, this.getCouncilTypesIds());
		jsonOfficial.put(JSON_IS_ACTIVE, this.getIsActive());
		jsonOfficial.put(JSON_LAST_ACTIVITY, this.getLastActivity());
		jsonOfficial.put(JSON_LAST_SIGN_IN_DEVICE_INFO, this.getLastSignInDeviceInfo());

		return jsonOfficial;
	}

}