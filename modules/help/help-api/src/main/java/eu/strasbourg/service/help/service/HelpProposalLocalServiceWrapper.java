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

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link HelpProposalLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see HelpProposalLocalService
 * @generated
 */
@ProviderType
public class HelpProposalLocalServiceWrapper
	implements HelpProposalLocalService,
			   ServiceWrapper<HelpProposalLocalService> {

	public HelpProposalLocalServiceWrapper(
		HelpProposalLocalService helpProposalLocalService) {

		_helpProposalLocalService = helpProposalLocalService;
	}

	/**
	 * Adds the help proposal to the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpProposal the help proposal
	 * @return the help proposal that was added
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpProposal addHelpProposal(
		eu.strasbourg.service.help.model.HelpProposal helpProposal) {

		return _helpProposalLocalService.addHelpProposal(helpProposal);
	}

	/**
	 * Creates a new help proposal with the primary key. Does not add the help proposal to the database.
	 *
	 * @param helpProposalId the primary key for the new help proposal
	 * @return the new help proposal
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpProposal createHelpProposal(
		long helpProposalId) {

		return _helpProposalLocalService.createHelpProposal(helpProposalId);
	}

	/**
	 * Crée une helpProposal vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpProposal createHelpProposal(
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpProposalLocalService.createHelpProposal(sc);
	}

	/**
	 * Deletes the help proposal from the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpProposal the help proposal
	 * @return the help proposal that was removed
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpProposal deleteHelpProposal(
		eu.strasbourg.service.help.model.HelpProposal helpProposal) {

		return _helpProposalLocalService.deleteHelpProposal(helpProposal);
	}

	/**
	 * Deletes the help proposal with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param helpProposalId the primary key of the help proposal
	 * @return the help proposal that was removed
	 * @throws PortalException if a help proposal with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpProposal deleteHelpProposal(
			long helpProposalId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpProposalLocalService.deleteHelpProposal(helpProposalId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpProposalLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _helpProposalLocalService.dynamicQuery();
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

		return _helpProposalLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.help.model.impl.HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _helpProposalLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.help.model.impl.HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _helpProposalLocalService.dynamicQuery(
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

		return _helpProposalLocalService.dynamicQueryCount(dynamicQuery);
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

		return _helpProposalLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.help.model.HelpProposal fetchHelpProposal(
		long helpProposalId) {

		return _helpProposalLocalService.fetchHelpProposal(helpProposalId);
	}

	/**
	 * Returns the help proposal matching the UUID and group.
	 *
	 * @param uuid the help proposal's UUID
	 * @param groupId the primary key of the group
	 * @return the matching help proposal, or <code>null</code> if a matching help proposal could not be found
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpProposal
		fetchHelpProposalByUuidAndGroupId(String uuid, long groupId) {

		return _helpProposalLocalService.fetchHelpProposalByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Recherche par mot clés
	 */
	@Override
	public java.util.List<eu.strasbourg.service.help.model.HelpProposal>
		findByKeyword(String keyword, long groupId, int start, int end) {

		return _helpProposalLocalService.findByKeyword(
			keyword, groupId, start, end);
	}

	/**
	 * Recherche par mot clés (compte)
	 */
	@Override
	public long findByKeywordCount(String keyword, long groupId) {
		return _helpProposalLocalService.findByKeywordCount(keyword, groupId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.help.model.HelpProposal>
		findHelpedByPublikId(String publikUserId) {

		return _helpProposalLocalService.findHelpedByPublikId(publikUserId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _helpProposalLocalService.getActionableDynamicQuery();
	}

	/**
	 * Retourne toutes les helpProposal d'un groupe
	 */
	@Override
	public java.util.List<eu.strasbourg.service.help.model.HelpProposal>
		getByGroupId(long groupId) {

		return _helpProposalLocalService.getByGroupId(groupId);
	}

	/**
	 * Retourne toutes les helpProposal d'un utilisateur (MonStrasbourg)
	 */
	@Override
	public java.util.List<eu.strasbourg.service.help.model.HelpProposal>
		getByPublikID(String publikId) {

		return _helpProposalLocalService.getByPublikID(publikId);
	}

	/**
	 * Retourne la demande d'aide pour un utilisateur et une proposition donnee
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpProposal
		getByPublikIdAndHelpProposalId(String publikId, long helpProposalId) {

		return _helpProposalLocalService.getByPublikIdAndHelpProposalId(
			publikId, helpProposalId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _helpProposalLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	/**
	 * Returns the help proposal with the primary key.
	 *
	 * @param helpProposalId the primary key of the help proposal
	 * @return the help proposal
	 * @throws PortalException if a help proposal with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpProposal getHelpProposal(
			long helpProposalId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpProposalLocalService.getHelpProposal(helpProposalId);
	}

	/**
	 * Returns the help proposal matching the UUID and group.
	 *
	 * @param uuid the help proposal's UUID
	 * @param groupId the primary key of the group
	 * @return the matching help proposal
	 * @throws PortalException if a matching help proposal could not be found
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpProposal
			getHelpProposalByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpProposalLocalService.getHelpProposalByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the help proposals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.help.model.impl.HelpProposalModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @return the range of help proposals
	 */
	@Override
	public java.util.List<eu.strasbourg.service.help.model.HelpProposal>
		getHelpProposals(int start, int end) {

		return _helpProposalLocalService.getHelpProposals(start, end);
	}

	/**
	 * Returns all the help proposals matching the UUID and company.
	 *
	 * @param uuid the UUID of the help proposals
	 * @param companyId the primary key of the company
	 * @return the matching help proposals, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<eu.strasbourg.service.help.model.HelpProposal>
		getHelpProposalsByUuidAndCompanyId(String uuid, long companyId) {

		return _helpProposalLocalService.getHelpProposalsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of help proposals matching the UUID and company.
	 *
	 * @param uuid the UUID of the help proposals
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of help proposals
	 * @param end the upper bound of the range of help proposals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching help proposals, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<eu.strasbourg.service.help.model.HelpProposal>
		getHelpProposalsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<eu.strasbourg.service.help.model.HelpProposal>
					orderByComparator) {

		return _helpProposalLocalService.getHelpProposalsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of help proposals.
	 *
	 * @return the number of help proposals
	 */
	@Override
	public int getHelpProposalsCount() {
		return _helpProposalLocalService.getHelpProposalsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _helpProposalLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _helpProposalLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpProposalLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Supprime une helpProposal
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpProposal removeHelpProposal(
			long helpProposalId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpProposalLocalService.removeHelpProposal(helpProposalId);
	}

	/**
	 * Updates the help proposal in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param helpProposal the help proposal
	 * @return the help proposal that was updated
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpProposal updateHelpProposal(
		eu.strasbourg.service.help.model.HelpProposal helpProposal) {

		return _helpProposalLocalService.updateHelpProposal(helpProposal);
	}

	/**
	 * Met à jour une helpProposal et l'enregistre en base de données
	 *
	 * @throws IOException
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpProposal updateHelpProposal(
			eu.strasbourg.service.help.model.HelpProposal helpProposal,
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpProposalLocalService.updateHelpProposal(helpProposal, sc);
	}

	/**
	 * Met à jour le statut de la helpProposal "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(
			eu.strasbourg.service.help.model.HelpProposal helpProposal,
			int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		_helpProposalLocalService.updateStatus(helpProposal, status);
	}

	/**
	 * Met à jour le statut de la helpProposal par le framework workflow
	 */
	@Override
	public eu.strasbourg.service.help.model.HelpProposal updateStatus(
			long userId, long entryId, int status,
			com.liferay.portal.kernel.service.ServiceContext sc,
			java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _helpProposalLocalService.updateStatus(
			userId, entryId, status, sc, workflowContext);
	}

	@Override
	public HelpProposalLocalService getWrappedService() {
		return _helpProposalLocalService;
	}

	@Override
	public void setWrappedService(
		HelpProposalLocalService helpProposalLocalService) {

		_helpProposalLocalService = helpProposalLocalService;
	}

	private HelpProposalLocalService _helpProposalLocalService;

}