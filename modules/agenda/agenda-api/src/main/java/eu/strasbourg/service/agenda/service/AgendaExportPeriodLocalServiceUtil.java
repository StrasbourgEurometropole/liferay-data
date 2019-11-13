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
 * Provides the local service utility for AgendaExportPeriod. This utility wraps
 * {@link eu.strasbourg.service.agenda.service.impl.AgendaExportPeriodLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author BenjaminBini
 * @see AgendaExportPeriodLocalService
 * @see eu.strasbourg.service.agenda.service.base.AgendaExportPeriodLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.impl.AgendaExportPeriodLocalServiceImpl
 * @generated
 */
@ProviderType
public class AgendaExportPeriodLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.agenda.service.impl.AgendaExportPeriodLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the agenda export period to the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExportPeriod the agenda export period
	* @return the agenda export period that was added
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExportPeriod addAgendaExportPeriod(
		eu.strasbourg.service.agenda.model.AgendaExportPeriod agendaExportPeriod) {
		return getService().addAgendaExportPeriod(agendaExportPeriod);
	}

	/**
	* Créé un nouvel object AgendaExportPeriod, non ajoutée à la base de donnée
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExportPeriod createAgendaExportPeriod()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createAgendaExportPeriod();
	}

	/**
	* Creates a new agenda export period with the primary key. Does not add the agenda export period to the database.
	*
	* @param agendaExportPeriodId the primary key for the new agenda export period
	* @return the new agenda export period
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExportPeriod createAgendaExportPeriod(
		long agendaExportPeriodId) {
		return getService().createAgendaExportPeriod(agendaExportPeriodId);
	}

	/**
	* Deletes the agenda export period from the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExportPeriod the agenda export period
	* @return the agenda export period that was removed
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExportPeriod deleteAgendaExportPeriod(
		eu.strasbourg.service.agenda.model.AgendaExportPeriod agendaExportPeriod) {
		return getService().deleteAgendaExportPeriod(agendaExportPeriod);
	}

	/**
	* Deletes the agenda export period with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExportPeriodId the primary key of the agenda export period
	* @return the agenda export period that was removed
	* @throws PortalException if a agenda export period with the primary key could not be found
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExportPeriod deleteAgendaExportPeriod(
		long agendaExportPeriodId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAgendaExportPeriod(agendaExportPeriodId);
	}

	public static eu.strasbourg.service.agenda.model.AgendaExportPeriod fetchAgendaExportPeriod(
		long agendaExportPeriodId) {
		return getService().fetchAgendaExportPeriod(agendaExportPeriodId);
	}

	/**
	* Returns the agenda export period with the primary key.
	*
	* @param agendaExportPeriodId the primary key of the agenda export period
	* @return the agenda export period
	* @throws PortalException if a agenda export period with the primary key could not be found
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExportPeriod getAgendaExportPeriod(
		long agendaExportPeriodId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAgendaExportPeriod(agendaExportPeriodId);
	}

	/**
	* Updates the agenda export period in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param agendaExportPeriod the agenda export period
	* @return the agenda export period that was updated
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExportPeriod updateAgendaExportPeriod(
		eu.strasbourg.service.agenda.model.AgendaExportPeriod agendaExportPeriod) {
		return getService().updateAgendaExportPeriod(agendaExportPeriod);
	}

	/**
	* Returns the number of agenda export periods.
	*
	* @return the number of agenda export periods
	*/
	public static int getAgendaExportPeriodsCount() {
		return getService().getAgendaExportPeriodsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.AgendaExportPeriodModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static java.util.List<eu.strasbourg.service.agenda.model.AgendaExportPeriod> getAgendaExportPeriods(
		int start, int end) {
		return getService().getAgendaExportPeriods(start, end);
	}

	/**
	* Retourne les périodes d'un événement
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.AgendaExportPeriod> getByAgendaExportId(
		long eventId) {
		return getService().getByAgendaExportId(eventId);
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

	public static AgendaExportPeriodLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AgendaExportPeriodLocalService, AgendaExportPeriodLocalService> _serviceTracker =
		ServiceTrackerFactory.open(AgendaExportPeriodLocalService.class);
}