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
 * Provides a wrapper for {@link HistoricLocalService}.
 *
 * @author Angelique Zunino Champougny
 * @see HistoricLocalService
 * @generated
 */
@ProviderType
public class HistoricLocalServiceWrapper
	implements HistoricLocalService, ServiceWrapper<HistoricLocalService> {

	public HistoricLocalServiceWrapper(
		HistoricLocalService historicLocalService) {

		_historicLocalService = historicLocalService;
	}

	/**
	 * Adds the historic to the database. Also notifies the appropriate model listeners.
	 *
	 * @param historic the historic
	 * @return the historic that was added
	 */
	@Override
	public eu.strasbourg.service.place.model.Historic addHistoric(
		eu.strasbourg.service.place.model.Historic historic) {

		return _historicLocalService.addHistoric(historic);
	}

	/**
	 * Creates a new historic with the primary key. Does not add the historic to the database.
	 *
	 * @param sigId the primary key for the new historic
	 * @return the new historic
	 */
	@Override
	public eu.strasbourg.service.place.model.Historic createHistoric(
		String sigId) {

		return _historicLocalService.createHistoric(sigId);
	}

	/**
	 * Deletes the historic from the database. Also notifies the appropriate model listeners.
	 *
	 * @param historic the historic
	 * @return the historic that was removed
	 */
	@Override
	public eu.strasbourg.service.place.model.Historic deleteHistoric(
		eu.strasbourg.service.place.model.Historic historic) {

		return _historicLocalService.deleteHistoric(historic);
	}

	/**
	 * Deletes the historic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param sigId the primary key of the historic
	 * @return the historic that was removed
	 * @throws PortalException if a historic with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.place.model.Historic deleteHistoric(
			String sigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _historicLocalService.deleteHistoric(sigId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _historicLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _historicLocalService.dynamicQuery();
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

		return _historicLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.HistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _historicLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.HistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _historicLocalService.dynamicQuery(
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

		return _historicLocalService.dynamicQueryCount(dynamicQuery);
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

		return _historicLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.place.model.Historic fetchHistoric(
		String sigId) {

		return _historicLocalService.fetchHistoric(sigId);
	}

	/**
	 * Retourne les caches d'un lieu créé après une date et actif
	 */
	@Override
	public java.util.List<eu.strasbourg.service.place.model.Historic>
		getBySuppressionDate(java.util.Date date) {

		return _historicLocalService.getBySuppressionDate(date);
	}

	/**
	 * Returns the historic with the primary key.
	 *
	 * @param sigId the primary key of the historic
	 * @return the historic
	 * @throws PortalException if a historic with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.place.model.Historic getHistoric(String sigId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _historicLocalService.getHistoric(sigId);
	}

	/**
	 * Returns a range of all the historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.place.model.impl.HistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of historics
	 * @param end the upper bound of the range of historics (not inclusive)
	 * @return the range of historics
	 */
	@Override
	public java.util.List<eu.strasbourg.service.place.model.Historic>
		getHistorics(int start, int end) {

		return _historicLocalService.getHistorics(start, end);
	}

	/**
	 * Returns the number of historics.
	 *
	 * @return the number of historics
	 */
	@Override
	public int getHistoricsCount() {
		return _historicLocalService.getHistoricsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _historicLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _historicLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the historic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param historic the historic
	 * @return the historic that was updated
	 */
	@Override
	public eu.strasbourg.service.place.model.Historic updateHistoric(
		eu.strasbourg.service.place.model.Historic historic) {

		return _historicLocalService.updateHistoric(historic);
	}

	@Override
	public HistoricLocalService getWrappedService() {
		return _historicLocalService;
	}

	@Override
	public void setWrappedService(HistoricLocalService historicLocalService) {
		_historicLocalService = historicLocalService;
	}

	private HistoricLocalService _historicLocalService;

}