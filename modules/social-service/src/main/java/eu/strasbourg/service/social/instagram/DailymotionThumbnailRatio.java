package eu.strasbourg.service.social.instagram;

public enum DailymotionThumbnailRatio {
	
	ORIGINAL("original"), 
	WIDESCREEN("widescreen"), 
	SQUARE("square");

	private String name = "";

	DailymotionThumbnailRatio(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

}
