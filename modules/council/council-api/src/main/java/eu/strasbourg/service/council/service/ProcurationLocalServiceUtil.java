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
 * Provides the local service utility for Procuration. This utility wraps
 * {@link eu.strasbourg.service.council.service.impl.ProcurationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ProcurationLocalService
 * @see eu.strasbourg.service.council.service.base.ProcurationLocalServiceBaseImpl
 * @see eu.strasbourg.service.council.service.impl.ProcurationLocalServiceImpl
 * @generated
 */
@ProviderType
public class ProcurationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.council.service.impl.ProcurationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the procuration to the database. Also notifies the appropriate model listeners.
	*
	* @param procuration the procuration
	* @return the procuration that was added
	*/
	public static eu.strasbourg.service.council.model.Procuration addProcuration(
		eu.strasbourg.service.council.model.Procuration procuration) {
		return getService().addProcuration(procuration);
	}

	/**
	* Crée une entité vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.council.model.Procuration createProcuration(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createProcuration(sc);
	}

	/**
	* Creates a new procuration with the primary key. Does not add the procuration to the database.
	*
	* @param procurationId the primary key for the new procuration
	* @return the new procuration
	*/
	public static eu.strasbourg.service.council.model.Procuration createProcuration(
		long procurationId) {
		return getService().createProcuration(procurationId);
	}

	/**
	* Deletes the procuration from the database. Also notifies the appropriate model listeners.
	*
	* @param procuration the procuration
	* @return the procuration that was removed
	*/
	public static eu.strasbourg.service.council.model.Procuration deleteProcuration(
		eu.strasbourg.service.council.model.Procuration procuration) {
		return getService().deleteProcuration(procuration);
	}

	/**
	* Deletes the procuration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param procurationId the primary key of the procuration
	* @return the procuration that was removed
	* @throws PortalException if a procuration with the primary key could not be found
	*/
	public static eu.strasbourg.service.council.model.Procuration deleteProcuration(
		long procurationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteProcuration(procurationId);
	}

	public static eu.strasbourg.service.council.model.Procuration fetchProcuration(
		long procurationId) {
		return getService().fetchProcuration(procurationId);
	}

	/**
	* Returns the procuration matching the UUID and group.
	*
	* @param uuid the procuration's UUID
	* @param groupId the primary key of the group
	* @return the matching procuration, or <code>null</code> if a matching procuration could not be found
	*/
	public static eu.strasbourg.service.council.model.Procuration fetchProcurationByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchProcurationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Recherche par ID de CouncilSession, élu et mandataire,
	*/
	public static eu.strasbourg.service.council.model.Procuration findByCouncilSessionIdAndOfficialVotersAndUnavailableIds(
		long councilSessionId, long officialVoter, long officialUnavailableId) {
		return getService()
				   .findByCouncilSessionIdAndOfficialVotersAndUnavailableIds(councilSessionId,
			officialVoter, officialUnavailableId);
	}

	/**
	* Returns the procuration with the primary key.
	*
	* @param procurationId the primary key of the procuration
	* @return the procuration
	* @throws PortalException if a procuration with the primary key could not be found
	*/
	public static eu.strasbourg.service.council.model.Procuration getProcuration(
		long procurationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProcuration(procurationId);
	}

	/**
	* Returns the procuration matching the UUID and group.
	*
	* @param uuid the procuration's UUID
	* @param groupId the primary key of the group
	* @return the matching procuration
	* @throws PortalException if a matching procuration could not be found
	*/
	public static eu.strasbourg.service.council.model.Procuration getProcurationByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProcurationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une entité
	*/
	public static eu.strasbourg.service.council.model.Procuration removeProcuration(
		long procurationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeProcuration(procurationId);
	}

	/**
	* Updates the procuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param procuration the procuration
	* @return the procuration that was updated
	*/
	public static eu.strasbourg.service.council.model.Procuration updateProcuration(
		eu.strasbourg.service.council.model.Procuration procuration) {
		return getService().updateProcuration(procuration);
	}

	/**
	* Met à jour une entité et l'enregistre en base de données
	*/
	public static eu.strasbourg.service.council.model.Procuration updateProcuration(
		eu.strasbourg.service.council.model.Procuration procuration,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateProcuration(procuration, sc);
	}

	/**
	* Met à jour le statut de l'entité par le framework workflow
	*/
	public static eu.strasbourg.service.council.model.Procuration updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, entryId, status, sc, workflowContext);
	}

	/**
	* Returns the number of procurations.
	*
	* @return the number of procurations
	*/
	public static int getProcurationsCount() {
		return getService().getProcurationsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.ProcurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.ProcurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static java.util.List<eu.strasbourg.service.council.model.Procuration> findByCouncilSessionId(
		long councilSessionId) {
		return getService().findByCouncilSessionId(councilSessionId);
	}

	/**
	* Recherche par ID de CouncilSession et Mandataire
	*/
	public static java.util.List<eu.strasbourg.service.council.model.Procuration> findByCouncilSessionIdAndOfficialVotersId(
		long councilSessionId, long officialVoter) {
		return getService()
				   .findByCouncilSessionIdAndOfficialVotersId(councilSessionId,
			officialVoter);
	}

	/**
	* Returns a range of all the procurations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.ProcurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of procurations
	* @param end the upper bound of the range of procurations (not inclusive)
	* @return the range of procurations
	*/
	public static java.util.List<eu.strasbourg.service.council.model.Procuration> getProcurations(
		int start, int end) {
		return getService().getProcurations(start, end);
	}

	/**
	* Returns all the procurations matching the UUID and company.
	*
	* @param uuid the UUID of the procurations
	* @param companyId the primary key of the company
	* @return the matching procurations, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.council.model.Procuration> getProcurationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getProcurationsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of procurations matching the UUID and company.
	*
	* @param uuid the UUID of the procurations
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of procurations
	* @param end the upper bound of the range of procurations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching procurations, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.council.model.Procuration> getProcurationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.council.model.Procuration> orderByComparator) {
		return getService()
				   .getProcurationsByUuidAndCompanyId(uuid, companyId, start,
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
		eu.strasbourg.service.council.model.Procuration procuration, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateStatus(procuration, status);
	}

	public static ProcurationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcurationLocalService, ProcurationLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ProcurationLocalService.class);
}