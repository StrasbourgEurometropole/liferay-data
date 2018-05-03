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

package eu.strasbourg.service.objtp.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.objtp.model.ObjectCategory;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the object category service. This utility wraps {@link eu.strasbourg.service.objtp.service.persistence.impl.ObjectCategoryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author JeremyZwickert
 * @see ObjectCategoryPersistence
 * @see eu.strasbourg.service.objtp.service.persistence.impl.ObjectCategoryPersistenceImpl
 * @generated
 */
@ProviderType
public class ObjectCategoryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ObjectCategory objectCategory) {
		getPersistence().clearCache(objectCategory);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ObjectCategory> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ObjectCategory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ObjectCategory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ObjectCategory> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ObjectCategory update(ObjectCategory objectCategory) {
		return getPersistence().update(objectCategory);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ObjectCategory update(ObjectCategory objectCategory,
		ServiceContext serviceContext) {
		return getPersistence().update(objectCategory, serviceContext);
	}

	/**
	* Caches the object category in the entity cache if it is enabled.
	*
	* @param objectCategory the object category
	*/
	public static void cacheResult(ObjectCategory objectCategory) {
		getPersistence().cacheResult(objectCategory);
	}

	/**
	* Caches the object categories in the entity cache if it is enabled.
	*
	* @param objectCategories the object categories
	*/
	public static void cacheResult(List<ObjectCategory> objectCategories) {
		getPersistence().cacheResult(objectCategories);
	}

	/**
	* Creates a new object category with the primary key. Does not add the object category to the database.
	*
	* @param code the primary key for the new object category
	* @return the new object category
	*/
	public static ObjectCategory create(java.lang.String code) {
		return getPersistence().create(code);
	}

	/**
	* Removes the object category with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param code the primary key of the object category
	* @return the object category that was removed
	* @throws NoSuchObjectCategoryException if a object category with the primary key could not be found
	*/
	public static ObjectCategory remove(java.lang.String code)
		throws eu.strasbourg.service.objtp.exception.NoSuchObjectCategoryException {
		return getPersistence().remove(code);
	}

	public static ObjectCategory updateImpl(ObjectCategory objectCategory) {
		return getPersistence().updateImpl(objectCategory);
	}

	/**
	* Returns the object category with the primary key or throws a {@link NoSuchObjectCategoryException} if it could not be found.
	*
	* @param code the primary key of the object category
	* @return the object category
	* @throws NoSuchObjectCategoryException if a object category with the primary key could not be found
	*/
	public static ObjectCategory findByPrimaryKey(java.lang.String code)
		throws eu.strasbourg.service.objtp.exception.NoSuchObjectCategoryException {
		return getPersistence().findByPrimaryKey(code);
	}

	/**
	* Returns the object category with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param code the primary key of the object category
	* @return the object category, or <code>null</code> if a object category with the primary key could not be found
	*/
	public static ObjectCategory fetchByPrimaryKey(java.lang.String code) {
		return getPersistence().fetchByPrimaryKey(code);
	}

	public static java.util.Map<java.io.Serializable, ObjectCategory> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the object categories.
	*
	* @return the object categories
	*/
	public static List<ObjectCategory> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the object categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ObjectCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of object categories
	* @param end the upper bound of the range of object categories (not inclusive)
	* @return the range of object categories
	*/
	public static List<ObjectCategory> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the object categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ObjectCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of object categories
	* @param end the upper bound of the range of object categories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of object categories
	*/
	public static List<ObjectCategory> findAll(int start, int end,
		OrderByComparator<ObjectCategory> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the object categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ObjectCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of object categories
	* @param end the upper bound of the range of object categories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of object categories
	*/
	public static List<ObjectCategory> findAll(int start, int end,
		OrderByComparator<ObjectCategory> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the object categories from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of object categories.
	*
	* @return the number of object categories
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ObjectCategoryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ObjectCategoryPersistence, ObjectCategoryPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ObjectCategoryPersistence.class);
}