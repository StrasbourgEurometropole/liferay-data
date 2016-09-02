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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.artwork.exception.NoSuchArtworkCollectionException;
import eu.strasbourg.service.artwork.model.ArtworkCollection;

/**
 * The persistence interface for the artwork collection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.artwork.service.persistence.impl.ArtworkCollectionPersistenceImpl
 * @see ArtworkCollectionUtil
 * @generated
 */
@ProviderType
public interface ArtworkCollectionPersistence extends BasePersistence<ArtworkCollection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ArtworkCollectionUtil} to access the artwork collection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the artwork collections where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching artwork collections
	*/
	public java.util.List<ArtworkCollection> findByUuid(java.lang.String uuid);

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
	public java.util.List<ArtworkCollection> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<ArtworkCollection> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ArtworkCollection> orderByComparator);

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
	public java.util.List<ArtworkCollection> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ArtworkCollection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first artwork collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork collection
	* @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	*/
	public ArtworkCollection findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ArtworkCollection> orderByComparator)
		throws NoSuchArtworkCollectionException;

	/**
	* Returns the first artwork collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	public ArtworkCollection fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ArtworkCollection> orderByComparator);

	/**
	* Returns the last artwork collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork collection
	* @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	*/
	public ArtworkCollection findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ArtworkCollection> orderByComparator)
		throws NoSuchArtworkCollectionException;

	/**
	* Returns the last artwork collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	public ArtworkCollection fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ArtworkCollection> orderByComparator);

	/**
	* Returns the artwork collections before and after the current artwork collection in the ordered set where uuid = &#63;.
	*
	* @param collectionId the primary key of the current artwork collection
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next artwork collection
	* @throws NoSuchArtworkCollectionException if a artwork collection with the primary key could not be found
	*/
	public ArtworkCollection[] findByUuid_PrevAndNext(long collectionId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ArtworkCollection> orderByComparator)
		throws NoSuchArtworkCollectionException;

	/**
	* Removes all the artwork collections where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of artwork collections where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching artwork collections
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the artwork collection where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchArtworkCollectionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching artwork collection
	* @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	*/
	public ArtworkCollection findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchArtworkCollectionException;

	/**
	* Returns the artwork collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	public ArtworkCollection fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the artwork collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	public ArtworkCollection fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the artwork collection where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the artwork collection that was removed
	*/
	public ArtworkCollection removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchArtworkCollectionException;

	/**
	* Returns the number of artwork collections where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching artwork collections
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the artwork collections where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching artwork collections
	*/
	public java.util.List<ArtworkCollection> findByUuid_C(
		java.lang.String uuid, long companyId);

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
	public java.util.List<ArtworkCollection> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end);

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
	public java.util.List<ArtworkCollection> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ArtworkCollection> orderByComparator);

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
	public java.util.List<ArtworkCollection> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ArtworkCollection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first artwork collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork collection
	* @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	*/
	public ArtworkCollection findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ArtworkCollection> orderByComparator)
		throws NoSuchArtworkCollectionException;

	/**
	* Returns the first artwork collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	public ArtworkCollection fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ArtworkCollection> orderByComparator);

	/**
	* Returns the last artwork collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork collection
	* @throws NoSuchArtworkCollectionException if a matching artwork collection could not be found
	*/
	public ArtworkCollection findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ArtworkCollection> orderByComparator)
		throws NoSuchArtworkCollectionException;

	/**
	* Returns the last artwork collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork collection, or <code>null</code> if a matching artwork collection could not be found
	*/
	public ArtworkCollection fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ArtworkCollection> orderByComparator);

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
	public ArtworkCollection[] findByUuid_C_PrevAndNext(long collectionId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ArtworkCollection> orderByComparator)
		throws NoSuchArtworkCollectionException;

	/**
	* Removes all the artwork collections where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of artwork collections where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching artwork collections
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the artwork collection in the entity cache if it is enabled.
	*
	* @param artworkCollection the artwork collection
	*/
	public void cacheResult(ArtworkCollection artworkCollection);

	/**
	* Caches the artwork collections in the entity cache if it is enabled.
	*
	* @param artworkCollections the artwork collections
	*/
	public void cacheResult(
		java.util.List<ArtworkCollection> artworkCollections);

	/**
	* Creates a new artwork collection with the primary key. Does not add the artwork collection to the database.
	*
	* @param collectionId the primary key for the new artwork collection
	* @return the new artwork collection
	*/
	public ArtworkCollection create(long collectionId);

	/**
	* Removes the artwork collection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param collectionId the primary key of the artwork collection
	* @return the artwork collection that was removed
	* @throws NoSuchArtworkCollectionException if a artwork collection with the primary key could not be found
	*/
	public ArtworkCollection remove(long collectionId)
		throws NoSuchArtworkCollectionException;

	public ArtworkCollection updateImpl(ArtworkCollection artworkCollection);

	/**
	* Returns the artwork collection with the primary key or throws a {@link NoSuchArtworkCollectionException} if it could not be found.
	*
	* @param collectionId the primary key of the artwork collection
	* @return the artwork collection
	* @throws NoSuchArtworkCollectionException if a artwork collection with the primary key could not be found
	*/
	public ArtworkCollection findByPrimaryKey(long collectionId)
		throws NoSuchArtworkCollectionException;

	/**
	* Returns the artwork collection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param collectionId the primary key of the artwork collection
	* @return the artwork collection, or <code>null</code> if a artwork collection with the primary key could not be found
	*/
	public ArtworkCollection fetchByPrimaryKey(long collectionId);

	@Override
	public java.util.Map<java.io.Serializable, ArtworkCollection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the artwork collections.
	*
	* @return the artwork collections
	*/
	public java.util.List<ArtworkCollection> findAll();

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
	public java.util.List<ArtworkCollection> findAll(int start, int end);

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
	public java.util.List<ArtworkCollection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ArtworkCollection> orderByComparator);

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
	public java.util.List<ArtworkCollection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ArtworkCollection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the artwork collections from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of artwork collections.
	*
	* @return the number of artwork collections
	*/
	public int countAll();

	/**
	* Returns the primaryKeys of artworks associated with the artwork collection.
	*
	* @param pk the primary key of the artwork collection
	* @return long[] of the primaryKeys of artworks associated with the artwork collection
	*/
	public long[] getArtworkPrimaryKeys(long pk);

	/**
	* Returns all the artworks associated with the artwork collection.
	*
	* @param pk the primary key of the artwork collection
	* @return the artworks associated with the artwork collection
	*/
	public java.util.List<eu.strasbourg.service.artwork.model.Artwork> getArtworks(
		long pk);

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
	public java.util.List<eu.strasbourg.service.artwork.model.Artwork> getArtworks(
		long pk, int start, int end);

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
	public java.util.List<eu.strasbourg.service.artwork.model.Artwork> getArtworks(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.artwork.model.Artwork> orderByComparator);

	/**
	* Returns the number of artworks associated with the artwork collection.
	*
	* @param pk the primary key of the artwork collection
	* @return the number of artworks associated with the artwork collection
	*/
	public int getArtworksSize(long pk);

	/**
	* Returns <code>true</code> if the artwork is associated with the artwork collection.
	*
	* @param pk the primary key of the artwork collection
	* @param artworkPK the primary key of the artwork
	* @return <code>true</code> if the artwork is associated with the artwork collection; <code>false</code> otherwise
	*/
	public boolean containsArtwork(long pk, long artworkPK);

	/**
	* Returns <code>true</code> if the artwork collection has any artworks associated with it.
	*
	* @param pk the primary key of the artwork collection to check for associations with artworks
	* @return <code>true</code> if the artwork collection has any artworks associated with it; <code>false</code> otherwise
	*/
	public boolean containsArtworks(long pk);

	/**
	* Adds an association between the artwork collection and the artwork. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artworkPK the primary key of the artwork
	*/
	public void addArtwork(long pk, long artworkPK);

	/**
	* Adds an association between the artwork collection and the artwork. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artwork the artwork
	*/
	public void addArtwork(long pk,
		eu.strasbourg.service.artwork.model.Artwork artwork);

	/**
	* Adds an association between the artwork collection and the artworks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artworkPKs the primary keys of the artworks
	*/
	public void addArtworks(long pk, long[] artworkPKs);

	/**
	* Adds an association between the artwork collection and the artworks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artworks the artworks
	*/
	public void addArtworks(long pk,
		java.util.List<eu.strasbourg.service.artwork.model.Artwork> artworks);

	/**
	* Clears all associations between the artwork collection and its artworks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection to clear the associated artworks from
	*/
	public void clearArtworks(long pk);

	/**
	* Removes the association between the artwork collection and the artwork. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artworkPK the primary key of the artwork
	*/
	public void removeArtwork(long pk, long artworkPK);

	/**
	* Removes the association between the artwork collection and the artwork. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artwork the artwork
	*/
	public void removeArtwork(long pk,
		eu.strasbourg.service.artwork.model.Artwork artwork);

	/**
	* Removes the association between the artwork collection and the artworks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artworkPKs the primary keys of the artworks
	*/
	public void removeArtworks(long pk, long[] artworkPKs);

	/**
	* Removes the association between the artwork collection and the artworks. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artworks the artworks
	*/
	public void removeArtworks(long pk,
		java.util.List<eu.strasbourg.service.artwork.model.Artwork> artworks);

	/**
	* Sets the artworks associated with the artwork collection, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artworkPKs the primary keys of the artworks to be associated with the artwork collection
	*/
	public void setArtworks(long pk, long[] artworkPKs);

	/**
	* Sets the artworks associated with the artwork collection, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the artwork collection
	* @param artworks the artworks to be associated with the artwork collection
	*/
	public void setArtworks(long pk,
		java.util.List<eu.strasbourg.service.artwork.model.Artwork> artworks);

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}