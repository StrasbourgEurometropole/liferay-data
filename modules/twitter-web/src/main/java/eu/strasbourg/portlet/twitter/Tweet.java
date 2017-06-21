package eu.strasbourg.portlet.twitter;

import java.io.Serializable;
import java.util.Date;

import eu.strasbourg.portlet.twitter.autolink.Autolink;

public class Tweet implements Serializable {
	private static final long serialVersionUID = 1953550332174235144L;
	
	private String userName;
    private String screenName;
    private String text;
    private String url;
    private Date creationDate;
    private long displayDate;
    private String displayDateUnit;
    private boolean isRetweet;
    private String retweetUserName;
    private String retweetScreenName;
    
    public String getTextWithLinks() {
	String link = new Autolink().autoLink(text);
	return link;
    }
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getScreenName() {
        return screenName;
    }
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        
        long seconds = (new Date().getTime() - this.creationDate.getTime()) / 1000;
	long hours = seconds / 3600;
	long days = hours / 24;
	seconds = seconds % 3600;
	long minutes = seconds / 60;
	
	if (days >= 1) {
	    displayDate = days;
	    displayDateUnit = "days";
	} else if(hours >= 1) {
	    displayDate = hours;
	    if (hours == 1) {
		displayDateUnit = "hour";
	    } else {
		displayDateUnit = "hours";
	    }
	} else if (minutes >= 1) {
	    displayDate = minutes;
	    if (minutes == 1) {
		displayDateUnit = "minute";
	    } else {
		displayDateUnit = "minutes";
	    }
	} else {
	    displayDate = 0;
	    displayDateUnit = "now";
	}
    }
    public long getDisplayDate() {
	return displayDate;
    }
    
    public void setDisplayDate(long displayDate) {
        this.displayDate = displayDate;
    }
    public boolean isRetweet() {
        return isRetweet;
    }
    public void setRetweet(boolean isRetweet) {
        this.isRetweet = isRetweet;
    }
    public String getRetweetUserName() {
        return retweetUserName;
    }
    public void setRetweetUserName(String retweetUserName) {
        this.retweetUserName = retweetUserName;
    }
    public String getDisplayDateUnit() {
        return displayDateUnit;
    }
    public void setDisplayDateUnit(String displayDateUnit) {
        this.displayDateUnit = displayDateUnit;
    }
    public String getRetweetScreenName() {
        return retweetScreenName;
    }
    public void setRetweetScreenName(String retweetScreenName) {
        this.retweetScreenName = retweetScreenName;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
