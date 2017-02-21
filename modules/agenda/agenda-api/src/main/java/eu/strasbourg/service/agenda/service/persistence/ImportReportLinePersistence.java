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

import eu.strasbourg.service.agenda.exception.NoSuchImportReportLineException;
import eu.strasbourg.service.agenda.model.ImportReportLine;

/**
 * The persistence interface for the import report line service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.agenda.service.persistence.impl.ImportReportLinePersistenceImpl
 * @see ImportReportLineUtil
 * @generated
 */
@ProviderType
public interface ImportReportLinePersistence extends BasePersistence<ImportReportLine> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ImportReportLineUtil} to access the import report line persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the import report lines where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching import report lines
	*/
	public java.util.List<ImportReportLine> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the import report lines where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @return the range of matching import report lines
	*/
	public java.util.List<ImportReportLine> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the import report lines where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import report lines
	*/
	public java.util.List<ImportReportLine> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator);

	/**
	* Returns an ordered range of all the import report lines where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import report lines
	*/
	public java.util.List<ImportReportLine> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first import report line in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import report line
	* @throws NoSuchImportReportLineException if a matching import report line could not be found
	*/
	public ImportReportLine findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator)
		throws NoSuchImportReportLineException;

	/**
	* Returns the first import report line in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import report line, or <code>null</code> if a matching import report line could not be found
	*/
	public ImportReportLine fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator);

	/**
	* Returns the last import report line in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import report line
	* @throws NoSuchImportReportLineException if a matching import report line could not be found
	*/
	public ImportReportLine findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator)
		throws NoSuchImportReportLineException;

	/**
	* Returns the last import report line in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import report line, or <code>null</code> if a matching import report line could not be found
	*/
	public ImportReportLine fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator);

	/**
	* Returns the import report lines before and after the current import report line in the ordered set where uuid = &#63;.
	*
	* @param lineId the primary key of the current import report line
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import report line
	* @throws NoSuchImportReportLineException if a import report line with the primary key could not be found
	*/
	public ImportReportLine[] findByUuid_PrevAndNext(long lineId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator)
		throws NoSuchImportReportLineException;

	/**
	* Removes all the import report lines where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of import report lines where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching import report lines
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns all the import report lines where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @return the matching import report lines
	*/
	public java.util.List<ImportReportLine> findByTypeAndStatus(
		java.lang.String type, long status);

	/**
	* Returns a range of all the import report lines where type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @return the range of matching import report lines
	*/
	public java.util.List<ImportReportLine> findByTypeAndStatus(
		java.lang.String type, long status, int start, int end);

	/**
	* Returns an ordered range of all the import report lines where type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import report lines
	*/
	public java.util.List<ImportReportLine> findByTypeAndStatus(
		java.lang.String type, long status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator);

	/**
	* Returns an ordered range of all the import report lines where type = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import report lines
	*/
	public java.util.List<ImportReportLine> findByTypeAndStatus(
		java.lang.String type, long status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first import report line in the ordered set where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import report line
	* @throws NoSuchImportReportLineException if a matching import report line could not be found
	*/
	public ImportReportLine findByTypeAndStatus_First(java.lang.String type,
		long status,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator)
		throws NoSuchImportReportLineException;

	/**
	* Returns the first import report line in the ordered set where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import report line, or <code>null</code> if a matching import report line could not be found
	*/
	public ImportReportLine fetchByTypeAndStatus_First(java.lang.String type,
		long status,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator);

	/**
	* Returns the last import report line in the ordered set where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import report line
	* @throws NoSuchImportReportLineException if a matching import report line could not be found
	*/
	public ImportReportLine findByTypeAndStatus_Last(java.lang.String type,
		long status,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator)
		throws NoSuchImportReportLineException;

	/**
	* Returns the last import report line in the ordered set where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import report line, or <code>null</code> if a matching import report line could not be found
	*/
	public ImportReportLine fetchByTypeAndStatus_Last(java.lang.String type,
		long status,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator);

	/**
	* Returns the import report lines before and after the current import report line in the ordered set where type = &#63; and status = &#63;.
	*
	* @param lineId the primary key of the current import report line
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import report line
	* @throws NoSuchImportReportLineException if a import report line with the primary key could not be found
	*/
	public ImportReportLine[] findByTypeAndStatus_PrevAndNext(long lineId,
		java.lang.String type, long status,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator)
		throws NoSuchImportReportLineException;

	/**
	* Removes all the import report lines where type = &#63; and status = &#63; from the database.
	*
	* @param type the type
	* @param status the status
	*/
	public void removeByTypeAndStatus(java.lang.String type, long status);

	/**
	* Returns the number of import report lines where type = &#63; and status = &#63;.
	*
	* @param type the type
	* @param status the status
	* @return the number of matching import report lines
	*/
	public int countByTypeAndStatus(java.lang.String type, long status);

	/**
	* Returns all the import report lines where reportId = &#63;.
	*
	* @param reportId the report ID
	* @return the matching import report lines
	*/
	public java.util.List<ImportReportLine> findByReportId(long reportId);

	/**
	* Returns a range of all the import report lines where reportId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param reportId the report ID
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @return the range of matching import report lines
	*/
	public java.util.List<ImportReportLine> findByReportId(long reportId,
		int start, int end);

	/**
	* Returns an ordered range of all the import report lines where reportId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param reportId the report ID
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching import report lines
	*/
	public java.util.List<ImportReportLine> findByReportId(long reportId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator);

	/**
	* Returns an ordered range of all the import report lines where reportId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param reportId the report ID
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching import report lines
	*/
	public java.util.List<ImportReportLine> findByReportId(long reportId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first import report line in the ordered set where reportId = &#63;.
	*
	* @param reportId the report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import report line
	* @throws NoSuchImportReportLineException if a matching import report line could not be found
	*/
	public ImportReportLine findByReportId_First(long reportId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator)
		throws NoSuchImportReportLineException;

	/**
	* Returns the first import report line in the ordered set where reportId = &#63;.
	*
	* @param reportId the report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching import report line, or <code>null</code> if a matching import report line could not be found
	*/
	public ImportReportLine fetchByReportId_First(long reportId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator);

	/**
	* Returns the last import report line in the ordered set where reportId = &#63;.
	*
	* @param reportId the report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import report line
	* @throws NoSuchImportReportLineException if a matching import report line could not be found
	*/
	public ImportReportLine findByReportId_Last(long reportId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator)
		throws NoSuchImportReportLineException;

	/**
	* Returns the last import report line in the ordered set where reportId = &#63;.
	*
	* @param reportId the report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching import report line, or <code>null</code> if a matching import report line could not be found
	*/
	public ImportReportLine fetchByReportId_Last(long reportId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator);

	/**
	* Returns the import report lines before and after the current import report line in the ordered set where reportId = &#63;.
	*
	* @param lineId the primary key of the current import report line
	* @param reportId the report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next import report line
	* @throws NoSuchImportReportLineException if a import report line with the primary key could not be found
	*/
	public ImportReportLine[] findByReportId_PrevAndNext(long lineId,
		long reportId,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator)
		throws NoSuchImportReportLineException;

	/**
	* Removes all the import report lines where reportId = &#63; from the database.
	*
	* @param reportId the report ID
	*/
	public void removeByReportId(long reportId);

	/**
	* Returns the number of import report lines where reportId = &#63;.
	*
	* @param reportId the report ID
	* @return the number of matching import report lines
	*/
	public int countByReportId(long reportId);

	/**
	* Caches the import report line in the entity cache if it is enabled.
	*
	* @param importReportLine the import report line
	*/
	public void cacheResult(ImportReportLine importReportLine);

	/**
	* Caches the import report lines in the entity cache if it is enabled.
	*
	* @param importReportLines the import report lines
	*/
	public void cacheResult(java.util.List<ImportReportLine> importReportLines);

	/**
	* Creates a new import report line with the primary key. Does not add the import report line to the database.
	*
	* @param lineId the primary key for the new import report line
	* @return the new import report line
	*/
	public ImportReportLine create(long lineId);

	/**
	* Removes the import report line with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lineId the primary key of the import report line
	* @return the import report line that was removed
	* @throws NoSuchImportReportLineException if a import report line with the primary key could not be found
	*/
	public ImportReportLine remove(long lineId)
		throws NoSuchImportReportLineException;

	public ImportReportLine updateImpl(ImportReportLine importReportLine);

	/**
	* Returns the import report line with the primary key or throws a {@link NoSuchImportReportLineException} if it could not be found.
	*
	* @param lineId the primary key of the import report line
	* @return the import report line
	* @throws NoSuchImportReportLineException if a import report line with the primary key could not be found
	*/
	public ImportReportLine findByPrimaryKey(long lineId)
		throws NoSuchImportReportLineException;

	/**
	* Returns the import report line with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param lineId the primary key of the import report line
	* @return the import report line, or <code>null</code> if a import report line with the primary key could not be found
	*/
	public ImportReportLine fetchByPrimaryKey(long lineId);

	@Override
	public java.util.Map<java.io.Serializable, ImportReportLine> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the import report lines.
	*
	* @return the import report lines
	*/
	public java.util.List<ImportReportLine> findAll();

	/**
	* Returns a range of all the import report lines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @return the range of import report lines
	*/
	public java.util.List<ImportReportLine> findAll(int start, int end);

	/**
	* Returns an ordered range of all the import report lines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of import report lines
	*/
	public java.util.List<ImportReportLine> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator);

	/**
	* Returns an ordered range of all the import report lines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of import report lines
	*/
	public java.util.List<ImportReportLine> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ImportReportLine> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the import report lines from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of import report lines.
	*
	* @return the number of import report lines
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}