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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CsmapCacheJson. This utility wraps
 * <code>eu.strasbourg.service.place.service.impl.CsmapCacheJsonLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Angelique Zunino Champougny
 * @see CsmapCacheJsonLocalService
 * @generated
 */
public class CsmapCacheJsonLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.place.service.impl.CsmapCacheJsonLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static eu.strasbourg.service.place.model.CsmapCacheJson
		addCsmapCacheJson(
			eu.strasbourg.service.place.model.CsmapCacheJson csmapCacheJson) {

		return getService().addCsmapCacheJson(csmapCacheJson);
	}

	/**
	 * Creates a new csmap cache json with the primary key. Does not add the csmap cache json to the database.
	 *
	 * @param sigId the primary key for the new csmap cache json
	 * @return the new csmap cache json
	 */
	public static eu.strasbourg.service.place.model.CsmapCacheJson
		createCsmapCacheJson(String sigId) {

		return getService().createCsmapCacheJson(sigId);
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
	public static eu.strasbourg.service.place.model.CsmapCacheJson
		deleteCsmapCacheJson(
			eu.strasbourg.service.place.model.CsmapCacheJson csmapCacheJson) {

		return getService().deleteCsmapCacheJson(csmapCacheJson);
	}

	/**
	 * Deletes the csmap cache json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CsmapCacheJsonLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sigId the primary key of the csmap cache json
	 * @return the csmap cache json that was removed
	 * @throws PortalException if a csmap cache json with the primary key could not be found
	 */
	public static eu.strasbourg.service.place.model.CsmapCacheJson
			deleteCsmapCacheJson(String sigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCsmapCacheJson(sigId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.CsmapCacheJsonModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.CsmapCacheJsonModelImpl</code>.
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

	public static eu.strasbourg.service.place.model.CsmapCacheJson
		fetchCsmapCacheJson(String sigId) {

		return getService().fetchCsmapCacheJson(sigId);
	}

	/**
	 * Retourne les caches d'un lieu créé après une date et actif
	 */
	public static java.util.List
		<eu.strasbourg.service.place.model.CsmapCacheJson>
			getByCreatedDateAndIsActive(java.util.Date date) {

		return getService().getByCreatedDateAndIsActive(date);
	}

	/**
	 * Retourne les caches d'un lieu modifié après une date, créé avant cette date et actif
	 */
	public static java.util.List
		<eu.strasbourg.service.place.model.CsmapCacheJson>
			getByCreatedDateAndModifiedDateAndIsActive(java.util.Date date) {

		return getService().getByCreatedDateAndModifiedDateAndIsActive(date);
	}

	/**
	 * Retourne les caches d'un lieu modifié après une date et inactif
	 */
	public static java.util.List
		<eu.strasbourg.service.place.model.CsmapCacheJson>
			getByModifiedDateAndIsNotActive(java.util.Date date) {

		return getService().getByModifiedDateAndIsNotActive(date);
	}

	/**
	 * Returns the csmap cache json with the primary key.
	 *
	 * @param sigId the primary key of the csmap cache json
	 * @return the csmap cache json
	 * @throws PortalException if a csmap cache json with the primary key could not be found
	 */
	public static eu.strasbourg.service.place.model.CsmapCacheJson
			getCsmapCacheJson(String sigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCsmapCacheJson(sigId);
	}

	/**
	 * Returns a range of all the csmap cache jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.CsmapCacheJsonModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of csmap cache jsons
	 * @param end the upper bound of the range of csmap cache jsons (not inclusive)
	 * @return the range of csmap cache jsons
	 */
	public static java.util.List
		<eu.strasbourg.service.place.model.CsmapCacheJson> getCsmapCacheJsons(
			int start, int end) {

		return getService().getCsmapCacheJsons(start, end);
	}

	/**
	 * Returns the number of csmap cache jsons.
	 *
	 * @return the number of csmap cache jsons
	 */
	public static int getCsmapCacheJsonsCount() {
		return getService().getCsmapCacheJsonsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
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
	public static eu.strasbourg.service.place.model.CsmapCacheJson
		updateCsmapCacheJson(
			eu.strasbourg.service.place.model.CsmapCacheJson csmapCacheJson) {

		return getService().updateCsmapCacheJson(csmapCacheJson);
	}

	public static CsmapCacheJsonLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CsmapCacheJsonLocalService, CsmapCacheJsonLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CsmapCacheJsonLocalService.class);

		ServiceTracker<CsmapCacheJsonLocalService, CsmapCacheJsonLocalService>
			serviceTracker =
				new ServiceTracker
					<CsmapCacheJsonLocalService, CsmapCacheJsonLocalService>(
						bundle.getBundleContext(),
						CsmapCacheJsonLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}