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
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.gtfs.model.Arret;
import eu.strasbourg.service.gtfs.model.Direction;
import eu.strasbourg.service.gtfs.service.DirectionLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;

/**
 * The extended model implementation for the Ligne service. Represents a row in the &quot;gtfs_Ligne&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.gtfs.model.Ligne} interface.
 * </p>
 *
 * @author Cedric Henry
 */
@ProviderType
public class LigneImpl extends LigneBaseImpl {

	private static final long serialVersionUID = -4984282213511478569L;
	public final static Log log = LogFactoryUtil.getLog(LigneImpl.class);

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a ligne model instance should use the {@link eu.strasbourg.service.gtfs.model.Ligne} interface instead.
	 */
	public LigneImpl() {
	}
	
	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Arret.class.getName(), this.getLigneId());
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
	 * Renvoie la liste des Directions de cette ligne
	 */
	@Override
	public List<Direction> getDirections() {
		return DirectionLocalServiceUtil.getByRouteId(this.getRouteId());
	}
	
	/**
	 * Retourne les couleurs de la ligne au format JSON
	 */
	@Override
	public JSONObject getColors() {
		try {
			JSONObject ligneColors;
			ligneColors = JSONFactoryUtil.createJSONObject(this.getShortName());
		
			ligneColors.put("backgroundColor", this.getBackgroundColor());
			ligneColors.put("textColor", this.getTextColor());
		
			return ligneColors;
		} catch (JSONException e) {
			log.error(e);
			return JSONFactoryUtil.createJSONObject();
		}
	}
	
}