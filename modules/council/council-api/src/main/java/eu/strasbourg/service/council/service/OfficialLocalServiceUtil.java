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
 * Provides the local service utility for Official. This utility wraps
 * {@link eu.strasbourg.service.council.service.impl.OfficialLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see OfficialLocalService
 * @see eu.strasbourg.service.council.service.base.OfficialLocalServiceBaseImpl
 * @see eu.strasbourg.service.council.service.impl.OfficialLocalServiceImpl
 * @generated
 */
@ProviderType
public class OfficialLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.council.service.impl.OfficialLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the official to the database. Also notifies the appropriate model listeners.
	*
	* @param official the official
	* @return the official that was added
	*/
	public static eu.strasbourg.service.council.model.Official addOfficial(
		eu.strasbourg.service.council.model.Official official) {
		return getService().addOfficial(official);
	}

	/**
	* Crée une entité vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.council.model.Official createOfficial(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createOfficial(sc);
	}

	/**
	* Creates a new official with the primary key. Does not add the official to the database.
	*
	* @param officialId the primary key for the new official
	* @return the new official
	*/
	public static eu.strasbourg.service.council.model.Official createOfficial(
		long officialId) {
		return getService().createOfficial(officialId);
	}

	/**
	* Deletes the official from the database. Also notifies the appropriate model listeners.
	*
	* @param official the official
	* @return the official that was removed
	*/
	public static eu.strasbourg.service.council.model.Official deleteOfficial(
		eu.strasbourg.service.council.model.Official official) {
		return getService().deleteOfficial(official);
	}

	/**
	* Deletes the official with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param officialId the primary key of the official
	* @return the official that was removed
	* @throws PortalException if a official with the primary key could not be found
	*/
	public static eu.strasbourg.service.council.model.Official deleteOfficial(
		long officialId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOfficial(officialId);
	}

	public static eu.strasbourg.service.council.model.Official fetchOfficial(
		long officialId) {
		return getService().fetchOfficial(officialId);
	}

	/**
	* Returns the official matching the UUID and group.
	*
	* @param uuid the official's UUID
	* @param groupId the primary key of the group
	* @return the matching official, or <code>null</code> if a matching official could not be found
	*/
	public static eu.strasbourg.service.council.model.Official fetchOfficialByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchOfficialByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the official with the primary key.
	*
	* @param officialId the primary key of the official
	* @return the official
	* @throws PortalException if a official with the primary key could not be found
	*/
	public static eu.strasbourg.service.council.model.Official getOfficial(
		long officialId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOfficial(officialId);
	}

	/**
	* Returns the official matching the UUID and group.
	*
	* @param uuid the official's UUID
	* @param groupId the primary key of the group
	* @return the matching official
	* @throws PortalException if a matching official could not be found
	*/
	public static eu.strasbourg.service.council.model.Official getOfficialByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOfficialByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une entité
	*/
	public static eu.strasbourg.service.council.model.Official removeOfficial(
		long officialId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeOfficial(officialId);
	}

	/**
	* Updates the official in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param official the official
	* @return the official that was updated
	*/
	public static eu.strasbourg.service.council.model.Official updateOfficial(
		eu.strasbourg.service.council.model.Official official) {
		return getService().updateOfficial(official);
	}

	/**
	* Met à jour une entité et l'enregistre en base de données
	*/
	public static eu.strasbourg.service.council.model.Official updateOfficial(
		eu.strasbourg.service.council.model.Official official,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateOfficial(official, sc);
	}

	/**
	* Met à jour le statut de l'entité par le framework workflow
	*/
	public static eu.strasbourg.service.council.model.Official updateStatus(
		long userId, long entryId, int status,
		com.liferay.portal.kernel.service.ServiceContext sc,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, entryId, status, sc, workflowContext);
	}

	/**
	* Returns the number of officials.
	*
	* @return the number of officials
	*/
	public static int getOfficialsCount() {
		return getService().getOfficialsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.OfficialModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.OfficialModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static java.util.List<eu.strasbourg.service.council.model.Official> findByEmail(
		java.lang.String email) {
		return getService().findByEmail(email);
	}

	/**
	* Recherche par site et activité ou non de l'élu
	*/
	public static java.util.List<eu.strasbourg.service.council.model.Official> findByGroupIdAndIsActive(
		long groupId, boolean isActive) {
		return getService().findByGroupIdAndIsActive(groupId, isActive);
	}

	/**
	* Recherche par site, activité ou non de l'élu et type
	*/
	public static java.util.List<eu.strasbourg.service.council.model.Official> findByGroupIdAndIsActiveAndType(
		long groupId, boolean isActive, java.lang.String type) {
		return getService()
				   .findByGroupIdAndIsActiveAndType(groupId, isActive, type);
	}

	/**
	* Returns a range of all the officials.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.OfficialModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of officials
	* @param end the upper bound of the range of officials (not inclusive)
	* @return the range of officials
	*/
	public static java.util.List<eu.strasbourg.service.council.model.Official> getOfficials(
		int start, int end) {
		return getService().getOfficials(start, end);
	}

	/**
	* Returns all the officials matching the UUID and company.
	*
	* @param uuid the UUID of the officials
	* @param companyId the primary key of the company
	* @return the matching officials, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.council.model.Official> getOfficialsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getOfficialsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of officials matching the UUID and company.
	*
	* @param uuid the UUID of the officials
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of officials
	* @param end the upper bound of the range of officials (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching officials, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.council.model.Official> getOfficialsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.council.model.Official> orderByComparator) {
		return getService()
				   .getOfficialsByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
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
		eu.strasbourg.service.council.model.Official official, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateStatus(official, status);
	}

	public static OfficialLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OfficialLocalService, OfficialLocalService> _serviceTracker =
		ServiceTrackerFactory.open(OfficialLocalService.class);
}