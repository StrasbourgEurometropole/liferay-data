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

package eu.strasbourg.service.artwork.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.artwork.model.ArtworkCollection;

/**
 * The extended model implementation for the ArtworkCollection service. Represents a row in the &quot;artwork_ArtworkCollection&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.artwork.model.ArtworkCollection} interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class ArtworkCollectionImpl extends ArtworkCollectionBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a artwork collection model instance should use the {@link eu.strasbourg.service.artwork.model.ArtworkCollection} interface instead.
	 */
	public ArtworkCollectionImpl() {
	}
	
	/**
	 * Retourne l'AssetEntry correspondant à cet item
	 */
	public AssetEntry getAssetEntry() throws PortalException {
		return AssetEntryLocalServiceUtil.getEntry(ArtworkCollection.class.getName(),
			this.getCollectionId());
	}
	
	/**
	 * Retourne la liste des AssetCategory correspondant à cet item (via l'AssetEntry)
	 */
	public List<AssetCategory> getCategories() throws PortalException {
		long[] categoryIds = this.getAssetEntry().getCategoryIds();
		List<AssetCategory> categories = new ArrayList<AssetCategory>();
		for (long categoryId : categoryIds) {
			AssetCategory category = AssetCategoryLocalServiceUtil.getAssetCategory(categoryId);
			categories.add(category);
		}
		return categories;
	}
}