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

package eu.strasbourg.service.gtfs.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ImportHistoric service. Represents a row in the &quot;gtfs_ImportHistoric&quot; database table, with each column mapped to a property of this class.
 *
 * @author Cedric Henry
 * @see ImportHistoricModel
 * @see eu.strasbourg.service.gtfs.model.impl.ImportHistoricImpl
 * @see eu.strasbourg.service.gtfs.model.impl.ImportHistoricModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.gtfs.model.impl.ImportHistoricImpl")
@ProviderType
public interface ImportHistoric extends ImportHistoricModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.gtfs.model.impl.ImportHistoricImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ImportHistoric, Long> IMPORT_HISTORIC_ID_ACCESSOR =
		new Accessor<ImportHistoric, Long>() {
			@Override
			public Long get(ImportHistoric importHistoric) {
				return importHistoric.getImportHistoricId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ImportHistoric> getTypeClass() {
				return ImportHistoric.class;
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
	* Renvoie le label affichable du resultat de l'import
	*
	* @return
	*/
	public java.lang.String getResultLabel();

	/**
	* Ajout d'une ligne dans le resultat de l'import
	*
	* @return
	*/
	public void addNewOperation(java.lang.String operation);
}