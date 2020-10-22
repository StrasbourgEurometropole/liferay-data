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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link InitiativeHelpLocalService}.
 *
 * @author Cedric Henry
 * @see InitiativeHelpLocalService
 * @generated
 */
@ProviderType
public class InitiativeHelpLocalServiceWrapper
	implements InitiativeHelpLocalService,
			   ServiceWrapper<InitiativeHelpLocalService> {

	public InitiativeHelpLocalServiceWrapper(
		InitiativeHelpLocalService initiativeHelpLocalService) {

		_initiativeHelpLocalService = initiativeHelpLocalService;
	}

	/**
	 * Adds the initiative help to the database. Also notifies the appropriate model listeners.
	 *
	 * @param initiativeHelp the initiative help
	 * @return the initiative help that was added
	 */
	@Override
	public eu.strasbourg.service.project.model.InitiativeHelp addInitiativeHelp(
		eu.strasbourg.service.project.model.InitiativeHelp initiativeHelp) {

		return _initiativeHelpLocalService.addInitiativeHelp(initiativeHelp);
	}

	/**
	 * Creates a new initiative help with the primary key. Does not add the initiative help to the database.
	 *
	 * @param initiativeHelpId the primary key for the new initiative help
	 * @return the new initiative help
	 */
	@Override
	public eu.strasbourg.service.project.model.InitiativeHelp
		createInitiativeHelp(long initiativeHelpId) {

		return _initiativeHelpLocalService.createInitiativeHelp(
			initiativeHelpId);
	}

	/**
	 * Methode de creation d'une initiative
	 *
	 * @param sc Le contexte de la requete.
	 * @return L'aide cree.
	 */
	@Override
	public eu.strasbourg.service.project.model.InitiativeHelp
		createInitiativeHelp(
			com.liferay.portal.kernel.service.ServiceContext sc) {

		return _initiativeHelpLocalService.createInitiativeHelp(sc);
	}

	/**
	 * Deletes the initiative help from the database. Also notifies the appropriate model listeners.
	 *
	 * @param initiativeHelp the initiative help
	 * @return the initiative help that was removed
	 */
	@Override
	public eu.strasbourg.service.project.model.InitiativeHelp
		deleteInitiativeHelp(
			eu.strasbourg.service.project.model.InitiativeHelp initiativeHelp) {

		return _initiativeHelpLocalService.deleteInitiativeHelp(initiativeHelp);
	}

	/**
	 * Deletes the initiative help with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param initiativeHelpId the primary key of the initiative help
	 * @return the initiative help that was removed
	 * @throws PortalException if a initiative help with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.project.model.InitiativeHelp
			deleteInitiativeHelp(long initiativeHelpId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _initiativeHelpLocalService.deleteInitiativeHelp(
			initiativeHelpId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _initiativeHelpLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _initiativeHelpLocalService.dynamicQuery();
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

		return _initiativeHelpLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.project.model.impl.InitiativeHelpModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _initiativeHelpLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.project.model.impl.InitiativeHelpModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _initiativeHelpLocalService.dynamicQuery(
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

		return _initiativeHelpLocalService.dynamicQueryCount(dynamicQuery);
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

		return _initiativeHelpLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.project.model.InitiativeHelp
		fetchInitiativeHelp(long initiativeHelpId) {

		return _initiativeHelpLocalService.fetchInitiativeHelp(
			initiativeHelpId);
	}

	/**
	 * Returns the initiative help matching the UUID and group.
	 *
	 * @param uuid the initiative help's UUID
	 * @param groupId the primary key of the group
	 * @return the matching initiative help, or <code>null</code> if a matching initiative help could not be found
	 */
	@Override
	public eu.strasbourg.service.project.model.InitiativeHelp
		fetchInitiativeHelpByUuidAndGroupId(String uuid, long groupId) {

		return _initiativeHelpLocalService.fetchInitiativeHelpByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _initiativeHelpLocalService.getActionableDynamicQuery();
	}

	/**
	 * Retourne la liste des aides d'une initiative
	 */
	@Override
	public java.util.List<eu.strasbourg.service.project.model.InitiativeHelp>
		getByInitiativeId(long initiativeId) {

		return _initiativeHelpLocalService.getByInitiativeId(initiativeId);
	}

	/**
	 * Retourne les aides proposees par un utilisateur
	 */
	@Override
	public java.util.List<eu.strasbourg.service.project.model.InitiativeHelp>
		getByPublikUserId(String publikUserId) {

		return _initiativeHelpLocalService.getByPublikUserId(publikUserId);
	}

	/**
	 * Retourne l'aide proposee par un utilisateur pour une initiative donnee
	 */
	@Override
	public eu.strasbourg.service.project.model.InitiativeHelp
		getByPublikUserIdAndInitiativeId(
			String publikUserId, long initiativeId) {

		return _initiativeHelpLocalService.getByPublikUserIdAndInitiativeId(
			publikUserId, initiativeId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _initiativeHelpLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the initiative help with the primary key.
	 *
	 * @param initiativeHelpId the primary key of the initiative help
	 * @return the initiative help
	 * @throws PortalException if a initiative help with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.project.model.InitiativeHelp getInitiativeHelp(
			long initiativeHelpId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _initiativeHelpLocalService.getInitiativeHelp(initiativeHelpId);
	}

	/**
	 * Returns the initiative help matching the UUID and group.
	 *
	 * @param uuid the initiative help's UUID
	 * @param groupId the primary key of the group
	 * @return the matching initiative help
	 * @throws PortalException if a matching initiative help could not be found
	 */
	@Override
	public eu.strasbourg.service.project.model.InitiativeHelp
			getInitiativeHelpByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _initiativeHelpLocalService.getInitiativeHelpByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the initiative helps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.project.model.impl.InitiativeHelpModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of initiative helps
	 * @param end the upper bound of the range of initiative helps (not inclusive)
	 * @return the range of initiative helps
	 */
	@Override
	public java.util.List<eu.strasbourg.service.project.model.InitiativeHelp>
		getInitiativeHelps(int start, int end) {

		return _initiativeHelpLocalService.getInitiativeHelps(start, end);
	}

	/**
	 * Returns the number of initiative helps.
	 *
	 * @return the number of initiative helps
	 */
	@Override
	public int getInitiativeHelpsCount() {
		return _initiativeHelpLocalService.getInitiativeHelpsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _initiativeHelpLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _initiativeHelpLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Supprimer un soutien donne
	 *
	 * @param initiativeHelpId Id de l'aide inititative
	 */
	@Override
	public eu.strasbourg.service.project.model.InitiativeHelp
		removeInitiativeHelp(long initiativeHelpId) {

		return _initiativeHelpLocalService.removeInitiativeHelp(
			initiativeHelpId);
	}

	/**
	 * Updates the initiative help in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param initiativeHelp the initiative help
	 * @return the initiative help that was updated
	 */
	@Override
	public eu.strasbourg.service.project.model.InitiativeHelp
		updateInitiativeHelp(
			eu.strasbourg.service.project.model.InitiativeHelp initiativeHelp) {

		return _initiativeHelpLocalService.updateInitiativeHelp(initiativeHelp);
	}

	@Override
	public InitiativeHelpLocalService getWrappedService() {
		return _initiativeHelpLocalService;
	}

	@Override
	public void setWrappedService(
		InitiativeHelpLocalService initiativeHelpLocalService) {

		_initiativeHelpLocalService = initiativeHelpLocalService;
	}

	private InitiativeHelpLocalService _initiativeHelpLocalService;

}