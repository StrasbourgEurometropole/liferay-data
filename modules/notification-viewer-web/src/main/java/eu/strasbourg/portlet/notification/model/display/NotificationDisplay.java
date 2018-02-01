package eu.strasbourg.portlet.notification.model.display;

//Simple holder de données pour l'affichage
public class NotificationDisplay {
	
	private String title;
	private String date;
	private boolean isRead;
	private Long notificationId;
	
	public String getDate() {
		return date;
	}
	public Long getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	
	
}
