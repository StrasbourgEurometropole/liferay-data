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

package eu.strasbourg.service.help.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.help.model.HelpRequest;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the help request service. This utility wraps <code>eu.strasbourg.service.help.service.persistence.impl.HelpRequestPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see HelpRequestPersistence
 * @generated
 */
@ProviderType
public class HelpRequestUtil {

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
	public static void clearCache(HelpRequest helpRequest) {
		getPersistence().clearCache(helpRequest);
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
	public static Map<Serializable, HelpRequest> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<HelpRequest> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<HelpRequest> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<HelpRequest> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<HelpRequest> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static HelpRequest update(HelpRequest helpRequest) {
		return getPersistence().update(helpRequest);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static HelpRequest update(
		HelpRequest helpRequest, ServiceContext serviceContext) {

		return getPersistence().update(helpRequest, serviceContext);
	}

	/**
	 * Returns all the help requests where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching help requests
	 */
	public static List<HelpRequest> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the help requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @return the range of matching help requests
	 */
	public static List<HelpRequest> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the help requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help requests
	 */
	public static List<HelpRequest> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<HelpRequest> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help requests where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching help requests
	 */
	public static List<HelpRequest> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<HelpRequest> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first help request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public static HelpRequest findByUuid_First(
			String uuid, OrderByComparator<HelpRequest> orderByComparator)
		throws eu.strasbourg.service.help.exception.NoSuchHelpRequestException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first help request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public static HelpRequest fetchByUuid_First(
		String uuid, OrderByComparator<HelpRequest> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last help request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public static HelpRequest findByUuid_Last(
			String uuid, OrderByComparator<HelpRequest> orderByComparator)
		throws eu.strasbourg.service.help.exception.NoSuchHelpRequestException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last help request in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public static HelpRequest fetchByUuid_Last(
		String uuid, OrderByComparator<HelpRequest> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the help requests before and after the current help request in the ordered set where uuid = &#63;.
	 *
	 * @param helpRequestId the primary key of the current help request
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help request
	 * @throws NoSuchHelpRequestException if a help request with the primary key could not be found
	 */
	public static HelpRequest[] findByUuid_PrevAndNext(
			long helpRequestId, String uuid,
			OrderByComparator<HelpRequest> orderByComparator)
		throws eu.strasbourg.service.help.exception.NoSuchHelpRequestException {

		return getPersistence().findByUuid_PrevAndNext(
			helpRequestId, uuid, orderByComparator);
	}

	/**
	 * Removes all the help requests where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of help requests where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching help requests
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the help request where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchHelpRequestException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public static HelpRequest findByUUID_G(String uuid, long groupId)
		throws eu.strasbourg.service.help.exception.NoSuchHelpRequestException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the help request where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public static HelpRequest fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the help request where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public static HelpRequest fetchByUUID_G(
		String uuid, long groupId, boolean retrieveFromCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	 * Removes the help request where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the help request that was removed
	 */
	public static HelpRequest removeByUUID_G(String uuid, long groupId)
		throws eu.strasbourg.service.help.exception.NoSuchHelpRequestException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of help requests where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching help requests
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the help requests where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching help requests
	 */
	public static List<HelpRequest> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the help requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @return the range of matching help requests
	 */
	public static List<HelpRequest> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the help requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help requests
	 */
	public static List<HelpRequest> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<HelpRequest> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help requests where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching help requests
	 */
	public static List<HelpRequest> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<HelpRequest> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first help request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public static HelpRequest findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<HelpRequest> orderByComparator)
		throws eu.strasbourg.service.help.exception.NoSuchHelpRequestException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first help request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public static HelpRequest fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<HelpRequest> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last help request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public static HelpRequest findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<HelpRequest> orderByComparator)
		throws eu.strasbourg.service.help.exception.NoSuchHelpRequestException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last help request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public static HelpRequest fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<HelpRequest> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the help requests before and after the current help request in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param helpRequestId the primary key of the current help request
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help request
	 * @throws NoSuchHelpRequestException if a help request with the primary key could not be found
	 */
	public static HelpRequest[] findByUuid_C_PrevAndNext(
			long helpRequestId, String uuid, long companyId,
			OrderByComparator<HelpRequest> orderByComparator)
		throws eu.strasbourg.service.help.exception.NoSuchHelpRequestException {

		return getPersistence().findByUuid_C_PrevAndNext(
			helpRequestId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the help requests where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of help requests where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching help requests
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the help requests where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the matching help requests
	 */
	public static List<HelpRequest> findByPublikId(String publikId) {
		return getPersistence().findByPublikId(publikId);
	}

	/**
	 * Returns a range of all the help requests where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @return the range of matching help requests
	 */
	public static List<HelpRequest> findByPublikId(
		String publikId, int start, int end) {

		return getPersistence().findByPublikId(publikId, start, end);
	}

	/**
	 * Returns an ordered range of all the help requests where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help requests
	 */
	public static List<HelpRequest> findByPublikId(
		String publikId, int start, int end,
		OrderByComparator<HelpRequest> orderByComparator) {

		return getPersistence().findByPublikId(
			publikId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help requests where publikId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param publikId the publik ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching help requests
	 */
	public static List<HelpRequest> findByPublikId(
		String publikId, int start, int end,
		OrderByComparator<HelpRequest> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByPublikId(
			publikId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first help request in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public static HelpRequest findByPublikId_First(
			String publikId, OrderByComparator<HelpRequest> orderByComparator)
		throws eu.strasbourg.service.help.exception.NoSuchHelpRequestException {

		return getPersistence().findByPublikId_First(
			publikId, orderByComparator);
	}

	/**
	 * Returns the first help request in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public static HelpRequest fetchByPublikId_First(
		String publikId, OrderByComparator<HelpRequest> orderByComparator) {

		return getPersistence().fetchByPublikId_First(
			publikId, orderByComparator);
	}

	/**
	 * Returns the last help request in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public static HelpRequest findByPublikId_Last(
			String publikId, OrderByComparator<HelpRequest> orderByComparator)
		throws eu.strasbourg.service.help.exception.NoSuchHelpRequestException {

		return getPersistence().findByPublikId_Last(
			publikId, orderByComparator);
	}

	/**
	 * Returns the last help request in the ordered set where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public static HelpRequest fetchByPublikId_Last(
		String publikId, OrderByComparator<HelpRequest> orderByComparator) {

		return getPersistence().fetchByPublikId_Last(
			publikId, orderByComparator);
	}

	/**
	 * Returns the help requests before and after the current help request in the ordered set where publikId = &#63;.
	 *
	 * @param helpRequestId the primary key of the current help request
	 * @param publikId the publik ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help request
	 * @throws NoSuchHelpRequestException if a help request with the primary key could not be found
	 */
	public static HelpRequest[] findByPublikId_PrevAndNext(
			long helpRequestId, String publikId,
			OrderByComparator<HelpRequest> orderByComparator)
		throws eu.strasbourg.service.help.exception.NoSuchHelpRequestException {

		return getPersistence().findByPublikId_PrevAndNext(
			helpRequestId, publikId, orderByComparator);
	}

	/**
	 * Removes all the help requests where publikId = &#63; from the database.
	 *
	 * @param publikId the publik ID
	 */
	public static void removeByPublikId(String publikId) {
		getPersistence().removeByPublikId(publikId);
	}

	/**
	 * Returns the number of help requests where publikId = &#63;.
	 *
	 * @param publikId the publik ID
	 * @return the number of matching help requests
	 */
	public static int countByPublikId(String publikId) {
		return getPersistence().countByPublikId(publikId);
	}

	/**
	 * Returns all the help requests where helpProposalId = &#63;.
	 *
	 * @param helpProposalId the help proposal ID
	 * @return the matching help requests
	 */
	public static List<HelpRequest> findByHelpProposalId(long helpProposalId) {
		return getPersistence().findByHelpProposalId(helpProposalId);
	}

	/**
	 * Returns a range of all the help requests where helpProposalId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param helpProposalId the help proposal ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @return the range of matching help requests
	 */
	public static List<HelpRequest> findByHelpProposalId(
		long helpProposalId, int start, int end) {

		return getPersistence().findByHelpProposalId(
			helpProposalId, start, end);
	}

	/**
	 * Returns an ordered range of all the help requests where helpProposalId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param helpProposalId the help proposal ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching help requests
	 */
	public static List<HelpRequest> findByHelpProposalId(
		long helpProposalId, int start, int end,
		OrderByComparator<HelpRequest> orderByComparator) {

		return getPersistence().findByHelpProposalId(
			helpProposalId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help requests where helpProposalId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param helpProposalId the help proposal ID
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching help requests
	 */
	public static List<HelpRequest> findByHelpProposalId(
		long helpProposalId, int start, int end,
		OrderByComparator<HelpRequest> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findByHelpProposalId(
			helpProposalId, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Returns the first help request in the ordered set where helpProposalId = &#63;.
	 *
	 * @param helpProposalId the help proposal ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public static HelpRequest findByHelpProposalId_First(
			long helpProposalId,
			OrderByComparator<HelpRequest> orderByComparator)
		throws eu.strasbourg.service.help.exception.NoSuchHelpRequestException {

		return getPersistence().findByHelpProposalId_First(
			helpProposalId, orderByComparator);
	}

	/**
	 * Returns the first help request in the ordered set where helpProposalId = &#63;.
	 *
	 * @param helpProposalId the help proposal ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public static HelpRequest fetchByHelpProposalId_First(
		long helpProposalId, OrderByComparator<HelpRequest> orderByComparator) {

		return getPersistence().fetchByHelpProposalId_First(
			helpProposalId, orderByComparator);
	}

	/**
	 * Returns the last help request in the ordered set where helpProposalId = &#63;.
	 *
	 * @param helpProposalId the help proposal ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help request
	 * @throws NoSuchHelpRequestException if a matching help request could not be found
	 */
	public static HelpRequest findByHelpProposalId_Last(
			long helpProposalId,
			OrderByComparator<HelpRequest> orderByComparator)
		throws eu.strasbourg.service.help.exception.NoSuchHelpRequestException {

		return getPersistence().findByHelpProposalId_Last(
			helpProposalId, orderByComparator);
	}

	/**
	 * Returns the last help request in the ordered set where helpProposalId = &#63;.
	 *
	 * @param helpProposalId the help proposal ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching help request, or <code>null</code> if a matching help request could not be found
	 */
	public static HelpRequest fetchByHelpProposalId_Last(
		long helpProposalId, OrderByComparator<HelpRequest> orderByComparator) {

		return getPersistence().fetchByHelpProposalId_Last(
			helpProposalId, orderByComparator);
	}

	/**
	 * Returns the help requests before and after the current help request in the ordered set where helpProposalId = &#63;.
	 *
	 * @param helpRequestId the primary key of the current help request
	 * @param helpProposalId the help proposal ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next help request
	 * @throws NoSuchHelpRequestException if a help request with the primary key could not be found
	 */
	public static HelpRequest[] findByHelpProposalId_PrevAndNext(
			long helpRequestId, long helpProposalId,
			OrderByComparator<HelpRequest> orderByComparator)
		throws eu.strasbourg.service.help.exception.NoSuchHelpRequestException {

		return getPersistence().findByHelpProposalId_PrevAndNext(
			helpRequestId, helpProposalId, orderByComparator);
	}

	/**
	 * Removes all the help requests where helpProposalId = &#63; from the database.
	 *
	 * @param helpProposalId the help proposal ID
	 */
	public static void removeByHelpProposalId(long helpProposalId) {
		getPersistence().removeByHelpProposalId(helpProposalId);
	}

	/**
	 * Returns the number of help requests where helpProposalId = &#63;.
	 *
	 * @param helpProposalId the help proposal ID
	 * @return the number of matching help requests
	 */
	public static int countByHelpProposalId(long helpProposalId) {
		return getPersistence().countByHelpProposalId(helpProposalId);
	}

	/**
	 * Caches the help request in the entity cache if it is enabled.
	 *
	 * @param helpRequest the help request
	 */
	public static void cacheResult(HelpRequest helpRequest) {
		getPersistence().cacheResult(helpRequest);
	}

	/**
	 * Caches the help requests in the entity cache if it is enabled.
	 *
	 * @param helpRequests the help requests
	 */
	public static void cacheResult(List<HelpRequest> helpRequests) {
		getPersistence().cacheResult(helpRequests);
	}

	/**
	 * Creates a new help request with the primary key. Does not add the help request to the database.
	 *
	 * @param helpRequestId the primary key for the new help request
	 * @return the new help request
	 */
	public static HelpRequest create(long helpRequestId) {
		return getPersistence().create(helpRequestId);
	}

	/**
	 * Removes the help request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpRequestId the primary key of the help request
	 * @return the help request that was removed
	 * @throws NoSuchHelpRequestException if a help request with the primary key could not be found
	 */
	public static HelpRequest remove(long helpRequestId)
		throws eu.strasbourg.service.help.exception.NoSuchHelpRequestException {

		return getPersistence().remove(helpRequestId);
	}

	public static HelpRequest updateImpl(HelpRequest helpRequest) {
		return getPersistence().updateImpl(helpRequest);
	}

	/**
	 * Returns the help request with the primary key or throws a <code>NoSuchHelpRequestException</code> if it could not be found.
	 *
	 * @param helpRequestId the primary key of the help request
	 * @return the help request
	 * @throws NoSuchHelpRequestException if a help request with the primary key could not be found
	 */
	public static HelpRequest findByPrimaryKey(long helpRequestId)
		throws eu.strasbourg.service.help.exception.NoSuchHelpRequestException {

		return getPersistence().findByPrimaryKey(helpRequestId);
	}

	/**
	 * Returns the help request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param helpRequestId the primary key of the help request
	 * @return the help request, or <code>null</code> if a help request with the primary key could not be found
	 */
	public static HelpRequest fetchByPrimaryKey(long helpRequestId) {
		return getPersistence().fetchByPrimaryKey(helpRequestId);
	}

	/**
	 * Returns all the help requests.
	 *
	 * @return the help requests
	 */
	public static List<HelpRequest> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the help requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @return the range of help requests
	 */
	public static List<HelpRequest> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the help requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of help requests
	 */
	public static List<HelpRequest> findAll(
		int start, int end, OrderByComparator<HelpRequest> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the help requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>HelpRequestModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of help requests
	 */
	public static List<HelpRequest> findAll(
		int start, int end, OrderByComparator<HelpRequest> orderByComparator,
		boolean retrieveFromCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	 * Removes all the help requests from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of help requests.
	 *
	 * @return the number of help requests
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static HelpRequestPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<HelpRequestPersistence, HelpRequestPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(HelpRequestPersistence.class);

		ServiceTracker<HelpRequestPersistence, HelpRequestPersistence>
			serviceTracker =
				new ServiceTracker
					<HelpRequestPersistence, HelpRequestPersistence>(
						bundle.getBundleContext(), HelpRequestPersistence.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}