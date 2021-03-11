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

package eu.strasbourg.service.help.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the HelpProposal service. Represents a row in the &quot;help_HelpProposal&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see HelpProposalModel
 * @generated
 */
@ImplementationClassName(
	"eu.strasbourg.service.help.model.impl.HelpProposalImpl"
)
@ProviderType
public interface HelpProposal extends HelpProposalModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>eu.strasbourg.service.help.model.impl.HelpProposalImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<HelpProposal, Long> HELP_PROPOSAL_ID_ACCESSOR =
		new Accessor<HelpProposal, Long>() {

			@Override
			public Long get(HelpProposal helpProposal) {
				return helpProposal.getHelpProposalId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<HelpProposal> getTypeClass() {
				return HelpProposal.class;
			}

		};

	/**
	 * Retourne l'AssetEntry rattaché a cette proposition d'aide
	 */
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	 * Renvoie la liste des AssetCategory rattachées à cette proposition d'aide (via
	 * l'assetEntry)
	 */
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCategories();

	/**
	 * Retourne la couleur du statut d'activité de la proposition d'aide
	 */
	public String getActivityStatusColor();

	/**
	 * Retourne la class du statut d'activité de la proposition d'aide
	 */
	public String getActivityStatusClass();

	/**
	 * Retourne le statut d'activité de la proposition d'aide (
	 */
	public String getActivityStatusTitle(java.util.Locale locale);

	/**
	 * Retourne la catégorie statut activite proposition d'aide de l'aide
	 */
	public com.liferay.asset.kernel.model.AssetCategory
		getActivityStatusCategory();

	/**
	 * Retourne si la catégorie statut activité est bien à "Active" sinon "Inactive"
	 */
	public boolean isActive();

	/**
	 * Retourne la class du statut de modération de la proposition d'aide (
	 */
	public String getModerationStatusClass();

	/**
	 * Retourne le statut de modération de la proposition d'aide (
	 */
	public String getModerationStatusTitle(java.util.Locale locale);

	/**
	 * Retourne la catégorie statut activite proposition d'aide de l'aide
	 */
	public com.liferay.asset.kernel.model.AssetCategory
		getModerationStatusCategory();

	/**
	 * Retourne une chaine des localisations correspondant
	 */
	public String getHelpProposalTypeLabel(java.util.Locale locale);

	/**
	 * Retourne les type d'aide de la proposition d'aide
	 */
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getHelpProposalTypeCategories();

	/**
	 * Retourne les types d'aidant de la proposition d'aide
	 */
	public com.liferay.asset.kernel.model.AssetCategory
		getHelpProposalHelperCategory();

	/**
	 * Retourne une chaine des localisations correspondant
	 */
	public String getLocalisationLabel(java.util.Locale locale);

	/**
	 * Retourne les sous-catégories 'Territoire' correspondant aux villes de la initiative
	 *
	 * @return : null si vide, sinon la liste des catégories
	 */
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCityCategories();

	/**
	 * Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers de la proposition d'aide
	 *
	 * @return : null si vide, sinon la liste des catégories
	 */
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getDistrictCategories();

	/**
	 * Retourne les catégories 'Territoire' correspondant aux pays de la proposaition d'aide
	 */
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getTerritoryCategories();

	/**
	 * Retourne le nom de du depositaire sous forme "Truc M." ou le "Au nom de ..."
	 */
	public String getAuthorLabel();

	/**
	 * Retourne l'adresse mail du depositaire s'il existe
	 */
	public String getAuthorEmail();

	/**
	 * Retourne le nom prenom du depositaire s'il existe
	 */
	public String getAuthorNameLabel();

	/**
	 * Retourne l'utilisateur Publik depositaire
	 */
	public eu.strasbourg.service.oidc.model.PublikUser getAuthor();

	/**
	 * Retourne le nombre de demandes d'aides pour cette proposition
	 */
	public int getNbHelpRequests();

	/**
	 * @return La date de création au format français jj/mm/aaaa
	 */
	public String getCreatedDateFr();

	/**
	 * @return La date de modification utilisateur au format français jj/mm/aaaa si != de la date de création
	 */
	public String getModifiedByUserDateFr();

	/**
	 * Retourne l'URL de l'image à partir de l'id du DLFileEntry
	 */
	public String getImageURL();

	/**
	 * Retourne le copyright de l'image principale
	 */
	public String getImageCopyright(java.util.Locale locale);

	/**
	 * Est-ce l'utilisateur qui a crée la proposition ?
	 *
	 * @param publikUserId L'identifiant Publik de l'utilisateur
	 * @return La verite
	 */
	public boolean isUserHelping(String publikUserId);

	/**
	 * Retourne la version JSON de l'entité
	 */
	public com.liferay.portal.kernel.json.JSONObject toJSON(
		java.util.Locale locale);

}