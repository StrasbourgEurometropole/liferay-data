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

import eu.strasbourg.service.artwork.exception.NoSuchArtworkException;
import eu.strasbourg.service.artwork.model.Artwork;

/**
 * The persistence interface for the artwork service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.artwork.service.persistence.impl.ArtworkPersistenceImpl
 * @see ArtworkUtil
 * @generated
 */
@ProviderType
public interface ArtworkPersistence extends BasePersistence<Artwork> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ArtworkUtil} to access the artwork persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the artworks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching artworks
	*/
	public java.util.List<Artwork> findByUuid(java.lang.String uuid);

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
	public java.util.List<Artwork> findByUuid(java.lang.String uuid, int start,
		int end);

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
	public java.util.List<Artwork> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Artwork> orderByComparator);

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
	public java.util.List<Artwork> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Artwork> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first artwork in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork
	* @throws NoSuchArtworkException if a matching artwork could not be found
	*/
	public Artwork findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Artwork> orderByComparator)
		throws NoSuchArtworkException;

	/**
	* Returns the first artwork in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork, or <code>null</code> if a matching artwork could not be found
	*/
	public Artwork fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Artwork> orderByComparator);

	/**
	* Returns the last artwork in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork
	* @throws NoSuchArtworkException if a matching artwork could not be found
	*/
	public Artwork findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Artwork> orderByComparator)
		throws NoSuchArtworkException;

	/**
	* Returns the last artwork in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork, or <code>null</code> if a matching artwork could not be found
	*/
	public Artwork fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Artwork> orderByComparator);

	/**
	* Returns the artworks before and after the current artwork in the ordered set where uuid = &#63;.
	*
	* @param artworkId the primary key of the current artwork
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next artwork
	* @throws NoSuchArtworkException if a artwork with the primary key could not be found
	*/
	public Artwork[] findByUuid_PrevAndNext(long artworkId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Artwork> orderByComparator)
		throws NoSuchArtworkException;

	/**
	* Removes all the artworks where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of artworks where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching artworks
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the artwork where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchArtworkException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching artwork
	* @throws NoSuchArtworkException if a matching artwork could not be found
	*/
	public Artwork findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchArtworkException;

	/**
	* Returns the artwork where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching artwork, or <code>null</code> if a matching artwork could not be found
	*/
	public Artwork fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the artwork where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching artwork, or <code>null</code> if a matching artwork could not be found
	*/
	public Artwork fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the artwork where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the artwork that was removed
	*/
	public Artwork removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchArtworkException;

	/**
	* Returns the number of artworks where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching artworks
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the artworks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching artworks
	*/
	public java.util.List<Artwork> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<Artwork> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<Artwork> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Artwork> orderByComparator);

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
	public java.util.List<Artwork> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Artwork> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first artwork in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork
	* @throws NoSuchArtworkException if a matching artwork could not be found
	*/
	public Artwork findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Artwork> orderByComparator)
		throws NoSuchArtworkException;

	/**
	* Returns the first artwork in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching artwork, or <code>null</code> if a matching artwork could not be found
	*/
	public Artwork fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Artwork> orderByComparator);

	/**
	* Returns the last artwork in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork
	* @throws NoSuchArtworkException if a matching artwork could not be found
	*/
	public Artwork findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Artwork> orderByComparator)
		throws NoSuchArtworkException;

	/**
	* Returns the last artwork in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching artwork, or <code>null</code> if a matching artwork could not be found
	*/
	public Artwork fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Artwork> orderByComparator);

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
	public Artwork[] findByUuid_C_PrevAndNext(long artworkId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Artwork> orderByComparator)
		throws NoSuchArtworkException;

	/**
	* Removes all the artworks where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of artworks where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching artworks
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the artwork in the entity cache if it is enabled.
	*
	* @param artwork the artwork
	*/
	public void cacheResult(Artwork artwork);

	/**
	* Caches the artworks in the entity cache if it is enabled.
	*
	* @param artworks the artworks
	*/
	public void cacheResult(java.util.List<Artwork> artworks);

	/**
	* Creates a new artwork with the primary key. Does not add the artwork to the database.
	*
	* @param artworkId the primary key for the new artwork
	* @return the new artwork
	*/
	public Artwork create(long artworkId);

	/**
	* Removes the artwork with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param artworkId the primary key of the artwork
	* @return the artwork that was removed
	* @throws NoSuchArtworkException if a artwork with the primary key could not be found
	*/
	public Artwork remove(long artworkId) throws NoSuchArtworkException;

	public Artwork updateImpl(Artwork artwork);

	/**
	* Returns the artwork with the primary key or throws a {@link NoSuchArtworkException} if it could not be found.
	*
	* @param artworkId the primary key of the artwork
	* @return the artwork
	* @throws NoSuchArtworkException if a artwork with the primary key could not be found
	*/
	public Artwork findByPrimaryKey(long artworkId)
		throws NoSuchArtworkException;

	/**
	* Returns the artwork with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param artworkId the primary key of the artwork
	* @return the artwork, or <code>null</code> if a artwork with the primary key could not be found
	*/
	public Artwork fetchByPrimaryKey(long artworkId);

	@Override
	public java.util.Map<java.io.Serializable, Artwork> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the artworks.
	*
	* @return the artworks
	*/
	public java.util.List<Artwork> findAll();

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
	public java.util.List<Artwork> findAll(int start, int end);

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
	public java.util.List<Artwork> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Artwork> orderByComparator);

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
	public java.util.List<Artwork> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Artwork> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the artworks from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of artworks.
	*
	* @return the number of artworks
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}