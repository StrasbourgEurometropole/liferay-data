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

package eu.strasbourg.service.favorite.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.favorite.model.Favorite;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the favorite service. This utility wraps <code>eu.strasbourg.service.favorite.service.persistence.impl.FavoritePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see FavoritePersistence
 * @generated
 */
public class FavoriteUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Favorite favorite) {
		getPersistence().clearCache(favorite);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Favorite> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Favorite> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Favorite> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Favorite> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Favorite> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Favorite update(Favorite favorite) {
		return getPersistence().update(favorite);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Favorite update(
		Favorite favorite, ServiceContext serviceContext) {

		return getPersistence().update(favorite, serviceContext);
	}

	/**
	 * Returns all the favorites where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the matching favorites
	 */
	public static List<Favorite> findByPublikUserId(String publikUserId) {
		return getPersistence().findByPublikUserId(publikUserId);
	}

	/**
	 * Returns a range of all the favorites where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FavoriteModelImpl</code>.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of favorites
	 * @param end the upper bound of the range of favorites (not inclusive)
	 * @return the range of matching favorites
	 */
	public static List<Favorite> findByPublikUserId(
		String publikUserId, int start, int end) {

		return getPersistence().findByPublikUserId(publikUserId, start, end);
	}

	/**
	 * Returns an ordered range of all the favorites where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FavoriteModelImpl</code>.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of favorites
	 * @param end the upper bound of the range of favorites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching favorites
	 */
	public static List<Favorite> findByPublikUserId(
		String publikUserId, int start, int end,
		OrderByComparator<Favorite> orderByComparator) {

		return getPersistence().findByPublikUserId(
			publikUserId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the favorites where publikUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FavoriteModelImpl</code>.
	 * </p>
	 *
	 * @param publikUserId the publik user ID
	 * @param start the lower bound of the range of favorites
	 * @param end the upper bound of the range of favorites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching favorites
	 */
	public static List<Favorite> findByPublikUserId(
		String publikUserId, int start, int end,
		OrderByComparator<Favorite> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByPublikUserId(
			publikUserId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first favorite in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching favorite
	 * @throws NoSuchFavoriteException if a matching favorite could not be found
	 */
	public static Favorite findByPublikUserId_First(
			String publikUserId, OrderByComparator<Favorite> orderByComparator)
		throws eu.strasbourg.service.favorite.exception.
			NoSuchFavoriteException {

		return getPersistence().findByPublikUserId_First(
			publikUserId, orderByComparator);
	}

	/**
	 * Returns the first favorite in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching favorite, or <code>null</code> if a matching favorite could not be found
	 */
	public static Favorite fetchByPublikUserId_First(
		String publikUserId, OrderByComparator<Favorite> orderByComparator) {

		return getPersistence().fetchByPublikUserId_First(
			publikUserId, orderByComparator);
	}

	/**
	 * Returns the last favorite in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching favorite
	 * @throws NoSuchFavoriteException if a matching favorite could not be found
	 */
	public static Favorite findByPublikUserId_Last(
			String publikUserId, OrderByComparator<Favorite> orderByComparator)
		throws eu.strasbourg.service.favorite.exception.
			NoSuchFavoriteException {

		return getPersistence().findByPublikUserId_Last(
			publikUserId, orderByComparator);
	}

	/**
	 * Returns the last favorite in the ordered set where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching favorite, or <code>null</code> if a matching favorite could not be found
	 */
	public static Favorite fetchByPublikUserId_Last(
		String publikUserId, OrderByComparator<Favorite> orderByComparator) {

		return getPersistence().fetchByPublikUserId_Last(
			publikUserId, orderByComparator);
	}

	/**
	 * Returns the favorites before and after the current favorite in the ordered set where publikUserId = &#63;.
	 *
	 * @param favoriteId the primary key of the current favorite
	 * @param publikUserId the publik user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next favorite
	 * @throws NoSuchFavoriteException if a favorite with the primary key could not be found
	 */
	public static Favorite[] findByPublikUserId_PrevAndNext(
			long favoriteId, String publikUserId,
			OrderByComparator<Favorite> orderByComparator)
		throws eu.strasbourg.service.favorite.exception.
			NoSuchFavoriteException {

		return getPersistence().findByPublikUserId_PrevAndNext(
			favoriteId, publikUserId, orderByComparator);
	}

	/**
	 * Removes all the favorites where publikUserId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 */
	public static void removeByPublikUserId(String publikUserId) {
		getPersistence().removeByPublikUserId(publikUserId);
	}

	/**
	 * Returns the number of favorites where publikUserId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @return the number of matching favorites
	 */
	public static int countByPublikUserId(String publikUserId) {
		return getPersistence().countByPublikUserId(publikUserId);
	}

	/**
	 * Returns the favorite where publikUserId = &#63; and title = &#63; and typeId = &#63; and entityId = &#63; or throws a <code>NoSuchFavoriteException</code> if it could not be found.
	 *
	 * @param publikUserId the publik user ID
	 * @param title the title
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @return the matching favorite
	 * @throws NoSuchFavoriteException if a matching favorite could not be found
	 */
	public static Favorite findByAllAttributes(
			String publikUserId, String title, long typeId, long entityId)
		throws eu.strasbourg.service.favorite.exception.
			NoSuchFavoriteException {

		return getPersistence().findByAllAttributes(
			publikUserId, title, typeId, entityId);
	}

	/**
	 * Returns the favorite where publikUserId = &#63; and title = &#63; and typeId = &#63; and entityId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param publikUserId the publik user ID
	 * @param title the title
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @return the matching favorite, or <code>null</code> if a matching favorite could not be found
	 */
	public static Favorite fetchByAllAttributes(
		String publikUserId, String title, long typeId, long entityId) {

		return getPersistence().fetchByAllAttributes(
			publikUserId, title, typeId, entityId);
	}

	/**
	 * Returns the favorite where publikUserId = &#63; and title = &#63; and typeId = &#63; and entityId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param publikUserId the publik user ID
	 * @param title the title
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching favorite, or <code>null</code> if a matching favorite could not be found
	 */
	public static Favorite fetchByAllAttributes(
		String publikUserId, String title, long typeId, long entityId,
		boolean useFinderCache) {

		return getPersistence().fetchByAllAttributes(
			publikUserId, title, typeId, entityId, useFinderCache);
	}

	/**
	 * Removes the favorite where publikUserId = &#63; and title = &#63; and typeId = &#63; and entityId = &#63; from the database.
	 *
	 * @param publikUserId the publik user ID
	 * @param title the title
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @return the favorite that was removed
	 */
	public static Favorite removeByAllAttributes(
			String publikUserId, String title, long typeId, long entityId)
		throws eu.strasbourg.service.favorite.exception.
			NoSuchFavoriteException {

		return getPersistence().removeByAllAttributes(
			publikUserId, title, typeId, entityId);
	}

	/**
	 * Returns the number of favorites where publikUserId = &#63; and title = &#63; and typeId = &#63; and entityId = &#63;.
	 *
	 * @param publikUserId the publik user ID
	 * @param title the title
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @return the number of matching favorites
	 */
	public static int countByAllAttributes(
		String publikUserId, String title, long typeId, long entityId) {

		return getPersistence().countByAllAttributes(
			publikUserId, title, typeId, entityId);
	}

	/**
	 * Returns all the favorites where entityId = &#63; and typeId = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @return the matching favorites
	 */
	public static List<Favorite> findByEntityIdAndTypeId(
		long entityId, long typeId) {

		return getPersistence().findByEntityIdAndTypeId(entityId, typeId);
	}

	/**
	 * Returns a range of all the favorites where entityId = &#63; and typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FavoriteModelImpl</code>.
	 * </p>
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param start the lower bound of the range of favorites
	 * @param end the upper bound of the range of favorites (not inclusive)
	 * @return the range of matching favorites
	 */
	public static List<Favorite> findByEntityIdAndTypeId(
		long entityId, long typeId, int start, int end) {

		return getPersistence().findByEntityIdAndTypeId(
			entityId, typeId, start, end);
	}

	/**
	 * Returns an ordered range of all the favorites where entityId = &#63; and typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FavoriteModelImpl</code>.
	 * </p>
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param start the lower bound of the range of favorites
	 * @param end the upper bound of the range of favorites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching favorites
	 */
	public static List<Favorite> findByEntityIdAndTypeId(
		long entityId, long typeId, int start, int end,
		OrderByComparator<Favorite> orderByComparator) {

		return getPersistence().findByEntityIdAndTypeId(
			entityId, typeId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the favorites where entityId = &#63; and typeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FavoriteModelImpl</code>.
	 * </p>
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param start the lower bound of the range of favorites
	 * @param end the upper bound of the range of favorites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching favorites
	 */
	public static List<Favorite> findByEntityIdAndTypeId(
		long entityId, long typeId, int start, int end,
		OrderByComparator<Favorite> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByEntityIdAndTypeId(
			entityId, typeId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first favorite in the ordered set where entityId = &#63; and typeId = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching favorite
	 * @throws NoSuchFavoriteException if a matching favorite could not be found
	 */
	public static Favorite findByEntityIdAndTypeId_First(
			long entityId, long typeId,
			OrderByComparator<Favorite> orderByComparator)
		throws eu.strasbourg.service.favorite.exception.
			NoSuchFavoriteException {

		return getPersistence().findByEntityIdAndTypeId_First(
			entityId, typeId, orderByComparator);
	}

	/**
	 * Returns the first favorite in the ordered set where entityId = &#63; and typeId = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching favorite, or <code>null</code> if a matching favorite could not be found
	 */
	public static Favorite fetchByEntityIdAndTypeId_First(
		long entityId, long typeId,
		OrderByComparator<Favorite> orderByComparator) {

		return getPersistence().fetchByEntityIdAndTypeId_First(
			entityId, typeId, orderByComparator);
	}

	/**
	 * Returns the last favorite in the ordered set where entityId = &#63; and typeId = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching favorite
	 * @throws NoSuchFavoriteException if a matching favorite could not be found
	 */
	public static Favorite findByEntityIdAndTypeId_Last(
			long entityId, long typeId,
			OrderByComparator<Favorite> orderByComparator)
		throws eu.strasbourg.service.favorite.exception.
			NoSuchFavoriteException {

		return getPersistence().findByEntityIdAndTypeId_Last(
			entityId, typeId, orderByComparator);
	}

	/**
	 * Returns the last favorite in the ordered set where entityId = &#63; and typeId = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching favorite, or <code>null</code> if a matching favorite could not be found
	 */
	public static Favorite fetchByEntityIdAndTypeId_Last(
		long entityId, long typeId,
		OrderByComparator<Favorite> orderByComparator) {

		return getPersistence().fetchByEntityIdAndTypeId_Last(
			entityId, typeId, orderByComparator);
	}

	/**
	 * Returns the favorites before and after the current favorite in the ordered set where entityId = &#63; and typeId = &#63;.
	 *
	 * @param favoriteId the primary key of the current favorite
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next favorite
	 * @throws NoSuchFavoriteException if a favorite with the primary key could not be found
	 */
	public static Favorite[] findByEntityIdAndTypeId_PrevAndNext(
			long favoriteId, long entityId, long typeId,
			OrderByComparator<Favorite> orderByComparator)
		throws eu.strasbourg.service.favorite.exception.
			NoSuchFavoriteException {

		return getPersistence().findByEntityIdAndTypeId_PrevAndNext(
			favoriteId, entityId, typeId, orderByComparator);
	}

	/**
	 * Removes all the favorites where entityId = &#63; and typeId = &#63; from the database.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 */
	public static void removeByEntityIdAndTypeId(long entityId, long typeId) {
		getPersistence().removeByEntityIdAndTypeId(entityId, typeId);
	}

	/**
	 * Returns the number of favorites where entityId = &#63; and typeId = &#63;.
	 *
	 * @param entityId the entity ID
	 * @param typeId the type ID
	 * @return the number of matching favorites
	 */
	public static int countByEntityIdAndTypeId(long entityId, long typeId) {
		return getPersistence().countByEntityIdAndTypeId(entityId, typeId);
	}

	/**
	 * Returns all the favorites where typeId = &#63; and entityId = &#63; and publikUserId = &#63; and content = &#63;.
	 *
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @param publikUserId the publik user ID
	 * @param content the content
	 * @return the matching favorites
	 */
	public static List<Favorite>
		findByTypeIdAndEntityIdAndPublikUserIdAndContent(
			long typeId, long entityId, String publikUserId, String content) {

		return getPersistence().
			findByTypeIdAndEntityIdAndPublikUserIdAndContent(
				typeId, entityId, publikUserId, content);
	}

	/**
	 * Returns a range of all the favorites where typeId = &#63; and entityId = &#63; and publikUserId = &#63; and content = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FavoriteModelImpl</code>.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @param publikUserId the publik user ID
	 * @param content the content
	 * @param start the lower bound of the range of favorites
	 * @param end the upper bound of the range of favorites (not inclusive)
	 * @return the range of matching favorites
	 */
	public static List<Favorite>
		findByTypeIdAndEntityIdAndPublikUserIdAndContent(
			long typeId, long entityId, String publikUserId, String content,
			int start, int end) {

		return getPersistence().
			findByTypeIdAndEntityIdAndPublikUserIdAndContent(
				typeId, entityId, publikUserId, content, start, end);
	}

	/**
	 * Returns an ordered range of all the favorites where typeId = &#63; and entityId = &#63; and publikUserId = &#63; and content = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FavoriteModelImpl</code>.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @param publikUserId the publik user ID
	 * @param content the content
	 * @param start the lower bound of the range of favorites
	 * @param end the upper bound of the range of favorites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching favorites
	 */
	public static List<Favorite>
		findByTypeIdAndEntityIdAndPublikUserIdAndContent(
			long typeId, long entityId, String publikUserId, String content,
			int start, int end, OrderByComparator<Favorite> orderByComparator) {

		return getPersistence().
			findByTypeIdAndEntityIdAndPublikUserIdAndContent(
				typeId, entityId, publikUserId, content, start, end,
				orderByComparator);
	}

	/**
	 * Returns an ordered range of all the favorites where typeId = &#63; and entityId = &#63; and publikUserId = &#63; and content = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FavoriteModelImpl</code>.
	 * </p>
	 *
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @param publikUserId the publik user ID
	 * @param content the content
	 * @param start the lower bound of the range of favorites
	 * @param end the upper bound of the range of favorites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching favorites
	 */
	public static List<Favorite>
		findByTypeIdAndEntityIdAndPublikUserIdAndContent(
			long typeId, long entityId, String publikUserId, String content,
			int start, int end, OrderByComparator<Favorite> orderByComparator,
			boolean useFinderCache) {

		return getPersistence().
			findByTypeIdAndEntityIdAndPublikUserIdAndContent(
				typeId, entityId, publikUserId, content, start, end,
				orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first favorite in the ordered set where typeId = &#63; and entityId = &#63; and publikUserId = &#63; and content = &#63;.
	 *
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @param publikUserId the publik user ID
	 * @param content the content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching favorite
	 * @throws NoSuchFavoriteException if a matching favorite could not be found
	 */
	public static Favorite
			findByTypeIdAndEntityIdAndPublikUserIdAndContent_First(
				long typeId, long entityId, String publikUserId, String content,
				OrderByComparator<Favorite> orderByComparator)
		throws eu.strasbourg.service.favorite.exception.
			NoSuchFavoriteException {

		return getPersistence().
			findByTypeIdAndEntityIdAndPublikUserIdAndContent_First(
				typeId, entityId, publikUserId, content, orderByComparator);
	}

	/**
	 * Returns the first favorite in the ordered set where typeId = &#63; and entityId = &#63; and publikUserId = &#63; and content = &#63;.
	 *
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @param publikUserId the publik user ID
	 * @param content the content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching favorite, or <code>null</code> if a matching favorite could not be found
	 */
	public static Favorite
		fetchByTypeIdAndEntityIdAndPublikUserIdAndContent_First(
			long typeId, long entityId, String publikUserId, String content,
			OrderByComparator<Favorite> orderByComparator) {

		return getPersistence().
			fetchByTypeIdAndEntityIdAndPublikUserIdAndContent_First(
				typeId, entityId, publikUserId, content, orderByComparator);
	}

	/**
	 * Returns the last favorite in the ordered set where typeId = &#63; and entityId = &#63; and publikUserId = &#63; and content = &#63;.
	 *
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @param publikUserId the publik user ID
	 * @param content the content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching favorite
	 * @throws NoSuchFavoriteException if a matching favorite could not be found
	 */
	public static Favorite
			findByTypeIdAndEntityIdAndPublikUserIdAndContent_Last(
				long typeId, long entityId, String publikUserId, String content,
				OrderByComparator<Favorite> orderByComparator)
		throws eu.strasbourg.service.favorite.exception.
			NoSuchFavoriteException {

		return getPersistence().
			findByTypeIdAndEntityIdAndPublikUserIdAndContent_Last(
				typeId, entityId, publikUserId, content, orderByComparator);
	}

	/**
	 * Returns the last favorite in the ordered set where typeId = &#63; and entityId = &#63; and publikUserId = &#63; and content = &#63;.
	 *
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @param publikUserId the publik user ID
	 * @param content the content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching favorite, or <code>null</code> if a matching favorite could not be found
	 */
	public static Favorite
		fetchByTypeIdAndEntityIdAndPublikUserIdAndContent_Last(
			long typeId, long entityId, String publikUserId, String content,
			OrderByComparator<Favorite> orderByComparator) {

		return getPersistence().
			fetchByTypeIdAndEntityIdAndPublikUserIdAndContent_Last(
				typeId, entityId, publikUserId, content, orderByComparator);
	}

	/**
	 * Returns the favorites before and after the current favorite in the ordered set where typeId = &#63; and entityId = &#63; and publikUserId = &#63; and content = &#63;.
	 *
	 * @param favoriteId the primary key of the current favorite
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @param publikUserId the publik user ID
	 * @param content the content
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next favorite
	 * @throws NoSuchFavoriteException if a favorite with the primary key could not be found
	 */
	public static Favorite[]
			findByTypeIdAndEntityIdAndPublikUserIdAndContent_PrevAndNext(
				long favoriteId, long typeId, long entityId,
				String publikUserId, String content,
				OrderByComparator<Favorite> orderByComparator)
		throws eu.strasbourg.service.favorite.exception.
			NoSuchFavoriteException {

		return getPersistence().
			findByTypeIdAndEntityIdAndPublikUserIdAndContent_PrevAndNext(
				favoriteId, typeId, entityId, publikUserId, content,
				orderByComparator);
	}

	/**
	 * Removes all the favorites where typeId = &#63; and entityId = &#63; and publikUserId = &#63; and content = &#63; from the database.
	 *
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @param publikUserId the publik user ID
	 * @param content the content
	 */
	public static void removeByTypeIdAndEntityIdAndPublikUserIdAndContent(
		long typeId, long entityId, String publikUserId, String content) {

		getPersistence().removeByTypeIdAndEntityIdAndPublikUserIdAndContent(
			typeId, entityId, publikUserId, content);
	}

	/**
	 * Returns the number of favorites where typeId = &#63; and entityId = &#63; and publikUserId = &#63; and content = &#63;.
	 *
	 * @param typeId the type ID
	 * @param entityId the entity ID
	 * @param publikUserId the publik user ID
	 * @param content the content
	 * @return the number of matching favorites
	 */
	public static int countByTypeIdAndEntityIdAndPublikUserIdAndContent(
		long typeId, long entityId, String publikUserId, String content) {

		return getPersistence().
			countByTypeIdAndEntityIdAndPublikUserIdAndContent(
				typeId, entityId, publikUserId, content);
	}

	/**
	 * Caches the favorite in the entity cache if it is enabled.
	 *
	 * @param favorite the favorite
	 */
	public static void cacheResult(Favorite favorite) {
		getPersistence().cacheResult(favorite);
	}

	/**
	 * Caches the favorites in the entity cache if it is enabled.
	 *
	 * @param favorites the favorites
	 */
	public static void cacheResult(List<Favorite> favorites) {
		getPersistence().cacheResult(favorites);
	}

	/**
	 * Creates a new favorite with the primary key. Does not add the favorite to the database.
	 *
	 * @param favoriteId the primary key for the new favorite
	 * @return the new favorite
	 */
	public static Favorite create(long favoriteId) {
		return getPersistence().create(favoriteId);
	}

	/**
	 * Removes the favorite with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param favoriteId the primary key of the favorite
	 * @return the favorite that was removed
	 * @throws NoSuchFavoriteException if a favorite with the primary key could not be found
	 */
	public static Favorite remove(long favoriteId)
		throws eu.strasbourg.service.favorite.exception.
			NoSuchFavoriteException {

		return getPersistence().remove(favoriteId);
	}

	public static Favorite updateImpl(Favorite favorite) {
		return getPersistence().updateImpl(favorite);
	}

	/**
	 * Returns the favorite with the primary key or throws a <code>NoSuchFavoriteException</code> if it could not be found.
	 *
	 * @param favoriteId the primary key of the favorite
	 * @return the favorite
	 * @throws NoSuchFavoriteException if a favorite with the primary key could not be found
	 */
	public static Favorite findByPrimaryKey(long favoriteId)
		throws eu.strasbourg.service.favorite.exception.
			NoSuchFavoriteException {

		return getPersistence().findByPrimaryKey(favoriteId);
	}

	/**
	 * Returns the favorite with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param favoriteId the primary key of the favorite
	 * @return the favorite, or <code>null</code> if a favorite with the primary key could not be found
	 */
	public static Favorite fetchByPrimaryKey(long favoriteId) {
		return getPersistence().fetchByPrimaryKey(favoriteId);
	}

	/**
	 * Returns all the favorites.
	 *
	 * @return the favorites
	 */
	public static List<Favorite> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the favorites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FavoriteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of favorites
	 * @param end the upper bound of the range of favorites (not inclusive)
	 * @return the range of favorites
	 */
	public static List<Favorite> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the favorites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FavoriteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of favorites
	 * @param end the upper bound of the range of favorites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of favorites
	 */
	public static List<Favorite> findAll(
		int start, int end, OrderByComparator<Favorite> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the favorites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FavoriteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of favorites
	 * @param end the upper bound of the range of favorites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of favorites
	 */
	public static List<Favorite> findAll(
		int start, int end, OrderByComparator<Favorite> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the favorites from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of favorites.
	 *
	 * @return the number of favorites
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static FavoritePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FavoritePersistence, FavoritePersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(FavoritePersistence.class);

		ServiceTracker<FavoritePersistence, FavoritePersistence>
			serviceTracker =
				new ServiceTracker<FavoritePersistence, FavoritePersistence>(
					bundle.getBundleContext(), FavoritePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}