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
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.service.ArtworkCollectionLocalServiceUtil;
import eu.strasbourg.service.artwork.service.ArtworkLocalServiceUtil;
import eu.strasbourg.utils.FileEntryHelper;

/**
 * The extended model implementation for the Artwork service. Represents a row
 * in the &quot;artwork_Artwork&quot; database table, with each column mapped to
 * a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.artwork.model.Artwork} interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class ArtworkImpl extends ArtworkBaseImpl {

	private static final long serialVersionUID = 6509106661147599704L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a artwork
	 * model instance should use the {@link
	 * eu.strasbourg.service.artwork.model.Artwork} interface instead.
	 */
	public ArtworkImpl() {
	}

	/**
	 * Retourne l'AssetEntry correspondant à cet item
	 */
	@Override
	public AssetEntry getAssetEntry() throws PortalException {
		return AssetEntryLocalServiceUtil.getEntry(Artwork.class.getName(),
			this.getArtworkId());
	}
	
	/**
	 * Retourne la liste des AssetCategory correspondant à cet item (via l'AssetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() throws PortalException {
		long[] categoryIds = this.getAssetEntry().getCategoryIds();
		List<AssetCategory> categories = new ArrayList<AssetCategory>();
		for (long categoryId : categoryIds) {
			AssetCategory category = AssetCategoryLocalServiceUtil.getAssetCategory(categoryId);
			categories.add(category);
		}
		return categories;
	}
	
	/**
	 * Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	 * 
	 * @throws PortalException
	 * @throws NumberFormatException
	 */
	@Override
	public String getImageURL() {
		return FileEntryHelper.getFileEntryURL(this.getImageId());
	}

	@Override
	public List<ArtworkCollection> getArtworkCollections() {
		return ArtworkCollectionLocalServiceUtil.getArtworkArtworkCollections(this.getArtworkId());
	}

	@Override
	public String getArtworkCollectionsIds() {
		List<ArtworkCollection> collections = this.getArtworkCollections();
		String ids = "";
		for (ArtworkCollection collection : collections) {
			if (ids.length() > 0) {
				ids += ",";
			}
			ids += collection.getCollectionId();
		}
		return ids;
	}

	/**
	 * Renvoie la version live de l'oeuvre, si elle existe
	 */
	public Artwork getLiveVersion() {
		long groupId = this.getGroupId();
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		if (group == null || !group.isStagingGroup()) {
			return null;
		}
		long liveGroupId = group.getLiveGroupId();
		Artwork liveArtwork= ArtworkLocalServiceUtil.fetchArtworkByUuidAndGroupId(this.getUuid(), liveGroupId);
		return liveArtwork;
	}
}