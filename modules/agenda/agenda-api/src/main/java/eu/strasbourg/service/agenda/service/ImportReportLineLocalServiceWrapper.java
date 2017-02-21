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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImportReportLineLocalService}.
 *
 * @author BenjaminBini
 * @see ImportReportLineLocalService
 * @generated
 */
@ProviderType
public class ImportReportLineLocalServiceWrapper
	implements ImportReportLineLocalService,
		ServiceWrapper<ImportReportLineLocalService> {
	public ImportReportLineLocalServiceWrapper(
		ImportReportLineLocalService importReportLineLocalService) {
		_importReportLineLocalService = importReportLineLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _importReportLineLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _importReportLineLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _importReportLineLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _importReportLineLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _importReportLineLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the import report line to the database. Also notifies the appropriate model listeners.
	*
	* @param importReportLine the import report line
	* @return the import report line that was added
	*/
	@Override
	public eu.strasbourg.service.agenda.model.ImportReportLine addImportReportLine(
		eu.strasbourg.service.agenda.model.ImportReportLine importReportLine) {
		return _importReportLineLocalService.addImportReportLine(importReportLine);
	}

	/**
	* Crée une ligne de rapport d'import avec une PK, non ajouté à la base de
	* donnée
	*/
	@Override
	public eu.strasbourg.service.agenda.model.ImportReportLine createImportReportLine()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _importReportLineLocalService.createImportReportLine();
	}

	/**
	* Creates a new import report line with the primary key. Does not add the import report line to the database.
	*
	* @param lineId the primary key for the new import report line
	* @return the new import report line
	*/
	@Override
	public eu.strasbourg.service.agenda.model.ImportReportLine createImportReportLine(
		long lineId) {
		return _importReportLineLocalService.createImportReportLine(lineId);
	}

	/**
	* Deletes the import report line from the database. Also notifies the appropriate model listeners.
	*
	* @param importReportLine the import report line
	* @return the import report line that was removed
	*/
	@Override
	public eu.strasbourg.service.agenda.model.ImportReportLine deleteImportReportLine(
		eu.strasbourg.service.agenda.model.ImportReportLine importReportLine) {
		return _importReportLineLocalService.deleteImportReportLine(importReportLine);
	}

	/**
	* Deletes the import report line with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lineId the primary key of the import report line
	* @return the import report line that was removed
	* @throws PortalException if a import report line with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.ImportReportLine deleteImportReportLine(
		long lineId) throws com.liferay.portal.kernel.exception.PortalException {
		return _importReportLineLocalService.deleteImportReportLine(lineId);
	}

	@Override
	public eu.strasbourg.service.agenda.model.ImportReportLine fetchImportReportLine(
		long lineId) {
		return _importReportLineLocalService.fetchImportReportLine(lineId);
	}

	/**
	* Returns the import report line with the primary key.
	*
	* @param lineId the primary key of the import report line
	* @return the import report line
	* @throws PortalException if a import report line with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.ImportReportLine getImportReportLine(
		long lineId) throws com.liferay.portal.kernel.exception.PortalException {
		return _importReportLineLocalService.getImportReportLine(lineId);
	}

	/**
	* Updates the import report line in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param importReportLine the import report line
	* @return the import report line that was updated
	*/
	@Override
	public eu.strasbourg.service.agenda.model.ImportReportLine updateImportReportLine(
		eu.strasbourg.service.agenda.model.ImportReportLine importReportLine) {
		return _importReportLineLocalService.updateImportReportLine(importReportLine);
	}

	/**
	* Returns the number of import report lines.
	*
	* @return the number of import report lines
	*/
	@Override
	public int getImportReportLinesCount() {
		return _importReportLineLocalService.getImportReportLinesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _importReportLineLocalService.getOSGiServiceIdentifier();
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
		return _importReportLineLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _importReportLineLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _importReportLineLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.ImportReportLine> getImportReportLines(
		int start, int end) {
		return _importReportLineLocalService.getImportReportLines(start, end);
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
		return _importReportLineLocalService.dynamicQueryCount(dynamicQuery);
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
		return _importReportLineLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public ImportReportLineLocalService getWrappedService() {
		return _importReportLineLocalService;
	}

	@Override
	public void setWrappedService(
		ImportReportLineLocalService importReportLineLocalService) {
		_importReportLineLocalService = importReportLineLocalService;
	}

	private ImportReportLineLocalService _importReportLineLocalService;
}