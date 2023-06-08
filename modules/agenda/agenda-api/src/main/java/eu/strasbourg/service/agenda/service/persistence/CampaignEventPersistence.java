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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.agenda.exception.NoSuchCampaignEventException;
import eu.strasbourg.service.agenda.model.CampaignEvent;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the campaign event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see CampaignEventUtil
 * @generated
 */
@ProviderType
public interface CampaignEventPersistence
	extends BasePersistence<CampaignEvent> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CampaignEventUtil} to access the campaign event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, CampaignEvent> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the campaign events where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching campaign events
	 */
	public java.util.List<CampaignEvent> findByUuid(String uuid);

	/**
	 * Returns a range of all the campaign events where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignEventModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of campaign events
	 * @param end the upper bound of the range of campaign events (not inclusive)
	 * @return the range of matching campaign events
	 */
	public java.util.List<CampaignEvent> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the campaign events where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignEventModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of campaign events
	 * @param end the upper bound of the range of campaign events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching campaign events
	 */
	public java.util.List<CampaignEvent> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
			orderByComparator);

	/**
	 * Returns an ordered range of all the campaign events where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignEventModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of campaign events
	 * @param end the upper bound of the range of campaign events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching campaign events
	 */
	public java.util.List<CampaignEvent> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first campaign event in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching campaign event
	 * @throws NoSuchCampaignEventException if a matching campaign event could not be found
	 */
	public CampaignEvent findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
				orderByComparator)
		throws NoSuchCampaignEventException;

	/**
	 * Returns the first campaign event in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching campaign event, or <code>null</code> if a matching campaign event could not be found
	 */
	public CampaignEvent fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
			orderByComparator);

	/**
	 * Returns the last campaign event in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching campaign event
	 * @throws NoSuchCampaignEventException if a matching campaign event could not be found
	 */
	public CampaignEvent findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
				orderByComparator)
		throws NoSuchCampaignEventException;

	/**
	 * Returns the last campaign event in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching campaign event, or <code>null</code> if a matching campaign event could not be found
	 */
	public CampaignEvent fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
			orderByComparator);

	/**
	 * Returns the campaign events before and after the current campaign event in the ordered set where uuid = &#63;.
	 *
	 * @param campaignEventId the primary key of the current campaign event
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next campaign event
	 * @throws NoSuchCampaignEventException if a campaign event with the primary key could not be found
	 */
	public CampaignEvent[] findByUuid_PrevAndNext(
			long campaignEventId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
				orderByComparator)
		throws NoSuchCampaignEventException;

	/**
	 * Removes all the campaign events where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of campaign events where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching campaign events
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the campaign event where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchCampaignEventException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching campaign event
	 * @throws NoSuchCampaignEventException if a matching campaign event could not be found
	 */
	public CampaignEvent findByUUID_G(String uuid, long groupId)
		throws NoSuchCampaignEventException;

	/**
	 * Returns the campaign event where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching campaign event, or <code>null</code> if a matching campaign event could not be found
	 */
	public CampaignEvent fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the campaign event where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching campaign event, or <code>null</code> if a matching campaign event could not be found
	 */
	public CampaignEvent fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the campaign event where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the campaign event that was removed
	 */
	public CampaignEvent removeByUUID_G(String uuid, long groupId)
		throws NoSuchCampaignEventException;

	/**
	 * Returns the number of campaign events where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching campaign events
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the campaign events where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching campaign events
	 */
	public java.util.List<CampaignEvent> findByUuid_C(
		String uuid, long companyId);

	/**
	 * Returns a range of all the campaign events where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignEventModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of campaign events
	 * @param end the upper bound of the range of campaign events (not inclusive)
	 * @return the range of matching campaign events
	 */
	public java.util.List<CampaignEvent> findByUuid_C(
		String uuid, long companyId, int start, int end);

	/**
	 * Returns an ordered range of all the campaign events where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignEventModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of campaign events
	 * @param end the upper bound of the range of campaign events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching campaign events
	 */
	public java.util.List<CampaignEvent> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
			orderByComparator);

	/**
	 * Returns an ordered range of all the campaign events where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignEventModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of campaign events
	 * @param end the upper bound of the range of campaign events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching campaign events
	 */
	public java.util.List<CampaignEvent> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first campaign event in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching campaign event
	 * @throws NoSuchCampaignEventException if a matching campaign event could not be found
	 */
	public CampaignEvent findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
				orderByComparator)
		throws NoSuchCampaignEventException;

	/**
	 * Returns the first campaign event in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching campaign event, or <code>null</code> if a matching campaign event could not be found
	 */
	public CampaignEvent fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
			orderByComparator);

	/**
	 * Returns the last campaign event in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching campaign event
	 * @throws NoSuchCampaignEventException if a matching campaign event could not be found
	 */
	public CampaignEvent findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
				orderByComparator)
		throws NoSuchCampaignEventException;

	/**
	 * Returns the last campaign event in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching campaign event, or <code>null</code> if a matching campaign event could not be found
	 */
	public CampaignEvent fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
			orderByComparator);

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
	public CampaignEvent[] findByUuid_C_PrevAndNext(
			long campaignEventId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
				orderByComparator)
		throws NoSuchCampaignEventException;

	/**
	 * Removes all the campaign events where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of campaign events where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching campaign events
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the campaign events where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the matching campaign events
	 */
	public java.util.List<CampaignEvent> findByCampaignId(Long campaignId);

	/**
	 * Returns a range of all the campaign events where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignEventModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of campaign events
	 * @param end the upper bound of the range of campaign events (not inclusive)
	 * @return the range of matching campaign events
	 */
	public java.util.List<CampaignEvent> findByCampaignId(
		Long campaignId, int start, int end);

	/**
	 * Returns an ordered range of all the campaign events where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignEventModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of campaign events
	 * @param end the upper bound of the range of campaign events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching campaign events
	 */
	public java.util.List<CampaignEvent> findByCampaignId(
		Long campaignId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
			orderByComparator);

	/**
	 * Returns an ordered range of all the campaign events where campaignId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignEventModelImpl</code>.
	 * </p>
	 *
	 * @param campaignId the campaign ID
	 * @param start the lower bound of the range of campaign events
	 * @param end the upper bound of the range of campaign events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching campaign events
	 */
	public java.util.List<CampaignEvent> findByCampaignId(
		Long campaignId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first campaign event in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching campaign event
	 * @throws NoSuchCampaignEventException if a matching campaign event could not be found
	 */
	public CampaignEvent findByCampaignId_First(
			Long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
				orderByComparator)
		throws NoSuchCampaignEventException;

	/**
	 * Returns the first campaign event in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching campaign event, or <code>null</code> if a matching campaign event could not be found
	 */
	public CampaignEvent fetchByCampaignId_First(
		Long campaignId,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
			orderByComparator);

	/**
	 * Returns the last campaign event in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching campaign event
	 * @throws NoSuchCampaignEventException if a matching campaign event could not be found
	 */
	public CampaignEvent findByCampaignId_Last(
			Long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
				orderByComparator)
		throws NoSuchCampaignEventException;

	/**
	 * Returns the last campaign event in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching campaign event, or <code>null</code> if a matching campaign event could not be found
	 */
	public CampaignEvent fetchByCampaignId_Last(
		Long campaignId,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
			orderByComparator);

	/**
	 * Returns the campaign events before and after the current campaign event in the ordered set where campaignId = &#63;.
	 *
	 * @param campaignEventId the primary key of the current campaign event
	 * @param campaignId the campaign ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next campaign event
	 * @throws NoSuchCampaignEventException if a campaign event with the primary key could not be found
	 */
	public CampaignEvent[] findByCampaignId_PrevAndNext(
			long campaignEventId, Long campaignId,
			com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
				orderByComparator)
		throws NoSuchCampaignEventException;

	/**
	 * Removes all the campaign events where campaignId = &#63; from the database.
	 *
	 * @param campaignId the campaign ID
	 */
	public void removeByCampaignId(Long campaignId);

	/**
	 * Returns the number of campaign events where campaignId = &#63;.
	 *
	 * @param campaignId the campaign ID
	 * @return the number of matching campaign events
	 */
	public int countByCampaignId(Long campaignId);

	/**
	 * Caches the campaign event in the entity cache if it is enabled.
	 *
	 * @param campaignEvent the campaign event
	 */
	public void cacheResult(CampaignEvent campaignEvent);

	/**
	 * Caches the campaign events in the entity cache if it is enabled.
	 *
	 * @param campaignEvents the campaign events
	 */
	public void cacheResult(java.util.List<CampaignEvent> campaignEvents);

	/**
	 * Creates a new campaign event with the primary key. Does not add the campaign event to the database.
	 *
	 * @param campaignEventId the primary key for the new campaign event
	 * @return the new campaign event
	 */
	public CampaignEvent create(long campaignEventId);

	/**
	 * Removes the campaign event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param campaignEventId the primary key of the campaign event
	 * @return the campaign event that was removed
	 * @throws NoSuchCampaignEventException if a campaign event with the primary key could not be found
	 */
	public CampaignEvent remove(long campaignEventId)
		throws NoSuchCampaignEventException;

	public CampaignEvent updateImpl(CampaignEvent campaignEvent);

	/**
	 * Returns the campaign event with the primary key or throws a <code>NoSuchCampaignEventException</code> if it could not be found.
	 *
	 * @param campaignEventId the primary key of the campaign event
	 * @return the campaign event
	 * @throws NoSuchCampaignEventException if a campaign event with the primary key could not be found
	 */
	public CampaignEvent findByPrimaryKey(long campaignEventId)
		throws NoSuchCampaignEventException;

	/**
	 * Returns the campaign event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param campaignEventId the primary key of the campaign event
	 * @return the campaign event, or <code>null</code> if a campaign event with the primary key could not be found
	 */
	public CampaignEvent fetchByPrimaryKey(long campaignEventId);

	/**
	 * Returns all the campaign events.
	 *
	 * @return the campaign events
	 */
	public java.util.List<CampaignEvent> findAll();

	/**
	 * Returns a range of all the campaign events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignEventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaign events
	 * @param end the upper bound of the range of campaign events (not inclusive)
	 * @return the range of campaign events
	 */
	public java.util.List<CampaignEvent> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the campaign events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignEventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaign events
	 * @param end the upper bound of the range of campaign events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of campaign events
	 */
	public java.util.List<CampaignEvent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
			orderByComparator);

	/**
	 * Returns an ordered range of all the campaign events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CampaignEventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of campaign events
	 * @param end the upper bound of the range of campaign events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of campaign events
	 */
	public java.util.List<CampaignEvent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEvent>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the campaign events from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of campaign events.
	 *
	 * @return the number of campaign events
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}