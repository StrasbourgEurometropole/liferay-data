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
 * Provides a wrapper for {@link ThematicLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ThematicLocalService
 * @generated
 */
public class ThematicLocalServiceWrapper
	implements ServiceWrapper<ThematicLocalService>, ThematicLocalService {

	public ThematicLocalServiceWrapper(
		ThematicLocalService thematicLocalService) {

		_thematicLocalService = thematicLocalService;
	}

	/**
	 * Adds the thematic to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ThematicLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param thematic the thematic
	 * @return the thematic that was added
	 */
	@Override
	public eu.strasbourg.service.csmap.model.Thematic addThematic(
		eu.strasbourg.service.csmap.model.Thematic thematic) {

		return _thematicLocalService.addThematic(thematic);
	}

	/**
	 * Creates a new thematic with the primary key. Does not add the thematic to the database.
	 *
	 * @param thematicId the primary key for the new thematic
	 * @return the new thematic
	 */
	@Override
	public eu.strasbourg.service.csmap.model.Thematic createThematic(
		long thematicId) {

		return _thematicLocalService.createThematic(thematicId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _thematicLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the thematic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ThematicLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param thematicId the primary key of the thematic
	 * @return the thematic that was removed
	 * @throws PortalException if a thematic with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.csmap.model.Thematic deleteThematic(
			long thematicId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _thematicLocalService.deleteThematic(thematicId);
	}

	/**
	 * Deletes the thematic from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ThematicLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param thematic the thematic
	 * @return the thematic that was removed
	 */
	@Override
	public eu.strasbourg.service.csmap.model.Thematic deleteThematic(
		eu.strasbourg.service.csmap.model.Thematic thematic) {

		return _thematicLocalService.deleteThematic(thematic);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _thematicLocalService.dynamicQuery();
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

		return _thematicLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.ThematicModelImpl</code>.
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

		return _thematicLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.ThematicModelImpl</code>.
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

		return _thematicLocalService.dynamicQuery(
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

		return _thematicLocalService.dynamicQueryCount(dynamicQuery);
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

		return _thematicLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.csmap.model.Thematic fetchThematic(
		long thematicId) {

		return _thematicLocalService.fetchThematic(thematicId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _thematicLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _thematicLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _thematicLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _thematicLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the thematic with the primary key.
	 *
	 * @param thematicId the primary key of the thematic
	 * @return the thematic
	 * @throws PortalException if a thematic with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.csmap.model.Thematic getThematic(
			long thematicId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _thematicLocalService.getThematic(thematicId);
	}

	/**
	 * Returns a range of all the thematics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.ThematicModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of thematics
	 * @param end the upper bound of the range of thematics (not inclusive)
	 * @return the range of thematics
	 */
	@Override
	public java.util.List<eu.strasbourg.service.csmap.model.Thematic>
		getThematics(int start, int end) {

		return _thematicLocalService.getThematics(start, end);
	}

	/**
	 * Returns the number of thematics.
	 *
	 * @return the number of thematics
	 */
	@Override
	public int getThematicsCount() {
		return _thematicLocalService.getThematicsCount();
	}

	/**
	 * Updates the thematic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ThematicLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param thematic the thematic
	 * @return the thematic that was updated
	 */
	@Override
	public eu.strasbourg.service.csmap.model.Thematic updateThematic(
		eu.strasbourg.service.csmap.model.Thematic thematic) {

		return _thematicLocalService.updateThematic(thematic);
	}

	@Override
	public ThematicLocalService getWrappedService() {
		return _thematicLocalService;
	}

	@Override
	public void setWrappedService(ThematicLocalService thematicLocalService) {
		_thematicLocalService = thematicLocalService;
	}

	private ThematicLocalService _thematicLocalService;

}