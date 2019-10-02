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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.agenda.exception.NoSuchAgendaExportPeriodException;
import eu.strasbourg.service.agenda.model.AgendaExportPeriod;

/**
 * The persistence interface for the agenda export period service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.agenda.service.persistence.impl.AgendaExportPeriodPersistenceImpl
 * @see AgendaExportPeriodUtil
 * @generated
 */
@ProviderType
public interface AgendaExportPeriodPersistence extends BasePersistence<AgendaExportPeriod> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AgendaExportPeriodUtil} to access the agenda export period persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the agenda export periods where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching agenda export periods
	*/
	public java.util.List<AgendaExportPeriod> findByUuid(java.lang.String uuid);

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
	public java.util.List<AgendaExportPeriod> findByUuid(
		java.lang.String uuid, int start, int end);

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
	public java.util.List<AgendaExportPeriod> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExportPeriod> orderByComparator);

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
	public java.util.List<AgendaExportPeriod> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExportPeriod> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first agenda export period in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export period
	* @throws NoSuchAgendaExportPeriodException if a matching agenda export period could not be found
	*/
	public AgendaExportPeriod findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws NoSuchAgendaExportPeriodException;

	/**
	* Returns the first agenda export period in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export period, or <code>null</code> if a matching agenda export period could not be found
	*/
	public AgendaExportPeriod fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExportPeriod> orderByComparator);

	/**
	* Returns the last agenda export period in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export period
	* @throws NoSuchAgendaExportPeriodException if a matching agenda export period could not be found
	*/
	public AgendaExportPeriod findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws NoSuchAgendaExportPeriodException;

	/**
	* Returns the last agenda export period in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export period, or <code>null</code> if a matching agenda export period could not be found
	*/
	public AgendaExportPeriod fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExportPeriod> orderByComparator);

	/**
	* Returns the agenda export periods before and after the current agenda export period in the ordered set where uuid = &#63;.
	*
	* @param agendaExportPeriodId the primary key of the current agenda export period
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next agenda export period
	* @throws NoSuchAgendaExportPeriodException if a agenda export period with the primary key could not be found
	*/
	public AgendaExportPeriod[] findByUuid_PrevAndNext(
		long agendaExportPeriodId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws NoSuchAgendaExportPeriodException;

	/**
	* Removes all the agenda export periods where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of agenda export periods where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching agenda export periods
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns all the agenda export periods where agendaExportId = &#63;.
	*
	* @param agendaExportId the agenda export ID
	* @return the matching agenda export periods
	*/
	public java.util.List<AgendaExportPeriod> findByAgendaExportId(
		long agendaExportId);

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
	public java.util.List<AgendaExportPeriod> findByAgendaExportId(
		long agendaExportId, int start, int end);

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
	public java.util.List<AgendaExportPeriod> findByAgendaExportId(
		long agendaExportId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExportPeriod> orderByComparator);

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
	public java.util.List<AgendaExportPeriod> findByAgendaExportId(
		long agendaExportId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExportPeriod> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first agenda export period in the ordered set where agendaExportId = &#63;.
	*
	* @param agendaExportId the agenda export ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export period
	* @throws NoSuchAgendaExportPeriodException if a matching agenda export period could not be found
	*/
	public AgendaExportPeriod findByAgendaExportId_First(long agendaExportId,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws NoSuchAgendaExportPeriodException;

	/**
	* Returns the first agenda export period in the ordered set where agendaExportId = &#63;.
	*
	* @param agendaExportId the agenda export ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export period, or <code>null</code> if a matching agenda export period could not be found
	*/
	public AgendaExportPeriod fetchByAgendaExportId_First(long agendaExportId,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExportPeriod> orderByComparator);

	/**
	* Returns the last agenda export period in the ordered set where agendaExportId = &#63;.
	*
	* @param agendaExportId the agenda export ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export period
	* @throws NoSuchAgendaExportPeriodException if a matching agenda export period could not be found
	*/
	public AgendaExportPeriod findByAgendaExportId_Last(long agendaExportId,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws NoSuchAgendaExportPeriodException;

	/**
	* Returns the last agenda export period in the ordered set where agendaExportId = &#63;.
	*
	* @param agendaExportId the agenda export ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export period, or <code>null</code> if a matching agenda export period could not be found
	*/
	public AgendaExportPeriod fetchByAgendaExportId_Last(long agendaExportId,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExportPeriod> orderByComparator);

	/**
	* Returns the agenda export periods before and after the current agenda export period in the ordered set where agendaExportId = &#63;.
	*
	* @param agendaExportPeriodId the primary key of the current agenda export period
	* @param agendaExportId the agenda export ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next agenda export period
	* @throws NoSuchAgendaExportPeriodException if a agenda export period with the primary key could not be found
	*/
	public AgendaExportPeriod[] findByAgendaExportId_PrevAndNext(
		long agendaExportPeriodId, long agendaExportId,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExportPeriod> orderByComparator)
		throws NoSuchAgendaExportPeriodException;

	/**
	* Removes all the agenda export periods where agendaExportId = &#63; from the database.
	*
	* @param agendaExportId the agenda export ID
	*/
	public void removeByAgendaExportId(long agendaExportId);

	/**
	* Returns the number of agenda export periods where agendaExportId = &#63;.
	*
	* @param agendaExportId the agenda export ID
	* @return the number of matching agenda export periods
	*/
	public int countByAgendaExportId(long agendaExportId);

	/**
	* Caches the agenda export period in the entity cache if it is enabled.
	*
	* @param agendaExportPeriod the agenda export period
	*/
	public void cacheResult(AgendaExportPeriod agendaExportPeriod);

	/**
	* Caches the agenda export periods in the entity cache if it is enabled.
	*
	* @param agendaExportPeriods the agenda export periods
	*/
	public void cacheResult(
		java.util.List<AgendaExportPeriod> agendaExportPeriods);

	/**
	* Creates a new agenda export period with the primary key. Does not add the agenda export period to the database.
	*
	* @param agendaExportPeriodId the primary key for the new agenda export period
	* @return the new agenda export period
	*/
	public AgendaExportPeriod create(long agendaExportPeriodId);

	/**
	* Removes the agenda export period with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExportPeriodId the primary key of the agenda export period
	* @return the agenda export period that was removed
	* @throws NoSuchAgendaExportPeriodException if a agenda export period with the primary key could not be found
	*/
	public AgendaExportPeriod remove(long agendaExportPeriodId)
		throws NoSuchAgendaExportPeriodException;

	public AgendaExportPeriod updateImpl(AgendaExportPeriod agendaExportPeriod);

	/**
	* Returns the agenda export period with the primary key or throws a {@link NoSuchAgendaExportPeriodException} if it could not be found.
	*
	* @param agendaExportPeriodId the primary key of the agenda export period
	* @return the agenda export period
	* @throws NoSuchAgendaExportPeriodException if a agenda export period with the primary key could not be found
	*/
	public AgendaExportPeriod findByPrimaryKey(long agendaExportPeriodId)
		throws NoSuchAgendaExportPeriodException;

	/**
	* Returns the agenda export period with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param agendaExportPeriodId the primary key of the agenda export period
	* @return the agenda export period, or <code>null</code> if a agenda export period with the primary key could not be found
	*/
	public AgendaExportPeriod fetchByPrimaryKey(long agendaExportPeriodId);

	@Override
	public java.util.Map<java.io.Serializable, AgendaExportPeriod> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the agenda export periods.
	*
	* @return the agenda export periods
	*/
	public java.util.List<AgendaExportPeriod> findAll();

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
	public java.util.List<AgendaExportPeriod> findAll(int start, int end);

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
	public java.util.List<AgendaExportPeriod> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExportPeriod> orderByComparator);

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
	public java.util.List<AgendaExportPeriod> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExportPeriod> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the agenda export periods from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of agenda export periods.
	*
	* @return the number of agenda export periods
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}