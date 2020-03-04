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

import eu.strasbourg.service.gtfs.exception.NoSuchImportHistoricException;
import eu.strasbourg.service.gtfs.model.ImportHistoric;

/**
 * The persistence interface for the import historic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Cedric Henry
 * @see eu.strasbourg.service.gtfs.service.persistence.impl.ImportHistoricPersistenceImpl
 * @see ImportHistoricUtil
 * @generated
 */
@ProviderType
public interface ImportHistoricPersistence extends BasePersistence<ImportHistoric> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ImportHistoricUtil} to access the import historic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the import historics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching import historics
	*/
	public java.util.List<ImportHistoric> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the import historics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import historics
	* @param end the upper bound of the range of import historics (not inclusive)
	* @return the range of matching import historics
	*/
	public java.util.List<ImportHistoric> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the import historics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import historics
	* @param end the upper bound of the range of import historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import historics
	*/
	public java.util.List<ImportHistoric> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator);

	/**
	* Returns an ordered range of all the import historics where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import historics
	* @param end the upper bound of the range of import historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import historics
	*/
	public java.util.List<ImportHistoric> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first import historic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import historic
	* @throws NoSuchImportHistoricException if a matching import historic could not be found
	*/
	public ImportHistoric findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator)
		throws NoSuchImportHistoricException;

	/**
	* Returns the first import historic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import historic, or <code>null</code> if a matching import historic could not be found
	*/
	public ImportHistoric fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator);

	/**
	* Returns the last import historic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import historic
	* @throws NoSuchImportHistoricException if a matching import historic could not be found
	*/
	public ImportHistoric findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator)
		throws NoSuchImportHistoricException;

	/**
	* Returns the last import historic in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import historic, or <code>null</code> if a matching import historic could not be found
	*/
	public ImportHistoric fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator);

	/**
	* Returns the import historics before and after the current import historic in the ordered set where uuid = &#63;.
	*
	* @param importHistoricId the primary key of the current import historic
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import historic
	* @throws NoSuchImportHistoricException if a import historic with the primary key could not be found
	*/
	public ImportHistoric[] findByUuid_PrevAndNext(long importHistoricId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator)
		throws NoSuchImportHistoricException;

	/**
	* Removes all the import historics where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of import historics where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching import historics
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the import historic where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchImportHistoricException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching import historic
	* @throws NoSuchImportHistoricException if a matching import historic could not be found
	*/
	public ImportHistoric findByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchImportHistoricException;

	/**
	* Returns the import historic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching import historic, or <code>null</code> if a matching import historic could not be found
	*/
	public ImportHistoric fetchByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns the import historic where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching import historic, or <code>null</code> if a matching import historic could not be found
	*/
	public ImportHistoric fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the import historic where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the import historic that was removed
	*/
	public ImportHistoric removeByUUID_G(java.lang.String uuid, long groupId)
		throws NoSuchImportHistoricException;

	/**
	* Returns the number of import historics where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching import historics
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId);

	/**
	* Returns all the import historics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching import historics
	*/
	public java.util.List<ImportHistoric> findByUuid_C(java.lang.String uuid,
		long companyId);

	/**
	* Returns a range of all the import historics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of import historics
	* @param end the upper bound of the range of import historics (not inclusive)
	* @return the range of matching import historics
	*/
	public java.util.List<ImportHistoric> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the import historics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of import historics
	* @param end the upper bound of the range of import historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import historics
	*/
	public java.util.List<ImportHistoric> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator);

	/**
	* Returns an ordered range of all the import historics where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of import historics
	* @param end the upper bound of the range of import historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import historics
	*/
	public java.util.List<ImportHistoric> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first import historic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import historic
	* @throws NoSuchImportHistoricException if a matching import historic could not be found
	*/
	public ImportHistoric findByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator)
		throws NoSuchImportHistoricException;

	/**
	* Returns the first import historic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import historic, or <code>null</code> if a matching import historic could not be found
	*/
	public ImportHistoric fetchByUuid_C_First(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator);

	/**
	* Returns the last import historic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import historic
	* @throws NoSuchImportHistoricException if a matching import historic could not be found
	*/
	public ImportHistoric findByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator)
		throws NoSuchImportHistoricException;

	/**
	* Returns the last import historic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import historic, or <code>null</code> if a matching import historic could not be found
	*/
	public ImportHistoric fetchByUuid_C_Last(java.lang.String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator);

	/**
	* Returns the import historics before and after the current import historic in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param importHistoricId the primary key of the current import historic
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import historic
	* @throws NoSuchImportHistoricException if a import historic with the primary key could not be found
	*/
	public ImportHistoric[] findByUuid_C_PrevAndNext(long importHistoricId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator)
		throws NoSuchImportHistoricException;

	/**
	* Removes all the import historics where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of import historics where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching import historics
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns all the import historics where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching import historics
	*/
	public java.util.List<ImportHistoric> findByGroupId(long groupId);

	/**
	* Returns a range of all the import historics where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of import historics
	* @param end the upper bound of the range of import historics (not inclusive)
	* @return the range of matching import historics
	*/
	public java.util.List<ImportHistoric> findByGroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the import historics where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of import historics
	* @param end the upper bound of the range of import historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import historics
	*/
	public java.util.List<ImportHistoric> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator);

	/**
	* Returns an ordered range of all the import historics where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of import historics
	* @param end the upper bound of the range of import historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import historics
	*/
	public java.util.List<ImportHistoric> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first import historic in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import historic
	* @throws NoSuchImportHistoricException if a matching import historic could not be found
	*/
	public ImportHistoric findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator)
		throws NoSuchImportHistoricException;

	/**
	* Returns the first import historic in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import historic, or <code>null</code> if a matching import historic could not be found
	*/
	public ImportHistoric fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator);

	/**
	* Returns the last import historic in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import historic
	* @throws NoSuchImportHistoricException if a matching import historic could not be found
	*/
	public ImportHistoric findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator)
		throws NoSuchImportHistoricException;

	/**
	* Returns the last import historic in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import historic, or <code>null</code> if a matching import historic could not be found
	*/
	public ImportHistoric fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator);

	/**
	* Returns the import historics before and after the current import historic in the ordered set where groupId = &#63;.
	*
	* @param importHistoricId the primary key of the current import historic
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import historic
	* @throws NoSuchImportHistoricException if a import historic with the primary key could not be found
	*/
	public ImportHistoric[] findByGroupId_PrevAndNext(long importHistoricId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator)
		throws NoSuchImportHistoricException;

	/**
	* Removes all the import historics where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of import historics where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching import historics
	*/
	public int countByGroupId(long groupId);

	/**
	* Caches the import historic in the entity cache if it is enabled.
	*
	* @param importHistoric the import historic
	*/
	public void cacheResult(ImportHistoric importHistoric);

	/**
	* Caches the import historics in the entity cache if it is enabled.
	*
	* @param importHistorics the import historics
	*/
	public void cacheResult(java.util.List<ImportHistoric> importHistorics);

	/**
	* Creates a new import historic with the primary key. Does not add the import historic to the database.
	*
	* @param importHistoricId the primary key for the new import historic
	* @return the new import historic
	*/
	public ImportHistoric create(long importHistoricId);

	/**
	* Removes the import historic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param importHistoricId the primary key of the import historic
	* @return the import historic that was removed
	* @throws NoSuchImportHistoricException if a import historic with the primary key could not be found
	*/
	public ImportHistoric remove(long importHistoricId)
		throws NoSuchImportHistoricException;

	public ImportHistoric updateImpl(ImportHistoric importHistoric);

	/**
	* Returns the import historic with the primary key or throws a {@link NoSuchImportHistoricException} if it could not be found.
	*
	* @param importHistoricId the primary key of the import historic
	* @return the import historic
	* @throws NoSuchImportHistoricException if a import historic with the primary key could not be found
	*/
	public ImportHistoric findByPrimaryKey(long importHistoricId)
		throws NoSuchImportHistoricException;

	/**
	* Returns the import historic with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param importHistoricId the primary key of the import historic
	* @return the import historic, or <code>null</code> if a import historic with the primary key could not be found
	*/
	public ImportHistoric fetchByPrimaryKey(long importHistoricId);

	@Override
	public java.util.Map<java.io.Serializable, ImportHistoric> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the import historics.
	*
	* @return the import historics
	*/
	public java.util.List<ImportHistoric> findAll();

	/**
	* Returns a range of all the import historics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import historics
	* @param end the upper bound of the range of import historics (not inclusive)
	* @return the range of import historics
	*/
	public java.util.List<ImportHistoric> findAll(int start, int end);

	/**
	* Returns an ordered range of all the import historics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import historics
	* @param end the upper bound of the range of import historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of import historics
	*/
	public java.util.List<ImportHistoric> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator);

	/**
	* Returns an ordered range of all the import historics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import historics
	* @param end the upper bound of the range of import historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of import historics
	*/
	public java.util.List<ImportHistoric> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportHistoric> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the import historics from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of import historics.
	*
	* @return the number of import historics
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}