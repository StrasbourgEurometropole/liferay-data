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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.project.model.BudgetParticipatif;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the budget participatif service. This utility wraps <code>eu.strasbourg.service.project.service.persistence.impl.BudgetParticipatifPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see BudgetParticipatifPersistence
 * @generated
 */
public class BudgetParticipatifUtil {

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
	public static void clearCache(BudgetParticipatif budgetParticipatif) {
		getPersistence().clearCache(budgetParticipatif);
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
	public static Map<Serializable, BudgetParticipatif> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<BudgetParticipatif> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BudgetParticipatif> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BudgetParticipatif> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static BudgetParticipatif update(
		BudgetParticipatif budgetParticipatif) {

		return getPersistence().update(budgetParticipatif);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static BudgetParticipatif update(
		BudgetParticipatif budgetParticipatif, ServiceContext serviceContext) {

		return getPersistence().update(budgetParticipatif, serviceContext);
	}

	/**
	 * Returns all the budget participatifs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the budget participatifs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first budget participatif in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif findByUuid_First(
			String uuid,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first budget participatif in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByUuid_First(
		String uuid, OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last budget participatif in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif findByUuid_Last(
			String uuid,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last budget participatif in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByUuid_Last(
		String uuid, OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the budget participatifs before and after the current budget participatif in the ordered set where uuid = &#63;.
	 *
	 * @param budgetParticipatifId the primary key of the current budget participatif
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget participatif
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	public static BudgetParticipatif[] findByUuid_PrevAndNext(
			long budgetParticipatifId, String uuid,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByUuid_PrevAndNext(
			budgetParticipatifId, uuid, orderByComparator);
	}

	/**
	 * Removes all the budget participatifs where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of budget participatifs where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching budget participatifs
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the budget participatif where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchBudgetParticipatifException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif findByUUID_G(String uuid, long groupId)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the budget participatif where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the budget participatif where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the budget participatif where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the budget participatif that was removed
	 */
	public static BudgetParticipatif removeByUUID_G(String uuid, long groupId)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of budget participatifs where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching budget participatifs
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the budget participatifs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the budget participatifs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first budget participatif in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first budget participatif in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last budget participatif in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last budget participatif in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

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
	public static BudgetParticipatif[] findByUuid_C_PrevAndNext(
			long budgetParticipatifId, String uuid, long companyId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByUuid_C_PrevAndNext(
			budgetParticipatifId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the budget participatifs where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of budget participatifs where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching budget participatifs
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the budget participatifs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the budget participatifs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first budget participatif in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif findByGroupId_First(
			long groupId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first budget participatif in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByGroupId_First(
		long groupId, OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last budget participatif in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif findByGroupId_Last(
			long groupId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last budget participatif in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByGroupId_Last(
		long groupId, OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the budget participatifs before and after the current budget participatif in the ordered set where groupId = &#63;.
	 *
	 * @param budgetParticipatifId the primary key of the current budget participatif
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget participatif
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	public static BudgetParticipatif[] findByGroupId_PrevAndNext(
			long budgetParticipatifId, long groupId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByGroupId_PrevAndNext(
			budgetParticipatifId, groupId, orderByComparator);
	}

	/**
	 * Removes all the budget participatifs where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of budget participatifs where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching budget participatifs
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the budget participatifs where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @return the matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByStatusAndGroupId(
		int status, long groupId) {

		return getPersistence().findByStatusAndGroupId(status, groupId);
	}

	/**
	 * Returns a range of all the budget participatifs where status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByStatusAndGroupId(
		int status, long groupId, int start, int end) {

		return getPersistence().findByStatusAndGroupId(
			status, groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByStatusAndGroupId(
		int status, long groupId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().findByStatusAndGroupId(
			status, groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByStatusAndGroupId(
		int status, long groupId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByStatusAndGroupId(
			status, groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first budget participatif in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif findByStatusAndGroupId_First(
			int status, long groupId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByStatusAndGroupId_First(
			status, groupId, orderByComparator);
	}

	/**
	 * Returns the first budget participatif in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByStatusAndGroupId_First(
		int status, long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().fetchByStatusAndGroupId_First(
			status, groupId, orderByComparator);
	}

	/**
	 * Returns the last budget participatif in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif findByStatusAndGroupId_Last(
			int status, long groupId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByStatusAndGroupId_Last(
			status, groupId, orderByComparator);
	}

	/**
	 * Returns the last budget participatif in the ordered set where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByStatusAndGroupId_Last(
		int status, long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().fetchByStatusAndGroupId_Last(
			status, groupId, orderByComparator);
	}

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
	public static BudgetParticipatif[] findByStatusAndGroupId_PrevAndNext(
			long budgetParticipatifId, int status, long groupId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByStatusAndGroupId_PrevAndNext(
			budgetParticipatifId, status, groupId, orderByComparator);
	}

	/**
	 * Removes all the budget participatifs where status = &#63; and groupId = &#63; from the database.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 */
	public static void removeByStatusAndGroupId(int status, long groupId) {
		getPersistence().removeByStatusAndGroupId(status, groupId);
	}

	/**
	 * Returns the number of budget participatifs where status = &#63; and groupId = &#63;.
	 *
	 * @param status the status
	 * @param groupId the group ID
	 * @return the number of matching budget participatifs
	 */
	public static int countByStatusAndGroupId(int status, long groupId) {
		return getPersistence().countByStatusAndGroupId(status, groupId);
	}

	/**
	 * Returns all the budget participatifs where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByPublikId(String publikId) {
		return getPersistence().findByPublikId(publikId);
	}

	/**
	 * Returns a range of all the budget participatifs where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByPublikId(
		String publikId, int start, int end) {

		return getPersistence().findByPublikId(publikId, start, end);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByPublikId(
		String publikId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().findByPublikId(
			publikId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByPublikId(
		String publikId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPublikId(
			publikId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first budget participatif in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif findByPublikId_First(
			String publikId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByPublikId_First(
			publikId, orderByComparator);
	}

	/**
	 * Returns the first budget participatif in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByPublikId_First(
		String publikId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().fetchByPublikId_First(
			publikId, orderByComparator);
	}

	/**
	 * Returns the last budget participatif in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif findByPublikId_Last(
			String publikId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByPublikId_Last(
			publikId, orderByComparator);
	}

	/**
	 * Returns the last budget participatif in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByPublikId_Last(
		String publikId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().fetchByPublikId_Last(
			publikId, orderByComparator);
	}

	/**
	 * Returns the budget participatifs before and after the current budget participatif in the ordered set where publikId = &#63;.
	 *
	 * @param budgetParticipatifId the primary key of the current budget participatif
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget participatif
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	public static BudgetParticipatif[] findByPublikId_PrevAndNext(
			long budgetParticipatifId, String publikId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByPublikId_PrevAndNext(
			budgetParticipatifId, publikId, orderByComparator);
	}

	/**
	 * Removes all the budget participatifs where publikId = &#63; from the database.
	 *
	 * @param publikId the publik ID
	 */
	public static void removeByPublikId(String publikId) {
		getPersistence().removeByPublikId(publikId);
	}

	/**
	 * Returns the number of budget participatifs where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the number of matching budget participatifs
	 */
	public static int countByPublikId(String publikId) {
		return getPersistence().countByPublikId(publikId);
	}

	/**
	 * Returns all the budget participatifs where budgetPhaseId = &#63;.
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @return the matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByBudgetPhaseId(
		long budgetPhaseId) {

		return getPersistence().findByBudgetPhaseId(budgetPhaseId);
	}

	/**
	 * Returns a range of all the budget participatifs where budgetPhaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByBudgetPhaseId(
		long budgetPhaseId, int start, int end) {

		return getPersistence().findByBudgetPhaseId(budgetPhaseId, start, end);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where budgetPhaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByBudgetPhaseId(
		long budgetPhaseId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().findByBudgetPhaseId(
			budgetPhaseId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where budgetPhaseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByBudgetPhaseId(
		long budgetPhaseId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByBudgetPhaseId(
			budgetPhaseId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first budget participatif in the ordered set where budgetPhaseId = &#63;.
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif findByBudgetPhaseId_First(
			long budgetPhaseId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByBudgetPhaseId_First(
			budgetPhaseId, orderByComparator);
	}

	/**
	 * Returns the first budget participatif in the ordered set where budgetPhaseId = &#63;.
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByBudgetPhaseId_First(
		long budgetPhaseId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().fetchByBudgetPhaseId_First(
			budgetPhaseId, orderByComparator);
	}

	/**
	 * Returns the last budget participatif in the ordered set where budgetPhaseId = &#63;.
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif findByBudgetPhaseId_Last(
			long budgetPhaseId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByBudgetPhaseId_Last(
			budgetPhaseId, orderByComparator);
	}

	/**
	 * Returns the last budget participatif in the ordered set where budgetPhaseId = &#63;.
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByBudgetPhaseId_Last(
		long budgetPhaseId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().fetchByBudgetPhaseId_Last(
			budgetPhaseId, orderByComparator);
	}

	/**
	 * Returns the budget participatifs before and after the current budget participatif in the ordered set where budgetPhaseId = &#63;.
	 *
	 * @param budgetParticipatifId the primary key of the current budget participatif
	 * @param budgetPhaseId the budget phase ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget participatif
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	public static BudgetParticipatif[] findByBudgetPhaseId_PrevAndNext(
			long budgetParticipatifId, long budgetPhaseId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByBudgetPhaseId_PrevAndNext(
			budgetParticipatifId, budgetPhaseId, orderByComparator);
	}

	/**
	 * Removes all the budget participatifs where budgetPhaseId = &#63; from the database.
	 *
	 * @param budgetPhaseId the budget phase ID
	 */
	public static void removeByBudgetPhaseId(long budgetPhaseId) {
		getPersistence().removeByBudgetPhaseId(budgetPhaseId);
	}

	/**
	 * Returns the number of budget participatifs where budgetPhaseId = &#63;.
	 *
	 * @param budgetPhaseId the budget phase ID
	 * @return the number of matching budget participatifs
	 */
	public static int countByBudgetPhaseId(long budgetPhaseId) {
		return getPersistence().countByBudgetPhaseId(budgetPhaseId);
	}

	/**
	 * Returns all the budget participatifs where parentId = &#63;.
	 *
	 * @param parentId the parent ID
	 * @return the matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByParentId(long parentId) {
		return getPersistence().findByParentId(parentId);
	}

	/**
	 * Returns a range of all the budget participatifs where parentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param parentId the parent ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByParentId(
		long parentId, int start, int end) {

		return getPersistence().findByParentId(parentId, start, end);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where parentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param parentId the parent ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByParentId(
		long parentId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().findByParentId(
			parentId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where parentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param parentId the parent ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByParentId(
		long parentId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByParentId(
			parentId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first budget participatif in the ordered set where parentId = &#63;.
	 *
	 * @param parentId the parent ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif findByParentId_First(
			long parentId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByParentId_First(
			parentId, orderByComparator);
	}

	/**
	 * Returns the first budget participatif in the ordered set where parentId = &#63;.
	 *
	 * @param parentId the parent ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByParentId_First(
		long parentId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().fetchByParentId_First(
			parentId, orderByComparator);
	}

	/**
	 * Returns the last budget participatif in the ordered set where parentId = &#63;.
	 *
	 * @param parentId the parent ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif
	 * @throws NoSuchBudgetParticipatifException if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif findByParentId_Last(
			long parentId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByParentId_Last(
			parentId, orderByComparator);
	}

	/**
	 * Returns the last budget participatif in the ordered set where parentId = &#63;.
	 *
	 * @param parentId the parent ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByParentId_Last(
		long parentId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().fetchByParentId_Last(
			parentId, orderByComparator);
	}

	/**
	 * Returns the budget participatifs before and after the current budget participatif in the ordered set where parentId = &#63;.
	 *
	 * @param budgetParticipatifId the primary key of the current budget participatif
	 * @param parentId the parent ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next budget participatif
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	public static BudgetParticipatif[] findByParentId_PrevAndNext(
			long budgetParticipatifId, long parentId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByParentId_PrevAndNext(
			budgetParticipatifId, parentId, orderByComparator);
	}

	/**
	 * Removes all the budget participatifs where parentId = &#63; from the database.
	 *
	 * @param parentId the parent ID
	 */
	public static void removeByParentId(long parentId) {
		getPersistence().removeByParentId(parentId);
	}

	/**
	 * Returns the number of budget participatifs where parentId = &#63;.
	 *
	 * @param parentId the parent ID
	 * @return the number of matching budget participatifs
	 */
	public static int countByParentId(long parentId) {
		return getPersistence().countByParentId(parentId);
	}

	/**
	 * Returns all the budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 * @return the matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByisCrushAndPublished(
		boolean isCrush, int status, long groupId) {

		return getPersistence().findByisCrushAndPublished(
			isCrush, status, groupId);
	}

	/**
	 * Returns a range of all the budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByisCrushAndPublished(
		boolean isCrush, int status, long groupId, int start, int end) {

		return getPersistence().findByisCrushAndPublished(
			isCrush, status, groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
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
	public static List<BudgetParticipatif> findByisCrushAndPublished(
		boolean isCrush, int status, long groupId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().findByisCrushAndPublished(
			isCrush, status, groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching budget participatifs
	 */
	public static List<BudgetParticipatif> findByisCrushAndPublished(
		boolean isCrush, int status, long groupId, int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByisCrushAndPublished(
			isCrush, status, groupId, start, end, orderByComparator,
			useFinderCache);
	}

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
	public static BudgetParticipatif findByisCrushAndPublished_First(
			boolean isCrush, int status, long groupId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByisCrushAndPublished_First(
			isCrush, status, groupId, orderByComparator);
	}

	/**
	 * Returns the first budget participatif in the ordered set where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByisCrushAndPublished_First(
		boolean isCrush, int status, long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().fetchByisCrushAndPublished_First(
			isCrush, status, groupId, orderByComparator);
	}

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
	public static BudgetParticipatif findByisCrushAndPublished_Last(
			boolean isCrush, int status, long groupId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByisCrushAndPublished_Last(
			isCrush, status, groupId, orderByComparator);
	}

	/**
	 * Returns the last budget participatif in the ordered set where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching budget participatif, or <code>null</code> if a matching budget participatif could not be found
	 */
	public static BudgetParticipatif fetchByisCrushAndPublished_Last(
		boolean isCrush, int status, long groupId,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().fetchByisCrushAndPublished_Last(
			isCrush, status, groupId, orderByComparator);
	}

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
	public static BudgetParticipatif[] findByisCrushAndPublished_PrevAndNext(
			long budgetParticipatifId, boolean isCrush, int status,
			long groupId,
			OrderByComparator<BudgetParticipatif> orderByComparator)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByisCrushAndPublished_PrevAndNext(
			budgetParticipatifId, isCrush, status, groupId, orderByComparator);
	}

	/**
	 * Removes all the budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63; from the database.
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 */
	public static void removeByisCrushAndPublished(
		boolean isCrush, int status, long groupId) {

		getPersistence().removeByisCrushAndPublished(isCrush, status, groupId);
	}

	/**
	 * Returns the number of budget participatifs where isCrush = &#63; and status = &#63; and groupId = &#63;.
	 *
	 * @param isCrush the is crush
	 * @param status the status
	 * @param groupId the group ID
	 * @return the number of matching budget participatifs
	 */
	public static int countByisCrushAndPublished(
		boolean isCrush, int status, long groupId) {

		return getPersistence().countByisCrushAndPublished(
			isCrush, status, groupId);
	}

	/**
	 * Caches the budget participatif in the entity cache if it is enabled.
	 *
	 * @param budgetParticipatif the budget participatif
	 */
	public static void cacheResult(BudgetParticipatif budgetParticipatif) {
		getPersistence().cacheResult(budgetParticipatif);
	}

	/**
	 * Caches the budget participatifs in the entity cache if it is enabled.
	 *
	 * @param budgetParticipatifs the budget participatifs
	 */
	public static void cacheResult(
		List<BudgetParticipatif> budgetParticipatifs) {

		getPersistence().cacheResult(budgetParticipatifs);
	}

	/**
	 * Creates a new budget participatif with the primary key. Does not add the budget participatif to the database.
	 *
	 * @param budgetParticipatifId the primary key for the new budget participatif
	 * @return the new budget participatif
	 */
	public static BudgetParticipatif create(long budgetParticipatifId) {
		return getPersistence().create(budgetParticipatifId);
	}

	/**
	 * Removes the budget participatif with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param budgetParticipatifId the primary key of the budget participatif
	 * @return the budget participatif that was removed
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	public static BudgetParticipatif remove(long budgetParticipatifId)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().remove(budgetParticipatifId);
	}

	public static BudgetParticipatif updateImpl(
		BudgetParticipatif budgetParticipatif) {

		return getPersistence().updateImpl(budgetParticipatif);
	}

	/**
	 * Returns the budget participatif with the primary key or throws a <code>NoSuchBudgetParticipatifException</code> if it could not be found.
	 *
	 * @param budgetParticipatifId the primary key of the budget participatif
	 * @return the budget participatif
	 * @throws NoSuchBudgetParticipatifException if a budget participatif with the primary key could not be found
	 */
	public static BudgetParticipatif findByPrimaryKey(long budgetParticipatifId)
		throws eu.strasbourg.service.project.exception.
			NoSuchBudgetParticipatifException {

		return getPersistence().findByPrimaryKey(budgetParticipatifId);
	}

	/**
	 * Returns the budget participatif with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param budgetParticipatifId the primary key of the budget participatif
	 * @return the budget participatif, or <code>null</code> if a budget participatif with the primary key could not be found
	 */
	public static BudgetParticipatif fetchByPrimaryKey(
		long budgetParticipatifId) {

		return getPersistence().fetchByPrimaryKey(budgetParticipatifId);
	}

	/**
	 * Returns all the budget participatifs.
	 *
	 * @return the budget participatifs
	 */
	public static List<BudgetParticipatif> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the budget participatifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @return the range of budget participatifs
	 */
	public static List<BudgetParticipatif> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the budget participatifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of budget participatifs
	 */
	public static List<BudgetParticipatif> findAll(
		int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the budget participatifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BudgetParticipatifModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of budget participatifs
	 * @param end the upper bound of the range of budget participatifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of budget participatifs
	 */
	public static List<BudgetParticipatif> findAll(
		int start, int end,
		OrderByComparator<BudgetParticipatif> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the budget participatifs from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of budget participatifs.
	 *
	 * @return the number of budget participatifs
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static BudgetParticipatifPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<BudgetParticipatifPersistence, BudgetParticipatifPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			BudgetParticipatifPersistence.class);

		ServiceTracker
			<BudgetParticipatifPersistence, BudgetParticipatifPersistence>
				serviceTracker =
					new ServiceTracker
						<BudgetParticipatifPersistence,
						 BudgetParticipatifPersistence>(
							 bundle.getBundleContext(),
							 BudgetParticipatifPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}