package eu.strasbourg.service.social.impl.twitter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;

import eu.strasbourg.service.social.twitter.Tweet;
import eu.strasbourg.utils.StrasbourgPropsUtil;
import twitter4j.MediaEntity;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterClient {

	private static String twitterConsumerKey;
	private static String twitterConsumerSecret;

	private static String twitterAccessToken;
	private static String twitterAccessTokenSecret;

	private static Twitter twitter;

	static {
		try {
			// Initialisation du wrapper de l'API Twitter
			twitterConsumerKey = StrasbourgPropsUtil.getTwitterConsumerKey();
			twitterConsumerSecret = StrasbourgPropsUtil.getTwitterConsumerSecret();
			twitterAccessToken = StrasbourgPropsUtil.getTwitterToken();
			twitterAccessTokenSecret = StrasbourgPropsUtil.getTwitterTokenSecret();
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey(twitterConsumerKey)
					.setOAuthConsumerSecret(twitterConsumerSecret).setOAuthAccessToken(twitterAccessToken)
					.setOAuthAccessTokenSecret(twitterAccessTokenSecret).setTweetModeExtended(true);
			TwitterFactory tf = new TwitterFactory(cb.build());
			twitter = tf.getInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Tweet> getUserTimeline(String username, int count) {

		Object timelineFromCache = MultiVMPoolUtil.getPortalCache("twitter_cache").get(username);
		Object lastTimelineUpdate = MultiVMPoolUtil.getPortalCache("twitter_cache").get(username + "_last_update");
		if (timelineFromCache != null && lastTimelineUpdate != null) {
			long now = new Date().getTime();
			long timeBeforeNextUpdate = 100 - (now - ((Long) lastTimelineUpdate)) / 1000;
			if (timeBeforeNextUpdate > 0) {
				return (List<Tweet>) timelineFromCache;
			}
		}

		List<Tweet> tweets = new ArrayList<Tweet>();
		try {
			List<Status> statusList = twitter.getUserTimeline(username);
			int i = 0;
			for (Status status : statusList) {
				Tweet tweet = new Tweet();
				tweet.setDate(status.getCreatedAt());
				tweet.setScreenName(status.getUser().getScreenName());
				tweet.setUsername(status.getUser().getName());
				tweet.setRetweet(status.getRetweetedStatus() != null);
				tweet.setUrl("https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId());
				if (tweet.isRetweet()) {
					tweet.setRetweetUserName(status.getRetweetedStatus().getUser().getName());
					tweet.setRetweetScreenName(status.getRetweetedStatus().getUser().getScreenName());
					tweet.setContent(status.getRetweetedStatus().getText());

					// Checks for images posted using twitter API
					MediaEntity[] medias = status.getRetweetedStatus().getMediaEntities();
					if (medias.length > 0) {
						for (MediaEntity media : medias) {
							if (media.getType().equals("photo")) {
								tweet.setImageURL(media.getMediaURLHttps().toString()+":small");
								break;
							}
						}
					}
					// Checks for images posted using other APIs
					/*
					else {
						URLEntity[] entities = status.getRetweetedStatus().getURLEntities();
						if (entities.length > 0) {
							URLEntity entity = entities[0];
							if (entity.getExpandedURL() != null) {
								tweet.setImageURL(entity.getExpandedURL().toString());
							} else {
								if (entity.getDisplayURL() != null) {
									tweet.setImageURL(entity.getDisplayURL().toString());
								}
							}
						}
					}
					*/

				} else {
					tweet.setContent(status.getText());

					// Checks for images posted using twitter API
					MediaEntity[] medias = status.getMediaEntities();
					if (medias != null) {
						for (MediaEntity media : medias) {
							if (media.getType().equals("photo")) {
								tweet.setImageURL(media.getMediaURLHttps().toString()+":small");
								break;
							}
						}
					}

					// Checks for images posted using other APIs
					/*
					else {
						URLEntity[] entities = status.getURLEntities();
						if (entities.length > 0) {
							URLEntity entity = entities[0];
							if (entity.getExpandedURL() != null) {
								tweet.setImageURL(entity.getExpandedURL().toString());
							} else {
								if (entity.getDisplayURL() != null) {
									tweet.setImageURL(entity.getDisplayURL().toString());
								}
							}
						}
					}
					*/
				}
				tweets.add(tweet);
				i++;
				if (i >= count) {
					break;
				}
			}
			MultiVMPoolUtil.getPortalCache("twitter_cache").remove(username);
			MultiVMPoolUtil.getPortalCache("twitter_cache").remove(username + "_last_update");
			MultiVMPoolUtil.getPortalCache("twitter_cache").put(username, (Serializable) tweets);
			MultiVMPoolUtil.getPortalCache("twitter_cache").put(username + "_last_update", new Date().getTime());
			return tweets;
		} catch (TwitterException e) {
			e.printStackTrace();
			// Si on a du cache, on le renvoie
			if (timelineFromCache != null) {
				return (List<Tweet>) timelineFromCache;
			}
			return tweets;
		}
	}

}
