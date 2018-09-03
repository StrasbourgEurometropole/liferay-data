/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package eu.strasbourg.service.video.model.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
import eu.strasbourg.service.like.model.Like;
import eu.strasbourg.service.like.service.LikeLocalServiceUtil;
import eu.strasbourg.service.video.model.Video;
import eu.strasbourg.service.video.model.VideoGallery;
import eu.strasbourg.service.video.service.VideoGalleryLocalServiceUtil;
import eu.strasbourg.service.video.service.VideoLocalServiceUtil;
import eu.strasbourg.utils.AssetVocabularyHelper;
import eu.strasbourg.utils.FileEntryHelper;
import eu.strasbourg.utils.JSONHelper;
import eu.strasbourg.utils.SearchHelper;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import eu.strasbourg.utils.constants.VocabularyNames;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

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
     * Retourne les thematiques de la vidéo
     */
    @Override
    public List<AssetCategory> getThematicCategories() {
        return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
                VocabularyNames.THEMATIC);
    }

    /**
     * Retourne les sous-sous-catégories 'Territoire' correspondant aux quartiers de la vidéo
     *
     * @return : la liste des catégories
     */
    @Override
    public List<AssetCategory> getDistrictCategories() {
        List<AssetCategory> territories = AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
                VocabularyNames.TERRITORY);
        List<AssetCategory> districts = new ArrayList<>();
        for (AssetCategory territory : territories) {
            try {
                if (territory.getAncestors().size() == 2) {
                    districts.add(territory);
                }
            } catch (PortalException e) {
                continue;
            }
        }
        return districts;
    }

    /**
     * Retourne le projet de la video
     */
    @Override
    public AssetCategory getProjectCategory() {
        return AssetVocabularyHelper.getAssetEntryCategoriesByVocabulary(this.getAssetEntry(),
                VocabularyNames.PROJECT).stream().findFirst().orElse(null);
    }

    /**
     * Retourne la liste des like/dislike de l'entité
     *
     * @see eu.strasbourg.service.like.model.LikeType
     */
    @Override
    public List<Like> getLikesDislikes() {
        return LikeLocalServiceUtil.getByEntityIdAndTypeId(
                this.getVideoId(),
                3);
    }

    /**
     * Retourne la liste des likes de l'entité
     *
     * @see eu.strasbourg.service.like.model.LikeType
     */
    @Override
    public List<Like> getLikes() {
        return LikeLocalServiceUtil.getByEntityIdAndTypeIdAndIsDislike(
                this.getVideoId(),
                3,
                false);
    }

    /**
     * Retourne la liste des dislikes de l'entité
     *
     * @see eu.strasbourg.service.like.model.LikeType
     */
    @Override
    public List<Like> getDislikes() {
        return LikeLocalServiceUtil.getByEntityIdAndTypeIdAndIsDislike(
                this.getVideoId(),
                3,
                true);
    }

    /**
     * Retourne le nombre de likes/dislikes de l'entité
     *
     * @see eu.strasbourg.service.like.model.LikeType
     */
    @Override
    public int getNbLikesDislikes() {
        return LikeLocalServiceUtil.getByEntityIdAndTypeId(
                this.getVideoId(),
                3).size();
    }

    /**
     * Retourne le nombre de likes de l'entité
     *
     * @see eu.strasbourg.service.like.model.LikeType
     */
    @Override
    public int getNbLikes() {
        return LikeLocalServiceUtil.getByEntityIdAndTypeIdAndIsDislike(
                this.getVideoId(),
                3,
                false).size();
    }

    /**
     * Retourne le nombre de dislikes de l'entité
     *
     * @see eu.strasbourg.service.like.model.LikeType
     */
    @Override
    public int getNbDislikes() {
        return LikeLocalServiceUtil.getByEntityIdAndTypeIdAndIsDislike(
                this.getVideoId(),
                3,
                true).size();
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
     * Retourne le site cible de la vidéo
     */
    @Override
    public String getSiteVideo(String videoUrl) {
        String site = "";
        if (Validator.isNotNull(videoUrl)) {
            if (videoUrl.contains("dailymotion")) {
                if (videoUrl.contains("embed")) {
                    site = "embed/dailymotion";
                } else {
                    site = "dailymotion";
                }
            } else if (videoUrl.contains("youtube")) {
                if (videoUrl.contains("embed")) {
                    site = "embed/youtube";
                } else {
                    site = "youtube";
                }
            } else if (videoUrl.contains("vimeo")) {
                if (videoUrl.contains("vimeo.com/video")) {
                    site = "embed/vimeo";
                } else {
                    site = "vimeo";
                }
            }
        }

        return site;
    }

    /**
     * Retourne l'id de la vidéo
     */
    @Override
    public String getVideoId(String site, String videoUrl) {
        String videoId = "";
        if (Validator.isNotNull(site) && Validator.isNotNull(videoUrl)) {
            if (site.contains("embed")) {
                // vidéo embed youtube, dailymotion ou vimeo
                if (site.contains("dailymotion")) {
                    String[] videoUrlParts = videoUrl.split("video/");
                    videoUrlParts = videoUrlParts[videoUrlParts.length - 1].split("\\?");
                    videoId = videoUrlParts[0];
                } else if (site.contains("youtube")) {
                    String[] videoUrlParts = videoUrl.split("embed/");
                    videoUrlParts = videoUrlParts[videoUrlParts.length - 1].split("\\?");
                    videoId = videoUrlParts[0];
                } else if (site.contains("vimeo")) {
                    String[] videoUrlParts = videoUrl.split("video/");
                    videoUrlParts = videoUrlParts[videoUrlParts.length - 1].split("\\?");
                    videoId = videoUrlParts[0];
                }
            } else {
                if (site.equals("dailymotion")) {
                    String[] videoUrlParts = videoUrl.split("/");
                    videoUrlParts = videoUrlParts[videoUrlParts.length - 1].split("\\?");
                    videoId = videoUrlParts[0];
                } else if (site.equals("youtube")) {
                    String[] videoUrlParts = videoUrl.split("v=");
                    videoId = videoUrlParts[videoUrlParts.length - 1];
                } else if (site.equals("vimeo")) {
                    String[] videoUrlParts = videoUrl.split("/");
                    videoId = videoUrlParts[videoUrlParts.length - 1];
                }
            }
        }

        return videoId;
    }

    /**
     * Retourne le nombre de vues d'une vidéo
     */
    @Override
    public String getNbViews(String site, String videoId) {
        String nbViews = "";
        String url = "";
        if (Validator.isNotNull(site) && Validator.isNotNull(videoId)) {
            if (site.contains("dailymotion")) {
                url = StrasbourgPropsUtil.getDailymotionApiUrl().replace("[videoID]", videoId);
                try {
                    JSONObject json = JSONHelper.readJsonFromURL(url);
                    nbViews = json.getString("views_total");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (site.contains("youtube")) {
                url = StrasbourgPropsUtil.getYoutubeApiUrl().replace("[videoID]", videoId);
                try {
                    JSONObject json = JSONHelper.readJsonFromURL(url);
                    JSONObject item = json.getJSONArray("items").getJSONObject(0);
                    JSONObject statistics = item.getJSONObject("statistics");
                    nbViews = statistics.getString("viewCount");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return nbViews;
    }

    /**
     * Retourne le code embed de la vidéo si le champ "source" est un lien
     * vers une vidéo Dailymotion, YouTube ou Vimeo, retourne le contenu du
     * champ source sinon
     */
    @Override
    public String getEmbedURL(String site, String videoUrl) {
        String embedURL = "";
        if (Validator.isNotNull(site) && Validator.isNotNull(videoUrl)) {
            if (site.contains("embed")) {
                // vidéo embed youtube, dailymotion ou vimeo
                embedURL = videoUrl;
            } else {
                String videoId = getVideoId(site, videoUrl);
                if (site.equals("dailymotion")) {
                    embedURL = "//www.dailymotion.com/embed/video/" + videoId + "?api=postMessage&autoplay=1&quality=1080&wmode=opaque&muted=1";
                } else if (site.equals("youtube")) {
                    embedURL = "https://www.youtube.com/embed/" + videoId + "?enablejsapi=1";
                } else if (site.equals("vimeo")) {
                    embedURL = "https://player.vimeo.com/video/" + videoId + "?title=0&byline=0&portrait=0&api=1";
                }
            }
        }

        return embedURL;
    }

    /**
     * Retourne le code html embed du player si le champ "source" est un lien
     * vers une vidéo Dailymotion, YouTube ou Vimeo, retourne le contenu du
     * champ source sinon
     */
    @Override
    public String getPlayer(Locale locale) {
        String videoURL = this.getSource(locale);
        String site = getSiteVideo(videoURL);
        String player = "";
        if (site.contains("embed")) {
            // vidéo embed youtube, dailymotion ou vimeo
            player = videoURL;
        } else {
            String codeEmbed = getEmbedURL(site, videoURL);
            if (site.equals("dailymotion")) {
                player = "<iframe src=\"" + codeEmbed + "\" width=\"auto\" height=\"auto\" frameborder=\"0\" allowfullscreen></iframe>";
            } else if (site.equals("youtube")) {
                player = "<iframe src=\"" + codeEmbed + "\" id=\"youtubePlayer\" width=\"auto\" height=\"auto\" frameborder=\"0\" allowfullscreen></iframe>";
            } else if (site.equals("vimeo")) {
                player = "<iframe src=\"" + codeEmbed + "\" width=\"auto\" height=\"auto\" frameborder=\"0\" allowfullscreen></iframe>";
            }
        }

        return player;
    }
    
    /**
	 * Retourne le code html nécessaire à l'affichage de la vidéo dans le header du site vidéo
	 * et de son utilisation par les différentes API
	 */
    @Override
	public String getPlayerHeaderVideo(Locale locale) {
		String player = "";
		String videoUrl = this.getSource(locale);
		if (videoUrl.contains("dailymotion.")) {
			String[] videoUrlParts = videoUrl.split("/");
			String videoId = videoUrlParts[videoUrlParts.length - 1];            
			player = "<div id=\"player\" data-video-id=\""+videoId+"\"></div>";
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
     * Retourne le texte à afficher pour les chaînes de la vidéo
     */
    @Override
    public String getChannelsLabel(Locale locale) {
        List<AssetCategory> channels = this.getChaines();
        String label = "";
        for (AssetCategory channel : channels) {
            if (label.length() > 0) {
                label += ", ";
            }
            label += channel.getTitle(locale);
        }
        return label;
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
     *
     * @throws PortalException
     */
    @Override
    public List<Video> getSuggestions(Locale locale) {
        return getSuggestions(locale, 3);
    }

    /**
     * Retourne X suggestions max pour un thème appartenant à la vidéo en cours
     *
     * @throws PortalException
     */
    @Override
    public List<Video> getSuggestions(Locale locale, int nbSuggestions) {

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
        String[] className = {Video.class.getName()};

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
        for (
                AssetCategory assetCategory : themes)

        {
            categoriesIds[i] = assetCategory.getCategoryId();
            i++;
        }
        categoriesIdsList.add(categoriesIds);

        Hits hits = SearchHelper.getGlobalSearchHits(searchContext, className,
                groupId, globalGroupId, false, "", false, "",
                localDate, localDate, categoriesIdsList, prefilterCategoriesIds, prefilterTagsNames, locale, -1, -1,
                "", true);

        if (hits != null)

        {
            List<Video> videos = new ArrayList<Video>();
            for (Document document : hits.getDocs()) {
                Video video = VideoLocalServiceUtil.fetchVideo(
                        GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK)));
                if (video != null && video.getVideoId() != this.getVideoId()) {
                    videos.add(video);
                }
            }
            Collections.shuffle(videos);
            if (videos.size() > nbSuggestions) {
                for (int j = 0; j < nbSuggestions; j++) {
                    suggestions.add(videos.get(j));
                }
            } else {
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

    /**
     * Retourne la vidéo au format JSON
     */
    @Override
    public JSONObject toJSON() {
        JSONObject videoJSON = JSONFactoryUtil.createJSONObject();
        videoJSON.put("id", this.getVideoId());
        videoJSON.put("title", JSONHelper.getJSONFromI18nMap(this.getTitleMap()));
        videoJSON.put("description", JSONHelper.getJSONFromI18nMap(this.getDescriptionMap()));
        videoJSON.put("source", JSONHelper.getJSONFromI18nMap(this.getSourceMap()));

        Set<Locale> locales = this.getSourceMap().keySet();
        JSONObject playerJSON = JSONFactoryUtil.createJSONObject();
        for (Locale locale : locales) {
            playerJSON.put(locale.toString(), this.getPlayer(locale));
        }
        videoJSON.put("player", playerJSON);

        return videoJSON;
    }
}