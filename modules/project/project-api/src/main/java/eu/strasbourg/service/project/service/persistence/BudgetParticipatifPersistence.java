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

import eu.strasbourg.service.project.exception.NoSuchBudgetParticipatifException;
import eu.strasbourg.service.project.model.BudgetParticipatif;

/**
 * The persistence interface for the budget participatif service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.persistence.impl.BudgetParticipatifPersistenceImpl
 * @see BudgetParticipatifUtil
 * @generated
 */
@ProviderType
public interface BudgetParticipatifPersistence extends BasePersistence<BudgetParticipatif> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BudgetParticipatifUtil} to access the budget participatif persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the budget participatifs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the budget participatifs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @return the range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByUuid(
		java.lang.String uuid, int start, int end);

	/**
	* Returns an ordered range of all the budget participatifs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns an ordered range of all the budget participatifs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first budget participatif in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget participatif
	* @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	*/
	public BudgetParticipatif findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the first budget participatif in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	public BudgetParticipatif fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns the last budget participatif in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget participatif
	* @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	*/
	public BudgetParticipatif findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the last budget participatif in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	public BudgetParticipatif fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns the budget participatifs before and after the current budget participatif in the ordered set where uuid = &#63;.
	*
	* @param budgetParticipatifId the primary key of the current budget participatif
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next budget participatif
	* @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	*/
	public BudgetParticipatif[] findByUuid_PrevAndNext(
		long budgetParticipatifId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Removes all the budget participatifs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of budget participatifs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching budget participatifs
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the budget participatif where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBudgetParticipatifException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching budget participatif
	* @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	*/
	public BudgetParticipatif findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the budget participatif where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	public BudgetParticipatif fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the budget participatif where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	public BudgetParticipatif fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache);

	/**
	* Removes the budget participatif where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the budget participatif that was removed
	*/
	public BudgetParticipatif removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the number of budget participatifs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching budget participatifs
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the budget participatifs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByUuid_C(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of all the budget participatifs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @return the range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

	/**
	* Returns an ordered range of all the budget participatifs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns an ordered range of all the budget participatifs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first budget participatif in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget participatif
	* @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	*/
	public BudgetParticipatif findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the first budget participatif in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	public BudgetParticipatif fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns the last budget participatif in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget participatif
	* @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	*/
	public BudgetParticipatif findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the last budget participatif in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	public BudgetParticipatif fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns the budget participatifs before and after the current budget participatif in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param budgetParticipatifId the primary key of the current budget participatif
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next budget participatif
	* @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	*/
	public BudgetParticipatif[] findByUuid_C_PrevAndNext(
		long budgetParticipatifId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Removes all the budget participatifs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of budget participatifs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching budget participatifs
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the budget participatifs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByGroupId(long groupId);

	/**
	* Returns a range of all the budget participatifs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @return the range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the budget participatifs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns an ordered range of all the budget participatifs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first budget participatif in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget participatif
	* @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	*/
	public BudgetParticipatif findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the first budget participatif in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	public BudgetParticipatif fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns the last budget participatif in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget participatif
	* @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	*/
	public BudgetParticipatif findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the last budget participatif in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	public BudgetParticipatif fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns the budget participatifs before and after the current budget participatif in the ordered set where groupId = &#63;.
	*
	* @param budgetParticipatifId the primary key of the current budget participatif
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next budget participatif
	* @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	*/
	public BudgetParticipatif[] findByGroupId_PrevAndNext(
		long budgetParticipatifId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Removes all the budget participatifs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of budget participatifs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching budget participatifs
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the budget participatifs where status = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param groupId the group ID
	* @return the matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByStatusAndGroupId(
		int status, long groupId);

	/**
	* Returns a range of all the budget participatifs where status = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param groupId the group ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @return the range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByStatusAndGroupId(
		int status, long groupId, int start, int end);

	/**
	* Returns an ordered range of all the budget participatifs where status = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param groupId the group ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByStatusAndGroupId(
		int status, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns an ordered range of all the budget participatifs where status = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param groupId the group ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByStatusAndGroupId(
		int status, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first budget participatif in the ordered set where status = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget participatif
	* @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	*/
	public BudgetParticipatif findByStatusAndGroupId_First(int status,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the first budget participatif in the ordered set where status = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	public BudgetParticipatif fetchByStatusAndGroupId_First(int status,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns the last budget participatif in the ordered set where status = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget participatif
	* @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	*/
	public BudgetParticipatif findByStatusAndGroupId_Last(int status,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the last budget participatif in the ordered set where status = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	public BudgetParticipatif fetchByStatusAndGroupId_Last(int status,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns the budget participatifs before and after the current budget participatif in the ordered set where status = &#63; and groupId = &#63;.
	*
	* @param budgetParticipatifId the primary key of the current budget participatif
	* @param status the status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next budget participatif
	* @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	*/
	public BudgetParticipatif[] findByStatusAndGroupId_PrevAndNext(
		long budgetParticipatifId, int status, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Removes all the budget participatifs where status = &#63; and groupId = &#63; from the database.
	*
	* @param status the status
	* @param groupId the group ID
	*/
	public void removeByStatusAndGroupId(int status, long groupId);

	/**
	* Returns the number of budget participatifs where status = &#63; and groupId = &#63;.
	*
	* @param status the status
	* @param groupId the group ID
	* @return the number of matching budget participatifs
	*/
	public int countByStatusAndGroupId(int status, long groupId);

	/**
	* Returns all the budget participatifs where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @return the matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByPublikId(
		java.lang.String publikId);

	/**
	* Returns a range of all the budget participatifs where publikId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikId the publik ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @return the range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByPublikId(
		java.lang.String publikId, int start, int end);

	/**
	* Returns an ordered range of all the budget participatifs where publikId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikId the publik ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByPublikId(
		java.lang.String publikId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns an ordered range of all the budget participatifs where publikId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikId the publik ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByPublikId(
		java.lang.String publikId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first budget participatif in the ordered set where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget participatif
	* @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	*/
	public BudgetParticipatif findByPublikId_First(java.lang.String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the first budget participatif in the ordered set where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	public BudgetParticipatif fetchByPublikId_First(java.lang.String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns the last budget participatif in the ordered set where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget participatif
	* @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	*/
	public BudgetParticipatif findByPublikId_Last(java.lang.String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the last budget participatif in the ordered set where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	public BudgetParticipatif fetchByPublikId_Last(java.lang.String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns the budget participatifs before and after the current budget participatif in the ordered set where publikId = &#63;.
	*
	* @param budgetParticipatifId the primary key of the current budget participatif
	* @param publikId the publik ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next budget participatif
	* @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	*/
	public BudgetParticipatif[] findByPublikId_PrevAndNext(
		long budgetParticipatifId, java.lang.String publikId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Removes all the budget participatifs where publikId = &#63; from the database.
	*
	* @param publikId the publik ID
	*/
	public void removeByPublikId(java.lang.String publikId);

	/**
	* Returns the number of budget participatifs where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @return the number of matching budget participatifs
	*/
	public int countByPublikId(java.lang.String publikId);

	/**
	* Returns all the budget participatifs where budgetPhaseId = &#63;.
	*
	* @param budgetPhaseId the budget phase ID
	* @return the matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByBudgetPhaseId(
		long budgetPhaseId);

	/**
	* Returns a range of all the budget participatifs where budgetPhaseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param budgetPhaseId the budget phase ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @return the range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByBudgetPhaseId(
		long budgetPhaseId, int start, int end);

	/**
	* Returns an ordered range of all the budget participatifs where budgetPhaseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param budgetPhaseId the budget phase ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByBudgetPhaseId(
		long budgetPhaseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns an ordered range of all the budget participatifs where budgetPhaseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param budgetPhaseId the budget phase ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByBudgetPhaseId(
		long budgetPhaseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first budget participatif in the ordered set where budgetPhaseId = &#63;.
	*
	* @param budgetPhaseId the budget phase ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget participatif
	* @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	*/
	public BudgetParticipatif findByBudgetPhaseId_First(long budgetPhaseId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the first budget participatif in the ordered set where budgetPhaseId = &#63;.
	*
	* @param budgetPhaseId the budget phase ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	public BudgetParticipatif fetchByBudgetPhaseId_First(long budgetPhaseId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns the last budget participatif in the ordered set where budgetPhaseId = &#63;.
	*
	* @param budgetPhaseId the budget phase ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget participatif
	* @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	*/
	public BudgetParticipatif findByBudgetPhaseId_Last(long budgetPhaseId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the last budget participatif in the ordered set where budgetPhaseId = &#63;.
	*
	* @param budgetPhaseId the budget phase ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	public BudgetParticipatif fetchByBudgetPhaseId_Last(long budgetPhaseId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns the budget participatifs before and after the current budget participatif in the ordered set where budgetPhaseId = &#63;.
	*
	* @param budgetParticipatifId the primary key of the current budget participatif
	* @param budgetPhaseId the budget phase ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next budget participatif
	* @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	*/
	public BudgetParticipatif[] findByBudgetPhaseId_PrevAndNext(
		long budgetParticipatifId, long budgetPhaseId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Removes all the budget participatifs where budgetPhaseId = &#63; from the database.
	*
	* @param budgetPhaseId the budget phase ID
	*/
	public void removeByBudgetPhaseId(long budgetPhaseId);

	/**
	* Returns the number of budget participatifs where budgetPhaseId = &#63;.
	*
	* @param budgetPhaseId the budget phase ID
	* @return the number of matching budget participatifs
	*/
	public int countByBudgetPhaseId(long budgetPhaseId);

	/**
	* Returns all the budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63;.
	*
	* @param isCrush the is crush
	* @param status the status
	* @param groupId the group ID
	* @return the matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByisCrushAndPublished(
		boolean isCrush, int status, long groupId);

	/**
	* Returns a range of all the budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param isCrush the is crush
	* @param status the status
	* @param groupId the group ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @return the range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByisCrushAndPublished(
		boolean isCrush, int status, long groupId, int start, int end);

	/**
	* Returns an ordered range of all the budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param isCrush the is crush
	* @param status the status
	* @param groupId the group ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByisCrushAndPublished(
		boolean isCrush, int status, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns an ordered range of all the budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param isCrush the is crush
	* @param status the status
	* @param groupId the group ID
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findByisCrushAndPublished(
		boolean isCrush, int status, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first budget participatif in the ordered set where isCrush = &#63; and status = &#63; and groupId = &#63;.
	*
	* @param isCrush the is crush
	* @param status the status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget participatif
	* @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	*/
	public BudgetParticipatif findByisCrushAndPublished_First(boolean isCrush,
		int status, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the first budget participatif in the ordered set where isCrush = &#63; and status = &#63; and groupId = &#63;.
	*
	* @param isCrush the is crush
	* @param status the status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	public BudgetParticipatif fetchByisCrushAndPublished_First(
		boolean isCrush, int status, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns the last budget participatif in the ordered set where isCrush = &#63; and status = &#63; and groupId = &#63;.
	*
	* @param isCrush the is crush
	* @param status the status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget participatif
	* @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	*/
	public BudgetParticipatif findByisCrushAndPublished_Last(boolean isCrush,
		int status, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the last budget participatif in the ordered set where isCrush = &#63; and status = &#63; and groupId = &#63;.
	*
	* @param isCrush the is crush
	* @param status the status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	*/
	public BudgetParticipatif fetchByisCrushAndPublished_Last(boolean isCrush,
		int status, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns the budget participatifs before and after the current budget participatif in the ordered set where isCrush = &#63; and status = &#63; and groupId = &#63;.
	*
	* @param budgetParticipatifId the primary key of the current budget participatif
	* @param isCrush the is crush
	* @param status the status
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next budget participatif
	* @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	*/
	public BudgetParticipatif[] findByisCrushAndPublished_PrevAndNext(
		long budgetParticipatifId, boolean isCrush, int status, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator)
		throws NoSuchBudgetParticipatifException;

	/**
	* Removes all the budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63; from the database.
	*
	* @param isCrush the is crush
	* @param status the status
	* @param groupId the group ID
	*/
	public void removeByisCrushAndPublished(boolean isCrush, int status,
		long groupId);

	/**
	* Returns the number of budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63;.
	*
	* @param isCrush the is crush
	* @param status the status
	* @param groupId the group ID
	* @return the number of matching budget participatifs
	*/
	public int countByisCrushAndPublished(boolean isCrush, int status,
		long groupId);

	/**
	* Caches the budget participatif in the entity cache if it is enabled.
	*
	* @param budgetParticipatif the budget participatif
	*/
	public void cacheResult(BudgetParticipatif budgetParticipatif);

	/**
	* Caches the budget participatifs in the entity cache if it is enabled.
	*
	* @param budgetParticipatifs the budget participatifs
	*/
	public void cacheResult(
		java.util.List<BudgetParticipatif> budgetParticipatifs);

	/**
	* Creates a new budget participatif with the primary key. Does not add the budget participatif to the database.
	*
	* @param budgetParticipatifId the primary key for the new budget participatif
	* @return the new budget participatif
	*/
	public BudgetParticipatif create(long budgetParticipatifId);

	/**
	* Removes the budget participatif with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param budgetParticipatifId the primary key of the budget participatif
	* @return the budget participatif that was removed
	* @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	*/
	public BudgetParticipatif remove(long budgetParticipatifId)
		throws NoSuchBudgetParticipatifException;

	public BudgetParticipatif updateImpl(BudgetParticipatif budgetParticipatif);

	/**
	* Returns the budget participatif with the primary key or throws a {@link NoSuchBudgetParticipatifException} if it could not be found.
	*
	* @param budgetParticipatifId the primary key of the budget participatif
	* @return the budget participatif
	* @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	*/
	public BudgetParticipatif findByPrimaryKey(long budgetParticipatifId)
		throws NoSuchBudgetParticipatifException;

	/**
	* Returns the budget participatif with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param budgetParticipatifId the primary key of the budget participatif
	* @return the budget participatif, or <code>null</code> if a budget participatif with the primary key could not be found
	*/
	public BudgetParticipatif fetchByPrimaryKey(long budgetParticipatifId);

	@Override
	public java.util.Map<java.io.Serializable, BudgetParticipatif> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the budget participatifs.
	*
	* @return the budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findAll();

	/**
	* Returns a range of all the budget participatifs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @return the range of budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findAll(int start, int end);

	/**
	* Returns an ordered range of all the budget participatifs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator);

	/**
	* Returns an ordered range of all the budget participatifs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetParticipatifModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of budget participatifs
	* @param end the upper bound of the range of budget participatifs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of budget participatifs
	*/
	public java.util.List<BudgetParticipatif> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the budget participatifs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of budget participatifs.
	*
	* @return the number of budget participatifs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}