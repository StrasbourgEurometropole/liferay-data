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

package eu.strasbourg.service.edition.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.edition.exception.NoSuchEditionGalleryException;
import eu.strasbourg.service.edition.model.EditionGallery;

/**
 * The persistence interface for the edition gallery service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.edition.service.persistence.impl.EditionGalleryPersistenceImpl
 * @see EditionGalleryUtil
 * @generated
 */
@ProviderType
public interface EditionGalleryPersistence extends BasePersistence<EditionGallery> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EditionGalleryUtil} to access the edition gallery persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the edition galleries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching edition galleries
	*/
	public java.util.List<EditionGallery> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the edition galleries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of edition galleries
	* @param end the upper bound of the range of edition galleries (not inclusive)
	* @return the range of matching edition galleries
	*/
	public java.util.List<EditionGallery> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the edition galleries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of edition galleries
	* @param end the upper bound of the range of edition galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching edition galleries
	*/
	public java.util.List<EditionGallery> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator);

	/**
	* Returns an ordered range of all the edition galleries where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of edition galleries
	* @param end the upper bound of the range of edition galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching edition galleries
	*/
	public java.util.List<EditionGallery> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first edition gallery in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition gallery
	* @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	*/
	public EditionGallery findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException;

	/**
	* Returns the first edition gallery in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public EditionGallery fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator);

	/**
	* Returns the last edition gallery in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition gallery
	* @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	*/
	public EditionGallery findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException;

	/**
	* Returns the last edition gallery in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public EditionGallery fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator);

	/**
	* Returns the edition galleries before and after the current edition gallery in the ordered set where uuid = &#63;.
	*
	* @param galleryId the primary key of the current edition gallery
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next edition gallery
	* @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	*/
	public EditionGallery[] findByUuid_PrevAndNext(long galleryId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException;

	/**
	* Removes all the edition galleries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of edition galleries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching edition galleries
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the edition gallery where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEditionGalleryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching edition gallery
	* @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	*/
	public EditionGallery findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchEditionGalleryException;

	/**
	* Returns the edition gallery where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public EditionGallery fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the edition gallery where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public EditionGallery fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the edition gallery where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the edition gallery that was removed
	*/
	public EditionGallery removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchEditionGalleryException;

	/**
	* Returns the number of edition galleries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching edition galleries
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the edition galleries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching edition galleries
	*/
	public java.util.List<EditionGallery> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the edition galleries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of edition galleries
	* @param end the upper bound of the range of edition galleries (not inclusive)
	* @return the range of matching edition galleries
	*/
	public java.util.List<EditionGallery> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the edition galleries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of edition galleries
	* @param end the upper bound of the range of edition galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching edition galleries
	*/
	public java.util.List<EditionGallery> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator);

	/**
	* Returns an ordered range of all the edition galleries where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of edition galleries
	* @param end the upper bound of the range of edition galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching edition galleries
	*/
	public java.util.List<EditionGallery> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first edition gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition gallery
	* @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	*/
	public EditionGallery findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException;

	/**
	* Returns the first edition gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public EditionGallery fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator);

	/**
	* Returns the last edition gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition gallery
	* @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	*/
	public EditionGallery findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException;

	/**
	* Returns the last edition gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public EditionGallery fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator);

	/**
	* Returns the edition galleries before and after the current edition gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param galleryId the primary key of the current edition gallery
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next edition gallery
	* @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	*/
	public EditionGallery[] findByUuid_C_PrevAndNext(long galleryId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException;

	/**
	* Removes all the edition galleries where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of edition galleries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching edition galleries
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the edition galleries where title = &#63;.
	*
	* @param title the title
	* @return the matching edition galleries
	*/
	public java.util.List<EditionGallery> findBytitle(java.lang.String title);

	/**
	* Returns a range of all the edition galleries where title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of edition galleries
	* @param end the upper bound of the range of edition galleries (not inclusive)
	* @return the range of matching edition galleries
	*/
	public java.util.List<EditionGallery> findBytitle(java.lang.String title,
		int start, int end);

	/**
	* Returns an ordered range of all the edition galleries where title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of edition galleries
	* @param end the upper bound of the range of edition galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching edition galleries
	*/
	public java.util.List<EditionGallery> findBytitle(java.lang.String title,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator);

	/**
	* Returns an ordered range of all the edition galleries where title = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param title the title
	* @param start the lower bound of the range of edition galleries
	* @param end the upper bound of the range of edition galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching edition galleries
	*/
	public java.util.List<EditionGallery> findBytitle(java.lang.String title,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first edition gallery in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition gallery
	* @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	*/
	public EditionGallery findBytitle_First(java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException;

	/**
	* Returns the first edition gallery in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public EditionGallery fetchBytitle_First(java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator);

	/**
	* Returns the last edition gallery in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition gallery
	* @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	*/
	public EditionGallery findBytitle_Last(java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException;

	/**
	* Returns the last edition gallery in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public EditionGallery fetchBytitle_Last(java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator);

	/**
	* Returns the edition galleries before and after the current edition gallery in the ordered set where title = &#63;.
	*
	* @param galleryId the primary key of the current edition gallery
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next edition gallery
	* @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	*/
	public EditionGallery[] findBytitle_PrevAndNext(long galleryId,
		java.lang.String title,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator)
		throws NoSuchEditionGalleryException;

	/**
	* Removes all the edition galleries where title = &#63; from the database.
	*
	* @param title the title
	*/
	public void removeBytitle(java.lang.String title);

	/**
	* Returns the number of edition galleries where title = &#63;.
	*
	* @param title the title
	* @return the number of matching edition galleries
	*/
	public int countBytitle(java.lang.String title);

	/**
	* Caches the edition gallery in the entity cache if it is enabled.
	*
	* @param editionGallery the edition gallery
	*/
	public void cacheResult(EditionGallery editionGallery);

	/**
	* Caches the edition galleries in the entity cache if it is enabled.
	*
	* @param editionGalleries the edition galleries
	*/
	public void cacheResult(java.util.List<EditionGallery> editionGalleries);

	/**
	* Creates a new edition gallery with the primary key. Does not add the edition gallery to the database.
	*
	* @param galleryId the primary key for the new edition gallery
	* @return the new edition gallery
	*/
	public EditionGallery create(long galleryId);

	/**
	* Removes the edition gallery with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param galleryId the primary key of the edition gallery
	* @return the edition gallery that was removed
	* @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	*/
	public EditionGallery remove(long galleryId)
		throws NoSuchEditionGalleryException;

	public EditionGallery updateImpl(EditionGallery editionGallery);

	/**
	* Returns the edition gallery with the primary key or throws a {@link NoSuchEditionGalleryException} if it could not be found.
	*
	* @param galleryId the primary key of the edition gallery
	* @return the edition gallery
	* @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	*/
	public EditionGallery findByPrimaryKey(long galleryId)
		throws NoSuchEditionGalleryException;

	/**
	* Returns the edition gallery with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param galleryId the primary key of the edition gallery
	* @return the edition gallery, or <code>null</code> if a edition gallery with the primary key could not be found
	*/
	public EditionGallery fetchByPrimaryKey(long galleryId);

	@Override
	public java.util.Map<java.io.Serializable, EditionGallery> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the edition galleries.
	*
	* @return the edition galleries
	*/
	public java.util.List<EditionGallery> findAll();

	/**
	* Returns a range of all the edition galleries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of edition galleries
	* @param end the upper bound of the range of edition galleries (not inclusive)
	* @return the range of edition galleries
	*/
	public java.util.List<EditionGallery> findAll(int start, int end);

	/**
	* Returns an ordered range of all the edition galleries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of edition galleries
	* @param end the upper bound of the range of edition galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of edition galleries
	*/
	public java.util.List<EditionGallery> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator);

	/**
	* Returns an ordered range of all the edition galleries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of edition galleries
	* @param end the upper bound of the range of edition galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of edition galleries
	*/
	public java.util.List<EditionGallery> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EditionGallery> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the edition galleries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of edition galleries.
	*
	* @return the number of edition galleries
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}