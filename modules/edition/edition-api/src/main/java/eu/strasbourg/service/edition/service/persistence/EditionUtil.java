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

package eu.strasbourg.service.edition.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.edition.model.Edition;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the edition service. This utility wraps <code>eu.strasbourg.service.edition.service.persistence.impl.EditionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see EditionPersistence
 * @generated
 */
public class EditionUtil {

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
	public static void clearCache(Edition edition) {
		getPersistence().clearCache(edition);
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
	public static Map<Serializable, Edition> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Edition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Edition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Edition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Edition> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Edition update(Edition edition) {
		return getPersistence().update(edition);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Edition update(
		Edition edition, ServiceContext serviceContext) {

		return getPersistence().update(edition, serviceContext);
	}

	/**
	 * Returns all the editions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching editions
	 */
	public static List<Edition> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the editions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @return the range of matching editions
	 */
	public static List<Edition> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the editions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching editions
	 */
	public static List<Edition> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Edition> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the editions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching editions
	 */
	public static List<Edition> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Edition> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first edition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	public static Edition findByUuid_First(
			String uuid, OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first edition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition, or <code>null</code> if a matching edition could not be found
	 */
	public static Edition fetchByUuid_First(
		String uuid, OrderByComparator<Edition> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last edition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	public static Edition findByUuid_Last(
			String uuid, OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last edition in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition, or <code>null</code> if a matching edition could not be found
	 */
	public static Edition fetchByUuid_Last(
		String uuid, OrderByComparator<Edition> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the editions before and after the current edition in the ordered set where uuid = &#63;.
	 *
	 * @param editionId the primary key of the current edition
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next edition
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	public static Edition[] findByUuid_PrevAndNext(
			long editionId, String uuid,
			OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByUuid_PrevAndNext(
			editionId, uuid, orderByComparator);
	}

	/**
	 * Removes all the editions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of editions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching editions
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the edition where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchEditionException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	public static Edition findByUUID_G(String uuid, long groupId)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the edition where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching edition, or <code>null</code> if a matching edition could not be found
	 */
	public static Edition fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the edition where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching edition, or <code>null</code> if a matching edition could not be found
	 */
	public static Edition fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the edition where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the edition that was removed
	 */
	public static Edition removeByUUID_G(String uuid, long groupId)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of editions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching editions
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the editions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching editions
	 */
	public static List<Edition> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the editions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @return the range of matching editions
	 */
	public static List<Edition> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the editions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching editions
	 */
	public static List<Edition> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Edition> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the editions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching editions
	 */
	public static List<Edition> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Edition> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first edition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	public static Edition findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first edition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition, or <code>null</code> if a matching edition could not be found
	 */
	public static Edition fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Edition> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last edition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	public static Edition findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last edition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition, or <code>null</code> if a matching edition could not be found
	 */
	public static Edition fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Edition> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the editions before and after the current edition in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param editionId the primary key of the current edition
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next edition
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	public static Edition[] findByUuid_C_PrevAndNext(
			long editionId, String uuid, long companyId,
			OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByUuid_C_PrevAndNext(
			editionId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the editions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of editions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching editions
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the editions where title = &#63;.
	 *
	 * @param title the title
	 * @return the matching editions
	 */
	public static List<Edition> findByTitle(String title) {
		return getPersistence().findByTitle(title);
	}

	/**
	 * Returns a range of all the editions where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @return the range of matching editions
	 */
	public static List<Edition> findByTitle(String title, int start, int end) {
		return getPersistence().findByTitle(title, start, end);
	}

	/**
	 * Returns an ordered range of all the editions where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching editions
	 */
	public static List<Edition> findByTitle(
		String title, int start, int end,
		OrderByComparator<Edition> orderByComparator) {

		return getPersistence().findByTitle(
			title, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the editions where title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param title the title
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching editions
	 */
	public static List<Edition> findByTitle(
		String title, int start, int end,
		OrderByComparator<Edition> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByTitle(
			title, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first edition in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	public static Edition findByTitle_First(
			String title, OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByTitle_First(title, orderByComparator);
	}

	/**
	 * Returns the first edition in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition, or <code>null</code> if a matching edition could not be found
	 */
	public static Edition fetchByTitle_First(
		String title, OrderByComparator<Edition> orderByComparator) {

		return getPersistence().fetchByTitle_First(title, orderByComparator);
	}

	/**
	 * Returns the last edition in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	public static Edition findByTitle_Last(
			String title, OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByTitle_Last(title, orderByComparator);
	}

	/**
	 * Returns the last edition in the ordered set where title = &#63;.
	 *
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition, or <code>null</code> if a matching edition could not be found
	 */
	public static Edition fetchByTitle_Last(
		String title, OrderByComparator<Edition> orderByComparator) {

		return getPersistence().fetchByTitle_Last(title, orderByComparator);
	}

	/**
	 * Returns the editions before and after the current edition in the ordered set where title = &#63;.
	 *
	 * @param editionId the primary key of the current edition
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next edition
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	public static Edition[] findByTitle_PrevAndNext(
			long editionId, String title,
			OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByTitle_PrevAndNext(
			editionId, title, orderByComparator);
	}

	/**
	 * Removes all the editions where title = &#63; from the database.
	 *
	 * @param title the title
	 */
	public static void removeByTitle(String title) {
		getPersistence().removeByTitle(title);
	}

	/**
	 * Returns the number of editions where title = &#63;.
	 *
	 * @param title the title
	 * @return the number of matching editions
	 */
	public static int countByTitle(String title) {
		return getPersistence().countByTitle(title);
	}

	/**
	 * Returns all the editions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching editions
	 */
	public static List<Edition> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the editions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @return the range of matching editions
	 */
	public static List<Edition> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the editions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching editions
	 */
	public static List<Edition> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Edition> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the editions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching editions
	 */
	public static List<Edition> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<Edition> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first edition in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	public static Edition findByGroupId_First(
			long groupId, OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first edition in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition, or <code>null</code> if a matching edition could not be found
	 */
	public static Edition fetchByGroupId_First(
		long groupId, OrderByComparator<Edition> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last edition in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	public static Edition findByGroupId_Last(
			long groupId, OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last edition in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition, or <code>null</code> if a matching edition could not be found
	 */
	public static Edition fetchByGroupId_Last(
		long groupId, OrderByComparator<Edition> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the editions before and after the current edition in the ordered set where groupId = &#63;.
	 *
	 * @param editionId the primary key of the current edition
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next edition
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	public static Edition[] findByGroupId_PrevAndNext(
			long editionId, long groupId,
			OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByGroupId_PrevAndNext(
			editionId, groupId, orderByComparator);
	}

	/**
	 * Removes all the editions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of editions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching editions
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the editions where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the matching editions
	 */
	public static List<Edition> findByGroupIdAndTitle(
		long groupId, String title) {

		return getPersistence().findByGroupIdAndTitle(groupId, title);
	}

	/**
	 * Returns a range of all the editions where groupId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @return the range of matching editions
	 */
	public static List<Edition> findByGroupIdAndTitle(
		long groupId, String title, int start, int end) {

		return getPersistence().findByGroupIdAndTitle(
			groupId, title, start, end);
	}

	/**
	 * Returns an ordered range of all the editions where groupId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching editions
	 */
	public static List<Edition> findByGroupIdAndTitle(
		long groupId, String title, int start, int end,
		OrderByComparator<Edition> orderByComparator) {

		return getPersistence().findByGroupIdAndTitle(
			groupId, title, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the editions where groupId = &#63; and title = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching editions
	 */
	public static List<Edition> findByGroupIdAndTitle(
		long groupId, String title, int start, int end,
		OrderByComparator<Edition> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByGroupIdAndTitle(
			groupId, title, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first edition in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	public static Edition findByGroupIdAndTitle_First(
			long groupId, String title,
			OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByGroupIdAndTitle_First(
			groupId, title, orderByComparator);
	}

	/**
	 * Returns the first edition in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition, or <code>null</code> if a matching edition could not be found
	 */
	public static Edition fetchByGroupIdAndTitle_First(
		long groupId, String title,
		OrderByComparator<Edition> orderByComparator) {

		return getPersistence().fetchByGroupIdAndTitle_First(
			groupId, title, orderByComparator);
	}

	/**
	 * Returns the last edition in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	public static Edition findByGroupIdAndTitle_Last(
			long groupId, String title,
			OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByGroupIdAndTitle_Last(
			groupId, title, orderByComparator);
	}

	/**
	 * Returns the last edition in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition, or <code>null</code> if a matching edition could not be found
	 */
	public static Edition fetchByGroupIdAndTitle_Last(
		long groupId, String title,
		OrderByComparator<Edition> orderByComparator) {

		return getPersistence().fetchByGroupIdAndTitle_Last(
			groupId, title, orderByComparator);
	}

	/**
	 * Returns the editions before and after the current edition in the ordered set where groupId = &#63; and title = &#63;.
	 *
	 * @param editionId the primary key of the current edition
	 * @param groupId the group ID
	 * @param title the title
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next edition
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	public static Edition[] findByGroupIdAndTitle_PrevAndNext(
			long editionId, long groupId, String title,
			OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByGroupIdAndTitle_PrevAndNext(
			editionId, groupId, title, orderByComparator);
	}

	/**
	 * Removes all the editions where groupId = &#63; and title = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 */
	public static void removeByGroupIdAndTitle(long groupId, String title) {
		getPersistence().removeByGroupIdAndTitle(groupId, title);
	}

	/**
	 * Returns the number of editions where groupId = &#63; and title = &#63;.
	 *
	 * @param groupId the group ID
	 * @param title the title
	 * @return the number of matching editions
	 */
	public static int countByGroupIdAndTitle(long groupId, String title) {
		return getPersistence().countByGroupIdAndTitle(groupId, title);
	}

	/**
	 * Returns all the editions where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @return the matching editions
	 */
	public static List<Edition> findByPublicationDateAndStatus(
		Date publicationDate, int status) {

		return getPersistence().findByPublicationDateAndStatus(
			publicationDate, status);
	}

	/**
	 * Returns a range of all the editions where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @return the range of matching editions
	 */
	public static List<Edition> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end) {

		return getPersistence().findByPublicationDateAndStatus(
			publicationDate, status, start, end);
	}

	/**
	 * Returns an ordered range of all the editions where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching editions
	 */
	public static List<Edition> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		OrderByComparator<Edition> orderByComparator) {

		return getPersistence().findByPublicationDateAndStatus(
			publicationDate, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the editions where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching editions
	 */
	public static List<Edition> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		OrderByComparator<Edition> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByPublicationDateAndStatus(
			publicationDate, status, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first edition in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	public static Edition findByPublicationDateAndStatus_First(
			Date publicationDate, int status,
			OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByPublicationDateAndStatus_First(
			publicationDate, status, orderByComparator);
	}

	/**
	 * Returns the first edition in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching edition, or <code>null</code> if a matching edition could not be found
	 */
	public static Edition fetchByPublicationDateAndStatus_First(
		Date publicationDate, int status,
		OrderByComparator<Edition> orderByComparator) {

		return getPersistence().fetchByPublicationDateAndStatus_First(
			publicationDate, status, orderByComparator);
	}

	/**
	 * Returns the last edition in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition
	 * @throws NoSuchEditionException if a matching edition could not be found
	 */
	public static Edition findByPublicationDateAndStatus_Last(
			Date publicationDate, int status,
			OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByPublicationDateAndStatus_Last(
			publicationDate, status, orderByComparator);
	}

	/**
	 * Returns the last edition in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching edition, or <code>null</code> if a matching edition could not be found
	 */
	public static Edition fetchByPublicationDateAndStatus_Last(
		Date publicationDate, int status,
		OrderByComparator<Edition> orderByComparator) {

		return getPersistence().fetchByPublicationDateAndStatus_Last(
			publicationDate, status, orderByComparator);
	}

	/**
	 * Returns the editions before and after the current edition in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param editionId the primary key of the current edition
	 * @param publicationDate the publication date
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next edition
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	public static Edition[] findByPublicationDateAndStatus_PrevAndNext(
			long editionId, Date publicationDate, int status,
			OrderByComparator<Edition> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByPublicationDateAndStatus_PrevAndNext(
			editionId, publicationDate, status, orderByComparator);
	}

	/**
	 * Removes all the editions where publicationDate &lt; &#63; and status = &#63; from the database.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 */
	public static void removeByPublicationDateAndStatus(
		Date publicationDate, int status) {

		getPersistence().removeByPublicationDateAndStatus(
			publicationDate, status);
	}

	/**
	 * Returns the number of editions where publicationDate &lt; &#63; and status = &#63;.
	 *
	 * @param publicationDate the publication date
	 * @param status the status
	 * @return the number of matching editions
	 */
	public static int countByPublicationDateAndStatus(
		Date publicationDate, int status) {

		return getPersistence().countByPublicationDateAndStatus(
			publicationDate, status);
	}

	/**
	 * Caches the edition in the entity cache if it is enabled.
	 *
	 * @param edition the edition
	 */
	public static void cacheResult(Edition edition) {
		getPersistence().cacheResult(edition);
	}

	/**
	 * Caches the editions in the entity cache if it is enabled.
	 *
	 * @param editions the editions
	 */
	public static void cacheResult(List<Edition> editions) {
		getPersistence().cacheResult(editions);
	}

	/**
	 * Creates a new edition with the primary key. Does not add the edition to the database.
	 *
	 * @param editionId the primary key for the new edition
	 * @return the new edition
	 */
	public static Edition create(long editionId) {
		return getPersistence().create(editionId);
	}

	/**
	 * Removes the edition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param editionId the primary key of the edition
	 * @return the edition that was removed
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	public static Edition remove(long editionId)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().remove(editionId);
	}

	public static Edition updateImpl(Edition edition) {
		return getPersistence().updateImpl(edition);
	}

	/**
	 * Returns the edition with the primary key or throws a <code>NoSuchEditionException</code> if it could not be found.
	 *
	 * @param editionId the primary key of the edition
	 * @return the edition
	 * @throws NoSuchEditionException if a edition with the primary key could not be found
	 */
	public static Edition findByPrimaryKey(long editionId)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionException {

		return getPersistence().findByPrimaryKey(editionId);
	}

	/**
	 * Returns the edition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param editionId the primary key of the edition
	 * @return the edition, or <code>null</code> if a edition with the primary key could not be found
	 */
	public static Edition fetchByPrimaryKey(long editionId) {
		return getPersistence().fetchByPrimaryKey(editionId);
	}

	/**
	 * Returns all the editions.
	 *
	 * @return the editions
	 */
	public static List<Edition> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the editions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @return the range of editions
	 */
	public static List<Edition> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the editions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of editions
	 */
	public static List<Edition> findAll(
		int start, int end, OrderByComparator<Edition> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the editions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of editions
	 */
	public static List<Edition> findAll(
		int start, int end, OrderByComparator<Edition> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the editions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of editions.
	 *
	 * @return the number of editions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	 * Returns the primaryKeys of edition galleries associated with the edition.
	 *
	 * @param pk the primary key of the edition
	 * @return long[] of the primaryKeys of edition galleries associated with the edition
	 */
	public static long[] getEditionGalleryPrimaryKeys(long pk) {
		return getPersistence().getEditionGalleryPrimaryKeys(pk);
	}

	/**
	 * Returns all the edition galleries associated with the edition.
	 *
	 * @param pk the primary key of the edition
	 * @return the edition galleries associated with the edition
	 */
	public static List<eu.strasbourg.service.edition.model.EditionGallery>
		getEditionGalleries(long pk) {

		return getPersistence().getEditionGalleries(pk);
	}

	/**
	 * Returns a range of all the edition galleries associated with the edition.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the edition
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @return the range of edition galleries associated with the edition
	 */
	public static List<eu.strasbourg.service.edition.model.EditionGallery>
		getEditionGalleries(long pk, int start, int end) {

		return getPersistence().getEditionGalleries(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the edition galleries associated with the edition.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>EditionModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the edition
	 * @param start the lower bound of the range of editions
	 * @param end the upper bound of the range of editions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of edition galleries associated with the edition
	 */
	public static List<eu.strasbourg.service.edition.model.EditionGallery>
		getEditionGalleries(
			long pk, int start, int end,
			OrderByComparator
				<eu.strasbourg.service.edition.model.EditionGallery>
					orderByComparator) {

		return getPersistence().getEditionGalleries(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of edition galleries associated with the edition.
	 *
	 * @param pk the primary key of the edition
	 * @return the number of edition galleries associated with the edition
	 */
	public static int getEditionGalleriesSize(long pk) {
		return getPersistence().getEditionGalleriesSize(pk);
	}

	/**
	 * Returns <code>true</code> if the edition gallery is associated with the edition.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleryPK the primary key of the edition gallery
	 * @return <code>true</code> if the edition gallery is associated with the edition; <code>false</code> otherwise
	 */
	public static boolean containsEditionGallery(
		long pk, long editionGalleryPK) {

		return getPersistence().containsEditionGallery(pk, editionGalleryPK);
	}

	/**
	 * Returns <code>true</code> if the edition has any edition galleries associated with it.
	 *
	 * @param pk the primary key of the edition to check for associations with edition galleries
	 * @return <code>true</code> if the edition has any edition galleries associated with it; <code>false</code> otherwise
	 */
	public static boolean containsEditionGalleries(long pk) {
		return getPersistence().containsEditionGalleries(pk);
	}

	/**
	 * Adds an association between the edition and the edition gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleryPK the primary key of the edition gallery
	 */
	public static void addEditionGallery(long pk, long editionGalleryPK) {
		getPersistence().addEditionGallery(pk, editionGalleryPK);
	}

	/**
	 * Adds an association between the edition and the edition gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGallery the edition gallery
	 */
	public static void addEditionGallery(
		long pk,
		eu.strasbourg.service.edition.model.EditionGallery editionGallery) {

		getPersistence().addEditionGallery(pk, editionGallery);
	}

	/**
	 * Adds an association between the edition and the edition galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleryPKs the primary keys of the edition galleries
	 */
	public static void addEditionGalleries(long pk, long[] editionGalleryPKs) {
		getPersistence().addEditionGalleries(pk, editionGalleryPKs);
	}

	/**
	 * Adds an association between the edition and the edition galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleries the edition galleries
	 */
	public static void addEditionGalleries(
		long pk,
		List<eu.strasbourg.service.edition.model.EditionGallery>
			editionGalleries) {

		getPersistence().addEditionGalleries(pk, editionGalleries);
	}

	/**
	 * Clears all associations between the edition and its edition galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition to clear the associated edition galleries from
	 */
	public static void clearEditionGalleries(long pk) {
		getPersistence().clearEditionGalleries(pk);
	}

	/**
	 * Removes the association between the edition and the edition gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleryPK the primary key of the edition gallery
	 */
	public static void removeEditionGallery(long pk, long editionGalleryPK) {
		getPersistence().removeEditionGallery(pk, editionGalleryPK);
	}

	/**
	 * Removes the association between the edition and the edition gallery. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGallery the edition gallery
	 */
	public static void removeEditionGallery(
		long pk,
		eu.strasbourg.service.edition.model.EditionGallery editionGallery) {

		getPersistence().removeEditionGallery(pk, editionGallery);
	}

	/**
	 * Removes the association between the edition and the edition galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleryPKs the primary keys of the edition galleries
	 */
	public static void removeEditionGalleries(
		long pk, long[] editionGalleryPKs) {

		getPersistence().removeEditionGalleries(pk, editionGalleryPKs);
	}

	/**
	 * Removes the association between the edition and the edition galleries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleries the edition galleries
	 */
	public static void removeEditionGalleries(
		long pk,
		List<eu.strasbourg.service.edition.model.EditionGallery>
			editionGalleries) {

		getPersistence().removeEditionGalleries(pk, editionGalleries);
	}

	/**
	 * Sets the edition galleries associated with the edition, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleryPKs the primary keys of the edition galleries to be associated with the edition
	 */
	public static void setEditionGalleries(long pk, long[] editionGalleryPKs) {
		getPersistence().setEditionGalleries(pk, editionGalleryPKs);
	}

	/**
	 * Sets the edition galleries associated with the edition, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the edition
	 * @param editionGalleries the edition galleries to be associated with the edition
	 */
	public static void setEditionGalleries(
		long pk,
		List<eu.strasbourg.service.edition.model.EditionGallery>
			editionGalleries) {

		getPersistence().setEditionGalleries(pk, editionGalleries);
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static EditionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EditionPersistence, EditionPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(EditionPersistence.class);

		ServiceTracker<EditionPersistence, EditionPersistence> serviceTracker =
			new ServiceTracker<EditionPersistence, EditionPersistence>(
				bundle.getBundleContext(), EditionPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}