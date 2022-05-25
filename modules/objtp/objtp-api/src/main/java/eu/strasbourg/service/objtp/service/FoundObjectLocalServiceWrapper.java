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

package eu.strasbourg.service.objtp.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FoundObjectLocalService}.
 *
 * @author JeremyZwickert
 * @see FoundObjectLocalService
 * @generated
 */
public class FoundObjectLocalServiceWrapper
	implements FoundObjectLocalService,
			   ServiceWrapper<FoundObjectLocalService> {

	public FoundObjectLocalServiceWrapper(
		FoundObjectLocalService foundObjectLocalService) {

		_foundObjectLocalService = foundObjectLocalService;
	}

	/**
	 * Adds the found object to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FoundObjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param foundObject the found object
	 * @return the found object that was added
	 */
	@Override
	public eu.strasbourg.service.objtp.model.FoundObject addFoundObject(
		eu.strasbourg.service.objtp.model.FoundObject foundObject) {

		return _foundObjectLocalService.addFoundObject(foundObject);
	}

	/**
	 * Creates a new found object with the primary key. Does not add the found object to the database.
	 *
	 * @param number the primary key for the new found object
	 * @return the new found object
	 */
	@Override
	public eu.strasbourg.service.objtp.model.FoundObject createFoundObject(
		String number) {

		return _foundObjectLocalService.createFoundObject(number);
	}

	/**
	 * Deletes the found object from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FoundObjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param foundObject the found object
	 * @return the found object that was removed
	 */
	@Override
	public eu.strasbourg.service.objtp.model.FoundObject deleteFoundObject(
		eu.strasbourg.service.objtp.model.FoundObject foundObject) {

		return _foundObjectLocalService.deleteFoundObject(foundObject);
	}

	/**
	 * Deletes the found object with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FoundObjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param number the primary key of the found object
	 * @return the found object that was removed
	 * @throws PortalException if a found object with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.objtp.model.FoundObject deleteFoundObject(
			String number)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _foundObjectLocalService.deleteFoundObject(number);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _foundObjectLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public void doImport()
		throws com.liferay.portal.kernel.exception.PortalException,
			   com.liferay.portal.kernel.json.JSONException,
			   java.io.IOException, java.text.ParseException {

		_foundObjectLocalService.doImport();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _foundObjectLocalService.dynamicQuery();
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

		return _foundObjectLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.objtp.model.impl.FoundObjectModelImpl</code>.
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

		return _foundObjectLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.objtp.model.impl.FoundObjectModelImpl</code>.
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

		return _foundObjectLocalService.dynamicQuery(
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

		return _foundObjectLocalService.dynamicQueryCount(dynamicQuery);
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

		return _foundObjectLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.objtp.model.FoundObject fetchFoundObject(
		String number) {

		return _foundObjectLocalService.fetchFoundObject(number);
	}

	/**
	 * Returns the found object with the primary key.
	 *
	 * @param number the primary key of the found object
	 * @return the found object
	 * @throws PortalException if a found object with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.objtp.model.FoundObject getFoundObject(
			String number)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _foundObjectLocalService.getFoundObject(number);
	}

	@Override
	public java.util.List<eu.strasbourg.service.objtp.model.FoundObject>
		getFoundObjectByCategoryCode(String codeCategory) {

		return _foundObjectLocalService.getFoundObjectByCategoryCode(
			codeCategory);
	}

	/**
	 * Returns a range of all the found objects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.objtp.model.impl.FoundObjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of found objects
	 * @param end the upper bound of the range of found objects (not inclusive)
	 * @return the range of found objects
	 */
	@Override
	public java.util.List<eu.strasbourg.service.objtp.model.FoundObject>
		getFoundObjects(int start, int end) {

		return _foundObjectLocalService.getFoundObjects(start, end);
	}

	/**
	 * Returns the number of found objects.
	 *
	 * @return the number of found objects
	 */
	@Override
	public int getFoundObjectsCount() {
		return _foundObjectLocalService.getFoundObjectsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _foundObjectLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _foundObjectLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the found object in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FoundObjectLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param foundObject the found object
	 * @return the found object that was updated
	 */
	@Override
	public eu.strasbourg.service.objtp.model.FoundObject updateFoundObject(
		eu.strasbourg.service.objtp.model.FoundObject foundObject) {

		return _foundObjectLocalService.updateFoundObject(foundObject);
	}

	@Override
	public FoundObjectLocalService getWrappedService() {
		return _foundObjectLocalService;
	}

	@Override
	public void setWrappedService(
		FoundObjectLocalService foundObjectLocalService) {

		_foundObjectLocalService = foundObjectLocalService;
	}

	private FoundObjectLocalService _foundObjectLocalService;

}