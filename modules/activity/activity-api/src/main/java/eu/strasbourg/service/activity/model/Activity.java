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
 * The extended model interface for the Activity service. Represents a row in the &quot;activity_Activity&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityModel
 * @see eu.strasbourg.service.activity.model.impl.ActivityImpl
 * @see eu.strasbourg.service.activity.model.impl.ActivityModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.activity.model.impl.ActivityImpl")
@ProviderType
public interface Activity extends ActivityModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.activity.model.impl.ActivityImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Activity, Long> ACTIVITY_ID_ACCESSOR = new Accessor<Activity, Long>() {
			@Override
			public Long get(Activity activity) {
				return activity.getActivityId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Activity> getTypeClass() {
				return Activity.class;
			}
		};

	/**
	* Retourne les cours de l'activité
	*/
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> getActivityCourses();

	/**
	* Retourne les cours publiés du lieu
	*/
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCourse> getPublishedActivityCourses();

	/**
	* Retourne la version live de cette entité
	*
	* @return
	*/
	public eu.strasbourg.service.activity.model.Activity getLiveVersion();

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
	* Retourne les types de l'activité
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTypes();

	/**
	* Retourne le texte à afficher pour les types de l'activité
	*/
	public java.lang.String getTypesLabel(java.util.Locale locale);

	/**
	* Retourne les publics de l'activité
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getPublics();

	/**
	* Retourne le texte à afficher pour les publics de l'activité
	*/
	public java.lang.String getPublicsLabel(java.util.Locale locale);

	/**
	* Retourne l'URL de l'image
	*/
	public java.lang.String getImageURL();

	/**
	* Retourne la liste des URL publiques des images additionnelles
	*/
	public java.util.List<java.lang.String> getImagesURLs();

	/**
	* Retourne la liste des vidéos
	*/
	public java.util.List<eu.strasbourg.service.video.model.Video> getVideos();

	/**
	* Retourne la liste des URLs des documents
	*/
	public java.util.List<java.lang.String> getFilesURLs();
}