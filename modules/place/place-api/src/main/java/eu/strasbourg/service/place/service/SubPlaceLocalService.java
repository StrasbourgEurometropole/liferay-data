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

package eu.strasbourg.service.place.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.place.model.SubPlace;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for SubPlace. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Angelique Zunino Champougny
 * @see SubPlaceLocalServiceUtil
 * @see eu.strasbourg.service.place.service.base.SubPlaceLocalServiceBaseImpl
 * @see eu.strasbourg.service.place.service.impl.SubPlaceLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SubPlaceLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SubPlaceLocalServiceUtil} to access the sub place local service. Add custom service methods to {@link eu.strasbourg.service.place.service.impl.SubPlaceLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Adds the sub place to the database. Also notifies the appropriate model listeners.
	*
	* @param subPlace the sub place
	* @return the sub place that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SubPlace addSubPlace(SubPlace subPlace);

	/**
	* Crée un sous-lieu vide avec une PK, non ajouté à la base de donnée
	*/
	public SubPlace createSubPlace(ServiceContext sc) throws PortalException;

	/**
	* Creates a new sub place with the primary key. Does not add the sub place to the database.
	*
	* @param subPlaceId the primary key for the new sub place
	* @return the new sub place
	*/
	public SubPlace createSubPlace(long subPlaceId);

	/**
	* Deletes the sub place from the database. Also notifies the appropriate model listeners.
	*
	* @param subPlace the sub place
	* @return the sub place that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public SubPlace deleteSubPlace(SubPlace subPlace);

	/**
	* Deletes the sub place with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param subPlaceId the primary key of the sub place
	* @return the sub place that was removed
	* @throws PortalException if a sub place with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public SubPlace deleteSubPlace(long subPlaceId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SubPlace fetchSubPlace(long subPlaceId);

	/**
	* Returns the sub place with the primary key.
	*
	* @param subPlaceId the primary key of the sub place
	* @return the sub place
	* @throws PortalException if a sub place with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SubPlace getSubPlace(long subPlaceId) throws PortalException;

	/**
	* Supprime un sous-lieu
	*/
	public SubPlace removeSubPlace(long subPlaceId) throws PortalException;

	/**
	* Met à jour le statut du sous-lieu par le framework workflow
	*/
	public SubPlace updateStatus(long userId, long entryId, int status,
		ServiceContext sc, Map<java.lang.String, Serializable> workflowContext)
		throws PortalException;

	/**
	* Updates the sub place in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param subPlace the sub place
	* @return the sub place that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SubPlace updateSubPlace(SubPlace subPlace);

	/**
	* Met à jour un sous-lieu et l'enregistre en base de données
	*/
	public SubPlace updateSubPlace(SubPlace subPlace, ServiceContext sc)
		throws PortalException;

	/**
	* Returns the number of sub places.
	*
	* @return the number of sub places
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSubPlacesCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Lance une recherche par mots-clés
	*/
	public List<SubPlace> findByKeyword(java.lang.String keyword, int start,
		int end);

	/**
	* Retourne les SubPlace rattachés à un lieu
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SubPlace> getByPlaceId(long placeId);

	/**
	* Returns a range of all the sub places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.place.model.impl.SubPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sub places
	* @param end the upper bound of the range of sub places (not inclusive)
	* @return the range of sub places
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SubPlace> getSubPlaces(int start, int end);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	/**
	* Compte de la recherche par mots-clés
	*/
	public long findByKeywordCount(java.lang.String keyword);

	/**
	* Met à jour le statut du sous-lieu "manuellement" (pas via le workflow)
	*/
	public void updateStatus(long userId, SubPlace subPlace, int status)
		throws PortalException;
}