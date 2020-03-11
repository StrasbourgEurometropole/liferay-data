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

import eu.strasbourg.service.gtfs.exception.NoSuchAgencyException;
import eu.strasbourg.service.gtfs.model.Agency;

/**
 * The persistence interface for the agency service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.gtfs.service.persistence.impl.AgencyPersistenceImpl
 * @see AgencyUtil
 * @generated
 */
@ProviderType
public interface AgencyPersistence extends BasePersistence<Agency> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AgencyUtil} to access the agency persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the agencies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching agencies
	*/
	public java.util.List<Agency> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the agencies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of agencies
	* @param end the upper bound of the range of agencies (not inclusive)
	* @return the range of matching agencies
	*/
	public java.util.List<Agency> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the agencies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of agencies
	* @param end the upper bound of the range of agencies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching agencies
	*/
	public java.util.List<Agency> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Agency> orderByComparator);

	/**
	* Returns an ordered range of all the agencies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of agencies
	* @param end the upper bound of the range of agencies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching agencies
	*/
	public java.util.List<Agency> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Agency> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first agency in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agency
	* @throws NoSuchAgencyException if a matching agency could not be found
	*/
	public Agency findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Agency> orderByComparator)
		throws NoSuchAgencyException;

	/**
	* Returns the first agency in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agency, or <code>null</code> if a matching agency could not be found
	*/
	public Agency fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Agency> orderByComparator);

	/**
	* Returns the last agency in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agency
	* @throws NoSuchAgencyException if a matching agency could not be found
	*/
	public Agency findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Agency> orderByComparator)
		throws NoSuchAgencyException;

	/**
	* Returns the last agency in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agency, or <code>null</code> if a matching agency could not be found
	*/
	public Agency fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Agency> orderByComparator);

	/**
	* Returns the agencies before and after the current agency in the ordered set where uuid = &#63;.
	*
	* @param id the primary key of the current agency
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next agency
	* @throws NoSuchAgencyException if a agency with the primary key could not be found
	*/
	public Agency[] findByUuid_PrevAndNext(long id, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Agency> orderByComparator)
		throws NoSuchAgencyException;

	/**
	* Removes all the agencies where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of agencies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching agencies
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Caches the agency in the entity cache if it is enabled.
	*
	* @param agency the agency
	*/
	public void cacheResult(Agency agency);

	/**
	* Caches the agencies in the entity cache if it is enabled.
	*
	* @param agencies the agencies
	*/
	public void cacheResult(java.util.List<Agency> agencies);

	/**
	* Creates a new agency with the primary key. Does not add the agency to the database.
	*
	* @param id the primary key for the new agency
	* @return the new agency
	*/
	public Agency create(long id);

	/**
	* Removes the agency with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the agency
	* @return the agency that was removed
	* @throws NoSuchAgencyException if a agency with the primary key could not be found
	*/
	public Agency remove(long id) throws NoSuchAgencyException;

	public Agency updateImpl(Agency agency);

	/**
	* Returns the agency with the primary key or throws a {@link NoSuchAgencyException} if it could not be found.
	*
	* @param id the primary key of the agency
	* @return the agency
	* @throws NoSuchAgencyException if a agency with the primary key could not be found
	*/
	public Agency findByPrimaryKey(long id) throws NoSuchAgencyException;

	/**
	* Returns the agency with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the agency
	* @return the agency, or <code>null</code> if a agency with the primary key could not be found
	*/
	public Agency fetchByPrimaryKey(long id);

	@Override
	public java.util.Map<java.io.Serializable, Agency> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the agencies.
	*
	* @return the agencies
	*/
	public java.util.List<Agency> findAll();

	/**
	* Returns a range of all the agencies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of agencies
	* @param end the upper bound of the range of agencies (not inclusive)
	* @return the range of agencies
	*/
	public java.util.List<Agency> findAll(int start, int end);

	/**
	* Returns an ordered range of all the agencies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of agencies
	* @param end the upper bound of the range of agencies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of agencies
	*/
	public java.util.List<Agency> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Agency> orderByComparator);

	/**
	* Returns an ordered range of all the agencies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgencyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of agencies
	* @param end the upper bound of the range of agencies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of agencies
	*/
	public java.util.List<Agency> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Agency> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the agencies from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of agencies.
	*
	* @return the number of agencies
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}