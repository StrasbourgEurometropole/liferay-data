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

package eu.strasbourg.service.like.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Like. This utility wraps
 * {@link eu.strasbourg.service.like.service.impl.LikeServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Cedric Henry
 * @see LikeService
 * @see eu.strasbourg.service.like.service.base.LikeServiceBaseImpl
 * @see eu.strasbourg.service.like.service.impl.LikeServiceImpl
 * @generated
 */
@ProviderType
public class LikeServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.like.service.impl.LikeServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Ajoute un like/dislike d'un utilisateur a une entite
	*/
	public static com.liferay.portal.kernel.json.JSONObject addLike(
		java.lang.String userId, java.lang.String title, boolean isDislike,
		long typeId, long entityId) {
		return getService().addLike(userId, title, isDislike, typeId, entityId);
	}

	/**
	* Ajoute un like à un utilisateur
	*/
	public static com.liferay.portal.kernel.json.JSONObject addLikeLink(
		java.lang.String title, boolean isDislike, long typeId, long entityId,
		long entityGroupId) {
		return getService()
				   .addLikeLink(title, isDislike, typeId, entityId,
			entityGroupId);
	}

	/**
	* Supprime un like d'un utilisateur
	*/
	public static com.liferay.portal.kernel.json.JSONObject deleteLike(
		java.lang.String userId, long likeId) {
		return getService().deleteLike(userId, likeId);
	}

	/**
	* Supprime un like d'un utilisateur
	*/
	public static com.liferay.portal.kernel.json.JSONObject deleteLikeLink(
		java.lang.String title, java.lang.String url, boolean isDislike,
		long typeId, long entityId) {
		return getService()
				   .deleteLikeLink(title, url, isDislike, typeId, entityId);
	}

	/**
	* Retourne la liste des types de likes/dislikes
	*/
	public static com.liferay.portal.kernel.json.JSONObject getTypes() {
		return getService().getTypes();
	}

	/**
	* Retourne les likes d'un utilisateur
	*/
	public static com.liferay.portal.kernel.json.JSONObject getUserLikes(
		java.lang.String userId) {
		return getService().getUserLikes(userId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static LikeService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LikeService, LikeService> _serviceTracker = ServiceTrackerFactory.open(LikeService.class);
}