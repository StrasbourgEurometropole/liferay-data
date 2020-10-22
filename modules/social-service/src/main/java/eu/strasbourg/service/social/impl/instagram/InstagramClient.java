package eu.strasbourg.service.social.impl.instagram;


import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.strasbourg.service.social.SocialPost;
import eu.strasbourg.service.social.impl.SocialMedia;
import eu.strasbourg.utils.JSONHelper;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InstagramClient {

	private static Log log = LogFactoryUtil.getLog(InstagramClient.class);

	public static List<SocialPost> getInstagramPosts(String token, int count) {


		Object timelineFromCache = MultiVMPoolUtil.getPortalCache("instagram_cache").get(token);
		Object lastTimelineUpdate = MultiVMPoolUtil.getPortalCache("instagram_cache").get(token + "_last_update");

		if (timelineFromCache != null && lastTimelineUpdate != null) {
			long now = new Date().getTime();
			//Cache de 5min car 200 appels (tout confondus, dont get:User/media + get:Media) autorisés par heure
			long timeBeforeNextUpdate = 300
					- (now - ((Long) lastTimelineUpdate)) / 1000;
			if (timeBeforeNextUpdate > 0) {
				return (List<SocialPost>) timelineFromCache;
			}
		}


		Object[] stringData = {token};
		String apiURL = "https://graph.instagram.com/me?access_token=%s&fields=media";
		apiURL = String.format(apiURL, stringData);

		List<SocialPost> posts = new ArrayList<SocialPost>();

		try {
			JSONObject json = JSONHelper.readJsonFromURL(apiURL);

			JSONArray jsonMediaIdList = json.getJSONObject("media").getJSONArray("data");

			for (int i = 0; i < count; i++) {
				JSONObject jsonMediaId = jsonMediaIdList.getJSONObject(i);

				// Récupération du média
				Object[] stringDataMedia = {jsonMediaId.getString("id"),token};
				String apiMediaURL = "https://graph.instagram.com/%s?access_token=%s&fields=username,media_type,media_url,thumbnail_url,caption,timestamp,permalink";
				apiMediaURL = String.format(apiMediaURL, stringDataMedia);
				JSONObject jsonMedia = JSONHelper.readJsonFromURL(apiMediaURL);

				SocialPost post = new SocialPost();
				post.setSocialMedia(SocialMedia.INSTAGRAM);
				// Username
				String username = jsonMedia.getString("username");
				post.setUsername(username);

				// Image
				// type de média
				String type = jsonMedia.getString("media_type");
				String imageURL = "";
				if(type.equals("VIDEO")) {
					imageURL = jsonMedia.getString("thumbnail_url");
				}else {
					imageURL = jsonMedia.getString("media_url");
				}
				post.setImageURL(imageURL);

				// Texte
				String caption = jsonMedia.getString("caption");
				post.setContent(caption);

				// Date
				String formattedDate = jsonMedia.getString("timestamp");
				LocalDateTime dateTime = LocalDateTime.parse(formattedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"));
				Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
				post.setDate(date);

				// URL
				String url = jsonMedia.getString("permalink");
				post.setUrl(url);

				posts.add(post);
			}
		} catch (JSONException | IOException e) {
			log.error(e);
		}

		MultiVMPoolUtil.getPortalCache("instagram_cache").remove(token);
		MultiVMPoolUtil.getPortalCache("instagram_cache")
				.remove(token + "_last_update");

		MultiVMPoolUtil.getPortalCache("instagram_cache")
				.put(token, (Serializable) posts);
		MultiVMPoolUtil.getPortalCache("instagram_cache")
				.put(token + "_last_update", new Date().getTime());

		return posts;
	}

}
