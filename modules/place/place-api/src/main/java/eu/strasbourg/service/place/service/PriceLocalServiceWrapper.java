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
 * Provides a wrapper for {@link PriceLocalService}.
 *
 * @author Angelique Zunino Champougny
 * @see PriceLocalService
 * @generated
 */
@ProviderType
public class PriceLocalServiceWrapper implements PriceLocalService,
	ServiceWrapper<PriceLocalService> {
	public PriceLocalServiceWrapper(PriceLocalService priceLocalService) {
		_priceLocalService = priceLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _priceLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _priceLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _priceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _priceLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _priceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the price to the database. Also notifies the appropriate model listeners.
	*
	* @param price the price
	* @return the price that was added
	*/
	@Override
	public eu.strasbourg.service.place.model.Price addPrice(
		eu.strasbourg.service.place.model.Price price) {
		return _priceLocalService.addPrice(price);
	}

	/**
	* Crée un prix vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.place.model.Price createPrice(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _priceLocalService.createPrice(sc);
	}

	/**
	* Creates a new price with the primary key. Does not add the price to the database.
	*
	* @param priceId the primary key for the new price
	* @return the new price
	*/
	@Override
	public eu.strasbourg.service.place.model.Price createPrice(long priceId) {
		return _priceLocalService.createPrice(priceId);
	}

	/**
	* Deletes the price from the database. Also notifies the appropriate model listeners.
	*
	* @param price the price
	* @return the price that was removed
	*/
	@Override
	public eu.strasbourg.service.place.model.Price deletePrice(
		eu.strasbourg.service.place.model.Price price) {
		return _priceLocalService.deletePrice(price);
	}

	/**
	* Deletes the price with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param priceId the primary key of the price
	* @return the price that was removed
	* @throws PortalException if a price with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.place.model.Price deletePrice(long priceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _priceLocalService.deletePrice(priceId);
	}

	@Override
	public eu.strasbourg.service.place.model.Price fetchPrice(long priceId) {
		return _priceLocalService.fetchPrice(priceId);
	}

	/**
	* Returns the price with the primary key.
	*
	* @param priceId the primary key of the price
	* @return the price
	* @throws PortalException if a price with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.place.model.Price getPrice(long priceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _priceLocalService.getPrice(priceId);
	}

	/**
	* Updates the price in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param price the price
	* @return the price that was updated
	*/
	@Override
	public eu.strasbourg.service.place.model.Price updatePrice(
		eu.strasbourg.service.place.model.Price price) {
		return _priceLocalService.updatePrice(price);
	}

	/**
	* Returns the number of prices.
	*
	* @return the number of prices
	*/
	@Override
	public int getPricesCount() {
		return _priceLocalService.getPricesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _priceLocalService.getOSGiServiceIdentifier();
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
		return _priceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.PriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _priceLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.PriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _priceLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the prices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.PriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of prices
	* @param end the upper bound of the range of prices (not inclusive)
	* @return the range of prices
	*/
	@Override
	public java.util.List<eu.strasbourg.service.place.model.Price> getPrices(
		int start, int end) {
		return _priceLocalService.getPrices(start, end);
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
		return _priceLocalService.dynamicQueryCount(dynamicQuery);
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
		return _priceLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public PriceLocalService getWrappedService() {
		return _priceLocalService;
	}

	@Override
	public void setWrappedService(PriceLocalService priceLocalService) {
		_priceLocalService = priceLocalService;
	}

	private PriceLocalService _priceLocalService;
}