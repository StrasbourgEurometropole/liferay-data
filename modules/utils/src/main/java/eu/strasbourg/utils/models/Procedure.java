package eu.strasbourg.utils.models;

public class Procedure {

	private String name;
	private String status;
	private String url;

	public static Procedure fromValues(String name, String status, String url) {
		Procedure procedure = new Procedure();
		procedure.setName(name);
		procedure.setStatus(status);
		procedure.setUrl(url);
		return procedure;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

}
