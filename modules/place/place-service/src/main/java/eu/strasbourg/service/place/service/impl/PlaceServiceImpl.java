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

package eu.strasbourg.service.place.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.agenda.model.Event;
import eu.strasbourg.service.place.model.Place;
import eu.strasbourg.service.place.service.base.PlaceServiceBaseImpl;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

/**
 * The implementation of the place remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link eu.strasbourg.service.place.service.PlaceService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see PlaceServiceBaseImpl
 * @see eu.strasbourg.service.place.service.PlaceServiceUtil
 */
@ProviderType
public class PlaceServiceImpl extends PlaceServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * eu.strasbourg.service.place.service.PlaceServiceUtil} to access the place
	 * remote service.
	 */

	@Override
	public JSONArray getPlaces() throws PortalException {
		List<Place> places = this.placeLocalService.getPlaces(-1, -1);
		return this.getApprovedJSONPlaces(places);
	}

	@Override
	public JSONObject getPlaceById(long id) throws PortalException {
		Place place = this.placeLocalService.fetchPlace(id);
		if (place == null || !place.isApproved()) {
			return JSONFactoryUtil.createJSONObject();
		}
		return place.toJSON();
	}

	@Override
	public JSONObject getPlaceByIdSIG(String sigId) throws PortalException {
		Place place = this.placeLocalService.getPlaceBySIGId(sigId);
		if (place == null || !place.isApproved()) {
			return JSONFactoryUtil.createJSONObject();
		}
		return place.toJSON();
	}

	@Override
	public JSONArray getPlacesByType(String typeId) throws PortalException {
		// Recherche du catégoryId
		AssetVocabulary vocabularyTypeLieu = AssetVocabularyHelper
				.getGlobalVocabulary("Type de lieu");
		AssetCategory category = AssetVocabularyHelper
				.getCategoryByExternalId(vocabularyTypeLieu, typeId);
		long[] categoriesIds = { category.getCategoryId() };

		Hits hits = SearchHelper.getPlaceWebServiceSearchHits(
				Place.class.getName(), categoriesIds, null, null);
		List<Place> places = new ArrayList<Place>();
		for (Document document : hits.getDocs()) {
			Long placeId = GetterUtil
					.getLong(document.get(Field.ENTRY_CLASS_PK));
			Place place = this.placeLocalService.fetchPlace(placeId);
			if (place != null) {
				places.add(place);
			}
		}
		return this.getApprovedJSONPlaces(places);
	}

	@Override
	public JSONArray getPlacesByTerritory(String territoryId)
			throws PortalException {
		// Recherche du catégoryId
		AssetVocabulary vocabularyTerritory = AssetVocabularyHelper
				.getGlobalVocabulary("Territoire");
		AssetCategory category = AssetVocabularyHelper
				.getCategoryByExternalId(vocabularyTerritory, territoryId);
		long[] categoriesIds = { category.getCategoryId() };

		Hits hits = SearchHelper.getPlaceWebServiceSearchHits(
				Place.class.getName(), categoriesIds, null, null);
		List<Place> places = new ArrayList<Place>();
		for (Document document : hits.getDocs()) {
			Long placeId = GetterUtil
					.getLong(document.get(Field.ENTRY_CLASS_PK));
			Place place = this.placeLocalService.fetchPlace(placeId);
			if (place != null) {
				places.add(place);
			}
		}
		return this.getApprovedJSONPlaces(places);
	}

	@Override
	public JSONArray getPlacesByNameAndLanguage(String name, String language)
			throws PortalException {
		Locale locale = LocaleUtil.fromLanguageId(language);
		Hits hits = SearchHelper.getPlaceWebServiceSearchHits(
				Place.class.getName(), null, name, locale);
		List<Place> places = new ArrayList<Place>();
		for (Document document : hits.getDocs()) {
			Long placeId = GetterUtil
					.getLong(document.get(Field.ENTRY_CLASS_PK));
			Place place = this.placeLocalService.fetchPlace(placeId);
			if (place != null) {
				places.add(place);
			}
		}
		return this.getApprovedJSONPlaces(places);
	}

	@Override
	public JSONArray getPlacesByTerritoryAndType(String territoryId,
			String typeId) throws PortalException {
		// Recherche du catégoryId du vocabulaire Territoire
		AssetVocabulary vocabularyTerritory = AssetVocabularyHelper
				.getGlobalVocabulary("Territoire");
		AssetCategory categoryTerritory = AssetVocabularyHelper
				.getCategoryByExternalId(vocabularyTerritory, territoryId);

		// Recherche du catégoryId du vocabulaire Type de lieu
		AssetVocabulary vocabularyType = AssetVocabularyHelper
				.getGlobalVocabulary("Type de lieu");
		AssetCategory categoryType = AssetVocabularyHelper
				.getCategoryByExternalId(vocabularyType, typeId);

		long[] categoriesIds = { categoryTerritory.getCategoryId(),
				categoryType.getCategoryId() };

		Hits hits = SearchHelper.getPlaceWebServiceSearchHits(
				Place.class.getName(), categoriesIds, null, null);
		List<Place> places = new ArrayList<Place>();
		for (Document document : hits.getDocs()) {
			Long placeId = GetterUtil
					.getLong(document.get(Field.ENTRY_CLASS_PK));
			Place place = this.placeLocalService.fetchPlace(placeId);
			if (place != null) {
				places.add(place);
			}
		}
		return this.getApprovedJSONPlaces(places);
	}
	
	/**
	 * Retourne l'horrible ancien web service LR6
	 */
	@Override
	public JSONObject getLegacyJSON() {
		JSONObject json = JSONFactoryUtil.createJSONObject();
		json.put("javaClass", "java.util.ArrayList");

		List<Place> places = this.placeLocalService.getPlaces(-1, -1);
		json.put("list", this.getApprovedJSONPlaces(places, true));
		return json;		
	}

	private JSONArray getApprovedJSONPlaces(List<Place> places) {
		return getApprovedJSONPlaces(places, false);
	}
	
	private JSONArray getApprovedJSONPlaces(List<Place> places, boolean legacy) {
		JSONArray jsonPlaces = JSONFactoryUtil.createJSONArray();
		for (Place place : places) {
			try {
				if (place.isApproved()) {
					if (legacy) {
						jsonPlaces.put(place.toLegacyJSON());
					} else {
						jsonPlaces.put(place.toJSON());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonPlaces;
	}

	
	/**
	 * Retourne l'ancien web service LR6 concernant les Types de lieu
	 */
	public JSONObject getLegacyCategoriesJSON() throws PortalException {
		JSONObject json = JSONFactoryUtil.createJSONObject();
		
		
		List<AssetCategory> assetCategories = new ArrayList<>();
		AssetVocabulary vocabulary = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.PLACE_TYPE);
		
		assetCategories = vocabulary.getCategories();
		
		for(AssetCategory asset : assetCategories)
		{
			JSONObject jsonbis = JSONFactoryUtil.createJSONObject();		
			jsonbis.put("parentCode", AssetVocabularyHelper.getCategoryProperty(asset.getParentCategoryId(), "SIG"));
			jsonbis.put("nom", asset.getName());
			json.put(AssetVocabularyHelper.getCategoryProperty(asset.getCategoryId(), "SIG"), jsonbis);
		}
		
		return json;		
	}
	
	
	/**
	 * Retourne l'ancien web service LR6 concernant les Territoires
	 */
	public JSONObject getLegacyTerritoriesJSON() throws PortalException {
		JSONObject json = JSONFactoryUtil.createJSONObject();
		
		
		List<AssetCategory> assetCategories = new ArrayList<>();
		AssetVocabulary vocabulary = AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.TERRITORY);
		
		assetCategories = vocabulary.getCategories();
		
		for(AssetCategory asset : assetCategories)
		{
			JSONObject jsonbis = JSONFactoryUtil.createJSONObject();		
			jsonbis.put("parentCode", AssetVocabularyHelper.getCategoryProperty(asset.getParentCategoryId(), "SIG"));
			jsonbis.put("nom", asset.getName());
			json.put(AssetVocabularyHelper.getCategoryProperty(asset.getCategoryId(), "SIG"), jsonbis);
		}
		
		return json;		
	}
	
	@Override
	public JSONArray getTypes() throws PortalException {	
		return AssetVocabularyHelper.toJSON(AssetVocabularyHelper.getGlobalVocabulary(VocabularyNames.PLACE_TYPE));
	}
	
}