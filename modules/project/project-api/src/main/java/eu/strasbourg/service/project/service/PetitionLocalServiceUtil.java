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
import com.liferay.osgi.util.ServiceTrackerFactory;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Petition. This utility wraps
 * {@link eu.strasbourg.service.project.service.impl.PetitionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Cedric Henry
 * @see PetitionLocalService
 * @see eu.strasbourg.service.project.service.base.PetitionLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.impl.PetitionLocalServiceImpl
 * @generated
 */
@ProviderType
public class PetitionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.project.service.impl.PetitionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the petition to the database. Also notifies the appropriate model listeners.
	*
	* @param petition the petition
	* @return the petition that was added
	*/
	public static eu.strasbourg.service.project.model.Petition addPetition(
		eu.strasbourg.service.project.model.Petition petition) {
		return getService().addPetition(petition);
	}

	/**
	* Crée une participation vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.project.model.Petition createPetition(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createPetition(sc);
	}

	/**
	* Creates a new petition with the primary key. Does not add the petition to the database.
	*
	* @param petitionId the primary key for the new petition
	* @return the new petition
	*/
	public static eu.strasbourg.service.project.model.Petition createPetition(
		long petitionId) {
		return getService().createPetition(petitionId);
	}

	/**
	* Deletes the petition from the database. Also notifies the appropriate model listeners.
	*
	* @param petition the petition
	* @return the petition that was removed
	*/
	public static eu.strasbourg.service.project.model.Petition deletePetition(
		eu.strasbourg.service.project.model.Petition petition) {
		return getService().deletePetition(petition);
	}

	/**
	* Deletes the petition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param petitionId the primary key of the petition
	* @return the petition that was removed
	* @throws PortalException if a petition with the primary key could not be found
	*/
	public static eu.strasbourg.service.project.model.Petition deletePetition(
		long petitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePetition(petitionId);
	}

	public static eu.strasbourg.service.project.model.Petition fetchPetition(
		long petitionId) {
		return getService().fetchPetition(petitionId);
	}

	/**
	* Returns the petition matching the UUID and group.
	*
	* @param uuid the petition's UUID
	* @param groupId the primary key of the group
	* @return the matching petition, or <code>null</code> if a matching petition could not be found
	*/
	public static eu.strasbourg.service.project.model.Petition fetchPetitionByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchPetitionByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the petition with the primary key.
	*
	* @param petitionId the primary key of the petition
	* @return the petition
	* @throws PortalException if a petition with the primary key could not be found
	*/
	public static eu.strasbourg.service.project.model.Petition getPetition(
		long petitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPetition(petitionId);
	}

	/**
	* Returns the petition matching the UUID and group.
	*
	* @param uuid the petition's UUID
	* @param groupId the primary key of the group
	* @return the matching petition
	* @throws PortalException if a matching petition could not be found
	*/
	public static eu.strasbourg.service.project.model.Petition getPetitionByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPetitionByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une petition
	*/
	public static eu.strasbourg.service.project.model.Petition removePetition(
		long petitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removePetition(petitionId);
	}

	/**
	* Updates the petition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param petition the petition
	* @return the petition that was updated
	*/
	public static eu.strasbourg.service.project.model.Petition updatePetition(
		eu.strasbourg.service.project.model.Petition petition) {
		return getService().updatePetition(petition);
	}

	public static eu.strasbourg.service.project.model.Petition updatePetition(
		eu.strasbourg.service.project.model.Petition petition,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updatePetition(petition, sc);
	}

	public static eu.strasbourg.service.project.model.Petition updateStatus(
		long userId, long petitionId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, petitionId, status, serviceContext,
			workflowContext);
	}

	/**
	* Returns the number of petitions.
	*
	* @return the number of petitions
	*/
	public static int getPetitionsCount() {
		return getService().getPetitionsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.PetitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<eu.strasbourg.service.project.model.Petition> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return getService().findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à une petition
	*/
	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return getService().getAttachedVocabularies(groupId);
	}

	/**
	* Retourne tous les petitions d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Petition> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
	}

	public static java.util.List<eu.strasbourg.service.project.model.Petition> getPetitionByPublikUserID(
		java.lang.String publikId) {
		return getService().getPetitionByPublikUserID(publikId);
	}

	public static java.util.List<eu.strasbourg.service.project.model.Petition> getPetitionBySignatairePublikId(
		java.lang.String publikId) {
		return getService().getPetitionBySignatairePublikId(publikId);
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
	public static java.util.List<eu.strasbourg.service.project.model.Petition> getPetitions(
		int start, int end) {
		return getService().getPetitions(start, end);
	}

	/**
	* Returns all the petitions matching the UUID and company.
	*
	* @param uuid the UUID of the petitions
	* @param companyId the primary key of the company
	* @return the matching petitions, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Petition> getPetitionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getPetitionsByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<eu.strasbourg.service.project.model.Petition> getPetitionsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.project.model.Petition> orderByComparator) {
		return getService()
				   .getPetitionsByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Retourne tous les petitions publiés d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Petition> getPublishedByGroupId(
		long groupId) {
		return getService().getPublishedByGroupId(groupId);
	}

	public static java.util.List<eu.strasbourg.service.project.model.Petition> getTheMostCommented(
		long groupId) {
		return getService().getTheMostCommented(groupId);
	}

	/**
	* Méthode permettant de trier les petitions
	*
	* @return
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Petition> getTheMostSigned(
		long groupId) {
		return getService().getTheMostSigned(groupId);
	}

	public static java.util.List<eu.strasbourg.service.project.model.Petition> getTheThreeLessSigned(
		long groupId) {
		return getService().getTheThreeLessSigned(groupId);
	}

	/**
	* @return
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Petition> getTheThreeMostSigned(
		long groupId) {
		return getService().getTheThreeMostSigned(groupId);
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
	* Recherche par mot clés (compte)
	*/
	public static long findByKeywordCount(java.lang.String keyword, long groupId) {
		return getService().findByKeywordCount(keyword, groupId);
	}

	public static void updateAllPetitionsStatus()
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateAllPetitionsStatus();
	}

	/**
	* Met à jour le statut du petition "manuellement" (pas via le workflow)
	*/
	public static void updateStatus(
		eu.strasbourg.service.project.model.Petition petition, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateStatus(petition, status);
	}

	public static PetitionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PetitionLocalService, PetitionLocalService> _serviceTracker =
		ServiceTrackerFactory.open(PetitionLocalService.class);
}