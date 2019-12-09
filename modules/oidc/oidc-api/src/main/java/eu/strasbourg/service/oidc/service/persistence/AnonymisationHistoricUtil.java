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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.oidc.model.AnonymisationHistoric;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the anonymisation historic service. This utility wraps {@link eu.strasbourg.service.oidc.service.persistence.impl.AnonymisationHistoricPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnonymisationHistoricPersistence
 * @see eu.strasbourg.service.oidc.service.persistence.impl.AnonymisationHistoricPersistenceImpl
 * @generated
 */
@ProviderType
public class AnonymisationHistoricUtil {
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
	public static void clearCache(AnonymisationHistoric anonymisationHistoric) {
		getPersistence().clearCache(anonymisationHistoric);
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
	public static List<AnonymisationHistoric> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AnonymisationHistoric> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AnonymisationHistoric> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AnonymisationHistoric update(
		AnonymisationHistoric anonymisationHistoric) {
		return getPersistence().update(anonymisationHistoric);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AnonymisationHistoric update(
		AnonymisationHistoric anonymisationHistoric,
		ServiceContext serviceContext) {
		return getPersistence().update(anonymisationHistoric, serviceContext);
	}

	/**
	* Returns all the anonymisation historics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching anonymisation historics
	*/
	public static List<AnonymisationHistoric> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the anonymisation historics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of anonymisation historics
	* @param end the upper bound of the range of anonymisation historics (not inclusive)
	* @return the range of matching anonymisation historics
	*/
	public static List<AnonymisationHistoric> findByUuid(
		java.lang.String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the anonymisation historics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of anonymisation historics
	* @param end the upper bound of the range of anonymisation historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching anonymisation historics
	*/
	public static List<AnonymisationHistoric> findByUuid(
		java.lang.String uuid, int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the anonymisation historics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of anonymisation historics
	* @param end the upper bound of the range of anonymisation historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching anonymisation historics
	*/
	public static List<AnonymisationHistoric> findByUuid(
		java.lang.String uuid, int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first anonymisation historic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching anonymisation historic
	* @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	*/
	public static AnonymisationHistoric findByUuid_First(
		java.lang.String uuid,
		OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws eu.strasbourg.service.oidc.exception.NoSuchAnonymisationHistoricException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first anonymisation historic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	*/
	public static AnonymisationHistoric fetchByUuid_First(
		java.lang.String uuid,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last anonymisation historic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching anonymisation historic
	* @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	*/
	public static AnonymisationHistoric findByUuid_Last(java.lang.String uuid,
		OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws eu.strasbourg.service.oidc.exception.NoSuchAnonymisationHistoricException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last anonymisation historic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	*/
	public static AnonymisationHistoric fetchByUuid_Last(
		java.lang.String uuid,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the anonymisation historics before and after the current anonymisation historic in the ordered set where uuid = &#63;.
	*
	* @param anonymisationHistoricId the primary key of the current anonymisation historic
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next anonymisation historic
	* @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	*/
	public static AnonymisationHistoric[] findByUuid_PrevAndNext(
		long anonymisationHistoricId, java.lang.String uuid,
		OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws eu.strasbourg.service.oidc.exception.NoSuchAnonymisationHistoricException {
		return getPersistence()
				   .findByUuid_PrevAndNext(anonymisationHistoricId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the anonymisation historics where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of anonymisation historics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching anonymisation historics
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the anonymisation historic where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchAnonymisationHistoricException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching anonymisation historic
	* @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	*/
	public static AnonymisationHistoric findByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.oidc.exception.NoSuchAnonymisationHistoricException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the anonymisation historic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	*/
	public static AnonymisationHistoric fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the anonymisation historic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	*/
	public static AnonymisationHistoric fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the anonymisation historic where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the anonymisation historic that was removed
	*/
	public static AnonymisationHistoric removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.oidc.exception.NoSuchAnonymisationHistoricException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of anonymisation historics where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching anonymisation historics
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the anonymisation historics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching anonymisation historics
	*/
	public static List<AnonymisationHistoric> findByUuid_C(
		java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the anonymisation historics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of anonymisation historics
	* @param end the upper bound of the range of anonymisation historics (not inclusive)
	* @return the range of matching anonymisation historics
	*/
	public static List<AnonymisationHistoric> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the anonymisation historics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of anonymisation historics
	* @param end the upper bound of the range of anonymisation historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching anonymisation historics
	*/
	public static List<AnonymisationHistoric> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the anonymisation historics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static List<AnonymisationHistoric> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first anonymisation historic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching anonymisation historic
	* @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	*/
	public static AnonymisationHistoric findByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws eu.strasbourg.service.oidc.exception.NoSuchAnonymisationHistoricException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first anonymisation historic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	*/
	public static AnonymisationHistoric fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last anonymisation historic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching anonymisation historic
	* @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	*/
	public static AnonymisationHistoric findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws eu.strasbourg.service.oidc.exception.NoSuchAnonymisationHistoricException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last anonymisation historic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	*/
	public static AnonymisationHistoric fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static AnonymisationHistoric[] findByUuid_C_PrevAndNext(
		long anonymisationHistoricId, java.lang.String uuid, long companyId,
		OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws eu.strasbourg.service.oidc.exception.NoSuchAnonymisationHistoricException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(anonymisationHistoricId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the anonymisation historics where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of anonymisation historics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching anonymisation historics
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the anonymisation historics where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching anonymisation historics
	*/
	public static List<AnonymisationHistoric> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the anonymisation historics where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of anonymisation historics
	* @param end the upper bound of the range of anonymisation historics (not inclusive)
	* @return the range of matching anonymisation historics
	*/
	public static List<AnonymisationHistoric> findByGroupId(long groupId,
		int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the anonymisation historics where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of anonymisation historics
	* @param end the upper bound of the range of anonymisation historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching anonymisation historics
	*/
	public static List<AnonymisationHistoric> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the anonymisation historics where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of anonymisation historics
	* @param end the upper bound of the range of anonymisation historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching anonymisation historics
	*/
	public static List<AnonymisationHistoric> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first anonymisation historic in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching anonymisation historic
	* @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	*/
	public static AnonymisationHistoric findByGroupId_First(long groupId,
		OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws eu.strasbourg.service.oidc.exception.NoSuchAnonymisationHistoricException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first anonymisation historic in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	*/
	public static AnonymisationHistoric fetchByGroupId_First(long groupId,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last anonymisation historic in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching anonymisation historic
	* @throws NoSuchAnonymisationHistoricException if a matching anonymisation historic could not be found
	*/
	public static AnonymisationHistoric findByGroupId_Last(long groupId,
		OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws eu.strasbourg.service.oidc.exception.NoSuchAnonymisationHistoricException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last anonymisation historic in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	*/
	public static AnonymisationHistoric fetchByGroupId_Last(long groupId,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the anonymisation historics before and after the current anonymisation historic in the ordered set where groupId = &#63;.
	*
	* @param anonymisationHistoricId the primary key of the current anonymisation historic
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next anonymisation historic
	* @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	*/
	public static AnonymisationHistoric[] findByGroupId_PrevAndNext(
		long anonymisationHistoricId, long groupId,
		OrderByComparator<AnonymisationHistoric> orderByComparator)
		throws eu.strasbourg.service.oidc.exception.NoSuchAnonymisationHistoricException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(anonymisationHistoricId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the anonymisation historics where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of anonymisation historics where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching anonymisation historics
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Caches the anonymisation historic in the entity cache if it is enabled.
	*
	* @param anonymisationHistoric the anonymisation historic
	*/
	public static void cacheResult(AnonymisationHistoric anonymisationHistoric) {
		getPersistence().cacheResult(anonymisationHistoric);
	}

	/**
	* Caches the anonymisation historics in the entity cache if it is enabled.
	*
	* @param anonymisationHistorics the anonymisation historics
	*/
	public static void cacheResult(
		List<AnonymisationHistoric> anonymisationHistorics) {
		getPersistence().cacheResult(anonymisationHistorics);
	}

	/**
	* Creates a new anonymisation historic with the primary key. Does not add the anonymisation historic to the database.
	*
	* @param anonymisationHistoricId the primary key for the new anonymisation historic
	* @return the new anonymisation historic
	*/
	public static AnonymisationHistoric create(long anonymisationHistoricId) {
		return getPersistence().create(anonymisationHistoricId);
	}

	/**
	* Removes the anonymisation historic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param anonymisationHistoricId the primary key of the anonymisation historic
	* @return the anonymisation historic that was removed
	* @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	*/
	public static AnonymisationHistoric remove(long anonymisationHistoricId)
		throws eu.strasbourg.service.oidc.exception.NoSuchAnonymisationHistoricException {
		return getPersistence().remove(anonymisationHistoricId);
	}

	public static AnonymisationHistoric updateImpl(
		AnonymisationHistoric anonymisationHistoric) {
		return getPersistence().updateImpl(anonymisationHistoric);
	}

	/**
	* Returns the anonymisation historic with the primary key or throws a {@link NoSuchAnonymisationHistoricException} if it could not be found.
	*
	* @param anonymisationHistoricId the primary key of the anonymisation historic
	* @return the anonymisation historic
	* @throws NoSuchAnonymisationHistoricException if a anonymisation historic with the primary key could not be found
	*/
	public static AnonymisationHistoric findByPrimaryKey(
		long anonymisationHistoricId)
		throws eu.strasbourg.service.oidc.exception.NoSuchAnonymisationHistoricException {
		return getPersistence().findByPrimaryKey(anonymisationHistoricId);
	}

	/**
	* Returns the anonymisation historic with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param anonymisationHistoricId the primary key of the anonymisation historic
	* @return the anonymisation historic, or <code>null</code> if a anonymisation historic with the primary key could not be found
	*/
	public static AnonymisationHistoric fetchByPrimaryKey(
		long anonymisationHistoricId) {
		return getPersistence().fetchByPrimaryKey(anonymisationHistoricId);
	}

	public static java.util.Map<java.io.Serializable, AnonymisationHistoric> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the anonymisation historics.
	*
	* @return the anonymisation historics
	*/
	public static List<AnonymisationHistoric> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the anonymisation historics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of anonymisation historics
	* @param end the upper bound of the range of anonymisation historics (not inclusive)
	* @return the range of anonymisation historics
	*/
	public static List<AnonymisationHistoric> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the anonymisation historics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of anonymisation historics
	* @param end the upper bound of the range of anonymisation historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of anonymisation historics
	*/
	public static List<AnonymisationHistoric> findAll(int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the anonymisation historics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of anonymisation historics
	* @param end the upper bound of the range of anonymisation historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of anonymisation historics
	*/
	public static List<AnonymisationHistoric> findAll(int start, int end,
		OrderByComparator<AnonymisationHistoric> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the anonymisation historics from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of anonymisation historics.
	*
	* @return the number of anonymisation historics
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AnonymisationHistoricPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AnonymisationHistoricPersistence, AnonymisationHistoricPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AnonymisationHistoricPersistence.class);
}