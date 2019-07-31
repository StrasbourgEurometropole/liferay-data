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

import eu.strasbourg.service.activity.model.Practice;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the practice service. This utility wraps {@link eu.strasbourg.service.activity.service.persistence.impl.PracticePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PracticePersistence
 * @see eu.strasbourg.service.activity.service.persistence.impl.PracticePersistenceImpl
 * @generated
 */
@ProviderType
public class PracticeUtil {
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
	public static void clearCache(Practice practice) {
		getPersistence().clearCache(practice);
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
	public static List<Practice> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Practice> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Practice> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Practice> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Practice update(Practice practice) {
		return getPersistence().update(practice);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Practice update(Practice practice,
		ServiceContext serviceContext) {
		return getPersistence().update(practice, serviceContext);
	}

	/**
	* Returns all the practices where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching practices
	*/
	public static List<Practice> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the practices where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @return the range of matching practices
	*/
	public static List<Practice> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the practices where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching practices
	*/
	public static List<Practice> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Practice> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the practices where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching practices
	*/
	public static List<Practice> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Practice> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first practice in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public static Practice findByUuid_First(java.lang.String uuid,
		OrderByComparator<Practice> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchPracticeException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first practice in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public static Practice fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Practice> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last practice in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public static Practice findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Practice> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchPracticeException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last practice in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public static Practice fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Practice> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the practices before and after the current practice in the ordered set where uuid = &#63;.
	*
	* @param practiceId the primary key of the current practice
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next practice
	* @throws NoSuchPracticeException if a practice with the primary key could not be found
	*/
	public static Practice[] findByUuid_PrevAndNext(long practiceId,
		java.lang.String uuid, OrderByComparator<Practice> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchPracticeException {
		return getPersistence()
				   .findByUuid_PrevAndNext(practiceId, uuid, orderByComparator);
	}

	/**
	* Removes all the practices where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of practices where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching practices
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the practice where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPracticeException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public static Practice findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.activity.exception.NoSuchPracticeException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the practice where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public static Practice fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the practice where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public static Practice fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the practice where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the practice that was removed
	*/
	public static Practice removeByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.activity.exception.NoSuchPracticeException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of practices where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching practices
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the practices where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching practices
	*/
	public static List<Practice> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the practices where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @return the range of matching practices
	*/
	public static List<Practice> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the practices where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching practices
	*/
	public static List<Practice> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Practice> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the practices where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching practices
	*/
	public static List<Practice> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Practice> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first practice in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public static Practice findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Practice> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchPracticeException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first practice in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public static Practice fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Practice> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last practice in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public static Practice findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Practice> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchPracticeException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last practice in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public static Practice fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Practice> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the practices before and after the current practice in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param practiceId the primary key of the current practice
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next practice
	* @throws NoSuchPracticeException if a practice with the primary key could not be found
	*/
	public static Practice[] findByUuid_C_PrevAndNext(long practiceId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Practice> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchPracticeException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(practiceId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the practices where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of practices where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching practices
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the practices where associationId = &#63;.
	*
	* @param associationId the association ID
	* @return the matching practices
	*/
	public static List<Practice> findByAssociation(long associationId) {
		return getPersistence().findByAssociation(associationId);
	}

	/**
	* Returns a range of all the practices where associationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param associationId the association ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @return the range of matching practices
	*/
	public static List<Practice> findByAssociation(long associationId,
		int start, int end) {
		return getPersistence().findByAssociation(associationId, start, end);
	}

	/**
	* Returns an ordered range of all the practices where associationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param associationId the association ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching practices
	*/
	public static List<Practice> findByAssociation(long associationId,
		int start, int end, OrderByComparator<Practice> orderByComparator) {
		return getPersistence()
				   .findByAssociation(associationId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the practices where associationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param associationId the association ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching practices
	*/
	public static List<Practice> findByAssociation(long associationId,
		int start, int end, OrderByComparator<Practice> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAssociation(associationId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first practice in the ordered set where associationId = &#63;.
	*
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public static Practice findByAssociation_First(long associationId,
		OrderByComparator<Practice> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchPracticeException {
		return getPersistence()
				   .findByAssociation_First(associationId, orderByComparator);
	}

	/**
	* Returns the first practice in the ordered set where associationId = &#63;.
	*
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public static Practice fetchByAssociation_First(long associationId,
		OrderByComparator<Practice> orderByComparator) {
		return getPersistence()
				   .fetchByAssociation_First(associationId, orderByComparator);
	}

	/**
	* Returns the last practice in the ordered set where associationId = &#63;.
	*
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public static Practice findByAssociation_Last(long associationId,
		OrderByComparator<Practice> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchPracticeException {
		return getPersistence()
				   .findByAssociation_Last(associationId, orderByComparator);
	}

	/**
	* Returns the last practice in the ordered set where associationId = &#63;.
	*
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public static Practice fetchByAssociation_Last(long associationId,
		OrderByComparator<Practice> orderByComparator) {
		return getPersistence()
				   .fetchByAssociation_Last(associationId, orderByComparator);
	}

	/**
	* Returns the practices before and after the current practice in the ordered set where associationId = &#63;.
	*
	* @param practiceId the primary key of the current practice
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next practice
	* @throws NoSuchPracticeException if a practice with the primary key could not be found
	*/
	public static Practice[] findByAssociation_PrevAndNext(long practiceId,
		long associationId, OrderByComparator<Practice> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchPracticeException {
		return getPersistence()
				   .findByAssociation_PrevAndNext(practiceId, associationId,
			orderByComparator);
	}

	/**
	* Removes all the practices where associationId = &#63; from the database.
	*
	* @param associationId the association ID
	*/
	public static void removeByAssociation(long associationId) {
		getPersistence().removeByAssociation(associationId);
	}

	/**
	* Returns the number of practices where associationId = &#63;.
	*
	* @param associationId the association ID
	* @return the number of matching practices
	*/
	public static int countByAssociation(long associationId) {
		return getPersistence().countByAssociation(associationId);
	}

	/**
	* Returns all the practices where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching practices
	*/
	public static List<Practice> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the practices where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @return the range of matching practices
	*/
	public static List<Practice> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the practices where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching practices
	*/
	public static List<Practice> findByGroupId(long groupId, int start,
		int end, OrderByComparator<Practice> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the practices where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching practices
	*/
	public static List<Practice> findByGroupId(long groupId, int start,
		int end, OrderByComparator<Practice> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first practice in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public static Practice findByGroupId_First(long groupId,
		OrderByComparator<Practice> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchPracticeException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first practice in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public static Practice fetchByGroupId_First(long groupId,
		OrderByComparator<Practice> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last practice in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching practice
	* @throws NoSuchPracticeException if a matching practice could not be found
	*/
	public static Practice findByGroupId_Last(long groupId,
		OrderByComparator<Practice> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchPracticeException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last practice in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public static Practice fetchByGroupId_Last(long groupId,
		OrderByComparator<Practice> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the practices before and after the current practice in the ordered set where groupId = &#63;.
	*
	* @param practiceId the primary key of the current practice
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next practice
	* @throws NoSuchPracticeException if a practice with the primary key could not be found
	*/
	public static Practice[] findByGroupId_PrevAndNext(long practiceId,
		long groupId, OrderByComparator<Practice> orderByComparator)
		throws eu.strasbourg.service.activity.exception.NoSuchPracticeException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(practiceId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the practices where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of practices where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching practices
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Caches the practice in the entity cache if it is enabled.
	*
	* @param practice the practice
	*/
	public static void cacheResult(Practice practice) {
		getPersistence().cacheResult(practice);
	}

	/**
	* Caches the practices in the entity cache if it is enabled.
	*
	* @param practices the practices
	*/
	public static void cacheResult(List<Practice> practices) {
		getPersistence().cacheResult(practices);
	}

	/**
	* Creates a new practice with the primary key. Does not add the practice to the database.
	*
	* @param practiceId the primary key for the new practice
	* @return the new practice
	*/
	public static Practice create(long practiceId) {
		return getPersistence().create(practiceId);
	}

	/**
	* Removes the practice with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param practiceId the primary key of the practice
	* @return the practice that was removed
	* @throws NoSuchPracticeException if a practice with the primary key could not be found
	*/
	public static Practice remove(long practiceId)
		throws eu.strasbourg.service.activity.exception.NoSuchPracticeException {
		return getPersistence().remove(practiceId);
	}

	public static Practice updateImpl(Practice practice) {
		return getPersistence().updateImpl(practice);
	}

	/**
	* Returns the practice with the primary key or throws a {@link NoSuchPracticeException} if it could not be found.
	*
	* @param practiceId the primary key of the practice
	* @return the practice
	* @throws NoSuchPracticeException if a practice with the primary key could not be found
	*/
	public static Practice findByPrimaryKey(long practiceId)
		throws eu.strasbourg.service.activity.exception.NoSuchPracticeException {
		return getPersistence().findByPrimaryKey(practiceId);
	}

	/**
	* Returns the practice with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param practiceId the primary key of the practice
	* @return the practice, or <code>null</code> if a practice with the primary key could not be found
	*/
	public static Practice fetchByPrimaryKey(long practiceId) {
		return getPersistence().fetchByPrimaryKey(practiceId);
	}

	public static java.util.Map<java.io.Serializable, Practice> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the practices.
	*
	* @return the practices
	*/
	public static List<Practice> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the practices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @return the range of practices
	*/
	public static List<Practice> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the practices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of practices
	*/
	public static List<Practice> findAll(int start, int end,
		OrderByComparator<Practice> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the practices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of practices
	*/
	public static List<Practice> findAll(int start, int end,
		OrderByComparator<Practice> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the practices from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of practices.
	*
	* @return the number of practices
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PracticePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PracticePersistence, PracticePersistence> _serviceTracker =
		ServiceTrackerFactory.open(PracticePersistence.class);
}