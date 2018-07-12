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

package eu.strasbourg.service.like.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LikeLocalService}.
 *
 * @author Cedric Henry
 * @see LikeLocalService
 * @generated
 */
@ProviderType
public class LikeLocalServiceWrapper implements LikeLocalService,
	ServiceWrapper<LikeLocalService> {
	public LikeLocalServiceWrapper(LikeLocalService likeLocalService) {
		_likeLocalService = likeLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _likeLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _likeLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _likeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _likeLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _likeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Adds the like to the database. Also notifies the appropriate model listeners.
	*
	* @param like the like
	* @return the like that was added
	*/
	@Override
	public eu.strasbourg.service.like.model.Like addLike(
		eu.strasbourg.service.like.model.Like like) {
		return _likeLocalService.addLike(like);
	}

	/**
	* Crée un nouveau like/dislike
	*/
	@Override
	public eu.strasbourg.service.like.model.Like createLike() {
		return _likeLocalService.createLike();
	}

	/**
	* Creates a new like with the primary key. Does not add the like to the database.
	*
	* @param likeId the primary key for the new like
	* @return the new like
	*/
	@Override
	public eu.strasbourg.service.like.model.Like createLike(long likeId) {
		return _likeLocalService.createLike(likeId);
	}

	/**
	* Deletes the like from the database. Also notifies the appropriate model listeners.
	*
	* @param like the like
	* @return the like that was removed
	*/
	@Override
	public eu.strasbourg.service.like.model.Like deleteLike(
		eu.strasbourg.service.like.model.Like like) {
		return _likeLocalService.deleteLike(like);
	}

	/**
	* Deletes the like with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param likeId the primary key of the like
	* @return the like that was removed
	* @throws PortalException if a like with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.like.model.Like deleteLike(long likeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _likeLocalService.deleteLike(likeId);
	}

	@Override
	public eu.strasbourg.service.like.model.Like fetchLike(long likeId) {
		return _likeLocalService.fetchLike(likeId);
	}

	/**
	* Returns the like with the primary key.
	*
	* @param likeId the primary key of the like
	* @return the like
	* @throws PortalException if a like with the primary key could not be found
	*/
	@Override
	public eu.strasbourg.service.like.model.Like getLike(long likeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _likeLocalService.getLike(likeId);
	}

	/**
	* Updates the like in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param like the like
	* @return the like that was updated
	*/
	@Override
	public eu.strasbourg.service.like.model.Like updateLike(
		eu.strasbourg.service.like.model.Like like) {
		return _likeLocalService.updateLike(like);
	}

	/**
	* Returns the number of likes.
	*
	* @return the number of likes
	*/
	@Override
	public int getLikesCount() {
		return _likeLocalService.getLikesCount();
	}

	/**
	* Retourne le type du like/dislike de l'element
	*/
	@Override
	public java.lang.Long getLikeTypeByClass(java.lang.String likeClass) {
		return _likeLocalService.getLikeTypeByClass(likeClass);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _likeLocalService.getOSGiServiceIdentifier();
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
		return _likeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.like.model.impl.LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _likeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.like.model.impl.LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _likeLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Retourne la liste des likes/dislikes d'un utilisateur
	*/
	@Override
	public java.util.List<eu.strasbourg.service.like.model.Like> getByPublikUser(
		java.lang.String publikUserId) {
		return _likeLocalService.getByPublikUser(publikUserId);
	}

	/**
	* Returns a range of all the likes.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link eu.strasbourg.service.like.model.impl.LikeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of likes
	* @param end the upper bound of the range of likes (not inclusive)
	* @return the range of likes
	*/
	@Override
	public java.util.List<eu.strasbourg.service.like.model.Like> getLikes(
		int start, int end) {
		return _likeLocalService.getLikes(start, end);
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
		return _likeLocalService.dynamicQueryCount(dynamicQuery);
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
		return _likeLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Supprime les likes/dislike d'une entité
	*/
	@Override
	public void deleteLikeByEntityIdAndType(long entityId, long typeId) {
		_likeLocalService.deleteLikeByEntityIdAndType(entityId, typeId);
	}

	/**
	* Supprime les likes/dislike d'une entité selon son type (Like ou Dislike)
	*/
	@Override
	public void deleteLikeByEntityIdAndTypeAndIsDislike(long entityId,
		long typeId, boolean isDislike) {
		_likeLocalService.deleteLikeByEntityIdAndTypeAndIsDislike(entityId,
			typeId, isDislike);
	}

	@Override
	public LikeLocalService getWrappedService() {
		return _likeLocalService;
	}

	@Override
	public void setWrappedService(LikeLocalService likeLocalService) {
		_likeLocalService = likeLocalService;
	}

	private LikeLocalService _likeLocalService;
}