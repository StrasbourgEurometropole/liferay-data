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

package eu.strasbourg.service.activity.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AssociationActivity service. Represents a row in the &quot;activity_AssociationActivity&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AssociationActivityModel
 * @see eu.strasbourg.service.activity.model.impl.AssociationActivityImpl
 * @see eu.strasbourg.service.activity.model.impl.AssociationActivityModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.activity.model.impl.AssociationActivityImpl")
@ProviderType
public interface AssociationActivity extends AssociationActivityModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.activity.model.impl.AssociationActivityImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AssociationActivity, Long> ASSOCIATION_ACTIVITY_ID_ACCESSOR =
		new Accessor<AssociationActivity, Long>() {
			@Override
			public Long get(AssociationActivity associationActivity) {
				return associationActivity.getAssociationActivityId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssociationActivity> getTypeClass() {
				return AssociationActivity.class;
			}
		};

	/**
	* Retourne la version live de cette entité
	*/
	public eu.strasbourg.service.activity.model.AssociationActivity getLiveVersion();

	/**
	* Retourne l'AssetEntry rattaché à cette entité
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Renvoie la liste des AssetCategory rattachées à cette entité (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Retourne l'association de l'activité
	*/
	public eu.strasbourg.service.activity.model.Association getAssociation();

	/**
	* Retourne le label de l'activité de l'association
	*/
	public java.lang.String getActivityLabel(java.util.Locale locale);
}