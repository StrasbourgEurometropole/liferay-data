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

import eu.strasbourg.service.agenda.exception.NoSuchCampaignEventStatusException;
import eu.strasbourg.service.agenda.model.CampaignEventStatus;

/**
 * The persistence interface for the campaign event status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.agenda.service.persistence.impl.CampaignEventStatusPersistenceImpl
 * @see CampaignEventStatusUtil
 * @generated
 */
@ProviderType
public interface CampaignEventStatusPersistence extends BasePersistence<CampaignEventStatus> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CampaignEventStatusUtil} to access the campaign event status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the campaign event statuses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching campaign event statuses
	*/
	public java.util.List<CampaignEventStatus> findByUuid(java.lang.String uuid);

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
	public java.util.List<CampaignEventStatus> findByUuid(
		java.lang.String uuid, int start, int end);

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
	public java.util.List<CampaignEventStatus> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEventStatus> orderByComparator);

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
	public java.util.List<CampaignEventStatus> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEventStatus> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first campaign event status in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching campaign event status
	* @throws NoSuchCampaignEventStatusException if a matching campaign event status could not be found
	*/
	public CampaignEventStatus findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEventStatus> orderByComparator)
		throws NoSuchCampaignEventStatusException;

	/**
	* Returns the first campaign event status in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching campaign event status, or <code>null</code> if a matching campaign event status could not be found
	*/
	public CampaignEventStatus fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEventStatus> orderByComparator);

	/**
	* Returns the last campaign event status in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching campaign event status
	* @throws NoSuchCampaignEventStatusException if a matching campaign event status could not be found
	*/
	public CampaignEventStatus findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEventStatus> orderByComparator)
		throws NoSuchCampaignEventStatusException;

	/**
	* Returns the last campaign event status in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching campaign event status, or <code>null</code> if a matching campaign event status could not be found
	*/
	public CampaignEventStatus fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEventStatus> orderByComparator);

	/**
	* Returns the campaign event statuses before and after the current campaign event status in the ordered set where uuid = &#63;.
	*
	* @param statusId the primary key of the current campaign event status
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next campaign event status
	* @throws NoSuchCampaignEventStatusException if a campaign event status with the primary key could not be found
	*/
	public CampaignEventStatus[] findByUuid_PrevAndNext(long statusId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEventStatus> orderByComparator)
		throws NoSuchCampaignEventStatusException;

	/**
	* Removes all the campaign event statuses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of campaign event statuses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching campaign event statuses
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns all the campaign event statuses where campaignEventId = &#63;.
	*
	* @param campaignEventId the campaign event ID
	* @return the matching campaign event statuses
	*/
	public java.util.List<CampaignEventStatus> findByCampaignEventId(
		long campaignEventId);

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
	public java.util.List<CampaignEventStatus> findByCampaignEventId(
		long campaignEventId, int start, int end);

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
	public java.util.List<CampaignEventStatus> findByCampaignEventId(
		long campaignEventId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEventStatus> orderByComparator);

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
	public java.util.List<CampaignEventStatus> findByCampaignEventId(
		long campaignEventId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEventStatus> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first campaign event status in the ordered set where campaignEventId = &#63;.
	*
	* @param campaignEventId the campaign event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching campaign event status
	* @throws NoSuchCampaignEventStatusException if a matching campaign event status could not be found
	*/
	public CampaignEventStatus findByCampaignEventId_First(
		long campaignEventId,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEventStatus> orderByComparator)
		throws NoSuchCampaignEventStatusException;

	/**
	* Returns the first campaign event status in the ordered set where campaignEventId = &#63;.
	*
	* @param campaignEventId the campaign event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching campaign event status, or <code>null</code> if a matching campaign event status could not be found
	*/
	public CampaignEventStatus fetchByCampaignEventId_First(
		long campaignEventId,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEventStatus> orderByComparator);

	/**
	* Returns the last campaign event status in the ordered set where campaignEventId = &#63;.
	*
	* @param campaignEventId the campaign event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching campaign event status
	* @throws NoSuchCampaignEventStatusException if a matching campaign event status could not be found
	*/
	public CampaignEventStatus findByCampaignEventId_Last(
		long campaignEventId,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEventStatus> orderByComparator)
		throws NoSuchCampaignEventStatusException;

	/**
	* Returns the last campaign event status in the ordered set where campaignEventId = &#63;.
	*
	* @param campaignEventId the campaign event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching campaign event status, or <code>null</code> if a matching campaign event status could not be found
	*/
	public CampaignEventStatus fetchByCampaignEventId_Last(
		long campaignEventId,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEventStatus> orderByComparator);

	/**
	* Returns the campaign event statuses before and after the current campaign event status in the ordered set where campaignEventId = &#63;.
	*
	* @param statusId the primary key of the current campaign event status
	* @param campaignEventId the campaign event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next campaign event status
	* @throws NoSuchCampaignEventStatusException if a campaign event status with the primary key could not be found
	*/
	public CampaignEventStatus[] findByCampaignEventId_PrevAndNext(
		long statusId, long campaignEventId,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEventStatus> orderByComparator)
		throws NoSuchCampaignEventStatusException;

	/**
	* Removes all the campaign event statuses where campaignEventId = &#63; from the database.
	*
	* @param campaignEventId the campaign event ID
	*/
	public void removeByCampaignEventId(long campaignEventId);

	/**
	* Returns the number of campaign event statuses where campaignEventId = &#63;.
	*
	* @param campaignEventId the campaign event ID
	* @return the number of matching campaign event statuses
	*/
	public int countByCampaignEventId(long campaignEventId);

	/**
	* Caches the campaign event status in the entity cache if it is enabled.
	*
	* @param campaignEventStatus the campaign event status
	*/
	public void cacheResult(CampaignEventStatus campaignEventStatus);

	/**
	* Caches the campaign event statuses in the entity cache if it is enabled.
	*
	* @param campaignEventStatuses the campaign event statuses
	*/
	public void cacheResult(
		java.util.List<CampaignEventStatus> campaignEventStatuses);

	/**
	* Creates a new campaign event status with the primary key. Does not add the campaign event status to the database.
	*
	* @param statusId the primary key for the new campaign event status
	* @return the new campaign event status
	*/
	public CampaignEventStatus create(long statusId);

	/**
	* Removes the campaign event status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param statusId the primary key of the campaign event status
	* @return the campaign event status that was removed
	* @throws NoSuchCampaignEventStatusException if a campaign event status with the primary key could not be found
	*/
	public CampaignEventStatus remove(long statusId)
		throws NoSuchCampaignEventStatusException;

	public CampaignEventStatus updateImpl(
		CampaignEventStatus campaignEventStatus);

	/**
	* Returns the campaign event status with the primary key or throws a {@link NoSuchCampaignEventStatusException} if it could not be found.
	*
	* @param statusId the primary key of the campaign event status
	* @return the campaign event status
	* @throws NoSuchCampaignEventStatusException if a campaign event status with the primary key could not be found
	*/
	public CampaignEventStatus findByPrimaryKey(long statusId)
		throws NoSuchCampaignEventStatusException;

	/**
	* Returns the campaign event status with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param statusId the primary key of the campaign event status
	* @return the campaign event status, or <code>null</code> if a campaign event status with the primary key could not be found
	*/
	public CampaignEventStatus fetchByPrimaryKey(long statusId);

	@Override
	public java.util.Map<java.io.Serializable, CampaignEventStatus> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the campaign event statuses.
	*
	* @return the campaign event statuses
	*/
	public java.util.List<CampaignEventStatus> findAll();

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
	public java.util.List<CampaignEventStatus> findAll(int start, int end);

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
	public java.util.List<CampaignEventStatus> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEventStatus> orderByComparator);

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
	public java.util.List<CampaignEventStatus> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CampaignEventStatus> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the campaign event statuses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of campaign event statuses.
	*
	* @return the number of campaign event statuses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}