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
import eu.strasbourg.service.project.model.Signataire;
import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the signataire service. This utility wraps {@link eu.strasbourg.service.project.service.persistence.impl.SignatairePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see SignatairePersistence
 * @see eu.strasbourg.service.project.service.persistence.impl.SignatairePersistenceImpl
 * @generated
 */
@ProviderType
public class SignataireUtil {
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
	public static void clearCache(Signataire signataire) {
		getPersistence().clearCache(signataire);
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
	public static List<Signataire> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Signataire> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Signataire> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Signataire> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Signataire update(Signataire signataire) {
		return getPersistence().update(signataire);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Signataire update(Signataire signataire,
		ServiceContext serviceContext) {
		return getPersistence().update(signataire, serviceContext);
	}

	/**
	* Returns all the signataires where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching signataires
	*/
	public static List<Signataire> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the signataires where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @return the range of matching signataires
	*/
	public static List<Signataire> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the signataires where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching signataires
	*/
	public static List<Signataire> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Signataire> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the signataires where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching signataires
	*/
	public static List<Signataire> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Signataire> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first signataire in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public static Signataire findByUuid_First(java.lang.String uuid,
		OrderByComparator<Signataire> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first signataire in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public static Signataire fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Signataire> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last signataire in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public static Signataire findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Signataire> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last signataire in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public static Signataire fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Signataire> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the signataires before and after the current signataire in the ordered set where uuid = &#63;.
	*
	* @param signataireId the primary key of the current signataire
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next signataire
	* @throws NoSuchSignataireException if a signataire with the primary key could not be found
	*/
	public static Signataire[] findByUuid_PrevAndNext(long signataireId,
		java.lang.String uuid, OrderByComparator<Signataire> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence()
				   .findByUuid_PrevAndNext(signataireId, uuid, orderByComparator);
	}

	/**
	* Removes all the signataires where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of signataires where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching signataires
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the signataire where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSignataireException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public static Signataire findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the signataire where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public static Signataire fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the signataire where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public static Signataire fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the signataire where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the signataire that was removed
	*/
	public static Signataire removeByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of signataires where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching signataires
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the signataires where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching signataires
	*/
	public static List<Signataire> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the signataires where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @return the range of matching signataires
	*/
	public static List<Signataire> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the signataires where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching signataires
	*/
	public static List<Signataire> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Signataire> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the signataires where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching signataires
	*/
	public static List<Signataire> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Signataire> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first signataire in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public static Signataire findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Signataire> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first signataire in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public static Signataire fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Signataire> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last signataire in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public static Signataire findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Signataire> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last signataire in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public static Signataire fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Signataire> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the signataires before and after the current signataire in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param signataireId the primary key of the current signataire
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next signataire
	* @throws NoSuchSignataireException if a signataire with the primary key could not be found
	*/
	public static Signataire[] findByUuid_C_PrevAndNext(long signataireId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Signataire> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(signataireId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the signataires where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of signataires where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching signataires
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the signataires where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching signataires
	*/
	public static List<Signataire> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the signataires where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @return the range of matching signataires
	*/
	public static List<Signataire> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the signataires where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching signataires
	*/
	public static List<Signataire> findByGroupId(long groupId, int start,
		int end, OrderByComparator<Signataire> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the signataires where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching signataires
	*/
	public static List<Signataire> findByGroupId(long groupId, int start,
		int end, OrderByComparator<Signataire> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first signataire in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public static Signataire findByGroupId_First(long groupId,
		OrderByComparator<Signataire> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first signataire in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public static Signataire fetchByGroupId_First(long groupId,
		OrderByComparator<Signataire> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last signataire in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public static Signataire findByGroupId_Last(long groupId,
		OrderByComparator<Signataire> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last signataire in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public static Signataire fetchByGroupId_Last(long groupId,
		OrderByComparator<Signataire> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the signataires before and after the current signataire in the ordered set where groupId = &#63;.
	*
	* @param signataireId the primary key of the current signataire
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next signataire
	* @throws NoSuchSignataireException if a signataire with the primary key could not be found
	*/
	public static Signataire[] findByGroupId_PrevAndNext(long signataireId,
		long groupId, OrderByComparator<Signataire> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(signataireId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the signataires where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of signataires where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching signataires
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the signataires where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @return the matching signataires
	*/
	public static List<Signataire> findByPetition(long petitionId) {
		return getPersistence().findByPetition(petitionId);
	}

	/**
	* Returns a range of all the signataires where petitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param petitionId the petition ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @return the range of matching signataires
	*/
	public static List<Signataire> findByPetition(long petitionId, int start,
		int end) {
		return getPersistence().findByPetition(petitionId, start, end);
	}

	/**
	* Returns an ordered range of all the signataires where petitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param petitionId the petition ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching signataires
	*/
	public static List<Signataire> findByPetition(long petitionId, int start,
		int end, OrderByComparator<Signataire> orderByComparator) {
		return getPersistence()
				   .findByPetition(petitionId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the signataires where petitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param petitionId the petition ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching signataires
	*/
	public static List<Signataire> findByPetition(long petitionId, int start,
		int end, OrderByComparator<Signataire> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPetition(petitionId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first signataire in the ordered set where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public static Signataire findByPetition_First(long petitionId,
		OrderByComparator<Signataire> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence()
				   .findByPetition_First(petitionId, orderByComparator);
	}

	/**
	* Returns the first signataire in the ordered set where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public static Signataire fetchByPetition_First(long petitionId,
		OrderByComparator<Signataire> orderByComparator) {
		return getPersistence()
				   .fetchByPetition_First(petitionId, orderByComparator);
	}

	/**
	* Returns the last signataire in the ordered set where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public static Signataire findByPetition_Last(long petitionId,
		OrderByComparator<Signataire> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence()
				   .findByPetition_Last(petitionId, orderByComparator);
	}

	/**
	* Returns the last signataire in the ordered set where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public static Signataire fetchByPetition_Last(long petitionId,
		OrderByComparator<Signataire> orderByComparator) {
		return getPersistence()
				   .fetchByPetition_Last(petitionId, orderByComparator);
	}

	/**
	* Returns the signataires before and after the current signataire in the ordered set where petitionId = &#63;.
	*
	* @param signataireId the primary key of the current signataire
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next signataire
	* @throws NoSuchSignataireException if a signataire with the primary key could not be found
	*/
	public static Signataire[] findByPetition_PrevAndNext(long signataireId,
		long petitionId, OrderByComparator<Signataire> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence()
				   .findByPetition_PrevAndNext(signataireId, petitionId,
			orderByComparator);
	}

	/**
	* Removes all the signataires where petitionId = &#63; from the database.
	*
	* @param petitionId the petition ID
	*/
	public static void removeByPetition(long petitionId) {
		getPersistence().removeByPetition(petitionId);
	}

	/**
	* Returns the number of signataires where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @return the number of matching signataires
	*/
	public static int countByPetition(long petitionId) {
		return getPersistence().countByPetition(petitionId);
	}

	/**
	* Returns all the signataires where petitionId = &#63; and signataireName = &#63;.
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @return the matching signataires
	*/
	public static List<Signataire> findByPetitionIdAndSignataireName(
		long petitionId, java.lang.String signataireName) {
		return getPersistence()
				   .findByPetitionIdAndSignataireName(petitionId, signataireName);
	}

	/**
	* Returns a range of all the signataires where petitionId = &#63; and signataireName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @return the range of matching signataires
	*/
	public static List<Signataire> findByPetitionIdAndSignataireName(
		long petitionId, java.lang.String signataireName, int start, int end) {
		return getPersistence()
				   .findByPetitionIdAndSignataireName(petitionId,
			signataireName, start, end);
	}

	/**
	* Returns an ordered range of all the signataires where petitionId = &#63; and signataireName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching signataires
	*/
	public static List<Signataire> findByPetitionIdAndSignataireName(
		long petitionId, java.lang.String signataireName, int start, int end,
		OrderByComparator<Signataire> orderByComparator) {
		return getPersistence()
				   .findByPetitionIdAndSignataireName(petitionId,
			signataireName, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the signataires where petitionId = &#63; and signataireName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching signataires
	*/
	public static List<Signataire> findByPetitionIdAndSignataireName(
		long petitionId, java.lang.String signataireName, int start, int end,
		OrderByComparator<Signataire> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPetitionIdAndSignataireName(petitionId,
			signataireName, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first signataire in the ordered set where petitionId = &#63; and signataireName = &#63;.
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public static Signataire findByPetitionIdAndSignataireName_First(
		long petitionId, java.lang.String signataireName,
		OrderByComparator<Signataire> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence()
				   .findByPetitionIdAndSignataireName_First(petitionId,
			signataireName, orderByComparator);
	}

	/**
	* Returns the first signataire in the ordered set where petitionId = &#63; and signataireName = &#63;.
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public static Signataire fetchByPetitionIdAndSignataireName_First(
		long petitionId, java.lang.String signataireName,
		OrderByComparator<Signataire> orderByComparator) {
		return getPersistence()
				   .fetchByPetitionIdAndSignataireName_First(petitionId,
			signataireName, orderByComparator);
	}

	/**
	* Returns the last signataire in the ordered set where petitionId = &#63; and signataireName = &#63;.
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public static Signataire findByPetitionIdAndSignataireName_Last(
		long petitionId, java.lang.String signataireName,
		OrderByComparator<Signataire> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence()
				   .findByPetitionIdAndSignataireName_Last(petitionId,
			signataireName, orderByComparator);
	}

	/**
	* Returns the last signataire in the ordered set where petitionId = &#63; and signataireName = &#63;.
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public static Signataire fetchByPetitionIdAndSignataireName_Last(
		long petitionId, java.lang.String signataireName,
		OrderByComparator<Signataire> orderByComparator) {
		return getPersistence()
				   .fetchByPetitionIdAndSignataireName_Last(petitionId,
			signataireName, orderByComparator);
	}

	/**
	* Returns the signataires before and after the current signataire in the ordered set where petitionId = &#63; and signataireName = &#63;.
	*
	* @param signataireId the primary key of the current signataire
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next signataire
	* @throws NoSuchSignataireException if a signataire with the primary key could not be found
	*/
	public static Signataire[] findByPetitionIdAndSignataireName_PrevAndNext(
		long signataireId, long petitionId, java.lang.String signataireName,
		OrderByComparator<Signataire> orderByComparator)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence()
				   .findByPetitionIdAndSignataireName_PrevAndNext(signataireId,
			petitionId, signataireName, orderByComparator);
	}

	/**
	* Removes all the signataires where petitionId = &#63; and signataireName = &#63; from the database.
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	*/
	public static void removeByPetitionIdAndSignataireName(long petitionId,
		java.lang.String signataireName) {
		getPersistence()
			.removeByPetitionIdAndSignataireName(petitionId, signataireName);
	}

	/**
	* Returns the number of signataires where petitionId = &#63; and signataireName = &#63;.
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @return the number of matching signataires
	*/
	public static int countByPetitionIdAndSignataireName(long petitionId,
		java.lang.String signataireName) {
		return getPersistence()
				   .countByPetitionIdAndSignataireName(petitionId,
			signataireName);
	}

	/**
	* Caches the signataire in the entity cache if it is enabled.
	*
	* @param signataire the signataire
	*/
	public static void cacheResult(Signataire signataire) {
		getPersistence().cacheResult(signataire);
	}

	/**
	* Caches the signataires in the entity cache if it is enabled.
	*
	* @param signataires the signataires
	*/
	public static void cacheResult(List<Signataire> signataires) {
		getPersistence().cacheResult(signataires);
	}

	/**
	* Creates a new signataire with the primary key. Does not add the signataire to the database.
	*
	* @param signataireId the primary key for the new signataire
	* @return the new signataire
	*/
	public static Signataire create(long signataireId) {
		return getPersistence().create(signataireId);
	}

	/**
	* Removes the signataire with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param signataireId the primary key of the signataire
	* @return the signataire that was removed
	* @throws NoSuchSignataireException if a signataire with the primary key could not be found
	*/
	public static Signataire remove(long signataireId)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence().remove(signataireId);
	}

	public static Signataire updateImpl(Signataire signataire) {
		return getPersistence().updateImpl(signataire);
	}

	/**
	* Returns the signataire with the primary key or throws a {@link NoSuchSignataireException} if it could not be found.
	*
	* @param signataireId the primary key of the signataire
	* @return the signataire
	* @throws NoSuchSignataireException if a signataire with the primary key could not be found
	*/
	public static Signataire findByPrimaryKey(long signataireId)
		throws eu.strasbourg.service.project.exception.NoSuchSignataireException {
		return getPersistence().findByPrimaryKey(signataireId);
	}

	/**
	* Returns the signataire with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param signataireId the primary key of the signataire
	* @return the signataire, or <code>null</code> if a signataire with the primary key could not be found
	*/
	public static Signataire fetchByPrimaryKey(long signataireId) {
		return getPersistence().fetchByPrimaryKey(signataireId);
	}

	public static java.util.Map<java.io.Serializable, Signataire> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the signataires.
	*
	* @return the signataires
	*/
	public static List<Signataire> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the signataires.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @return the range of signataires
	*/
	public static List<Signataire> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the signataires.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of signataires
	*/
	public static List<Signataire> findAll(int start, int end,
		OrderByComparator<Signataire> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the signataires.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of signataires
	*/
	public static List<Signataire> findAll(int start, int end,
		OrderByComparator<Signataire> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the signataires from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of signataires.
	*
	* @return the number of signataires
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SignatairePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SignatairePersistence, SignatairePersistence> _serviceTracker =
		ServiceTrackerFactory.open(SignatairePersistence.class);
}