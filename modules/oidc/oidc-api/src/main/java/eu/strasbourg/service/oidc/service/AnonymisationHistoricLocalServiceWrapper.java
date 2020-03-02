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

package eu.strasbourg.service.oidc.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AnonymisationHistoricLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AnonymisationHistoricLocalService
 * @generated
 */
@ProviderType
public class AnonymisationHistoricLocalServiceWrapper
	implements AnonymisationHistoricLocalService,
		ServiceWrapper<AnonymisationHistoricLocalService> {
	public AnonymisationHistoricLocalServiceWrapper(
		AnonymisationHistoricLocalService anonymisationHistoricLocalService) {
		_anonymisationHistoricLocalService = anonymisationHistoricLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _anonymisationHistoricLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _anonymisationHistoricLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _anonymisationHistoricLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _anonymisationHistoricLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _anonymisationHistoricLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _anonymisationHistoricLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the anonymisation historic to the database. Also notifies the appropriate model listeners.
	*
	* @param anonymisationHistoric the anonymisation historic
	* @return the anonymisation historic that was added
	*/
	@Override
	public eu.strasbourg.service.oidc.model.AnonymisationHistoric addAnonymisationHistoric(
		eu.strasbourg.service.oidc.model.AnonymisationHistoric anonymisationHistoric) {
		return _anonymisationHistoricLocalService.addAnonymisationHistoric(anonymisationHistoric);
	}

	/**
	* Crée une entree d'anonymisation vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.oidc.model.AnonymisationHistoric createAnonymisationHistoric(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _anonymisationHistoricLocalService.createAnonymisationHistoric(sc);
	}

	/**
	* Creates a new anonymisation historic with the primary key. Does not add the anonymisation historic to the database.
	*
	* @param anonymisationHistoricId the primary key for the new anonymisation historic
	* @return the new anonymisation historic
	*/
	@Override
	public eu.strasbourg.service.oidc.model.AnonymisationHistoric createAnonymisationHistoric(
		long anonymisationHistoricId) {
		return _anonymisationHistoricLocalService.createAnonymisationHistoric(anonymisationHistoricId);
	}

	/**
	* Deletes the anonymisation historic from the database. Also notifies the appropriate model listeners.
	*
	* @param anonymisationHistoric the anonymisation historic
	* @return the anonymisation historic that was removed
	*/
	@Override
	public eu.strasbourg.service.oidc.model.AnonymisationHistoric deleteAnonymisationHistoric(
		eu.strasbourg.service.oidc.model.AnonymisationHistoric anonymisationHistoric) {
		return _anonymisationHistoricLocalService.deleteAnonymisationHistoric(anonymisationHistoric);
	}

	/**
	* Deletes the anonymisation historic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param anonymisationHistoricId the primary key of the anonymisation historic
	* @return the anonymisation historic that was removed
	* @throws PortalException if a anonymisation historic with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.oidc.model.AnonymisationHistoric deleteAnonymisationHistoric(
		long anonymisationHistoricId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _anonymisationHistoricLocalService.deleteAnonymisationHistoric(anonymisationHistoricId);
	}

	@Override
	public eu.strasbourg.service.oidc.model.AnonymisationHistoric fetchAnonymisationHistoric(
		long anonymisationHistoricId) {
		return _anonymisationHistoricLocalService.fetchAnonymisationHistoric(anonymisationHistoricId);
	}

	/**
	* Returns the anonymisation historic matching the UUID and group.
	*
	* @param uuid the anonymisation historic's UUID
	* @param groupId the primary key of the group
	* @return the matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	*/
	@Override
	public eu.strasbourg.service.oidc.model.AnonymisationHistoric fetchAnonymisationHistoricByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _anonymisationHistoricLocalService.fetchAnonymisationHistoricByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the anonymisation historic with the primary key.
	*
	* @param anonymisationHistoricId the primary key of the anonymisation historic
	* @return the anonymisation historic
	* @throws PortalException if a anonymisation historic with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.oidc.model.AnonymisationHistoric getAnonymisationHistoric(
		long anonymisationHistoricId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _anonymisationHistoricLocalService.getAnonymisationHistoric(anonymisationHistoricId);
	}

	/**
	* Returns the anonymisation historic matching the UUID and group.
	*
	* @param uuid the anonymisation historic's UUID
	* @param groupId the primary key of the group
	* @return the matching anonymisation historic
	* @throws PortalException if a matching anonymisation historic could not be found
	*/
	@Override
	public eu.strasbourg.service.oidc.model.AnonymisationHistoric getAnonymisationHistoricByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _anonymisationHistoricLocalService.getAnonymisationHistoricByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Supprime l'entree d'anonymisation
	*/
	@Override
	public eu.strasbourg.service.oidc.model.AnonymisationHistoric removeAnonymisationHistoric(
		long anonymisationHistoricId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _anonymisationHistoricLocalService.removeAnonymisationHistoric(anonymisationHistoricId);
	}

	/**
	* Updates the anonymisation historic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param anonymisationHistoric the anonymisation historic
	* @return the anonymisation historic that was updated
	*/
	@Override
	public eu.strasbourg.service.oidc.model.AnonymisationHistoric updateAnonymisationHistoric(
		eu.strasbourg.service.oidc.model.AnonymisationHistoric anonymisationHistoric) {
		return _anonymisationHistoricLocalService.updateAnonymisationHistoric(anonymisationHistoric);
	}

	/**
	* Met à jour une entree d'anonymisation et l'enregistre en base de données
	*
	* @throws IOException
	*/
	@Override
	public eu.strasbourg.service.oidc.model.AnonymisationHistoric updateAnonymisationHistoric(
		eu.strasbourg.service.oidc.model.AnonymisationHistoric anonymisationHistoric,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _anonymisationHistoricLocalService.updateAnonymisationHistoric(anonymisationHistoric,
			sc);
	}

	/**
	* Met à jour le statut de l'entree d'anonymisation par le framework workflow
	*/
	@Override
	public eu.strasbourg.service.oidc.model.AnonymisationHistoric updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _anonymisationHistoricLocalService.updateStatus(userId, entryId,
			status, sc, workflowContext);
	}

	/**
	* Returns the number of anonymisation historics.
	*
	* @return the number of anonymisation historics
	*/
	@Override
	public int getAnonymisationHistoricsCount() {
		return _anonymisationHistoricLocalService.getAnonymisationHistoricsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _anonymisationHistoricLocalService.getOSGiServiceIdentifier();
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
		return _anonymisationHistoricLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.oidc.model.impl.AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _anonymisationHistoricLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.oidc.model.impl.AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _anonymisationHistoricLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Recherche par mot clés
	*/
	@Override
	public java.util.List<eu.strasbourg.service.oidc.model.AnonymisationHistoric> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _anonymisationHistoricLocalService.findByKeyword(keyword,
			groupId, start, end);
	}

	/**
	* Returns a range of all the anonymisation historics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.oidc.model.impl.AnonymisationHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of anonymisation historics
	* @param end the upper bound of the range of anonymisation historics (not inclusive)
	* @return the range of anonymisation historics
	*/
	@Override
	public java.util.List<eu.strasbourg.service.oidc.model.AnonymisationHistoric> getAnonymisationHistorics(
		int start, int end) {
		return _anonymisationHistoricLocalService.getAnonymisationHistorics(start,
			end);
	}

	/**
	* Returns all the anonymisation historics matching the UUID and company.
	*
	* @param uuid the UUID of the anonymisation historics
	* @param companyId the primary key of the company
	* @return the matching anonymisation historics, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.oidc.model.AnonymisationHistoric> getAnonymisationHistoricsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _anonymisationHistoricLocalService.getAnonymisationHistoricsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of anonymisation historics matching the UUID and company.
	*
	* @param uuid the UUID of the anonymisation historics
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of anonymisation historics
	* @param end the upper bound of the range of anonymisation historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching anonymisation historics, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.oidc.model.AnonymisationHistoric> getAnonymisationHistoricsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.oidc.model.AnonymisationHistoric> orderByComparator) {
		return _anonymisationHistoricLocalService.getAnonymisationHistoricsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à l'anonymisation
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _anonymisationHistoricLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les anonymisations d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.oidc.model.AnonymisationHistoric> getByGroupId(
		long groupId) {
		return _anonymisationHistoricLocalService.getByGroupId(groupId);
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
		return _anonymisationHistoricLocalService.dynamicQueryCount(dynamicQuery);
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
		return _anonymisationHistoricLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Recherche par mot clés (compte)
	*/
	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _anonymisationHistoricLocalService.findByKeywordCount(keyword,
			groupId);
	}

	/**
	* Effectue l'anonymisation des donnees issues des fichiers GTFS
	*/
	@Override
	public void doAnonymisation(
		com.liferay.portal.kernel.service.ServiceContext sc,
		eu.strasbourg.service.oidc.model.AnonymisationHistoric anonymisationHistoric) {
		_anonymisationHistoricLocalService.doAnonymisation(sc,
			anonymisationHistoric);
	}

	/**
	* Met à jour le statut de l'entree d'anonymisation "manuellement" (pas via le workflow)
	*/
	@Override
	public void updateStatus(
		eu.strasbourg.service.oidc.model.AnonymisationHistoric anonymisationHistoric,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		_anonymisationHistoricLocalService.updateStatus(anonymisationHistoric,
			status);
	}

	@Override
	public AnonymisationHistoricLocalService getWrappedService() {
		return _anonymisationHistoricLocalService;
	}

	@Override
	public void setWrappedService(
		AnonymisationHistoricLocalService anonymisationHistoricLocalService) {
		_anonymisationHistoricLocalService = anonymisationHistoricLocalService;
	}

	private AnonymisationHistoricLocalService _anonymisationHistoricLocalService;
}