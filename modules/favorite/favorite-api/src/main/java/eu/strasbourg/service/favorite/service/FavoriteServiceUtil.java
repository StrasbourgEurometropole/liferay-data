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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Favorite. This utility wraps
 * <code>eu.strasbourg.service.favorite.service.impl.FavoriteServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author BenjaminBini
 * @see FavoriteService
 * @generated
 */
public class FavoriteServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.favorite.service.impl.FavoriteServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Ajoute un favoris à un utilisateur
	 */
	public static com.liferay.portal.kernel.json.JSONObject addFavorite(
		String title, String url, long typeId, String userId, long entityId) {

		return getService().addFavorite(title, url, typeId, userId, entityId);
	}

	/**
	 * Ajoute un favori à un utilisateur
	 */
	public static com.liferay.portal.kernel.json.JSONObject addFavoriteLink(
		String title, String url, long typeId, long entityId,
		long entityGroupId) {

		return getService().addFavoriteLink(
			title, url, typeId, entityId, entityGroupId);
	}

	/**
	 * Supprime un favoris d'un utilisateur
	 */
	public static com.liferay.portal.kernel.json.JSONObject deleteFavorite(
		String userId, long favoriteId) {

		return getService().deleteFavorite(userId, favoriteId);
	}

	/**
	 * Supprime un favoris d'un utilisateur
	 */
	public static com.liferay.portal.kernel.json.JSONObject deleteFavoriteLink(
		String title, String url, long typeId, long entityId) {

		return getService().deleteFavoriteLink(title, url, typeId, entityId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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
		String userId) {

		return getService().getUserFavorites(userId);
	}

	public static FavoriteService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FavoriteService, FavoriteService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(FavoriteService.class);

		ServiceTracker<FavoriteService, FavoriteService> serviceTracker =
			new ServiceTracker<FavoriteService, FavoriteService>(
				bundle.getBundleContext(), FavoriteService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}