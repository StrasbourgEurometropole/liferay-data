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

/**
 * Provides a wrapper for {@link PlaceCategoriesLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlaceCategoriesLocalService
 * @generated
 */
public class PlaceCategoriesLocalServiceWrapper
	implements PlaceCategoriesLocalService,
			   ServiceWrapper<PlaceCategoriesLocalService> {

	public PlaceCategoriesLocalServiceWrapper(
		PlaceCategoriesLocalService placeCategoriesLocalService) {

		_placeCategoriesLocalService = placeCategoriesLocalService;
	}

	/**
	 * Adds the place categories to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PlaceCategoriesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param placeCategories the place categories
	 * @return the place categories that was added
	 */
	@Override
	public eu.strasbourg.service.csmap.model.PlaceCategories addPlaceCategories(
		eu.strasbourg.service.csmap.model.PlaceCategories placeCategories) {

		return _placeCategoriesLocalService.addPlaceCategories(placeCategories);
	}

	@Override
	public eu.strasbourg.service.csmap.model.PlaceCategories
		createPlaceCategories() {

		return _placeCategoriesLocalService.createPlaceCategories();
	}

	/**
	 * Creates a new place categories with the primary key. Does not add the place categories to the database.
	 *
	 * @param placeCategoriesId the primary key for the new place categories
	 * @return the new place categories
	 */
	@Override
	public eu.strasbourg.service.csmap.model.PlaceCategories
		createPlaceCategories(long placeCategoriesId) {

		return _placeCategoriesLocalService.createPlaceCategories(
			placeCategoriesId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _placeCategoriesLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the place categories with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PlaceCategoriesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param placeCategoriesId the primary key of the place categories
	 * @return the place categories that was removed
	 * @throws PortalException if a place categories with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.csmap.model.PlaceCategories
			deletePlaceCategories(long placeCategoriesId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _placeCategoriesLocalService.deletePlaceCategories(
			placeCategoriesId);
	}

	/**
	 * Deletes the place categories from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PlaceCategoriesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param placeCategories the place categories
	 * @return the place categories that was removed
	 */
	@Override
	public eu.strasbourg.service.csmap.model.PlaceCategories
		deletePlaceCategories(
			eu.strasbourg.service.csmap.model.PlaceCategories placeCategories) {

		return _placeCategoriesLocalService.deletePlaceCategories(
			placeCategories);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _placeCategoriesLocalService.dynamicQuery();
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

		return _placeCategoriesLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.PlaceCategoriesModelImpl</code>.
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

		return _placeCategoriesLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.PlaceCategoriesModelImpl</code>.
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

		return _placeCategoriesLocalService.dynamicQuery(
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

		return _placeCategoriesLocalService.dynamicQueryCount(dynamicQuery);
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

		return _placeCategoriesLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.csmap.model.PlaceCategories
		fetchPlaceCategories(long placeCategoriesId) {

		return _placeCategoriesLocalService.fetchPlaceCategories(
			placeCategoriesId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _placeCategoriesLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _placeCategoriesLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _placeCategoriesLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _placeCategoriesLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public eu.strasbourg.service.csmap.model.PlaceCategories
		getPlaceCategories() {

		return _placeCategoriesLocalService.getPlaceCategories();
	}

	/**
	 * Returns the place categories with the primary key.
	 *
	 * @param placeCategoriesId the primary key of the place categories
	 * @return the place categories
	 * @throws PortalException if a place categories with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.csmap.model.PlaceCategories getPlaceCategories(
			long placeCategoriesId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _placeCategoriesLocalService.getPlaceCategories(
			placeCategoriesId);
	}

	/**
	 * Returns a range of all the place categorieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.PlaceCategoriesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of place categorieses
	 * @param end the upper bound of the range of place categorieses (not inclusive)
	 * @return the range of place categorieses
	 */
	@Override
	public java.util.List<eu.strasbourg.service.csmap.model.PlaceCategories>
		getPlaceCategorieses(int start, int end) {

		return _placeCategoriesLocalService.getPlaceCategorieses(start, end);
	}

	/**
	 * Returns the number of place categorieses.
	 *
	 * @return the number of place categorieses
	 */
	@Override
	public int getPlaceCategoriesesCount() {
		return _placeCategoriesLocalService.getPlaceCategoriesesCount();
	}

	/**
	 * Updates the place categories in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect PlaceCategoriesLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param placeCategories the place categories
	 * @return the place categories that was updated
	 */
	@Override
	public eu.strasbourg.service.csmap.model.PlaceCategories
		updatePlaceCategories(
			eu.strasbourg.service.csmap.model.PlaceCategories placeCategories) {

		return _placeCategoriesLocalService.updatePlaceCategories(
			placeCategories);
	}

	@Override
	public PlaceCategoriesLocalService getWrappedService() {
		return _placeCategoriesLocalService;
	}

	@Override
	public void setWrappedService(
		PlaceCategoriesLocalService placeCategoriesLocalService) {

		_placeCategoriesLocalService = placeCategoriesLocalService;
	}

	private PlaceCategoriesLocalService _placeCategoriesLocalService;

}