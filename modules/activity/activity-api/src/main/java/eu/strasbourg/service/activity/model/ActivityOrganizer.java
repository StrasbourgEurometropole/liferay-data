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
 * The extended model interface for the ActivityOrganizer service. Represents a row in the &quot;activity_ActivityOrganizer&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityOrganizerModel
 * @see eu.strasbourg.service.activity.model.impl.ActivityOrganizerImpl
 * @see eu.strasbourg.service.activity.model.impl.ActivityOrganizerModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.activity.model.impl.ActivityOrganizerImpl")
@ProviderType
public interface ActivityOrganizer extends ActivityOrganizerModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.activity.model.impl.ActivityOrganizerImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ActivityOrganizer, Long> ACTIVITY_ORGANIZER_ID_ACCESSOR =
		new Accessor<ActivityOrganizer, Long>() {
			@Override
			public Long get(ActivityOrganizer activityOrganizer) {
				return activityOrganizer.getActivityOrganizerId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ActivityOrganizer> getTypeClass() {
				return ActivityOrganizer.class;
			}
		};

	/**
	* Retourne la version live de cette entité
	*/
	public eu.strasbourg.service.activity.model.ActivityOrganizer getLiveVersion();

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
	* Retourne l'URL de l'image
	*/
	public java.lang.String getImageURL();
}