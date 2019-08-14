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

package eu.strasbourg.service.project.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the BudgetPhase service. Represents a row in the &quot;project_BudgetPhase&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see BudgetPhaseModel
 * @see eu.strasbourg.service.project.model.impl.BudgetPhaseImpl
 * @see eu.strasbourg.service.project.model.impl.BudgetPhaseModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.project.model.impl.BudgetPhaseImpl")
@ProviderType
public interface BudgetPhase extends BudgetPhaseModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.project.model.impl.BudgetPhaseImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<BudgetPhase, Long> BUDGET_PHASE_ID_ACCESSOR = new Accessor<BudgetPhase, Long>() {
			@Override
			public Long get(BudgetPhase budgetPhase) {
				return budgetPhase.getBudgetPhaseId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<BudgetPhase> getTypeClass() {
				return BudgetPhase.class;
			}
		};

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Retourne la categorie "Phase du budget participatif" de la phase
	*/
	public com.liferay.asset.kernel.model.AssetCategory getPhaseCategory();

	/**
	* Renvoie si la phase est en période de dépot
	*/
	public boolean isInDepositPeriod();

	/**
	* Renvoie si la phase est en période de vote
	*/
	public boolean isInVotingPeriod();

	/**
	* Genere le label de haut de page affichant le temps restant avant de passer a la prochaine
	* peridode de la phase en cours
	*/
	public java.lang.String getLivePeriodLabel();
}