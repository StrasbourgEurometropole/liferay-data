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

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.model.Signalement;
import eu.strasbourg.service.comment.service.CommentLocalService;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;

import java.util.List;

/**
 * The extended model implementation for the Signalement service. Represents a row in the &quot;comment_Signalement&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.comment.model.Signalement} interface.
 * </p>
 *
 * @author Romain Vergnais
 */
@ProviderType
public class SignalementImpl extends SignalementBaseImpl {


	public final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a signalement model instance should use the {@link eu.strasbourg.service.comment.model.Signalement} interface instead.
	 */
	public SignalementImpl() {
	}

	/**
	 * méthode qui permet de récupérer le commmentaire lié au signalement.
	 * @return le commentaire.
	 */
	public Comment getComment(){
		Comment result = null;
		try {
			result = CommentLocalServiceUtil.getComment(this.getCommentId());
		} catch (PortalException e) {
			_log.error("Erreur dans la récupération du commentaire : ",e);
		}
		return result;
	}

    /**
     * méthode qui permet de récupérer le commmentaire lié au signalement.
     * @return le commentaire.
     */
    public String getCommentContent(){
        String result = "";
        try {
            Comment comment = CommentLocalServiceUtil.getComment(this.getCommentId());
            result = comment.getComment();
        } catch (PortalException e) {
            _log.error("Erreur dans la récupération du commentaire : ",e);
        }
        return result;
    }

    /**
     * Renvoie la liste des AssetCategory rattachées à cet item (via
     * l'assetEntry)
     */
    @Override
    public List<AssetCategory> getCategories() {
        return AssetVocabularyHelper
                .getAssetEntryCategories(this.getAssetEntry());
    }

    /**
     * Retourne l'AssetEntry rattaché cet item
     */
    @Override
    public AssetEntry getAssetEntry() {
        return AssetEntryLocalServiceUtil.fetchEntry(Signalement.class.getName(),this.getSignalementId());
    }

}