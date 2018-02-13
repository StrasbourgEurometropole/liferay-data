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

package eu.strasbourg.service.favorite.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FavoriteService}.
 *
 * @author BenjaminBini
 * @see FavoriteService
 * @generated
 */
@ProviderType
public class FavoriteServiceWrapper implements FavoriteService,
	ServiceWrapper<FavoriteService> {
	public FavoriteServiceWrapper(FavoriteService favoriteService) {
		_favoriteService = favoriteService;
	}

	/**
	* Ajoute un favoris à un utilisateur
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject addFavorite(
		java.lang.String title, java.lang.String url, long typeId,
		java.lang.String userId, long entityId) {
		return _favoriteService.addFavorite(title, url, typeId, userId, entityId);
	}

	/**
	* Ajoute un favori à un utilisateur
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject addFavoriteLink(
		java.lang.String title, java.lang.String url, long typeId,
		long entityId, long entityGroupId) {
		return _favoriteService.addFavoriteLink(title, url, typeId, entityId,
			entityGroupId);
	}

	/**
	* Supprime un favoris d'un utilisateur
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject deleteFavorite(
		java.lang.String userId, long favoriteId) {
		return _favoriteService.deleteFavorite(userId, favoriteId);
	}

	/**
	* Supprime un favoris d'un utilisateur
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject deleteFavoriteLink(
		java.lang.String title, java.lang.String url, long typeId, long entityId) {
		return _favoriteService.deleteFavoriteLink(title, url, typeId, entityId);
	}

	/**
	* Retourne la liste des types de favoris
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject getTypes() {
		return _favoriteService.getTypes();
	}

	/**
	* Retourne les favoris d'un utilisateur
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONObject getUserFavorites(
		java.lang.String userId) {
		return _favoriteService.getUserFavorites(userId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _favoriteService.getOSGiServiceIdentifier();
	}

	@Override
	public FavoriteService getWrappedService() {
		return _favoriteService;
	}

	@Override
	public void setWrappedService(FavoriteService favoriteService) {
		_favoriteService = favoriteService;
	}

	private FavoriteService _favoriteService;
}