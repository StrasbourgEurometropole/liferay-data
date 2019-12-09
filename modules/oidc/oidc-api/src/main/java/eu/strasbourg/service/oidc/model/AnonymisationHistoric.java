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

package eu.strasbourg.service.oidc.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AnonymisationHistoric service. Represents a row in the &quot;publik_AnonymisationHistoric&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AnonymisationHistoricModel
 * @see eu.strasbourg.service.oidc.model.impl.AnonymisationHistoricImpl
 * @see eu.strasbourg.service.oidc.model.impl.AnonymisationHistoricModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.oidc.model.impl.AnonymisationHistoricImpl")
@ProviderType
public interface AnonymisationHistoric extends AnonymisationHistoricModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.oidc.model.impl.AnonymisationHistoricImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AnonymisationHistoric, Long> ANONYMISATION_HISTORIC_ID_ACCESSOR =
		new Accessor<AnonymisationHistoric, Long>() {
			@Override
			public Long get(AnonymisationHistoric anonymisationHistoric) {
				return anonymisationHistoric.getAnonymisationHistoricId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AnonymisationHistoric> getTypeClass() {
				return AnonymisationHistoric.class;
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
	* Renvoie le label affichable du resultat de l'anonymisation
	*
	* @return
	*/
	public java.lang.String getResultLabel();

	/**
	* Ajout d'une ligne dans le resultat de l'anonymisation
	*
	* @return
	*/
	public void addNewOperation(java.lang.String operation);

	/**
	* Envoi du mail d'import
	*/
	public void sendMail();
}