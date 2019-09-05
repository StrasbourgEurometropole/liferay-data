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

import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;

import org.osgi.util.tracker.ServiceTracker;

import aQute.bnd.annotation.ProviderType;

/**
 * Provides the local service utility for Association. This utility wraps
 * {@link eu.strasbourg.service.activity.service.impl.AssociationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssociationLocalService
 * @see eu.strasbourg.service.activity.service.base.AssociationLocalServiceBaseImpl
 * @see eu.strasbourg.service.activity.service.impl.AssociationLocalServiceImpl
 * @generated
 */
@ProviderType
public class AssociationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link eu.strasbourg.service.activity.service.impl.AssociationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Adds the association to the database. Also notifies the appropriate model listeners.
	*
	* @param association the association
	* @return the association that was added
	*/
	public static eu.strasbourg.service.activity.model.Association addAssociation(
		eu.strasbourg.service.activity.model.Association association) {
		return getService().addAssociation(association);
	}

	/**
	* Crée une association vide avec une PK, non ajouté à la base de donnée
	*/
	public static eu.strasbourg.service.activity.model.Association createAssociation(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().createAssociation(sc);
	}

	/**
	* Creates a new association with the primary key. Does not add the association to the database.
	*
	* @param associationId the primary key for the new association
	* @return the new association
	*/
	public static eu.strasbourg.service.activity.model.Association createAssociation(
		long associationId) {
		return getService().createAssociation(associationId);
	}

	/**
	* Deletes the association from the database. Also notifies the appropriate model listeners.
	*
	* @param association the association
	* @return the association that was removed
	*/
	public static eu.strasbourg.service.activity.model.Association deleteAssociation(
		eu.strasbourg.service.activity.model.Association association) {
		return getService().deleteAssociation(association);
	}

	/**
	* Deletes the association with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param associationId the primary key of the association
	* @return the association that was removed
	* @throws PortalException if a association with the primary key could not be found
	*/
	public static eu.strasbourg.service.activity.model.Association deleteAssociation(
		long associationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteAssociation(associationId);
	}

	public static eu.strasbourg.service.activity.model.Association fetchAssociation(
		long associationId) {
		return getService().fetchAssociation(associationId);
	}

	/**
	* Returns the association matching the UUID and group.
	*
	* @param uuid the association's UUID
	* @param groupId the primary key of the group
	* @return the matching association, or <code>null</code> if a matching association could not be found
	*/
	public static eu.strasbourg.service.activity.model.Association fetchAssociationByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchAssociationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the association with the primary key.
	*
	* @param associationId the primary key of the association
	* @return the association
	* @throws PortalException if a association with the primary key could not be found
	*/
	public static eu.strasbourg.service.activity.model.Association getAssociation(
		long associationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAssociation(associationId);
	}

	/**
	* Returns the association matching the UUID and group.
	*
	* @param uuid the association's UUID
	* @param groupId the primary key of the group
	* @return the matching association
	* @throws PortalException if a matching association could not be found
	*/
	public static eu.strasbourg.service.activity.model.Association getAssociationByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getAssociationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une entité
	*/
	public static eu.strasbourg.service.activity.model.Association removeAssociation(
		long associationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeAssociation(associationId);
	}

	/**
	* Updates the association in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param association the association
	* @return the association that was updated
	*/
	public static eu.strasbourg.service.activity.model.Association updateAssociation(
		eu.strasbourg.service.activity.model.Association association) {
		return getService().updateAssociation(association);
	}

	/**
	* Met à jour une association et l'enregistre en base de données
	*/
	public static eu.strasbourg.service.activity.model.Association updateAssociation(
		eu.strasbourg.service.activity.model.Association association,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateAssociation(association, sc);
	}

	/**
	* Met à jour le statut de l'association par le framework workflow
	*/
	public static eu.strasbourg.service.activity.model.Association updateStatus(
		long userId, long entryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateStatus(userId, entryId, status);
	}

	/**
	* Returns the number of associations.
	*
	* @return the number of associations
	*/
	public static int getAssociationsCount() {
		return getService().getAssociationsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.AssociationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.AssociationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static java.util.List<eu.strasbourg.service.activity.model.Association> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return getService().findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Returns a range of all the associations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.activity.model.impl.AssociationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of associations
	* @param end the upper bound of the range of associations (not inclusive)
	* @return the range of associations
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.Association> getAssociations(
		int start, int end) {
		return getService().getAssociations(start, end);
	}

	/**
	* Returns all the associations matching the UUID and company.
	*
	* @param uuid the UUID of the associations
	* @param companyId the primary key of the company
	* @return the matching associations, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.Association> getAssociationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getAssociationsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of associations matching the UUID and company.
	*
	* @param uuid the UUID of the associations
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of associations
	* @param end the upper bound of the range of associations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching associations, or an empty list if no matches were found
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.Association> getAssociationsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.activity.model.Association> orderByComparator) {
		return getService()
				   .getAssociationsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité
	*/
	public static java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return getService().getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les éditions d'un groupe
	*/
	public static java.util.List<eu.strasbourg.service.activity.model.Association> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
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

	public static AssociationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<AssociationLocalService, AssociationLocalService> _serviceTracker =
		ServiceTrackerFactory.open(AssociationLocalService.class);
}