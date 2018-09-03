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

package eu.strasbourg.service.comment.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.comment.model.Signalement;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the signalement service. This utility wraps {@link eu.strasbourg.service.comment.service.persistence.impl.SignalementPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Romain Vergnais
 * @see SignalementPersistence
 * @see eu.strasbourg.service.comment.service.persistence.impl.SignalementPersistenceImpl
 * @generated
 */
@ProviderType
public class SignalementUtil {
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
	public static void clearCache(Signalement signalement) {
		getPersistence().clearCache(signalement);
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
	public static List<Signalement> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Signalement> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Signalement> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Signalement> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Signalement update(Signalement signalement) {
		return getPersistence().update(signalement);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Signalement update(Signalement signalement,
		ServiceContext serviceContext) {
		return getPersistence().update(signalement, serviceContext);
	}

	/**
	* Returns all the signalements where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching signalements
	*/
	public static List<Signalement> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the signalements where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @return the range of matching signalements
	*/
	public static List<Signalement> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the signalements where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching signalements
	*/
	public static List<Signalement> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<Signalement> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the signalements where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching signalements
	*/
	public static List<Signalement> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<Signalement> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first signalement in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signalement
	* @throws NoSuchSignalementException if a matching signalement could not be found
	*/
	public static Signalement findByUuid_First(java.lang.String uuid,
		OrderByComparator<Signalement> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchSignalementException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first signalement in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signalement, or <code>null</code> if a matching signalement could not be found
	*/
	public static Signalement fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Signalement> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last signalement in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signalement
	* @throws NoSuchSignalementException if a matching signalement could not be found
	*/
	public static Signalement findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Signalement> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchSignalementException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last signalement in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signalement, or <code>null</code> if a matching signalement could not be found
	*/
	public static Signalement fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Signalement> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the signalements before and after the current signalement in the ordered set where uuid = &#63;.
	*
	* @param signalementId the primary key of the current signalement
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next signalement
	* @throws NoSuchSignalementException if a signalement with the primary key could not be found
	*/
	public static Signalement[] findByUuid_PrevAndNext(long signalementId,
		java.lang.String uuid, OrderByComparator<Signalement> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchSignalementException {
		return getPersistence()
				   .findByUuid_PrevAndNext(signalementId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the signalements where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of signalements where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching signalements
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the signalement where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSignalementException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching signalement
	* @throws NoSuchSignalementException if a matching signalement could not be found
	*/
	public static Signalement findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.comment.exception.NoSuchSignalementException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the signalement where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching signalement, or <code>null</code> if a matching signalement could not be found
	*/
	public static Signalement fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the signalement where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching signalement, or <code>null</code> if a matching signalement could not be found
	*/
	public static Signalement fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the signalement where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the signalement that was removed
	*/
	public static Signalement removeByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.comment.exception.NoSuchSignalementException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of signalements where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching signalements
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the signalements where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching signalements
	*/
	public static List<Signalement> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the signalements where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @return the range of matching signalements
	*/
	public static List<Signalement> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the signalements where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching signalements
	*/
	public static List<Signalement> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Signalement> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the signalements where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching signalements
	*/
	public static List<Signalement> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Signalement> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first signalement in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signalement
	* @throws NoSuchSignalementException if a matching signalement could not be found
	*/
	public static Signalement findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Signalement> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchSignalementException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first signalement in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signalement, or <code>null</code> if a matching signalement could not be found
	*/
	public static Signalement fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Signalement> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last signalement in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signalement
	* @throws NoSuchSignalementException if a matching signalement could not be found
	*/
	public static Signalement findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Signalement> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchSignalementException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last signalement in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signalement, or <code>null</code> if a matching signalement could not be found
	*/
	public static Signalement fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Signalement> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the signalements before and after the current signalement in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param signalementId the primary key of the current signalement
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next signalement
	* @throws NoSuchSignalementException if a signalement with the primary key could not be found
	*/
	public static Signalement[] findByUuid_C_PrevAndNext(long signalementId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Signalement> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchSignalementException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(signalementId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the signalements where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of signalements where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching signalements
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the signalements where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching signalements
	*/
	public static List<Signalement> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the signalements where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @return the range of matching signalements
	*/
	public static List<Signalement> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the signalements where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching signalements
	*/
	public static List<Signalement> findByGroupId(long groupId, int start,
		int end, OrderByComparator<Signalement> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the signalements where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching signalements
	*/
	public static List<Signalement> findByGroupId(long groupId, int start,
		int end, OrderByComparator<Signalement> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first signalement in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signalement
	* @throws NoSuchSignalementException if a matching signalement could not be found
	*/
	public static Signalement findByGroupId_First(long groupId,
		OrderByComparator<Signalement> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchSignalementException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first signalement in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signalement, or <code>null</code> if a matching signalement could not be found
	*/
	public static Signalement fetchByGroupId_First(long groupId,
		OrderByComparator<Signalement> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last signalement in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signalement
	* @throws NoSuchSignalementException if a matching signalement could not be found
	*/
	public static Signalement findByGroupId_Last(long groupId,
		OrderByComparator<Signalement> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchSignalementException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last signalement in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signalement, or <code>null</code> if a matching signalement could not be found
	*/
	public static Signalement fetchByGroupId_Last(long groupId,
		OrderByComparator<Signalement> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the signalements before and after the current signalement in the ordered set where groupId = &#63;.
	*
	* @param signalementId the primary key of the current signalement
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next signalement
	* @throws NoSuchSignalementException if a signalement with the primary key could not be found
	*/
	public static Signalement[] findByGroupId_PrevAndNext(long signalementId,
		long groupId, OrderByComparator<Signalement> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchSignalementException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(signalementId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the signalements where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of signalements where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching signalements
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the signalements where commentId = &#63;.
	*
	* @param commentId the comment ID
	* @return the matching signalements
	*/
	public static List<Signalement> findByCommentId(long commentId) {
		return getPersistence().findByCommentId(commentId);
	}

	/**
	* Returns a range of all the signalements where commentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commentId the comment ID
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @return the range of matching signalements
	*/
	public static List<Signalement> findByCommentId(long commentId, int start,
		int end) {
		return getPersistence().findByCommentId(commentId, start, end);
	}

	/**
	* Returns an ordered range of all the signalements where commentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commentId the comment ID
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching signalements
	*/
	public static List<Signalement> findByCommentId(long commentId, int start,
		int end, OrderByComparator<Signalement> orderByComparator) {
		return getPersistence()
				   .findByCommentId(commentId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the signalements where commentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param commentId the comment ID
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching signalements
	*/
	public static List<Signalement> findByCommentId(long commentId, int start,
		int end, OrderByComparator<Signalement> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCommentId(commentId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first signalement in the ordered set where commentId = &#63;.
	*
	* @param commentId the comment ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signalement
	* @throws NoSuchSignalementException if a matching signalement could not be found
	*/
	public static Signalement findByCommentId_First(long commentId,
		OrderByComparator<Signalement> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchSignalementException {
		return getPersistence()
				   .findByCommentId_First(commentId, orderByComparator);
	}

	/**
	* Returns the first signalement in the ordered set where commentId = &#63;.
	*
	* @param commentId the comment ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signalement, or <code>null</code> if a matching signalement could not be found
	*/
	public static Signalement fetchByCommentId_First(long commentId,
		OrderByComparator<Signalement> orderByComparator) {
		return getPersistence()
				   .fetchByCommentId_First(commentId, orderByComparator);
	}

	/**
	* Returns the last signalement in the ordered set where commentId = &#63;.
	*
	* @param commentId the comment ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signalement
	* @throws NoSuchSignalementException if a matching signalement could not be found
	*/
	public static Signalement findByCommentId_Last(long commentId,
		OrderByComparator<Signalement> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchSignalementException {
		return getPersistence()
				   .findByCommentId_Last(commentId, orderByComparator);
	}

	/**
	* Returns the last signalement in the ordered set where commentId = &#63;.
	*
	* @param commentId the comment ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signalement, or <code>null</code> if a matching signalement could not be found
	*/
	public static Signalement fetchByCommentId_Last(long commentId,
		OrderByComparator<Signalement> orderByComparator) {
		return getPersistence()
				   .fetchByCommentId_Last(commentId, orderByComparator);
	}

	/**
	* Returns the signalements before and after the current signalement in the ordered set where commentId = &#63;.
	*
	* @param signalementId the primary key of the current signalement
	* @param commentId the comment ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next signalement
	* @throws NoSuchSignalementException if a signalement with the primary key could not be found
	*/
	public static Signalement[] findByCommentId_PrevAndNext(
		long signalementId, long commentId,
		OrderByComparator<Signalement> orderByComparator)
		throws eu.strasbourg.service.comment.exception.NoSuchSignalementException {
		return getPersistence()
				   .findByCommentId_PrevAndNext(signalementId, commentId,
			orderByComparator);
	}

	/**
	* Removes all the signalements where commentId = &#63; from the database.
	*
	* @param commentId the comment ID
	*/
	public static void removeByCommentId(long commentId) {
		getPersistence().removeByCommentId(commentId);
	}

	/**
	* Returns the number of signalements where commentId = &#63;.
	*
	* @param commentId the comment ID
	* @return the number of matching signalements
	*/
	public static int countByCommentId(long commentId) {
		return getPersistence().countByCommentId(commentId);
	}

	/**
	* Caches the signalement in the entity cache if it is enabled.
	*
	* @param signalement the signalement
	*/
	public static void cacheResult(Signalement signalement) {
		getPersistence().cacheResult(signalement);
	}

	/**
	* Caches the signalements in the entity cache if it is enabled.
	*
	* @param signalements the signalements
	*/
	public static void cacheResult(List<Signalement> signalements) {
		getPersistence().cacheResult(signalements);
	}

	/**
	* Creates a new signalement with the primary key. Does not add the signalement to the database.
	*
	* @param signalementId the primary key for the new signalement
	* @return the new signalement
	*/
	public static Signalement create(long signalementId) {
		return getPersistence().create(signalementId);
	}

	/**
	* Removes the signalement with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param signalementId the primary key of the signalement
	* @return the signalement that was removed
	* @throws NoSuchSignalementException if a signalement with the primary key could not be found
	*/
	public static Signalement remove(long signalementId)
		throws eu.strasbourg.service.comment.exception.NoSuchSignalementException {
		return getPersistence().remove(signalementId);
	}

	public static Signalement updateImpl(Signalement signalement) {
		return getPersistence().updateImpl(signalement);
	}

	/**
	* Returns the signalement with the primary key or throws a {@link NoSuchSignalementException} if it could not be found.
	*
	* @param signalementId the primary key of the signalement
	* @return the signalement
	* @throws NoSuchSignalementException if a signalement with the primary key could not be found
	*/
	public static Signalement findByPrimaryKey(long signalementId)
		throws eu.strasbourg.service.comment.exception.NoSuchSignalementException {
		return getPersistence().findByPrimaryKey(signalementId);
	}

	/**
	* Returns the signalement with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param signalementId the primary key of the signalement
	* @return the signalement, or <code>null</code> if a signalement with the primary key could not be found
	*/
	public static Signalement fetchByPrimaryKey(long signalementId) {
		return getPersistence().fetchByPrimaryKey(signalementId);
	}

	public static java.util.Map<java.io.Serializable, Signalement> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the signalements.
	*
	* @return the signalements
	*/
	public static List<Signalement> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the signalements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @return the range of signalements
	*/
	public static List<Signalement> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the signalements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of signalements
	*/
	public static List<Signalement> findAll(int start, int end,
		OrderByComparator<Signalement> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the signalements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignalementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of signalements
	* @param end the upper bound of the range of signalements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of signalements
	*/
	public static List<Signalement> findAll(int start, int end,
		OrderByComparator<Signalement> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the signalements from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of signalements.
	*
	* @return the number of signalements
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SignalementPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SignalementPersistence, SignalementPersistence> _serviceTracker =
		ServiceTrackerFactory.open(SignalementPersistence.class);
}