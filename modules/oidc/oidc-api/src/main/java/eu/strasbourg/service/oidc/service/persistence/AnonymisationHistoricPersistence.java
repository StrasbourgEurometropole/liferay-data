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

package eu.strasbourg.service.oidc.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.oidc.exception.NoSuchAnonymisationHistoricException;
import eu.strasbourg.service.oidc.model.AnonymisationHistoric;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the anonymisation historic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnonymisationHistoricUtil
 * @generated
 */
@ProviderType
public interface AnonymisationHistoricPersistence
	extends BasePersistence<AnonymisationHistoric> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AnonymisationHistoricUtil} to access the anonymisation historic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, AnonymisationHistoric> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the anonymisation historics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching anonymisation historics
	 */
	public java.util.List<AnonymisationHistoric> findByUuid(String uuid);

	/**
	 * Returns a range of all the anonymisation historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @return the range of matching anonymisation historics
	 */
	public java.util.List<AnonymisationHistoric> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the anonymisation historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching anonymisation historics
	 */
	public java.util.List<AnonymisationHistoric> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AnonymisationHistoric>
			orderByComparator);

	/**
	 * Returns an ordered range of all the anonymisation historics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching anonymisation historics
	 */
	public java.util.List<AnonymisationHistoric> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AnonymisationHistoric>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first anonymisation historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	public AnonymisationHistoric findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException;

	/**
	 * Returns the first anonymisation historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	public AnonymisationHistoric fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AnonymisationHistoric>
			orderByComparator);

	/**
	 * Returns the last anonymisation historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	public AnonymisationHistoric findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException;

	/**
	 * Returns the last anonymisation historic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	public AnonymisationHistoric fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AnonymisationHistoric>
			orderByComparator);

	/**
	 * Returns the anonymisation historics before and after the current anonymisation historic in the ordered set where uuid = &#63;.
	 *
	 * @param anonymisationHistoricId the primary key of the current anonymisation historic
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	 */
	public AnonymisationHistoric[] findByUuid_PrevAndNext(
			long anonymisationHistoricId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException;

	/**
	 * Removes all the anonymisation historics where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of anonymisation historics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching anonymisation historics
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the anonymisation historic where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchAnonymisationHistoricException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	public AnonymisationHistoric findByUUID_G(String uuid, long groupId)
		throws NoSuchAnonymisationHistoricException;

	/**
	 * Returns the anonymisation historic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	public AnonymisationHistoric fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the anonymisation historic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	public AnonymisationHistoric fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache);

	/**
	 * Removes the anonymisation historic where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the anonymisation historic that was removed
	 */
	public AnonymisationHistoric removeByUUID_G(String uuid, long groupId)
		throws NoSuchAnonymisationHistoricException;

	/**
	 * Returns the number of anonymisation historics where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching anonymisation historics
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the anonymisation historics where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching anonymisation historics
	 */
	public java.util.List<AnonymisationHistoric> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the anonymisation historics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @return the range of matching anonymisation historics
	 */
	public java.util.List<AnonymisationHistoric> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the anonymisation historics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching anonymisation historics
	 */
	public java.util.List<AnonymisationHistoric> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AnonymisationHistoric>
			orderByComparator);

	/**
	 * Returns an ordered range of all the anonymisation historics where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching anonymisation historics
	 */
	public java.util.List<AnonymisationHistoric> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AnonymisationHistoric>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first anonymisation historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	public AnonymisationHistoric findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException;

	/**
	 * Returns the first anonymisation historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	public AnonymisationHistoric fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AnonymisationHistoric>
			orderByComparator);

	/**
	 * Returns the last anonymisation historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	public AnonymisationHistoric findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException;

	/**
	 * Returns the last anonymisation historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	public AnonymisationHistoric fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AnonymisationHistoric>
			orderByComparator);

	/**
	 * Returns the anonymisation historics before and after the current anonymisation historic in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param anonymisationHistoricId the primary key of the current anonymisation historic
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	 */
	public AnonymisationHistoric[] findByUuid_C_PrevAndNext(
			long anonymisationHistoricId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException;

	/**
	 * Removes all the anonymisation historics where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of anonymisation historics where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching anonymisation historics
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the anonymisation historics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching anonymisation historics
	 */
	public java.util.List<AnonymisationHistoric> findByGroupId(long groupId);

	/**
	 * Returns a range of all the anonymisation historics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @return the range of matching anonymisation historics
	 */
	public java.util.List<AnonymisationHistoric> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the anonymisation historics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching anonymisation historics
	 */
	public java.util.List<AnonymisationHistoric> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AnonymisationHistoric>
			orderByComparator);

	/**
	 * Returns an ordered range of all the anonymisation historics where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching anonymisation historics
	 */
	public java.util.List<AnonymisationHistoric> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AnonymisationHistoric>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first anonymisation historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	public AnonymisationHistoric findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException;

	/**
	 * Returns the first anonymisation historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	public AnonymisationHistoric fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AnonymisationHistoric>
			orderByComparator);

	/**
	 * Returns the last anonymisation historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	 */
	public AnonymisationHistoric findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException;

	/**
	 * Returns the last anonymisation historic in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	public AnonymisationHistoric fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AnonymisationHistoric>
			orderByComparator);

	/**
	 * Returns the anonymisation historics before and after the current anonymisation historic in the ordered set where groupId = &#63;.
	 *
	 * @param anonymisationHistoricId the primary key of the current anonymisation historic
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	 */
	public AnonymisationHistoric[] findByGroupId_PrevAndNext(
			long anonymisationHistoricId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AnonymisationHistoric> orderByComparator)
		throws NoSuchAnonymisationHistoricException;

	/**
	 * Removes all the anonymisation historics where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of anonymisation historics where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching anonymisation historics
	 */
	public int countByGroupId(long groupId);

	/**
	 * Caches the anonymisation historic in the entity cache if it is enabled.
	 *
	 * @param anonymisationHistoric the anonymisation historic
	 */
	public void cacheResult(AnonymisationHistoric anonymisationHistoric);

	/**
	 * Caches the anonymisation historics in the entity cache if it is enabled.
	 *
	 * @param anonymisationHistorics the anonymisation historics
	 */
	public void cacheResult(
		java.util.List<AnonymisationHistoric> anonymisationHistorics);

	/**
	 * Creates a new anonymisation historic with the primary key. Does not add the anonymisation historic to the database.
	 *
	 * @param anonymisationHistoricId the primary key for the new anonymisation historic
	 * @return the new anonymisation historic
	 */
	public AnonymisationHistoric create(long anonymisationHistoricId);

	/**
	 * Removes the anonymisation historic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param anonymisationHistoricId the primary key of the anonymisation historic
	 * @return the anonymisation historic that was removed
	 * @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	 */
	public AnonymisationHistoric remove(long anonymisationHistoricId)
		throws NoSuchAnonymisationHistoricException;

	public AnonymisationHistoric updateImpl(
		AnonymisationHistoric anonymisationHistoric);

	/**
	 * Returns the anonymisation historic with the primary key or throws a <code>NoSuchAnonymisationHistoricException</code> if it could not be found.
	 *
	 * @param anonymisationHistoricId the primary key of the anonymisation historic
	 * @return the anonymisation historic
	 * @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	 */
	public AnonymisationHistoric findByPrimaryKey(long anonymisationHistoricId)
		throws NoSuchAnonymisationHistoricException;

	/**
	 * Returns the anonymisation historic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param anonymisationHistoricId the primary key of the anonymisation historic
	 * @return the anonymisation historic, or <code>null</code> if a anonymisation historic with the primary key could not be found
	 */
	public AnonymisationHistoric fetchByPrimaryKey(
		long anonymisationHistoricId);

	/**
	 * Returns all the anonymisation historics.
	 *
	 * @return the anonymisation historics
	 */
	public java.util.List<AnonymisationHistoric> findAll();

	/**
	 * Returns a range of all the anonymisation historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @return the range of anonymisation historics
	 */
	public java.util.List<AnonymisationHistoric> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the anonymisation historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of anonymisation historics
	 */
	public java.util.List<AnonymisationHistoric> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AnonymisationHistoric>
			orderByComparator);

	/**
	 * Returns an ordered range of all the anonymisation historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>AnonymisationHistoricModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of anonymisation historics
	 */
	public java.util.List<AnonymisationHistoric> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AnonymisationHistoric>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the anonymisation historics from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of anonymisation historics.
	 *
	 * @return the number of anonymisation historics
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}