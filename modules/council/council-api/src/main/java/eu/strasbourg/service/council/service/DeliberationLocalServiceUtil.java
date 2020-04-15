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

package eu.strasbourg.service.council.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Deliberation. This utility wraps
 * {@link eu.strasbourg.service.council.service.impl.DeliberationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see DeliberationLocalService
 * @see eu.strasbourg.service.council.service.base.DeliberationLocalServiceBaseImpl
 * @see eu.strasbourg.service.council.service.impl.DeliberationLocalServiceImpl
 * @generated
 */
@ProviderType
public class DeliberationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.council.service.impl.DeliberationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the deliberation to the database. Also notifies the appropriate model listeners.
	*
	* @param deliberation the deliberation
	* @return the deliberation that was added
	*/
	public static eu.strasbourg.service.council.model.Deliberation addDeliberation(
		eu.strasbourg.service.council.model.Deliberation deliberation) {
		return getService().addDeliberation(deliberation);
	}

	/**
	* Crée une entité vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.council.model.Deliberation createDeliberation(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createDeliberation(sc);
	}

	/**
	* Creates a new deliberation with the primary key. Does not add the deliberation to the database.
	*
	* @param deliberationId the primary key for the new deliberation
	* @return the new deliberation
	*/
	public static eu.strasbourg.service.council.model.Deliberation createDeliberation(
		long deliberationId) {
		return getService().createDeliberation(deliberationId);
	}

	/**
	* Deletes the deliberation from the database. Also notifies the appropriate model listeners.
	*
	* @param deliberation the deliberation
	* @return the deliberation that was removed
	*/
	public static eu.strasbourg.service.council.model.Deliberation deleteDeliberation(
		eu.strasbourg.service.council.model.Deliberation deliberation) {
		return getService().deleteDeliberation(deliberation);
	}

	/**
	* Deletes the deliberation with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliberationId the primary key of the deliberation
	* @return the deliberation that was removed
	* @throws PortalException if a deliberation with the primary key could not be found
	*/
	public static eu.strasbourg.service.council.model.Deliberation deleteDeliberation(
		long deliberationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDeliberation(deliberationId);
	}

	public static eu.strasbourg.service.council.model.Deliberation fetchDeliberation(
		long deliberationId) {
		return getService().fetchDeliberation(deliberationId);
	}

	/**
	* Returns the deliberation matching the UUID and group.
	*
	* @param uuid the deliberation's UUID
	* @param groupId the primary key of the group
	* @return the matching deliberation, or <code>null</code> if a matching deliberation could not be found
	*/
	public static eu.strasbourg.service.council.model.Deliberation fetchDeliberationByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchDeliberationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the deliberation with the primary key.
	*
	* @param deliberationId the primary key of the deliberation
	* @return the deliberation
	* @throws PortalException if a deliberation with the primary key could not be found
	*/
	public static eu.strasbourg.service.council.model.Deliberation getDeliberation(
		long deliberationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDeliberation(deliberationId);
	}

	/**
	* Returns the deliberation matching the UUID and group.
	*
	* @param uuid the deliberation's UUID
	* @param groupId the primary key of the group
	* @return the matching deliberation
	* @throws PortalException if a matching deliberation could not be found
	*/
	public static eu.strasbourg.service.council.model.Deliberation getDeliberationByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDeliberationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une entité
	*/
	public static eu.strasbourg.service.council.model.Deliberation removeDeliberation(
		long deliberationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeDeliberation(deliberationId);
	}

	/**
	* Updates the deliberation in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param deliberation the deliberation
	* @return the deliberation that was updated
	*/
	public static eu.strasbourg.service.council.model.Deliberation updateDeliberation(
		eu.strasbourg.service.council.model.Deliberation deliberation) {
		return getService().updateDeliberation(deliberation);
	}

	/**
	* Met à jour un projet et l'enregistre en base de données
	*/
	public static eu.strasbourg.service.council.model.Deliberation updateDeliberation(
		eu.strasbourg.service.council.model.Deliberation deliberation,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateDeliberation(deliberation, sc);
	}

	/**
	* Met à jour le statut de l'entité par le framework workflow
	*/
	public static eu.strasbourg.service.council.model.Deliberation updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, entryId, status, sc, workflowContext);
	}

	/**
	* Returns the number of deliberations.
	*
	* @return the number of deliberations
	*/
	public static int getDeliberationsCount() {
		return getService().getDeliberationsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.DeliberationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.DeliberationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Recherche par ID de CouncilSession
	*/
	public static java.util.List<eu.strasbourg.service.council.model.Deliberation> findByCouncilSessionId(
		long councilSessionId) {
		return getService().findByCouncilSessionId(councilSessionId);
	}

	/**
	* Returns a range of all the deliberations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.DeliberationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of deliberations
	* @param end the upper bound of the range of deliberations (not inclusive)
	* @return the range of deliberations
	*/
	public static java.util.List<eu.strasbourg.service.council.model.Deliberation> getDeliberations(
		int start, int end) {
		return getService().getDeliberations(start, end);
	}

	/**
	* Returns all the deliberations matching the UUID and company.
	*
	* @param uuid the UUID of the deliberations
	* @param companyId the primary key of the company
	* @return the matching deliberations, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.council.model.Deliberation> getDeliberationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getDeliberationsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of deliberations matching the UUID and company.
	*
	* @param uuid the UUID of the deliberations
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of deliberations
	* @param end the upper bound of the range of deliberations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching deliberations, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.council.model.Deliberation> getDeliberationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.council.model.Deliberation> orderByComparator) {
		return getService()
				   .getDeliberationsByUuidAndCompanyId(uuid, companyId, start,
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
	* Met à jour le statut de l'entité "manuellement" (pas via le workflow)
	*/
	public static void updateStatus(
		eu.strasbourg.service.council.model.Deliberation deliberation,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateStatus(deliberation, status);
	}

	public static DeliberationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DeliberationLocalService, DeliberationLocalService> _serviceTracker =
		ServiceTrackerFactory.open(DeliberationLocalService.class);
}