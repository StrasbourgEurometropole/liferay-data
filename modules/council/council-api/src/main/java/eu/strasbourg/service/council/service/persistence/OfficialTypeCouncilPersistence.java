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

import eu.strasbourg.service.council.exception.NoSuchOfficialTypeCouncilException;
import eu.strasbourg.service.council.model.OfficialTypeCouncil;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the official type council service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfficialTypeCouncilUtil
 * @generated
 */
@ProviderType
public interface OfficialTypeCouncilPersistence
	extends BasePersistence<OfficialTypeCouncil> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OfficialTypeCouncilUtil} to access the official type council persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, OfficialTypeCouncil> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the official type councils where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findByUuid(String uuid);

	/**
	 * Returns a range of all the official type councils where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of matching official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the official type councils where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator);

	/**
	 * Returns an ordered range of all the official type councils where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first official type council in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public OfficialTypeCouncil findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Returns the first official type council in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public OfficialTypeCouncil fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator);

	/**
	 * Returns the last official type council in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public OfficialTypeCouncil findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Returns the last official type council in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public OfficialTypeCouncil fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator);

	/**
	 * Returns the official type councils before and after the current official type council in the ordered set where uuid = &#63;.
	 *
	 * @param officialTypeCouncilPK the primary key of the current official type council
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next official type council
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	public OfficialTypeCouncil[] findByUuid_PrevAndNext(
			eu.strasbourg.service.council.service.persistence.
				OfficialTypeCouncilPK officialTypeCouncilPK,
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Removes all the official type councils where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of official type councils where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching official type councils
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the official type council where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchOfficialTypeCouncilException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public OfficialTypeCouncil findByUUID_G(String uuid, long groupId)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Returns the official type council where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public OfficialTypeCouncil fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the official type council where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public OfficialTypeCouncil fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache);

	/**
	 * Removes the official type council where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the official type council that was removed
	 */
	public OfficialTypeCouncil removeByUUID_G(String uuid, long groupId)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Returns the number of official type councils where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching official type councils
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the official type councils where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the official type councils where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of matching official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the official type councils where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator);

	/**
	 * Returns an ordered range of all the official type councils where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first official type council in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public OfficialTypeCouncil findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Returns the first official type council in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public OfficialTypeCouncil fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator);

	/**
	 * Returns the last official type council in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public OfficialTypeCouncil findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Returns the last official type council in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public OfficialTypeCouncil fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator);

	/**
	 * Returns the official type councils before and after the current official type council in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param officialTypeCouncilPK the primary key of the current official type council
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next official type council
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	public OfficialTypeCouncil[] findByUuid_C_PrevAndNext(
			eu.strasbourg.service.council.service.persistence.
				OfficialTypeCouncilPK officialTypeCouncilPK,
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Removes all the official type councils where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of official type councils where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching official type councils
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the official type councils where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @return the matching official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findByOfficialId(
		long officialId);

	/**
	 * Returns a range of all the official type councils where officialId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param officialId the official ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of matching official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findByOfficialId(
		long officialId, int start, int end);

	/**
	 * Returns an ordered range of all the official type councils where officialId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param officialId the official ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findByOfficialId(
		long officialId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator);

	/**
	 * Returns an ordered range of all the official type councils where officialId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param officialId the official ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findByOfficialId(
		long officialId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first official type council in the ordered set where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public OfficialTypeCouncil findByOfficialId_First(
			long officialId,
			com.liferay.portal.kernel.util.OrderByComparator
				<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Returns the first official type council in the ordered set where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public OfficialTypeCouncil fetchByOfficialId_First(
		long officialId,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator);

	/**
	 * Returns the last official type council in the ordered set where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public OfficialTypeCouncil findByOfficialId_Last(
			long officialId,
			com.liferay.portal.kernel.util.OrderByComparator
				<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Returns the last official type council in the ordered set where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public OfficialTypeCouncil fetchByOfficialId_Last(
		long officialId,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator);

	/**
	 * Returns the official type councils before and after the current official type council in the ordered set where officialId = &#63;.
	 *
	 * @param officialTypeCouncilPK the primary key of the current official type council
	 * @param officialId the official ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next official type council
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	public OfficialTypeCouncil[] findByOfficialId_PrevAndNext(
			eu.strasbourg.service.council.service.persistence.
				OfficialTypeCouncilPK officialTypeCouncilPK,
			long officialId,
			com.liferay.portal.kernel.util.OrderByComparator
				<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Removes all the official type councils where officialId = &#63; from the database.
	 *
	 * @param officialId the official ID
	 */
	public void removeByOfficialId(long officialId);

	/**
	 * Returns the number of official type councils where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @return the number of matching official type councils
	 */
	public int countByOfficialId(long officialId);

	/**
	 * Returns all the official type councils where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the matching official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findByTypeId(long typeId);

	/**
	 * Returns a range of all the official type councils where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of matching official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findByTypeId(
		long typeId, int start, int end);

	/**
	 * Returns an ordered range of all the official type councils where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findByTypeId(
		long typeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator);

	/**
	 * Returns an ordered range of all the official type councils where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findByTypeId(
		long typeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first official type council in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public OfficialTypeCouncil findByTypeId_First(
			long typeId,
			com.liferay.portal.kernel.util.OrderByComparator
				<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Returns the first official type council in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public OfficialTypeCouncil fetchByTypeId_First(
		long typeId,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator);

	/**
	 * Returns the last official type council in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public OfficialTypeCouncil findByTypeId_Last(
			long typeId,
			com.liferay.portal.kernel.util.OrderByComparator
				<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Returns the last official type council in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public OfficialTypeCouncil fetchByTypeId_Last(
		long typeId,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator);

	/**
	 * Returns the official type councils before and after the current official type council in the ordered set where typeId = &#63;.
	 *
	 * @param officialTypeCouncilPK the primary key of the current official type council
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next official type council
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	public OfficialTypeCouncil[] findByTypeId_PrevAndNext(
			eu.strasbourg.service.council.service.persistence.
				OfficialTypeCouncilPK officialTypeCouncilPK,
			long typeId,
			com.liferay.portal.kernel.util.OrderByComparator
				<OfficialTypeCouncil> orderByComparator)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Removes all the official type councils where typeId = &#63; from the database.
	 *
	 * @param typeId the type ID
	 */
	public void removeByTypeId(long typeId);

	/**
	 * Returns the number of official type councils where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the number of matching official type councils
	 */
	public int countByTypeId(long typeId);

	/**
	 * Returns the official type council where typeId = &#63; and officialId = &#63; or throws a <code>NoSuchOfficialTypeCouncilException</code> if it could not be found.
	 *
	 * @param typeId the type ID
	 * @param officialId the official ID
	 * @return the matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public OfficialTypeCouncil findByTypeIdAndOfficialId(
			long typeId, long officialId)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Returns the official type council where typeId = &#63; and officialId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param typeId the type ID
	 * @param officialId the official ID
	 * @return the matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public OfficialTypeCouncil fetchByTypeIdAndOfficialId(
		long typeId, long officialId);

	/**
	 * Returns the official type council where typeId = &#63; and officialId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param typeId the type ID
	 * @param officialId the official ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public OfficialTypeCouncil fetchByTypeIdAndOfficialId(
		long typeId, long officialId, boolean retrieveFromCache);

	/**
	 * Removes the official type council where typeId = &#63; and officialId = &#63; from the database.
	 *
	 * @param typeId the type ID
	 * @param officialId the official ID
	 * @return the official type council that was removed
	 */
	public OfficialTypeCouncil removeByTypeIdAndOfficialId(
			long typeId, long officialId)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Returns the number of official type councils where typeId = &#63; and officialId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param officialId the official ID
	 * @return the number of matching official type councils
	 */
	public int countByTypeIdAndOfficialId(long typeId, long officialId);

	/**
	 * Caches the official type council in the entity cache if it is enabled.
	 *
	 * @param officialTypeCouncil the official type council
	 */
	public void cacheResult(OfficialTypeCouncil officialTypeCouncil);

	/**
	 * Caches the official type councils in the entity cache if it is enabled.
	 *
	 * @param officialTypeCouncils the official type councils
	 */
	public void cacheResult(
		java.util.List<OfficialTypeCouncil> officialTypeCouncils);

	/**
	 * Creates a new official type council with the primary key. Does not add the official type council to the database.
	 *
	 * @param officialTypeCouncilPK the primary key for the new official type council
	 * @return the new official type council
	 */
	public OfficialTypeCouncil create(
		eu.strasbourg.service.council.service.persistence.OfficialTypeCouncilPK
			officialTypeCouncilPK);

	/**
	 * Removes the official type council with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param officialTypeCouncilPK the primary key of the official type council
	 * @return the official type council that was removed
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	public OfficialTypeCouncil remove(
			eu.strasbourg.service.council.service.persistence.
				OfficialTypeCouncilPK officialTypeCouncilPK)
		throws NoSuchOfficialTypeCouncilException;

	public OfficialTypeCouncil updateImpl(
		OfficialTypeCouncil officialTypeCouncil);

	/**
	 * Returns the official type council with the primary key or throws a <code>NoSuchOfficialTypeCouncilException</code> if it could not be found.
	 *
	 * @param officialTypeCouncilPK the primary key of the official type council
	 * @return the official type council
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	public OfficialTypeCouncil findByPrimaryKey(
			eu.strasbourg.service.council.service.persistence.
				OfficialTypeCouncilPK officialTypeCouncilPK)
		throws NoSuchOfficialTypeCouncilException;

	/**
	 * Returns the official type council with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param officialTypeCouncilPK the primary key of the official type council
	 * @return the official type council, or <code>null</code> if a official type council with the primary key could not be found
	 */
	public OfficialTypeCouncil fetchByPrimaryKey(
		eu.strasbourg.service.council.service.persistence.OfficialTypeCouncilPK
			officialTypeCouncilPK);

	/**
	 * Returns all the official type councils.
	 *
	 * @return the official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findAll();

	/**
	 * Returns a range of all the official type councils.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the official type councils.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator);

	/**
	 * Returns an ordered range of all the official type councils.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of official type councils
	 */
	public java.util.List<OfficialTypeCouncil> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfficialTypeCouncil>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the official type councils from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of official type councils.
	 *
	 * @return the number of official type councils
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

	public Set<String> getCompoundPKColumnNames();

}