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

package eu.strasbourg.service.activity.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.activity.exception.NoSuchAssociationException;
import eu.strasbourg.service.activity.model.Association;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the association service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssociationUtil
 * @generated
 */
@ProviderType
public interface AssociationPersistence extends BasePersistence<Association> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssociationUtil} to access the association persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, Association> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the associations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching associations
	 */
	public java.util.List<Association> findByUuid(String uuid);

	/**
	 * Returns a range of all the associations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssociationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of associations
	 * @param end the upper bound of the range of associations (not inclusive)
	 * @return the range of matching associations
	 */
	public java.util.List<Association> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the associations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssociationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of associations
	 * @param end the upper bound of the range of associations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching associations
	 */
	public java.util.List<Association> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Association>
			orderByComparator);

	/**
	 * Returns an ordered range of all the associations where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssociationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of associations
	 * @param end the upper bound of the range of associations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching associations
	 */
	public java.util.List<Association> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Association>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first association in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching association
	 * @throws NoSuchAssociationException if a matching association could not be found
	 */
	public Association findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Association>
				orderByComparator)
		throws NoSuchAssociationException;

	/**
	 * Returns the first association in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching association, or <code>null</code> if a matching association could not be found
	 */
	public Association fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Association>
			orderByComparator);

	/**
	 * Returns the last association in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching association
	 * @throws NoSuchAssociationException if a matching association could not be found
	 */
	public Association findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Association>
				orderByComparator)
		throws NoSuchAssociationException;

	/**
	 * Returns the last association in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching association, or <code>null</code> if a matching association could not be found
	 */
	public Association fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Association>
			orderByComparator);

	/**
	 * Returns the associations before and after the current association in the ordered set where uuid = &#63;.
	 *
	 * @param associationId the primary key of the current association
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next association
	 * @throws NoSuchAssociationException if a association with the primary key could not be found
	 */
	public Association[] findByUuid_PrevAndNext(
			long associationId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Association>
				orderByComparator)
		throws NoSuchAssociationException;

	/**
	 * Removes all the associations where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of associations where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching associations
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the association where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchAssociationException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching association
	 * @throws NoSuchAssociationException if a matching association could not be found
	 */
	public Association findByUUID_G(String uuid, long groupId)
		throws NoSuchAssociationException;

	/**
	 * Returns the association where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching association, or <code>null</code> if a matching association could not be found
	 */
	public Association fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the association where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching association, or <code>null</code> if a matching association could not be found
	 */
	public Association fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the association where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the association that was removed
	 */
	public Association removeByUUID_G(String uuid, long groupId)
		throws NoSuchAssociationException;

	/**
	 * Returns the number of associations where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching associations
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the associations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching associations
	 */
	public java.util.List<Association> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the associations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssociationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of associations
	 * @param end the upper bound of the range of associations (not inclusive)
	 * @return the range of matching associations
	 */
	public java.util.List<Association> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the associations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssociationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of associations
	 * @param end the upper bound of the range of associations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching associations
	 */
	public java.util.List<Association> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Association>
			orderByComparator);

	/**
	 * Returns an ordered range of all the associations where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssociationModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of associations
	 * @param end the upper bound of the range of associations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching associations
	 */
	public java.util.List<Association> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Association>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first association in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching association
	 * @throws NoSuchAssociationException if a matching association could not be found
	 */
	public Association findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Association>
				orderByComparator)
		throws NoSuchAssociationException;

	/**
	 * Returns the first association in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching association, or <code>null</code> if a matching association could not be found
	 */
	public Association fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Association>
			orderByComparator);

	/**
	 * Returns the last association in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching association
	 * @throws NoSuchAssociationException if a matching association could not be found
	 */
	public Association findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Association>
				orderByComparator)
		throws NoSuchAssociationException;

	/**
	 * Returns the last association in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching association, or <code>null</code> if a matching association could not be found
	 */
	public Association fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Association>
			orderByComparator);

	/**
	 * Returns the associations before and after the current association in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param associationId the primary key of the current association
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next association
	 * @throws NoSuchAssociationException if a association with the primary key could not be found
	 */
	public Association[] findByUuid_C_PrevAndNext(
			long associationId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<Association>
				orderByComparator)
		throws NoSuchAssociationException;

	/**
	 * Removes all the associations where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of associations where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching associations
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the associations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching associations
	 */
	public java.util.List<Association> findByGroupId(long groupId);

	/**
	 * Returns a range of all the associations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssociationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of associations
	 * @param end the upper bound of the range of associations (not inclusive)
	 * @return the range of matching associations
	 */
	public java.util.List<Association> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the associations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssociationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of associations
	 * @param end the upper bound of the range of associations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching associations
	 */
	public java.util.List<Association> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Association>
			orderByComparator);

	/**
	 * Returns an ordered range of all the associations where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssociationModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of associations
	 * @param end the upper bound of the range of associations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching associations
	 */
	public java.util.List<Association> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Association>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first association in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching association
	 * @throws NoSuchAssociationException if a matching association could not be found
	 */
	public Association findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Association>
				orderByComparator)
		throws NoSuchAssociationException;

	/**
	 * Returns the first association in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching association, or <code>null</code> if a matching association could not be found
	 */
	public Association fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Association>
			orderByComparator);

	/**
	 * Returns the last association in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching association
	 * @throws NoSuchAssociationException if a matching association could not be found
	 */
	public Association findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Association>
				orderByComparator)
		throws NoSuchAssociationException;

	/**
	 * Returns the last association in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching association, or <code>null</code> if a matching association could not be found
	 */
	public Association fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Association>
			orderByComparator);

	/**
	 * Returns the associations before and after the current association in the ordered set where groupId = &#63;.
	 *
	 * @param associationId the primary key of the current association
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next association
	 * @throws NoSuchAssociationException if a association with the primary key could not be found
	 */
	public Association[] findByGroupId_PrevAndNext(
			long associationId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<Association>
				orderByComparator)
		throws NoSuchAssociationException;

	/**
	 * Removes all the associations where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of associations where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching associations
	 */
	public int countByGroupId(long groupId);

	/**
	 * Caches the association in the entity cache if it is enabled.
	 *
	 * @param association the association
	 */
	public void cacheResult(Association association);

	/**
	 * Caches the associations in the entity cache if it is enabled.
	 *
	 * @param associations the associations
	 */
	public void cacheResult(java.util.List<Association> associations);

	/**
	 * Creates a new association with the primary key. Does not add the association to the database.
	 *
	 * @param associationId the primary key for the new association
	 * @return the new association
	 */
	public Association create(long associationId);

	/**
	 * Removes the association with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param associationId the primary key of the association
	 * @return the association that was removed
	 * @throws NoSuchAssociationException if a association with the primary key could not be found
	 */
	public Association remove(long associationId)
		throws NoSuchAssociationException;

	public Association updateImpl(Association association);

	/**
	 * Returns the association with the primary key or throws a <code>NoSuchAssociationException</code> if it could not be found.
	 *
	 * @param associationId the primary key of the association
	 * @return the association
	 * @throws NoSuchAssociationException if a association with the primary key could not be found
	 */
	public Association findByPrimaryKey(long associationId)
		throws NoSuchAssociationException;

	/**
	 * Returns the association with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param associationId the primary key of the association
	 * @return the association, or <code>null</code> if a association with the primary key could not be found
	 */
	public Association fetchByPrimaryKey(long associationId);

	/**
	 * Returns all the associations.
	 *
	 * @return the associations
	 */
	public java.util.List<Association> findAll();

	/**
	 * Returns a range of all the associations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssociationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of associations
	 * @param end the upper bound of the range of associations (not inclusive)
	 * @return the range of associations
	 */
	public java.util.List<Association> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the associations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssociationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of associations
	 * @param end the upper bound of the range of associations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of associations
	 */
	public java.util.List<Association> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Association>
			orderByComparator);

	/**
	 * Returns an ordered range of all the associations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssociationModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of associations
	 * @param end the upper bound of the range of associations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of associations
	 */
	public java.util.List<Association> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Association>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the associations from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of associations.
	 *
	 * @return the number of associations
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}