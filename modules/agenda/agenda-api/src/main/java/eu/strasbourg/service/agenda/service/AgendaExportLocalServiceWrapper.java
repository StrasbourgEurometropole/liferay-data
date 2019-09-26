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
 * Provides a wrapper for {@link AgendaExportLocalService}.
 *
 * @author BenjaminBini
 * @see AgendaExportLocalService
 * @generated
 */
@ProviderType
public class AgendaExportLocalServiceWrapper implements AgendaExportLocalService,
	ServiceWrapper<AgendaExportLocalService> {
	public AgendaExportLocalServiceWrapper(
		AgendaExportLocalService agendaExportLocalService) {
		_agendaExportLocalService = agendaExportLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _agendaExportLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _agendaExportLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _agendaExportLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _agendaExportLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _agendaExportLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _agendaExportLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Lance une recherche selon le searchContext
	*/
	@Override
	public com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return _agendaExportLocalService.search(searchContext);
	}

	/**
	* Adds the agenda export to the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExport the agenda export
	* @return the agenda export that was added
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExport addAgendaExport(
		eu.strasbourg.service.agenda.model.AgendaExport agendaExport) {
		return _agendaExportLocalService.addAgendaExport(agendaExport);
	}

	@Override
	public eu.strasbourg.service.agenda.model.AgendaExport cloneAgendaExport(
		com.liferay.portal.kernel.service.ServiceContext sc,
		eu.strasbourg.service.agenda.model.AgendaExport agendaToCopy)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _agendaExportLocalService.cloneAgendaExport(sc, agendaToCopy);
	}

	/**
	* Crée un Agenda Export vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExport createAgendaExport(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _agendaExportLocalService.createAgendaExport(sc);
	}

	/**
	* Creates a new agenda export with the primary key. Does not add the agenda export to the database.
	*
	* @param agendaExportId the primary key for the new agenda export
	* @return the new agenda export
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExport createAgendaExport(
		long agendaExportId) {
		return _agendaExportLocalService.createAgendaExport(agendaExportId);
	}

	/**
	* Deletes the agenda export from the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExport the agenda export
	* @return the agenda export that was removed
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExport deleteAgendaExport(
		eu.strasbourg.service.agenda.model.AgendaExport agendaExport) {
		return _agendaExportLocalService.deleteAgendaExport(agendaExport);
	}

	/**
	* Deletes the agenda export with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param agendaExportId the primary key of the agenda export
	* @return the agenda export that was removed
	* @throws PortalException if a agenda export with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExport deleteAgendaExport(
		long agendaExportId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _agendaExportLocalService.deleteAgendaExport(agendaExportId);
	}

	@Override
	public eu.strasbourg.service.agenda.model.AgendaExport fetchAgendaExport(
		long agendaExportId) {
		return _agendaExportLocalService.fetchAgendaExport(agendaExportId);
	}

	/**
	* Returns the agenda export matching the UUID and group.
	*
	* @param uuid the agenda export's UUID
	* @param groupId the primary key of the group
	* @return the matching agenda export, or <code>null</code> if a matching agenda export could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExport fetchAgendaExportByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _agendaExportLocalService.fetchAgendaExportByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the agenda export with the primary key.
	*
	* @param agendaExportId the primary key of the agenda export
	* @return the agenda export
	* @throws PortalException if a agenda export with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExport getAgendaExport(
		long agendaExportId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _agendaExportLocalService.getAgendaExport(agendaExportId);
	}

	/**
	* Returns the agenda export matching the UUID and group.
	*
	* @param uuid the agenda export's UUID
	* @param groupId the primary key of the group
	* @return the matching agenda export
	* @throws PortalException if a matching agenda export could not be found
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExport getAgendaExportByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _agendaExportLocalService.getAgendaExportByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Supprime un AgendaExport
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExport removeAgendaExport(
		long agendaExportId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _agendaExportLocalService.removeAgendaExport(agendaExportId);
	}

	/**
	* Updates the agenda export in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param agendaExport the agenda export
	* @return the agenda export that was updated
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExport updateAgendaExport(
		eu.strasbourg.service.agenda.model.AgendaExport agendaExport) {
		return _agendaExportLocalService.updateAgendaExport(agendaExport);
	}

	/**
	* Met à jour un Agenda Export et l'enregistre en base de données
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExport updateAgendaExport(
		eu.strasbourg.service.agenda.model.AgendaExport agendaExport,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _agendaExportLocalService.updateAgendaExport(agendaExport, sc);
	}

	/**
	* Met à jour le statut de l'AgendaExport par le framework workflow
	*/
	@Override
	public eu.strasbourg.service.agenda.model.AgendaExport updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _agendaExportLocalService.updateStatus(userId, entryId, status,
			sc, workflowContext);
	}

	/**
	* Returns the number of agenda exports.
	*
	* @return the number of agenda exports
	*/
	@Override
	public int getAgendaExportsCount() {
		return _agendaExportLocalService.getAgendaExportsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _agendaExportLocalService.getOSGiServiceIdentifier();
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
		return _agendaExportLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _agendaExportLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _agendaExportLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Lance une recherche par mots-clés
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.AgendaExport> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _agendaExportLocalService.findByKeyword(keyword, groupId, start,
			end);
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
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.AgendaExport> getAgendaExports(
		int start, int end) {
		return _agendaExportLocalService.getAgendaExports(start, end);
	}

	/**
	* Returns all the agenda exports matching the UUID and company.
	*
	* @param uuid the UUID of the agenda exports
	* @param companyId the primary key of the company
	* @return the matching agenda exports, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.AgendaExport> getAgendaExportsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _agendaExportLocalService.getAgendaExportsByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.AgendaExport> getAgendaExportsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.agenda.model.AgendaExport> orderByComparator) {
		return _agendaExportLocalService.getAgendaExportsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Retourne les vocabulaires rattrachés à ce type d'entité pour un groupe
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _agendaExportLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne tous les AGendaExport d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.agenda.model.AgendaExport> getByGroupId(
		long groupId) {
		return _agendaExportLocalService.getByGroupId(groupId);
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
		return _agendaExportLocalService.dynamicQueryCount(dynamicQuery);
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
		return _agendaExportLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Compte de la recherche par mots-clés
	*/
	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _agendaExportLocalService.findByKeywordCount(keyword, groupId);
	}

	/**
	* Met à jour le statut de l'AgendaExport "manuellement" (pas via le workflow)
	*/
	@Override
	public void updateStatus(
		eu.strasbourg.service.agenda.model.AgendaExport agendaExport, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		_agendaExportLocalService.updateStatus(agendaExport, status);
	}

	@Override
	public AgendaExportLocalService getWrappedService() {
		return _agendaExportLocalService;
	}

	@Override
	public void setWrappedService(
		AgendaExportLocalService agendaExportLocalService) {
		_agendaExportLocalService = agendaExportLocalService;
	}

	private AgendaExportLocalService _agendaExportLocalService;
}