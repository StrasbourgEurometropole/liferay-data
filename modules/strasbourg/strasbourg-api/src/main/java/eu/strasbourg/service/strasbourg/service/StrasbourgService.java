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

package eu.strasbourg.service.strasbourg.service;

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
 * Provides the remote service interface for Strasbourg. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see StrasbourgServiceUtil
 * @see eu.strasbourg.service.strasbourg.service.base.StrasbourgServiceBaseImpl
 * @see eu.strasbourg.service.strasbourg.service.impl.StrasbourgServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=strasbourg", "json.web.service.context.path=Strasbourg"}, service = StrasbourgService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface StrasbourgService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StrasbourgServiceUtil} to access the strasbourg remote service. Add custom service methods to {@link eu.strasbourg.service.strasbourg.service.impl.StrasbourgServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getCoordinateForAddress(java.lang.String address);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getAlerts();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getCopyright(long groupId, java.lang.String uuid,
		java.lang.String language);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getFavoritesPois(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getFavoritesPois(long groupId,
		java.lang.String typeContenu);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getFileDetails(long groupId, java.lang.String uuid,
		java.lang.String language);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getPois(java.lang.String interests,
		java.lang.String categories, java.lang.String prefilters, long groupId,
		java.lang.String typeContenu);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getPois(java.lang.String interests, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getTraffic();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject searchStreets(java.lang.String query);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject searchStreets(java.lang.String query,
		java.lang.String city);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFavoritesPoisCount(long groupId, java.lang.String typeContenu);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPoisCategoryCount(long idCategory,
		java.lang.String prefilters, long groupId, java.lang.String typeContenu);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPoisInterestCount(long idCategory, long groupId,
		java.lang.String typeContenu);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.lang.String getArticleHTMLContent(long groupId,
		java.lang.String articleId);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	public void hidePortlet(java.lang.String portletId);
}