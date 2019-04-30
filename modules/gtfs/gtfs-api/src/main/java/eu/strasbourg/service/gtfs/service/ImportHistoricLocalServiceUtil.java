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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for ImportHistoric. This utility wraps
 * {@link eu.strasbourg.service.gtfs.service.impl.ImportHistoricLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Cedric Henry
 * @see ImportHistoricLocalService
 * @see eu.strasbourg.service.gtfs.service.base.ImportHistoricLocalServiceBaseImpl
 * @see eu.strasbourg.service.gtfs.service.impl.ImportHistoricLocalServiceImpl
 * @generated
 */
@ProviderType
public class ImportHistoricLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.gtfs.service.impl.ImportHistoricLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the import historic to the database. Also notifies the appropriate model listeners.
	*
	* @param importHistoric the import historic
	* @return the import historic that was added
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistoric addImportHistoric(
		eu.strasbourg.service.gtfs.model.ImportHistoric importHistoric) {
		return getService().addImportHistoric(importHistoric);
	}

	/**
	* Crée une entree d'import vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistoric createImportHistoric(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createImportHistoric(sc);
	}

	/**
	* Creates a new import historic with the primary key. Does not add the import historic to the database.
	*
	* @param importHistoricId the primary key for the new import historic
	* @return the new import historic
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistoric createImportHistoric(
		long importHistoricId) {
		return getService().createImportHistoric(importHistoricId);
	}

	/**
	* Deletes the import historic from the database. Also notifies the appropriate model listeners.
	*
	* @param importHistoric the import historic
	* @return the import historic that was removed
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistoric deleteImportHistoric(
		eu.strasbourg.service.gtfs.model.ImportHistoric importHistoric) {
		return getService().deleteImportHistoric(importHistoric);
	}

	/**
	* Deletes the import historic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param importHistoricId the primary key of the import historic
	* @return the import historic that was removed
	* @throws PortalException if a import historic with the primary key could not be found
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistoric deleteImportHistoric(
		long importHistoricId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteImportHistoric(importHistoricId);
	}

	public static eu.strasbourg.service.gtfs.model.ImportHistoric fetchImportHistoric(
		long importHistoricId) {
		return getService().fetchImportHistoric(importHistoricId);
	}

	/**
	* Returns the import historic matching the UUID and group.
	*
	* @param uuid the import historic's UUID
	* @param groupId the primary key of the group
	* @return the matching import historic, or <code>null</code> if a matching import historic could not be found
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistoric fetchImportHistoricByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchImportHistoricByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the import historic with the primary key.
	*
	* @param importHistoricId the primary key of the import historic
	* @return the import historic
	* @throws PortalException if a import historic with the primary key could not be found
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistoric getImportHistoric(
		long importHistoricId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getImportHistoric(importHistoricId);
	}

	/**
	* Returns the import historic matching the UUID and group.
	*
	* @param uuid the import historic's UUID
	* @param groupId the primary key of the group
	* @return the matching import historic
	* @throws PortalException if a matching import historic could not be found
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistoric getImportHistoricByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getImportHistoricByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime l'entree d'import
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistoric removeImportHistoric(
		long importHistoricId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeImportHistoric(importHistoricId);
	}

	/**
	* Updates the import historic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param importHistoric the import historic
	* @return the import historic that was updated
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistoric updateImportHistoric(
		eu.strasbourg.service.gtfs.model.ImportHistoric importHistoric) {
		return getService().updateImportHistoric(importHistoric);
	}

	/**
	* Met à jour une entree d'import et l'enregistre en base de données
	*
	* @throws IOException
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistoric updateImportHistoric(
		eu.strasbourg.service.gtfs.model.ImportHistoric importHistoric,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateImportHistoric(importHistoric, sc);
	}

	/**
	* Met à jour le statut de l'entree d'import par le framework workflow
	*/
	public static eu.strasbourg.service.gtfs.model.ImportHistoric updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, entryId, status, sc, workflowContext);
	}

	/**
	* Returns the number of import historics.
	*
	* @return the number of import historics
	*/
	public static int getImportHistoricsCount() {
		return getService().getImportHistoricsCount();
	}

	/**
	* Effectue l'import des donnees issues des fichiers GTFS
	*/
	public static java.lang.String doImportGTFS() {
		return getService().doImportGTFS();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Renvoie la liste des vocabulaires rattachés à un projet
	*/
	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return getService().getAttachedVocabularies(groupId);
	}

	/**
	* Retourne tous les projets d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.gtfs.model.ImportHistoric> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
	}

	/**
	* Returns a range of all the import historics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.gtfs.model.impl.ImportHistoricModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of import historics
	* @param end the upper bound of the range of import historics (not inclusive)
	* @return the range of import historics
	*/
	public static java.util.List<eu.strasbourg.service.gtfs.model.ImportHistoric> getImportHistorics(
		int start, int end) {
		return getService().getImportHistorics(start, end);
	}

	/**
	* Returns all the import historics matching the UUID and company.
	*
	* @param uuid the UUID of the import historics
	* @param companyId the primary key of the company
	* @return the matching import historics, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.gtfs.model.ImportHistoric> getImportHistoricsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getImportHistoricsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of import historics matching the UUID and company.
	*
	* @param uuid the UUID of the import historics
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of import historics
	* @param end the upper bound of the range of import historics (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching import historics, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.gtfs.model.ImportHistoric> getImportHistoricsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.gtfs.model.ImportHistoric> orderByComparator) {
		return getService()
				   .getImportHistoricsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
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
	* Met à jour le statut de l'entree d'import "manuellement" (pas via le workflow)
	*/
	public static void updateStatus(
		eu.strasbourg.service.gtfs.model.ImportHistoric importHistoric,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateStatus(importHistoric, status);
	}

	public static ImportHistoricLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ImportHistoricLocalService, ImportHistoricLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ImportHistoricLocalService.class);
}