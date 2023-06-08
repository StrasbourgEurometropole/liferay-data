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

package eu.strasbourg.service.help.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link HelpRequestLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HelpRequestLocalService
 * @generated
 */
public class HelpRequestLocalServiceWrapper
	implements HelpRequestLocalService,
			   ServiceWrapper<HelpRequestLocalService> {

	public HelpRequestLocalServiceWrapper(
		HelpRequestLocalService helpRequestLocalService) {

		_helpRequestLocalService = helpRequestLocalService;
	}

	/**
	 * Adds the help request to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpRequest the help request
	 * @return the help request that was added
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpRequest addHelpRequest(
		eu.strasbourg.service.help.model.HelpRequest helpRequest) {

		return _helpRequestLocalService.addHelpRequest(helpRequest);
	}

	/**
	 * Creates a new help request with the primary key. Does not add the help request to the database.
	 *
	 * @param helpRequestId the primary key for the new help request
	 * @return the new help request
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpRequest createHelpRequest(
		long helpRequestId) {

		return _helpRequestLocalService.createHelpRequest(helpRequestId);
	}

	/**
	 * Methode de creation d'une demande d'aide
	 *
	 * @param sc Le contexte de la requete.
	 * @return L'aide cree.
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpRequest createHelpRequest(
		com.liferay.portal.kernel.service.ServiceContext sc) {

		return _helpRequestLocalService.createHelpRequest(sc);
	}

	/**
	 * Deletes the help request from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpRequest the help request
	 * @return the help request that was removed
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpRequest deleteHelpRequest(
		eu.strasbourg.service.help.model.HelpRequest helpRequest) {

		return _helpRequestLocalService.deleteHelpRequest(helpRequest);
	}

	/**
	 * Deletes the help request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpRequestId the primary key of the help request
	 * @return the help request that was removed
	 * @throws PortalException if a help request with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpRequest deleteHelpRequest(
			long helpRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpRequestLocalService.deleteHelpRequest(helpRequestId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpRequestLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _helpRequestLocalService.dynamicQuery();
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

		return _helpRequestLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.help.model.impl.HelpRequestModelImpl</code>.
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

		return _helpRequestLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.help.model.impl.HelpRequestModelImpl</code>.
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

		return _helpRequestLocalService.dynamicQuery(
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

		return _helpRequestLocalService.dynamicQueryCount(dynamicQuery);
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

		return _helpRequestLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.help.model.HelpRequest fetchHelpRequest(
		long helpRequestId) {

		return _helpRequestLocalService.fetchHelpRequest(helpRequestId);
	}

	/**
	 * Returns the help request matching the UUID and group.
	 *
	 * @param uuid the help request's UUID
	 * @param groupId the primary key of the group
	 * @return the matching help request, or <code>null</code> if a matching help request could not be found
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpRequest
		fetchHelpRequestByUuidAndGroupId(String uuid, long groupId) {

		return _helpRequestLocalService.fetchHelpRequestByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Recherche par mot clés
	 */
	@Override
	public java.util.List<eu.strasbourg.service.help.model.HelpRequest>
		findByKeyword(String keyword, long groupId, int start, int end) {

		return _helpRequestLocalService.findByKeyword(
			keyword, groupId, start, end);
	}

	/**
	 * Recherche par mot clés (compte)
	 */
	@Override
	public long findByKeywordCount(String keyword, long groupId) {
		return _helpRequestLocalService.findByKeywordCount(keyword, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _helpRequestLocalService.getActionableDynamicQuery();
	}

	/**
	 * Retourne la liste des demands d'aides pour une proposition
	 */
	@Override
	public java.util.List<eu.strasbourg.service.help.model.HelpRequest>
		getByHelpProposalId(long helpRequestId) {

		return _helpRequestLocalService.getByHelpProposalId(helpRequestId);
	}

	/**
	 * Retourne les demandes d'aides pour un utilisateur
	 */
	@Override
	public java.util.List<eu.strasbourg.service.help.model.HelpRequest>
		getByPublikId(String publikId) {

		return _helpRequestLocalService.getByPublikId(publikId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _helpRequestLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	/**
	 * Returns the help request with the primary key.
	 *
	 * @param helpRequestId the primary key of the help request
	 * @return the help request
	 * @throws PortalException if a help request with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpRequest getHelpRequest(
			long helpRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpRequestLocalService.getHelpRequest(helpRequestId);
	}

	/**
	 * Returns the help request matching the UUID and group.
	 *
	 * @param uuid the help request's UUID
	 * @param groupId the primary key of the group
	 * @return the matching help request
	 * @throws PortalException if a matching help request could not be found
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpRequest
			getHelpRequestByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpRequestLocalService.getHelpRequestByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the help requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.help.model.impl.HelpRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @return the range of help requests
	 */
	@Override
	public java.util.List<eu.strasbourg.service.help.model.HelpRequest>
		getHelpRequests(int start, int end) {

		return _helpRequestLocalService.getHelpRequests(start, end);
	}

	/**
	 * Returns all the help requests matching the UUID and company.
	 *
	 * @param uuid the UUID of the help requests
	 * @param companyId the primary key of the company
	 * @return the matching help requests, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<eu.strasbourg.service.help.model.HelpRequest>
		getHelpRequestsByUuidAndCompanyId(String uuid, long companyId) {

		return _helpRequestLocalService.getHelpRequestsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of help requests matching the UUID and company.
	 *
	 * @param uuid the UUID of the help requests
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of help requests
	 * @param end the upper bound of the range of help requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching help requests, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<eu.strasbourg.service.help.model.HelpRequest>
		getHelpRequestsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<eu.strasbourg.service.help.model.HelpRequest>
					orderByComparator) {

		return _helpRequestLocalService.getHelpRequestsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of help requests.
	 *
	 * @return the number of help requests
	 */
	@Override
	public int getHelpRequestsCount() {
		return _helpRequestLocalService.getHelpRequestsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _helpRequestLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _helpRequestLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpRequestLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public void reindexById(long helpRequestId)
		throws com.liferay.portal.kernel.search.SearchException {

		_helpRequestLocalService.reindexById(helpRequestId);
	}

	/**
	 * Supprimer une demande d'aide
	 *
	 * @param helpRequestId Id de la demande d'aide
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpRequest removeHelpRequest(
			long helpRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpRequestLocalService.removeHelpRequest(helpRequestId);
	}

	/**
	 * Updates the help request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect HelpRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param helpRequest the help request
	 * @return the help request that was updated
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpRequest updateHelpRequest(
		eu.strasbourg.service.help.model.HelpRequest helpRequest) {

		return _helpRequestLocalService.updateHelpRequest(helpRequest);
	}

	/**
	 * Met à jour une helpProposal et l'enregistre en base de données
	 *
	 * @throws IOException
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpRequest updateHelpRequest(
			eu.strasbourg.service.help.model.HelpRequest helpRequest,
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpRequestLocalService.updateHelpRequest(helpRequest, sc);
	}

	/**
	 * Met à jour le statut de la helpProposal "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(
			eu.strasbourg.service.help.model.HelpRequest helpRequest,
			int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		_helpRequestLocalService.updateStatus(helpRequest, status);
	}

	/**
	 * Met à jour le statut de la helpProposal par le framework workflow
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpRequest updateStatus(
			long userId, long entryId, int status,
			com.liferay.portal.kernel.service.ServiceContext sc,
			java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpRequestLocalService.updateStatus(
			userId, entryId, status, sc, workflowContext);
	}

	@Override
	public HelpRequestLocalService getWrappedService() {
		return _helpRequestLocalService;
	}

	@Override
	public void setWrappedService(
		HelpRequestLocalService helpRequestLocalService) {

		_helpRequestLocalService = helpRequestLocalService;
	}

	private HelpRequestLocalService _helpRequestLocalService;

}