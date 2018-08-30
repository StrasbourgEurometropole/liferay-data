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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import eu.strasbourg.service.comment.model.Comment;
import eu.strasbourg.service.comment.service.CommentLocalServiceUtil;
import eu.strasbourg.service.project.model.Petition;
import eu.strasbourg.service.project.model.PlacitPlace;
import eu.strasbourg.service.project.model.Signataire;
import eu.strasbourg.service.project.service.PetitionLocalServiceUtil;
import eu.strasbourg.service.project.service.PlacitPlaceLocalServiceUtil;
import eu.strasbourg.service.project.service.SignataireLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

	public static final String COMPLETED = "completed";
	public static final String DRAFT = "Brouillon";
	public static final String FAILED = "failed";

	public final Log _log = LogFactoryUtil.getLog(this.getClass().getName());

	public PetitionImpl() {
	}

    /**
     * Retourne les catégories 'Territoire' correspondant aux pays de la petition
     */
    @Override
    public List<AssetCategory> getTerritoryCategories() {
        return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
                VocabularyNames.TERRITORY);
    }

    /**
     * Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers de la petition
     * @return : null si vide, sinon la liste des catégories
     */
    @Override
    public List<AssetCategory> getDistrictCategories() {
        List<AssetCategory> territories = getTerritoryCategories();
        List<AssetCategory> districts = new ArrayList<>();
        for (AssetCategory territory : territories) {
            try {
                if (territory.getAncestors().size() == 2) {
                    districts.add(territory);
                }
            } catch (PortalException ignored) {
            }
        }
        return districts;
    }

	/**
	 * méthode permettant de récuperer les faux signataires d'une pétitions.
	 * @return les faux signataires.
	 */
    @Override
	public int getCountFakeSignataire(){
		return SignataireLocalServiceUtil.countFakeSignataireByPetition(getPetitionId());
	}

    /**
     * méthode permettant de récupérer le pourcentage de signatures obtenu.
     * @return le pourcentage en long.
     */
    @Override
    public double getPourcentageSignature(){
        Double nombreSignature = (double) getNombreSignature();
        Double quotaSignature = (double) getQuotaSignature();
        double result = nombreSignature / quotaSignature;
        return result * 100;
    }

    /**
     * Méthode permettant de retourner le nombre de signataire de la pétition
     * @return le nombre.
     */
    @Override
    public long getNombreSignature(){
		return (long) SignataireLocalServiceUtil.countSignataireByPetitionId(getPetitionId());
    }

    /**
     * méthode permettant d'afficher le nombre de signature.
     * @return le nombre avec des zeros devant.
     */
    @Override
    public String getNombreSignatureBoard(){
        long nbResult = getNombreSignature();
        return String.format("%06d",nbResult);
    }

    /**
     * Retourne une chaine des 'Territoires' correspondant aux quartiers de la petition
     * @return : Chaine des quartiers ou description "Aucun" ou "Tous"
     */
    @Override
    public String getDistrictLabel(Locale locale) {
        StringBuilder result = new StringBuilder();
        List<AssetCategory> districts = getDistrictCategories();
        if (districts==null || districts.isEmpty()){
            result.append("Aucun quartier");
        } else if (AssetVocabularyHelper.isAllDistrict(districts.size())){
            result.append("Tous les quartiers");
        } else {
            result.append(districts.stream()
                    .map(district -> district.getTitle(locale))
                    .collect(Collectors.joining(" - ")));
        }
        return result.toString();
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
     * Calcul la différence de jours entre la date du jour et celle d'expiration
     */
    @Override
    public int getTodayExpirationDifferenceDays () {
        // Instanciation des variables
        Date todayDate = new Date();
        Date expirationDate = this.getExpirationDate();

        // Calcul du nombre de millisecondes entre les deux dates et
        // conversion en nombre de jours
        long diff = expirationDate.getTime() - todayDate.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * Retourne la liste des URLs des documents
     */
    @Override
    public List<String> getFilesURLs() {
        List<String> URLs = new ArrayList<String>();
        for (String fileIdStr : this.getFilesIds().split(",")) {
            Long fileId = GetterUtil.getLong(fileIdStr);
            if (Validator.isNotNull(fileId)) {
                String fileURL = FileEntryHelper.getFileEntryURL(fileId);
                URLs.add(fileURL);
            }
        }
        return URLs;
    }

	/**
	 * Retourne l'URL de l'image à partir de l'id du DLFileEntry
	 */
    @Override
	public String getImageURL() {
		if (Validator.isNotNull(this.getExternalImageURL())) {
			return this.getExternalImageURL();
		} else {
			return FileEntryHelper.getFileEntryURL(this.getImageId());
		}
	}


	/**
	 * Retourne 3 suggestions max pour un thème appartenant à la vidéo en cours
	 * @param locale la locale de la région
	 * @return la liste de pétition.
	 */
	@Override
	public List<Petition> getSuggestions(Locale locale) {
		return getSuggestions(locale, 10);
	}

	/**
	 * Retourne X suggestions max pour un thème appartenant à la vidéo en cours
	 * @param locale la locale de la région
	 * @param nbSuggestions le nombre de suggestions.
	 * @return la liste de pétition.
	 */
	@Override
	public List<Petition> getSuggestions(Locale locale, int nbSuggestions) {
		List<Petition> suggestions = new ArrayList<>();
		long[] assetCategoryIds = {};
		String[] assetTagNames = {};
		Map<String, Serializable> attributes = new HashMap<>();
		Layout layout = null;
		long scopeGroupId = 0;
		TimeZone timeZone = TimeZone.getDefault();
		SearchContext searchContext = SearchContextFactory
				.getInstance(assetCategoryIds, assetTagNames, attributes, this.getCompanyId(), "", layout, locale, scopeGroupId, timeZone, this.getUserId());

		// ClassNames
		String[] className = {Petition.class.getName()};

		// Group Id
		long groupId = this.getGroupId();

		// Group Id global
		long globalGroupId = 0;

		List<Long[]> prefilterCategoriesIds = new ArrayList<>();
		String[] prefilterTagsNames = {};

		Hits hits = SearchHelper.getGlobalSearchHits(searchContext, className,
				groupId, globalGroupId, false, "", false, "",
				null, null, new ArrayList<>(), new ArrayList<>(), prefilterTagsNames, locale, -1, -1,
				"", true);

		if (hits != null)

		{
			List<Petition> petitions = new ArrayList<>();
			for (Document document : hits.getDocs()) {
				Petition petition = PetitionLocalServiceUtil.fetchPetition(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (petition != null && petition.getPetitionId() != this.getPetitionId()) {
					petitions.add(petition);
				}
			}
			Collections.shuffle(petitions);
			if (petitions.size() > nbSuggestions) {
				for (int j = 0; j < nbSuggestions; j++) {
					suggestions.add(petitions.get(j));
				}
			} else {
				suggestions = petitions;
			}
		}

		return suggestions;
	}

	/**
	 * Retourne le copyright de l'image principale
	 */
	@Override
	public String getImageCopyright(Locale locale) {
		if (Validator.isNotNull(this.getExternalImageCopyright())) {
			return this.getExternalImageCopyright();
		} else {
			return FileEntryHelper.getImageCopyright(this.getImageId(), locale);
		}
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
	 * Retourne les thematiques de la petition (
	 */
	@Override
	public List<AssetCategory> getThematicCategories() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.THEMATIC);
	}

	/**
	 * Retourne le projet de la petition (
	 */
	@Override
	public AssetCategory getProjectCategory() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.PROJECT).get(0);
	}


	@Override
	public String getAssetEntryTitle(){
		String result="N/A";
		try {
			AssetEntry entry = AssetEntryLocalServiceUtil.getAssetEntry(this.getAssetEntryId());
			String temp = entry.getTitle();
			if (temp!=null&&!temp.isEmpty()){
				if (temp.length()>50){
					temp = entry.getTitle(this.getLocale("FR"));
				}
				result = temp;
			}
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
        return AssetVocabularyHelper
                .getAssetEntryCategories(this.getAssetEntry());
    }

    List<Signataire> getSignataires(){
		return SignataireLocalServiceUtil.getSignatairesByPetitionId(getPetitionId());
	}

    /**
     * Retourne le status de la petition
     */
    @Override
    public AssetCategory getPetitionStatusCategory() {
        List<AssetCategory> listStatus = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
                VocabularyNames.PLACIT_STATUS);
        return listStatus.size() > 0 ? listStatus.get(0) : null;
    }

    /**
     * méthode permettant de récuperer le nombre de signataire nécessaire pour finir la pétition.
     * @return le nombre
     */
    @Override
    public long getSignataireNeeded() {
        return getQuotaSignature() - getNombreSignature();
    }

    @Override
    public String getPetitionStatus(){
    	String result = DRAFT;
		if (getPublicationDate() != null && getExpirationDate() != null) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime expirationTime = new Timestamp(getExpirationDate().getTime()).toLocalDateTime();
			LocalDateTime publicationTime = new Timestamp(getPublicationDate().getTime()).toLocalDateTime();
			boolean isExpired = now.isAfter(expirationTime);
			boolean quotaSignatureAtteint = getNombreSignature() >= getQuotaSignature();
			if (quotaSignatureAtteint && !isExpired)
				result = COMPLETED;
			else if (isExpired && !quotaSignatureAtteint)
				result = FAILED;
			else {
				long periodTemp = ChronoUnit.DAYS.between(now, expirationTime);
				long periodNews = ChronoUnit.DAYS.between(publicationTime, now);
				if (!isExpired && periodNews <= 7)
					result = ParticipationImpl.NEW;
				else if (!isExpired && periodTemp <= 7)
					result = ParticipationImpl.SOON_FINISHED;
				else result = ParticipationImpl.IN_PROGRESS;
			}
		}
		return result;
	}

	/**
	 * Retourne la liste des lieux placit liés à la petition
	 */
	@Override
	public List<PlacitPlace> getPlacitPlaces() {
		return PlacitPlaceLocalServiceUtil.getByPetition(this.getPetitionId());
	}

}