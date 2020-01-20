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

import java.util.Date;
import java.util.List;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.project.constants.PhaseState;
import eu.strasbourg.service.project.model.BudgetPhase;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

/**
 * The extended model implementation for the BudgetPhase service. Represents a row in the &quot;project_BudgetPhase&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.project.model.BudgetPhase} interface.
 * </p>
 *
 * @author Cedric Henry
 */
@ProviderType
public class BudgetPhaseImpl extends BudgetPhaseBaseImpl {

	private static final long serialVersionUID = 1316097781263834178L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a budget phase model instance should use the {@link eu.strasbourg.service.project.model.BudgetPhase} interface instead.
	 */
	public BudgetPhaseImpl() {
	}
	
	/**
	 * Retourne l'AssetEntry rattaché cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(BudgetPhase.class.getName(),
			this.getBudgetPhaseId());
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
	 * Retourne la categorie "Phase du budget participatif" de la phase
	 */
	@Override
	public AssetCategory getPhaseCategory() {
		List<AssetCategory> assetCategories = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
				VocabularyNames.PLACIT_BUDGET_PARTICIPATIF_PHASE);
        if (assetCategories.size() > 0) {
        	return assetCategories.get(0);
        } else {
        	return null;
        }
	}
	
	/**
	 * Renvoie si la phase est en période de dépot
	 */
	@Override
	public boolean isInDepositPeriod() {
		Date today = new Date();
		if (this.getIsActive() 
				&& today.compareTo(this.getBeginDate()) >= 0 
				&& today.compareTo(this.getEndDate()) <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	/**
	 * Renvoie si la phase est en période de vote
	 */
	@Override
	public boolean isInVotingPeriod() {
		Date today = new Date();
		if (this.getIsActive() 
				&& today.compareTo(this.getBeginVoteDate()) >= 0 
				&& today.compareTo(this.getEndVoteDate()) <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Genere le label de haut de page affichant le temps restant avant de passer a la prochaine
	 * peridode de la phase en cours
	 */
	@Override
	public String getLivePeriodLabel() {
		if (this.getIsActive()) {
			// Date du jour
			Date dateNow = new Date();
			
			// Avant la periode de depot
			if (dateNow.compareTo(this.getBeginDate()) <= 0) {
				return "before-begin-deposit";
			}
			// Avant la date de fin de depot
			else if (dateNow.compareTo(this.getEndDate()) <= 0) {
				return "before-end-deposit";
			}
			// Avant la periode de vote
			else if (dateNow.compareTo(this.getBeginVoteDate()) <= 0) {
				return "before-begin-vote";
			}
			// Avant la date de fin de vote
			else if (dateNow.compareTo(this.getEndVoteDate()) <= 0) {
				return "before-end-vote";
			} 
			// Apres l'ensemble des periodes
			else {
				return "";
			}
		} else {
			return "";
		}	
	}
	
	/**
	 * La l'etat de la phase
	 * @return
	 */
	@Override
	public PhaseState getPhaseState() {
		
		if (this.getIsActive()) {
			// Date du jour
			Date dateNow = new Date();
			
			// Avant la periode de depot
			if (dateNow.compareTo(this.getBeginDate()) <= 0) 
				return PhaseState.BEFORE_BEGIN_DEPOSIT;
			// Avant la date de fin de depot
			else if (dateNow.compareTo(this.getEndDate()) <= 0) 
				return PhaseState.BEFORE_END_DEPOSIT;
			// Avant la periode de vote
			else if (dateNow.compareTo(this.getBeginVoteDate()) <= 0) 
				return PhaseState.BEFORE_BEGIN_VOTE;
			// Avant la date de fin de vote
			else if (dateNow.compareTo(this.getEndVoteDate()) <= 0) 
				return PhaseState.BEFORE_END_VOTE;
			// Apres l'ensemble des periodes
			else 
				return PhaseState.AFTER_VOTE;
			
		} else 
			return PhaseState.NOT_ACTIVE;
		
	}
	
}