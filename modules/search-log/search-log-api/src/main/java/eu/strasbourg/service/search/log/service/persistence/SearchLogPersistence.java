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

package eu.strasbourg.service.search.log.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import eu.strasbourg.service.search.log.exception.NoSuchSearchLogException;
import eu.strasbourg.service.search.log.model.SearchLog;

/**
 * The persistence interface for the search log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see eu.strasbourg.service.search.log.service.persistence.impl.SearchLogPersistenceImpl
 * @see SearchLogUtil
 * @generated
 */
@ProviderType
public interface SearchLogPersistence extends BasePersistence<SearchLog> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SearchLogUtil} to access the search log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the search log in the entity cache if it is enabled.
	*
	* @param searchLog the search log
	*/
	public void cacheResult(SearchLog searchLog);

	/**
	* Caches the search logs in the entity cache if it is enabled.
	*
	* @param searchLogs the search logs
	*/
	public void cacheResult(java.util.List<SearchLog> searchLogs);

	/**
	* Creates a new search log with the primary key. Does not add the search log to the database.
	*
	* @param searchLogId the primary key for the new search log
	* @return the new search log
	*/
	public SearchLog create(long searchLogId);

	/**
	* Removes the search log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param searchLogId the primary key of the search log
	* @return the search log that was removed
	* @throws NoSuchSearchLogException if a search log with the primary key could not be found
	*/
	public SearchLog remove(long searchLogId) throws NoSuchSearchLogException;

	public SearchLog updateImpl(SearchLog searchLog);

	/**
	* Returns the search log with the primary key or throws a {@link NoSuchSearchLogException} if it could not be found.
	*
	* @param searchLogId the primary key of the search log
	* @return the search log
	* @throws NoSuchSearchLogException if a search log with the primary key could not be found
	*/
	public SearchLog findByPrimaryKey(long searchLogId)
		throws NoSuchSearchLogException;

	/**
	* Returns the search log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param searchLogId the primary key of the search log
	* @return the search log, or <code>null</code> if a search log with the primary key could not be found
	*/
	public SearchLog fetchByPrimaryKey(long searchLogId);

	@Override
	public java.util.Map<java.io.Serializable, SearchLog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the search logs.
	*
	* @return the search logs
	*/
	public java.util.List<SearchLog> findAll();

	/**
	* Returns a range of all the search logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of search logs
	* @param end the upper bound of the range of search logs (not inclusive)
	* @return the range of search logs
	*/
	public java.util.List<SearchLog> findAll(int start, int end);

	/**
	* Returns an ordered range of all the search logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of search logs
	* @param end the upper bound of the range of search logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of search logs
	*/
	public java.util.List<SearchLog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SearchLog> orderByComparator);

	/**
	* Returns an ordered range of all the search logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SearchLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of search logs
	* @param end the upper bound of the range of search logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of search logs
	*/
	public java.util.List<SearchLog> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SearchLog> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the search logs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of search logs.
	*
	* @return the number of search logs
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}