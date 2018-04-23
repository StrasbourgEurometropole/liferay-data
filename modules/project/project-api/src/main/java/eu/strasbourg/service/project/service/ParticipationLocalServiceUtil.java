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
 * Provides the local service utility for Participation. This utility wraps
 * {@link eu.strasbourg.service.project.service.impl.ParticipationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Cedric Henry
 * @see ParticipationLocalService
 * @see eu.strasbourg.service.project.service.base.ParticipationLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.impl.ParticipationLocalServiceImpl
 * @generated
 */
@ProviderType
public class ParticipationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.project.service.impl.ParticipationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the participation to the database. Also notifies the appropriate model listeners.
	*
	* @param participation the participation
	* @return the participation that was added
	*/
	public static eu.strasbourg.service.project.model.Participation addParticipation(
		eu.strasbourg.service.project.model.Participation participation) {
		return getService().addParticipation(participation);
	}

	/**
	* Crée une participation vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.project.model.Participation createParticipation(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createParticipation(sc);
	}

	/**
	* Creates a new participation with the primary key. Does not add the participation to the database.
	*
	* @param participationId the primary key for the new participation
	* @return the new participation
	*/
	public static eu.strasbourg.service.project.model.Participation createParticipation(
		long participationId) {
		return getService().createParticipation(participationId);
	}

	/**
	* Deletes the participation from the database. Also notifies the appropriate model listeners.
	*
	* @param participation the participation
	* @return the participation that was removed
	*/
	public static eu.strasbourg.service.project.model.Participation deleteParticipation(
		eu.strasbourg.service.project.model.Participation participation) {
		return getService().deleteParticipation(participation);
	}

	/**
	* Deletes the participation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param participationId the primary key of the participation
	* @return the participation that was removed
	* @throws PortalException if a participation with the primary key could not be found
	*/
	public static eu.strasbourg.service.project.model.Participation deleteParticipation(
		long participationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteParticipation(participationId);
	}

	public static eu.strasbourg.service.project.model.Participation fetchParticipation(
		long participationId) {
		return getService().fetchParticipation(participationId);
	}

	/**
	* Returns the participation matching the UUID and group.
	*
	* @param uuid the participation's UUID
	* @param groupId the primary key of the group
	* @return the matching participation, or <code>null</code> if a matching participation could not be found
	*/
	public static eu.strasbourg.service.project.model.Participation fetchParticipationByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchParticipationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the participation with the primary key.
	*
	* @param participationId the primary key of the participation
	* @return the participation
	* @throws PortalException if a participation with the primary key could not be found
	*/
	public static eu.strasbourg.service.project.model.Participation getParticipation(
		long participationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getParticipation(participationId);
	}

	/**
	* Returns the participation matching the UUID and group.
	*
	* @param uuid the participation's UUID
	* @param groupId the primary key of the group
	* @return the matching participation
	* @throws PortalException if a matching participation could not be found
	*/
	public static eu.strasbourg.service.project.model.Participation getParticipationByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getParticipationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une participation
	*/
	public static eu.strasbourg.service.project.model.Participation removeParticipation(
		long participationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeParticipation(participationId);
	}

	/**
	* Updates the participation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param participation the participation
	* @return the participation that was updated
	*/
	public static eu.strasbourg.service.project.model.Participation updateParticipation(
		eu.strasbourg.service.project.model.Participation participation) {
		return getService().updateParticipation(participation);
	}

	/**
	* Met à jour une participation et l'enregistre en base de données
	*/
	public static eu.strasbourg.service.project.model.Participation updateParticipation(
		eu.strasbourg.service.project.model.Participation participation,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateParticipation(participation, sc);
	}

	/**
	* Met à jour le statut de la participation par le framework workflow
	*/
	public static eu.strasbourg.service.project.model.Participation updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, entryId, status, sc, workflowContext);
	}

	/**
	* Returns the number of participations.
	*
	* @return the number of participations
	*/
	public static int getParticipationsCount() {
		return getService().getParticipationsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Renvoie la liste des vocabulaires rattachés à une participation
	*/
	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return getService().getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les participations d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Participation> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
	}

	/**
	* Returns a range of all the participations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.ParticipationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @return the range of participations
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Participation> getParticipations(
		int start, int end) {
		return getService().getParticipations(start, end);
	}

	/**
	* Returns all the participations matching the UUID and company.
	*
	* @param uuid the UUID of the participations
	* @param companyId the primary key of the company
	* @return the matching participations, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Participation> getParticipationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getParticipationsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of participations matching the UUID and company.
	*
	* @param uuid the UUID of the participations
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of participations
	* @param end the upper bound of the range of participations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching participations, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Participation> getParticipationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.project.model.Participation> orderByComparator) {
		return getService()
				   .getParticipationsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
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
	* Met à jour le statut de la participation "manuellement" (pas via le workflow)
	*/
	public static void updateStatus(
		eu.strasbourg.service.project.model.Participation participation,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateStatus(participation, status);
	}

	public static ParticipationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ParticipationLocalService, ParticipationLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ParticipationLocalService.class);
}