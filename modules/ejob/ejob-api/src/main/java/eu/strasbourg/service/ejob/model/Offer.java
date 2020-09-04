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

package eu.strasbourg.service.ejob.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Offer service. Represents a row in the &quot;ejob_Offer&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see OfferModel
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.ejob.model.impl.OfferImpl")
@ProviderType
public interface Offer extends OfferModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>eu.strasbourg.service.ejob.model.impl.OfferImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Offer, Long> OFFER_ID_ACCESSOR =
		new Accessor<Offer, Long>() {

			@Override
			public Long get(Offer offer) {
				return offer.getOfferId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Offer> getTypeClass() {
				return Offer.class;
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
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCategories();

	/**
	 * Retourne la direction
	 */
	public com.liferay.asset.kernel.model.AssetCategory getOfferDirection();

	/**
	 * Retourne le service
	 */
	public com.liferay.asset.kernel.model.AssetCategory getOfferService();

	/**
	 * Retourne la filière
	 */
	public com.liferay.asset.kernel.model.AssetCategory getOfferFiliere();

	/**
	 * Renvoie la categorie des filières
	 */
	@SuppressWarnings(value = "unused")
	public com.liferay.asset.kernel.model.AssetCategory
		getOfferFiliereCategorie();

	/**
	 * Renvoie le grade
	 */
	@SuppressWarnings(value = "unused")
	public com.liferay.asset.kernel.model.AssetCategory getOfferGrade();

	/**
	 * Renvoie la Famille de métiers
	 */
	@SuppressWarnings(value = "unused")
	public com.liferay.asset.kernel.model.AssetCategory getOfferFamille();

	/**
	 * Renvoie le Niveau d'étude
	 */
	@SuppressWarnings(value = "unused")
	public com.liferay.asset.kernel.model.AssetCategory getOfferNiveauEtude();

	/**
	 * Renvoie le type de recrutement
	 */
	@SuppressWarnings(value = "unused")
	public com.liferay.asset.kernel.model.AssetCategory
		getOfferTypeRecrutement();

	/**
	 * Renvoie le contact RE
	 */
	@SuppressWarnings(value = "unused")
	public com.liferay.asset.kernel.model.AssetCategory getOfferContact();

	public com.liferay.asset.kernel.model.AssetCategory getTypePublication();

	/**
	 * Retourne la version JSON de l'offre
	 */
	public com.liferay.portal.kernel.json.JSONObject toJSON();

}