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

package eu.strasbourg.service.activity.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.activity.exception.NoSuchAssociationActivityException;
import eu.strasbourg.service.activity.model.AssociationActivity;

/**
 * The persistence interface for the association activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see eu.strasbourg.service.activity.service.persistence.impl.AssociationActivityPersistenceImpl
 * @see AssociationActivityUtil
 * @generated
 */
@ProviderType
public interface AssociationActivityPersistence extends BasePersistence<AssociationActivity> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssociationActivityUtil} to access the association activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the association activities where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching association activities
	*/
	public java.util.List<AssociationActivity> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the association activities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @return the range of matching association activities
	*/
	public java.util.List<AssociationActivity> findByUuid(
		java.lang.String uuid, int start, int end);

	/**
	* Returns an ordered range of all the association activities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching association activities
	*/
	public java.util.List<AssociationActivity> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator);

	/**
	* Returns an ordered range of all the association activities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching association activities
	*/
	public java.util.List<AssociationActivity> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first association activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public AssociationActivity findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException;

	/**
	* Returns the first association activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public AssociationActivity fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator);

	/**
	* Returns the last association activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public AssociationActivity findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException;

	/**
	* Returns the last association activity in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public AssociationActivity fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator);

	/**
	* Returns the association activities before and after the current association activity in the ordered set where uuid = &#63;.
	*
	* @param associationActivityId the primary key of the current association activity
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next association activity
	* @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	*/
	public AssociationActivity[] findByUuid_PrevAndNext(
		long associationActivityId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException;

	/**
	* Removes all the association activities where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of association activities where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching association activities
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the association activity where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchAssociationActivityException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public AssociationActivity findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchAssociationActivityException;

	/**
	* Returns the association activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public AssociationActivity fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the association activity where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public AssociationActivity fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache);

	/**
	* Removes the association activity where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the association activity that was removed
	*/
	public AssociationActivity removeByUUID_G(java.lang.String uuid,
		long groupId) throws NoSuchAssociationActivityException;

	/**
	* Returns the number of association activities where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching association activities
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the association activities where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching association activities
	*/
	public java.util.List<AssociationActivity> findByUuid_C(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of all the association activities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @return the range of matching association activities
	*/
	public java.util.List<AssociationActivity> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

	/**
	* Returns an ordered range of all the association activities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching association activities
	*/
	public java.util.List<AssociationActivity> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator);

	/**
	* Returns an ordered range of all the association activities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching association activities
	*/
	public java.util.List<AssociationActivity> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first association activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public AssociationActivity findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException;

	/**
	* Returns the first association activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public AssociationActivity fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator);

	/**
	* Returns the last association activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public AssociationActivity findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException;

	/**
	* Returns the last association activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public AssociationActivity fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator);

	/**
	* Returns the association activities before and after the current association activity in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param associationActivityId the primary key of the current association activity
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next association activity
	* @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	*/
	public AssociationActivity[] findByUuid_C_PrevAndNext(
		long associationActivityId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException;

	/**
	* Removes all the association activities where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of association activities where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching association activities
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the association activities where associationId = &#63;.
	*
	* @param associationId the association ID
	* @return the matching association activities
	*/
	public java.util.List<AssociationActivity> findByAssociation(
		long associationId);

	/**
	* Returns a range of all the association activities where associationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param associationId the association ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @return the range of matching association activities
	*/
	public java.util.List<AssociationActivity> findByAssociation(
		long associationId, int start, int end);

	/**
	* Returns an ordered range of all the association activities where associationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param associationId the association ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching association activities
	*/
	public java.util.List<AssociationActivity> findByAssociation(
		long associationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator);

	/**
	* Returns an ordered range of all the association activities where associationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param associationId the association ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching association activities
	*/
	public java.util.List<AssociationActivity> findByAssociation(
		long associationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first association activity in the ordered set where associationId = &#63;.
	*
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public AssociationActivity findByAssociation_First(long associationId,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException;

	/**
	* Returns the first association activity in the ordered set where associationId = &#63;.
	*
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public AssociationActivity fetchByAssociation_First(long associationId,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator);

	/**
	* Returns the last association activity in the ordered set where associationId = &#63;.
	*
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public AssociationActivity findByAssociation_Last(long associationId,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException;

	/**
	* Returns the last association activity in the ordered set where associationId = &#63;.
	*
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public AssociationActivity fetchByAssociation_Last(long associationId,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator);

	/**
	* Returns the association activities before and after the current association activity in the ordered set where associationId = &#63;.
	*
	* @param associationActivityId the primary key of the current association activity
	* @param associationId the association ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next association activity
	* @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	*/
	public AssociationActivity[] findByAssociation_PrevAndNext(
		long associationActivityId, long associationId,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException;

	/**
	* Removes all the association activities where associationId = &#63; from the database.
	*
	* @param associationId the association ID
	*/
	public void removeByAssociation(long associationId);

	/**
	* Returns the number of association activities where associationId = &#63;.
	*
	* @param associationId the association ID
	* @return the number of matching association activities
	*/
	public int countByAssociation(long associationId);

	/**
	* Returns all the association activities where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching association activities
	*/
	public java.util.List<AssociationActivity> findByGroupId(long groupId);

	/**
	* Returns a range of all the association activities where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @return the range of matching association activities
	*/
	public java.util.List<AssociationActivity> findByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the association activities where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching association activities
	*/
	public java.util.List<AssociationActivity> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator);

	/**
	* Returns an ordered range of all the association activities where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching association activities
	*/
	public java.util.List<AssociationActivity> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first association activity in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public AssociationActivity findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException;

	/**
	* Returns the first association activity in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public AssociationActivity fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator);

	/**
	* Returns the last association activity in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching association activity
	* @throws NoSuchAssociationActivityException if a matching association activity could not be found
	*/
	public AssociationActivity findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException;

	/**
	* Returns the last association activity in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	public AssociationActivity fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator);

	/**
	* Returns the association activities before and after the current association activity in the ordered set where groupId = &#63;.
	*
	* @param associationActivityId the primary key of the current association activity
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next association activity
	* @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	*/
	public AssociationActivity[] findByGroupId_PrevAndNext(
		long associationActivityId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator)
		throws NoSuchAssociationActivityException;

	/**
	* Removes all the association activities where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of association activities where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching association activities
	*/
	public int countByGroupId(long groupId);

	/**
	* Caches the association activity in the entity cache if it is enabled.
	*
	* @param associationActivity the association activity
	*/
	public void cacheResult(AssociationActivity associationActivity);

	/**
	* Caches the association activities in the entity cache if it is enabled.
	*
	* @param associationActivities the association activities
	*/
	public void cacheResult(
		java.util.List<AssociationActivity> associationActivities);

	/**
	* Creates a new association activity with the primary key. Does not add the association activity to the database.
	*
	* @param associationActivityId the primary key for the new association activity
	* @return the new association activity
	*/
	public AssociationActivity create(long associationActivityId);

	/**
	* Removes the association activity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param associationActivityId the primary key of the association activity
	* @return the association activity that was removed
	* @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	*/
	public AssociationActivity remove(long associationActivityId)
		throws NoSuchAssociationActivityException;

	public AssociationActivity updateImpl(
		AssociationActivity associationActivity);

	/**
	* Returns the association activity with the primary key or throws a {@link NoSuchAssociationActivityException} if it could not be found.
	*
	* @param associationActivityId the primary key of the association activity
	* @return the association activity
	* @throws NoSuchAssociationActivityException if a association activity with the primary key could not be found
	*/
	public AssociationActivity findByPrimaryKey(long associationActivityId)
		throws NoSuchAssociationActivityException;

	/**
	* Returns the association activity with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param associationActivityId the primary key of the association activity
	* @return the association activity, or <code>null</code> if a association activity with the primary key could not be found
	*/
	public AssociationActivity fetchByPrimaryKey(long associationActivityId);

	@Override
	public java.util.Map<java.io.Serializable, AssociationActivity> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the association activities.
	*
	* @return the association activities
	*/
	public java.util.List<AssociationActivity> findAll();

	/**
	* Returns a range of all the association activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @return the range of association activities
	*/
	public java.util.List<AssociationActivity> findAll(int start, int end);

	/**
	* Returns an ordered range of all the association activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of association activities
	*/
	public java.util.List<AssociationActivity> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator);

	/**
	* Returns an ordered range of all the association activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of association activities
	*/
	public java.util.List<AssociationActivity> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssociationActivity> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the association activities from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of association activities.
	*
	* @return the number of association activities
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}