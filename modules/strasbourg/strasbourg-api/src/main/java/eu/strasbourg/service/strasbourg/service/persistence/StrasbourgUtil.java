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

package eu.strasbourg.service.strasbourg.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.strasbourg.model.Strasbourg;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the strasbourg service. This utility wraps {@link eu.strasbourg.service.strasbourg.service.persistence.impl.StrasbourgPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StrasbourgPersistence
 * @see eu.strasbourg.service.strasbourg.service.persistence.impl.StrasbourgPersistenceImpl
 * @generated
 */
@ProviderType
public class StrasbourgUtil {
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
	public static void clearCache(Strasbourg strasbourg) {
		getPersistence().clearCache(strasbourg);
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
	public static List<Strasbourg> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Strasbourg> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Strasbourg> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Strasbourg> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Strasbourg update(Strasbourg strasbourg) {
		return getPersistence().update(strasbourg);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Strasbourg update(Strasbourg strasbourg,
		ServiceContext serviceContext) {
		return getPersistence().update(strasbourg, serviceContext);
	}

	/**
	* Returns all the strasbourgs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching strasbourgs
	*/
	public static List<Strasbourg> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the strasbourgs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrasbourgModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of strasbourgs
	* @param end the upper bound of the range of strasbourgs (not inclusive)
	* @return the range of matching strasbourgs
	*/
	public static List<Strasbourg> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the strasbourgs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrasbourgModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of strasbourgs
	* @param end the upper bound of the range of strasbourgs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching strasbourgs
	*/
	public static List<Strasbourg> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Strasbourg> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the strasbourgs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrasbourgModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of strasbourgs
	* @param end the upper bound of the range of strasbourgs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching strasbourgs
	*/
	public static List<Strasbourg> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Strasbourg> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first strasbourg in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strasbourg
	* @throws NoSuchStrasbourgException if a matching strasbourg could not be found
	*/
	public static Strasbourg findByUuid_First(java.lang.String uuid,
		OrderByComparator<Strasbourg> orderByComparator)
		throws eu.strasbourg.service.strasbourg.exception.NoSuchStrasbourgException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first strasbourg in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching strasbourg, or <code>null</code> if a matching strasbourg could not be found
	*/
	public static Strasbourg fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Strasbourg> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last strasbourg in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strasbourg
	* @throws NoSuchStrasbourgException if a matching strasbourg could not be found
	*/
	public static Strasbourg findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Strasbourg> orderByComparator)
		throws eu.strasbourg.service.strasbourg.exception.NoSuchStrasbourgException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last strasbourg in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching strasbourg, or <code>null</code> if a matching strasbourg could not be found
	*/
	public static Strasbourg fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Strasbourg> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the strasbourgs before and after the current strasbourg in the ordered set where uuid = &#63;.
	*
	* @param id the primary key of the current strasbourg
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next strasbourg
	* @throws NoSuchStrasbourgException if a strasbourg with the primary key could not be found
	*/
	public static Strasbourg[] findByUuid_PrevAndNext(long id,
		java.lang.String uuid, OrderByComparator<Strasbourg> orderByComparator)
		throws eu.strasbourg.service.strasbourg.exception.NoSuchStrasbourgException {
		return getPersistence()
				   .findByUuid_PrevAndNext(id, uuid, orderByComparator);
	}

	/**
	* Removes all the strasbourgs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of strasbourgs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching strasbourgs
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Caches the strasbourg in the entity cache if it is enabled.
	*
	* @param strasbourg the strasbourg
	*/
	public static void cacheResult(Strasbourg strasbourg) {
		getPersistence().cacheResult(strasbourg);
	}

	/**
	* Caches the strasbourgs in the entity cache if it is enabled.
	*
	* @param strasbourgs the strasbourgs
	*/
	public static void cacheResult(List<Strasbourg> strasbourgs) {
		getPersistence().cacheResult(strasbourgs);
	}

	/**
	* Creates a new strasbourg with the primary key. Does not add the strasbourg to the database.
	*
	* @param id the primary key for the new strasbourg
	* @return the new strasbourg
	*/
	public static Strasbourg create(long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the strasbourg with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the strasbourg
	* @return the strasbourg that was removed
	* @throws NoSuchStrasbourgException if a strasbourg with the primary key could not be found
	*/
	public static Strasbourg remove(long id)
		throws eu.strasbourg.service.strasbourg.exception.NoSuchStrasbourgException {
		return getPersistence().remove(id);
	}

	public static Strasbourg updateImpl(Strasbourg strasbourg) {
		return getPersistence().updateImpl(strasbourg);
	}

	/**
	* Returns the strasbourg with the primary key or throws a {@link NoSuchStrasbourgException} if it could not be found.
	*
	* @param id the primary key of the strasbourg
	* @return the strasbourg
	* @throws NoSuchStrasbourgException if a strasbourg with the primary key could not be found
	*/
	public static Strasbourg findByPrimaryKey(long id)
		throws eu.strasbourg.service.strasbourg.exception.NoSuchStrasbourgException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the strasbourg with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the strasbourg
	* @return the strasbourg, or <code>null</code> if a strasbourg with the primary key could not be found
	*/
	public static Strasbourg fetchByPrimaryKey(long id) {
		return getPersistence().fetchByPrimaryKey(id);
	}

	public static java.util.Map<java.io.Serializable, Strasbourg> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the strasbourgs.
	*
	* @return the strasbourgs
	*/
	public static List<Strasbourg> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the strasbourgs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrasbourgModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of strasbourgs
	* @param end the upper bound of the range of strasbourgs (not inclusive)
	* @return the range of strasbourgs
	*/
	public static List<Strasbourg> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the strasbourgs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrasbourgModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of strasbourgs
	* @param end the upper bound of the range of strasbourgs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of strasbourgs
	*/
	public static List<Strasbourg> findAll(int start, int end,
		OrderByComparator<Strasbourg> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the strasbourgs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StrasbourgModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of strasbourgs
	* @param end the upper bound of the range of strasbourgs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of strasbourgs
	*/
	public static List<Strasbourg> findAll(int start, int end,
		OrderByComparator<Strasbourg> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the strasbourgs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of strasbourgs.
	*
	* @return the number of strasbourgs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static StrasbourgPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StrasbourgPersistence, StrasbourgPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StrasbourgPersistence.class);
}