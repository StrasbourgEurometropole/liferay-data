package eu.strasbourg.service.social.impl.instagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jinstagram.Instagram;
import org.jinstagram.auth.model.Token;
import org.jinstagram.entity.common.ImageData;
import org.jinstagram.entity.common.Images;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import eu.strasbourg.service.social.SocialPost;
import eu.strasbourg.service.social.impl.SocialMedia;
import eu.strasbourg.utils.StrasbourgPropsUtil;

public class InstagramClient {

	private static Log log = LogFactoryUtil.getLog(InstagramClient.class);

	public static List<SocialPost> getInstagramPosts(String username,
		int count) {

		Object timelineFromCache = MultiVMPoolUtil
			.getPortalCache("instagram_cache").get(username);
		Object lastTimelineUpdate = MultiVMPoolUtil
			.getPortalCache("instagram_cache").get(username + "_last_update");
		if (timelineFromCache != null && lastTimelineUpdate != null) {
			long now = new Date().getTime();
			long timeBeforeNextUpdate = 100
				- (now - ((Long) lastTimelineUpdate)) / 1000;
			if (timeBeforeNextUpdate > 0) {
				return (List<SocialPost>) timelineFromCache;
			}
		}
		
		Token accessToken = new Token(
			StrasbourgPropsUtil.getInstagramAccessToken(),
			StrasbourgPropsUtil.getInstagramClientSecret());
		Instagram instagram = new Instagram(accessToken);
		List<SocialPost> posts = new ArrayList<SocialPost>();

		try {
			/*
			UserFeed userFeed = instagram.searchUser(username);
			List<UserFeedData> users = userFeed.getUserList();

			if (users.size() == 0) {
				return posts;
			}

			String userId = users.get(0).getId();
			MediaFeed mediaFeed = instagram.getRecentMediaFeed(userId);

		 	*/
			
			for (MediaFeedData mediaData : instagram.getUserRecentMedia().getData()) {
				SocialPost socialPost = new SocialPost();
				
				socialPost.setSocialMedia(SocialMedia.INSTAGRAM);
				
				// Username
				socialPost.setUsername(username);

				// Image
				Images images = mediaData.getImages();
				ImageData image = images.getStandardResolution();
				socialPost.setImageURL(image.getImageUrl());

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

		MultiVMPoolUtil.getPortalCache("instagram_cache").remove(username);
		MultiVMPoolUtil.getPortalCache("instagram_cache")
			.remove(username + "_last_update");
		MultiVMPoolUtil.getPortalCache("instagram_cache").put(username,
			(Serializable) posts);
		MultiVMPoolUtil.getPortalCache("instagram_cache")
			.put(username + "_last_update", new Date().getTime());
		return posts;
	}

}
