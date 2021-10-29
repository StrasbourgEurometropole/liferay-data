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

package eu.strasbourg.service.notif.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.notif.exception.NoSuchServiceNotifException;
import eu.strasbourg.service.notif.model.ServiceNotif;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the service notif service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceNotifUtil
 * @generated
 */
@ProviderType
public interface ServiceNotifPersistence extends BasePersistence<ServiceNotif> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ServiceNotifUtil} to access the service notif persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, ServiceNotif> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the service notifs where organisationId = &#63;.
	 *
	 * @param organisationId the organisation ID
	 * @return the matching service notifs
	 */
	public java.util.List<ServiceNotif> findByOrganisationIds(
		long organisationId);

	/**
	 * Returns a range of all the service notifs where organisationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param organisationId the organisation ID
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @return the range of matching service notifs
	 */
	public java.util.List<ServiceNotif> findByOrganisationIds(
		long organisationId, int start, int end);

	/**
	 * Returns an ordered range of all the service notifs where organisationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param organisationId the organisation ID
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service notifs
	 */
	public java.util.List<ServiceNotif> findByOrganisationIds(
		long organisationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceNotif>
			orderByComparator);

	/**
	 * Returns an ordered range of all the service notifs where organisationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param organisationId the organisation ID
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service notifs
	 */
	public java.util.List<ServiceNotif> findByOrganisationIds(
		long organisationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceNotif>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first service notif in the ordered set where organisationId = &#63;.
	 *
	 * @param organisationId the organisation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service notif
	 * @throws NoSuchServiceNotifException if a matching service notif could not be found
	 */
	public ServiceNotif findByOrganisationIds_First(
			long organisationId,
			com.liferay.portal.kernel.util.OrderByComparator<ServiceNotif>
				orderByComparator)
		throws NoSuchServiceNotifException;

	/**
	 * Returns the first service notif in the ordered set where organisationId = &#63;.
	 *
	 * @param organisationId the organisation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching service notif, or <code>null</code> if a matching service notif could not be found
	 */
	public ServiceNotif fetchByOrganisationIds_First(
		long organisationId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceNotif>
			orderByComparator);

	/**
	 * Returns the last service notif in the ordered set where organisationId = &#63;.
	 *
	 * @param organisationId the organisation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service notif
	 * @throws NoSuchServiceNotifException if a matching service notif could not be found
	 */
	public ServiceNotif findByOrganisationIds_Last(
			long organisationId,
			com.liferay.portal.kernel.util.OrderByComparator<ServiceNotif>
				orderByComparator)
		throws NoSuchServiceNotifException;

	/**
	 * Returns the last service notif in the ordered set where organisationId = &#63;.
	 *
	 * @param organisationId the organisation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching service notif, or <code>null</code> if a matching service notif could not be found
	 */
	public ServiceNotif fetchByOrganisationIds_Last(
		long organisationId,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceNotif>
			orderByComparator);

	/**
	 * Returns the service notifs before and after the current service notif in the ordered set where organisationId = &#63;.
	 *
	 * @param serviceId the primary key of the current service notif
	 * @param organisationId the organisation ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next service notif
	 * @throws NoSuchServiceNotifException if a service notif with the primary key could not be found
	 */
	public ServiceNotif[] findByOrganisationIds_PrevAndNext(
			long serviceId, long organisationId,
			com.liferay.portal.kernel.util.OrderByComparator<ServiceNotif>
				orderByComparator)
		throws NoSuchServiceNotifException;

	/**
	 * Returns all the service notifs where organisationId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param organisationIds the organisation IDs
	 * @return the matching service notifs
	 */
	public java.util.List<ServiceNotif> findByOrganisationIds(
		long[] organisationIds);

	/**
	 * Returns a range of all the service notifs where organisationId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param organisationIds the organisation IDs
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @return the range of matching service notifs
	 */
	public java.util.List<ServiceNotif> findByOrganisationIds(
		long[] organisationIds, int start, int end);

	/**
	 * Returns an ordered range of all the service notifs where organisationId = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param organisationIds the organisation IDs
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching service notifs
	 */
	public java.util.List<ServiceNotif> findByOrganisationIds(
		long[] organisationIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceNotif>
			orderByComparator);

	/**
	 * Returns an ordered range of all the service notifs where organisationId = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param organisationId the organisation ID
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching service notifs
	 */
	public java.util.List<ServiceNotif> findByOrganisationIds(
		long[] organisationIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceNotif>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the service notifs where organisationId = &#63; from the database.
	 *
	 * @param organisationId the organisation ID
	 */
	public void removeByOrganisationIds(long organisationId);

	/**
	 * Returns the number of service notifs where organisationId = &#63;.
	 *
	 * @param organisationId the organisation ID
	 * @return the number of matching service notifs
	 */
	public int countByOrganisationIds(long organisationId);

	/**
	 * Returns the number of service notifs where organisationId = any &#63;.
	 *
	 * @param organisationIds the organisation IDs
	 * @return the number of matching service notifs
	 */
	public int countByOrganisationIds(long[] organisationIds);

	/**
	 * Caches the service notif in the entity cache if it is enabled.
	 *
	 * @param serviceNotif the service notif
	 */
	public void cacheResult(ServiceNotif serviceNotif);

	/**
	 * Caches the service notifs in the entity cache if it is enabled.
	 *
	 * @param serviceNotifs the service notifs
	 */
	public void cacheResult(java.util.List<ServiceNotif> serviceNotifs);

	/**
	 * Creates a new service notif with the primary key. Does not add the service notif to the database.
	 *
	 * @param serviceId the primary key for the new service notif
	 * @return the new service notif
	 */
	public ServiceNotif create(long serviceId);

	/**
	 * Removes the service notif with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param serviceId the primary key of the service notif
	 * @return the service notif that was removed
	 * @throws NoSuchServiceNotifException if a service notif with the primary key could not be found
	 */
	public ServiceNotif remove(long serviceId)
		throws NoSuchServiceNotifException;

	public ServiceNotif updateImpl(ServiceNotif serviceNotif);

	/**
	 * Returns the service notif with the primary key or throws a <code>NoSuchServiceNotifException</code> if it could not be found.
	 *
	 * @param serviceId the primary key of the service notif
	 * @return the service notif
	 * @throws NoSuchServiceNotifException if a service notif with the primary key could not be found
	 */
	public ServiceNotif findByPrimaryKey(long serviceId)
		throws NoSuchServiceNotifException;

	/**
	 * Returns the service notif with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param serviceId the primary key of the service notif
	 * @return the service notif, or <code>null</code> if a service notif with the primary key could not be found
	 */
	public ServiceNotif fetchByPrimaryKey(long serviceId);

	/**
	 * Returns all the service notifs.
	 *
	 * @return the service notifs
	 */
	public java.util.List<ServiceNotif> findAll();

	/**
	 * Returns a range of all the service notifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @return the range of service notifs
	 */
	public java.util.List<ServiceNotif> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the service notifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of service notifs
	 */
	public java.util.List<ServiceNotif> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceNotif>
			orderByComparator);

	/**
	 * Returns an ordered range of all the service notifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ServiceNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service notifs
	 * @param end the upper bound of the range of service notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of service notifs
	 */
	public java.util.List<ServiceNotif> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ServiceNotif>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the service notifs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of service notifs.
	 *
	 * @return the number of service notifs
	 */
	public int countAll();

}