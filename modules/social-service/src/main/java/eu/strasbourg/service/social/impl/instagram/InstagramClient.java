package eu.strasbourg.service.social.impl.instagram;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import eu.strasbourg.service.social.impl.twitter.twemoji.Twemoji;
import eu.strasbourg.utils.JSONHelper;
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

	public static List<SocialPost> getInstagramPosts(String mediaId, String token, int count) {

		Object timelineFromCache = MultiVMPoolUtil
				.getPortalCache("instagram_cache").get(token);
		Object lastTimelineUpdate = MultiVMPoolUtil
				.getPortalCache("instagram_cache").get(token + "_last_update");
		if (timelineFromCache != null && lastTimelineUpdate != null) {
			long now = new Date().getTime();
			long timeBeforeNextUpdate = 100
					- (now - ((Long) lastTimelineUpdate)) / 1000;
			if (timeBeforeNextUpdate > 0) {
				return (List<SocialPost>) timelineFromCache;
			}
		}

		Object[] stringData = {token};
		String apiURL = "https://graph.instagram.com/me?access_token=%s&fields=media&limit=50";
		apiURL = String.format(apiURL, stringData);

		List<SocialPost> posts = new ArrayList<SocialPost>();
		try {
			JSONObject json = JSONHelper.readJsonFromURL(apiURL);

			JSONArray jsonPostList = json.getJSONArray("data");

			for (int i = 0; i < jsonPostList.length(); i++) {
				JSONObject jsonPost = jsonPostList.getJSONObject(i);
				// type de média
				String type = jsonPost.getString("media_type");
				if(type.equals("IMAGE")) {

					SocialPost post = new SocialPost();
					post.setSocialMedia(SocialMedia.INSTAGRAM);
					// Username
					String username = jsonPost.getString("username");
					post.setUsername(username);

					// Image
					String imageURL = jsonPost.getString("media_url");
					post.setImageURL(imageURL);

					// Texte
					String caption = jsonPost.getString("caption");
					post.setContent(Twemoji.parse(caption));

					// Date
					String formattedDate = jsonPost.getString("timestamp");
					LocalDateTime dateTime = LocalDateTime.parse(formattedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"));
					Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
					post.setDate(date);

					// URL
					String url = jsonPost.getString("permalink");
					post.setUrl(url);

					posts.add(post);
				}
			}
		} catch (JSONException | IOException e) {
			log.error(e);
		}

		MultiVMPoolUtil.getPortalCache("instagram_cache").remove(token);
		MultiVMPoolUtil.getPortalCache("instagram_cache")
				.remove(token + "_last_update");
		MultiVMPoolUtil.getPortalCache("instagram_cache").put(token,
				(Serializable) posts);
		MultiVMPoolUtil.getPortalCache("instagram_cache")
				.put(token + "_last_update", new Date().getTime());

		return posts;
	}

}
