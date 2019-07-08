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

package eu.strasbourg.service.gtfs.model.impl;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.gtfs.model.Arret;
import eu.strasbourg.service.gtfs.model.Direction;
import eu.strasbourg.service.gtfs.service.DirectionLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;

/**
 * The extended model implementation for the Arret service. Represents a row in the &quot;gtfs_Arret&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.gtfs.model.Arret} interface.
 * </p>
 *
 * @author Cedric Henry
 */
@ProviderType
public class ArretImpl extends ArretBaseImpl {

	private static final long serialVersionUID = 3843907860876078856L;
	
	public static final String TYPE_BUS = "bus";
	public static final String TYPE_TRAM = "tram";

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a arret model instance should use the {@link eu.strasbourg.service.gtfs.model.Arret} interface instead.
	 */
	public ArretImpl() {
	}
	
	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Arret.class.getName(), this.getArretId());
	}
	
	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper.getAssetEntryCategories(this.getAssetEntry());
	}
	
	/**
	 * Renvoie la liste des Directions de cet arret
	 */
	@Override
	public List<Direction> getDirections() {
		return DirectionLocalServiceUtil.getByStopId(this.getStopId());
	}
	
	/**
	 * Renvoie la liste des Directions de cet arret
	 */
	@Override
	public List<Direction> getLignes() {
		return DirectionLocalServiceUtil.getByStopId(this.getStopId());
	}
	
	/**
	 * Renvoie la correspondance du type d'arret en format texte
	 */
	@Override
	public String getTypeText() {
		return this.getType() == 0 ? TYPE_TRAM : TYPE_BUS;
	}
	
	/**
	 * Renvoie le JSON de l'entite au format GeoJSON
	 */
	@Override
	public JSONObject getGeoJSON() {
		JSONObject feature = JSONFactoryUtil.createJSONObject();
		
			feature.put("type", "Feature");
			
			JSONObject geometry = JSONFactoryUtil.createJSONObject();
			
				geometry.put("type", "Point");
				
				JSONArray coordinates = JSONFactoryUtil.createJSONArray();
				
					coordinates.put(Float.valueOf(this.getLongitude()));
					coordinates.put(Float.valueOf(this.getLatitude()));
					
				geometry.put("coordinates", coordinates);
				
			feature.put("geometry", geometry);
			
			JSONObject properties = JSONFactoryUtil.createJSONObject();
			
				properties.put("name", this.getTitle());
				properties.put("type", this.getTypeText());
				properties.put("code", this.getCode());
			
			feature.put("properties", properties);
		
		return feature;
	}
	
}