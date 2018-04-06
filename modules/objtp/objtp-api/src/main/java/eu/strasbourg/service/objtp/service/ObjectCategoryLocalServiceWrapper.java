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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ObjectCategoryLocalService}.
 *
 * @author JeremyZwickert
 * @see ObjectCategoryLocalService
 * @generated
 */
@ProviderType
public class ObjectCategoryLocalServiceWrapper
	implements ObjectCategoryLocalService,
		ServiceWrapper<ObjectCategoryLocalService> {
	public ObjectCategoryLocalServiceWrapper(
		ObjectCategoryLocalService objectCategoryLocalService) {
		_objectCategoryLocalService = objectCategoryLocalService;
	}

	/**
	* Lance l'import des catégories d'objet
	*
	* @throws MalformedURLException
	* @throws IOException
	* @throws JSONException
	*/
	@Override
	public boolean doImport()
		throws com.liferay.portal.kernel.json.JSONException, java.io.IOException {
		return _objectCategoryLocalService.doImport();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _objectCategoryLocalService.dynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _objectCategoryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _objectCategoryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the object category to the database. Also notifies the appropriate model listeners.
	*
	* @param objectCategory the object category
	* @return the object category that was added
	*/
	@Override
	public eu.strasbourg.service.objtp.model.ObjectCategory addObjectCategory(
		eu.strasbourg.service.objtp.model.ObjectCategory objectCategory) {
		return _objectCategoryLocalService.addObjectCategory(objectCategory);
	}

	/**
	* Creates a new object category with the primary key. Does not add the object category to the database.
	*
	* @param code the primary key for the new object category
	* @return the new object category
	*/
	@Override
	public eu.strasbourg.service.objtp.model.ObjectCategory createObjectCategory(
		java.lang.String code) {
		return _objectCategoryLocalService.createObjectCategory(code);
	}

	/**
	* Deletes the object category from the database. Also notifies the appropriate model listeners.
	*
	* @param objectCategory the object category
	* @return the object category that was removed
	*/
	@Override
	public eu.strasbourg.service.objtp.model.ObjectCategory deleteObjectCategory(
		eu.strasbourg.service.objtp.model.ObjectCategory objectCategory) {
		return _objectCategoryLocalService.deleteObjectCategory(objectCategory);
	}

	/**
	* Deletes the object category with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param code the primary key of the object category
	* @return the object category that was removed
	* @throws PortalException if a object category with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.objtp.model.ObjectCategory deleteObjectCategory(
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _objectCategoryLocalService.deleteObjectCategory(code);
	}

	@Override
	public eu.strasbourg.service.objtp.model.ObjectCategory fetchObjectCategory(
		java.lang.String code) {
		return _objectCategoryLocalService.fetchObjectCategory(code);
	}

	/**
	* Returns the object category with the primary key.
	*
	* @param code the primary key of the object category
	* @return the object category
	* @throws PortalException if a object category with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.objtp.model.ObjectCategory getObjectCategory(
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _objectCategoryLocalService.getObjectCategory(code);
	}

	/**
	* Updates the object category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param objectCategory the object category
	* @return the object category that was updated
	*/
	@Override
	public eu.strasbourg.service.objtp.model.ObjectCategory updateObjectCategory(
		eu.strasbourg.service.objtp.model.ObjectCategory objectCategory) {
		return _objectCategoryLocalService.updateObjectCategory(objectCategory);
	}

	/**
	* Returns the number of object categories.
	*
	* @return the number of object categories
	*/
	@Override
	public int getObjectCategoriesCount() {
		return _objectCategoryLocalService.getObjectCategoriesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _objectCategoryLocalService.getOSGiServiceIdentifier();
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
		return _objectCategoryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.objtp.model.impl.ObjectCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _objectCategoryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.objtp.model.impl.ObjectCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _objectCategoryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the object categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.objtp.model.impl.ObjectCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of object categories
	* @param end the upper bound of the range of object categories (not inclusive)
	* @return the range of object categories
	*/
	@Override
	public java.util.List<eu.strasbourg.service.objtp.model.ObjectCategory> getObjectCategories(
		int start, int end) {
		return _objectCategoryLocalService.getObjectCategories(start, end);
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
		return _objectCategoryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _objectCategoryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void importObjectCategory(
		com.liferay.portal.kernel.json.JSONObject objectCategoryJSON) {
		_objectCategoryLocalService.importObjectCategory(objectCategoryJSON);
	}

	@Override
	public ObjectCategoryLocalService getWrappedService() {
		return _objectCategoryLocalService;
	}

	@Override
	public void setWrappedService(
		ObjectCategoryLocalService objectCategoryLocalService) {
		_objectCategoryLocalService = objectCategoryLocalService;
	}

	private ObjectCategoryLocalService _objectCategoryLocalService;
}