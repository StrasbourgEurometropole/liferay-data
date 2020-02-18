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

package eu.strasbourg.service.objtp.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.objtp.exception.NoSuchFoundObjectException;
import eu.strasbourg.service.objtp.model.FoundObject;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the found object service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author JeremyZwickert
 * @see FoundObjectUtil
 * @generated
 */
@ProviderType
public interface FoundObjectPersistence extends BasePersistence<FoundObject> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FoundObjectUtil} to access the found object persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, FoundObject> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the found objects where categoryCode = &#63;.
	 *
	 * @param categoryCode the category code
	 * @return the matching found objects
	 */
	public java.util.List<FoundObject> findByCategoryCode(String categoryCode);

	/**
	 * Returns a range of all the found objects where categoryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FoundObjectModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param categoryCode the category code
	 * @param start the lower bound of the range of found objects
	 * @param end the upper bound of the range of found objects (not inclusive)
	 * @return the range of matching found objects
	 */
	public java.util.List<FoundObject> findByCategoryCode(
		String categoryCode, int start, int end);

	/**
	 * Returns an ordered range of all the found objects where categoryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FoundObjectModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param categoryCode the category code
	 * @param start the lower bound of the range of found objects
	 * @param end the upper bound of the range of found objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching found objects
	 */
	public java.util.List<FoundObject> findByCategoryCode(
		String categoryCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FoundObject>
			orderByComparator);

	/**
	 * Returns an ordered range of all the found objects where categoryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FoundObjectModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param categoryCode the category code
	 * @param start the lower bound of the range of found objects
	 * @param end the upper bound of the range of found objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching found objects
	 */
	public java.util.List<FoundObject> findByCategoryCode(
		String categoryCode, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FoundObject>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first found object in the ordered set where categoryCode = &#63;.
	 *
	 * @param categoryCode the category code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching found object
	 * @throws NoSuchFoundObjectException if a matching found object could not be found
	 */
	public FoundObject findByCategoryCode_First(
			String categoryCode,
			com.liferay.portal.kernel.util.OrderByComparator<FoundObject>
				orderByComparator)
		throws NoSuchFoundObjectException;

	/**
	 * Returns the first found object in the ordered set where categoryCode = &#63;.
	 *
	 * @param categoryCode the category code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching found object, or <code>null</code> if a matching found object could not be found
	 */
	public FoundObject fetchByCategoryCode_First(
		String categoryCode,
		com.liferay.portal.kernel.util.OrderByComparator<FoundObject>
			orderByComparator);

	/**
	 * Returns the last found object in the ordered set where categoryCode = &#63;.
	 *
	 * @param categoryCode the category code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching found object
	 * @throws NoSuchFoundObjectException if a matching found object could not be found
	 */
	public FoundObject findByCategoryCode_Last(
			String categoryCode,
			com.liferay.portal.kernel.util.OrderByComparator<FoundObject>
				orderByComparator)
		throws NoSuchFoundObjectException;

	/**
	 * Returns the last found object in the ordered set where categoryCode = &#63;.
	 *
	 * @param categoryCode the category code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching found object, or <code>null</code> if a matching found object could not be found
	 */
	public FoundObject fetchByCategoryCode_Last(
		String categoryCode,
		com.liferay.portal.kernel.util.OrderByComparator<FoundObject>
			orderByComparator);

	/**
	 * Returns the found objects before and after the current found object in the ordered set where categoryCode = &#63;.
	 *
	 * @param number the primary key of the current found object
	 * @param categoryCode the category code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next found object
	 * @throws NoSuchFoundObjectException if a found object with the primary key could not be found
	 */
	public FoundObject[] findByCategoryCode_PrevAndNext(
			String number, String categoryCode,
			com.liferay.portal.kernel.util.OrderByComparator<FoundObject>
				orderByComparator)
		throws NoSuchFoundObjectException;

	/**
	 * Removes all the found objects where categoryCode = &#63; from the database.
	 *
	 * @param categoryCode the category code
	 */
	public void removeByCategoryCode(String categoryCode);

	/**
	 * Returns the number of found objects where categoryCode = &#63;.
	 *
	 * @param categoryCode the category code
	 * @return the number of matching found objects
	 */
	public int countByCategoryCode(String categoryCode);

	/**
	 * Caches the found object in the entity cache if it is enabled.
	 *
	 * @param foundObject the found object
	 */
	public void cacheResult(FoundObject foundObject);

	/**
	 * Caches the found objects in the entity cache if it is enabled.
	 *
	 * @param foundObjects the found objects
	 */
	public void cacheResult(java.util.List<FoundObject> foundObjects);

	/**
	 * Creates a new found object with the primary key. Does not add the found object to the database.
	 *
	 * @param number the primary key for the new found object
	 * @return the new found object
	 */
	public FoundObject create(String number);

	/**
	 * Removes the found object with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param number the primary key of the found object
	 * @return the found object that was removed
	 * @throws NoSuchFoundObjectException if a found object with the primary key could not be found
	 */
	public FoundObject remove(String number) throws NoSuchFoundObjectException;

	public FoundObject updateImpl(FoundObject foundObject);

	/**
	 * Returns the found object with the primary key or throws a <code>NoSuchFoundObjectException</code> if it could not be found.
	 *
	 * @param number the primary key of the found object
	 * @return the found object
	 * @throws NoSuchFoundObjectException if a found object with the primary key could not be found
	 */
	public FoundObject findByPrimaryKey(String number)
		throws NoSuchFoundObjectException;

	/**
	 * Returns the found object with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param number the primary key of the found object
	 * @return the found object, or <code>null</code> if a found object with the primary key could not be found
	 */
	public FoundObject fetchByPrimaryKey(String number);

	/**
	 * Returns all the found objects.
	 *
	 * @return the found objects
	 */
	public java.util.List<FoundObject> findAll();

	/**
	 * Returns a range of all the found objects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FoundObjectModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of found objects
	 * @param end the upper bound of the range of found objects (not inclusive)
	 * @return the range of found objects
	 */
	public java.util.List<FoundObject> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the found objects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FoundObjectModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of found objects
	 * @param end the upper bound of the range of found objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of found objects
	 */
	public java.util.List<FoundObject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FoundObject>
			orderByComparator);

	/**
	 * Returns an ordered range of all the found objects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>FoundObjectModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of found objects
	 * @param end the upper bound of the range of found objects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of found objects
	 */
	public java.util.List<FoundObject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FoundObject>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the found objects from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of found objects.
	 *
	 * @return the number of found objects
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}