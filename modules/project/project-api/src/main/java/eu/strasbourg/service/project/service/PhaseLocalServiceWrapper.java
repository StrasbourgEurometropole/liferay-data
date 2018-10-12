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

package eu.strasbourg.service.project.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PhaseLocalService}.
 *
 * @author Cedric Henry
 * @see PhaseLocalService
 * @generated
 */
@ProviderType
public class PhaseLocalServiceWrapper implements PhaseLocalService,
	ServiceWrapper<PhaseLocalService> {
	public PhaseLocalServiceWrapper(PhaseLocalService phaseLocalService) {
		_phaseLocalService = phaseLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _phaseLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _phaseLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _phaseLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _phaseLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phaseLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phaseLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the phase to the database. Also notifies the appropriate model listeners.
	*
	* @param phase the phase
	* @return the phase that was added
	*/
	@Override
	public eu.strasbourg.service.project.model.Phase addPhase(
		eu.strasbourg.service.project.model.Phase phase) {
		return _phaseLocalService.addPhase(phase);
	}

	/**
	* Creates a new phase with the primary key. Does not add the phase to the database.
	*
	* @param PhaseId the primary key for the new phase
	* @return the new phase
	*/
	@Override
	public eu.strasbourg.service.project.model.Phase createPhase(long PhaseId) {
		return _phaseLocalService.createPhase(PhaseId);
	}

	/**
	* Deletes the phase from the database. Also notifies the appropriate model listeners.
	*
	* @param phase the phase
	* @return the phase that was removed
	*/
	@Override
	public eu.strasbourg.service.project.model.Phase deletePhase(
		eu.strasbourg.service.project.model.Phase phase) {
		return _phaseLocalService.deletePhase(phase);
	}

	/**
	* Deletes the phase with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param PhaseId the primary key of the phase
	* @return the phase that was removed
	* @throws PortalException if a phase with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.Phase deletePhase(long PhaseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phaseLocalService.deletePhase(PhaseId);
	}

	@Override
	public eu.strasbourg.service.project.model.Phase fetchPhase(long PhaseId) {
		return _phaseLocalService.fetchPhase(PhaseId);
	}

	/**
	* Returns the phase matching the UUID and group.
	*
	* @param uuid the phase's UUID
	* @param groupId the primary key of the group
	* @return the matching phase, or <code>null</code> if a matching phase could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.Phase fetchPhaseByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _phaseLocalService.fetchPhaseByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the phase with the primary key.
	*
	* @param PhaseId the primary key of the phase
	* @return the phase
	* @throws PortalException if a phase with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.Phase getPhase(long PhaseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phaseLocalService.getPhase(PhaseId);
	}

	/**
	* Returns the phase matching the UUID and group.
	*
	* @param uuid the phase's UUID
	* @param groupId the primary key of the group
	* @return the matching phase
	* @throws PortalException if a matching phase could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.Phase getPhaseByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _phaseLocalService.getPhaseByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the phase in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param phase the phase
	* @return the phase that was updated
	*/
	@Override
	public eu.strasbourg.service.project.model.Phase updatePhase(
		eu.strasbourg.service.project.model.Phase phase) {
		return _phaseLocalService.updatePhase(phase);
	}

	/**
	* Returns the number of phases.
	*
	* @return the number of phases
	*/
	@Override
	public int getPhasesCount() {
		return _phaseLocalService.getPhasesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _phaseLocalService.getOSGiServiceIdentifier();
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
		return _phaseLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _phaseLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _phaseLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the phases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @return the range of phases
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Phase> getPhases(
		int start, int end) {
		return _phaseLocalService.getPhases(start, end);
	}

	/**
	* Returns all the phases matching the UUID and company.
	*
	* @param uuid the UUID of the phases
	* @param companyId the primary key of the company
	* @return the matching phases, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Phase> getPhasesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _phaseLocalService.getPhasesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of phases matching the UUID and company.
	*
	* @param uuid the UUID of the phases
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of phases
	* @param end the upper bound of the range of phases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching phases, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Phase> getPhasesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.project.model.Phase> orderByComparator) {
		return _phaseLocalService.getPhasesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
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
		return _phaseLocalService.dynamicQueryCount(dynamicQuery);
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
		return _phaseLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public PhaseLocalService getWrappedService() {
		return _phaseLocalService;
	}

	@Override
	public void setWrappedService(PhaseLocalService phaseLocalService) {
		_phaseLocalService = phaseLocalService;
	}

	private PhaseLocalService _phaseLocalService;
}