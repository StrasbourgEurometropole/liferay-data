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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StrasbourgService}.
 *
 * @author Brian Wing Shun Chan
 * @see StrasbourgService
 * @generated
 */
@ProviderType
public class StrasbourgServiceWrapper implements StrasbourgService,
	ServiceWrapper<StrasbourgService> {
	public StrasbourgServiceWrapper(StrasbourgService strasbourgService) {
		_strasbourgService = strasbourgService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getCoordinateForAddress(
		java.lang.String address) {
		return _strasbourgService.getCoordinateForAddress(address);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getPracticeCategories(
		long parentCategoryId, java.lang.String localeId) {
		return _strasbourgService.getPracticeCategories(parentCategoryId,
			localeId);
	}

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
	@Override
	public com.liferay.portal.kernel.json.JSONObject addDocument(
		java.lang.String fileContent, java.lang.String fileName,
		java.lang.String commissionName, java.lang.String publicationDate,
		java.lang.String documentType, java.lang.String documentName) {
		return _strasbourgService.addDocument(fileContent, fileName,
			commissionName, publicationDate, documentType, documentName);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getAlerts() {
		return _strasbourgService.getAlerts();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getCopyright(
		long groupId, java.lang.String uuid, java.lang.String language) {
		return _strasbourgService.getCopyright(groupId, uuid, language);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getFavoritesPois(
		long groupId) {
		return _strasbourgService.getFavoritesPois(groupId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getFavoritesPois(
		long groupId, java.lang.String typeContenu) {
		return _strasbourgService.getFavoritesPois(groupId, typeContenu);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getFavoritesPois(
		long groupId, java.lang.String typeContenu, java.lang.String localeId) {
		return _strasbourgService.getFavoritesPois(groupId, typeContenu,
			localeId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getFileDetails(
		long groupId, java.lang.String uuid, java.lang.String language) {
		return _strasbourgService.getFileDetails(groupId, uuid, language);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getPois(
		java.lang.String interests, java.lang.String categories,
		java.lang.String prefilters, long groupId, java.lang.String typeContenu) {
		return _strasbourgService.getPois(interests, categories, prefilters,
			groupId, typeContenu);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getPois(
		java.lang.String interests, java.lang.String categories,
		java.lang.String prefilters, long groupId,
		java.lang.String typeContenu, java.lang.String localeId) {
		return _strasbourgService.getPois(interests, categories, prefilters,
			groupId, typeContenu, localeId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getPois(
		java.lang.String interests, long groupId) {
		return _strasbourgService.getPois(interests, groupId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getPois(
		java.lang.String interests, long groupId, java.lang.String localeId) {
		return _strasbourgService.getPois(interests, groupId, localeId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getTraffic() {
		return _strasbourgService.getTraffic();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject searchStreets(
		java.lang.String query) {
		return _strasbourgService.searchStreets(query);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject searchStreets(
		java.lang.String query, java.lang.String city) {
		return _strasbourgService.searchStreets(query, city);
	}

	@Override
	public int getFavoritesPoisCount(long groupId, java.lang.String typeContenu) {
		return _strasbourgService.getFavoritesPoisCount(groupId, typeContenu);
	}

	@Override
	public int getPoisCategoryCount(long idCategory,
		java.lang.String prefilters, long groupId, java.lang.String typeContenu) {
		return _strasbourgService.getPoisCategoryCount(idCategory, prefilters,
			groupId, typeContenu);
	}

	@Override
	public int getPoisInterestCount(long idCategory, long groupId,
		java.lang.String typeContenu) {
		return _strasbourgService.getPoisInterestCount(idCategory, groupId,
			typeContenu);
	}

	@Override
	public java.lang.String getArticleHTMLContent(long groupId,
		java.lang.String articleId) {
		return _strasbourgService.getArticleHTMLContent(groupId, articleId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _strasbourgService.getOSGiServiceIdentifier();
	}

	@Override
	public void foldPortlet(java.lang.String portletId) {
		_strasbourgService.foldPortlet(portletId);
	}

	@Override
	public void hidePortlet(java.lang.String portletId) {
		_strasbourgService.hidePortlet(portletId);
	}

	@Override
	public void unfoldPortlet(java.lang.String portletId) {
		_strasbourgService.unfoldPortlet(portletId);
	}

	@Override
	public StrasbourgService getWrappedService() {
		return _strasbourgService;
	}

	@Override
	public void setWrappedService(StrasbourgService strasbourgService) {
		_strasbourgService = strasbourgService;
	}

	private StrasbourgService _strasbourgService;
}