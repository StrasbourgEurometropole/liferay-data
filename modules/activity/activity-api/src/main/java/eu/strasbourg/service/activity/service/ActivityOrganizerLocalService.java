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

import eu.strasbourg.service.activity.model.ActivityOrganizer;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ActivityOrganizer. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityOrganizerLocalServiceUtil
 * @see eu.strasbourg.service.activity.service.base.ActivityOrganizerLocalServiceBaseImpl
 * @see eu.strasbourg.service.activity.service.impl.ActivityOrganizerLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ActivityOrganizerLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ActivityOrganizerLocalServiceUtil} to access the activity organizer local service. Add custom service methods to {@link eu.strasbourg.service.activity.service.impl.ActivityOrganizerLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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
	* Adds the activity organizer to the database. Also notifies the appropriate model listeners.
	*
	* @param activityOrganizer the activity organizer
	* @return the activity organizer that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ActivityOrganizer addActivityOrganizer(
		ActivityOrganizer activityOrganizer);

	/**
	* Crée une activité vide avec une PK, non ajouté à la base de donnée
	*/
	public ActivityOrganizer createActivityOrganizer(ServiceContext sc)
		throws PortalException;

	/**
	* Creates a new activity organizer with the primary key. Does not add the activity organizer to the database.
	*
	* @param activityOrganizerId the primary key for the new activity organizer
	* @return the new activity organizer
	*/
	public ActivityOrganizer createActivityOrganizer(long activityOrganizerId);

	/**
	* Deletes the activity organizer from the database. Also notifies the appropriate model listeners.
	*
	* @param activityOrganizer the activity organizer
	* @return the activity organizer that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ActivityOrganizer deleteActivityOrganizer(
		ActivityOrganizer activityOrganizer);

	/**
	* Deletes the activity organizer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityOrganizerId the primary key of the activity organizer
	* @return the activity organizer that was removed
	* @throws PortalException if a activity organizer with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ActivityOrganizer deleteActivityOrganizer(long activityOrganizerId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActivityOrganizer fetchActivityOrganizer(long activityOrganizerId);

	/**
	* Returns the activity organizer matching the UUID and group.
	*
	* @param uuid the activity organizer's UUID
	* @param groupId the primary key of the group
	* @return the matching activity organizer, or <code>null</code> if a matching activity organizer could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActivityOrganizer fetchActivityOrganizerByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the activity organizer with the primary key.
	*
	* @param activityOrganizerId the primary key of the activity organizer
	* @return the activity organizer
	* @throws PortalException if a activity organizer with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActivityOrganizer getActivityOrganizer(long activityOrganizerId)
		throws PortalException;

	/**
	* Returns the activity organizer matching the UUID and group.
	*
	* @param uuid the activity organizer's UUID
	* @param groupId the primary key of the group
	* @return the matching activity organizer
	* @throws PortalException if a matching activity organizer could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActivityOrganizer getActivityOrganizerByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Supprime une entité
	*/
	public ActivityOrganizer removeActivityOrganizer(long activityOrganizerId)
		throws PortalException;

	/**
	* Updates the activity organizer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param activityOrganizer the activity organizer
	* @return the activity organizer that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ActivityOrganizer updateActivityOrganizer(
		ActivityOrganizer activityOrganizer);

	/**
	* Met à jour une activité et l'enregistre en base de données
	*/
	public ActivityOrganizer updateActivityOrganizer(
		ActivityOrganizer activityOrganizer, ServiceContext sc)
		throws PortalException;

	/**
	* Met à jour le statut de l'édition par le framework workflow
	*/
	public ActivityOrganizer updateStatus(long userId, long entryId, int status)
		throws PortalException;

	/**
	* Returns the number of activity organizers.
	*
	* @return the number of activity organizers
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getActivityOrganizersCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityOrganizerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityOrganizerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<ActivityOrganizer> findByKeyword(java.lang.String keyword,
		long groupId, int start, int end);

	/**
	* Returns a range of all the activity organizers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityOrganizerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity organizers
	* @param end the upper bound of the range of activity organizers (not inclusive)
	* @return the range of activity organizers
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ActivityOrganizer> getActivityOrganizers(int start, int end);

	/**
	* Returns all the activity organizers matching the UUID and company.
	*
	* @param uuid the UUID of the activity organizers
	* @param companyId the primary key of the company
	* @return the matching activity organizers, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ActivityOrganizer> getActivityOrganizersByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of activity organizers matching the UUID and company.
	*
	* @param uuid the UUID of the activity organizers
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of activity organizers
	* @param end the upper bound of the range of activity organizers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching activity organizers, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ActivityOrganizer> getActivityOrganizersByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<ActivityOrganizer> orderByComparator);

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	* Retourne toutes les éditions d'un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ActivityOrganizer> getByGroupId(long groupId);

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