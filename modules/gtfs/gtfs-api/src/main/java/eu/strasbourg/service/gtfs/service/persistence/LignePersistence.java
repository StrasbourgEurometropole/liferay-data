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

import eu.strasbourg.service.gtfs.exception.NoSuchLigneException;
import eu.strasbourg.service.gtfs.model.Ligne;

/**
 * The persistence interface for the ligne service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.gtfs.service.persistence.impl.LignePersistenceImpl
 * @see LigneUtil
 * @generated
 */
@ProviderType
public interface LignePersistence extends BasePersistence<Ligne> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LigneUtil} to access the ligne persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the lignes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching lignes
	*/
	public java.util.List<Ligne> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the lignes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @return the range of matching lignes
	*/
	public java.util.List<Ligne> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the lignes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lignes
	*/
	public java.util.List<Ligne> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator);

	/**
	* Returns an ordered range of all the lignes where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lignes
	*/
	public java.util.List<Ligne> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ligne in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ligne
	* @throws NoSuchLigneException if a matching ligne could not be found
	*/
	public Ligne findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator)
		throws NoSuchLigneException;

	/**
	* Returns the first ligne in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	public Ligne fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator);

	/**
	* Returns the last ligne in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ligne
	* @throws NoSuchLigneException if a matching ligne could not be found
	*/
	public Ligne findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator)
		throws NoSuchLigneException;

	/**
	* Returns the last ligne in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	public Ligne fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator);

	/**
	* Returns the lignes before and after the current ligne in the ordered set where uuid = &#63;.
	*
	* @param ligneId the primary key of the current ligne
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ligne
	* @throws NoSuchLigneException if a ligne with the primary key could not be found
	*/
	public Ligne[] findByUuid_PrevAndNext(long ligneId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator)
		throws NoSuchLigneException;

	/**
	* Removes all the lignes where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of lignes where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching lignes
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the ligne where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchLigneException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching ligne
	* @throws NoSuchLigneException if a matching ligne could not be found
	*/
	public Ligne findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchLigneException;

	/**
	* Returns the ligne where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	public Ligne fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the ligne where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	public Ligne fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the ligne where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the ligne that was removed
	*/
	public Ligne removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchLigneException;

	/**
	* Returns the number of lignes where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching lignes
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the lignes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching lignes
	*/
	public java.util.List<Ligne> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the lignes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @return the range of matching lignes
	*/
	public java.util.List<Ligne> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the lignes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lignes
	*/
	public java.util.List<Ligne> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator);

	/**
	* Returns an ordered range of all the lignes where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lignes
	*/
	public java.util.List<Ligne> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ligne in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ligne
	* @throws NoSuchLigneException if a matching ligne could not be found
	*/
	public Ligne findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator)
		throws NoSuchLigneException;

	/**
	* Returns the first ligne in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	public Ligne fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator);

	/**
	* Returns the last ligne in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ligne
	* @throws NoSuchLigneException if a matching ligne could not be found
	*/
	public Ligne findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator)
		throws NoSuchLigneException;

	/**
	* Returns the last ligne in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	public Ligne fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator);

	/**
	* Returns the lignes before and after the current ligne in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param ligneId the primary key of the current ligne
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ligne
	* @throws NoSuchLigneException if a ligne with the primary key could not be found
	*/
	public Ligne[] findByUuid_C_PrevAndNext(long ligneId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator)
		throws NoSuchLigneException;

	/**
	* Removes all the lignes where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of lignes where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching lignes
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the lignes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching lignes
	*/
	public java.util.List<Ligne> findByGroupId(long groupId);

	/**
	* Returns a range of all the lignes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @return the range of matching lignes
	*/
	public java.util.List<Ligne> findByGroupId(long groupId, int start, int end);

	/**
	* Returns an ordered range of all the lignes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching lignes
	*/
	public java.util.List<Ligne> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator);

	/**
	* Returns an ordered range of all the lignes where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching lignes
	*/
	public java.util.List<Ligne> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ligne in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ligne
	* @throws NoSuchLigneException if a matching ligne could not be found
	*/
	public Ligne findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator)
		throws NoSuchLigneException;

	/**
	* Returns the first ligne in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	public Ligne fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator);

	/**
	* Returns the last ligne in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ligne
	* @throws NoSuchLigneException if a matching ligne could not be found
	*/
	public Ligne findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator)
		throws NoSuchLigneException;

	/**
	* Returns the last ligne in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	public Ligne fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator);

	/**
	* Returns the lignes before and after the current ligne in the ordered set where groupId = &#63;.
	*
	* @param ligneId the primary key of the current ligne
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ligne
	* @throws NoSuchLigneException if a ligne with the primary key could not be found
	*/
	public Ligne[] findByGroupId_PrevAndNext(long ligneId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator)
		throws NoSuchLigneException;

	/**
	* Removes all the lignes where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of lignes where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching lignes
	*/
	public int countByGroupId(long groupId);

	/**
	* Caches the ligne in the entity cache if it is enabled.
	*
	* @param ligne the ligne
	*/
	public void cacheResult(Ligne ligne);

	/**
	* Caches the lignes in the entity cache if it is enabled.
	*
	* @param lignes the lignes
	*/
	public void cacheResult(java.util.List<Ligne> lignes);

	/**
	* Creates a new ligne with the primary key. Does not add the ligne to the database.
	*
	* @param ligneId the primary key for the new ligne
	* @return the new ligne
	*/
	public Ligne create(long ligneId);

	/**
	* Removes the ligne with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ligneId the primary key of the ligne
	* @return the ligne that was removed
	* @throws NoSuchLigneException if a ligne with the primary key could not be found
	*/
	public Ligne remove(long ligneId) throws NoSuchLigneException;

	public Ligne updateImpl(Ligne ligne);

	/**
	* Returns the ligne with the primary key or throws a {@link NoSuchLigneException} if it could not be found.
	*
	* @param ligneId the primary key of the ligne
	* @return the ligne
	* @throws NoSuchLigneException if a ligne with the primary key could not be found
	*/
	public Ligne findByPrimaryKey(long ligneId) throws NoSuchLigneException;

	/**
	* Returns the ligne with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ligneId the primary key of the ligne
	* @return the ligne, or <code>null</code> if a ligne with the primary key could not be found
	*/
	public Ligne fetchByPrimaryKey(long ligneId);

	@Override
	public java.util.Map<java.io.Serializable, Ligne> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the lignes.
	*
	* @return the lignes
	*/
	public java.util.List<Ligne> findAll();

	/**
	* Returns a range of all the lignes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @return the range of lignes
	*/
	public java.util.List<Ligne> findAll(int start, int end);

	/**
	* Returns an ordered range of all the lignes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of lignes
	*/
	public java.util.List<Ligne> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator);

	/**
	* Returns an ordered range of all the lignes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of lignes
	*/
	public java.util.List<Ligne> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Ligne> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the lignes from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of lignes.
	*
	* @return the number of lignes
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}