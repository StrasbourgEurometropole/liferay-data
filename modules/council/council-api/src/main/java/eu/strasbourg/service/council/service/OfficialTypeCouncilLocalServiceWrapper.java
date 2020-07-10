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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OfficialTypeCouncilLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see OfficialTypeCouncilLocalService
 * @generated
 */
@ProviderType
public class OfficialTypeCouncilLocalServiceWrapper
	implements OfficialTypeCouncilLocalService,
			   ServiceWrapper<OfficialTypeCouncilLocalService> {

	public OfficialTypeCouncilLocalServiceWrapper(
		OfficialTypeCouncilLocalService officialTypeCouncilLocalService) {

		_officialTypeCouncilLocalService = officialTypeCouncilLocalService;
	}

	/**
	 * Adds the official type council to the database. Also notifies the appropriate model listeners.
	 *
	 * @param officialTypeCouncil the official type council
	 * @return the official type council that was added
	 */
	@Override
	public eu.strasbourg.service.council.model.OfficialTypeCouncil
		addOfficialTypeCouncil(
			eu.strasbourg.service.council.model.OfficialTypeCouncil
				officialTypeCouncil) {

		return _officialTypeCouncilLocalService.addOfficialTypeCouncil(
			officialTypeCouncil);
	}

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public eu.strasbourg.service.council.model.OfficialTypeCouncil
		createOfficialTypeCouncil(
			long officialId, long typeId,
			com.liferay.portal.kernel.service.ServiceContext sc) {

		return _officialTypeCouncilLocalService.createOfficialTypeCouncil(
			officialId, typeId, sc);
	}

	/**
	 * Creates a new official type council with the primary key. Does not add the official type council to the database.
	 *
	 * @param officialTypeCouncilPK the primary key for the new official type council
	 * @return the new official type council
	 */
	@Override
	public eu.strasbourg.service.council.model.OfficialTypeCouncil
		createOfficialTypeCouncil(
			eu.strasbourg.service.council.service.persistence.
				OfficialTypeCouncilPK officialTypeCouncilPK) {

		return _officialTypeCouncilLocalService.createOfficialTypeCouncil(
			officialTypeCouncilPK);
	}

	/**
	 * Deletes the official type council from the database. Also notifies the appropriate model listeners.
	 *
	 * @param officialTypeCouncil the official type council
	 * @return the official type council that was removed
	 */
	@Override
	public eu.strasbourg.service.council.model.OfficialTypeCouncil
		deleteOfficialTypeCouncil(
			eu.strasbourg.service.council.model.OfficialTypeCouncil
				officialTypeCouncil) {

		return _officialTypeCouncilLocalService.deleteOfficialTypeCouncil(
			officialTypeCouncil);
	}

	/**
	 * Deletes the official type council with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param officialTypeCouncilPK the primary key of the official type council
	 * @return the official type council that was removed
	 * @throws PortalException if a official type council with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.council.model.OfficialTypeCouncil
			deleteOfficialTypeCouncil(
				eu.strasbourg.service.council.service.persistence.
					OfficialTypeCouncilPK officialTypeCouncilPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _officialTypeCouncilLocalService.deleteOfficialTypeCouncil(
			officialTypeCouncilPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _officialTypeCouncilLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _officialTypeCouncilLocalService.dynamicQuery();
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

		return _officialTypeCouncilLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.council.model.impl.OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _officialTypeCouncilLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.council.model.impl.OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _officialTypeCouncilLocalService.dynamicQuery(
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

		return _officialTypeCouncilLocalService.dynamicQueryCount(dynamicQuery);
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

		return _officialTypeCouncilLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.council.model.OfficialTypeCouncil
		fetchOfficialTypeCouncil(
			eu.strasbourg.service.council.service.persistence.
				OfficialTypeCouncilPK officialTypeCouncilPK) {

		return _officialTypeCouncilLocalService.fetchOfficialTypeCouncil(
			officialTypeCouncilPK);
	}

	/**
	 * Returns the official type council matching the UUID and group.
	 *
	 * @param uuid the official type council's UUID
	 * @param groupId the primary key of the group
	 * @return the matching official type council, or <code>null</code> if a matching official type council could not be found
	 */
	@Override
	public eu.strasbourg.service.council.model.OfficialTypeCouncil
		fetchOfficialTypeCouncilByUuidAndGroupId(String uuid, long groupId) {

		return _officialTypeCouncilLocalService.
			fetchOfficialTypeCouncilByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Retourne les types de Conseil d'un élu
	 *
	 * @return
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.council.model.OfficialTypeCouncil>
			findByOfficialId(long officialId) {

		return _officialTypeCouncilLocalService.findByOfficialId(officialId);
	}

	/**
	 * Retourne la liste des votes d'une délibération
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.council.model.OfficialTypeCouncil> findByTypeId(
			long typeId) {

		return _officialTypeCouncilLocalService.findByTypeId(typeId);
	}

	/**
	 * Retourne le vote d'un élu pour une délibération
	 */
	@Override
	public eu.strasbourg.service.council.model.OfficialTypeCouncil
		findByTypeIdandOfficialId(long typeId, long officialId) {

		return _officialTypeCouncilLocalService.findByTypeIdandOfficialId(
			typeId, officialId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _officialTypeCouncilLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _officialTypeCouncilLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the official type council with the primary key.
	 *
	 * @param officialTypeCouncilPK the primary key of the official type council
	 * @return the official type council
	 * @throws PortalException if a official type council with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.council.model.OfficialTypeCouncil
			getOfficialTypeCouncil(
				eu.strasbourg.service.council.service.persistence.
					OfficialTypeCouncilPK officialTypeCouncilPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _officialTypeCouncilLocalService.getOfficialTypeCouncil(
			officialTypeCouncilPK);
	}

	/**
	 * Returns the official type council matching the UUID and group.
	 *
	 * @param uuid the official type council's UUID
	 * @param groupId the primary key of the group
	 * @return the matching official type council
	 * @throws PortalException if a matching official type council could not be found
	 */
	@Override
	public eu.strasbourg.service.council.model.OfficialTypeCouncil
			getOfficialTypeCouncilByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _officialTypeCouncilLocalService.
			getOfficialTypeCouncilByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the official type councils.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.council.model.impl.OfficialTypeCouncilModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @return the range of official type councils
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.council.model.OfficialTypeCouncil>
			getOfficialTypeCouncils(int start, int end) {

		return _officialTypeCouncilLocalService.getOfficialTypeCouncils(
			start, end);
	}

	/**
	 * Returns all the official type councils matching the UUID and company.
	 *
	 * @param uuid the UUID of the official type councils
	 * @param companyId the primary key of the company
	 * @return the matching official type councils, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.council.model.OfficialTypeCouncil>
			getOfficialTypeCouncilsByUuidAndCompanyId(
				String uuid, long companyId) {

		return _officialTypeCouncilLocalService.
			getOfficialTypeCouncilsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of official type councils matching the UUID and company.
	 *
	 * @param uuid the UUID of the official type councils
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of official type councils
	 * @param end the upper bound of the range of official type councils (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching official type councils, or an empty list if no matches were found
	 */
	@Override
	public java.util.List
		<eu.strasbourg.service.council.model.OfficialTypeCouncil>
			getOfficialTypeCouncilsByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<eu.strasbourg.service.council.model.OfficialTypeCouncil>
						orderByComparator) {

		return _officialTypeCouncilLocalService.
			getOfficialTypeCouncilsByUuidAndCompanyId(
				uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of official type councils.
	 *
	 * @return the number of official type councils
	 */
	@Override
	public int getOfficialTypeCouncilsCount() {
		return _officialTypeCouncilLocalService.getOfficialTypeCouncilsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _officialTypeCouncilLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _officialTypeCouncilLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public eu.strasbourg.service.council.model.OfficialTypeCouncil
			removeOfficialTypeCouncil(long officialId, long typeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _officialTypeCouncilLocalService.removeOfficialTypeCouncil(
			officialId, typeId);
	}

	/**
	 * Updates the official type council in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param officialTypeCouncil the official type council
	 * @return the official type council that was updated
	 */
	@Override
	public eu.strasbourg.service.council.model.OfficialTypeCouncil
		updateOfficialTypeCouncil(
			eu.strasbourg.service.council.model.OfficialTypeCouncil
				officialTypeCouncil) {

		return _officialTypeCouncilLocalService.updateOfficialTypeCouncil(
			officialTypeCouncil);
	}

	@Override
	public OfficialTypeCouncilLocalService getWrappedService() {
		return _officialTypeCouncilLocalService;
	}

	@Override
	public void setWrappedService(
		OfficialTypeCouncilLocalService officialTypeCouncilLocalService) {

		_officialTypeCouncilLocalService = officialTypeCouncilLocalService;
	}

	private OfficialTypeCouncilLocalService _officialTypeCouncilLocalService;

}