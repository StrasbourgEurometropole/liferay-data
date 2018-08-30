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

package eu.strasbourg.service.project.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.project.exception.NoSuchSignataireException;
import eu.strasbourg.service.project.model.Signataire;

/**
 * The persistence interface for the signataire service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.persistence.impl.SignatairePersistenceImpl
 * @see SignataireUtil
 * @generated
 */
@ProviderType
public interface SignatairePersistence extends BasePersistence<Signataire> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SignataireUtil} to access the signataire persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the signataires where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching signataires
	*/
	public java.util.List<Signataire> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the signataires where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @return the range of matching signataires
	*/
	public java.util.List<Signataire> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the signataires where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching signataires
	*/
	public java.util.List<Signataire> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator);

	/**
	* Returns an ordered range of all the signataires where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching signataires
	*/
	public java.util.List<Signataire> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first signataire in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public Signataire findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException;

	/**
	* Returns the first signataire in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public Signataire fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator);

	/**
	* Returns the last signataire in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public Signataire findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException;

	/**
	* Returns the last signataire in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public Signataire fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator);

	/**
	* Returns the signataires before and after the current signataire in the ordered set where uuid = &#63;.
	*
	* @param signataireId the primary key of the current signataire
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next signataire
	* @throws NoSuchSignataireException if a signataire with the primary key could not be found
	*/
	public Signataire[] findByUuid_PrevAndNext(long signataireId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException;

	/**
	* Removes all the signataires where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of signataires where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching signataires
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the signataire where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchSignataireException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public Signataire findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchSignataireException;

	/**
	* Returns the signataire where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public Signataire fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the signataire where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public Signataire fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the signataire where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the signataire that was removed
	*/
	public Signataire removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchSignataireException;

	/**
	* Returns the number of signataires where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching signataires
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the signataires where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching signataires
	*/
	public java.util.List<Signataire> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the signataires where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @return the range of matching signataires
	*/
	public java.util.List<Signataire> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the signataires where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching signataires
	*/
	public java.util.List<Signataire> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator);

	/**
	* Returns an ordered range of all the signataires where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching signataires
	*/
	public java.util.List<Signataire> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first signataire in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public Signataire findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException;

	/**
	* Returns the first signataire in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public Signataire fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator);

	/**
	* Returns the last signataire in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public Signataire findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException;

	/**
	* Returns the last signataire in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public Signataire fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator);

	/**
	* Returns the signataires before and after the current signataire in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param signataireId the primary key of the current signataire
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next signataire
	* @throws NoSuchSignataireException if a signataire with the primary key could not be found
	*/
	public Signataire[] findByUuid_C_PrevAndNext(long signataireId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException;

	/**
	* Removes all the signataires where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of signataires where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching signataires
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the signataires where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching signataires
	*/
	public java.util.List<Signataire> findByGroupId(long groupId);

	/**
	* Returns a range of all the signataires where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @return the range of matching signataires
	*/
	public java.util.List<Signataire> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the signataires where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching signataires
	*/
	public java.util.List<Signataire> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator);

	/**
	* Returns an ordered range of all the signataires where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching signataires
	*/
	public java.util.List<Signataire> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first signataire in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public Signataire findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException;

	/**
	* Returns the first signataire in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public Signataire fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator);

	/**
	* Returns the last signataire in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public Signataire findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException;

	/**
	* Returns the last signataire in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public Signataire fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator);

	/**
	* Returns the signataires before and after the current signataire in the ordered set where groupId = &#63;.
	*
	* @param signataireId the primary key of the current signataire
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next signataire
	* @throws NoSuchSignataireException if a signataire with the primary key could not be found
	*/
	public Signataire[] findByGroupId_PrevAndNext(long signataireId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException;

	/**
	* Removes all the signataires where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of signataires where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching signataires
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the signataires where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @return the matching signataires
	*/
	public java.util.List<Signataire> findByPetition(long petitionId);

	/**
	* Returns a range of all the signataires where petitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param petitionId the petition ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @return the range of matching signataires
	*/
	public java.util.List<Signataire> findByPetition(long petitionId,
		int start, int end);

	/**
	* Returns an ordered range of all the signataires where petitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param petitionId the petition ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching signataires
	*/
	public java.util.List<Signataire> findByPetition(long petitionId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator);

	/**
	* Returns an ordered range of all the signataires where petitionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param petitionId the petition ID
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching signataires
	*/
	public java.util.List<Signataire> findByPetition(long petitionId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first signataire in the ordered set where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public Signataire findByPetition_First(long petitionId,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException;

	/**
	* Returns the first signataire in the ordered set where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public Signataire fetchByPetition_First(long petitionId,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator);

	/**
	* Returns the last signataire in the ordered set where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public Signataire findByPetition_Last(long petitionId,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException;

	/**
	* Returns the last signataire in the ordered set where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public Signataire fetchByPetition_Last(long petitionId,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator);

	/**
	* Returns the signataires before and after the current signataire in the ordered set where petitionId = &#63;.
	*
	* @param signataireId the primary key of the current signataire
	* @param petitionId the petition ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next signataire
	* @throws NoSuchSignataireException if a signataire with the primary key could not be found
	*/
	public Signataire[] findByPetition_PrevAndNext(long signataireId,
		long petitionId,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException;

	/**
	* Removes all the signataires where petitionId = &#63; from the database.
	*
	* @param petitionId the petition ID
	*/
	public void removeByPetition(long petitionId);

	/**
	* Returns the number of signataires where petitionId = &#63;.
	*
	* @param petitionId the petition ID
	* @return the number of matching signataires
	*/
	public int countByPetition(long petitionId);

	/**
	* Returns all the signataires where petitionId = &#63; and signataireName = &#63;.
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @return the matching signataires
	*/
	public java.util.List<Signataire> findByPetitionIdAndSignataireName(
		long petitionId, java.lang.String signataireName);

	/**
	* Returns a range of all the signataires where petitionId = &#63; and signataireName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @return the range of matching signataires
	*/
	public java.util.List<Signataire> findByPetitionIdAndSignataireName(
		long petitionId, java.lang.String signataireName, int start, int end);

	/**
	* Returns an ordered range of all the signataires where petitionId = &#63; and signataireName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching signataires
	*/
	public java.util.List<Signataire> findByPetitionIdAndSignataireName(
		long petitionId, java.lang.String signataireName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator);

	/**
	* Returns an ordered range of all the signataires where petitionId = &#63; and signataireName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching signataires
	*/
	public java.util.List<Signataire> findByPetitionIdAndSignataireName(
		long petitionId, java.lang.String signataireName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first signataire in the ordered set where petitionId = &#63; and signataireName = &#63;.
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public Signataire findByPetitionIdAndSignataireName_First(long petitionId,
		java.lang.String signataireName,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException;

	/**
	* Returns the first signataire in the ordered set where petitionId = &#63; and signataireName = &#63;.
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public Signataire fetchByPetitionIdAndSignataireName_First(
		long petitionId, java.lang.String signataireName,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator);

	/**
	* Returns the last signataire in the ordered set where petitionId = &#63; and signataireName = &#63;.
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire
	* @throws NoSuchSignataireException if a matching signataire could not be found
	*/
	public Signataire findByPetitionIdAndSignataireName_Last(long petitionId,
		java.lang.String signataireName,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException;

	/**
	* Returns the last signataire in the ordered set where petitionId = &#63; and signataireName = &#63;.
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching signataire, or <code>null</code> if a matching signataire could not be found
	*/
	public Signataire fetchByPetitionIdAndSignataireName_Last(long petitionId,
		java.lang.String signataireName,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator);

	/**
	* Returns the signataires before and after the current signataire in the ordered set where petitionId = &#63; and signataireName = &#63;.
	*
	* @param signataireId the primary key of the current signataire
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next signataire
	* @throws NoSuchSignataireException if a signataire with the primary key could not be found
	*/
	public Signataire[] findByPetitionIdAndSignataireName_PrevAndNext(
		long signataireId, long petitionId, java.lang.String signataireName,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator)
		throws NoSuchSignataireException;

	/**
	* Removes all the signataires where petitionId = &#63; and signataireName = &#63; from the database.
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	*/
	public void removeByPetitionIdAndSignataireName(long petitionId,
		java.lang.String signataireName);

	/**
	* Returns the number of signataires where petitionId = &#63; and signataireName = &#63;.
	*
	* @param petitionId the petition ID
	* @param signataireName the signataire name
	* @return the number of matching signataires
	*/
	public int countByPetitionIdAndSignataireName(long petitionId,
		java.lang.String signataireName);

	/**
	* Caches the signataire in the entity cache if it is enabled.
	*
	* @param signataire the signataire
	*/
	public void cacheResult(Signataire signataire);

	/**
	* Caches the signataires in the entity cache if it is enabled.
	*
	* @param signataires the signataires
	*/
	public void cacheResult(java.util.List<Signataire> signataires);

	/**
	* Creates a new signataire with the primary key. Does not add the signataire to the database.
	*
	* @param signataireId the primary key for the new signataire
	* @return the new signataire
	*/
	public Signataire create(long signataireId);

	/**
	* Removes the signataire with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param signataireId the primary key of the signataire
	* @return the signataire that was removed
	* @throws NoSuchSignataireException if a signataire with the primary key could not be found
	*/
	public Signataire remove(long signataireId)
		throws NoSuchSignataireException;

	public Signataire updateImpl(Signataire signataire);

	/**
	* Returns the signataire with the primary key or throws a {@link NoSuchSignataireException} if it could not be found.
	*
	* @param signataireId the primary key of the signataire
	* @return the signataire
	* @throws NoSuchSignataireException if a signataire with the primary key could not be found
	*/
	public Signataire findByPrimaryKey(long signataireId)
		throws NoSuchSignataireException;

	/**
	* Returns the signataire with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param signataireId the primary key of the signataire
	* @return the signataire, or <code>null</code> if a signataire with the primary key could not be found
	*/
	public Signataire fetchByPrimaryKey(long signataireId);

	@Override
	public java.util.Map<java.io.Serializable, Signataire> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the signataires.
	*
	* @return the signataires
	*/
	public java.util.List<Signataire> findAll();

	/**
	* Returns a range of all the signataires.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @return the range of signataires
	*/
	public java.util.List<Signataire> findAll(int start, int end);

	/**
	* Returns an ordered range of all the signataires.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of signataires
	*/
	public java.util.List<Signataire> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator);

	/**
	* Returns an ordered range of all the signataires.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SignataireModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of signataires
	* @param end the upper bound of the range of signataires (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of signataires
	*/
	public java.util.List<Signataire> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Signataire> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the signataires from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of signataires.
	*
	* @return the number of signataires
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}