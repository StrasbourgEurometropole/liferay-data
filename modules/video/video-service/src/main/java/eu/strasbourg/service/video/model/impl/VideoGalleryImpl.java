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

package eu.strasbourg.service.video.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.service.VideoGalleryLocalServiceUtil;
import eu.strasbourg.service.video.service.VideoLocalServiceUtil;
import eu.strasbourg.utils.FileEntryHelper;

/**
 * The extended model implementation for the VideoGallery service. Represents a row in the &quot;video_VideoGallery&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link eu.strasbourg.service.video.model.VideoGallery} interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class VideoGalleryImpl extends VideoGalleryBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a video gallery model instance should use the {@link eu.strasbourg.service.video.model.VideoGallery} interface instead.
	 */
	public VideoGalleryImpl() {
	}
	
	/**
	 * Retourne l'AssetEntry correspondant à cet item
	 */
	@Override
	public AssetEntry getAssetEntry() throws PortalException {
		return AssetEntryLocalServiceUtil.getEntry(VideoGallery.class.getName(),
			this.getGalleryId());
	}
	
	/**
	 * Retourne la liste des AssetCategory correspondant à cet item (via l'AssetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() throws PortalException {
		long[] categoryIds = this.getAssetEntry().getCategoryIds();
		List<AssetCategory> categories = new ArrayList<AssetCategory>();
		for (long categoryId : categoryIds) {
			AssetCategory category = AssetCategoryLocalServiceUtil.getAssetCategory(categoryId);
			categories.add(category);
		}
		return categories;
	}
	
	/**
	 * Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	 * 
	 * @throws PortalException
	 * @throws NumberFormatException
	 */
	@Override
	public String getImageURL() {
		return FileEntryHelper.getFileEntryURL(this.getImageId());
	}
	
	/**
	 * Retourne la liste des vidéos de la galerie
	 */
	@Override
	public List<Video> getVideos() {
		return VideoLocalServiceUtil.getVideoGalleryVideos(this.getGalleryId());
	}
	
	/**
	 * Retourne la liste des ids des vidéos de la galerie
	 */
	@Override
	public String getVideosIds() {
		List<Video> videos = this.getVideos();
		String ids = "";
		for (Video video : videos) {
			if (ids.length() > 0) {
				ids += ",";
			}
			ids += video.getVideoId();
		}
		return ids;
	}

	/**
	 * Renvoie la version live de la galerie, si elle existe
	 */
	@Override
	public VideoGallery getLiveVersion() {
		long groupId = this.getGroupId();
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		if (group == null || !group.isStagingGroup()) {
			return null;
		}
		long liveGroupId = group.getLiveGroupId();
		VideoGallery liveGallery = VideoGalleryLocalServiceUtil.fetchVideoGalleryByUuidAndGroupId(this.getUuid(), liveGroupId);
		return liveGallery;
	}
}