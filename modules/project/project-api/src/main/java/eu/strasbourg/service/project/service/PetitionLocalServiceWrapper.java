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
 * Provides a wrapper for {@link PetitionLocalService}.
 *
 * @author Cedric Henry
 * @see PetitionLocalService
 * @generated
 */
@ProviderType
public class PetitionLocalServiceWrapper implements PetitionLocalService,
	ServiceWrapper<PetitionLocalService> {
	public PetitionLocalServiceWrapper(
		PetitionLocalService petitionLocalService) {
		_petitionLocalService = petitionLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _petitionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _petitionLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _petitionLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _petitionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _petitionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _petitionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the petition to the database. Also notifies the appropriate model listeners.
	*
	* @param petition the petition
	* @return the petition that was added
	*/
	@Override
	public eu.strasbourg.service.project.model.Petition addPetition(
		eu.strasbourg.service.project.model.Petition petition) {
		return _petitionLocalService.addPetition(petition);
	}

	/**
	* Crée une participation vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.project.model.Petition createPetition(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _petitionLocalService.createPetition(sc);
	}

	/**
	* Creates a new petition with the primary key. Does not add the petition to the database.
	*
	* @param petitionId the primary key for the new petition
	* @return the new petition
	*/
	@Override
	public eu.strasbourg.service.project.model.Petition createPetition(
		long petitionId) {
		return _petitionLocalService.createPetition(petitionId);
	}

	/**
	* Deletes the petition from the database. Also notifies the appropriate model listeners.
	*
	* @param petition the petition
	* @return the petition that was removed
	*/
	@Override
	public eu.strasbourg.service.project.model.Petition deletePetition(
		eu.strasbourg.service.project.model.Petition petition) {
		return _petitionLocalService.deletePetition(petition);
	}

	/**
	* Deletes the petition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param petitionId the primary key of the petition
	* @return the petition that was removed
	* @throws PortalException if a petition with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.Petition deletePetition(
		long petitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _petitionLocalService.deletePetition(petitionId);
	}

	@Override
	public eu.strasbourg.service.project.model.Petition fetchPetition(
		long petitionId) {
		return _petitionLocalService.fetchPetition(petitionId);
	}

	/**
	* Returns the petition matching the UUID and group.
	*
	* @param uuid the petition's UUID
	* @param groupId the primary key of the group
	* @return the matching petition, or <code>null</code> if a matching petition could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.Petition fetchPetitionByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _petitionLocalService.fetchPetitionByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the petition with the primary key.
	*
	* @param petitionId the primary key of the petition
	* @return the petition
	* @throws PortalException if a petition with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.Petition getPetition(
		long petitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _petitionLocalService.getPetition(petitionId);
	}

	/**
	* Returns the petition matching the UUID and group.
	*
	* @param uuid the petition's UUID
	* @param groupId the primary key of the group
	* @return the matching petition
	* @throws PortalException if a matching petition could not be found
	*/
	@Override
	public eu.strasbourg.service.project.model.Petition getPetitionByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _petitionLocalService.getPetitionByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une petition
	*/
	@Override
	public eu.strasbourg.service.project.model.Petition removePetition(
		long petitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _petitionLocalService.removePetition(petitionId);
	}

	/**
	* Updates the petition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param petition the petition
	* @return the petition that was updated
	*/
	@Override
	public eu.strasbourg.service.project.model.Petition updatePetition(
		eu.strasbourg.service.project.model.Petition petition) {
		return _petitionLocalService.updatePetition(petition);
	}

	@Override
	public eu.strasbourg.service.project.model.Petition updatePetition(
		eu.strasbourg.service.project.model.Petition petition,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _petitionLocalService.updatePetition(petition, sc);
	}

	@Override
	public eu.strasbourg.service.project.model.Petition updateStatus(
		long userId, long petitionId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _petitionLocalService.updateStatus(userId, petitionId, status,
			serviceContext, workflowContext);
	}

	/**
	* Returns the number of petitions.
	*
	* @return the number of petitions
	*/
	@Override
	public int getPetitionsCount() {
		return _petitionLocalService.getPetitionsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _petitionLocalService.getOSGiServiceIdentifier();
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
		return _petitionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _petitionLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _petitionLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<eu.strasbourg.service.project.model.Petition> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _petitionLocalService.findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à une petition
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _petitionLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne tous les petitions d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Petition> getByGroupId(
		long groupId) {
		return _petitionLocalService.getByGroupId(groupId);
	}

	/**
	* Returns a range of all the petitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of petitions
	* @param end the upper bound of the range of petitions (not inclusive)
	* @return the range of petitions
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Petition> getPetitions(
		int start, int end) {
		return _petitionLocalService.getPetitions(start, end);
	}

	/**
	* Returns all the petitions matching the UUID and company.
	*
	* @param uuid the UUID of the petitions
	* @param companyId the primary key of the company
	* @return the matching petitions, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Petition> getPetitionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _petitionLocalService.getPetitionsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of petitions matching the UUID and company.
	*
	* @param uuid the UUID of the petitions
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of petitions
	* @param end the upper bound of the range of petitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching petitions, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Petition> getPetitionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.project.model.Petition> orderByComparator) {
		return _petitionLocalService.getPetitionsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Retourne tous les petitions publiés d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Petition> getPublishedByGroupId(
		long groupId) {
		return _petitionLocalService.getPublishedByGroupId(groupId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.project.model.Petition> getTheMostCommented(
		long groupId) {
		return _petitionLocalService.getTheMostCommented(groupId);
	}

	/**
	* Méthode permettant de trier les petitions
	*
	* @return
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Petition> getTheMostSigned(
		long groupId) {
		return _petitionLocalService.getTheMostSigned(groupId);
	}

	@Override
	public java.util.List<eu.strasbourg.service.project.model.Petition> getTheThreeLessSigned(
		long groupId) {
		return _petitionLocalService.getTheThreeLessSigned(groupId);
	}

	/**
	* @return
	*/
	@Override
	public java.util.List<eu.strasbourg.service.project.model.Petition> getTheThreeMostSigned(
		long groupId) {
		return _petitionLocalService.getTheThreeMostSigned(groupId);
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
		return _petitionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _petitionLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Recherche par mot clés (compte)
	*/
	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _petitionLocalService.findByKeywordCount(keyword, groupId);
	}

	@Override
	public void updateAllPetitionsStatus()
		throws com.liferay.portal.kernel.exception.PortalException {
		_petitionLocalService.updateAllPetitionsStatus();
	}

	/**
	* Met à jour le statut du petition "manuellement" (pas via le workflow)
	*/
	@Override
	public void updateStatus(
		eu.strasbourg.service.project.model.Petition petition, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		_petitionLocalService.updateStatus(petition, status);
	}

	@Override
	public PetitionLocalService getWrappedService() {
		return _petitionLocalService;
	}

	@Override
	public void setWrappedService(PetitionLocalService petitionLocalService) {
		_petitionLocalService = petitionLocalService;
	}

	private PetitionLocalService _petitionLocalService;
}