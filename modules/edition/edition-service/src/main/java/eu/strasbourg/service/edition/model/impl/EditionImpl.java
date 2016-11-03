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
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.edition.model.Edition;
import eu.strasbourg.service.edition.model.EditionGallery;
import eu.strasbourg.service.edition.service.EditionGalleryLocalServiceUtil;
import eu.strasbourg.service.edition.service.EditionLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;

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

	private static final long serialVersionUID = -263639533491031888L;

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
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Edition.class.getName(),
			this.getEditionId());
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper
			.getAssetEntryCategories(this.getAssetEntry());
	}

	/**
	 * Renvoie l'URL de l'image à partir de l'id du DLFileEntry
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
	 * Renvoie la liste des galleries auxquelles cette édition appartient
	 */
	@Override
	public List<EditionGallery> getEditionGalleries() {
		return EditionGalleryLocalServiceUtil
			.getEditionEditionGalleries(this.getEditionId());
	}

	/**
	 * Renvoie la liste des IDs des galleries auxquelles cette édition
	 * appartient sous forme de String
	 */
	@Override
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
	 * Renvoie la liste des galeries publiées
	 */
	@Override
	public List<EditionGallery> getPublishedEditionGalleries() {
		List<EditionGallery> galleries = this.getEditionGalleries();
		List<EditionGallery> result = new ArrayList<EditionGallery>();
		for (EditionGallery gallery : galleries) {
			if (gallery.isApproved()) {
				result.add(gallery);
			}
		}
		return result;
	}

	/**
	 * Renvoie l'URL de téléchargement du fichier (que ce soit un FileEntry ou
	 * une URL externe)
	 */
	@Override
	public String getFileDownloadURL(Locale locale) {
		return FileEntryHelper
			.getFileEntryURL(GetterUtil.getLong(this.getFileId(locale)));
	}

	/**
	 * Renovie la taille du fichier sous forme de String
	 */
	@Override
	public String getFileSize(Locale locale) {
		return FileEntryHelper.getReadableFileEntrySize(
			GetterUtil.getLong(this.getFileId(locale)), locale);
	}

	/**
	 * Renovie le type du fichier sous forme de String (si c'est une FileEntry -
	 * renvoie une chaîne vide si c'est une URL externe)
	 */
	@Override
	public String getFileType(Locale locale) {
		DLFileEntry fileEntry = DLFileEntryLocalServiceUtil
			.fetchDLFileEntry(GetterUtil.getLong(this.getFileId(locale)));
		if (fileEntry != null) {
			return fileEntry.getExtension().toUpperCase();
		} else {
			return "";
		}
	}

	/**
	 * Renvoie la version live de l'édition, si elle existe
	 */
	@Override
	public Edition getLiveVersion() {
		long groupId = this.getGroupId();
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		if (group == null || !group.isStagingGroup()) {
			return null;
		}
		long liveGroupId = group.getLiveGroupId();
		Edition liveEdition = EditionLocalServiceUtil
			.fetchEditionByUuidAndGroupId(this.getUuid(), liveGroupId);
		return liveEdition;
	}

	//
	// Catégories
	//

	/**
	 * Retourne les sources de l'édition
	 */
	@Override
	public List<AssetCategory> getSources() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
			this.getAssetEntry(), "source des editions");
	}

	/**
	 * Retourne les types de l'édition
	 */
	@Override
	public List<AssetCategory> getTypes() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
			this.getAssetEntry(), "type des editions");
	}

}