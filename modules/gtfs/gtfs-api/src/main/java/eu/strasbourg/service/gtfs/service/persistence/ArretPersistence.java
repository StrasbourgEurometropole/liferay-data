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

package eu.strasbourg.service.gtfs.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.gtfs.exception.NoSuchArretException;
import eu.strasbourg.service.gtfs.model.Arret;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the arret service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see ArretUtil
 * @generated
 */
@ProviderType
public interface ArretPersistence extends BasePersistence<Arret> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ArretUtil} to access the arret persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, Arret> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the arrets where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching arrets
	 */
	public java.util.List<Arret> findByUuid(String uuid);

	/**
	 * Returns a range of all the arrets where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @return the range of matching arrets
	 */
	public java.util.List<Arret> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the arrets where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching arrets
	 */
	public java.util.List<Arret> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator);

	/**
	 * Returns an ordered range of all the arrets where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching arrets
	 */
	public java.util.List<Arret> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first arret in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching arret
	 * @throws NoSuchArretException if a matching arret could not be found
	 */
	public Arret findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Arret>
				orderByComparator)
		throws NoSuchArretException;

	/**
	 * Returns the first arret in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching arret, or <code>null</code> if a matching arret could not be found
	 */
	public Arret fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator);

	/**
	 * Returns the last arret in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching arret
	 * @throws NoSuchArretException if a matching arret could not be found
	 */
	public Arret findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Arret>
				orderByComparator)
		throws NoSuchArretException;

	/**
	 * Returns the last arret in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching arret, or <code>null</code> if a matching arret could not be found
	 */
	public Arret fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator);

	/**
	 * Returns the arrets before and after the current arret in the ordered set where uuid = &#63;.
	 *
	 * @param arretId the primary key of the current arret
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next arret
	 * @throws NoSuchArretException if a arret with the primary key could not be found
	 */
	public Arret[] findByUuid_PrevAndNext(
			long arretId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Arret>
				orderByComparator)
		throws NoSuchArretException;

	/**
	 * Removes all the arrets where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of arrets where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching arrets
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the arret where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchArretException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching arret
	 * @throws NoSuchArretException if a matching arret could not be found
	 */
	public Arret findByUUID_G(String uuid, long groupId)
		throws NoSuchArretException;

	/**
	 * Returns the arret where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching arret, or <code>null</code> if a matching arret could not be found
	 */
	public Arret fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the arret where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching arret, or <code>null</code> if a matching arret could not be found
	 */
	public Arret fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the arret where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the arret that was removed
	 */
	public Arret removeByUUID_G(String uuid, long groupId)
		throws NoSuchArretException;

	/**
	 * Returns the number of arrets where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching arrets
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the arrets where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching arrets
	 */
	public java.util.List<Arret> findByUuid_C(String uuid, long companyId);

	/**
	 * Returns a range of all the arrets where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @return the range of matching arrets
	 */
	public java.util.List<Arret> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the arrets where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching arrets
	 */
	public java.util.List<Arret> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator);

	/**
	 * Returns an ordered range of all the arrets where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching arrets
	 */
	public java.util.List<Arret> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first arret in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching arret
	 * @throws NoSuchArretException if a matching arret could not be found
	 */
	public Arret findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Arret>
				orderByComparator)
		throws NoSuchArretException;

	/**
	 * Returns the first arret in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching arret, or <code>null</code> if a matching arret could not be found
	 */
	public Arret fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator);

	/**
	 * Returns the last arret in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching arret
	 * @throws NoSuchArretException if a matching arret could not be found
	 */
	public Arret findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Arret>
				orderByComparator)
		throws NoSuchArretException;

	/**
	 * Returns the last arret in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching arret, or <code>null</code> if a matching arret could not be found
	 */
	public Arret fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator);

	/**
	 * Returns the arrets before and after the current arret in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param arretId the primary key of the current arret
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next arret
	 * @throws NoSuchArretException if a arret with the primary key could not be found
	 */
	public Arret[] findByUuid_C_PrevAndNext(
			long arretId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Arret>
				orderByComparator)
		throws NoSuchArretException;

	/**
	 * Removes all the arrets where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of arrets where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching arrets
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the arrets where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching arrets
	 */
	public java.util.List<Arret> findByGroupId(long groupId);

	/**
	 * Returns a range of all the arrets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @return the range of matching arrets
	 */
	public java.util.List<Arret> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the arrets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching arrets
	 */
	public java.util.List<Arret> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator);

	/**
	 * Returns an ordered range of all the arrets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching arrets
	 */
	public java.util.List<Arret> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first arret in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching arret
	 * @throws NoSuchArretException if a matching arret could not be found
	 */
	public Arret findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Arret>
				orderByComparator)
		throws NoSuchArretException;

	/**
	 * Returns the first arret in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching arret, or <code>null</code> if a matching arret could not be found
	 */
	public Arret fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator);

	/**
	 * Returns the last arret in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching arret
	 * @throws NoSuchArretException if a matching arret could not be found
	 */
	public Arret findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Arret>
				orderByComparator)
		throws NoSuchArretException;

	/**
	 * Returns the last arret in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching arret, or <code>null</code> if a matching arret could not be found
	 */
	public Arret fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator);

	/**
	 * Returns the arrets before and after the current arret in the ordered set where groupId = &#63;.
	 *
	 * @param arretId the primary key of the current arret
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next arret
	 * @throws NoSuchArretException if a arret with the primary key could not be found
	 */
	public Arret[] findByGroupId_PrevAndNext(
			long arretId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Arret>
				orderByComparator)
		throws NoSuchArretException;

	/**
	 * Removes all the arrets where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of arrets where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching arrets
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns all the arrets where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching arrets
	 */
	public java.util.List<Arret> findByStatus(int status);

	/**
	 * Returns a range of all the arrets where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @return the range of matching arrets
	 */
	public java.util.List<Arret> findByStatus(int status, int start, int end);

	/**
	 * Returns an ordered range of all the arrets where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching arrets
	 */
	public java.util.List<Arret> findByStatus(
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator);

	/**
	 * Returns an ordered range of all the arrets where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching arrets
	 */
	public java.util.List<Arret> findByStatus(
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first arret in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching arret
	 * @throws NoSuchArretException if a matching arret could not be found
	 */
	public Arret findByStatus_First(
			int status,
			com.liferay.portal.kernel.util.OrderByComparator<Arret>
				orderByComparator)
		throws NoSuchArretException;

	/**
	 * Returns the first arret in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching arret, or <code>null</code> if a matching arret could not be found
	 */
	public Arret fetchByStatus_First(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator);

	/**
	 * Returns the last arret in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching arret
	 * @throws NoSuchArretException if a matching arret could not be found
	 */
	public Arret findByStatus_Last(
			int status,
			com.liferay.portal.kernel.util.OrderByComparator<Arret>
				orderByComparator)
		throws NoSuchArretException;

	/**
	 * Returns the last arret in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching arret, or <code>null</code> if a matching arret could not be found
	 */
	public Arret fetchByStatus_Last(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator);

	/**
	 * Returns the arrets before and after the current arret in the ordered set where status = &#63;.
	 *
	 * @param arretId the primary key of the current arret
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next arret
	 * @throws NoSuchArretException if a arret with the primary key could not be found
	 */
	public Arret[] findByStatus_PrevAndNext(
			long arretId, int status,
			com.liferay.portal.kernel.util.OrderByComparator<Arret>
				orderByComparator)
		throws NoSuchArretException;

	/**
	 * Removes all the arrets where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	public void removeByStatus(int status);

	/**
	 * Returns the number of arrets where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching arrets
	 */
	public int countByStatus(int status);

	/**
	 * Returns the arret where stopId = &#63; or throws a <code>NoSuchArretException</code> if it could not be found.
	 *
	 * @param stopId the stop ID
	 * @return the matching arret
	 * @throws NoSuchArretException if a matching arret could not be found
	 */
	public Arret findByStopId(String stopId) throws NoSuchArretException;

	/**
	 * Returns the arret where stopId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param stopId the stop ID
	 * @return the matching arret, or <code>null</code> if a matching arret could not be found
	 */
	public Arret fetchByStopId(String stopId);

	/**
	 * Returns the arret where stopId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param stopId the stop ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching arret, or <code>null</code> if a matching arret could not be found
	 */
	public Arret fetchByStopId(String stopId, boolean useFinderCache);

	/**
	 * Removes the arret where stopId = &#63; from the database.
	 *
	 * @param stopId the stop ID
	 * @return the arret that was removed
	 */
	public Arret removeByStopId(String stopId) throws NoSuchArretException;

	/**
	 * Returns the number of arrets where stopId = &#63;.
	 *
	 * @param stopId the stop ID
	 * @return the number of matching arrets
	 */
	public int countByStopId(String stopId);

	/**
	 * Returns all the arrets where code = &#63;.
	 *
	 * @param code the code
	 * @return the matching arrets
	 */
	public java.util.List<Arret> findByCode(String code);

	/**
	 * Returns a range of all the arrets where code = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param code the code
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @return the range of matching arrets
	 */
	public java.util.List<Arret> findByCode(String code, int start, int end);

	/**
	 * Returns an ordered range of all the arrets where code = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param code the code
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching arrets
	 */
	public java.util.List<Arret> findByCode(
		String code, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator);

	/**
	 * Returns an ordered range of all the arrets where code = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param code the code
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching arrets
	 */
	public java.util.List<Arret> findByCode(
		String code, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first arret in the ordered set where code = &#63;.
	 *
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching arret
	 * @throws NoSuchArretException if a matching arret could not be found
	 */
	public Arret findByCode_First(
			String code,
			com.liferay.portal.kernel.util.OrderByComparator<Arret>
				orderByComparator)
		throws NoSuchArretException;

	/**
	 * Returns the first arret in the ordered set where code = &#63;.
	 *
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching arret, or <code>null</code> if a matching arret could not be found
	 */
	public Arret fetchByCode_First(
		String code,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator);

	/**
	 * Returns the last arret in the ordered set where code = &#63;.
	 *
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching arret
	 * @throws NoSuchArretException if a matching arret could not be found
	 */
	public Arret findByCode_Last(
			String code,
			com.liferay.portal.kernel.util.OrderByComparator<Arret>
				orderByComparator)
		throws NoSuchArretException;

	/**
	 * Returns the last arret in the ordered set where code = &#63;.
	 *
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching arret, or <code>null</code> if a matching arret could not be found
	 */
	public Arret fetchByCode_Last(
		String code,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator);

	/**
	 * Returns the arrets before and after the current arret in the ordered set where code = &#63;.
	 *
	 * @param arretId the primary key of the current arret
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next arret
	 * @throws NoSuchArretException if a arret with the primary key could not be found
	 */
	public Arret[] findByCode_PrevAndNext(
			long arretId, String code,
			com.liferay.portal.kernel.util.OrderByComparator<Arret>
				orderByComparator)
		throws NoSuchArretException;

	/**
	 * Removes all the arrets where code = &#63; from the database.
	 *
	 * @param code the code
	 */
	public void removeByCode(String code);

	/**
	 * Returns the number of arrets where code = &#63;.
	 *
	 * @param code the code
	 * @return the number of matching arrets
	 */
	public int countByCode(String code);

	/**
	 * Caches the arret in the entity cache if it is enabled.
	 *
	 * @param arret the arret
	 */
	public void cacheResult(Arret arret);

	/**
	 * Caches the arrets in the entity cache if it is enabled.
	 *
	 * @param arrets the arrets
	 */
	public void cacheResult(java.util.List<Arret> arrets);

	/**
	 * Creates a new arret with the primary key. Does not add the arret to the database.
	 *
	 * @param arretId the primary key for the new arret
	 * @return the new arret
	 */
	public Arret create(long arretId);

	/**
	 * Removes the arret with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param arretId the primary key of the arret
	 * @return the arret that was removed
	 * @throws NoSuchArretException if a arret with the primary key could not be found
	 */
	public Arret remove(long arretId) throws NoSuchArretException;

	public Arret updateImpl(Arret arret);

	/**
	 * Returns the arret with the primary key or throws a <code>NoSuchArretException</code> if it could not be found.
	 *
	 * @param arretId the primary key of the arret
	 * @return the arret
	 * @throws NoSuchArretException if a arret with the primary key could not be found
	 */
	public Arret findByPrimaryKey(long arretId) throws NoSuchArretException;

	/**
	 * Returns the arret with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param arretId the primary key of the arret
	 * @return the arret, or <code>null</code> if a arret with the primary key could not be found
	 */
	public Arret fetchByPrimaryKey(long arretId);

	/**
	 * Returns all the arrets.
	 *
	 * @return the arrets
	 */
	public java.util.List<Arret> findAll();

	/**
	 * Returns a range of all the arrets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @return the range of arrets
	 */
	public java.util.List<Arret> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the arrets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of arrets
	 */
	public java.util.List<Arret> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator);

	/**
	 * Returns an ordered range of all the arrets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ArretModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of arrets
	 */
	public java.util.List<Arret> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Arret>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the arrets from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of arrets.
	 *
	 * @return the number of arrets
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}