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
 * Provides the remote service interface for Favorite. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author BenjaminBini
 * @see FavoriteServiceUtil
 * @see eu.strasbourg.service.favorite.service.base.FavoriteServiceBaseImpl
 * @see eu.strasbourg.service.favorite.service.impl.FavoriteServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=favorite", "json.web.service.context.path=Favorite"}, service = FavoriteService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface FavoriteService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FavoriteServiceUtil} to access the favorite remote service. Add custom service methods to {@link eu.strasbourg.service.favorite.service.impl.FavoriteServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Ajoute un favoris à un utilisateur
	*/
	public JSONObject addFavorite(java.lang.String title, java.lang.String url,
		long typeId, java.lang.String userId, long entityId);

	/**
	* Ajoute un favori à un utilisateur
	*/
	public JSONObject addFavoriteLink(java.lang.String title,
		java.lang.String url, long typeId, long entityId, long entityGroupId);

	/**
	* Supprime un favoris d'un utilisateur
	*/
	public JSONObject deleteFavorite(java.lang.String userId, long favoriteId);

	/**
	* Supprime un favoris d'un utilisateur
	*/
	public JSONObject deleteFavoriteLink(java.lang.String title,
		java.lang.String url, long typeId, long entityId);

	/**
	* Retourne la liste des types de favoris
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getTypes();

	/**
	* Retourne les favoris d'un utilisateur
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getUserFavorites(java.lang.String userId);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();
}