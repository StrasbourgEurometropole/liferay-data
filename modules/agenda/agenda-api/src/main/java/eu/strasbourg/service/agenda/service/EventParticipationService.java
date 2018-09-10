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

package eu.strasbourg.service.agenda.service;

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
 * Provides the remote service interface for EventParticipation. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author BenjaminBini
 * @see EventParticipationServiceUtil
 * @see eu.strasbourg.service.agenda.service.base.EventParticipationServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.impl.EventParticipationServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=agenda", "json.web.service.context.path=EventParticipation"}, service = EventParticipationService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface EventParticipationService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EventParticipationServiceUtil} to access the event participation remote service. Add custom service methods to {@link eu.strasbourg.service.agenda.service.impl.EventParticipationServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Ajoute une participation à un utilisateur
	*/
	public JSONObject addEventParticipationLink(long eventId, long groupId);

	/**
	* Supprime une participation d'evenement d'un utilisateur
	*/
	public JSONObject deleteEventParticipationLink(long eventId);

	/**
	* Retourne les participation d'evenement d'un utilisateur
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getUserEventParticipations(java.lang.String userId);

	/**
	* Verifie si l'utilisateur courant participe a l'evenement
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject isUserParticipates(long eventId);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();
}