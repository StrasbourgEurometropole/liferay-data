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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.project.model.BudgetSupport;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the budget support service. This utility wraps {@link eu.strasbourg.service.project.service.persistence.impl.BudgetSupportPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see BudgetSupportPersistence
 * @see eu.strasbourg.service.project.service.persistence.impl.BudgetSupportPersistenceImpl
 * @generated
 */
@ProviderType
public class BudgetSupportUtil {
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
	public static void clearCache(BudgetSupport budgetSupport) {
		getPersistence().clearCache(budgetSupport);
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
	public static List<BudgetSupport> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BudgetSupport> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BudgetSupport> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static BudgetSupport update(BudgetSupport budgetSupport) {
		return getPersistence().update(budgetSupport);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static BudgetSupport update(BudgetSupport budgetSupport,
		ServiceContext serviceContext) {
		return getPersistence().update(budgetSupport, serviceContext);
	}

	/**
	* Returns all the budget supports where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching budget supports
	*/
	public static List<BudgetSupport> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the budget supports where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @return the range of matching budget supports
	*/
	public static List<BudgetSupport> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the budget supports where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching budget supports
	*/
	public static List<BudgetSupport> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the budget supports where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching budget supports
	*/
	public static List<BudgetSupport> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<BudgetSupport> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first budget support in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget support
	* @throws NoSuchBudgetSupportException if a matching budget support could not be found
	*/
	public static BudgetSupport findByUuid_First(java.lang.String uuid,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first budget support in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	*/
	public static BudgetSupport fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last budget support in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget support
	* @throws NoSuchBudgetSupportException if a matching budget support could not be found
	*/
	public static BudgetSupport findByUuid_Last(java.lang.String uuid,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last budget support in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	*/
	public static BudgetSupport fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the budget supports before and after the current budget support in the ordered set where uuid = &#63;.
	*
	* @param budgetSupportId the primary key of the current budget support
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next budget support
	* @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	*/
	public static BudgetSupport[] findByUuid_PrevAndNext(long budgetSupportId,
		java.lang.String uuid,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence()
				   .findByUuid_PrevAndNext(budgetSupportId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the budget supports where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of budget supports where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching budget supports
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the budget support where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchBudgetSupportException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching budget support
	* @throws NoSuchBudgetSupportException if a matching budget support could not be found
	*/
	public static BudgetSupport findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the budget support where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching budget support, or <code>null</code> if a matching budget support could not be found
	*/
	public static BudgetSupport fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the budget support where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching budget support, or <code>null</code> if a matching budget support could not be found
	*/
	public static BudgetSupport fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the budget support where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the budget support that was removed
	*/
	public static BudgetSupport removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of budget supports where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching budget supports
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the budget supports where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching budget supports
	*/
	public static List<BudgetSupport> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the budget supports where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @return the range of matching budget supports
	*/
	public static List<BudgetSupport> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the budget supports where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching budget supports
	*/
	public static List<BudgetSupport> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the budget supports where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static List<BudgetSupport> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first budget support in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget support
	* @throws NoSuchBudgetSupportException if a matching budget support could not be found
	*/
	public static BudgetSupport findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first budget support in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	*/
	public static BudgetSupport fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last budget support in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget support
	* @throws NoSuchBudgetSupportException if a matching budget support could not be found
	*/
	public static BudgetSupport findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last budget support in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	*/
	public static BudgetSupport fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static BudgetSupport[] findByUuid_C_PrevAndNext(
		long budgetSupportId, java.lang.String uuid, long companyId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(budgetSupportId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the budget supports where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of budget supports where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching budget supports
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the budget supports where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching budget supports
	*/
	public static List<BudgetSupport> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the budget supports where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @return the range of matching budget supports
	*/
	public static List<BudgetSupport> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the budget supports where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching budget supports
	*/
	public static List<BudgetSupport> findByGroupId(long groupId, int start,
		int end, OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the budget supports where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching budget supports
	*/
	public static List<BudgetSupport> findByGroupId(long groupId, int start,
		int end, OrderByComparator<BudgetSupport> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first budget support in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget support
	* @throws NoSuchBudgetSupportException if a matching budget support could not be found
	*/
	public static BudgetSupport findByGroupId_First(long groupId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first budget support in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	*/
	public static BudgetSupport fetchByGroupId_First(long groupId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last budget support in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget support
	* @throws NoSuchBudgetSupportException if a matching budget support could not be found
	*/
	public static BudgetSupport findByGroupId_Last(long groupId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last budget support in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	*/
	public static BudgetSupport fetchByGroupId_Last(long groupId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the budget supports before and after the current budget support in the ordered set where groupId = &#63;.
	*
	* @param budgetSupportId the primary key of the current budget support
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next budget support
	* @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	*/
	public static BudgetSupport[] findByGroupId_PrevAndNext(
		long budgetSupportId, long groupId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(budgetSupportId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the budget supports where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of budget supports where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching budget supports
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the budget supports where budgetParticipatifId = &#63;.
	*
	* @param budgetParticipatifId the budget participatif ID
	* @return the matching budget supports
	*/
	public static List<BudgetSupport> findByBudgetParticipatif(
		long budgetParticipatifId) {
		return getPersistence().findByBudgetParticipatif(budgetParticipatifId);
	}

	/**
	* Returns a range of all the budget supports where budgetParticipatifId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param budgetParticipatifId the budget participatif ID
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @return the range of matching budget supports
	*/
	public static List<BudgetSupport> findByBudgetParticipatif(
		long budgetParticipatifId, int start, int end) {
		return getPersistence()
				   .findByBudgetParticipatif(budgetParticipatifId, start, end);
	}

	/**
	* Returns an ordered range of all the budget supports where budgetParticipatifId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param budgetParticipatifId the budget participatif ID
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching budget supports
	*/
	public static List<BudgetSupport> findByBudgetParticipatif(
		long budgetParticipatifId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence()
				   .findByBudgetParticipatif(budgetParticipatifId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the budget supports where budgetParticipatifId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param budgetParticipatifId the budget participatif ID
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching budget supports
	*/
	public static List<BudgetSupport> findByBudgetParticipatif(
		long budgetParticipatifId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByBudgetParticipatif(budgetParticipatifId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first budget support in the ordered set where budgetParticipatifId = &#63;.
	*
	* @param budgetParticipatifId the budget participatif ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget support
	* @throws NoSuchBudgetSupportException if a matching budget support could not be found
	*/
	public static BudgetSupport findByBudgetParticipatif_First(
		long budgetParticipatifId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence()
				   .findByBudgetParticipatif_First(budgetParticipatifId,
			orderByComparator);
	}

	/**
	* Returns the first budget support in the ordered set where budgetParticipatifId = &#63;.
	*
	* @param budgetParticipatifId the budget participatif ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	*/
	public static BudgetSupport fetchByBudgetParticipatif_First(
		long budgetParticipatifId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence()
				   .fetchByBudgetParticipatif_First(budgetParticipatifId,
			orderByComparator);
	}

	/**
	* Returns the last budget support in the ordered set where budgetParticipatifId = &#63;.
	*
	* @param budgetParticipatifId the budget participatif ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget support
	* @throws NoSuchBudgetSupportException if a matching budget support could not be found
	*/
	public static BudgetSupport findByBudgetParticipatif_Last(
		long budgetParticipatifId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence()
				   .findByBudgetParticipatif_Last(budgetParticipatifId,
			orderByComparator);
	}

	/**
	* Returns the last budget support in the ordered set where budgetParticipatifId = &#63;.
	*
	* @param budgetParticipatifId the budget participatif ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	*/
	public static BudgetSupport fetchByBudgetParticipatif_Last(
		long budgetParticipatifId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence()
				   .fetchByBudgetParticipatif_Last(budgetParticipatifId,
			orderByComparator);
	}

	/**
	* Returns the budget supports before and after the current budget support in the ordered set where budgetParticipatifId = &#63;.
	*
	* @param budgetSupportId the primary key of the current budget support
	* @param budgetParticipatifId the budget participatif ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next budget support
	* @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	*/
	public static BudgetSupport[] findByBudgetParticipatif_PrevAndNext(
		long budgetSupportId, long budgetParticipatifId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence()
				   .findByBudgetParticipatif_PrevAndNext(budgetSupportId,
			budgetParticipatifId, orderByComparator);
	}

	/**
	* Removes all the budget supports where budgetParticipatifId = &#63; from the database.
	*
	* @param budgetParticipatifId the budget participatif ID
	*/
	public static void removeByBudgetParticipatif(long budgetParticipatifId) {
		getPersistence().removeByBudgetParticipatif(budgetParticipatifId);
	}

	/**
	* Returns the number of budget supports where budgetParticipatifId = &#63;.
	*
	* @param budgetParticipatifId the budget participatif ID
	* @return the number of matching budget supports
	*/
	public static int countByBudgetParticipatif(long budgetParticipatifId) {
		return getPersistence().countByBudgetParticipatif(budgetParticipatifId);
	}

	/**
	* Returns all the budget supports where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the matching budget supports
	*/
	public static List<BudgetSupport> findByPublikUserId(
		java.lang.String publikUserId) {
		return getPersistence().findByPublikUserId(publikUserId);
	}

	/**
	* Returns a range of all the budget supports where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @return the range of matching budget supports
	*/
	public static List<BudgetSupport> findByPublikUserId(
		java.lang.String publikUserId, int start, int end) {
		return getPersistence().findByPublikUserId(publikUserId, start, end);
	}

	/**
	* Returns an ordered range of all the budget supports where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching budget supports
	*/
	public static List<BudgetSupport> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the budget supports where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching budget supports
	*/
	public static List<BudgetSupport> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPublikUserId(publikUserId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first budget support in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget support
	* @throws NoSuchBudgetSupportException if a matching budget support could not be found
	*/
	public static BudgetSupport findByPublikUserId_First(
		java.lang.String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence()
				   .findByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the first budget support in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	*/
	public static BudgetSupport fetchByPublikUserId_First(
		java.lang.String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_First(publikUserId, orderByComparator);
	}

	/**
	* Returns the last budget support in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget support
	* @throws NoSuchBudgetSupportException if a matching budget support could not be found
	*/
	public static BudgetSupport findByPublikUserId_Last(
		java.lang.String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence()
				   .findByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the last budget support in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	*/
	public static BudgetSupport fetchByPublikUserId_Last(
		java.lang.String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence()
				   .fetchByPublikUserId_Last(publikUserId, orderByComparator);
	}

	/**
	* Returns the budget supports before and after the current budget support in the ordered set where publikUserId = &#63;.
	*
	* @param budgetSupportId the primary key of the current budget support
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next budget support
	* @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	*/
	public static BudgetSupport[] findByPublikUserId_PrevAndNext(
		long budgetSupportId, java.lang.String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence()
				   .findByPublikUserId_PrevAndNext(budgetSupportId,
			publikUserId, orderByComparator);
	}

	/**
	* Removes all the budget supports where publikUserId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	*/
	public static void removeByPublikUserId(java.lang.String publikUserId) {
		getPersistence().removeByPublikUserId(publikUserId);
	}

	/**
	* Returns the number of budget supports where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the number of matching budget supports
	*/
	public static int countByPublikUserId(java.lang.String publikUserId) {
		return getPersistence().countByPublikUserId(publikUserId);
	}

	/**
	* Returns all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	*
	* @param budgetParticipatifId the budget participatif ID
	* @param publikUserId the publik user ID
	* @return the matching budget supports
	*/
	public static List<BudgetSupport> findByBudgetParticipatifIdAndPublikUserId(
		long budgetParticipatifId, java.lang.String publikUserId) {
		return getPersistence()
				   .findByBudgetParticipatifIdAndPublikUserId(budgetParticipatifId,
			publikUserId);
	}

	/**
	* Returns a range of all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param budgetParticipatifId the budget participatif ID
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @return the range of matching budget supports
	*/
	public static List<BudgetSupport> findByBudgetParticipatifIdAndPublikUserId(
		long budgetParticipatifId, java.lang.String publikUserId, int start,
		int end) {
		return getPersistence()
				   .findByBudgetParticipatifIdAndPublikUserId(budgetParticipatifId,
			publikUserId, start, end);
	}

	/**
	* Returns an ordered range of all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param budgetParticipatifId the budget participatif ID
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching budget supports
	*/
	public static List<BudgetSupport> findByBudgetParticipatifIdAndPublikUserId(
		long budgetParticipatifId, java.lang.String publikUserId, int start,
		int end, OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence()
				   .findByBudgetParticipatifIdAndPublikUserId(budgetParticipatifId,
			publikUserId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static List<BudgetSupport> findByBudgetParticipatifIdAndPublikUserId(
		long budgetParticipatifId, java.lang.String publikUserId, int start,
		int end, OrderByComparator<BudgetSupport> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByBudgetParticipatifIdAndPublikUserId(budgetParticipatifId,
			publikUserId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first budget support in the ordered set where budgetParticipatifId = &#63; and publikUserId = &#63;.
	*
	* @param budgetParticipatifId the budget participatif ID
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget support
	* @throws NoSuchBudgetSupportException if a matching budget support could not be found
	*/
	public static BudgetSupport findByBudgetParticipatifIdAndPublikUserId_First(
		long budgetParticipatifId, java.lang.String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence()
				   .findByBudgetParticipatifIdAndPublikUserId_First(budgetParticipatifId,
			publikUserId, orderByComparator);
	}

	/**
	* Returns the first budget support in the ordered set where budgetParticipatifId = &#63; and publikUserId = &#63;.
	*
	* @param budgetParticipatifId the budget participatif ID
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching budget support, or <code>null</code> if a matching budget support could not be found
	*/
	public static BudgetSupport fetchByBudgetParticipatifIdAndPublikUserId_First(
		long budgetParticipatifId, java.lang.String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence()
				   .fetchByBudgetParticipatifIdAndPublikUserId_First(budgetParticipatifId,
			publikUserId, orderByComparator);
	}

	/**
	* Returns the last budget support in the ordered set where budgetParticipatifId = &#63; and publikUserId = &#63;.
	*
	* @param budgetParticipatifId the budget participatif ID
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget support
	* @throws NoSuchBudgetSupportException if a matching budget support could not be found
	*/
	public static BudgetSupport findByBudgetParticipatifIdAndPublikUserId_Last(
		long budgetParticipatifId, java.lang.String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence()
				   .findByBudgetParticipatifIdAndPublikUserId_Last(budgetParticipatifId,
			publikUserId, orderByComparator);
	}

	/**
	* Returns the last budget support in the ordered set where budgetParticipatifId = &#63; and publikUserId = &#63;.
	*
	* @param budgetParticipatifId the budget participatif ID
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching budget support, or <code>null</code> if a matching budget support could not be found
	*/
	public static BudgetSupport fetchByBudgetParticipatifIdAndPublikUserId_Last(
		long budgetParticipatifId, java.lang.String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence()
				   .fetchByBudgetParticipatifIdAndPublikUserId_Last(budgetParticipatifId,
			publikUserId, orderByComparator);
	}

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
	public static BudgetSupport[] findByBudgetParticipatifIdAndPublikUserId_PrevAndNext(
		long budgetSupportId, long budgetParticipatifId,
		java.lang.String publikUserId,
		OrderByComparator<BudgetSupport> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence()
				   .findByBudgetParticipatifIdAndPublikUserId_PrevAndNext(budgetSupportId,
			budgetParticipatifId, publikUserId, orderByComparator);
	}

	/**
	* Removes all the budget supports where budgetParticipatifId = &#63; and publikUserId = &#63; from the database.
	*
	* @param budgetParticipatifId the budget participatif ID
	* @param publikUserId the publik user ID
	*/
	public static void removeByBudgetParticipatifIdAndPublikUserId(
		long budgetParticipatifId, java.lang.String publikUserId) {
		getPersistence()
			.removeByBudgetParticipatifIdAndPublikUserId(budgetParticipatifId,
			publikUserId);
	}

	/**
	* Returns the number of budget supports where budgetParticipatifId = &#63; and publikUserId = &#63;.
	*
	* @param budgetParticipatifId the budget participatif ID
	* @param publikUserId the publik user ID
	* @return the number of matching budget supports
	*/
	public static int countByBudgetParticipatifIdAndPublikUserId(
		long budgetParticipatifId, java.lang.String publikUserId) {
		return getPersistence()
				   .countByBudgetParticipatifIdAndPublikUserId(budgetParticipatifId,
			publikUserId);
	}

	/**
	* Caches the budget support in the entity cache if it is enabled.
	*
	* @param budgetSupport the budget support
	*/
	public static void cacheResult(BudgetSupport budgetSupport) {
		getPersistence().cacheResult(budgetSupport);
	}

	/**
	* Caches the budget supports in the entity cache if it is enabled.
	*
	* @param budgetSupports the budget supports
	*/
	public static void cacheResult(List<BudgetSupport> budgetSupports) {
		getPersistence().cacheResult(budgetSupports);
	}

	/**
	* Creates a new budget support with the primary key. Does not add the budget support to the database.
	*
	* @param budgetSupportId the primary key for the new budget support
	* @return the new budget support
	*/
	public static BudgetSupport create(long budgetSupportId) {
		return getPersistence().create(budgetSupportId);
	}

	/**
	* Removes the budget support with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param budgetSupportId the primary key of the budget support
	* @return the budget support that was removed
	* @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	*/
	public static BudgetSupport remove(long budgetSupportId)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence().remove(budgetSupportId);
	}

	public static BudgetSupport updateImpl(BudgetSupport budgetSupport) {
		return getPersistence().updateImpl(budgetSupport);
	}

	/**
	* Returns the budget support with the primary key or throws a {@link NoSuchBudgetSupportException} if it could not be found.
	*
	* @param budgetSupportId the primary key of the budget support
	* @return the budget support
	* @throws NoSuchBudgetSupportException if a budget support with the primary key could not be found
	*/
	public static BudgetSupport findByPrimaryKey(long budgetSupportId)
		throws eu.strasbourg.service.project.exception.NoSuchBudgetSupportException {
		return getPersistence().findByPrimaryKey(budgetSupportId);
	}

	/**
	* Returns the budget support with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param budgetSupportId the primary key of the budget support
	* @return the budget support, or <code>null</code> if a budget support with the primary key could not be found
	*/
	public static BudgetSupport fetchByPrimaryKey(long budgetSupportId) {
		return getPersistence().fetchByPrimaryKey(budgetSupportId);
	}

	public static java.util.Map<java.io.Serializable, BudgetSupport> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the budget supports.
	*
	* @return the budget supports
	*/
	public static List<BudgetSupport> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the budget supports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @return the range of budget supports
	*/
	public static List<BudgetSupport> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the budget supports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of budget supports
	*/
	public static List<BudgetSupport> findAll(int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the budget supports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link BudgetSupportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of budget supports
	* @param end the upper bound of the range of budget supports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of budget supports
	*/
	public static List<BudgetSupport> findAll(int start, int end,
		OrderByComparator<BudgetSupport> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the budget supports from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of budget supports.
	*
	* @return the number of budget supports
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static BudgetSupportPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BudgetSupportPersistence, BudgetSupportPersistence> _serviceTracker =
		ServiceTrackerFactory.open(BudgetSupportPersistence.class);
}