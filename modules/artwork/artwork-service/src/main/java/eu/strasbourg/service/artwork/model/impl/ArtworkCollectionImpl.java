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
import java.util.Locale;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetCategoryProperty;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryPropertyLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.service.ArtworkCollectionLocalServiceUtil;
import eu.strasbourg.service.artwork.service.ArtworkLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;

/**
 * The extended model implementation for the ArtworkCollection service.
 * Represents a row in the &quot;artwork_ArtworkCollection&quot; database table,
 * with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.artwork.model.ArtworkCollection}
 * interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class ArtworkCollectionImpl extends ArtworkCollectionBaseImpl {

	private static final long serialVersionUID = -3072412689727443267L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a artwork
	 * collection model instance should use the {@link
	 * eu.strasbourg.service.artwork.model.ArtworkCollection} interface instead.
	 */
	public ArtworkCollectionImpl() {
	}

	/**
	 * Retourne l'AssetEntry correspondant à cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(
			ArtworkCollection.class.getName(), this.getCollectionId());
	}

	/**
	 * Retourne la liste des AssetCategory correspondant à cet item (via
	 * l'AssetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper.getAssetEntryCategories(this.getAssetEntry());
	}

	/**
	 * Retourne l'URL de l'image à partir de l'id du DLFileEntry
	 */
	@Override
	public String getImageURL() {
		return FileEntryHelper.getFileEntryURL(this.getImageId());
	}

	/**
	 * Retourne le copyright de l'image principale
	 */
	@Override
	public String getImageCopyright(Locale locale) {
		return FileEntryHelper.getImageCopyright(this.getImageId(), locale);
	}

	/**
	 * Retourne la liste des oeuvres
	 */
	@Override
	public List<Artwork> getArtworks() {
		return ArtworkLocalServiceUtil
			.getArtworkCollectionArtworks(this.getCollectionId());
	}

	/**
	 * Retourne la liste des ids d'oeuvres sous forme de String
	 */
	@Override
	public String getArtworksIds() {
		List<Artwork> artworks = this.getArtworks();
		String ids = "";
		for (Artwork artwork : artworks) {
			if (ids.length() > 0) {
				ids += ",";
			}
			ids += artwork.getArtworkId();
		}
		return ids;
	}

	/**
	 * Retourne la liste des oeuvres publiées
	 */
	@Override
	public List<Artwork> getPublishedArtworks() {
		List<Artwork> artworks = this.getArtworks();
		List<Artwork> result = new ArrayList<Artwork>();
		for (Artwork artwork : artworks) {
			if (artwork.isApproved()) {
				result.add(artwork);
			}
		}
		return result;
	}

	/**
	 * Retourne la version live de la collection, si elle existe
	 */
	@Override
	public ArtworkCollection getLiveVersion() {
		long groupId = this.getGroupId();
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		if (group == null || !group.isStagingGroup()) {
			return null;
		}
		long liveGroupId = group.getLiveGroupId();
		ArtworkCollection liveCollection = ArtworkCollectionLocalServiceUtil
			.fetchArtworkCollectionByUuidAndGroupId(this.getUuid(),
				liveGroupId);
		return liveCollection;
	}

	/*
	 * Catégories
	 */

	/**
	 * Retourne les sources de la collection
	 */
	@Override
	public List<AssetCategory> getSources() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(), "musees");
	}
	
	/**
	 * Retourne la classe css correspondante à la source
	 */
	@Override
	public String getSourceCSSClass() {
		List<AssetCategory> sources = this.getSources();
		String cssClass = "";
		if (sources.size() > 0) {
			try {
				AssetCategoryProperty property = AssetCategoryPropertyLocalServiceUtil
					.getCategoryProperty(sources.get(0).getCategoryId(), "css");
				cssClass = property.getValue();
			} catch (PortalException e) {
				// Pas de propriété avec ce nom, rien de spécial à faire
			}
		}
		return cssClass;
	}
}