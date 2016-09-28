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

import eu.strasbourg.service.artwork.model.Artwork;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the artwork service. This utility wraps {@link eu.strasbourg.service.artwork.service.persistence.impl.ArtworkPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see ArtworkPersistence
 * @see eu.strasbourg.service.artwork.service.persistence.impl.ArtworkPersistenceImpl
 * @generated
 */
@ProviderType
public class ArtworkUtil {
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
	public static void clearCache(Artwork artwork) {
		getPersistence().clearCache(artwork);
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
	public static List<Artwork> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Artwork> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Artwork> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Artwork> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Artwork update(Artwork artwork) {
		return getPersistence().update(artwork);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Artwork update(Artwork artwork, ServiceContext serviceContext) {
		return getPersistence().update(artwork, serviceContext);
	}

	/**
	* Returns all the artworks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching artworks
	*/
	public static List<Artwork> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the artworks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of artworks
	* @param end the upper bound of the range of artworks (not inclusive)
	* @return the range of matching artworks
	*/
	public static List<Artwork> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the artworks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of artworks
	* @param end the upper bound of the range of artworks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching artworks
	*/
	public static List<Artwork> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Artwork> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the artworks where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of artworks
	* @param end the upper bound of the range of artworks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching artworks
	*/
	public static List<Artwork> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<Artwork> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first artwork in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork
	* @throws NoSuchArtworkException if a matching artwork could not be found
	*/
	public static Artwork findByUuid_First(java.lang.String uuid,
		OrderByComparator<Artwork> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first artwork in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork, or <code>null</code> if a matching artwork could not be found
	*/
	public static Artwork fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<Artwork> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last artwork in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork
	* @throws NoSuchArtworkException if a matching artwork could not be found
	*/
	public static Artwork findByUuid_Last(java.lang.String uuid,
		OrderByComparator<Artwork> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last artwork in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork, or <code>null</code> if a matching artwork could not be found
	*/
	public static Artwork fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<Artwork> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the artworks before and after the current artwork in the ordered set where uuid = &#63;.
	*
	* @param artworkId the primary key of the current artwork
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next artwork
	* @throws NoSuchArtworkException if a artwork with the primary key could not be found
	*/
	public static Artwork[] findByUuid_PrevAndNext(long artworkId,
		java.lang.String uuid, OrderByComparator<Artwork> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkException {
		return getPersistence()
				   .findByUuid_PrevAndNext(artworkId, uuid, orderByComparator);
	}

	/**
	* Removes all the artworks where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of artworks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching artworks
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the artwork where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchArtworkException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching artwork
	* @throws NoSuchArtworkException if a matching artwork could not be found
	*/
	public static Artwork findByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the artwork where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching artwork, or <code>null</code> if a matching artwork could not be found
	*/
	public static Artwork fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the artwork where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching artwork, or <code>null</code> if a matching artwork could not be found
	*/
	public static Artwork fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the artwork where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the artwork that was removed
	*/
	public static Artwork removeByUUID_G(java.lang.String uuid, long groupId)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of artworks where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching artworks
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the artworks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching artworks
	*/
	public static List<Artwork> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the artworks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of artworks
	* @param end the upper bound of the range of artworks (not inclusive)
	* @return the range of matching artworks
	*/
	public static List<Artwork> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the artworks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of artworks
	* @param end the upper bound of the range of artworks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching artworks
	*/
	public static List<Artwork> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Artwork> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the artworks where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of artworks
	* @param end the upper bound of the range of artworks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching artworks
	*/
	public static List<Artwork> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<Artwork> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first artwork in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork
	* @throws NoSuchArtworkException if a matching artwork could not be found
	*/
	public static Artwork findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Artwork> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first artwork in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork, or <code>null</code> if a matching artwork could not be found
	*/
	public static Artwork fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<Artwork> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last artwork in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork
	* @throws NoSuchArtworkException if a matching artwork could not be found
	*/
	public static Artwork findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Artwork> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last artwork in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork, or <code>null</code> if a matching artwork could not be found
	*/
	public static Artwork fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<Artwork> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the artworks before and after the current artwork in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param artworkId the primary key of the current artwork
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next artwork
	* @throws NoSuchArtworkException if a artwork with the primary key could not be found
	*/
	public static Artwork[] findByUuid_C_PrevAndNext(long artworkId,
		java.lang.String uuid, long companyId,
		OrderByComparator<Artwork> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(artworkId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the artworks where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of artworks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching artworks
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the artworks where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching artworks
	*/
	public static List<Artwork> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the artworks where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of artworks
	* @param end the upper bound of the range of artworks (not inclusive)
	* @return the range of matching artworks
	*/
	public static List<Artwork> findByGroupId(long groupId, int start, int end) {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the artworks where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of artworks
	* @param end the upper bound of the range of artworks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching artworks
	*/
	public static List<Artwork> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Artwork> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the artworks where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of artworks
	* @param end the upper bound of the range of artworks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching artworks
	*/
	public static List<Artwork> findByGroupId(long groupId, int start, int end,
		OrderByComparator<Artwork> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first artwork in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork
	* @throws NoSuchArtworkException if a matching artwork could not be found
	*/
	public static Artwork findByGroupId_First(long groupId,
		OrderByComparator<Artwork> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first artwork in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork, or <code>null</code> if a matching artwork could not be found
	*/
	public static Artwork fetchByGroupId_First(long groupId,
		OrderByComparator<Artwork> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last artwork in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork
	* @throws NoSuchArtworkException if a matching artwork could not be found
	*/
	public static Artwork findByGroupId_Last(long groupId,
		OrderByComparator<Artwork> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last artwork in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork, or <code>null</code> if a matching artwork could not be found
	*/
	public static Artwork fetchByGroupId_Last(long groupId,
		OrderByComparator<Artwork> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the artworks before and after the current artwork in the ordered set where groupId = &#63;.
	*
	* @param artworkId the primary key of the current artwork
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next artwork
	* @throws NoSuchArtworkException if a artwork with the primary key could not be found
	*/
	public static Artwork[] findByGroupId_PrevAndNext(long artworkId,
		long groupId, OrderByComparator<Artwork> orderByComparator)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(artworkId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the artworks where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of artworks where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching artworks
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Caches the artwork in the entity cache if it is enabled.
	*
	* @param artwork the artwork
	*/
	public static void cacheResult(Artwork artwork) {
		getPersistence().cacheResult(artwork);
	}

	/**
	* Caches the artworks in the entity cache if it is enabled.
	*
	* @param artworks the artworks
	*/
	public static void cacheResult(List<Artwork> artworks) {
		getPersistence().cacheResult(artworks);
	}

	/**
	* Creates a new artwork with the primary key. Does not add the artwork to the database.
	*
	* @param artworkId the primary key for the new artwork
	* @return the new artwork
	*/
	public static Artwork create(long artworkId) {
		return getPersistence().create(artworkId);
	}

	/**
	* Removes the artwork with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param artworkId the primary key of the artwork
	* @return the artwork that was removed
	* @throws NoSuchArtworkException if a artwork with the primary key could not be found
	*/
	public static Artwork remove(long artworkId)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkException {
		return getPersistence().remove(artworkId);
	}

	public static Artwork updateImpl(Artwork artwork) {
		return getPersistence().updateImpl(artwork);
	}

	/**
	* Returns the artwork with the primary key or throws a {@link NoSuchArtworkException} if it could not be found.
	*
	* @param artworkId the primary key of the artwork
	* @return the artwork
	* @throws NoSuchArtworkException if a artwork with the primary key could not be found
	*/
	public static Artwork findByPrimaryKey(long artworkId)
		throws eu.strasbourg.service.artwork.exception.NoSuchArtworkException {
		return getPersistence().findByPrimaryKey(artworkId);
	}

	/**
	* Returns the artwork with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param artworkId the primary key of the artwork
	* @return the artwork, or <code>null</code> if a artwork with the primary key could not be found
	*/
	public static Artwork fetchByPrimaryKey(long artworkId) {
		return getPersistence().fetchByPrimaryKey(artworkId);
	}

	public static java.util.Map<java.io.Serializable, Artwork> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the artworks.
	*
	* @return the artworks
	*/
	public static List<Artwork> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the artworks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of artworks
	* @param end the upper bound of the range of artworks (not inclusive)
	* @return the range of artworks
	*/
	public static List<Artwork> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the artworks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of artworks
	* @param end the upper bound of the range of artworks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of artworks
	*/
	public static List<Artwork> findAll(int start, int end,
		OrderByComparator<Artwork> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the artworks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of artworks
	* @param end the upper bound of the range of artworks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of artworks
	*/
	public static List<Artwork> findAll(int start, int end,
		OrderByComparator<Artwork> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the artworks from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of artworks.
	*
	* @return the number of artworks
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of artwork collections associated with the artwork.
	*
	* @param pk the primary key of the artwork
	* @return long[] of the primaryKeys of artwork collections associated with the artwork
	*/
	public static long[] getArtworkCollectionPrimaryKeys(long pk) {
		return getPersistence().getArtworkCollectionPrimaryKeys(pk);
	}

	/**
	* Returns all the artwork collections associated with the artwork.
	*
	* @param pk the primary key of the artwork
	* @return the artwork collections associated with the artwork
	*/
	public static List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkCollections(
		long pk) {
		return getPersistence().getArtworkCollections(pk);
	}

	/**
	* Returns a range of all the artwork collections associated with the artwork.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the artwork
	* @param start the lower bound of the range of artworks
	* @param end the upper bound of the range of artworks (not inclusive)
	* @return the range of artwork collections associated with the artwork
	*/
	public static List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkCollections(
		long pk, int start, int end) {
		return getPersistence().getArtworkCollections(pk, start, end);
	}

	/**
	* Returns an ordered range of all the artwork collections associated with the artwork.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArtworkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the artwork
	* @param start the lower bound of the range of artworks
	* @param end the upper bound of the range of artworks (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of artwork collections associated with the artwork
	*/
	public static List<eu.strasbourg.service.artwork.model.ArtworkCollection> getArtworkCollections(
		long pk, int start, int end,
		OrderByComparator<eu.strasbourg.service.artwork.model.ArtworkCollection> orderByComparator) {
		return getPersistence()
				   .getArtworkCollections(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of artwork collections associated with the artwork.
	*
	* @param pk the primary key of the artwork
	* @return the number of artwork collections associated with the artwork
	*/
	public static int getArtworkCollectionsSize(long pk) {
		return getPersistence().getArtworkCollectionsSize(pk);
	}

	/**
	* Returns <code>true</code> if the artwork collection is associated with the artwork.
	*
	* @param pk the primary key of the artwork
	* @param artworkCollectionPK the primary key of the artwork collection
	* @return <code>true</code> if the artwork collection is associated with the artwork; <code>false</code> otherwise
	*/
	public static boolean containsArtworkCollection(long pk,
		long artworkCollectionPK) {
		return getPersistence()
				   .containsArtworkCollection(pk, artworkCollectionPK);
	}

	/**
	* Returns <code>true</code> if the artwork has any artwork collections associated with it.
	*
	* @param pk the primary key of the artwork to check for associations with artwork collections
	* @return <code>true</code> if the artwork has any artwork collections associated with it; <code>false</code> otherwise
	*/
	public static boolean containsArtworkCollections(long pk) {
		return getPersistence().containsArtworkCollections(pk);
	}

	/**
	* Adds an association between the artwork and the artwork collection. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork
	* @param artworkCollectionPK the primary key of the artwork collection
	*/
	public static void addArtworkCollection(long pk, long artworkCollectionPK) {
		getPersistence().addArtworkCollection(pk, artworkCollectionPK);
	}

	/**
	* Adds an association between the artwork and the artwork collection. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork
	* @param artworkCollection the artwork collection
	*/
	public static void addArtworkCollection(long pk,
		eu.strasbourg.service.artwork.model.ArtworkCollection artworkCollection) {
		getPersistence().addArtworkCollection(pk, artworkCollection);
	}

	/**
	* Adds an association between the artwork and the artwork collections. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork
	* @param artworkCollectionPKs the primary keys of the artwork collections
	*/
	public static void addArtworkCollections(long pk,
		long[] artworkCollectionPKs) {
		getPersistence().addArtworkCollections(pk, artworkCollectionPKs);
	}

	/**
	* Adds an association between the artwork and the artwork collections. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork
	* @param artworkCollections the artwork collections
	*/
	public static void addArtworkCollections(long pk,
		List<eu.strasbourg.service.artwork.model.ArtworkCollection> artworkCollections) {
		getPersistence().addArtworkCollections(pk, artworkCollections);
	}

	/**
	* Clears all associations between the artwork and its artwork collections. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork to clear the associated artwork collections from
	*/
	public static void clearArtworkCollections(long pk) {
		getPersistence().clearArtworkCollections(pk);
	}

	/**
	* Removes the association between the artwork and the artwork collection. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork
	* @param artworkCollectionPK the primary key of the artwork collection
	*/
	public static void removeArtworkCollection(long pk, long artworkCollectionPK) {
		getPersistence().removeArtworkCollection(pk, artworkCollectionPK);
	}

	/**
	* Removes the association between the artwork and the artwork collection. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork
	* @param artworkCollection the artwork collection
	*/
	public static void removeArtworkCollection(long pk,
		eu.strasbourg.service.artwork.model.ArtworkCollection artworkCollection) {
		getPersistence().removeArtworkCollection(pk, artworkCollection);
	}

	/**
	* Removes the association between the artwork and the artwork collections. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork
	* @param artworkCollectionPKs the primary keys of the artwork collections
	*/
	public static void removeArtworkCollections(long pk,
		long[] artworkCollectionPKs) {
		getPersistence().removeArtworkCollections(pk, artworkCollectionPKs);
	}

	/**
	* Removes the association between the artwork and the artwork collections. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork
	* @param artworkCollections the artwork collections
	*/
	public static void removeArtworkCollections(long pk,
		List<eu.strasbourg.service.artwork.model.ArtworkCollection> artworkCollections) {
		getPersistence().removeArtworkCollections(pk, artworkCollections);
	}

	/**
	* Sets the artwork collections associated with the artwork, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork
	* @param artworkCollectionPKs the primary keys of the artwork collections to be associated with the artwork
	*/
	public static void setArtworkCollections(long pk,
		long[] artworkCollectionPKs) {
		getPersistence().setArtworkCollections(pk, artworkCollectionPKs);
	}

	/**
	* Sets the artwork collections associated with the artwork, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork
	* @param artworkCollections the artwork collections to be associated with the artwork
	*/
	public static void setArtworkCollections(long pk,
		List<eu.strasbourg.service.artwork.model.ArtworkCollection> artworkCollections) {
		getPersistence().setArtworkCollections(pk, artworkCollections);
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ArtworkPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ArtworkPersistence, ArtworkPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ArtworkPersistence.class);
}