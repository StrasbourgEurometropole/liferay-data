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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StrasbourgLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see StrasbourgLocalService
 * @generated
 */
@ProviderType
public class StrasbourgLocalServiceWrapper implements StrasbourgLocalService,
	ServiceWrapper<StrasbourgLocalService> {
	public StrasbourgLocalServiceWrapper(
		StrasbourgLocalService strasbourgLocalService) {
		_strasbourgLocalService = strasbourgLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _strasbourgLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _strasbourgLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _strasbourgLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _strasbourgLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _strasbourgLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the strasbourg to the database. Also notifies the appropriate model listeners.
	*
	* @param strasbourg the strasbourg
	* @return the strasbourg that was added
	*/
	@Override
	public eu.strasbourg.service.strasbourg.model.Strasbourg addStrasbourg(
		eu.strasbourg.service.strasbourg.model.Strasbourg strasbourg) {
		return _strasbourgLocalService.addStrasbourg(strasbourg);
	}

	/**
	* Creates a new strasbourg with the primary key. Does not add the strasbourg to the database.
	*
	* @param id the primary key for the new strasbourg
	* @return the new strasbourg
	*/
	@Override
	public eu.strasbourg.service.strasbourg.model.Strasbourg createStrasbourg(
		long id) {
		return _strasbourgLocalService.createStrasbourg(id);
	}

	/**
	* Deletes the strasbourg from the database. Also notifies the appropriate model listeners.
	*
	* @param strasbourg the strasbourg
	* @return the strasbourg that was removed
	*/
	@Override
	public eu.strasbourg.service.strasbourg.model.Strasbourg deleteStrasbourg(
		eu.strasbourg.service.strasbourg.model.Strasbourg strasbourg) {
		return _strasbourgLocalService.deleteStrasbourg(strasbourg);
	}

	/**
	* Deletes the strasbourg with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the strasbourg
	* @return the strasbourg that was removed
	* @throws PortalException if a strasbourg with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.strasbourg.model.Strasbourg deleteStrasbourg(
		long id) throws com.liferay.portal.kernel.exception.PortalException {
		return _strasbourgLocalService.deleteStrasbourg(id);
	}

	@Override
	public eu.strasbourg.service.strasbourg.model.Strasbourg fetchStrasbourg(
		long id) {
		return _strasbourgLocalService.fetchStrasbourg(id);
	}

	/**
	* Returns the strasbourg with the primary key.
	*
	* @param id the primary key of the strasbourg
	* @return the strasbourg
	* @throws PortalException if a strasbourg with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.strasbourg.model.Strasbourg getStrasbourg(
		long id) throws com.liferay.portal.kernel.exception.PortalException {
		return _strasbourgLocalService.getStrasbourg(id);
	}

	/**
	* Updates the strasbourg in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param strasbourg the strasbourg
	* @return the strasbourg that was updated
	*/
	@Override
	public eu.strasbourg.service.strasbourg.model.Strasbourg updateStrasbourg(
		eu.strasbourg.service.strasbourg.model.Strasbourg strasbourg) {
		return _strasbourgLocalService.updateStrasbourg(strasbourg);
	}

	/**
	* Returns the number of strasbourgs.
	*
	* @return the number of strasbourgs
	*/
	@Override
	public int getStrasbourgsCount() {
		return _strasbourgLocalService.getStrasbourgsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _strasbourgLocalService.getOSGiServiceIdentifier();
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
		return _strasbourgLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.strasbourg.model.impl.StrasbourgModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _strasbourgLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.strasbourg.model.impl.StrasbourgModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _strasbourgLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the strasbourgs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.strasbourg.model.impl.StrasbourgModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of strasbourgs
	* @param end the upper bound of the range of strasbourgs (not inclusive)
	* @return the range of strasbourgs
	*/
	@Override
	public java.util.List<eu.strasbourg.service.strasbourg.model.Strasbourg> getStrasbourgs(
		int start, int end) {
		return _strasbourgLocalService.getStrasbourgs(start, end);
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
		return _strasbourgLocalService.dynamicQueryCount(dynamicQuery);
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
		return _strasbourgLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public StrasbourgLocalService getWrappedService() {
		return _strasbourgLocalService;
	}

	@Override
	public void setWrappedService(StrasbourgLocalService strasbourgLocalService) {
		_strasbourgLocalService = strasbourgLocalService;
	}

	private StrasbourgLocalService _strasbourgLocalService;
}