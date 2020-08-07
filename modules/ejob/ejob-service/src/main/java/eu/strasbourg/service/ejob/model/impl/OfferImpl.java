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

package eu.strasbourg.service.ejob.model.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.util.ArrayList;
import java.util.List;

/**
 * The extended model implementation for the Offer service. Represents a row in the &quot;ejob_Offer&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>eu.strasbourg.service.ejob.model.Offer<code> interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class OfferImpl extends OfferBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a offer model instance should use the {@link eu.strasbourg.service.ejob.model.Offer} interface instead.
	 */
	public OfferImpl() {
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Offer.class.getName(),
				this.getOfferId());
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
	 * Retourne les types de l'événement
	 */
	@Override
	public List<AssetCategory> getDirections() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_DIRECTION);
	}

	/**
	 * Retourne les types de l'événement
	 */
	@Override
	public List<AssetCategory> getServices() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_SERVICE);
	}

	/**
	 * Retourne les types de l'événement
	 */
	@Override
	public List<AssetCategory> getFilieres() {
		List<AssetCategory> filieres = new ArrayList<>();
		if (filieres == null || filieres.isEmpty()) {
			List<AssetCategory> filieres_voca = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
					VocabularyNames.EJOB_FILIERES);
			for (AssetCategory filiere : filieres_voca) {
				if (filiere.getParentCategory() == null) {
					filieres.add(filiere);
				}
			}
		}

		return filieres;
	}

	/**
	 * Renvoie les categories des filieres
	 */
	@SuppressWarnings("unused")
	public List<AssetCategory> getFilieresCategories() {
		List<AssetCategory> filieresCategories = new ArrayList<>();
		if (filieresCategories == null || filieresCategories.isEmpty()) {
			List<AssetCategory> filieres_voca = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
					VocabularyNames.EJOB_FILIERES);
			for (AssetCategory category : filieres_voca.) {
				if (this.getFilieres().contains(category.getParentCategory())) {
					filieresCategories.add(category);
				}
			}
		}

		return filieresCategories;
	}

	/**
	 * Renvoie les grades
	 */
	@SuppressWarnings("unused")
	public List<AssetCategory> getGrades() {
		List<AssetCategory> grades = new ArrayList<>();
		if (grades == null || grades.isEmpty()) {
			List<AssetCategory> filieres_voca = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
					VocabularyNames.EJOB_FILIERES);
			for (AssetCategory grade: filieres_voca) {
				if(this.getFilieresCategories().contains(grade.getParentCategory())) {
					grades.add(grade);
				}
			}
		}

		return grades;
	}

	/**
	 * Renvoie les Famille de métiers
	 */
	@SuppressWarnings("unused")
	public List<AssetCategory> getFamilles() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_FAMILLE);
	}

	/**
	 * Renvoie les Niveau d'étude
	 */
	@SuppressWarnings("unused")
	public List<AssetCategory> getNiveauEtudes() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_NIVEAU_ETUDE);
	}

	/**
	 * Renvoie les types de recrutements
	 */
	@SuppressWarnings("unused")
	public List<AssetCategory> getTypeRecrutements() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_TYPE_RECRUTEMENT);
	}

	/**
	 * Renvoie les contact RE
	 */
	@SuppressWarnings("unused")
	public List<AssetCategory> getContacts() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_CONTACT);
	}

}