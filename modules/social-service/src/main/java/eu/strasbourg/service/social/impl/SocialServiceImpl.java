package eu.strasbourg.service.social.impl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

import eu.strasbourg.service.social.SocialPost;
import eu.strasbourg.service.social.SocialService;
import eu.strasbourg.service.social.impl.dailymotion.DailymotionClient;
import eu.strasbourg.service.social.impl.facebook.FacebookClient;
import eu.strasbourg.service.social.impl.instagram.InstagramClient;
import eu.strasbourg.service.social.impl.twitter.TwitterClient;
import eu.strasbourg.service.social.instagram.DailymotionThumbnailRatio;
import eu.strasbourg.service.social.twitter.Tweet;

/**
 * @author 01i345
 */
@Component(
	immediate = true,
	property = {
	},
	service = SocialService.class
)
public class SocialServiceImpl implements SocialService {
	
	@Override
	public List<SocialPost> getFacebookPosts(String accessToken, int count) {
		return FacebookClient.getFacebookPosts(accessToken, count);
	}

	@Override
	public List<Tweet> getUserTweets(String username, int count) {
		return TwitterClient.getUserTimeline(username, count);
	}

	@Override
	public List<SocialPost> getInstagramPosts(String username, int count) {
		return InstagramClient.getInstagramPosts(username, count);
	}

	@Override
	public List<SocialPost> getDailymotionVideos(String accountId, int count, DailymotionThumbnailRatio ratio) {
		return DailymotionClient.getDailymotionVideos(accountId, count, ratio);
	}

}