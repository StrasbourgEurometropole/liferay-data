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

package eu.strasbourg.service.link.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.link.exception.NoSuchLinkException;
import eu.strasbourg.service.link.model.Link;

/**
 * The persistence interface for the link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.link.service.persistence.impl.LinkPersistenceImpl
 * @see LinkUtil
 * @generated
 */
@ProviderType
public interface LinkPersistence extends BasePersistence<Link> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LinkUtil} to access the link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the links where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching links
	*/
	public java.util.List<Link> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the links where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of links
	* @param end the upper bound of the range of links (not inclusive)
	* @return the range of matching links
	*/
	public java.util.List<Link> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the links where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of links
	* @param end the upper bound of the range of links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching links
	*/
	public java.util.List<Link> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Link> orderByComparator);

	/**
	* Returns an ordered range of all the links where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of links
	* @param end the upper bound of the range of links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching links
	*/
	public java.util.List<Link> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Link> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first link in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching link
	* @throws NoSuchLinkException if a matching link could not be found
	*/
	public Link findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Link> orderByComparator)
		throws NoSuchLinkException;

	/**
	* Returns the first link in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching link, or <code>null</code> if a matching link could not be found
	*/
	public Link fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Link> orderByComparator);

	/**
	* Returns the last link in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching link
	* @throws NoSuchLinkException if a matching link could not be found
	*/
	public Link findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Link> orderByComparator)
		throws NoSuchLinkException;

	/**
	* Returns the last link in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching link, or <code>null</code> if a matching link could not be found
	*/
	public Link fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Link> orderByComparator);

	/**
	* Returns the links before and after the current link in the ordered set where uuid = &#63;.
	*
	* @param linkId the primary key of the current link
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next link
	* @throws NoSuchLinkException if a link with the primary key could not be found
	*/
	public Link[] findByUuid_PrevAndNext(long linkId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Link> orderByComparator)
		throws NoSuchLinkException;

	/**
	* Removes all the links where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of links where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching links
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the link where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchLinkException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching link
	* @throws NoSuchLinkException if a matching link could not be found
	*/
	public Link findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchLinkException;

	/**
	* Returns the link where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching link, or <code>null</code> if a matching link could not be found
	*/
	public Link fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the link where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching link, or <code>null</code> if a matching link could not be found
	*/
	public Link fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the link where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the link that was removed
	*/
	public Link removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchLinkException;

	/**
	* Returns the number of links where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching links
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the links where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching links
	*/
	public java.util.List<Link> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the links where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of links
	* @param end the upper bound of the range of links (not inclusive)
	* @return the range of matching links
	*/
	public java.util.List<Link> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the links where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of links
	* @param end the upper bound of the range of links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching links
	*/
	public java.util.List<Link> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Link> orderByComparator);

	/**
	* Returns an ordered range of all the links where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of links
	* @param end the upper bound of the range of links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching links
	*/
	public java.util.List<Link> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Link> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first link in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching link
	* @throws NoSuchLinkException if a matching link could not be found
	*/
	public Link findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Link> orderByComparator)
		throws NoSuchLinkException;

	/**
	* Returns the first link in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching link, or <code>null</code> if a matching link could not be found
	*/
	public Link fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Link> orderByComparator);

	/**
	* Returns the last link in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching link
	* @throws NoSuchLinkException if a matching link could not be found
	*/
	public Link findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Link> orderByComparator)
		throws NoSuchLinkException;

	/**
	* Returns the last link in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching link, or <code>null</code> if a matching link could not be found
	*/
	public Link fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Link> orderByComparator);

	/**
	* Returns the links before and after the current link in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param linkId the primary key of the current link
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next link
	* @throws NoSuchLinkException if a link with the primary key could not be found
	*/
	public Link[] findByUuid_C_PrevAndNext(long linkId, java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<Link> orderByComparator)
		throws NoSuchLinkException;

	/**
	* Removes all the links where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of links where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching links
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the link in the entity cache if it is enabled.
	*
	* @param link the link
	*/
	public void cacheResult(Link link);

	/**
	* Caches the links in the entity cache if it is enabled.
	*
	* @param links the links
	*/
	public void cacheResult(java.util.List<Link> links);

	/**
	* Creates a new link with the primary key. Does not add the link to the database.
	*
	* @param linkId the primary key for the new link
	* @return the new link
	*/
	public Link create(long linkId);

	/**
	* Removes the link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param linkId the primary key of the link
	* @return the link that was removed
	* @throws NoSuchLinkException if a link with the primary key could not be found
	*/
	public Link remove(long linkId) throws NoSuchLinkException;

	public Link updateImpl(Link link);

	/**
	* Returns the link with the primary key or throws a {@link NoSuchLinkException} if it could not be found.
	*
	* @param linkId the primary key of the link
	* @return the link
	* @throws NoSuchLinkException if a link with the primary key could not be found
	*/
	public Link findByPrimaryKey(long linkId) throws NoSuchLinkException;

	/**
	* Returns the link with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param linkId the primary key of the link
	* @return the link, or <code>null</code> if a link with the primary key could not be found
	*/
	public Link fetchByPrimaryKey(long linkId);

	@Override
	public java.util.Map<java.io.Serializable, Link> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the links.
	*
	* @return the links
	*/
	public java.util.List<Link> findAll();

	/**
	* Returns a range of all the links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of links
	* @param end the upper bound of the range of links (not inclusive)
	* @return the range of links
	*/
	public java.util.List<Link> findAll(int start, int end);

	/**
	* Returns an ordered range of all the links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of links
	* @param end the upper bound of the range of links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of links
	*/
	public java.util.List<Link> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Link> orderByComparator);

	/**
	* Returns an ordered range of all the links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of links
	* @param end the upper bound of the range of links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of links
	*/
	public java.util.List<Link> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Link> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the links from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of links.
	*
	* @return the number of links
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}