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
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(
	property = {
		"json.web.service.context.name=strasbourg",
		"json.web.service.context.path=Strasbourg"
	},
	service = StrasbourgService.class
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface StrasbourgService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StrasbourgServiceUtil} to access the strasbourg remote service. Add custom service methods to <code>eu.strasbourg.service.strasbourg.service.impl.StrasbourgServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Envoie <code>error</code> si le document n'a pas été envoyé.
	 *
	 * Returns <code>succes</code> un document de commission.
	 *
	 * @param fileContent le fichier en base 64
	 * @param fileName le nom du fichier
	 * @param commissionName le nom de la commission
	 * @param publicationDate la date de publication au format yyyy-MM-ddThh:mm:ss
	 * @param documentType Le type de docuemnt (Strasbourg, Eurométropole)
	 * @param documentName Le nom du document
	 * @return <code>succes</code> un document de commission, sinon <code>error</code>.
	 */
	public JSONObject addDocument(
		String fileContent, String fileName, String commissionName,
		String publicationDate, String documentType, String documentName);

	public void foldPortlet(String portletId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getAlerts();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getArticleHTMLContent(long groupId, String articleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getCategoriesByClassNameAndGroupIds(
		long[] groupIds, String className);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getCategoriesPois(
			String categories, String vocabulariesEmptyIds, String prefilters,
			String tags, long groupId, String typeContenu, boolean dateField,
			String fromDate, String toDate, String localeId, long globalGroupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getCoordinateForAddress(
			String address, String zipCode, String city);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getCopyright(long groupId, String uuid, String language);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getFavoritesPois(
			long groupId, String typeContenu, String localeId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getFileDetails(
		long groupId, String uuid, String language);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getInterestsPois(
		String interests, long groupId, String typeContenu, String localeId,
		long globalGroupId);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getPracticeCategories(
		long parentCategoryId, String localeId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getStructuresByGroupIds(long[] groupIds);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getTagsAndCategoriesByGroupIdsAndClassName(
		long[] groupIds, String className);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getTagsByGroupIds(long[] groupIds);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject getTraffic();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONArray getVocabulariesByGroupIds(long[] groupIds);

	public void hidePortlet(String portletId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject searchStreets(String query);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public JSONObject searchStreets(String query, String city);

	public void unfoldPortlet(String portletId);

}