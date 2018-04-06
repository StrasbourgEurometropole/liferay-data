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

package eu.strasbourg.service.place.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.place.model.Period;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the period service. This utility wraps {@link eu.strasbourg.service.place.service.persistence.impl.PeriodPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see PeriodPersistence
 * @see eu.strasbourg.service.place.service.persistence.impl.PeriodPersistenceImpl
 * @generated
 */
@ProviderType
public class PeriodUtil {
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
	public static void clearCache(Period period) {
		getPersistence().clearCache(period);
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
	public static List<Period> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Period> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Period> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Period> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Period update(Period period) {
		return getPersistence().update(period);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Period update(Period period, ServiceContext serviceContext) {
		return getPersistence().update(period, serviceContext);
	}

	/**
	* Returns all the periods where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching periods
	*/
	public static List<Period> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the periods where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of periods
	* @param end the upper bound of the range of periods (not inclusive)
	* @return the range of matching periods
	*/
	public static List<Period> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the periods where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of periods
	* @param end the upper bound of the range of periods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching periods
	*/
	public static List<Period> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Period> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the periods where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of periods
	* @param end the upper bound of the range of periods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching periods
	*/
	public static List<Period> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Period> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first period in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching period
	* @throws NoSuchPeriodException if a matching period could not be found
	*/
	public static Period findByUuid_First(java.lang.String uuid,
		OrderByComparator<Period> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPeriodException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first period in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching period, or <code>null</code> if a matching period could not be found
	*/
	public static Period fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Period> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last period in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching period
	* @throws NoSuchPeriodException if a matching period could not be found
	*/
	public static Period findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Period> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPeriodException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last period in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching period, or <code>null</code> if a matching period could not be found
	*/
	public static Period fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Period> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the periods before and after the current period in the ordered set where uuid = &#63;.
	*
	* @param periodId the primary key of the current period
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next period
	* @throws NoSuchPeriodException if a period with the primary key could not be found
	*/
	public static Period[] findByUuid_PrevAndNext(long periodId,
		java.lang.String uuid, OrderByComparator<Period> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPeriodException {
		return getPersistence()
				   .findByUuid_PrevAndNext(periodId, uuid, orderByComparator);
	}

	/**
	* Removes all the periods where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of periods where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching periods
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the periods where placeId = &#63;.
	*
	* @param placeId the place ID
	* @return the matching periods
	*/
	public static List<Period> findByPlaceId(long placeId) {
		return getPersistence().findByPlaceId(placeId);
	}

	/**
	* Returns a range of all the periods where placeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeId the place ID
	* @param start the lower bound of the range of periods
	* @param end the upper bound of the range of periods (not inclusive)
	* @return the range of matching periods
	*/
	public static List<Period> findByPlaceId(long placeId, int start, int end) {
		return getPersistence().findByPlaceId(placeId, start, end);
	}

	/**
	* Returns an ordered range of all the periods where placeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeId the place ID
	* @param start the lower bound of the range of periods
	* @param end the upper bound of the range of periods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching periods
	*/
	public static List<Period> findByPlaceId(long placeId, int start, int end,
		OrderByComparator<Period> orderByComparator) {
		return getPersistence()
				   .findByPlaceId(placeId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the periods where placeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param placeId the place ID
	* @param start the lower bound of the range of periods
	* @param end the upper bound of the range of periods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching periods
	*/
	public static List<Period> findByPlaceId(long placeId, int start, int end,
		OrderByComparator<Period> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByPlaceId(placeId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first period in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching period
	* @throws NoSuchPeriodException if a matching period could not be found
	*/
	public static Period findByPlaceId_First(long placeId,
		OrderByComparator<Period> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPeriodException {
		return getPersistence().findByPlaceId_First(placeId, orderByComparator);
	}

	/**
	* Returns the first period in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching period, or <code>null</code> if a matching period could not be found
	*/
	public static Period fetchByPlaceId_First(long placeId,
		OrderByComparator<Period> orderByComparator) {
		return getPersistence().fetchByPlaceId_First(placeId, orderByComparator);
	}

	/**
	* Returns the last period in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching period
	* @throws NoSuchPeriodException if a matching period could not be found
	*/
	public static Period findByPlaceId_Last(long placeId,
		OrderByComparator<Period> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPeriodException {
		return getPersistence().findByPlaceId_Last(placeId, orderByComparator);
	}

	/**
	* Returns the last period in the ordered set where placeId = &#63;.
	*
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching period, or <code>null</code> if a matching period could not be found
	*/
	public static Period fetchByPlaceId_Last(long placeId,
		OrderByComparator<Period> orderByComparator) {
		return getPersistence().fetchByPlaceId_Last(placeId, orderByComparator);
	}

	/**
	* Returns the periods before and after the current period in the ordered set where placeId = &#63;.
	*
	* @param periodId the primary key of the current period
	* @param placeId the place ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next period
	* @throws NoSuchPeriodException if a period with the primary key could not be found
	*/
	public static Period[] findByPlaceId_PrevAndNext(long periodId,
		long placeId, OrderByComparator<Period> orderByComparator)
		throws eu.strasbourg.service.place.exception.NoSuchPeriodException {
		return getPersistence()
				   .findByPlaceId_PrevAndNext(periodId, placeId,
			orderByComparator);
	}

	/**
	* Removes all the periods where placeId = &#63; from the database.
	*
	* @param placeId the place ID
	*/
	public static void removeByPlaceId(long placeId) {
		getPersistence().removeByPlaceId(placeId);
	}

	/**
	* Returns the number of periods where placeId = &#63;.
	*
	* @param placeId the place ID
	* @return the number of matching periods
	*/
	public static int countByPlaceId(long placeId) {
		return getPersistence().countByPlaceId(placeId);
	}

	/**
	* Caches the period in the entity cache if it is enabled.
	*
	* @param period the period
	*/
	public static void cacheResult(Period period) {
		getPersistence().cacheResult(period);
	}

	/**
	* Caches the periods in the entity cache if it is enabled.
	*
	* @param periods the periods
	*/
	public static void cacheResult(List<Period> periods) {
		getPersistence().cacheResult(periods);
	}

	/**
	* Creates a new period with the primary key. Does not add the period to the database.
	*
	* @param periodId the primary key for the new period
	* @return the new period
	*/
	public static Period create(long periodId) {
		return getPersistence().create(periodId);
	}

	/**
	* Removes the period with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param periodId the primary key of the period
	* @return the period that was removed
	* @throws NoSuchPeriodException if a period with the primary key could not be found
	*/
	public static Period remove(long periodId)
		throws eu.strasbourg.service.place.exception.NoSuchPeriodException {
		return getPersistence().remove(periodId);
	}

	public static Period updateImpl(Period period) {
		return getPersistence().updateImpl(period);
	}

	/**
	* Returns the period with the primary key or throws a {@link NoSuchPeriodException} if it could not be found.
	*
	* @param periodId the primary key of the period
	* @return the period
	* @throws NoSuchPeriodException if a period with the primary key could not be found
	*/
	public static Period findByPrimaryKey(long periodId)
		throws eu.strasbourg.service.place.exception.NoSuchPeriodException {
		return getPersistence().findByPrimaryKey(periodId);
	}

	/**
	* Returns the period with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param periodId the primary key of the period
	* @return the period, or <code>null</code> if a period with the primary key could not be found
	*/
	public static Period fetchByPrimaryKey(long periodId) {
		return getPersistence().fetchByPrimaryKey(periodId);
	}

	public static java.util.Map<java.io.Serializable, Period> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the periods.
	*
	* @return the periods
	*/
	public static List<Period> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the periods.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of periods
	* @param end the upper bound of the range of periods (not inclusive)
	* @return the range of periods
	*/
	public static List<Period> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the periods.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of periods
	* @param end the upper bound of the range of periods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of periods
	*/
	public static List<Period> findAll(int start, int end,
		OrderByComparator<Period> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the periods.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of periods
	* @param end the upper bound of the range of periods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of periods
	*/
	public static List<Period> findAll(int start, int end,
		OrderByComparator<Period> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the periods from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of periods.
	*
	* @return the number of periods
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PeriodPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PeriodPersistence, PeriodPersistence> _serviceTracker =
		ServiceTrackerFactory.open(PeriodPersistence.class);
}