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

package eu.strasbourg.service.favorite.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Favorite. This utility wraps
 * {@link eu.strasbourg.service.favorite.service.impl.FavoriteLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author BenjaminBini
 * @see FavoriteLocalService
 * @see eu.strasbourg.service.favorite.service.base.FavoriteLocalServiceBaseImpl
 * @see eu.strasbourg.service.favorite.service.impl.FavoriteLocalServiceImpl
 * @generated
 */
@ProviderType
public class FavoriteLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.favorite.service.impl.FavoriteLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
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
	* Adds the favorite to the database. Also notifies the appropriate model listeners.
	*
	* @param favorite the favorite
	* @return the favorite that was added
	*/
	public static eu.strasbourg.service.favorite.model.Favorite addFavorite(
		eu.strasbourg.service.favorite.model.Favorite favorite) {
		return getService().addFavorite(favorite);
	}

	/**
	* Crée un nouveau favoris
	*/
	public static eu.strasbourg.service.favorite.model.Favorite createFavorite() {
		return getService().createFavorite();
	}

	/**
	* Creates a new favorite with the primary key. Does not add the favorite to the database.
	*
	* @param favoriteId the primary key for the new favorite
	* @return the new favorite
	*/
	public static eu.strasbourg.service.favorite.model.Favorite createFavorite(
		long favoriteId) {
		return getService().createFavorite(favoriteId);
	}

	/**
	* Deletes the favorite from the database. Also notifies the appropriate model listeners.
	*
	* @param favorite the favorite
	* @return the favorite that was removed
	*/
	public static eu.strasbourg.service.favorite.model.Favorite deleteFavorite(
		eu.strasbourg.service.favorite.model.Favorite favorite) {
		return getService().deleteFavorite(favorite);
	}

	/**
	* Deletes the favorite with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param favoriteId the primary key of the favorite
	* @return the favorite that was removed
	* @throws PortalException if a favorite with the primary key could not be found
	*/
	public static eu.strasbourg.service.favorite.model.Favorite deleteFavorite(
		long favoriteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteFavorite(favoriteId);
	}

	public static eu.strasbourg.service.favorite.model.Favorite fetchFavorite(
		long favoriteId) {
		return getService().fetchFavorite(favoriteId);
	}

	/**
	* Returns the favorite with the primary key.
	*
	* @param favoriteId the primary key of the favorite
	* @return the favorite
	* @throws PortalException if a favorite with the primary key could not be found
	*/
	public static eu.strasbourg.service.favorite.model.Favorite getFavorite(
		long favoriteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFavorite(favoriteId);
	}

	/**
	* Updates the favorite in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param favorite the favorite
	* @return the favorite that was updated
	*/
	public static eu.strasbourg.service.favorite.model.Favorite updateFavorite(
		eu.strasbourg.service.favorite.model.Favorite favorite) {
		return getService().updateFavorite(favorite);
	}

	/**
	* Returns the number of favorites.
	*
	* @return the number of favorites
	*/
	public static int getFavoritesCount() {
		return getService().getFavoritesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.favorite.model.impl.FavoriteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.favorite.model.impl.FavoriteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Retourne la liste des favoris d'un utilisateur
	*/
	public static java.util.List<eu.strasbourg.service.favorite.model.Favorite> getByPublikUser(
		java.lang.String publikUserId) {
		return getService().getByPublikUser(publikUserId);
	}

	/**
	* Returns a range of all the favorites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.favorite.model.impl.FavoriteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of favorites
	* @param end the upper bound of the range of favorites (not inclusive)
	* @return the range of favorites
	*/
	public static java.util.List<eu.strasbourg.service.favorite.model.Favorite> getFavorites(
		int start, int end) {
		return getService().getFavorites(start, end);
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

	public static void deleteFavoriteByEntityIdAndType(long entityId,
		long typeId) {
		getService().deleteFavoriteByEntityIdAndType(entityId, typeId);
	}

	public static FavoriteLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FavoriteLocalService, FavoriteLocalService> _serviceTracker =
		ServiceTrackerFactory.open(FavoriteLocalService.class);
}