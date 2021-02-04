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

package eu.strasbourg.service.csmap.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides a wrapper for {@link NonceLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see NonceLocalService
 * @generated
 */
@ProviderType
public class NonceLocalServiceWrapper
	implements NonceLocalService, ServiceWrapper<NonceLocalService> {

	public NonceLocalServiceWrapper(NonceLocalService nonceLocalService) {
		_nonceLocalService = nonceLocalService;
	}

	/**
	 * Adds the nonce to the database. Also notifies the appropriate model listeners.
	 *
	 * @param nonce the nonce
	 * @return the nonce that was added
	 */
	@Override
	public eu.strasbourg.service.csmap.model.Nonce addNonce(
		eu.strasbourg.service.csmap.model.Nonce nonce) {

		return _nonceLocalService.addNonce(nonce);
	}

	/**
	 * Creates a new nonce with the primary key. Does not add the nonce to the database.
	 *
	 * @param nonceId the primary key for the new nonce
	 * @return the new nonce
	 */
	@Override
	public eu.strasbourg.service.csmap.model.Nonce createNonce(long nonceId) {
		return _nonceLocalService.createNonce(nonceId);
	}

	/**
	 * Deletes the nonce with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param nonceId the primary key of the nonce
	 * @return the nonce that was removed
	 * @throws PortalException if a nonce with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.csmap.model.Nonce deleteNonce(long nonceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _nonceLocalService.deleteNonce(nonceId);
	}

	/**
	 * Deletes the nonce from the database. Also notifies the appropriate model listeners.
	 *
	 * @param nonce the nonce
	 * @return the nonce that was removed
	 */
	@Override
	public eu.strasbourg.service.csmap.model.Nonce deleteNonce(
		eu.strasbourg.service.csmap.model.Nonce nonce) {

		return _nonceLocalService.deleteNonce(nonce);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _nonceLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _nonceLocalService.dynamicQuery();
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

		return _nonceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.NonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _nonceLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.NonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _nonceLocalService.dynamicQuery(
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

		return _nonceLocalService.dynamicQueryCount(dynamicQuery);
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

		return _nonceLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public eu.strasbourg.service.csmap.model.Nonce fetchNonce(long nonceId) {
		return _nonceLocalService.fetchNonce(nonceId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _nonceLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _nonceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the nonce with the primary key.
	 *
	 * @param nonceId the primary key of the nonce
	 * @return the nonce
	 * @throws PortalException if a nonce with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.csmap.model.Nonce getNonce(long nonceId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _nonceLocalService.getNonce(nonceId);
	}

	/**
	 * Returns a range of all the nonces.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.NonceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nonces
	 * @param end the upper bound of the range of nonces (not inclusive)
	 * @return the range of nonces
	 */
	@Override
	public java.util.List<eu.strasbourg.service.csmap.model.Nonce> getNonces(
		int start, int end) {

		return _nonceLocalService.getNonces(start, end);
	}

	/**
	 * Returns the number of nonces.
	 *
	 * @return the number of nonces
	 */
	@Override
	public int getNoncesCount() {
		return _nonceLocalService.getNoncesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _nonceLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _nonceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the nonce in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param nonce the nonce
	 * @return the nonce that was updated
	 */
	@Override
	public eu.strasbourg.service.csmap.model.Nonce updateNonce(
		eu.strasbourg.service.csmap.model.Nonce nonce) {

		return _nonceLocalService.updateNonce(nonce);
	}

	@Override
	public NonceLocalService getWrappedService() {
		return _nonceLocalService;
	}

	@Override
	public void setWrappedService(NonceLocalService nonceLocalService) {
		_nonceLocalService = nonceLocalService;
	}

	private NonceLocalService _nonceLocalService;

}