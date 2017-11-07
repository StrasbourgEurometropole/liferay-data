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
 * The extended model interface for the ActivityCourse service. Represents a row in the &quot;activity_ActivityCourse&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCourseModel
 * @see eu.strasbourg.service.activity.model.impl.ActivityCourseImpl
 * @see eu.strasbourg.service.activity.model.impl.ActivityCourseModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.activity.model.impl.ActivityCourseImpl")
@ProviderType
public interface ActivityCourse extends ActivityCourseModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.activity.model.impl.ActivityCourseImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ActivityCourse, Long> ACTIVITY_COURSE_ID_ACCESSOR =
		new Accessor<ActivityCourse, Long>() {
			@Override
			public Long get(ActivityCourse activityCourse) {
				return activityCourse.getActivityCourseId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ActivityCourse> getTypeClass() {
				return ActivityCourse.class;
			}
		};

	/**
	* Retourne le nom de l'organisateur du cours :
	* soit via le service, soit l'organisateur d'activité
	*/
	public java.lang.String getOrganizerName(java.util.Locale locale);

	/**
	* Retourne l'organisateur du cours
	*/
	public eu.strasbourg.service.activity.model.ActivityOrganizer getActivityOrganizer();

	/**
	* Retourne le service du cours
	*/
	public com.liferay.asset.kernel.model.AssetCategory getService();

	/**
	* Retourne l'activité du cours
	*/
	public eu.strasbourg.service.activity.model.Activity getActivity();

	/**
	* Retourne les horaires par lieu du cours
	*/
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCoursePlace> getActivityCoursePlaces();

	/**
	* Retourne les noms des lieux du cours
	*/
	public java.util.List<java.lang.String> getPlaceNames(
		java.util.Locale locale);

	/**
	* Retourne les ids SIG des lieux duc ours
	*/
	public java.util.List<java.lang.String> getPlaceSIGIds(
		java.util.Locale locale);

	/**
	* Retourne les publics dui cours
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getPublics();

	/**
	* Retourne le texte à afficher pour les publics du cours
	*/
	public java.lang.String getPublicsLabel(java.util.Locale locale);

	/**
	* Retourne les types du cours
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getTypes();

	/**
	* Retourne le texte à afficher pour les types du cours
	*/
	public java.lang.String getTypesLabels(java.util.Locale locale);

	/**
	* Retourne la version live de cette entité
	*/
	public eu.strasbourg.service.activity.model.ActivityCourse getLiveVersion();

	/**
	* Retourne l'AssetEntry rattaché à cette entité
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Renvoie la liste des AssetCategory rattachées à cette entité (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	public eu.strasbourg.service.activity.model.CourseAgenda getCourseAgenda(
		long groupId, java.util.Locale locale);
}