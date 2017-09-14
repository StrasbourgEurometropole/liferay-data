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

package eu.strasbourg.service.oidc.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.oidc.exception.NoSuchPublikUserException;
import eu.strasbourg.service.oidc.model.PublikUser;

/**
 * The persistence interface for the publik user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see eu.strasbourg.service.oidc.service.persistence.impl.PublikUserPersistenceImpl
 * @see PublikUserUtil
 * @generated
 */
@ProviderType
public interface PublikUserPersistence extends BasePersistence<PublikUser> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PublikUserUtil} to access the publik user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the publik users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching publik users
	*/
	public java.util.List<PublikUser> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the publik users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublikUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of publik users
	* @param end the upper bound of the range of publik users (not inclusive)
	* @return the range of matching publik users
	*/
	public java.util.List<PublikUser> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the publik users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublikUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of publik users
	* @param end the upper bound of the range of publik users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching publik users
	*/
	public java.util.List<PublikUser> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublikUser> orderByComparator);

	/**
	* Returns an ordered range of all the publik users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublikUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of publik users
	* @param end the upper bound of the range of publik users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching publik users
	*/
	public java.util.List<PublikUser> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublikUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first publik user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publik user
	* @throws NoSuchPublikUserException if a matching publik user could not be found
	*/
	public PublikUser findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PublikUser> orderByComparator)
		throws NoSuchPublikUserException;

	/**
	* Returns the first publik user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching publik user, or <code>null</code> if a matching publik user could not be found
	*/
	public PublikUser fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PublikUser> orderByComparator);

	/**
	* Returns the last publik user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publik user
	* @throws NoSuchPublikUserException if a matching publik user could not be found
	*/
	public PublikUser findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PublikUser> orderByComparator)
		throws NoSuchPublikUserException;

	/**
	* Returns the last publik user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching publik user, or <code>null</code> if a matching publik user could not be found
	*/
	public PublikUser fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PublikUser> orderByComparator);

	/**
	* Returns the publik users before and after the current publik user in the ordered set where uuid = &#63;.
	*
	* @param publikUserLiferayId the primary key of the current publik user
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next publik user
	* @throws NoSuchPublikUserException if a publik user with the primary key could not be found
	*/
	public PublikUser[] findByUuid_PrevAndNext(long publikUserLiferayId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PublikUser> orderByComparator)
		throws NoSuchPublikUserException;

	/**
	* Removes all the publik users where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of publik users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching publik users
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the publik user where publikId = &#63; or throws a {@link NoSuchPublikUserException} if it could not be found.
	*
	* @param publikId the publik ID
	* @return the matching publik user
	* @throws NoSuchPublikUserException if a matching publik user could not be found
	*/
	public PublikUser findByPublikId(java.lang.String publikId)
		throws NoSuchPublikUserException;

	/**
	* Returns the publik user where publikId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param publikId the publik ID
	* @return the matching publik user, or <code>null</code> if a matching publik user could not be found
	*/
	public PublikUser fetchByPublikId(java.lang.String publikId);

	/**
	* Returns the publik user where publikId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param publikId the publik ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching publik user, or <code>null</code> if a matching publik user could not be found
	*/
	public PublikUser fetchByPublikId(java.lang.String publikId,
		boolean retrieveFromCache);

	/**
	* Removes the publik user where publikId = &#63; from the database.
	*
	* @param publikId the publik ID
	* @return the publik user that was removed
	*/
	public PublikUser removeByPublikId(java.lang.String publikId)
		throws NoSuchPublikUserException;

	/**
	* Returns the number of publik users where publikId = &#63;.
	*
	* @param publikId the publik ID
	* @return the number of matching publik users
	*/
	public int countByPublikId(java.lang.String publikId);

	/**
	* Caches the publik user in the entity cache if it is enabled.
	*
	* @param publikUser the publik user
	*/
	public void cacheResult(PublikUser publikUser);

	/**
	* Caches the publik users in the entity cache if it is enabled.
	*
	* @param publikUsers the publik users
	*/
	public void cacheResult(java.util.List<PublikUser> publikUsers);

	/**
	* Creates a new publik user with the primary key. Does not add the publik user to the database.
	*
	* @param publikUserLiferayId the primary key for the new publik user
	* @return the new publik user
	*/
	public PublikUser create(long publikUserLiferayId);

	/**
	* Removes the publik user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param publikUserLiferayId the primary key of the publik user
	* @return the publik user that was removed
	* @throws NoSuchPublikUserException if a publik user with the primary key could not be found
	*/
	public PublikUser remove(long publikUserLiferayId)
		throws NoSuchPublikUserException;

	public PublikUser updateImpl(PublikUser publikUser);

	/**
	* Returns the publik user with the primary key or throws a {@link NoSuchPublikUserException} if it could not be found.
	*
	* @param publikUserLiferayId the primary key of the publik user
	* @return the publik user
	* @throws NoSuchPublikUserException if a publik user with the primary key could not be found
	*/
	public PublikUser findByPrimaryKey(long publikUserLiferayId)
		throws NoSuchPublikUserException;

	/**
	* Returns the publik user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param publikUserLiferayId the primary key of the publik user
	* @return the publik user, or <code>null</code> if a publik user with the primary key could not be found
	*/
	public PublikUser fetchByPrimaryKey(long publikUserLiferayId);

	@Override
	public java.util.Map<java.io.Serializable, PublikUser> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the publik users.
	*
	* @return the publik users
	*/
	public java.util.List<PublikUser> findAll();

	/**
	* Returns a range of all the publik users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublikUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of publik users
	* @param end the upper bound of the range of publik users (not inclusive)
	* @return the range of publik users
	*/
	public java.util.List<PublikUser> findAll(int start, int end);

	/**
	* Returns an ordered range of all the publik users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublikUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of publik users
	* @param end the upper bound of the range of publik users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of publik users
	*/
	public java.util.List<PublikUser> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublikUser> orderByComparator);

	/**
	* Returns an ordered range of all the publik users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PublikUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of publik users
	* @param end the upper bound of the range of publik users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of publik users
	*/
	public java.util.List<PublikUser> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PublikUser> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the publik users from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of publik users.
	*
	* @return the number of publik users
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}