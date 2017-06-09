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

package eu.strasbourg.service.activity.model.impl;

import java.util.List;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.model.ActivityCoursePlace;
import eu.strasbourg.service.activity.model.ActivityOrganizer;
import eu.strasbourg.service.activity.service.ActivityCourseLocalServiceUtil;
import eu.strasbourg.service.activity.service.ActivityCoursePlaceLocalServiceUtil;
import eu.strasbourg.service.activity.service.ActivityLocalServiceUtil;
import eu.strasbourg.service.activity.service.ActivityOrganizerLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;

/**
 * The extended model implementation for the ActivityCourse service. Represents
 * a row in the &quot;activity_ActivityCourse&quot; database table, with each
 * column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.activity.model.ActivityCourse}
 * interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class ActivityCourseImpl extends ActivityCourseBaseImpl {
	private static final long serialVersionUID = 2578567090018184821L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a activity
	 * course model instance should use the {@link
	 * eu.strasbourg.service.activity.model.ActivityCourse} interface instead.
	 */
	public ActivityCourseImpl() {
	}

	/**
	 * Retourne l'organisateur du cours
	 */
	@Override
	public ActivityOrganizer getActivityOrganizer() {
		return ActivityOrganizerLocalServiceUtil
			.fetchActivityOrganizer(this.getOrganizerId());
	}

	/**
	 * Retourne le service du cours
	 */
	@Override
	public AssetCategory getService() {
		AssetCategory service = null;
		if (this.getServiceId() > 0) {
			service = AssetCategoryLocalServiceUtil
				.fetchAssetCategory(this.getServiceId());
		}
		return service;
	}

	/**
	 * Retourne l'activité du cours
	 */
	@Override
	public Activity getActivity() {
		return ActivityLocalServiceUtil.fetchActivity(this.getActivityId());
	}

	/**
	 * Retourne les lieux du cours
	 */
	@Override
	public List<ActivityCoursePlace> getActivityCoursePlaces() {
		return ActivityCoursePlaceLocalServiceUtil
			.getByActivityCourse(this.getActivityCourseId());
	}

	/**
	 * Retourne la version live de cette entité
	 */
	@Override
	public ActivityCourse getLiveVersion() {
		long groupId = this.getGroupId();
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		if (group == null || !group.isStagingGroup()) {
			return null;
		}
		long liveGroupId = group.getLiveGroupId();
		ActivityCourse liveActivity = ActivityCourseLocalServiceUtil
			.fetchActivityCourseByUuidAndGroupId(this.getUuid(), liveGroupId);
		return liveActivity;
	}

	/**
	 * Retourne l'AssetEntry rattaché à cette entité
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil
			.fetchEntry(ActivityCourse.class.getName(), this.getPrimaryKey());
	}

	/**
	 * Renvoie la liste des AssetCategory rattachées à cette entité (via
	 * l'assetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper
			.getAssetEntryCategories(this.getAssetEntry());
	}
}