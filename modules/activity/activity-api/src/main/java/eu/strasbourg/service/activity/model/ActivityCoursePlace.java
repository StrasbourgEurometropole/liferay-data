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
 * The extended model interface for the ActivityCoursePlace service. Represents a row in the &quot;activity_ActivityCoursePlace&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ActivityCoursePlaceModel
 * @see eu.strasbourg.service.activity.model.impl.ActivityCoursePlaceImpl
 * @see eu.strasbourg.service.activity.model.impl.ActivityCoursePlaceModelImpl
 * @generated
 */
@ImplementationClassName("eu.strasbourg.service.activity.model.impl.ActivityCoursePlaceImpl")
@ProviderType
public interface ActivityCoursePlace extends ActivityCoursePlaceModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link eu.strasbourg.service.activity.model.impl.ActivityCoursePlaceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ActivityCoursePlace, Long> ACTIVITY_COURSE_PLACE_ID_ACCESSOR =
		new Accessor<ActivityCoursePlace, Long>() {
			@Override
			public Long get(ActivityCoursePlace activityCoursePlace) {
				return activityCoursePlace.getActivityCoursePlaceId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ActivityCoursePlace> getTypeClass() {
				return ActivityCoursePlace.class;
			}
		};

	/**
	* Retourne les horaires du cours dans le lieu
	*/
	public java.util.List<eu.strasbourg.service.activity.model.ActivityCourseSchedule> getActivityCourseSchedules();

	/**
	* Retourne le cours du lieu
	*/
	public eu.strasbourg.service.activity.model.ActivityCourse getActivityCourse();

	/**
	* Retourne la version live de cette entité
	*/
	public eu.strasbourg.service.activity.model.ActivityCoursePlace getLiveVersion();

	/**
	* Retourne l'AssetEntry rattaché à cette entité
	*/
	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	/**
	* Retourne la liste des AssetCategory rattachées à cette entité (via
	* l'assetEntry)
	*/
	public java.util.List<com.liferay.asset.kernel.model.AssetCategory> getCategories();

	/**
	* Retourne le nom du lieu SIG
	*/
	public java.lang.String getPlaceAlias(java.util.Locale locale);
}