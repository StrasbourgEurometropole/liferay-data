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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.artwork.model.Artwork;
import eu.strasbourg.service.artwork.model.ArtworkCollection;
import eu.strasbourg.service.artwork.service.ArtworkCollectionLocalServiceUtil;
import eu.strasbourg.service.artwork.service.ArtworkLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
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
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Artwork.class.getName(),
			this.getArtworkId());
	}

	/**
	 * Retourne la liste des AssetCategory correspondant à cet item (via
	 * l'AssetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper
			.getAssetEntryCategories(this.getAssetEntry());
	}

	/**
	 * Retourne l'URL de l'image à partir de l'id du DLFileEntry
	 * 
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
	 * Retourne la liste des URL publiques des images additionnelles
	 */
	@Override
	public List<String> getImagesURLs() {
		List<String> URLs = new ArrayList<String>();
		for (String imageIdStr : this.getImagesIds().split(",")) {
			Long imageId = GetterUtil.getLong(imageIdStr);
			if (Validator.isNotNull(imageId)) {
				String imageURL = FileEntryHelper.getFileEntryURL(imageId);
				URLs.add(imageURL);
			}
		}
		return URLs;
	}

	/**
	 * Retourne la liste des collections d'oeuvres
	 */
	@Override
	public List<ArtworkCollection> getArtworkCollections() {
		return ArtworkCollectionLocalServiceUtil
			.getArtworkArtworkCollections(this.getArtworkId());
	}

	/**
	 * Retourne la liste des ids de collections d'oeuvres sous forme de String
	 */
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
	 * Retourne la liste des collections d'oeuvres publiées
	 */
	@Override
	public List<ArtworkCollection> getPublishedArtworkCollections() {
		List<ArtworkCollection> collections = this.getArtworkCollections();
		List<ArtworkCollection> result = new ArrayList<ArtworkCollection>();
		for (ArtworkCollection collection : collections) {
			if (collection.isApproved()) {
				result.add(collection);
			}
		}
		return result;
	}

	/**
	 * Retourne la version live de l'oeuvre, si elle existe
	 */
	@Override
	public Artwork getLiveVersion() {
		long groupId = this.getGroupId();
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		if (group == null || !group.isStagingGroup()) {
			return null;
		}
		long liveGroupId = group.getLiveGroupId();
		Artwork liveArtwork = ArtworkLocalServiceUtil
			.fetchArtworkByUuidAndGroupId(this.getUuid(), liveGroupId);
		return liveArtwork;
	}

	/*
	 * Catégories
	 */

	/**
	 * Retourne les sources de l'oeuvre
	 */
	@Override
	public List<AssetCategory> getSources() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(), "source des oeuvres");
	}

	/**
	 * Retourne la classe css correspondante à la source
	 */
	@Override
	public String getSourceCSSClass() {
		List<AssetCategory> sources = this.getSources();
		String cssClass = "cs-default";
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