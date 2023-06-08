package eu.strasbourg.service.notification.model;

public enum NotificationChannel {
	EMAIL(1, "email"), PHONE(2, "phone");

	private int id;
	private String name;

	NotificationChannel(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static NotificationChannel get(int id) {
		for (NotificationChannel e : values()) {
			if (e.getId() == id) {
				return e;
			}
		}
		return null;
	}
}
