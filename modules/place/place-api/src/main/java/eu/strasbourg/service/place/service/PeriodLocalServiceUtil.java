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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Period. This utility wraps
 * {@link eu.strasbourg.service.place.service.impl.PeriodLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Angelique Zunino Champougny
 * @see PeriodLocalService
 * @see eu.strasbourg.service.place.service.base.PeriodLocalServiceBaseImpl
 * @see eu.strasbourg.service.place.service.impl.PeriodLocalServiceImpl
 * @generated
 */
@ProviderType
public class PeriodLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.place.service.impl.PeriodLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the period to the database. Also notifies the appropriate model listeners.
	*
	* @param period the period
	* @return the period that was added
	*/
	public static eu.strasbourg.service.place.model.Period addPeriod(
		eu.strasbourg.service.place.model.Period period) {
		return getService().addPeriod(period);
	}

	/**
	* Crée une période vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.place.model.Period createPeriod(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createPeriod(sc);
	}

	/**
	* Creates a new period with the primary key. Does not add the period to the database.
	*
	* @param periodId the primary key for the new period
	* @return the new period
	*/
	public static eu.strasbourg.service.place.model.Period createPeriod(
		long periodId) {
		return getService().createPeriod(periodId);
	}

	/**
	* Deletes the period from the database. Also notifies the appropriate model listeners.
	*
	* @param period the period
	* @return the period that was removed
	*/
	public static eu.strasbourg.service.place.model.Period deletePeriod(
		eu.strasbourg.service.place.model.Period period) {
		return getService().deletePeriod(period);
	}

	/**
	* Deletes the period with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param periodId the primary key of the period
	* @return the period that was removed
	* @throws PortalException if a period with the primary key could not be found
	*/
	public static eu.strasbourg.service.place.model.Period deletePeriod(
		long periodId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePeriod(periodId);
	}

	public static eu.strasbourg.service.place.model.Period fetchPeriod(
		long periodId) {
		return getService().fetchPeriod(periodId);
	}

	/**
	* Returns the period with the primary key.
	*
	* @param periodId the primary key of the period
	* @return the period
	* @throws PortalException if a period with the primary key could not be found
	*/
	public static eu.strasbourg.service.place.model.Period getPeriod(
		long periodId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPeriod(periodId);
	}

	/**
	* Supprime une période
	*/
	public static eu.strasbourg.service.place.model.Period removePeriod(
		long periodId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removePeriod(periodId);
	}

	/**
	* Updates the period in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param period the period
	* @return the period that was updated
	*/
	public static eu.strasbourg.service.place.model.Period updatePeriod(
		eu.strasbourg.service.place.model.Period period) {
		return getService().updatePeriod(period);
	}

	/**
	* Returns the number of periods.
	*
	* @return the number of periods
	*/
	public static int getPeriodsCount() {
		return getService().getPeriodsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Retourne les Periods rattachées à un lieu
	*/
	public static java.util.List<eu.strasbourg.service.place.model.Period> getByPlaceId(
		long placeId) {
		return getService().getByPlaceId(placeId);
	}

	/**
	* Retourne les Periods rattachées à un sous-lieu
	*/
	public static java.util.List<eu.strasbourg.service.place.model.Period> getBySubPlaceId(
		long subPlaceId) {
		return getService().getBySubPlaceId(subPlaceId);
	}

	/**
	* Returns a range of all the periods.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of periods
	* @param end the upper bound of the range of periods (not inclusive)
	* @return the range of periods
	*/
	public static java.util.List<eu.strasbourg.service.place.model.Period> getPeriods(
		int start, int end) {
		return getService().getPeriods(start, end);
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

	public static PeriodLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PeriodLocalService, PeriodLocalService> _serviceTracker =
		ServiceTrackerFactory.open(PeriodLocalService.class);
}