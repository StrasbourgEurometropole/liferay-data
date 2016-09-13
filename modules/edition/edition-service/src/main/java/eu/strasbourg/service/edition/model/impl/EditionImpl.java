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

package eu.strasbourg.service.edition.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.EditionGalleryLocalServiceUtil;
import eu.strasbourg.utils.DLFileEntryHelper;

/**
 * The extended model implementation for the Edition service. Represents a row
 * in the &quot;edition&quot; database table, with each column mapped to a
 * property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.edition.model.Edition} interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class EditionImpl extends EditionBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a edition
	 * model instance should use the {@link
	 * eu.strasbourg.service.edition.model.Edition} interface instead.
	 */
	public EditionImpl() {
	}

	/**
	 * Retourne l'AssetEntry rattachée à cet item
	 */
	public AssetEntry getAssetEntry() {
		try {
			return AssetEntryLocalServiceUtil.getEntry(Edition.class.getName(),
				this.getEditionId());
		} catch (PortalException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	public List<AssetCategory> getCategories() throws PortalException {
		AssetEntry entry = this.getAssetEntry();
		long[] categoryIds = entry.getCategoryIds();
		List<AssetCategory> categories = new ArrayList<AssetCategory>();
		for (long categoryId : categoryIds) {
			categories.add(
				AssetCategoryLocalServiceUtil.getAssetCategory(categoryId));
		}
		return categories;
	}

	/**
	 * Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	 * 
	 * @throws PortalException
	 * @throws NumberFormatException
	 */
	public String getImageURL() {
		return DLFileEntryHelper.getFileEntryURL(this.getImageId());
	}
	
	/**
	 * Renvoie la liste des galleries auxquelles cette édition appartient
	 */
	public List<EditionGallery> getEditionGalleries() {
		return EditionGalleryLocalServiceUtil.getEditionEditionGalleries(this.getEditionId());
	}
	
	/**
	 * Renvoie la liste des IDs des galleries auxquelles cette édition appartient
	 * sous forme de String séparée par des virgules
	 */
	public String getEditionGalleriesIds() {
		List<EditionGallery> galleries = this.getEditionGalleries();
		String ids = "";
		for (EditionGallery gallery : galleries) {
			if (ids.length() > 0) {
				ids += ",";
			}
			ids += gallery.getGalleryId();
		}
		return ids;
	}
	
	/**
	 * Renvoie l'URL de téléchargement du fichier (que ce soit un FileEntry ou une URL externe)
	 */
	public String getFileDownloadURL(Locale locale) {
		String URL = this.getURL(locale);
		if (Validator.isNull(URL)) {
			URL = DLFileEntryHelper.getFileEntryURL(Long.parseLong(this.getFileId(locale)));
		}
		return URL;
	}
	
	/**
	 * Renovie la taille du fichier sous forme de String 
	 * (si c'est une FileEntry - renvoie une chaîne vide si c'est une URL externe)
	 */
	public String getFileSize(Locale locale) {
		if (Validator.isNotNull(this.getURL(locale))) {
			return "";
		} else {
			return DLFileEntryHelper.getReadableFileEntrySize(Long.parseLong(this.getFileId(locale)), locale);
		}
	}
	
	/**
	 * Renovie le type du fichier sous forme de String 
	 * (si c'est une FileEntry - renvoie une chaîne vide si c'est une URL externe)
	 */
	public String getFileType(Locale locale) {
		if (Validator.isNotNull(this.getURL(locale))) {
			return "";
		} else {
			DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(Long.parseLong(this.getFileId(locale)));
			return fileEntry.getExtension().toUpperCase();
		}
	}
}