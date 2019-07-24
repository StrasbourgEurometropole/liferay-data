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

package eu.strasbourg.service.activity.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.activity.model.AssociationActivity;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the association activity service. This utility wraps {@link eu.strasbourg.service.activity.service.persistence.impl.AssociationActivityPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssociationActivityPersistence
 * @see eu.strasbourg.service.activity.service.persistence.impl.AssociationActivityPersistenceImpl
 * @generated
 */
@ProviderType
public class AssociationActivityUtil {
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
	public static void clearCache(AssociationActivity associationActivity) {
		getPersistence().clearCache(associationActivity);
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
	public static List<AssociationActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssociationActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssociationActivity> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AssociationActivity update(
		AssociationActivity associationActivity) {
		return getPersistence().update(associationActivity);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AssociationActivity update(
		AssociationActivity associationActivity, ServiceContext serviceContext) {
		return getPersistence().update(associationActivity, serviceContext);
	}

	/**
	* Returns all the association activities where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching association activities
	*/
	public static List<AssociationActivity> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the association activities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @return the range of matching association activities
	*/
	public static List<AssociationActivity> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the association activities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching association activities
	*/
	public static List<AssociationActivity> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the association activities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching association activities
	*/
	public static List<AssociationActivity> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first association activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public static AssociationActivity findByUuid_First(java.lang.String uuid,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first association activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public static AssociationActivity fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last association activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public static AssociationActivity findByUuid_Last(java.lang.String uuid,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last association activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public static AssociationActivity fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the association activities before and after the current association activity in the ordered set where uuid = &#63;.
	*
	* @param associationActivityId the primary key of the current association activity
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next association activity
	* @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	*/
	public static AssociationActivity[] findByUuid_PrevAndNext(
		long associationActivityId, java.lang.String uuid,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException {
		return getPersistence()
				   .findByUuid_PrevAndNext(associationActivityId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the association activities where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of association activities where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching association activities
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the association activity where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchAssociationActivityException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public static AssociationActivity findByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the association activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public static AssociationActivity fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the association activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public static AssociationActivity fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the association activity where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the association activity that was removed
	*/
	public static AssociationActivity removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of association activities where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching association activities
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the association activities where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching association activities
	*/
	public static List<AssociationActivity> findByUuid_C(
		java.lang.String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the association activities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @return the range of matching association activities
	*/
	public static List<AssociationActivity> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the association activities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching association activities
	*/
	public static List<AssociationActivity> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the association activities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching association activities
	*/
	public static List<AssociationActivity> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first association activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public static AssociationActivity findByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first association activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public static AssociationActivity fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last association activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public static AssociationActivity findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<AssociationActivity> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last association activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public static AssociationActivity fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the association activities before and after the current association activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param associationActivityId the primary key of the current association activity
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next association activity
	* @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	*/
	public static AssociationActivity[] findByUuid_C_PrevAndNext(
		long associationActivityId, java.lang.String uuid, long companyId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(associationActivityId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the association activities where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of association activities where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching association activities
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the association activities where associationId = &#63;.
	*
	* @param associationId the association ID
	* @return the matching association activities
	*/
	public static List<AssociationActivity> findByAssociation(
		long associationId) {
		return getPersistence().findByAssociation(associationId);
	}

	/**
	* Returns a range of all the association activities where associationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param associationId the association ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @return the range of matching association activities
	*/
	public static List<AssociationActivity> findByAssociation(
		long associationId, int start, int end) {
		return getPersistence().findByAssociation(associationId, start, end);
	}

	/**
	* Returns an ordered range of all the association activities where associationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param associationId the association ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching association activities
	*/
	public static List<AssociationActivity> findByAssociation(
		long associationId, int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return getPersistence()
				   .findByAssociation(associationId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the association activities where associationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param associationId the association ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching association activities
	*/
	public static List<AssociationActivity> findByAssociation(
		long associationId, int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAssociation(associationId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first association activity in the ordered set where associationId = &#63;.
	*
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public static AssociationActivity findByAssociation_First(
		long associationId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException {
		return getPersistence()
				   .findByAssociation_First(associationId, orderByComparator);
	}

	/**
	* Returns the first association activity in the ordered set where associationId = &#63;.
	*
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public static AssociationActivity fetchByAssociation_First(
		long associationId,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return getPersistence()
				   .fetchByAssociation_First(associationId, orderByComparator);
	}

	/**
	* Returns the last association activity in the ordered set where associationId = &#63;.
	*
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public static AssociationActivity findByAssociation_Last(
		long associationId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException {
		return getPersistence()
				   .findByAssociation_Last(associationId, orderByComparator);
	}

	/**
	* Returns the last association activity in the ordered set where associationId = &#63;.
	*
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public static AssociationActivity fetchByAssociation_Last(
		long associationId,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return getPersistence()
				   .fetchByAssociation_Last(associationId, orderByComparator);
	}

	/**
	* Returns the association activities before and after the current association activity in the ordered set where associationId = &#63;.
	*
	* @param associationActivityId the primary key of the current association activity
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next association activity
	* @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	*/
	public static AssociationActivity[] findByAssociation_PrevAndNext(
		long associationActivityId, long associationId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException {
		return getPersistence()
				   .findByAssociation_PrevAndNext(associationActivityId,
			associationId, orderByComparator);
	}

	/**
	* Removes all the association activities where associationId = &#63; from the database.
	*
	* @param associationId the association ID
	*/
	public static void removeByAssociation(long associationId) {
		getPersistence().removeByAssociation(associationId);
	}

	/**
	* Returns the number of association activities where associationId = &#63;.
	*
	* @param associationId the association ID
	* @return the number of matching association activities
	*/
	public static int countByAssociation(long associationId) {
		return getPersistence().countByAssociation(associationId);
	}

	/**
	* Returns all the association activities where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching association activities
	*/
	public static List<AssociationActivity> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the association activities where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @return the range of matching association activities
	*/
	public static List<AssociationActivity> findByGroupId(long groupId,
		int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the association activities where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching association activities
	*/
	public static List<AssociationActivity> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the association activities where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching association activities
	*/
	public static List<AssociationActivity> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first association activity in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public static AssociationActivity findByGroupId_First(long groupId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first association activity in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public static AssociationActivity fetchByGroupId_First(long groupId,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last association activity in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public static AssociationActivity findByGroupId_Last(long groupId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last association activity in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public static AssociationActivity fetchByGroupId_Last(long groupId,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the association activities before and after the current association activity in the ordered set where groupId = &#63;.
	*
	* @param associationActivityId the primary key of the current association activity
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next association activity
	* @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	*/
	public static AssociationActivity[] findByGroupId_PrevAndNext(
		long associationActivityId, long groupId,
		OrderByComparator<AssociationActivity> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(associationActivityId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the association activities where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of association activities where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching association activities
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Caches the association activity in the entity cache if it is enabled.
	*
	* @param associationActivity the association activity
	*/
	public static void cacheResult(AssociationActivity associationActivity) {
		getPersistence().cacheResult(associationActivity);
	}

	/**
	* Caches the association activities in the entity cache if it is enabled.
	*
	* @param associationActivities the association activities
	*/
	public static void cacheResult(
		List<AssociationActivity> associationActivities) {
		getPersistence().cacheResult(associationActivities);
	}

	/**
	* Creates a new association activity with the primary key. Does not add the association activity to the database.
	*
	* @param associationActivityId the primary key for the new association activity
	* @return the new association activity
	*/
	public static AssociationActivity create(long associationActivityId) {
		return getPersistence().create(associationActivityId);
	}

	/**
	* Removes the association activity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param associationActivityId the primary key of the association activity
	* @return the association activity that was removed
	* @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	*/
	public static AssociationActivity remove(long associationActivityId)
		throws eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException {
		return getPersistence().remove(associationActivityId);
	}

	public static AssociationActivity updateImpl(
		AssociationActivity associationActivity) {
		return getPersistence().updateImpl(associationActivity);
	}

	/**
	* Returns the association activity with the primary key or throws a {@link NoSuchAssociationActivityException} if it could not be found.
	*
	* @param associationActivityId the primary key of the association activity
	* @return the association activity
	* @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	*/
	public static AssociationActivity findByPrimaryKey(
		long associationActivityId)
		throws eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException {
		return getPersistence().findByPrimaryKey(associationActivityId);
	}

	/**
	* Returns the association activity with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param associationActivityId the primary key of the association activity
	* @return the association activity, or <code>null</code> if a association activity with the primary key could not be found
	*/
	public static AssociationActivity fetchByPrimaryKey(
		long associationActivityId) {
		return getPersistence().fetchByPrimaryKey(associationActivityId);
	}

	public static java.util.Map<java.io.Serializable, AssociationActivity> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the association activities.
	*
	* @return the association activities
	*/
	public static List<AssociationActivity> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the association activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @return the range of association activities
	*/
	public static List<AssociationActivity> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the association activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of association activities
	*/
	public static List<AssociationActivity> findAll(int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the association activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of association activities
	*/
	public static List<AssociationActivity> findAll(int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the association activities from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of association activities.
	*
	* @return the number of association activities
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AssociationActivityPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AssociationActivityPersistence, AssociationActivityPersistence> _serviceTracker =
		ServiceTrackerFactory.open(AssociationActivityPersistence.class);
}