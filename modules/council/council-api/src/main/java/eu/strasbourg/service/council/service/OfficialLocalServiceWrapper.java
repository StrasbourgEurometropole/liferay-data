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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OfficialLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see OfficialLocalService
 * @generated
 */
public class OfficialLocalServiceWrapper
	implements OfficialLocalService, ServiceWrapper<OfficialLocalService> {

	public OfficialLocalServiceWrapper(
		OfficialLocalService officialLocalService) {

		_officialLocalService = officialLocalService;
	}

	/**
	 * Adds the official to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OfficialLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param official the official
	 * @return the official that was added
	 */
	@Override
	public eu.strasbourg.service.council.model.Official addOfficial(
		eu.strasbourg.service.council.model.Official official) {

		return _officialLocalService.addOfficial(official);
	}

	/**
	 * Creates a new official with the primary key. Does not add the official to the database.
	 *
	 * @param officialId the primary key for the new official
	 * @return the new official
	 */
	@Override
	public eu.strasbourg.service.council.model.Official createOfficial(
		long officialId) {

		return _officialLocalService.createOfficial(officialId);
	}

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public eu.strasbourg.service.council.model.Official createOfficial(
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _officialLocalService.createOfficial(sc);
	}

	/**
	 * Deletes the official with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OfficialLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param officialId the primary key of the official
	 * @return the official that was removed
	 * @throws PortalException if a official with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.council.model.Official deleteOfficial(
			long officialId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _officialLocalService.deleteOfficial(officialId);
	}

	/**
	 * Deletes the official from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OfficialLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param official the official
	 * @return the official that was removed
	 */
	@Override
	public eu.strasbourg.service.council.model.Official deleteOfficial(
		eu.strasbourg.service.council.model.Official official) {

		return _officialLocalService.deleteOfficial(official);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _officialLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _officialLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _officialLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.council.model.impl.OfficialModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _officialLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.council.model.impl.OfficialModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _officialLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _officialLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _officialLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.council.model.Official fetchOfficial(
		long officialId) {

		return _officialLocalService.fetchOfficial(officialId);
	}

	/**
	 * Returns the official matching the UUID and group.
	 *
	 * @param uuid the official's UUID
	 * @param groupId the primary key of the group
	 * @return the matching official, or <code>null</code> if a matching official could not be found
	 */
	@Override
	public eu.strasbourg.service.council.model.Official
		fetchOfficialByUuidAndGroupId(String uuid, long groupId) {

		return _officialLocalService.fetchOfficialByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Recherche par ID de CouncilSession
	 */
	@Override
	public eu.strasbourg.service.council.model.Official findByEmail(
		String email) {

		return _officialLocalService.findByEmail(email);
	}

	/**
	 * Recherche par site et activité ou non de l'élu
	 */
	@Override
	public java.util.List<eu.strasbourg.service.council.model.Official>
		findByGroupIdAndIsActive(long groupId, boolean isActive) {

		return _officialLocalService.findByGroupIdAndIsActive(
			groupId, isActive);
	}

	/**
	 * Recherche par site, et type de conseil
	 */
	@Override
	public java.util.List<eu.strasbourg.service.council.model.Official>
		findByGroupIdAndTypeId(long groupId, long typeId) {

		return _officialLocalService.findByGroupIdAndTypeId(groupId, typeId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _officialLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _officialLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _officialLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the official with the primary key.
	 *
	 * @param officialId the primary key of the official
	 * @return the official
	 * @throws PortalException if a official with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.council.model.Official getOfficial(
			long officialId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _officialLocalService.getOfficial(officialId);
	}

	/**
	 * Returns the official matching the UUID and group.
	 *
	 * @param uuid the official's UUID
	 * @param groupId the primary key of the group
	 * @return the matching official
	 * @throws PortalException if a matching official could not be found
	 */
	@Override
	public eu.strasbourg.service.council.model.Official
			getOfficialByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _officialLocalService.getOfficialByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the officials.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.council.model.impl.OfficialModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of officials
	 * @param end the upper bound of the range of officials (not inclusive)
	 * @return the range of officials
	 */
	@Override
	public java.util.List<eu.strasbourg.service.council.model.Official>
		getOfficials(int start, int end) {

		return _officialLocalService.getOfficials(start, end);
	}

	/**
	 * Returns all the officials matching the UUID and company.
	 *
	 * @param uuid the UUID of the officials
	 * @param companyId the primary key of the company
	 * @return the matching officials, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<eu.strasbourg.service.council.model.Official>
		getOfficialsByUuidAndCompanyId(String uuid, long companyId) {

		return _officialLocalService.getOfficialsByUuidAndCompanyId(
			uuid, companyId);
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
	@Override
	public java.util.List<eu.strasbourg.service.council.model.Official>
		getOfficialsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<eu.strasbourg.service.council.model.Official>
					orderByComparator) {

		return _officialLocalService.getOfficialsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of officials.
	 *
	 * @return the number of officials
	 */
	@Override
	public int getOfficialsCount() {
		return _officialLocalService.getOfficialsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _officialLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _officialLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public eu.strasbourg.service.council.model.Official removeOfficial(
			long officialId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _officialLocalService.removeOfficial(officialId);
	}

	/**
	 * Updates the official in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect OfficialLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param official the official
	 * @return the official that was updated
	 */
	@Override
	public eu.strasbourg.service.council.model.Official updateOfficial(
		eu.strasbourg.service.council.model.Official official) {

		return _officialLocalService.updateOfficial(official);
	}

	/**
	 * Met à jour une entité et l'enregistre en base de données
	 */
	@Override
	public eu.strasbourg.service.council.model.Official updateOfficial(
			eu.strasbourg.service.council.model.Official official,
			com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _officialLocalService.updateOfficial(official, sc);
	}

	/**
	 * Mise à jour des informations de connexion d'un élu
	 *
	 * @param officialId ID de l'élu
	 * @parma officialDeviceInfo Informations décrivant l'appareil utilisé par l'élu
	 */
	@Override
	public void updateOfficialInfo(long officialId, String officialDeviceInfo) {
		_officialLocalService.updateOfficialInfo(
			officialId, officialDeviceInfo);
	}

	/**
	 * Met à jour le statut de l'entité par le framework workflow
	 */
	@Override
	public eu.strasbourg.service.council.model.Official updateStatus(
			long userId, long entryId, int status,
			com.liferay.portal.kernel.service.ServiceContext sc,
			java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _officialLocalService.updateStatus(
			userId, entryId, status, sc, workflowContext);
	}

	/**
	 * Met à jour le statut de l'entité "manuellement" (pas via le workflow)
	 */
	@Override
	public void updateStatus(
			eu.strasbourg.service.council.model.Official official, int status)
		throws com.liferay.portal.kernel.exception.PortalException {

		_officialLocalService.updateStatus(official, status);
	}

	@Override
	public OfficialLocalService getWrappedService() {
		return _officialLocalService;
	}

	@Override
	public void setWrappedService(OfficialLocalService officialLocalService) {
		_officialLocalService = officialLocalService;
	}

	private OfficialLocalService _officialLocalService;

}