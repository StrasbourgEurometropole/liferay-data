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
	 * Retourne les catégories 'Territoire' correspondant aux pays de la initiative
	 */
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getTerritoryCategories();

	/**
	 * Retourne les sous-catégories 'Territoire' correspondant aux villes de la proposition d'aide
	 *
	 * @return : null si vide, sinon la liste des catégories
	 */
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCityCategories();

	/**
	 * Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers de la initiative
	 *
	 * @return : null si vide, sinon la liste des catégories
	 */
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getDistrictCategories();

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
	 *
	 * @return
	 */
	public eu.strasbourg.service.oidc.model.PublikUser getAuthor();

	/**
	 * Retourne le nombre de demandes d'aides pour cette proposition
	 */
	public int getNbHelpRequests();

	/**
	 * @return La date de publication au format français jj/mm/aaaa
	 */
	public String getPublicationDateFr();

	/**
	 * Retourne la version JSON de l'entité
	 *
	 * @throws PortalException
	 */
	public com.liferay.portal.kernel.json.JSONObject toJSON()
		throws com.liferay.portal.kernel.exception.PortalException;

}