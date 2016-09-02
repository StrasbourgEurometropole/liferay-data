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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.edition.model.EditionGallery;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the edition gallery service. This utility wraps {@link eu.strasbourg.service.edition.service.persistence.impl.EditionGalleryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see EditionGalleryPersistence
 * @see eu.strasbourg.service.edition.service.persistence.impl.EditionGalleryPersistenceImpl
 * @generated
 */
@ProviderType
public class EditionGalleryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(EditionGallery editionGallery) {
		getPersistence().clearCache(editionGallery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<EditionGallery> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EditionGallery> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EditionGallery> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EditionGallery> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EditionGallery update(EditionGallery editionGallery) {
		return getPersistence().update(editionGallery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EditionGallery update(EditionGallery editionGallery,
		ServiceContext serviceContext) {
		return getPersistence().update(editionGallery, serviceContext);
	}

	/**
	* Returns all the edition galleries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching edition galleries
	*/
	public static List<EditionGallery> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<EditionGallery> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<EditionGallery> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<EditionGallery> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<EditionGallery> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<EditionGallery> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first edition gallery in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition gallery
	* @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	*/
	public static EditionGallery findByUuid_First(java.lang.String uuid,
		OrderByComparator<EditionGallery> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionGalleryException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first edition gallery in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public static EditionGallery fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<EditionGallery> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last edition gallery in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition gallery
	* @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	*/
	public static EditionGallery findByUuid_Last(java.lang.String uuid,
		OrderByComparator<EditionGallery> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionGalleryException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last edition gallery in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public static EditionGallery fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<EditionGallery> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the edition galleries before and after the current edition gallery in the ordered set where uuid = &#63;.
	*
	* @param galleryId the primary key of the current edition gallery
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next edition gallery
	* @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	*/
	public static EditionGallery[] findByUuid_PrevAndNext(long galleryId,
		java.lang.String uuid,
		OrderByComparator<EditionGallery> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionGalleryException {
		return getPersistence()
				   .findByUuid_PrevAndNext(galleryId, uuid, orderByComparator);
	}

	/**
	* Removes all the edition galleries where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of edition galleries where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching edition galleries
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the edition gallery where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchEditionGalleryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching edition gallery
	* @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	*/
	public static EditionGallery findByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionGalleryException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the edition gallery where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public static EditionGallery fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the edition gallery where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public static EditionGallery fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the edition gallery where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the edition gallery that was removed
	*/
	public static EditionGallery removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionGalleryException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of edition galleries where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching edition galleries
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the edition galleries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching edition galleries
	*/
	public static List<EditionGallery> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

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
	public static List<EditionGallery> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

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
	public static List<EditionGallery> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<EditionGallery> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

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
	public static List<EditionGallery> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<EditionGallery> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first edition gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition gallery
	* @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	*/
	public static EditionGallery findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<EditionGallery> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionGalleryException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first edition gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public static EditionGallery fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<EditionGallery> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last edition gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition gallery
	* @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	*/
	public static EditionGallery findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<EditionGallery> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionGalleryException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last edition gallery in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public static EditionGallery fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<EditionGallery> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

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
	public static EditionGallery[] findByUuid_C_PrevAndNext(long galleryId,
		java.lang.String uuid, long companyId,
		OrderByComparator<EditionGallery> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionGalleryException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(galleryId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the edition galleries where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of edition galleries where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching edition galleries
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the edition galleries where title = &#63;.
	*
	* @param title the title
	* @return the matching edition galleries
	*/
	public static List<EditionGallery> findBytitle(java.lang.String title) {
		return getPersistence().findBytitle(title);
	}

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
	public static List<EditionGallery> findBytitle(java.lang.String title,
		int start, int end) {
		return getPersistence().findBytitle(title, start, end);
	}

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
	public static List<EditionGallery> findBytitle(java.lang.String title,
		int start, int end, OrderByComparator<EditionGallery> orderByComparator) {
		return getPersistence().findBytitle(title, start, end, orderByComparator);
	}

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
	public static List<EditionGallery> findBytitle(java.lang.String title,
		int start, int end,
		OrderByComparator<EditionGallery> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBytitle(title, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first edition gallery in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition gallery
	* @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	*/
	public static EditionGallery findBytitle_First(java.lang.String title,
		OrderByComparator<EditionGallery> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionGalleryException {
		return getPersistence().findBytitle_First(title, orderByComparator);
	}

	/**
	* Returns the first edition gallery in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public static EditionGallery fetchBytitle_First(java.lang.String title,
		OrderByComparator<EditionGallery> orderByComparator) {
		return getPersistence().fetchBytitle_First(title, orderByComparator);
	}

	/**
	* Returns the last edition gallery in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition gallery
	* @throws NoSuchEditionGalleryException if a matching edition gallery could not be found
	*/
	public static EditionGallery findBytitle_Last(java.lang.String title,
		OrderByComparator<EditionGallery> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionGalleryException {
		return getPersistence().findBytitle_Last(title, orderByComparator);
	}

	/**
	* Returns the last edition gallery in the ordered set where title = &#63;.
	*
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching edition gallery, or <code>null</code> if a matching edition gallery could not be found
	*/
	public static EditionGallery fetchBytitle_Last(java.lang.String title,
		OrderByComparator<EditionGallery> orderByComparator) {
		return getPersistence().fetchBytitle_Last(title, orderByComparator);
	}

	/**
	* Returns the edition galleries before and after the current edition gallery in the ordered set where title = &#63;.
	*
	* @param galleryId the primary key of the current edition gallery
	* @param title the title
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next edition gallery
	* @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	*/
	public static EditionGallery[] findBytitle_PrevAndNext(long galleryId,
		java.lang.String title,
		OrderByComparator<EditionGallery> orderByComparator)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionGalleryException {
		return getPersistence()
				   .findBytitle_PrevAndNext(galleryId, title, orderByComparator);
	}

	/**
	* Removes all the edition galleries where title = &#63; from the database.
	*
	* @param title the title
	*/
	public static void removeBytitle(java.lang.String title) {
		getPersistence().removeBytitle(title);
	}

	/**
	* Returns the number of edition galleries where title = &#63;.
	*
	* @param title the title
	* @return the number of matching edition galleries
	*/
	public static int countBytitle(java.lang.String title) {
		return getPersistence().countBytitle(title);
	}

	/**
	* Caches the edition gallery in the entity cache if it is enabled.
	*
	* @param editionGallery the edition gallery
	*/
	public static void cacheResult(EditionGallery editionGallery) {
		getPersistence().cacheResult(editionGallery);
	}

	/**
	* Caches the edition galleries in the entity cache if it is enabled.
	*
	* @param editionGalleries the edition galleries
	*/
	public static void cacheResult(List<EditionGallery> editionGalleries) {
		getPersistence().cacheResult(editionGalleries);
	}

	/**
	* Creates a new edition gallery with the primary key. Does not add the edition gallery to the database.
	*
	* @param galleryId the primary key for the new edition gallery
	* @return the new edition gallery
	*/
	public static EditionGallery create(long galleryId) {
		return getPersistence().create(galleryId);
	}

	/**
	* Removes the edition gallery with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param galleryId the primary key of the edition gallery
	* @return the edition gallery that was removed
	* @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	*/
	public static EditionGallery remove(long galleryId)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionGalleryException {
		return getPersistence().remove(galleryId);
	}

	public static EditionGallery updateImpl(EditionGallery editionGallery) {
		return getPersistence().updateImpl(editionGallery);
	}

	/**
	* Returns the edition gallery with the primary key or throws a {@link NoSuchEditionGalleryException} if it could not be found.
	*
	* @param galleryId the primary key of the edition gallery
	* @return the edition gallery
	* @throws NoSuchEditionGalleryException if a edition gallery with the primary key could not be found
	*/
	public static EditionGallery findByPrimaryKey(long galleryId)
		throws eu.strasbourg.service.edition.exception.NoSuchEditionGalleryException {
		return getPersistence().findByPrimaryKey(galleryId);
	}

	/**
	* Returns the edition gallery with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param galleryId the primary key of the edition gallery
	* @return the edition gallery, or <code>null</code> if a edition gallery with the primary key could not be found
	*/
	public static EditionGallery fetchByPrimaryKey(long galleryId) {
		return getPersistence().fetchByPrimaryKey(galleryId);
	}

	public static java.util.Map<java.io.Serializable, EditionGallery> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the edition galleries.
	*
	* @return the edition galleries
	*/
	public static List<EditionGallery> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<EditionGallery> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<EditionGallery> findAll(int start, int end,
		OrderByComparator<EditionGallery> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<EditionGallery> findAll(int start, int end,
		OrderByComparator<EditionGallery> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the edition galleries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of edition galleries.
	*
	* @return the number of edition galleries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of editions associated with the edition gallery.
	*
	* @param pk the primary key of the edition gallery
	* @return long[] of the primaryKeys of editions associated with the edition gallery
	*/
	public static long[] getEditionPrimaryKeys(long pk) {
		return getPersistence().getEditionPrimaryKeys(pk);
	}

	/**
	* Returns all the editions associated with the edition gallery.
	*
	* @param pk the primary key of the edition gallery
	* @return the editions associated with the edition gallery
	*/
	public static List<eu.strasbourg.service.edition.model.Edition> getEditions(
		long pk) {
		return getPersistence().getEditions(pk);
	}

	/**
	* Returns a range of all the editions associated with the edition gallery.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the edition gallery
	* @param start the lower bound of the range of edition galleries
	* @param end the upper bound of the range of edition galleries (not inclusive)
	* @return the range of editions associated with the edition gallery
	*/
	public static List<eu.strasbourg.service.edition.model.Edition> getEditions(
		long pk, int start, int end) {
		return getPersistence().getEditions(pk, start, end);
	}

	/**
	* Returns an ordered range of all the editions associated with the edition gallery.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EditionGalleryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the edition gallery
	* @param start the lower bound of the range of edition galleries
	* @param end the upper bound of the range of edition galleries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of editions associated with the edition gallery
	*/
	public static List<eu.strasbourg.service.edition.model.Edition> getEditions(
		long pk, int start, int end,
		OrderByComparator<eu.strasbourg.service.edition.model.Edition> orderByComparator) {
		return getPersistence().getEditions(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of editions associated with the edition gallery.
	*
	* @param pk the primary key of the edition gallery
	* @return the number of editions associated with the edition gallery
	*/
	public static int getEditionsSize(long pk) {
		return getPersistence().getEditionsSize(pk);
	}

	/**
	* Returns <code>true</code> if the edition is associated with the edition gallery.
	*
	* @param pk the primary key of the edition gallery
	* @param editionPK the primary key of the edition
	* @return <code>true</code> if the edition is associated with the edition gallery; <code>false</code> otherwise
	*/
	public static boolean containsEdition(long pk, long editionPK) {
		return getPersistence().containsEdition(pk, editionPK);
	}

	/**
	* Returns <code>true</code> if the edition gallery has any editions associated with it.
	*
	* @param pk the primary key of the edition gallery to check for associations with editions
	* @return <code>true</code> if the edition gallery has any editions associated with it; <code>false</code> otherwise
	*/
	public static boolean containsEditions(long pk) {
		return getPersistence().containsEditions(pk);
	}

	/**
	* Adds an association between the edition gallery and the edition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition gallery
	* @param editionPK the primary key of the edition
	*/
	public static void addEdition(long pk, long editionPK) {
		getPersistence().addEdition(pk, editionPK);
	}

	/**
	* Adds an association between the edition gallery and the edition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition gallery
	* @param edition the edition
	*/
	public static void addEdition(long pk,
		eu.strasbourg.service.edition.model.Edition edition) {
		getPersistence().addEdition(pk, edition);
	}

	/**
	* Adds an association between the edition gallery and the editions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition gallery
	* @param editionPKs the primary keys of the editions
	*/
	public static void addEditions(long pk, long[] editionPKs) {
		getPersistence().addEditions(pk, editionPKs);
	}

	/**
	* Adds an association between the edition gallery and the editions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition gallery
	* @param editions the editions
	*/
	public static void addEditions(long pk,
		List<eu.strasbourg.service.edition.model.Edition> editions) {
		getPersistence().addEditions(pk, editions);
	}

	/**
	* Clears all associations between the edition gallery and its editions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition gallery to clear the associated editions from
	*/
	public static void clearEditions(long pk) {
		getPersistence().clearEditions(pk);
	}

	/**
	* Removes the association between the edition gallery and the edition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition gallery
	* @param editionPK the primary key of the edition
	*/
	public static void removeEdition(long pk, long editionPK) {
		getPersistence().removeEdition(pk, editionPK);
	}

	/**
	* Removes the association between the edition gallery and the edition. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition gallery
	* @param edition the edition
	*/
	public static void removeEdition(long pk,
		eu.strasbourg.service.edition.model.Edition edition) {
		getPersistence().removeEdition(pk, edition);
	}

	/**
	* Removes the association between the edition gallery and the editions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition gallery
	* @param editionPKs the primary keys of the editions
	*/
	public static void removeEditions(long pk, long[] editionPKs) {
		getPersistence().removeEditions(pk, editionPKs);
	}

	/**
	* Removes the association between the edition gallery and the editions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition gallery
	* @param editions the editions
	*/
	public static void removeEditions(long pk,
		List<eu.strasbourg.service.edition.model.Edition> editions) {
		getPersistence().removeEditions(pk, editions);
	}

	/**
	* Sets the editions associated with the edition gallery, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition gallery
	* @param editionPKs the primary keys of the editions to be associated with the edition gallery
	*/
	public static void setEditions(long pk, long[] editionPKs) {
		getPersistence().setEditions(pk, editionPKs);
	}

	/**
	* Sets the editions associated with the edition gallery, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the edition gallery
	* @param editions the editions to be associated with the edition gallery
	*/
	public static void setEditions(long pk,
		List<eu.strasbourg.service.edition.model.Edition> editions) {
		getPersistence().setEditions(pk, editions);
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static EditionGalleryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EditionGalleryPersistence, EditionGalleryPersistence> _serviceTracker =
		ServiceTrackerFactory.open(EditionGalleryPersistence.class);
}