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

package eu.strasbourg.service.council.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.council.exception.NoSuchCouncilSessionException;
import eu.strasbourg.service.council.model.CouncilSession;

/**
 * The persistence interface for the council session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see eu.strasbourg.service.council.service.persistence.impl.CouncilSessionPersistenceImpl
 * @see CouncilSessionUtil
 * @generated
 */
@ProviderType
public interface CouncilSessionPersistence extends BasePersistence<CouncilSession> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CouncilSessionUtil} to access the council session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the council sessions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching council sessions
	*/
	public java.util.List<CouncilSession> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the council sessions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @return the range of matching council sessions
	*/
	public java.util.List<CouncilSession> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the council sessions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching council sessions
	*/
	public java.util.List<CouncilSession> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CouncilSession> orderByComparator);

	/**
	* Returns an ordered range of all the council sessions where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching council sessions
	*/
	public java.util.List<CouncilSession> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CouncilSession> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first council session in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching council session
	* @throws NoSuchCouncilSessionException if a matching council session could not be found
	*/
	public CouncilSession findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException;

	/**
	* Returns the first council session in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching council session, or <code>null</code> if a matching council session could not be found
	*/
	public CouncilSession fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CouncilSession> orderByComparator);

	/**
	* Returns the last council session in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching council session
	* @throws NoSuchCouncilSessionException if a matching council session could not be found
	*/
	public CouncilSession findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException;

	/**
	* Returns the last council session in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching council session, or <code>null</code> if a matching council session could not be found
	*/
	public CouncilSession fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CouncilSession> orderByComparator);

	/**
	* Returns the council sessions before and after the current council session in the ordered set where uuid = &#63;.
	*
	* @param councilSessionId the primary key of the current council session
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next council session
	* @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	*/
	public CouncilSession[] findByUuid_PrevAndNext(long councilSessionId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException;

	/**
	* Removes all the council sessions where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of council sessions where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching council sessions
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the council session where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCouncilSessionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching council session
	* @throws NoSuchCouncilSessionException if a matching council session could not be found
	*/
	public CouncilSession findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchCouncilSessionException;

	/**
	* Returns the council session where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching council session, or <code>null</code> if a matching council session could not be found
	*/
	public CouncilSession fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the council session where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching council session, or <code>null</code> if a matching council session could not be found
	*/
	public CouncilSession fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the council session where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the council session that was removed
	*/
	public CouncilSession removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchCouncilSessionException;

	/**
	* Returns the number of council sessions where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching council sessions
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the council sessions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching council sessions
	*/
	public java.util.List<CouncilSession> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the council sessions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @return the range of matching council sessions
	*/
	public java.util.List<CouncilSession> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the council sessions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching council sessions
	*/
	public java.util.List<CouncilSession> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CouncilSession> orderByComparator);

	/**
	* Returns an ordered range of all the council sessions where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching council sessions
	*/
	public java.util.List<CouncilSession> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CouncilSession> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first council session in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching council session
	* @throws NoSuchCouncilSessionException if a matching council session could not be found
	*/
	public CouncilSession findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException;

	/**
	* Returns the first council session in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching council session, or <code>null</code> if a matching council session could not be found
	*/
	public CouncilSession fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CouncilSession> orderByComparator);

	/**
	* Returns the last council session in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching council session
	* @throws NoSuchCouncilSessionException if a matching council session could not be found
	*/
	public CouncilSession findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException;

	/**
	* Returns the last council session in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching council session, or <code>null</code> if a matching council session could not be found
	*/
	public CouncilSession fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CouncilSession> orderByComparator);

	/**
	* Returns the council sessions before and after the current council session in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param councilSessionId the primary key of the current council session
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next council session
	* @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	*/
	public CouncilSession[] findByUuid_C_PrevAndNext(long councilSessionId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<CouncilSession> orderByComparator)
		throws NoSuchCouncilSessionException;

	/**
	* Removes all the council sessions where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of council sessions where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching council sessions
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Caches the council session in the entity cache if it is enabled.
	*
	* @param councilSession the council session
	*/
	public void cacheResult(CouncilSession councilSession);

	/**
	* Caches the council sessions in the entity cache if it is enabled.
	*
	* @param councilSessions the council sessions
	*/
	public void cacheResult(java.util.List<CouncilSession> councilSessions);

	/**
	* Creates a new council session with the primary key. Does not add the council session to the database.
	*
	* @param councilSessionId the primary key for the new council session
	* @return the new council session
	*/
	public CouncilSession create(long councilSessionId);

	/**
	* Removes the council session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param councilSessionId the primary key of the council session
	* @return the council session that was removed
	* @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	*/
	public CouncilSession remove(long councilSessionId)
		throws NoSuchCouncilSessionException;

	public CouncilSession updateImpl(CouncilSession councilSession);

	/**
	* Returns the council session with the primary key or throws a {@link NoSuchCouncilSessionException} if it could not be found.
	*
	* @param councilSessionId the primary key of the council session
	* @return the council session
	* @throws NoSuchCouncilSessionException if a council session with the primary key could not be found
	*/
	public CouncilSession findByPrimaryKey(long councilSessionId)
		throws NoSuchCouncilSessionException;

	/**
	* Returns the council session with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param councilSessionId the primary key of the council session
	* @return the council session, or <code>null</code> if a council session with the primary key could not be found
	*/
	public CouncilSession fetchByPrimaryKey(long councilSessionId);

	@Override
	public java.util.Map<java.io.Serializable, CouncilSession> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the council sessions.
	*
	* @return the council sessions
	*/
	public java.util.List<CouncilSession> findAll();

	/**
	* Returns a range of all the council sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @return the range of council sessions
	*/
	public java.util.List<CouncilSession> findAll(int start, int end);

	/**
	* Returns an ordered range of all the council sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of council sessions
	*/
	public java.util.List<CouncilSession> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CouncilSession> orderByComparator);

	/**
	* Returns an ordered range of all the council sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CouncilSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of council sessions
	* @param end the upper bound of the range of council sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of council sessions
	*/
	public java.util.List<CouncilSession> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CouncilSession> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the council sessions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of council sessions.
	*
	* @return the number of council sessions
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}