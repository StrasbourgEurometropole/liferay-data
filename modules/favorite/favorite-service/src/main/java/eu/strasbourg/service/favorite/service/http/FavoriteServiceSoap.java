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

package eu.strasbourg.service.favorite.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import eu.strasbourg.service.favorite.service.FavoriteServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link FavoriteServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link eu.strasbourg.service.favorite.model.FavoriteSoap}.
 * If the method in the service utility returns a
 * {@link eu.strasbourg.service.favorite.model.Favorite}, that is translated to a
 * {@link eu.strasbourg.service.favorite.model.FavoriteSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author BenjaminBini
 * @see FavoriteServiceHttp
 * @see eu.strasbourg.service.favorite.model.FavoriteSoap
 * @see FavoriteServiceUtil
 * @generated
 */
@ProviderType
public class FavoriteServiceSoap {
	/**
	* Retourne la liste des types de favoris
	*/
	public static java.lang.String getTypes() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = FavoriteServiceUtil.getTypes();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Retourne les favoris d'un utilisateur
	*/
	public static java.lang.String getUserFavorites(java.lang.String userId)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = FavoriteServiceUtil.getUserFavorites(userId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Ajoute un favoris à un utilisateur
	*/
	public static java.lang.String addFavorite(java.lang.String title,
		java.lang.String url, long typeId, java.lang.String userId,
		long entityId) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = FavoriteServiceUtil.addFavorite(title,
					url, typeId, userId, entityId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Ajoute un favori à un utilisateur
	*/
	public static java.lang.String addFavoriteLink(java.lang.String title,
		java.lang.String url, long typeId, long entityId, long entityGroupId)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = FavoriteServiceUtil.addFavoriteLink(title,
					url, typeId, entityId, entityGroupId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Supprime un favoris d'un utilisateur
	*/
	public static java.lang.String deleteFavorite(java.lang.String userId,
		long favoriteId) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = FavoriteServiceUtil.deleteFavorite(userId,
					favoriteId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	* Supprime un favoris d'un utilisateur
	*/
	public static java.lang.String deleteFavoriteLink(java.lang.String title,
		java.lang.String url, long typeId, long entityId)
		throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue = FavoriteServiceUtil.deleteFavoriteLink(title,
					url, typeId, entityId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(FavoriteServiceSoap.class);
}