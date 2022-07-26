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

import eu.strasbourg.service.csmap.exception.NoSuchThematicException;
import eu.strasbourg.service.csmap.model.Thematic;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the thematic service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ThematicUtil
 * @generated
 */
@ProviderType
public interface ThematicPersistence extends BasePersistence<Thematic> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ThematicUtil} to access the thematic persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the thematics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching thematics
	 */
	public java.util.List<Thematic> findByUuid(String uuid);

	/**
	 * Returns a range of all the thematics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ThematicModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of thematics
	 * @param end the upper bound of the range of thematics (not inclusive)
	 * @return the range of matching thematics
	 */
	public java.util.List<Thematic> findByUuid(String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the thematics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ThematicModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of thematics
	 * @param end the upper bound of the range of thematics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching thematics
	 */
	public java.util.List<Thematic> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Thematic>
			orderByComparator);

	/**
	 * Returns an ordered range of all the thematics where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ThematicModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of thematics
	 * @param end the upper bound of the range of thematics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching thematics
	 */
	public java.util.List<Thematic> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Thematic>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first thematic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching thematic
	 * @throws NoSuchThematicException if a matching thematic could not be found
	 */
	public Thematic findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Thematic>
				orderByComparator)
		throws NoSuchThematicException;

	/**
	 * Returns the first thematic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching thematic, or <code>null</code> if a matching thematic could not be found
	 */
	public Thematic fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Thematic>
			orderByComparator);

	/**
	 * Returns the last thematic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching thematic
	 * @throws NoSuchThematicException if a matching thematic could not be found
	 */
	public Thematic findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Thematic>
				orderByComparator)
		throws NoSuchThematicException;

	/**
	 * Returns the last thematic in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching thematic, or <code>null</code> if a matching thematic could not be found
	 */
	public Thematic fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Thematic>
			orderByComparator);

	/**
	 * Returns the thematics before and after the current thematic in the ordered set where uuid = &#63;.
	 *
	 * @param thematicId the primary key of the current thematic
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next thematic
	 * @throws NoSuchThematicException if a thematic with the primary key could not be found
	 */
	public Thematic[] findByUuid_PrevAndNext(
			long thematicId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<Thematic>
				orderByComparator)
		throws NoSuchThematicException;

	/**
	 * Removes all the thematics where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of thematics where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching thematics
	 */
	public int countByUuid(String uuid);

	/**
	 * Caches the thematic in the entity cache if it is enabled.
	 *
	 * @param thematic the thematic
	 */
	public void cacheResult(Thematic thematic);

	/**
	 * Caches the thematics in the entity cache if it is enabled.
	 *
	 * @param thematics the thematics
	 */
	public void cacheResult(java.util.List<Thematic> thematics);

	/**
	 * Creates a new thematic with the primary key. Does not add the thematic to the database.
	 *
	 * @param thematicId the primary key for the new thematic
	 * @return the new thematic
	 */
	public Thematic create(long thematicId);

	/**
	 * Removes the thematic with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param thematicId the primary key of the thematic
	 * @return the thematic that was removed
	 * @throws NoSuchThematicException if a thematic with the primary key could not be found
	 */
	public Thematic remove(long thematicId) throws NoSuchThematicException;

	public Thematic updateImpl(Thematic thematic);

	/**
	 * Returns the thematic with the primary key or throws a <code>NoSuchThematicException</code> if it could not be found.
	 *
	 * @param thematicId the primary key of the thematic
	 * @return the thematic
	 * @throws NoSuchThematicException if a thematic with the primary key could not be found
	 */
	public Thematic findByPrimaryKey(long thematicId)
		throws NoSuchThematicException;

	/**
	 * Returns the thematic with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param thematicId the primary key of the thematic
	 * @return the thematic, or <code>null</code> if a thematic with the primary key could not be found
	 */
	public Thematic fetchByPrimaryKey(long thematicId);

	/**
	 * Returns all the thematics.
	 *
	 * @return the thematics
	 */
	public java.util.List<Thematic> findAll();

	/**
	 * Returns a range of all the thematics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ThematicModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of thematics
	 * @param end the upper bound of the range of thematics (not inclusive)
	 * @return the range of thematics
	 */
	public java.util.List<Thematic> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the thematics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ThematicModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of thematics
	 * @param end the upper bound of the range of thematics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of thematics
	 */
	public java.util.List<Thematic> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Thematic>
			orderByComparator);

	/**
	 * Returns an ordered range of all the thematics.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ThematicModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of thematics
	 * @param end the upper bound of the range of thematics (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of thematics
	 */
	public java.util.List<Thematic> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Thematic>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the thematics from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of thematics.
	 *
	 * @return the number of thematics
	 */
	public int countAll();

}