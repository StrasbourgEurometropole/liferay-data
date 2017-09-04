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

package eu.strasbourg.service.interest.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link InterestLocalService}.
 *
 * @author BenjaminBini
 * @see InterestLocalService
 * @generated
 */
@ProviderType
public class InterestLocalServiceWrapper implements InterestLocalService,
	ServiceWrapper<InterestLocalService> {
	public InterestLocalServiceWrapper(
		InterestLocalService interestLocalService) {
		_interestLocalService = interestLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _interestLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _interestLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _interestLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _interestLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _interestLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _interestLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Lance une recherche selon le searchContext
	*/
	@Override
	public com.liferay.portal.kernel.search.Hits search(
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.SearchException {
		return _interestLocalService.search(searchContext);
	}

	/**
	* Adds the interest to the database. Also notifies the appropriate model listeners.
	*
	* @param interest the interest
	* @return the interest that was added
	*/
	@Override
	public eu.strasbourg.service.interest.model.Interest addInterest(
		eu.strasbourg.service.interest.model.Interest interest) {
		return _interestLocalService.addInterest(interest);
	}

	/**
	* Crée une entité vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.interest.model.Interest createInterest(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _interestLocalService.createInterest(sc);
	}

	/**
	* Creates a new interest with the primary key. Does not add the interest to the database.
	*
	* @param interestId the primary key for the new interest
	* @return the new interest
	*/
	@Override
	public eu.strasbourg.service.interest.model.Interest createInterest(
		long interestId) {
		return _interestLocalService.createInterest(interestId);
	}

	/**
	* Deletes the interest from the database. Also notifies the appropriate model listeners.
	*
	* @param interest the interest
	* @return the interest that was removed
	*/
	@Override
	public eu.strasbourg.service.interest.model.Interest deleteInterest(
		eu.strasbourg.service.interest.model.Interest interest) {
		return _interestLocalService.deleteInterest(interest);
	}

	/**
	* Deletes the interest with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param interestId the primary key of the interest
	* @return the interest that was removed
	* @throws PortalException if a interest with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.interest.model.Interest deleteInterest(
		long interestId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _interestLocalService.deleteInterest(interestId);
	}

	@Override
	public eu.strasbourg.service.interest.model.Interest fetchInterest(
		long interestId) {
		return _interestLocalService.fetchInterest(interestId);
	}

	/**
	* Returns the interest matching the UUID and group.
	*
	* @param uuid the interest's UUID
	* @param groupId the primary key of the group
	* @return the matching interest, or <code>null</code> if a matching interest could not be found
	*/
	@Override
	public eu.strasbourg.service.interest.model.Interest fetchInterestByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _interestLocalService.fetchInterestByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the interest with the primary key.
	*
	* @param interestId the primary key of the interest
	* @return the interest
	* @throws PortalException if a interest with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.interest.model.Interest getInterest(
		long interestId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _interestLocalService.getInterest(interestId);
	}

	/**
	* Returns the interest matching the UUID and group.
	*
	* @param uuid the interest's UUID
	* @param groupId the primary key of the group
	* @return the matching interest
	* @throws PortalException if a matching interest could not be found
	*/
	@Override
	public eu.strasbourg.service.interest.model.Interest getInterestByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _interestLocalService.getInterestByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une entité
	*/
	@Override
	public eu.strasbourg.service.interest.model.Interest removeInterest(
		long interestId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _interestLocalService.removeInterest(interestId);
	}

	/**
	* Updates the interest in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param interest the interest
	* @return the interest that was updated
	*/
	@Override
	public eu.strasbourg.service.interest.model.Interest updateInterest(
		eu.strasbourg.service.interest.model.Interest interest) {
		return _interestLocalService.updateInterest(interest);
	}

	/**
	* Met à jour une entité et l'enregistre en base de données
	*/
	@Override
	public eu.strasbourg.service.interest.model.Interest updateInterest(
		eu.strasbourg.service.interest.model.Interest interest,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _interestLocalService.updateInterest(interest, sc);
	}

	/**
	* Met à jour le statut de l'entité
	*/
	@Override
	public eu.strasbourg.service.interest.model.Interest updateStatus(
		long userId, long entryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _interestLocalService.updateStatus(userId, entryId, status);
	}

	/**
	* Returns the number of interests.
	*
	* @return the number of interests
	*/
	@Override
	public int getInterestsCount() {
		return _interestLocalService.getInterestsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _interestLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _interestLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.interest.model.impl.InterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _interestLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.interest.model.impl.InterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _interestLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Lance une recherche par mots-clés
	*/
	@Override
	public java.util.List<eu.strasbourg.service.interest.model.Interest> findByKeyword(
		java.lang.String keyword, long groupId, int start, int end) {
		return _interestLocalService.findByKeyword(keyword, groupId, start, end);
	}

	/**
	* Renvoie la liste des vocabulaires rattachés à l'entité Interest
	*/
	@Override
	public java.util.List<com.liferay.asset.kernel.model.AssetVocabulary> getAttachedVocabularies(
		long groupId) {
		return _interestLocalService.getAttachedVocabularies(groupId);
	}

	/**
	* Retourne toutes les entité d'un groupe
	*/
	@Override
	public java.util.List<eu.strasbourg.service.interest.model.Interest> getByGroupId(
		long groupId) {
		return _interestLocalService.getByGroupId(groupId);
	}

	/**
	* Retourne tous les centres d'intérêts (publiées) d'un utilisateur
	*/
	@Override
	public java.util.List<eu.strasbourg.service.interest.model.Interest> getByPublikUserId(
		long publikUserId) {
		return _interestLocalService.getByPublikUserId(publikUserId);
	}

	/**
	* Returns a range of all the interests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.interest.model.impl.InterestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of interests
	* @param end the upper bound of the range of interests (not inclusive)
	* @return the range of interests
	*/
	@Override
	public java.util.List<eu.strasbourg.service.interest.model.Interest> getInterests(
		int start, int end) {
		return _interestLocalService.getInterests(start, end);
	}

	/**
	* Returns all the interests matching the UUID and company.
	*
	* @param uuid the UUID of the interests
	* @param companyId the primary key of the company
	* @return the matching interests, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.interest.model.Interest> getInterestsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _interestLocalService.getInterestsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of interests matching the UUID and company.
	*
	* @param uuid the UUID of the interests
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of interests
	* @param end the upper bound of the range of interests (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching interests, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.interest.model.Interest> getInterestsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.interest.model.Interest> orderByComparator) {
		return _interestLocalService.getInterestsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _interestLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _interestLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Compte de la recherche par mots-clés
	*/
	@Override
	public long findByKeywordCount(java.lang.String keyword, long groupId) {
		return _interestLocalService.findByKeywordCount(keyword, groupId);
	}

	/**
	* Met à jour la relation entre un utilisateur et ses centres d'intérêts
	*/
	@Override
	public void setUserInterests(long publikUserId, long[] interestIds) {
		_interestLocalService.setUserInterests(publikUserId, interestIds);
	}

	@Override
	public InterestLocalService getWrappedService() {
		return _interestLocalService;
	}

	@Override
	public void setWrappedService(InterestLocalService interestLocalService) {
		_interestLocalService = interestLocalService;
	}

	private InterestLocalService _interestLocalService;
}