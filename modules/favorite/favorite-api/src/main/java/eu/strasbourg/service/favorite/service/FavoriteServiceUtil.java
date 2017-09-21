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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Favorite. This utility wraps
 * {@link eu.strasbourg.service.favorite.service.impl.FavoriteServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author BenjaminBini
 * @see FavoriteService
 * @see eu.strasbourg.service.favorite.service.base.FavoriteServiceBaseImpl
 * @see eu.strasbourg.service.favorite.service.impl.FavoriteServiceImpl
 * @generated
 */
@ProviderType
public class FavoriteServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.favorite.service.impl.FavoriteServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Ajoute un favoris à un utilisateur
	*/
	public static com.liferay.portal.kernel.json.JSONObject addFavorite(
		java.lang.String title, java.lang.String url, long typeId,
		java.lang.String userId, long entityId) {
		return getService().addFavorite(title, url, typeId, userId, entityId);
	}

	/**
	* Supprime un favoris d'un utilisateur
	*/
	public static com.liferay.portal.kernel.json.JSONObject deleteFavorite(
		java.lang.String userId, long favoriteId) {
		return getService().deleteFavorite(userId, favoriteId);
	}

	/**
	* Retourne la liste des types de favoris
	*/
	public static com.liferay.portal.kernel.json.JSONObject getTypes() {
		return getService().getTypes();
	}

	/**
	* Retourne les favoris d'un utilisateur
	*/
	public static com.liferay.portal.kernel.json.JSONObject getUserFavorites(
		java.lang.String userId) {
		return getService().getUserFavorites(userId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static FavoriteService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FavoriteService, FavoriteService> _serviceTracker =
		ServiceTrackerFactory.open(FavoriteService.class);
}