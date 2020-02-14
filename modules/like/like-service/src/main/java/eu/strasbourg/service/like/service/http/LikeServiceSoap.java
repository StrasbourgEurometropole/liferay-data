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

package eu.strasbourg.service.like.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import eu.strasbourg.service.like.service.LikeServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>LikeServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>eu.strasbourg.service.like.model.LikeSoap</code>. If the method in the
 * service utility returns a
 * <code>eu.strasbourg.service.like.model.Like</code>, that is translated to a
 * <code>eu.strasbourg.service.like.model.LikeSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
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
 * @author Cedric Henry
 * @see LikeServiceHttp
 * @generated
 */
@ProviderType
public class LikeServiceSoap {

	/**
	 * Retourne la liste des types de likes/dislikes
	 */
	public static String getTypes() throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				LikeServiceUtil.getTypes();

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	 * Retourne les likes d'un utilisateur
	 */
	public static String getUserLikes(String userId) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				LikeServiceUtil.getUserLikes(userId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	 * Ajoute un like/dislike d'un utilisateur a une entite
	 */
	public static String addLike(
			String userId, String title, boolean isDislike, long typeId,
			long entityId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				LikeServiceUtil.addLike(
					userId, title, isDislike, typeId, entityId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	 * Ajoute un like à un utilisateur
	 */
	public static String addLikeLink(
			String title, boolean isDislike, long typeId, long entityId,
			long entityGroupId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				LikeServiceUtil.addLikeLink(
					title, isDislike, typeId, entityId, entityGroupId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	 * Supprime un like d'un utilisateur
	 */
	public static String deleteLike(String userId, long likeId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				LikeServiceUtil.deleteLike(userId, likeId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	/**
	 * Supprime un like d'un utilisateur
	 */
	public static String deleteLikeLink(
			String title, String url, boolean isDislike, long typeId,
			long entityId)
		throws RemoteException {

		try {
			com.liferay.portal.kernel.json.JSONObject returnValue =
				LikeServiceUtil.deleteLikeLink(
					title, url, isDislike, typeId, entityId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LikeServiceSoap.class);

}