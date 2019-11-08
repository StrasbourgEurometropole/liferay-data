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

package eu.strasbourg.service.formSendRecordField.model.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.service.formSendRecordField.model.FormSendRecordField;
import eu.strasbourg.service.formSendRecordField.service.FormSendRecordFieldLocalServiceUtil;
import eu.strasbourg.service.like.model.Like;
import eu.strasbourg.service.like.service.LikeLocalServiceUtil;

import java.util.List;

/**
 * The extended model implementation for the FormSendRecordField service. Represents a row in the &quot;SendFormRecordField_FormSendRecordField&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.formSendRecordField.model.FormSendRecordField} interface.
 * </p>
 *
 * @author Angélique Zunino
 */
@ProviderType
public class FormSendRecordFieldImpl extends FormSendRecordFieldBaseImpl {

	private static final long serialVersionUID = 6922508470027188080L;
	public final Log _log = LogFactoryUtil.getLog(this.getClass().getName());
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a form send record field model instance should use the {@link eu.strasbourg.service.formSendRecordField.model.FormSendRecordField} interface instead.
	 */
	public FormSendRecordFieldImpl() {
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		AssetEntry result = AssetEntryLocalServiceUtil.fetchEntry(FormSendRecordField.class.getName(),
				this.getFormSendRecordFieldId());
		if (result==null){
			try {
				result = AssetEntryLocalServiceUtil.getAssetEntry(this.getAssetEntryId());
				if (result == null){
					_log.error("Erreur lors de l'enregistrement d'un formSendRecordField : l'asset est null");
				}
			} catch (PortalException e) {
				_log.error("Erreur lors de l'enregistrement d'un formSendRecordField : ",e);
			}
		}
		return result;
	}

	/**
	 * Retourne la liste des like/dislike de l'entité
	 * @see eu.strasbourg.service.like.model.LikeType
	 */
//	@Override
//	public List<Like> getLikesDislikes() {
//		return LikeLocalServiceUtil.getByEntityIdAndTypeId(
//				this.getFormSendRecordFieldId(),
//				16);
//	}

	/**
	 * Retourne la liste des likes de l'entité
	 *  @see eu.strasbourg.service.like.model.LikeType
	 */
	@Override
	public List<Like> getLikes() {
		return LikeLocalServiceUtil.getByEntityIdAndTypeIdAndIsDislike(
				this.getFormSendRecordFieldId(),
				20,
				false);
	}

	/**
	 * Retourne la liste des dislikes de l'entité
	 *  @see eu.strasbourg.service.like.model.LikeType
	 */
//	@Override
//	public List<Like> getDislikes() {
//		return LikeLocalServiceUtil.getByEntityIdAndTypeIdAndIsDislike(
//				this.getFormSendRecordFieldId(),
//				16,
//				true);
//	}

	/**
	 * Retourne le nombre de likes/dislikes de l'entité
	 * @see eu.strasbourg.service.like.model.LikeType
	 */
//	@Override
//	public int getNbLikesDislikes() {
//		return LikeLocalServiceUtil.getByEntityIdAndTypeId(
//				this.getFormSendRecordFieldId(),
//				16).size();
//	}

	/**
	 * Retourne le nombre de likes de l'entité
	 *  @see eu.strasbourg.service.like.model.LikeType
	 */
	@Override
	public int getNbLikes() {
		return LikeLocalServiceUtil.getByEntityIdAndTypeIdAndIsDislike(
				this.getFormSendRecordFieldId(),
				20,
				false).size();
	}

	/**
	 * Retourne le nombre de dislikes de l'entité
	 *  @see eu.strasbourg.service.like.model.LikeType
	 */
	@Override
	public int getNbDislikes() {
		return LikeLocalServiceUtil.getByEntityIdAndTypeIdAndIsDislike(
				this.getFormSendRecordFieldId(),
				20,
				true).size();
	}


	/**
	 * méthode qui renvoie la liste des signalements d'un commentaire.
	 * @return la liste des signalements
	 */
//	public List<Signalement> findSignalements(){
//		return SignalementLocalServiceUtil.findByFormSendRecordFieldId(getFormSendRecordFieldId());
//	}

	/**
	 * méthode qui renvoie le nombre de signalement pour un commentaire.
	 * @return le nombre de signalement en int.
	 */
//	public int getCountSignalements(){
//		List<Signalement> signalements = findSignalements();
//		List<Signalement> resultList = signalements.stream().filter(signalement -> signalement.getStatus()==0).collect(Collectors.toList());
//		return resultList.size();
//	}
}