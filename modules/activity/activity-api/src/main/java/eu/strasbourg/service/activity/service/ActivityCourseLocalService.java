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

import eu.strasbourg.service.activity.model.ActivityCourse;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for ActivityCourse. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCourseLocalServiceUtil
 * @see eu.strasbourg.service.activity.service.base.ActivityCourseLocalServiceBaseImpl
 * @see eu.strasbourg.service.activity.service.impl.ActivityCourseLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ActivityCourseLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ActivityCourseLocalServiceUtil} to access the activity course local service. Add custom service methods to {@link eu.strasbourg.service.activity.service.impl.ActivityCourseLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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
	* Adds the activity course to the database. Also notifies the appropriate model listeners.
	*
	* @param activityCourse the activity course
	* @return the activity course that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ActivityCourse addActivityCourse(ActivityCourse activityCourse);

	/**
	* Crée une activité vide avec une PK, non ajouté à la base de donnée
	*/
	public ActivityCourse createActivityCourse(ServiceContext sc)
		throws PortalException;

	/**
	* Creates a new activity course with the primary key. Does not add the activity course to the database.
	*
	* @param activityCourseId the primary key for the new activity course
	* @return the new activity course
	*/
	public ActivityCourse createActivityCourse(long activityCourseId);

	/**
	* Deletes the activity course from the database. Also notifies the appropriate model listeners.
	*
	* @param activityCourse the activity course
	* @return the activity course that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ActivityCourse deleteActivityCourse(ActivityCourse activityCourse);

	/**
	* Deletes the activity course with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param activityCourseId the primary key of the activity course
	* @return the activity course that was removed
	* @throws PortalException if a activity course with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ActivityCourse deleteActivityCourse(long activityCourseId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActivityCourse fetchActivityCourse(long activityCourseId);

	/**
	* Returns the activity course matching the UUID and group.
	*
	* @param uuid the activity course's UUID
	* @param groupId the primary key of the group
	* @return the matching activity course, or <code>null</code> if a matching activity course could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActivityCourse fetchActivityCourseByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the activity course with the primary key.
	*
	* @param activityCourseId the primary key of the activity course
	* @return the activity course
	* @throws PortalException if a activity course with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActivityCourse getActivityCourse(long activityCourseId)
		throws PortalException;

	/**
	* Returns the activity course matching the UUID and group.
	*
	* @param uuid the activity course's UUID
	* @param groupId the primary key of the group
	* @return the matching activity course
	* @throws PortalException if a matching activity course could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActivityCourse getActivityCourseByUuidAndGroupId(
		java.lang.String uuid, long groupId) throws PortalException;

	/**
	* Supprime une entité
	*/
	public ActivityCourse removeActivityCourse(long activityCourseId)
		throws PortalException;

	/**
	* Updates the activity course in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param activityCourse the activity course
	* @return the activity course that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ActivityCourse updateActivityCourse(ActivityCourse activityCourse);

	/**
	* Met à jour une activité et l'enregistre en base de données
	*/
	public ActivityCourse updateActivityCourse(ActivityCourse activityCourse,
		ServiceContext sc) throws PortalException;

	/**
	* Met à jour le statut de l'édition par le framework workflow
	*/
	public ActivityCourse updateStatus(long userId, long entryId, int status)
		throws PortalException;

	/**
	* Returns the number of activity courses.
	*
	* @return the number of activity courses
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getActivityCoursesCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public List<ActivityCourse> findByKeyword(java.lang.String keyword,
		long groupId, int start, int end);

	/**
	* Returns a range of all the activity courses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.ActivityCourseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @return the range of activity courses
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ActivityCourse> getActivityCourses(int start, int end);

	/**
	* Returns all the activity courses matching the UUID and company.
	*
	* @param uuid the UUID of the activity courses
	* @param companyId the primary key of the company
	* @return the matching activity courses, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ActivityCourse> getActivityCoursesByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

	/**
	* Returns a range of activity courses matching the UUID and company.
	*
	* @param uuid the UUID of the activity courses
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of activity courses
	* @param end the upper bound of the range of activity courses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching activity courses, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ActivityCourse> getActivityCoursesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<ActivityCourse> orderByComparator);

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	* Retourne les cours d'une activité
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ActivityCourse> getByActivity(long activityId);

	/**
	* Retourne toutes les éditions d'un groupe
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ActivityCourse> getByGroupId(long groupId);

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