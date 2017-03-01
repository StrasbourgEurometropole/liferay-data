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

package eu.strasbourg.service.place.model.impl;

import java.util.List;
import java.util.Locale;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.place.model.Period;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.model.Price;
import eu.strasbourg.service.place.model.ScheduleException;
import eu.strasbourg.service.place.model.SubPlace;
import eu.strasbourg.service.place.service.PeriodLocalServiceUtil;
import eu.strasbourg.service.place.service.PriceLocalServiceUtil;
import eu.strasbourg.service.place.service.ScheduleExceptionLocalServiceUtil;
import eu.strasbourg.service.place.service.SubPlaceLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;

/**
 * The extended model implementation for the Place service. Represents a row in
 * the &quot;place_Place&quot; database table, with each column mapped to a
 * property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.place.model.Place} interface.
 * </p>
 *
 * @author Angelique Zunino Champougny
 */
@ProviderType
public class PlaceImpl extends PlaceBaseImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8684903451087898120L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a place
	 * model instance should use the {@link
	 * eu.strasbourg.service.place.model.Place} interface instead.
	 */
	public PlaceImpl() {
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Place.class.getName(),
				this.getPlaceId());
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
	 * Retourne le prix rattaché au lieu
	 */
	@Override
	public Price getPrice() {
		return PriceLocalServiceUtil.fetchPrice(this.getPriceId());
	}

	/**
	 * Retourne les ScheduleExceptions du lieu
	 */
	@Override
	public List<ScheduleException> getScheduleExceptions() {
		return ScheduleExceptionLocalServiceUtil
				.getByPlaceId(this.getPlaceId());
	}
	
	/**
	 *  Retourne les SubPlaces du lieux
	 */
	@Override
	public List<SubPlace> getSubPlaces(){
		return SubPlaceLocalServiceUtil.getByPlaceId(this.getPlaceId());
	}
	
	/**
	 *  Retourne les Periods du lieux
	 */
	@Override
	public List<Period> getPeriods(){
		return PeriodLocalServiceUtil.getByPlaceId(this.getPlaceId());
	}

	/**
	 * Retourne le territoire du lieu
	 */
	@Override
	public List<AssetCategory> getSources() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
				this.getAssetEntry(), "territoire");
	}

	/**
	 * Retourne les types du lieu
	 */
	@Override
	public List<AssetCategory> getTypes() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
				this.getAssetEntry(), "type des lieux");
	}
}