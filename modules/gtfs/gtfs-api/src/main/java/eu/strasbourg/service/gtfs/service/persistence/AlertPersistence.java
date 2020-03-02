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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.gtfs.exception.NoSuchAlertException;
import eu.strasbourg.service.gtfs.model.Alert;

/**
 * The persistence interface for the alert service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.gtfs.service.persistence.impl.AlertPersistenceImpl
 * @see AlertUtil
 * @generated
 */
@ProviderType
public interface AlertPersistence extends BasePersistence<Alert> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AlertUtil} to access the alert persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the alerts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching alerts
	*/
	public java.util.List<Alert> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the alerts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @return the range of matching alerts
	*/
	public java.util.List<Alert> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the alerts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching alerts
	*/
	public java.util.List<Alert> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator);

	/**
	* Returns an ordered range of all the alerts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching alerts
	*/
	public java.util.List<Alert> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first alert in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public Alert findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator)
		throws NoSuchAlertException;

	/**
	* Returns the first alert in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public Alert fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator);

	/**
	* Returns the last alert in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public Alert findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator)
		throws NoSuchAlertException;

	/**
	* Returns the last alert in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public Alert fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator);

	/**
	* Returns the alerts before and after the current alert in the ordered set where uuid = &#63;.
	*
	* @param alertId the primary key of the current alert
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next alert
	* @throws NoSuchAlertException if a alert with the primary key could not be found
	*/
	public Alert[] findByUuid_PrevAndNext(long alertId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator)
		throws NoSuchAlertException;

	/**
	* Removes all the alerts where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of alerts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching alerts
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the alert where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchAlertException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public Alert findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchAlertException;

	/**
	* Returns the alert where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public Alert fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the alert where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public Alert fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the alert where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the alert that was removed
	*/
	public Alert removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchAlertException;

	/**
	* Returns the number of alerts where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching alerts
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the alerts where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching alerts
	*/
	public java.util.List<Alert> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the alerts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @return the range of matching alerts
	*/
	public java.util.List<Alert> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the alerts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching alerts
	*/
	public java.util.List<Alert> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator);

	/**
	* Returns an ordered range of all the alerts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching alerts
	*/
	public java.util.List<Alert> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first alert in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public Alert findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator)
		throws NoSuchAlertException;

	/**
	* Returns the first alert in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public Alert fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator);

	/**
	* Returns the last alert in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public Alert findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator)
		throws NoSuchAlertException;

	/**
	* Returns the last alert in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public Alert fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator);

	/**
	* Returns the alerts before and after the current alert in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param alertId the primary key of the current alert
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next alert
	* @throws NoSuchAlertException if a alert with the primary key could not be found
	*/
	public Alert[] findByUuid_C_PrevAndNext(long alertId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator)
		throws NoSuchAlertException;

	/**
	* Removes all the alerts where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of alerts where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching alerts
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the alerts where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching alerts
	*/
	public java.util.List<Alert> findByGroupId(long groupId);

	/**
	* Returns a range of all the alerts where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @return the range of matching alerts
	*/
	public java.util.List<Alert> findByGroupId(long groupId, int start, int end);

	/**
	* Returns an ordered range of all the alerts where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching alerts
	*/
	public java.util.List<Alert> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator);

	/**
	* Returns an ordered range of all the alerts where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching alerts
	*/
	public java.util.List<Alert> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first alert in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public Alert findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator)
		throws NoSuchAlertException;

	/**
	* Returns the first alert in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public Alert fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator);

	/**
	* Returns the last alert in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public Alert findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator)
		throws NoSuchAlertException;

	/**
	* Returns the last alert in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public Alert fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator);

	/**
	* Returns the alerts before and after the current alert in the ordered set where groupId = &#63;.
	*
	* @param alertId the primary key of the current alert
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next alert
	* @throws NoSuchAlertException if a alert with the primary key could not be found
	*/
	public Alert[] findByGroupId_PrevAndNext(long alertId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator)
		throws NoSuchAlertException;

	/**
	* Removes all the alerts where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of alerts where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching alerts
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the alerts where arretId = &#63;.
	*
	* @param arretId the arret ID
	* @return the matching alerts
	*/
	public java.util.List<Alert> findByArretId(long arretId);

	/**
	* Returns a range of all the alerts where arretId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param arretId the arret ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @return the range of matching alerts
	*/
	public java.util.List<Alert> findByArretId(long arretId, int start, int end);

	/**
	* Returns an ordered range of all the alerts where arretId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param arretId the arret ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching alerts
	*/
	public java.util.List<Alert> findByArretId(long arretId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator);

	/**
	* Returns an ordered range of all the alerts where arretId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param arretId the arret ID
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching alerts
	*/
	public java.util.List<Alert> findByArretId(long arretId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first alert in the ordered set where arretId = &#63;.
	*
	* @param arretId the arret ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public Alert findByArretId_First(long arretId,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator)
		throws NoSuchAlertException;

	/**
	* Returns the first alert in the ordered set where arretId = &#63;.
	*
	* @param arretId the arret ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public Alert fetchByArretId_First(long arretId,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator);

	/**
	* Returns the last alert in the ordered set where arretId = &#63;.
	*
	* @param arretId the arret ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching alert
	* @throws NoSuchAlertException if a matching alert could not be found
	*/
	public Alert findByArretId_Last(long arretId,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator)
		throws NoSuchAlertException;

	/**
	* Returns the last alert in the ordered set where arretId = &#63;.
	*
	* @param arretId the arret ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching alert, or <code>null</code> if a matching alert could not be found
	*/
	public Alert fetchByArretId_Last(long arretId,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator);

	/**
	* Returns the alerts before and after the current alert in the ordered set where arretId = &#63;.
	*
	* @param alertId the primary key of the current alert
	* @param arretId the arret ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next alert
	* @throws NoSuchAlertException if a alert with the primary key could not be found
	*/
	public Alert[] findByArretId_PrevAndNext(long alertId, long arretId,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator)
		throws NoSuchAlertException;

	/**
	* Removes all the alerts where arretId = &#63; from the database.
	*
	* @param arretId the arret ID
	*/
	public void removeByArretId(long arretId);

	/**
	* Returns the number of alerts where arretId = &#63;.
	*
	* @param arretId the arret ID
	* @return the number of matching alerts
	*/
	public int countByArretId(long arretId);

	/**
	* Caches the alert in the entity cache if it is enabled.
	*
	* @param alert the alert
	*/
	public void cacheResult(Alert alert);

	/**
	* Caches the alerts in the entity cache if it is enabled.
	*
	* @param alerts the alerts
	*/
	public void cacheResult(java.util.List<Alert> alerts);

	/**
	* Creates a new alert with the primary key. Does not add the alert to the database.
	*
	* @param alertId the primary key for the new alert
	* @return the new alert
	*/
	public Alert create(long alertId);

	/**
	* Removes the alert with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param alertId the primary key of the alert
	* @return the alert that was removed
	* @throws NoSuchAlertException if a alert with the primary key could not be found
	*/
	public Alert remove(long alertId) throws NoSuchAlertException;

	public Alert updateImpl(Alert alert);

	/**
	* Returns the alert with the primary key or throws a {@link NoSuchAlertException} if it could not be found.
	*
	* @param alertId the primary key of the alert
	* @return the alert
	* @throws NoSuchAlertException if a alert with the primary key could not be found
	*/
	public Alert findByPrimaryKey(long alertId) throws NoSuchAlertException;

	/**
	* Returns the alert with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param alertId the primary key of the alert
	* @return the alert, or <code>null</code> if a alert with the primary key could not be found
	*/
	public Alert fetchByPrimaryKey(long alertId);

	@Override
	public java.util.Map<java.io.Serializable, Alert> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the alerts.
	*
	* @return the alerts
	*/
	public java.util.List<Alert> findAll();

	/**
	* Returns a range of all the alerts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @return the range of alerts
	*/
	public java.util.List<Alert> findAll(int start, int end);

	/**
	* Returns an ordered range of all the alerts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of alerts
	*/
	public java.util.List<Alert> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator);

	/**
	* Returns an ordered range of all the alerts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AlertModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of alerts
	* @param end the upper bound of the range of alerts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of alerts
	*/
	public java.util.List<Alert> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Alert> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the alerts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of alerts.
	*
	* @return the number of alerts
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}