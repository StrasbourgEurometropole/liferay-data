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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImportHistoryLocalService}.
 *
 * @author Cedric Henry
 * @see ImportHistoryLocalService
 * @generated
 */
@ProviderType
public class ImportHistoryLocalServiceWrapper
	implements ImportHistoryLocalService,
		ServiceWrapper<ImportHistoryLocalService> {
	public ImportHistoryLocalServiceWrapper(
		ImportHistoryLocalService importHistoryLocalService) {
		_importHistoryLocalService = importHistoryLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _importHistoryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _importHistoryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _importHistoryLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _importHistoryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _importHistoryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _importHistoryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the import history to the database. Also notifies the appropriate model listeners.
	*
	* @param importHistory the import history
	* @return the import history that was added
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.ImportHistory addImportHistory(
		eu.strasbourg.service.gtfs.model.ImportHistory importHistory) {
		return _importHistoryLocalService.addImportHistory(importHistory);
	}

	/**
	* Creates a new import history with the primary key. Does not add the import history to the database.
	*
	* @param importHistoryId the primary key for the new import history
	* @return the new import history
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.ImportHistory createImportHistory(
		long importHistoryId) {
		return _importHistoryLocalService.createImportHistory(importHistoryId);
	}

	/**
	* Deletes the import history from the database. Also notifies the appropriate model listeners.
	*
	* @param importHistory the import history
	* @return the import history that was removed
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.ImportHistory deleteImportHistory(
		eu.strasbourg.service.gtfs.model.ImportHistory importHistory) {
		return _importHistoryLocalService.deleteImportHistory(importHistory);
	}

	/**
	* Deletes the import history with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param importHistoryId the primary key of the import history
	* @return the import history that was removed
	* @throws PortalException if a import history with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.ImportHistory deleteImportHistory(
		long importHistoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _importHistoryLocalService.deleteImportHistory(importHistoryId);
	}

	@Override
	public eu.strasbourg.service.gtfs.model.ImportHistory fetchImportHistory(
		long importHistoryId) {
		return _importHistoryLocalService.fetchImportHistory(importHistoryId);
	}

	/**
	* Returns the import history matching the UUID and group.
	*
	* @param uuid the import history's UUID
	* @param groupId the primary key of the group
	* @return the matching import history, or <code>null</code> if a matching import history could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.ImportHistory fetchImportHistoryByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _importHistoryLocalService.fetchImportHistoryByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the import history with the primary key.
	*
	* @param importHistoryId the primary key of the import history
	* @return the import history
	* @throws PortalException if a import history with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.ImportHistory getImportHistory(
		long importHistoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _importHistoryLocalService.getImportHistory(importHistoryId);
	}

	/**
	* Returns the import history matching the UUID and group.
	*
	* @param uuid the import history's UUID
	* @param groupId the primary key of the group
	* @return the matching import history
	* @throws PortalException if a matching import history could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.ImportHistory getImportHistoryByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _importHistoryLocalService.getImportHistoryByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the import history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param importHistory the import history
	* @return the import history that was updated
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.ImportHistory updateImportHistory(
		eu.strasbourg.service.gtfs.model.ImportHistory importHistory) {
		return _importHistoryLocalService.updateImportHistory(importHistory);
	}

	/**
	* Returns the number of import histories.
	*
	* @return the number of import histories
	*/
	@Override
	public int getImportHistoriesCount() {
		return _importHistoryLocalService.getImportHistoriesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _importHistoryLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _importHistoryLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _importHistoryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _importHistoryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

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
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.ImportHistory> getImportHistories(
		int start, int end) {
		return _importHistoryLocalService.getImportHistories(start, end);
	}

	/**
	* Returns all the import histories matching the UUID and company.
	*
	* @param uuid the UUID of the import histories
	* @param companyId the primary key of the company
	* @return the matching import histories, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.ImportHistory> getImportHistoriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _importHistoryLocalService.getImportHistoriesByUuidAndCompanyId(uuid,
			companyId);
	}

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
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.ImportHistory> getImportHistoriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.gtfs.model.ImportHistory> orderByComparator) {
		return _importHistoryLocalService.getImportHistoriesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _importHistoryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _importHistoryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public ImportHistoryLocalService getWrappedService() {
		return _importHistoryLocalService;
	}

	@Override
	public void setWrappedService(
		ImportHistoryLocalService importHistoryLocalService) {
		_importHistoryLocalService = importHistoryLocalService;
	}

	private ImportHistoryLocalService _importHistoryLocalService;
}