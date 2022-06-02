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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for AnonymisationHistoric. This utility wraps
 * <code>eu.strasbourg.service.oidc.service.impl.AnonymisationHistoricLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AnonymisationHistoricLocalService
 * @generated
 */
public class AnonymisationHistoricLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.oidc.service.impl.AnonymisationHistoricLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the anonymisation historic to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnonymisationHistoricLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param anonymisationHistoric the anonymisation historic
	 * @return the anonymisation historic that was added
	 */
	public static eu.strasbourg.service.oidc.model.AnonymisationHistoric
		addAnonymisationHistoric(
			eu.strasbourg.service.oidc.model.AnonymisationHistoric
				anonymisationHistoric) {

		return getService().addAnonymisationHistoric(anonymisationHistoric);
	}

	/**
	 * Creates a new anonymisation historic with the primary key. Does not add the anonymisation historic to the database.
	 *
	 * @param anonymisationHistoricId the primary key for the new anonymisation historic
	 * @return the new anonymisation historic
	 */
	public static eu.strasbourg.service.oidc.model.AnonymisationHistoric
		createAnonymisationHistoric(long anonymisationHistoricId) {

		return getService().createAnonymisationHistoric(
			anonymisationHistoricId);
	}

	/**
	 * Crée une entree d'anonymisation vide avec une PK, non ajouté à la base de donnée
	 */
	public static eu.strasbourg.service.oidc.model.AnonymisationHistoric
			createAnonymisationHistoric(
				com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createAnonymisationHistoric(sc);
	}

	/**
	 * Deletes the anonymisation historic from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnonymisationHistoricLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param anonymisationHistoric the anonymisation historic
	 * @return the anonymisation historic that was removed
	 */
	public static eu.strasbourg.service.oidc.model.AnonymisationHistoric
		deleteAnonymisationHistoric(
			eu.strasbourg.service.oidc.model.AnonymisationHistoric
				anonymisationHistoric) {

		return getService().deleteAnonymisationHistoric(anonymisationHistoric);
	}

	/**
	 * Deletes the anonymisation historic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnonymisationHistoricLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param anonymisationHistoricId the primary key of the anonymisation historic
	 * @return the anonymisation historic that was removed
	 * @throws PortalException if a anonymisation historic with the primary key could not be found
	 */
	public static eu.strasbourg.service.oidc.model.AnonymisationHistoric
			deleteAnonymisationHistoric(long anonymisationHistoricId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteAnonymisationHistoric(
			anonymisationHistoricId);
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

	/**
	 * Effectue l'anonymisation des donnees issues des fichiers GTFS
	 */
	public static void doAnonymisation(
		com.liferay.portal.kernel.service.ServiceContext sc,
		eu.strasbourg.service.oidc.model.AnonymisationHistoric
			anonymisationHistoric) {

		getService().doAnonymisation(sc, anonymisationHistoric);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.oidc.model.impl.AnonymisationHistoricModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.oidc.model.impl.AnonymisationHistoricModelImpl</code>.
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

	public static eu.strasbourg.service.oidc.model.AnonymisationHistoric
		fetchAnonymisationHistoric(long anonymisationHistoricId) {

		return getService().fetchAnonymisationHistoric(anonymisationHistoricId);
	}

	/**
	 * Returns the anonymisation historic matching the UUID and group.
	 *
	 * @param uuid the anonymisation historic's UUID
	 * @param groupId the primary key of the group
	 * @return the matching anonymisation historic, or <code>null</code> if a matching anonymisation historic could not be found
	 */
	public static eu.strasbourg.service.oidc.model.AnonymisationHistoric
		fetchAnonymisationHistoricByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchAnonymisationHistoricByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Recherche par mot clés
	 */
	public static java.util.List
		<eu.strasbourg.service.oidc.model.AnonymisationHistoric> findByKeyword(
			String keyword, long groupId, int start, int end) {

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
	 * Returns the anonymisation historic with the primary key.
	 *
	 * @param anonymisationHistoricId the primary key of the anonymisation historic
	 * @return the anonymisation historic
	 * @throws PortalException if a anonymisation historic with the primary key could not be found
	 */
	public static eu.strasbourg.service.oidc.model.AnonymisationHistoric
			getAnonymisationHistoric(long anonymisationHistoricId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAnonymisationHistoric(anonymisationHistoricId);
	}

	/**
	 * Returns the anonymisation historic matching the UUID and group.
	 *
	 * @param uuid the anonymisation historic's UUID
	 * @param groupId the primary key of the group
	 * @return the matching anonymisation historic
	 * @throws PortalException if a matching anonymisation historic could not be found
	 */
	public static eu.strasbourg.service.oidc.model.AnonymisationHistoric
			getAnonymisationHistoricByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAnonymisationHistoricByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the anonymisation historics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.oidc.model.impl.AnonymisationHistoricModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of anonymisation historics
	 * @param end the upper bound of the range of anonymisation historics (not inclusive)
	 * @return the range of anonymisation historics
	 */
	public static java.util.List
		<eu.strasbourg.service.oidc.model.AnonymisationHistoric>
			getAnonymisationHistorics(int start, int end) {

		return getService().getAnonymisationHistorics(start, end);
	}

	/**
	 * Returns all the anonymisation historics matching the UUID and company.
	 *
	 * @param uuid the UUID of the anonymisation historics
	 * @param companyId the primary key of the company
	 * @return the matching anonymisation historics, or an empty list if no matches were found
	 */
	public static java.util.List
		<eu.strasbourg.service.oidc.model.AnonymisationHistoric>
			getAnonymisationHistoricsByUuidAndCompanyId(
				String uuid, long companyId) {

		return getService().getAnonymisationHistoricsByUuidAndCompanyId(
			uuid, companyId);
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
	public static java.util.List
		<eu.strasbourg.service.oidc.model.AnonymisationHistoric>
			getAnonymisationHistoricsByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<eu.strasbourg.service.oidc.model.AnonymisationHistoric>
						orderByComparator) {

		return getService().getAnonymisationHistoricsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of anonymisation historics.
	 *
	 * @return the number of anonymisation historics
	 */
	public static int getAnonymisationHistoricsCount() {
		return getService().getAnonymisationHistoricsCount();
	}

	/**
	 * Renvoie la liste des vocabulaires rattachés à l'anonymisation
	 */
	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary>
		getAttachedVocabularies(long groupId) {

		return getService().getAttachedVocabularies(groupId);
	}

	/**
	 * Retourne toutes les anonymisations d'un groupe
	 */
	public static java.util.List
		<eu.strasbourg.service.oidc.model.AnonymisationHistoric> getByGroupId(
			long groupId) {

		return getService().getByGroupId(groupId);
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

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Supprime l'entree d'anonymisation
	 */
	public static eu.strasbourg.service.oidc.model.AnonymisationHistoric
			removeAnonymisationHistoric(long anonymisationHistoricId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().removeAnonymisationHistoric(
			anonymisationHistoricId);
	}

	/**
	 * Updates the anonymisation historic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect AnonymisationHistoricLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param anonymisationHistoric the anonymisation historic
	 * @return the anonymisation historic that was updated
	 */
	public static eu.strasbourg.service.oidc.model.AnonymisationHistoric
		updateAnonymisationHistoric(
			eu.strasbourg.service.oidc.model.AnonymisationHistoric
				anonymisationHistoric) {

		return getService().updateAnonymisationHistoric(anonymisationHistoric);
	}

	/**
	 * Met à jour une entree d'anonymisation et l'enregistre en base de données
	 *
	 * @throws IOException
	 */
	public static eu.strasbourg.service.oidc.model.AnonymisationHistoric
			updateAnonymisationHistoric(
				eu.strasbourg.service.oidc.model.AnonymisationHistoric
					anonymisationHistoric,
				com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateAnonymisationHistoric(
			anonymisationHistoric, sc);
	}

	/**
	 * Met à jour le statut de l'entree d'anonymisation "manuellement" (pas via le workflow)
	 */
	public static void updateStatus(
			eu.strasbourg.service.oidc.model.AnonymisationHistoric
				anonymisationHistoric,
			int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateStatus(anonymisationHistoric, status);
	}

	/**
	 * Met à jour le statut de l'entree d'anonymisation par le framework workflow
	 */
	public static eu.strasbourg.service.oidc.model.AnonymisationHistoric
			updateStatus(
				long userId, long entryId, int status,
				com.liferay.portal.kernel.service.ServiceContext sc,
				java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateStatus(
			userId, entryId, status, sc, workflowContext);
	}

	public static AnonymisationHistoricLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<AnonymisationHistoricLocalService, AnonymisationHistoricLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			AnonymisationHistoricLocalService.class);

		ServiceTracker
			<AnonymisationHistoricLocalService,
			 AnonymisationHistoricLocalService> serviceTracker =
				new ServiceTracker
					<AnonymisationHistoricLocalService,
					 AnonymisationHistoricLocalService>(
						 bundle.getBundleContext(),
						 AnonymisationHistoricLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}