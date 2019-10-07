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
 * Provides a wrapper for {@link AgendaExportPeriodLocalService}.
 *
 * @author BenjaminBini
 * @see AgendaExportPeriodLocalService
 * @generated
 */
@ProviderType
public class AgendaExportPeriodLocalServiceWrapper
	implements AgendaExportPeriodLocalService,
		ServiceWrapper<AgendaExportPeriodLocalService> {
	public AgendaExportPeriodLocalServiceWrapper(
		AgendaExportPeriodLocalService agendaExportPeriodLocalService) {
		_agendaExportPeriodLocalService = agendaExportPeriodLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _agendaExportPeriodLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _agendaExportPeriodLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _agendaExportPeriodLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _agendaExportPeriodLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _agendaExportPeriodLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the agenda export period to the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExportPeriod the agenda export period
	* @return the agenda export period that was added
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExportPeriod addAgendaExportPeriod(
		eu.strasbourg.service.agenda.model.AgendaExportPeriod agendaExportPeriod) {
		return _agendaExportPeriodLocalService.addAgendaExportPeriod(agendaExportPeriod);
	}

	/**
	* Créé un nouvel object AgendaExportPeriod, non ajoutée à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExportPeriod createAgendaExportPeriod()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _agendaExportPeriodLocalService.createAgendaExportPeriod();
	}

	/**
	* Creates a new agenda export period with the primary key. Does not add the agenda export period to the database.
	*
	* @param agendaExportPeriodId the primary key for the new agenda export period
	* @return the new agenda export period
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExportPeriod createAgendaExportPeriod(
		long agendaExportPeriodId) {
		return _agendaExportPeriodLocalService.createAgendaExportPeriod(agendaExportPeriodId);
	}

	/**
	* Deletes the agenda export period from the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExportPeriod the agenda export period
	* @return the agenda export period that was removed
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExportPeriod deleteAgendaExportPeriod(
		eu.strasbourg.service.agenda.model.AgendaExportPeriod agendaExportPeriod) {
		return _agendaExportPeriodLocalService.deleteAgendaExportPeriod(agendaExportPeriod);
	}

	/**
	* Deletes the agenda export period with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExportPeriodId the primary key of the agenda export period
	* @return the agenda export period that was removed
	* @throws PortalException if a agenda export period with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExportPeriod deleteAgendaExportPeriod(
		long agendaExportPeriodId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _agendaExportPeriodLocalService.deleteAgendaExportPeriod(agendaExportPeriodId);
	}

	@Override
	public eu.strasbourg.service.agenda.model.AgendaExportPeriod fetchAgendaExportPeriod(
		long agendaExportPeriodId) {
		return _agendaExportPeriodLocalService.fetchAgendaExportPeriod(agendaExportPeriodId);
	}

	/**
	* Returns the agenda export period with the primary key.
	*
	* @param agendaExportPeriodId the primary key of the agenda export period
	* @return the agenda export period
	* @throws PortalException if a agenda export period with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExportPeriod getAgendaExportPeriod(
		long agendaExportPeriodId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _agendaExportPeriodLocalService.getAgendaExportPeriod(agendaExportPeriodId);
	}

	/**
	* Updates the agenda export period in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param agendaExportPeriod the agenda export period
	* @return the agenda export period that was updated
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExportPeriod updateAgendaExportPeriod(
		eu.strasbourg.service.agenda.model.AgendaExportPeriod agendaExportPeriod) {
		return _agendaExportPeriodLocalService.updateAgendaExportPeriod(agendaExportPeriod);
	}

	/**
	* Returns the number of agenda export periods.
	*
	* @return the number of agenda export periods
	*/
	@Override
	public int getAgendaExportPeriodsCount() {
		return _agendaExportPeriodLocalService.getAgendaExportPeriodsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _agendaExportPeriodLocalService.getOSGiServiceIdentifier();
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
		return _agendaExportPeriodLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _agendaExportPeriodLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _agendaExportPeriodLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the agenda export periods.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of agenda export periods
	* @param end the upper bound of the range of agenda export periods (not inclusive)
	* @return the range of agenda export periods
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.AgendaExportPeriod> getAgendaExportPeriods(
		int start, int end) {
		return _agendaExportPeriodLocalService.getAgendaExportPeriods(start, end);
	}

	/**
	* Retourne les périodes d'un événement
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.AgendaExportPeriod> getByAgendaExportId(
		long eventId) {
		return _agendaExportPeriodLocalService.getByAgendaExportId(eventId);
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
		return _agendaExportPeriodLocalService.dynamicQueryCount(dynamicQuery);
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
		return _agendaExportPeriodLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public AgendaExportPeriodLocalService getWrappedService() {
		return _agendaExportPeriodLocalService;
	}

	@Override
	public void setWrappedService(
		AgendaExportPeriodLocalService agendaExportPeriodLocalService) {
		_agendaExportPeriodLocalService = agendaExportPeriodLocalService;
	}

	private AgendaExportPeriodLocalService _agendaExportPeriodLocalService;
}