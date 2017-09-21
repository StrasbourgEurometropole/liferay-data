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

package eu.strasbourg.service.favorite.service.impl;

import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONSerializable;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import eu.strasbourg.service.favorite.model.Favorite;
import eu.strasbourg.service.favorite.model.FavoriteType;
import eu.strasbourg.service.favorite.service.base.FavoriteServiceBaseImpl;
import eu.strasbourg.utils.constants.StrasbourgPortletKeys;

/**
 * The implementation of the favorite remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.favorite.service.FavoriteService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author BenjaminBini
 * @see FavoriteServiceBaseImpl
 * @see eu.strasbourg.service.favorite.service.FavoriteServiceUtil
 */
public class FavoriteServiceImpl extends FavoriteServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.favorite.service.FavoriteServiceUtil} to access the favorite remote service.
	 */
	
	/**
	 * Retourne la liste des types de favoris
	 */
	@Override
	public JSONObject getTypes() {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray jsonTypes = JSONFactoryUtil.createJSONArray();
		
		for (FavoriteType type : FavoriteType.values()) {
			JSONObject jsonType = JSONFactoryUtil.createJSONObject();
			jsonType.put("id", type.getId());
			jsonType.put("name", type.getName());
			jsonTypes.put(jsonType);
		}
		result.put("types", jsonTypes);
		return result;
	}
	
	/**
	 * Retourne les favoris d'un utilisateur
	 */
	@Override
	public JSONObject getUserFavorites(String userId) {
		if (!isAuthorized()) {
			return error("not authorized");
		}
		
		JSONObject result = JSONFactoryUtil.createJSONObject();
		JSONArray jsonFavorites = JSONFactoryUtil.createJSONArray();
		
		List<Favorite> favorites = this.favoriteLocalService.getByPublikUser(userId);
		for (Favorite favorite : favorites) {
			jsonFavorites.put(favorite.toJSON());
		}
		
		result.put("favorites", jsonFavorites);
		return result;
	}
	
	/**
	 * Ajoute un favoris à un utilisateur
	 */
	@Override
	public JSONObject addFavorite(String title, String url, long typeId, String userId, long entityId) {
		if (!isAuthorized()) {
			return error("not authorized");
		}
		
		// Vérification du type
		FavoriteType type = FavoriteType.get(typeId);
		if (type == null) {
			return error("type does not exist");
		}
		
		// Validations d'autres champs
		if (Validator.isNull(title)) {
			return error("title is empty");
		}

		// Création de l'objet
		Favorite favorite = this.favoriteLocalService.createFavorite();
		favorite.setTitle(title);
		favorite.setUrl(url);
		favorite.setTypeId(typeId);
		favorite.setPublikUserId(userId);
		favorite.setEntityId(entityId);
		

		// Vérification de l'identifiant de l'entité (plus pratique après initialisation de l'objet)
		if (type != FavoriteType.PROCEDURE && favorite.getAssetEntry() == null) {
			return error("entity does not exist");
		}
		
		this.favoriteLocalService.updateFavorite(favorite);
		
		return success("favorite added");
	}
	
	/**
	 * Supprime un favoris d'un utilisateur
	 */
	public JSONObject deleteFavorite(String userId, long favoriteId) {
		if (!isAuthorized()) {
			return error("not authorized");
		}
		
		Favorite favorite = this.favoriteLocalService.fetchFavorite(favoriteId);
		if (favorite == null) {
			return error("favorite does not exist");
		}
		if (!favorite.getPublikUserId().equals(userId)) {
			return error("favorite does not belong to user");
		}
		try {
			this.favoriteLocalService.deleteFavorite(favoriteId);
			return success("favorite deleted");
		} catch (PortalException e) {
			return error("unknown error");
		}
		
	}

	private boolean isAuthorized() {
		try {
			Company defaultCompany = CompanyLocalServiceUtil.getCompanyByWebId("liferay.com");
			long globalGroupId = defaultCompany.getGroup().getGroupId();
			return this.getPermissionChecker().hasPermission(globalGroupId, StrasbourgPortletKeys.NOTIFICATION_BO,
					StrasbourgPortletKeys.NOTIFICATION_BO, "CONTRIBUTE");
		} catch (PortalException e) {
			return false;
		}
	}
	
	private JSONObject success(String message) {
		return JSONFactoryUtil.createJSONObject().put("sucess", message);
	}

	private JSONObject error(String message) {
		return JSONFactoryUtil.createJSONObject().put("error", message);
	}
}