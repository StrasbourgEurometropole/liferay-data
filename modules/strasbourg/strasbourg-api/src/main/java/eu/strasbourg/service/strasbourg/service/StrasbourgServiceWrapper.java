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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StrasbourgService}.
 *
 * @author Brian Wing Shun Chan
 * @see StrasbourgService
 * @generated
 */
public class StrasbourgServiceWrapper
	implements ServiceWrapper<StrasbourgService>, StrasbourgService {

	public StrasbourgServiceWrapper(StrasbourgService strasbourgService) {
		_strasbourgService = strasbourgService;
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
	 * @param publicationDateFin la date de fin de publication au format yyyy-MM-ddThh:mm:ss
	 * @param documentType Le type de document (Strasbourg, Eurométropole)
	 * @param documentName Le nom du document
	 * @return <code>succes</code> un document de commission, sinon <code>error</code>.
	 */
	@Override
	public com.liferay.portal.kernel.json.JSONObject addDocument(
		String fileContent, String fileName, String commissionName,
		String publicationDate, String publicationDateFin, String documentType,
		String documentName) {

		return _strasbourgService.addDocument(
			fileContent, fileName, commissionName, publicationDate,
			publicationDateFin, documentType, documentName);
	}

	@Override
	public void foldPortlet(String portletId) {
		_strasbourgService.foldPortlet(portletId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getAlerts() {
		return _strasbourgService.getAlerts();
	}

	@Override
	public String getArticleHTMLContent(long groupId, String articleId) {
		return _strasbourgService.getArticleHTMLContent(groupId, articleId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray
		getCategoriesByClassNameAndGroupIds(long[] groupIds, String className) {

		return _strasbourgService.getCategoriesByClassNameAndGroupIds(
			groupIds, className);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getCategoriesPois(
		String categories, String vocabulariesEmptyIds, String prefilters,
		String tags, long groupId, String typeContenu, boolean dateField,
		String fromDate, String toDate, String localeId, long globalGroupId) {

		return _strasbourgService.getCategoriesPois(
			categories, vocabulariesEmptyIds, prefilters, tags, groupId,
			typeContenu, dateField, fromDate, toDate, localeId, globalGroupId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getCoordinateForAddress(
		String address, String zipCode, String city) {

		return _strasbourgService.getCoordinateForAddress(
			address, zipCode, city);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getCopyright(
		long groupId, String uuid, String language) {

		return _strasbourgService.getCopyright(groupId, uuid, language);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getFavoritesPois(
		long groupId, String typeContenu, String localeId) {

		return _strasbourgService.getFavoritesPois(
			groupId, typeContenu, localeId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getFileDetails(
		long groupId, String uuid, String language) {

		return _strasbourgService.getFileDetails(groupId, uuid, language);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getInterestsPois(
		String interests, long groupId, String typeContenu, String localeId,
		long globalGroupId) {

		return _strasbourgService.getInterestsPois(
			interests, groupId, typeContenu, localeId, globalGroupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _strasbourgService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getPracticeCategories(
		long parentCategoryId, String localeId) {

		return _strasbourgService.getPracticeCategories(
			parentCategoryId, localeId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getStructuresByGroupIds(
		long[] groupIds) {

		return _strasbourgService.getStructuresByGroupIds(groupIds);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject
		getTagsAndCategoriesByGroupIdsAndClassName(
			long[] groupIds, String className) {

		return _strasbourgService.getTagsAndCategoriesByGroupIdsAndClassName(
			groupIds, className);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getTagsByGroupIds(
		long[] groupIds) {

		return _strasbourgService.getTagsByGroupIds(groupIds);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getTraffic() {
		return _strasbourgService.getTraffic();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getVocabulariesByGroupIds(
		long[] groupIds) {

		return _strasbourgService.getVocabulariesByGroupIds(groupIds);
	}

	@Override
	public void hidePortlet(String portletId) {
		_strasbourgService.hidePortlet(portletId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject searchStreets(
		String query) {

		return _strasbourgService.searchStreets(query);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject searchStreets(
		String query, String city) {

		return _strasbourgService.searchStreets(query, city);
	}

	@Override
	public void unfoldPortlet(String portletId) {
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