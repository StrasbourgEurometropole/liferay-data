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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CsmapCacheJsonLocalService}.
 *
 * @author BenjaminBini
 * @see CsmapCacheJsonLocalService
 * @generated
 */
public class CsmapCacheJsonLocalServiceWrapper
	implements CsmapCacheJsonLocalService,
			   ServiceWrapper<CsmapCacheJsonLocalService> {

	public CsmapCacheJsonLocalServiceWrapper(
		CsmapCacheJsonLocalService csmapCacheJsonLocalService) {

		_csmapCacheJsonLocalService = csmapCacheJsonLocalService;
	}

	/**
	 * Adds the csmap cache json to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CsmapCacheJsonLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param csmapCacheJson the csmap cache json
	 * @return the csmap cache json that was added
	 */
	@Override
	public eu.strasbourg.service.agenda.model.CsmapCacheJson addCsmapCacheJson(
		eu.strasbourg.service.agenda.model.CsmapCacheJson csmapCacheJson) {

		return _csmapCacheJsonLocalService.addCsmapCacheJson(csmapCacheJson);
	}

	/**
	 * Creates a new csmap cache json with the primary key. Does not add the csmap cache json to the database.
	 *
	 * @param eventId the primary key for the new csmap cache json
	 * @return the new csmap cache json
	 */
	@Override
	public eu.strasbourg.service.agenda.model.CsmapCacheJson
		createCsmapCacheJson(long eventId) {

		return _csmapCacheJsonLocalService.createCsmapCacheJson(eventId);
	}

	/**
	 * Deletes the csmap cache json from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CsmapCacheJsonLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param csmapCacheJson the csmap cache json
	 * @return the csmap cache json that was removed
	 */
	@Override
	public eu.strasbourg.service.agenda.model.CsmapCacheJson
		deleteCsmapCacheJson(
			eu.strasbourg.service.agenda.model.CsmapCacheJson csmapCacheJson) {

		return _csmapCacheJsonLocalService.deleteCsmapCacheJson(csmapCacheJson);
	}

	/**
	 * Deletes the csmap cache json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CsmapCacheJsonLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param eventId the primary key of the csmap cache json
	 * @return the csmap cache json that was removed
	 * @throws PortalException if a csmap cache json with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.agenda.model.CsmapCacheJson
			deleteCsmapCacheJson(long eventId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _csmapCacheJsonLocalService.deleteCsmapCacheJson(eventId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _csmapCacheJsonLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _csmapCacheJsonLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _csmapCacheJsonLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.agenda.model.impl.CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _csmapCacheJsonLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.agenda.model.impl.CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _csmapCacheJsonLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _csmapCacheJsonLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _csmapCacheJsonLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.agenda.model.CsmapCacheJson
		fetchCsmapCacheJson(long eventId) {

		return _csmapCacheJsonLocalService.fetchCsmapCacheJson(eventId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _csmapCacheJsonLocalService.getActionableDynamicQuery();
	}

	/**
	 * Retourne les caches d'un event créé après une date et actif
	 */
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.CsmapCacheJson>
		getByCreatedDateAndIsActive(java.util.Date date) {

		return _csmapCacheJsonLocalService.getByCreatedDateAndIsActive(date);
	}

	/**
	 * Retourne les caches d'un event créé après une date, actif et avec schedules
	 */
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.CsmapCacheJson>
		getByCreatedDateAndIsActiveAndWithSchedules(java.util.Date date) {

		return _csmapCacheJsonLocalService.
			getByCreatedDateAndIsActiveAndWithSchedules(date);
	}

	/**
	 * Retourne les caches d'un event modifié après une date, créé avant cette date et actif
	 */
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.CsmapCacheJson>
		getByCreatedDateAndModifiedDateAndIsActive(java.util.Date date) {

		return _csmapCacheJsonLocalService.
			getByCreatedDateAndModifiedDateAndIsActive(date);
	}

	/**
	 * Retourne les caches d'un event modifié après une date, créé avant cette date, actif
	 * et avec schedules
	 */
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.CsmapCacheJson>
		getByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(
			java.util.Date date) {

		return _csmapCacheJsonLocalService.
			getByCreatedDateAndModifiedDateAndIsActiveAndWithSchedules(date);
	}

	/**
	 * Retourne les caches d'un lieu modifié après une date et inactif
	 */
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.CsmapCacheJson>
		getByModifiedDateAndIsNotActive(java.util.Date date) {

		return _csmapCacheJsonLocalService.getByModifiedDateAndIsNotActive(
			date);
	}

	/**
	 * Returns the csmap cache json with the primary key.
	 *
	 * @param eventId the primary key of the csmap cache json
	 * @return the csmap cache json
	 * @throws PortalException if a csmap cache json with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.agenda.model.CsmapCacheJson getCsmapCacheJson(
			long eventId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _csmapCacheJsonLocalService.getCsmapCacheJson(eventId);
	}

	/**
	 * Returns a range of all the csmap cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.agenda.model.impl.CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of csmap cache jsons
	 */
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.CsmapCacheJson>
		getCsmapCacheJsons(int start, int end) {

		return _csmapCacheJsonLocalService.getCsmapCacheJsons(start, end);
	}

	/**
	 * Returns the number of csmap cache jsons.
	 *
	 * @return the number of csmap cache jsons
	 */
	@Override
	public int getCsmapCacheJsonsCount() {
		return _csmapCacheJsonLocalService.getCsmapCacheJsonsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _csmapCacheJsonLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _csmapCacheJsonLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _csmapCacheJsonLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the csmap cache json in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CsmapCacheJsonLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param csmapCacheJson the csmap cache json
	 * @return the csmap cache json that was updated
	 */
	@Override
	public eu.strasbourg.service.agenda.model.CsmapCacheJson
		updateCsmapCacheJson(
			eu.strasbourg.service.agenda.model.CsmapCacheJson csmapCacheJson) {

		return _csmapCacheJsonLocalService.updateCsmapCacheJson(csmapCacheJson);
	}

	/**
	 * Met à jour les jsonEvent des event
	 */
	@Override
	public void updateJsonEvent() {
		_csmapCacheJsonLocalService.updateJsonEvent();
	}

	@Override
	public CsmapCacheJsonLocalService getWrappedService() {
		return _csmapCacheJsonLocalService;
	}

	@Override
	public void setWrappedService(
		CsmapCacheJsonLocalService csmapCacheJsonLocalService) {

		_csmapCacheJsonLocalService = csmapCacheJsonLocalService;
	}

	private CsmapCacheJsonLocalService _csmapCacheJsonLocalService;

}