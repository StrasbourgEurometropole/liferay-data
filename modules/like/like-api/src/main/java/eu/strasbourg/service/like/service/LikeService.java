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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * Provides the remote service interface for Like. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Cedric Henry
 * @see LikeServiceUtil
 * @see eu.strasbourg.service.like.service.base.LikeServiceBaseImpl
 * @see eu.strasbourg.service.like.service.impl.LikeServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=like", "json.web.service.context.path=Like"}, service = LikeService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LikeService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LikeServiceUtil} to access the like remote service. Add custom service methods to {@link eu.strasbourg.service.like.service.impl.LikeServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Ajoute un like/dislike d'un utilisateur a une entite
	*/
	public JSONObject addLike(java.lang.String userId, java.lang.String title,
		boolean isDislike, long typeId, long entityId);

	/**
	* Ajoute un like à un utilisateur
	*/
	public JSONObject addLikeLink(java.lang.String title, boolean isDislike,
		long typeId, long entityId, long entityGroupId);

	/**
	* Supprime un like d'un utilisateur
	*/
	public JSONObject deleteLike(java.lang.String userId, long likeId);

	/**
	* Supprime un like d'un utilisateur
	*/
	public JSONObject deleteLikeLink(java.lang.String title,
		java.lang.String url, boolean isDislike, long typeId, long entityId);

	/**
	* Retourne la liste des types de likes/dislikes
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getTypes();

	/**
	* Retourne les likes d'un utilisateur
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getUserLikes(java.lang.String userId);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();
}