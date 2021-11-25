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
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * Provides the remote service interface for Event. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author BenjaminBini
 * @see EventServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(
	property = {
		"json.web.service.context.name=agenda",
		"json.web.service.context.path=Event"
	},
	service = EventService.class
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface EventService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EventServiceUtil} to access the event remote service. Add custom service methods to <code>eu.strasbourg.service.agenda.service.impl.EventServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getCategories() throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getCategory(long id) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getEvent(long id) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getEvents() throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getEventsByCategory(String categoryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getEventsByDate(String date) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getEventsByLanguage(String language)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getEventsByPlace(String placeSIGId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getPublics() throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getServices() throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getSessions(long eventID);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getTerritories() throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getThemes() throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getTypes() throws PortalException;

}