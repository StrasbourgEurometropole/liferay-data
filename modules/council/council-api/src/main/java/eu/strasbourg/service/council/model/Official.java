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

package eu.strasbourg.service.council.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Official service. Represents a row in the &quot;council_Official&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see OfficialModel
 * @see eu.strasbourg.service.council.model.impl.OfficialImpl
 * @see eu.strasbourg.service.council.model.impl.OfficialModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.council.model.impl.OfficialImpl")
@ProviderType
public interface Official extends OfficialModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.council.model.impl.OfficialImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Official, Long> OFFICIAL_ID_ACCESSOR = new Accessor<Official, Long>() {
			@Override
			public Long get(Official official) {
				return official.getOfficialId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Official> getTypeClass() {
				return Official.class;
			}
		};

	/**
	* Retourne l'AssetEntry rattaché cet item
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Renvoie la liste des AssetCategory rattachées à cet item (via l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Renvoie le nom de complet au format "Prénom NOM"
	*/
	public java.lang.String getFullName();

	/**
	* Renvoie si l'electeur est noté absent pour la session données
	*/
	public boolean isNotedAbsent(long councilSessionId);

	/**
	* Renvoie le statut de connection de l'utilisateur
	*
	* @return True si la dernière connection date de moins de 15sec
	*/
	public boolean isConnected();

	/**
	* Renvoie les types de conseil rattachés à cet élu
	*/
	public java.util.List<eu.strasbourg.service.council.model.Type> getCouncilTypes();

	/**
	* Renvoie un strind 'id types de conseil rattachés à cet élu
	*/
	public java.lang.String getCouncilTypesIds();

	/**
	* Renvoie l'élu au format JSON
	*/
	public com.liferay.portal.kernel.json.JSONObject toJSON();
}