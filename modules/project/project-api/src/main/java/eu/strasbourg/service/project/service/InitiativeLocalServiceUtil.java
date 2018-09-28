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
 * Provides the local service utility for Initiative. This utility wraps
 * {@link eu.strasbourg.service.project.service.impl.InitiativeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Cedric Henry
 * @see InitiativeLocalService
 * @see eu.strasbourg.service.project.service.base.InitiativeLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.impl.InitiativeLocalServiceImpl
 * @generated
 */
@ProviderType
public class InitiativeLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.project.service.impl.InitiativeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the initiative to the database. Also notifies the appropriate model listeners.
	*
	* @param initiative the initiative
	* @return the initiative that was added
	*/
	public static eu.strasbourg.service.project.model.Initiative addInitiative(
		eu.strasbourg.service.project.model.Initiative initiative) {
		return getService().addInitiative(initiative);
	}

	/**
	* Crée une initiative vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.project.model.Initiative createInitiative(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createInitiative(sc);
	}

	/**
	* Creates a new initiative with the primary key. Does not add the initiative to the database.
	*
	* @param initiativeId the primary key for the new initiative
	* @return the new initiative
	*/
	public static eu.strasbourg.service.project.model.Initiative createInitiative(
		long initiativeId) {
		return getService().createInitiative(initiativeId);
	}

	/**
	* Deletes the initiative from the database. Also notifies the appropriate model listeners.
	*
	* @param initiative the initiative
	* @return the initiative that was removed
	*/
	public static eu.strasbourg.service.project.model.Initiative deleteInitiative(
		eu.strasbourg.service.project.model.Initiative initiative) {
		return getService().deleteInitiative(initiative);
	}

	/**
	* Deletes the initiative with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param initiativeId the primary key of the initiative
	* @return the initiative that was removed
	* @throws PortalException if a initiative with the primary key could not be found
	*/
	public static eu.strasbourg.service.project.model.Initiative deleteInitiative(
		long initiativeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteInitiative(initiativeId);
	}

	public static eu.strasbourg.service.project.model.Initiative fetchInitiative(
		long initiativeId) {
		return getService().fetchInitiative(initiativeId);
	}

	/**
	* Returns the initiative matching the UUID and group.
	*
	* @param uuid the initiative's UUID
	* @param groupId the primary key of the group
	* @return the matching initiative, or <code>null</code> if a matching initiative could not be found
	*/
	public static eu.strasbourg.service.project.model.Initiative fetchInitiativeByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchInitiativeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the initiative with the primary key.
	*
	* @param initiativeId the primary key of the initiative
	* @return the initiative
	* @throws PortalException if a initiative with the primary key could not be found
	*/
	public static eu.strasbourg.service.project.model.Initiative getInitiative(
		long initiativeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getInitiative(initiativeId);
	}

	/**
	* Returns the initiative matching the UUID and group.
	*
	* @param uuid the initiative's UUID
	* @param groupId the primary key of the group
	* @return the matching initiative
	* @throws PortalException if a matching initiative could not be found
	*/
	public static eu.strasbourg.service.project.model.Initiative getInitiativeByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getInitiativeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une initiative
	*/
	public static eu.strasbourg.service.project.model.Initiative removeInitiative(
		long initiativeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeInitiative(initiativeId);
	}

	/**
	* Updates the initiative in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param initiative the initiative
	* @return the initiative that was updated
	*/
	public static eu.strasbourg.service.project.model.Initiative updateInitiative(
		eu.strasbourg.service.project.model.Initiative initiative) {
		return getService().updateInitiative(initiative);
	}

	/**
	* Met à jour une initiative et l'enregistre en base de données
	*
	* @throws IOException
	*/
	public static eu.strasbourg.service.project.model.Initiative updateInitiative(
		eu.strasbourg.service.project.model.Initiative initiative,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateInitiative(initiative, sc);
	}

	/**
	* Returns the number of initiatives.
	*
	* @return the number of initiatives
	*/
	public static int getInitiativesCount() {
		return getService().getInitiativesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Recherche par mot clés
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Initiative> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return getService().findByKeyword(keyword, groupId, start, end);
	}

	public static java.util.List<eu.strasbourg.service.project.model.Initiative> findByPublikUserId(
		java.lang.String publikUserId) {
		return getService().findByPublikUserId(publikUserId);
	}

	/**
	* Retourne toutes les initiatives d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Initiative> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
	}

	/**
	* Returns a range of all the initiatives.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.InitiativeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @return the range of initiatives
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Initiative> getInitiatives(
		int start, int end) {
		return getService().getInitiatives(start, end);
	}

	/**
	* Returns all the initiatives matching the UUID and company.
	*
	* @param uuid the UUID of the initiatives
	* @param companyId the primary key of the company
	* @return the matching initiatives, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Initiative> getInitiativesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getInitiativesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of initiatives matching the UUID and company.
	*
	* @param uuid the UUID of the initiatives
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of initiatives
	* @param end the upper bound of the range of initiatives (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching initiatives, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.project.model.Initiative> getInitiativesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.project.model.Initiative> orderByComparator) {
		return getService()
				   .getInitiativesByUuidAndCompanyId(uuid, companyId, start,
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
	* Recherche par mot clés (compte)
	*/
	public static long findByKeywordCount(java.lang.String keyword, long groupId) {
		return getService().findByKeywordCount(keyword, groupId);
	}

	public static InitiativeLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<InitiativeLocalService, InitiativeLocalService> _serviceTracker =
		ServiceTrackerFactory.open(InitiativeLocalService.class);
}