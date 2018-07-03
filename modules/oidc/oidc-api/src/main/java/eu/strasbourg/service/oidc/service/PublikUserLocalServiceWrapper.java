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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PublikUserLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PublikUserLocalService
 * @generated
 */
@ProviderType
public class PublikUserLocalServiceWrapper implements PublikUserLocalService,
	ServiceWrapper<PublikUserLocalService> {
	public PublikUserLocalServiceWrapper(
		PublikUserLocalService publikUserLocalService) {
		_publikUserLocalService = publikUserLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _publikUserLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _publikUserLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _publikUserLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publikUserLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publikUserLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the publik user to the database. Also notifies the appropriate model listeners.
	*
	* @param publikUser the publik user
	* @return the publik user that was added
	*/
	@Override
	public eu.strasbourg.service.oidc.model.PublikUser addPublikUser(
		eu.strasbourg.service.oidc.model.PublikUser publikUser) {
		return _publikUserLocalService.addPublikUser(publikUser);
	}

	@Override
	public eu.strasbourg.service.oidc.model.PublikUser createPublikUser() {
		return _publikUserLocalService.createPublikUser();
	}

	/**
	* Creates a new publik user with the primary key. Does not add the publik user to the database.
	*
	* @param publikUserLiferayId the primary key for the new publik user
	* @return the new publik user
	*/
	@Override
	public eu.strasbourg.service.oidc.model.PublikUser createPublikUser(
		long publikUserLiferayId) {
		return _publikUserLocalService.createPublikUser(publikUserLiferayId);
	}

	/**
	* Deletes the publik user from the database. Also notifies the appropriate model listeners.
	*
	* @param publikUser the publik user
	* @return the publik user that was removed
	*/
	@Override
	public eu.strasbourg.service.oidc.model.PublikUser deletePublikUser(
		eu.strasbourg.service.oidc.model.PublikUser publikUser) {
		return _publikUserLocalService.deletePublikUser(publikUser);
	}

	/**
	* Deletes the publik user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param publikUserLiferayId the primary key of the publik user
	* @return the publik user that was removed
	* @throws PortalException if a publik user with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.oidc.model.PublikUser deletePublikUser(
		long publikUserLiferayId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publikUserLocalService.deletePublikUser(publikUserLiferayId);
	}

	@Override
	public eu.strasbourg.service.oidc.model.PublikUser fetchPublikUser(
		long publikUserLiferayId) {
		return _publikUserLocalService.fetchPublikUser(publikUserLiferayId);
	}

	@Override
	public eu.strasbourg.service.oidc.model.PublikUser getByPublikUserId(
		java.lang.String publikUserId) {
		return _publikUserLocalService.getByPublikUserId(publikUserId);
	}

	/**
	* Returns the publik user with the primary key.
	*
	* @param publikUserLiferayId the primary key of the publik user
	* @return the publik user
	* @throws PortalException if a publik user with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.oidc.model.PublikUser getPublikUser(
		long publikUserLiferayId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publikUserLocalService.getPublikUser(publikUserLiferayId);
	}

	/**
	* Supprime une entité
	*/
	@Override
	public eu.strasbourg.service.oidc.model.PublikUser removePublikUser(
		java.lang.String publikUserId) {
		return _publikUserLocalService.removePublikUser(publikUserId);
	}

	/**
	* Updates the publik user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param publikUser the publik user
	* @return the publik user that was updated
	*/
	@Override
	public eu.strasbourg.service.oidc.model.PublikUser updatePublikUser(
		eu.strasbourg.service.oidc.model.PublikUser publikUser) {
		return _publikUserLocalService.updatePublikUser(publikUser);
	}

	/**
	* Met à jour un projet et l'enregistre en base de données
	*
	* @throws IOException
	*/
	@Override
	public eu.strasbourg.service.oidc.model.PublikUser updatePublikUser(
		eu.strasbourg.service.oidc.model.PublikUser publikUser,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _publikUserLocalService.updatePublikUser(publikUser, sc);
	}

	/**
	* Returns the number of publik users.
	*
	* @return the number of publik users
	*/
	@Override
	public int getPublikUsersCount() {
		return _publikUserLocalService.getPublikUsersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _publikUserLocalService.getOSGiServiceIdentifier();
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
		return _publikUserLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.oidc.model.impl.PublikUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _publikUserLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.oidc.model.impl.PublikUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _publikUserLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<eu.strasbourg.service.oidc.model.PublikUser> getAllPublikUsers() {
		return _publikUserLocalService.getAllPublikUsers();
	}

	/**
	* Returns a range of all the publik users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.oidc.model.impl.PublikUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of publik users
	* @param end the upper bound of the range of publik users (not inclusive)
	* @return the range of publik users
	*/
	@Override
	public java.util.List<eu.strasbourg.service.oidc.model.PublikUser> getPublikUsers(
		int start, int end) {
		return _publikUserLocalService.getPublikUsers(start, end);
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
		return _publikUserLocalService.dynamicQueryCount(dynamicQuery);
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
		return _publikUserLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public PublikUserLocalService getWrappedService() {
		return _publikUserLocalService;
	}

	@Override
	public void setWrappedService(PublikUserLocalService publikUserLocalService) {
		_publikUserLocalService = publikUserLocalService;
	}

	private PublikUserLocalService _publikUserLocalService;
}