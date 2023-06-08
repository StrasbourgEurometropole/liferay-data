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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImportReportLocalService}.
 *
 * @author BenjaminBini
 * @see ImportReportLocalService
 * @generated
 */
public class ImportReportLocalServiceWrapper
	implements ImportReportLocalService,
			   ServiceWrapper<ImportReportLocalService> {

	public ImportReportLocalServiceWrapper(
		ImportReportLocalService importReportLocalService) {

		_importReportLocalService = importReportLocalService;
	}

	/**
	 * Adds the import report to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImportReportLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param importReport the import report
	 * @return the import report that was added
	 */
	@Override
	public eu.strasbourg.service.agenda.model.ImportReport addImportReport(
		eu.strasbourg.service.agenda.model.ImportReport importReport) {

		return _importReportLocalService.addImportReport(importReport);
	}

	/**
	 * Crée une édition vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public eu.strasbourg.service.agenda.model.ImportReport createImportReport()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _importReportLocalService.createImportReport();
	}

	/**
	 * Creates a new import report with the primary key. Does not add the import report to the database.
	 *
	 * @param reportId the primary key for the new import report
	 * @return the new import report
	 */
	@Override
	public eu.strasbourg.service.agenda.model.ImportReport createImportReport(
		long reportId) {

		return _importReportLocalService.createImportReport(reportId);
	}

	/**
	 * Deletes the import report from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImportReportLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param importReport the import report
	 * @return the import report that was removed
	 */
	@Override
	public eu.strasbourg.service.agenda.model.ImportReport deleteImportReport(
		eu.strasbourg.service.agenda.model.ImportReport importReport) {

		return _importReportLocalService.deleteImportReport(importReport);
	}

	/**
	 * Deletes the import report with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImportReportLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param reportId the primary key of the import report
	 * @return the import report that was removed
	 * @throws PortalException if a import report with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.agenda.model.ImportReport deleteImportReport(
			long reportId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _importReportLocalService.deleteImportReport(reportId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _importReportLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _importReportLocalService.dynamicQuery();
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

		return _importReportLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.agenda.model.impl.ImportReportModelImpl</code>.
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

		return _importReportLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.agenda.model.impl.ImportReportModelImpl</code>.
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

		return _importReportLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

		return _importReportLocalService.dynamicQueryCount(dynamicQuery);
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

		return _importReportLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.agenda.model.ImportReport fetchImportReport(
		long reportId) {

		return _importReportLocalService.fetchImportReport(reportId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _importReportLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the import report with the primary key.
	 *
	 * @param reportId the primary key of the import report
	 * @return the import report
	 * @throws PortalException if a import report with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.agenda.model.ImportReport getImportReport(
			long reportId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _importReportLocalService.getImportReport(reportId);
	}

	/**
	 * Returns a range of all the import reports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.agenda.model.impl.ImportReportModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of import reports
	 * @param end the upper bound of the range of import reports (not inclusive)
	 * @return the range of import reports
	 */
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.ImportReport>
		getImportReports(int start, int end) {

		return _importReportLocalService.getImportReports(start, end);
	}

	/**
	 * Returns the number of import reports.
	 *
	 * @return the number of import reports
	 */
	@Override
	public int getImportReportsCount() {
		return _importReportLocalService.getImportReportsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _importReportLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _importReportLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _importReportLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine>
		getReportLines(eu.strasbourg.service.agenda.model.ImportReport report) {

		return _importReportLocalService.getReportLines(report);
	}

	/**
	 * On ne garde que les 10 rapports les plus récents
	 */
	@Override
	public void purgeReports() {
		_importReportLocalService.purgeReports();
	}

	/**
	 * Updates the import report in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ImportReportLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param importReport the import report
	 * @return the import report that was updated
	 */
	@Override
	public eu.strasbourg.service.agenda.model.ImportReport updateImportReport(
		eu.strasbourg.service.agenda.model.ImportReport importReport) {

		return _importReportLocalService.updateImportReport(importReport);
	}

	@Override
	public ImportReportLocalService getWrappedService() {
		return _importReportLocalService;
	}

	@Override
	public void setWrappedService(
		ImportReportLocalService importReportLocalService) {

		_importReportLocalService = importReportLocalService;
	}

	private ImportReportLocalService _importReportLocalService;

}