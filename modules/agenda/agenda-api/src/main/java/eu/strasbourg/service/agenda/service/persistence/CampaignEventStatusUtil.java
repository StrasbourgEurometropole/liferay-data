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

import eu.strasbourg.service.agenda.model.CampaignEventStatus;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the campaign event status service. This utility wraps {@link eu.strasbourg.service.agenda.service.persistence.impl.CampaignEventStatusPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see CampaignEventStatusPersistence
 * @see eu.strasbourg.service.agenda.service.persistence.impl.CampaignEventStatusPersistenceImpl
 * @generated
 */
@ProviderType
public class CampaignEventStatusUtil {
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
	public static void clearCache(CampaignEventStatus campaignEventStatus) {
		getPersistence().clearCache(campaignEventStatus);
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
	public static List<CampaignEventStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CampaignEventStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CampaignEventStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CampaignEventStatus> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CampaignEventStatus update(
		CampaignEventStatus campaignEventStatus) {
		return getPersistence().update(campaignEventStatus);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CampaignEventStatus update(
		CampaignEventStatus campaignEventStatus, ServiceContext serviceContext) {
		return getPersistence().update(campaignEventStatus, serviceContext);
	}

	/**
	* Returns all the campaign event statuses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching campaign event statuses
	*/
	public static List<CampaignEventStatus> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the campaign event statuses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of campaign event statuses
	* @param end the upper bound of the range of campaign event statuses (not inclusive)
	* @return the range of matching campaign event statuses
	*/
	public static List<CampaignEventStatus> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the campaign event statuses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of campaign event statuses
	* @param end the upper bound of the range of campaign event statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching campaign event statuses
	*/
	public static List<CampaignEventStatus> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<CampaignEventStatus> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the campaign event statuses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of campaign event statuses
	* @param end the upper bound of the range of campaign event statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching campaign event statuses
	*/
	public static List<CampaignEventStatus> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<CampaignEventStatus> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first campaign event status in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching campaign event status
	* @throws NoSuchCampaignEventStatusException if a matching campaign event status could not be found
	*/
	public static CampaignEventStatus findByUuid_First(java.lang.String uuid,
		OrderByComparator<CampaignEventStatus> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventStatusException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first campaign event status in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching campaign event status, or <code>null</code> if a matching campaign event status could not be found
	*/
	public static CampaignEventStatus fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<CampaignEventStatus> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last campaign event status in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching campaign event status
	* @throws NoSuchCampaignEventStatusException if a matching campaign event status could not be found
	*/
	public static CampaignEventStatus findByUuid_Last(java.lang.String uuid,
		OrderByComparator<CampaignEventStatus> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventStatusException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last campaign event status in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching campaign event status, or <code>null</code> if a matching campaign event status could not be found
	*/
	public static CampaignEventStatus fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<CampaignEventStatus> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the campaign event statuses before and after the current campaign event status in the ordered set where uuid = &#63;.
	*
	* @param statusId the primary key of the current campaign event status
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next campaign event status
	* @throws NoSuchCampaignEventStatusException if a campaign event status with the primary key could not be found
	*/
	public static CampaignEventStatus[] findByUuid_PrevAndNext(long statusId,
		java.lang.String uuid,
		OrderByComparator<CampaignEventStatus> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventStatusException {
		return getPersistence()
				   .findByUuid_PrevAndNext(statusId, uuid, orderByComparator);
	}

	/**
	* Removes all the campaign event statuses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of campaign event statuses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching campaign event statuses
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the campaign event statuses where campaignEventId = &#63;.
	*
	* @param campaignEventId the campaign event ID
	* @return the matching campaign event statuses
	*/
	public static List<CampaignEventStatus> findByCampaignEventId(
		long campaignEventId) {
		return getPersistence().findByCampaignEventId(campaignEventId);
	}

	/**
	* Returns a range of all the campaign event statuses where campaignEventId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param campaignEventId the campaign event ID
	* @param start the lower bound of the range of campaign event statuses
	* @param end the upper bound of the range of campaign event statuses (not inclusive)
	* @return the range of matching campaign event statuses
	*/
	public static List<CampaignEventStatus> findByCampaignEventId(
		long campaignEventId, int start, int end) {
		return getPersistence()
				   .findByCampaignEventId(campaignEventId, start, end);
	}

	/**
	* Returns an ordered range of all the campaign event statuses where campaignEventId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param campaignEventId the campaign event ID
	* @param start the lower bound of the range of campaign event statuses
	* @param end the upper bound of the range of campaign event statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching campaign event statuses
	*/
	public static List<CampaignEventStatus> findByCampaignEventId(
		long campaignEventId, int start, int end,
		OrderByComparator<CampaignEventStatus> orderByComparator) {
		return getPersistence()
				   .findByCampaignEventId(campaignEventId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the campaign event statuses where campaignEventId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param campaignEventId the campaign event ID
	* @param start the lower bound of the range of campaign event statuses
	* @param end the upper bound of the range of campaign event statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching campaign event statuses
	*/
	public static List<CampaignEventStatus> findByCampaignEventId(
		long campaignEventId, int start, int end,
		OrderByComparator<CampaignEventStatus> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCampaignEventId(campaignEventId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first campaign event status in the ordered set where campaignEventId = &#63;.
	*
	* @param campaignEventId the campaign event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching campaign event status
	* @throws NoSuchCampaignEventStatusException if a matching campaign event status could not be found
	*/
	public static CampaignEventStatus findByCampaignEventId_First(
		long campaignEventId,
		OrderByComparator<CampaignEventStatus> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventStatusException {
		return getPersistence()
				   .findByCampaignEventId_First(campaignEventId,
			orderByComparator);
	}

	/**
	* Returns the first campaign event status in the ordered set where campaignEventId = &#63;.
	*
	* @param campaignEventId the campaign event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching campaign event status, or <code>null</code> if a matching campaign event status could not be found
	*/
	public static CampaignEventStatus fetchByCampaignEventId_First(
		long campaignEventId,
		OrderByComparator<CampaignEventStatus> orderByComparator) {
		return getPersistence()
				   .fetchByCampaignEventId_First(campaignEventId,
			orderByComparator);
	}

	/**
	* Returns the last campaign event status in the ordered set where campaignEventId = &#63;.
	*
	* @param campaignEventId the campaign event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching campaign event status
	* @throws NoSuchCampaignEventStatusException if a matching campaign event status could not be found
	*/
	public static CampaignEventStatus findByCampaignEventId_Last(
		long campaignEventId,
		OrderByComparator<CampaignEventStatus> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventStatusException {
		return getPersistence()
				   .findByCampaignEventId_Last(campaignEventId,
			orderByComparator);
	}

	/**
	* Returns the last campaign event status in the ordered set where campaignEventId = &#63;.
	*
	* @param campaignEventId the campaign event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching campaign event status, or <code>null</code> if a matching campaign event status could not be found
	*/
	public static CampaignEventStatus fetchByCampaignEventId_Last(
		long campaignEventId,
		OrderByComparator<CampaignEventStatus> orderByComparator) {
		return getPersistence()
				   .fetchByCampaignEventId_Last(campaignEventId,
			orderByComparator);
	}

	/**
	* Returns the campaign event statuses before and after the current campaign event status in the ordered set where campaignEventId = &#63;.
	*
	* @param statusId the primary key of the current campaign event status
	* @param campaignEventId the campaign event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next campaign event status
	* @throws NoSuchCampaignEventStatusException if a campaign event status with the primary key could not be found
	*/
	public static CampaignEventStatus[] findByCampaignEventId_PrevAndNext(
		long statusId, long campaignEventId,
		OrderByComparator<CampaignEventStatus> orderByComparator)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventStatusException {
		return getPersistence()
				   .findByCampaignEventId_PrevAndNext(statusId,
			campaignEventId, orderByComparator);
	}

	/**
	* Removes all the campaign event statuses where campaignEventId = &#63; from the database.
	*
	* @param campaignEventId the campaign event ID
	*/
	public static void removeByCampaignEventId(long campaignEventId) {
		getPersistence().removeByCampaignEventId(campaignEventId);
	}

	/**
	* Returns the number of campaign event statuses where campaignEventId = &#63;.
	*
	* @param campaignEventId the campaign event ID
	* @return the number of matching campaign event statuses
	*/
	public static int countByCampaignEventId(long campaignEventId) {
		return getPersistence().countByCampaignEventId(campaignEventId);
	}

	/**
	* Caches the campaign event status in the entity cache if it is enabled.
	*
	* @param campaignEventStatus the campaign event status
	*/
	public static void cacheResult(CampaignEventStatus campaignEventStatus) {
		getPersistence().cacheResult(campaignEventStatus);
	}

	/**
	* Caches the campaign event statuses in the entity cache if it is enabled.
	*
	* @param campaignEventStatuses the campaign event statuses
	*/
	public static void cacheResult(
		List<CampaignEventStatus> campaignEventStatuses) {
		getPersistence().cacheResult(campaignEventStatuses);
	}

	/**
	* Creates a new campaign event status with the primary key. Does not add the campaign event status to the database.
	*
	* @param statusId the primary key for the new campaign event status
	* @return the new campaign event status
	*/
	public static CampaignEventStatus create(long statusId) {
		return getPersistence().create(statusId);
	}

	/**
	* Removes the campaign event status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param statusId the primary key of the campaign event status
	* @return the campaign event status that was removed
	* @throws NoSuchCampaignEventStatusException if a campaign event status with the primary key could not be found
	*/
	public static CampaignEventStatus remove(long statusId)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventStatusException {
		return getPersistence().remove(statusId);
	}

	public static CampaignEventStatus updateImpl(
		CampaignEventStatus campaignEventStatus) {
		return getPersistence().updateImpl(campaignEventStatus);
	}

	/**
	* Returns the campaign event status with the primary key or throws a {@link NoSuchCampaignEventStatusException} if it could not be found.
	*
	* @param statusId the primary key of the campaign event status
	* @return the campaign event status
	* @throws NoSuchCampaignEventStatusException if a campaign event status with the primary key could not be found
	*/
	public static CampaignEventStatus findByPrimaryKey(long statusId)
		throws eu.strasbourg.service.agenda.exception.NoSuchCampaignEventStatusException {
		return getPersistence().findByPrimaryKey(statusId);
	}

	/**
	* Returns the campaign event status with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param statusId the primary key of the campaign event status
	* @return the campaign event status, or <code>null</code> if a campaign event status with the primary key could not be found
	*/
	public static CampaignEventStatus fetchByPrimaryKey(long statusId) {
		return getPersistence().fetchByPrimaryKey(statusId);
	}

	public static java.util.Map<java.io.Serializable, CampaignEventStatus> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the campaign event statuses.
	*
	* @return the campaign event statuses
	*/
	public static List<CampaignEventStatus> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the campaign event statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of campaign event statuses
	* @param end the upper bound of the range of campaign event statuses (not inclusive)
	* @return the range of campaign event statuses
	*/
	public static List<CampaignEventStatus> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the campaign event statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of campaign event statuses
	* @param end the upper bound of the range of campaign event statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of campaign event statuses
	*/
	public static List<CampaignEventStatus> findAll(int start, int end,
		OrderByComparator<CampaignEventStatus> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the campaign event statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CampaignEventStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of campaign event statuses
	* @param end the upper bound of the range of campaign event statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of campaign event statuses
	*/
	public static List<CampaignEventStatus> findAll(int start, int end,
		OrderByComparator<CampaignEventStatus> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the campaign event statuses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of campaign event statuses.
	*
	* @return the number of campaign event statuses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CampaignEventStatusPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CampaignEventStatusPersistence, CampaignEventStatusPersistence> _serviceTracker =
		ServiceTrackerFactory.open(CampaignEventStatusPersistence.class);
}