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

package eu.strasbourg.service.project.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.project.exception.NoSuchInitiativeHelpException;
import eu.strasbourg.service.project.model.InitiativeHelp;

/**
 * The persistence interface for the initiative help service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.project.service.persistence.impl.InitiativeHelpPersistenceImpl
 * @see InitiativeHelpUtil
 * @generated
 */
@ProviderType
public interface InitiativeHelpPersistence extends BasePersistence<InitiativeHelp> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link InitiativeHelpUtil} to access the initiative help persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the initiative helps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching initiative helps
	*/
	public java.util.List<InitiativeHelp> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the initiative helps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @return the range of matching initiative helps
	*/
	public java.util.List<InitiativeHelp> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the initiative helps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching initiative helps
	*/
	public java.util.List<InitiativeHelp> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator);

	/**
	* Returns an ordered range of all the initiative helps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching initiative helps
	*/
	public java.util.List<InitiativeHelp> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first initiative help in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative help
	* @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	*/
	public InitiativeHelp findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator)
		throws NoSuchInitiativeHelpException;

	/**
	* Returns the first initiative help in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public InitiativeHelp fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator);

	/**
	* Returns the last initiative help in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative help
	* @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	*/
	public InitiativeHelp findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator)
		throws NoSuchInitiativeHelpException;

	/**
	* Returns the last initiative help in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public InitiativeHelp fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator);

	/**
	* Returns the initiative helps before and after the current initiative help in the ordered set where uuid = &#63;.
	*
	* @param initiativeHelpId the primary key of the current initiative help
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next initiative help
	* @throws NoSuchInitiativeHelpException if a initiative help with the primary key could not be found
	*/
	public InitiativeHelp[] findByUuid_PrevAndNext(long initiativeHelpId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator)
		throws NoSuchInitiativeHelpException;

	/**
	* Removes all the initiative helps where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of initiative helps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching initiative helps
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the initiative help where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchInitiativeHelpException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching initiative help
	* @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	*/
	public InitiativeHelp findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchInitiativeHelpException;

	/**
	* Returns the initiative help where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public InitiativeHelp fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the initiative help where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public InitiativeHelp fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the initiative help where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the initiative help that was removed
	*/
	public InitiativeHelp removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchInitiativeHelpException;

	/**
	* Returns the number of initiative helps where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching initiative helps
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the initiative helps where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the matching initiative helps
	*/
	public java.util.List<InitiativeHelp> findByPublikUserId(
		java.lang.String publikUserId);

	/**
	* Returns a range of all the initiative helps where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @return the range of matching initiative helps
	*/
	public java.util.List<InitiativeHelp> findByPublikUserId(
		java.lang.String publikUserId, int start, int end);

	/**
	* Returns an ordered range of all the initiative helps where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching initiative helps
	*/
	public java.util.List<InitiativeHelp> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator);

	/**
	* Returns an ordered range of all the initiative helps where publikUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param publikUserId the publik user ID
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching initiative helps
	*/
	public java.util.List<InitiativeHelp> findByPublikUserId(
		java.lang.String publikUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first initiative help in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative help
	* @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	*/
	public InitiativeHelp findByPublikUserId_First(
		java.lang.String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator)
		throws NoSuchInitiativeHelpException;

	/**
	* Returns the first initiative help in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public InitiativeHelp fetchByPublikUserId_First(
		java.lang.String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator);

	/**
	* Returns the last initiative help in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative help
	* @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	*/
	public InitiativeHelp findByPublikUserId_Last(
		java.lang.String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator)
		throws NoSuchInitiativeHelpException;

	/**
	* Returns the last initiative help in the ordered set where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public InitiativeHelp fetchByPublikUserId_Last(
		java.lang.String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator);

	/**
	* Returns the initiative helps before and after the current initiative help in the ordered set where publikUserId = &#63;.
	*
	* @param initiativeHelpId the primary key of the current initiative help
	* @param publikUserId the publik user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next initiative help
	* @throws NoSuchInitiativeHelpException if a initiative help with the primary key could not be found
	*/
	public InitiativeHelp[] findByPublikUserId_PrevAndNext(
		long initiativeHelpId, java.lang.String publikUserId,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator)
		throws NoSuchInitiativeHelpException;

	/**
	* Removes all the initiative helps where publikUserId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	*/
	public void removeByPublikUserId(java.lang.String publikUserId);

	/**
	* Returns the number of initiative helps where publikUserId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @return the number of matching initiative helps
	*/
	public int countByPublikUserId(java.lang.String publikUserId);

	/**
	* Returns all the initiative helps where initiativeId = &#63;.
	*
	* @param initiativeId the initiative ID
	* @return the matching initiative helps
	*/
	public java.util.List<InitiativeHelp> findByinitiativeId(long initiativeId);

	/**
	* Returns a range of all the initiative helps where initiativeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param initiativeId the initiative ID
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @return the range of matching initiative helps
	*/
	public java.util.List<InitiativeHelp> findByinitiativeId(
		long initiativeId, int start, int end);

	/**
	* Returns an ordered range of all the initiative helps where initiativeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param initiativeId the initiative ID
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching initiative helps
	*/
	public java.util.List<InitiativeHelp> findByinitiativeId(
		long initiativeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator);

	/**
	* Returns an ordered range of all the initiative helps where initiativeId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param initiativeId the initiative ID
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching initiative helps
	*/
	public java.util.List<InitiativeHelp> findByinitiativeId(
		long initiativeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first initiative help in the ordered set where initiativeId = &#63;.
	*
	* @param initiativeId the initiative ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative help
	* @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	*/
	public InitiativeHelp findByinitiativeId_First(long initiativeId,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator)
		throws NoSuchInitiativeHelpException;

	/**
	* Returns the first initiative help in the ordered set where initiativeId = &#63;.
	*
	* @param initiativeId the initiative ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public InitiativeHelp fetchByinitiativeId_First(long initiativeId,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator);

	/**
	* Returns the last initiative help in the ordered set where initiativeId = &#63;.
	*
	* @param initiativeId the initiative ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative help
	* @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	*/
	public InitiativeHelp findByinitiativeId_Last(long initiativeId,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator)
		throws NoSuchInitiativeHelpException;

	/**
	* Returns the last initiative help in the ordered set where initiativeId = &#63;.
	*
	* @param initiativeId the initiative ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public InitiativeHelp fetchByinitiativeId_Last(long initiativeId,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator);

	/**
	* Returns the initiative helps before and after the current initiative help in the ordered set where initiativeId = &#63;.
	*
	* @param initiativeHelpId the primary key of the current initiative help
	* @param initiativeId the initiative ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next initiative help
	* @throws NoSuchInitiativeHelpException if a initiative help with the primary key could not be found
	*/
	public InitiativeHelp[] findByinitiativeId_PrevAndNext(
		long initiativeHelpId, long initiativeId,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator)
		throws NoSuchInitiativeHelpException;

	/**
	* Removes all the initiative helps where initiativeId = &#63; from the database.
	*
	* @param initiativeId the initiative ID
	*/
	public void removeByinitiativeId(long initiativeId);

	/**
	* Returns the number of initiative helps where initiativeId = &#63;.
	*
	* @param initiativeId the initiative ID
	* @return the number of matching initiative helps
	*/
	public int countByinitiativeId(long initiativeId);

	/**
	* Returns the initiative help where publikUserId = &#63; and initiativeId = &#63; or throws a {@link NoSuchInitiativeHelpException} if it could not be found.
	*
	* @param publikUserId the publik user ID
	* @param initiativeId the initiative ID
	* @return the matching initiative help
	* @throws NoSuchInitiativeHelpException if a matching initiative help could not be found
	*/
	public InitiativeHelp findByPublikUserIdAndInitiativeId(
		java.lang.String publikUserId, long initiativeId)
		throws NoSuchInitiativeHelpException;

	/**
	* Returns the initiative help where publikUserId = &#63; and initiativeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param publikUserId the publik user ID
	* @param initiativeId the initiative ID
	* @return the matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public InitiativeHelp fetchByPublikUserIdAndInitiativeId(
		java.lang.String publikUserId, long initiativeId);

	/**
	* Returns the initiative help where publikUserId = &#63; and initiativeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param publikUserId the publik user ID
	* @param initiativeId the initiative ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching initiative help, or <code>null</code> if a matching initiative help could not be found
	*/
	public InitiativeHelp fetchByPublikUserIdAndInitiativeId(
		java.lang.String publikUserId, long initiativeId,
		boolean retrieveFromCache);

	/**
	* Removes the initiative help where publikUserId = &#63; and initiativeId = &#63; from the database.
	*
	* @param publikUserId the publik user ID
	* @param initiativeId the initiative ID
	* @return the initiative help that was removed
	*/
	public InitiativeHelp removeByPublikUserIdAndInitiativeId(
		java.lang.String publikUserId, long initiativeId)
		throws NoSuchInitiativeHelpException;

	/**
	* Returns the number of initiative helps where publikUserId = &#63; and initiativeId = &#63;.
	*
	* @param publikUserId the publik user ID
	* @param initiativeId the initiative ID
	* @return the number of matching initiative helps
	*/
	public int countByPublikUserIdAndInitiativeId(
		java.lang.String publikUserId, long initiativeId);

	/**
	* Caches the initiative help in the entity cache if it is enabled.
	*
	* @param initiativeHelp the initiative help
	*/
	public void cacheResult(InitiativeHelp initiativeHelp);

	/**
	* Caches the initiative helps in the entity cache if it is enabled.
	*
	* @param initiativeHelps the initiative helps
	*/
	public void cacheResult(java.util.List<InitiativeHelp> initiativeHelps);

	/**
	* Creates a new initiative help with the primary key. Does not add the initiative help to the database.
	*
	* @param initiativeHelpId the primary key for the new initiative help
	* @return the new initiative help
	*/
	public InitiativeHelp create(long initiativeHelpId);

	/**
	* Removes the initiative help with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param initiativeHelpId the primary key of the initiative help
	* @return the initiative help that was removed
	* @throws NoSuchInitiativeHelpException if a initiative help with the primary key could not be found
	*/
	public InitiativeHelp remove(long initiativeHelpId)
		throws NoSuchInitiativeHelpException;

	public InitiativeHelp updateImpl(InitiativeHelp initiativeHelp);

	/**
	* Returns the initiative help with the primary key or throws a {@link NoSuchInitiativeHelpException} if it could not be found.
	*
	* @param initiativeHelpId the primary key of the initiative help
	* @return the initiative help
	* @throws NoSuchInitiativeHelpException if a initiative help with the primary key could not be found
	*/
	public InitiativeHelp findByPrimaryKey(long initiativeHelpId)
		throws NoSuchInitiativeHelpException;

	/**
	* Returns the initiative help with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param initiativeHelpId the primary key of the initiative help
	* @return the initiative help, or <code>null</code> if a initiative help with the primary key could not be found
	*/
	public InitiativeHelp fetchByPrimaryKey(long initiativeHelpId);

	@Override
	public java.util.Map<java.io.Serializable, InitiativeHelp> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the initiative helps.
	*
	* @return the initiative helps
	*/
	public java.util.List<InitiativeHelp> findAll();

	/**
	* Returns a range of all the initiative helps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @return the range of initiative helps
	*/
	public java.util.List<InitiativeHelp> findAll(int start, int end);

	/**
	* Returns an ordered range of all the initiative helps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of initiative helps
	*/
	public java.util.List<InitiativeHelp> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator);

	/**
	* Returns an ordered range of all the initiative helps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link InitiativeHelpModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of initiative helps
	* @param end the upper bound of the range of initiative helps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of initiative helps
	*/
	public java.util.List<InitiativeHelp> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<InitiativeHelp> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the initiative helps from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of initiative helps.
	*
	* @return the number of initiative helps
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}