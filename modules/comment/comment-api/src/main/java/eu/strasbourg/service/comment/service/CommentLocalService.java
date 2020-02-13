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

package eu.strasbourg.service.comment.service;

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
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import eu.strasbourg.service.comment.model.Comment;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for Comment. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Romain Vergnais
 * @see CommentLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CommentLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommentLocalServiceUtil} to access the comment local service. Add custom service methods to <code>eu.strasbourg.service.comment.service.impl.CommentLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the comment to the database. Also notifies the appropriate model listeners.
	 *
	 * @param comment the comment
	 * @return the comment that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Comment addComment(Comment comment);

	/**
	 * Creates a new comment with the primary key. Does not add the comment to the database.
	 *
	 * @param commentId the primary key for the new comment
	 * @return the new comment
	 */
	@Transactional(enabled = false)
	public Comment createComment(long commentId);

	/**
	 * Crée un commentaire vide avec une PK, non ajouté à la base de donnée
	 */
	public Comment createComment(String userPublikId, ServiceContext sc)
		throws PortalException;

	/**
	 * Deletes the comment from the database. Also notifies the appropriate model listeners.
	 *
	 * @param comment the comment
	 * @return the comment that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public Comment deleteComment(Comment comment);

	/**
	 * Deletes the comment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commentId the primary key of the comment
	 * @return the comment that was removed
	 * @throws PortalException if a comment with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public Comment deleteComment(long commentId) throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.comment.model.impl.CommentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.comment.model.impl.CommentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Comment fetchComment(long commentId);

	/**
	 * Returns the comment matching the UUID and group.
	 *
	 * @param uuid the comment's UUID
	 * @param groupId the primary key of the group
	 * @return the matching comment, or <code>null</code> if a matching comment could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Comment fetchCommentByUuidAndGroupId(String uuid, long groupId);

	/**
	 * Recherche par mot clés
	 */
	public List<Comment> findByKeyword(
		String keyword, long groupId, int start, int end);

	/**
	 * Recherche par mot clés (compte)
	 */
	public long findByKeywordCount(String keyword, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Renvoie la liste des vocabulaires rattachés à un commentaire
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetVocabulary> getAttachedVocabularies(long groupId);

	/**
	 * Retourne tous les commentaires d'un asset entry
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Comment> getByAssetEntry(long assetEntryId, int status);

	/**
	 * Retourne tous les commentaires d'un asset entry
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Comment> getByAssetEntryAndLevel(
		long assetEntryId, int level, int status);

	/**
	 * Retourne tous les commentaires d'un groupe
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Comment> getByGroupId(long groupId);

	/**
	 * Retourne tous les commentaires d'un commentaire parent
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Comment> getByParentCommentId(long parentCommentId, int status);

	/**
	 * Retourne tous les commentaires d'un utilisateur
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Comment> getByPublikId(String publikId);

	/**
	 * Returns the comment with the primary key.
	 *
	 * @param commentId the primary key of the comment
	 * @return the comment
	 * @throws PortalException if a comment with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Comment getComment(long commentId) throws PortalException;

	/**
	 * Returns the comment matching the UUID and group.
	 *
	 * @param uuid the comment's UUID
	 * @param groupId the primary key of the group
	 * @return the matching comment
	 * @throws PortalException if a matching comment could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Comment getCommentByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	 * Returns a range of all the comments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>eu.strasbourg.service.comment.model.impl.CommentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of comments
	 * @param end the upper bound of the range of comments (not inclusive)
	 * @return the range of comments
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Comment> getComments(int start, int end);

	/**
	 * Returns all the comments matching the UUID and company.
	 *
	 * @param uuid the UUID of the comments
	 * @param companyId the primary key of the company
	 * @return the matching comments, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Comment> getCommentsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	 * Returns a range of comments matching the UUID and company.
	 *
	 * @param uuid the UUID of the comments
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of comments
	 * @param end the upper bound of the range of comments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching comments, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Comment> getCommentsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Comment> orderByComparator);

	/**
	 * Returns the number of comments.
	 *
	 * @return the number of comments
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommentsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * méthode permettant d'obtenir une partie du commentaire.
	 *
	 * @param comment le commentaire en entier.
	 * @return le résultat du commentaire.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getSummary(String comment);

	/**
	 * Supprime un lien
	 */
	public Comment removeComment(long commentId) throws PortalException;

	/**
	 * Updates the comment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param comment the comment
	 * @return the comment that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public Comment updateComment(Comment comment);

	/**
	 * Met à jour un commentaire et l'enregistre en base de données
	 *
	 * @throws IOException
	 */
	public Comment updateComment(Comment comment, ServiceContext sc)
		throws PortalException;

	/**
	 * Met à jour le statut du projet "manuellement" (pas via le workflow)
	 */
	public void updateStatus(Comment comment, int status)
		throws PortalException;

	/**
	 * Met à jour le statut du projet par le framework workflow
	 */
	public Comment updateStatus(
			long userId, long entryId, int status, ServiceContext sc,
			Map<String, Serializable> workflowContext)
		throws PortalException;

}