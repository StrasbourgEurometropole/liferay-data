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

package eu.strasbourg.service.comment.service.impl;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.model.Signalement;
import eu.strasbourg.service.comment.service.SignalementLocalServiceUtil;
import eu.strasbourg.service.comment.service.base.CommentLocalServiceBaseImpl;
import eu.strasbourg.service.like.model.Like;
import eu.strasbourg.service.like.service.LikeLocalServiceUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

/**
 * The implementation of the comment local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.comment.service.CommentLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Romain Vergnais
 * @see CommentLocalServiceBaseImpl
 * @see eu.strasbourg.service.comment.service.CommentLocalServiceUtil
 */
public class CommentLocalServiceImpl extends CommentLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link eu.strasbourg.service.comment.service.CommentLocalServiceUtil} to access the comment local service.
	 */
	
	/**
	 * Retourne tous les commentaires d'un groupe
	 */
	@Override
	public List<Comment> getByGroupId(long groupId) {
		return this.commentPersistence.findByGroupId(groupId);
	}
	
	/**
	 * Retourne tous les commentaires d'un asset entry
	 */
	@Override
	public List<Comment> getByAssetEntry(long assetEntryId, int status) {
		return this.commentPersistence.findByAssetEntryId(assetEntryId, status);
	}
	
	/**
	 * Retourne tous les commentaires d'un asset entry
	 */
	@Override
	public List<Comment> getByAssetEntryAndLevel(long assetEntryId, int level, int status) {
		return this.commentPersistence.findByAssetEntryIdAndLevel(assetEntryId, level, status);
	}
	
	/**
	 * Retourne tous les commentaires d'un commentaire parent
	 */
	@Override 
	public List<Comment> getByParentCommentId(long parentCommentId, int status) {
		return this.commentPersistence.findByParentCommentId(parentCommentId, status);
	}
	
	/**
	 * Crée un commentaire vide avec une PK, non ajouté à la base de donnée
	 */
	@Override
	public Comment createComment(String userPublikId,ServiceContext sc) throws PortalException {
		long pk = counterLocalService.increment();

		Comment comment = this.commentLocalService.createComment(pk);
		comment.setPublikId(userPublikId);
		comment.setGroupId(sc.getScopeGroupId());
		comment.setUserId(sc.getUserId());
		comment.setStatus(WorkflowConstants.STATUS_APPROVED);
		return comment;
	}

	/**
	 * Met à jour un commentaire et l'enregistre en base de données
	 * @throws IOException
	 */
    @Override
	public Comment updateComment(Comment comment, ServiceContext sc)
			throws PortalException {
		User user = UserLocalServiceUtil.getUser(sc.getUserId());
		comment.setStatusByUserId(sc.getUserId());
		comment.setStatusByUserName(comment.getPublikUserName());
		comment.setStatusDate(sc.getModifiedDate());
		if (sc.getWorkflowAction() == WorkflowConstants.ACTION_PUBLISH) {
			comment.setStatus(WorkflowConstants.STATUS_APPROVED);
		} else {
			comment.setStatus(WorkflowConstants.STATUS_DRAFT);
		}
		comment = this.commentLocalService.updateComment(comment);
		this.updateAssetEntry(comment, sc);
		this.reindex(comment, false);

		return comment;
	}

	/**
	 * Met à jour l'AssetEntry rattachée au projet
	 */
	private void updateAssetEntry(Comment comment, ServiceContext sc)
			throws PortalException {
		this.assetEntryLocalService.updateEntry(
		        sc.getUserId(),
                sc.getScopeGroupId(),
                comment.getCreateDate(),
                comment.getModifiedDate(),
                Comment.class.getName(),
                comment.getPrimaryKey(),
                comment.getUuid(),
                0,
                sc.getAssetCategoryIds(),
                sc.getAssetTagNames(),
                true,
                comment.isApproved(),
                comment.getCreateDate(),
                null,
                comment.getCreateDate(),
                null,
                ContentTypes.TEXT_HTML,
                comment.getUserName(),
                comment.getComment(),
                comment.getComment(),
                null,
                null,
                0,
                0,
                null); // Priority

		// Réindexe le projet
		this.reindex(comment, false);
	}

    /**
     * méthode permettant d'obtenir une partie du commentaire.
     * @param comment le commentaire en entier.
     * @return le résultat du commentaire.
     */
    public String getSummary(String comment) {
        return (comment.length()<20)?comment: comment.substring(0, 15) + "...";
    }

    /**
	 * Reindex le projet dans le moteur de recherche
	 */
	private void reindex(Comment comment, boolean delete) throws SearchException {
		Indexer<Comment> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(Comment.class);
		if (delete) {
			indexer.delete(comment);
		} else {
			indexer.reindex(comment);
		}
	}

    /**
     * Met à jour le statut du projet par le framework workflow
     */
    @Override
    public Comment updateStatus(long userId, long entryId, int status,
                                ServiceContext sc, Map<String, Serializable> workflowContext)
            throws PortalException {
        Date now = new Date();
        // Statut de l'entité
        Comment comment = this.getComment(entryId);
        comment.setStatus(status);
        User user = UserLocalServiceUtil.fetchUser(userId);
        if (user != null) {
            comment.setStatusByUserId(user.getUserId());
            comment.setStatusByUserName(user.getFullName());
        }
        comment.setStatusDate(new Date());
        comment = this.commentLocalService.updateComment(comment);

        // Statut de l'entry
        AssetEntry entry = this.assetEntryLocalService
                .getEntry(Comment.class.getName(), comment.getPrimaryKey());
        entry.setVisible(status == WorkflowConstants.STATUS_APPROVED);
        if (entry.isVisible()) {
            entry.setPublishDate(now);
        }
        this.assetEntryLocalService.updateAssetEntry(entry);

        this.reindex(comment, false);

        return comment;
    }

    /**
     * Met à jour le statut du projet "manuellement" (pas via le workflow)
     */
    @Override
    public void updateStatus(Comment comment, int status) throws PortalException {
        this.updateStatus(comment.getUserId(), comment.getCommentId(), status, null,
                null);
    }

	/**
	 * Supprime un lien
	 */
	@Override
	public Comment removeComment(long commentId) throws PortalException {
		AssetEntry entry = this.assetEntryLocalService
			.fetchEntry(Comment.class.getName(), commentId);

		if (entry != null) {
			// Supprime lien avec les catégories
			for (long categoryId : entry.getCategoryIds()) {
				this.assetEntryLocalService.deleteAssetCategoryAssetEntry(
					categoryId, entry.getEntryId());
			}

			// Supprime lien avec les tags
			long[] tagsIds = this.assetEntryLocalService
				.getAssetTagPrimaryKeys(entry.getEntryId());
			for (long tagId : tagsIds) {
				this.assetEntryLocalService.deleteAssetTagAssetEntry(tagId,
					entry.getEntryId());
			}

			// Supprime lien avec les autres entries
			List<AssetLink> links = this.assetLinkLocalService
					.getLinks(entry.getEntryId());
			for (AssetLink link : links) {
				this.assetLinkLocalService.deleteAssetLink(link);
			}

			// Supprime l'AssetEntry
			this.assetEntryLocalService.deleteEntry(entry);
		}

		// Supprime le lien
		Comment comment = this.commentPersistence.remove(commentId);

		//supprime son indexation
		this.reindex(comment,true);

		// Supprime les reponses
		List<Comment> childComments = comment.getApprovedChildComments();
		if (childComments!=null&&!childComments.isEmpty()){
            for (Comment childComment : childComments) {
                this.removeComment(childComment.getCommentId());
            }
        }

		//Supprime les signalements
        List<Signalement> signalements = SignalementLocalServiceUtil.findByCommentId(commentId);
        if (signalements!=null&&!signalements.isEmpty()){
            signalements.forEach(SignalementLocalServiceUtil::deleteSignalement);
        }

		//supprime les likes
        List<Like> likes = comment.getLikes();
        if (likes!=null&&!likes.isEmpty()){
            likes.forEach(LikeLocalServiceUtil::deleteLike);
        }

		return comment;
	}


    /**
     * Renvoie la liste des vocabulaires rattachés à un commentaire
     */
    @Override
    public List<AssetVocabulary> getAttachedVocabularies(long groupId) {
        List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil
                .getAssetVocabularies(-1, -1);
        List<AssetVocabulary> attachedVocabularies = new ArrayList<>();
        long classNameId = ClassNameLocalServiceUtil
                .getClassNameId(Comment.class);
        for (AssetVocabulary vocabulary : vocabularies) {
            if (vocabulary.getGroupId() == groupId
                    && LongStream.of(vocabulary.getSelectedClassNameIds())
                    .anyMatch(c -> c == classNameId)) {
                attachedVocabularies.add(vocabulary);
            }
        }
        return attachedVocabularies;
    }


    /**
     * Recherche par mot clés
     */
    @Override
    public List<Comment> findByKeyword(String keyword, long groupId, int start,
                                       int end) {
        DynamicQuery dynamicQuery = dynamicQuery();

        if (keyword.length() > 0) {
            dynamicQuery.add(
                    RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
        }
        if (groupId > 0) {
            dynamicQuery
                    .add(PropertyFactoryUtil.forName("groupId").eq(groupId));
        }
        
        return commentPersistence.findWithDynamicQuery(dynamicQuery, start, end);
    }
    
    /**
     * Recherche par mot clés (compte)
     */
    @Override
    public long findByKeywordCount(String keyword, long groupId) {
        DynamicQuery dynamicQuery = dynamicQuery();
        if (keyword.length() > 0) {
            dynamicQuery.add(
                    RestrictionsFactoryUtil.like("title", "%" + keyword + "%"));
        }
        if (groupId > 0) {
            dynamicQuery
                    .add(PropertyFactoryUtil.forName("groupId").eq(groupId));
        }

        return commentPersistence.countWithDynamicQuery(dynamicQuery);
    }

}