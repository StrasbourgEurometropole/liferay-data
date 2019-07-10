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

package eu.strasbourg.service.gtfs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LigneLocalService}.
 *
 * @author Cedric Henry
 * @see LigneLocalService
 * @generated
 */
@ProviderType
public class LigneLocalServiceWrapper implements LigneLocalService,
	ServiceWrapper<LigneLocalService> {
	public LigneLocalServiceWrapper(LigneLocalService ligneLocalService) {
		_ligneLocalService = ligneLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ligneLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ligneLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _ligneLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ligneLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Recuperer toutes les couleurs de ligne au format JSON
	*/
	@Override
	public com.liferay.portal.kernel.json.JSONArray getLigneColors() {
		return _ligneLocalService.getLigneColors();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ligneLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ligneLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the ligne to the database. Also notifies the appropriate model listeners.
	*
	* @param ligne the ligne
	* @return the ligne that was added
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne addLigne(
		eu.strasbourg.service.gtfs.model.Ligne ligne) {
		return _ligneLocalService.addLigne(ligne);
	}

	/**
	* Crée une entree avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne createLigne(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ligneLocalService.createLigne(sc);
	}

	/**
	* Creates a new ligne with the primary key. Does not add the ligne to the database.
	*
	* @param ligneId the primary key for the new ligne
	* @return the new ligne
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne createLigne(long ligneId) {
		return _ligneLocalService.createLigne(ligneId);
	}

	/**
	* Deletes the ligne from the database. Also notifies the appropriate model listeners.
	*
	* @param ligne the ligne
	* @return the ligne that was removed
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne deleteLigne(
		eu.strasbourg.service.gtfs.model.Ligne ligne) {
		return _ligneLocalService.deleteLigne(ligne);
	}

	/**
	* Deletes the ligne with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ligneId the primary key of the ligne
	* @return the ligne that was removed
	* @throws PortalException if a ligne with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne deleteLigne(long ligneId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ligneLocalService.deleteLigne(ligneId);
	}

	@Override
	public eu.strasbourg.service.gtfs.model.Ligne fetchLigne(long ligneId) {
		return _ligneLocalService.fetchLigne(ligneId);
	}

	/**
	* Returns the ligne matching the UUID and group.
	*
	* @param uuid the ligne's UUID
	* @param groupId the primary key of the group
	* @return the matching ligne, or <code>null</code> if a matching ligne could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne fetchLigneByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _ligneLocalService.fetchLigneByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Retourne une ligne via son routeId CTS
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne getByRouteId(
		java.lang.String routeId) {
		return _ligneLocalService.getByRouteId(routeId);
	}

	/**
	* Returns the ligne with the primary key.
	*
	* @param ligneId the primary key of the ligne
	* @return the ligne
	* @throws PortalException if a ligne with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne getLigne(long ligneId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ligneLocalService.getLigne(ligneId);
	}

	/**
	* Returns the ligne matching the UUID and group.
	*
	* @param uuid the ligne's UUID
	* @param groupId the primary key of the group
	* @return the matching ligne
	* @throws PortalException if a matching ligne could not be found
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne getLigneByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ligneLocalService.getLigneByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime l'entree
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne removeLigne(long ligneId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ligneLocalService.removeLigne(ligneId);
	}

	/**
	* Updates the ligne in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ligne the ligne
	* @return the ligne that was updated
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne updateLigne(
		eu.strasbourg.service.gtfs.model.Ligne ligne) {
		return _ligneLocalService.updateLigne(ligne);
	}

	/**
	* Met à jour une entree et l'enregistre en base de données
	*
	* @throws IOException
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne updateLigne(
		eu.strasbourg.service.gtfs.model.Ligne ligne,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ligneLocalService.updateLigne(ligne, sc);
	}

	/**
	* Met à jour le statut de l'entree par le framework workflow
	*/
	@Override
	public eu.strasbourg.service.gtfs.model.Ligne updateStatus(long userId,
		long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ligneLocalService.updateStatus(userId, entryId, status, sc,
			workflowContext);
	}

	/**
	* Returns the number of lignes.
	*
	* @return the number of lignes
	*/
	@Override
	public int getLignesCount() {
		return _ligneLocalService.getLignesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ligneLocalService.getOSGiServiceIdentifier();
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
		return _ligneLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ligneLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _ligneLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Recherche par mot clés
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.Ligne> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _ligneLocalService.findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entree
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _ligneLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les entrees d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.Ligne> getByGroupId(
		long groupId) {
		return _ligneLocalService.getByGroupId(groupId);
	}

	/**
	* Returns a range of all the lignes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.LigneModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @return the range of lignes
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.Ligne> getLignes(
		int start, int end) {
		return _ligneLocalService.getLignes(start, end);
	}

	/**
	* Returns all the lignes matching the UUID and company.
	*
	* @param uuid the UUID of the lignes
	* @param companyId the primary key of the company
	* @return the matching lignes, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.Ligne> getLignesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _ligneLocalService.getLignesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of lignes matching the UUID and company.
	*
	* @param uuid the UUID of the lignes
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of lignes
	* @param end the upper bound of the range of lignes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching lignes, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.gtfs.model.Ligne> getLignesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.gtfs.model.Ligne> orderByComparator) {
		return _ligneLocalService.getLignesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Retourne la liste de toutes les lignes
	*/
	@Override
	public java.util.Map<java.lang.String, eu.strasbourg.service.gtfs.model.Ligne> getAll() {
		return _ligneLocalService.getAll();
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
		return _ligneLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ligneLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Recherche par mot clés (compte)
	*/
	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _ligneLocalService.findByKeywordCount(keyword, groupId);
	}

	/**
	* Met à jour le statut "manuellement" (pas via le workflow) des entrees
	*/
	@Override
	public void unpublishLignes(
		java.util.List<eu.strasbourg.service.gtfs.model.Ligne> lignes,
		eu.strasbourg.service.gtfs.model.ImportHistoric importHistoric,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ligneLocalService.unpublishLignes(lignes, importHistoric, sc);
	}

	/**
	* Met à jour les entree donnees
	*
	* @throws IOException
	*/
	@Override
	public void updateLignes(
		java.util.List<eu.strasbourg.service.gtfs.model.Ligne> lignes,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ligneLocalService.updateLignes(lignes, sc);
	}

	/**
	* Met à jour le statut "manuellement" (pas via le workflow)
	*/
	@Override
	public void updateStatus(eu.strasbourg.service.gtfs.model.Ligne ligne,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		_ligneLocalService.updateStatus(ligne, status);
	}

	@Override
	public LigneLocalService getWrappedService() {
		return _ligneLocalService;
	}

	@Override
	public void setWrappedService(LigneLocalService ligneLocalService) {
		_ligneLocalService = ligneLocalService;
	}

	private LigneLocalService _ligneLocalService;
}