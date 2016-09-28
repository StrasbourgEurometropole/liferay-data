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

package eu.strasbourg.service.artwork.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.artwork.model.ArtworkCollection;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the artwork collection service. This utility wraps {@link eu.strasbourg.service.artwork.service.persistence.impl.ArtworkCollectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see ArtworkCollectionPersistence
 * @see eu.strasbourg.service.artwork.service.persistence.impl.ArtworkCollectionPersistenceImpl
 * @generated
 */
@ProviderType
public class ArtworkCollectionUtil {
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
	public static void clearCache(ArtworkCollection artworkCollection) {
		getPersistence().clearCache(artworkCollection);
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
	public static List<ArtworkCollection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ArtworkCollection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ArtworkCollection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ArtworkCollection update(ArtworkCollection artworkCollection) {
		return getPersistence().update(artworkCollection);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ArtworkCollection update(
		ArtworkCollection artworkCollection, ServiceContext serviceContext) {
		return getPersistence().update(artworkCollection, serviceContext);
	}

	/**
	* Returns all the artwork collections where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching artwork collections
	*/
	public static List<ArtworkCollection> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the artwork collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of artwork collections
	* @param end the upper bound of the range of artwork collections (not inclusive)
	* @return the range of matching artwork collections
	*/
	public static List<ArtworkCollection> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the artwork collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of artwork collections
	* @param end the upper bound of the range of artwork collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching artwork collections
	*/
	public static List<ArtworkCollection> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the artwork collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of artwork collections
	* @param end the upper bound of the range of artwork collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching artwork collections
	*/
	public static List<ArtworkCollection> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first artwork collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork collection
	* @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	*/
	public static ArtworkCollection findByUuid_First(java.lang.String uuid,
		OrderByComparator<ArtworkCollection> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkCollectionException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first artwork collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	public static ArtworkCollection fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<ArtworkCollection> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last artwork collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork collection
	* @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	*/
	public static ArtworkCollection findByUuid_Last(java.lang.String uuid,
		OrderByComparator<ArtworkCollection> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkCollectionException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last artwork collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	public static ArtworkCollection fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<ArtworkCollection> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the artwork collections before and after the current artwork collection in the ordered set where uuid = &#63;.
	*
	* @param collectionId the primary key of the current artwork collection
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next artwork collection
	* @throws NoSuchArtworkCollectionException if a artwork collection with the primary key could not be found
	*/
	public static ArtworkCollection[] findByUuid_PrevAndNext(
		long collectionId, java.lang.String uuid,
		OrderByComparator<ArtworkCollection> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkCollectionException {
		return getPersistence()
				   .findByUuid_PrevAndNext(collectionId, uuid, orderByComparator);
	}

	/**
	* Removes all the artwork collections where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of artwork collections where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching artwork collections
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the artwork collection where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchArtworkCollectionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching artwork collection
	* @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	*/
	public static ArtworkCollection findByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkCollectionException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the artwork collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	public static ArtworkCollection fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the artwork collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	public static ArtworkCollection fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the artwork collection where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the artwork collection that was removed
	*/
	public static ArtworkCollection removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkCollectionException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of artwork collections where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching artwork collections
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the artwork collections where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching artwork collections
	*/
	public static List<ArtworkCollection> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the artwork collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of artwork collections
	* @param end the upper bound of the range of artwork collections (not inclusive)
	* @return the range of matching artwork collections
	*/
	public static List<ArtworkCollection> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the artwork collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of artwork collections
	* @param end the upper bound of the range of artwork collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching artwork collections
	*/
	public static List<ArtworkCollection> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the artwork collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of artwork collections
	* @param end the upper bound of the range of artwork collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching artwork collections
	*/
	public static List<ArtworkCollection> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first artwork collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork collection
	* @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	*/
	public static ArtworkCollection findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<ArtworkCollection> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkCollectionException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first artwork collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	public static ArtworkCollection fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<ArtworkCollection> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last artwork collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork collection
	* @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	*/
	public static ArtworkCollection findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<ArtworkCollection> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkCollectionException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last artwork collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	public static ArtworkCollection fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<ArtworkCollection> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the artwork collections before and after the current artwork collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param collectionId the primary key of the current artwork collection
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next artwork collection
	* @throws NoSuchArtworkCollectionException if a artwork collection with the primary key could not be found
	*/
	public static ArtworkCollection[] findByUuid_C_PrevAndNext(
		long collectionId, java.lang.String uuid, long companyId,
		OrderByComparator<ArtworkCollection> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkCollectionException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(collectionId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the artwork collections where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of artwork collections where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching artwork collections
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the artwork collections where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching artwork collections
	*/
	public static List<ArtworkCollection> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the artwork collections where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of artwork collections
	* @param end the upper bound of the range of artwork collections (not inclusive)
	* @return the range of matching artwork collections
	*/
	public static List<ArtworkCollection> findByGroupId(long groupId,
		int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the artwork collections where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of artwork collections
	* @param end the upper bound of the range of artwork collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching artwork collections
	*/
	public static List<ArtworkCollection> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the artwork collections where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of artwork collections
	* @param end the upper bound of the range of artwork collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching artwork collections
	*/
	public static List<ArtworkCollection> findByGroupId(long groupId,
		int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first artwork collection in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork collection
	* @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	*/
	public static ArtworkCollection findByGroupId_First(long groupId,
		OrderByComparator<ArtworkCollection> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkCollectionException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first artwork collection in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	public static ArtworkCollection fetchByGroupId_First(long groupId,
		OrderByComparator<ArtworkCollection> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last artwork collection in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork collection
	* @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	*/
	public static ArtworkCollection findByGroupId_Last(long groupId,
		OrderByComparator<ArtworkCollection> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkCollectionException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last artwork collection in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	public static ArtworkCollection fetchByGroupId_Last(long groupId,
		OrderByComparator<ArtworkCollection> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the artwork collections before and after the current artwork collection in the ordered set where groupId = &#63;.
	*
	* @param collectionId the primary key of the current artwork collection
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next artwork collection
	* @throws NoSuchArtworkCollectionException if a artwork collection with the primary key could not be found
	*/
	public static ArtworkCollection[] findByGroupId_PrevAndNext(
		long collectionId, long groupId,
		OrderByComparator<ArtworkCollection> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkCollectionException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(collectionId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the artwork collections where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of artwork collections where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching artwork collections
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Caches the artwork collection in the entity cache if it is enabled.
	*
	* @param artworkCollection the artwork collection
	*/
	public static void cacheResult(ArtworkCollection artworkCollection) {
		getPersistence().cacheResult(artworkCollection);
	}

	/**
	* Caches the artwork collections in the entity cache if it is enabled.
	*
	* @param artworkCollections the artwork collections
	*/
	public static void cacheResult(List<ArtworkCollection> artworkCollections) {
		getPersistence().cacheResult(artworkCollections);
	}

	/**
	* Creates a new artwork collection with the primary key. Does not add the artwork collection to the database.
	*
	* @param collectionId the primary key for the new artwork collection
	* @return the new artwork collection
	*/
	public static ArtworkCollection create(long collectionId) {
		return getPersistence().create(collectionId);
	}

	/**
	* Removes the artwork collection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param collectionId the primary key of the artwork collection
	* @return the artwork collection that was removed
	* @throws NoSuchArtworkCollectionException if a artwork collection with the primary key could not be found
	*/
	public static ArtworkCollection remove(long collectionId)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkCollectionException {
		return getPersistence().remove(collectionId);
	}

	public static ArtworkCollection updateImpl(
		ArtworkCollection artworkCollection) {
		return getPersistence().updateImpl(artworkCollection);
	}

	/**
	* Returns the artwork collection with the primary key or throws a {@link NoSuchArtworkCollectionException} if it could not be found.
	*
	* @param collectionId the primary key of the artwork collection
	* @return the artwork collection
	* @throws NoSuchArtworkCollectionException if a artwork collection with the primary key could not be found
	*/
	public static ArtworkCollection findByPrimaryKey(long collectionId)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkCollectionException {
		return getPersistence().findByPrimaryKey(collectionId);
	}

	/**
	* Returns the artwork collection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param collectionId the primary key of the artwork collection
	* @return the artwork collection, or <code>null</code> if a artwork collection with the primary key could not be found
	*/
	public static ArtworkCollection fetchByPrimaryKey(long collectionId) {
		return getPersistence().fetchByPrimaryKey(collectionId);
	}

	public static java.util.Map<java.io.Serializable, ArtworkCollection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the artwork collections.
	*
	* @return the artwork collections
	*/
	public static List<ArtworkCollection> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the artwork collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of artwork collections
	* @param end the upper bound of the range of artwork collections (not inclusive)
	* @return the range of artwork collections
	*/
	public static List<ArtworkCollection> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the artwork collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of artwork collections
	* @param end the upper bound of the range of artwork collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of artwork collections
	*/
	public static List<ArtworkCollection> findAll(int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the artwork collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of artwork collections
	* @param end the upper bound of the range of artwork collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of artwork collections
	*/
	public static List<ArtworkCollection> findAll(int start, int end,
		OrderByComparator<ArtworkCollection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the artwork collections from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of artwork collections.
	*
	* @return the number of artwork collections
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of artworks associated with the artwork collection.
	*
	* @param pk the primary key of the artwork collection
	* @return long[] of the primaryKeys of artworks associated with the artwork collection
	*/
	public static long[] getArtworkPrimaryKeys(long pk) {
		return getPersistence().getArtworkPrimaryKeys(pk);
	}

	/**
	* Returns all the artworks associated with the artwork collection.
	*
	* @param pk the primary key of the artwork collection
	* @return the artworks associated with the artwork collection
	*/
	public static List<eu.strasbourg.service.artwork.model.Artwork> getArtworks(
		long pk) {
		return getPersistence().getArtworks(pk);
	}

	/**
	* Returns a range of all the artworks associated with the artwork collection.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the artwork collection
	* @param start the lower bound of the range of artwork collections
	* @param end the upper bound of the range of artwork collections (not inclusive)
	* @return the range of artworks associated with the artwork collection
	*/
	public static List<eu.strasbourg.service.artwork.model.Artwork> getArtworks(
		long pk, int start, int end) {
		return getPersistence().getArtworks(pk, start, end);
	}

	/**
	* Returns an ordered range of all the artworks associated with the artwork collection.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the artwork collection
	* @param start the lower bound of the range of artwork collections
	* @param end the upper bound of the range of artwork collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of artworks associated with the artwork collection
	*/
	public static List<eu.strasbourg.service.artwork.model.Artwork> getArtworks(
		long pk, int start, int end,
		OrderByComparator<eu.strasbourg.service.artwork.model.Artwork> orderByComparator) {
		return getPersistence().getArtworks(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of artworks associated with the artwork collection.
	*
	* @param pk the primary key of the artwork collection
	* @return the number of artworks associated with the artwork collection
	*/
	public static int getArtworksSize(long pk) {
		return getPersistence().getArtworksSize(pk);
	}

	/**
	* Returns <code>true</code> if the artwork is associated with the artwork collection.
	*
	* @param pk the primary key of the artwork collection
	* @param artworkPK the primary key of the artwork
	* @return <code>true</code> if the artwork is associated with the artwork collection; <code>false</code> otherwise
	*/
	public static boolean containsArtwork(long pk, long artworkPK) {
		return getPersistence().containsArtwork(pk, artworkPK);
	}

	/**
	* Returns <code>true</code> if the artwork collection has any artworks associated with it.
	*
	* @param pk the primary key of the artwork collection to check for associations with artworks
	* @return <code>true</code> if the artwork collection has any artworks associated with it; <code>false</code> otherwise
	*/
	public static boolean containsArtworks(long pk) {
		return getPersistence().containsArtworks(pk);
	}

	/**
	* Adds an association between the artwork collection and the artwork. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artworkPK the primary key of the artwork
	*/
	public static void addArtwork(long pk, long artworkPK) {
		getPersistence().addArtwork(pk, artworkPK);
	}

	/**
	* Adds an association between the artwork collection and the artwork. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artwork the artwork
	*/
	public static void addArtwork(long pk,
		eu.strasbourg.service.artwork.model.Artwork artwork) {
		getPersistence().addArtwork(pk, artwork);
	}

	/**
	* Adds an association between the artwork collection and the artworks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artworkPKs the primary keys of the artworks
	*/
	public static void addArtworks(long pk, long[] artworkPKs) {
		getPersistence().addArtworks(pk, artworkPKs);
	}

	/**
	* Adds an association between the artwork collection and the artworks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artworks the artworks
	*/
	public static void addArtworks(long pk,
		List<eu.strasbourg.service.artwork.model.Artwork> artworks) {
		getPersistence().addArtworks(pk, artworks);
	}

	/**
	* Clears all associations between the artwork collection and its artworks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection to clear the associated artworks from
	*/
	public static void clearArtworks(long pk) {
		getPersistence().clearArtworks(pk);
	}

	/**
	* Removes the association between the artwork collection and the artwork. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artworkPK the primary key of the artwork
	*/
	public static void removeArtwork(long pk, long artworkPK) {
		getPersistence().removeArtwork(pk, artworkPK);
	}

	/**
	* Removes the association between the artwork collection and the artwork. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artwork the artwork
	*/
	public static void removeArtwork(long pk,
		eu.strasbourg.service.artwork.model.Artwork artwork) {
		getPersistence().removeArtwork(pk, artwork);
	}

	/**
	* Removes the association between the artwork collection and the artworks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artworkPKs the primary keys of the artworks
	*/
	public static void removeArtworks(long pk, long[] artworkPKs) {
		getPersistence().removeArtworks(pk, artworkPKs);
	}

	/**
	* Removes the association between the artwork collection and the artworks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artworks the artworks
	*/
	public static void removeArtworks(long pk,
		List<eu.strasbourg.service.artwork.model.Artwork> artworks) {
		getPersistence().removeArtworks(pk, artworks);
	}

	/**
	* Sets the artworks associated with the artwork collection, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artworkPKs the primary keys of the artworks to be associated with the artwork collection
	*/
	public static void setArtworks(long pk, long[] artworkPKs) {
		getPersistence().setArtworks(pk, artworkPKs);
	}

	/**
	* Sets the artworks associated with the artwork collection, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artworks the artworks to be associated with the artwork collection
	*/
	public static void setArtworks(long pk,
		List<eu.strasbourg.service.artwork.model.Artwork> artworks) {
		getPersistence().setArtworks(pk, artworks);
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ArtworkCollectionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ArtworkCollectionPersistence, ArtworkCollectionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ArtworkCollectionPersistence.class);
}