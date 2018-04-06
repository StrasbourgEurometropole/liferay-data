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

import eu.strasbourg.service.objtp.exception.NoSuchObjectCategoryException;
import eu.strasbourg.service.objtp.model.ObjectCategory;

/**
 * The persistence interface for the object category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author JeremyZwickert
 * @see eu.strasbourg.service.objtp.service.persistence.impl.ObjectCategoryPersistenceImpl
 * @see ObjectCategoryUtil
 * @generated
 */
@ProviderType
public interface ObjectCategoryPersistence extends BasePersistence<ObjectCategory> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ObjectCategoryUtil} to access the object category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the object category in the entity cache if it is enabled.
	*
	* @param objectCategory the object category
	*/
	public void cacheResult(ObjectCategory objectCategory);

	/**
	* Caches the object categories in the entity cache if it is enabled.
	*
	* @param objectCategories the object categories
	*/
	public void cacheResult(java.util.List<ObjectCategory> objectCategories);

	/**
	* Creates a new object category with the primary key. Does not add the object category to the database.
	*
	* @param code the primary key for the new object category
	* @return the new object category
	*/
	public ObjectCategory create(java.lang.String code);

	/**
	* Removes the object category with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param code the primary key of the object category
	* @return the object category that was removed
	* @throws NoSuchObjectCategoryException if a object category with the primary key could not be found
	*/
	public ObjectCategory remove(java.lang.String code)
		throws NoSuchObjectCategoryException;

	public ObjectCategory updateImpl(ObjectCategory objectCategory);

	/**
	* Returns the object category with the primary key or throws a {@link NoSuchObjectCategoryException} if it could not be found.
	*
	* @param code the primary key of the object category
	* @return the object category
	* @throws NoSuchObjectCategoryException if a object category with the primary key could not be found
	*/
	public ObjectCategory findByPrimaryKey(java.lang.String code)
		throws NoSuchObjectCategoryException;

	/**
	* Returns the object category with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param code the primary key of the object category
	* @return the object category, or <code>null</code> if a object category with the primary key could not be found
	*/
	public ObjectCategory fetchByPrimaryKey(java.lang.String code);

	@Override
	public java.util.Map<java.io.Serializable, ObjectCategory> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the object categories.
	*
	* @return the object categories
	*/
	public java.util.List<ObjectCategory> findAll();

	/**
	* Returns a range of all the object categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ObjectCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of object categories
	* @param end the upper bound of the range of object categories (not inclusive)
	* @return the range of object categories
	*/
	public java.util.List<ObjectCategory> findAll(int start, int end);

	/**
	* Returns an ordered range of all the object categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ObjectCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of object categories
	* @param end the upper bound of the range of object categories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of object categories
	*/
	public java.util.List<ObjectCategory> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ObjectCategory> orderByComparator);

	/**
	* Returns an ordered range of all the object categories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ObjectCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of object categories
	* @param end the upper bound of the range of object categories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of object categories
	*/
	public java.util.List<ObjectCategory> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ObjectCategory> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the object categories from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of object categories.
	*
	* @return the number of object categories
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}