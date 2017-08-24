package eu.strasbourg.service.social;

import java.util.List;

import eu.strasbourg.service.social.instagram.DailymotionThumbnailRatio;
import eu.strasbourg.service.social.twitter.Tweet;

public interface SocialService {

	List<SocialPost> getFacebookPosts(String accessToken, int count);
	
	List<Tweet> getUserTweets(String username, int count);
	
	List<SocialPost> getInstagramPosts(String username, int count);
	
	List<SocialPost> getDailymotionVideos(String accountId, int count, DailymotionThumbnailRatio ratio);
}
