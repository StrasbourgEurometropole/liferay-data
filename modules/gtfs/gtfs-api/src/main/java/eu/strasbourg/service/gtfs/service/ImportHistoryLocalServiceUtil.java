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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ImportHistory. This utility wraps
 * {@link eu.strasbourg.service.gtfs.service.impl.ImportHistoryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Cedric Henry
 * @see ImportHistoryLocalService
 * @see eu.strasbourg.service.gtfs.service.base.ImportHistoryLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.impl.ImportHistoryLocalServiceImpl
 * @generated
 */
@ProviderType
public class ImportHistoryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.gtfs.service.impl.ImportHistoryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the import history to the database. Also notifies the appropriate model listeners.
	*
	* @param importHistory the import history
	* @return the import history that was added
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistory addImportHistory(
		eu.strasbourg.service.gtfs.model.ImportHistory importHistory) {
		return getService().addImportHistory(importHistory);
	}

	/**
	* Creates a new import history with the primary key. Does not add the import history to the database.
	*
	* @param importHistoryId the primary key for the new import history
	* @return the new import history
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistory createImportHistory(
		long importHistoryId) {
		return getService().createImportHistory(importHistoryId);
	}

	/**
	* Deletes the import history from the database. Also notifies the appropriate model listeners.
	*
	* @param importHistory the import history
	* @return the import history that was removed
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistory deleteImportHistory(
		eu.strasbourg.service.gtfs.model.ImportHistory importHistory) {
		return getService().deleteImportHistory(importHistory);
	}

	/**
	* Deletes the import history with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param importHistoryId the primary key of the import history
	* @return the import history that was removed
	* @throws PortalException if a import history with the primary key could not be found
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistory deleteImportHistory(
		long importHistoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteImportHistory(importHistoryId);
	}

	public static eu.strasbourg.service.gtfs.model.ImportHistory fetchImportHistory(
		long importHistoryId) {
		return getService().fetchImportHistory(importHistoryId);
	}

	/**
	* Returns the import history matching the UUID and group.
	*
	* @param uuid the import history's UUID
	* @param groupId the primary key of the group
	* @return the matching import history, or <code>null</code> if a matching import history could not be found
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistory fetchImportHistoryByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchImportHistoryByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the import history with the primary key.
	*
	* @param importHistoryId the primary key of the import history
	* @return the import history
	* @throws PortalException if a import history with the primary key could not be found
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistory getImportHistory(
		long importHistoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getImportHistory(importHistoryId);
	}

	/**
	* Returns the import history matching the UUID and group.
	*
	* @param uuid the import history's UUID
	* @param groupId the primary key of the group
	* @return the matching import history
	* @throws PortalException if a matching import history could not be found
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistory getImportHistoryByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getImportHistoryByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the import history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param importHistory the import history
	* @return the import history that was updated
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistory updateImportHistory(
		eu.strasbourg.service.gtfs.model.ImportHistory importHistory) {
		return getService().updateImportHistory(importHistory);
	}

	/**
	* Returns the number of import histories.
	*
	* @return the number of import histories
	*/
	public static int getImportHistoriesCount() {
		return getService().getImportHistoriesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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
	public static java.util.List<eu.strasbourg.service.gtfs.model.ImportHistory> getImportHistories(
		int start, int end) {
		return getService().getImportHistories(start, end);
	}

	/**
	* Returns all the import histories matching the UUID and company.
	*
	* @param uuid the UUID of the import histories
	* @param companyId the primary key of the company
	* @return the matching import histories, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.gtfs.model.ImportHistory> getImportHistoriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getImportHistoriesByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<eu.strasbourg.service.gtfs.model.ImportHistory> getImportHistoriesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.gtfs.model.ImportHistory> orderByComparator) {
		return getService()
				   .getImportHistoriesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static ImportHistoryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ImportHistoryLocalService, ImportHistoryLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ImportHistoryLocalService.class);
}