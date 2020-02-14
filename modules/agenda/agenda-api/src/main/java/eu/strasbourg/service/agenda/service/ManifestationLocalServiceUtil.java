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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Manifestation. This utility wraps
 * <code>eu.strasbourg.service.agenda.service.impl.ManifestationLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author BenjaminBini
 * @see ManifestationLocalService
 * @generated
 */
@ProviderType
public class ManifestationLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.agenda.service.impl.ManifestationLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addEventManifestation(
		long eventId, long manifestationId) {

		getService().addEventManifestation(eventId, manifestationId);
	}

	public static void addEventManifestation(
		long eventId,
		eu.strasbourg.service.agenda.model.Manifestation manifestation) {

		getService().addEventManifestation(eventId, manifestation);
	}

	public static void addEventManifestations(
		long eventId,
		java.util.List<eu.strasbourg.service.agenda.model.Manifestation>
			manifestations) {

		getService().addEventManifestations(eventId, manifestations);
	}

	public static void addEventManifestations(
		long eventId, long[] manifestationIds) {

		getService().addEventManifestations(eventId, manifestationIds);
	}

	/**
	 * Adds the manifestation to the database. Also notifies the appropriate model listeners.
	 *
	 * @param manifestation the manifestation
	 * @return the manifestation that was added
	 */
	public static eu.strasbourg.service.agenda.model.Manifestation
		addManifestation(
			eu.strasbourg.service.agenda.model.Manifestation manifestation) {

		return getService().addManifestation(manifestation);
	}

	/**
	 * Modifie le statut de tous les manifestations au statut "SCHEDULED" qui
	 * ont une date de publication dans le futur
	 */
	public static void checkManifestations()
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().checkManifestations();
	}

	public static void clearEventManifestations(long eventId) {
		getService().clearEventManifestations(eventId);
	}

	/**
	 * Creates a new manifestation with the primary key. Does not add the manifestation to the database.
	 *
	 * @param manifestationId the primary key for the new manifestation
	 * @return the new manifestation
	 */
	public static eu.strasbourg.service.agenda.model.Manifestation
		createManifestation(long manifestationId) {

		return getService().createManifestation(manifestationId);
	}

	/**
	 * Crée un lien vide avec une PK, non ajouté à la base de donnée
	 */
	public static eu.strasbourg.service.agenda.model.Manifestation
			createManifestation(
				com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createManifestation(sc);
	}

	public static void deleteEventManifestation(
		long eventId, long manifestationId) {

		getService().deleteEventManifestation(eventId, manifestationId);
	}

	public static void deleteEventManifestation(
		long eventId,
		eu.strasbourg.service.agenda.model.Manifestation manifestation) {

		getService().deleteEventManifestation(eventId, manifestation);
	}

	public static void deleteEventManifestations(
		long eventId,
		java.util.List<eu.strasbourg.service.agenda.model.Manifestation>
			manifestations) {

		getService().deleteEventManifestations(eventId, manifestations);
	}

	public static void deleteEventManifestations(
		long eventId, long[] manifestationIds) {

		getService().deleteEventManifestations(eventId, manifestationIds);
	}

	/**
	 * Deletes the manifestation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param manifestationId the primary key of the manifestation
	 * @return the manifestation that was removed
	 * @throws PortalException if a manifestation with the primary key could not be found
	 */
	public static eu.strasbourg.service.agenda.model.Manifestation
			deleteManifestation(long manifestationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteManifestation(manifestationId);
	}

	/**
	 * Deletes the manifestation from the database. Also notifies the appropriate model listeners.
	 *
	 * @param manifestation the manifestation
	 * @return the manifestation that was removed
	 */
	public static eu.strasbourg.service.agenda.model.Manifestation
		deleteManifestation(
			eu.strasbourg.service.agenda.model.Manifestation manifestation) {

		return getService().deleteManifestation(manifestation);
	}

	/**
	 * Supprime les manifestations dépubliés depuis au moins un mois
	 */
	public static void deleteOldUnpublishedManifestations()
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteOldUnpublishedManifestations();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.agenda.model.impl.ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.agenda.model.impl.ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

	public static eu.strasbourg.service.agenda.model.Manifestation
		fetchManifestation(long manifestationId) {

		return getService().fetchManifestation(manifestationId);
	}

	/**
	 * Returns the manifestation matching the UUID and group.
	 *
	 * @param uuid the manifestation's UUID
	 * @param groupId the primary key of the group
	 * @return the matching manifestation, or <code>null</code> if a matching manifestation could not be found
	 */
	public static eu.strasbourg.service.agenda.model.Manifestation
		fetchManifestationByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchManifestationByUuidAndGroupId(uuid, groupId);
	}

	public static eu.strasbourg.service.agenda.model.Manifestation
		findByIdSource(String idSource) {

		return getService().findByIdSource(idSource);
	}

	public static java.util.List
		<eu.strasbourg.service.agenda.model.Manifestation> findByKeyword(
			String keyword, long groupId, int start, int end) {

		return getService().findByKeyword(keyword, groupId, start, end);
	}

	public static long findByKeywordCount(String keyword, long groupId) {
		return getService().findByKeywordCount(keyword, groupId);
	}

	public static eu.strasbourg.service.agenda.model.Manifestation
		findBySourceAndIdSource(String source, String idSource) {

		return getService().findBySourceAndIdSource(source, idSource);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary>
		getAttachedVocabularies(long groupId) {

		return getService().getAttachedVocabularies(groupId);
	}

	/**
	 * Retourne toutes les galeries éditions d'un groupe
	 */
	public static java.util.List
		<eu.strasbourg.service.agenda.model.Manifestation> getByGroupId(
			long groupId) {

		return getService().getByGroupId(groupId);
	}

	public static java.util.List
		<eu.strasbourg.service.agenda.model.Manifestation>
			getEventManifestations(long eventId) {

		return getService().getEventManifestations(eventId);
	}

	public static java.util.List
		<eu.strasbourg.service.agenda.model.Manifestation>
			getEventManifestations(long eventId, int start, int end) {

		return getService().getEventManifestations(eventId, start, end);
	}

	public static java.util.List
		<eu.strasbourg.service.agenda.model.Manifestation>
			getEventManifestations(
				long eventId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<eu.strasbourg.service.agenda.model.Manifestation>
						orderByComparator) {

		return getService().getEventManifestations(
			eventId, start, end, orderByComparator);
	}

	public static int getEventManifestationsCount(long eventId) {
		return getService().getEventManifestationsCount(eventId);
	}

	/**
	 * Returns the eventIds of the events associated with the manifestation.
	 *
	 * @param manifestationId the manifestationId of the manifestation
	 * @return long[] the eventIds of events associated with the manifestation
	 */
	public static long[] getEventPrimaryKeys(long manifestationId) {
		return getService().getEventPrimaryKeys(manifestationId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the manifestation with the primary key.
	 *
	 * @param manifestationId the primary key of the manifestation
	 * @return the manifestation
	 * @throws PortalException if a manifestation with the primary key could not be found
	 */
	public static eu.strasbourg.service.agenda.model.Manifestation
			getManifestation(long manifestationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getManifestation(manifestationId);
	}

	/**
	 * Returns the manifestation matching the UUID and group.
	 *
	 * @param uuid the manifestation's UUID
	 * @param groupId the primary key of the group
	 * @return the matching manifestation
	 * @throws PortalException if a matching manifestation could not be found
	 */
	public static eu.strasbourg.service.agenda.model.Manifestation
			getManifestationByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getManifestationByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the manifestations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.agenda.model.impl.ManifestationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @return the range of manifestations
	 */
	public static java.util.List
		<eu.strasbourg.service.agenda.model.Manifestation> getManifestations(
			int start, int end) {

		return getService().getManifestations(start, end);
	}

	/**
	 * Returns all the manifestations matching the UUID and company.
	 *
	 * @param uuid the UUID of the manifestations
	 * @param companyId the primary key of the company
	 * @return the matching manifestations, or an empty list if no matches were found
	 */
	public static java.util.List
		<eu.strasbourg.service.agenda.model.Manifestation>
			getManifestationsByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getManifestationsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of manifestations matching the UUID and company.
	 *
	 * @param uuid the UUID of the manifestations
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of manifestations
	 * @param end the upper bound of the range of manifestations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching manifestations, or an empty list if no matches were found
	 */
	public static java.util.List
		<eu.strasbourg.service.agenda.model.Manifestation>
			getManifestationsByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<eu.strasbourg.service.agenda.model.Manifestation>
						orderByComparator) {

		return getService().getManifestationsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of manifestations.
	 *
	 * @return the number of manifestations
	 */
	public static int getManifestationsCount() {
		return getService().getManifestationsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static boolean hasEventManifestation(
		long eventId, long manifestationId) {

		return getService().hasEventManifestation(eventId, manifestationId);
	}

	public static boolean hasEventManifestations(long eventId) {
		return getService().hasEventManifestations(eventId);
	}

	/**
	 * Delete an Event Manifestation
	 *
	 * @param manifestationId
	 The ID of the event manifestation to delete
	 * @return The deleted Manifestation
	 * @throws PortalException
	 */
	public static eu.strasbourg.service.agenda.model.Manifestation
			removeManifestation(long manifestationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().removeManifestation(manifestationId);
	}

	public static com.liferay.portal.kernel.search.Hits search(
			com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {

		return getService().search(searchContext);
	}

	public static void setEventManifestations(
		long eventId, long[] manifestationIds) {

		getService().setEventManifestations(eventId, manifestationIds);
	}

	/**
	 * Dépublie les manifestations dont la date de fin est dépassée
	 */
	public static void unpublishPastManifestations()
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().unpublishPastManifestations();
	}

	/**
	 * Updates the manifestation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param manifestation the manifestation
	 * @return the manifestation that was updated
	 */
	public static eu.strasbourg.service.agenda.model.Manifestation
		updateManifestation(
			eu.strasbourg.service.agenda.model.Manifestation manifestation) {

		return getService().updateManifestation(manifestation);
	}

	/**
	 * Met à jour un lien et l'enregistre en base de données
	 */
	public static eu.strasbourg.service.agenda.model.Manifestation
			updateManifestation(
				eu.strasbourg.service.agenda.model.Manifestation manifestation,
				com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateManifestation(manifestation, sc);
	}

	/**
	 * Met à jour le statut de la galerie par le framework workflow
	 */
	public static eu.strasbourg.service.agenda.model.Manifestation updateStatus(
			long userId, long entryId, int status,
			com.liferay.portal.kernel.service.ServiceContext sc,
			java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateStatus(
			userId, entryId, status, sc, workflowContext);
	}

	/**
	 * Met à jour le statut de la galerie "manuellement" (pas via le workflow)
	 */
	public static void updateStatus(
			eu.strasbourg.service.agenda.model.Manifestation manifestation,
			int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateStatus(manifestation, status);
	}

	public static ManifestationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ManifestationLocalService, ManifestationLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ManifestationLocalService.class);

		ServiceTracker<ManifestationLocalService, ManifestationLocalService>
			serviceTracker =
				new ServiceTracker
					<ManifestationLocalService, ManifestationLocalService>(
						bundle.getBundleContext(),
						ManifestationLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}