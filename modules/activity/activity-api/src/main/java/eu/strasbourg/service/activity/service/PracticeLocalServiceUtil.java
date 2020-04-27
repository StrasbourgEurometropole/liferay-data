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

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Practice. This utility wraps
 * {@link eu.strasbourg.service.activity.service.impl.PracticeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PracticeLocalService
 * @see eu.strasbourg.service.activity.service.base.PracticeLocalServiceBaseImpl
 * @see eu.strasbourg.service.activity.service.impl.PracticeLocalServiceImpl
 * @generated
 */
@ProviderType
public class PracticeLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.activity.service.impl.PracticeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Lance une recherche selon le searchContext
	*/
	public static Hits search(SearchContext searchContext)
		throws SearchException {
		return getService().search(searchContext);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the practice to the database. Also notifies the appropriate model listeners.
	*
	* @param practice the practice
	* @return the practice that was added
	*/
	public static eu.strasbourg.service.activity.model.Practice addPractice(
		eu.strasbourg.service.activity.model.Practice practice) {
		return getService().addPractice(practice);
	}

	/**
	* Crée une pratique vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.activity.model.Practice createPractice(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createPractice(sc);
	}

	/**
	* Creates a new practice with the primary key. Does not add the practice to the database.
	*
	* @param practiceId the primary key for the new practice
	* @return the new practice
	*/
	public static eu.strasbourg.service.activity.model.Practice createPractice(
		long practiceId) {
		return getService().createPractice(practiceId);
	}

	/**
	* Deletes the practice from the database. Also notifies the appropriate model listeners.
	*
	* @param practice the practice
	* @return the practice that was removed
	*/
	public static eu.strasbourg.service.activity.model.Practice deletePractice(
		eu.strasbourg.service.activity.model.Practice practice) {
		return getService().deletePractice(practice);
	}

	/**
	* Deletes the practice with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param practiceId the primary key of the practice
	* @return the practice that was removed
	* @throws PortalException if a practice with the primary key could not be found
	*/
	public static eu.strasbourg.service.activity.model.Practice deletePractice(
		long practiceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePractice(practiceId);
	}

	public static eu.strasbourg.service.activity.model.Practice fetchPractice(
		long practiceId) {
		return getService().fetchPractice(practiceId);
	}

	/**
	* Returns the practice matching the UUID and group.
	*
	* @param uuid the practice's UUID
	* @param groupId the primary key of the group
	* @return the matching practice, or <code>null</code> if a matching practice could not be found
	*/
	public static eu.strasbourg.service.activity.model.Practice fetchPracticeByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchPracticeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the practice with the primary key.
	*
	* @param practiceId the primary key of the practice
	* @return the practice
	* @throws PortalException if a practice with the primary key could not be found
	*/
	public static eu.strasbourg.service.activity.model.Practice getPractice(
		long practiceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPractice(practiceId);
	}

	/**
	* Returns the practice matching the UUID and group.
	*
	* @param uuid the practice's UUID
	* @param groupId the primary key of the group
	* @return the matching practice
	* @throws PortalException if a matching practice could not be found
	*/
	public static eu.strasbourg.service.activity.model.Practice getPracticeByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPracticeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une entité
	*/
	public static eu.strasbourg.service.activity.model.Practice removePractice(
		long practiceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removePractice(practiceId);
	}

	/**
	* Updates the practice in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param practice the practice
	* @return the practice that was updated
	*/
	public static eu.strasbourg.service.activity.model.Practice updatePractice(
		eu.strasbourg.service.activity.model.Practice practice) {
		return getService().updatePractice(practice);
	}

	/**
	* Met à jour une pratique et l'enregistre en base de données
	*/
	public static eu.strasbourg.service.activity.model.Practice updatePractice(
		eu.strasbourg.service.activity.model.Practice practice,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updatePractice(practice, sc);
	}

	/**
	* Met à jour le statut de la pratique par le framework workflow
	*/
	public static eu.strasbourg.service.activity.model.Practice updateStatus(
		long userId, long entryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateStatus(userId, entryId, status);
	}

	/**
	* Returns the number of practices.
	*
	* @return the number of practices
	*/
	public static int getPracticesCount() {
		return getService().getPracticesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Lance une recherche par mots-clés
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.Practice> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return getService().findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité
	*/
	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return getService().getAttachedVocabularies(groupId);
	}

	/**
	* Retourne les pratiques d'une association
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.Practice> getByAssociation(
		long associationId) {
		return getService().getByAssociation(associationId);
	}

	/**
	* Retourne toutes les éditions d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.Practice> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
	}

	/**
	* Returns a range of all the practices.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.PracticeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @return the range of practices
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.Practice> getPractices(
		int start, int end) {
		return getService().getPractices(start, end);
	}

	/**
	* Returns all the practices matching the UUID and company.
	*
	* @param uuid the UUID of the practices
	* @param companyId the primary key of the company
	* @return the matching practices, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.Practice> getPracticesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getPracticesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of practices matching the UUID and company.
	*
	* @param uuid the UUID of the practices
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of practices
	* @param end the upper bound of the range of practices (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching practices, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.Practice> getPracticesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.activity.model.Practice> orderByComparator) {
		return getService()
				   .getPracticesByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Compte de la recherche par mots-clés
	*/
	public static long findByKeywordCount(java.lang.String keyword, long groupId) {
		return getService().findByKeywordCount(keyword, groupId);
	}

	public static PracticeLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PracticeLocalService, PracticeLocalService> _serviceTracker =
		ServiceTrackerFactory.open(PracticeLocalService.class);
}