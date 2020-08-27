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
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import eu.strasbourg.service.ejob.model.Offer;
import eu.strasbourg.utils.AssetVocabularyAccessor;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
	public AssetCategory getOfferDirection() {
		List<AssetCategory> list = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_DIRECTION);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	/**
	 * Retourne les types de l'événement
	 */
	@Override
	public AssetCategory getOfferService() {
		List<AssetCategory> services = new ArrayList<>();
		if (services == null || services.isEmpty()) {
			List<AssetCategory> services_voca = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
					VocabularyNames.EJOB_DIRECTION);
			for (AssetCategory service : services_voca) {
				if (this.getOfferDirection().equals(service.getParentCategory())) {
					return service;
				}
			}
		}

		return null;
	}

	/**
	 * Retourne les types de l'événement
	 */
	@Override
	public AssetCategory getOfferFiliere() {
		List<AssetCategory> filieres = new ArrayList<>();
		if (filieres == null || filieres.isEmpty()) {
			List<AssetCategory> filieres_voca = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
					VocabularyNames.EJOB_FILIERES);
			for (AssetCategory filiere : filieres_voca) {
				if (filiere.getParentCategory() == null) {
					 return filiere;
				}
			}
		}

		return null;
	}

	/**
	 * Renvoie les categories des filieres
	 */
	@SuppressWarnings("unused")
	public AssetCategory getOfferFiliereCategorie() {
		List<AssetCategory> filieresCategories = new ArrayList<>();
		if (filieresCategories == null || filieresCategories.isEmpty()) {
			List<AssetCategory> filieres_voca = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
					VocabularyNames.EJOB_FILIERES);
			for (AssetCategory category : filieres_voca) {
				if (this.getOfferFiliere().equals(category.getParentCategory())) {
					return category;
				}
			}
		}

		return null;
	}

	/**
	 * Renvoie les grades
	 */
	@SuppressWarnings("unused")
	public AssetCategory getOfferGrade() {
		List<AssetCategory> grades = new ArrayList<>();
		if (grades == null || grades.isEmpty()) {
			List<AssetCategory> filieres_voca = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
					VocabularyNames.EJOB_FILIERES);
			for (AssetCategory grade: filieres_voca) {
				if(this.getOfferFiliereCategorie().equals(grade.getParentCategory())) {
					return grade;
				}
			}
		}

		return null;
	}

	/**
	 * Renvoie les Famille de métiers
	 */
	@SuppressWarnings("unused")
	public AssetCategory getOfferFamille() {
		List<AssetCategory> list = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_FAMILLE);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	/**
	 * Renvoie les Niveau d'étude
	 */
	@SuppressWarnings("unused")
	public AssetCategory getOfferNiveauEtude() {
		List<AssetCategory> list = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_NIVEAU_ETUDE);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	/**
	 * Renvoie les types de recrutements
	 */
	@SuppressWarnings("unused")
	public AssetCategory getOfferTypeRecrutement() {
		List<AssetCategory> list = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_TYPE_RECRUTEMENT);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	/**
	 * Renvoie les contact RE
	 */
	@SuppressWarnings("unused")
	public AssetCategory getOfferContact() {
		List<AssetCategory> list = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_CONTACT);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public String getInterneExterne(){
		List<AssetCategory> list = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.EJOB_INTERNE_EXTERNE);
		if(!list.isEmpty()){
			return list.get(0).getTitle(Locale.FRANCE);
		}
		return null;
	}
}