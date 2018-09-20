package eu.strasbourg.service.social.impl.instagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jinstagram.Instagram;
import org.jinstagram.auth.model.Token;
import org.jinstagram.entity.common.Caption;
import org.jinstagram.entity.common.ImageData;
import org.jinstagram.entity.common.Images;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import eu.strasbourg.service.social.SocialPost;
import eu.strasbourg.service.social.impl.SocialMedia;

public class InstagramClient {

	private static Log log = LogFactoryUtil.getLog(InstagramClient.class);

	public static List<SocialPost> getInstagramPosts(String clientId, String clientSecret, String token, int count) {

		Object timelineFromCache = MultiVMPoolUtil.getPortalCache("instagram_cache").get(clientId);
		Object lastTimelineUpdate = MultiVMPoolUtil.getPortalCache("instagram_cache").get(clientId + "_last_update");
		if (timelineFromCache != null && lastTimelineUpdate != null) {
			long now = new Date().getTime();
			long timeBeforeNextUpdate = 100 - (now - ((Long) lastTimelineUpdate)) / 1000;
			if (timeBeforeNextUpdate > 0) {
				return (List<SocialPost>) timelineFromCache;
			}
		}

		Token accessToken = new Token(token, clientSecret);
		Instagram instagram = new Instagram(accessToken);
		List<SocialPost> posts = new ArrayList<SocialPost>();

		try {
			/*
			 * UserFeed userFeed = instagram.searchUser(username);
			 * List<UserFeedData> users = userFeed.getUserList();
			 * 
			 * if (users.size() == 0) { return posts; }
			 * 
			 * String userId = users.get(0).getId(); MediaFeed mediaFeed =
			 * instagram.getRecentMediaFeed(userId);
			 * 
			 */

			for (MediaFeedData mediaData : instagram.getUserRecentMedia().getData()) {
				SocialPost socialPost = new SocialPost();

				socialPost.setSocialMedia(SocialMedia.INSTAGRAM);

				// Username
				socialPost.setUsername(mediaData.getUser().getUserName());

				// Image
				Images images = mediaData.getImages();
				ImageData image = images.getLowResolution();
				if(image != null && (image.getImageHeight() <= 285 || image.getImageWidth() <= 285))
					image = images.getStandardResolution();
				socialPost.setImageURL(image.getImageUrl());

				// Texte
				Caption caption = mediaData.getCaption();
				if (caption != null) {
					socialPost.setContent(caption.getText());
				}

				// Date
				Date postDate = new Date(Long.parseLong(mediaData.getCreatedTime()) * 1000);
				socialPost.setDate(postDate);

				// URL
				socialPost.setUrl(mediaData.getLink());

				posts.add(socialPost);

				if (posts.size() >= count) {
					break;
				}
			}
		} catch (InstagramException e) {
			log.error(e);
		}

		MultiVMPoolUtil.getPortalCache("instagram_cache").remove(clientId);
		MultiVMPoolUtil.getPortalCache("instagram_cache").remove(clientId + "_last_update");
		MultiVMPoolUtil.getPortalCache("instagram_cache").put(clientId, (Serializable) posts);
		MultiVMPoolUtil.getPortalCache("instagram_cache").put(clientId + "_last_update", new Date().getTime());
		return posts;
	}

}
