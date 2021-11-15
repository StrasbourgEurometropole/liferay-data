package eu.strasbourg.service.csmap.utils;

import eu.strasbourg.utils.StrasbourgPropsUtil;
import twitter4j.GeoLocation;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;

public class TwitterUtil {

	private static String twitterConsumerKey;
	private static String twitterConsumerSecret;

	private static String twitterAccessToken;
	private static String twitterAccessTokenSecret;

	private static Twitter twitter;

	static {
		// Initialisation du wrapper de l'API Twitter
		twitterConsumerKey = StrasbourgPropsUtil.getTwitterConsumerKey();
		twitterConsumerSecret = StrasbourgPropsUtil.getTwitterConsumerSecret();
		twitterAccessToken = StrasbourgPropsUtil.getTwitterToken();
		twitterAccessTokenSecret = StrasbourgPropsUtil.getTwitterTokenSecret();
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(twitterConsumerKey)
			.setOAuthConsumerSecret(twitterConsumerSecret)
			.setOAuthAccessToken(twitterAccessToken)
			.setOAuthAccessTokenSecret(twitterAccessTokenSecret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}

	public static void sendTweet(String status) {
		try {
			twitter.updateStatus(status);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	public static void sendTweet(String status, File file, String url, int latitude, int longitude) {
		try {
			StatusUpdate su = new StatusUpdate(status);
			su.setMedia(file);
			su.setAttachmentUrl(url);
			su.setLocation(new GeoLocation(latitude, longitude));
			twitter.updateStatus(su);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}
