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
 * Provides a wrapper for {@link PeriodLocalService}.
 *
 * @author Angelique Zunino Champougny
 * @see PeriodLocalService
 * @generated
 */
@ProviderType
public class PeriodLocalServiceWrapper implements PeriodLocalService,
	ServiceWrapper<PeriodLocalService> {
	public PeriodLocalServiceWrapper(PeriodLocalService periodLocalService) {
		_periodLocalService = periodLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _periodLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _periodLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _periodLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _periodLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _periodLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the period to the database. Also notifies the appropriate model listeners.
	*
	* @param period the period
	* @return the period that was added
	*/
	@Override
	public eu.strasbourg.service.place.model.Period addPeriod(
		eu.strasbourg.service.place.model.Period period) {
		return _periodLocalService.addPeriod(period);
	}

	/**
	* Crée une période vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.place.model.Period createPeriod(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _periodLocalService.createPeriod(sc);
	}

	/**
	* Creates a new period with the primary key. Does not add the period to the database.
	*
	* @param periodId the primary key for the new period
	* @return the new period
	*/
	@Override
	public eu.strasbourg.service.place.model.Period createPeriod(long periodId) {
		return _periodLocalService.createPeriod(periodId);
	}

	/**
	* Deletes the period from the database. Also notifies the appropriate model listeners.
	*
	* @param period the period
	* @return the period that was removed
	*/
	@Override
	public eu.strasbourg.service.place.model.Period deletePeriod(
		eu.strasbourg.service.place.model.Period period) {
		return _periodLocalService.deletePeriod(period);
	}

	/**
	* Deletes the period with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param periodId the primary key of the period
	* @return the period that was removed
	* @throws PortalException if a period with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.place.model.Period deletePeriod(long periodId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _periodLocalService.deletePeriod(periodId);
	}

	@Override
	public eu.strasbourg.service.place.model.Period fetchPeriod(long periodId) {
		return _periodLocalService.fetchPeriod(periodId);
	}

	/**
	* Returns the period with the primary key.
	*
	* @param periodId the primary key of the period
	* @return the period
	* @throws PortalException if a period with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.place.model.Period getPeriod(long periodId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _periodLocalService.getPeriod(periodId);
	}

	/**
	* Supprime une période
	*/
	@Override
	public eu.strasbourg.service.place.model.Period removePeriod(long periodId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _periodLocalService.removePeriod(periodId);
	}

	/**
	* Updates the period in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param period the period
	* @return the period that was updated
	*/
	@Override
	public eu.strasbourg.service.place.model.Period updatePeriod(
		eu.strasbourg.service.place.model.Period period) {
		return _periodLocalService.updatePeriod(period);
	}

	/**
	* Returns the number of periods.
	*
	* @return the number of periods
	*/
	@Override
	public int getPeriodsCount() {
		return _periodLocalService.getPeriodsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _periodLocalService.getOSGiServiceIdentifier();
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
		return _periodLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _periodLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _periodLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Retourne les Periods rattachées à un lieu
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.Period> getByPlaceId(
		long placeId) {
		return _periodLocalService.getByPlaceId(placeId);
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
	@Override
	public java.util.List<eu.strasbourg.service.place.model.Period> getPeriods(
		int start, int end) {
		return _periodLocalService.getPeriods(start, end);
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
		return _periodLocalService.dynamicQueryCount(dynamicQuery);
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
		return _periodLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public PeriodLocalService getWrappedService() {
		return _periodLocalService;
	}

	@Override
	public void setWrappedService(PeriodLocalService periodLocalService) {
		_periodLocalService = periodLocalService;
	}

	private PeriodLocalService _periodLocalService;
}