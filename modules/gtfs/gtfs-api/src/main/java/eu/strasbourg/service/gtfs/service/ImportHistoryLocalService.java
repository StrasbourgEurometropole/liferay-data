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

package eu.strasbourg.service.gtfs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.gtfs.model.ImportHistory;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ImportHistory. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Cedric Henry
 * @see ImportHistoryLocalServiceUtil
 * @see eu.strasbourg.service.gtfs.service.base.ImportHistoryLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.impl.ImportHistoryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ImportHistoryLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ImportHistoryLocalServiceUtil} to access the import history local service. Add custom service methods to {@link eu.strasbourg.service.gtfs.service.impl.ImportHistoryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Adds the import history to the database. Also notifies the appropriate model listeners.
	*
	* @param importHistory the import history
	* @return the import history that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ImportHistory addImportHistory(ImportHistory importHistory);

	/**
	* Creates a new import history with the primary key. Does not add the import history to the database.
	*
	* @param importHistoryId the primary key for the new import history
	* @return the new import history
	*/
	public ImportHistory createImportHistory(long importHistoryId);

	/**
	* Deletes the import history from the database. Also notifies the appropriate model listeners.
	*
	* @param importHistory the import history
	* @return the import history that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ImportHistory deleteImportHistory(ImportHistory importHistory);

	/**
	* Deletes the import history with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param importHistoryId the primary key of the import history
	* @return the import history that was removed
	* @throws PortalException if a import history with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ImportHistory deleteImportHistory(long importHistoryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ImportHistory fetchImportHistory(long importHistoryId);

	/**
	* Returns the import history matching the UUID and group.
	*
	* @param uuid the import history's UUID
	* @param groupId the primary key of the group
	* @return the matching import history, or <code>null</code> if a matching import history could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ImportHistory fetchImportHistoryByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the import history with the primary key.
	*
	* @param importHistoryId the primary key of the import history
	* @return the import history
	* @throws PortalException if a import history with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ImportHistory getImportHistory(long importHistoryId)
		throws PortalException;

	/**
	* Returns the import history matching the UUID and group.
	*
	* @param uuid the import history's UUID
	* @param groupId the primary key of the group
	* @return the matching import history
	* @throws PortalException if a matching import history could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ImportHistory getImportHistoryByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Updates the import history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param importHistory the import history
	* @return the import history that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ImportHistory updateImportHistory(ImportHistory importHistory);

	/**
	* Returns the number of import histories.
	*
	* @return the number of import histories
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getImportHistoriesCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.ImportHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.ImportHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns a range of all the import histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.ImportHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import histories
	* @param end the upper bound of the range of import histories (not inclusive)
	* @return the range of import histories
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ImportHistory> getImportHistories(int start, int end);

	/**
	* Returns all the import histories matching the UUID and company.
	*
	* @param uuid the UUID of the import histories
	* @param companyId the primary key of the company
	* @return the matching import histories, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ImportHistory> getImportHistoriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of import histories matching the UUID and company.
	*
	* @param uuid the UUID of the import histories
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of import histories
	* @param end the upper bound of the range of import histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching import histories, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ImportHistory> getImportHistoriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<ImportHistory> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);
}