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

package eu.strasbourg.service.agenda.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.agenda.model.CampaignEvent;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the campaign event service. This utility wraps {@link eu.strasbourg.service.agenda.service.persistence.impl.CampaignEventPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see CampaignEventPersistence
 * @see eu.strasbourg.service.agenda.service.persistence.impl.CampaignEventPersistenceImpl
 * @generated
 */
@ProviderType
public class CampaignEventUtil {
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
	public static void clearCache(CampaignEvent campaignEvent) {
		getPersistence().clearCache(campaignEvent);
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
	public static List<CampaignEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CampaignEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CampaignEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CampaignEvent> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CampaignEvent update(CampaignEvent campaignEvent) {
		return getPersistence().update(campaignEvent);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CampaignEvent update(CampaignEvent campaignEvent,
		ServiceContext serviceContext) {
		return getPersistence().update(campaignEvent, serviceContext);
	}

	/**
	* Returns all the campaign events where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching campaign events
	*/
	public static List<CampaignEvent> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the campaign events where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of campaign events
	* @param end the upper bound of the range of campaign events (not inclusive)
	* @return the range of matching campaign events
	*/
	public static List<CampaignEvent> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the campaign events where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of campaign events
	* @param end the upper bound of the range of campaign events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching campaign events
	*/
	public static List<CampaignEvent> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<CampaignEvent> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the campaign events where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of campaign events
	* @param end the upper bound of the range of campaign events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching campaign events
	*/
	public static List<CampaignEvent> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<CampaignEvent> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first campaign event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching campaign event
	* @throws NoSuchCampaignEventException if a matching campaign event could not be found
	*/
	public static CampaignEvent findByUuid_First(java.lang.String uuid,
		OrderByComparator<CampaignEvent> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first campaign event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching campaign event, or <code>null</code> if a matching campaign event could not be found
	*/
	public static CampaignEvent fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<CampaignEvent> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last campaign event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching campaign event
	* @throws NoSuchCampaignEventException if a matching campaign event could not be found
	*/
	public static CampaignEvent findByUuid_Last(java.lang.String uuid,
		OrderByComparator<CampaignEvent> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last campaign event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching campaign event, or <code>null</code> if a matching campaign event could not be found
	*/
	public static CampaignEvent fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<CampaignEvent> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the campaign events before and after the current campaign event in the ordered set where uuid = &#63;.
	*
	* @param campaignEventId the primary key of the current campaign event
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next campaign event
	* @throws NoSuchCampaignEventException if a campaign event with the primary key could not be found
	*/
	public static CampaignEvent[] findByUuid_PrevAndNext(long campaignEventId,
		java.lang.String uuid,
		OrderByComparator<CampaignEvent> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventException {
		return getPersistence()
				   .findByUuid_PrevAndNext(campaignEventId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the campaign events where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of campaign events where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching campaign events
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the campaign event where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCampaignEventException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching campaign event
	* @throws NoSuchCampaignEventException if a matching campaign event could not be found
	*/
	public static CampaignEvent findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the campaign event where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching campaign event, or <code>null</code> if a matching campaign event could not be found
	*/
	public static CampaignEvent fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the campaign event where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching campaign event, or <code>null</code> if a matching campaign event could not be found
	*/
	public static CampaignEvent fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the campaign event where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the campaign event that was removed
	*/
	public static CampaignEvent removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of campaign events where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching campaign events
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the campaign events where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching campaign events
	*/
	public static List<CampaignEvent> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the campaign events where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of campaign events
	* @param end the upper bound of the range of campaign events (not inclusive)
	* @return the range of matching campaign events
	*/
	public static List<CampaignEvent> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the campaign events where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of campaign events
	* @param end the upper bound of the range of campaign events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching campaign events
	*/
	public static List<CampaignEvent> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<CampaignEvent> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the campaign events where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of campaign events
	* @param end the upper bound of the range of campaign events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching campaign events
	*/
	public static List<CampaignEvent> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<CampaignEvent> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first campaign event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching campaign event
	* @throws NoSuchCampaignEventException if a matching campaign event could not be found
	*/
	public static CampaignEvent findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<CampaignEvent> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first campaign event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching campaign event, or <code>null</code> if a matching campaign event could not be found
	*/
	public static CampaignEvent fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<CampaignEvent> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last campaign event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching campaign event
	* @throws NoSuchCampaignEventException if a matching campaign event could not be found
	*/
	public static CampaignEvent findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<CampaignEvent> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last campaign event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching campaign event, or <code>null</code> if a matching campaign event could not be found
	*/
	public static CampaignEvent fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<CampaignEvent> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the campaign events before and after the current campaign event in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param campaignEventId the primary key of the current campaign event
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next campaign event
	* @throws NoSuchCampaignEventException if a campaign event with the primary key could not be found
	*/
	public static CampaignEvent[] findByUuid_C_PrevAndNext(
		long campaignEventId, java.lang.String uuid, long companyId,
		OrderByComparator<CampaignEvent> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(campaignEventId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the campaign events where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of campaign events where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching campaign events
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the campaign events where campaignId = &#63;.
	*
	* @param campaignId the campaign ID
	* @return the matching campaign events
	*/
	public static List<CampaignEvent> findByCampaignId(
		java.lang.Long campaignId) {
		return getPersistence().findByCampaignId(campaignId);
	}

	/**
	* Returns a range of all the campaign events where campaignId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param campaignId the campaign ID
	* @param start the lower bound of the range of campaign events
	* @param end the upper bound of the range of campaign events (not inclusive)
	* @return the range of matching campaign events
	*/
	public static List<CampaignEvent> findByCampaignId(
		java.lang.Long campaignId, int start, int end) {
		return getPersistence().findByCampaignId(campaignId, start, end);
	}

	/**
	* Returns an ordered range of all the campaign events where campaignId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param campaignId the campaign ID
	* @param start the lower bound of the range of campaign events
	* @param end the upper bound of the range of campaign events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching campaign events
	*/
	public static List<CampaignEvent> findByCampaignId(
		java.lang.Long campaignId, int start, int end,
		OrderByComparator<CampaignEvent> orderByComparator) {
		return getPersistence()
				   .findByCampaignId(campaignId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the campaign events where campaignId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param campaignId the campaign ID
	* @param start the lower bound of the range of campaign events
	* @param end the upper bound of the range of campaign events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching campaign events
	*/
	public static List<CampaignEvent> findByCampaignId(
		java.lang.Long campaignId, int start, int end,
		OrderByComparator<CampaignEvent> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCampaignId(campaignId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first campaign event in the ordered set where campaignId = &#63;.
	*
	* @param campaignId the campaign ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching campaign event
	* @throws NoSuchCampaignEventException if a matching campaign event could not be found
	*/
	public static CampaignEvent findByCampaignId_First(
		java.lang.Long campaignId,
		OrderByComparator<CampaignEvent> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventException {
		return getPersistence()
				   .findByCampaignId_First(campaignId, orderByComparator);
	}

	/**
	* Returns the first campaign event in the ordered set where campaignId = &#63;.
	*
	* @param campaignId the campaign ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching campaign event, or <code>null</code> if a matching campaign event could not be found
	*/
	public static CampaignEvent fetchByCampaignId_First(
		java.lang.Long campaignId,
		OrderByComparator<CampaignEvent> orderByComparator) {
		return getPersistence()
				   .fetchByCampaignId_First(campaignId, orderByComparator);
	}

	/**
	* Returns the last campaign event in the ordered set where campaignId = &#63;.
	*
	* @param campaignId the campaign ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching campaign event
	* @throws NoSuchCampaignEventException if a matching campaign event could not be found
	*/
	public static CampaignEvent findByCampaignId_Last(
		java.lang.Long campaignId,
		OrderByComparator<CampaignEvent> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventException {
		return getPersistence()
				   .findByCampaignId_Last(campaignId, orderByComparator);
	}

	/**
	* Returns the last campaign event in the ordered set where campaignId = &#63;.
	*
	* @param campaignId the campaign ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching campaign event, or <code>null</code> if a matching campaign event could not be found
	*/
	public static CampaignEvent fetchByCampaignId_Last(
		java.lang.Long campaignId,
		OrderByComparator<CampaignEvent> orderByComparator) {
		return getPersistence()
				   .fetchByCampaignId_Last(campaignId, orderByComparator);
	}

	/**
	* Returns the campaign events before and after the current campaign event in the ordered set where campaignId = &#63;.
	*
	* @param campaignEventId the primary key of the current campaign event
	* @param campaignId the campaign ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next campaign event
	* @throws NoSuchCampaignEventException if a campaign event with the primary key could not be found
	*/
	public static CampaignEvent[] findByCampaignId_PrevAndNext(
		long campaignEventId, java.lang.Long campaignId,
		OrderByComparator<CampaignEvent> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventException {
		return getPersistence()
				   .findByCampaignId_PrevAndNext(campaignEventId, campaignId,
			orderByComparator);
	}

	/**
	* Removes all the campaign events where campaignId = &#63; from the database.
	*
	* @param campaignId the campaign ID
	*/
	public static void removeByCampaignId(java.lang.Long campaignId) {
		getPersistence().removeByCampaignId(campaignId);
	}

	/**
	* Returns the number of campaign events where campaignId = &#63;.
	*
	* @param campaignId the campaign ID
	* @return the number of matching campaign events
	*/
	public static int countByCampaignId(java.lang.Long campaignId) {
		return getPersistence().countByCampaignId(campaignId);
	}

	/**
	* Caches the campaign event in the entity cache if it is enabled.
	*
	* @param campaignEvent the campaign event
	*/
	public static void cacheResult(CampaignEvent campaignEvent) {
		getPersistence().cacheResult(campaignEvent);
	}

	/**
	* Caches the campaign events in the entity cache if it is enabled.
	*
	* @param campaignEvents the campaign events
	*/
	public static void cacheResult(List<CampaignEvent> campaignEvents) {
		getPersistence().cacheResult(campaignEvents);
	}

	/**
	* Creates a new campaign event with the primary key. Does not add the campaign event to the database.
	*
	* @param campaignEventId the primary key for the new campaign event
	* @return the new campaign event
	*/
	public static CampaignEvent create(long campaignEventId) {
		return getPersistence().create(campaignEventId);
	}

	/**
	* Removes the campaign event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param campaignEventId the primary key of the campaign event
	* @return the campaign event that was removed
	* @throws NoSuchCampaignEventException if a campaign event with the primary key could not be found
	*/
	public static CampaignEvent remove(long campaignEventId)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventException {
		return getPersistence().remove(campaignEventId);
	}

	public static CampaignEvent updateImpl(CampaignEvent campaignEvent) {
		return getPersistence().updateImpl(campaignEvent);
	}

	/**
	* Returns the campaign event with the primary key or throws a {@link NoSuchCampaignEventException} if it could not be found.
	*
	* @param campaignEventId the primary key of the campaign event
	* @return the campaign event
	* @throws NoSuchCampaignEventException if a campaign event with the primary key could not be found
	*/
	public static CampaignEvent findByPrimaryKey(long campaignEventId)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventException {
		return getPersistence().findByPrimaryKey(campaignEventId);
	}

	/**
	* Returns the campaign event with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param campaignEventId the primary key of the campaign event
	* @return the campaign event, or <code>null</code> if a campaign event with the primary key could not be found
	*/
	public static CampaignEvent fetchByPrimaryKey(long campaignEventId) {
		return getPersistence().fetchByPrimaryKey(campaignEventId);
	}

	public static java.util.Map<java.io.Serializable, CampaignEvent> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the campaign events.
	*
	* @return the campaign events
	*/
	public static List<CampaignEvent> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the campaign events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of campaign events
	* @param end the upper bound of the range of campaign events (not inclusive)
	* @return the range of campaign events
	*/
	public static List<CampaignEvent> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the campaign events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of campaign events
	* @param end the upper bound of the range of campaign events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of campaign events
	*/
	public static List<CampaignEvent> findAll(int start, int end,
		OrderByComparator<CampaignEvent> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the campaign events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of campaign events
	* @param end the upper bound of the range of campaign events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of campaign events
	*/
	public static List<CampaignEvent> findAll(int start, int end,
		OrderByComparator<CampaignEvent> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the campaign events from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of campaign events.
	*
	* @return the number of campaign events
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CampaignEventPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CampaignEventPersistence, CampaignEventPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CampaignEventPersistence.class);
}