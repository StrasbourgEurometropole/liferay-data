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

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CacheAgendaJson. This utility wraps
 * <code>eu.strasbourg.service.csmap.service.impl.CacheAgendaJsonLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see CacheAgendaJsonLocalService
 * @generated
 */
@ProviderType
public class CacheAgendaJsonLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.csmap.service.impl.CacheAgendaJsonLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the cache agenda json to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheAgendaJson the cache agenda json
	 * @return the cache agenda json that was added
	 */
	public static eu.strasbourg.service.csmap.model.CacheAgendaJson
		addCacheAgendaJson(
			eu.strasbourg.service.csmap.model.CacheAgendaJson cacheAgendaJson) {

		return getService().addCacheAgendaJson(cacheAgendaJson);
	}

	/**
	 * Creates a new cache agenda json with the primary key. Does not add the cache agenda json to the database.
	 *
	 * @param cacheId the primary key for the new cache agenda json
	 * @return the new cache agenda json
	 */
	public static eu.strasbourg.service.csmap.model.CacheAgendaJson
		createCacheAgendaJson(long cacheId) {

		return getService().createCacheAgendaJson(cacheId);
	}

	/**
	 * Deletes the cache agenda json from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheAgendaJson the cache agenda json
	 * @return the cache agenda json that was removed
	 */
	public static eu.strasbourg.service.csmap.model.CacheAgendaJson
		deleteCacheAgendaJson(
			eu.strasbourg.service.csmap.model.CacheAgendaJson cacheAgendaJson) {

		return getService().deleteCacheAgendaJson(cacheAgendaJson);
	}

	/**
	 * Deletes the cache agenda json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cacheId the primary key of the cache agenda json
	 * @return the cache agenda json that was removed
	 * @throws PortalException if a cache agenda json with the primary key could not be found
	 */
	public static eu.strasbourg.service.csmap.model.CacheAgendaJson
			deleteCacheAgendaJson(long cacheId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCacheAgendaJson(cacheId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.CacheAgendaJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.CacheAgendaJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static eu.strasbourg.service.csmap.model.CacheAgendaJson
		fetchCacheAgendaJson(long cacheId) {

		return getService().fetchCacheAgendaJson(cacheId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the cache agenda json with the primary key.
	 *
	 * @param cacheId the primary key of the cache agenda json
	 * @return the cache agenda json
	 * @throws PortalException if a cache agenda json with the primary key could not be found
	 */
	public static eu.strasbourg.service.csmap.model.CacheAgendaJson
			getCacheAgendaJson(long cacheId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCacheAgendaJson(cacheId);
	}

	/**
	 * Returns a range of all the cache agenda jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.CacheAgendaJsonModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache agenda jsons
	 * @param end the upper bound of the range of cache agenda jsons (not inclusive)
	 * @return the range of cache agenda jsons
	 */
	public static java.util.List
		<eu.strasbourg.service.csmap.model.CacheAgendaJson> getCacheAgendaJsons(
			int start, int end) {

		return getService().getCacheAgendaJsons(start, end);
	}

	/**
	 * Returns the number of cache agenda jsons.
	 *
	 * @return the number of cache agenda jsons
	 */
	public static int getCacheAgendaJsonsCount() {
		return getService().getCacheAgendaJsonsCount();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Met à jour le cache des agendas
	 */
	public static void updateCacheAgendaJson() {
		getService().updateCacheAgendaJson();
	}

	/**
	 * Updates the cache agenda json in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cacheAgendaJson the cache agenda json
	 * @return the cache agenda json that was updated
	 */
	public static eu.strasbourg.service.csmap.model.CacheAgendaJson
		updateCacheAgendaJson(
			eu.strasbourg.service.csmap.model.CacheAgendaJson cacheAgendaJson) {

		return getService().updateCacheAgendaJson(cacheAgendaJson);
	}

	public static CacheAgendaJsonLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CacheAgendaJsonLocalService, CacheAgendaJsonLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CacheAgendaJsonLocalService.class);

		ServiceTracker<CacheAgendaJsonLocalService, CacheAgendaJsonLocalService>
			serviceTracker =
				new ServiceTracker
					<CacheAgendaJsonLocalService, CacheAgendaJsonLocalService>(
						bundle.getBundleContext(),
						CacheAgendaJsonLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}