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

package eu.strasbourg.service.gtfs.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.gtfs.model.Ligne;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ligne service. This utility wraps {@link eu.strasbourg.service.gtfs.service.persistence.impl.LignePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see LignePersistence
 * @see eu.strasbourg.service.gtfs.service.persistence.impl.LignePersistenceImpl
 * @generated
 */
@ProviderType
public class LigneUtil {
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
	public static void clearCache(Ligne ligne) {
		getPersistence().clearCache(ligne);
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
	public static List<Ligne> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Ligne> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Ligne> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Ligne> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Ligne update(Ligne ligne) {
		return getPersistence().update(ligne);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Ligne update(Ligne ligne, ServiceContext serviceContext) {
		return getPersistence().update(ligne, serviceContext);
	}

	/**
	* Returns all the lignes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching lignes
	*/
	public static List<Ligne> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the lignes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @return the range of matching lignes
	*/
	public static List<Ligne> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the lignes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lignes
	*/
	public static List<Ligne> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Ligne> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the lignes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lignes
	*/
	public static List<Ligne> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Ligne> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first ligne in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ligne
	* @throws NoSuchLigneException if a matching ligne could not be found
	*/
	public static Ligne findByUuid_First(java.lang.String uuid,
		OrderByComparator<Ligne> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchLigneException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first ligne in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	public static Ligne fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Ligne> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last ligne in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ligne
	* @throws NoSuchLigneException if a matching ligne could not be found
	*/
	public static Ligne findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Ligne> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchLigneException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last ligne in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	public static Ligne fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Ligne> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the lignes before and after the current ligne in the ordered set where uuid = &#63;.
	*
	* @param ligneId the primary key of the current ligne
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ligne
	* @throws NoSuchLigneException if a ligne with the primary key could not be found
	*/
	public static Ligne[] findByUuid_PrevAndNext(long ligneId,
		java.lang.String uuid, OrderByComparator<Ligne> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchLigneException {
		return getPersistence()
				   .findByUuid_PrevAndNext(ligneId, uuid, orderByComparator);
	}

	/**
	* Removes all the lignes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of lignes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching lignes
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the ligne where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchLigneException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching ligne
	* @throws NoSuchLigneException if a matching ligne could not be found
	*/
	public static Ligne findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.gtfs.exception.NoSuchLigneException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the ligne where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	public static Ligne fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the ligne where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	public static Ligne fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the ligne where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the ligne that was removed
	*/
	public static Ligne removeByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.gtfs.exception.NoSuchLigneException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of lignes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching lignes
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the lignes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching lignes
	*/
	public static List<Ligne> findByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the lignes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @return the range of matching lignes
	*/
	public static List<Ligne> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the lignes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lignes
	*/
	public static List<Ligne> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Ligne> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the lignes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lignes
	*/
	public static List<Ligne> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Ligne> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ligne in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ligne
	* @throws NoSuchLigneException if a matching ligne could not be found
	*/
	public static Ligne findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Ligne> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchLigneException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first ligne in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	public static Ligne fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Ligne> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last ligne in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ligne
	* @throws NoSuchLigneException if a matching ligne could not be found
	*/
	public static Ligne findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Ligne> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchLigneException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last ligne in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	public static Ligne fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Ligne> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the lignes before and after the current ligne in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param ligneId the primary key of the current ligne
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ligne
	* @throws NoSuchLigneException if a ligne with the primary key could not be found
	*/
	public static Ligne[] findByUuid_C_PrevAndNext(long ligneId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Ligne> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchLigneException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(ligneId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the lignes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of lignes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching lignes
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the lignes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching lignes
	*/
	public static List<Ligne> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the lignes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @return the range of matching lignes
	*/
	public static List<Ligne> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the lignes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lignes
	*/
	public static List<Ligne> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Ligne> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the lignes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lignes
	*/
	public static List<Ligne> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Ligne> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first ligne in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ligne
	* @throws NoSuchLigneException if a matching ligne could not be found
	*/
	public static Ligne findByGroupId_First(long groupId,
		OrderByComparator<Ligne> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchLigneException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first ligne in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	public static Ligne fetchByGroupId_First(long groupId,
		OrderByComparator<Ligne> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last ligne in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ligne
	* @throws NoSuchLigneException if a matching ligne could not be found
	*/
	public static Ligne findByGroupId_Last(long groupId,
		OrderByComparator<Ligne> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchLigneException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last ligne in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	public static Ligne fetchByGroupId_Last(long groupId,
		OrderByComparator<Ligne> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the lignes before and after the current ligne in the ordered set where groupId = &#63;.
	*
	* @param ligneId the primary key of the current ligne
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ligne
	* @throws NoSuchLigneException if a ligne with the primary key could not be found
	*/
	public static Ligne[] findByGroupId_PrevAndNext(long ligneId, long groupId,
		OrderByComparator<Ligne> orderByComparator)
		throws eu.strasbourg.service.gtfs.exception.NoSuchLigneException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(ligneId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the lignes where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of lignes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching lignes
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Caches the ligne in the entity cache if it is enabled.
	*
	* @param ligne the ligne
	*/
	public static void cacheResult(Ligne ligne) {
		getPersistence().cacheResult(ligne);
	}

	/**
	* Caches the lignes in the entity cache if it is enabled.
	*
	* @param lignes the lignes
	*/
	public static void cacheResult(List<Ligne> lignes) {
		getPersistence().cacheResult(lignes);
	}

	/**
	* Creates a new ligne with the primary key. Does not add the ligne to the database.
	*
	* @param ligneId the primary key for the new ligne
	* @return the new ligne
	*/
	public static Ligne create(long ligneId) {
		return getPersistence().create(ligneId);
	}

	/**
	* Removes the ligne with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ligneId the primary key of the ligne
	* @return the ligne that was removed
	* @throws NoSuchLigneException if a ligne with the primary key could not be found
	*/
	public static Ligne remove(long ligneId)
		throws eu.strasbourg.service.gtfs.exception.NoSuchLigneException {
		return getPersistence().remove(ligneId);
	}

	public static Ligne updateImpl(Ligne ligne) {
		return getPersistence().updateImpl(ligne);
	}

	/**
	* Returns the ligne with the primary key or throws a {@link NoSuchLigneException} if it could not be found.
	*
	* @param ligneId the primary key of the ligne
	* @return the ligne
	* @throws NoSuchLigneException if a ligne with the primary key could not be found
	*/
	public static Ligne findByPrimaryKey(long ligneId)
		throws eu.strasbourg.service.gtfs.exception.NoSuchLigneException {
		return getPersistence().findByPrimaryKey(ligneId);
	}

	/**
	* Returns the ligne with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ligneId the primary key of the ligne
	* @return the ligne, or <code>null</code> if a ligne with the primary key could not be found
	*/
	public static Ligne fetchByPrimaryKey(long ligneId) {
		return getPersistence().fetchByPrimaryKey(ligneId);
	}

	public static java.util.Map<java.io.Serializable, Ligne> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the lignes.
	*
	* @return the lignes
	*/
	public static List<Ligne> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the lignes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @return the range of lignes
	*/
	public static List<Ligne> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the lignes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of lignes
	*/
	public static List<Ligne> findAll(int start, int end,
		OrderByComparator<Ligne> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the lignes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of lignes
	*/
	public static List<Ligne> findAll(int start, int end,
		OrderByComparator<Ligne> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the lignes from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of lignes.
	*
	* @return the number of lignes
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LignePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LignePersistence, LignePersistence> _serviceTracker =
		ServiceTrackerFactory.open(LignePersistence.class);
}