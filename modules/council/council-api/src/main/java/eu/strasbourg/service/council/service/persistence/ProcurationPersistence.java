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

package eu.strasbourg.service.council.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.council.exception.NoSuchProcurationException;
import eu.strasbourg.service.council.model.Procuration;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the procuration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProcurationUtil
 * @generated
 */
@ProviderType
public interface ProcurationPersistence extends BasePersistence<Procuration> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProcurationUtil} to access the procuration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, Procuration> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the procurations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching procurations
	 */
	public java.util.List<Procuration> findByUuid(String uuid);

	/**
	 * Returns a range of all the procurations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @return the range of matching procurations
	 */
	public java.util.List<Procuration> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the procurations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching procurations
	 */
	public java.util.List<Procuration> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator);

	/**
	 * Returns an ordered range of all the procurations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching procurations
	 */
	public java.util.List<Procuration> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first procuration in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching procuration
	 * @throws NoSuchProcurationException if a matching procuration could not be found
	 */
	public Procuration findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator)
		throws NoSuchProcurationException;

	/**
	 * Returns the first procuration in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	public Procuration fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator);

	/**
	 * Returns the last procuration in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching procuration
	 * @throws NoSuchProcurationException if a matching procuration could not be found
	 */
	public Procuration findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator)
		throws NoSuchProcurationException;

	/**
	 * Returns the last procuration in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	public Procuration fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator);

	/**
	 * Returns the procurations before and after the current procuration in the ordered set where uuid = &#63;.
	 *
	 * @param procurationId the primary key of the current procuration
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next procuration
	 * @throws NoSuchProcurationException if a procuration with the primary key could not be found
	 */
	public Procuration[] findByUuid_PrevAndNext(
			long procurationId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator)
		throws NoSuchProcurationException;

	/**
	 * Removes all the procurations where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of procurations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching procurations
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the procuration where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchProcurationException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching procuration
	 * @throws NoSuchProcurationException if a matching procuration could not be found
	 */
	public Procuration findByUUID_G(String uuid, long groupId)
		throws NoSuchProcurationException;

	/**
	 * Returns the procuration where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	public Procuration fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the procuration where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	public Procuration fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the procuration where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the procuration that was removed
	 */
	public Procuration removeByUUID_G(String uuid, long groupId)
		throws NoSuchProcurationException;

	/**
	 * Returns the number of procurations where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching procurations
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the procurations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching procurations
	 */
	public java.util.List<Procuration> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the procurations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @return the range of matching procurations
	 */
	public java.util.List<Procuration> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the procurations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching procurations
	 */
	public java.util.List<Procuration> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator);

	/**
	 * Returns an ordered range of all the procurations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching procurations
	 */
	public java.util.List<Procuration> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first procuration in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching procuration
	 * @throws NoSuchProcurationException if a matching procuration could not be found
	 */
	public Procuration findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator)
		throws NoSuchProcurationException;

	/**
	 * Returns the first procuration in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	public Procuration fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator);

	/**
	 * Returns the last procuration in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching procuration
	 * @throws NoSuchProcurationException if a matching procuration could not be found
	 */
	public Procuration findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator)
		throws NoSuchProcurationException;

	/**
	 * Returns the last procuration in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	public Procuration fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator);

	/**
	 * Returns the procurations before and after the current procuration in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param procurationId the primary key of the current procuration
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next procuration
	 * @throws NoSuchProcurationException if a procuration with the primary key could not be found
	 */
	public Procuration[] findByUuid_C_PrevAndNext(
			long procurationId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator)
		throws NoSuchProcurationException;

	/**
	 * Removes all the procurations where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of procurations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching procurations
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the procurations where councilSessionId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @return the matching procurations
	 */
	public java.util.List<Procuration> findByCouncilSessionId(
		long councilSessionId);

	/**
	 * Returns a range of all the procurations where councilSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param councilSessionId the council session ID
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @return the range of matching procurations
	 */
	public java.util.List<Procuration> findByCouncilSessionId(
		long councilSessionId, int start, int end);

	/**
	 * Returns an ordered range of all the procurations where councilSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param councilSessionId the council session ID
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching procurations
	 */
	public java.util.List<Procuration> findByCouncilSessionId(
		long councilSessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator);

	/**
	 * Returns an ordered range of all the procurations where councilSessionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param councilSessionId the council session ID
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching procurations
	 */
	public java.util.List<Procuration> findByCouncilSessionId(
		long councilSessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first procuration in the ordered set where councilSessionId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching procuration
	 * @throws NoSuchProcurationException if a matching procuration could not be found
	 */
	public Procuration findByCouncilSessionId_First(
			long councilSessionId,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator)
		throws NoSuchProcurationException;

	/**
	 * Returns the first procuration in the ordered set where councilSessionId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	public Procuration fetchByCouncilSessionId_First(
		long councilSessionId,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator);

	/**
	 * Returns the last procuration in the ordered set where councilSessionId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching procuration
	 * @throws NoSuchProcurationException if a matching procuration could not be found
	 */
	public Procuration findByCouncilSessionId_Last(
			long councilSessionId,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator)
		throws NoSuchProcurationException;

	/**
	 * Returns the last procuration in the ordered set where councilSessionId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	public Procuration fetchByCouncilSessionId_Last(
		long councilSessionId,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator);

	/**
	 * Returns the procurations before and after the current procuration in the ordered set where councilSessionId = &#63;.
	 *
	 * @param procurationId the primary key of the current procuration
	 * @param councilSessionId the council session ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next procuration
	 * @throws NoSuchProcurationException if a procuration with the primary key could not be found
	 */
	public Procuration[] findByCouncilSessionId_PrevAndNext(
			long procurationId, long councilSessionId,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator)
		throws NoSuchProcurationException;

	/**
	 * Removes all the procurations where councilSessionId = &#63; from the database.
	 *
	 * @param councilSessionId the council session ID
	 */
	public void removeByCouncilSessionId(long councilSessionId);

	/**
	 * Returns the number of procurations where councilSessionId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @return the number of matching procurations
	 */
	public int countByCouncilSessionId(long councilSessionId);

	/**
	 * Returns all the procurations where councilSessionId = &#63; and officialVotersId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialVotersId the official voters ID
	 * @return the matching procurations
	 */
	public java.util.List<Procuration>
		findByCouncilSessionIdAndOfficialVotersId(
			long councilSessionId, long officialVotersId);

	/**
	 * Returns a range of all the procurations where councilSessionId = &#63; and officialVotersId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param councilSessionId the council session ID
	 * @param officialVotersId the official voters ID
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @return the range of matching procurations
	 */
	public java.util.List<Procuration>
		findByCouncilSessionIdAndOfficialVotersId(
			long councilSessionId, long officialVotersId, int start, int end);

	/**
	 * Returns an ordered range of all the procurations where councilSessionId = &#63; and officialVotersId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param councilSessionId the council session ID
	 * @param officialVotersId the official voters ID
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching procurations
	 */
	public java.util.List<Procuration>
		findByCouncilSessionIdAndOfficialVotersId(
			long councilSessionId, long officialVotersId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator);

	/**
	 * Returns an ordered range of all the procurations where councilSessionId = &#63; and officialVotersId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param councilSessionId the council session ID
	 * @param officialVotersId the official voters ID
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching procurations
	 */
	public java.util.List<Procuration>
		findByCouncilSessionIdAndOfficialVotersId(
			long councilSessionId, long officialVotersId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator,
			boolean useFinderCache);

	/**
	 * Returns the first procuration in the ordered set where councilSessionId = &#63; and officialVotersId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialVotersId the official voters ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching procuration
	 * @throws NoSuchProcurationException if a matching procuration could not be found
	 */
	public Procuration findByCouncilSessionIdAndOfficialVotersId_First(
			long councilSessionId, long officialVotersId,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator)
		throws NoSuchProcurationException;

	/**
	 * Returns the first procuration in the ordered set where councilSessionId = &#63; and officialVotersId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialVotersId the official voters ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	public Procuration fetchByCouncilSessionIdAndOfficialVotersId_First(
		long councilSessionId, long officialVotersId,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator);

	/**
	 * Returns the last procuration in the ordered set where councilSessionId = &#63; and officialVotersId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialVotersId the official voters ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching procuration
	 * @throws NoSuchProcurationException if a matching procuration could not be found
	 */
	public Procuration findByCouncilSessionIdAndOfficialVotersId_Last(
			long councilSessionId, long officialVotersId,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator)
		throws NoSuchProcurationException;

	/**
	 * Returns the last procuration in the ordered set where councilSessionId = &#63; and officialVotersId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialVotersId the official voters ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	public Procuration fetchByCouncilSessionIdAndOfficialVotersId_Last(
		long councilSessionId, long officialVotersId,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator);

	/**
	 * Returns the procurations before and after the current procuration in the ordered set where councilSessionId = &#63; and officialVotersId = &#63;.
	 *
	 * @param procurationId the primary key of the current procuration
	 * @param councilSessionId the council session ID
	 * @param officialVotersId the official voters ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next procuration
	 * @throws NoSuchProcurationException if a procuration with the primary key could not be found
	 */
	public Procuration[] findByCouncilSessionIdAndOfficialVotersId_PrevAndNext(
			long procurationId, long councilSessionId, long officialVotersId,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator)
		throws NoSuchProcurationException;

	/**
	 * Removes all the procurations where councilSessionId = &#63; and officialVotersId = &#63; from the database.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialVotersId the official voters ID
	 */
	public void removeByCouncilSessionIdAndOfficialVotersId(
		long councilSessionId, long officialVotersId);

	/**
	 * Returns the number of procurations where councilSessionId = &#63; and officialVotersId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialVotersId the official voters ID
	 * @return the number of matching procurations
	 */
	public int countByCouncilSessionIdAndOfficialVotersId(
		long councilSessionId, long officialVotersId);

	/**
	 * Returns all the procurations where councilSessionId = &#63; and officialUnavailableId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialUnavailableId the official unavailable ID
	 * @return the matching procurations
	 */
	public java.util.List<Procuration>
		findByCouncilSessionIdAndOfficialUnavailableId(
			long councilSessionId, long officialUnavailableId);

	/**
	 * Returns a range of all the procurations where councilSessionId = &#63; and officialUnavailableId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param councilSessionId the council session ID
	 * @param officialUnavailableId the official unavailable ID
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @return the range of matching procurations
	 */
	public java.util.List<Procuration>
		findByCouncilSessionIdAndOfficialUnavailableId(
			long councilSessionId, long officialUnavailableId, int start,
			int end);

	/**
	 * Returns an ordered range of all the procurations where councilSessionId = &#63; and officialUnavailableId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param councilSessionId the council session ID
	 * @param officialUnavailableId the official unavailable ID
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching procurations
	 */
	public java.util.List<Procuration>
		findByCouncilSessionIdAndOfficialUnavailableId(
			long councilSessionId, long officialUnavailableId, int start,
			int end,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator);

	/**
	 * Returns an ordered range of all the procurations where councilSessionId = &#63; and officialUnavailableId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param councilSessionId the council session ID
	 * @param officialUnavailableId the official unavailable ID
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching procurations
	 */
	public java.util.List<Procuration>
		findByCouncilSessionIdAndOfficialUnavailableId(
			long councilSessionId, long officialUnavailableId, int start,
			int end,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator,
			boolean useFinderCache);

	/**
	 * Returns the first procuration in the ordered set where councilSessionId = &#63; and officialUnavailableId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialUnavailableId the official unavailable ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching procuration
	 * @throws NoSuchProcurationException if a matching procuration could not be found
	 */
	public Procuration findByCouncilSessionIdAndOfficialUnavailableId_First(
			long councilSessionId, long officialUnavailableId,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator)
		throws NoSuchProcurationException;

	/**
	 * Returns the first procuration in the ordered set where councilSessionId = &#63; and officialUnavailableId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialUnavailableId the official unavailable ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	public Procuration fetchByCouncilSessionIdAndOfficialUnavailableId_First(
		long councilSessionId, long officialUnavailableId,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator);

	/**
	 * Returns the last procuration in the ordered set where councilSessionId = &#63; and officialUnavailableId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialUnavailableId the official unavailable ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching procuration
	 * @throws NoSuchProcurationException if a matching procuration could not be found
	 */
	public Procuration findByCouncilSessionIdAndOfficialUnavailableId_Last(
			long councilSessionId, long officialUnavailableId,
			com.liferay.portal.kernel.util.OrderByComparator<Procuration>
				orderByComparator)
		throws NoSuchProcurationException;

	/**
	 * Returns the last procuration in the ordered set where councilSessionId = &#63; and officialUnavailableId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialUnavailableId the official unavailable ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	public Procuration fetchByCouncilSessionIdAndOfficialUnavailableId_Last(
		long councilSessionId, long officialUnavailableId,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator);

	/**
	 * Returns the procurations before and after the current procuration in the ordered set where councilSessionId = &#63; and officialUnavailableId = &#63;.
	 *
	 * @param procurationId the primary key of the current procuration
	 * @param councilSessionId the council session ID
	 * @param officialUnavailableId the official unavailable ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next procuration
	 * @throws NoSuchProcurationException if a procuration with the primary key could not be found
	 */
	public Procuration[]
			findByCouncilSessionIdAndOfficialUnavailableId_PrevAndNext(
				long procurationId, long councilSessionId,
				long officialUnavailableId,
				com.liferay.portal.kernel.util.OrderByComparator<Procuration>
					orderByComparator)
		throws NoSuchProcurationException;

	/**
	 * Removes all the procurations where councilSessionId = &#63; and officialUnavailableId = &#63; from the database.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialUnavailableId the official unavailable ID
	 */
	public void removeByCouncilSessionIdAndOfficialUnavailableId(
		long councilSessionId, long officialUnavailableId);

	/**
	 * Returns the number of procurations where councilSessionId = &#63; and officialUnavailableId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialUnavailableId the official unavailable ID
	 * @return the number of matching procurations
	 */
	public int countByCouncilSessionIdAndOfficialUnavailableId(
		long councilSessionId, long officialUnavailableId);

	/**
	 * Returns the procuration where councilSessionId = &#63; and officialVotersId = &#63; and officialUnavailableId = &#63; or throws a <code>NoSuchProcurationException</code> if it could not be found.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialVotersId the official voters ID
	 * @param officialUnavailableId the official unavailable ID
	 * @return the matching procuration
	 * @throws NoSuchProcurationException if a matching procuration could not be found
	 */
	public Procuration findByCouncilSessionIdAndOfficialVotersAndUnavailableIds(
			long councilSessionId, long officialVotersId,
			long officialUnavailableId)
		throws NoSuchProcurationException;

	/**
	 * Returns the procuration where councilSessionId = &#63; and officialVotersId = &#63; and officialUnavailableId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialVotersId the official voters ID
	 * @param officialUnavailableId the official unavailable ID
	 * @return the matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	public Procuration
		fetchByCouncilSessionIdAndOfficialVotersAndUnavailableIds(
			long councilSessionId, long officialVotersId,
			long officialUnavailableId);

	/**
	 * Returns the procuration where councilSessionId = &#63; and officialVotersId = &#63; and officialUnavailableId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialVotersId the official voters ID
	 * @param officialUnavailableId the official unavailable ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	public Procuration
		fetchByCouncilSessionIdAndOfficialVotersAndUnavailableIds(
			long councilSessionId, long officialVotersId,
			long officialUnavailableId, boolean useFinderCache);

	/**
	 * Removes the procuration where councilSessionId = &#63; and officialVotersId = &#63; and officialUnavailableId = &#63; from the database.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialVotersId the official voters ID
	 * @param officialUnavailableId the official unavailable ID
	 * @return the procuration that was removed
	 */
	public Procuration
			removeByCouncilSessionIdAndOfficialVotersAndUnavailableIds(
				long councilSessionId, long officialVotersId,
				long officialUnavailableId)
		throws NoSuchProcurationException;

	/**
	 * Returns the number of procurations where councilSessionId = &#63; and officialVotersId = &#63; and officialUnavailableId = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialVotersId the official voters ID
	 * @param officialUnavailableId the official unavailable ID
	 * @return the number of matching procurations
	 */
	public int countByCouncilSessionIdAndOfficialVotersAndUnavailableIds(
		long councilSessionId, long officialVotersId,
		long officialUnavailableId);

	/**
	 * Returns the procuration where councilSessionId = &#63; and officialUnavailableId = &#63; and isAbsent = &#63; or throws a <code>NoSuchProcurationException</code> if it could not be found.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialUnavailableId the official unavailable ID
	 * @param isAbsent the is absent
	 * @return the matching procuration
	 * @throws NoSuchProcurationException if a matching procuration could not be found
	 */
	public Procuration findByAbsenceForCouncilSession(
			long councilSessionId, long officialUnavailableId, boolean isAbsent)
		throws NoSuchProcurationException;

	/**
	 * Returns the procuration where councilSessionId = &#63; and officialUnavailableId = &#63; and isAbsent = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialUnavailableId the official unavailable ID
	 * @param isAbsent the is absent
	 * @return the matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	public Procuration fetchByAbsenceForCouncilSession(
		long councilSessionId, long officialUnavailableId, boolean isAbsent);

	/**
	 * Returns the procuration where councilSessionId = &#63; and officialUnavailableId = &#63; and isAbsent = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialUnavailableId the official unavailable ID
	 * @param isAbsent the is absent
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching procuration, or <code>null</code> if a matching procuration could not be found
	 */
	public Procuration fetchByAbsenceForCouncilSession(
		long councilSessionId, long officialUnavailableId, boolean isAbsent,
		boolean useFinderCache);

	/**
	 * Removes the procuration where councilSessionId = &#63; and officialUnavailableId = &#63; and isAbsent = &#63; from the database.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialUnavailableId the official unavailable ID
	 * @param isAbsent the is absent
	 * @return the procuration that was removed
	 */
	public Procuration removeByAbsenceForCouncilSession(
			long councilSessionId, long officialUnavailableId, boolean isAbsent)
		throws NoSuchProcurationException;

	/**
	 * Returns the number of procurations where councilSessionId = &#63; and officialUnavailableId = &#63; and isAbsent = &#63;.
	 *
	 * @param councilSessionId the council session ID
	 * @param officialUnavailableId the official unavailable ID
	 * @param isAbsent the is absent
	 * @return the number of matching procurations
	 */
	public int countByAbsenceForCouncilSession(
		long councilSessionId, long officialUnavailableId, boolean isAbsent);

	/**
	 * Caches the procuration in the entity cache if it is enabled.
	 *
	 * @param procuration the procuration
	 */
	public void cacheResult(Procuration procuration);

	/**
	 * Caches the procurations in the entity cache if it is enabled.
	 *
	 * @param procurations the procurations
	 */
	public void cacheResult(java.util.List<Procuration> procurations);

	/**
	 * Creates a new procuration with the primary key. Does not add the procuration to the database.
	 *
	 * @param procurationId the primary key for the new procuration
	 * @return the new procuration
	 */
	public Procuration create(long procurationId);

	/**
	 * Removes the procuration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param procurationId the primary key of the procuration
	 * @return the procuration that was removed
	 * @throws NoSuchProcurationException if a procuration with the primary key could not be found
	 */
	public Procuration remove(long procurationId)
		throws NoSuchProcurationException;

	public Procuration updateImpl(Procuration procuration);

	/**
	 * Returns the procuration with the primary key or throws a <code>NoSuchProcurationException</code> if it could not be found.
	 *
	 * @param procurationId the primary key of the procuration
	 * @return the procuration
	 * @throws NoSuchProcurationException if a procuration with the primary key could not be found
	 */
	public Procuration findByPrimaryKey(long procurationId)
		throws NoSuchProcurationException;

	/**
	 * Returns the procuration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param procurationId the primary key of the procuration
	 * @return the procuration, or <code>null</code> if a procuration with the primary key could not be found
	 */
	public Procuration fetchByPrimaryKey(long procurationId);

	/**
	 * Returns all the procurations.
	 *
	 * @return the procurations
	 */
	public java.util.List<Procuration> findAll();

	/**
	 * Returns a range of all the procurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @return the range of procurations
	 */
	public java.util.List<Procuration> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the procurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of procurations
	 */
	public java.util.List<Procuration> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator);

	/**
	 * Returns an ordered range of all the procurations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProcurationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of procurations
	 * @param end the upper bound of the range of procurations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of procurations
	 */
	public java.util.List<Procuration> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Procuration>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the procurations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of procurations.
	 *
	 * @return the number of procurations
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}