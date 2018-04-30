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

import eu.strasbourg.service.objtp.model.FoundObject;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the found object service. This utility wraps {@link eu.strasbourg.service.objtp.service.persistence.impl.FoundObjectPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author JeremyZwickert
 * @see FoundObjectPersistence
 * @see eu.strasbourg.service.objtp.service.persistence.impl.FoundObjectPersistenceImpl
 * @generated
 */
@ProviderType
public class FoundObjectUtil {
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
	public static void clearCache(FoundObject foundObject) {
		getPersistence().clearCache(foundObject);
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
	public static List<FoundObject> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FoundObject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FoundObject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FoundObject> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FoundObject update(FoundObject foundObject) {
		return getPersistence().update(foundObject);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FoundObject update(FoundObject foundObject,
		ServiceContext serviceContext) {
		return getPersistence().update(foundObject, serviceContext);
	}

	/**
	* Returns all the found objects where categoryCode = &#63;.
	*
	* @param categoryCode the category code
	* @return the matching found objects
	*/
	public static List<FoundObject> findByCategoryCode(
		java.lang.String categoryCode) {
		return getPersistence().findByCategoryCode(categoryCode);
	}

	/**
	* Returns a range of all the found objects where categoryCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FoundObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param categoryCode the category code
	* @param start the lower bound of the range of found objects
	* @param end the upper bound of the range of found objects (not inclusive)
	* @return the range of matching found objects
	*/
	public static List<FoundObject> findByCategoryCode(
		java.lang.String categoryCode, int start, int end) {
		return getPersistence().findByCategoryCode(categoryCode, start, end);
	}

	/**
	* Returns an ordered range of all the found objects where categoryCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FoundObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param categoryCode the category code
	* @param start the lower bound of the range of found objects
	* @param end the upper bound of the range of found objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching found objects
	*/
	public static List<FoundObject> findByCategoryCode(
		java.lang.String categoryCode, int start, int end,
		OrderByComparator<FoundObject> orderByComparator) {
		return getPersistence()
				   .findByCategoryCode(categoryCode, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the found objects where categoryCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FoundObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param categoryCode the category code
	* @param start the lower bound of the range of found objects
	* @param end the upper bound of the range of found objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching found objects
	*/
	public static List<FoundObject> findByCategoryCode(
		java.lang.String categoryCode, int start, int end,
		OrderByComparator<FoundObject> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCategoryCode(categoryCode, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first found object in the ordered set where categoryCode = &#63;.
	*
	* @param categoryCode the category code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching found object
	* @throws NoSuchFoundObjectException if a matching found object could not be found
	*/
	public static FoundObject findByCategoryCode_First(
		java.lang.String categoryCode,
		OrderByComparator<FoundObject> orderByComparator)
		throws eu.strasbourg.service.objtp.exception.NoSuchFoundObjectException {
		return getPersistence()
				   .findByCategoryCode_First(categoryCode, orderByComparator);
	}

	/**
	* Returns the first found object in the ordered set where categoryCode = &#63;.
	*
	* @param categoryCode the category code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching found object, or <code>null</code> if a matching found object could not be found
	*/
	public static FoundObject fetchByCategoryCode_First(
		java.lang.String categoryCode,
		OrderByComparator<FoundObject> orderByComparator) {
		return getPersistence()
				   .fetchByCategoryCode_First(categoryCode, orderByComparator);
	}

	/**
	* Returns the last found object in the ordered set where categoryCode = &#63;.
	*
	* @param categoryCode the category code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching found object
	* @throws NoSuchFoundObjectException if a matching found object could not be found
	*/
	public static FoundObject findByCategoryCode_Last(
		java.lang.String categoryCode,
		OrderByComparator<FoundObject> orderByComparator)
		throws eu.strasbourg.service.objtp.exception.NoSuchFoundObjectException {
		return getPersistence()
				   .findByCategoryCode_Last(categoryCode, orderByComparator);
	}

	/**
	* Returns the last found object in the ordered set where categoryCode = &#63;.
	*
	* @param categoryCode the category code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching found object, or <code>null</code> if a matching found object could not be found
	*/
	public static FoundObject fetchByCategoryCode_Last(
		java.lang.String categoryCode,
		OrderByComparator<FoundObject> orderByComparator) {
		return getPersistence()
				   .fetchByCategoryCode_Last(categoryCode, orderByComparator);
	}

	/**
	* Returns the found objects before and after the current found object in the ordered set where categoryCode = &#63;.
	*
	* @param number the primary key of the current found object
	* @param categoryCode the category code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next found object
	* @throws NoSuchFoundObjectException if a found object with the primary key could not be found
	*/
	public static FoundObject[] findByCategoryCode_PrevAndNext(
		java.lang.String number, java.lang.String categoryCode,
		OrderByComparator<FoundObject> orderByComparator)
		throws eu.strasbourg.service.objtp.exception.NoSuchFoundObjectException {
		return getPersistence()
				   .findByCategoryCode_PrevAndNext(number, categoryCode,
			orderByComparator);
	}

	/**
	* Removes all the found objects where categoryCode = &#63; from the database.
	*
	* @param categoryCode the category code
	*/
	public static void removeByCategoryCode(java.lang.String categoryCode) {
		getPersistence().removeByCategoryCode(categoryCode);
	}

	/**
	* Returns the number of found objects where categoryCode = &#63;.
	*
	* @param categoryCode the category code
	* @return the number of matching found objects
	*/
	public static int countByCategoryCode(java.lang.String categoryCode) {
		return getPersistence().countByCategoryCode(categoryCode);
	}

	/**
	* Caches the found object in the entity cache if it is enabled.
	*
	* @param foundObject the found object
	*/
	public static void cacheResult(FoundObject foundObject) {
		getPersistence().cacheResult(foundObject);
	}

	/**
	* Caches the found objects in the entity cache if it is enabled.
	*
	* @param foundObjects the found objects
	*/
	public static void cacheResult(List<FoundObject> foundObjects) {
		getPersistence().cacheResult(foundObjects);
	}

	/**
	* Creates a new found object with the primary key. Does not add the found object to the database.
	*
	* @param number the primary key for the new found object
	* @return the new found object
	*/
	public static FoundObject create(java.lang.String number) {
		return getPersistence().create(number);
	}

	/**
	* Removes the found object with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param number the primary key of the found object
	* @return the found object that was removed
	* @throws NoSuchFoundObjectException if a found object with the primary key could not be found
	*/
	public static FoundObject remove(java.lang.String number)
		throws eu.strasbourg.service.objtp.exception.NoSuchFoundObjectException {
		return getPersistence().remove(number);
	}

	public static FoundObject updateImpl(FoundObject foundObject) {
		return getPersistence().updateImpl(foundObject);
	}

	/**
	* Returns the found object with the primary key or throws a {@link NoSuchFoundObjectException} if it could not be found.
	*
	* @param number the primary key of the found object
	* @return the found object
	* @throws NoSuchFoundObjectException if a found object with the primary key could not be found
	*/
	public static FoundObject findByPrimaryKey(java.lang.String number)
		throws eu.strasbourg.service.objtp.exception.NoSuchFoundObjectException {
		return getPersistence().findByPrimaryKey(number);
	}

	/**
	* Returns the found object with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param number the primary key of the found object
	* @return the found object, or <code>null</code> if a found object with the primary key could not be found
	*/
	public static FoundObject fetchByPrimaryKey(java.lang.String number) {
		return getPersistence().fetchByPrimaryKey(number);
	}

	public static java.util.Map<java.io.Serializable, FoundObject> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the found objects.
	*
	* @return the found objects
	*/
	public static List<FoundObject> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the found objects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FoundObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of found objects
	* @param end the upper bound of the range of found objects (not inclusive)
	* @return the range of found objects
	*/
	public static List<FoundObject> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the found objects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FoundObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of found objects
	* @param end the upper bound of the range of found objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of found objects
	*/
	public static List<FoundObject> findAll(int start, int end,
		OrderByComparator<FoundObject> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the found objects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FoundObjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of found objects
	* @param end the upper bound of the range of found objects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of found objects
	*/
	public static List<FoundObject> findAll(int start, int end,
		OrderByComparator<FoundObject> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the found objects from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of found objects.
	*
	* @return the number of found objects
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static FoundObjectPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FoundObjectPersistence, FoundObjectPersistence> _serviceTracker =
		ServiceTrackerFactory.open(FoundObjectPersistence.class);
}