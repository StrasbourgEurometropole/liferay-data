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

import eu.strasbourg.service.notif.exception.NoSuchNatureNotifException;
import eu.strasbourg.service.notif.model.NatureNotif;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the nature notif service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see NatureNotifUtil
 * @generated
 */
@ProviderType
public interface NatureNotifPersistence extends BasePersistence<NatureNotif> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link NatureNotifUtil} to access the nature notif persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, NatureNotif> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the nature notifs where serviceId = &#63;.
	 *
	 * @param serviceId the service ID
	 * @return the matching nature notifs
	 */
	public java.util.List<NatureNotif> findByServiceId(long serviceId);

	/**
	 * Returns a range of all the nature notifs where serviceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NatureNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceId the service ID
	 * @param start the lower bound of the range of nature notifs
	 * @param end the upper bound of the range of nature notifs (not inclusive)
	 * @return the range of matching nature notifs
	 */
	public java.util.List<NatureNotif> findByServiceId(
		long serviceId, int start, int end);

	/**
	 * Returns an ordered range of all the nature notifs where serviceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NatureNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceId the service ID
	 * @param start the lower bound of the range of nature notifs
	 * @param end the upper bound of the range of nature notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching nature notifs
	 */
	public java.util.List<NatureNotif> findByServiceId(
		long serviceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NatureNotif>
			orderByComparator);

	/**
	 * Returns an ordered range of all the nature notifs where serviceId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NatureNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param serviceId the service ID
	 * @param start the lower bound of the range of nature notifs
	 * @param end the upper bound of the range of nature notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching nature notifs
	 */
	public java.util.List<NatureNotif> findByServiceId(
		long serviceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NatureNotif>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first nature notif in the ordered set where serviceId = &#63;.
	 *
	 * @param serviceId the service ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching nature notif
	 * @throws NoSuchNatureNotifException if a matching nature notif could not be found
	 */
	public NatureNotif findByServiceId_First(
			long serviceId,
			com.liferay.portal.kernel.util.OrderByComparator<NatureNotif>
				orderByComparator)
		throws NoSuchNatureNotifException;

	/**
	 * Returns the first nature notif in the ordered set where serviceId = &#63;.
	 *
	 * @param serviceId the service ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching nature notif, or <code>null</code> if a matching nature notif could not be found
	 */
	public NatureNotif fetchByServiceId_First(
		long serviceId,
		com.liferay.portal.kernel.util.OrderByComparator<NatureNotif>
			orderByComparator);

	/**
	 * Returns the last nature notif in the ordered set where serviceId = &#63;.
	 *
	 * @param serviceId the service ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching nature notif
	 * @throws NoSuchNatureNotifException if a matching nature notif could not be found
	 */
	public NatureNotif findByServiceId_Last(
			long serviceId,
			com.liferay.portal.kernel.util.OrderByComparator<NatureNotif>
				orderByComparator)
		throws NoSuchNatureNotifException;

	/**
	 * Returns the last nature notif in the ordered set where serviceId = &#63;.
	 *
	 * @param serviceId the service ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching nature notif, or <code>null</code> if a matching nature notif could not be found
	 */
	public NatureNotif fetchByServiceId_Last(
		long serviceId,
		com.liferay.portal.kernel.util.OrderByComparator<NatureNotif>
			orderByComparator);

	/**
	 * Returns the nature notifs before and after the current nature notif in the ordered set where serviceId = &#63;.
	 *
	 * @param natureId the primary key of the current nature notif
	 * @param serviceId the service ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next nature notif
	 * @throws NoSuchNatureNotifException if a nature notif with the primary key could not be found
	 */
	public NatureNotif[] findByServiceId_PrevAndNext(
			long natureId, long serviceId,
			com.liferay.portal.kernel.util.OrderByComparator<NatureNotif>
				orderByComparator)
		throws NoSuchNatureNotifException;

	/**
	 * Removes all the nature notifs where serviceId = &#63; from the database.
	 *
	 * @param serviceId the service ID
	 */
	public void removeByServiceId(long serviceId);

	/**
	 * Returns the number of nature notifs where serviceId = &#63;.
	 *
	 * @param serviceId the service ID
	 * @return the number of matching nature notifs
	 */
	public int countByServiceId(long serviceId);

	/**
	 * Caches the nature notif in the entity cache if it is enabled.
	 *
	 * @param natureNotif the nature notif
	 */
	public void cacheResult(NatureNotif natureNotif);

	/**
	 * Caches the nature notifs in the entity cache if it is enabled.
	 *
	 * @param natureNotifs the nature notifs
	 */
	public void cacheResult(java.util.List<NatureNotif> natureNotifs);

	/**
	 * Creates a new nature notif with the primary key. Does not add the nature notif to the database.
	 *
	 * @param natureId the primary key for the new nature notif
	 * @return the new nature notif
	 */
	public NatureNotif create(long natureId);

	/**
	 * Removes the nature notif with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param natureId the primary key of the nature notif
	 * @return the nature notif that was removed
	 * @throws NoSuchNatureNotifException if a nature notif with the primary key could not be found
	 */
	public NatureNotif remove(long natureId) throws NoSuchNatureNotifException;

	public NatureNotif updateImpl(NatureNotif natureNotif);

	/**
	 * Returns the nature notif with the primary key or throws a <code>NoSuchNatureNotifException</code> if it could not be found.
	 *
	 * @param natureId the primary key of the nature notif
	 * @return the nature notif
	 * @throws NoSuchNatureNotifException if a nature notif with the primary key could not be found
	 */
	public NatureNotif findByPrimaryKey(long natureId)
		throws NoSuchNatureNotifException;

	/**
	 * Returns the nature notif with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param natureId the primary key of the nature notif
	 * @return the nature notif, or <code>null</code> if a nature notif with the primary key could not be found
	 */
	public NatureNotif fetchByPrimaryKey(long natureId);

	/**
	 * Returns all the nature notifs.
	 *
	 * @return the nature notifs
	 */
	public java.util.List<NatureNotif> findAll();

	/**
	 * Returns a range of all the nature notifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NatureNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nature notifs
	 * @param end the upper bound of the range of nature notifs (not inclusive)
	 * @return the range of nature notifs
	 */
	public java.util.List<NatureNotif> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the nature notifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NatureNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nature notifs
	 * @param end the upper bound of the range of nature notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of nature notifs
	 */
	public java.util.List<NatureNotif> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NatureNotif>
			orderByComparator);

	/**
	 * Returns an ordered range of all the nature notifs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>NatureNotifModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nature notifs
	 * @param end the upper bound of the range of nature notifs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of nature notifs
	 */
	public java.util.List<NatureNotif> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<NatureNotif>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the nature notifs from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of nature notifs.
	 *
	 * @return the number of nature notifs
	 */
	public int countAll();

}