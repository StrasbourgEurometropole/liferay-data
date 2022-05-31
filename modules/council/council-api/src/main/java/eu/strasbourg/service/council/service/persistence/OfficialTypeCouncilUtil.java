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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.council.model.OfficialTypeCouncil;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the official type council service. This utility wraps <code>eu.strasbourg.service.council.service.persistence.impl.OfficialTypeCouncilPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfficialTypeCouncilPersistence
 * @generated
 */
public class OfficialTypeCouncilUtil {

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
	public static void clearCache(OfficialTypeCouncil officialTypeCouncil) {
		getPersistence().clearCache(officialTypeCouncil);
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
	public static Map<Serializable, OfficialTypeCouncil> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<OfficialTypeCouncil> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OfficialTypeCouncil> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OfficialTypeCouncil> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OfficialTypeCouncil update(
		OfficialTypeCouncil officialTypeCouncil) {

		return getPersistence().update(officialTypeCouncil);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OfficialTypeCouncil update(
		OfficialTypeCouncil officialTypeCouncil,
		ServiceContext serviceContext) {

		return getPersistence().update(officialTypeCouncil, serviceContext);
	}

	/**
	 * Returns all the official type councils where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching official type councils
	 */
	public static List<OfficialTypeCouncil> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the official type councils where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of matching official type councils
	 */
	public static List<OfficialTypeCouncil> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the official type councils where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching official type councils
	 */
	public static List<OfficialTypeCouncil> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the official type councils where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching official type councils
	 */
	public static List<OfficialTypeCouncil> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first official type council in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil findByUuid_First(
			String uuid,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first official type council in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil fetchByUuid_First(
		String uuid, OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last official type council in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil findByUuid_Last(
			String uuid,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last official type council in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil fetchByUuid_Last(
		String uuid, OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the official type councils before and after the current official type council in the ordered set where uuid = &#63;.
	 *
	 * @param officialTypeCouncilPK the primary key of the current official type council
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next official type council
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	public static OfficialTypeCouncil[] findByUuid_PrevAndNext(
			eu.strasbourg.service.council.service.persistence.
				OfficialTypeCouncilPK officialTypeCouncilPK,
			String uuid,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().findByUuid_PrevAndNext(
			officialTypeCouncilPK, uuid, orderByComparator);
	}

	/**
	 * Removes all the official type councils where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of official type councils where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching official type councils
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the official type council where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchOfficialTypeCouncilException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil findByUUID_G(String uuid, long groupId)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the official type council where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the official type council where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the official type council where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the official type council that was removed
	 */
	public static OfficialTypeCouncil removeByUUID_G(String uuid, long groupId)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of official type councils where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching official type councils
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the official type councils where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching official type councils
	 */
	public static List<OfficialTypeCouncil> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the official type councils where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of matching official type councils
	 */
	public static List<OfficialTypeCouncil> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the official type councils where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching official type councils
	 */
	public static List<OfficialTypeCouncil> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the official type councils where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching official type councils
	 */
	public static List<OfficialTypeCouncil> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first official type council in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first official type council in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last official type council in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last official type council in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

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
	public static OfficialTypeCouncil[] findByUuid_C_PrevAndNext(
			eu.strasbourg.service.council.service.persistence.
				OfficialTypeCouncilPK officialTypeCouncilPK,
			String uuid, long companyId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().findByUuid_C_PrevAndNext(
			officialTypeCouncilPK, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the official type councils where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of official type councils where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching official type councils
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the official type councils where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @return the matching official type councils
	 */
	public static List<OfficialTypeCouncil> findByOfficialId(long officialId) {
		return getPersistence().findByOfficialId(officialId);
	}

	/**
	 * Returns a range of all the official type councils where officialId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param officialId the official ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of matching official type councils
	 */
	public static List<OfficialTypeCouncil> findByOfficialId(
		long officialId, int start, int end) {

		return getPersistence().findByOfficialId(officialId, start, end);
	}

	/**
	 * Returns an ordered range of all the official type councils where officialId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param officialId the official ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching official type councils
	 */
	public static List<OfficialTypeCouncil> findByOfficialId(
		long officialId, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return getPersistence().findByOfficialId(
			officialId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the official type councils where officialId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param officialId the official ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching official type councils
	 */
	public static List<OfficialTypeCouncil> findByOfficialId(
		long officialId, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByOfficialId(
			officialId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first official type council in the ordered set where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil findByOfficialId_First(
			long officialId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().findByOfficialId_First(
			officialId, orderByComparator);
	}

	/**
	 * Returns the first official type council in the ordered set where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil fetchByOfficialId_First(
		long officialId,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return getPersistence().fetchByOfficialId_First(
			officialId, orderByComparator);
	}

	/**
	 * Returns the last official type council in the ordered set where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil findByOfficialId_Last(
			long officialId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().findByOfficialId_Last(
			officialId, orderByComparator);
	}

	/**
	 * Returns the last official type council in the ordered set where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil fetchByOfficialId_Last(
		long officialId,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return getPersistence().fetchByOfficialId_Last(
			officialId, orderByComparator);
	}

	/**
	 * Returns the official type councils before and after the current official type council in the ordered set where officialId = &#63;.
	 *
	 * @param officialTypeCouncilPK the primary key of the current official type council
	 * @param officialId the official ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next official type council
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	public static OfficialTypeCouncil[] findByOfficialId_PrevAndNext(
			eu.strasbourg.service.council.service.persistence.
				OfficialTypeCouncilPK officialTypeCouncilPK,
			long officialId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().findByOfficialId_PrevAndNext(
			officialTypeCouncilPK, officialId, orderByComparator);
	}

	/**
	 * Removes all the official type councils where officialId = &#63; from the database.
	 *
	 * @param officialId the official ID
	 */
	public static void removeByOfficialId(long officialId) {
		getPersistence().removeByOfficialId(officialId);
	}

	/**
	 * Returns the number of official type councils where officialId = &#63;.
	 *
	 * @param officialId the official ID
	 * @return the number of matching official type councils
	 */
	public static int countByOfficialId(long officialId) {
		return getPersistence().countByOfficialId(officialId);
	}

	/**
	 * Returns all the official type councils where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the matching official type councils
	 */
	public static List<OfficialTypeCouncil> findByTypeId(long typeId) {
		return getPersistence().findByTypeId(typeId);
	}

	/**
	 * Returns a range of all the official type councils where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of matching official type councils
	 */
	public static List<OfficialTypeCouncil> findByTypeId(
		long typeId, int start, int end) {

		return getPersistence().findByTypeId(typeId, start, end);
	}

	/**
	 * Returns an ordered range of all the official type councils where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching official type councils
	 */
	public static List<OfficialTypeCouncil> findByTypeId(
		long typeId, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return getPersistence().findByTypeId(
			typeId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the official type councils where typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching official type councils
	 */
	public static List<OfficialTypeCouncil> findByTypeId(
		long typeId, int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByTypeId(
			typeId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first official type council in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil findByTypeId_First(
			long typeId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().findByTypeId_First(typeId, orderByComparator);
	}

	/**
	 * Returns the first official type council in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil fetchByTypeId_First(
		long typeId, OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return getPersistence().fetchByTypeId_First(typeId, orderByComparator);
	}

	/**
	 * Returns the last official type council in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil findByTypeId_Last(
			long typeId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().findByTypeId_Last(typeId, orderByComparator);
	}

	/**
	 * Returns the last official type council in the ordered set where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil fetchByTypeId_Last(
		long typeId, OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return getPersistence().fetchByTypeId_Last(typeId, orderByComparator);
	}

	/**
	 * Returns the official type councils before and after the current official type council in the ordered set where typeId = &#63;.
	 *
	 * @param officialTypeCouncilPK the primary key of the current official type council
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next official type council
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	public static OfficialTypeCouncil[] findByTypeId_PrevAndNext(
			eu.strasbourg.service.council.service.persistence.
				OfficialTypeCouncilPK officialTypeCouncilPK,
			long typeId,
			OrderByComparator<OfficialTypeCouncil> orderByComparator)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().findByTypeId_PrevAndNext(
			officialTypeCouncilPK, typeId, orderByComparator);
	}

	/**
	 * Removes all the official type councils where typeId = &#63; from the database.
	 *
	 * @param typeId the type ID
	 */
	public static void removeByTypeId(long typeId) {
		getPersistence().removeByTypeId(typeId);
	}

	/**
	 * Returns the number of official type councils where typeId = &#63;.
	 *
	 * @param typeId the type ID
	 * @return the number of matching official type councils
	 */
	public static int countByTypeId(long typeId) {
		return getPersistence().countByTypeId(typeId);
	}

	/**
	 * Returns the official type council where typeId = &#63; and officialId = &#63; or throws a <code>NoSuchOfficialTypeCouncilException</code> if it could not be found.
	 *
	 * @param typeId the type ID
	 * @param officialId the official ID
	 * @return the matching official type council
	 * @throws NoSuchOfficialTypeCouncilException if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil findByTypeIdAndOfficialId(
			long typeId, long officialId)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().findByTypeIdAndOfficialId(typeId, officialId);
	}

	/**
	 * Returns the official type council where typeId = &#63; and officialId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param typeId the type ID
	 * @param officialId the official ID
	 * @return the matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil fetchByTypeIdAndOfficialId(
		long typeId, long officialId) {

		return getPersistence().fetchByTypeIdAndOfficialId(typeId, officialId);
	}

	/**
	 * Returns the official type council where typeId = &#63; and officialId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param typeId the type ID
	 * @param officialId the official ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	public static OfficialTypeCouncil fetchByTypeIdAndOfficialId(
		long typeId, long officialId, boolean useFinderCache) {

		return getPersistence().fetchByTypeIdAndOfficialId(
			typeId, officialId, useFinderCache);
	}

	/**
	 * Removes the official type council where typeId = &#63; and officialId = &#63; from the database.
	 *
	 * @param typeId the type ID
	 * @param officialId the official ID
	 * @return the official type council that was removed
	 */
	public static OfficialTypeCouncil removeByTypeIdAndOfficialId(
			long typeId, long officialId)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().removeByTypeIdAndOfficialId(typeId, officialId);
	}

	/**
	 * Returns the number of official type councils where typeId = &#63; and officialId = &#63;.
	 *
	 * @param typeId the type ID
	 * @param officialId the official ID
	 * @return the number of matching official type councils
	 */
	public static int countByTypeIdAndOfficialId(long typeId, long officialId) {
		return getPersistence().countByTypeIdAndOfficialId(typeId, officialId);
	}

	/**
	 * Caches the official type council in the entity cache if it is enabled.
	 *
	 * @param officialTypeCouncil the official type council
	 */
	public static void cacheResult(OfficialTypeCouncil officialTypeCouncil) {
		getPersistence().cacheResult(officialTypeCouncil);
	}

	/**
	 * Caches the official type councils in the entity cache if it is enabled.
	 *
	 * @param officialTypeCouncils the official type councils
	 */
	public static void cacheResult(
		List<OfficialTypeCouncil> officialTypeCouncils) {

		getPersistence().cacheResult(officialTypeCouncils);
	}

	/**
	 * Creates a new official type council with the primary key. Does not add the official type council to the database.
	 *
	 * @param officialTypeCouncilPK the primary key for the new official type council
	 * @return the new official type council
	 */
	public static OfficialTypeCouncil create(
		eu.strasbourg.service.council.service.persistence.OfficialTypeCouncilPK
			officialTypeCouncilPK) {

		return getPersistence().create(officialTypeCouncilPK);
	}

	/**
	 * Removes the official type council with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param officialTypeCouncilPK the primary key of the official type council
	 * @return the official type council that was removed
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	public static OfficialTypeCouncil remove(
			eu.strasbourg.service.council.service.persistence.
				OfficialTypeCouncilPK officialTypeCouncilPK)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().remove(officialTypeCouncilPK);
	}

	public static OfficialTypeCouncil updateImpl(
		OfficialTypeCouncil officialTypeCouncil) {

		return getPersistence().updateImpl(officialTypeCouncil);
	}

	/**
	 * Returns the official type council with the primary key or throws a <code>NoSuchOfficialTypeCouncilException</code> if it could not be found.
	 *
	 * @param officialTypeCouncilPK the primary key of the official type council
	 * @return the official type council
	 * @throws NoSuchOfficialTypeCouncilException if a official type council with the primary key could not be found
	 */
	public static OfficialTypeCouncil findByPrimaryKey(
			eu.strasbourg.service.council.service.persistence.
				OfficialTypeCouncilPK officialTypeCouncilPK)
		throws eu.strasbourg.service.council.exception.
			NoSuchOfficialTypeCouncilException {

		return getPersistence().findByPrimaryKey(officialTypeCouncilPK);
	}

	/**
	 * Returns the official type council with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param officialTypeCouncilPK the primary key of the official type council
	 * @return the official type council, or <code>null</code> if a official type council with the primary key could not be found
	 */
	public static OfficialTypeCouncil fetchByPrimaryKey(
		eu.strasbourg.service.council.service.persistence.OfficialTypeCouncilPK
			officialTypeCouncilPK) {

		return getPersistence().fetchByPrimaryKey(officialTypeCouncilPK);
	}

	/**
	 * Returns all the official type councils.
	 *
	 * @return the official type councils
	 */
	public static List<OfficialTypeCouncil> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the official type councils.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of official type councils
	 */
	public static List<OfficialTypeCouncil> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the official type councils.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of official type councils
	 */
	public static List<OfficialTypeCouncil> findAll(
		int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the official type councils.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>OfficialTypeCouncilModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of official type councils
	 */
	public static List<OfficialTypeCouncil> findAll(
		int start, int end,
		OrderByComparator<OfficialTypeCouncil> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the official type councils from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of official type councils.
	 *
	 * @return the number of official type councils
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static OfficialTypeCouncilPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<OfficialTypeCouncilPersistence, OfficialTypeCouncilPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			OfficialTypeCouncilPersistence.class);

		ServiceTracker
			<OfficialTypeCouncilPersistence, OfficialTypeCouncilPersistence>
				serviceTracker =
					new ServiceTracker
						<OfficialTypeCouncilPersistence,
						 OfficialTypeCouncilPersistence>(
							 bundle.getBundleContext(),
							 OfficialTypeCouncilPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}