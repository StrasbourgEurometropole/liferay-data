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

package eu.strasbourg.service.gtfs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.gtfs.model.CacheHoursJSON;
import eu.strasbourg.service.gtfs.service.persistence.CacheHoursJSONPK;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for CacheHoursJSON. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Cedric Henry
 * @see CacheHoursJSONLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CacheHoursJSONLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CacheHoursJSONLocalServiceUtil} to access the cache hours json local service. Add custom service methods to <code>eu.strasbourg.service.gtfs.service.impl.CacheHoursJSONLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the cache hours json to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CacheHoursJSONLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cacheHoursJSON the cache hours json
	 * @return the cache hours json that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CacheHoursJSON addCacheHoursJSON(CacheHoursJSON cacheHoursJSON);

	/**
	 * Creates a new cache hours json with the primary key. Does not add the cache hours json to the database.
	 *
	 * @param cacheHoursJSONPK the primary key for the new cache hours json
	 * @return the new cache hours json
	 */
	@Transactional(enabled = false)
	public CacheHoursJSON createCacheHoursJSON(
		CacheHoursJSONPK cacheHoursJSONPK);

	/**
	 * Crée une entité vide avec une PK, non ajouté à la base de donnée
	 */
	public CacheHoursJSON createCacheHoursJSON(String stopCode, int type);

	/**
	 * Deletes the cache hours json from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CacheHoursJSONLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cacheHoursJSON the cache hours json
	 * @return the cache hours json that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public CacheHoursJSON deleteCacheHoursJSON(CacheHoursJSON cacheHoursJSON);

	/**
	 * Deletes the cache hours json with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CacheHoursJSONLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cacheHoursJSONPK the primary key of the cache hours json
	 * @return the cache hours json that was removed
	 * @throws PortalException if a cache hours json with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public CacheHoursJSON deleteCacheHoursJSON(
			CacheHoursJSONPK cacheHoursJSONPK)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.gtfs.model.impl.CacheHoursJSONModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.gtfs.model.impl.CacheHoursJSONModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	/**
	 * Retourne le cache d'un arret et type
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CacheHoursJSON fetchByStopCodeAndType(String stopCode, int type);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CacheHoursJSON fetchCacheHoursJSON(
		CacheHoursJSONPK cacheHoursJSONPK);

	/**
	 * Returns the cache hours json with the primary key.
	 *
	 * @param cacheHoursJSONPK the primary key of the cache hours json
	 * @return the cache hours json
	 * @throws PortalException if a cache hours json with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CacheHoursJSON getCacheHoursJSON(CacheHoursJSONPK cacheHoursJSONPK)
		throws PortalException;

	/**
	 * Returns a range of all the cache hours jsons.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.gtfs.model.impl.CacheHoursJSONModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of cache hours jsons
	 * @param end the upper bound of the range of cache hours jsons (not inclusive)
	 * @return the range of cache hours jsons
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CacheHoursJSON> getCacheHoursJSONs(int start, int end);

	/**
	 * Returns the number of cache hours jsons.
	 *
	 * @return the number of cache hours jsons
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCacheHoursJSONsCount();

	/**
	 * Met à jour le jsonHour du stop
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getJsonHour(String stopCode, int type, int timeOut);

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Updates the cache hours json in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CacheHoursJSONLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param cacheHoursJSON the cache hours json
	 * @return the cache hours json that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CacheHoursJSON updateCacheHoursJSON(CacheHoursJSON cacheHoursJSON);

}