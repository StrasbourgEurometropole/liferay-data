package eu.strasbourg.service.social.impl;

public enum SocialMedia {
	
	FACEBOOK("facebook"),
	TWITTER("twitter"),
	INSTAGRAM("instagram"),
	DAILYMOTION("dailymotion");
	
	private String name = "";
	
	SocialMedia(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}

}
