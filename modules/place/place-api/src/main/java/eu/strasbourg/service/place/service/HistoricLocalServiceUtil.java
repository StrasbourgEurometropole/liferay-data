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
 * Provides the local service utility for Historic. This utility wraps
 * <code>eu.strasbourg.service.place.service.impl.HistoricLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Angelique Zunino Champougny
 * @see HistoricLocalService
 * @generated
 */
public class HistoricLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.place.service.impl.HistoricLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the historic to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HistoricLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param historic the historic
	 * @return the historic that was added
	 */
	public static eu.strasbourg.service.place.model.Historic addHistoric(
		eu.strasbourg.service.place.model.Historic historic) {

		return getService().addHistoric(historic);
	}

	/**
	 * Creates a new historic with the primary key. Does not add the historic to the database.
	 *
	 * @param sigId the primary key for the new historic
	 * @return the new historic
	 */
	public static eu.strasbourg.service.place.model.Historic createHistoric(
		String sigId) {

		return getService().createHistoric(sigId);
	}

	/**
	 * Deletes the historic from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HistoricLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param historic the historic
	 * @return the historic that was removed
	 */
	public static eu.strasbourg.service.place.model.Historic deleteHistoric(
		eu.strasbourg.service.place.model.Historic historic) {

		return getService().deleteHistoric(historic);
	}

	/**
	 * Deletes the historic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HistoricLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param sigId the primary key of the historic
	 * @return the historic that was removed
	 * @throws PortalException if a historic with the primary key could not be found
	 */
	public static eu.strasbourg.service.place.model.Historic deleteHistoric(
			String sigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteHistoric(sigId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.HistoricModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.HistoricModelImpl</code>.
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

	public static eu.strasbourg.service.place.model.Historic fetchHistoric(
		String sigId) {

		return getService().fetchHistoric(sigId);
	}

	/**
	 * Retourne les caches d'un lieu créé après une date et actif
	 */
	public static java.util.List<eu.strasbourg.service.place.model.Historic>
		getBySuppressionDate(java.util.Date date) {

		return getService().getBySuppressionDate(date);
	}

	/**
	 * Returns the historic with the primary key.
	 *
	 * @param sigId the primary key of the historic
	 * @return the historic
	 * @throws PortalException if a historic with the primary key could not be found
	 */
	public static eu.strasbourg.service.place.model.Historic getHistoric(
			String sigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getHistoric(sigId);
	}

	/**
	 * Returns a range of all the historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.HistoricModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @return the range of historics
	 */
	public static java.util.List<eu.strasbourg.service.place.model.Historic>
		getHistorics(int start, int end) {

		return getService().getHistorics(start, end);
	}

	/**
	 * Returns the number of historics.
	 *
	 * @return the number of historics
	 */
	public static int getHistoricsCount() {
		return getService().getHistoricsCount();
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
	 * Updates the historic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HistoricLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param historic the historic
	 * @return the historic that was updated
	 */
	public static eu.strasbourg.service.place.model.Historic updateHistoric(
		eu.strasbourg.service.place.model.Historic historic) {

		return getService().updateHistoric(historic);
	}

	public static HistoricLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<HistoricLocalService, HistoricLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(HistoricLocalService.class);

		ServiceTracker<HistoricLocalService, HistoricLocalService>
			serviceTracker =
				new ServiceTracker<HistoricLocalService, HistoricLocalService>(
					bundle.getBundleContext(), HistoricLocalService.class,
					null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}