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

package eu.strasbourg.service.comment.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.service.like.model.Like;
import eu.strasbourg.service.like.service.LikeLocalServiceUtil;
import eu.strasbourg.service.comment.model.Signalement;
import eu.strasbourg.service.comment.service.SignalementLocalServiceUtil;
import eu.strasbourg.service.comment.service.persistence.SignalementUtil;
import eu.strasbourg.service.comment.model.Signalement;
import eu.strasbourg.service.comment.service.SignalementLocalServiceUtil;
import eu.strasbourg.service.comment.service.persistence.SignalementUtil;
import eu.strasbourg.service.oidc.model.PublikUser;
import eu.strasbourg.service.oidc.service.PublikUserLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;

/**
 * The extended model implementation for the Comment service. Represents a row in the &quot;comment_Comment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.comment.model.Comment} interface.
 * </p>
 *
 * @author Romain Vergnais
 */
@ProviderType
public class CommentImpl extends CommentBaseImpl {

	private static final long serialVersionUID = 6922508470027188080L;
	public final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a comment model instance should use the {@link eu.strasbourg.service.comment.model.Comment} interface instead.
	 */
	public CommentImpl() {
	}
	
	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		//FIXME vérifier pourquoi la méthode fetchEntry renvoie null lors de l'enregistrement d'un commentaire.
		AssetEntry result = AssetEntryLocalServiceUtil.fetchEntry(Comment.class.getName(),
				this.getCommentId());
		if (result==null){
			try {
				result = AssetEntryLocalServiceUtil.getAssetEntry(this.getAssetEntryId());
				if (result == null){
					_log.error("Erreur lors de l'enregistrement d'un commentaire : l'asset est null");
				}
			} catch (PortalException e) {
				_log.error("Erreur lors de l'enregistrement d'un commentaire : ",e);
			}
		}
		return result;
	}

    @Override
    public String getTypeAssetEntry(){
	    String result="";
        try {
            AssetEntry entry = AssetEntryLocalServiceUtil.getAssetEntry(this.getAssetEntryId());
            String temp = entry.getClassName();
            result = temp.substring(temp.lastIndexOf(".")+1);
        } catch (PortalException e) {
			_log.error("Erreur lors de la récupération du type : ",e);
        }
        return result;
    }

    @Override
    public String getAssetEntryTitle(){
        String result="";
        try {
            AssetEntry entry = AssetEntryLocalServiceUtil.getAssetEntry(this.getAssetEntryId());
            result = entry.getTitle();
        } catch (PortalException e) {
			_log.error("Erreur lors de la récupération du nom : ",e);
        }
        return result;
    }

	/**
	 * Renvoie la liste des AssetCategory rattachées à cet item (via
	 * l'assetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
        AssetEntry param = this.getAssetEntry();
		return AssetVocabularyHelper
			.getAssetEntryCategories(param);
	}
	
	@Override
	public PublikUser getPublikUser() {
		return PublikUserLocalServiceUtil.getByPublikUserId(this.getPublikId());
	}
	
	//Le nom de l'utilisateur formaté : Vincent L.
	@Override
	public String getPublikUserName() {
		return StringUtil.upperCaseFirstLetter(getPublikUser().getFirstName()) 
				+ " " 
				+  StringUtil.toUpperCase(StringUtil.shorten(getPublikUser().getLastName(), 2, "."));
	}

	
	/**
	 * Retourne la liste des like/dislike de l'entité
	 * @see eu.strasbourg.service.like.model.LikeType
	 */
	@Override
	public List<Like> getLikesDislikes() {
		return LikeLocalServiceUtil.getByEntityIdAndTypeId(
				this.getCommentId(), 
				16);
	}
	
	/**
	 * Retourne la liste des likes de l'entité
	 *  @see eu.strasbourg.service.like.model.LikeType
	 */
	@Override
	public List<Like> getLikes() {
		return LikeLocalServiceUtil.getByEntityIdAndTypeIdAndIsDislike(
				this.getCommentId(), 
				16, 
				false);
	}
	
	/**
	 * Retourne la liste des dislikes de l'entité
	 *  @see eu.strasbourg.service.like.model.LikeType
	 */
	@Override
	public List<Like> getDislikes() {
		return LikeLocalServiceUtil.getByEntityIdAndTypeIdAndIsDislike(
				this.getCommentId(), 
				16, 
				true);
	}
	
	/**
	 * Retourne le nombre de likes/dislikes de l'entité
	 * @see eu.strasbourg.service.like.model.LikeType
	 */
	@Override
	public int getNbLikesDislikes() {
		return LikeLocalServiceUtil.getByEntityIdAndTypeId(
				this.getCommentId(), 
				16).size();
	}
	
	/**
	 * Retourne le nombre de likes de l'entité
	 *  @see eu.strasbourg.service.like.model.LikeType
	 */
	@Override
	public int getNbLikes() {
		return LikeLocalServiceUtil.getByEntityIdAndTypeIdAndIsDislike(
				this.getCommentId(), 
				16, 
				false).size();
	}
	
	/**
	 * Retourne le nombre de dislikes de l'entité
	 *  @see eu.strasbourg.service.like.model.LikeType
	 */
	@Override
	public int getNbDislikes() {
		return LikeLocalServiceUtil.getByEntityIdAndTypeIdAndIsDislike(
				this.getCommentId(), 
				16, 
				true).size();
	}
	
	/**
	 * Retourne la liste des commentaires enfants de l'item
	 */
	@Override
	public List<Comment> getApprovedChildComments() {
		return CommentLocalServiceUtil.getByParentCommentId(
				this.getCommentId(),
				WorkflowConstants.STATUS_APPROVED);
	}
	

	/**
	 * méthode qui renvoie la liste des signalements d'un commentaire.
	 * @return la liste des signalements
	 */
	public List<Signalement> findSignalements(){
		return SignalementLocalServiceUtil.findByCommentId(getCommentId());
	}

	/**
	 * méthode qui renvoie le nombre de signalement pour un commentaire.
	 * @return le nombre de signalement en int.
	 */
	public int getCountSignalements(){
		return findSignalements().size();
	}
}