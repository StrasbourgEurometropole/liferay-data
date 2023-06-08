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

/**
 * Provides a wrapper for {@link RefreshTokenLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RefreshTokenLocalService
 * @generated
 */
public class RefreshTokenLocalServiceWrapper
	implements RefreshTokenLocalService,
			   ServiceWrapper<RefreshTokenLocalService> {

	public RefreshTokenLocalServiceWrapper(
		RefreshTokenLocalService refreshTokenLocalService) {

		_refreshTokenLocalService = refreshTokenLocalService;
	}

	/**
	 * Adds the refresh token to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RefreshTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param refreshToken the refresh token
	 * @return the refresh token that was added
	 */
	@Override
	public eu.strasbourg.service.csmap.model.RefreshToken addRefreshToken(
		eu.strasbourg.service.csmap.model.RefreshToken refreshToken) {

		return _refreshTokenLocalService.addRefreshToken(refreshToken);
	}

	/**
	 * Creates a new refresh token with the primary key. Does not add the refresh token to the database.
	 *
	 * @param refreshTokenId the primary key for the new refresh token
	 * @return the new refresh token
	 */
	@Override
	public eu.strasbourg.service.csmap.model.RefreshToken createRefreshToken(
		long refreshTokenId) {

		return _refreshTokenLocalService.createRefreshToken(refreshTokenId);
	}

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public eu.strasbourg.service.csmap.model.RefreshToken createRefreshToken(
		com.liferay.portal.kernel.service.ServiceContext sc) {

		return _refreshTokenLocalService.createRefreshToken(sc);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _refreshTokenLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the refresh token with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RefreshTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param refreshTokenId the primary key of the refresh token
	 * @return the refresh token that was removed
	 * @throws PortalException if a refresh token with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.csmap.model.RefreshToken deleteRefreshToken(
			long refreshTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _refreshTokenLocalService.deleteRefreshToken(refreshTokenId);
	}

	/**
	 * Deletes the refresh token from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RefreshTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param refreshToken the refresh token
	 * @return the refresh token that was removed
	 */
	@Override
	public eu.strasbourg.service.csmap.model.RefreshToken deleteRefreshToken(
		eu.strasbourg.service.csmap.model.RefreshToken refreshToken) {

		return _refreshTokenLocalService.deleteRefreshToken(refreshToken);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _refreshTokenLocalService.dynamicQuery();
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

		return _refreshTokenLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.RefreshTokenModelImpl</code>.
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

		return _refreshTokenLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.RefreshTokenModelImpl</code>.
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

		return _refreshTokenLocalService.dynamicQuery(
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

		return _refreshTokenLocalService.dynamicQueryCount(dynamicQuery);
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

		return _refreshTokenLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	/**
	 * Retrouve un refresh token par sa valeur
	 */
	@Override
	public eu.strasbourg.service.csmap.model.RefreshToken fetchByValue(
		String value) {

		return _refreshTokenLocalService.fetchByValue(value);
	}

	@Override
	public eu.strasbourg.service.csmap.model.RefreshToken fetchRefreshToken(
		long refreshTokenId) {

		return _refreshTokenLocalService.fetchRefreshToken(refreshTokenId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _refreshTokenLocalService.getActionableDynamicQuery();
	}

	/**
	 * Retrouve un refresh token par sa valeur
	 */
	@Override
	public java.util.List<eu.strasbourg.service.csmap.model.RefreshToken>
		getByPublikId(String publikId) {

		return _refreshTokenLocalService.getByPublikId(publikId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _refreshTokenLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _refreshTokenLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _refreshTokenLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the refresh token with the primary key.
	 *
	 * @param refreshTokenId the primary key of the refresh token
	 * @return the refresh token
	 * @throws PortalException if a refresh token with the primary key could not be found
	 */
	@Override
	public eu.strasbourg.service.csmap.model.RefreshToken getRefreshToken(
			long refreshTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _refreshTokenLocalService.getRefreshToken(refreshTokenId);
	}

	/**
	 * Returns a range of all the refresh tokens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.RefreshTokenModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of refresh tokens
	 * @param end the upper bound of the range of refresh tokens (not inclusive)
	 * @return the range of refresh tokens
	 */
	@Override
	public java.util.List<eu.strasbourg.service.csmap.model.RefreshToken>
		getRefreshTokens(int start, int end) {

		return _refreshTokenLocalService.getRefreshTokens(start, end);
	}

	/**
	 * Returns the number of refresh tokens.
	 *
	 * @return the number of refresh tokens
	 */
	@Override
	public int getRefreshTokensCount() {
		return _refreshTokenLocalService.getRefreshTokensCount();
	}

	/**
	 * Supprime une entité
	 */
	@Override
	public eu.strasbourg.service.csmap.model.RefreshToken removeRefreshToken(
			long refreshTokenId)
		throws eu.strasbourg.service.csmap.exception.
			NoSuchRefreshTokenException {

		return _refreshTokenLocalService.removeRefreshToken(refreshTokenId);
	}

	/**
	 * Updates the refresh token in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect RefreshTokenLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param refreshToken the refresh token
	 * @return the refresh token that was updated
	 */
	@Override
	public eu.strasbourg.service.csmap.model.RefreshToken updateRefreshToken(
		eu.strasbourg.service.csmap.model.RefreshToken refreshToken) {

		return _refreshTokenLocalService.updateRefreshToken(refreshToken);
	}

	/**
	 * Met à jour une entité et l'enregistre en base de données
	 */
	@Override
	public eu.strasbourg.service.csmap.model.RefreshToken updateRefreshToken(
		eu.strasbourg.service.csmap.model.RefreshToken refreshToken,
		com.liferay.portal.kernel.service.ServiceContext sc) {

		return _refreshTokenLocalService.updateRefreshToken(refreshToken, sc);
	}

	@Override
	public RefreshTokenLocalService getWrappedService() {
		return _refreshTokenLocalService;
	}

	@Override
	public void setWrappedService(
		RefreshTokenLocalService refreshTokenLocalService) {

		_refreshTokenLocalService = refreshTokenLocalService;
	}

	private RefreshTokenLocalService _refreshTokenLocalService;

}