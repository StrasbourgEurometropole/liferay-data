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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link InitiativeLocalService}.
 *
 * @author Cedric Henry
 * @see InitiativeLocalService
 * @generated
 */
public class InitiativeLocalServiceWrapper
	implements InitiativeLocalService, ServiceWrapper<InitiativeLocalService> {

	public InitiativeLocalServiceWrapper(
		InitiativeLocalService initiativeLocalService) {

		_initiativeLocalService = initiativeLocalService;
	}

	/**
	 * Adds the initiative to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect InitiativeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param initiative the initiative
	 * @return the initiative that was added
	 */
	@Override
	public eu.strasbourg.service.project.model.Initiative addInitiative(
		eu.strasbourg.service.project.model.Initiative initiative) {

		return _initiativeLocalService.addInitiative(initiative);
	}

	/**
	 * Creates a new initiative with the primary key. Does not add the initiative to the database.
	 *
	 * @param initiativeId the primary key for the new initiative
	 * @return the new initiative
	 */
	@Override
	public eu.strasbourg.service.project.model.Initiative createInitiative(
		long initiativeId) {

		return _initiativeLocalService.createInitiative(initiativeId);
	}

	/**
	 * Crée une initiative vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public eu.strasbourg.service.project.model.Initiative createInitiative(
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _initiativeLocalService.createInitiative(sc);
	}

	/**
	 * Deletes the initiative from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect InitiativeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param initiative the initiative
	 * @return the initiative that was removed
	 */
	@Override
	public eu.strasbourg.service.project.model.Initiative deleteInitiative(
		eu.strasbourg.service.project.model.Initiative initiative) {

		return _initiativeLocalService.deleteInitiative(initiative);
	}

	/**
	 * Deletes the initiative with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect InitiativeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param initiativeId the primary key of the initiative
	 * @return the initiative that was removed
	 * @throws PortalException if a initiative with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.project.model.Initiative deleteInitiative(
			long initiativeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _initiativeLocalService.deleteInitiative(initiativeId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _initiativeLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _initiativeLocalService.dynamicQuery();
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

		return _initiativeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.project.model.impl.InitiativeModelImpl</code>.
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

		return _initiativeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.project.model.impl.InitiativeModelImpl</code>.
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

		return _initiativeLocalService.dynamicQuery(
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

		return _initiativeLocalService.dynamicQueryCount(dynamicQuery);
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

		return _initiativeLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.project.model.Initiative fetchInitiative(
		long initiativeId) {

		return _initiativeLocalService.fetchInitiative(initiativeId);
	}

	/**
	 * Returns the initiative matching the UUID and group.
	 *
	 * @param uuid the initiative's UUID
	 * @param groupId the primary key of the group
	 * @return the matching initiative, or <code>null</code> if a matching initiative could not be found
	 */
	@Override
	public eu.strasbourg.service.project.model.Initiative
		fetchInitiativeByUuidAndGroupId(String uuid, long groupId) {

		return _initiativeLocalService.fetchInitiativeByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Recherche par mot clés
	 */
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative>
		findByKeyword(String keyword, long groupId, int start, int end) {

		return _initiativeLocalService.findByKeyword(
			keyword, groupId, start, end);
	}

	/**
	 * Recherche par mot clés (compte)
	 */
	@Override
	public long findByKeywordCount(String keyword, long groupId) {
		return _initiativeLocalService.findByKeywordCount(keyword, groupId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative>
		findByPublikUserId(String publikUserId) {

		return _initiativeLocalService.findByPublikUserId(publikUserId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative>
		findHelpedByPublikUserId(String publikUserId) {

		return _initiativeLocalService.findHelpedByPublikUserId(publikUserId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _initiativeLocalService.getActionableDynamicQuery();
	}

	/**
	 * Retourne toutes les initiatives d'un groupe
	 */
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative>
		getByGroupId(long groupId) {

		return _initiativeLocalService.getByGroupId(groupId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative>
		getByPublikUserID(String publikId) {

		return _initiativeLocalService.getByPublikUserID(publikId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _initiativeLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _initiativeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the initiative with the primary key.
	 *
	 * @param initiativeId the primary key of the initiative
	 * @return the initiative
	 * @throws PortalException if a initiative with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.project.model.Initiative getInitiative(
			long initiativeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _initiativeLocalService.getInitiative(initiativeId);
	}

	/**
	 * Returns the initiative matching the UUID and group.
	 *
	 * @param uuid the initiative's UUID
	 * @param groupId the primary key of the group
	 * @return the matching initiative
	 * @throws PortalException if a matching initiative could not be found
	 */
	@Override
	public eu.strasbourg.service.project.model.Initiative
			getInitiativeByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _initiativeLocalService.getInitiativeByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the initiatives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.project.model.impl.InitiativeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of initiatives
	 * @param end the upper bound of the range of initiatives (not inclusive)
	 * @return the range of initiatives
	 */
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative>
		getInitiatives(int start, int end) {

		return _initiativeLocalService.getInitiatives(start, end);
	}

	/**
	 * Returns all the initiatives matching the UUID and company.
	 *
	 * @param uuid the UUID of the initiatives
	 * @param companyId the primary key of the company
	 * @return the matching initiatives, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative>
		getInitiativesByUuidAndCompanyId(String uuid, long companyId) {

		return _initiativeLocalService.getInitiativesByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of initiatives matching the UUID and company.
	 *
	 * @param uuid the UUID of the initiatives
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of initiatives
	 * @param end the upper bound of the range of initiatives (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching initiatives, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative>
		getInitiativesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<eu.strasbourg.service.project.model.Initiative>
					orderByComparator) {

		return _initiativeLocalService.getInitiativesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of initiatives.
	 *
	 * @return the number of initiatives
	 */
	@Override
	public int getInitiativesCount() {
		return _initiativeLocalService.getInitiativesCount();
	}

	/**
	 * Recuperer le nombre voulu d'initiatives les plus commentes
	 *
	 * @param groupId ID du site
	 * @param delta Nombre de resultats max voulu
	 * @return Liste d'initiatives les plus commentes triee.
	 */
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative>
		getMostCommented(long groupId, int delta) {

		return _initiativeLocalService.getMostCommented(groupId, delta);
	}

	/**
	 * Recuperer le nombre voulu d'initiatives les plus soutenus
	 *
	 * @param groupId ID du site
	 * @param delta Nombre de resultats max voulu
	 * @return Liste d'initiatives les plus aidé triee.
	 */
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative>
		getMostHelped(long groupId, int delta) {

		return _initiativeLocalService.getMostHelped(groupId, delta);
	}

	/**
	 * Recuperer le nombre voulu d'initiatives les plus soutenus
	 *
	 * @param groupId ID du site
	 * @param delta Nombre de resultats max voulu
	 * @return Liste d'initiatives les plus aidé triee.
	 */
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative>
		getMostLiked(long groupId, int delta) {

		return _initiativeLocalService.getMostLiked(groupId, delta);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _initiativeLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _initiativeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Retourne toutes les initiatives publiées d'un groupe
	 */
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative>
		getPublishedByGroupId(long groupId) {

		return _initiativeLocalService.getPublishedByGroupId(groupId);
	}

	/**
	 * Methode permettant de recuperer une liste d'initiatives triee par nombre de commentaires
	 *
	 * @param groupId ID du site
	 * @return Liste des initiatives triee par nombre de commentaires
	 */
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative>
		getSortedByNbComments(long groupId) {

		return _initiativeLocalService.getSortedByNbComments(groupId);
	}

	/**
	 * Methode permettant de recuperer une liste d'initiatives triee par nombre de soutiens
	 *
	 * @param groupId ID du site
	 * @return Liste des budgets participatifs triee par nombre de soutiens
	 */
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative>
		getSortedByNbHelps(long groupId) {

		return _initiativeLocalService.getSortedByNbHelps(groupId);
	}

	/**
	 * Methode permettant de recuperer une liste d'initiatives triee par nombre de soutiens
	 *
	 * @param groupId ID du site
	 * @return Liste d'initiatives triee par nombre de soutiens
	 */
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Initiative>
		getSortedByNbLikes(long groupId) {

		return _initiativeLocalService.getSortedByNbLikes(groupId);
	}

	/**
	 * Supprime une initiative
	 */
	@Override
	public eu.strasbourg.service.project.model.Initiative removeInitiative(
			long initiativeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _initiativeLocalService.removeInitiative(initiativeId);
	}

	/**
	 * Updates the initiative in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect InitiativeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param initiative the initiative
	 * @return the initiative that was updated
	 */
	@Override
	public eu.strasbourg.service.project.model.Initiative updateInitiative(
		eu.strasbourg.service.project.model.Initiative initiative) {

		return _initiativeLocalService.updateInitiative(initiative);
	}

	/**
	 * Met à jour une initiative et l'enregistre en base de données
	 *
	 * @throws IOException
	 */
	@Override
	public eu.strasbourg.service.project.model.Initiative updateInitiative(
			eu.strasbourg.service.project.model.Initiative initiative,
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _initiativeLocalService.updateInitiative(initiative, sc);
	}

	/**
	 * Met à jour le statut de l'initiative "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(
			eu.strasbourg.service.project.model.Initiative initiative,
			int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		_initiativeLocalService.updateStatus(initiative, status);
	}

	/**
	 * Met à jour le statut de l'initiative par le framework workflow
	 */
	@Override
	public eu.strasbourg.service.project.model.Initiative updateStatus(
			long userId, long entryId, int status,
			com.liferay.portal.kernel.service.ServiceContext sc,
			java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _initiativeLocalService.updateStatus(
			userId, entryId, status, sc, workflowContext);
	}

	@Override
	public InitiativeLocalService getWrappedService() {
		return _initiativeLocalService;
	}

	@Override
	public void setWrappedService(
		InitiativeLocalService initiativeLocalService) {

		_initiativeLocalService = initiativeLocalService;
	}

	private InitiativeLocalService _initiativeLocalService;

}