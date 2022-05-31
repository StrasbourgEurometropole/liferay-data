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

package eu.strasbourg.service.csmap.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.csmap.exception.NoSuchPlaceCategoriesException;
import eu.strasbourg.service.csmap.model.PlaceCategories;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the place categories service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlaceCategoriesUtil
 * @generated
 */
@ProviderType
public interface PlaceCategoriesPersistence
	extends BasePersistence<PlaceCategories> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PlaceCategoriesUtil} to access the place categories persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the place categorieses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching place categorieses
	 */
	public java.util.List<PlaceCategories> findByUuid(String uuid);

	/**
	 * Returns a range of all the place categorieses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlaceCategoriesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of place categorieses
	 * @param end the upper bound of the range of place categorieses (not inclusive)
	 * @return the range of matching place categorieses
	 */
	public java.util.List<PlaceCategories> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the place categorieses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlaceCategoriesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of place categorieses
	 * @param end the upper bound of the range of place categorieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching place categorieses
	 */
	public java.util.List<PlaceCategories> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlaceCategories>
			orderByComparator);

	/**
	 * Returns an ordered range of all the place categorieses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlaceCategoriesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of place categorieses
	 * @param end the upper bound of the range of place categorieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching place categorieses
	 */
	public java.util.List<PlaceCategories> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlaceCategories>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first place categories in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching place categories
	 * @throws NoSuchPlaceCategoriesException if a matching place categories could not be found
	 */
	public PlaceCategories findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<PlaceCategories>
				orderByComparator)
		throws NoSuchPlaceCategoriesException;

	/**
	 * Returns the first place categories in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching place categories, or <code>null</code> if a matching place categories could not be found
	 */
	public PlaceCategories fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PlaceCategories>
			orderByComparator);

	/**
	 * Returns the last place categories in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching place categories
	 * @throws NoSuchPlaceCategoriesException if a matching place categories could not be found
	 */
	public PlaceCategories findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<PlaceCategories>
				orderByComparator)
		throws NoSuchPlaceCategoriesException;

	/**
	 * Returns the last place categories in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching place categories, or <code>null</code> if a matching place categories could not be found
	 */
	public PlaceCategories fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<PlaceCategories>
			orderByComparator);

	/**
	 * Returns the place categorieses before and after the current place categories in the ordered set where uuid = &#63;.
	 *
	 * @param placeCategoriesId the primary key of the current place categories
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next place categories
	 * @throws NoSuchPlaceCategoriesException if a place categories with the primary key could not be found
	 */
	public PlaceCategories[] findByUuid_PrevAndNext(
			long placeCategoriesId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<PlaceCategories>
				orderByComparator)
		throws NoSuchPlaceCategoriesException;

	/**
	 * Removes all the place categorieses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of place categorieses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching place categorieses
	 */
	public int countByUuid(String uuid);

	/**
	 * Caches the place categories in the entity cache if it is enabled.
	 *
	 * @param placeCategories the place categories
	 */
	public void cacheResult(PlaceCategories placeCategories);

	/**
	 * Caches the place categorieses in the entity cache if it is enabled.
	 *
	 * @param placeCategorieses the place categorieses
	 */
	public void cacheResult(java.util.List<PlaceCategories> placeCategorieses);

	/**
	 * Creates a new place categories with the primary key. Does not add the place categories to the database.
	 *
	 * @param placeCategoriesId the primary key for the new place categories
	 * @return the new place categories
	 */
	public PlaceCategories create(long placeCategoriesId);

	/**
	 * Removes the place categories with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param placeCategoriesId the primary key of the place categories
	 * @return the place categories that was removed
	 * @throws NoSuchPlaceCategoriesException if a place categories with the primary key could not be found
	 */
	public PlaceCategories remove(long placeCategoriesId)
		throws NoSuchPlaceCategoriesException;

	public PlaceCategories updateImpl(PlaceCategories placeCategories);

	/**
	 * Returns the place categories with the primary key or throws a <code>NoSuchPlaceCategoriesException</code> if it could not be found.
	 *
	 * @param placeCategoriesId the primary key of the place categories
	 * @return the place categories
	 * @throws NoSuchPlaceCategoriesException if a place categories with the primary key could not be found
	 */
	public PlaceCategories findByPrimaryKey(long placeCategoriesId)
		throws NoSuchPlaceCategoriesException;

	/**
	 * Returns the place categories with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param placeCategoriesId the primary key of the place categories
	 * @return the place categories, or <code>null</code> if a place categories with the primary key could not be found
	 */
	public PlaceCategories fetchByPrimaryKey(long placeCategoriesId);

	/**
	 * Returns all the place categorieses.
	 *
	 * @return the place categorieses
	 */
	public java.util.List<PlaceCategories> findAll();

	/**
	 * Returns a range of all the place categorieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlaceCategoriesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of place categorieses
	 * @param end the upper bound of the range of place categorieses (not inclusive)
	 * @return the range of place categorieses
	 */
	public java.util.List<PlaceCategories> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the place categorieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlaceCategoriesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of place categorieses
	 * @param end the upper bound of the range of place categorieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of place categorieses
	 */
	public java.util.List<PlaceCategories> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlaceCategories>
			orderByComparator);

	/**
	 * Returns an ordered range of all the place categorieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>PlaceCategoriesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of place categorieses
	 * @param end the upper bound of the range of place categorieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of place categorieses
	 */
	public java.util.List<PlaceCategories> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PlaceCategories>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the place categorieses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of place categorieses.
	 *
	 * @return the number of place categorieses
	 */
	public int countAll();

}