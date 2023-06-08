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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for RefreshToken. This utility wraps
 * <code>eu.strasbourg.service.csmap.service.impl.RefreshTokenLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see RefreshTokenLocalService
 * @generated
 */
public class RefreshTokenLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>eu.strasbourg.service.csmap.service.impl.RefreshTokenLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static eu.strasbourg.service.csmap.model.RefreshToken
		addRefreshToken(
			eu.strasbourg.service.csmap.model.RefreshToken refreshToken) {

		return getService().addRefreshToken(refreshToken);
	}

	/**
	 * Creates a new refresh token with the primary key. Does not add the refresh token to the database.
	 *
	 * @param refreshTokenId the primary key for the new refresh token
	 * @return the new refresh token
	 */
	public static eu.strasbourg.service.csmap.model.RefreshToken
		createRefreshToken(long refreshTokenId) {

		return getService().createRefreshToken(refreshTokenId);
	}

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	public static eu.strasbourg.service.csmap.model.RefreshToken
		createRefreshToken(
			com.liferay.portal.kernel.service.ServiceContext sc) {

		return getService().createRefreshToken(sc);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
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
	public static eu.strasbourg.service.csmap.model.RefreshToken
			deleteRefreshToken(long refreshTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteRefreshToken(refreshTokenId);
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
	public static eu.strasbourg.service.csmap.model.RefreshToken
		deleteRefreshToken(
			eu.strasbourg.service.csmap.model.RefreshToken refreshToken) {

		return getService().deleteRefreshToken(refreshToken);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.RefreshTokenModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.csmap.model.impl.RefreshTokenModelImpl</code>.
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

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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
	 * Retrouve un refresh token par sa valeur
	 */
	public static eu.strasbourg.service.csmap.model.RefreshToken fetchByValue(
		String value) {

		return getService().fetchByValue(value);
	}

	public static eu.strasbourg.service.csmap.model.RefreshToken
		fetchRefreshToken(long refreshTokenId) {

		return getService().fetchRefreshToken(refreshTokenId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Retrouve un refresh token par sa valeur
	 */
	public static java.util.List<eu.strasbourg.service.csmap.model.RefreshToken>
		getByPublikId(String publikId) {

		return getService().getByPublikId(publikId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the refresh token with the primary key.
	 *
	 * @param refreshTokenId the primary key of the refresh token
	 * @return the refresh token
	 * @throws PortalException if a refresh token with the primary key could not be found
	 */
	public static eu.strasbourg.service.csmap.model.RefreshToken
			getRefreshToken(long refreshTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getRefreshToken(refreshTokenId);
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
	public static java.util.List<eu.strasbourg.service.csmap.model.RefreshToken>
		getRefreshTokens(int start, int end) {

		return getService().getRefreshTokens(start, end);
	}

	/**
	 * Returns the number of refresh tokens.
	 *
	 * @return the number of refresh tokens
	 */
	public static int getRefreshTokensCount() {
		return getService().getRefreshTokensCount();
	}

	/**
	 * Supprime une entité
	 */
	public static eu.strasbourg.service.csmap.model.RefreshToken
			removeRefreshToken(long refreshTokenId)
		throws eu.strasbourg.service.csmap.exception.
			NoSuchRefreshTokenException {

		return getService().removeRefreshToken(refreshTokenId);
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
	public static eu.strasbourg.service.csmap.model.RefreshToken
		updateRefreshToken(
			eu.strasbourg.service.csmap.model.RefreshToken refreshToken) {

		return getService().updateRefreshToken(refreshToken);
	}

	/**
	 * Met à jour une entité et l'enregistre en base de données
	 */
	public static eu.strasbourg.service.csmap.model.RefreshToken
		updateRefreshToken(
			eu.strasbourg.service.csmap.model.RefreshToken refreshToken,
			com.liferay.portal.kernel.service.ServiceContext sc) {

		return getService().updateRefreshToken(refreshToken, sc);
	}

	public static RefreshTokenLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<RefreshTokenLocalService, RefreshTokenLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RefreshTokenLocalService.class);

		ServiceTracker<RefreshTokenLocalService, RefreshTokenLocalService>
			serviceTracker =
				new ServiceTracker
					<RefreshTokenLocalService, RefreshTokenLocalService>(
						bundle.getBundleContext(),
						RefreshTokenLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}