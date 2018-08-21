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

package eu.strasbourg.service.project.model.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.service.PlacitPlaceLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * The extended model implementation for the Petition service. Represents a row in the &quot;project_Petition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.model.Petition} interface.
 * </p>
 *
 * @author Cedric Henry
 */
@ProviderType
public class PetitionImpl extends PetitionBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a petition model instance should use the {@link eu.strasbourg.service.project.model.Petition} interface instead.
	 */
	public PetitionImpl() {
	}

	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Petition.class.getName(),
				this.getPetitionId());
	}

	/**
	 * Retourne le label de 5 digits du nombre de commentaires de l'entité
	 */
	@Override
	public String getNbApprovedCommentsLabel() {
		// Transforme le numero en chaine de caractere
		String stringNum = Integer.toString(this.getNbApprovedComments());
		// Recupere le nombre de chiffre
		int nbDigits = stringNum.length();
		// Ajoute les zeros manquants avant la chaine
		stringNum = new String(new char[5 - nbDigits]).replace("\0", "0") + stringNum;
		return stringNum;
	}

	/**
	 * Retourne le nombre de commentaires de l'entité
	 */
	@Override
	public int getNbApprovedComments() {
		return CommentLocalServiceUtil.getByAssetEntry(
				this.getAssetEntry().getEntryId(),
				WorkflowConstants.STATUS_APPROVED).size();
	}


    /**
     * Retourne les commentaires de l'entité
     */
    @Override
    public List<Comment> getApprovedComments() {
        return CommentLocalServiceUtil.getByAssetEntry(
                this.getAssetEntry().getEntryId(),
                WorkflowConstants.STATUS_APPROVED);
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
     * Retourne le status de la petition
     */
    @Override
    public AssetCategory getPetitionStatusCategory() {
        List<AssetCategory> listStatus = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
                VocabularyNames.PETITION_STATUS);
        return listStatus.size() > 0 ? listStatus.get(0) : null;
    }

	public String getSituationStatusPetition() {
		String result = "Nouvelle";
		if (getExpirationDate()==null)
			return result;
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime expirationTime = new Timestamp(getExpirationDate().getTime()).toLocalDateTime();
		boolean isExpired = now.isAfter(expirationTime);
		boolean quotaSignatureAtteint = getNombreSignature() >= getQuotaSignature();

		if (quotaSignatureAtteint && !isExpired)
			result = "Aboutie";
		else if (isExpired && !quotaSignatureAtteint)
			result = "Non aboutie";
		else {
			long periodTemp = ChronoUnit.DAYS.between(now, expirationTime);
			if (!isExpired && periodTemp <= 7)
				result = "Bient&ocirc;t termin&eacute;e";
			else result = "En cours";
		}

		return result;
	}


	/**
	 * Retourne la liste des lieux placit liés à la participation
	 */
	@Override
	public List<PlacitPlace> getPlacitPlaces() {
		return PlacitPlaceLocalServiceUtil.getByPetition(this.getPetitionId());
	}

}