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
import java.util.Locale;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.service.VideoGalleryLocalServiceUtil;
import eu.strasbourg.service.video.service.VideoLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;

/**
 * The extended model implementation for the Video service. Represents a row in
 * the &quot;video_Video&quot; database table, with each column mapped to a
 * property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class.
 * Whenever methods are added, rerun ServiceBuilder to copy their definitions
 * into the {@link eu.strasbourg.service.video.model.Video} interface.
 * </p>
 *
 * @author BenjaminBini
 */
@ProviderType
public class VideoImpl extends VideoBaseImpl {

	private static final long serialVersionUID = 2515513279571209485L;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a video
	 * model instance should use the {@link
	 * eu.strasbourg.service.video.model.Video} interface instead.
	 */
	public VideoImpl() {
	}

	/**
	 * Retourne l'AssetEntry correspondant à cet item
	 */
	@Override
	public AssetEntry getAssetEntry() {
		return AssetEntryLocalServiceUtil.fetchEntry(Video.class.getName(),
			this.getVideoId());
	}

	/**
	 * Retourne la liste des AssetCategory correspondant à cet item (via
	 * l'AssetEntry)
	 */
	@Override
	public List<AssetCategory> getCategories() {
		return AssetVocabularyHelper
			.getAssetEntryCategories(this.getAssetEntry());
	}

	/**
	 * Renvoie l'URL de l'image à partir de l'id du DLFileEntry
	 */
	@Override
	public String getImageURL() {
		return FileEntryHelper.getFileEntryURL(this.getImageId());
	}

	/**
	 * Retourne la liste des galeries de la vidéo
	 */
	@Override
	public List<VideoGallery> getVideoGalleries() {
		return VideoGalleryLocalServiceUtil
			.getVideoVideoGalleries(this.getVideoId());
	}

	/**
	 * Retourne la liste des ids des galeries de la vidéo
	 */
	@Override
	public String getVideoGalleriesIds() {
		List<VideoGallery> galleries = this.getVideoGalleries();
		String ids = "";
		for (VideoGallery gallery : galleries) {
			if (ids.length() > 0) {
				ids += ",";
			}
			ids += gallery.getGalleryId();
		}
		return ids;
	}
	
	/**
	 * Renvoie la liste des galeries vidéos publiées de la
	 */
	@Override
	public List<VideoGallery> getPublishedVideoGalleries() {
		List<VideoGallery> galleries = this.getVideoGalleries();
		List<VideoGallery> result = new ArrayList<VideoGallery>();
		for (VideoGallery gallery : galleries) {
			if (gallery.isApproved()) {
				result.add(gallery);
			}
		}
		return result;
	}

	/**
	 * Retourne la version live de la vidéo si elle existe, null sinon
	 */
	@Override
	public Video getLiveVersion() {
		long groupId = this.getGroupId();
		Group group = GroupLocalServiceUtil.fetchGroup(groupId);
		if (group == null || !group.isStagingGroup()) {
			return null;
		}
		long liveGroupId = group.getLiveGroupId();
		Video liveVideo = VideoLocalServiceUtil
			.fetchVideoByUuidAndGroupId(this.getUuid(), liveGroupId);
		return liveVideo;
	}

	/**
	 * Retourne le code html embed du player si le champ "source" est un lien
	 * vers une vidéo Dailymotion, YouTube ou Vimeo, retourne le contenu du
	 * champ source sinon
	 */
	@Override
	public String getPlayer(Locale locale) {
		String player = "";
		String videoUrl = this.getSource(locale);
		if (videoUrl.contains("dailymotion.")) {
			String[] videoUrlParts = videoUrl.split("/");
			String videoId = videoUrlParts[videoUrlParts.length - 1];
			player = "<iframe src=\"//www.dailymotion.com/embed/video/"
				+ videoId
				+ "?api=postMessage&quality=1080&wmode=opaque\" width=\"auto\" height=\"auto\" frameborder=\"0\" allowfullscreen></iframe>";
		} else if (videoUrl.contains("youtube.")) {
			String[] videoUrlParts = videoUrl.split("v=");
			String videoId = videoUrlParts[videoUrlParts.length - 1];
			player = "<iframe id=\"youtubePlayer\" width=\"auto\" height=\"auto\" src=\"https://www.youtube.com/embed/"
				+ videoId
				+ "?enablejsapi=1\" frameborder=\"0\" allowfullscreen></iframe>";
		} else if (videoUrl.contains("vimeo.")) {
			String[] videoUrlParts = videoUrl.split("/");
			String videoId = videoUrlParts[videoUrlParts.length - 1];
			player = "<iframe src=\"https://player.vimeo.com/video/" + videoId
				+ "?title=0&byline=0&portrait=0&api=1\" width=\"auto\" height=\"auto\" frameborder=\"0\" allowfullscreen></iframe>";
		} else {
			player = this.getSource(locale);
		}

		return player;
	}
	
	/**
	 * Retourne l'URL de téléchargement du fichier de transcription
	 */
	@Override
	public String getTranscriptionDownloadURL() {
		return FileEntryHelper
			.getFileEntryURL(GetterUtil.getLong(this.getTranscriptionFileId()));
	}
}