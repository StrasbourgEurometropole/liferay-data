package eu.strasbourg.service.social;



import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;

import eu.strasbourg.service.social.impl.SocialMedia;

public class SocialPost {
	
	protected String content;
	protected String jsonEscapedContent;
	protected SocialMedia socialMedia;
	protected String imageURL;
	protected String url;
	protected String username;
	protected Date date;
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setJsonEscapedContent(String jsonEscapedContent) {
		this.jsonEscapedContent = jsonEscapedContent;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
		this.jsonEscapedContent = StringEscapeUtils.escapeJson(this.content);
	}
	public String getJsonEscapedContent() {
		return jsonEscapedContent;
	}
	public SocialMedia getSocialMedia() {
		return socialMedia;
	}
	public void setSocialMedia(SocialMedia socialMedia) {
		this.socialMedia = socialMedia;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
