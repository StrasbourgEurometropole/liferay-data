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

import java.util.List;
import java.util.Locale;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.PlaceLocalServiceUtil;
import eu.strasbourg.service.project.model.Participation;
import eu.strasbourg.service.project.model.Project;
import eu.strasbourg.service.project.service.ParticipationLocalServiceUtil;
import eu.strasbourg.service.project.service.ProjectLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;

/**
 * The extended model implementation for the PlacitPlace service. Represents a row in the &quot;project_PlacitPlace&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.model.PlacitPlace} interface.
 * </p>
 *
 * @author Cedric Henry
 */
@ProviderType
public class PlacitPlaceImpl extends PlacitPlaceBaseImpl {

	private static final long serialVersionUID = 2939226261046827826L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a placit place model instance should use the {@link eu.strasbourg.service.project.model.PlacitPlace} interface instead.
	 */
	public PlacitPlaceImpl() {
	}

	/**
	 * Retourne le projet du lieu Placit
	 */
	@Override
	public Project getProject() {
		return ProjectLocalServiceUtil.fetchProject(this.getProjectId());
	}
	
	/**
	 * Retourne la participation du lieu Placit
	 */
	@Override
	public Participation getParticipation() {
		return ParticipationLocalServiceUtil.fetchParticipation(this.getParticipationId());
	}

	/**
	 * Retourne l'AssetEntry rattaché à cette entité
	 */
	@Override
	public AssetEntry getAssetEntry() {
		if (this.getProjectId() > 0) {
			return AssetEntryLocalServiceUtil.fetchEntry(Project.class.getName(),
			this.getPrimaryKey());
		} else {
			return AssetEntryLocalServiceUtil.fetchEntry(Participation.class.getName(),
					this.getPrimaryKey());
		}
	}

	/**
	 * Retourne la liste des AssetCategory rattachées à cette entité (via
	 * l'assetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper.getAssetEntryCategories(this.getAssetEntry());
	}

	/**
	 * Retourne le nom du lieu SIG
	 */
	@Override
	public String getSIGPlaceAlias(Locale locale) {
		Place place = PlaceLocalServiceUtil
			.getPlaceBySIGId(this.getPlaceSIGId());
		if (place != null) {
			return place.getAlias(locale);
		} else {
			return "";
		}
	}

	/**
	 * Retourne le nom du lieu SIG ou "manuel"
	 */
	@Override
	public String getPlaceAlias(Locale locale) {
		Place place = PlaceLocalServiceUtil
			.getPlaceBySIGId(this.getPlaceSIGId());
		if (place != null) {
			return place.getAlias(locale);
		} else {
			return this.getPlaceName(locale);
		}
	}
	
	/**
	 * Retourne l'adresse (num + rue) du lieu SIG ou "manuel"
	 */
	@Override
	public String getAddress() {
		Place place = PlaceLocalServiceUtil
			.getPlaceBySIGId(this.getPlaceSIGId());
		if (place != null) {
			return place.getAddressStreet();
		} else {
			return this.getPlaceStreetNumber() + " " + this.getPlaceStreetName();
		}
	}
	
	/**
	 * Retourne l'adresse complete du lieu SIG ou "manuel"
	 */
	@Override
	public String getCompleteAddress(Locale locale) {
		Place place = PlaceLocalServiceUtil
			.getPlaceBySIGId(this.getPlaceSIGId());
		if (place != null && !place.getAddressStreet().isEmpty() && place.getAddressStreet() != "") {
			return place.getAddressStreet() + 
					", " + place.getAddressZipCode() + 
					" " + place.getCity(locale);
		} else if (!this.getPlaceStreetName().isEmpty() && this.getPlaceStreetName() != "") {
			return this.getPlaceStreetNumber() + 
					" " + this.getPlaceStreetName() + 
					", " + this.getZipCode() +
					" " + this.getCity(locale);
		}
		return "";
	}
	
	/**
	 * Retourne le code postal du lieu SIG ou "manuel"
	 */
	@Override
	public String getZipCode() {
		Place place = PlaceLocalServiceUtil
			.getPlaceBySIGId(this.getPlaceSIGId());
		if (place != null) {
			return place.getAddressZipCode();
		} else {
			return this.getPlaceZipCode();
		}
	}
	
	/**
	 * Retourne la ville du lieu SIG ou "manuel"
	 */
	@Override
	public String getCity(Locale locale) {
		Place place = PlaceLocalServiceUtil
			.getPlaceBySIGId(this.getPlaceSIGId());
		if (place != null) {
			return place.getCity(locale);
		} else {
			return this.getPlaceStreetNumber() + " " + this.getPlaceStreetName();
		}
	}
	
	/**
	 * Retourne l'ID de l'image du lieu SIG ou "manuel"
	 */
	@Override
	public String getImageURL() {
		Place place = PlaceLocalServiceUtil
			.getPlaceBySIGId(this.getPlaceSIGId());
		if (place != null) {
			return FileEntryHelper.getFileEntryURL(place.getImageId());
		} else {
			return FileEntryHelper.getFileEntryURL(this.getImageId());
		}
	}
	
	/**
	 * Renvoie si le lieu placit est SIG ou manuel
	 * @return True : lieu SIG ; False : lieu manuel
	 */
	@Override
	public boolean isSIG() {
		Place place = PlaceLocalServiceUtil.getPlaceBySIGId(this.getPlaceSIGId());
		
		return place != null ? true : false;
	}
	
	
}