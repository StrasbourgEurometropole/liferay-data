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

package eu.strasbourg.service.notification.service;

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
 * Provides the remote service interface for Notification. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author BenjaminBini
 * @see NotificationServiceUtil
 * @see eu.strasbourg.service.notification.service.base.NotificationServiceBaseImpl
 * @see eu.strasbourg.service.notification.service.impl.NotificationServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=notification", "json.web.service.context.path=Notification"}, service = NotificationService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface NotificationService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NotificationServiceUtil} to access the notification remote service. Add custom service methods to {@link eu.strasbourg.service.notification.service.impl.NotificationServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Envoie une notification à un utilisateur
	*/
	public JSONObject addNotification(java.lang.String userId,
		boolean isGlobal, java.lang.String title, java.lang.String url,
		java.lang.String publicationDate, java.lang.String expirationDate,
		java.lang.String typeId);

	/**
	* Retourne la liste des canaux de notifications
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getChannels();

	/**
	* Retourne la liste des types de notifications
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getTypes() throws PortalException;

	/**
	* Retourne la liste des notifications d'un utilisateur
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getUserNotifications(java.lang.String userId);

	/**
	* Retourne la liste des types et des canaux de communication d'un
	* utilisateur
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getUserSettings(java.lang.String userId);

	/**
	* Modification des abonnements et des canaux de communication d'un
	* utilisateur
	*/
	public JSONObject setUserSettings(java.lang.String userId,
		java.lang.String typeIds, java.lang.String channelIds);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();
}