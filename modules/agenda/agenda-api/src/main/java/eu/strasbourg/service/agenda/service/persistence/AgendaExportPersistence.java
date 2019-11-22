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

package eu.strasbourg.service.agenda.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.agenda.exception.NoSuchAgendaExportException;
import eu.strasbourg.service.agenda.model.AgendaExport;

/**
 * The persistence interface for the agenda export service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.agenda.service.persistence.impl.AgendaExportPersistenceImpl
 * @see AgendaExportUtil
 * @generated
 */
@ProviderType
public interface AgendaExportPersistence extends BasePersistence<AgendaExport> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AgendaExportUtil} to access the agenda export persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the agenda exports where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching agenda exports
	*/
	public java.util.List<AgendaExport> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the agenda exports where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @return the range of matching agenda exports
	*/
	public java.util.List<AgendaExport> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the agenda exports where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching agenda exports
	*/
	public java.util.List<AgendaExport> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Returns an ordered range of all the agenda exports where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching agenda exports
	*/
	public java.util.List<AgendaExport> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first agenda export in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export
	* @throws NoSuchAgendaExportException if a matching agenda export could not be found
	*/
	public AgendaExport findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator)
		throws NoSuchAgendaExportException;

	/**
	* Returns the first agenda export in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export, or <code>null</code> if a matching agenda export could not be found
	*/
	public AgendaExport fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Returns the last agenda export in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export
	* @throws NoSuchAgendaExportException if a matching agenda export could not be found
	*/
	public AgendaExport findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator)
		throws NoSuchAgendaExportException;

	/**
	* Returns the last agenda export in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export, or <code>null</code> if a matching agenda export could not be found
	*/
	public AgendaExport fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Returns the agenda exports before and after the current agenda export in the ordered set where uuid = &#63;.
	*
	* @param agendaExportId the primary key of the current agenda export
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next agenda export
	* @throws NoSuchAgendaExportException if a agenda export with the primary key could not be found
	*/
	public AgendaExport[] findByUuid_PrevAndNext(long agendaExportId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator)
		throws NoSuchAgendaExportException;

	/**
	* Removes all the agenda exports where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of agenda exports where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching agenda exports
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the agenda export where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchAgendaExportException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching agenda export
	* @throws NoSuchAgendaExportException if a matching agenda export could not be found
	*/
	public AgendaExport findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchAgendaExportException;

	/**
	* Returns the agenda export where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching agenda export, or <code>null</code> if a matching agenda export could not be found
	*/
	public AgendaExport fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the agenda export where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching agenda export, or <code>null</code> if a matching agenda export could not be found
	*/
	public AgendaExport fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the agenda export where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the agenda export that was removed
	*/
	public AgendaExport removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchAgendaExportException;

	/**
	* Returns the number of agenda exports where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching agenda exports
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the agenda exports where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching agenda exports
	*/
	public java.util.List<AgendaExport> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the agenda exports where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @return the range of matching agenda exports
	*/
	public java.util.List<AgendaExport> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the agenda exports where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching agenda exports
	*/
	public java.util.List<AgendaExport> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Returns an ordered range of all the agenda exports where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching agenda exports
	*/
	public java.util.List<AgendaExport> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first agenda export in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export
	* @throws NoSuchAgendaExportException if a matching agenda export could not be found
	*/
	public AgendaExport findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator)
		throws NoSuchAgendaExportException;

	/**
	* Returns the first agenda export in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export, or <code>null</code> if a matching agenda export could not be found
	*/
	public AgendaExport fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Returns the last agenda export in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export
	* @throws NoSuchAgendaExportException if a matching agenda export could not be found
	*/
	public AgendaExport findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator)
		throws NoSuchAgendaExportException;

	/**
	* Returns the last agenda export in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export, or <code>null</code> if a matching agenda export could not be found
	*/
	public AgendaExport fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Returns the agenda exports before and after the current agenda export in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param agendaExportId the primary key of the current agenda export
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next agenda export
	* @throws NoSuchAgendaExportException if a agenda export with the primary key could not be found
	*/
	public AgendaExport[] findByUuid_C_PrevAndNext(long agendaExportId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator)
		throws NoSuchAgendaExportException;

	/**
	* Removes all the agenda exports where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of agenda exports where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching agenda exports
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the agenda exports where status = &#63;.
	*
	* @param status the status
	* @return the matching agenda exports
	*/
	public java.util.List<AgendaExport> findByStatus(int status);

	/**
	* Returns a range of all the agenda exports where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @return the range of matching agenda exports
	*/
	public java.util.List<AgendaExport> findByStatus(int status, int start,
		int end);

	/**
	* Returns an ordered range of all the agenda exports where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching agenda exports
	*/
	public java.util.List<AgendaExport> findByStatus(int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Returns an ordered range of all the agenda exports where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching agenda exports
	*/
	public java.util.List<AgendaExport> findByStatus(int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first agenda export in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export
	* @throws NoSuchAgendaExportException if a matching agenda export could not be found
	*/
	public AgendaExport findByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator)
		throws NoSuchAgendaExportException;

	/**
	* Returns the first agenda export in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export, or <code>null</code> if a matching agenda export could not be found
	*/
	public AgendaExport fetchByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Returns the last agenda export in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export
	* @throws NoSuchAgendaExportException if a matching agenda export could not be found
	*/
	public AgendaExport findByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator)
		throws NoSuchAgendaExportException;

	/**
	* Returns the last agenda export in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export, or <code>null</code> if a matching agenda export could not be found
	*/
	public AgendaExport fetchByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Returns the agenda exports before and after the current agenda export in the ordered set where status = &#63;.
	*
	* @param agendaExportId the primary key of the current agenda export
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next agenda export
	* @throws NoSuchAgendaExportException if a agenda export with the primary key could not be found
	*/
	public AgendaExport[] findByStatus_PrevAndNext(long agendaExportId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator)
		throws NoSuchAgendaExportException;

	/**
	* Removes all the agenda exports where status = &#63; from the database.
	*
	* @param status the status
	*/
	public void removeByStatus(int status);

	/**
	* Returns the number of agenda exports where status = &#63;.
	*
	* @param status the status
	* @return the number of matching agenda exports
	*/
	public int countByStatus(int status);

	/**
	* Returns all the agenda exports where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching agenda exports
	*/
	public java.util.List<AgendaExport> findByGroupId(long groupId);

	/**
	* Returns a range of all the agenda exports where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @return the range of matching agenda exports
	*/
	public java.util.List<AgendaExport> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the agenda exports where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching agenda exports
	*/
	public java.util.List<AgendaExport> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Returns an ordered range of all the agenda exports where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching agenda exports
	*/
	public java.util.List<AgendaExport> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first agenda export in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export
	* @throws NoSuchAgendaExportException if a matching agenda export could not be found
	*/
	public AgendaExport findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator)
		throws NoSuchAgendaExportException;

	/**
	* Returns the first agenda export in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export, or <code>null</code> if a matching agenda export could not be found
	*/
	public AgendaExport fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Returns the last agenda export in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export
	* @throws NoSuchAgendaExportException if a matching agenda export could not be found
	*/
	public AgendaExport findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator)
		throws NoSuchAgendaExportException;

	/**
	* Returns the last agenda export in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export, or <code>null</code> if a matching agenda export could not be found
	*/
	public AgendaExport fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Returns the agenda exports before and after the current agenda export in the ordered set where groupId = &#63;.
	*
	* @param agendaExportId the primary key of the current agenda export
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next agenda export
	* @throws NoSuchAgendaExportException if a agenda export with the primary key could not be found
	*/
	public AgendaExport[] findByGroupId_PrevAndNext(long agendaExportId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator)
		throws NoSuchAgendaExportException;

	/**
	* Removes all the agenda exports where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of agenda exports where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching agenda exports
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the agenda exports where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the matching agenda exports
	*/
	public java.util.List<AgendaExport> findByGroupIdAndStatus(long groupId,
		int status);

	/**
	* Returns a range of all the agenda exports where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @return the range of matching agenda exports
	*/
	public java.util.List<AgendaExport> findByGroupIdAndStatus(long groupId,
		int status, int start, int end);

	/**
	* Returns an ordered range of all the agenda exports where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching agenda exports
	*/
	public java.util.List<AgendaExport> findByGroupIdAndStatus(long groupId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Returns an ordered range of all the agenda exports where groupId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param status the status
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching agenda exports
	*/
	public java.util.List<AgendaExport> findByGroupIdAndStatus(long groupId,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first agenda export in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export
	* @throws NoSuchAgendaExportException if a matching agenda export could not be found
	*/
	public AgendaExport findByGroupIdAndStatus_First(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator)
		throws NoSuchAgendaExportException;

	/**
	* Returns the first agenda export in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching agenda export, or <code>null</code> if a matching agenda export could not be found
	*/
	public AgendaExport fetchByGroupIdAndStatus_First(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Returns the last agenda export in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export
	* @throws NoSuchAgendaExportException if a matching agenda export could not be found
	*/
	public AgendaExport findByGroupIdAndStatus_Last(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator)
		throws NoSuchAgendaExportException;

	/**
	* Returns the last agenda export in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching agenda export, or <code>null</code> if a matching agenda export could not be found
	*/
	public AgendaExport fetchByGroupIdAndStatus_Last(long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Returns the agenda exports before and after the current agenda export in the ordered set where groupId = &#63; and status = &#63;.
	*
	* @param agendaExportId the primary key of the current agenda export
	* @param groupId the group ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next agenda export
	* @throws NoSuchAgendaExportException if a agenda export with the primary key could not be found
	*/
	public AgendaExport[] findByGroupIdAndStatus_PrevAndNext(
		long agendaExportId, long groupId, int status,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator)
		throws NoSuchAgendaExportException;

	/**
	* Removes all the agenda exports where groupId = &#63; and status = &#63; from the database.
	*
	* @param groupId the group ID
	* @param status the status
	*/
	public void removeByGroupIdAndStatus(long groupId, int status);

	/**
	* Returns the number of agenda exports where groupId = &#63; and status = &#63;.
	*
	* @param groupId the group ID
	* @param status the status
	* @return the number of matching agenda exports
	*/
	public int countByGroupIdAndStatus(long groupId, int status);

	/**
	* Caches the agenda export in the entity cache if it is enabled.
	*
	* @param agendaExport the agenda export
	*/
	public void cacheResult(AgendaExport agendaExport);

	/**
	* Caches the agenda exports in the entity cache if it is enabled.
	*
	* @param agendaExports the agenda exports
	*/
	public void cacheResult(java.util.List<AgendaExport> agendaExports);

	/**
	* Creates a new agenda export with the primary key. Does not add the agenda export to the database.
	*
	* @param agendaExportId the primary key for the new agenda export
	* @return the new agenda export
	*/
	public AgendaExport create(long agendaExportId);

	/**
	* Removes the agenda export with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExportId the primary key of the agenda export
	* @return the agenda export that was removed
	* @throws NoSuchAgendaExportException if a agenda export with the primary key could not be found
	*/
	public AgendaExport remove(long agendaExportId)
		throws NoSuchAgendaExportException;

	public AgendaExport updateImpl(AgendaExport agendaExport);

	/**
	* Returns the agenda export with the primary key or throws a {@link NoSuchAgendaExportException} if it could not be found.
	*
	* @param agendaExportId the primary key of the agenda export
	* @return the agenda export
	* @throws NoSuchAgendaExportException if a agenda export with the primary key could not be found
	*/
	public AgendaExport findByPrimaryKey(long agendaExportId)
		throws NoSuchAgendaExportException;

	/**
	* Returns the agenda export with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param agendaExportId the primary key of the agenda export
	* @return the agenda export, or <code>null</code> if a agenda export with the primary key could not be found
	*/
	public AgendaExport fetchByPrimaryKey(long agendaExportId);

	@Override
	public java.util.Map<java.io.Serializable, AgendaExport> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the agenda exports.
	*
	* @return the agenda exports
	*/
	public java.util.List<AgendaExport> findAll();

	/**
	* Returns a range of all the agenda exports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @return the range of agenda exports
	*/
	public java.util.List<AgendaExport> findAll(int start, int end);

	/**
	* Returns an ordered range of all the agenda exports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of agenda exports
	*/
	public java.util.List<AgendaExport> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator);

	/**
	* Returns an ordered range of all the agenda exports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of agenda exports
	*/
	public java.util.List<AgendaExport> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AgendaExport> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the agenda exports from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of agenda exports.
	*
	* @return the number of agenda exports
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}