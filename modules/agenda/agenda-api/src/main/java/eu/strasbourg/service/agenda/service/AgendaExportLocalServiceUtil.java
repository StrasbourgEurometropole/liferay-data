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
 * Provides the local service utility for AgendaExport. This utility wraps
 * {@link eu.strasbourg.service.agenda.service.impl.AgendaExportLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author BenjaminBini
 * @see AgendaExportLocalService
 * @see eu.strasbourg.service.agenda.service.base.AgendaExportLocalServiceBaseImpl
 * @see eu.strasbourg.service.agenda.service.impl.AgendaExportLocalServiceImpl
 * @generated
 */
@ProviderType
public class AgendaExportLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.agenda.service.impl.AgendaExportLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Lance une recherche selon le searchContext
	*/
	public static com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return getService().search(searchContext);
	}

	/**
	* Adds the agenda export to the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExport the agenda export
	* @return the agenda export that was added
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExport addAgendaExport(
		eu.strasbourg.service.agenda.model.AgendaExport agendaExport) {
		return getService().addAgendaExport(agendaExport);
	}

	/**
	* Crée un Agenda Export vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExport createAgendaExport(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createAgendaExport(sc);
	}

	/**
	* Creates a new agenda export with the primary key. Does not add the agenda export to the database.
	*
	* @param agendaExportId the primary key for the new agenda export
	* @return the new agenda export
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExport createAgendaExport(
		long agendaExportId) {
		return getService().createAgendaExport(agendaExportId);
	}

	/**
	* Deletes the agenda export from the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExport the agenda export
	* @return the agenda export that was removed
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExport deleteAgendaExport(
		eu.strasbourg.service.agenda.model.AgendaExport agendaExport) {
		return getService().deleteAgendaExport(agendaExport);
	}

	/**
	* Deletes the agenda export with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExportId the primary key of the agenda export
	* @return the agenda export that was removed
	* @throws PortalException if a agenda export with the primary key could not be found
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExport deleteAgendaExport(
		long agendaExportId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAgendaExport(agendaExportId);
	}

	public static eu.strasbourg.service.agenda.model.AgendaExport fetchAgendaExport(
		long agendaExportId) {
		return getService().fetchAgendaExport(agendaExportId);
	}

	/**
	* Returns the agenda export matching the UUID and group.
	*
	* @param uuid the agenda export's UUID
	* @param groupId the primary key of the group
	* @return the matching agenda export, or <code>null</code> if a matching agenda export could not be found
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExport fetchAgendaExportByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchAgendaExportByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the agenda export with the primary key.
	*
	* @param agendaExportId the primary key of the agenda export
	* @return the agenda export
	* @throws PortalException if a agenda export with the primary key could not be found
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExport getAgendaExport(
		long agendaExportId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAgendaExport(agendaExportId);
	}

	/**
	* Returns the agenda export matching the UUID and group.
	*
	* @param uuid the agenda export's UUID
	* @param groupId the primary key of the group
	* @return the matching agenda export
	* @throws PortalException if a matching agenda export could not be found
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExport getAgendaExportByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAgendaExportByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime un AgendaExport
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExport removeAgendaExport(
		long agendaExportId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeAgendaExport(agendaExportId);
	}

	/**
	* Updates the agenda export in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param agendaExport the agenda export
	* @return the agenda export that was updated
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExport updateAgendaExport(
		eu.strasbourg.service.agenda.model.AgendaExport agendaExport) {
		return getService().updateAgendaExport(agendaExport);
	}

	/**
	* Met à jour un Agenda Export et l'enregistre en base de données
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExport updateAgendaExport(
		eu.strasbourg.service.agenda.model.AgendaExport agendaExport,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateAgendaExport(agendaExport, sc);
	}

	/**
	* Met à jour le statut de l'AgendaExport par le framework workflow
	*/
	public static eu.strasbourg.service.agenda.model.AgendaExport updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, entryId, status, sc, workflowContext);
	}

	/**
	* Returns the number of agenda exports.
	*
	* @return the number of agenda exports
	*/
	public static int getAgendaExportsCount() {
		return getService().getAgendaExportsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Lance une recherche par mots-clés
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.AgendaExport> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return getService().findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Returns a range of all the agenda exports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.agenda.model.impl.AgendaExportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @return the range of agenda exports
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.AgendaExport> getAgendaExports(
		int start, int end) {
		return getService().getAgendaExports(start, end);
	}

	/**
	* Returns all the agenda exports matching the UUID and company.
	*
	* @param uuid the UUID of the agenda exports
	* @param companyId the primary key of the company
	* @return the matching agenda exports, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.AgendaExport> getAgendaExportsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getAgendaExportsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of agenda exports matching the UUID and company.
	*
	* @param uuid the UUID of the agenda exports
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of agenda exports
	* @param end the upper bound of the range of agenda exports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching agenda exports, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.AgendaExport> getAgendaExportsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.agenda.model.AgendaExport> orderByComparator) {
		return getService()
				   .getAgendaExportsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Retourne les vocabulaires rattrachés à ce type d'entité pour un groupe
	*/
	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return getService().getAttachedVocabularies(groupId);
	}

	/**
	* Retourne tous les AGendaExport d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.agenda.model.AgendaExport> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
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

	/**
	* Compte de la recherche par mots-clés
	*/
	public static long findByKeywordCount(java.lang.String keyword, long groupId) {
		return getService().findByKeywordCount(keyword, groupId);
	}

	/**
	* Met à jour le statut de l'AgendaExport "manuellement" (pas via le workflow)
	*/
	public static void updateStatus(
		eu.strasbourg.service.agenda.model.AgendaExport agendaExport, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateStatus(agendaExport, status);
	}

	public static AgendaExportLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AgendaExportLocalService, AgendaExportLocalService> _serviceTracker =
		ServiceTrackerFactory.open(AgendaExportLocalService.class);
}