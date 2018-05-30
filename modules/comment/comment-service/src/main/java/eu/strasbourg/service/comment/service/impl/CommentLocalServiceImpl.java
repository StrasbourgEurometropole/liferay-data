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

import java.util.List;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLink;
import com.liferay.portal.kernel.exception.PortalException;

import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.base.CommentLocalServiceBaseImpl;

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


		return comment;
	}
}