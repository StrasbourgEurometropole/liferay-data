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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.search.log.model.SearchLog;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the search log service. This utility wraps {@link eu.strasbourg.service.search.log.service.persistence.impl.SearchLogPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author BenjaminBini
 * @see SearchLogPersistence
 * @see eu.strasbourg.service.search.log.service.persistence.impl.SearchLogPersistenceImpl
 * @generated
 */
@ProviderType
public class SearchLogUtil {
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
	public static void clearCache(SearchLog searchLog) {
		getPersistence().clearCache(searchLog);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SearchLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SearchLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SearchLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SearchLog> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SearchLog update(SearchLog searchLog) {
		return getPersistence().update(searchLog);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SearchLog update(SearchLog searchLog,
		ServiceContext serviceContext) {
		return getPersistence().update(searchLog, serviceContext);
	}

	/**
	* Caches the search log in the entity cache if it is enabled.
	*
	* @param searchLog the search log
	*/
	public static void cacheResult(SearchLog searchLog) {
		getPersistence().cacheResult(searchLog);
	}

	/**
	* Caches the search logs in the entity cache if it is enabled.
	*
	* @param searchLogs the search logs
	*/
	public static void cacheResult(List<SearchLog> searchLogs) {
		getPersistence().cacheResult(searchLogs);
	}

	/**
	* Creates a new search log with the primary key. Does not add the search log to the database.
	*
	* @param searchLogId the primary key for the new search log
	* @return the new search log
	*/
	public static SearchLog create(long searchLogId) {
		return getPersistence().create(searchLogId);
	}

	/**
	* Removes the search log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param searchLogId the primary key of the search log
	* @return the search log that was removed
	* @throws NoSuchSearchLogException if a search log with the primary key could not be found
	*/
	public static SearchLog remove(long searchLogId)
		throws eu.strasbourg.service.search.log.exception.NoSuchSearchLogException {
		return getPersistence().remove(searchLogId);
	}

	public static SearchLog updateImpl(SearchLog searchLog) {
		return getPersistence().updateImpl(searchLog);
	}

	/**
	* Returns the search log with the primary key or throws a {@link NoSuchSearchLogException} if it could not be found.
	*
	* @param searchLogId the primary key of the search log
	* @return the search log
	* @throws NoSuchSearchLogException if a search log with the primary key could not be found
	*/
	public static SearchLog findByPrimaryKey(long searchLogId)
		throws eu.strasbourg.service.search.log.exception.NoSuchSearchLogException {
		return getPersistence().findByPrimaryKey(searchLogId);
	}

	/**
	* Returns the search log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param searchLogId the primary key of the search log
	* @return the search log, or <code>null</code> if a search log with the primary key could not be found
	*/
	public static SearchLog fetchByPrimaryKey(long searchLogId) {
		return getPersistence().fetchByPrimaryKey(searchLogId);
	}

	public static java.util.Map<java.io.Serializable, SearchLog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the search logs.
	*
	* @return the search logs
	*/
	public static List<SearchLog> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<SearchLog> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<SearchLog> findAll(int start, int end,
		OrderByComparator<SearchLog> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<SearchLog> findAll(int start, int end,
		OrderByComparator<SearchLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the search logs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of search logs.
	*
	* @return the number of search logs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SearchLogPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SearchLogPersistence, SearchLogPersistence> _serviceTracker =
		ServiceTrackerFactory.open(SearchLogPersistence.class);
}