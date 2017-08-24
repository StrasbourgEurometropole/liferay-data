package eu.strasbourg.service.social.twitter;

import eu.strasbourg.service.social.SocialPost;
import eu.strasbourg.service.social.impl.SocialMedia;
import eu.strasbourg.service.social.impl.twitter.twemoji.Twemoji;

public class Tweet extends SocialPost {

	private String screenName;
	private boolean retweet;
	private String retweetUserName;
	private String retweetScreenName;
	
	public Tweet() {
		this.socialMedia = SocialMedia.TWITTER;
	}

	@Override
	public void setContent(String content) {
		super.setContent(Twemoji.parse(content));
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public boolean isRetweet() {
		return retweet;
	}

	public void setRetweet(boolean retweet) {
		this.retweet = retweet;
	}

	public String getRetweetUserName() {
		return retweetUserName;
	}

	public void setRetweetUserName(String retweetUserName) {
		this.retweetUserName = retweetUserName;
	}

	public String getRetweetScreenName() {
		return retweetScreenName;
	}

	public void setRetweetScreenName(String retweetScreenName) {
		this.retweetScreenName = retweetScreenName;
	}

}
