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

package eu.strasbourg.service.agenda.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.agenda.model.AgendaExportPeriod;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the agenda export period service. This utility wraps {@link eu.strasbourg.service.agenda.service.persistence.impl.AgendaExportPeriodPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see AgendaExportPeriodPersistence
 * @see eu.strasbourg.service.agenda.service.persistence.impl.AgendaExportPeriodPersistenceImpl
 * @generated
 */
@ProviderType
public class AgendaExportPeriodUtil {
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
	public static void clearCache(AgendaExportPeriod agendaExportPeriod) {
		getPersistence().clearCache(agendaExportPeriod);
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
	public static List<AgendaExportPeriod> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AgendaExportPeriod> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AgendaExportPeriod> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AgendaExportPeriod> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AgendaExportPeriod update(
		AgendaExportPeriod agendaExportPeriod) {
		return getPersistence().update(agendaExportPeriod);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AgendaExportPeriod update(
		AgendaExportPeriod agendaExportPeriod, ServiceContext serviceContext) {
		return getPersistence().update(agendaExportPeriod, serviceContext);
	}

	/**
	* Returns all the agenda export periods where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching agenda export periods
	*/
	public static List<AgendaExportPeriod> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the agenda export periods where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of agenda export periods
	* @param end the upper bound of the range of agenda export periods (not inclusive)
	* @return the range of matching agenda export periods
	*/
	public static List<AgendaExportPeriod> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the agenda export periods where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of agenda export periods
	* @param end the upper bound of the range of agenda export periods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching agenda export periods
	*/
	public static List<AgendaExportPeriod> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<AgendaExportPeriod> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the agenda export periods where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of agenda export periods
	* @param end the upper bound of the range of agenda export periods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching agenda export periods
	*/
	public static List<AgendaExportPeriod> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<AgendaExportPeriod> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first agenda export period in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export period
	* @throws NoSuchAgendaExportPeriodException if a matching agenda export period could not be found
	*/
	public static AgendaExportPeriod findByUuid_First(java.lang.String uuid,
		OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchAgendaExportPeriodException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first agenda export period in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export period, or <code>null</code> if a matching agenda export period could not be found
	*/
	public static AgendaExportPeriod fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<AgendaExportPeriod> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last agenda export period in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export period
	* @throws NoSuchAgendaExportPeriodException if a matching agenda export period could not be found
	*/
	public static AgendaExportPeriod findByUuid_Last(java.lang.String uuid,
		OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchAgendaExportPeriodException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last agenda export period in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export period, or <code>null</code> if a matching agenda export period could not be found
	*/
	public static AgendaExportPeriod fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<AgendaExportPeriod> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the agenda export periods before and after the current agenda export period in the ordered set where uuid = &#63;.
	*
	* @param agendaExportPeriodId the primary key of the current agenda export period
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next agenda export period
	* @throws NoSuchAgendaExportPeriodException if a agenda export period with the primary key could not be found
	*/
	public static AgendaExportPeriod[] findByUuid_PrevAndNext(
		long agendaExportPeriodId, java.lang.String uuid,
		OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchAgendaExportPeriodException {
		return getPersistence()
				   .findByUuid_PrevAndNext(agendaExportPeriodId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the agenda export periods where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of agenda export periods where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching agenda export periods
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the agenda export periods where agendaExportId = &#63;.
	*
	* @param agendaExportId the agenda export ID
	* @return the matching agenda export periods
	*/
	public static List<AgendaExportPeriod> findByAgendaExportId(
		long agendaExportId) {
		return getPersistence().findByAgendaExportId(agendaExportId);
	}

	/**
	* Returns a range of all the agenda export periods where agendaExportId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param agendaExportId the agenda export ID
	* @param start the lower bound of the range of agenda export periods
	* @param end the upper bound of the range of agenda export periods (not inclusive)
	* @return the range of matching agenda export periods
	*/
	public static List<AgendaExportPeriod> findByAgendaExportId(
		long agendaExportId, int start, int end) {
		return getPersistence().findByAgendaExportId(agendaExportId, start, end);
	}

	/**
	* Returns an ordered range of all the agenda export periods where agendaExportId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param agendaExportId the agenda export ID
	* @param start the lower bound of the range of agenda export periods
	* @param end the upper bound of the range of agenda export periods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching agenda export periods
	*/
	public static List<AgendaExportPeriod> findByAgendaExportId(
		long agendaExportId, int start, int end,
		OrderByComparator<AgendaExportPeriod> orderByComparator) {
		return getPersistence()
				   .findByAgendaExportId(agendaExportId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the agenda export periods where agendaExportId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param agendaExportId the agenda export ID
	* @param start the lower bound of the range of agenda export periods
	* @param end the upper bound of the range of agenda export periods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching agenda export periods
	*/
	public static List<AgendaExportPeriod> findByAgendaExportId(
		long agendaExportId, int start, int end,
		OrderByComparator<AgendaExportPeriod> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAgendaExportId(agendaExportId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first agenda export period in the ordered set where agendaExportId = &#63;.
	*
	* @param agendaExportId the agenda export ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export period
	* @throws NoSuchAgendaExportPeriodException if a matching agenda export period could not be found
	*/
	public static AgendaExportPeriod findByAgendaExportId_First(
		long agendaExportId,
		OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchAgendaExportPeriodException {
		return getPersistence()
				   .findByAgendaExportId_First(agendaExportId, orderByComparator);
	}

	/**
	* Returns the first agenda export period in the ordered set where agendaExportId = &#63;.
	*
	* @param agendaExportId the agenda export ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export period, or <code>null</code> if a matching agenda export period could not be found
	*/
	public static AgendaExportPeriod fetchByAgendaExportId_First(
		long agendaExportId,
		OrderByComparator<AgendaExportPeriod> orderByComparator) {
		return getPersistence()
				   .fetchByAgendaExportId_First(agendaExportId,
			orderByComparator);
	}

	/**
	* Returns the last agenda export period in the ordered set where agendaExportId = &#63;.
	*
	* @param agendaExportId the agenda export ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export period
	* @throws NoSuchAgendaExportPeriodException if a matching agenda export period could not be found
	*/
	public static AgendaExportPeriod findByAgendaExportId_Last(
		long agendaExportId,
		OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchAgendaExportPeriodException {
		return getPersistence()
				   .findByAgendaExportId_Last(agendaExportId, orderByComparator);
	}

	/**
	* Returns the last agenda export period in the ordered set where agendaExportId = &#63;.
	*
	* @param agendaExportId the agenda export ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export period, or <code>null</code> if a matching agenda export period could not be found
	*/
	public static AgendaExportPeriod fetchByAgendaExportId_Last(
		long agendaExportId,
		OrderByComparator<AgendaExportPeriod> orderByComparator) {
		return getPersistence()
				   .fetchByAgendaExportId_Last(agendaExportId, orderByComparator);
	}

	/**
	* Returns the agenda export periods before and after the current agenda export period in the ordered set where agendaExportId = &#63;.
	*
	* @param agendaExportPeriodId the primary key of the current agenda export period
	* @param agendaExportId the agenda export ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next agenda export period
	* @throws NoSuchAgendaExportPeriodException if a agenda export period with the primary key could not be found
	*/
	public static AgendaExportPeriod[] findByAgendaExportId_PrevAndNext(
		long agendaExportPeriodId, long agendaExportId,
		OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchAgendaExportPeriodException {
		return getPersistence()
				   .findByAgendaExportId_PrevAndNext(agendaExportPeriodId,
			agendaExportId, orderByComparator);
	}

	/**
	* Removes all the agenda export periods where agendaExportId = &#63; from the database.
	*
	* @param agendaExportId the agenda export ID
	*/
	public static void removeByAgendaExportId(long agendaExportId) {
		getPersistence().removeByAgendaExportId(agendaExportId);
	}

	/**
	* Returns the number of agenda export periods where agendaExportId = &#63;.
	*
	* @param agendaExportId the agenda export ID
	* @return the number of matching agenda export periods
	*/
	public static int countByAgendaExportId(long agendaExportId) {
		return getPersistence().countByAgendaExportId(agendaExportId);
	}

	/**
	* Caches the agenda export period in the entity cache if it is enabled.
	*
	* @param agendaExportPeriod the agenda export period
	*/
	public static void cacheResult(AgendaExportPeriod agendaExportPeriod) {
		getPersistence().cacheResult(agendaExportPeriod);
	}

	/**
	* Caches the agenda export periods in the entity cache if it is enabled.
	*
	* @param agendaExportPeriods the agenda export periods
	*/
	public static void cacheResult(List<AgendaExportPeriod> agendaExportPeriods) {
		getPersistence().cacheResult(agendaExportPeriods);
	}

	/**
	* Creates a new agenda export period with the primary key. Does not add the agenda export period to the database.
	*
	* @param agendaExportPeriodId the primary key for the new agenda export period
	* @return the new agenda export period
	*/
	public static AgendaExportPeriod create(long agendaExportPeriodId) {
		return getPersistence().create(agendaExportPeriodId);
	}

	/**
	* Removes the agenda export period with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExportPeriodId the primary key of the agenda export period
	* @return the agenda export period that was removed
	* @throws NoSuchAgendaExportPeriodException if a agenda export period with the primary key could not be found
	*/
	public static AgendaExportPeriod remove(long agendaExportPeriodId)
		throws eu.strasbourg.service.agenda.exception.NoSuchAgendaExportPeriodException {
		return getPersistence().remove(agendaExportPeriodId);
	}

	public static AgendaExportPeriod updateImpl(
		AgendaExportPeriod agendaExportPeriod) {
		return getPersistence().updateImpl(agendaExportPeriod);
	}

	/**
	* Returns the agenda export period with the primary key or throws a {@link NoSuchAgendaExportPeriodException} if it could not be found.
	*
	* @param agendaExportPeriodId the primary key of the agenda export period
	* @return the agenda export period
	* @throws NoSuchAgendaExportPeriodException if a agenda export period with the primary key could not be found
	*/
	public static AgendaExportPeriod findByPrimaryKey(long agendaExportPeriodId)
		throws eu.strasbourg.service.agenda.exception.NoSuchAgendaExportPeriodException {
		return getPersistence().findByPrimaryKey(agendaExportPeriodId);
	}

	/**
	* Returns the agenda export period with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param agendaExportPeriodId the primary key of the agenda export period
	* @return the agenda export period, or <code>null</code> if a agenda export period with the primary key could not be found
	*/
	public static AgendaExportPeriod fetchByPrimaryKey(
		long agendaExportPeriodId) {
		return getPersistence().fetchByPrimaryKey(agendaExportPeriodId);
	}

	public static java.util.Map<java.io.Serializable, AgendaExportPeriod> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the agenda export periods.
	*
	* @return the agenda export periods
	*/
	public static List<AgendaExportPeriod> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the agenda export periods.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of agenda export periods
	* @param end the upper bound of the range of agenda export periods (not inclusive)
	* @return the range of agenda export periods
	*/
	public static List<AgendaExportPeriod> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the agenda export periods.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of agenda export periods
	* @param end the upper bound of the range of agenda export periods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of agenda export periods
	*/
	public static List<AgendaExportPeriod> findAll(int start, int end,
		OrderByComparator<AgendaExportPeriod> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the agenda export periods.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of agenda export periods
	* @param end the upper bound of the range of agenda export periods (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of agenda export periods
	*/
	public static List<AgendaExportPeriod> findAll(int start, int end,
		OrderByComparator<AgendaExportPeriod> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the agenda export periods from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of agenda export periods.
	*
	* @return the number of agenda export periods
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AgendaExportPeriodPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AgendaExportPeriodPersistence, AgendaExportPeriodPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AgendaExportPeriodPersistence.class);
}