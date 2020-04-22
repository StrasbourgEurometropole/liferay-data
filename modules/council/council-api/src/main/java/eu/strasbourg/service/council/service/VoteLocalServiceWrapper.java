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

package eu.strasbourg.service.council.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link VoteLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see VoteLocalService
 * @generated
 */
@ProviderType
public class VoteLocalServiceWrapper implements VoteLocalService,
	ServiceWrapper<VoteLocalService> {
	public VoteLocalServiceWrapper(VoteLocalService voteLocalService) {
		_voteLocalService = voteLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _voteLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _voteLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _voteLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _voteLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _voteLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the vote to the database. Also notifies the appropriate model listeners.
	*
	* @param vote the vote
	* @return the vote that was added
	*/
	@Override
	public eu.strasbourg.service.council.model.Vote addVote(
		eu.strasbourg.service.council.model.Vote vote) {
		return _voteLocalService.addVote(vote);
	}

	/**
	* Crée une entité vide avec une PK, non ajouté à la base de donnée
	*/
	@Override
	public eu.strasbourg.service.council.model.Vote createVote(
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _voteLocalService.createVote(sc);
	}

	/**
	* Creates a new vote with the primary key. Does not add the vote to the database.
	*
	* @param voteId the primary key for the new vote
	* @return the new vote
	*/
	@Override
	public eu.strasbourg.service.council.model.Vote createVote(long voteId) {
		return _voteLocalService.createVote(voteId);
	}

	/**
	* Deletes the vote from the database. Also notifies the appropriate model listeners.
	*
	* @param vote the vote
	* @return the vote that was removed
	*/
	@Override
	public eu.strasbourg.service.council.model.Vote deleteVote(
		eu.strasbourg.service.council.model.Vote vote) {
		return _voteLocalService.deleteVote(vote);
	}

	/**
	* Deletes the vote with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param voteId the primary key of the vote
	* @return the vote that was removed
	* @throws PortalException if a vote with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.council.model.Vote deleteVote(long voteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _voteLocalService.deleteVote(voteId);
	}

	@Override
	public eu.strasbourg.service.council.model.Vote fetchVote(long voteId) {
		return _voteLocalService.fetchVote(voteId);
	}

	/**
	* Returns the vote matching the UUID and group.
	*
	* @param uuid the vote's UUID
	* @param groupId the primary key of the group
	* @return the matching vote, or <code>null</code> if a matching vote could not be found
	*/
	@Override
	public eu.strasbourg.service.council.model.Vote fetchVoteByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _voteLocalService.fetchVoteByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the vote with the primary key.
	*
	* @param voteId the primary key of the vote
	* @return the vote
	* @throws PortalException if a vote with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.council.model.Vote getVote(long voteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _voteLocalService.getVote(voteId);
	}

	/**
	* Returns the vote matching the UUID and group.
	*
	* @param uuid the vote's UUID
	* @param groupId the primary key of the group
	* @return the matching vote
	* @throws PortalException if a matching vote could not be found
	*/
	@Override
	public eu.strasbourg.service.council.model.Vote getVoteByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _voteLocalService.getVoteByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Supprime une entité
	*/
	@Override
	public eu.strasbourg.service.council.model.Vote removeVote(long voteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _voteLocalService.removeVote(voteId);
	}

	/**
	* Updates the vote in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param vote the vote
	* @return the vote that was updated
	*/
	@Override
	public eu.strasbourg.service.council.model.Vote updateVote(
		eu.strasbourg.service.council.model.Vote vote) {
		return _voteLocalService.updateVote(vote);
	}

	/**
	* Met à jour une entité et l'enregistre en base de données
	*/
	@Override
	public eu.strasbourg.service.council.model.Vote updateVote(
		eu.strasbourg.service.council.model.Vote vote,
		com.liferay.portal.kernel.service.ServiceContext sc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _voteLocalService.updateVote(vote, sc);
	}

	/**
	* Returns the number of votes.
	*
	* @return the number of votes
	*/
	@Override
	public int getVotesCount() {
		return _voteLocalService.getVotesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _voteLocalService.getOSGiServiceIdentifier();
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
		return _voteLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _voteLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _voteLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Retourne la liste des votes d'une délibération
	*/
	@Override
	public java.util.List<eu.strasbourg.service.council.model.Vote> findByDeliberationId(
		long deliberationId) {
		return _voteLocalService.findByDeliberationId(deliberationId);
	}

	/**
	* Returns a range of all the votes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.council.model.impl.VoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of votes
	* @param end the upper bound of the range of votes (not inclusive)
	* @return the range of votes
	*/
	@Override
	public java.util.List<eu.strasbourg.service.council.model.Vote> getVotes(
		int start, int end) {
		return _voteLocalService.getVotes(start, end);
	}

	/**
	* Returns all the votes matching the UUID and company.
	*
	* @param uuid the UUID of the votes
	* @param companyId the primary key of the company
	* @return the matching votes, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.council.model.Vote> getVotesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _voteLocalService.getVotesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of votes matching the UUID and company.
	*
	* @param uuid the UUID of the votes
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of votes
	* @param end the upper bound of the range of votes (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching votes, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<eu.strasbourg.service.council.model.Vote> getVotesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<eu.strasbourg.service.council.model.Vote> orderByComparator) {
		return _voteLocalService.getVotesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
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
		return _voteLocalService.dynamicQueryCount(dynamicQuery);
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
		return _voteLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Supprime les votes d'une délibération
	*/
	@Override
	public void removeVotesFromDeliberation(long deliberationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_voteLocalService.removeVotesFromDeliberation(deliberationId);
	}

	@Override
	public VoteLocalService getWrappedService() {
		return _voteLocalService;
	}

	@Override
	public void setWrappedService(VoteLocalService voteLocalService) {
		_voteLocalService = voteLocalService;
	}

	private VoteLocalService _voteLocalService;
}