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

package eu.strasbourg.service.project.model.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

/**
 * The extended model implementation for the Participation service. Represents a row in the &quot;project_Participation&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.model.Participation} interface.
 * </p>
 *
 * @author Cedric Henry
 */
@ProviderType
public class ParticipationImpl extends ParticipationBaseImpl {

	private static final long serialVersionUID = 1311330918138728472L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a participation model instance should use the {@link eu.strasbourg.service.project.model.Participation} interface instead.
	 */
	public ParticipationImpl() {
	}
	
	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Participation.class.getName(),
			this.getParticipationId());
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
	 * Retourne le titre muni d'une balise <br> permettant l'affichage en deux lignes ...
	 */
	@Override
	public String getTitleInTwoLines() {
		String title = this.getTitle();
		int titleLength = title.length();
		int cutBr = title.indexOf(" ", 15);
		return title.substring(0,cutBr)+"<br>"+title.substring(cutBr,title.length());
	}
	
	/**
	 * Retourne les type de la participation (
	 */
	@Override
	public AssetCategory getType() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.PARTICIPATION_TYPE).get(0);
	}
	
	/**
	 * Retourne les territoire du lieu
	 */
	@Override
	public List<AssetCategory> getTerritories() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.TERRITORY);
	}
	
	/**
	 * Retourne la catégorie Territoire correspondant à la ville du lieu
	 */
	@Override
	public AssetCategory getCityCategory() {
		List<AssetCategory> territories = this.getTerritories();
		for (AssetCategory territory : territories) {
			try {
				if (territory.getAncestors().size() == 1) {
					return territory;
				}
			} catch (PortalException e) {
				continue;
			}
		}
		return null;
	}

	/**
	 * Retourne le quartier
	 */
	@Override
	public String getDistrict(Locale locale) {
		AssetCategory districtCategory = this.getDistrictCategory();
		if (districtCategory != null) {
			return districtCategory.getTitle(locale);
		}
		return "";
	}

	/**
	 * Retourne la catégorie Territoire correspondant au quartier du lieu
	 */
	@Override
	public AssetCategory getDistrictCategory() {
		List<AssetCategory> territories = this.getTerritories();
		for (AssetCategory territory : territories) {
			try {
				if (territory.getAncestors().size() == 2) {
					return territory;
				}
			} catch (PortalException e) {
				continue;
			}
		}
		return null;
	}
	
	/**
	 * Retourne le status de la participation selon la temporalité actuelle 
	 * @return le status suivant l'ordre :
	 * 		[soon_arrived] : date du jour antérieur à la date de publication
	 * 		[new] : 7 jour après la publication
	 * 		[in_progress] : toute la durée de la période de participation
	 * 		[soon_finished] : 7 jours avant l'expiration
	 * 		[finished] : date du jour postérieur à la date d'expiration
	 */
	@Override
	public String getParticipationStatus() {
		// Instanciation des variables
		Date todayDate = new Date();
		Date publicationDate = this.getPublicationDate();
		Date publicationDatePlus = this.getPublicationDate();
		Date expirationDate = this.getExpirationDate();
		Date expirationDateMinus = this.getExpirationDate();
		
		// Operation sur les ajouts et retraits de jours
		Calendar cal = Calendar.getInstance();
		
		// ... sur la date de publication
		cal.setTime(publicationDatePlus);
		cal.add(Calendar.DATE, 7);
		publicationDatePlus = cal.getTime();
		
		// ... sur la date d'expiration
		cal.setTime(expirationDateMinus);
		cal.add(Calendar.DATE, -7);
		expirationDateMinus = cal.getTime();
		
		if (todayDate.before(publicationDate)) {
			return "soon_arrived";
		} 
		else if (todayDate.after(expirationDate)) {
			return "finished";
		}
		else if (todayDate.after(expirationDateMinus)) {
			return "soon_finished";
		}
		else if (todayDate.before(publicationDatePlus)) {
			return "new";
		} 
		else {
			return "in_progress";
		}
	}
	
	@Override
	public int getTodayPublicationDifferenceDays () {
		// Instanciation des variables
		Date todayDate = new Date();
		Date publicationDate = this.getPublicationDate();
		
		// Calcul du nombre de millisecondes entre les deux dates et
		// conversion en nombre de jours
		long diff = publicationDate.getTime() - todayDate.getTime();
	    return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	@Override
	public int getTodayExpirationDifferenceDays () {
		// Instanciation des variables
		Date todayDate = new Date();
		Date expirationDate = this.getExpirationDate();
		
		// Calcul du nombre de millisecondes entre les deux dates et
		// conversion en nombre de jours
		long diff = expirationDate.getTime() - todayDate.getTime();
	    return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
}