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

package eu.strasbourg.service.place.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.place.exception.NoSuchPriceException;
import eu.strasbourg.service.place.model.Price;

/**
 * The persistence interface for the price service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Angelique Zunino Champougny
 * @see eu.strasbourg.service.place.service.persistence.impl.PricePersistenceImpl
 * @see PriceUtil
 * @generated
 */
@ProviderType
public interface PricePersistence extends BasePersistence<Price> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PriceUtil} to access the price persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the prices where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching prices
	*/
	public java.util.List<Price> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the prices where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of prices
	* @param end the upper bound of the range of prices (not inclusive)
	* @return the range of matching prices
	*/
	public java.util.List<Price> findByUuid(java.lang.String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the prices where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of prices
	* @param end the upper bound of the range of prices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching prices
	*/
	public java.util.List<Price> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Price> orderByComparator);

	/**
	* Returns an ordered range of all the prices where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of prices
	* @param end the upper bound of the range of prices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching prices
	*/
	public java.util.List<Price> findByUuid(java.lang.String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Price> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first price in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price
	* @throws NoSuchPriceException if a matching price could not be found
	*/
	public Price findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Price> orderByComparator)
		throws NoSuchPriceException;

	/**
	* Returns the first price in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching price, or <code>null</code> if a matching price could not be found
	*/
	public Price fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Price> orderByComparator);

	/**
	* Returns the last price in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price
	* @throws NoSuchPriceException if a matching price could not be found
	*/
	public Price findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Price> orderByComparator)
		throws NoSuchPriceException;

	/**
	* Returns the last price in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching price, or <code>null</code> if a matching price could not be found
	*/
	public Price fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Price> orderByComparator);

	/**
	* Returns the prices before and after the current price in the ordered set where uuid = &#63;.
	*
	* @param priceId the primary key of the current price
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next price
	* @throws NoSuchPriceException if a price with the primary key could not be found
	*/
	public Price[] findByUuid_PrevAndNext(long priceId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Price> orderByComparator)
		throws NoSuchPriceException;

	/**
	* Removes all the prices where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of prices where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching prices
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Caches the price in the entity cache if it is enabled.
	*
	* @param price the price
	*/
	public void cacheResult(Price price);

	/**
	* Caches the prices in the entity cache if it is enabled.
	*
	* @param prices the prices
	*/
	public void cacheResult(java.util.List<Price> prices);

	/**
	* Creates a new price with the primary key. Does not add the price to the database.
	*
	* @param priceId the primary key for the new price
	* @return the new price
	*/
	public Price create(long priceId);

	/**
	* Removes the price with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param priceId the primary key of the price
	* @return the price that was removed
	* @throws NoSuchPriceException if a price with the primary key could not be found
	*/
	public Price remove(long priceId) throws NoSuchPriceException;

	public Price updateImpl(Price price);

	/**
	* Returns the price with the primary key or throws a {@link NoSuchPriceException} if it could not be found.
	*
	* @param priceId the primary key of the price
	* @return the price
	* @throws NoSuchPriceException if a price with the primary key could not be found
	*/
	public Price findByPrimaryKey(long priceId) throws NoSuchPriceException;

	/**
	* Returns the price with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param priceId the primary key of the price
	* @return the price, or <code>null</code> if a price with the primary key could not be found
	*/
	public Price fetchByPrimaryKey(long priceId);

	@Override
	public java.util.Map<java.io.Serializable, Price> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the prices.
	*
	* @return the prices
	*/
	public java.util.List<Price> findAll();

	/**
	* Returns a range of all the prices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of prices
	* @param end the upper bound of the range of prices (not inclusive)
	* @return the range of prices
	*/
	public java.util.List<Price> findAll(int start, int end);

	/**
	* Returns an ordered range of all the prices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of prices
	* @param end the upper bound of the range of prices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of prices
	*/
	public java.util.List<Price> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Price> orderByComparator);

	/**
	* Returns an ordered range of all the prices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of prices
	* @param end the upper bound of the range of prices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of prices
	*/
	public java.util.List<Price> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Price> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the prices from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of prices.
	*
	* @return the number of prices
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}