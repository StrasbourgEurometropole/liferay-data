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

package eu.strasbourg.service.agenda.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ImportReportLine. This utility wraps
 * {@link eu.strasbourg.service.agenda.service.impl.ImportReportLineLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author BenjaminBini
 * @see ImportReportLineLocalService
 * @see eu.strasbourg.service.agenda.service.base.ImportReportLineLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.impl.ImportReportLineLocalServiceImpl
 * @generated
 */
@ProviderType
public class ImportReportLineLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.agenda.service.impl.ImportReportLineLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
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
	* Adds the import report line to the database. Also notifies the appropriate model listeners.
	*
	* @param importReportLine the import report line
	* @return the import report line that was added
	*/
	public static eu.strasbourg.service.agenda.model.ImportReportLine addImportReportLine(
		eu.strasbourg.service.agenda.model.ImportReportLine importReportLine) {
		return getService().addImportReportLine(importReportLine);
	}

	/**
	* Crée une ligne de rapport d'import avec une PK, non ajouté à la base de
	* donnée
	*/
	public static eu.strasbourg.service.agenda.model.ImportReportLine createImportReportLine()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createImportReportLine();
	}

	/**
	* Creates a new import report line with the primary key. Does not add the import report line to the database.
	*
	* @param lineId the primary key for the new import report line
	* @return the new import report line
	*/
	public static eu.strasbourg.service.agenda.model.ImportReportLine createImportReportLine(
		long lineId) {
		return getService().createImportReportLine(lineId);
	}

	/**
	* Deletes the import report line from the database. Also notifies the appropriate model listeners.
	*
	* @param importReportLine the import report line
	* @return the import report line that was removed
	*/
	public static eu.strasbourg.service.agenda.model.ImportReportLine deleteImportReportLine(
		eu.strasbourg.service.agenda.model.ImportReportLine importReportLine) {
		return getService().deleteImportReportLine(importReportLine);
	}

	/**
	* Deletes the import report line with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lineId the primary key of the import report line
	* @return the import report line that was removed
	* @throws PortalException if a import report line with the primary key could not be found
	*/
	public static eu.strasbourg.service.agenda.model.ImportReportLine deleteImportReportLine(
		long lineId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteImportReportLine(lineId);
	}

	public static eu.strasbourg.service.agenda.model.ImportReportLine fetchImportReportLine(
		long lineId) {
		return getService().fetchImportReportLine(lineId);
	}

	/**
	* Returns the import report line with the primary key.
	*
	* @param lineId the primary key of the import report line
	* @return the import report line
	* @throws PortalException if a import report line with the primary key could not be found
	*/
	public static eu.strasbourg.service.agenda.model.ImportReportLine getImportReportLine(
		long lineId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getImportReportLine(lineId);
	}

	/**
	* Updates the import report line in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param importReportLine the import report line
	* @return the import report line that was updated
	*/
	public static eu.strasbourg.service.agenda.model.ImportReportLine updateImportReportLine(
		eu.strasbourg.service.agenda.model.ImportReportLine importReportLine) {
		return getService().updateImportReportLine(importReportLine);
	}

	/**
	* Returns the number of import report lines.
	*
	* @return the number of import report lines
	*/
	public static int getImportReportLinesCount() {
		return getService().getImportReportLinesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the import report lines.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.ImportReportLineModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import report lines
	* @param end the upper bound of the range of import report lines (not inclusive)
	* @return the range of import report lines
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine> getImportReportLines(
		int start, int end) {
		return getService().getImportReportLines(start, end);
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

	public static ImportReportLineLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ImportReportLineLocalService, ImportReportLineLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ImportReportLineLocalService.class);
}