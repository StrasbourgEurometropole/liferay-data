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

import eu.strasbourg.service.agenda.exception.NoSuchManifestationException;
import eu.strasbourg.service.agenda.model.Manifestation;

import java.util.Date;

/**
 * The persistence interface for the manifestation service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.agenda.service.persistence.impl.ManifestationPersistenceImpl
 * @see ManifestationUtil
 * @generated
 */
@ProviderType
public interface ManifestationPersistence extends BasePersistence<Manifestation> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ManifestationUtil} to access the manifestation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the manifestations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching manifestations
	*/
	public java.util.List<Manifestation> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the manifestations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @return the range of matching manifestations
	*/
	public java.util.List<Manifestation> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the manifestations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching manifestations
	*/
	public java.util.List<Manifestation> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns an ordered range of all the manifestations where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching manifestations
	*/
	public java.util.List<Manifestation> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first manifestation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching manifestation
	* @throws NoSuchManifestationException if a matching manifestation could not be found
	*/
	public Manifestation findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException;

	/**
	* Returns the first manifestation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching manifestation, or <code>null</code> if a matching manifestation could not be found
	*/
	public Manifestation fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns the last manifestation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching manifestation
	* @throws NoSuchManifestationException if a matching manifestation could not be found
	*/
	public Manifestation findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException;

	/**
	* Returns the last manifestation in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching manifestation, or <code>null</code> if a matching manifestation could not be found
	*/
	public Manifestation fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns the manifestations before and after the current manifestation in the ordered set where uuid = &#63;.
	*
	* @param manifestationId the primary key of the current manifestation
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next manifestation
	* @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	*/
	public Manifestation[] findByUuid_PrevAndNext(long manifestationId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException;

	/**
	* Removes all the manifestations where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of manifestations where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching manifestations
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the manifestation where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchManifestationException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching manifestation
	* @throws NoSuchManifestationException if a matching manifestation could not be found
	*/
	public Manifestation findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchManifestationException;

	/**
	* Returns the manifestation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching manifestation, or <code>null</code> if a matching manifestation could not be found
	*/
	public Manifestation fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the manifestation where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching manifestation, or <code>null</code> if a matching manifestation could not be found
	*/
	public Manifestation fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the manifestation where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the manifestation that was removed
	*/
	public Manifestation removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchManifestationException;

	/**
	* Returns the number of manifestations where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching manifestations
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the manifestations where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching manifestations
	*/
	public java.util.List<Manifestation> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the manifestations where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @return the range of matching manifestations
	*/
	public java.util.List<Manifestation> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the manifestations where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching manifestations
	*/
	public java.util.List<Manifestation> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns an ordered range of all the manifestations where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching manifestations
	*/
	public java.util.List<Manifestation> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first manifestation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching manifestation
	* @throws NoSuchManifestationException if a matching manifestation could not be found
	*/
	public Manifestation findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException;

	/**
	* Returns the first manifestation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching manifestation, or <code>null</code> if a matching manifestation could not be found
	*/
	public Manifestation fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns the last manifestation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching manifestation
	* @throws NoSuchManifestationException if a matching manifestation could not be found
	*/
	public Manifestation findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException;

	/**
	* Returns the last manifestation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching manifestation, or <code>null</code> if a matching manifestation could not be found
	*/
	public Manifestation fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns the manifestations before and after the current manifestation in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param manifestationId the primary key of the current manifestation
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next manifestation
	* @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	*/
	public Manifestation[] findByUuid_C_PrevAndNext(long manifestationId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException;

	/**
	* Removes all the manifestations where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of manifestations where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching manifestations
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the manifestations where title = &#63;.
	*
	* @param title the title
	* @return the matching manifestations
	*/
	public java.util.List<Manifestation> findBytitle(java.lang.String title);

	/**
	* Returns a range of all the manifestations where title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @return the range of matching manifestations
	*/
	public java.util.List<Manifestation> findBytitle(java.lang.String title,
		int start, int end);

	/**
	* Returns an ordered range of all the manifestations where title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching manifestations
	*/
	public java.util.List<Manifestation> findBytitle(java.lang.String title,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns an ordered range of all the manifestations where title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching manifestations
	*/
	public java.util.List<Manifestation> findBytitle(java.lang.String title,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first manifestation in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching manifestation
	* @throws NoSuchManifestationException if a matching manifestation could not be found
	*/
	public Manifestation findBytitle_First(java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException;

	/**
	* Returns the first manifestation in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching manifestation, or <code>null</code> if a matching manifestation could not be found
	*/
	public Manifestation fetchBytitle_First(java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns the last manifestation in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching manifestation
	* @throws NoSuchManifestationException if a matching manifestation could not be found
	*/
	public Manifestation findBytitle_Last(java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException;

	/**
	* Returns the last manifestation in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching manifestation, or <code>null</code> if a matching manifestation could not be found
	*/
	public Manifestation fetchBytitle_Last(java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns the manifestations before and after the current manifestation in the ordered set where title = &#63;.
	*
	* @param manifestationId the primary key of the current manifestation
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next manifestation
	* @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	*/
	public Manifestation[] findBytitle_PrevAndNext(long manifestationId,
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException;

	/**
	* Removes all the manifestations where title = &#63; from the database.
	*
	* @param title the title
	*/
	public void removeBytitle(java.lang.String title);

	/**
	* Returns the number of manifestations where title = &#63;.
	*
	* @param title the title
	* @return the number of matching manifestations
	*/
	public int countBytitle(java.lang.String title);

	/**
	* Returns all the manifestations where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching manifestations
	*/
	public java.util.List<Manifestation> findByGroupId(long groupId);

	/**
	* Returns a range of all the manifestations where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @return the range of matching manifestations
	*/
	public java.util.List<Manifestation> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the manifestations where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching manifestations
	*/
	public java.util.List<Manifestation> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns an ordered range of all the manifestations where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching manifestations
	*/
	public java.util.List<Manifestation> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first manifestation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching manifestation
	* @throws NoSuchManifestationException if a matching manifestation could not be found
	*/
	public Manifestation findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException;

	/**
	* Returns the first manifestation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching manifestation, or <code>null</code> if a matching manifestation could not be found
	*/
	public Manifestation fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns the last manifestation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching manifestation
	* @throws NoSuchManifestationException if a matching manifestation could not be found
	*/
	public Manifestation findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException;

	/**
	* Returns the last manifestation in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching manifestation, or <code>null</code> if a matching manifestation could not be found
	*/
	public Manifestation fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns the manifestations before and after the current manifestation in the ordered set where groupId = &#63;.
	*
	* @param manifestationId the primary key of the current manifestation
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next manifestation
	* @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	*/
	public Manifestation[] findByGroupId_PrevAndNext(long manifestationId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException;

	/**
	* Removes all the manifestations where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of manifestations where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching manifestations
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the manifestations where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @return the matching manifestations
	*/
	public java.util.List<Manifestation> findByPublicationDateAndStatus(
		Date publicationDate, int status);

	/**
	* Returns a range of all the manifestations where publicationDate &lt; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @return the range of matching manifestations
	*/
	public java.util.List<Manifestation> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end);

	/**
	* Returns an ordered range of all the manifestations where publicationDate &lt; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching manifestations
	*/
	public java.util.List<Manifestation> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns an ordered range of all the manifestations where publicationDate &lt; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching manifestations
	*/
	public java.util.List<Manifestation> findByPublicationDateAndStatus(
		Date publicationDate, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first manifestation in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching manifestation
	* @throws NoSuchManifestationException if a matching manifestation could not be found
	*/
	public Manifestation findByPublicationDateAndStatus_First(
		Date publicationDate, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException;

	/**
	* Returns the first manifestation in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching manifestation, or <code>null</code> if a matching manifestation could not be found
	*/
	public Manifestation fetchByPublicationDateAndStatus_First(
		Date publicationDate, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns the last manifestation in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching manifestation
	* @throws NoSuchManifestationException if a matching manifestation could not be found
	*/
	public Manifestation findByPublicationDateAndStatus_Last(
		Date publicationDate, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException;

	/**
	* Returns the last manifestation in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching manifestation, or <code>null</code> if a matching manifestation could not be found
	*/
	public Manifestation fetchByPublicationDateAndStatus_Last(
		Date publicationDate, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns the manifestations before and after the current manifestation in the ordered set where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param manifestationId the primary key of the current manifestation
	* @param publicationDate the publication date
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next manifestation
	* @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	*/
	public Manifestation[] findByPublicationDateAndStatus_PrevAndNext(
		long manifestationId, Date publicationDate, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator)
		throws NoSuchManifestationException;

	/**
	* Removes all the manifestations where publicationDate &lt; &#63; and status = &#63; from the database.
	*
	* @param publicationDate the publication date
	* @param status the status
	*/
	public void removeByPublicationDateAndStatus(Date publicationDate,
		int status);

	/**
	* Returns the number of manifestations where publicationDate &lt; &#63; and status = &#63;.
	*
	* @param publicationDate the publication date
	* @param status the status
	* @return the number of matching manifestations
	*/
	public int countByPublicationDateAndStatus(Date publicationDate, int status);

	/**
	* Caches the manifestation in the entity cache if it is enabled.
	*
	* @param manifestation the manifestation
	*/
	public void cacheResult(Manifestation manifestation);

	/**
	* Caches the manifestations in the entity cache if it is enabled.
	*
	* @param manifestations the manifestations
	*/
	public void cacheResult(java.util.List<Manifestation> manifestations);

	/**
	* Creates a new manifestation with the primary key. Does not add the manifestation to the database.
	*
	* @param manifestationId the primary key for the new manifestation
	* @return the new manifestation
	*/
	public Manifestation create(long manifestationId);

	/**
	* Removes the manifestation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param manifestationId the primary key of the manifestation
	* @return the manifestation that was removed
	* @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	*/
	public Manifestation remove(long manifestationId)
		throws NoSuchManifestationException;

	public Manifestation updateImpl(Manifestation manifestation);

	/**
	* Returns the manifestation with the primary key or throws a {@link NoSuchManifestationException} if it could not be found.
	*
	* @param manifestationId the primary key of the manifestation
	* @return the manifestation
	* @throws NoSuchManifestationException if a manifestation with the primary key could not be found
	*/
	public Manifestation findByPrimaryKey(long manifestationId)
		throws NoSuchManifestationException;

	/**
	* Returns the manifestation with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param manifestationId the primary key of the manifestation
	* @return the manifestation, or <code>null</code> if a manifestation with the primary key could not be found
	*/
	public Manifestation fetchByPrimaryKey(long manifestationId);

	@Override
	public java.util.Map<java.io.Serializable, Manifestation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the manifestations.
	*
	* @return the manifestations
	*/
	public java.util.List<Manifestation> findAll();

	/**
	* Returns a range of all the manifestations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @return the range of manifestations
	*/
	public java.util.List<Manifestation> findAll(int start, int end);

	/**
	* Returns an ordered range of all the manifestations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of manifestations
	*/
	public java.util.List<Manifestation> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator);

	/**
	* Returns an ordered range of all the manifestations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of manifestations
	*/
	public java.util.List<Manifestation> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Manifestation> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the manifestations from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of manifestations.
	*
	* @return the number of manifestations
	*/
	public int countAll();

	/**
	* Returns the primaryKeys of events associated with the manifestation.
	*
	* @param pk the primary key of the manifestation
	* @return long[] of the primaryKeys of events associated with the manifestation
	*/
	public long[] getEventPrimaryKeys(long pk);

	/**
	* Returns all the events associated with the manifestation.
	*
	* @param pk the primary key of the manifestation
	* @return the events associated with the manifestation
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getEvents(
		long pk);

	/**
	* Returns a range of all the events associated with the manifestation.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the manifestation
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @return the range of events associated with the manifestation
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getEvents(
		long pk, int start, int end);

	/**
	* Returns an ordered range of all the events associated with the manifestation.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ManifestationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the manifestation
	* @param start the lower bound of the range of manifestations
	* @param end the upper bound of the range of manifestations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of events associated with the manifestation
	*/
	public java.util.List<eu.strasbourg.service.agenda.model.Event> getEvents(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.agenda.model.Event> orderByComparator);

	/**
	* Returns the number of events associated with the manifestation.
	*
	* @param pk the primary key of the manifestation
	* @return the number of events associated with the manifestation
	*/
	public int getEventsSize(long pk);

	/**
	* Returns <code>true</code> if the event is associated with the manifestation.
	*
	* @param pk the primary key of the manifestation
	* @param eventPK the primary key of the event
	* @return <code>true</code> if the event is associated with the manifestation; <code>false</code> otherwise
	*/
	public boolean containsEvent(long pk, long eventPK);

	/**
	* Returns <code>true</code> if the manifestation has any events associated with it.
	*
	* @param pk the primary key of the manifestation to check for associations with events
	* @return <code>true</code> if the manifestation has any events associated with it; <code>false</code> otherwise
	*/
	public boolean containsEvents(long pk);

	/**
	* Adds an association between the manifestation and the event. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the manifestation
	* @param eventPK the primary key of the event
	*/
	public void addEvent(long pk, long eventPK);

	/**
	* Adds an association between the manifestation and the event. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the manifestation
	* @param event the event
	*/
	public void addEvent(long pk, eu.strasbourg.service.agenda.model.Event event);

	/**
	* Adds an association between the manifestation and the events. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the manifestation
	* @param eventPKs the primary keys of the events
	*/
	public void addEvents(long pk, long[] eventPKs);

	/**
	* Adds an association between the manifestation and the events. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the manifestation
	* @param events the events
	*/
	public void addEvents(long pk,
		java.util.List<eu.strasbourg.service.agenda.model.Event> events);

	/**
	* Clears all associations between the manifestation and its events. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the manifestation to clear the associated events from
	*/
	public void clearEvents(long pk);

	/**
	* Removes the association between the manifestation and the event. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the manifestation
	* @param eventPK the primary key of the event
	*/
	public void removeEvent(long pk, long eventPK);

	/**
	* Removes the association between the manifestation and the event. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the manifestation
	* @param event the event
	*/
	public void removeEvent(long pk,
		eu.strasbourg.service.agenda.model.Event event);

	/**
	* Removes the association between the manifestation and the events. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the manifestation
	* @param eventPKs the primary keys of the events
	*/
	public void removeEvents(long pk, long[] eventPKs);

	/**
	* Removes the association between the manifestation and the events. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the manifestation
	* @param events the events
	*/
	public void removeEvents(long pk,
		java.util.List<eu.strasbourg.service.agenda.model.Event> events);

	/**
	* Sets the events associated with the manifestation, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the manifestation
	* @param eventPKs the primary keys of the events to be associated with the manifestation
	*/
	public void setEvents(long pk, long[] eventPKs);

	/**
	* Sets the events associated with the manifestation, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the manifestation
	* @param events the events to be associated with the manifestation
	*/
	public void setEvents(long pk,
		java.util.List<eu.strasbourg.service.agenda.model.Event> events);

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}