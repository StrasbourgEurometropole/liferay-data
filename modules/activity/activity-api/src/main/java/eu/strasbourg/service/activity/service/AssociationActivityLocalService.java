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

package eu.strasbourg.service.activity.service;

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
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.activity.model.AssociationActivity;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for AssociationActivity. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssociationActivityLocalServiceUtil
 * @see eu.strasbourg.service.activity.service.base.AssociationActivityLocalServiceBaseImpl
 * @see eu.strasbourg.service.activity.service.impl.AssociationActivityLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AssociationActivityLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssociationActivityLocalServiceUtil} to access the association activity local service. Add custom service methods to {@link eu.strasbourg.service.activity.service.impl.AssociationActivityLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Lance une recherche selon le searchContext
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(SearchContext searchContext) throws SearchException;

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
	* Adds the association activity to the database. Also notifies the appropriate model listeners.
	*
	* @param associationActivity the association activity
	* @return the association activity that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public AssociationActivity addAssociationActivity(
		AssociationActivity associationActivity);

	/**
	* Crée une activité vide avec une PK, non ajouté à la base de donnée
	*/
	public AssociationActivity createAssociationActivity(ServiceContext sc)
		throws PortalException;

	/**
	* Creates a new association activity with the primary key. Does not add the association activity to the database.
	*
	* @param associationActivityId the primary key for the new association activity
	* @return the new association activity
	*/
	public AssociationActivity createAssociationActivity(
		long associationActivityId);

	/**
	* Deletes the association activity from the database. Also notifies the appropriate model listeners.
	*
	* @param associationActivity the association activity
	* @return the association activity that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public AssociationActivity deleteAssociationActivity(
		AssociationActivity associationActivity);

	/**
	* Deletes the association activity with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param associationActivityId the primary key of the association activity
	* @return the association activity that was removed
	* @throws PortalException if a association activity with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public AssociationActivity deleteAssociationActivity(
		long associationActivityId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssociationActivity fetchAssociationActivity(
		long associationActivityId);

	/**
	* Returns the association activity matching the UUID and group.
	*
	* @param uuid the association activity's UUID
	* @param groupId the primary key of the group
	* @return the matching association activity, or <code>null</code> if a matching association activity could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssociationActivity fetchAssociationActivityByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the association activity with the primary key.
	*
	* @param associationActivityId the primary key of the association activity
	* @return the association activity
	* @throws PortalException if a association activity with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssociationActivity getAssociationActivity(
		long associationActivityId) throws PortalException;

	/**
	* Returns the association activity matching the UUID and group.
	*
	* @param uuid the association activity's UUID
	* @param groupId the primary key of the group
	* @return the matching association activity
	* @throws PortalException if a matching association activity could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssociationActivity getAssociationActivityByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Supprime une entité
	*/
	public AssociationActivity removeAssociationActivity(
		long associationActivityId) throws PortalException;

	/**
	* Updates the association activity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param associationActivity the association activity
	* @return the association activity that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public AssociationActivity updateAssociationActivity(
		AssociationActivity associationActivity);

	/**
	* Met à jour une activité et l'enregistre en base de données
	*/
	public AssociationActivity updateAssociationActivity(
		AssociationActivity associationActivity, ServiceContext sc)
		throws PortalException;

	/**
	* Met à jour le statut de l'actvité de l'association par le framework workflow
	*/
	public AssociationActivity updateStatus(long userId, long entryId,
		int status) throws PortalException;

	/**
	* Returns the number of association activities.
	*
	* @return the number of association activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssociationActivitiesCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<AssociationActivity> findByKeyword(java.lang.String keyword,
		long groupId, int start, int end);

	/**
	* Returns a range of all the association activities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.AssociationActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @return the range of association activities
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssociationActivity> getAssociationActivities(int start, int end);

	/**
	* Returns all the association activities matching the UUID and company.
	*
	* @param uuid the UUID of the association activities
	* @param companyId the primary key of the company
	* @return the matching association activities, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssociationActivity> getAssociationActivitiesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of association activities matching the UUID and company.
	*
	* @param uuid the UUID of the association activities
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of association activities
	* @param end the upper bound of the range of association activities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching association activities, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssociationActivity> getAssociationActivitiesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<AssociationActivity> orderByComparator);

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	* Retourne les activités d'une association
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssociationActivity> getByAssociation(long associationId);

	/**
	* Retourne toutes les éditions d'un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssociationActivity> getByGroupId(long groupId);

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
	public long findByKeywordCount(java.lang.String keyword, long groupId);
}