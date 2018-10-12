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

package eu.strasbourg.service.project.service;

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import eu.strasbourg.service.project.model.PlacitPlace;

import java.io.Serializable;
import java.util.List;

/**
 * Provides the local service interface for PlacitPlace. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Cedric Henry
 * @see PlacitPlaceLocalServiceUtil
 * @see eu.strasbourg.service.project.service.base.PlacitPlaceLocalServiceBaseImpl
 * @see eu.strasbourg.service.project.service.impl.PlacitPlaceLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface PlacitPlaceLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PlacitPlaceLocalServiceUtil} to access the placit place local service. Add custom service methods to {@link eu.strasbourg.service.project.service.impl.PlacitPlaceLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

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
	* Lance une recherche selon le searchContext
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(SearchContext searchContext) throws SearchException;

	/**
	* Adds the placit place to the database. Also notifies the appropriate model listeners.
	*
	* @param placitPlace the placit place
	* @return the placit place that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public PlacitPlace addPlacitPlace(PlacitPlace placitPlace);

	/**
	* Crée un lieu Placit vide avec une PK, non ajouté à la base de donnée
	*/
	public PlacitPlace createPlacitPlace(ServiceContext sc)
		throws PortalException;

	/**
	* Creates a new placit place with the primary key. Does not add the placit place to the database.
	*
	* @param placitPlaceId the primary key for the new placit place
	* @return the new placit place
	*/
	public PlacitPlace createPlacitPlace(long placitPlaceId);

	/**
	* Deletes the placit place from the database. Also notifies the appropriate model listeners.
	*
	* @param placitPlace the placit place
	* @return the placit place that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public PlacitPlace deletePlacitPlace(PlacitPlace placitPlace);

	/**
	* Deletes the placit place with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param placitPlaceId the primary key of the placit place
	* @return the placit place that was removed
	* @throws PortalException if a placit place with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public PlacitPlace deletePlacitPlace(long placitPlaceId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PlacitPlace fetchPlacitPlace(long placitPlaceId);

	/**
	* Returns the placit place matching the UUID and group.
	*
	* @param uuid the placit place's UUID
	* @param groupId the primary key of the group
	* @return the matching placit place, or <code>null</code> if a matching placit place could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PlacitPlace fetchPlacitPlaceByUuidAndGroupId(java.lang.String uuid,
		long groupId);

	/**
	* Returns the placit place with the primary key.
	*
	* @param placitPlaceId the primary key of the placit place
	* @return the placit place
	* @throws PortalException if a placit place with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PlacitPlace getPlacitPlace(long placitPlaceId)
		throws PortalException;

	/**
	* Returns the placit place matching the UUID and group.
	*
	* @param uuid the placit place's UUID
	* @param groupId the primary key of the group
	* @return the matching placit place
	* @throws PortalException if a matching placit place could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PlacitPlace getPlacitPlaceByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	/**
	* Supprime une entité
	*/
	public PlacitPlace removePlacitPlace(long placitPlaceId)
		throws PortalException;

	/**
	* Updates the placit place in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param placitPlace the placit place
	* @return the placit place that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public PlacitPlace updatePlacitPlace(PlacitPlace placitPlace);

	/**
	* Met à jour un lieu Placit et l'enregistre en base de données
	*/
	public PlacitPlace updatePlacitPlace(PlacitPlace placitPlace,
		ServiceContext sc) throws PortalException;

	/**
	* Returns the number of placit places.
	*
	* @return the number of placit places
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPlacitPlacesCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Lance une recherche par liste d'ids
	*/
	public List<PlacitPlace> findByIds(List<java.lang.Long> placitPlaceIds);

	/**
	* Lance une recherche par mots-clés
	*/
	public List<PlacitPlace> findByKeyword(java.lang.String keyword,
		long groupId, int start, int end);

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	* Retourne les lieux d'une participation
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PlacitPlace> getByBudgetParticipatif(long budgetParticipatifId);

	/**
	* Retourne tous les lieux Placit d'un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PlacitPlace> getByGroupId(long groupId);

	/**
	* Retourne les lieux d'une initiative
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PlacitPlace> getByInitiative(long initiativeId);

	/**
	* Retourne les lieux d'une participation
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PlacitPlace> getByParticipation(long participationId);

	/**
	* Retourne les lieux d'une participation
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PlacitPlace> getByPetition(long petitionId);

	/**
	* Retourne les lieux d'un projet
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PlacitPlace> getByProject(long projectId);

	/**
	* Retourne toutes les lieux de cours d'un lieu
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PlacitPlace> getBySigId(java.lang.String sigId);

	/**
	* Returns a range of all the placit places.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.project.model.impl.PlacitPlaceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @return the range of placit places
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PlacitPlace> getPlacitPlaces(int start, int end);

	/**
	* Returns all the placit places matching the UUID and company.
	*
	* @param uuid the UUID of the placit places
	* @param companyId the primary key of the company
	* @return the matching placit places, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PlacitPlace> getPlacitPlacesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of placit places matching the UUID and company.
	*
	* @param uuid the UUID of the placit places
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of placit places
	* @param end the upper bound of the range of placit places (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching placit places, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<PlacitPlace> getPlacitPlacesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<PlacitPlace> orderByComparator);

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
}