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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.activity.model.Activity;
import eu.strasbourg.service.activity.model.ActivityCourse;
import eu.strasbourg.service.activity.service.ActivityCourseLocalServiceUtil;
import eu.strasbourg.service.activity.service.ActivityLocalServiceUtil;
import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.service.VideoLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

/**
 * The extended model implementation for the Activity service. Represents a row
 * in the &quot;activity_Activity&quot; database table, with each column mapped
 * to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.activity.model.Activity} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class ActivityImpl extends ActivityBaseImpl {
	private static final long serialVersionUID = -4548591813164998186L;

	public ActivityImpl() {
	}

	/**
	 * Retourne les cours de l'activité
	 */
	@Override
	public List<ActivityCourse> getActivityCourses() {
		return ActivityCourseLocalServiceUtil.getByActivity(this.getActivityId());
	}

	/**
	 * Retourne les cours publiés du lieu
	 */
	@Override
	public List<ActivityCourse> getPublishedActivityCourses() {
		return ActivityCourseLocalServiceUtil.getByActivity(this.getActivityId())
			.stream().filter(c -> c.isApproved()).collect(Collectors.toList());
	}

	/**
	 * Retourne la version live de cette entité
	 * 
	 * @return
	 */
	@Override
	public Activity getLiveVersion() {
		long groupId = this.getGroupId();
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		if (group == null || !group.isStagingGroup()) {
			return null;
		}
		long liveGroupId = group.getLiveGroupId();
		Activity liveActivity = ActivityLocalServiceUtil
			.fetchActivityByUuidAndGroupId(this.getUuid(), liveGroupId);
		return liveActivity;
	}

	/**
	 * Retourne l'AssetEntry rattaché à cette entité
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Activity.class.getName(),
			this.getActivityId());
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

	/**
	 * Retourne les types de l'activité
	 */
	@Override
	public List<AssetCategory> getTypes() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
			this.getAssetEntry(), VocabularyNames.ACTIVITY_TYPE);
	}

	/**
	 * Retourne le texte à afficher pour les types de l'activité
	 */
	@Override
	public String getTypesLabel(Locale locale) {
		List<AssetCategory> types = this.getTypes();
		String label = "";
		for (AssetCategory type : types) {
			if (label.length() > 0) {
				label += ", ";
			}
			label += type.getTitle(locale);
		}
		return label;
	}

	/**
	 * Retourne les publics de l'activité
	 */
	@Override
	public List<AssetCategory> getPublics() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
			this.getAssetEntry(), VocabularyNames.ACTIVITY_PUBLIC);
	}

	/**
	 * Retourne le texte à afficher pour les publics de l'activité
	 */
	@Override
	public String getPublicsLabel(Locale locale) {
		List<AssetCategory> publics = this.getPublics();
		String label = "";
		for (AssetCategory publicCategory : publics) {
			if (label.length() > 0) {
				label += ", ";
			}
			label += publicCategory.getTitle(locale);
		}
		return label;
	}
	
	/**
	 * Retourne l'URL de l'image
	 */
	@Override
	public String getImageURL() {
		return FileEntryHelper.getFileEntryURL(this.getImageId());
	}

	/**
	 * Retourne la liste des URL publiques des images additionnelles
	 */
	@Override
	public List<String> getImagesURLs() {
		List<String> URLs = new ArrayList<String>();
		for (String imageIdStr : this.getImagesIds().split(",")) {
			Long imageId = GetterUtil.getLong(imageIdStr);
			if (Validator.isNotNull(imageId)) {
				String imageURL = FileEntryHelper.getFileEntryURL(imageId);
				URLs.add(imageURL);
			}
		}
		return URLs;
	}
	
	/**
	 * Retourne la liste des vidéos
	 */
	@Override
	public List<Video> getVideos() {
		List<Video> videos = new ArrayList<Video>();
		for (String videoIdsStr : this.getVideosIds().split(",")) {
			Long videoId = GetterUtil.getLong(videoIdsStr);
			Video video = VideoLocalServiceUtil.fetchVideo(videoId);
			if (video != null) {
				videos.add(video);
			}
		}
		return videos;
	}
	
	/**
	 * Retourne la liste des URLs des documents
	 */
	@Override
	public List<String> getFilesURLs() {
		List<String> URLs = new ArrayList<String>();
		for (String fileIdStr : this.getFilesIds().split(",")) {
			Long fileId = GetterUtil.getLong(fileIdStr);
			if (Validator.isNotNull(fileId)) {
				String fileURL = FileEntryHelper.getFileEntryURL(fileId);
				URLs.add(fileURL);
			}
		}
		return URLs;
	}

}