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

package eu.strasbourg.service.place.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CacheJsonLocalService}.
 *
 * @author Angelique Zunino Champougny
 * @see CacheJsonLocalService
 * @generated
 */
@ProviderType
public class CacheJsonLocalServiceWrapper
	implements CacheJsonLocalService, ServiceWrapper<CacheJsonLocalService> {

	public CacheJsonLocalServiceWrapper(
		CacheJsonLocalService cacheJsonLocalService) {

		_cacheJsonLocalService = cacheJsonLocalService;
	}

	/**
	 * Adds the cache json to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheJson the cache json
	 * @return the cache json that was added
	 */
	@Override
	public eu.strasbourg.service.place.model.CacheJson addCacheJson(
		eu.strasbourg.service.place.model.CacheJson cacheJson) {

		return _cacheJsonLocalService.addCacheJson(cacheJson);
	}

	/**
	 * Creates a new cache json with the primary key. Does not add the cache json to the database.
	 *
	 * @param sigId the primary key for the new cache json
	 * @return the new cache json
	 */
	@Override
	public eu.strasbourg.service.place.model.CacheJson createCacheJson(
		String sigId) {

		return _cacheJsonLocalService.createCacheJson(sigId);
	}

	/**
	 * Deletes the cache json from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheJson the cache json
	 * @return the cache json that was removed
	 */
	@Override
	public eu.strasbourg.service.place.model.CacheJson deleteCacheJson(
		eu.strasbourg.service.place.model.CacheJson cacheJson) {

		return _cacheJsonLocalService.deleteCacheJson(cacheJson);
	}

	/**
	 * Deletes the cache json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sigId the primary key of the cache json
	 * @return the cache json that was removed
	 * @throws PortalException if a cache json with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.place.model.CacheJson deleteCacheJson(
			String sigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cacheJsonLocalService.deleteCacheJson(sigId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cacheJsonLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cacheJsonLocalService.dynamicQuery();
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

		return _cacheJsonLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _cacheJsonLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _cacheJsonLocalService.dynamicQuery(
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

		return _cacheJsonLocalService.dynamicQueryCount(dynamicQuery);
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

		return _cacheJsonLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.place.model.CacheJson fetchCacheJson(
		String sigId) {

		return _cacheJsonLocalService.fetchCacheJson(sigId);
	}

	/**
	 * Retourne les caches d'un lieu créé après une date et actif
	 */
	@Override
	public java.util.List<eu.strasbourg.service.place.model.CacheJson>
		getByCreatedDateAndIsActive(java.util.Date date) {

		return _cacheJsonLocalService.getByCreatedDateAndIsActive(date);
	}

	/**
	 * Retourne les caches d'un lieu modifié après une date, créé avant cette date et actif
	 */
	@Override
	public java.util.List<eu.strasbourg.service.place.model.CacheJson>
		getByCreatedDateAndModifiedDateAndIsActive(java.util.Date date) {

		return _cacheJsonLocalService.
			getByCreatedDateAndModifiedDateAndIsActive(date);
	}

	/**
	 * Retourne les caches d'un lieu modifié après une date et inactif
	 */
	@Override
	public java.util.List<eu.strasbourg.service.place.model.CacheJson>
		getByModifiedDateAndIsNotActive(java.util.Date date) {

		return _cacheJsonLocalService.getByModifiedDateAndIsNotActive(date);
	}

	/**
	 * Returns the cache json with the primary key.
	 *
	 * @param sigId the primary key of the cache json
	 * @return the cache json
	 * @throws PortalException if a cache json with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.place.model.CacheJson getCacheJson(
			String sigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cacheJsonLocalService.getCacheJson(sigId);
	}

	/**
	 * Returns a range of all the cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.CacheJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache jsons
	 * @param end the upper bound of the range of cache jsons (not inclusive)
	 * @return the range of cache jsons
	 */
	@Override
	public java.util.List<eu.strasbourg.service.place.model.CacheJson>
		getCacheJsons(int start, int end) {

		return _cacheJsonLocalService.getCacheJsons(start, end);
	}

	/**
	 * Returns the number of cache jsons.
	 *
	 * @return the number of cache jsons
	 */
	@Override
	public int getCacheJsonsCount() {
		return _cacheJsonLocalService.getCacheJsonsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cacheJsonLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cacheJsonLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the cache json in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cacheJson the cache json
	 * @return the cache json that was updated
	 */
	@Override
	public eu.strasbourg.service.place.model.CacheJson updateCacheJson(
		eu.strasbourg.service.place.model.CacheJson cacheJson) {

		return _cacheJsonLocalService.updateCacheJson(cacheJson);
	}

	@Override
	public CacheJsonLocalService getWrappedService() {
		return _cacheJsonLocalService;
	}

	@Override
	public void setWrappedService(CacheJsonLocalService cacheJsonLocalService) {
		_cacheJsonLocalService = cacheJsonLocalService;
	}

	private CacheJsonLocalService _cacheJsonLocalService;

}