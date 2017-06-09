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

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;
import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.service.VideoGalleryLocalServiceUtil;
import eu.strasbourg.service.video.service.VideoLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.constants.VocabularyNames;

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
		return FileEntryHelper.getFileEntryURL(
				GetterUtil.getLong(this.getTranscriptionFileId()));
	}

	/**
	 * Retourne les fournisseurs de la vidéo
	 */
	@Override
	public List<AssetCategory> getProviders() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
				this.getAssetEntry(), VocabularyNames.VIDEO_PROVIDER);
	}

	/**
	 * Retourne le texte à afficher pour les fournisseurs de la vidéo
	 */
	@Override
	public String getProvidersLabel(Locale locale) {
		List<AssetCategory> providers = this.getProviders();
		String label = "";
		for (AssetCategory provider : providers) {
			if (label.length() > 0) {
				label += ", ";
			}
			label += provider.getTitle(locale);
		}
		return label;
	}

	/**
	 * Retourne les chaînes de la vidéo
	 */
	@Override
	public List<AssetCategory> getChaines() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
				this.getAssetEntry(), VocabularyNames.CHAINE);
	}

	/**
	 * Retourne l'url de la catégorie de la vidéo
	 */
	@Override
	public String getCategoryURL(AssetCategory category)
			throws PortalException {

		String url = "";
		if (Validator.isNotNull(category)) {
			url = AssetVocabularyHelper
					.getCategoryProperty(category.getCategoryId(), "URL");
		}
		return url;
	}

	/**
	 * Retourne 3 suggestions max pour un thème appartenant à la vidéo en cours
	 * @throws PortalException 
	 */
	@Override
	public List<Video> getSuggestions(Locale locale) {

		List<Video> suggestions = new ArrayList<Video>();
		long[] assetCategoryIds = {};
		String[] assetTagNames = {};
		Map<String, Serializable> attributes = new HashMap<>();
		Layout layout = null;
		long scopeGroupId = 0;
		TimeZone timeZone = TimeZone.getDefault();
		SearchContext searchContext = SearchContextFactory
				.getInstance(assetCategoryIds, assetTagNames, attributes, this.getCompanyId(), "", layout, locale, scopeGroupId, timeZone, this.getUserId());
		
		// ClassNames
		String[] className = { Video.class.getName() };
		
		// Group Id
		long groupId = this.getGroupId();
		
		// Group Id global
		long globalGroupId = 0;
			
		LocalDate localDate = null;
		List<Long[]> categoriesIdsList = new ArrayList<Long[]>();
		List<Long[]> prefilterCategoriesIds = new ArrayList<Long[]>();
		String[] prefilterTagsNames = {};
		List<AssetCategory> themes = this.getThemes();
		Long[] categoriesIds = new Long[themes.size()];
		int i = 0;
		for (AssetCategory assetCategory : themes) {
			categoriesIds[i] = assetCategory.getCategoryId();
			i++;
		}
		categoriesIdsList.add(categoriesIds);

		Hits hits = SearchHelper.getGlobalSearchHits(searchContext, className,
				groupId, globalGroupId, false, "", false, "",
				localDate, localDate, categoriesIdsList, prefilterCategoriesIds, prefilterTagsNames, locale, -1, -1,
				"", true);

		if (hits != null) {
			List<Video> videos = new ArrayList<Video>();
			for (Document document : hits.getDocs()) {
				Video video = VideoLocalServiceUtil.fetchVideo(
						GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
				if (video != null && video.getVideoId() != this.getVideoId()) {
					videos.add(video);
				}
			}
			Collections.shuffle(videos);
			if (videos.size() > 3){
				suggestions.add(videos.get(0));
				suggestions.add(videos.get(1));
				suggestions.add(videos.get(2));
			}else{
				suggestions = videos;
			}
		}
		
		return suggestions;
	}

	/**
	 * Retourne les thèmes de la vidéo
	 */
	@Override
	public List<AssetCategory> getThemes() {
		return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(
				this.getAssetEntry(), VocabularyNames.VIDEO_THEME);
	}
}