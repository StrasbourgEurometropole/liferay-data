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

package eu.strasbourg.service.csmap.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link CsmapCacheLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CsmapCacheLocalService
 * @generated
 */
@ProviderType
public class CsmapCacheLocalServiceWrapper
	implements CsmapCacheLocalService, ServiceWrapper<CsmapCacheLocalService> {

	public CsmapCacheLocalServiceWrapper(
		CsmapCacheLocalService csmapCacheLocalService) {

		_csmapCacheLocalService = csmapCacheLocalService;
	}

	/**
	 * Adds the csmap cache to the database. Also notifies the appropriate model listeners.
	 *
	 * @param csmapCache the csmap cache
	 * @return the csmap cache that was added
	 */
	@Override
	public eu.strasbourg.service.csmap.model.CsmapCache addCsmapCache(
		eu.strasbourg.service.csmap.model.CsmapCache csmapCache) {

		return _csmapCacheLocalService.addCsmapCache(csmapCache);
	}

	/**
	 * Creates a new csmap cache with the primary key. Does not add the csmap cache to the database.
	 *
	 * @param cacheId the primary key for the new csmap cache
	 * @return the new csmap cache
	 */
	@Override
	public eu.strasbourg.service.csmap.model.CsmapCache createCsmapCache(
		long cacheId) {

		return _csmapCacheLocalService.createCsmapCache(cacheId);
	}

	@Override
	public eu.strasbourg.service.csmap.model.CsmapCache createCsmapCache(
		long codeCache, String json, java.util.Date date) {

		return _csmapCacheLocalService.createCsmapCache(codeCache, json, date);
	}

	/**
	 * Deletes the csmap cache from the database. Also notifies the appropriate model listeners.
	 *
	 * @param csmapCache the csmap cache
	 * @return the csmap cache that was removed
	 */
	@Override
	public eu.strasbourg.service.csmap.model.CsmapCache deleteCsmapCache(
		eu.strasbourg.service.csmap.model.CsmapCache csmapCache) {

		return _csmapCacheLocalService.deleteCsmapCache(csmapCache);
	}

	/**
	 * Deletes the csmap cache with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheId the primary key of the csmap cache
	 * @return the csmap cache that was removed
	 * @throws PortalException if a csmap cache with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.csmap.model.CsmapCache deleteCsmapCache(
			long cacheId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _csmapCacheLocalService.deleteCsmapCache(cacheId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _csmapCacheLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _csmapCacheLocalService.dynamicQuery();
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

		return _csmapCacheLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.CsmapCacheModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _csmapCacheLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.CsmapCacheModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _csmapCacheLocalService.dynamicQuery(
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

		return _csmapCacheLocalService.dynamicQueryCount(dynamicQuery);
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

		return _csmapCacheLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.csmap.model.CsmapCache fetchByCodeCache(
		long codeCache) {

		return _csmapCacheLocalService.fetchByCodeCache(codeCache);
	}

	@Override
	public eu.strasbourg.service.csmap.model.CsmapCache fetchCsmapCache(
		long cacheId) {

		return _csmapCacheLocalService.fetchCsmapCache(cacheId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.csmap.model.CsmapCache>
		findLastProcessNotSuccess() {

		return _csmapCacheLocalService.findLastProcessNotSuccess();
	}

	@Override
	public void generateCsmapCache(long codeCache) {
		_csmapCacheLocalService.generateCsmapCache(codeCache);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _csmapCacheLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the csmap cache with the primary key.
	 *
	 * @param cacheId the primary key of the csmap cache
	 * @return the csmap cache
	 * @throws PortalException if a csmap cache with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.csmap.model.CsmapCache getCsmapCache(
			long cacheId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _csmapCacheLocalService.getCsmapCache(cacheId);
	}

	/**
	 * Returns a range of all the csmap caches.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.CsmapCacheModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap caches
	 * @param end the upper bound of the range of csmap caches (not inclusive)
	 * @return the range of csmap caches
	 */
	@Override
	public java.util.List<eu.strasbourg.service.csmap.model.CsmapCache>
		getCsmapCaches(int start, int end) {

		return _csmapCacheLocalService.getCsmapCaches(start, end);
	}

	/**
	 * Returns the number of csmap caches.
	 *
	 * @return the number of csmap caches
	 */
	@Override
	public int getCsmapCachesCount() {
		return _csmapCacheLocalService.getCsmapCachesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _csmapCacheLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getJsonVide() {
		return _csmapCacheLocalService.getJsonVide();
	}

	@Override
	public java.util.Date getLastModifiedEvent() {
		return _csmapCacheLocalService.getLastModifiedEvent();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _csmapCacheLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _csmapCacheLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the csmap cache in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param csmapCache the csmap cache
	 * @return the csmap cache that was updated
	 */
	@Override
	public eu.strasbourg.service.csmap.model.CsmapCache updateCsmapCache(
		eu.strasbourg.service.csmap.model.CsmapCache csmapCache) {

		return _csmapCacheLocalService.updateCsmapCache(csmapCache);
	}

	@Override
	public CsmapCacheLocalService getWrappedService() {
		return _csmapCacheLocalService;
	}

	@Override
	public void setWrappedService(
		CsmapCacheLocalService csmapCacheLocalService) {

		_csmapCacheLocalService = csmapCacheLocalService;
	}

	private CsmapCacheLocalService _csmapCacheLocalService;

}