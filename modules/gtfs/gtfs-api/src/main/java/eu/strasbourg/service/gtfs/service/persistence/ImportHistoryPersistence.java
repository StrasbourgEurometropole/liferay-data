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

package eu.strasbourg.service.gtfs.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.gtfs.exception.NoSuchImportHistoryException;
import eu.strasbourg.service.gtfs.model.ImportHistory;

/**
 * The persistence interface for the import history service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.gtfs.service.persistence.impl.ImportHistoryPersistenceImpl
 * @see ImportHistoryUtil
 * @generated
 */
@ProviderType
public interface ImportHistoryPersistence extends BasePersistence<ImportHistory> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ImportHistoryUtil} to access the import history persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the import histories where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching import histories
	*/
	public java.util.List<ImportHistory> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the import histories where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import histories
	* @param end the upper bound of the range of import histories (not inclusive)
	* @return the range of matching import histories
	*/
	public java.util.List<ImportHistory> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the import histories where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import histories
	* @param end the upper bound of the range of import histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import histories
	*/
	public java.util.List<ImportHistory> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator);

	/**
	* Returns an ordered range of all the import histories where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import histories
	* @param end the upper bound of the range of import histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import histories
	*/
	public java.util.List<ImportHistory> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first import history in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import history
	* @throws NoSuchImportHistoryException if a matching import history could not be found
	*/
	public ImportHistory findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator)
		throws NoSuchImportHistoryException;

	/**
	* Returns the first import history in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import history, or <code>null</code> if a matching import history could not be found
	*/
	public ImportHistory fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator);

	/**
	* Returns the last import history in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import history
	* @throws NoSuchImportHistoryException if a matching import history could not be found
	*/
	public ImportHistory findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator)
		throws NoSuchImportHistoryException;

	/**
	* Returns the last import history in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import history, or <code>null</code> if a matching import history could not be found
	*/
	public ImportHistory fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator);

	/**
	* Returns the import histories before and after the current import history in the ordered set where uuid = &#63;.
	*
	* @param importHistoryId the primary key of the current import history
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import history
	* @throws NoSuchImportHistoryException if a import history with the primary key could not be found
	*/
	public ImportHistory[] findByUuid_PrevAndNext(long importHistoryId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator)
		throws NoSuchImportHistoryException;

	/**
	* Removes all the import histories where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of import histories where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching import histories
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the import history where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchImportHistoryException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching import history
	* @throws NoSuchImportHistoryException if a matching import history could not be found
	*/
	public ImportHistory findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchImportHistoryException;

	/**
	* Returns the import history where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching import history, or <code>null</code> if a matching import history could not be found
	*/
	public ImportHistory fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the import history where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching import history, or <code>null</code> if a matching import history could not be found
	*/
	public ImportHistory fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the import history where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the import history that was removed
	*/
	public ImportHistory removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchImportHistoryException;

	/**
	* Returns the number of import histories where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching import histories
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the import histories where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching import histories
	*/
	public java.util.List<ImportHistory> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the import histories where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of import histories
	* @param end the upper bound of the range of import histories (not inclusive)
	* @return the range of matching import histories
	*/
	public java.util.List<ImportHistory> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the import histories where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of import histories
	* @param end the upper bound of the range of import histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import histories
	*/
	public java.util.List<ImportHistory> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator);

	/**
	* Returns an ordered range of all the import histories where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of import histories
	* @param end the upper bound of the range of import histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import histories
	*/
	public java.util.List<ImportHistory> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first import history in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import history
	* @throws NoSuchImportHistoryException if a matching import history could not be found
	*/
	public ImportHistory findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator)
		throws NoSuchImportHistoryException;

	/**
	* Returns the first import history in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import history, or <code>null</code> if a matching import history could not be found
	*/
	public ImportHistory fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator);

	/**
	* Returns the last import history in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import history
	* @throws NoSuchImportHistoryException if a matching import history could not be found
	*/
	public ImportHistory findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator)
		throws NoSuchImportHistoryException;

	/**
	* Returns the last import history in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import history, or <code>null</code> if a matching import history could not be found
	*/
	public ImportHistory fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator);

	/**
	* Returns the import histories before and after the current import history in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param importHistoryId the primary key of the current import history
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import history
	* @throws NoSuchImportHistoryException if a import history with the primary key could not be found
	*/
	public ImportHistory[] findByUuid_C_PrevAndNext(long importHistoryId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator)
		throws NoSuchImportHistoryException;

	/**
	* Removes all the import histories where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of import histories where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching import histories
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the import histories where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching import histories
	*/
	public java.util.List<ImportHistory> findByGroupId(long groupId);

	/**
	* Returns a range of all the import histories where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of import histories
	* @param end the upper bound of the range of import histories (not inclusive)
	* @return the range of matching import histories
	*/
	public java.util.List<ImportHistory> findByGroupId(long groupId, int start,
		int end);

	/**
	* Returns an ordered range of all the import histories where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of import histories
	* @param end the upper bound of the range of import histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import histories
	*/
	public java.util.List<ImportHistory> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator);

	/**
	* Returns an ordered range of all the import histories where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of import histories
	* @param end the upper bound of the range of import histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import histories
	*/
	public java.util.List<ImportHistory> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first import history in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import history
	* @throws NoSuchImportHistoryException if a matching import history could not be found
	*/
	public ImportHistory findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator)
		throws NoSuchImportHistoryException;

	/**
	* Returns the first import history in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import history, or <code>null</code> if a matching import history could not be found
	*/
	public ImportHistory fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator);

	/**
	* Returns the last import history in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import history
	* @throws NoSuchImportHistoryException if a matching import history could not be found
	*/
	public ImportHistory findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator)
		throws NoSuchImportHistoryException;

	/**
	* Returns the last import history in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import history, or <code>null</code> if a matching import history could not be found
	*/
	public ImportHistory fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator);

	/**
	* Returns the import histories before and after the current import history in the ordered set where groupId = &#63;.
	*
	* @param importHistoryId the primary key of the current import history
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import history
	* @throws NoSuchImportHistoryException if a import history with the primary key could not be found
	*/
	public ImportHistory[] findByGroupId_PrevAndNext(long importHistoryId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator)
		throws NoSuchImportHistoryException;

	/**
	* Removes all the import histories where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of import histories where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching import histories
	*/
	public int countByGroupId(long groupId);

	/**
	* Caches the import history in the entity cache if it is enabled.
	*
	* @param importHistory the import history
	*/
	public void cacheResult(ImportHistory importHistory);

	/**
	* Caches the import histories in the entity cache if it is enabled.
	*
	* @param importHistories the import histories
	*/
	public void cacheResult(java.util.List<ImportHistory> importHistories);

	/**
	* Creates a new import history with the primary key. Does not add the import history to the database.
	*
	* @param importHistoryId the primary key for the new import history
	* @return the new import history
	*/
	public ImportHistory create(long importHistoryId);

	/**
	* Removes the import history with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param importHistoryId the primary key of the import history
	* @return the import history that was removed
	* @throws NoSuchImportHistoryException if a import history with the primary key could not be found
	*/
	public ImportHistory remove(long importHistoryId)
		throws NoSuchImportHistoryException;

	public ImportHistory updateImpl(ImportHistory importHistory);

	/**
	* Returns the import history with the primary key or throws a {@link NoSuchImportHistoryException} if it could not be found.
	*
	* @param importHistoryId the primary key of the import history
	* @return the import history
	* @throws NoSuchImportHistoryException if a import history with the primary key could not be found
	*/
	public ImportHistory findByPrimaryKey(long importHistoryId)
		throws NoSuchImportHistoryException;

	/**
	* Returns the import history with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param importHistoryId the primary key of the import history
	* @return the import history, or <code>null</code> if a import history with the primary key could not be found
	*/
	public ImportHistory fetchByPrimaryKey(long importHistoryId);

	@Override
	public java.util.Map<java.io.Serializable, ImportHistory> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the import histories.
	*
	* @return the import histories
	*/
	public java.util.List<ImportHistory> findAll();

	/**
	* Returns a range of all the import histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import histories
	* @param end the upper bound of the range of import histories (not inclusive)
	* @return the range of import histories
	*/
	public java.util.List<ImportHistory> findAll(int start, int end);

	/**
	* Returns an ordered range of all the import histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import histories
	* @param end the upper bound of the range of import histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of import histories
	*/
	public java.util.List<ImportHistory> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator);

	/**
	* Returns an ordered range of all the import histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import histories
	* @param end the upper bound of the range of import histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of import histories
	*/
	public java.util.List<ImportHistory> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistory> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the import histories from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of import histories.
	*
	* @return the number of import histories
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}