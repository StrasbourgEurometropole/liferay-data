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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.place.model.PublicHoliday;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the public holiday service. This utility wraps <code>eu.strasbourg.service.place.service.persistence.impl.PublicHolidayPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see PublicHolidayPersistence
 * @generated
 */
@ProviderType
public class PublicHolidayUtil {

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
	public static void clearCache(PublicHoliday publicHoliday) {
		getPersistence().clearCache(publicHoliday);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, PublicHoliday> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<PublicHoliday> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PublicHoliday> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PublicHoliday> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PublicHoliday> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PublicHoliday update(PublicHoliday publicHoliday) {
		return getPersistence().update(publicHoliday);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PublicHoliday update(
		PublicHoliday publicHoliday, ServiceContext serviceContext) {

		return getPersistence().update(publicHoliday, serviceContext);
	}

	/**
	 * Returns all the public holidaies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching public holidaies
	 */
	public static List<PublicHoliday> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the public holidaies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @return the range of matching public holidaies
	 */
	public static List<PublicHoliday> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the public holidaies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching public holidaies
	 */
	public static List<PublicHoliday> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<PublicHoliday> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the public holidaies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching public holidaies
	 */
	public static List<PublicHoliday> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<PublicHoliday> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first public holiday in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching public holiday
	 * @throws NoSuchPublicHolidayException if a matching public holiday could not be found
	 */
	public static PublicHoliday findByUuid_First(
			String uuid, OrderByComparator<PublicHoliday> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchPublicHolidayException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first public holiday in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching public holiday, or <code>null</code> if a matching public holiday could not be found
	 */
	public static PublicHoliday fetchByUuid_First(
		String uuid, OrderByComparator<PublicHoliday> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last public holiday in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching public holiday
	 * @throws NoSuchPublicHolidayException if a matching public holiday could not be found
	 */
	public static PublicHoliday findByUuid_Last(
			String uuid, OrderByComparator<PublicHoliday> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchPublicHolidayException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last public holiday in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching public holiday, or <code>null</code> if a matching public holiday could not be found
	 */
	public static PublicHoliday fetchByUuid_Last(
		String uuid, OrderByComparator<PublicHoliday> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the public holidaies before and after the current public holiday in the ordered set where uuid = &#63;.
	 *
	 * @param publicHolidayId the primary key of the current public holiday
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next public holiday
	 * @throws NoSuchPublicHolidayException if a public holiday with the primary key could not be found
	 */
	public static PublicHoliday[] findByUuid_PrevAndNext(
			long publicHolidayId, String uuid,
			OrderByComparator<PublicHoliday> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchPublicHolidayException {

		return getPersistence().findByUuid_PrevAndNext(
			publicHolidayId, uuid, orderByComparator);
	}

	/**
	 * Removes all the public holidaies where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of public holidaies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching public holidaies
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns all the public holidaies where recurrent = &#63;.
	 *
	 * @param recurrent the recurrent
	 * @return the matching public holidaies
	 */
	public static List<PublicHoliday> findByRecurrent(boolean recurrent) {
		return getPersistence().findByRecurrent(recurrent);
	}

	/**
	 * Returns a range of all the public holidaies where recurrent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param recurrent the recurrent
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @return the range of matching public holidaies
	 */
	public static List<PublicHoliday> findByRecurrent(
		boolean recurrent, int start, int end) {

		return getPersistence().findByRecurrent(recurrent, start, end);
	}

	/**
	 * Returns an ordered range of all the public holidaies where recurrent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param recurrent the recurrent
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching public holidaies
	 */
	public static List<PublicHoliday> findByRecurrent(
		boolean recurrent, int start, int end,
		OrderByComparator<PublicHoliday> orderByComparator) {

		return getPersistence().findByRecurrent(
			recurrent, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the public holidaies where recurrent = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param recurrent the recurrent
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching public holidaies
	 */
	public static List<PublicHoliday> findByRecurrent(
		boolean recurrent, int start, int end,
		OrderByComparator<PublicHoliday> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByRecurrent(
			recurrent, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first public holiday in the ordered set where recurrent = &#63;.
	 *
	 * @param recurrent the recurrent
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching public holiday
	 * @throws NoSuchPublicHolidayException if a matching public holiday could not be found
	 */
	public static PublicHoliday findByRecurrent_First(
			boolean recurrent,
			OrderByComparator<PublicHoliday> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchPublicHolidayException {

		return getPersistence().findByRecurrent_First(
			recurrent, orderByComparator);
	}

	/**
	 * Returns the first public holiday in the ordered set where recurrent = &#63;.
	 *
	 * @param recurrent the recurrent
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching public holiday, or <code>null</code> if a matching public holiday could not be found
	 */
	public static PublicHoliday fetchByRecurrent_First(
		boolean recurrent, OrderByComparator<PublicHoliday> orderByComparator) {

		return getPersistence().fetchByRecurrent_First(
			recurrent, orderByComparator);
	}

	/**
	 * Returns the last public holiday in the ordered set where recurrent = &#63;.
	 *
	 * @param recurrent the recurrent
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching public holiday
	 * @throws NoSuchPublicHolidayException if a matching public holiday could not be found
	 */
	public static PublicHoliday findByRecurrent_Last(
			boolean recurrent,
			OrderByComparator<PublicHoliday> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchPublicHolidayException {

		return getPersistence().findByRecurrent_Last(
			recurrent, orderByComparator);
	}

	/**
	 * Returns the last public holiday in the ordered set where recurrent = &#63;.
	 *
	 * @param recurrent the recurrent
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching public holiday, or <code>null</code> if a matching public holiday could not be found
	 */
	public static PublicHoliday fetchByRecurrent_Last(
		boolean recurrent, OrderByComparator<PublicHoliday> orderByComparator) {

		return getPersistence().fetchByRecurrent_Last(
			recurrent, orderByComparator);
	}

	/**
	 * Returns the public holidaies before and after the current public holiday in the ordered set where recurrent = &#63;.
	 *
	 * @param publicHolidayId the primary key of the current public holiday
	 * @param recurrent the recurrent
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next public holiday
	 * @throws NoSuchPublicHolidayException if a public holiday with the primary key could not be found
	 */
	public static PublicHoliday[] findByRecurrent_PrevAndNext(
			long publicHolidayId, boolean recurrent,
			OrderByComparator<PublicHoliday> orderByComparator)
		throws eu.strasbourg.service.place.exception.
			NoSuchPublicHolidayException {

		return getPersistence().findByRecurrent_PrevAndNext(
			publicHolidayId, recurrent, orderByComparator);
	}

	/**
	 * Removes all the public holidaies where recurrent = &#63; from the database.
	 *
	 * @param recurrent the recurrent
	 */
	public static void removeByRecurrent(boolean recurrent) {
		getPersistence().removeByRecurrent(recurrent);
	}

	/**
	 * Returns the number of public holidaies where recurrent = &#63;.
	 *
	 * @param recurrent the recurrent
	 * @return the number of matching public holidaies
	 */
	public static int countByRecurrent(boolean recurrent) {
		return getPersistence().countByRecurrent(recurrent);
	}

	/**
	 * Caches the public holiday in the entity cache if it is enabled.
	 *
	 * @param publicHoliday the public holiday
	 */
	public static void cacheResult(PublicHoliday publicHoliday) {
		getPersistence().cacheResult(publicHoliday);
	}

	/**
	 * Caches the public holidaies in the entity cache if it is enabled.
	 *
	 * @param publicHolidaies the public holidaies
	 */
	public static void cacheResult(List<PublicHoliday> publicHolidaies) {
		getPersistence().cacheResult(publicHolidaies);
	}

	/**
	 * Creates a new public holiday with the primary key. Does not add the public holiday to the database.
	 *
	 * @param publicHolidayId the primary key for the new public holiday
	 * @return the new public holiday
	 */
	public static PublicHoliday create(long publicHolidayId) {
		return getPersistence().create(publicHolidayId);
	}

	/**
	 * Removes the public holiday with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param publicHolidayId the primary key of the public holiday
	 * @return the public holiday that was removed
	 * @throws NoSuchPublicHolidayException if a public holiday with the primary key could not be found
	 */
	public static PublicHoliday remove(long publicHolidayId)
		throws eu.strasbourg.service.place.exception.
			NoSuchPublicHolidayException {

		return getPersistence().remove(publicHolidayId);
	}

	public static PublicHoliday updateImpl(PublicHoliday publicHoliday) {
		return getPersistence().updateImpl(publicHoliday);
	}

	/**
	 * Returns the public holiday with the primary key or throws a <code>NoSuchPublicHolidayException</code> if it could not be found.
	 *
	 * @param publicHolidayId the primary key of the public holiday
	 * @return the public holiday
	 * @throws NoSuchPublicHolidayException if a public holiday with the primary key could not be found
	 */
	public static PublicHoliday findByPrimaryKey(long publicHolidayId)
		throws eu.strasbourg.service.place.exception.
			NoSuchPublicHolidayException {

		return getPersistence().findByPrimaryKey(publicHolidayId);
	}

	/**
	 * Returns the public holiday with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param publicHolidayId the primary key of the public holiday
	 * @return the public holiday, or <code>null</code> if a public holiday with the primary key could not be found
	 */
	public static PublicHoliday fetchByPrimaryKey(long publicHolidayId) {
		return getPersistence().fetchByPrimaryKey(publicHolidayId);
	}

	/**
	 * Returns all the public holidaies.
	 *
	 * @return the public holidaies
	 */
	public static List<PublicHoliday> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the public holidaies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @return the range of public holidaies
	 */
	public static List<PublicHoliday> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the public holidaies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of public holidaies
	 */
	public static List<PublicHoliday> findAll(
		int start, int end,
		OrderByComparator<PublicHoliday> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the public holidaies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>PublicHolidayModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of public holidaies
	 * @param end the upper bound of the range of public holidaies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of public holidaies
	 */
	public static List<PublicHoliday> findAll(
		int start, int end, OrderByComparator<PublicHoliday> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the public holidaies from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of public holidaies.
	 *
	 * @return the number of public holidaies
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PublicHolidayPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<PublicHolidayPersistence, PublicHolidayPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PublicHolidayPersistence.class);

		ServiceTracker<PublicHolidayPersistence, PublicHolidayPersistence>
			serviceTracker =
				new ServiceTracker
					<PublicHolidayPersistence, PublicHolidayPersistence>(
						bundle.getBundleContext(),
						PublicHolidayPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}