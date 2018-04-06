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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ObjectCategory. This utility wraps
 * {@link eu.strasbourg.service.objtp.service.impl.ObjectCategoryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author JeremyZwickert
 * @see ObjectCategoryLocalService
 * @see eu.strasbourg.service.objtp.service.base.ObjectCategoryLocalServiceBaseImpl
 * @see eu.strasbourg.service.objtp.service.impl.ObjectCategoryLocalServiceImpl
 * @generated
 */
@ProviderType
public class ObjectCategoryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.objtp.service.impl.ObjectCategoryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Lance l'import des catégories d'objet
	*
	* @throws MalformedURLException
	* @throws IOException
	* @throws JSONException
	*/
	public static boolean doImport()
		throws com.liferay.portal.kernel.json.JSONException, java.io.IOException {
		return getService().doImport();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
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
	* Adds the object category to the database. Also notifies the appropriate model listeners.
	*
	* @param objectCategory the object category
	* @return the object category that was added
	*/
	public static eu.strasbourg.service.objtp.model.ObjectCategory addObjectCategory(
		eu.strasbourg.service.objtp.model.ObjectCategory objectCategory) {
		return getService().addObjectCategory(objectCategory);
	}

	/**
	* Creates a new object category with the primary key. Does not add the object category to the database.
	*
	* @param code the primary key for the new object category
	* @return the new object category
	*/
	public static eu.strasbourg.service.objtp.model.ObjectCategory createObjectCategory(
		java.lang.String code) {
		return getService().createObjectCategory(code);
	}

	/**
	* Deletes the object category from the database. Also notifies the appropriate model listeners.
	*
	* @param objectCategory the object category
	* @return the object category that was removed
	*/
	public static eu.strasbourg.service.objtp.model.ObjectCategory deleteObjectCategory(
		eu.strasbourg.service.objtp.model.ObjectCategory objectCategory) {
		return getService().deleteObjectCategory(objectCategory);
	}

	/**
	* Deletes the object category with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param code the primary key of the object category
	* @return the object category that was removed
	* @throws PortalException if a object category with the primary key could not be found
	*/
	public static eu.strasbourg.service.objtp.model.ObjectCategory deleteObjectCategory(
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteObjectCategory(code);
	}

	public static eu.strasbourg.service.objtp.model.ObjectCategory fetchObjectCategory(
		java.lang.String code) {
		return getService().fetchObjectCategory(code);
	}

	/**
	* Returns the object category with the primary key.
	*
	* @param code the primary key of the object category
	* @return the object category
	* @throws PortalException if a object category with the primary key could not be found
	*/
	public static eu.strasbourg.service.objtp.model.ObjectCategory getObjectCategory(
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getObjectCategory(code);
	}

	/**
	* Updates the object category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param objectCategory the object category
	* @return the object category that was updated
	*/
	public static eu.strasbourg.service.objtp.model.ObjectCategory updateObjectCategory(
		eu.strasbourg.service.objtp.model.ObjectCategory objectCategory) {
		return getService().updateObjectCategory(objectCategory);
	}

	/**
	* Returns the number of object categories.
	*
	* @return the number of object categories
	*/
	public static int getObjectCategoriesCount() {
		return getService().getObjectCategoriesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.objtp.model.impl.ObjectCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.objtp.model.impl.ObjectCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static java.util.List<eu.strasbourg.service.objtp.model.ObjectCategory> getObjectCategories(
		int start, int end) {
		return getService().getObjectCategories(start, end);
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

	public static void importObjectCategory(
		com.liferay.portal.kernel.json.JSONObject objectCategoryJSON) {
		getService().importObjectCategory(objectCategoryJSON);
	}

	public static ObjectCategoryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ObjectCategoryLocalService, ObjectCategoryLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ObjectCategoryLocalService.class);
}