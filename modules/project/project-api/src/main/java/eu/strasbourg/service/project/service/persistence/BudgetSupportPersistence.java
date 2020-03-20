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

package eu.strasbourg.service.project.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.project.exception.NoSuchBudgetSupportException;
import eu.strasbourg.service.project.model.BudgetSupport;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the budget support service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see BudgetSupportUtil
 * @generated
 */
@ProviderType
public interface BudgetSupportPersistence
	extends BasePersistence<BudgetSupport> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BudgetSupportUtil} to access the budget support persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, BudgetSupport> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the budget supports where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching budget supports
	 */
	public java.util.List<BudgetSupport> findByUuid(String uuid);

	/**
	 * Returns a range of all the budget supports where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @return the range of matching budget supports
	 */
	public java.util.List<BudgetSupport> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the budget supports where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget supports
	 */
	public java.util.List<BudgetSupport> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns an ordered range of all the budget supports where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget supports
	 */
	public java.util.List<BudgetSupport> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first budget support in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	public BudgetSupport findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Returns the first budget support in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	public BudgetSupport fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns the last budget support in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	public BudgetSupport findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Returns the last budget support in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	public BudgetSupport fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns the budget supports before and after the current budget support in the ordered set where uuid = &#63;.
	 *
	 * @param budgetSupportId the primary key of the current budget support
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget support
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	public BudgetSupport[] findByUuid_PrevAndNext(
			long budgetSupportId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Removes all the budget supports where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of budget supports where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching budget supports
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the budget support where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchBudgetSupportException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	public BudgetSupport findByUUID_G(String uuid, long groupId)
		throws NoSuchBudgetSupportException;

	/**
	 * Returns the budget support where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	public BudgetSupport fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the budget support where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	public BudgetSupport fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache);

	/**
	 * Removes the budget support where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the budget support that was removed
	 */
	public BudgetSupport removeByUUID_G(String uuid, long groupId)
		throws NoSuchBudgetSupportException;

	/**
	 * Returns the number of budget supports where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching budget supports
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the budget supports where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching budget supports
	 */
	public java.util.List<BudgetSupport> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the budget supports where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @return the range of matching budget supports
	 */
	public java.util.List<BudgetSupport> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the budget supports where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget supports
	 */
	public java.util.List<BudgetSupport> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns an ordered range of all the budget supports where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget supports
	 */
	public java.util.List<BudgetSupport> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first budget support in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	public BudgetSupport findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Returns the first budget support in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	public BudgetSupport fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns the last budget support in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	public BudgetSupport findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Returns the last budget support in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	public BudgetSupport fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns the budget supports before and after the current budget support in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param budgetSupportId the primary key of the current budget support
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget support
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	public BudgetSupport[] findByUuid_C_PrevAndNext(
			long budgetSupportId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Removes all the budget supports where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of budget supports where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching budget supports
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the budget supports where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching budget supports
	 */
	public java.util.List<BudgetSupport> findByGroupId(long groupId);

	/**
	 * Returns a range of all the budget supports where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @return the range of matching budget supports
	 */
	public java.util.List<BudgetSupport> findByGroupId(
		long groupId, int start, int end);

	/**
	 * Returns an ordered range of all the budget supports where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget supports
	 */
	public java.util.List<BudgetSupport> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns an ordered range of all the budget supports where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget supports
	 */
	public java.util.List<BudgetSupport> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first budget support in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	public BudgetSupport findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Returns the first budget support in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	public BudgetSupport fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns the last budget support in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	public BudgetSupport findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Returns the last budget support in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	public BudgetSupport fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns the budget supports before and after the current budget support in the ordered set where groupId = &#63;.
	 *
	 * @param budgetSupportId the primary key of the current budget support
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget support
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	public BudgetSupport[] findByGroupId_PrevAndNext(
			long budgetSupportId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Removes all the budget supports where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of budget supports where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching budget supports
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns all the budget supports where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @return the matching budget supports
	 */
	public java.util.List<BudgetSupport> findByBudgetParticipatif(
		long budgetParticipatifId);

	/**
	 * Returns a range of all the budget supports where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @return the range of matching budget supports
	 */
	public java.util.List<BudgetSupport> findByBudgetParticipatif(
		long budgetParticipatifId, int start, int end);

	/**
	 * Returns an ordered range of all the budget supports where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget supports
	 */
	public java.util.List<BudgetSupport> findByBudgetParticipatif(
		long budgetParticipatifId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns an ordered range of all the budget supports where budgetParticipatifId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget supports
	 */
	public java.util.List<BudgetSupport> findByBudgetParticipatif(
		long budgetParticipatifId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first budget support in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	public BudgetSupport findByBudgetParticipatif_First(
			long budgetParticipatifId,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Returns the first budget support in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	public BudgetSupport fetchByBudgetParticipatif_First(
		long budgetParticipatifId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns the last budget support in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	public BudgetSupport findByBudgetParticipatif_Last(
			long budgetParticipatifId,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Returns the last budget support in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	public BudgetSupport fetchByBudgetParticipatif_Last(
		long budgetParticipatifId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns the budget supports before and after the current budget support in the ordered set where budgetParticipatifId = &#63;.
	 *
	 * @param budgetSupportId the primary key of the current budget support
	 * @param budgetParticipatifId the budget participatif ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget support
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	public BudgetSupport[] findByBudgetParticipatif_PrevAndNext(
			long budgetSupportId, long budgetParticipatifId,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Removes all the budget supports where budgetParticipatifId = &#63; from the database.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 */
	public void removeByBudgetParticipatif(long budgetParticipatifId);

	/**
	 * Returns the number of budget supports where budgetParticipatifId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @return the number of matching budget supports
	 */
	public int countByBudgetParticipatif(long budgetParticipatifId);

	/**
	 * Returns all the budget supports where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the matching budget supports
	 */
	public java.util.List<BudgetSupport> findByPublikUserId(
		String publikUserId);

	/**
	 * Returns a range of all the budget supports where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @return the range of matching budget supports
	 */
	public java.util.List<BudgetSupport> findByPublikUserId(
		String publikUserId, int start, int end);

	/**
	 * Returns an ordered range of all the budget supports where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget supports
	 */
	public java.util.List<BudgetSupport> findByPublikUserId(
		String publikUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns an ordered range of all the budget supports where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget supports
	 */
	public java.util.List<BudgetSupport> findByPublikUserId(
		String publikUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first budget support in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	public BudgetSupport findByPublikUserId_First(
			String publikUserId,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Returns the first budget support in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	public BudgetSupport fetchByPublikUserId_First(
		String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns the last budget support in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	public BudgetSupport findByPublikUserId_Last(
			String publikUserId,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Returns the last budget support in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	public BudgetSupport fetchByPublikUserId_Last(
		String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns the budget supports before and after the current budget support in the ordered set where publikUserId = &#63;.
	 *
	 * @param budgetSupportId the primary key of the current budget support
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget support
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	public BudgetSupport[] findByPublikUserId_PrevAndNext(
			long budgetSupportId, String publikUserId,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Removes all the budget supports where publikUserId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 */
	public void removeByPublikUserId(String publikUserId);

	/**
	 * Returns the number of budget supports where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the number of matching budget supports
	 */
	public int countByPublikUserId(String publikUserId);

	/**
	 * Returns all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @return the matching budget supports
	 */
	public java.util.List<BudgetSupport>
		findByBudgetParticipatifIdAndPublikUserId(
			long budgetParticipatifId, String publikUserId);

	/**
	 * Returns a range of all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @return the range of matching budget supports
	 */
	public java.util.List<BudgetSupport>
		findByBudgetParticipatifIdAndPublikUserId(
			long budgetParticipatifId, String publikUserId, int start, int end);

	/**
	 * Returns an ordered range of all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget supports
	 */
	public java.util.List<BudgetSupport>
		findByBudgetParticipatifIdAndPublikUserId(
			long budgetParticipatifId, String publikUserId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator);

	/**
	 * Returns an ordered range of all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching budget supports
	 */
	public java.util.List<BudgetSupport>
		findByBudgetParticipatifIdAndPublikUserId(
			long budgetParticipatifId, String publikUserId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator,
			boolean retrieveFromCache);

	/**
	 * Returns the first budget support in the ordered set where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	public BudgetSupport findByBudgetParticipatifIdAndPublikUserId_First(
			long budgetParticipatifId, String publikUserId,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Returns the first budget support in the ordered set where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	public BudgetSupport fetchByBudgetParticipatifIdAndPublikUserId_First(
		long budgetParticipatifId, String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns the last budget support in the ordered set where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support
	 * @throws NoSuchBudgetSupportException if a matching budget support could not be found
	 */
	public BudgetSupport findByBudgetParticipatifIdAndPublikUserId_Last(
			long budgetParticipatifId, String publikUserId,
			com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
				orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Returns the last budget support in the ordered set where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	 */
	public BudgetSupport fetchByBudgetParticipatifIdAndPublikUserId_Last(
		long budgetParticipatifId, String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns the budget supports before and after the current budget support in the ordered set where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * @param budgetSupportId the primary key of the current budget support
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget support
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	public BudgetSupport[]
			findByBudgetParticipatifIdAndPublikUserId_PrevAndNext(
				long budgetSupportId, long budgetParticipatifId,
				String publikUserId,
				com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
					orderByComparator)
		throws NoSuchBudgetSupportException;

	/**
	 * Removes all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63; from the database.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 */
	public void removeByBudgetParticipatifIdAndPublikUserId(
		long budgetParticipatifId, String publikUserId);

	/**
	 * Returns the number of budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	 *
	 * @param budgetParticipatifId the budget participatif ID
	 * @param publikUserId the publik user ID
	 * @return the number of matching budget supports
	 */
	public int countByBudgetParticipatifIdAndPublikUserId(
		long budgetParticipatifId, String publikUserId);

	/**
	 * Caches the budget support in the entity cache if it is enabled.
	 *
	 * @param budgetSupport the budget support
	 */
	public void cacheResult(BudgetSupport budgetSupport);

	/**
	 * Caches the budget supports in the entity cache if it is enabled.
	 *
	 * @param budgetSupports the budget supports
	 */
	public void cacheResult(java.util.List<BudgetSupport> budgetSupports);

	/**
	 * Creates a new budget support with the primary key. Does not add the budget support to the database.
	 *
	 * @param budgetSupportId the primary key for the new budget support
	 * @return the new budget support
	 */
	public BudgetSupport create(long budgetSupportId);

	/**
	 * Removes the budget support with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param budgetSupportId the primary key of the budget support
	 * @return the budget support that was removed
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	public BudgetSupport remove(long budgetSupportId)
		throws NoSuchBudgetSupportException;

	public BudgetSupport updateImpl(BudgetSupport budgetSupport);

	/**
	 * Returns the budget support with the primary key or throws a <code>NoSuchBudgetSupportException</code> if it could not be found.
	 *
	 * @param budgetSupportId the primary key of the budget support
	 * @return the budget support
	 * @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	 */
	public BudgetSupport findByPrimaryKey(long budgetSupportId)
		throws NoSuchBudgetSupportException;

	/**
	 * Returns the budget support with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param budgetSupportId the primary key of the budget support
	 * @return the budget support, or <code>null</code> if a budget support with the primary key could not be found
	 */
	public BudgetSupport fetchByPrimaryKey(long budgetSupportId);

	/**
	 * Returns all the budget supports.
	 *
	 * @return the budget supports
	 */
	public java.util.List<BudgetSupport> findAll();

	/**
	 * Returns a range of all the budget supports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @return the range of budget supports
	 */
	public java.util.List<BudgetSupport> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the budget supports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of budget supports
	 */
	public java.util.List<BudgetSupport> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator);

	/**
	 * Returns an ordered range of all the budget supports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>BudgetSupportModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget supports
	 * @param end the upper bound of the range of budget supports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of budget supports
	 */
	public java.util.List<BudgetSupport> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetSupport>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the budget supports from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of budget supports.
	 *
	 * @return the number of budget supports
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}