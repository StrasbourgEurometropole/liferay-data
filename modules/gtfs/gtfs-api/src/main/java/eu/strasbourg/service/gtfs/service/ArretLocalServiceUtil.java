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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Arret. This utility wraps
 * <code>eu.strasbourg.service.gtfs.service.impl.ArretLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Cedric Henry
 * @see ArretLocalService
 * @generated
 */
@ProviderType
public class ArretLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.gtfs.service.impl.ArretLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the arret to the database. Also notifies the appropriate model listeners.
	 *
	 * @param arret the arret
	 * @return the arret that was added
	 */
	public static eu.strasbourg.service.gtfs.model.Arret addArret(
		eu.strasbourg.service.gtfs.model.Arret arret) {

		return getService().addArret(arret);
	}

	/**
	 * Creates a new arret with the primary key. Does not add the arret to the database.
	 *
	 * @param arretId the primary key for the new arret
	 * @return the new arret
	 */
	public static eu.strasbourg.service.gtfs.model.Arret createArret(
		long arretId) {

		return getService().createArret(arretId);
	}

	/**
	 * Crée une entree avec une PK, non ajouté à la base de donnée
	 */
	public static eu.strasbourg.service.gtfs.model.Arret createArret(
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createArret(sc);
	}

	/**
	 * Deletes the arret from the database. Also notifies the appropriate model listeners.
	 *
	 * @param arret the arret
	 * @return the arret that was removed
	 */
	public static eu.strasbourg.service.gtfs.model.Arret deleteArret(
		eu.strasbourg.service.gtfs.model.Arret arret) {

		return getService().deleteArret(arret);
	}

	/**
	 * Deletes the arret with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param arretId the primary key of the arret
	 * @return the arret that was removed
	 * @throws PortalException if a arret with the primary key could not be found
	 */
	public static eu.strasbourg.service.gtfs.model.Arret deleteArret(
			long arretId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteArret(arretId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.gtfs.model.impl.ArretModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.gtfs.model.impl.ArretModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static eu.strasbourg.service.gtfs.model.Arret fetchArret(
		long arretId) {

		return getService().fetchArret(arretId);
	}

	/**
	 * Returns the arret matching the UUID and group.
	 *
	 * @param uuid the arret's UUID
	 * @param groupId the primary key of the group
	 * @return the matching arret, or <code>null</code> if a matching arret could not be found
	 */
	public static eu.strasbourg.service.gtfs.model.Arret
		fetchArretByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchArretByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Recherche par mot clés
	 */
	public static java.util.List<eu.strasbourg.service.gtfs.model.Arret>
		findByKeyword(String keyword, long groupId, int start, int end) {

		return getService().findByKeyword(keyword, groupId, start, end);
	}

	/**
	 * Recherche par mot clés (compte)
	 */
	public static long findByKeywordCount(String keyword, long groupId) {
		return getService().findByKeywordCount(keyword, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Retourne la liste de tous les arrets
	 */
	public static java.util.Map<String, eu.strasbourg.service.gtfs.model.Arret>
		getAll() {

		return getService().getAll();
	}

	/**
	 * Returns the arret with the primary key.
	 *
	 * @param arretId the primary key of the arret
	 * @return the arret
	 * @throws PortalException if a arret with the primary key could not be found
	 */
	public static eu.strasbourg.service.gtfs.model.Arret getArret(long arretId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getArret(arretId);
	}

	/**
	 * Returns the arret matching the UUID and group.
	 *
	 * @param uuid the arret's UUID
	 * @param groupId the primary key of the group
	 * @return the matching arret
	 * @throws PortalException if a matching arret could not be found
	 */
	public static eu.strasbourg.service.gtfs.model.Arret
			getArretByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getArretByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the arrets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.gtfs.model.impl.ArretModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @return the range of arrets
	 */
	public static java.util.List<eu.strasbourg.service.gtfs.model.Arret>
		getArrets(int start, int end) {

		return getService().getArrets(start, end);
	}

	/**
	 * Returns all the arrets matching the UUID and company.
	 *
	 * @param uuid the UUID of the arrets
	 * @param companyId the primary key of the company
	 * @return the matching arrets, or an empty list if no matches were found
	 */
	public static java.util.List<eu.strasbourg.service.gtfs.model.Arret>
		getArretsByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getArretsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of arrets matching the UUID and company.
	 *
	 * @param uuid the UUID of the arrets
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of arrets
	 * @param end the upper bound of the range of arrets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching arrets, or an empty list if no matches were found
	 */
	public static java.util.List<eu.strasbourg.service.gtfs.model.Arret>
		getArretsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<eu.strasbourg.service.gtfs.model.Arret> orderByComparator) {

		return getService().getArretsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of arrets.
	 *
	 * @return the number of arrets
	 */
	public static int getArretsCount() {
		return getService().getArretsCount();
	}

	/**
	 * Renvoie la liste des vocabulaires rattachés à l'entree
	 */
	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary>
		getAttachedVocabularies(long groupId) {

		return getService().getAttachedVocabularies(groupId);
	}

	/**
	 * Retourne toutes les entrees d'un groupe
	 */
	public static java.util.List<eu.strasbourg.service.gtfs.model.Arret>
		getByGroupId(long groupId) {

		return getService().getByGroupId(groupId);
	}

	/**
	 * Retourne tous les arret avec un status choisi
	 */
	public static java.util.List<eu.strasbourg.service.gtfs.model.Arret>
		getByStatus(int status) {

		return getService().getByStatus(status);
	}

	/**
	 * Retourne un arret via son stopId CTS
	 */
	public static eu.strasbourg.service.gtfs.model.Arret getByStopId(
		String stopId) {

		return getService().getByStopId(stopId);
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

	/**
	 * Supprime l'entree
	 */
	public static eu.strasbourg.service.gtfs.model.Arret removeArret(
			long arretId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().removeArret(arretId);
	}

	/**
	 * Supprime les entrees
	 */
	public static void removeArrets(
			java.util.List<eu.strasbourg.service.gtfs.model.Arret> arrets,
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().removeArrets(arrets, sc);
	}

	/**
	 * Met à jour le statut "manuellement" (pas via le workflow) des entrees
	 */
	public static void unpublishArrets(
			java.util.List<eu.strasbourg.service.gtfs.model.Arret> arrets,
			eu.strasbourg.service.gtfs.model.ImportHistoric importHistoric,
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().unpublishArrets(arrets, importHistoric, sc);
	}

	/**
	 * Updates the arret in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param arret the arret
	 * @return the arret that was updated
	 */
	public static eu.strasbourg.service.gtfs.model.Arret updateArret(
		eu.strasbourg.service.gtfs.model.Arret arret) {

		return getService().updateArret(arret);
	}

	/**
	 * Met à jour une entree et l'enregistre en base de données
	 *
	 * @throws IOException
	 */
	public static eu.strasbourg.service.gtfs.model.Arret updateArret(
			eu.strasbourg.service.gtfs.model.Arret arret,
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateArret(arret, sc);
	}

	/**
	 * Met à jour les entree donnees
	 *
	 * @throws IOException
	 */
	public static void updateArrets(
			java.util.List<eu.strasbourg.service.gtfs.model.Arret> arrets,
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateArrets(arrets, sc);
	}

	/**
	 * Met à jour le statut "manuellement" (pas via le workflow)
	 */
	public static void updateStatus(
			eu.strasbourg.service.gtfs.model.Arret arret, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateStatus(arret, status);
	}

	/**
	 * Met à jour le statut de l'entree par le framework workflow
	 */
	public static eu.strasbourg.service.gtfs.model.Arret updateStatus(
			long userId, long entryId, int status,
			com.liferay.portal.kernel.service.ServiceContext sc,
			java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateStatus(
			userId, entryId, status, sc, workflowContext);
	}

	public static ArretLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ArretLocalService, ArretLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ArretLocalService.class);

		ServiceTracker<ArretLocalService, ArretLocalService> serviceTracker =
			new ServiceTracker<ArretLocalService, ArretLocalService>(
				bundle.getBundleContext(), ArretLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}