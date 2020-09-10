package eu.strasbourg.portlet.offer;

public class Application {

	String title;
	String url;
	String codeSuivi;
	String date;
	String status;
	
	public Application(String title, String url, String codeSuivi, String date, String status) {
		super();
		this.title = title;
		this.url = url;
		this.codeSuivi = codeSuivi;
		this.date = date;
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCodeSuivi() {
		return codeSuivi;
	}

	public void setCodeSuivi(String codeSuivi) {
		this.codeSuivi = codeSuivi;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

}
